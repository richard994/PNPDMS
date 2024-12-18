package Util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditService")
public class EditService extends HttpServlet{
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
	
	public EditService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
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
		
		String[] quotes = request.getParameterValues("quoteToEdit");
		String quoteid = quotes[0];
		PriceParser pp = new PriceParser();
		Quotes quote = pp.getQuote(quoteid);
		String mats = pp.getMaterials(quoteid);
		
		if (quote != null) {
			request.setAttribute("pmodel", quote.getModelName());
			request.setAttribute("ppunit", "centimeters");
			request.setAttribute("ppcm", quote.getPicksPer());
			request.setAttribute("totalWarp", quote.getTotalWarp());
			request.setAttribute("finishing", quote.getFinishModule());
			request.setAttribute("quoteMemo", quote.getMemo());
			request.setAttribute("date", quote.getDate());
			request.setAttribute("weight", quote.getWeight());
			request.setAttribute("ftype", quote.getFabricType());
		}
		
		request.setAttribute("finishingsList", finishingsList);
		request.setAttribute("matList", matList);
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
		request.setAttribute("selectedMaterials", mats);
		request.setAttribute("quoteToEdit", quoteid);
		request.getRequestDispatcher("editqp.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}