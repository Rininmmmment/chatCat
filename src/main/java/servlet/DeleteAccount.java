package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.User;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get");
    	// セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		// アカウント削除
		AccountsDAO dao = new AccountsDAO();
		dao.deleteAccount(loginUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// メールアドレスを変更
		AccountsDAO dao = new AccountsDAO();
		dao.deleteAccount(loginUser);
		
		// JSPへフォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
//	    dispatcher.forward(request, response);
	}

}
