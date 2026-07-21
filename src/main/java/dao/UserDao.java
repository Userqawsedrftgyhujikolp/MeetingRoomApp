package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserDao {

	//コンストラクタ------
	private UserDao() {
	}

	//利用者IDとパスワードで利用者認証を行い，認証した利用者情報を返します。
	public static UserBean certificate(String id, String password) {
		if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}
		//データベース接続
		//String sql="SELECT*FROM user WHERE id=? and password=?";
		String sql = "SELECT * FROM user WHERE id = ? and password = ? and delete_flg = 0";
		// try-with-resources構文でリソースを自動的にクローズ
		try (
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// プレースホルダーに値を設定
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			//SQL文を実行して結果を取得
			try (ResultSet rs = pstmt.executeQuery()) {
				//結果セットをViewへ送るための準備
				while (rs.next()) {
					//結果セットから取得

					String id1 = rs.getString("id");
					String password1 = rs.getString("password");
					String name = rs.getString("name");
					String address = rs.getString("address");
					UserBean user = new UserBean(id1, password1, name, address);
					return user;

					//userData[2]=rs.getString("password");
				}
			}
			System.out.println("ドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		System.out.println("SQLに関するエラーです");

		//try-with-resourcesによりconnとpstmtは自動的にクローズされる
		return null;
	}

	public static void deleteUser(String id) throws ClassNotFoundException {
		//データベース接続
		String sql = "UPDATE user SET delete_flg = 1 WHERE id = ? ";
		//try-with-resources構文でリソースを自動的にクローズ
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//プレースホルダーに値セット
			try {
				pstmt.setString(1, id);

				int result = pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}

	public int Insert(UserBean user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO user ( id , password , name , address)VALUE( ? , ? , ? , ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql);
		{
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAddress());
			int result = pstmt.executeUpdate();
		}
		return 0;
	}

	public String GetMaxId(String year) throws ClassNotFoundException, SQLException {
		String sql = "SELECT MAX (id) FROM user WHERE id LIKE ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//プレースホルダーから値貰う(西暦２桁)
		pstmt.setString(1, year + "%");//Meetingroom貰うよ
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String MaxId = rs.getString(1);
			return MaxId;
		}
		return null;
	}
}
