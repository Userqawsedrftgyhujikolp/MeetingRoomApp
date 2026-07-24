package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReservationBean;

public class ReservationDao {

	//	コンストラクタ
	private ReservationDao() {
	}

	//	メソッド
	//	利用日の予約日を検索
	public static List<ReservationBean> findByDate(String date) throws SQLException, ClassNotFoundException {
		//DB取得結果を格納するリスト
		List<ReservationBean> rList = new ArrayList<ReservationBean>();
		//データベース接続
		String sql = "SELECT * FROM reservation WHERE date = ?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//プレースホルダーに値を設定
			pstmt.setString(1, date);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					//ReservationBeanに引数を渡すためgetしたものを変数に格納
					int yoyakuid = rs.getInt("id");
					String yoyakudate = rs.getString("date");
					String yoyakuroomId = rs.getString("roomId");
					String yoyakustart = rs.getString("start");
					String yoyakuend = rs.getString("end");
					String yoyakuuserId = rs.getString("userId");
					//時刻の修正（HH:mm:ss -> HH:mm）
					yoyakustart = yoyakustart.substring(0, 5);
					yoyakuend = yoyakuend.substring(0, 5);
					
					ReservationBean kensaku = new ReservationBean(yoyakuid, yoyakuroomId, yoyakudate ,yoyakustart,
							yoyakuend, yoyakuuserId);
					rList.add(kensaku);
				}
				return rList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 指定したユーザーが行った指定時刻以降の予約を返します
	 * @param userId ユーザーID
	 * @param date 日付
	 * @param time 時刻
	 * @return 予約一覧
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<ReservationBean> findById(String userId, String date, String time) throws SQLException, ClassNotFoundException {
		List<ReservationBean> rList = new ArrayList<ReservationBean>();
		//データベース接続
		String sql = "SELECT * FROM reservation WHERE userId = ? and (date > ? or (date = ? and start >= ?)) ORDER BY date ASC, start ASC";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//プレースホルダーに値を設定
			pstmt.setString(1, userId);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			pstmt.setString(4, time);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					//ReservationBeanに引数を渡すためgetしたものを変数に格納
					int yoyakuid = rs.getInt("id");
					String yoyakudate = rs.getString("date");
					String yoyakuroomId = rs.getString("roomId");
					String yoyakustart = rs.getString("start");
					String yoyakuend = rs.getString("end");
					String yoyakuuserId = rs.getString("userId");
					//時刻の修正（HH:mm:ss -> HH:mm）
					yoyakustart = yoyakustart.substring(0, 5);
					yoyakuend = yoyakuend.substring(0, 5);
					
					ReservationBean kensaku = new ReservationBean(yoyakuid, yoyakuroomId, yoyakudate ,yoyakustart,
							yoyakuend, yoyakuuserId);
					rList.add(kensaku);
				}
				return rList;
			}
		}
	}
	
	//	予約を追加するメソッド
	public static boolean insert(ReservationBean reservation) throws ClassNotFoundException {
		String sql = "INSERT INTO reservation(roomId,date,start,end,userId)VALUES(?,?,?,?,?)";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//プレースホルダーに値を設定
			pstmt.setString(1, reservation.getRoomId());
			pstmt.setString(2, reservation.getDate());
			pstmt.setString(3, reservation.getStart());
			pstmt.setString(4, reservation.getEnd());
			pstmt.setString(5, reservation.getUserId());
			int ret = pstmt.executeUpdate();
			//更新したデータが0でなければtrue
			return ret != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//	予約を削除するメソッド
	public static boolean delete(ReservationBean reservation) throws ClassNotFoundException {
		String sql = "DELETE FROM reservation WHERE id = ?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//プレースホルダーに値を設定
			pstmt.setInt(1, reservation.getId());
			int ret = 0;
			//executeQueryメソッド実行する
			ret = pstmt.executeUpdate();
			System.out.println("予約を" + ret + "件、キャンセルしました");
			return ret != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
};