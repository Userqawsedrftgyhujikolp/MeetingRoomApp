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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
//		データを受信
		String date= request.getParameter("date");
//		メソッドを呼び出す
		ReservationBean rList = ReservationDao.findByDate(date);
//		取得したDBデータをリクエスト属性に格納
		request.setAttribute("kensaku", rList);
		String nextPage="/reserved.jsp";
		if(rList.size()==0) {
			re
		}
	}

}
