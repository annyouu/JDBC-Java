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
import sql.SelectOneProduct;
import sql.Update;

@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	顧客編集画面への遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
//		編集リンクから商品IDを取得
		int id = Integer.parseInt(request.getParameter("product_id"));
		
		SelectOneProduct one_product = new SelectOneProduct();
//		リンクで選択された商品情報を取得する
		Product product = one_product.get_One_Product_Info(id);
		
		request.setAttribute("product", product);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_update.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("product_id"));
		String product_name = request.getParameter("product_name");
		String category = request.getParameter("category");
		String explanation = request.getParameter("explanation");
		String quantityStr = request.getParameter("quantity"); 
//		intに型変換
		int quantity = Integer.parseInt(quantityStr);
		Update sql = new Update();
		
		sql.product_update(product_name, category, explanation, quantity, id);
		
//		管理者セッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");
		
		Login login = new Login();
		
		List<Product> product = null;
		
		product = login.getProductInfo(String.valueOf(admin.getId()));
		
		request.setAttribute("product", product);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_list.jsp");
		dispatcher.forward(request, response);
		
	}

}
