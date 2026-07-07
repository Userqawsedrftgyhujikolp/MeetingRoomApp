import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("ドライバが見つかりません");
			return;
		}
		String url = "jdbc:mysql://localhost:3306/book";
		String user = "root";
		String pass = "pass";
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			System.out.println("DB状態 -> "+conn.isClosed());
		}catch(SQLException e) {
			System.out.println("SQLエラー");
		}
	}

}
