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
import bean.UserBean;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//データを受信
		HttpSession session = request.getSession();
		//パスワード、名前、住所を取得
		String addPass = request.getParameter("password");
		String addName = request.getParameter("name");
		String addAddress = request.getParameter("address");
		MeetingRoom mr = new MeetingRoom();
		try {
			UserBean userb = mr.InsertUser(addPass, addName, addAddress);
			if(userb == null) {
				throw new Exception();
			}
			//追加に成功したらセッション属性に入れて、画面へフォワード
			session.setAttribute("useradd", userb);
			mr.login(userb.getId(), userb.getPassword());
			session.setAttribute("meetingRoom", mr);
			RequestDispatcher rdp = request.getRequestDispatcher("AddOut.jsp");
			rdp.forward(request, response);
		}catch(IllegalArgumentException e) {
			System.out.println("パスワードが6文字未満です");
			e.printStackTrace();
			RequestDispatcher rdp = request.getRequestDispatcher("AddError.jsp");
			UserBean useradd = new UserBean(null, addPass, addName, addAddress);
			request.setAttribute("useradd", useradd);
			rdp.forward(request, response);
		} catch (Exception e) {
			//その他失敗時・エラー時
			request.setAttribute("error", "追加できませんでした"+e.getMessage());
			e.printStackTrace();
			RequestDispatcher rdp = request.getRequestDispatcher("AddError.jsp");
			UserBean useradd = new UserBean(null, addPass, addName, addAddress);
			request.setAttribute("useradd", useradd);
			rdp.forward(request, response);
		}
	}
}
