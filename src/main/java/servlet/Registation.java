package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.RegistationLogic;

@WebServlet("/Registation")
public class Registation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registation.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		// 入力値チェック
//	    if (userid != null && userid.length() != 0) {
			// アカウントを登録
			Account account = new Account(userid, pass);
			RegistationLogic registationLogic = new RegistationLogic();
			registationLogic.execute(account);
//	    } else {
//	      // エラーメッセージをリクエストスコープに保存
//	      request.setAttribute("errorMsg", "フォームを入力してください");
//	    }
	    
	    // フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerOK.jsp");
	    dispatcher.forward(request, response);
	}

}
