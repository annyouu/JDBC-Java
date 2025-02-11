package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Update {
	public void product_update(String product_name, String category, String explanation, int quantity, int product_id) throws FileNotFoundException {
//		データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
//		実行SQL
		String update_sql = "UPDATE product_tb set product_name = ?, category = ?,  explanation = ?, quantity = ? WHERE product_id = ?";
		
		
		try (Connection conn = DriverManager.getConnection(url,user,pass)) {
			// オートコミット機能を無効化
			conn.setAutoCommit(false);
			
			try (PreparedStatement stmt = conn.prepareStatement(update_sql)) {
				stmt.setString(1, product_name);
				stmt.setString(2, category);
				stmt.setString(3, explanation);
				stmt.setInt(4,quantity);
				stmt.setInt(5, product_id);
				
				// SQLの実行
				stmt.executeUpdate();
				//コミット
				conn.commit();
				System.out.println("編集更新処理が成功しました");
				
			} catch(SQLException e) {
				conn.rollback();
			    System.out.println("編集のロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
