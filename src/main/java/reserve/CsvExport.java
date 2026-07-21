package reserve;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;
import bean.ReservationBean;

/**
 * Servlet implementation class CsvExport
 */
@WebServlet("/CsvExport")
public class CsvExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CsvExport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
		request.setCharacterEncoding("UTF-8");
		String charset = request.getParameter("char");
		if (charset == null) {
			charset = "UTF-8";
		}
		response.setContentType("text/csv");
		response.setCharacterEncoding(charset);
		String attachment = "attachment; filename=\"" + mr.getDate() + "data.csv\"";
		response.setHeader("Content-Disposition", attachment);
		ReservationBean[][] reservations = mr.getReservations();
		try (PrintWriter writer = response.getWriter()) {
			writer.println("予約番号,会議室,開始時刻,終了時刻,利用者");
			for (ReservationBean[] reservR : reservations) {
				for (ReservationBean reserv : reservR) {
					if (reserv != null) {
						//予約情報を書き込む
						int id = reserv.getId();
						String room = mr.getRoom(reserv.getRoomId()).getName();
						String start = reserv.getStart();
						String end = reserv.getEnd();
						String user = reserv.getUserId();
						writer.println(id + "," + room + "," + start + "," + end + "," + user);
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "csvSelect.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
