package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatData {
	private static HashMap<String, Double> whitePrices;
	private static HashMap<String, Double> dyePrices;
	private static HashMap<String, Double> colorPrices;
	private static HashMap<String, Double> drPrices;
	private static HashMap<String, Double> prPrices;
	private static HashMap<String, Double> countGu;
	private static HashMap<String, Double> countZhi;
	private static HashMap<String, Double> loss;
	private static HashMap<String, String> mtype;
	private static HashMap<String, String> mCategory;
	private static List<String> names;
	private static int numChenilleYarn;
	private static int numCottonYarn;
	private static int numRayon;
	private static int numFancyYarn;
	private static int numFilamentYarn;
	private static int numPolyesterYarn;
	private static int numWoolYarn;
	private static int numCashmere;
	private static int numLinenYarn;
	private static int numArtificialCotton;
	private static int numFullCotton;
	private static int numTwistedYarn;
	private static int numPolyCotton;
	private static int numSlubYarn;
	private static int numSilk;
	private static int numAcrylicYarn;
	private static int numFireRetardent;
	private static int numFilamentPolyester;
	private static int numNylon;
	private static int numPolyLinen;
	private static int numRecycledYarn;
	
	public MatData() {
		getData();
	}
	
	public static void getData() {
		numChenilleYarn=0;
		numCottonYarn=0;
		numRayon=0;
		numFancyYarn=0;
		numFilamentYarn=0;
		numPolyesterYarn=0;
		numWoolYarn=0;
		numCashmere=0;
		numLinenYarn=0;
		numArtificialCotton=0;
		numFullCotton=0;
		numTwistedYarn=0;
		numPolyCotton=0;
		numSlubYarn=0;
		numSilk=0;
		numAcrylicYarn=0;
		numFireRetardent=0;
		numFilamentPolyester=0;
		numNylon=0;
		numPolyLinen=0;
		numRecycledYarn=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM mats";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			ResultSet rs = quoteStmt.executeQuery();
			names = new ArrayList<String>();
			whitePrices = new HashMap<String, Double>();
			dyePrices = new HashMap<String, Double>();
			colorPrices = new HashMap<String, Double>();
			drPrices = new HashMap<String, Double>();
			prPrices = new HashMap<String, Double>();
			countGu = new HashMap<String, Double>();
			countZhi = new HashMap<String, Double>();
			loss = new HashMap<String, Double>();
			mtype = new HashMap<String, String>();
			mCategory = new HashMap<String, String>();
			MatMap matMap = new MatMap();
			while (rs.next()) {
				String name = rs.getString("mtrename");
				double whitePrice = rs.getDouble("whiteprice");
				double dyePrice = rs.getDouble("dyeprice");
				double colorPrice = rs.getDouble("colorprice");
				double drPrice = rs.getDouble("drprice");
				double prPrice = rs.getDouble("prprice");
				double countgu = rs.getDouble("countgu");
				double countzhi = rs.getDouble("countzhi");
				String mtrtype = rs.getString("mtrtype");
				double wloss = rs.getDouble("warploss");
				String key = rs.getString("mtrid").substring(0,1);
				names.add(name);
				whitePrices.put(name, whitePrice);
				dyePrices.put(name, dyePrice);
				colorPrices.put(name, colorPrice);
				drPrices.put(name, drPrice);
				prPrices.put(name, prPrice);
				countGu.put(name, countgu);
				countZhi.put(name, countzhi);
				mtype.put(name, mtrtype);
				loss.put(name, wloss);
				String category = matMap.getType(key);
				mCategory.put(name, category);
				if (category.equals("Chenille Yarn")) {
					numChenilleYarn++;
				} else if (category.equals("Cotton Yarn")) {
					numCottonYarn++;
				} else if (category.equals("Rayon")) {
					numRayon++;
				} else if (category.equals("Fancy Yarn")) {
					numFancyYarn++;
				} else if (category.equals("Filament Yarn")) {
					numFilamentYarn++;
				} else if (category.equals("Polyester Yarn")) {
					numPolyesterYarn++;
				} else if (category.equals("Wool Yarn")) {
					numWoolYarn++;
				} else if (category.equals("Cashmere")) {
					numCashmere++;
				} else if (category.equals("Linen Yarn")) {
					numLinenYarn++;
				} else if (category.equals("Artificial Cotton")) {
					numArtificialCotton++;
				} else if (category.equals("100% Cotton")) {
					numFullCotton++;
				} else if (category.equals("Twisted Yarn")) {
					numTwistedYarn++;
				} else if (category.equals("Poly Cotton")) {
					numPolyCotton++;
				} else if (category.equals("Slub Yarn")) {
					numSlubYarn++;
				} else if (category.equals("Silk")) {
					numSilk++;
				} else if (category.equals("Acrylic Yarn")) {
					numAcrylicYarn++;
				} else if (category.equals("Fire Retardent")) {
					numFireRetardent++;
				} else if (category.equals("Filament Polyester")) {
					numFilamentPolyester++;
				} else if (category.equals("Nylon")) {
					numNylon++;
				} else if (category.equals("Poly Linen")) {
					numPolyLinen++;
				} else if (category.equals("Recycled Yarn")) {
					numRecycledYarn++;
				} else {} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<String> getMatList() {
		return names;
	}
	
	public String getMatStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i);
			buffer += ",";
		}
		buffer += names.get(names.size()-1);
		return buffer;
	}
	
	public String getMatColorPriceStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + colorPrices.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + colorPrices.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatDrPriceStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + drPrices.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + drPrices.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatPrPriceStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + prPrices.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + prPrices.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatWhitePriceStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + whitePrices.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + whitePrices.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatDyePriceStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + dyePrices.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + dyePrices.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatCgStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + countGu.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + countGu.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatCzStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + countZhi.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + countZhi.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatLossStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + loss.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + loss.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatMtrtypeStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + mtype.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + mtype.get(names.get(names.size()-1));
		return buffer;
	}
	
	public String getMatCategoryStr() {
		String buffer = "";
		for (int i=0; i<names.size()-1; i++) {
			buffer += names.get(i) + ":" + mCategory.get(names.get(i));
			buffer += ",";
		}
		buffer += names.get(names.size()-1) + mCategory.get(names.get(names.size()-1));
		return buffer;
	}
	
	public Double getWhitePrice(String name) {
		return whitePrices.get(name);
	}
	
	public Double getDyePrice(String name) {
		return dyePrices.get(name);
	}
	
	public Double getColorPrice(String name) {
		return colorPrices.get(name);
	}
	
	public Double getDrPrice(String name) {
		return drPrices.get(name);
	}
	
	public Double getPrPrice(String name) {
		return prPrices.get(name);
	}
	
	public Double getCountGu(String name) {
		return countGu.get(name);
	}
	
	public Double getCountZhi(String name) {
		return countZhi.get(name);
	}
	
	public Double getLoss(String name) {
		return loss.get(name);
	}
	
	public String getmtrType(String name) {
		return mtype.get(name);
	}
	
	public int getNumChenillerYarn() {
		return numChenilleYarn;
	}
	
	public int getNumCottonYarn() {
		return numCottonYarn;
	}
	
	public int getNumRayon() {
		return numRayon;
	}	
	
	public int getNumFancyYarn() {
		return numFancyYarn;
	}
	
	public int getNumFilamentYarn() {
		return numFilamentYarn;
	}
	
	public int getNumPolyesterYarn() {
		return numPolyesterYarn;
	}
	
	public int getNumWoolYarn() {
		return numWoolYarn;
	}
	
	public int getNumCashmere() {
		return numCashmere;
	}
	
	public int getNumLinenYarn() {
		return numLinenYarn;
	}
	
	public int getNumArtificialCotton() {
		return numArtificialCotton;
	}
	
	public int getNumFullCotton() {
		return numFullCotton;
	}
	
	public int getNumTwistedYarn() {
		return numTwistedYarn;
	}
	
	public int getNumPolyCotton() {
		return numPolyCotton;
	}
	
	public int getNumSlubYarn() {
		return numSlubYarn;
	}
	
	public int getNumSilk() {
		return numSilk;
	}
	
	public int getNumAcrylicYarn() {
		return numAcrylicYarn;
	}
	
	public int getNumFireRetardent() {
		return numFireRetardent;
	}
	
	public int getNumFilamentPolyester() {
		return numFilamentPolyester;
	}
	
	public int getNumNylon() {
		return numNylon;
	}
	
	public int getNumPolyLinen() {
		return numPolyLinen;
	}
	
	public int getNumRecycledYarn() {
		return numRecycledYarn;
	}
}