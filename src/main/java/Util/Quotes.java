package Util;

public class Quotes {
	private String modelName;
	private String fabricType;
	private String createDate;
	private String finishModule;
	private String memo;
	private double salePrice;
	private double ppcm;
	private double totalWarp;
	private double weight;
	private int id;
	
	public Quotes() {}
	
	public Quotes(String mn, String ft, String cd, String fm, String mm, double sp, double p, double tw, double w, int i) {
		this.modelName = mn;
		this.fabricType = ft;
		this.createDate = cd;
		this.finishModule = fm;
		this.memo = mm;
		this.salePrice = sp;
		this.ppcm = p;
		this.totalWarp = tw;
		this.weight = w;
		this.id = i;
	}
	
	public void setModelName(String mn) {
		this.modelName = mn;
	}
	
	public void setFabricType(String ft) {
		this.fabricType = ft;
	}
	
	public void setDate(String cd) {
		this.createDate = cd;
	}
	
	public void setFinishModule(String fm) {
		this.finishModule = fm;
	}
	
	public void setMemo(String mm) {
		this.memo = mm;
	}
	
	public void setSalePrice(double sp) {
		this.salePrice = sp;
	}
	
	public void setPPCM(double p) {
		this.ppcm = p;
	}
	
	public void setTotalWarp(double tw) {
		this.totalWarp = tw;
	}
	
	public void setWeight(double w) {
		this.weight = w;
	}
	
	public void setId(int i) {
		this.id = i;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public String getFabricType() {
		return fabricType;
	}
	
	public String getDate() {
		return createDate;
	}
	
	public String getFinishModule() {
		return finishModule;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public double getFloatSalePrice() {
		return salePrice;
	}
	
	public double getFloatPicksPer() {
		return ppcm;
	}
	
	public double getFloatTotalWarp() {
		return totalWarp;
	}
	
	public double getFloatWeight() {
		return weight;
	}
	
	public String getSalePrice() {
		return String.format("%.2f", salePrice);
	}
	
	public String getPicksPer() {
		return String.format("%.2f", ppcm);
	}
	
	public String getTotalWarp() {
		return String.format("%.2f", totalWarp);
	}
	
	public String getWeight() {
		return String.format("%.2f", weight);
	}
	
	public String getId() {
		return String.valueOf(id);
	}
}