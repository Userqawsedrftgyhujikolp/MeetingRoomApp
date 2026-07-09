package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReservationBean;
import dao.ReservationDao;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//		データを受信
		String yoyakuid = request.getParameter("id");
		String yoyakudate = request.getParameter("date");
		String yoyakuroomId = request.getParameter("roomId");
		String yoyakustart = request.getParameter("start");
		String yoyakuend = request.getParameter("end");
		String yoyakuuserId = request.getParameter("userId");
		//		メソッドを呼び出す
		ReservationBean rList = ReservationDao.insert();
		if (rList.size() == 0) {
			ReservationBean yoyaku = new ReservationBean(yoyakuid, yoyakudate, yoyakuroomId, yoyakustart, yoyakuend,
					yoyakuuserId);
			//			登録
			request.setAttribute("yoyaku", yoyaku);
			String nextPage = "/reserved.jsp";
		}

	}

}
