package Util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteService")
public class DeleteService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DeleteService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		String[] rows = request.getParameterValues("rowToDelete");
		String row = rows[0];
		PriceParser pp = new PriceParser();
		pp.deleteQuote(row);
		
		request.getRequestDispatcher("PricingService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}