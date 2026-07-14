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
		String yoyakuroomId = request.getParameter("roomId");
		String yoyakustart = request.getParameter("start");
		try {
			//	まずは予約を生成
			//	値をセット
			MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
			ReservationBean rb = mr.createReservation(yoyakuroomId, yoyakustart);
			//	予約が重複していないか(MeetingRoomにあるメソッドを使う。重複の確認)	
			mr.reserve(rb);
			RequestDispatcher rdp = request.getRequestDispatcher("reserveConfirm.jsp");
			rdp.forward(request, response);
		} catch (Exception e) {
			System.out.println("---------------");
			e.printStackTrace();
			request.setAttribute("error", "予約できませんでした");
			RequestDispatcher rdp = request.getRequestDispatcher("reserveError.jsp");
			rdp.forward(request, response);
		}
		

	}
}
