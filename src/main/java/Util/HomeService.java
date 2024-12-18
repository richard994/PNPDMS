package Util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeService")
public class HomeService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public HomeService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if (session.getAttribute("loggedin") == null) {
			request.setAttribute("login", "no");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			boolean loggedin = (boolean) session.getAttribute("loggedin");
			if (!loggedin) {
				request.setAttribute("login", "no");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				request.setAttribute("login", "yes");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}