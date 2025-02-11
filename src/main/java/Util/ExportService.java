package Util;

import java.io.*;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExportService")
public class ExportService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static boolean needParagonClean;
	private static boolean need400hrFCL;
	private static boolean needPieceDyed;
	private static boolean needFeedback;
	private static boolean needSDY;
	private static boolean needStrikeOff;
	private static boolean needBlanket;
	private static boolean needRollSample;
	private static boolean needTesting;
	private static String season;
	private static String warp_type;
	private static String yarn_type;
	private static String colorist;
	private static ArrayList<String> selectedPhases = new ArrayList<String>();
	
	public ExportService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		String temp[];
		
		temp = request.getParameterValues("ModalFeedbackCB");
		if (temp != null) {
			needFeedback = true;
			System.out.println("ModalFeedbackCB checked.\n");
		} else {
			needFeedback = false;
			System.out.println("ModalFeedbackCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalParagonCleanCB");
		if (temp != null) {
			needParagonClean = true;
			System.out.println("ModalParagonCleanCB checked.\n");
		} else {
			needParagonClean = false;
			System.out.println("ModalParagonCleanCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalFCLCB");
		if (temp != null) {
			need400hrFCL = true;
			System.out.println("ModalFCLCB checked.\n");
		} else {
			need400hrFCL = false;
			System.out.println("ModalFCLCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalPDCB");
		if (temp != null) {
			needPieceDyed = true;
			System.out.println("ModalPDCB checked.\n");
		} else {
			needPieceDyed = false;
			System.out.println("ModalPDCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalSDYCB");
		if (temp != null) {
			needSDY = true;
			System.out.println("ModalSDYCB checked.\n");
		} else {
			needSDY = false;
			System.out.println("ModalSDYCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalStrikeCB");
		if (temp != null) {
			needStrikeOff = true;
			selectedPhases.add("Strike-off");
			System.out.println("ModalStrikeCB checked.\n");
		} else {
			needStrikeOff = false;
			System.out.println("ModalStrikeCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalBlanketCB");
		if (temp != null) {
			needBlanket = true;
			selectedPhases.add("Blanket");
			System.out.println("ModalBlanketCB checked.\n");
		} else {
			needBlanket = false;
			System.out.println("ModalBlanketCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalRollSampleCB");
		if (temp != null) {
			needRollSample = true;
			selectedPhases.add("Roll Sample");
			System.out.println("ModalRollSampleCB checked.\n");
		} else {
			needRollSample = false;
			System.out.println("ModalRollSampleCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalTestingCB");
		if (temp != null) {
			needTesting = true;
			selectedPhases.add("Testing");
			selectedPhases.add("Testing Passed");
			System.out.println("ModalTestingCB checked.\n");
		} else {
			needTesting = false;
			System.out.println("ModalTestingCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalSeason");
		if (temp != null) {
			season = temp[0];
			System.out.println("Successfully retrieved ModalSeason: " + season + "\n");
		} else {
			System.out.println("Fail to retrieve ModalSeason.");
		}
		
		temp = request.getParameterValues("ModalWarpType");
		if (temp != null) {
			warp_type = temp[0];
			System.out.println("Successfully retrieved ModalWarpType: " + warp_type + "\n");
		} else {
			System.out.println("Fail to retrieve ModalWarpType.");
		}
		
		temp = request.getParameterValues("ModalYarnType");
		if (temp != null) {
			yarn_type = temp[0];
			System.out.println("Successfully retrieved ModalYarnType: " + yarn_type + "\n");
		} else {
			System.out.println("Fail to retrieve ModalYarnType.");
		}
		
		temp = request.getParameterValues("ModalColorist");
		if (temp != null) {
			colorist = temp[0];
			System.out.println("Successfully retrieved ModalColorist: " + colorist + "\n");
		} else {
			System.out.println("Fail to retrieve ModalColorist.");
		}
		
		
		DevData devdata = new DevData();
		ArrayList<Developments> developments = devdata.getDevelopments();
		String filename = "developments.xlsx";
		
		// Set response headers to indicate file download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        
        // Export Excel data and write it to the response output stream
        try (OutputStream out = response.getOutputStream()) {
            exportToExcel(developments, out); 
        } catch (IOException e) {
        	System.out.println("Failed to write to excel.\n");
        	e.getStackTrace();
        }
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    private void exportToExcel(ArrayList<Developments> developments, OutputStream out) throws IOException {
    	Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Developments");
        Row header = sheet.createRow(0);
        String[] columnNames = {"Title", "Fabric Code", "Color", "Cost", "Paragon Clean", "400 hour FCL", "Piece Dyed", "Need Feedback", "SDY", "Fabric Type", "Design Type", "Colorist", "Backing", "Season", "Yarn Type", "Warp Type", "Content", "Strike-off Status", "Blanket Status", "Colorline Status", "Colorline Est. Date", "Roll Sample Status", "Roll Sample Est. Date", "Test Status", "Test Est. Date", "Customs Category", "MOQ", "Weight", "Nickname", "# of Colorline requested", "PPCM", "Note", "Fabric Image", "PID image", "Test Report Image", "Creation Date", "Last Modified Date", "Current Phase Started Date"};
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnNames[i]);
        }
        
        int numRows = 1;
        for (Developments dev : developments) {
        	if (filter(dev)) {
        		Row row = sheet.createRow(numRows);
        		row.createCell(0).setCellValue(dev.getCode());
        		row.createCell(1).setCellValue(dev.getColor());
        		row.createCell(2).setCellValue(dev.getCost());
        		row.createCell(3).setCellValue(dev.isParagonClean());
        		row.createCell(4).setCellValue(dev.isIs400hrFCL());
        		row.createCell(5).setCellValue(dev.isPieceDyed());
        		row.createCell(6).setCellValue(dev.isNeedFeedback());
        		row.createCell(7).setCellValue(dev.isSDY());
        		row.createCell(8).setCellValue(dev.isChenille());
        		row.createCell(9).setCellValue(dev.getFabric_type());
        		row.createCell(10).setCellValue(dev.getDesign_type());
        		row.createCell(11).setCellValue(dev.getColorist());
        		row.createCell(12).setCellValue(dev.getFinishing_used());
        		row.createCell(13).setCellValue(dev.getSeason());
        		row.createCell(14).setCellValue(dev.getYarn_type());
        		row.createCell(15).setCellValue(dev.getWarp_type());
        		row.createCell(16).setCellValue(dev.getContent());
        		row.createCell(17).setCellValue(dev.getStrike_off_status());
        		row.createCell(18).setCellValue(dev.getBlanket_status());
        		row.createCell(19).setCellValue(dev.getColorline_status());
        		row.createCell(20).setCellValue(dev.getColorline_datestamp());
        		row.createCell(21).setCellValue(dev.getRollsample_status());
        		row.createCell(22).setCellValue(dev.getRollsample_datestamp());
        		row.createCell(23).setCellValue(dev.getTest_status());
        		row.createCell(24).setCellValue(dev.getTest_datestamp());
        		row.createCell(25).setCellValue(dev.getMoq());
        		row.createCell(26).setCellValue(dev.getWeight());
        		row.createCell(27).setCellValue(dev.getNumColorline());
        		row.createCell(28).setCellValue(dev.getPpcm());
        		row.createCell(29).setCellValue(dev.getNote());
        		String fabric_img_path = dev.getFabric_img_path();
        		String pid_path = dev.getPid_path();
        		String test_report_path = dev.getTest_report_path();
        		if (fabric_img_path.equals("none")) {
        			row.createCell(30).setCellValue(fabric_img_path);
        		} else {
        			try {
            			String filePath = getServletContext().getRealPath("") + File.separator + fabric_img_path;
            			LoadImage(workbook, sheet, filePath, numRows, 32);
            		} catch (IOException e) {
            			System.out.println("Failed to load Fabric Image.\n");
            			e.printStackTrace();
            		}
        		}
        		if (pid_path.equals("none")) {
        			row.createCell(31).setCellValue(pid_path);
        		} else {
        			try {
            			String filePath = getServletContext().getRealPath("") + File.separator + pid_path;
            			LoadImage(workbook, sheet, filePath, numRows, 33);
            		} catch (IOException e) {
            			System.out.println("Failed to load PID Image.\n");
            			e.printStackTrace();
            		}
        		}
        		if (test_report_path.equals("none")) {
        			row.createCell(32).setCellValue(test_report_path);
        		} else {
        			try {
            			String filePath = getServletContext().getRealPath("") + File.separator + test_report_path;
            			LoadImage(workbook, sheet, filePath, numRows, 34);
            		} catch (IOException e) {
            			System.out.println("Failed to load Test Report Image.\n");
            			e.printStackTrace();
            		}
        		}
        		row.createCell(33).setCellValue(dev.getDateTime());
        		row.createCell(34).setCellValue(dev.getLastModified());
        		row.createCell(35).setCellValue(dev.getDateCurrentPhase());
        		numRows++;
        	}
        }
        
        // Auto-size all columns based on their content
        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the data to an Excel file
        workbook.write(out);
        workbook.close();
    }
    
    private boolean filter(Developments development) {
    	if (needParagonClean && !development.isParagonClean()) {
            return false;
        }
    	
        if (need400hrFCL && !development.isIs400hrFCL()) {
            return false;
        }
        
        if (needPieceDyed && !development.isPieceDyed()) {
            return false;
        }
        
        if (needFeedback && !development.isNeedFeedback()) {
            return false;
        }
        
        if (needSDY && !development.isSDY()) {
            return false;
        }
        
        String currentPhase = development.getCurrentPhase();
        if (needStrikeOff && !selectedPhases.contains(currentPhase)) {
        	return false;
        }
        
        if (needBlanket && !selectedPhases.contains(currentPhase)) {
        	return false;
        }
        
        if (needRollSample && !selectedPhases.contains(currentPhase)) {
        	return false;
        }
        
        if (needTesting && !selectedPhases.contains(currentPhase)) {
        	return false;
        }
        
        if (!"".equals(season) && !season.equals(development.getSeason())) {
        	return false;
        }
        
        if (!"".equals(warp_type) && !warp_type.equals(development.getWarp_type())) {
        	return false;
        }
        
        if (!"".equals(yarn_type) && !yarn_type.equals(development.getYarn_type())) {
        	return false;
        }
        
        if (!"".equals(colorist) && !colorist.equals(development.getColorist())) {
        	return false;
        }
        
        return true;
    }
    
    private void LoadImage(Workbook workbook, Sheet sheet, String imagePath, int row, int col) throws IOException {
    	double imageWidthInPixels = 60;
    	double imageHeightInPixels = 25;
    	double cellWidthInPixels = 100;
    	double cellHeightInPixels = 50;
    	
    	// Load image as byte array
        FileInputStream imageStream = new FileInputStream(imagePath);
        byte[] imageBytes = IOUtils.toByteArray(imageStream);
        int pictureIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
        imageStream.close();

        // Create an image in the sheet
        CreationHelper helper = workbook.getCreationHelper();
        Drawing<?> drawing = sheet.createDrawingPatriarch();

        // Set image position and size
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(col);  
        anchor.setRow1(row); 
        
        // Create the picture at the anchor position
        Picture picture = drawing.createPicture(anchor, pictureIndex);
        double width_scale = imageWidthInPixels / picture.getImageDimension().getWidth();
        double height_scale = imageHeightInPixels / picture.getImageDimension().getHeight();
        
        // Resize the image
        picture.resize(width_scale, height_scale);

        // Adjust column width to fit the image
        sheet.setColumnWidth(col, (int)(cellWidthInPixels * 37));  // Approximate pixel-to-character ratio for Excel columns

        // Optionally, adjust row height to fit the image
        sheet.getRow(row).setHeightInPoints((float) (cellHeightInPixels * 0.75));
    }
}