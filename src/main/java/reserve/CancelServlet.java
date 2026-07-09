package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String id = request.getParameter("id");
		String roomId = request.getParameter("roomId");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String userId = request.getParameter("userId");
	//DAOのメソッド呼び出す
		ReservationBean rList= ReservationBean.delete(); 
		ReservationBean sakujo = new ReservationBean(id,roomId,date,start,end,userId);
	//取得したデータをリクエスト属性に格納
		
		
		
		
		
	}

}
