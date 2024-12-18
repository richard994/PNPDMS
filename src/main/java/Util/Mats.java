package Util;

public class Mats {
	private String mat_name;
	private String mat_type;
	private String color_type;
	private String memo;
	private double use_percentage;
	private double cost;
	
	public Mats() {}
	
	public Mats(String mn, String mt, String ct, String mm, double up, double c) {
		this.mat_name = mn;
		this.mat_type = mt;
		this.color_type = ct;
		this.memo = mm;
		this.use_percentage = up;
		this.cost = c;
	}
	
	public void setMatName(String mn) {
		this.mat_name = mn;
	}
	
	public void setMatType(String mt) {
		this.mat_type = mt;
	}
	
	public void setMatColor(String ct) {
		this.color_type = ct;
	}
	
	public void setMatMemo(String mm) {
		this.memo = mm;
	}
	
	public void setUsePercent(double up) {
		this.use_percentage = up;
	}
	
	public void setMatCost(double c) {
		this.cost = c;
	}
	
	public String getMatStr() {
		String buffer = mat_name + ',' + mat_type + ',' + color_type + ',' + memo + ',' + use_percentage + ',' + cost;
		return buffer;
	}
	
	public String getMatName() {
		return mat_name;
	}
	
	public String getMatType() {
		return mat_type;
	}
	
	public String getMatColor() {
		return color_type;
	}
	
	public String getMatMemo() {
		return memo;
	}
	
	public double getUsePercent() {
		return use_percentage;
	}
	
	public double getMatCost() {
		return cost;
	}
}