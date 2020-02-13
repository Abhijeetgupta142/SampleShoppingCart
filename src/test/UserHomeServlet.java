package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class UserHomeServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		HttpSession hs = req.getSession(false);
		if(hs == null) {
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.forward(req, res);
			return;
		}
		String uname = (String)hs.getAttribute("UserName");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head>");
		out.println("<title>Shopping Cart Example</title>");
		out.println("</head><body>");
		out.println("<table width='100%' height='90%' border='1'>");
		out.println("<tr align = 'center'>");
		out.println("<td height='39' colspan='2'>");
		out.println("<strong><font size='5'>MyShopping Site</font>");
		out.println("</strong></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td width='18%' height='500' valign='top'>");
		out.println("<p>&nbsp;</p>");
		out.println("<blockquote>");
		out.println("<p><a href='"+res.encodeURL("getProducts")+"'>");
		out.println("View Products</a></p>");
		out.println("<p><a href='"+res.encodeURL("getCart")+"'>");
		out.println("View Cart Details</a></p>");
		out.println("<p><a href='"+res.encodeURL("logout")+"'>");
		out.println("Logout</a></p>");
		out.println("</blockquote></td>");
		out.println("<td width='82%' align='left' valign='top'>");
		out.println("<font size='6'>Welcome,"+uname+"</font>");
		out.println("</td></tr>");
		out.println("<tr align='center'>");
		out.println("<td colspan='2'>");
		out.println("<em>&copy;CopyRights 2019-20</em></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body></html>");
		out.flush();
		out.close();
	}
}
