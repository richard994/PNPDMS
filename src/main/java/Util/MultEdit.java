package Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MultEdit")
public class MultEdit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public MultEdit() {}
	
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
			    String username = (String) session.getAttribute("userName");
			    request.setAttribute("user", username);

			    DevData devdata = new DevData();
			    ArrayList<Developments> dev = devdata.getDevelopments();
			    ObjectMapper objectMapper = new ObjectMapper();
			    String devJson = objectMapper.writeValueAsString(dev);
			    request.setAttribute("dev", devJson);
			    
			    // Forward to the appropriate page
			    request.getRequestDispatcher("/MultEdit.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}