import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionProvider;

public class DBtest {
	public static void main(String[] args) throws ClassNotFoundException {
		String sql1 = "SELECT * FROM user";
		String sql2 = "SELECT * FROM room";
		try (Connection conn = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							rs.getString("id") + "\t" +
									rs.getString("password") + "\t" +
									rs.getString("name") + "\t" +
									rs.getString("address"));
				}
			}
			System.out.println("------------------------");
			try(PreparedStatement pstmt = conn.prepareStatement(sql2)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(
							rs.getString("id") + "\t" +
									rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLエラー");
		}
	}
}
