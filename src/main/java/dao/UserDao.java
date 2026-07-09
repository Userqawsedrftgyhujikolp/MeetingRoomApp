package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class UserDao {


	//コンストラクタ------
	private UserDao(){}
	
	//利用者IDとパスワードで利用者認証を行い，認証した利用者情報を返します。
	public static Object certificate(String id,String password){
		if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            return null;
            
	//DB取得結果を格納する
	String[] userData=new String[3];
	
	//データベース接続
	String sql="SELECT*FROM user WHERE user_id=?";
	
	// try-with-resources構文でリソースを自動的にクローズ
	try (
            Connection conn = UserConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		// プレースホルダーに値を設定
        pstmt.setString(1,id);
        pstmt.setString(2,password);
        //SQL文を実行して結果を取得
        try(ResultSet rs=pstmt.executeQuery()){
        	//結果セットをViewへ送るための準備
        	while(rs.next()){
        		//結果セットから取得
        		userData[0]=rs.getString("user_id");
        		userData[1]=rs.getString("name");
        		userData[2]=rs.getString("password");
        	}
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }
    			System.out.println("ドライバが見つかりません");
	    }catch(SQLException e) {
    		e.printStackTrace();
    	 }
    			System.out.println("SQLに関するエラーです");		
        }
		//try-with-resourcesによりconnとpstmtは自動的にクローズされる
		return null;
	}
	}


