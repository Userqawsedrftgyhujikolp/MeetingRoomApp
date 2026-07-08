import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBtest {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("idを入力してください:");
		String inputId = scanner.nextLine();
		String sql = "SELECT * FROM user WHERE id=?";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, inputId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						rs.getString("id") + "\t" +
								rs.getString("password") + "\t" +
								rs.getString("name") + "\t" +
								rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQLエラー");
		}
	}
}
