package Util;

public class Mats {
	private String name;
	private double whitePrice;
	private double dyePrice;
	private double colorPrice;
	private double drPrice;
	private double prPrice;
	private double countgu;
	private double countzhi;
	private String mtrtype;
	private double wloss;
	private String key;
	private String matName; 
	private String matType;
	private String matColor; 
	private String matMemo;
	private double usePercent;
	
	public Mats() {}
	
	public Mats(String name, double whitePrice, double dyePrice, double colorPrice, 
			double drPrice, double prPrice, double countgu, double countzhi, 
			String mtrtype, double wloss, String key) {
		setName(name);
		setWhitePrice(whitePrice);
		setDyePrice(dyePrice);
		setColorPrice(colorPrice);
		setDrPrice(drPrice);
		setPrPrice(prPrice);
		setCountgu(countgu);
		setCountzhi(countzhi);
		setMtrtype(mtrtype);
		setWloss(wloss);
		setKey(key);
	}
	
	public Mats(String matName, String wwType, String matColor, String matMemo, double usePercent) {
		setMatName(matName);
		setMatType(wwType);
		setMatColor(matColor);
		setMatMemo(matMemo);
		setUsePercent(usePercent);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWhitePrice() {
		return whitePrice;
	}

	public void setWhitePrice(double whitePrice) {
		this.whitePrice = whitePrice;
	}

	public double getDyePrice() {
		return dyePrice;
	}

	public void setDyePrice(double dyePrice) {
		this.dyePrice = dyePrice;
	}

	public double getColorPrice() {
		return colorPrice;
	}

	public void setColorPrice(double colorPrice) {
		this.colorPrice = colorPrice;
	}

	public double getDrPrice() {
		return drPrice;
	}

	public void setDrPrice(double drPrice) {
		this.drPrice = drPrice;
	}

	public double getPrPrice() {
		return prPrice;
	}

	public void setPrPrice(double prPrice) {
		this.prPrice = prPrice;
	}

	public double getCountgu() {
		return countgu;
	}

	public void setCountgu(double countgu) {
		this.countgu = countgu;
	}

	public double getCountzhi() {
		return countzhi;
	}

	public void setCountzhi(double countzhi) {
		this.countzhi = countzhi;
	}

	public String getMtrtype() {
		return mtrtype;
	}

	public void setMtrtype(String mtrtype) {
		this.mtrtype = mtrtype;
	}

	public double getWloss() {
		return wloss;
	}

	public void setWloss(double wloss) {
		this.wloss = wloss;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public String getMatType() {
		return matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	public String getMatColor() {
		return matColor;
	}

	public void setMatColor(String matColor) {
		this.matColor = matColor;
	}

	public String getMatMemo() {
		return matMemo;
	}

	public void setMatMemo(String matMemo) {
		this.matMemo = matMemo;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
	}
}