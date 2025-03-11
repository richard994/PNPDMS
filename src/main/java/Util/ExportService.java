package Util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
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
	private static boolean needChenille;
	private static boolean needKnit;
	private static boolean needGeorgeCanceled;
	private static String season;
	private static String design_type;
	private static String warp_type;
	private static String yarn_type;
	private static String colorist;
	private static String fabric_type;
	private static String designer;
	private static String direction;
	private static String content;
	private static String strikeoff;
	private static String blanket;
	private static String colorline;
	private static String rollsample;
	private static String test;
	
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
		
		temp = request.getParameterValues("ModalChenilleCB");
		if (temp != null) {
			needChenille = true;
			System.out.println("ChenilleCB checked.\n");
		} else {
			needChenille = false;
			System.out.println("ChenilleCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalKnitCB");
		if (temp != null) {
			needKnit = true;
			System.out.println("KnitCB checked.\n");
		} else {
			needKnit = false;
			System.out.println("KnitCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalGeorgeCancelCB");
		if (temp != null) {
			needGeorgeCanceled = true;
			System.out.println("GeorgeCancelCB checked.\n");
		} else {
			needGeorgeCanceled = false;
			System.out.println("GeorgeCancelCB unchecked.\n");
		}
		
		temp = request.getParameterValues("ModalSeason");
		if (temp != null) {
			season = temp[0];
			System.out.println("Successfully retrieved ModalSeason: " + season + "\n");
		} else {
			System.out.println("Fail to retrieve ModalSeason.");
		}
		
		temp = request.getParameterValues("ModalDesignType");
		if (temp != null) {
			design_type = temp[0];
			System.out.println("Successfully retrieved ModalDesignType: " + design_type + "\n");
		} else {
			System.out.println("Fail to retrieve ModalDesignType.");
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
		
		temp = request.getParameterValues("ModalFabricType");
		if (temp != null) {
			fabric_type = temp[0];
			System.out.println("Successfully retrieved FabricType: " + fabric_type + "\n");
		} else {
			System.out.println("Fail to retrieve FabricType.");
		}
		

		temp = request.getParameterValues("ModalDesigner");
		if (temp != null) {
			designer = temp[0];
			System.out.println("Successfully retrieved Designer: " + designer + "\n");
		} else {
			System.out.println("Fail to retrieve Designer.");
		}
		
		temp = request.getParameterValues("ModalDirection");
		if (temp != null) {
			direction = temp[0];
			System.out.println("Successfully retrieved Direction: " + direction + "\n");
		} else {
			System.out.println("Fail to retrieve Direction.");
		}
		
		temp = request.getParameterValues("ModalContent");
		if (temp != null) {
			content = temp[0];
			System.out.println("Successfully retrieved content: " + content + "\n");
		} else {
			System.out.println("Fail to retrieve content.");
		}
		
		temp = request.getParameterValues("ModalStrikeProgress");
		if (temp != null) {
			strikeoff = temp[0];
			System.out.println("Successfully retrieved StrikeProgress: " + strikeoff + "\n");
		} else {
			System.out.println("Fail to retrieve StrikeProgress.");
		}
		
		temp = request.getParameterValues("ModalBlanketStatus");
		if (temp != null) {
			blanket = temp[0];
			System.out.println("Successfully retrieved BlanketStatus: " + blanket + "\n");
		} else {
			System.out.println("Fail to retrieve BlanketStatus.");
		}
		
		temp = request.getParameterValues("ModalColorLineProgress");
		if (temp != null) {
			colorline = temp[0];
			System.out.println("Successfully retrieved ColorLineProgress: " + colorline + "\n");
		} else {
			System.out.println("Fail to retrieve ColorLineProgress.");
		}
		
		temp = request.getParameterValues("ModalRollSampleProgress");
		if (temp != null) {
			rollsample = temp[0];
			System.out.println("Successfully retrieved RollSampleProgress: " + rollsample + "\n");
		} else {
			System.out.println("Fail to retrieve RollSampleProgress.");
		}
		
		temp = request.getParameterValues("ModalTestingProgress");
		if (temp != null) {
			test = temp[0];
			System.out.println("Successfully retrieved TestingProgress: " + test + "\n");
		} else {
			System.out.println("Fail to retrieve TestingProgress.");
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
        String[] columnNames = {"Title", "Color", "Cost", "Paragon Clean", "400 hour FCL", "Piece Dyed", "Need Feedback", "SDY", "Chenille", "Knit", "George Canceled", "Fabric Type", "Design Type", "Colorist", "Designer", "Direction", "Finishing", "Season", "Yarn Type", "Warp Type", "Content", "Strike-off Status", "Blanket Status", "Colorline Status", "Colorline Est. Date", "Roll Sample Status", "Roll Sample Est. Date", "Test Status", "Test Est. Date", "MOQ", "Weight", "# of Colorline requested", "PPCM", "Note", "Fabric Image", "PID image", "Test Report Image", "Creation Date", "Last Modified Date", "Current Phase Started Date"};
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
        		row.createCell(9).setCellValue(dev.isKnit());
        		row.createCell(10).setCellValue(dev.isGeorgeCanceled());
        		row.createCell(11).setCellValue(dev.getFabric_type());
        		row.createCell(12).setCellValue(dev.getDesign_type());
        		row.createCell(13).setCellValue(dev.getColorist());
        		row.createCell(14).setCellValue(dev.getDesigner());
        		row.createCell(15).setCellValue(dev.getDirection());
        		row.createCell(16).setCellValue(dev.getFinishing_used());
        		row.createCell(17).setCellValue(dev.getSeason());
        		row.createCell(18).setCellValue(dev.getYarn_type());
        		row.createCell(19).setCellValue(dev.getWarp_type());
        		row.createCell(20).setCellValue(dev.getContent());
        		row.createCell(21).setCellValue(dev.getStrike_off_status());
        		row.createCell(22).setCellValue(dev.getBlanket_status());
        		row.createCell(23).setCellValue(dev.getColorline_status());
        		row.createCell(24).setCellValue(dev.getColorline_datestamp());
        		row.createCell(25).setCellValue(dev.getRollsample_status());
        		row.createCell(26).setCellValue(dev.getRollsample_datestamp());
        		row.createCell(27).setCellValue(dev.getTest_status());
        		row.createCell(28).setCellValue(dev.getTest_datestamp());
        		row.createCell(29).setCellValue(dev.getMoq());
        		row.createCell(30).setCellValue(dev.getWeight());
        		row.createCell(31).setCellValue(dev.getNumColorline());
        		row.createCell(32).setCellValue(dev.getPpcm());
        		row.createCell(33).setCellValue(dev.getNote());
        		 // Insert images into specified cells
                insertImageFromUrl(dev.getFabric_img_path(), sheet, row, 34); 
                insertImageFromUrl(dev.getPid_path(), sheet, row, 35);  
                insertImageFromUrl(dev.getTest_report_path(), sheet, row, 36); 
        		row.createCell(37).setCellValue(dev.getDateTime());
        		row.createCell(38).setCellValue(dev.getLastModified());
        		row.createCell(39).setCellValue(dev.getDateCurrentPhase());
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
        
        if (needChenille && !development.isChenille()) {
            return false;
        }
        
        if (needKnit && !development.isKnit()) {
            return false;
        }
        
        if (needGeorgeCanceled && !development.isGeorgeCanceled()) {
            return false;
        }
        
        if (!"".equals(season) && !season.equals(development.getSeason())) {
        	return false;
        }
        
        if (!"".equals(design_type) && !design_type.equals(development.getDesign_type())) {
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
        
        if (!"".equals(fabric_type) && !fabric_type.equals(development.getFabric_type())) {
        	return false;
        }
        
        if (!"".equals(designer) && !designer.equals(development.getDesigner())) {
        	return false;
        }
        
        if (!"".equals(direction) && !direction.equals(development.getDirection())) {
        	return false;
        }
        
        if (!"".equals(content) && !content.equals(development.getContent())) {
        	return false;
        }
        
        if (!"".equals(strikeoff) && !"DNE".equals(strikeoff) && !strikeoff.equals(development.getStrike_off_status())) {
        	return false;
        }
        
        if (!"".equals(blanket) && !"DNE".equals(blanket) && !blanket.equals(development.getBlanket_status())) {
        	return false;
        }
        
        if (!"".equals(colorline) && !"DNE".equals(colorline) && !colorline.equals(development.getColorline_status())) {
        	return false;
        }
        
        if (!"".equals(rollsample) && !"DNE".equals(rollsample) && !rollsample.equals(development.getRollsample_status())) {
        	return false;
        }
        
        if (!"".equals(test) && !"DNE".equals(test) && !test.equals(development.getTest_status())) {
        	return false;
        }
        
        return true;
    }
    
    private static void insertImageFromUrl(String imageUrl, Sheet sheet, Row row, int colIndex) {
    	if (imageUrl.equals("none")) {
    		row.createCell(colIndex).setCellValue("none");
    		return;
    	}
    	
        try (InputStream imageStream = new URL(imageUrl).openStream()) {
            BufferedImage bufferedImage = ImageIO.read(imageStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            Workbook workbook = sheet.getWorkbook();
            int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);

            CreationHelper helper = workbook.getCreationHelper();
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(colIndex);
            anchor.setRow1(row.getRowNum());

            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize(0.2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}