package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.net.HttpURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser implements Runnable {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = Constant.DBUrl;
	private static String user = Constant.DBUserName;
	private static String password = Constant.DBPassword;
	static Connection conn = null;
	
	public DataParser() {}
	
	public static void ParseJSONmat(String[] args) throws IOException, JSONException {
		try {
			URL u = new URL("http://218.75.78.226:9000/zhongya_eos/ajax/ZY/dataMaterial24");
			HttpURLConnection hr = (HttpURLConnection) u.openConnection();
			if (hr.getResponseCode()==200) {
				InputStream is = hr.getInputStream();
				StringBuffer sb = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					line = br.readLine();
				}
				JSONArray arr = new JSONArray(sb.toString());
				if (arr.length() > 0) {
					truncateMats();
				} else {
					return;
				}
				String mtrename;
				String mtrtype;
				double countgu;
				double countzhi;
				double colorprice;
				double drprice;
				double prprice;
				double whiteprice;
				double dyeprice;
				double whiteloss;
				double warploss;
				double colorloss;
				String mtrid;
				int material_id;
				for (int i=0; i<arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					mtrename = obj.getString("mtrename");
					mtrtype = obj.getString("mtrtype");
					countgu = obj.getDouble("countgu");
					countzhi = obj.getDouble("countzhi");
					prprice = obj.getDouble("prprice");
					colorprice = obj.getDouble("colorprice");
					drprice = obj.getDouble("drprice");
					whiteprice = obj.getDouble("whiteprice");
					dyeprice = obj.getDouble("dyeprice");
					whiteloss = obj.getDouble("whiteloss");
					warploss = obj.getDouble("warploss");
					colorloss = obj.getDouble("colorloss");
					mtrid = obj.getString("mtrid");
					material_id = obj.getInt("id");
					insertMats(mtrename, mtrtype, countgu, countzhi, prprice, colorprice, drprice, whiteprice, dyeprice, whiteloss, warploss, colorloss, mtrid, material_id);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void ParseJSONdev(String[] args) throws IOException, JSONException {
		try {
			URL u = new URL("http://218.75.78.226:9000/zhongya_eos/ajax/ZY/dataProductList");
			HttpURLConnection hr = (HttpURLConnection) u.openConnection();
			if (hr.getResponseCode()==200) {
				InputStream is = hr.getInputStream();
				StringBuffer sb = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					line = br.readLine();
				}
				JSONArray arr = new JSONArray(sb.toString());
				DevData devdata = new DevData();
				ArrayList<Developments> developments = devdata.getDevelopments();
				String productCode;
				String content;
				String finishing; 
				String producttype;
				double moq;
				double weight;
				double ppcm;
				for (int i=0; i<arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					productCode = obj.getString("productid");
					content = obj.getString("component");
					finishing = checkFinishingType(obj.getString("defaultpdtypename"));
					producttype = checkProductType(obj.getString("producttype"));
					moq = parseSafeDouble(obj, "minquantity");
					weight = parseSafeDouble(obj, "Squareweight");
					ppcm = parseSafeDouble(obj, "Avabbdensity");
					for (Developments development : developments) {
						if (development.getCode().equals(productCode)) {
							//System.out.println(productCode + " found. MOQ: " + moq + ". Content: " + content + ". Weight:" + weight + ". PPCM: " + ppcm + ". finish: " + finishing + ". producttype: " + producttype + ".");
							devdata.updateDevTableString("content", content, "code", productCode);
							devdata.updateDevTableString("finishing_used", finishing, "code", productCode);
							devdata.updateDevTableString("fabric_type", producttype, "code", productCode);
							devdata.updateDevTableDouble("moq", moq, "code", productCode);
							devdata.updateDevTableDouble("weight", weight, "code", productCode);
							devdata.updateDevTableDouble("ppcm", ppcm, "code", productCode);
							break;
						}
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static double parseSafeDouble(JSONObject o, String key) {
	    String raw = o.optString(key, "").trim();
	    if (raw.isEmpty()) {
	        // missing or empty
	        return 0; 
	    }
	    try {
	        return Double.parseDouble(raw);
	    } catch (NumberFormatException e) {
	        // non‐numeric
	        return 0;
	    }
	}
	
	private static String checkProductType(String key) {
		if (key.equals("平板布")) {
			return "Base";
		} else if (key.equals("花布")) {
			return "Jaquard";
		} else {
			return "";
		}
	}
	
	private static String checkFinishingType(String key) {
		String finishing = "";
		if (key.contains("涂层")) {
			finishing += "KC,";
		}
		
		if (key.contains("轧毛")) {
			finishing += "NP,";
		}
		
		if (key.contains("空气洗")) {
			finishing += "AW,";
		}
		
		if (key.contains("定型")) {
			finishing += "HS,";
		}
		
		if (key.contains("单面绒")) {
			finishing += "SPB,";
		}
		
		if (key.contains("无纺棉")) {
			finishing += "FB,";
		}
		
		if (key.contains("TC布")) {
			finishing += "TC,";
		}
		
		if (key.contains("春亚纺")) {
			finishing += "KB,";
		}
		
		if (finishing.endsWith(",")) {
		    finishing = finishing.substring(0, finishing.length() - 1);
		}
		
		return finishing;
	}
	
	public static void getConn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(  
					url, user, password);
		}
		catch(SQLException e) {
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void truncateMats() {
		try {
			getConn();
			String sql = "TRUNCATE TABLE MijuPrice.mats;";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
	
	public static void insertMats(String mtrename, String mtrtype, double countgu, double countzhi, double prprice, double colorprice, double drprice, double whiteprice, double dyeprice, double whiteloss, double warploss, double colorloss, String mtrid, int material_id) {
		try {
			getConn();
			String sql = "INSERT INTO MijuPrice.mats(mtrename, mtrtype, countgu, countzhi, prprice, colorprice, drprice, whiteprice, dyeprice, whiteloss, warploss, colorloss, mtrid, material_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mtrename);
			stmt.setString(2, mtrtype);
			stmt.setDouble(3, countgu);
			stmt.setDouble(4, countzhi);
			stmt.setDouble(5, prprice);
			stmt.setDouble(6, colorprice);
			stmt.setDouble(7, drprice);
			stmt.setDouble(8, whiteprice);
			stmt.setDouble(9, dyeprice);
			stmt.setDouble(10, whiteloss);
			stmt.setDouble(11, warploss);
			stmt.setDouble(12, colorloss);
			stmt.setString(13, mtrid);
			stmt.setInt(14, material_id);
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

	@Override
	public void run() {
		try {
			ParseJSONmat(null);
			ParseJSONdev(null);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		} 
	}
}