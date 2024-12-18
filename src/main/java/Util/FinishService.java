package Util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FinishService")
public class FinishService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static List<String> finishingsList;
	private static int numFinish;
	
	public FinishService() {}
	
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
				FinishData findata = new FinishData();
				finishingsList = findata.getFinishingList();
				numFinish = finishingsList.size();
				int remainder = numFinish % 3;
				for (int i=0; i<remainder; i++) {
					finishingsList.add("EMPTY");
				}
				request.setAttribute("numFinish", numFinish);
				request.setAttribute("finishingsList", finishingsList);
				request.getRequestDispatcher("/finishing.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}