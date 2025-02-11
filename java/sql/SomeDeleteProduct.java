package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class SomeDeleteProduct {
	public void productsome_delete(String[] productIds) throws FileNotFoundException {
		
		//データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getUrl();
		String user = db_info.getUser();
		String pass = db_info.getPassword();
		
//		実行するSQL文 プレースホルダーを動的に作成
		StringBuilder deleteSql = new StringBuilder("DELETE FROM product_tb WHERE product_id IN (");   
		
		for (int i = 0; i < productIds.length; i++) {
			deleteSql.append("?");
			if (i < productIds.length - 1) {
				deleteSql.append(",");
			}
		}
		deleteSql.append(")");
		
//		データベースへ接続
		try(Connection conn = DriverManager.getConnection(url, user, pass)) {
			// オートコミット機能を無効化
            conn.setAutoCommit(false);
			
			try (PreparedStatement stmt = conn.prepareStatement(deleteSql.toString())) {
//				パラメータを設定
				for (int i = 0; i < productIds.length; i++) {
					stmt.setInt(i + 1, Integer.parseInt(productIds[i]));
				}
				
				int rs = stmt.executeUpdate();
				conn.commit();
				System.out.println(rs + "件の商品データを削除しました。");
				
			} catch (SQLException e) {
//				ロールバック処理
				conn.rollback();
				 System.err.println("ロールバック処理を行いました: " + e.getMessage());
	             throw new RuntimeException("データ削除中にエラーが発生しました。", e);
			}
			
		} catch (SQLException e1) {
			 System.err.println("DB接続に失敗しました: " + e1.getMessage());
	         throw new RuntimeException("データベース接続中にエラーが発生しました。", e1);
		} 
	}
}
