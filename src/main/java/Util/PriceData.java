package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class PriceData {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = Constant.DBUrl;
	private String user = Constant.DBUserName;
	private String password = Constant.DBPassword;
	Connection conn = null;
	
	public void getConn() {
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(  
					url, user, password);
		}
		catch(SQLException e) {
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int insertQuote(String date, String model, String fabricType, String finish, String memo, double ppcm, double totalWarp, double weight, double salePrice) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.quotes(create_date, model_name, fabric_type, finish_module, memo, ppcm, total_warp, weight, sale_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, date);
			stmt.setString(2, model);
			stmt.setString(3, fabricType);
			stmt.setString(4, finish);
			stmt.setString(5, memo);
			stmt.setDouble(6, ppcm);
			stmt.setDouble(7, totalWarp);
			stmt.setDouble(8, weight);
			stmt.setDouble(9, salePrice);
			int affectedRows = stmt.executeUpdate();
			ResultSet generatedKeys = null;
			// Check if the insert was successful and retrieve the generated keys
            if (affectedRows > 0) {
                // Get the generated keys (auto-incremented ID)
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Retrieve the generated ID (assuming it's the first column in the ResultSet)
                    int generatedID = generatedKeys.getInt(1);
                    return generatedID;
                }
            }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public void insertMat(int quoteId, String matName, String wwType, String colorType, double usePercent, String memo) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.materials(quote_id, mat_name, ww_type, color_type, use_percent, mat_memo) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quoteId);
			stmt.setString(2, matName);
			stmt.setString(3, wwType);
			stmt.setString(4, colorType);
			stmt.setDouble(5, usePercent);
			stmt.setString(6, memo);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Mats> getMatList() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Mats> mats = new ArrayList<Mats>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM mats";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			ResultSet rs = quoteStmt.executeQuery();
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
				Mats mat = new Mats(name, whitePrice, dyePrice, colorPrice, drPrice, prPrice, countgu, countzhi, mtrtype, wloss, key);
				mats.add(mat);
			}
			return mats;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
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
	
	public void deleteQuote(int id) {
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
			matStmt.setInt(1, id);
			matStmt.executeUpdate();
			quoteStmt.setInt(1, id);
			quoteStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Mats> getMaterialsById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Mats> mats = new ArrayList<Mats>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String matQuery = "SELECT * FROM materials WHERE quote_id = ?";
			PreparedStatement matStmt = con.prepareStatement(matQuery);
			matStmt.setInt(1, id);
			ResultSet rs = matStmt.executeQuery();
			while (rs.next()) {
				String matName = rs.getString("mat_name"); 
				String matType = rs.getString("ww_type");
				String matColor = rs.getString("color_type"); 
				String matMemo = rs.getString("mat_memo");
				double usePercent = rs.getDouble("use_percent");
				Mats mat = new Mats(matName, matType, matColor, matMemo, usePercent);
				mats.add(mat);
			}
			return mats;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Quotes getQuoteById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String quoteQuery = "SELECT * FROM quotes WHERE quote_id = ?";
			PreparedStatement quoteStmt = con.prepareStatement(quoteQuery);
			quoteStmt.setInt(1, id);
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
				Quotes quote = new Quotes(modelName, fabricType, createDate, finishModule, memo, salePrice, ppcm, totalWarp, weight, id);
				return quote;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}