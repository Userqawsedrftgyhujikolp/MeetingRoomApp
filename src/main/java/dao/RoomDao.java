package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.RoomBean;

public class RoomDao {
	public static void main(String args[]) {
		try {
			RoomDao.findAll();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("クラスがが見つかりません。");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("SQLに関するえらーです。");
		}}
		//コンストラクタ
		private RoomDao() {}
		//メソッド-----すべての会議室を検索する
		public static RoomBean[] findAll() throws SQLException, ClassNotFoundException {
			//JDBC固定コンボ4つ
			String sql = "SELECT * FROM room"; 
		try(
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
		try(
			ResultSet rs = pstmt.executeQuery()){
			
			List <RoomBean> list = new ArrayList <RoomBean>();//空のリストを作って新しく部屋を用意した
			while(rs.next()) {
				RoomBean bean = new RoomBean(rs.getString("id"),rs.getString("name")); //DBから1つずつ取り出して箱に入れる
				list.add(bean);	
			}
			if(list.size() == 0) { //もしリストが空なら
				return null; //nullを返すよ。
			}
			RoomBean[] result = new RoomBean[list.size()];//リストに入っているデータの数と同じサイズの配列を作った
			for(int i = 0 ; i < list.size(); ++i){
				result[i] = list.get(i);
			}
				return list.toArray(new RoomBean[0]);
	
		}catch(SQLException e) {
			throw new SQLException();
		}
			
		}}}

