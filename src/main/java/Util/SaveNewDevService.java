package Util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveNewDevService")
public class SaveNewDevService extends HttpServlet{
	private static final long serialVersionUID = 1L;
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
	private static String fabric_img_path;
	private static String pid_path;
	private static String test_report_path;
	
	public SaveNewDevService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
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
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve cost.");
		}
		
		temp = request.getParameterValues("ParagonCleanCB");
		if (temp != null) {
			IsParagonClean = true;
			System.out.println("IsParagonClean checked.\n");
		} else {
			System.out.println("IsParagonClean unchecked.");
		}
		
		temp = request.getParameterValues("FCLCB");
		if (temp != null) {
			Is400hrFCL = true;
			System.out.println("Is400hrFCL checked.\n");
		} else {
			System.out.println("Is400hrFCL unchecked.");
		}
		
		temp = request.getParameterValues("PDCB");
		if (temp != null) {
			IsPieceDyed = true;
			System.out.println("IsPieceDyed checked.\n");
		} else {
			System.out.println("IsPieceDyed unchecked.");
		}
		
		temp = request.getParameterValues("FeedbackCB");
		if (temp != null) {
			NeedFeedback = true;
			System.out.println("NeedFeedback checked.\n");
		} else {
			System.out.println("NeedFeedback unchecked.");
		}
		
		temp = request.getParameterValues("SDYCB");
		if (temp != null) {
			IsSDY = true;
			System.out.println("IsSDY checked.\n");
		} else {
			System.out.println("IsSDY unchecked.");
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
		
		temp = request.getParameterValues("Finishing");
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
				System.out.println("Exception: " + e);
			}
		} else {
			System.out.println("Fail to retrieve num colorline required.");
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
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}