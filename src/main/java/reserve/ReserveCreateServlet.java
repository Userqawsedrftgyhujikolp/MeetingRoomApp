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

@WebServlet("/ReserveCreate")
public class ReserveCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//		データを受信
		HttpSession session = request.getSession();
		int yoyakuid = Integer.parseInt(request.getParameter("id"));
		String yoyakudate = request.getParameter("date");
		String yoyakuroomId = request.getParameter("roomId");
		String yoyakustart = request.getParameter("start");
		String yoyakuend = request.getParameter("end");
		String yoyakuuserId = request.getParameter("userId");
		try {
		ReservationBean rb = new ReservationBean(yoyakuid, yoyakudate, yoyakuroomId, yoyakustart, yoyakuend,
				yoyakuuserId);
		MeetingRoom mr = new MeetingRoom();
		//	予約が重複していないか(条件式違うかも)
			mr.roomIdIndex(yoyakuroomId){
				
			}
			mr.createReservation(yoyakuroomId, yoyakustart);
			//	値をセット
			session.setAttribute("yoyaku", rb);
			session.setAttribute("jikan", mr);
			RequestDispatcher rdp = request.getRequestDispatcher("reserveConfirm.jsp");
			rdp.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("error", "ログインしてください");
			RequestDispatcher rdp = request.getRequestDispatcher("login.jsp");
			rdp.forward(request, response);

		}
	}
}
