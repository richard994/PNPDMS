package Util;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;

import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/SaveNewDevService")
@MultipartConfig
public class SaveNewDevService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String AWS_BUCKET = "databricks-workspace-stack-a24ba-bucket";
	private static String code;
	private static String color;
	private static double cost;
	private static boolean IsParagonClean = false;
	private static boolean Is400hrFCL = false;
	private static boolean IsPieceDyed = false;
	private static boolean NeedFeedback = false;
	private static boolean IsSDY = false;
	private static boolean IsChenille = false;
	private static String fabric_type;
	private static String design_type;
	private static String colorist;
	private static String finishing_used; 
	private static String season;
	private static String yarn_type;
	private static String warp_type;
	private static String content;
	private static String strike_off_status;
	private static String strike_off_birthday;
	private static String blanket_status;
	private static String colorline_status;
	private static String colorline_datestamp;
	private static String rollsample_status;
	private static String rollsample_datestamp;
	private static String test_status;
	private static String test_datestamp;
	private static double moq;
	private static double weight;
	private static int numColorline;
	private static double ppcm;
	private static String note;
	private static String fabric_img_path;
	private static String pid_path;
	private static String test_report_path;
	private static String currentPhase;
	private static String DateTime;
	private static String LastModified;
	private static String DateCurrentPhase;
	private static String LeahComment;
	private static String LeahComment_datestamp;
	private static String USComment;
	private static String USComment_datestamp;
	private static String MillComment;
	private static String MillComment_datestamp;
	private static String GeorgeComment;
	private static String GeorgeComment_datestamp;
	private static boolean IsKnit = false;
	private static String designer;
	private static String direction;
	private static boolean GeorgeCanceled = false;
	private S3Client s3Client = S3Client.builder()
            .region(Region.of("us-west-2"))
            .build();
	
	public SaveNewDevService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		String temp[];
		String action = request.getParameter("action");
		
		if ("delete".equals(action)) {
			int dev_id = Integer.parseInt(request.getParameter("devId"));
			DevData devdata = new DevData();
			Developments dev = devdata.getDevelopmentById(dev_id);
			devdata.deleteDev(dev_id);
			deleteFile(dev.getFabric_img_path());
			deleteFile(dev.getPid_path());
			deleteFile(dev.getTest_report_path());
			System.out.println("Deleted development " + dev_id + ".\n");
			request.getRequestDispatcher("/TrackerService").forward(request, response);
			return;
		}
		
		if ("duplicate".equals(action)) {
			int dev_id = Integer.parseInt(request.getParameter("devId"));
			DevData devdata = new DevData();
			Developments dev = devdata.getDevelopmentById(dev_id);
			LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTime = currentDateTime.toString();
	        LastModified = currentDateTime.toString();
	        DateCurrentPhase = currentDateTime.toString();
	        dev.setDateTime(DateTime);
	        dev.setLastModified(LastModified);
	        dev.setDateCurrentPhase(DateCurrentPhase);
	        
	        String fabric_img_path = dev.getFabric_img_path();
	        String pid_path = dev.getPid_path();
	        String test_report_path = dev.getTest_report_path();
	        if (!fabric_img_path.equals("none")) {
	        	fabric_img_path = copyFile(fabric_img_path);
	        	dev.setFabric_img_path(fabric_img_path);
	        }
	        if (!pid_path.equals("none")) {
	        	pid_path = copyFile(pid_path);
	        	dev.setPid_path(pid_path);
	        }
	        if (!test_report_path.equals("none")) {
	        	test_report_path = copyFile(test_report_path);
	        	dev.setTest_report_path(test_report_path);
	        }
	        
	        int new_id = devdata.duplicateDev(dev);
	        HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("userName");
	        devdata.insertLog(new_id, userName, DateTime, "Duplicated this development.");
	        System.out.println("Duplicate development " + dev_id + ".\n");
			request.getRequestDispatcher("/TrackerService").forward(request, response);
			return;
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
			IsParagonClean = false;
			System.out.println("IsParagonClean unchecked.\n");
		}
		
		temp = request.getParameterValues("FCLCB");
		if (temp != null) {
			Is400hrFCL = true;
			System.out.println("Is400hrFCL checked.\n");
		} else {
			Is400hrFCL = false;
			System.out.println("Is400hrFCL unchecked.\n");
		}
		
		temp = request.getParameterValues("PDCB");
		if (temp != null) {
			IsPieceDyed = true;
			System.out.println("IsPieceDyed checked.\n");
		} else {
			IsPieceDyed = false;
			System.out.println("IsPieceDyed unchecked.\n");
		}
		
		temp = request.getParameterValues("FeedbackCB");
		if (temp != null) {
			NeedFeedback = true;
			System.out.println("NeedFeedback checked.\n");
		} else {
			NeedFeedback = false;
			System.out.println("NeedFeedback unchecked.\n");
		}
		
		temp = request.getParameterValues("SDYCB");
		if (temp != null) {
			IsSDY = true;
			System.out.println("IsSDY checked.\n");
		} else {
			IsSDY = false;
			System.out.println("IsSDY unchecked.\n");
		}
		
		temp = request.getParameterValues("ChenilleCB");
		if (temp != null) {
			IsChenille = true;
			System.out.println("IsChenille checked.\n");
		} else {
			IsChenille = false;
			System.out.println("IsChenille unchecked.\n");
		}
		
		temp = request.getParameterValues("KnitCB");
		if (temp != null) {
			IsKnit = true;
			System.out.println("IsKnit checked.\n");
		} else {
			IsKnit = false;
			System.out.println("IsKnit unchecked.\n");
		}
		
		temp = request.getParameterValues("GeorgeCancelCB");
		if (temp != null) {
			GeorgeCanceled = true;
			System.out.println("GeorgeCanceled checked.\n");
		} else {
			GeorgeCanceled = false;
			System.out.println("GeorgeCanceled unchecked.\n");
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
		
		temp = request.getParameterValues("Designer");
		if (temp != null) {
			designer = temp[0];
			System.out.println("Successfully retrieved designer: " + designer + "\n");
		} else {
			System.out.println("Fail to retrieve designer.");
		}
		
		temp = request.getParameterValues("Direction");
		if (temp != null) {
			direction = temp[0];
			System.out.println("Successfully retrieved direction: " + direction + "\n");
		} else {
			System.out.println("Fail to retrieve direction.");
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
		
		temp = request.getParameterValues("StrikeBirthday");
		if (temp != null) {
			strike_off_birthday = temp[0];
			System.out.println("Successfully retrieved strike-off birthday: " + strike_off_birthday + "\n");
		} else {
			System.out.println("Fail to retrieve strike-off birthday.");
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
		
		if (test_status.equals("DNE")) {
			currentPhase = "";
		} else if (test_status.equals("Test Passed")) {
			currentPhase = "Test Passed";
		} else if (test_status.equals("Test Failed")){
			currentPhase = "Test Failed";
		} else {
			currentPhase = "Testing";
		}
		System.out.println("Current Phase: " + currentPhase + "\n");
		
		Part fabricPicPart = request.getPart("FabricPicInput");
        System.out.println("fabricPicPart: " + fabricPicPart);
        if (fabricPicPart != null && fabricPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + fabricPicPart.getSize());
        	fabric_img_path = saveFile(fabricPicPart);
        	System.out.println("FabricPic stored at: " + fabric_img_path + "\n");
        } else {
        	fabric_img_path = "none";
        	System.out.println("FabricPic not uploaded.\n");
        }
        
        Part pidPicPart = request.getPart("PidInput");
        System.out.println("pidPicPart: " + pidPicPart);
        if (pidPicPart != null && pidPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + pidPicPart.getSize());
        	pid_path = saveFile(pidPicPart);
            System.out.println("PidPic stored at: " + pid_path + "\n");
        } else {
        	pid_path = "none";
        	System.out.println("PidPic not uploaded.\n");
        }
        
        Part testReportPicPart = request.getPart("TestReportInput");
        System.out.println("testReportPicPart: " + testReportPicPart);
        if (testReportPicPart != null && testReportPicPart.getSize() > 0) {
        	System.out.println("Size of file: " + testReportPicPart.getSize());
        	test_report_path = saveFile(testReportPicPart);
            System.out.println("TestReportPic stored at: " + test_report_path + "\n");
        } else {
        	test_report_path = "none";
        	System.out.println("TestReportPic not uploaded.\n");
        }
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTime = currentDateTime.toString();
        LastModified = currentDateTime.toString();
        DateCurrentPhase = currentDateTime.toString();
        System.out.println("Current date & time: " + DateTime + "\n");
		
		DevData devdata = new DevData();
		if ("edit".equals(action)) {
			int old_dev_id = Integer.parseInt(request.getParameter("devId"));
			Developments old_development = devdata.getDevelopmentById(old_dev_id);
			DateTime = old_development.getDateTime();
			if (fabric_img_path.equals("none")) {
				fabric_img_path = old_development.getFabric_img_path();
			} else {
				deleteFile(old_development.getFabric_img_path());
			}
			if (pid_path.equals("none")) {
				pid_path = old_development.getPid_path();
			} else {
				deleteFile(old_development.getPid_path());
			}
			if (test_report_path.equals("none")) {
				test_report_path = old_development.getTest_report_path();
			} else {
				deleteFile(old_development.getTest_report_path());
			}
			String old_phase = old_development.getCurrentPhase();
			if (old_phase.equals(currentPhase)) {
				DateCurrentPhase = old_development.getDateCurrentPhase();
			}
		}
		int devid = devdata.insertDevelopment(code, color, cost, IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, IsSDY, IsChenille, fabric_type, design_type, colorist, finishing_used, season, yarn_type, warp_type, content, strike_off_status, blanket_status, colorline_status, colorline_datestamp, rollsample_status, rollsample_datestamp, test_status, test_datestamp, moq, weight, numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path, currentPhase, DateTime, LastModified, DateCurrentPhase, IsKnit, designer, direction, GeorgeCanceled, strike_off_birthday);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
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
		
		if ("edit".equals(action) && !LeahComment.equals("")) {
			Comment comment = new Comment("Leah", LeahComment_datestamp, LeahComment, 0);
			comments.add(comment);
		} else if (!LeahComment.equals("")) {
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
		if ("edit".equals(action) && !USComment.equals("")) {
			Comment comment = new Comment("US", USComment_datestamp, USComment, 0);
			comments.add(comment);
		} else if (!USComment.equals("")) {
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
		
		if ("edit".equals(action) && !MillComment.equals("")) {
			Comment comment = new Comment("Mill", MillComment_datestamp, MillComment, 0);
			comments.add(comment);
		} else if (!MillComment.equals("")) {
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
		
		if ("edit".equals(action) && !GeorgeComment.equals("")) {
			Comment comment = new Comment("George", GeorgeComment_datestamp, GeorgeComment, 0);
			comments.add(comment);
		} else if (!GeorgeComment.equals("")) {
			devdata.insertComment(devid, "George", GeorgeComment_datestamp, GeorgeComment);
		}
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		if ("edit".equals(action)) {
			System.out.println("Editing.\n");
			int old_dev_id = Integer.parseInt(request.getParameter("devId"));
			Developments old_development = devdata.getDevelopmentById(old_dev_id);
			Developments new_development = devdata.getDevelopmentById(devid);
			ArrayList<Log> logs = devdata.getLogsById(old_dev_id);
			logs.addAll(old_development.compare(new_development, userName));
			ArrayList<Log> comment_logs = getCommentLogs(devdata, comments, old_dev_id, devid, userName, DateTime);
			logs.addAll(comment_logs);
			if (!logs.isEmpty()) {
				for (Log log: logs) {
					devdata.insertLog(devid, log.getName(), log.getDatestamp(), log.getContent());
				}
				devdata.deleteDev(old_dev_id);
				System.out.println("Deleted old development " + old_dev_id + ".\n");
			} else {
				devdata.deleteDev(devid);
				System.out.println("No change, deleting new development " + devid + ".\n");
			}
			request.getRequestDispatcher("/TrackerService").forward(request, response);
		} else {
			devdata.insertLog(devid, userName, DateTime, "Created New Development");
			request.getRequestDispatcher("/DevSuccess.jsp").forward(request, response);
		}
	}


	private String saveFile(Part part) throws IOException {
		if (part != null && part.getSize() > 0) {
            String fileName = getFileName(part);
            String uniqueFileName = getUniqueFileName(fileName);  // Get unique file name for S3

            // Upload file to S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                                .bucket(AWS_BUCKET)
                                                                .key(uniqueFileName)
                                                                .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(part.getInputStream(), part.getSize()));

            System.out.println("Successfully uploaded file: " + uniqueFileName);

            // Return the S3 URL of the uploaded file
            return s3Client.utilities().getUrl(b -> b.bucket(AWS_BUCKET).key(uniqueFileName)).toString();
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
	
	private void deleteFile(String filename) {
		if (filename.equals("none")) {
			System.out.println("No file to delete.");
			return;
		}
		
		try {
			// Define source and destination for the copy
            String strippedFilename = filename.substring(filename.indexOf("com/") + "com/".length());
            // Delete file from S3
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                                                                        .bucket(AWS_BUCKET)
                                                                        .key(strippedFilename)
                                                                        .build();

            s3Client.deleteObject(deleteObjectRequest);
            System.out.println("Successfully deleted file from S3: " + strippedFilename);
        } catch (S3Exception e) {
            System.out.println("Error occurred while deleting the file from S3.");
            e.printStackTrace();
        }
	}
	
	private String copyFile(String filename) {
		try {
            // Define source and destination for the copy
            String strippedFilename = filename.substring(filename.indexOf("com/") + "com/".length());
            String newFileName = "duplicated_" + strippedFilename;

            // Copy the file within the same S3 bucket
            CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                                                                   .sourceBucket(AWS_BUCKET)
                                                                   .sourceKey(strippedFilename)
                                                                   .destinationBucket(AWS_BUCKET)
                                                                   .destinationKey(newFileName)
                                                                   .build();

            s3Client.copyObject(copyObjectRequest);

            System.out.println("Successfully copied file in S3 to: " + newFileName);
            return s3Client.utilities().getUrl(b -> b.bucket(AWS_BUCKET).key(newFileName)).toString();
        } catch (S3Exception e) {
            System.out.println("Error occurred while copying the file in S3.");
            e.printStackTrace();
        }
        return "";
	}
	
	private ArrayList<Log> getCommentLogs(DevData devdata, ArrayList<Comment> new_comments, int old_dev_id, int new_dev_id, String username, String dateTime) {
		System.out.println("Getting Added Comments.\n");
		ArrayList<Log> logs = new ArrayList<Log>();
		ArrayList<Comment> added_comments = new ArrayList<Comment>();
		ArrayList<Comment> old_comments = devdata.getCommentsById(old_dev_id);
		
		for (Comment oldComment : old_comments) {
			devdata.insertComment(new_dev_id, oldComment.getName(), oldComment.getDatestamp(), oldComment.getContent());
			System.out.println("Saving old comments from dev_id " + old_dev_id + ": " + oldComment.getName() + " " + oldComment.getDatestamp() + " " + oldComment.getContent() + ".\n");
		}
		
		// Create a set for fast look-up
        Set<Comment> oldCommentSet = new HashSet<>(old_comments);
        
        // Check for added comments (those in new_comments but not in old_comments)
        for (Comment newComment : new_comments) {
            if (!oldCommentSet.contains(newComment)) {
                added_comments.add(newComment);
            }
            devdata.insertComment(new_dev_id, newComment.getName(), newComment.getDatestamp(), newComment.getContent());
        }
        
        if (!added_comments.isEmpty()) {
        	for (Comment comment : added_comments) {
        		Log log = new Log(username, dateTime, "Added " + comment.getName() + " Comment.");
        		logs.add(log);
        	}
        }
		
		return logs;
	}
	
	// Function to generate a unique file name if the file already exists
	private String getUniqueFileName(String fileName) {
		String filename = fileName;
        int counter = 1;

        // Check if the file already exists in S3, if so, generate a new name
        while (doesObjectExist(filename)) {
            String baseName = filename.substring(0, filename.lastIndexOf("."));
            String extension = filename.substring(filename.lastIndexOf("."));

            filename = baseName + "(" + counter + ")" + extension;
            counter++;
        }

        return filename;  // Return the unique file name
	}
	
	private boolean doesObjectExist(String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                                                                   .bucket(AWS_BUCKET)
                                                                   .key(key)
                                                                   .build();
            s3Client.headObject(headObjectRequest);
            return true; // Object exists
        } catch (NoSuchKeyException e) {
            return false; // Object does not exist
        }
    }
}