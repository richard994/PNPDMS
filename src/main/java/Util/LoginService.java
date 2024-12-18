package Util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginService")
public class LoginService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public LoginService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		String email = "";
		String password = "";
		String[] emailBuffer = request.getParameterValues("email");
		String[] passwordBuffer = request.getParameterValues("password");
		email = emailBuffer[0];
		password = passwordBuffer[0];
		
		UserData ud = new UserData();
		boolean exist = ud.CheckExist(email, password);
		session.setAttribute("loggedin", exist);
		session.setMaxInactiveInterval(1200);
		
		if (exist) {
			request.getRequestDispatcher("HomeService").forward(request, response);
		} else {
			request.setAttribute("wrongInput", "yes");
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}