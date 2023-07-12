package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chatbot.PostAuto;
import model.GetMutter;
import model.GetMutterListLogic;
import model.PostMutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Clinic")
public class ClinicService extends HttpServlet{
	String page_name = "clinicService";
	String bbs_type = "clinic";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // ログインしているか確認するため
	    // セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");
	    
	    // つぶやきリスト取得用のユーザーID
	    String user_account = loginUser.getUserid();

	    if (loginUser == null) { // ログインしていない
	    // リダイレクト
	      response.sendRedirect("index.jsp");
	    } else { // ログイン済み
	    // フォワード
			// つぶやきリストを取得して、リクエストスコープに保存
			GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
			List<GetMutter> mutterList = getMutterListLogic.execute(bbs_type, user_account);
			request.setAttribute("mutterList", mutterList);
	      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/" + page_name + ".jsp");
	      dispatcher.forward(request, response);
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		 // つぶやきリスト取得用のユーザーIDを初期化
	    String user_account = "";
		
	    // 入力値チェック
	    if (text != null && text.length() != 0) {
	    	// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			user_account = loginUser.getUserid();
			// つぶやきをつぶやきリストに追加
			PostMutter mutter = new PostMutter(loginUser.getUserid(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, user_account, "clinic");
	    } else {
	      // エラーメッセージをリクエストスコープに保存
	      request.setAttribute("errorMsg", "つぶやきが入力されていません");
	    }
	    
	    PostAuto postauto = new PostAuto();
	    postauto.timer_create(bbs_type, user_account);


//	     つぶやきリストを取得して、リクエストスコープに保存
	    GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
	    List<GetMutter> mutterList = getMutterListLogic.execute(bbs_type, user_account);
	    request.setAttribute("mutterList", mutterList);

//	     フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/" + page_name + ".jsp");
	    dispatcher.forward(request, response);
	}

}
