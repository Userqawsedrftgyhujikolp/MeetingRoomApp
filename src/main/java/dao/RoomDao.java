package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.RoomBean;

public class RoomDao {
	public static void main(String args[]) {
		try {
			RoomDao.findAll();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("クラスがが見つかりません。");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("SQLに関するえらーです。");
		}
	}

	//コンストラクタ
	private RoomDao() {
	}

	//メソッド-----すべての会議室を検索する
	public static RoomBean[] findAll() throws SQLException, ClassNotFoundException {
		//JDBC固定コンボ4つ
		String sql = "SELECT * FROM room";
		try (
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (
					ResultSet rs = pstmt.executeQuery()) {

				List<RoomBean> list = new ArrayList<RoomBean>();//空のリストを作って新しく部屋を用意した
				while (rs.next()) {
					RoomBean bean = new RoomBean(rs.getString("id"), rs.getString("name")); //DBから1つずつ取り出して箱に入れる
					list.add(bean);
				}
				if (list.size() == 0) { //もしリストが空なら
					return null; //nullを返すよ。
				}
				RoomBean[] result = new RoomBean[list.size()];//リストに入っているデータの数と同じサイズの配列を作った
				for (int i = 0; i < list.size(); ++i) {
					result[i] = list.get(i);
				}
				return list.toArray(new RoomBean[0]);

			} catch (SQLException e) {
				throw new SQLException();
			}

		}
	}
	
	public static RoomBean insertRoom(String floor, String name) throws SQLException {
		if(floor.length() == 1) {
			floor = "0"+floor;
		}else if(floor.length() != 2) {
			throw new IllegalArgumentException("正しくない階が入力されました");
		}
		try (Connection conn = ConnectionProvider.getConnection()) {
			String sql1 = "SELECT MAX(CAST(SUBSTRING(id, 3, 2) AS UNSIGNED)) AS id FROM room WHERE id LIKE ?";
			String sql2 = "INSERT INTO room (id, name )VALUES(?, ?)";
			try (PreparedStatement pstmt1 = conn.prepareStatement(sql1);
					PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
				pstmt1.setString(1, floor + "%");
				String maxId = null;
				String newId;
				try (ResultSet rs = pstmt1.executeQuery()) {
					if (rs.next() && rs.getObject("id") != null) {
						maxId = rs.getString("id");
					}
				}
				if (maxId != null) {
					int num = Integer.parseInt(maxId);
					num++;
					newId = floor + String.format("%02d", num);
				} else {
					newId = floor + "01";
				}

				pstmt2.setString(1, newId);
				pstmt2.setString(2, name);
				int result = pstmt2.executeUpdate();
				if(result != 0) {
					return new RoomBean(newId,name);
				}else {
					return null;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
