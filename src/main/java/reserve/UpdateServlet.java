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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//データを受信
		HttpSession session = request.getSession();
		//パスワード、名前、住所を取得
		String upPass = request.getParameter("password");
		String upName = request.getParameter("name");
		String upAddress = request.getParameter("address");
		MeetingRoom mr = new MeetingRoom();
		UserBean userb = mr.InsertUser(upPass, upName, upAddress);
		//更新に成功したらセッション属性に入れて、画面へフォワード
		session.setAttribute("userUp", userb);
		mr.login(userb.getId(), userb.getPassword());
		session.setAttribute("meetingRoom", mr);
		RequestDispatcher rdp = request.getRequestDispatcher("UpdateResult.jsp");
		rdp.forward(request, response);
	}

}
