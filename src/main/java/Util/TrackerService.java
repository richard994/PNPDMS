	package Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TrackerService")
public class TrackerService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public TrackerService() {}
	
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
				String action = request.getParameter("action");
				request.setAttribute("filtered", false);
				request.setAttribute("sorted", false);
				
				String username = (String) session.getAttribute("userName");
				request.setAttribute("user", username);
				
		        DevData devData = new DevData();
		        ArrayList<Developments> developments = devData.getDevelopments();
		        if ("sort".equals(action)) {
		        	Collections.reverse(developments);
		        	request.setAttribute("sorted", true);
		        } else {
		        	developments.sort(Comparator.comparing(Developments::getCode));
		        }
		        int itemsPerPage = 15;
		        int totalItems = developments.size();
		        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		        // Get the current page from request (default to 1 if not set)
		        int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");

		        // Calculate the starting index for the sublist
		        int startIndex = (currentPage - 1) * itemsPerPage;
		        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		        // Get the sublist for the current page
		        List<Developments> currentPageList = developments.subList(startIndex, endIndex);
		        request.setAttribute("currentPageList", currentPageList);
		        request.setAttribute("totalPages", totalPages);
		        request.setAttribute("currentPage", currentPage);

				request.getRequestDispatcher("/tracker.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}