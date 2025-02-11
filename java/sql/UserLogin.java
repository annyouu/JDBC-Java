package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import object.Product;
import object.User;

public class UserLogin {
	public User check_user(String user_id, String password) throws FileNotFoundException {
		
//		データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
//		実行するSQL文
		String loginuser_sql = "SELECT * FROM user_tb WHERE user_id = ? AND password = ?";
		 
		User usering = new User();
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement stmt = conn.prepareStatement(loginuser_sql);
			stmt.setString(1, user_id); 
			stmt.setString(2, password); 
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				usering.setId(rs.getInt("user_id"));
				usering.setName(rs.getString("name"));
				usering.setPassword(rs.getString("password"));
				usering.setLogin_flag(true);
			} else {
				usering.setLogin_flag(false);
			}
		} 
		catch(SQLException e) {
			System.out.println("ユーザー認証データベース接続エラー");
			e.printStackTrace();
			
		} 
		
		return usering;
		
	}
	public List<Product> getuserProductInfo(String admin_id) throws FileNotFoundException {
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
		String product_sql = "SELECT * FROM product_tb WHERE admin_id = ?";
		
		List<Product> pro_list = new ArrayList<Product>();
		
		try {
			// MySQLドライバを手動でロード（必要な場合）
			Class.forName("com.mysql.cj.jdbc.Driver");
//			データベースへ接続
			try (Connection conn = DriverManager.getConnection(url, user, pass)) {
				PreparedStatement stmt = conn.prepareStatement(product_sql);
				
				stmt.setString(1, admin_id);
				ResultSet rs = stmt.executeQuery();
				
//			　SQL実行後 リストに格納
				while(rs.next()) {
//					クラスのインスタンスに一時格納
					Product product_info = new Product();
					product_info.setProductid(rs.getInt("product_id"));
					product_info.setAdminid(rs.getInt("admin_id"));
					product_info.setProductName(rs.getString("product_name"));
					product_info.setCategory(rs.getString("category"));
					product_info.setExplanation(rs.getString("explanation"));
					product_info.setQuantity(rs.getInt("quantity"));
					product_info.setRegistered_time(rs.getDate("registered_time"));
					product_info.setUpdated_time(rs.getDate("updated_time"));
					
					pro_list.add(product_info);
				}
				
			} catch (SQLException e) {
				System.out.println("ユーザーデータベース接続エラー");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySQLドライバが見つかりません");
			e.printStackTrace();
		}
		
		
		return pro_list;
	}
	public boolean checkAdminExist(String admin_id) {
		DBconfig db_info = new DBconfig();
	    String url = db_info.getUrl();
	    String user = db_info.getUser();
	    String pass = db_info.getPassword();
	    
	    String query = "SELECT * FROM admin_tb WHERE admin_id = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url, user, pass);
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, Integer.parseInt(admin_id));
	        ResultSet rs = stmt.executeQuery();
	        
	        // 結果があれば管理者が存在する
	        return rs.next();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false; 
		
	}
		
}
	
