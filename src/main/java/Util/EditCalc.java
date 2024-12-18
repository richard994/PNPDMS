package Util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCalc")
public class EditCalc extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int width = 155;
	private static double warpWeight;
	private static double weftWeight;
	private static double loss;
	private static double warpCost;
	private static double weftCost;
	private static double totalMtrCost;
	private static double coefficient;
	private static double processCost;
	private static double finPrice;
	private static double finCost;
	private static double pdrate;
	private static double costPrice;
	private static double managementCost;
	private static double totalProductCost;
	private static double profitMargin;
	private static double finalSalePrice;
	
	// from user input
	private static String[] sFinish;
	private static String ppunit;
	private static String pmodel;
	private static String memo;
	private static String ftype;
	private static String date;
	private static double totalWarp;
	private static double ppcm;
	
	public EditCalc() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		MatData matData = new MatData();
		FinishData finData = new FinishData();
		ArrayList<Mats> materials = new ArrayList<Mats>();
		String temp[];
		String finStr = "";
		
		warpWeight=0;
		weftWeight=0;
		loss=0;
		warpCost=0;
		weftCost=0;
		totalMtrCost=0;
		coefficient=0;
		processCost=0;
		finPrice=0;
		finCost=0;
		pdrate=0;
		costPrice=0;
		managementCost=0;
		totalProductCost=0;
		profitMargin=0;
		finalSalePrice=0;
		
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
			if (ftype.equals("plain")) {
				profitMargin = 0.1;
			} else if (ftype.equals("jacquard")){
				profitMargin = 0.1;
			} else {
				System.out.println("Unknown fabric type.");
			}
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
				double f = 0;
				try {
					f = finData.getPrice(sFinish[i]);
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
				finPrice += f;
				double pdr = 0;
				try {
					pdr = finData.getPdrate(sFinish[i]);
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
				if (pdr > pdrate) {
					pdrate = pdr;
				}
				if (i != sFinish.length-1) {
					finStr += sFinish[i] + ',';
				} else {
					finStr += sFinish[i];
				}
				System.out.println(sFinish[i] + " price is: " + f + "; pdrate is: " + pdr);
			}
		} else {
			System.out.println("Fail to retrieve finishings data.");
		}
		
		temp = request.getParameterValues("ppunit");
		if (temp != null) {
			ppunit = temp[0];
			System.out.println("Successfully retrieved Unit for Picks: " + ppunit + "\n");
		} else {
			System.out.println("Fail to retrieve PPCM.");
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
		
		if (ppunit.equals("inches")) {
			ppcm /= 2.54;
			System.out.println("Using PPCM: " + ppcm + ".");
		} else {
			System.out.println("Using PPCM: " + ppcm + ".");
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
		
		for (int i=1; i<50; i++) {
			String buffer1="", buffer2="", buffer3="", buffer4="";
			double usep=0;
			String wt = "wwtype" + i;
			String mt = "mattype" + i;
			String ct = "colortype" + i;
			String up = "usePercent" + i;
			String mm = "matMemo" + i;
			
			temp = request.getParameterValues(wt);
			if (temp != null) {
				buffer1 = temp[0];
				System.out.println("Successfully retrieved wwtype" + i + ": " + buffer1);
			} else {
				System.out.println("Fail to retrieve wwtype for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(mt);
			if (temp != null) {
				buffer2= temp[0];
				System.out.println("Successfully retrieved material type" + i + ": " + buffer2);
			} else {
				System.out.println("Fail to retrieve material type for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(ct);
			if (temp != null) {
				buffer4 = temp[0];
				System.out.println("Successfully retrieved color type" + i + ": " + buffer4);
			} else {
				System.out.println("Fail to retrieve color type for material number " + i + ".");
				continue;
			}
			
			temp = request.getParameterValues(up);
			if (temp != null) {
				try {
					usep = Double.parseDouble(temp[0]);
					System.out.println("Successfully retrieved use percent" + i + ": " + usep);
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
				buffer3 = temp[0];
				System.out.println("Successfully retrieved material memo" + i + ": " + buffer3);
			} else {
				System.out.println("Fail to retrieve memo for material number " + i + ".");
				buffer3 = "";
			}
			
			double colorPrice = matData.getColorPrice(buffer2);
			double dyePrice = matData.getDyePrice(buffer2);
			double whitePrice = matData.getWhitePrice(buffer2);
			double drPrice = matData.getDrPrice(buffer2);
			double prPrice = matData.getPrPrice(buffer2);
			double matPrice = checkPrice(buffer4, prPrice, colorPrice, drPrice, whitePrice, dyePrice);
			System.out.println("Price for material number " + i + " is " + matPrice + ".");
			
			
			String mtrtype = matData.getmtrType(buffer2);
			double hdr = 0;
			double cg = matData.getCountGu(buffer2);
			double cz = matData.getCountZhi(buffer2);
			loss = matData.getLoss(buffer2);
			if (buffer1.equals("Weft")) {
				hdr = calculateWeight(mtrtype, buffer1, totalWarp, cg, cz, usep, ppcm, loss, width);
				weftWeight += hdr;
				double c = hdr * matPrice * 10 / 1000 * 1.1 * 1.1;
				weftCost += c;
				System.out.println("weft weight for material " + i + " is " + hdr);
				System.out.println("weft cost for material number " + i + " is " + c);
			} else if (buffer1.equals("Warp")) {
				hdr = calculateWeight(mtrtype, buffer1, totalWarp, cg, cz, usep, ppcm, loss, width);
				warpWeight += hdr;
				double c = hdr * matPrice * 10 / 1000 * 1.1;
				warpCost += c;
				System.out.println("warp weight for material " + i + " is " + hdr);
				System.out.println("warp cost for material number " + i + " is " + c);
			} else {
				System.out.println("UNKNOWN WW TYPE.");
			}
			Mats mat = new Mats(buffer2, buffer1, buffer4, buffer3, usep, matPrice);
			materials.add(mat);
		}
		totalMtrCost = weftCost + warpCost;
		System.out.println("Calculated total weft weight is " + weftWeight + " grams.");
		System.out.println("Calculated total warp weight is " + warpWeight + " grams.");
		System.out.println("Calculated total material cost is " + totalMtrCost + " yuan.");
		
		if (ppcm > 10) {
			coefficient = 0.2;
		} else {
			coefficient = 0.25;
		}
		processCost = ppcm * coefficient;
		System.out.println("Calculated process cost is " + processCost + " yuan.");
		
		finCost = (totalMtrCost + processCost) * pdrate + finPrice;
		System.out.println("Calculated finishing cost is " + finCost + " yuan.");
		
		costPrice = processCost + totalMtrCost + finCost + 1.4;
		System.out.println("Calculated cost price is " + costPrice + " yuan.");
		
		managementCost = costPrice/(0.87/1.1)*0.14;
		System.out.println("Calculated management cost is " + managementCost + " yuan.");
		
		totalProductCost = costPrice + managementCost;
		System.out.println("Calculated total product cost is " + totalProductCost + " yuan.");
		
		finalSalePrice = totalProductCost/(1-profitMargin)*profitMargin + totalProductCost;
		System.out.println("Calculated final sale price is " + finalSalePrice + " yuan.");
		
		PriceData priceData = new PriceData();
		priceData.insertQuote(date, pmodel, ftype, finStr, memo, ppcm, totalWarp, warpWeight + weftWeight, finalSalePrice);
		int quoteid = priceData.getQuoteID(pmodel);
		for (int i=0; i<materials.size(); i++) {
			priceData.insertMat(quoteid, materials.get(i).getMatName(), materials.get(i).getMatType(), materials.get(i).getMatColor(), materials.get(i).getUsePercent(), materials.get(i).getMatCost(), materials.get(i).getMatMemo());
		}
		
		String[] quotes = request.getParameterValues("quoteToEdit");
		String quoteToEdit = quotes[0];
		PriceParser pp = new PriceParser();
		pp.deleteQuote(quoteToEdit);
		
		request.getRequestDispatcher("PricingService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public double calculateWeight(String mt, String wt, double tw, double cg, double cz, double up, double ppcm, double wl, int w) {
		double temp = 0;
		
		if (wt.equals("Weft")) {
			if (mt.equals("D")) {
				temp = w * ((cz/cg)*((ppcm*up)/1000)/9000) * wl;
			} else if (mt.equals("N")) {
				temp = w * (9000/(cz/cg)*(ppcm*up)/1000)/9000 * wl;
			} else if (mt.equals("S")) {
				temp = w * (5315/(cz/cg)) * ((ppcm*up)/1000)/9000 * wl;
			} else {
				System.out.println("UNKNOWN MTR TYPE.");
			}
		} else if (wt.equals("Warp")) {
			if (mt.equals("D")) {
				temp = cz/cg * tw * up/1000/9000 * wl;
			} else if (mt.equals("N")) {
				temp = (9000/(cz/cg)) * tw * up/1000/9000 * wl;
			} else if (mt.equals("S")) {
				temp = (5315/(cz/cg)) * tw * up/1000/9000 * wl;
			} else {
				System.out.println("UNKNOWN MTR TYPE.");
			}
		} else {
			System.out.println("UNKNOWN WW TYPE.");
		}
		
		return temp;
	}
	
	public double checkPrice(String ct, double prp, double cp, double drp, double wp, double dp) {
		if (ct.equals("White Yarn")) {
			return wp;
		} else if (ct.equals("Yarn Dyed")) {
			return dp;
		} else if (ct.equals("Fiber Dyed Yarn")) {
			return cp;
		} else if (ct.equals("Space Dyed Yarn")) {
			return drp;
		} else if (ct.equals("Jet Dyed Yarn")) {
			return prp;
		} else {
			return 0.0;
		}
	};
}