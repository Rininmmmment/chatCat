package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.AnalyzePosts;
import model.User;

@WebServlet("/Mypage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインユーザー情報を取得
		HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");
		
	    // 投稿分析データをリクエストスコープに保存
	    // 部屋ごとの投稿回数リスト
		AnalyzePosts analyzePosts = new AnalyzePosts();
		Map<String, Integer> PostCountsEachBbsList = analyzePosts.getPostedTimes(loginUser.getUserid());
		request.setAttribute("postCountsEachBbsList", PostCountsEachBbsList);
		
		// 全部屋の投稿回数の和
		int postedTimesSum = analyzePosts.sumPostedTimes(loginUser.getUserid());
		request.setAttribute("postedTimesSum", postedTimesSum);
		
		// レベル
		int level = analyzePosts.calculateLevel(loginUser.getUserid());
		request.setAttribute("level", level);
		
		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得（メールアドレス）
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		
	    // 入力値チェック
	    if (email != null && email.length() != 0) {
	    	// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			
			// メールアドレスを変更
			AccountsDAO dao = new AccountsDAO();
			dao.addEmail(loginUser, email);
			
	    } else {
	      // エラーメッセージをリクエストスコープに保存
	      request.setAttribute("errorMsg", "メールアドレスが入力されていません");
	    }
		
		// JSPへフォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
//	    dispatcher.forward(request, response);
	    doGet(request, response);
	}

}
