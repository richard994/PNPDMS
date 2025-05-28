package Util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/CreateService")
public class CreateService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public CreateService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if (session.getAttribute("loggedin") == null) {
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
		} else {
			boolean loggedin = (boolean) session.getAttribute("loggedin");
			if (!loggedin) {
				request.getRequestDispatcher("/auth.jsp").forward(request, response);
			} else {
				request.setAttribute("edit", false);
				PriceData pricedata = new PriceData();
				ArrayList<Mats> mats = pricedata.getMatList();
				ObjectMapper objectMapper = new ObjectMapper();
				String matJson = objectMapper.writeValueAsString(mats);
				
				int quoteId = 0;
				String action = request.getParameter("action");
				if ("edit".equals(action)) {
					quoteId = Integer.parseInt(request.getParameter("quoteId"));
					request.setAttribute("edit", true);
				}
				
				Quotes quote = pricedata.getQuoteById(quoteId);
				ArrayList<Mats> materials = pricedata.getMaterialsById(quoteId);
				String quoteJson = objectMapper.writeValueAsString(quote);
				String qmatJson = objectMapper.writeValueAsString(materials);
				request.setAttribute("quoteJson", quoteJson);
				request.setAttribute("qmatJson", qmatJson);
				
				request.setAttribute("matList", matJson);
				request.getRequestDispatcher("/createqp.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}