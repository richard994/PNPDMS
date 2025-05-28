package Util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcService")
public class CalcService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String[] sFinish;
	private static String pmodel;
	private static String memo;
	private static String ftype;
	private static String date;
	private static double totalWarp;
	private static double ppcm;
	private static double finalSalePrice;
	private static double weight;
	
	public CalcService() {}
	
	public void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		PriceData priceData = new PriceData();
		ArrayList<Mats> receivedMats = new ArrayList<Mats>();
		String temp[];
		String finStr = "";
		
		String action = request.getParameter("action");
		System.out.println("Action: " + action + "\n");
		if ("copy".equals(action)) {
			int quoteId = Integer.parseInt(request.getParameter("quoteId"));
			PriceData pd = new PriceData();
			Quotes quote = pd.getQuoteById(quoteId);
			ArrayList<Mats> mats = priceData.getMaterialsById(quoteId);
			int newId = pd.insertQuote(quote.getDate(), quote.getModelName(), quote.getFabricType(), quote.getFinishModule(), quote.getMemo(), quote.getFloatPicksPer(), quote.getFloatTotalWarp(), quote.getFloatWeight(), quote.getFloatSalePrice());
			for (int i=0; i<mats.size(); i++) {
				priceData.insertMat(newId, mats.get(i).getMatName(), mats.get(i).getMatType(), mats.get(i).getMatColor(), mats.get(i).getUsePercent(), mats.get(i).getMatMemo());
			}
			request.getRequestDispatcher("/PricingService").forward(request, response);
			return;
		} else if ("delete".equals(action)) {
			int quoteId = Integer.parseInt(request.getParameter("quoteId"));
			PriceData pd = new PriceData();
			pd.deleteQuote(quoteId);
			request.getRequestDispatcher("/PricingService").forward(request, response);
			return;
		} else if ("edit".equals(action)) {
			int quoteId = Integer.parseInt(request.getParameter("quoteId"));
			System.out.println("Deleting quoteID: " + quoteId + "\n");
			PriceData pd = new PriceData();
			pd.deleteQuote(quoteId);
		}
		
		temp = request.getParameterValues("pmodel");
		if (temp != null) {
			pmodel = temp[0];
			System.out.println("Successfully retrieved product model: " + pmodel + "\n");
		} else {
			System.out.println("Fail to retrieve product model.");
		}
		
		temp = request.getParameterValues("memo");
		if (temp != null) {
			memo = temp[0];
			System.out.println("Successfully retrieved product memo: " + memo + "\n");
		} else {
			System.out.println("Fail to retrieve product memo.");
		}
		
		temp = request.getParameterValues("fabrictype");
		if (temp != null) {
			ftype = temp[0];
			System.out.println("Successfully retrieved fabric type: " + ftype + "\n");
		} else {
			System.out.println("Fail to retrieve fabric type.");
		}
		
		temp = request.getParameterValues("date");
		if (temp != null) {
			date = temp[0];
			System.out.println("Successfully retrieved date: " + date + "\n");
		} else {
			System.out.println("Fail to retrieve date info.");
		}
		
		temp = request.getParameterValues("ppmodel");
		if (temp != null) {
			sFinish = temp[0].split(",");
			System.out.println("Successfully retrieved finishings data: ");
			for (int i=0; i<sFinish.length; i++) {
				if (i != sFinish.length-1) {
					finStr += sFinish[i] + ',';
				} else {
					finStr += sFinish[i];
				}
				System.out.println(sFinish[i] + ",");
			}
		} else {
			System.out.println("Fail to retrieve finishings data.");
		}
		
		temp = request.getParameterValues("ppcm");
		if (temp != null) {
			try {
				ppcm = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved Picks: " + ppcm + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve Picks.");
		}
		
		temp = request.getParameterValues("totalWarp");
		if (temp != null) {
			try {
				totalWarp = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved total warp: " + totalWarp + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve total warp.");
		}
		
		temp = request.getParameterValues("calculatedPriceInput");
		if (temp != null) {
			try {
				finalSalePrice = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved sale price: " + finalSalePrice + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve sale price info.");
		}
		
		temp = request.getParameterValues("weightInput");
		if (temp != null) {
			try {
				weight = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved weight: " + weight + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve weight info.");
		}
		
		for (int i = 0; i < 50; i++) {
			String wt = "wwtype" + i;
			String mt = "mattype" + i;
			String ct = "colortype" + i;
			String up = "usePercent" + i;
			String mm = "matMemo" + i;
			
			String wwtype="";
			String mattype="";
			String colortype="";
			double usePercent = 0.0;
			String matmemo="";
			
			temp = request.getParameterValues(wt);
			if (temp != null) {
				wwtype = temp[0];
				System.out.println("Successfully retrieved wwtype" + i + ": " + wwtype);
			} else {
				System.out.println("Fail to retrieve wwtype for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(mt);
			if (temp != null) {
				mattype= temp[0];
				System.out.println("Successfully retrieved material type" + i + ": " + mattype);
			} else {
				System.out.println("Fail to retrieve material type for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(ct);
			if (temp != null) {
				colortype = temp[0];
				System.out.println("Successfully retrieved color type" + i + ": " + colortype);
			} else {
				System.out.println("Fail to retrieve color type for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(up);
			if (temp != null) {
				try {
					usePercent = Double.parseDouble(temp[0]);
					System.out.println("Successfully retrieved use percent" + i + ": " + usePercent);
				}
				catch (Exception e) {
					System.out.println("Exception: " + e);
				}
			} else {
				System.out.println("Fail to retrieve use percentage for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(mm);
			if (temp != null) {
				matmemo = temp[0];
				System.out.println("Successfully retrieved material memo" + i + ": " + matmemo);
			} else {
				System.out.println("Fail to retrieve memo for material number " + i + ".");
				matmemo = "";
			}		    
			
		    Mats mat = new Mats(mattype, wwtype, colortype, matmemo, usePercent);
		    receivedMats.add(mat);
		}

		
		int quoteid = priceData.insertQuote(date, pmodel, ftype, finStr, memo, ppcm, totalWarp, weight, finalSalePrice);
		System.out.println("Successfully returned quoteID " + quoteid + "\n");
		for (int i=0; i<receivedMats.size(); i++) {
			priceData.insertMat(quoteid, receivedMats.get(i).getMatName(), receivedMats.get(i).getMatType(), receivedMats.get(i).getMatColor(), receivedMats.get(i).getUsePercent(), receivedMats.get(i).getMatMemo());
		}
		request.setAttribute("salePrice", Double.toString(finalSalePrice));
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doPost(request, response);
	}
}