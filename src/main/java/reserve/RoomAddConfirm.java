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
 * Servlet implementation class RoomAddConfirm
 */
@WebServlet("/RoomAddConfirm")
public class RoomAddConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/menu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
			
			RoomBean room = (RoomBean)session.getAttribute("room");
			session.removeAttribute("room");
			RoomBean newRoom = mr.insertRoom(room.getId(), room.getName());
			if(newRoom != null) {
				request.setAttribute("room", newRoom);
				request.setAttribute("tDis", "ID");
				request.setAttribute("message", "追加に成功しました");
			}else {
				request.setAttribute("room", room);
				request.setAttribute("tDis", "階");
				request.setAttribute("message", "追加に失敗しました");
			}
		} catch (Exception e) {
			request.setAttribute("room", "err");
			request.setAttribute("tDis", "階");
			request.setAttribute("message", "不明なエラーが発生しました");
		}
		
		request.getRequestDispatcher("roomAddResult.jsp").forward(request, response);
	}

}
