package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.RoomBean;

public class RoomDao {
		//コンストラクタ
		private RoomDao() {}
		//メソッド-----すべての会議室を検索する
		public static RoomBean[] findAll() {
			//JDBC固定コンボ4つ
			String sql = "SELECT * FROM room"; 
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			List <RoomBean> list = new ArrayList <RoomBean>();
			while(rs.next()) {
				RoomBean bean = new RoomBean(rs.getString("id"),rs.getString("name"));
				list.add(bean);	
			}
			if(list.size() == 0) {
				return null;
			}
			RoomBean[] result = new RoomBean[list.size()];
			for(int i = 0 ; i < list.size(); ++i){
				result[i] = list.get(i);
			}
				return list.toArray(new RoomBean[0]);
	
		}}

