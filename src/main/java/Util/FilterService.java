package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FilterService")
public class FilterService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static boolean needParagonClean;
	private static boolean need400hrFCL;
	private static boolean needPieceDyed;
	private static boolean needFeedback;
	private static boolean needSDY;
	private static String titleCode;
	private static String season;
	private static String design_type;
	private static String warp_type;
	private static String yarn_type;
	private static String colorist;
	private static double priceMin=0;
	private static double priceMax=10;
	
	public FilterService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		boolean filtered = (boolean) session.getAttribute("filtered");
		
		if (!filtered) {
			String temp[];
			
			DevData devData = new DevData();
	        ArrayList<Developments> developments = devData.getDevelopments();
	        
	        temp = request.getParameterValues("PriceRangeMin");
			if (temp != null) {
				priceMin = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved PriceRangeMin: " + priceMin + "\n");
			} else {
				System.out.println("Fail to retrieve PriceRangeMin.");
			}
			
			temp = request.getParameterValues("PriceRangeMax");
			if (temp != null) {
				priceMax = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved PriceRangeMax: " + priceMax + "\n");
			} else {
				System.out.println("Fail to retrieve PriceRangeMax.");
			}
			
			temp = request.getParameterValues("titleCode");
			if (temp != null) {
				titleCode = temp[0];
				System.out.println("Successfully retrieved title: " + titleCode + "\n");
			} else {
				System.out.println("Fail to retrieve title.");
			}
			
			temp = request.getParameterValues("FeedbackCB");
			if (temp != null) {
				needFeedback = true;
				System.out.println("FeedbackCB checked.\n");
			} else {
				needFeedback = false;
				System.out.println("FeedbackCB unchecked.\n");
			}
			
			temp = request.getParameterValues("ParagonCleanCB");
			if (temp != null) {
				needParagonClean = true;
				System.out.println("ParagonCleanCB checked.\n");
			} else {
				needParagonClean = false;
				System.out.println("ParagonCleanCB unchecked.\n");
			}
			
			temp = request.getParameterValues("FCLCB");
			if (temp != null) {
				need400hrFCL = true;
				System.out.println("FCLCB checked.\n");
			} else {
				need400hrFCL = false;
				System.out.println("FCLCB unchecked.\n");
			}
			
			temp = request.getParameterValues("PDCB");
			if (temp != null) {
				needPieceDyed = true;
				System.out.println("PDCB checked.\n");
			} else {
				needPieceDyed = false;
				System.out.println("PDCB unchecked.\n");
			}
			
			temp = request.getParameterValues("SDYCB");
			if (temp != null) {
				needSDY = true;
				System.out.println("SDYCB checked.\n");
			} else {
				needSDY = false;
				System.out.println("SDYCB unchecked.\n");
			}
			
			temp = request.getParameterValues("Season");
			if (temp != null) {
				season = temp[0];
				System.out.println("Successfully retrieved Season: " + season + "\n");
			} else {
				System.out.println("Fail to retrieve Season.");
			}
			
			temp = request.getParameterValues("DesignType");
			if (temp != null) {
				design_type = temp[0];
				System.out.println("Successfully retrieved DesignType: " + design_type + "\n");
			} else {
				System.out.println("Fail to retrieve DesignType.");
			}
			
			temp = request.getParameterValues("WarpType");
			if (temp != null) {
				warp_type = temp[0];
				System.out.println("Successfully retrieved WarpType: " + warp_type + "\n");
			} else {
				System.out.println("Fail to retrieve WarpType.");
			}
			
			temp = request.getParameterValues("YarnType");
			if (temp != null) {
				yarn_type = temp[0];
				System.out.println("Successfully retrieved YarnType: " + yarn_type + "\n");
			} else {
				System.out.println("Fail to retrieve YarnType.");
			}
			
			temp = request.getParameterValues("Colorist");
			if (temp != null) {
				colorist = temp[0];
				System.out.println("Successfully retrieved Colorist: " + colorist + "\n");
			} else {
				System.out.println("Fail to retrieve Colorist.");
			}
	    	
			developments.removeIf(dev -> !filter(dev));
	    	
	    	Collections.reverse(developments);
	    	session.setAttribute("filteredList", developments);
	    	session.setAttribute("filtered", true);
	        int itemsPerPage = 9;
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
	        request.setAttribute("filtered", true);
	
			request.getRequestDispatcher("/tracker.jsp").forward(request, response);
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Developments> developments = (ArrayList<Developments>) session.getAttribute("filteredList");
			int itemsPerPage = 9;
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
	        request.setAttribute("filtered", true);
	        session.setAttribute("filtered", true);
	
			request.getRequestDispatcher("/tracker.jsp").forward(request, response);
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean filter(Developments development) {
		if (!"".equals(titleCode) && !titleCode.equals(development.getCode())) {
			return false;
		}
		
		double cost = development.getCost();
		if (cost < priceMin || cost > priceMax) {
			return false;
		}
		
    	if (needParagonClean && !development.isParagonClean()) {
            return false;
        }
    	
        if (need400hrFCL && !development.isIs400hrFCL()) {
            return false;
        }
        
        if (needPieceDyed && !development.isPieceDyed()) {
            return false;
        }
        
        if (needFeedback && !development.isNeedFeedback()) {
            return false;
        }
        
        if (needSDY && !development.isSDY()) {
            return false;
        }
        
        if (!"".equals(season) && !season.equals(development.getSeason())) {
        	return false;
        }
        
        if (!"".equals(design_type) && !design_type.equals(development.getDesign_type())) {
        	return false;
        }
        
        if (!"".equals(warp_type) && !warp_type.equals(development.getWarp_type())) {
        	return false;
        }
        
        if (!"".equals(yarn_type) && !yarn_type.equals(development.getYarn_type())) {
        	return false;
        }
        
        if (!"".equals(colorist) && !colorist.equals(development.getColorist())) {
        	return false;
        }
        
        return true;
    }
}