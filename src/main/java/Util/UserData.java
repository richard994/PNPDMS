package Util;

import java.sql.*;

public class UserData {
	public UserData() {}
	
	public boolean CheckExist(String email, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String matQuery = "SELECT * FROM user";
			PreparedStatement matStmt = con.prepareStatement(matQuery);
			ResultSet rs = matStmt.executeQuery();
			while (rs.next()) {
				String e = rs.getString("email");
				String p = rs.getString("password");
				if (e.equals(email) && p.equals(password)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}