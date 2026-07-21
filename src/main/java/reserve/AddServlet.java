package reserve;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;
import bean.UserBean;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//データを受信
		HttpSession session = request.getSession();
		//パスワード、名前、住所を取得
		String addPass = request.getParameter("password");
		String addName = request.getParameter("name");
		String addAddress = request.getParameter("address");
		try {
			MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
			UserBean userb = mr.InsertUser(addPass, addName, addAddress);
			//追加に成功したらセッション属性に入れて、画面へフォワード
			session.setAttribute("useradd", userb);
			RequestDispatcher rdp = request.getRequestDispatcher("AddOut.jsp");
			rdp.forward(request, response);
		} catch (IOException e) {
			request.setAttribute("error", "6文字以上のパスワードに設定してください");
			RequestDispatcher rdp = request.getRequestDispatcher("AddError.jsp");
			rdp.forward(request, response);
		} catch (Exception e) {
			//その他失敗時・エラー時
			request.setAttribute("error", "追加できませんでした");
			RequestDispatcher rdp = request.getRequestDispatcher("AddError.jsp");
			rdp.forward(request, response);
		}
	}
}
