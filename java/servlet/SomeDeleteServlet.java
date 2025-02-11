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
import sql.SomeDeleteProduct;

@WebServlet("/SomeDeleteServlet")
public class SomeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
//        削除対象の顧客IDをリクエストから取得
        String[] productIds = request.getParameterValues("productIds");
        
        if (productIds == null || productIds.length == 0) {
        	request.setAttribute("error", "削除対象の商品を選択してください");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_list.jsp");
        	dispatcher.forward(request, response);
        	
        }
//        削除処理を実行
        SomeDeleteProduct somedelete = new SomeDeleteProduct();
        somedelete.productsome_delete(productIds);
        
//        管理者のセッションを取得
        HttpSession session = request.getSession(true);
        Admin admin = (Admin) session.getAttribute("admin");
        
//        商品情報を再取得
        Login login = new Login();
        List<Product> product = null;
        
        product = login.getProductInfo(String.valueOf(admin.getId()));
        
//        再取得した顧客情報をリクエストスコープにセット
        request.setAttribute("product", product);
        
//        顧客リスト画面へ遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_list.jsp");
        dispatcher.forward(request, response);
	}

}
