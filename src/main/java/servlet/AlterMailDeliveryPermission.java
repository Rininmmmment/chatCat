package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.User;

public class AlterMailDeliveryPermission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得（配信許可：permit）
		request.setCharacterEncoding("UTF-8");
		String whether_permit = request.getParameter("whether_permit");
//		System.out.println(whether_permit);
		
    	// セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// メールアドレスを変更
		AccountsDAO dao = new AccountsDAO();
		dao.alterMailDeliveryPermission(loginUser, whether_permit);
		
		// Mypageにリダイレクト
		String url = "Mypage";
		response.sendRedirect(url);
	}

}
