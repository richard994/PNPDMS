package Util;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MtrColors {
	private String materialtypestrucname;
	private String materialtypestruc;
	private String colorid;
	private String coloridx;

	public String getMaterialtypestrucname() {
		return materialtypestrucname;
	}

	public void setMaterialtypestrucname(String materialtypestrucname) {
		this.materialtypestrucname = materialtypestrucname;
	}

	public String getMaterialtypestruc() {
		return materialtypestruc;
	}

	public void setMaterialtypestruc(String materialtypestruc) {
		this.materialtypestruc = materialtypestruc;
	}

	public String getColorid() {
		return colorid;
	}

	public void setColorid(String colorid) {
		this.colorid = colorid;
	}

	public String getColoridx() {
		return coloridx;
	}

	public void setColoridx(String coloridx) {
		this.coloridx = coloridx;
	}

	public MtrColors() {}

	public MtrColors(String materialtypestrucname, String materialtypestruc, String colorid, String coloridx) {
		setMaterialtypestrucname(materialtypestrucname);
		setMaterialtypestruc(materialtypestruc);
		setColorid(colorid);
		setColoridx(coloridx);
	}
	
	public void setAll( String fabric_type, String design_type, String colorist, String finishing_used) {
		setMaterialtypestrucname(materialtypestrucname);
		setMaterialtypestruc(materialtypestruc);
		setColorid(colorid);
		setColoridx(coloridx);
	}
	
	public ArrayList<Log> compare(MtrColors otherDev, String username) {
		ArrayList<Log> logs = new ArrayList<>();
		
		try {
			// Get all fields of the Development class (including private ones)
            Field[] fields = MtrColors.class.getDeclaredFields();
            
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
	
	public String checkPhase(MtrColors otherDev) {
		String DateCurrentPhase = "";
		
		try {
			// Get all fields of the Development class (including private ones)
            Field[] fields = MtrColors.class.getDeclaredFields();
            
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


}