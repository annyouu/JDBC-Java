package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import object.Product;

public class Search {
	public List<Product> product_search(String name) {
		List<Product> products = new ArrayList<Product>();
//		データベースの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
		String search_sql ="SELECT * FROM product_tb WHERE product_name LIKE ?";
		
		try (Connection conn = DriverManager.getConnection(url,user,pass)) {
			// オートコミット機能を無効化
			conn.setAutoCommit(false);
			try (PreparedStatement stmt = conn.prepareStatement(search_sql)) {
				stmt.setString(1, "%" + name + "%");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Product product = new Product();
					product.setProductid(rs.getInt("product_id"));
					product.setProductName(rs.getString("product_name"));
					product.setCategory(rs.getString("category"));
					product.setExplanation(rs.getString("explanation"));
					product.setQuantity(rs.getInt("quantity"));
					product.setRegistered_time(rs.getDate("registered_time"));
					product.setUpdated_time(rs.getDate("updated_time"));
		            products.add(product);
				}
				// コミット処理
                conn.commit();
				
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("検索SQLのロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return products;
		
	}

}