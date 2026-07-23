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
import bean.ReservationBean;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/menu.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//データを受信
		HttpSession session = request.getSession();
		try {
			ReservationBean rb = (ReservationBean) session.getAttribute("reservation");
			MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
			//予約実行のメソッドを呼び出す
			mr.reserve(rb);
			session.setAttribute("yoyaku", rb);
			//画面遷移先を指定
			RequestDispatcher rdp = request.getRequestDispatcher("reserved.jsp");
			rdp.forward(request, response);
			session.removeAttribute("reservation");
		} catch (Exception e) {
			request.setAttribute("error",e.getMessage());
			request.getRequestDispatcher("reserveError.jsp").forward(request, response);
		}
	}
}
