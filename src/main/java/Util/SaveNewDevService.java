package Util;

import java.io.*;
import java.time.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/SaveNewDevService")
@MultipartConfig
public class SaveNewDevService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "uploads";
	private static String title; 
	private static String code;
	private static String color;
	private static double cost;
	private static boolean IsParagonClean = false;
	private static boolean Is400hrFCL = false;
	private static boolean IsPieceDyed = false;
	private static boolean NeedFeedback = false;
	private static boolean IsSDY = false;
	private static String fabric_type;
	private static String design_type;
	private static String colorist;
	private static String finishing_used; 
	private static String season;
	private static String yarn_type;
	private static String warp_type;
	private static String content;
	private static String strike_off_status;
	private static String blanket_status;
	private static String colorline_status;
	private static String colorline_datestamp;
	private static String rollsample_status;
	private static String rollsample_datestamp;
	private static String test_status;
	private static String test_datestamp;
	private static String customs;
	private static double moq;
	private static double weight;
	private static String nickname;
	private static int numColorline;
	private static double ppcm;
	private static String note;
	private static String fabric_img_path = "";
	private static String pid_path = "";
	private static String test_report_path = "";
	private static String currentPhase;
	private static String DateTime;
	private static String LeahComment;
	private static String LeahComment_datestamp;
	private static String USComment;
	private static String USComment_datestamp;
	private static String MillComment;
	private static String MillComment_datestamp;
	private static String GeorgeComment;
	private static String GeorgeComment_datestamp;
	
	public SaveNewDevService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		String temp[];

		temp = request.getParameterValues("Title");
		if (temp != null) {
			title = temp[0];
			System.out.println("Successfully retrieved title: " + title + "\n");
		} else {
			System.out.println("Fail to retrieve title.");
		}
		
		temp = request.getParameterValues("Code");
		if (temp != null) {
			code = temp[0];
			System.out.println("Successfully retrieved code: " + code + "\n");
		} else {
			System.out.println("Fail to retrieve code.");
		}
		
		temp = request.getParameterValues("Color");
		if (temp != null) {
			color = temp[0];
			System.out.println("Successfully retrieved color: " + color + "\n");
		} else {
			System.out.println("Fail to retrieve color.");
		}
		
		temp = request.getParameterValues("Cost");
		if (temp != null) {
			try {
				cost = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved cost: " + cost + "\n");
			}
			catch (Exception e) {
				cost = 0.0f;
				System.out.println("Exception: " + e);
			}
		} else {
			cost = 0.0f;
			System.out.println("Fail to retrieve cost, default to 0.0.\n");
		}
		
		temp = request.getParameterValues("ParagonCleanCB");
		if (temp != null) {
			IsParagonClean = true;
			System.out.println("IsParagonClean checked.\n");
		} else {
			System.out.println("IsParagonClean unchecked.\n");
		}
		
		temp = request.getParameterValues("FCLCB");
		if (temp != null) {
			Is400hrFCL = true;
			System.out.println("Is400hrFCL checked.\n");
		} else {
			System.out.println("Is400hrFCL unchecked.\n");
		}
		
		temp = request.getParameterValues("PDCB");
		if (temp != null) {
			IsPieceDyed = true;
			System.out.println("IsPieceDyed checked.\n");
		} else {
			System.out.println("IsPieceDyed unchecked.\n");
		}
		
		temp = request.getParameterValues("FeedbackCB");
		if (temp != null) {
			NeedFeedback = true;
			System.out.println("NeedFeedback checked.\n");
		} else {
			System.out.println("NeedFeedback unchecked.\n");
		}
		
		temp = request.getParameterValues("SDYCB");
		if (temp != null) {
			IsSDY = true;
			System.out.println("IsSDY checked.\n");
		} else {
			System.out.println("IsSDY unchecked.\n");
		}
		
		temp = request.getParameterValues("FabricType");
		if (temp != null) {
			fabric_type = temp[0];
			System.out.println("Successfully retrieved fabric type: " + fabric_type + "\n");
		} else {
			System.out.println("Fail to retrieve fabric type.");
		}
		
		temp = request.getParameterValues("DesignType");
		if (temp != null) {
			design_type = temp[0];
			System.out.println("Successfully retrieved design type: " + design_type + "\n");
		} else {
			System.out.println("Fail to retrieve design type.");
		}
		
		temp = request.getParameterValues("Colorist");
		if (temp != null) {
			colorist = temp[0];
			System.out.println("Successfully retrieved colorist: " + colorist + "\n");
		} else {
			System.out.println("Fail to retrieve colorist.");
		}
		
		temp = request.getParameterValues("Backing");
		if (temp != null) {
			finishing_used = temp[0];
			System.out.println("Successfully retrieved finishing: " + finishing_used + "\n");
		} else {
			System.out.println("Fail to retrieve finishing.");
		}
		
		temp = request.getParameterValues("Season");
		if (temp != null) {
			season = temp[0];
			System.out.println("Successfully retrieved season: " + season + "\n");
		} else {
			System.out.println("Fail to retrieve season.");
		}
		
		temp = request.getParameterValues("YarnType");
		if (temp != null) {
			yarn_type = temp[0];
			System.out.println("Successfully retrieved yarn type: " + yarn_type + "\n");
		} else {
			System.out.println("Fail to retrieve yarn type.");
		}
		
		temp = request.getParameterValues("WarpType");
		if (temp != null) {
			warp_type = temp[0];
			System.out.println("Successfully retrieved warp type: " + warp_type + "\n");
		} else {
			System.out.println("Fail to retrieve warp type.");
		}
		
		temp = request.getParameterValues("Content");
		if (temp != null) {
			content = temp[0];
			System.out.println("Successfully retrieved content: " + content + "\n");
		} else {
			System.out.println("Fail to retrieve content.");
		}
		
		temp = request.getParameterValues("StrikeProgress");
		if (temp != null) {
			strike_off_status = temp[0];
			System.out.println("Successfully retrieved strike-off progress: " + strike_off_status + "\n");
		} else {
			System.out.println("Fail to retrieve strike-off progress.");
		}
		
		temp = request.getParameterValues("BlanketStatus");
		if (temp != null) {
			blanket_status = temp[0];
			System.out.println("Successfully retrieved blanket status: " + blanket_status + "\n");
		} else {
			System.out.println("Fail to retrieve blanket status.");
		}
		
		temp = request.getParameterValues("ColorLineProgress");
		if (temp != null) {
			colorline_status = temp[0];
			System.out.println("Successfully retrieved colorline status: " + colorline_status + "\n");
		} else {
			System.out.println("Fail to retrieve colorline status.");
		}
		
		temp = request.getParameterValues("ColorlineDatestamp");
		if (temp != null) {
			colorline_datestamp = temp[0];
			System.out.println("Successfully retrieved colorline datestamp: " + colorline_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve colorline datestamp.");
		}
		
		temp = request.getParameterValues("RollSampleProgress");
		if (temp != null) {
			rollsample_status = temp[0];
			System.out.println("Successfully retrieved rollsample status: " + rollsample_status + "\n");
		} else {
			System.out.println("Fail to retrieve rollsample status.");
		}
		
		temp = request.getParameterValues("RollSampleDatestamp");
		if (temp != null) {
			rollsample_datestamp = temp[0];
			System.out.println("Successfully retrieved rollsample datestamp: " + rollsample_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve rollsample datestamp.");
		}
		
		temp = request.getParameterValues("TestingProgress");
		if (temp != null) {
			test_status = temp[0];
			System.out.println("Successfully retrieved test status: " + test_status + "\n");
		} else {
			System.out.println("Fail to retrieve test status.");
		}
		
		temp = request.getParameterValues("TestingDatestamp");
		if (temp != null) {
			test_datestamp = temp[0];
			System.out.println("Successfully retrieved test datestamp: " + test_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve test datestamp.");
		}
		
		temp = request.getParameterValues("CustomsCat");
		if (temp != null) {
			customs = temp[0];
			System.out.println("Successfully retrieved customs: " + customs + "\n");
		} else {
			System.out.println("Fail to retrieve customs.");
		}
		
		temp = request.getParameterValues("MOQ");
		if (temp != null) {
			try {
				moq = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved moq: " + moq + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve moq.");
		}
		
		temp = request.getParameterValues("Weight");
		if (temp != null) {
			try {
				weight = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved weight: " + weight + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve weight.");
		}
		
		temp = request.getParameterValues("FabricNickname");
		if (temp != null) {
			nickname = temp[0];
			System.out.println("Successfully retrieved fabric nickname: " + nickname + "\n");
		} else {
			System.out.println("Fail to retrieve fabric nickname.");
		}
		
		temp = request.getParameterValues("NumColorLine");
		if (temp != null) {
			try {
				numColorline = Integer.parseInt(temp[0]);
				System.out.println("Successfully retrieved num colorline required: " + numColorline + "\n");
			}
			catch (Exception e) {
				numColorline = 0;
				System.out.println("Exception: " + e);
			}
		} else {
			numColorline = 0;
			System.out.println("Fail to retrieve num colorline required, default to 0.\n");
		}
		
		temp = request.getParameterValues("PPCM");
		if (temp != null) {
			try {
				ppcm = Double.parseDouble(temp[0]);
				System.out.println("Successfully retrieved ppcm: " + ppcm + "\n");
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve ppcm.");
		}
		
		temp = request.getParameterValues("Note");
		if (temp != null) {
			note = temp[0];
			System.out.println("Successfully retrieved note: " + note + "\n");
		} else {
			System.out.println("Fail to retrieve note.");
		}
		
		if (test_status.equals("DNE") && rollsample_status.equals("DNE") && blanket_status.equals("DNE")) {
			currentPhase = "Strike-off";
		} else if (test_status.equals("DNE") && rollsample_status.equals("DNE")) {
			currentPhase =  "Blanket";
		} else if (test_status.equals("DNE")) {
			currentPhase = "Roll Sample";
		} else {
			currentPhase = "Testing";
			if (test_status.equals("Passed")) {
				currentPhase = "Testing Passed";
			}
		}
		System.out.println("Current Phase: " + currentPhase + "\n");
		
		Part fabricPicPart = request.getPart("FabricPicInput");
        System.out.println("fabricPicPart: " + fabricPicPart);
        if (fabricPicPart != null && fabricPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + fabricPicPart.getSize());
        	fabric_img_path = saveFile(fabricPicPart);
        	System.out.println("FabricPic stored at: " + fabric_img_path + "\n");
        } else {
        	System.out.println("FabricPic not uploaded.\n");
        }
        
        Part pidPicPart = request.getPart("PidInput");
        System.out.println("pidPicPart: " + pidPicPart);
        if (pidPicPart != null && pidPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + pidPicPart.getSize());
        	pid_path = saveFile(pidPicPart);
            System.out.println("PidPic stored at: " + pid_path + "\n");
        } else {
        	System.out.println("PidPic not uploaded.\n");
        }
        
        Part testReportPicPart = request.getPart("TestReportInput");
        System.out.println("testReportPicPart: " + testReportPicPart);
        if (testReportPicPart != null && testReportPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + testReportPicPart.getSize());
        	test_report_path = saveFile(testReportPicPart);
            System.out.println("TestReportPic stored at: " + test_report_path + "\n");
        } else {
        	System.out.println("TestReportPic not uploaded.\n");
        }
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTime = currentDateTime.toString();
        System.out.println("Current date & time: " + DateTime + "\n");
		
		DevData devdata = new DevData();
		devdata.insertQuote(title, code, color, cost, IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, IsSDY, fabric_type, design_type, colorist, finishing_used, season, yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, rollsample_status, rollsample_datestamp, test_status, test_datestamp, customs, moq, weight, nickname, numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime);
		
		int devid = devdata.getDevID(title);
		
		temp = request.getParameterValues("LeahCommentInput");
		if (temp != null) {
			LeahComment = temp[0];
			System.out.println("Successfully retrieved comment from Leah: " + LeahComment + "\n");
		} else {
			System.out.println("Fail to retrieve comment from Leah.");
		}
		
		temp = request.getParameterValues("LeahCommentDatestamp");
		if (temp != null) {
			LeahComment_datestamp = temp[0];
			System.out.println("Successfully retrieved datestamp of Leah comment: " + LeahComment_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve datestamp of Leah comment.");
		}
		
		if (LeahComment != "" && LeahComment_datestamp != "") {
			devdata.insertComment(devid, "Leah", LeahComment_datestamp, LeahComment);
		}
		
		temp = request.getParameterValues("USCommentInput");
		if (temp != null) {
			USComment = temp[0];
			System.out.println("Successfully retrieved comment from US: " + USComment + "\n");
		} else {
			System.out.println("Fail to retrieve comment from US.");
		}
		
		temp = request.getParameterValues("USCommentDatestamp");
		if (temp != null) {
			USComment_datestamp = temp[0];
			System.out.println("Successfully retrieved datestamp of US comment: " + USComment_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve datestamp of US comment.");
		}
		
		if (USComment != "" && USComment_datestamp != "") {
			devdata.insertComment(devid, "US", USComment_datestamp, USComment);
		}
		
		temp = request.getParameterValues("MillCommentInput");
		if (temp != null) {
			MillComment = temp[0];
			System.out.println("Successfully retrieved comment from Mill: " + MillComment + "\n");
		} else {
			System.out.println("Fail to retrieve comment from Mill.");
		}
		
		temp = request.getParameterValues("MillCommentDatestamp");
		if (temp != null) {
			MillComment_datestamp = temp[0];
			System.out.println("Successfully retrieved datestamp of Mill comment: " + MillComment_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve datestamp of Mill comment.");
		}
		
		if (MillComment != "" && MillComment_datestamp != "") {
			devdata.insertComment(devid, "Mill", MillComment_datestamp, MillComment);
		}
		
		temp = request.getParameterValues("GeorgeCommentInput");
		if (temp != null) {
			GeorgeComment = temp[0];
			System.out.println("Successfully retrieved comment from George: " + GeorgeComment + "\n");
		} else {
			System.out.println("Fail to retrieve comment from George.");
		}
		
		temp = request.getParameterValues("GeorgeCommentDatestamp");
		if (temp != null) {
			GeorgeComment_datestamp = temp[0];
			System.out.println("Successfully retrieved datestamp of George comment: " + GeorgeComment_datestamp + "\n");
		} else {
			System.out.println("Fail to retrieve datestamp of George comment.");
		}
		
		if (GeorgeComment != "" && GeorgeComment_datestamp != "") {
			devdata.insertComment(devid, "George", GeorgeComment_datestamp, GeorgeComment);
		}
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		devdata.insertLog(devid, userName, DateTime, "Created New Development");
		
		request.getRequestDispatcher("/DevSuccess.jsp").forward(request, response);
	}


	private String saveFile(Part part) throws IOException {
	    if (part != null && part.getSize() > 0) {
	        String fileName = getFileName(part);
	        String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;
	        File storeFile = new File(filePath);
	        if (!storeFile.exists()) {
	        	storeFile.mkdirs();
	        }
	        part.write(storeFile.getAbsolutePath());
	        return filePath;
	    }
	    return null;
	}
	
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	        }
	    }
	    return null;
	}

}