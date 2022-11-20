package forex.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forex.model.ForexBean;

@WebServlet("/display.html")
public class DisplayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text.html");
		
		PrintWriter out = response.getWriter();
		
		//how do we retrieve from the forwarded request sent by the Servlet controller?
		ForexBean forexObj = (ForexBean)request.getAttribute("myForexObject");
		
		
		out.print("<html>");
		out.print("<head><title>BPI Forex Servlet Lifecycle Demo</title></head>");
		out.print("<body>");
		out.print("<img src='images/forex.jpg'/>");
		
		/*
		 Step 6 - Extract the data from the beans using the getter methods. 
		 */
		out.print("<p>The amount of <b>Php" + forexObj.getPesoAmount() + "</b> is equivalent to <b>" 
				+ forexObj.getCurrency() + " " + forexObj.getConvertedAmount() +"</b>");
		out.print("<p>");
		out.print("<form action='index.html'>");
		out.print("	<input type='submit' value='<< GO BACK >>'>");
		out.print("</form>");
		out.print("<hr/>");
		out.print("<p><i>&copy; 2017 Bank of the Philippine Islands. All rights reserved.</i></p>");
		out.print("</body>");		
		out.print("</html>");
	out.close();

	}

}
