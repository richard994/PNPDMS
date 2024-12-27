package Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NewDevService")
public class NewDevService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public NewDevService() {}
	
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
				if ("view".equals(action)) {
					DevData devdata = new DevData();
					int devId = Integer.parseInt(request.getParameter("devId"));
					Developments dev = devdata.getDevelopmentById(devId);
					ObjectMapper objectMapper = new ObjectMapper();
					String devJson = objectMapper.writeValueAsString(dev);
					request.setAttribute("dev", devJson);
					request.setAttribute("view", true);
					request.setAttribute("edit", false);
					request.setAttribute("create", false);
					request.getRequestDispatcher("/AddNewDev.jsp").forward(request, response);
				} else if ("edit".equals(action)) {
					DevData devdata = new DevData();
					int devId = Integer.parseInt(request.getParameter("devId"));
					Developments dev = devdata.getDevelopmentById(devId);
					ObjectMapper objectMapper = new ObjectMapper();
					String devJson = objectMapper.writeValueAsString(dev);
					request.setAttribute("dev", devJson);
					request.setAttribute("view", false);
					request.setAttribute("edit", true);
					request.setAttribute("create", false);
					request.getRequestDispatcher("/AddNewDev.jsp").forward(request, response);
				} else {
					request.setAttribute("view", false);
					request.setAttribute("edit", false);
					request.setAttribute("create", true);
					request.getRequestDispatcher("/AddNewDev.jsp").forward(request, response);
				}
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}