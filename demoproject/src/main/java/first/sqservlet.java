package first;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/sq2")
public class sqservlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		/*HttpSession s=req.getSession();-->("we use this sseion and cookie for the flow of login page if any does not 
		  login it redirect to the page easyly and then if they use url of next page go there to access the page it first seen 
		  the session get attribute does the value null or not if null means it will go back and login again")
		int k=(int)s.getAttribute("k");
		PrintWriter out=res.getWriter();
		out.println("result"+k);*/
		//cookie:-
		/*int k=0;
		Cookie c1[]=req.getCookies();
		for(Cookie c:c1) {
			if(c.getName().equals("k")) {
				k=Integer.parseInt(c.getValue());
			}
		}*/
		/*int n=Integer.parseInt(req.getParameter("aid"));
		String s= req.getParameter("aname");*/
		HttpSession session = req.getSession(false);
		String gmail=(String) session.getAttribute("gmail");
		if(gmail==null)res.sendRedirect("profile.jsp");
		String s=req.getParameter("username");
		String s1=req.getParameter("email");
		String s2=req.getParameter("Role");
		String url = "jdbc:mysql://localhost:3306/orders";
		String un = "root";
		String pass = "suguprasath@14";
        String q = "INSERT INTO users (username, usermail, country) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, un, pass);
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, s);
            pst.setString(2, s1);
            pst.setString(3, s2);
            int c = pst.executeUpdate();
            pst.close();
            con.close();
            res.sendRedirect("registeredpage.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            if (!res.isCommitted()) {
                res.sendRedirect("profile.jsp");
            }
        }
	}
}
