package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DevData {

	private static final String DEV_COLUMNS = "code, color, cost, IsParagonClean, Is400hrFCL, "
			+ "IsPieceDyed, NeedFeedback, IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, season, "
			+ "yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, "
			+ "rollsample_status, rollsample_datestamp, test_status, test_datestamp, moq, weight, "
			+ "numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime, LastModified, DateCurrentPhase, "
			+ "IsKnit, designer, direction, GeorgeCanceled, strike_off_birthday, NeedChinaFeedback, inactive, priceConfirmed, Is1000HrFCL,"
			+ "blanket_datestamp, numColor, numTotalColor";
	private static final String DEV_PLACEHOLDERS = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

	private int insertDevelopmentRow(String sql, String code, String color, double cost,
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback,
			boolean IsSDY, boolean IsChenille, String fabric_type, String design_type, String colorist, String finishing_used,
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, double moq, double weight,
			int numColorline, double ppcm, String note, String fabric_img_path,
			String pid_path, String test_report_path, String currentPhase, String DateTime, String LastModified, String DateCurrentPhase,
			boolean isKnit, String designer, String direction, boolean georgeCanceled, String strike_off_birthday,
			boolean NeedChinaFeedback, boolean inactive, boolean priceConfirmed, boolean Is1000HrFCL,
			String blanket_datestamp, int numColor, int numTotalColor) {
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
			stmt.setBoolean(46, Is1000HrFCL);
			stmt.setString(47, blanket_datestamp);
			stmt.setInt(48, numColor);
			stmt.setInt(49, numTotalColor);
			int affectedRows = stmt.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return generatedKeys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertDevelopment(String code, String color, double cost,
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback,
			boolean IsSDY, boolean IsChenille, String fabric_type, String design_type, String colorist, String finishing_used,
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String blanket_datestamp, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, double moq, double weight,
			int numColorline, int numColor, int numTotalColor, double ppcm, String note, String fabric_img_path,
			String pid_path, String test_report_path, String currentPhase, String DateTime, String LastModified, String DateCurrentPhase,
			boolean isKnit, String designer, String direction, boolean georgeCanceled, String strike_off_birthday,
			boolean NeedChinaFeedback, boolean inactive, boolean priceConfirmed, boolean Is1000HrFCL) {
		String sql = "INSERT INTO MijuPrice.development(" + DEV_COLUMNS + ") VALUES (" + DEV_PLACEHOLDERS + ");";
		return insertDevelopmentRow(sql, code, color, cost, IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback,
				IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, season, yarn_type, warp_type,
				content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, rollsample_status,
				rollsample_datestamp, test_status, test_datestamp, moq, weight, numColorline, ppcm, note,
				fabric_img_path, pid_path, test_report_path, currentPhase, DateTime, LastModified, DateCurrentPhase,
				isKnit, designer, direction, georgeCanceled, strike_off_birthday, NeedChinaFeedback, inactive,
				priceConfirmed, Is1000HrFCL, blanket_datestamp, numColor, numTotalColor);
	}

	public int duplicateDev(Developments dev) {
		String sql = "INSERT INTO MijuPrice.development(" + DEV_COLUMNS + ") VALUES (" + DEV_PLACEHOLDERS + ");";
		return insertDevelopmentRow(sql, dev.getCode(), dev.getColor(), dev.getCost(), dev.isParagonClean(),
				dev.isIs400hrFCL(), dev.isPieceDyed(), dev.isNeedFeedback(), dev.isSDY(), dev.isChenille(),
				dev.getFabric_type(), dev.getDesign_type(), dev.getColorist(), dev.getFinishing_used(), dev.getSeason(),
				dev.getYarn_type(), dev.getWarp_type(), dev.getContent(), dev.getStrike_off_status(),
				dev.getBlanket_status(), dev.getColorline_status(), dev.getColorline_datestamp(),
				dev.getRollsample_status(), dev.getRollsample_datestamp(), dev.getTest_status(), dev.getTest_datestamp(),
				dev.getMoq(), dev.getWeight(), dev.getNumColorline(), dev.getPpcm(), dev.getNote(),
				dev.getFabric_img_path(), dev.getPid_path(), dev.getTest_report_path(), dev.getCurrentPhase(),
				dev.getDateTime(), dev.getLastModified(), dev.getDateCurrentPhase(), dev.isKnit(), dev.getDesigner(),
				dev.getDirection(), dev.isGeorgeCanceled(), dev.getStrike_off_birthday(), dev.isNeedChinaFeedback(),
				dev.isInactive(), dev.isPriceConfirmed(), dev.isIs1000hrFCL(), dev.getBlanket_datestamp(),
				dev.getNumColor(), dev.getNumTotalColor());
	}

	public void insertComment(int development_id, String name, String date_stamp, String content) {
		if (content != null && content.length() > 1000) {
			content = content.substring(0, 1000);
		}
		String sql = "INSERT INTO MijuPrice.comment(development_id, name, date_stamp, content) VALUES (?,?,?,?);";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, development_id);
			stmt.setString(2, name);
			stmt.setString(3, date_stamp);
			stmt.setString(4, content);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertLog(int development_id, String name, String date_stamp, String content) {
		String sql = "INSERT INTO MijuPrice.log(development_id, name, date_stamp, content) VALUES (?,?,?,?);";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, development_id);
			stmt.setString(2, name);
			stmt.setString(3, date_stamp);
			stmt.setString(4, content);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Developments mapRow(ResultSet rs) throws SQLException {
		Developments development = new Developments();
		development.setAll(
				rs.getInt("development_id"),
				rs.getString("code"),
				rs.getString("color"),
				rs.getDouble("cost"),
				rs.getBoolean("IsParagonClean"),
				rs.getBoolean("Is400hrFCL"),
				rs.getBoolean("IsPieceDyed"),
				rs.getBoolean("NeedFeedback"),
				rs.getBoolean("IsSDY"),
				rs.getBoolean("IsChenille"),
				rs.getString("fabric_type"),
				rs.getString("design_type"),
				rs.getString("colorist"),
				rs.getString("finishing_used"),
				rs.getString("season"),
				rs.getString("yarn_type"),
				rs.getString("warp_type"),
				rs.getString("content"),
				rs.getString("strike_off_status"),
				rs.getString("blanket_status"),
				rs.getString("blanket_datestamp"),
				rs.getString("colorline_status"),
				rs.getString("colorline_datestamp"),
				rs.getString("rollsample_status"),
				rs.getString("rollsample_datestamp"),
				rs.getString("test_status"),
				rs.getString("test_datestamp"),
				rs.getDouble("moq"),
				rs.getDouble("weight"),
				rs.getInt("numColorline"),
				rs.getInt("numColor"),
				rs.getInt("numTotalColor"),
				rs.getDouble("ppcm"),
				rs.getString("note"),
				rs.getString("fabric_img_path"),
				rs.getString("pid_path"),
				rs.getString("test_report_path"),
				rs.getString("currentPhase"),
				rs.getString("DateTime"),
				rs.getString("LastModified"),
				rs.getString("DateCurrentPhase"),
				rs.getBoolean("IsKnit"),
				rs.getString("designer"),
				rs.getString("direction"),
				rs.getBoolean("GeorgeCanceled"),
				rs.getString("strike_off_birthday"),
				rs.getBoolean("NeedChinaFeedback"),
				rs.getBoolean("inactive"),
				rs.getBoolean("priceConfirmed"),
				rs.getBoolean("Is1000HrFCL"));
		return development;
	}

	public ArrayList<Developments> getDevelopments() {
		ArrayList<Developments> developments = new ArrayList<Developments>();
		String devQuery = "SELECT * FROM development";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement devStmt = conn.prepareStatement(devQuery);
				ResultSet rs = devStmt.executeQuery()) {
			while (rs.next()) {
				developments.add(mapRow(rs));
			}
			return developments;
		} catch (SQLException e) {
			e.printStackTrace();
			return developments;
		}
	}

	// SQL equivalent of FilterService.filter(...) - see that method for the criteria this must
	// stay in lockstep with. Uses BINARY comparisons/ORDER BY so string matching and sorting stay
	// case-sensitive to exactly match Java's String.equals()/compareTo() semantics regardless of
	// the table's actual collation (utf8mb4_unicode_ci is case-insensitive by default, which would
	// silently change matching behavior if we relied on the column's default collation instead).
	public ArrayList<Developments> getFilteredDevelopments(Developments filterdev, boolean showAll,
			Set<String> selectedStrikeProgress, Set<String> selectedBlanketProgress, Set<String> selectedTestProgress) {
		StringBuilder sql = new StringBuilder("SELECT * FROM development WHERE 1=1");
		ArrayList<Object> params = new ArrayList<>();

		String titleCode = filterdev.getCode();
		if (!isBlank(titleCode)) {
			sql.append(" AND (LOWER(code) LIKE ? OR LOWER(color) LIKE ?)");
			// escape LIKE wildcards so a literal '%' or '_' in a search term (plausible in a
			// product code) is matched literally, not as a wildcard - MySQL's LIKE defaults to
			// backslash as the escape character
			String pattern = "%" + escapeLikePattern(titleCode.toLowerCase()) + "%";
			params.add(pattern);
			params.add(pattern);
		}

		sql.append(" AND cost >= ? AND cost <= ?");
		params.add(filterdev.getPriceMin());
		params.add(filterdev.getPriceMax());

		appendBooleanIfTrue(sql, params, "IsParagonClean", filterdev.isParagonClean());
		appendBooleanIfTrue(sql, params, "Is400hrFCL", filterdev.isIs400hrFCL());
		appendBooleanIfTrue(sql, params, "Is1000HrFCL", filterdev.isIs1000hrFCL());
		appendBooleanIfTrue(sql, params, "IsPieceDyed", filterdev.isPieceDyed());
		appendBooleanIfTrue(sql, params, "NeedFeedback", filterdev.isNeedFeedback());
		appendBooleanIfTrue(sql, params, "NeedChinaFeedback", filterdev.isNeedChinaFeedback());
		appendBooleanIfTrue(sql, params, "IsSDY", filterdev.isSDY());
		appendBooleanIfTrue(sql, params, "IsChenille", filterdev.isChenille());
		appendBooleanIfTrue(sql, params, "IsKnit", filterdev.isKnit());
		appendBooleanIfTrue(sql, params, "GeorgeCanceled", filterdev.isGeorgeCanceled());

		if (!showAll) {
			sql.append(" AND inactive = ?");
			params.add(filterdev.isInactive());
		}

		appendBooleanIfTrue(sql, params, "priceConfirmed", filterdev.isPriceConfirmed());

		appendBinaryEquals(sql, params, "season", filterdev.getSeason());
		appendBinaryEquals(sql, params, "design_type", filterdev.getDesign_type());
		appendBinaryEquals(sql, params, "warp_type", filterdev.getWarp_type());
		appendBinaryEquals(sql, params, "yarn_type", filterdev.getYarn_type());
		appendBinaryEquals(sql, params, "colorist", filterdev.getColorist());
		appendBinaryEquals(sql, params, "fabric_type", filterdev.getFabric_type());
		appendBinaryEquals(sql, params, "designer", filterdev.getDesigner());
		appendBinaryEquals(sql, params, "direction", filterdev.getDirection());

		appendBinaryIn(sql, params, "strike_off_status", selectedStrikeProgress);
		appendBinaryIn(sql, params, "blanket_status", selectedBlanketProgress);
		appendBinaryIn(sql, params, "test_status", selectedTestProgress);

		String colorline = filterdev.getColorline_status();
		if (!isBlank(colorline) && !"DNE".equals(colorline)) {
			sql.append(" AND BINARY colorline_status = ?");
			params.add(colorline);
		}

		String rollsample = filterdev.getRollsample_status();
		if (!isBlank(rollsample) && !"DNE".equals(rollsample)) {
			sql.append(" AND BINARY rollsample_status = ?");
			params.add(rollsample);
		}

		sql.append(" ORDER BY BINARY code");

		ArrayList<Developments> results = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					results.add(mapRow(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	private void appendBooleanIfTrue(StringBuilder sql, List<Object> params, String column, boolean required) {
		if (required) {
			sql.append(" AND ").append(column).append(" = ?");
			params.add(true);
		}
	}

	private void appendBinaryEquals(StringBuilder sql, List<Object> params, String column, String value) {
		if (!isBlank(value)) {
			sql.append(" AND BINARY ").append(column).append(" = ?");
			params.add(value);
		}
	}

	private void appendBinaryIn(StringBuilder sql, List<Object> params, String column, Set<String> selected) {
		if (!selected.isEmpty() && !selected.contains("")) {
			sql.append(" AND BINARY ").append(column).append(" IN (");
			for (int i = 0; i < selected.size(); i++) {
				sql.append(i == 0 ? "?" : ",?");
			}
			sql.append(")");
			params.addAll(selected);
		}
	}

	private static String escapeLikePattern(String value) {
		return value.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
	}

	private boolean isBlank(String s) {
		return s == null || s.isEmpty();
	}

	public Developments getDevelopmentById(int id) {
		Developments development = new Developments();
		String devQuery = "SELECT * FROM development WHERE development_id = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement devStmt = conn.prepareStatement(devQuery)) {
			devStmt.setInt(1, id);
			try (ResultSet rs = devStmt.executeQuery()) {
				if (rs.next()) {
					development = mapRow(rs);
				}
			}
			return development;
		} catch (SQLException e) {
			e.printStackTrace();
			return development;
		}
	}

	public Developments getDevelopmentByCode(String code) {
		Developments development = new Developments();
		String devQuery = "SELECT * FROM development WHERE code = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement devStmt = conn.prepareStatement(devQuery)) {
			devStmt.setString(1, code);
			try (ResultSet rs = devStmt.executeQuery()) {
				if (rs.next()) {
					development = mapRow(rs);
				}
			}
			return development;
		} catch (SQLException e) {
			e.printStackTrace();
			return development;
		}
	}

	public ArrayList<Comment> getCommentsById(int id) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		String commentQuery = "SELECT * FROM comment WHERE development_id = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement commentStmt = conn.prepareStatement(commentQuery)) {
			commentStmt.setInt(1, id);
			try (ResultSet rs = commentStmt.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("name");
					String datestamp = rs.getString("date_stamp");
					String content = rs.getString("content");
					int commentid = rs.getInt("comment_id");
					comments.add(new Comment(name, datestamp, content, commentid));
				}
			}
		} catch (SQLException e) {
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
		ArrayList<Log> logs = new ArrayList<Log>();
		String logQuery = "SELECT * FROM log WHERE development_id = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement logStmt = conn.prepareStatement(logQuery)) {
			logStmt.setInt(1, id);
			try (ResultSet rs = logStmt.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("name");
					String datestamp = rs.getString("date_stamp");
					String content = rs.getString("content");
					logs.add(new Log(name, datestamp, content));
				}
			}
		} catch (SQLException e) {
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

	public void deleteDev(int id) {
		String logUpdate = "DELETE FROM log WHERE development_id = ?";
		String commentUpdate = "DELETE FROM comment WHERE development_id = ?";
		String developmentUpdate = "DELETE FROM development WHERE development_id = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement logStmt = conn.prepareStatement(logUpdate);
				PreparedStatement commentStmt = conn.prepareStatement(commentUpdate);
				PreparedStatement developmentStmt = conn.prepareStatement(developmentUpdate)) {
			logStmt.setInt(1, id);
			logStmt.executeUpdate();
			commentStmt.setInt(1, id);
			commentStmt.executeUpdate();
			developmentStmt.setInt(1, id);
			developmentStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteCommentById(int devid, int commentid) {
		String commentUpdate = "DELETE FROM comment WHERE development_id = ? AND comment_id = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement commentStmt = conn.prepareStatement(commentUpdate)) {
			commentStmt.setInt(1, devid);
			commentStmt.setInt(2, commentid);
			commentStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateDevField(String att_to_alter, Object new_value, String key, Object key_value) {
		String updateCommand = "UPDATE development SET " + att_to_alter + " = ? WHERE " + key + " = ?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement updateStmt = conn.prepareStatement(updateCommand)) {
			updateStmt.setObject(1, new_value);
			updateStmt.setObject(2, key_value);
			updateStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateDevTableString(String att_to_alter, String new_value, String key, String key_value) {
		updateDevField(att_to_alter, new_value, key, key_value);
	}

	public void updateDevTableString(String att_to_alter, String new_value, String key, int key_value) {
		updateDevField(att_to_alter, new_value, key, key_value);
	}

	public void updateDevTableDouble(String att_to_alter, double new_value, String key, String key_value) {
		updateDevField(att_to_alter, new_value, key, key_value);
	}

	public void updateDevTableBoolean(String att_to_alter, boolean new_value, String key, String key_value) {
		updateDevField(att_to_alter, new_value, key, key_value);
	}

}
