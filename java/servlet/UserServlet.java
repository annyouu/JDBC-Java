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

import object.Product;
import object.User;
import sql.UserLogin;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userlogin.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
				
        //ログイン画面で入力された値を取得
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String admin_id = request.getParameter("admin_id");
		
		UserLogin logins = new UserLogin();
		User user = logins.check_user(user_id, password);
		
		
		
		if (user.getLogin_flag()) {
			System.out.println("ユーザーでログイン成功");
			
			HttpSession user_session = request.getSession(true);
			user_session.setAttribute("user", user);
			
			
			boolean adminExists = logins.checkAdminExist(admin_id);
			if (!adminExists) {
				request.setAttribute("userError", "ユーザーID または パスワード または 管理者IDが存在しません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userlogin.jsp");
                dispatcher.forward(request, response);
                return;
			}
		
			List<Product> product = logins.getuserProductInfo(admin_id);
			

			request.setAttribute("product", product);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userproduct_list.jsp");
	        dispatcher.forward(request, response);
			
		} else {
         //	ログイン失敗 エラーを表示
			System.out.println("ユーザログイン失敗");
			boolean adminExists = logins.checkAdminExist(admin_id);
			if (!adminExists) {
				request.setAttribute("userError", "ユーザーID または パスワード または 管理者IDが存在しません");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userlogin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
