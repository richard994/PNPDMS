package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DevData {
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
	
	public void insertDevelopment(String title, String code, String color, double cost, 
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback, 
			boolean IsSDY, String fabric_type, String design_type, String colorist, String finishing_used, 
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, String customs, double moq, double weight, 
			String nickname, int numColorline, double ppcm, String note, String fabric_img_path, 
			String pid_path, String test_report_path, String currentPhase, String DateTime) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.development(title, code, color, cost, IsParagonClean, Is400hrFCL, "
					+ "IsPieceDyed, NeedFeedback, IsSDY, fabric_type, design_type, colorist, finishing_used, season, "
					+ "yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, "
					+ "rollsample_status, rollsample_datestamp, test_status, test_datestamp, customs, moq, weight, "
					+ "nickname, numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, code);
			stmt.setString(3, color);
			stmt.setDouble(4, cost);
			stmt.setBoolean(5, IsParagonClean);
			stmt.setBoolean(6, Is400hrFCL);
			stmt.setBoolean(7, IsPieceDyed);
			stmt.setBoolean(8, NeedFeedback);
			stmt.setBoolean(9, IsSDY);
			stmt.setString(10, fabric_type);
			stmt.setString(11, design_type);
			stmt.setString(12, colorist);
			stmt.setString(13, finishing_used);
			stmt.setString(14, season);
			stmt.setString(15, yarn_type);
			stmt.setString(16, warp_type);
			stmt.setString(17, content);
			stmt.setString(18, strike_off_status);
			stmt.setString(19, blanket_status);
			stmt.setString(20, colorline_status);
			stmt.setString(21, colorline_datestamp);
			stmt.setString(22, rollsample_status);
			stmt.setString(23, rollsample_datestamp);
			stmt.setString(24, test_status);
			stmt.setString(25, test_datestamp);
			stmt.setString(26, customs);
			stmt.setDouble(27, moq);
			stmt.setDouble(28, weight);
			stmt.setString(29, nickname);
			stmt.setInt(30, numColorline);
			stmt.setDouble(31, ppcm);
			stmt.setString(32, note);
			stmt.setString(33, fabric_img_path);
			stmt.setString(34, pid_path);
			stmt.setString(35, test_report_path);
			stmt.setString(36, currentPhase);
			stmt.setString(37, DateTime);
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
	
	public void insertComment(int development_id, String name, String date_stamp, String content) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.comment(development_id, name, date_stamp, content) VALUES (?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, development_id);
			stmt.setString(2, name);
			stmt.setString(3, date_stamp);
			stmt.setString(4, content);
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
	
	public void insertLog(int development_id, String name, String date_stamp, String content) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.log(development_id, name, date_stamp, content) VALUES (?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, development_id);
			stmt.setString(2, name);
			stmt.setString(3, date_stamp);
			stmt.setString(4, content);
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
	
	public int getDevID(String title) {
		int devID = 1;
		try {
			getConn();
			String sql = "SELECT * FROM MijuPrice.development WHERE title = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				devID = rs.getInt("development_id");
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
		return devID;
	}
	
	public ArrayList<Developments> getDevelopments() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Developments> developments = new ArrayList<Developments>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String devQuery = "SELECT * FROM development";
			PreparedStatement devStmt = con.prepareStatement(devQuery);
			ResultSet rs = devStmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String code = rs.getString("code");
				String color = rs.getString("color"); 
				double cost = rs.getDouble("cost");
				boolean IsParagonClean = rs.getBoolean("IsParagonClean");
				boolean Is400hrFCL = rs.getBoolean("Is400hrFCL");
				boolean IsPieceDyed = rs.getBoolean("IsPieceDyed");
				boolean NeedFeedback = rs.getBoolean("NeedFeedback");
				boolean IsSDY = rs.getBoolean("IsSDY");
				String fabric_type = rs.getString("fabric_type");
				String design_type = rs.getString("design_type");
				String colorist = rs.getString("colorist");
				String finishing_used = rs.getString("finishing_used");
				String season = rs.getString("season");
				String yarn_type = rs.getString("yarn_type");
				String warp_type = rs.getString("warp_type");
				String content = rs.getString("content");
				String strike_off_status = rs.getString("strike_off_status");
				String blanket_status = rs.getString("blanket_status");
				String colorline_status = rs.getString("colorline_status");
				String colorline_datestamp = rs.getString("colorline_datestamp");
				String rollsample_status = rs.getString("rollsample_status");
				String rollsample_datestamp = rs.getString("rollsample_datestamp");
				String test_status = rs.getString("test_status");
				String test_datestamp = rs.getString("test_datestamp");
				String customs = rs.getString("customs");
				double moq = rs.getDouble("moq");
				double weight = rs.getDouble("weight");
				String nickname = rs.getString("nickname");
				int numColorline = rs.getInt("numColorline");
				double ppcm = rs.getDouble("ppcm");
				String note = rs.getString("note");
				String fabric_img_path = rs.getString("fabric_img_path");
				String pid_path = rs.getString("pid_path");
				String test_report_path = rs.getString("test_report_path");
				String currentPhase = rs.getString("currentPhase");
				String DateTime = rs.getString("DateTime");
				int dev_id = rs.getInt("development_id");
				Developments development = new Developments(dev_id, title, code, color, cost, 
										IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, 
										IsSDY, fabric_type, design_type, colorist, finishing_used, 
										season, yarn_type, warp_type, content, strike_off_status,
										blanket_status, colorline_status, colorline_datestamp,
										rollsample_status, rollsample_datestamp, test_status,
										test_datestamp, customs, moq, weight, 
										nickname, numColorline, ppcm, note, fabric_img_path, 
										pid_path, test_report_path, currentPhase, DateTime);
				developments.add(development);
			}
			return developments;
		} catch (Exception e) {
			e.printStackTrace();
			return developments;
		}
	}
	
	public Developments getDevelopmentById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Developments development = new Developments();
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String devQuery = "SELECT * FROM development WHERE development_id = ?";
			PreparedStatement devStmt = con.prepareStatement(devQuery);
			devStmt.setInt(1, id);
			ResultSet rs = devStmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				String code = rs.getString("code");
				String color = rs.getString("color"); 
				double cost = rs.getDouble("cost");
				boolean IsParagonClean = rs.getBoolean("IsParagonClean");
				boolean Is400hrFCL = rs.getBoolean("Is400hrFCL");
				boolean IsPieceDyed = rs.getBoolean("IsPieceDyed");
				boolean NeedFeedback = rs.getBoolean("NeedFeedback");
				boolean IsSDY = rs.getBoolean("IsSDY");
				String fabric_type = rs.getString("fabric_type");
				String design_type = rs.getString("design_type");
				String colorist = rs.getString("colorist");
				String finishing_used = rs.getString("finishing_used");
				String season = rs.getString("season");
				String yarn_type = rs.getString("yarn_type");
				String warp_type = rs.getString("warp_type");
				String content = rs.getString("content");
				String strike_off_status = rs.getString("strike_off_status");
				String blanket_status = rs.getString("blanket_status");
				String colorline_status = rs.getString("colorline_status");
				String colorline_datestamp = rs.getString("colorline_datestamp");
				String rollsample_status = rs.getString("rollsample_status");
				String rollsample_datestamp = rs.getString("rollsample_datestamp");
				String test_status = rs.getString("test_status");
				String test_datestamp = rs.getString("test_datestamp");
				String customs = rs.getString("customs");
				double moq = rs.getDouble("moq");
				double weight = rs.getDouble("weight");
				String nickname = rs.getString("nickname");
				int numColorline = rs.getInt("numColorline");
				double ppcm = rs.getDouble("ppcm");
				String note = rs.getString("note");
				String fabric_img_path = rs.getString("fabric_img_path");
				String pid_path = rs.getString("pid_path");
				String test_report_path = rs.getString("test_report_path");
				String currentPhase = rs.getString("currentPhase");
				String DateTime = rs.getString("DateTime");
				
				development.setAll(id, title, code, color, cost, 
									IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, 
									IsSDY, fabric_type, design_type, colorist, finishing_used, 
									season, yarn_type, warp_type, content, strike_off_status,
									blanket_status, colorline_status, colorline_datestamp,
									rollsample_status, rollsample_datestamp, test_status,
									test_datestamp, customs, moq, weight, 
									nickname, numColorline, ppcm, note, fabric_img_path, 
									pid_path, test_report_path, currentPhase, DateTime);
			}
			return development;
		} catch (Exception e) {
			e.printStackTrace();
			return development;
		}
	}
	
	public ArrayList<Comment> getCommentsById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String commentQuery = "SELECT * FROM comment WHERE development_id = ?";
			PreparedStatement commentStmt = con.prepareStatement(commentQuery);
			commentStmt.setInt(1, id);
			ResultSet rs = commentStmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String datestamp = rs.getString("date_stamp");
				String content = rs.getString("content");
				Comment comment = new Comment(name, datestamp, content);
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return comments;
		}
		Collections.reverse(comments);
		return comments;
	}
	
	public ArrayList<Log> getLogsById(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Log> logs = new ArrayList<Log>();
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String logQuery = "SELECT * FROM log WHERE development_id = ?";
			PreparedStatement logStmt = con.prepareStatement(logQuery);
			logStmt.setInt(1, id);
			ResultSet rs = logStmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String datestamp = rs.getString("date_stamp");
				String content = rs.getString("content");
				Log log = new Log(name, datestamp, content);
				logs.add(log);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return logs;
		}
		Collections.reverse(logs);
		return logs;
	}
}