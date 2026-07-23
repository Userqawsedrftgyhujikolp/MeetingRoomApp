package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;
import bean.RoomBean;

/**
 * Servlet implementation class RoomAddServlet
 */
@WebServlet("/RoomAdd")
public class RoomAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/menu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String num = request.getParameter("floor");
		String roomName = request.getParameter("name");
		MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
		if(num == null || roomName == null || num.isBlank() || roomName.isBlank()) {
			response.sendRedirect(request.getContextPath()+"/roomAdd.jsp");
			return;
		}
		RoomBean[] rooms = mr.getRooms();
		for(RoomBean room: rooms) {
			if(roomName.equals(room.getName())) {
				request.setAttribute("message", "会議室名が重複していますが追加して宜しいですか？");
			}
		}
		
		RoomBean newRoom = new RoomBean(num,roomName);
		session.setAttribute("room", newRoom);
		
		request.getRequestDispatcher("roomConfirm.jsp").forward(request, response);
	}

}
