package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(reserv == null) {this.doGet(request, response);}
		try {
			mr = (MeetingRoom)request.getAttribute("MeetingRoom");
			mr.setDate(request.getParameter("date"));
		}catch(Exception e) {
			mr = null;
		}
		if(mr != null) {
			response.sendRedirect(reserv);
		}else {
			response.sendRedirect(reserv);
		}
	}

}
