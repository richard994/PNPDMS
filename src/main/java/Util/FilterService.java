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

@WebServlet("/FilterService")
public class FilterService extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// Shadow-verification switch for the SQL-based filter rewrite (DevData.getFilteredDevelopments).
	// While false: the old in-memory (full-table-scan) filtering below still SERVES every request,
	// unchanged. The SQL path also runs on every request and any mismatch against the old path's
	// result set is logged - watch for "FILTER MISMATCH" in the server log under real usage before
	// flipping this to true. See docs/QA_CHECKLIST.md.
	private static final boolean SERVE_SQL_FILTER = false;

	public FilterService() {}

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String temp[];
		boolean needParagonClean;
		boolean need400hrFCL;
		boolean need1000hrFCL;
		boolean needPieceDyed;
		boolean needFeedback;
		boolean needChinaFeedback;
		boolean needSDY;
		boolean needChenille;
		boolean needKnit;
		boolean needGeorgeCanceled;
		boolean active;
		boolean inactive;
		boolean showAll;
		boolean priceConfirmed;
		String titleCode = "";
		String season = "";
		String design_type = "";
		String warp_type = "";
		String yarn_type = "";
		String colorist = "";
		String fabric_type = "";
		String designer = "";
		String direction = "";
		String strikeoff;
		String blanket;
		String colorline = "";
		String rollsample = "";
		String test;
		double priceMin = 0;
		double priceMax = 10;
		Set<String> selectedStrikeProgress = new HashSet<>();
		Set<String> selectedBlanketProgress = new HashSet<>();
		Set<String> selectedTestProgress = new HashSet<>();

		DevData devData = new DevData();
        ArrayList<Developments> developments = devData.getDevelopments();
        Developments filterdev = new Developments();

        temp = request.getParameterValues("PriceRangeMin");
		if (temp != null) {
			priceMin = Double.parseDouble(temp[0]);
			System.out.println("Successfully retrieved PriceRangeMin: " + priceMin + "\n");
		} else {
			System.out.println("Fail to retrieve PriceRangeMin.");
		}
		filterdev.setPriceMin(priceMin);

		temp = request.getParameterValues("PriceRangeMax");
		if (temp != null) {
			priceMax = Double.parseDouble(temp[0]);
			System.out.println("Successfully retrieved PriceRangeMax: " + priceMax + "\n");
		} else {
			System.out.println("Fail to retrieve PriceRangeMax.");
		}
		filterdev.setPriceMax(priceMax);

		temp = request.getParameterValues("titleCode");
		if (temp != null) {
			titleCode = temp[0];
			System.out.println("Successfully retrieved title: " + titleCode + "\n");
		} else {
			System.out.println("Fail to retrieve title.");
		}
		filterdev.setCode(titleCode);

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
		filterdev.setIs1000hrFCL(need1000hrFCL);

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

		temp = request.getParameterValues("InactiveCB");
		if (temp != null) {
			inactive = true;
			System.out.println("InactiveCB checked.\n");
		} else {
			inactive = false;
			System.out.println("InactiveCB unchecked.\n");
		}

		boolean showAllTemp = false;
		if (active && inactive) {
		    showAllTemp = true;
		} else if (active || !inactive) {
			filterdev.setInactive(false);
		} else {
		    filterdev.setInactive(true);
		}
		showAll = showAllTemp;

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
			System.out.println("Successfully retrieved Season: " + season + "\n");
		} else {
			System.out.println("Fail to retrieve Season.");
		}
		filterdev.setSeason(season);

		temp = request.getParameterValues("DesignType");
		if (temp != null) {
			design_type = temp[0];
			System.out.println("Successfully retrieved DesignType: " + design_type + "\n");
		} else {
			System.out.println("Fail to retrieve DesignType.");
		}
		filterdev.setDesign_type(design_type);

		temp = request.getParameterValues("WarpType");
		if (temp != null) {
			warp_type = temp[0];
			System.out.println("Successfully retrieved WarpType: " + warp_type + "\n");
		} else {
			System.out.println("Fail to retrieve WarpType.");
		}
		filterdev.setWarp_type(warp_type);

		temp = request.getParameterValues("YarnType");
		if (temp != null) {
			yarn_type = temp[0];
			System.out.println("Successfully retrieved YarnType: " + yarn_type + "\n");
		} else {
			System.out.println("Fail to retrieve YarnType.");
		}
		filterdev.setYarn_type(yarn_type);

		temp = request.getParameterValues("Colorist");
		if (temp != null) {
			colorist = temp[0];
			System.out.println("Successfully retrieved Colorist: " + colorist + "\n");
		} else {
			System.out.println("Fail to retrieve Colorist.");
		}
		filterdev.setColorist(colorist);

		temp = request.getParameterValues("FabricType");
		if (temp != null) {
			fabric_type = temp[0];
			System.out.println("Successfully retrieved FabricType: " + fabric_type + "\n");
		} else {
			System.out.println("Fail to retrieve FabricType.");
		}
		filterdev.setFabric_type(fabric_type);

		temp = request.getParameterValues("Designer");
		if (temp != null) {
			designer = temp[0];
			System.out.println("Successfully retrieved Designer: " + designer + "\n");
		} else {
			System.out.println("Fail to retrieve Designer.");
		}
		filterdev.setDesigner(designer);

		temp = request.getParameterValues("Direction");
		if (temp != null) {
			direction = temp[0];
			System.out.println("Successfully retrieved Direction: " + direction + "\n");
		} else {
			System.out.println("Fail to retrieve Direction.");
		}
		filterdev.setDirection(direction);

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
			System.out.println("Successfully retrieved ColorLineProgress: " + colorline + "\n");
		} else {
			System.out.println("Fail to retrieve ColorLineProgress.");
		}
		filterdev.setColorline_status(colorline);

		temp = request.getParameterValues("RollSampleProgress");
		if (temp != null) {
			rollsample = temp[0];
			System.out.println("Successfully retrieved RollSampleProgress: " + rollsample + "\n");
		} else {
			System.out.println("Fail to retrieve RollSampleProgress.");
		}
		filterdev.setRollsample_status(rollsample);

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

		developments.removeIf(dev -> !filter(dev, filterdev, showAll, selectedStrikeProgress, selectedBlanketProgress, selectedTestProgress));
		developments.sort(Comparator.comparing(Developments::getCode));

		verifySqlFilterShadow(devData, filterdev, showAll, selectedStrikeProgress, selectedBlanketProgress, selectedTestProgress, developments);
		if (SERVE_SQL_FILTER) {
			developments = devData.getFilteredDevelopments(filterdev, showAll, selectedStrikeProgress, selectedBlanketProgress, selectedTestProgress);
		}

        // Get the current page from request (default to 1 if not set or invalid)
        int currentPage;
        try {
        	currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        } catch (NumberFormatException e) {
        	currentPage = 1;
        }
        if (request.getParameter("page") != null) {
			ArrayList<Developments> sessionFiltered = (ArrayList<Developments>) session.getAttribute("filteredList");
			if (sessionFiltered != null) {
				developments = sessionFiltered;
			}
        } else {
        	session.setAttribute("filteredList", developments);
    	    String devJson = JsonUtil.toScriptSafeJson(filterdev);
    	    session.setAttribute("filterdev", devJson);
        }

        int itemsPerPage = 15;
        int totalItems = developments.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        currentPage = Math.max(1, Math.min(currentPage, Math.max(1, totalPages)));
        session.setAttribute("filteredPageNumber", currentPage);

        // Calculate the starting index for the sublist
        int startIndex = Math.min((currentPage - 1) * itemsPerPage, totalItems);
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
	
	// package-private (not private) so unit tests in this package can exercise it directly
	boolean filter(Developments development, Developments filterdev, boolean showAll,
			Set<String> selectedStrikeProgress, Set<String> selectedBlanketProgress, Set<String> selectedTestProgress) {
		String titleCode = filterdev.getCode();
		if (titleCode != null && !titleCode.isEmpty()) {
		    String tc = titleCode.toLowerCase();
		    String code  = development.getCode()  == null ? "" : development.getCode().toLowerCase();
		    String color = development.getColor() == null ? "" : development.getColor().toLowerCase();

		    if (!code.contains(tc) && !color.contains(tc)) {
		        return false;
		    }
		}

		double cost = development.getCost();
		if (cost < filterdev.getPriceMin() || cost > filterdev.getPriceMax()) {
			return false;
		}

    	if (filterdev.isParagonClean() && !development.isParagonClean()) {
            return false;
        }

        if (filterdev.isIs400hrFCL() && !development.isIs400hrFCL()) {
            return false;
        }

        if (filterdev.isIs1000hrFCL() && !development.isIs1000hrFCL()) {
            return false;
        }

        if (filterdev.isPieceDyed() && !development.isPieceDyed()) {
            return false;
        }

        if (filterdev.isNeedFeedback() && !development.isNeedFeedback()) {
            return false;
        }

        if (filterdev.isNeedChinaFeedback() && !development.isNeedChinaFeedback()) {
            return false;
        }

        if (filterdev.isSDY() && !development.isSDY()) {
            return false;
        }

        if (filterdev.isChenille() && !development.isChenille()) {
            return false;
        }

        if (filterdev.isKnit() && !development.isKnit()) {
            return false;
        }

        if (filterdev.isGeorgeCanceled() && !development.isGeorgeCanceled()) {
            return false;
        }

        if (!showAll && (filterdev.isInactive() != development.isInactive())) {
            return false;
        }

        if (filterdev.isPriceConfirmed() && !development.isPriceConfirmed()) {
            return false;
        }

        if (!isBlank(filterdev.getSeason()) && !filterdev.getSeason().equals(development.getSeason())) {
        	return false;
        }

        if (!isBlank(filterdev.getDesign_type()) && !filterdev.getDesign_type().equals(development.getDesign_type())) {
        	return false;
        }

        if (!isBlank(filterdev.getWarp_type()) && !filterdev.getWarp_type().equals(development.getWarp_type())) {
        	return false;
        }

        if (!isBlank(filterdev.getYarn_type()) && !filterdev.getYarn_type().equals(development.getYarn_type())) {
        	return false;
        }

        if (!isBlank(filterdev.getColorist()) && !filterdev.getColorist().equals(development.getColorist())) {
        	return false;
        }

        if (!isBlank(filterdev.getFabric_type()) && !filterdev.getFabric_type().equals(development.getFabric_type())) {
        	return false;
        }

        if (!isBlank(filterdev.getDesigner()) && !filterdev.getDesigner().equals(development.getDesigner())) {
        	return false;
        }

        if (!isBlank(filterdev.getDirection()) && !filterdev.getDirection().equals(development.getDirection())) {
        	return false;
        }

        if (!selectedStrikeProgress.isEmpty() && !selectedStrikeProgress.contains("") && !selectedStrikeProgress.contains(development.getStrike_off_status())) {
    	    return false;
    	}

        if (!selectedBlanketProgress.isEmpty() && !selectedBlanketProgress.contains("") && !selectedBlanketProgress.contains(development.getBlanket_status())) {
    	    return false;
    	}

        String colorline = filterdev.getColorline_status();
        if (!isBlank(colorline) && !"DNE".equals(colorline) && !colorline.equals(development.getColorline_status())) {
        	return false;
        }

        String rollsample = filterdev.getRollsample_status();
        if (!isBlank(rollsample) && !"DNE".equals(rollsample) && !rollsample.equals(development.getRollsample_status())) {
        	return false;
        }

        if (!selectedTestProgress.isEmpty() && !selectedTestProgress.contains("") && !selectedTestProgress.contains(development.getTest_status())) {
    	    return false;
    	}

        return true;
    }

	private boolean isBlank(String s) {
		return s == null || s.isEmpty();
	}

	// Logs a diff between the old (Java-filtered) and new (SQL-filtered) result sets for the same
	// request, by dev_id, without affecting what's served. See SERVE_SQL_FILTER above.
	private void verifySqlFilterShadow(DevData devData, Developments filterdev, boolean showAll,
			Set<String> selectedStrikeProgress, Set<String> selectedBlanketProgress, Set<String> selectedTestProgress,
			ArrayList<Developments> javaFiltered) {
		try {
			ArrayList<Developments> sqlFiltered = devData.getFilteredDevelopments(filterdev, showAll,
					selectedStrikeProgress, selectedBlanketProgress, selectedTestProgress);
			Set<Integer> oldIds = new HashSet<>();
			for (Developments d : javaFiltered) {
				oldIds.add(d.getDev_id());
			}
			Set<Integer> newIds = new HashSet<>();
			for (Developments d : sqlFiltered) {
				newIds.add(d.getDev_id());
			}
			if (oldIds.equals(newIds)) {
				System.out.println("FILTER SHADOW CHECK: match (" + oldIds.size() + " developments)");
			} else {
				Set<Integer> javaOnly = new HashSet<>(oldIds);
				javaOnly.removeAll(newIds);
				Set<Integer> sqlOnly = new HashSet<>(newIds);
				sqlOnly.removeAll(oldIds);
				System.out.println("FILTER MISMATCH: java-only dev_ids=" + javaOnly + " sql-only dev_ids=" + sqlOnly);
			}
		} catch (Exception e) {
			System.out.println("FILTER SHADOW CHECK failed: " + e.getMessage());
		}
	}
}