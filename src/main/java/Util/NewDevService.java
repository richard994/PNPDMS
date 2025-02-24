package Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			    String username = (String) session.getAttribute("userName");
			    request.setAttribute("user", username);
			    int devId = Integer.parseInt(request.getParameter("devId"));
			    request.setAttribute("devid", devId);

			    // Fetch the development data and associated comments and logs
			    DevData devdata = new DevData();

			    // Determine action and set appropriate flags
			    if ("view".equals(action)) {
			        request.setAttribute("view", true);
			        request.setAttribute("edit", false);
			        request.setAttribute("create", false);
			    } else if ("edit".equals(action) || "deleteCmt".equals(action)) {
			        if ("deleteCmt".equals(action)) {
			            int comment_id = Integer.parseInt(request.getParameter("cmtId"));
			            devdata.deleteCommentById(devId, comment_id);
			        }
			        request.setAttribute("view", false);
			        request.setAttribute("edit", true);
			        request.setAttribute("create", false);
			    } else {
			        request.setAttribute("view", false);
			        request.setAttribute("edit", false);
			        request.setAttribute("create", true);
			    }
			    
			    Developments dev = devdata.getDevelopmentById(devId);
			    ArrayList<Comment> comments = devdata.getCommentsById(devId);
			    ArrayList<Log> logs = devdata.getLogsById(devId);
			    List<Log> showLogs = logs.subList(0, Math.min(logs.size(), 13));

			    // Convert objects to JSON
			    ObjectMapper objectMapper = new ObjectMapper();
			    String devJson = objectMapper.writeValueAsString(dev);
			    String commentJson = objectMapper.writeValueAsString(comments);
			    String logJson = objectMapper.writeValueAsString(showLogs);
			    String fullLogJson = objectMapper.writeValueAsString(logs);

			    // Set common attributes
			    request.setAttribute("dev", devJson);
			    request.setAttribute("comments", commentJson);
			    request.setAttribute("logs", logJson);
			    request.setAttribute("fulllogs", fullLogJson);
			    
			    // Forward to the appropriate page
			    request.getRequestDispatcher("/AddNewDev.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}