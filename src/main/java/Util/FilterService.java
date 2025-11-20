package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/FilterService")
public class FilterService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static boolean needParagonClean;
	private static boolean need400hrFCL;
	private static boolean need1000hrFCL;
	private static boolean needPieceDyed;
	private static boolean needFeedback;
	private static boolean needChinaFeedback;
	private static boolean needSDY;
	private static boolean needChenille;
	private static boolean needKnit;
	private static boolean needGeorgeCanceled;
	private static boolean active;
	private static boolean priceConfirmed;
	private static String titleCode;
	private static String season;
	private static String design_type;
	private static String warp_type;
	private static String yarn_type;
	private static String colorist;
	private static String fabric_type;
	private static String designer;
	private static String direction;
	private static String strikeoff;
	private static String blanket;
	private static String colorline;
	private static String rollsample;
	private static String test;
	private static double priceMin=0;
	private static double priceMax=10;
	Set<String> selectedStrikeProgress = new HashSet<>();
	Set<String> selectedBlanketProgress = new HashSet<>();
	Set<String> selectedTestProgress = new HashSet<>();
	
	public FilterService() {}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
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
		
		temp = request.getParameterValues("ChinaFeedbackCB");
		if (temp != null) {
			needChinaFeedback = true;
			System.out.println("ChinaFeedbackCB checked.\n");
		} else {
			needChinaFeedback = false;
			System.out.println("ChinaFeedbackCB unchecked.\n");
		}
		filterdev.setNeedChinaFeedback(needChinaFeedback);
		
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
		
		temp = request.getParameterValues("TFCLCB");
		if (temp != null) {
			need1000hrFCL = true;
			System.out.println("TFCLCB checked.\n");
		} else {
			need1000hrFCL = false;
			System.out.println("TFCLCB unchecked.\n");
		}
		filterdev.setIs1000hrFCL(need1000hrFCL);;
		
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
		
		temp = request.getParameterValues("ActiveCB");
		if (temp != null) {
			active = true;
			System.out.println("ActiveCB checked.\n");
		} else {
			active = false;
			System.out.println("ActiveCB unchecked.\n");
		}
		filterdev.setInactive(!active);
		
		temp = request.getParameterValues("priceConfirmCB");
		if (temp != null) {
			priceConfirmed = true;
			System.out.println("priceConfirmCB checked.\n");
		} else {
			priceConfirmed = false;
			System.out.println("priceConfirmCB unchecked.\n");
		}
		filterdev.setPriceConfirmed(priceConfirmed);
		
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
		
		selectedStrikeProgress.clear();
		temp = request.getParameterValues("StrikeProgress");
		if (temp != null) {
			String[] values = temp[0].split("!");
			for (int i = 0; i < values.length; i++) {
			    // Remove leading spaces, commas, and tabs from each item
			    values[i] = values[i].replaceAll("^[\\s,]+", "").trim();
			}
			strikeoff = String.join("!", values);
			filterdev.setStrike_off_status(strikeoff);
			System.out.println("Successfully retrieved StrikeProgress: " + strikeoff + "\n");
			selectedStrikeProgress.addAll(Arrays.asList(values));
			System.out.println("selectedStrikeProgress: " + selectedStrikeProgress + "\n");
		} else {
			System.out.println("Fail to retrieve StrikeProgress.");
		}
		
		selectedBlanketProgress.clear();
		temp = request.getParameterValues("BlanketStatus");
		if (temp != null) {
			String[] values = temp[0].split("!");
			for (int i = 0; i < values.length; i++) {
			    // Remove leading spaces, commas, and tabs from each item
			    values[i] = values[i].replaceAll("^[\\s,]+", "").trim();
			}
			blanket = String.join("!", values);
			filterdev.setBlanket_status(blanket);
			System.out.println("Successfully retrieved BlanketStatus: " + blanket + "\n");
			selectedBlanketProgress.addAll(Arrays.asList(values));
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
		
		selectedTestProgress.clear();
		temp = request.getParameterValues("TestingProgress");
		if (temp != null) {
			String[] values = temp[0].split("!");
			for (int i = 0; i < values.length; i++) {
			    // Remove leading spaces, commas, and tabs from each item
			    values[i] = values[i].replaceAll("^[\\s,]+", "").trim();
			}
			test = String.join("!", values);
			filterdev.setTest_status(test);
			System.out.println("Successfully retrieved TestingProgress: " + test + "\n");
			selectedTestProgress.addAll(Arrays.asList(values));
		} else {
			System.out.println("Fail to retrieve TestingProgress.");
		}
    	
		developments.removeIf(dev -> !filter(dev));
		developments.sort(Comparator.comparing(Developments::getCode));

        // Get the current page from request (default to 1 if not set)
        int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        session.setAttribute("filteredPageNumber", currentPage);
        if (request.getParameter("page") != null) {
			developments = (ArrayList<Developments>) session.getAttribute("filteredList");
        } else {
        	session.setAttribute("filteredList", developments);
        	ObjectMapper objectMapper = new ObjectMapper();
    	    String devJson = objectMapper.writeValueAsString(filterdev);
    	    session.setAttribute("filterdev", devJson);
        }
        
        int itemsPerPage = 15;
        int totalItems = developments.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        // Calculate the starting index for the sublist
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        // Get the sublist for the current page
        List<Developments> currentPageList = developments.subList(startIndex, endIndex);
        request.setAttribute("currentPageList", currentPageList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("filtered", true);
        request.setAttribute("sorted", false);

		request.getRequestDispatcher("/tracker.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean filter(Developments development) {
		if (!titleCode.isEmpty()) {
		    String tc = titleCode.toLowerCase();
		    String code  = development.getCode()  == null ? "" : development.getCode().toLowerCase();
		    String color = development.getColor() == null ? "" : development.getColor().toLowerCase();

		    if (!code.contains(tc) && !color.contains(tc)) {
		        return false;
		    }
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
        
        if (need1000hrFCL && !development.isIs1000hrFCL()) {
            return false;
        }
        
        if (needPieceDyed && !development.isPieceDyed()) {
            return false;
        }
        
        if (needFeedback && !development.isNeedFeedback()) {
            return false;
        }
        
        if (needChinaFeedback && !development.isNeedChinaFeedback()) {
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
        
        if (active && development.isInactive()) {
            return false;
        }
        
        if (!active && !development.isInactive()) {
            return false;
        }
        
        if (priceConfirmed && !development.isPriceConfirmed()) {
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
        
        if (!selectedStrikeProgress.isEmpty() && !selectedStrikeProgress.contains("") && !selectedStrikeProgress.contains(development.getStrike_off_status())) {
    	    return false;
    	}
        
        if (!selectedBlanketProgress.isEmpty() && !selectedBlanketProgress.contains("") && !selectedBlanketProgress.contains(development.getBlanket_status())) {
    	    return false;
    	}
        
        if (!"".equals(colorline) && !"DNE".equals(colorline) && !colorline.equals(development.getColorline_status())) {
        	return false;
        }
        
        if (!"".equals(rollsample) && !"DNE".equals(rollsample) && !rollsample.equals(development.getRollsample_status())) {
        	return false;
        }
        
        if (!selectedTestProgress.isEmpty() && !selectedTestProgress.contains("") && !selectedTestProgress.contains(development.getTest_status())) {
    	    return false;
    	}
        
        return true;
    }
}