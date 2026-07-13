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
import bean.RoomBean;

@WebServlet("/CancelCreateServlet")
public class CancelCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	try {	
		//パラメータ受け取り
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int time = Integer.parseInt(request.getParameter("time"));
	//meetingroomを使う準備する
	MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
	//予約一覧を取得
	ReservationBean[][]reserve = mr.getReservations();
	//予約一件取り出す
		ReservationBean reserveB = reserve[roomId][time];
	//部屋女王法
	RoomBean roomB = mr.getRoom(reserveB.getRoomId());
	//セッション保存
	session.setAttribute("yayaku",reserveB);
	session.setAttribute("heya",roomB);
	//ですぱちゃ
	RequestDispatcher rd = request.getRequestDispatcher("cancelConfirm.jsp");
	rd.forward(request, response);
	
	}catch (Exception e) {
	    request.setAttribute("error", "エラーです");
	    RequestDispatcher rdp = request.getRequestDispatcher("cancelError.jsp");
		rdp.forward(request, response);
	
	}
}}