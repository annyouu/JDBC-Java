package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import object.Product;
import sql.Search;


@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	検索の情報を取得し、jspへ送る
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		フォームから送られた名前を取得する
		String productName = request.getParameter("productName");
		
//		名前が空でない場合のみ検索
		if (productName != null && !productName.trim().isEmpty()) {
			Search search = new Search();
			List<Product> product_details = search.product_search(productName);
			
//			検索結果をリクエストスコープに格納
			request.setAttribute("product_details", product_details);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/product_search.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
