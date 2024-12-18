package Util;

import java.sql.*;

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
	
	public void insertQuote(String date, String model, String fabricType, String finish, String memo, double ppcm, double totalWarp, double weight, double salePrice) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.quotes(create_date, model_name, fabric_type, finish_module, memo, ppcm, total_warp, weight, sale_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, date);
			stmt.setString(2, model);
			stmt.setString(3, fabricType);
			stmt.setString(4, finish);
			stmt.setString(5, memo);
			stmt.setDouble(6, ppcm);
			stmt.setDouble(7, totalWarp);
			stmt.setDouble(8, weight);
			stmt.setDouble(9, salePrice);
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
	
	public void insertMat(int quoteId, String matName, String wwType, String colorType, double usePercent, double matCost, String memo) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.materials(quote_id, mat_name, ww_type, color_type, use_percent, mat_cost, mat_memo) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quoteId);
			stmt.setString(2, matName);
			stmt.setString(3, wwType);
			stmt.setString(4, colorType);
			stmt.setDouble(5, usePercent);
			stmt.setDouble(6, matCost);
			stmt.setString(7, memo);
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
	
	public int getQuoteID(String modelname) {
		int quoteID = 1;
		try {
			getConn();
			String sql = "SELECT * FROM MijuPrice.quotes WHERE model_name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, modelname);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				quoteID = rs.getInt("quote_id");
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
		return quoteID;
	}
}