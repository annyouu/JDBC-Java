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
import object.Admin;
import object.Product;

public class Login {
	public Admin check(String admin_id, String password) throws FileNotFoundException {
		
//		データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
//		実行するSQL文
		String login_sql = "SELECT * FROM admin_tb WHERE admin_id = ? AND password = ?";
		
//		管理者クラスのインスタンスを生成
		Admin admin = new Admin();
		 
//		データベースへの接続 try catch文
		try {
			// MySQLドライバを手動でロード（
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースへの接続
			try (Connection conn = DriverManager.getConnection(url, user, pass)) {
				PreparedStatement stmt = conn.prepareStatement(login_sql);
				stmt.setString(1, admin_id); // 1つ目のパラメータにadmin_idをセット
				stmt.setString(2, password); // 2つ目のパラメータにpasswordをセット
				
				ResultSet rs = stmt.executeQuery(); // SQLを実行
				
				// 結果をAdminオブジェクトに格納
				if (rs.next()) {
					admin.setId(rs.getInt("admin_id"));
					admin.setName(rs.getString("name"));
					admin.setPassword(rs.getString("password"));
					admin.setLogin_flag(true);
				} else {
					admin.setLogin_flag(false);
				}
			} catch (SQLException e) {
				// SQLエラー処理
				System.out.println("管理者データベース接続エラー");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// ドライバが見つからない場合
			System.out.println("MySQLドライバが見つかりません");
			e.printStackTrace();
		}
//		データベースから取得した値を返す
		return admin;
		
	}
	
//	ログイン成功した後に管理者が管理している製品情報の取得
public List<Product> getProductInfo(String admin_id) throws FileNotFoundException {
//	データベースへの接続情報をプロパティファイルから取得
	DBconfig db_info = new DBconfig();
	String url = db_info.getUrl();
	String user = db_info.getUser();
	String pass = db_info.getPassword();
	
//	実行SQL文
//	admin_idで該当する製品情報をproduct_tbから取得する
	String product_sql = "SELECT * FROM product_tb WHERE admin_id = ?";
	
//	型をProductにした新しい配列を定義し、ここに製品情報を格納する
//	product型のオブジェクトにすることでより分かりやすく安全にリストを定義している
	List<Product> pro_list = new ArrayList<Product>();
	
	try {
		// MySQLドライバを手動でロード（必要な場合）
		Class.forName("com.mysql.cj.jdbc.Driver");
//		データベースへ接続
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement stmt = conn.prepareStatement(product_sql);
			
			stmt.setString(1, admin_id);
			ResultSet rs = stmt.executeQuery();
			
//		　SQL実行後 リストに格納
			while(rs.next()) {
//				クラスのインスタンスに一時格納
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
			System.out.println("管理者格納データベース接続エラー");
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		System.out.println("MySQLドライバが見つかりません");
		e.printStackTrace();
	}
	
	
	return pro_list;
}

	
}
