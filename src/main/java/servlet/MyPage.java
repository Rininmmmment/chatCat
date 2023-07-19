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
		
		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
	    dispatcher.forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
