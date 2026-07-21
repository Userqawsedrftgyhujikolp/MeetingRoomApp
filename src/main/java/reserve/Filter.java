package reserve;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/*")
public class Filter extends HttpFilter implements javax.servlet.Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Filter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
//		System.out.println("\nフィルタによるログインチェックが開始しました");
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
	    if (url.equals("/login.jsp") || url.endsWith(".css") ||
	    		url.equals("/Login") || url.equals("/UserAddInput.jsp") || url.equals("/AddError.jsp") ||
	    		url.equals("/AddServlet") || url.equals("/AddOut.jsp")) {
//	    	System.out.println("urlが指定の形式のためパスします");
	        chain.doFilter(request, response);
	        return;
	    }
	    HttpSession session = httpRequest.getSession();
	    MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
	    if(mr == null || mr.getUser() == null ){
//	    	System.out.println("未ログインのためligin.jspへリダイレクトします");
	    	httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
	    	return;
	    }else {
//	    	System.out.println("ログイン中のためパスします");
	    	chain.doFilter(request, response);
	    	return;
	    }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
