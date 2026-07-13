package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;

/**
 * Servlet implementation class ChangeDateServlet
 */
@WebServlet("/ChangeDate")
public class ChangeDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MeetingRoom mr;
		String reserv = request.getHeader("Referer");
		HttpSession session = request.getSession();
		if(reserv == null) {this.doGet(request, response);return;}
		try {
			mr = (MeetingRoom)session.getAttribute("meetingRoom");
			mr.setDate(request.getParameter("date"));
			session.setAttribute("meetingRoom", mr);
		}catch(Exception e) {
			mr = null;
			System.err.println("ChangeDateSevletにて例外が発生しました\n");
			e.printStackTrace();
		}
		if(mr != null) {
			response.sendRedirect(reserv);
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
	}

}
