package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
@SuppressWarnings("serial")
public class AddProductsServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		HttpSession hs = req.getSession(false);
		if(hs == null) {
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.forward(req, res);
			return;
		}
		String uname = (String)hs.getAttribute("UserName");
		ArrayList products = (ArrayList)hs.getAttribute("products");
		if(products == null) {
			products = new ArrayList();
			hs.setAttribute("products", products);
		}
		String[] pcodes = req.getParameterValues("products");
		for(int i=0; i<pcodes.length; i++) {
			if(req.getParameter(pcodes[i]).equals(""))
				continue;
			Product p = new Product();
			p.code = pcodes[i];
			int j = products.indexOf(p);
			if(j!=-1) {
				p = (Product)products.get(j);
				p.qty += Double.parseDouble(req.getParameter(pcodes[i]));
			}
			else {
				p.name = req.getParameter(pcodes[i]+"Name");
				p.qty = Double.parseDouble(req.getParameter(pcodes[i]));
				products.add(p);
			}
		}
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head>");
		out.println("<title>Shopping Cart Example</title>");
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
		out.println("<td width='82%' align='left' valign='top'>");
		out.println("<p><font size='6'>Welcome,"+uname+"</font></p>");
		out.println("Product Added Successfully to your Cart</td>");
		out.println("</tr>");
		out.println("<tr align='center'>");
		out.println("<td colspan='2'><em>&copy;CopyRights 2019-20</em></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body></html>");
		out.flush();
		out.close();
	}
}
