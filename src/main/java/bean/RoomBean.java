package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionProvider;

public class RoomBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;//バージョン管理
	private String id;
	private String name;

	//コンストラクタ
	public RoomBean() {
	}

	public RoomBean(String id, String name) {
		this.id = id;
		this.name = name;
	}
	//メソッド

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static void insertRoom(String floor, String name) throws SQLException {
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
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "RoomBean [id=" + id + ", name=" + name + ", toString()=" + super.toString() + "]";
	}

}
