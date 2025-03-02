package first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class idcheck
 */
@WebFilter("/sq2")
public class idcheck extends HttpFilter implements Filter {
    public idcheck() {
        super();
        
    }
	public void destroy() {
	
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String email = req.getParameter("email");
        String adminname = req.getParameter("admin");
        String pass= req.getParameter("password");
        if(email!=null) {
        	HttpSession  session=((HttpServletRequest) request).getSession();
        	session.setAttribute("gmail", email);
        if (isValidGmail(email)) {
            chain.doFilter(request, response);
        } else {
        	req.setAttribute("errorMessage", "Invalid Gmail address");
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
        }
        }
        if(adminname!=null && pass!=null) {
        if(isValidadmin(adminname,pass)) {
        	res.sendRedirect("result.jsp");
        }
        else {
        		req.setAttribute("errorMe", "invalid username or password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
        }
        }
	}
    private boolean isValidGmail(String email) {
        // Check if email ends with "@gmail.com"
        if (email == null) return false;
        return email.matches("^[\\w-\\.]+@gmail\\.com$"); // Regular expression to allow only Gmail emails
    }
    private boolean isValidadmin(String admin,String pass) {
    	if(admin.equals("prasath")&& pass.equals("suguprasath@14"))
    			return true;
    	else return false;
    }
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
