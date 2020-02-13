package test;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class GetCartDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
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
		out.println("<title>Shopping Cart</title>");
		out.println("</head><body>");
		out.println("<table width='100%' height='90%' border='1'>");
		out.println("<tr align = 'center'>");
		out.println("<td height='39' colspan='2'><strong><font size='5'>MyShopping Site</font></strong></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td width='18%' height='500' valign='top'><p>&nbsp;</p>");
		out.println("<blockquote>");
		out.println("<p><a href='"+res.encodeURL("getProducts")+"'>View Products</a></p>");
		out.println("<p><a href='"+res.encodeURL("getCart")+"'>View Cart Details</a></p>");
		out.println("<p><a href='"+res.encodeURL("logout")+"'>Logout</a></p>");
		out.println("</blockquote></td>");
		out.println("<td width='82%' align='left' valign='top'><p><font size='6'>Welcome,"+uname+"</font></p>");
		out.println("<center>Your Cart contains the following products");
		out.println("<table width='80%' border='1'>");
		out.println("<tr>");
		out.println("<th width='24%'>Product Code</th>");
		out.println("<th width='28%'>Product Name</th>");
		out.println("<th width='20%'>Quantity</th>");
		out.println("</tr>");
		Collection products = (Collection)hs.getAttribute("products");
		if(products == null) {
			out.println("<tr><td colspan='3' align='center'>");
			out.println("You Have Not Added Any Products into Cart");
			out.println("</td></tr>");
		}
		else {
			Iterator i = products.iterator();
			while(i.hasNext()) {
				Product p = (Product)i.next();
				out.println("<tr>");
				out.println("<td>"+p.getCode()+"</td>");
				out.println("<td>"+p.getName()+"</td>");
				out.println("<td>"+p.getQty()+"</td>");
				out.println("</tr>");
			}
		}
		out.println("</table></center>");
		out.println("</td></tr>");
		out.println("<tr align='center'>");
		out.println("<td colspan='2'><em>&copy;CopyRights 2019-20</em></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body></html>");
		out.flush();
		out.close();
	}
}
