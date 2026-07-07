package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean; 

public class UserDao {

	//コンストラクタ------
	UserDao(){}
	//メソッド------
	public static UserBean certificate(String id,String password){
	//DB取得結果を格納する
	User=new User();
	//データベース接続
	String sql="INSERT INTo user(id,password)VALUES(?,?)";
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
        }
	}
	}

}
