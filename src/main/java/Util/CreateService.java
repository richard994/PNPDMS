package Util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateService")
public class CreateService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static List<String> finishingsList;
	private static List<String> matList;
	private static int numFinish;
	private static String finString;
	private static String matStr;
	private static String matColorPriceStr;
	private static String matDrPriceStr;
	private static String matPrPriceStr;
	private static String matWhitePriceStr;
	private static String matDyePriceStr;
	private static String matMtrtypeStr;
	private static String matCgStr;
	private static String matCzStr;
	private static String matLossStr;
	
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
				FinishData findata = new FinishData();
				finishingsList = findata.getFinishingList();
				MatData matdata = new MatData();
				matList = matdata.getMatList();
				matStr = matdata.getMatStr();
				matColorPriceStr = matdata.getMatColorPriceStr();
				matDrPriceStr = matdata.getMatDrPriceStr();
				matPrPriceStr = matdata.getMatPrPriceStr();
				matWhitePriceStr = matdata.getMatWhitePriceStr();
				matDyePriceStr = matdata.getMatDyePriceStr();
				matMtrtypeStr = matdata.getMatMtrtypeStr();
				matCgStr = matdata.getMatCgStr();
				matCzStr = matdata.getMatCzStr();
				matLossStr = matdata.getMatLossStr();
				numFinish = finishingsList.size();
				finString = findata.getFinishingString();
				
				
				request.setAttribute("matList", matList);
				request.setAttribute("finishingsList", finishingsList);
				request.setAttribute("numFinish", numFinish);
				request.setAttribute("finString", finString);
				request.setAttribute("matStr", matStr);
				request.setAttribute("matColorPriceStr", matColorPriceStr);
				request.setAttribute("matDrPriceStr", matDrPriceStr);
				request.setAttribute("matPrPriceStr", matPrPriceStr);
				request.setAttribute("matWhitePriceStr", matWhitePriceStr);
				request.setAttribute("matDyePriceStr", matDyePriceStr);
				request.setAttribute("matMtrtypeStr", matMtrtypeStr);
				request.setAttribute("matCgStr", matCgStr);
				request.setAttribute("matCzStr", matCzStr);
				request.setAttribute("matLossStr", matLossStr);
				request.getRequestDispatcher("/createqp.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}