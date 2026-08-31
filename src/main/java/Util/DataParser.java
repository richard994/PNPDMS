package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.net.HttpURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser implements Runnable {
	private static final int CONNECT_TIMEOUT_MS = 10_000;
	private static final int READ_TIMEOUT_MS = 30_000;

	public DataParser() {}

	public static void ParseJSONmat(String[] args) throws IOException, JSONException {
		try {
			URL u = new URL("http://218.75.78.226:9000/zhongya_eos/ajax/ZY/dataMaterial24");
			HttpURLConnection hr = (HttpURLConnection) u.openConnection();
			hr.setConnectTimeout(CONNECT_TIMEOUT_MS);
			hr.setReadTimeout(READ_TIMEOUT_MS);
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
			hr.setConnectTimeout(CONNECT_TIMEOUT_MS);
			hr.setReadTimeout(READ_TIMEOUT_MS);
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
				String producttype;/*
				boolean isChenille;
				boolean isPieceDyed;
				boolean isSDY;
				boolean isKnit;*/
				double moq;
				double weight;
				double ppcm;
				Set<String> jsonCodes = new HashSet<>();
				for (int i=0; i<arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					productCode = obj.getString("productid");
					jsonCodes.add(productCode);
					
					content = obj.getString("component");
					finishing = checkFinishingType(obj.getString("defaultpdtypename"));
					producttype = checkProductType(obj.getString("producttype"));
					moq = parseSafeDouble(obj, "minquantity");
					weight = parseSafeDouble(obj, "Squareweight");
					ppcm = parseSafeDouble(obj, "Avabbdensity");/*
					isChenille = "Y".equals(obj.getString("isxne"));
					isPieceDyed = "Y".equals(obj.getString("ispiran"));
					isSDY = "Y".equals(obj.getString("issemuli"));
					isKnit = "Y".equals(obj.getString("iszhenzhi"));*/
					for (Developments development : developments) {
						if (development.getCode().equals(productCode)) {
							devdata.updateDevTableString("content", content, "code", productCode);
							devdata.updateDevTableString("finishing_used", finishing, "code", productCode);
							devdata.updateDevTableString("fabric_type", producttype, "code", productCode);
							devdata.updateDevTableDouble("moq", moq, "code", productCode);
							devdata.updateDevTableDouble("weight", weight, "code", productCode);
							devdata.updateDevTableDouble("ppcm", ppcm, "code", productCode);/*
							devdata.updateDevTableBoolean("IsChenille", isChenille, "code", productCode);
							devdata.updateDevTableBoolean("IsPieceDyed", isPieceDyed, "code", productCode);
							devdata.updateDevTableBoolean("IsSDY", isSDY, "code", productCode);
							devdata.updateDevTableBoolean("IsKnit", isKnit, "code", productCode);*/
							break;
						}
					}
				}
				
				for (Developments development : developments) {
				    String code = development.getCode();
				    if (!jsonCodes.contains(code)) {
				        devdata.updateDevTableString("content", "", "code", code);
				        devdata.updateDevTableString("finishing_used", "", "code", code);
				        devdata.updateDevTableString("fabric_type", "", "code", code);
				        devdata.updateDevTableDouble("moq", 0.0, "code", code);
				        devdata.updateDevTableDouble("weight", 0.0, "code", code);
				        devdata.updateDevTableDouble("ppcm", 0.0, "code", code);/*
				        devdata.updateDevTableBoolean("IsChenille", false, "code", code);
						devdata.updateDevTableBoolean("IsPieceDyed", false, "code", code);
						devdata.updateDevTableBoolean("IsSDY", false, "code", code);
						devdata.updateDevTableBoolean("IsKnit", false, "code", code);*/
				    }
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void ParseJSONColor(String[] args) throws IOException,JSONException
	{

		try {
			URL u = new URL("http://218.75.78.226:9000/zhongya_eos/ajax/ZY/dataMtrColor");
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

				//从中亚erp中获取包袱样所需要的原料色号  需要解决单个更新问题 以materialtypestruc和coloridx为关键词判断更新
				//将获取的数据保存到mtrcolor表中
				//上述过程需要单独实现
				DevData data = new DevData();
				ArrayList<MtrColors> mts = data.getMtrColors();

				//处理增加
				for(int i=0;i<arr.length();i++){
					JSONObject item = arr.getJSONObject(i);
					boolean ext = false;
					for(int j=0;j<mts.size();j++){
						//mts.get(j).compare();
						if(mts.get(i).getColoridx().equals(item.getString("coloridx")) &&
							mts.get(i).getMaterialtypestruc().equals(item.getString("materialtypestruc"))
						){
							ext = true;
							break;
						}
					}
					if(!ext){
						data.insertMtrColor(item.getString("materialtypestrucname"),
								item.getString("materialtypestruc"),
								item.getString("colorid"),
								item.getString("coloridx"));
					}
				}

				//处理删除
//				for(int i=0;i<mts.size();i++){
//					boolean ext = false;
//					for(int j=0;j<arr.length();j++){
//						JSONObject item = arr.getJSONObject(j);
//						if(mts.get(i).getColoridx().equals(item.getString("coloridx")) &&
//								mts.get(i).getMaterialtypestruc().equals(item.getString("materialtypestruc"))
//						){
//							ext = true;
//							break;
//						}
//					}
//					if(!ext){
//						data.deleteMtrColor(mts.get(i).getMaterialtypestruc(),mts.get(i).getColoridx());
//					}
//				}


			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	static double parseSafeDouble(JSONObject o, String key) {
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
	
	static String checkProductType(String key) {
		if (key.contains("平板布")) {
			return "Base";
		} else if (key.contains("花布")) {
			return "Jaquard";
		} else {
			return "";
		}
	}
	
	static String checkFinishingType(String key) {
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
		
		if (key.contains("砂洗")) {
			finishing += "SW,";
		}
		
		if (finishing.endsWith(",")) {
		    finishing = finishing.substring(0, finishing.length() - 1);
		}
		
		return finishing;
	}
	
	public static void truncateMats() {
		String sql = "TRUNCATE TABLE MijuPrice.mats;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertMats(String mtrename, String mtrtype, double countgu, double countzhi, double prprice, double colorprice, double drprice, double whiteprice, double dyeprice, double whiteloss, double warploss, double colorloss, String mtrid, int material_id) {
		String sql = "INSERT INTO MijuPrice.mats(mtrename, mtrtype, countgu, countzhi, prprice, colorprice, drprice, whiteprice, dyeprice, whiteloss, warploss, colorloss, mtrid, material_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			ParseJSONmat(null);
			ParseJSONdev(null);
			ParseJSONColor(null);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		} 
	}
}