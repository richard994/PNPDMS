package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinishData {
	private static HashMap<String, Double> prices;
	private static HashMap<String, Double> pdrates;
	private static List<String> finishings;
	
	public FinishData() {
		getData();
	}
	
	public static void getData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM finishing";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			ResultSet rs = quoteStmt.executeQuery();
			finishings = new ArrayList<String>();
			prices = new HashMap<String, Double>();
			pdrates = new HashMap<String, Double>();
			while (rs.next()) {
				double pdrate = rs.getDouble("pdrate");
				String name = rs.getString("postdisposaletypeename");
				double price = rs.getDouble("price");
				finishings.add(name);
				prices.put(name, price);
				pdrates.put(name, pdrate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getFinishingString() {
		String buffer = "";
		for (int i=0; i<finishings.size()-1; i++) {
			buffer += finishings.get(i);
			buffer += ",";
		}
		buffer += finishings.get(finishings.size()-1);
		return buffer;
	}
	
	public List<String> getFinishingList() {
		return finishings;
	}
	
	public Double getPrice(String name) {
		return prices.get(name);
	}
	
	public Double getPdrate(String name) {
		return pdrates.get(name);
	}
}