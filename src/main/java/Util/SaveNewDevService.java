package Util;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private static boolean NeedChinaFeedback = false;
	private static boolean IsSDY = false;
	private static boolean IsChenille = false;
	private static boolean inactive = false;
	private static boolean priceConfirmed = false;
	private static String fabric_type;
	private static String design_type;
	private static String colorist;
	private static String finishing_used; 
	private static String season;
	private static String style;
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
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		
		if ("delete".equals(action)) {
			handleDelete(request, response);
			request.getRequestDispatcher("/TrackerService").forward(request, response);
			return;
		} else if ("duplicate".equals(action)) {
			handleDuplicate(request, response);
			request.getRequestDispatcher("/TrackerService").forward(request, response);
			return;
		} else if ("multedit".equals(action)) {
			handleMultiEdit(request, response);
			request.getRequestDispatcher("/TrackerService").forward(request, response);
			return;
		}
		
		code = parseInput("Code", request);
		color = parseInput("Color", request);
		String costStr = parseInput("Cost", request); 
		if (!costStr.isEmpty()) {
		    cost = Double.parseDouble(costStr);
		}
		IsParagonClean = !parseInput("ParagonCleanCB", request).isEmpty();
		Is400hrFCL = !parseInput("FCLCB", request).isEmpty();
		IsPieceDyed = !parseInput("PDCB", request).isEmpty();
		NeedFeedback = !parseInput("FeedbackCB", request).isEmpty();
		NeedChinaFeedback = !parseInput("ChinaFeedbackCB", request).isEmpty();
		IsSDY = !parseInput("SDYCB", request).isEmpty();
		IsChenille = !parseInput("ChenilleCB", request).isEmpty();
		IsKnit = !parseInput("KnitCB", request).isEmpty();
		GeorgeCanceled = !parseInput("GeorgeCancelCB", request).isEmpty();
		inactive = !parseInput("inactiveCB", request).isEmpty();
		priceConfirmed = !parseInput("priceConfirmCB", request).isEmpty();
		fabric_type = parseInput("FabricType", request);
		design_type = parseInput("DesignType", request);
		colorist = parseInput("Colorist", request);
		designer = parseInput("Designer", request);
		direction = parseInput("Direction", request);
		finishing_used = parseInput("Backing", request);
		season = parseInput("Season", request);
		style = parseInput("YarnType", request);
		warp_type = parseInput("WarpType", request);
		strike_off_status = parseInput("StrikeProgress", request);
		strike_off_birthday = parseInput("StrikeBirthday", request);
		blanket_status = parseInput("BlanketStatus", request);
		colorline_status = parseInput("ColorLineProgress", request);
		colorline_datestamp = parseInput("ColorlineDatestamp", request);
		rollsample_status = parseInput("RollSampleProgress", request);
		rollsample_datestamp = parseInput("RollSampleDatestamp", request);
		test_status = parseInput("TestingProgress", request);
		test_datestamp = parseInput("TestingDatestamp", request);
		String moqStr = parseInput("MOQ", request); 
		if (!moqStr.isEmpty()) {
			moq = Double.parseDouble(moqStr);
		}
		String weightStr = parseInput("Weight", request); 
		if (!weightStr.isEmpty()) {
			weight = Double.parseDouble(weightStr);
		}
		String numColorStr = parseInput("NumColorLine", request); 
		if (!numColorStr.isEmpty()) {
			numColorline = Integer.parseInt(numColorStr);
		}
		String ppcmStr = parseInput("PPCM", request); 
		if (!ppcmStr.isEmpty()) {
			ppcm = Double.parseDouble(ppcmStr);
		}
		note = parseInput("Note", request);
		LeahComment = parseInput("LeahCommentInput", request);
		LeahComment_datestamp = parseInput("LeahCommentDatestamp", request);
		USComment = parseInput("USCommentInput", request);
		USComment_datestamp = parseInput("USCommentDatestamp", request);
		MillComment = parseInput("MillCommentInput", request);
		MillComment_datestamp = parseInput("MillCommentDatestamp", request);
		GeorgeComment = parseInput("GeorgeCommentInput", request);
		GeorgeComment_datestamp = parseInput("GeorgeCommentDatestamp", request);
		
		if (test_status.equals("DNE")) {
			currentPhase = "";
		} else if (test_status.equals("Testing passed")) {
			currentPhase = "Test Passed";
		} else if (test_status.contains("failed")){
			currentPhase = "Test Failed";
		} else {
			currentPhase = "Testing";
		}
		System.out.println("Current Phase: " + currentPhase + "\n");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTime = currentDateTime.toString();
        LastModified = currentDateTime.toString();
        DateCurrentPhase = currentDateTime.toString();
        System.out.println("Current date & time: " + DateTime + "\n");
		
        fabric_img_path  = storePart(request, "FabricPicInput",  "FabricPic");
        pid_path         = storePart(request, "PidInput",        "PidPic");
        test_report_path = storePart(request, "TestReportInput", "TestReportPic");

        DevData devdata = new DevData();
        if ("edit".equals(action)) {
            int oldDevId = Integer.parseInt(request.getParameter("devId"));
            Developments oldDev = devdata.getDevelopmentById(oldDevId);

            DateTime = oldDev.getDateTime(); // preserve original create time

            fabric_img_path  = keepOrReplacePath(fabric_img_path,  oldDev.getFabric_img_path());
            pid_path         = keepOrReplacePath(pid_path,         oldDev.getPid_path());
            test_report_path = keepOrReplacePath(test_report_path, oldDev.getTest_report_path());
        }

        int devid = devdata.insertDevelopment(
                code, color, cost, IsParagonClean, Is400hrFCL, IsPieceDyed, NeedFeedback, IsSDY, IsChenille,
                fabric_type, design_type, colorist, finishing_used, season, style, warp_type, content,
                strike_off_status, blanket_status, colorline_status, colorline_datestamp,
                rollsample_status, rollsample_datestamp, test_status, test_datestamp, moq, weight,
                numColorline, ppcm, note, fabric_img_path, pid_path, test_report_path,
                currentPhase, DateTime, LastModified, DateCurrentPhase,
                IsKnit, designer, direction, GeorgeCanceled, strike_off_birthday, NeedChinaFeedback,
                inactive, priceConfirmed
        );

        ArrayList<Comment> stagedComments = new ArrayList<>();
        addOrStageComment(stagedComments, devdata, "edit".equals(action), devid, "Leah",   LeahComment_datestamp,   LeahComment);
        addOrStageComment(stagedComments, devdata, "edit".equals(action), devid, "US",     USComment_datestamp,     USComment);
        addOrStageComment(stagedComments, devdata, "edit".equals(action), devid, "Mill",   MillComment_datestamp,   MillComment);
        addOrStageComment(stagedComments, devdata, "edit".equals(action), devid, "George", GeorgeComment_datestamp, GeorgeComment);

        if ("edit".equals(action)) {
            processEditReconciliation(request, response, session, devdata, devid, stagedComments, userName, DateTime);
        } else {
            devdata.insertLog(devid, userName, DateTime, "Created New Development");
            request.getRequestDispatcher("/DevSuccess.jsp").forward(request, response);
        }
	}
	
	private String parseInput(String name, HttpServletRequest request) {
		String temp[] = request.getParameterValues(name);
		if (temp != null) {
			System.out.println("Successfully retrieved " + name + ": " + temp[0] + "\n");
			return temp[0];
		}
		System.out.println("Fail to retrieve " + name + ".\n");
		return "";
	}
	
	private void handleDelete(HttpServletRequest request, HttpServletResponse response) {
		int dev_id = Integer.parseInt(request.getParameter("devId"));
		DevData devdata = new DevData();
		Developments dev = devdata.getDevelopmentById(dev_id);
		devdata.deleteDev(dev_id);
		deleteFile(dev.getFabric_img_path());
		deleteFile(dev.getPid_path());
		deleteFile(dev.getTest_report_path());
		System.out.println("Deleted development " + dev_id + ".\n");
	}
	
	private void handleDuplicate(HttpServletRequest request, HttpServletResponse response) {
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
	}

	private void handleMultiEdit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Multi Editing.");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String devIds = request.getParameter("devIds");
		DevData devdata = new DevData();
		
		String[] devIdArray = devIds.split(";");
		for (String code : devIdArray) {
		    System.out.println("Editing " + code);
		    Developments dev = devdata.getDevelopmentByCode(code);
		    
		    color = parseInput("Color-" + code, request);
		    updateStringIfChanged(devdata, "color", color, dev.getColor(), "code", code, "Color-" + code);
		    GeorgeCanceled = !parseInput("GeorgeCancelCB-" + code, request).isEmpty();
		    updateBooleanIfChanged(devdata, "GeorgeCanceled", GeorgeCanceled, dev.isGeorgeCanceled(), "code", code, "GeorgeCancelCB-" + code);	    
			inactive = !parseInput("inactiveCB-" + code, request).isEmpty();
			updateBooleanIfChanged(devdata, "inactive", inactive, dev.isInactive(), "code", code, "inactiveCB-" + code);
			priceConfirmed = !parseInput("priceConfirmCB-" + code, request).isEmpty();
			updateBooleanIfChanged(devdata, "priceConfirmed", priceConfirmed, dev.isPriceConfirmed(), "code", code, "priceConfirmCB-" + code);
			design_type = parseInput("DesignType-" + code, request);
			updateStringIfChanged(devdata, "design_type", design_type, dev.getDesign_type(), "code", code, "DesignType-" + code);
			colorist = parseInput("Colorist-" + code, request);
			updateStringIfChanged(devdata, "colorist", colorist, dev.getColorist(), "code", code, "Colorist-" + code);
			designer = parseInput("Designer-" + code, request);
			updateStringIfChanged(devdata, "designer", designer, dev.getDesigner(), "code", code, "Designer-" + code);
			season = parseInput("Season-" + code, request);
			updateStringIfChanged(devdata, "season", season, dev.getSeason(), "code", code, "Season-" + code);
			style = parseInput("YarnType-" + code, request);
			updateStringIfChanged(devdata, "yarn_type", style, dev.getYarn_type(), "code", code, "YarnType-" + code);
			warp_type = parseInput("WarpType-" + code, request);
			updateStringIfChanged(devdata, "warp_type", warp_type, dev.getWarp_type(), "code", code, "WarpType-" + code);
			direction = parseInput("Direction-" + code, request);
			updateStringIfChanged(devdata, "direction", direction, dev.getDirection(), "code", code, "Direction-" + code);
			strike_off_status = parseInput("StrikeProgress-" + code, request);
			updateStringIfChanged(devdata, "strike_off_status", strike_off_status, dev.getStrike_off_status(), "code", code, "StrikeProgress-" + code);
			strike_off_birthday = parseInput("StrikeBirthday-" + code, request);
			updateStringIfChanged(devdata, "strike_off_birthday", strike_off_birthday, dev.getStrike_off_birthday(), "code", code, "StrikeBirthday-" + code);
			blanket_status = parseInput("BlanketStatus-" + code, request);
			updateStringIfChanged(devdata, "blanket_status", blanket_status, dev.getBlanket_status(), "code", code, "BlanketStatus-" + code);
			test_status = parseInput("TestingProgress-" + code, request);
			updateStringIfChanged(devdata, "test_status", test_status, dev.getTest_status(), "code", code, "TestingProgress-" + code);
			test_datestamp = parseInput("TestingDatestamp-" + code, request);
			updateStringIfChanged(devdata, "test_datestamp", test_datestamp, dev.getTest_datestamp(), "code", code, "TestingDatestamp-" + code);
			
			Developments new_dev = devdata.getDevelopmentByCode(code);
			ArrayList<Log> logs = new ArrayList<Log>();
			logs.addAll(dev.compare(new_dev, userName));
			if (!logs.isEmpty()) {
				for (Log log: logs) {
					devdata.insertLog(dev.getDev_id(), log.getName(), log.getDatestamp(), log.getContent());
				}
				System.out.println("Logs added to development " + code + ".\n");
				String DateCurrPhase = dev.checkPhase(new_dev);
				if (!DateCurrPhase.equals("")) {
					devdata.updateDevTableString("DateCurrentPhase", DateCurrPhase, "development_id", dev.getDev_id());
				}
			} 
			System.out.println("Successfully editted development " + code + ".\n");
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
		if (filename.equals("none") || filename.isEmpty()) {
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
	
	private String storePart(HttpServletRequest request, String inputName, String label)
	        throws ServletException, IOException {
	    Part part = request.getPart(inputName);
	    System.out.println(label + " part: " + part);

	    if (part != null && part.getSize() > 0) {
	        System.out.println(label + " size: " + part.getSize());
	        String path = saveFile(part); 
	        System.out.println(label + " stored at: " + path);
	        return path;
	    } else {
	        System.out.println(label + " not uploaded.");
	        return "none";
	    }
	}
	
	private void addOrStageComment(List<Comment> staged,
            DevData devdata,
            boolean isEdit,
            int devId,
            String author,
            String datestamp,
            String content) {
		if (content == null || content.isEmpty()) return;
		
		if (isEdit) {
			staged.add(new Comment(author, datestamp, content, 0));
		} else {
			devdata.insertComment(devId, author, datestamp, content);
		}
	}
	
	private void processEditReconciliation(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            DevData devdata,
            int newDevId,
            List<Comment> stagedComments,
            String userName,
            String dateTime)
            					throws ServletException, IOException {
	
		System.out.println("Editing.\n");
		
		int oldDevId = Integer.parseInt(String.valueOf(request.getParameter("devId")));
		Developments oldDev = devdata.getDevelopmentById(oldDevId);
		Developments newDev = devdata.getDevelopmentById(newDevId);
		
		ArrayList<Log> logs = devdata.getLogsById(oldDevId);
		logs.addAll(oldDev.compare(newDev, userName));
		
		// Convert staged comments to logs (existing helper assumed)
		ArrayList<Log> commentLogs = getCommentLogs(devdata, new ArrayList<>(stagedComments),
		                     oldDevId, newDevId, userName, dateTime);
		logs.addAll(commentLogs);
		
		if (!logs.isEmpty()) {
			// Persist logs on the new dev
			for (Log log : logs) {
				devdata.insertLog(newDevId, log.getName(), log.getDatestamp(), log.getContent());
			}
			
			// Delete old record, maybe adjust DateCurrentPhase
			devdata.deleteDev(oldDevId);
			System.out.println("Deleted old development " + oldDevId + ".\n");
			
			String dateCurrPhase = oldDev.checkPhase(newDev);
			if (!dateCurrPhase.isEmpty()) {
				devdata.updateDevTableString("DateCurrentPhase", dateCurrPhase, "development_id", newDevId);
			}
			
			// Keep paginated filtered list state if present
			if (!updateFilteredListAndForward(request, response, session, devdata, oldDevId, newDevId)) {
				request.getRequestDispatcher("/TrackerService").forward(request, response);
			}
		} else {
			// Nothing changed: drop the new record
			devdata.deleteDev(newDevId);
			System.out.println("No change, deleting new development " + newDevId + ".\n");
			
			// Fall back to existing navigation
			if (session.getAttribute("filteredList") != null) {
				updateFilteredListAndForward(request, response, session, devdata, oldDevId, -1);
			} else {
				request.getRequestDispatcher("/TrackerService").forward(request, response);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean updateFilteredListAndForward(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            DevData devdata,
            int oldDevId,
            int newDevId)
            							throws ServletException, IOException {
		
		ArrayList<Developments> filtered = (ArrayList<Developments>) session.getAttribute("filteredList");
		if (filtered == null) return false;
		
		if (newDevId > 0) {
			filtered.removeIf(d -> d.getDev_id() == oldDevId);
			filtered.add(devdata.getDevelopmentById(newDevId));
			session.setAttribute("filteredList", filtered);
		}
		
		int currentPage = 1;
		Object pageAttr = session.getAttribute("filteredPageNumber");
		if (pageAttr != null) {
			try {
				currentPage = Integer.parseInt(String.valueOf(pageAttr));
			} catch (NumberFormatException ignored) {}
		}
		
		final int itemsPerPage = 15;
		int totalItems = filtered.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
		
		int startIndex = Math.max(0, (currentPage - 1) * itemsPerPage);
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
		
		List<Developments> currentPageList = filtered.subList(startIndex, endIndex);
		request.setAttribute("currentPageList", currentPageList);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("filtered", true);
		request.setAttribute("sorted", false);
		
		request.getRequestDispatcher("/tracker.jsp").forward(request, response);
		return true;
	}
	
	private String keepOrReplacePath(String newPath, String oldPath) {
	    if ("none".equals(newPath)) {
	        return oldPath;
	    }
	    deleteFile(oldPath);
	    return newPath;
	}
	
	private void updateStringIfChanged(DevData devdata,
            String column,
            String newVal,
            String oldVal,
            String keyCol,
            String keyVal,
            String label) {
		if (!java.util.Objects.equals(newVal, oldVal)) {
			devdata.updateDevTableString(column, newVal, keyCol, keyVal);
			System.out.println("Successfully edited " + label + ": " + newVal + "\n");
		}
	}
		
	private void updateBooleanIfChanged(DevData devdata,
             String column,
             boolean newVal,
             boolean oldVal,
             String keyCol,
             String keyVal,
             String label) {
		if (newVal != oldVal) {
			devdata.updateDevTableBoolean(column, newVal, keyCol, keyVal);
			System.out.println("Successfully edited " + label + ": " + newVal + "\n");
		}
	}
}