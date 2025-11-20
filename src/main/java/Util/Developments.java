package Util;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class Developments {
	private int dev_id;
	private String Code;
	private String Color;
	private double Cost;
	private boolean isParagonClean;
	private boolean is400hrFCL;
	private boolean isPieceDyed;
	private boolean needFeedback;
	private boolean needChinaFeedback;
	private boolean isSDY;
	private boolean isChenille;
	private boolean inactive;
	private boolean priceConfirmed;
	private boolean is1000hrFCL;
	private String Fabric_type;
	private String Design_type;
	private String Colorist;
	private String Finishing_used; 
	private String Season;
	private String Yarn_type;
	private String Warp_type;
	private String Content;
	private String Strike_off_status;
	private String Strike_off_birthday;
	private String Blanket_status;
	private String Blanket_datestamp;
	private String Colorline_status;
	private String Colorline_datestamp;
	private String Rollsample_status;
	private String Rollsample_datestamp;
	private String Test_status;
	private String Test_datestamp;
	private double Moq;
	private double Weight;
	private int NumColorline;
	private int NumColor;
	private int NumTotalColor;
	private double Ppcm;
	private String Note;
	private String Fabric_img_path = "";
	private String Pid_path = "";
	private String Test_report_path = "";
	private String CurrentPhase;
	private String dateTime;
	private String LastModified;
	private String DateCurrentPhase;
	private boolean isKnit;
	private String Designer;
	private String Direction;
	private boolean GeorgeCanceled;
	private double priceMin;
	private double priceMax;
	
	public Developments() {}
	
	public Developments(int id, String code, String color, double cost, 
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback, 
			boolean IsSDY, boolean IsChenille, String fabric_type, String design_type, String colorist, String finishing_used, 
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String blanket_datestamp, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, double moq, double weight, int numColorline, int numColor, int numTotalColor,
			double ppcm, String note, String fabric_img_path, String pid_path, 
			String test_report_path, String currentPhase, String DateTime, String LastModified, String DateCurrentPhase, 
			boolean IsKnit, String designer, String direction, boolean GeorgeCanceled, String strike_off_birthday,
			boolean NeedChinaFeedback, boolean inactive, boolean priceConfirmed, boolean Is1000hrFCL) {
		setDev_id(id);
		setCode(code);
		setColor(color);
		setCost(cost);
		setParagonClean(IsParagonClean);
		setIs400hrFCL(Is400hrFCL);
		setPieceDyed(IsPieceDyed);
		setNeedFeedback(NeedFeedback);
		setSDY(IsSDY);
		setChenille(IsChenille);
		setFabric_type(fabric_type);
		setDesign_type(design_type);
		setColorist(colorist);
		setFinishing_used(finishing_used);
		setSeason(season);
		setYarn_type(yarn_type);
		setWarp_type(warp_type);
		setContent(content);
		setStrike_off_status(strike_off_status);
		setBlanket_status(blanket_status);
		setBlanket_datestamp(blanket_datestamp);
		setColorline_status(colorline_status);
		setColorline_datestamp(colorline_datestamp);
		setRollsample_status(rollsample_status);
		setRollsample_datestamp(rollsample_datestamp);
		setTest_status(test_status);
		setTest_datestamp(test_datestamp);
		setMoq(moq);
		setWeight(weight);
		setNumColorline(numColorline);
		setNumColor(numColor);
		setNumTotalColor(numTotalColor);
		setPpcm(ppcm);
		setNote(note);
		setFabric_img_path(fabric_img_path);
		setPid_path(pid_path);
		setTest_report_path(test_report_path);
		setCurrentPhase(currentPhase);
		setDateTime(DateTime);
		setLastModified(LastModified);
		setDateCurrentPhase(DateCurrentPhase);
		setKnit(IsKnit);
		setDesigner(designer);
		setDirection(direction);
		setGeorgeCanceled(GeorgeCanceled);
		setStrike_off_birthday(strike_off_birthday);
		setNeedChinaFeedback(NeedChinaFeedback);
		setInactive(inactive);
		setPriceConfirmed(priceConfirmed);
		setIs1000hrFCL(Is1000hrFCL);
	}
	
	public void setAll(int id, String code, String color, double cost, 
			boolean IsParagonClean, boolean Is400hrFCL, boolean IsPieceDyed, boolean NeedFeedback, 
			boolean IsSDY, boolean IsChenille, String fabric_type, String design_type, String colorist, String finishing_used, 
			String season, String yarn_type, String warp_type, String content, String strike_off_status,
			String blanket_status, String blanket_datestamp, String colorline_status, String colorline_datestamp,
			String rollsample_status, String rollsample_datestamp, String test_status,
			String test_datestamp, double moq, double weight, int numColorline, int numColor, int numTotalColor, double ppcm, String note, String fabric_img_path, 
			String pid_path, String test_report_path, String currentPhase, String DateTime, String LastModified, String DateCurrentPhase, 
			boolean IsKnit, String designer, String direction, boolean GeorgeCanceled, String strike_off_birthday,
			boolean NeedChinaFeedback, boolean inactive, boolean priceConfirmed, boolean Is1000hrFCL) {
		setDev_id(id);
		setCode(code);
		setColor(color);
		setCost(cost);
		setParagonClean(IsParagonClean);
		setIs400hrFCL(Is400hrFCL);
		setPieceDyed(IsPieceDyed);
		setNeedFeedback(NeedFeedback);
		setSDY(IsSDY);
		setChenille(IsChenille);
		setFabric_type(fabric_type);
		setDesign_type(design_type);
		setColorist(colorist);
		setFinishing_used(finishing_used);
		setSeason(season);
		setYarn_type(yarn_type);
		setWarp_type(warp_type);
		setContent(content);
		setStrike_off_status(strike_off_status);
		setBlanket_status(blanket_status);
		setBlanket_datestamp(blanket_datestamp);
		setColorline_status(colorline_status);
		setColorline_datestamp(colorline_datestamp);
		setRollsample_status(rollsample_status);
		setRollsample_datestamp(rollsample_datestamp);
		setTest_status(test_status);
		setTest_datestamp(test_datestamp);
		setMoq(moq);
		setWeight(weight);
		setNumColorline(numColorline);
		setNumColor(numColor);
		setNumTotalColor(numTotalColor);
		setPpcm(ppcm);
		setNote(note);
		setFabric_img_path(fabric_img_path);
		setPid_path(pid_path);
		setTest_report_path(test_report_path);
		setCurrentPhase(currentPhase);
		setDateTime(DateTime);
		setLastModified(LastModified);
		setDateCurrentPhase(DateCurrentPhase);
		setKnit(IsKnit);
		setDesigner(designer);
		setDirection(direction);
		setGeorgeCanceled(GeorgeCanceled);
		setStrike_off_birthday(strike_off_birthday);
		setNeedChinaFeedback(NeedChinaFeedback);
		setInactive(inactive);
		setPriceConfirmed(priceConfirmed);
		setIs1000hrFCL(Is1000hrFCL);
	}
	
	public ArrayList<Log> compare(Developments otherDev, String username) {
		ArrayList<Log> logs = new ArrayList<>();
		
		try {
			// Get all fields of the Development class (including private ones)
            Field[] fields = Developments.class.getDeclaredFields();
            
            for (Field field : fields) {
                field.setAccessible(true); // Make private fields accessible
                String attributeName = field.getName();
                
                // Skip comparison for the 'dateTime' field
                if (attributeName.equals("dateTime") || attributeName.equals("dev_id") || attributeName.equals("LastModified") || attributeName.equals("DateCurrentPhase")) {
                    continue;  // Skip this field and move to the next one
                }

                Object oldValue = field.get(this); // Get value from the current object
                Object newValue = field.get(otherDev); // Get value from the other object
                // If the values are different, create a Log object
                if (newValue != null && !newValue.equals(oldValue)) {
                	String oldval = (oldValue == null) ? "none" : oldValue.toString();
                	String newval = newValue.toString();
                	if (attributeName.equals("Fabric_img_path") || attributeName.equals("Pid_path") || attributeName.equals("Test_report_path")) {
                		if (oldval.equals("") || oldval.equals("none")) {
                			oldval = "none";
                		} else {
                			try {
                				oldval = oldval.substring(oldval.indexOf("com/") + "com/".length());
                			} catch (Exception e) {
                				oldval = "old_path";
                			}
                		}
                		try {
                			newval = newval.substring(newval.indexOf("com/") + "com/".length());
                		} catch (Exception e) {
                			newval = "new_path";
                		}
                	}
                	LocalDateTime currentDateTime = LocalDateTime.now();
                    String datestamp = currentDateTime.toString();
                	Log log = new Log(username, datestamp, "Updated " + attributeName + " from " + oldval + " to " + newval);
                	logs.add(log);
                }
            }
		} catch (IllegalAccessException e) {
            e.printStackTrace();
            return logs;
        }
		
		return logs;
	}
	
	public String checkPhase(Developments otherDev) {
		String DateCurrentPhase = "";
		
		try {
			// Get all fields of the Development class (including private ones)
            Field[] fields = Developments.class.getDeclaredFields();
            
            for (Field field : fields) {
            	field.setAccessible(true); // Make private fields accessible
                String attributeName = field.getName();
                
                if (attributeName.equals("Strike_off_status") || attributeName.equals("Blanket_status") || 
                	attributeName.equals("Colorline_status") || attributeName.equals("Rollsample_status")) {
                	Object oldValue = field.get(this); // Get value from the current object
                    Object newValue = field.get(otherDev); // Get value from the other object
                    
                    if (newValue != null && !newValue.equals(oldValue)) {
                    	return LocalDateTime.now().toString();
                    }
                } else {
                	continue;
                }
            }
		} catch (IllegalAccessException e) {
            e.printStackTrace();
            return DateCurrentPhase;
        }
		
		return DateCurrentPhase;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		this.Code = code;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		this.Color = color;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		this.Cost = cost;
	}

	public boolean isParagonClean() {
		return isParagonClean;
	}

	public void setParagonClean(boolean isParagonClean) {
		this.isParagonClean = isParagonClean;
	}

	public boolean isIs400hrFCL() {
		return is400hrFCL;
	}

	public void setIs400hrFCL(boolean is400hrFCL) {
		this.is400hrFCL = is400hrFCL;
	}

	public boolean isPieceDyed() {
		return isPieceDyed;
	}

	public void setPieceDyed(boolean isPieceDyed) {
		this.isPieceDyed = isPieceDyed;
	}

	public boolean isNeedFeedback() {
		return needFeedback;
	}

	public void setNeedFeedback(boolean needFeedback) {
		this.needFeedback = needFeedback;
	}

	public boolean isSDY() {
		return isSDY;
	}

	public void setSDY(boolean isSDY) {
		this.isSDY = isSDY;
	}

	public String getFabric_type() {
		return Fabric_type;
	}

	public void setFabric_type(String fabric_type) {
		this.Fabric_type = fabric_type;
	}

	public String getDesign_type() {
		return Design_type;
	}

	public void setDesign_type(String design_type) {
		this.Design_type = design_type;
	}

	public String getColorist() {
		return Colorist;
	}

	public void setColorist(String colorist) {
		this.Colorist = colorist;
	}

	public String getFinishing_used() {
		return Finishing_used;
	}

	public void setFinishing_used(String finishing_used) {
		this.Finishing_used = finishing_used;
	}

	public String getSeason() {
		return Season;
	}

	public void setSeason(String season) {
		this.Season = season;
	}

	public String getYarn_type() {
		return Yarn_type;
	}

	public void setYarn_type(String yarn_type) {
		this.Yarn_type = yarn_type;
	}

	public String getWarp_type() {
		return Warp_type;
	}

	public void setWarp_type(String warp_type) {
		this.Warp_type = warp_type;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	public String getStrike_off_status() {
		return Strike_off_status;
	}

	public void setStrike_off_status(String strike_off_status) {
		this.Strike_off_status = strike_off_status;
	}

	public String getBlanket_status() {
		return Blanket_status;
	}

	public void setBlanket_status(String blanket_status) {
		this.Blanket_status = blanket_status;
	}

	public String getColorline_status() {
		return Colorline_status;
	}

	public void setColorline_status(String colorline_status) {
		this.Colorline_status = colorline_status;
	}

	public String getColorline_datestamp() {
		return Colorline_datestamp;
	}

	public void setColorline_datestamp(String colorline_datestamp) {
		this.Colorline_datestamp = colorline_datestamp;
	}

	public String getRollsample_status() {
		return Rollsample_status;
	}

	public void setRollsample_status(String rollsample_status) {
		this.Rollsample_status = rollsample_status;
	}

	public String getRollsample_datestamp() {
		return Rollsample_datestamp;
	}

	public void setRollsample_datestamp(String rollsample_datestamp) {
		this.Rollsample_datestamp = rollsample_datestamp;
	}

	public String getTest_status() {
		return Test_status;
	}

	public void setTest_status(String test_status) {
		this.Test_status = test_status;
	}

	public String getTest_datestamp() {
		return Test_datestamp;
	}

	public void setTest_datestamp(String test_datestamp) {
		this.Test_datestamp = test_datestamp;
	}

	public double getMoq() {
		return Moq;
	}

	public void setMoq(double moq) {
		this.Moq = moq;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		this.Weight = weight;
	}

	public int getNumColorline() {
		return NumColorline;
	}

	public void setNumColorline(int numColorline) {
		this.NumColorline = numColorline;
	}

	public double getPpcm() {
		return Ppcm;
	}

	public void setPpcm(double ppcm) {
		this.Ppcm = ppcm;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		this.Note = note;
	}

	public String getFabric_img_path() {
		return Fabric_img_path;
	}

	public void setFabric_img_path(String fabric_img_path) {
		this.Fabric_img_path = fabric_img_path;
	}

	public String getPid_path() {
		return Pid_path;
	}

	public void setPid_path(String pid_path) {
		this.Pid_path = pid_path;
	}

	public String getTest_report_path() {
		return Test_report_path;
	}

	public void setTest_report_path(String test_report_path) {
		this.Test_report_path = test_report_path;
	}

	public String getCurrentPhase() {
		return CurrentPhase;
	}

	public void setCurrentPhase(String currentPhase) {
		this.CurrentPhase = currentPhase;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getDev_id() {
		return dev_id;
	}

	public void setDev_id(int dev_id) {
		this.dev_id = dev_id;
	}

	public String getLastModified() {
		return LastModified;
	}

	public void setLastModified(String lastModified) {
		LastModified = lastModified;
	}

	public String getDateCurrentPhase() {
		return DateCurrentPhase;
	}

	public void setDateCurrentPhase(String dateCurrentPhase) {
		DateCurrentPhase = dateCurrentPhase;
	}

	public boolean isChenille() {
		return isChenille;
	}

	public void setChenille(boolean isChenille) {
		this.isChenille = isChenille;
	}

	public boolean isKnit() {
		return isKnit;
	}

	public void setKnit(boolean isKnit) {
		this.isKnit = isKnit;
	}

	public String getDesigner() {
		return Designer;
	}

	public void setDesigner(String designer) {
		Designer = designer;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public boolean isGeorgeCanceled() {
		return GeorgeCanceled;
	}

	public void setGeorgeCanceled(boolean georgeCanceled) {
		GeorgeCanceled = georgeCanceled;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	public double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	public String getStrike_off_birthday() {
		return Strike_off_birthday;
	}

	public void setStrike_off_birthday(String strike_off_birthday) {
		Strike_off_birthday = strike_off_birthday;
	}

	public boolean isNeedChinaFeedback() {
		return needChinaFeedback;
	}

	public void setNeedChinaFeedback(boolean needChinaFeedback) {
		this.needChinaFeedback = needChinaFeedback;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public boolean isPriceConfirmed() {
		return priceConfirmed;
	}

	public void setPriceConfirmed(boolean priceConfirmed) {
		this.priceConfirmed = priceConfirmed;
	}

	public boolean isIs1000hrFCL() {
		return is1000hrFCL;
	}

	public void setIs1000hrFCL(boolean is1000hrFCL) {
		this.is1000hrFCL = is1000hrFCL;
	}

	public String getBlanket_datestamp() {
		return Blanket_datestamp;
	}

	public void setBlanket_datestamp(String blanket_datestamp) {
		Blanket_datestamp = blanket_datestamp;
	}

	public int getNumColor() {
		return NumColor;
	}

	public void setNumColor(int numColor) {
		NumColor = numColor;
	}

	public int getNumTotalColor() {
		return NumTotalColor;
	}

	public void setNumTotalColor(int numTotalColor) {
		NumTotalColor = numTotalColor;
	}
}