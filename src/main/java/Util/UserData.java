package Util;

import java.sql.*;
import javax.servlet.http.HttpSession;

public class UserData {
	public UserData() {}
	
	public boolean CheckExist(String email, String password, HttpSession session) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection con= DriverManager.getConnection(  
				Constant.DBUrl, Constant.DBUserName,Constant.DBPassword);) {
			String matQuery = "SELECT * FROM user WHERE email = ? AND password = ?";
			PreparedStatement matStmt = con.prepareStatement(matQuery);
			
			matStmt.setString(1, email);
            matStmt.setString(2, password);
			
			ResultSet rs = matStmt.executeQuery();
			if (rs.next()) {
				String userName = rs.getString("name");
				session.setAttribute("userName", userName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}