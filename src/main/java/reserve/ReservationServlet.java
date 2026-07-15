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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//データを受信
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
			//予約実行のメソッドを呼び出す
			mr.reserve(rb);
			session.setAttribute("yoyaku", rb);
			session.setAttribute("jikan", mr);
			//画面遷移先を指定
			RequestDispatcher rdp = request.getRequestDispatcher("reserveConfirm.jsp");
			rdp.forward(request, response);
			session.removeAttribute("reservation");
		} catch (Exception e) {
			request.setAttribute("error", "予約できませんでした");
			RequestDispatcher rdp = request.getRequestDispatcher("reserveConfirm.jsp");
		}
	}
}
