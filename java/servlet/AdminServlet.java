package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import object.Admin;
import object.Product;
import sql.Login;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
//		ログイン画面で入力された値を取得
		String admin_id = request.getParameter("admin_id");
		String password = request.getParameter("password");
		
//		ログイン処理の実装 ログインクラスのインスタンス生成
//		ログインクラスのcheckメソッドでデータベースにnameとpasswordがあるかを確認
//		checkの引数にログイン画面で入力された値を格納する
//		それをAdminクラスの変数に代入
		Login login = new Login();
		Admin admin = login.check(admin_id, password);
		
//		ログイン成功時
		if (admin.getLogin_flag()) {
			System.out.println("ログイン成功");
			
//			ログイン成功時にセッションオブジェクトを作成する
			HttpSession admin_session = request.getSession(true);
//			セッションに管理者情報を格納
			admin_session.setAttribute("admin", admin);
			
			
			List<Product> product = null;
//			データベースから取得した顧客情報を格納
			product = login.getProductInfo(admin_id);
			request.setAttribute("product", product);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_list.jsp");
			dispatcher.forward(request, response);
		} else {
//			ログイン失敗 エラーを表示
			request.setAttribute("errorMsg","管理者ID または パスワードが違います");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
