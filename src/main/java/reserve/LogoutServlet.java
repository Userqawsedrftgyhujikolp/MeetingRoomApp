package reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションを取得する
		HttpSession session = request.getSession();
		//セッションを破棄する
		session.invalidate();
		//login.jsp へリダイレクトする
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// login.jsp へリダイレクトする処理
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
