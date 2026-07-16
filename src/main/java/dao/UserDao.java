package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean; 

public class UserDao {


	//コンストラクタ------
	private UserDao(){}
	
	//利用者IDとパスワードで利用者認証を行い，認証した利用者情報を返します。
	public static UserBean certificate(String id,String password){
		if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            return null;
		}    
	//データベース接続
	String sql="SELECT*FROM user WHERE id=? and password=?";
	
	// try-with-resources構文でリソースを自動的にクローズ
	try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		// プレースホルダーに値を設定
        pstmt.setString(1,id);
        pstmt.setString(2,password);
        //SQL文を実行して結果を取得
        try(ResultSet rs=pstmt.executeQuery()){
        	//結果セットをViewへ送るための準備
        	while(rs.next()){
        		//結果セットから取得
        		
        		String id1 = rs.getString("id");
        		String password1 = rs.getString("password");
        		String name = rs.getString("name");
        		String address = rs.getString("address");
        		UserBean user = new UserBean(id1,password1,name,address);
        		return user;
        		
        		//userData[2]=rs.getString("password");
        	}
        }
    			System.out.println("ドライバが見つかりません");
	    }catch(SQLException e) {
    		e.printStackTrace();
    	 } catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
    			System.out.println("SQLに関するエラーです");		
        
		//try-with-resourcesによりconnとpstmtは自動的にクローズされる
		return null;
	}
	public static void deleteUser(String id) {
	//データベース接続
	Strig sql = "DELETE * FROM user WHERE id = ?"
	//try-with-resources構文でリソースを自動的にクローズ
	try(
			Connection conn = ConnectionProvider.getConnection();
			Prepared Statement pstmt = conn.prepareStatement(sql)	
	}
	}


