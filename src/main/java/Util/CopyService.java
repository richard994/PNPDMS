package Util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CopyService")
public class CopyService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public CopyService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		String[] quotes = request.getParameterValues("quoteToCopy");
		String quoteid = quotes[0];
		PriceParser pp = new PriceParser();
		PriceData pd = new PriceData();
		Quotes quote = pp.getQuote(quoteid);
		pd.insertQuote(quote.getDate(), quote.getModelName(), quote.getFabricType(), quote.getFinishModule(), quote.getMemo(), quote.getFloatPicksPer(), quote.getFloatTotalWarp(), quote.getFloatWeight(), quote.getFloatSalePrice());
		int newQuoteId = pd.getQuoteID(quote.getModelName());
		ArrayList<Mats> mats = pp.getListMaterials(quoteid);
		for (int i=0; i<mats.size(); i++) {
			Mats mat = mats.get(i);
			pd.insertMat(newQuoteId, mat.getMatName(), mat.getMatType(), mat.getMatColor(), mat.getUsePercent(), mat.getMatCost(), mat.getMatMemo());
		}
		
		request.getRequestDispatcher("PricingService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}