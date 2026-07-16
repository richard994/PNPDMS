package Util;

import java.sql.*;
import javax.servlet.http.HttpSession;

public class UserData {
	public UserData() {}
	
	public boolean CheckExist(String email, String password, HttpSession session) {
		String matQuery = "SELECT * FROM user WHERE email = ? AND password = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement matStmt = conn.prepareStatement(matQuery)) {
			matStmt.setString(1, email);
            matStmt.setString(2, password);

			try (ResultSet rs = matStmt.executeQuery()) {
				if (rs.next()) {
					String userName = rs.getString("name");
					session.setAttribute("userName", userName);
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}