package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBconfig;
import object.Product;

public class SelectOneProduct {
	public Product get_One_Product_Info(int product_id) throws FileNotFoundException {
//		データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
		Product one_product = new Product();
		
//		実行SQL
		String one_product_sql = "SELECT * FROM product_tb WHERE product_id = ?";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement stmt = conn.prepareStatement(one_product_sql);
			
			stmt.setInt(1, product_id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				one_product.setProductid(rs.getInt("product_id"));
				one_product.setProductName(rs.getString("product_name"));
				one_product.setCategory(rs.getString("category"));
				one_product.setExplanation(rs.getString("explanation"));
				one_product.setQuantity(rs.getInt("quantity"));
			}
			
		} catch (SQLException e) {
			System.out.println("データベースとの接続を閉じる");
			e.printStackTrace();
		}
		
		
		return one_product;
	}

}
