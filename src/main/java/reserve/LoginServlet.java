package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//セッションを取得する
		HttpSession session = request.getSession();
		//ログイン情報を取得する
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		//try
		//MeetingRoom クラスのインスタンスを新しく作る(生成)
		MeetingRoom meetingRoom = new MeetingRoom();
		//オブジェクトが持っている、認証用のメソッドを呼び出す(認証)
		String nextPage;
		if (meetingRoom.login(userId, userPw)) {
			//セッションにこの「meetingRoom」オブジェクトをセットする
			// 遷移先を menu.jsp にする
			session.setAttribute("meetingRoom", meetingRoom);
			nextPage = request.getContextPath() + "/menu.jsp";
		} else {
			// 遷移先を login.jsp にする
			nextPage = request.getContextPath() + "/login.jsp";
		}
		// 変数 nextPage に決まった移行先へリダイレクトする
		response.sendRedirect(nextPage);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// login.jsp へリダイレクトする処理
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
