package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private static boolean needChenille;
	private static boolean needKnit;
	private static boolean needGeorgeCanceled;
	private static String titleCode;
	private static String season;
	private static String design_type;
	private static String warp_type;
	private static String yarn_type;
	private static String colorist;
	private static String fabric_type;
	private static String designer;
	private static String direction;
	private static String content;
	private static String strikeoff;
	private static String blanket;
	private static String colorline;
	private static String rollsample;
	private static String test;
	private static double priceMin=0;
	private static double priceMax=10;
	
	public FilterService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Object filteredObj = session.getAttribute("filtered");
		boolean filtered = (filteredObj != null) ? (boolean) filteredObj : false;
		
		if (!filtered) {
			String temp[];
			
			DevData devData = new DevData();
	        ArrayList<Developments> developments = devData.getDevelopments();
	        Developments filterdev = new Developments();
	        
	        temp = request.getParameterValues("PriceRangeMin");
			if (temp != null) {
				priceMin = Double.parseDouble(temp[0]);
				filterdev.setPriceMin(priceMin);
				System.out.println("Successfully retrieved PriceRangeMin: " + priceMin + "\n");
			} else {
				System.out.println("Fail to retrieve PriceRangeMin.");
			}
			
			temp = request.getParameterValues("PriceRangeMax");
			if (temp != null) {
				priceMax = Double.parseDouble(temp[0]);
				filterdev.setPriceMax(priceMax);
				System.out.println("Successfully retrieved PriceRangeMax: " + priceMax + "\n");
			} else {
				System.out.println("Fail to retrieve PriceRangeMax.");
			}
			
			temp = request.getParameterValues("titleCode");
			if (temp != null) {
				titleCode = temp[0];
				filterdev.setCode(titleCode);			
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
			filterdev.setNeedFeedback(needFeedback);
			
			temp = request.getParameterValues("ParagonCleanCB");
			if (temp != null) {
				needParagonClean = true;
				System.out.println("ParagonCleanCB checked.\n");
			} else {
				needParagonClean = false;
				System.out.println("ParagonCleanCB unchecked.\n");
			}
			filterdev.setParagonClean(needParagonClean);
			
			temp = request.getParameterValues("FCLCB");
			if (temp != null) {
				need400hrFCL = true;
				System.out.println("FCLCB checked.\n");
			} else {
				need400hrFCL = false;
				System.out.println("FCLCB unchecked.\n");
			}
			filterdev.setIs400hrFCL(need400hrFCL);
			
			temp = request.getParameterValues("PDCB");
			if (temp != null) {
				needPieceDyed = true;
				System.out.println("PDCB checked.\n");
			} else {
				needPieceDyed = false;
				System.out.println("PDCB unchecked.\n");
			}
			filterdev.setPieceDyed(needPieceDyed);
			
			temp = request.getParameterValues("SDYCB");
			if (temp != null) {
				needSDY = true;
				System.out.println("SDYCB checked.\n");
			} else {
				needSDY = false;
				System.out.println("SDYCB unchecked.\n");
			}
			filterdev.setSDY(needSDY);
			
			temp = request.getParameterValues("ChenilleCB");
			if (temp != null) {
				needChenille = true;
				System.out.println("ChenilleCB checked.\n");
			} else {
				needChenille = false;
				System.out.println("ChenilleCB unchecked.\n");
			}
			filterdev.setChenille(needChenille);
			
			temp = request.getParameterValues("KnitCB");
			if (temp != null) {
				needKnit = true;
				System.out.println("KnitCB checked.\n");
			} else {
				needKnit = false;
				System.out.println("KnitCB unchecked.\n");
			}
			filterdev.setKnit(needKnit);
			
			temp = request.getParameterValues("GeorgeCancelCB");
			if (temp != null) {
				needGeorgeCanceled = true;
				System.out.println("GeorgeCancelCB checked.\n");
			} else {
				needGeorgeCanceled = false;
				System.out.println("GeorgeCancelCB unchecked.\n");
			}
			filterdev.setGeorgeCanceled(needGeorgeCanceled);
			
			temp = request.getParameterValues("Season");
			if (temp != null) {
				season = temp[0];
				filterdev.setSeason(season);
				System.out.println("Successfully retrieved Season: " + season + "\n");
			} else {
				System.out.println("Fail to retrieve Season.");
			}
			
			temp = request.getParameterValues("DesignType");
			if (temp != null) {
				design_type = temp[0];
				filterdev.setDesign_type(design_type);
				System.out.println("Successfully retrieved DesignType: " + design_type + "\n");
			} else {
				System.out.println("Fail to retrieve DesignType.");
			}
			
			temp = request.getParameterValues("WarpType");
			if (temp != null) {
				warp_type = temp[0];
				filterdev.setWarp_type(warp_type);
				System.out.println("Successfully retrieved WarpType: " + warp_type + "\n");
			} else {
				System.out.println("Fail to retrieve WarpType.");
			}
			
			temp = request.getParameterValues("YarnType");
			if (temp != null) {
				yarn_type = temp[0];
				filterdev.setYarn_type(yarn_type);
				System.out.println("Successfully retrieved YarnType: " + yarn_type + "\n");
			} else {
				System.out.println("Fail to retrieve YarnType.");
			}
			
			temp = request.getParameterValues("Colorist");
			if (temp != null) {
				colorist = temp[0];
				filterdev.setColorist(colorist);
				System.out.println("Successfully retrieved Colorist: " + colorist + "\n");
			} else {
				System.out.println("Fail to retrieve Colorist.");
			}
			
			temp = request.getParameterValues("Content");
			if (temp != null) {
				content = temp[0];
				filterdev.setContent(content);
				System.out.println("Successfully retrieved Content: " + content + "\n");
			} else {
				System.out.println("Fail to retrieve Content.");
			}
			
			temp = request.getParameterValues("FabricType");
			if (temp != null) {
				fabric_type = temp[0];
				filterdev.setFabric_type(fabric_type);
				System.out.println("Successfully retrieved FabricType: " + fabric_type + "\n");
			} else {
				System.out.println("Fail to retrieve FabricType.");
			}
			
			temp = request.getParameterValues("Designer");
			if (temp != null) {
				designer = temp[0];
				filterdev.setDesigner(designer);
				System.out.println("Successfully retrieved Designer: " + designer + "\n");
			} else {
				System.out.println("Fail to retrieve Designer.");
			}
			
			temp = request.getParameterValues("Direction");
			if (temp != null) {
				direction = temp[0];
				filterdev.setDirection(direction);
				System.out.println("Successfully retrieved Direction: " + direction + "\n");
			} else {
				System.out.println("Fail to retrieve Direction.");
			}
			
			temp = request.getParameterValues("StrikeProgress");
			if (temp != null) {
				strikeoff = temp[0];
				filterdev.setStrike_off_status(strikeoff);
				System.out.println("Successfully retrieved StrikeProgress: " + strikeoff + "\n");
			} else {
				System.out.println("Fail to retrieve StrikeProgress.");
			}
			
			temp = request.getParameterValues("BlanketStatus");
			if (temp != null) {
				blanket = temp[0];
				filterdev.setBlanket_status(blanket);
				System.out.println("Successfully retrieved BlanketStatus: " + blanket + "\n");
			} else {
				System.out.println("Fail to retrieve BlanketStatus.");
			}
			
			temp = request.getParameterValues("ColorLineProgress");
			if (temp != null) {
				colorline = temp[0];
				filterdev.setColorline_status(colorline);
				System.out.println("Successfully retrieved ColorLineProgress: " + colorline + "\n");
			} else {
				System.out.println("Fail to retrieve ColorLineProgress.");
			}
			
			temp = request.getParameterValues("RollSampleProgress");
			if (temp != null) {
				rollsample = temp[0];
				filterdev.setRollsample_status(rollsample);
				System.out.println("Successfully retrieved RollSampleProgress: " + rollsample + "\n");
			} else {
				System.out.println("Fail to retrieve RollSampleProgress.");
			}
			
			temp = request.getParameterValues("TestingProgress");
			if (temp != null) {
				test = temp[0];
				filterdev.setTest_status(test);
				System.out.println("Successfully retrieved TestingProgress: " + test + "\n");
			} else {
				System.out.println("Fail to retrieve TestingProgress.");
			}
	    	
			developments.removeIf(dev -> !filter(dev));
	    	
	    	Collections.reverse(developments);
	    	ObjectMapper objectMapper = new ObjectMapper();
		    String devJson = objectMapper.writeValueAsString(filterdev);
	    	session.setAttribute("filteredList", developments);
	    	session.setAttribute("filterdev", devJson);
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
        
        if (needChenille && !development.isChenille()) {
            return false;
        }
        
        if (needKnit && !development.isKnit()) {
            return false;
        }
        
        if (needGeorgeCanceled && !development.isGeorgeCanceled()) {
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
        
        if (!"".equals(fabric_type) && !fabric_type.equals(development.getFabric_type())) {
        	return false;
        }
        
        if (!"".equals(designer) && !designer.equals(development.getDesigner())) {
        	return false;
        }
        
        if (!"".equals(direction) && !direction.equals(development.getDirection())) {
        	return false;
        }
        
        if (!"".equals(content) && !content.equals(development.getContent())) {
        	return false;
        }
        
        if (!"".equals(strikeoff) && !"DNE".equals(strikeoff) && !strikeoff.equals(development.getStrike_off_status())) {
        	return false;
        }
        
        if (!"".equals(blanket) && !"DNE".equals(blanket) && !blanket.equals(development.getBlanket_status())) {
        	return false;
        }
        
        if (!"".equals(colorline) && !"DNE".equals(colorline) && !colorline.equals(development.getColorline_status())) {
        	return false;
        }
        
        if (!"".equals(rollsample) && !"DNE".equals(rollsample) && !rollsample.equals(development.getRollsample_status())) {
        	return false;
        }
        
        if (!"".equals(test) && !"DNE".equals(test) && !test.equals(development.getTest_status())) {
        	return false;
        }
        
        return true;
    }
}