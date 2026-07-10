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
   
    public CancelServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
	//データ受信
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		String roomId = request.getParameter("roomId");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String userId = request.getParameter("userId"); 
	try {
		ReservationBean rb = new ReservationBean(id,roomId,date,start,end,userId);
		MeetingRoom mr = new MeetingRoom();
		mr.cancel(rb);
		
		
		session.setAttribute("yoyaku",rb);
		session.setAttribute("jikan",mr);
		
		RequestDispatcher rdp = request.getRequestDispatcher("cancel.jsp");
		rdp.forward(request, response);
		
	}catch (Exception e) {
	    request.setAttribute("error", "エラーです");
	    RequestDispatcher rdp = request.getRequestDispatcher("cancelError.jsp");
		rdp.forward(request, response);
	    
		//ReservationBean rList= ReservationBean.delete(); 
		//ReservationBean sakujo = new ReservationBean(id,roomId,date,start,end,userId);
	}
	}}
