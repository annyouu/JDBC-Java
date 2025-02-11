package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Register {
	public void product_register(int admin_id, String product_name, String category, String explanation, int quantity) throws FileNotFoundException {
		
//		データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
//		実行するSQL文
		String register_sql = "INSERT INTO product_tb (admin_id, product_name, category, explanation, quantity) "
				+ "VALUES(?,?,?,?,?)";
		
//		データベースの接続
		
		try (Connection conn = DriverManager.getConnection(url,user,pass)) {
			// オートコミット機能を無効化
			conn.setAutoCommit(false);

			try (PreparedStatement stmt = conn.prepareStatement(register_sql)) {
				
//				?の部分に挿入
				stmt.setInt(1, admin_id);
				stmt.setString(2, product_name);
				stmt.setString(3, category);
				stmt.setString(4, explanation);
				stmt.setInt(5, quantity);
				
				 // SQLの実行
				stmt.executeUpdate();

				// コミット
				conn.commit();
				System.out.println("コミット処理を行いました");
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
