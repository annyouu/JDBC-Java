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
import sql.Register;

@WebServlet("/ProductRegisterServlet")
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		製品登録画面へ行く
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_register.jsp");
		dispatcher.forward(request, response);
	}

//	入力された情報を取得してデータベースへ商品情報を登録する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String product_name = request.getParameter("product_name");
		String category = request.getParameter("category");
		String explanation = request.getParameter("explanation");
		String quantityStr = request.getParameter("quantity"); 
//		intに型変換
		int quantity = Integer.parseInt(quantityStr);
		
//		管理者のセッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");
		
		Register register = new Register();
		
//		商品登録処理を実行
		register.product_register(admin.getId(), product_name, category ,explanation, quantity);
		
		Login login = new Login();
		List<Product> product = null;
		
//		データベースから取得した情報を格納
		product = login.getProductInfo(String.valueOf(admin.getId()));
		
//		jspへこれらの情報を渡す
		request.setAttribute("product", product);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_list.jsp");
		dispatcher.forward(request, response);
		
	}

}
