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

@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/menu.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//データ受信
		HttpSession session = request.getSession();
		try {
			ReservationBean rb = (ReservationBean) session.getAttribute("yayaku");
			MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
			mr.cancel(rb);
			RequestDispatcher rdp = request.getRequestDispatcher("/canceled.jsp");
			rdp.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rdp = request.getRequestDispatcher("/cancelError.jsp");
			rdp.forward(request, response);
		}
	}
}
