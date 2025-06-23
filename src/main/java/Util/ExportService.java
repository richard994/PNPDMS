package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExportService")
public class ExportService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ExportService() {}
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		boolean filtered = Boolean.parseBoolean(request.getParameter("filtered"));
		
		DevData devdata = new DevData();
		ArrayList<Developments> developments = devdata.getDevelopments();
		developments.sort(Comparator.comparing(Developments::getCode));
		if (filtered) {
			developments = (ArrayList<Developments>) session.getAttribute("filteredList");
			developments.sort(Comparator.comparing(Developments::getCode));
		}
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
        String[] columnNames = {"Code", "Pattern", "Cost", "Paragon Clean", "400 hour FCL", "Piece Dyed", "Need US Feedback", "Need China Feedback", "SDY", "Chenille", "Knit", "George Canceled", "Inactive", "Fabric Type", "Design Type", "Colorist", "Designer", "Direction", "Finishing", "Season", "Style", "Warp Type", "Content", "Strike-off Status", "Strike-off Birthday", "Blanket Status", "Colorline Status", "Colorline Est. Date", "Roll Sample Status", "Roll Sample Est. Date", "Test Status", "Test Est. Date", "MOQ", "Weight", "# of Colorline requested", "PPCM", "Note", "Creation Date", "Last Modified Date", "Current Phase Started Date", "Leah Comments", "US Comments", "Mill Comments", "George Comments"};
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnNames[i]);
        }
        
        DevData devdata = new DevData();
        int numRows = 1;
        for (Developments dev : developments) {
    		Row row = sheet.createRow(numRows);
    		row.createCell(0).setCellValue(dev.getCode());
    		row.createCell(1).setCellValue(dev.getColor());
    		row.createCell(2).setCellValue(dev.getCost());
    		row.createCell(3).setCellValue(dev.isParagonClean() ? "yes" : "no");
    		row.createCell(4).setCellValue(dev.isIs400hrFCL() ? "yes" : "no");
    		row.createCell(5).setCellValue(dev.isPieceDyed() ? "yes" : "no");
    		row.createCell(6).setCellValue(dev.isNeedFeedback() ? "yes" : "no");
    		row.createCell(7).setCellValue(dev.isNeedChinaFeedback() ? "yes" : "no");
    		row.createCell(8).setCellValue(dev.isSDY() ? "yes" : "no");
    		row.createCell(9).setCellValue(dev.isChenille() ? "yes" : "no");
    		row.createCell(10).setCellValue(dev.isKnit() ? "yes" : "no");
    		row.createCell(11).setCellValue(dev.isGeorgeCanceled() ? "yes" : "no");
    		row.createCell(12).setCellValue(dev.isInactive() ? "yes" : "no");
    		row.createCell(13).setCellValue(dev.getFabric_type());
    		row.createCell(14).setCellValue(dev.getDesign_type());
    		row.createCell(15).setCellValue(dev.getColorist());
    		row.createCell(16).setCellValue(dev.getDesigner());
    		row.createCell(17).setCellValue(dev.getDirection());
    		row.createCell(18).setCellValue(dev.getFinishing_used());
    		row.createCell(19).setCellValue(dev.getSeason());
    		row.createCell(20).setCellValue(dev.getYarn_type());
    		row.createCell(21).setCellValue(dev.getWarp_type());
    		row.createCell(22).setCellValue(dev.getContent());
    		row.createCell(23).setCellValue(dev.getStrike_off_status());
    		row.createCell(24).setCellValue(dev.getStrike_off_birthday());
    		row.createCell(25).setCellValue(dev.getBlanket_status());
    		row.createCell(26).setCellValue(dev.getColorline_status());
    		row.createCell(27).setCellValue(dev.getColorline_datestamp());
    		row.createCell(28).setCellValue(dev.getRollsample_status());
    		row.createCell(29).setCellValue(dev.getRollsample_datestamp());
    		row.createCell(30).setCellValue(dev.getTest_status());
    		row.createCell(31).setCellValue(dev.getTest_datestamp());
    		row.createCell(32).setCellValue(dev.getMoq());
    		row.createCell(33).setCellValue(dev.getWeight());
    		row.createCell(34).setCellValue(dev.getNumColorline());
    		row.createCell(35).setCellValue(dev.getPpcm());
    		row.createCell(36).setCellValue(dev.getNote());
    		row.createCell(37).setCellValue(dev.getDateTime().substring(0, 10));
    		row.createCell(38).setCellValue(dev.getLastModified().substring(0, 10));
    		row.createCell(39).setCellValue(dev.getDateCurrentPhase().substring(0, 10));
    		
    		//parse comments
    		ArrayList<Comment> comments = devdata.getCommentsById(dev.getDev_id());
    		String leahCmt = "";
    		String USCmt = "";
    		String millCmt = "";
    		String georgeCmt = "";
    		for (Comment comment : comments) {
    			if (comment.getName().equals("Leah")) {
    				leahCmt += comment.getDatestamp() + ": " + comment.getContent() + ". ";
    			} else if (comment.getName().equals("US")) {
    				USCmt += comment.getDatestamp() + ": " + comment.getContent() + ". ";
    			} else if (comment.getName().equals("Mill")) {
    				millCmt += comment.getDatestamp() + ": " + comment.getContent() + ". ";
    			} else if (comment.getName().equals("George")) {
    				georgeCmt += comment.getDatestamp() + ": " + comment.getContent() + ". ";
    			}
    		}
    		row.createCell(40).setCellValue(leahCmt);
    		row.createCell(41).setCellValue(USCmt);
    		row.createCell(42).setCellValue(millCmt);
    		row.createCell(43).setCellValue(georgeCmt);
    		numRows++;
        }
        
        // Auto-size all columns based on their content
        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the data to an Excel file
        workbook.write(out);
        workbook.close();
    }
}