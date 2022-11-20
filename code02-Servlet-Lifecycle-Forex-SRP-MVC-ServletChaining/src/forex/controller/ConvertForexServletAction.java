package forex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forex.model.ForexBean;

/*
 Step 2 -  Use a servlet to handle requests. Servlet reads request parameters, checks for missing
and malformed data, calls business logic, etc
 */

@WebServlet("/process.action")
public class ConvertForexServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("inside the init() method");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//The Integer.parseInt() method converts a String value to an equivalent int value
		int amountInPeso = Integer.parseInt(request.getParameter("pesoAmount"));
		
		//objective is to retrieve the selected currency
		String currency = request.getParameter("currencyTarget");
		
		//step 2 - validation of input data - checking malformed or invalid input
		if ( (amountInPeso > 500 && amountInPeso <= 100000)) {			
			if (currency.equals("USD") || currency.equals("EUR") || currency.equals("JPY")) {
				
				/*
				 Step 3 - Obtain bean instances The servlet invokes business logic (application-specific
				code) or data-access code to obtain the results.   
				 */
				ForexBean forex = new ForexBean(amountInPeso, currency);
				forex.convertAmount();
				
				/*
				 Step 4 - Store the bean in the request, session, or
				servlet context. The servlet calls setAttribute on the request, session, or
				servlet context objects to store a reference to the beans
				that represent the results of the request. 
				 */
				//The setAttribute() perform the data object binding on request 
				request.setAttribute("myForexObject", forex);
				
				//Step 5 - Forward the request to another Servlet for Servlet-chain effect. 
				RequestDispatcher dispatcher = request.getRequestDispatcher("display.html");
				dispatcher.forward(request, response);				
			} else {
				response.sendRedirect("error.action");
			}			
		} else {
			//redirect to another page if the condition above is valid
			response.sendRedirect("error.action");
		}
	}
	
	public void destroy() {
		System.out.println("inside the destroy() method");
	}
}
