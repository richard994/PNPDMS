package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	public int insertDevelopment(String code, String color, double cost, 
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback, 
			boolean IsSDY, boolean IsChenille, String fabric_type, String design_type, String colorist, String finishing_used, 
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, double moq, double weight, 
			int numColorline, double ppcm, String note, String fabric_img_path, 
			String pid_path, String test_report_path, String currentPhase, String DateTime, String LastModified, String DateCurrentPhase, 
			boolean isKnit, String designer, String direction, boolean georgeCanceled, String strike_off_birthday,
			boolean NeedChinaFeedback, boolean inactive, boolean priceConfirmed) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.development(code, color, cost, IsParagonClean, Is400hrFCL, "
					+ "IsPieceDyed, NeedFeedback, IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, season, "
					+ "yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, "
					+ "rollsample_status, rollsample_datestamp, test_status, test_datestamp, moq, weight, "
					+ "numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime, LastModified, DateCurrentPhase, "
					+ "IsKnit, designer, direction, GeorgeCanceled, strike_off_birthday, NeedChinaFeedback, inactive, priceConfirmed) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, code);
			stmt.setString(2, color);
			stmt.setDouble(3, cost);
			stmt.setBoolean(4, IsParagonClean);
			stmt.setBoolean(5, Is400hrFCL);
			stmt.setBoolean(6, IsPieceDyed);
			stmt.setBoolean(7, NeedFeedback);
			stmt.setBoolean(8, IsSDY);
			stmt.setBoolean(9, IsChenille);
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
			stmt.setDouble(26, moq);
			stmt.setDouble(27, weight);
			stmt.setInt(28, numColorline);
			stmt.setDouble(29, ppcm);
			stmt.setString(30, note);
			stmt.setString(31, fabric_img_path);
			stmt.setString(32, pid_path);
			stmt.setString(33, test_report_path);
			stmt.setString(34, currentPhase);
			stmt.setString(35, DateTime);
			stmt.setString(36, LastModified);
			stmt.setString(37, DateCurrentPhase);
			stmt.setBoolean(38, isKnit);
			stmt.setString(39, designer);
			stmt.setString(40, direction);
			stmt.setBoolean(41, georgeCanceled);
			stmt.setString(42, strike_off_birthday);
			stmt.setBoolean(43, NeedChinaFeedback);
			stmt.setBoolean(44, inactive);
			stmt.setBoolean(45, priceConfirmed);
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
				String code = rs.getString("code");
				String color = rs.getString("color"); 
				double cost = rs.getDouble("cost");
				boolean IsParagonClean = rs.getBoolean("IsParagonClean");
				boolean Is400hrFCL = rs.getBoolean("Is400hrFCL");
				boolean IsPieceDyed = rs.getBoolean("IsPieceDyed");
				boolean NeedFeedback = rs.getBoolean("NeedFeedback");
				boolean IsSDY = rs.getBoolean("IsSDY");
				boolean IsChenille = rs.getBoolean("IsChenille");
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
				double moq = rs.getDouble("moq");
				double weight = rs.getDouble("weight");
				int numColorline = rs.getInt("numColorline");
				double ppcm = rs.getDouble("ppcm");
				String note = rs.getString("note");
				String fabric_img_path = rs.getString("fabric_img_path");
				String pid_path = rs.getString("pid_path");
				String test_report_path = rs.getString("test_report_path");
				String currentPhase = rs.getString("currentPhase");
				String DateTime = rs.getString("DateTime");
				String LastModified = rs.getString("LastModified");
				String DateCurrentPhase = rs.getString("DateCurrentPhase");
				int dev_id = rs.getInt("development_id");
				boolean isKnit = rs.getBoolean("IsKnit");
				String designer = rs.getString("designer");
				String direction = rs.getString("direction");
				boolean georgeCanceled = rs.getBoolean("GeorgeCanceled");
				String strike_off_birthday = rs.getString("strike_off_birthday");
				boolean NeedChinaFeedback = rs.getBoolean("NeedChinaFeedback");
				boolean inactive = rs.getBoolean("inactive");
				boolean priceConfirmed = rs.getBoolean("priceConfirmed");
				Developments development = new Developments(dev_id, code, color, cost, 
										IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, 
										IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, 
										season, yarn_type, warp_type, content, strike_off_status,
										blanket_status, colorline_status, colorline_datestamp,
										rollsample_status, rollsample_datestamp, test_status,
										test_datestamp, moq, weight, 
										numColorline, ppcm, note, fabric_img_path, 
										pid_path, test_report_path, currentPhase, DateTime, LastModified, 
										DateCurrentPhase, isKnit, designer, direction, georgeCanceled, strike_off_birthday,
										NeedChinaFeedback, inactive, priceConfirmed);
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
				String code = rs.getString("code");
				String color = rs.getString("color"); 
				double cost = rs.getDouble("cost");
				boolean IsParagonClean = rs.getBoolean("IsParagonClean");
				boolean Is400hrFCL = rs.getBoolean("Is400hrFCL");
				boolean IsPieceDyed = rs.getBoolean("IsPieceDyed");
				boolean NeedFeedback = rs.getBoolean("NeedFeedback");
				boolean IsSDY = rs.getBoolean("IsSDY");
				boolean IsChenille = rs.getBoolean("IsChenille");
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
				double moq = rs.getDouble("moq");
				double weight = rs.getDouble("weight");
				int numColorline = rs.getInt("numColorline");
				double ppcm = rs.getDouble("ppcm");
				String note = rs.getString("note");
				String fabric_img_path = rs.getString("fabric_img_path");
				String pid_path = rs.getString("pid_path");
				String test_report_path = rs.getString("test_report_path");
				String currentPhase = rs.getString("currentPhase");
				String DateTime = rs.getString("DateTime");
				String LastModified = rs.getString("LastModified");
				String DateCurrentPhase = rs.getString("DateCurrentPhase");
				boolean isKnit = rs.getBoolean("IsKnit");
				String designer = rs.getString("designer");
				String direction = rs.getString("direction");
				boolean georgeCanceled = rs.getBoolean("GeorgeCanceled");
				String strike_off_birthday = rs.getString("strike_off_birthday");
				boolean NeedChinaFeedback = rs.getBoolean("NeedChinaFeedback");
				boolean inactive = rs.getBoolean("inactive");
				boolean priceConfirmed = rs.getBoolean("priceConfirmed");
				
				development.setAll(id, code, color, cost, 
									IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, 
									IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, 
									season, yarn_type, warp_type, content, strike_off_status,
									blanket_status, colorline_status, colorline_datestamp,
									rollsample_status, rollsample_datestamp, test_status,
									test_datestamp, moq, weight, 
									numColorline, ppcm, note, fabric_img_path, 
									pid_path, test_report_path, currentPhase, DateTime, LastModified, 
									DateCurrentPhase, isKnit, designer, direction, georgeCanceled, strike_off_birthday,
									NeedChinaFeedback, inactive, priceConfirmed);
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
				int commentid = rs.getInt("comment_id");
				Comment comment = new Comment(name, datestamp, content, commentid);
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return comments;
		}
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment c1, Comment c2) {
                LocalDate date1 = LocalDate.parse(c1.getDatestamp(), formatter);
                LocalDate date2 = LocalDate.parse(c2.getDatestamp(), formatter);
                return date1.compareTo(date2);
            }
        });
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
		// Sort the list by date_stamp (ascending order)
        Collections.sort(logs, new Comparator<Log>() {
            @Override
            public int compare(Log log1, Log log2) {
            	// Convert the date_stamp string to LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                LocalDateTime dateTime1 = LocalDateTime.parse(log1.getDatestamp(), formatter);
                LocalDateTime dateTime2 = LocalDateTime.parse(log2.getDatestamp(), formatter);

                // Compare the two LocalDateTime objects
                return dateTime1.compareTo(dateTime2);
            }
        });
        Collections.reverse(logs);
		return logs;
	}
	
	public int duplicateDev(Developments dev) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.development(code, color, cost, IsParagonClean, Is400hrFCL, "
					+ "IsPieceDyed, NeedFeedback, IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, season, "
					+ "yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, "
					+ "rollsample_status, rollsample_datestamp, test_status, test_datestamp, moq, weight, "
					+ "numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime, LastModified, DateCurrentPhase, "
					+ "IsKnit, designer, direction, GeorgeCanceled, strike_off_birthday, NeedChinaFeedback, inactive, priceConfirmed) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, dev.getCode());
			stmt.setString(2, dev.getColor());
			stmt.setDouble(3, dev.getCost());
			stmt.setBoolean(4, dev.isParagonClean());
			stmt.setBoolean(5, dev.isIs400hrFCL());
			stmt.setBoolean(6, dev.isPieceDyed());
			stmt.setBoolean(7, dev.isNeedFeedback());
			stmt.setBoolean(8, dev.isSDY());
			stmt.setBoolean(9, dev.isChenille());
			stmt.setString(10, dev.getFabric_type());
			stmt.setString(11, dev.getDesign_type());
			stmt.setString(12, dev.getColorist());
			stmt.setString(13, dev.getFinishing_used());
			stmt.setString(14, dev.getSeason());
			stmt.setString(15, dev.getYarn_type());
			stmt.setString(16, dev.getWarp_type());
			stmt.setString(17, dev.getContent());
			stmt.setString(18, dev.getStrike_off_status());
			stmt.setString(19, dev.getBlanket_status());
			stmt.setString(20, dev.getColorline_status());
			stmt.setString(21, dev.getColorline_datestamp());
			stmt.setString(22, dev.getRollsample_status());
			stmt.setString(23, dev.getRollsample_datestamp());
			stmt.setString(24, dev.getTest_status());
			stmt.setString(25, dev.getTest_datestamp());
			stmt.setDouble(26, dev.getMoq());
			stmt.setDouble(27, dev.getWeight());
			stmt.setInt(28, dev.getNumColorline());
			stmt.setDouble(29, dev.getPpcm());
			stmt.setString(30, dev.getNote());
			stmt.setString(31, dev.getFabric_img_path());
			stmt.setString(32, dev.getPid_path());
			stmt.setString(33, dev.getTest_report_path());
			stmt.setString(34, dev.getCurrentPhase());
			stmt.setString(35, dev.getDateTime());
			stmt.setString(36, dev.getLastModified());
			stmt.setString(37, dev.getDateCurrentPhase());
			stmt.setBoolean(38, dev.isKnit());
			stmt.setString(39, dev.getDesigner());
			stmt.setString(40, dev.getDirection());
			stmt.setBoolean(41, dev.isGeorgeCanceled());
			stmt.setString(42, dev.getStrike_off_birthday());
			stmt.setBoolean(43, dev.isNeedChinaFeedback());
			stmt.setBoolean(44, dev.isInactive());
			stmt.setBoolean(45, dev.isPriceConfirmed());
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
	
	public void deleteDev(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String logUpdate = "DELETE FROM log WHERE development_id = ?";
			String commentUpdate = "DELETE FROM comment WHERE development_id = ?";
			String developmentUpdate = "DELETE FROM development WHERE development_id = ?";
			PreparedStatement logStmt = con.prepareStatement(logUpdate);
			PreparedStatement commentStmt = con.prepareStatement(commentUpdate);
			PreparedStatement developmentStmt = con.prepareStatement(developmentUpdate);
			logStmt.setInt(1, id);
			logStmt.executeUpdate();
			commentStmt.setInt(1, id);
			commentStmt.executeUpdate();
			developmentStmt.setInt(1, id);
			developmentStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteCommentById(int devid, int commentid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String commentUpdate = "DELETE FROM comment WHERE development_id = ? AND comment_id = ?";
			PreparedStatement commentStmt = con.prepareStatement(commentUpdate);
			commentStmt.setInt(1, devid);
			commentStmt.setInt(2, commentid);
			commentStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDevTableString(String att_to_alter, String new_value, String key, String key_value) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String updateCommand = "UPDATE development SET " + att_to_alter + " = ? WHERE " + key + " = ?";
			PreparedStatement updateStmt = con.prepareStatement(updateCommand);
			updateStmt.setString(1, new_value);
			updateStmt.setString(2, key_value);
			updateStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDevTableString(String att_to_alter, String new_value, String key, int key_value) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String updateCommand = "UPDATE development SET " + att_to_alter + " = ? WHERE " + key + " = ?";
			PreparedStatement updateStmt = con.prepareStatement(updateCommand);
			updateStmt.setString(1, new_value);
			updateStmt.setInt(2, key_value);
			updateStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDevTableDouble(String att_to_alter, double new_value, String key, String key_value) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String updateCommand = "UPDATE development SET " + att_to_alter + " = ? WHERE " + key + " = ?";
			PreparedStatement updateStmt = con.prepareStatement(updateCommand);
			updateStmt.setDouble(1, new_value);
			updateStmt.setString(2, key_value);
			updateStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDevTableBoolean(String att_to_alter, boolean new_value, String key, String key_value) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String updateCommand = "UPDATE development SET " + att_to_alter + " = ? WHERE " + key + " = ?";
			PreparedStatement updateStmt = con.prepareStatement(updateCommand);
			updateStmt.setBoolean(1, new_value);
			updateStmt.setString(2, key_value);
			updateStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}