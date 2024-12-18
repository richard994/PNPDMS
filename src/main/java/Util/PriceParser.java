package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class PriceParser {
	public PriceParser() {}
	
	public ArrayList<Quotes> getQuotes() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Quotes> quotes = new ArrayList<Quotes>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM quotes";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			ResultSet rs = quoteStmt.executeQuery();
			while (rs.next()) {
				String modelName = rs.getString("model_name");
				String fabricType = rs.getString("fabric_type");
				String createDate = rs.getString("create_date"); 
				String finishModule = rs.getString("finish_module");
				String memo = rs.getString("memo");
				double salePrice = rs.getDouble("sale_price");
				double ppcm = rs.getDouble("ppcm");
				double totalWarp = rs.getDouble("total_warp");
				double weight = rs.getDouble("weight");
				weight *= 8.8;
				int id = rs.getInt("quote_id");
				Quotes quote = new Quotes(modelName, fabricType, createDate, finishModule, memo, salePrice, ppcm, totalWarp, weight, id);
				quotes.add(quote);
			}
			Collections.reverse(quotes);
			return quotes;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String modelName = "";
			String fabricType = "";
			String createDate = ""; 
			String finishModule = "";
			String memo = "";
			double salePrice = 0;
			double ppcm = 0;
			double totalWarp = 0;
			double weight = 0;
			int id = 0;
			Quotes quote = new Quotes(modelName, fabricType, createDate, finishModule, memo, salePrice, ppcm, totalWarp, weight, id);
			quotes.add(quote);
			return quotes;
		}
	}
	
	public void deleteQuote(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteUpdate = "DELETE FROM quotes WHERE quote_id = ?";
			String matUpdate = "DELETE FROM materials WHERE quote_id = ?";
			PreparedStatement quoteStmt = con.prepareStatement(quoteUpdate);
			PreparedStatement matStmt = con.prepareStatement(matUpdate);
			matStmt.setString(1, id);
			matStmt.executeUpdate();
			quoteStmt.setString(1, id);
			quoteStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getMaterials(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String mats = "";
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String matQuery = "SELECT * FROM materials";
			PreparedStatement matStmt = con.prepareStatement(matQuery);
			ResultSet rs = matStmt.executeQuery();
			while (rs.next()) {
				String quoteid = rs.getString("quote_id");
				if (quoteid.equals(id)) {
					String matName = rs.getString("mat_name"); 
					String matType = rs.getString("ww_type"); 
					String matColor = rs.getString("color_type"); 
					String matMemo = rs.getString("mat_memo");
					double usePercent = rs.getDouble("use_percent");
					double cost = rs.getDouble("mat_cost");
					Mats mat = new Mats(matName, matType, matColor, matMemo, usePercent, cost);
					mats += mat.getMatStr() + ':';
				}
			}
			return mats;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Mats> getListMaterials(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Mats> mats = new ArrayList<Mats>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String matQuery = "SELECT * FROM materials";
			PreparedStatement matStmt = con.prepareStatement(matQuery);
			ResultSet rs = matStmt.executeQuery();
			while (rs.next()) {
				String quoteid = rs.getString("quote_id");
				if (quoteid.equals(id)) {
					String matName = rs.getString("mat_name"); 
					String matType = rs.getString("ww_type");
					String matColor = rs.getString("color_type"); 
					String matMemo = rs.getString("mat_memo");
					double usePercent = rs.getDouble("use_percent");
					double cost = rs.getDouble("mat_cost");
					Mats mat = new Mats(matName, matType, matColor, matMemo, usePercent, cost);
					mats.add(mat);
				}
			}
			return mats;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Quotes getQuote(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM quotes";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			ResultSet rs = quoteStmt.executeQuery();
			while (rs.next()) {
				String quoteid = rs.getString("quote_id");
				if (quoteid.equals(id)) {
					String modelName = rs.getString("model_name"); 
					String fabricType = rs.getString("fabric_type");
					String createDate = rs.getString("create_date"); 
					String finishModule = rs.getString("finish_module");
					String memo = rs.getString("memo");
					double salePrice = rs.getDouble("sale_price");
					double ppcm = rs.getDouble("ppcm");
					double totalWarp = rs.getDouble("total_warp");
					double weight = rs.getDouble("weight");
					weight *= 8.8;
					Quotes quote = new Quotes(modelName, fabricType, createDate, finishModule, memo, salePrice, ppcm, totalWarp, weight, Integer.parseInt(id));
					return quote;
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}