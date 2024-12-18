package Util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MatService")
public class MatService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static List<String> matList;
	private static int numMats;
	private static String matStr;
	
	public MatService() {}
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if (session.getAttribute("loggedin") == null) {
			request.getRequestDispatcher("/auth.jsp").forward(request, response);
		} else {
			boolean loggedin = (boolean) session.getAttribute("loggedin");
			if (!loggedin) {
				request.getRequestDispatcher("/auth.jsp").forward(request, response);
			} else {
				MatData matdata = new MatData();
				matStr = matdata.getMatStr();
				matList = matdata.getMatList();
				numMats = matList.size();
				int remainder = numMats % 3;
				if (remainder != 0) {
					remainder = 3 - remainder;
				}
				for (int i=0; i<remainder; i++) {
					matList.add("EMPTY");
				}
				request.setAttribute("numMats", numMats);
				request.setAttribute("matList", matList);
				request.setAttribute("matStr", matStr);
				request.setAttribute("matCatStr", matdata.getMatCategoryStr());
				
				int numChenilleYarn = matdata.getNumChenillerYarn();
				int numCottonYarn = matdata.getNumCottonYarn();
				int numRayon = matdata.getNumRayon();
				int numFancyYarn = matdata.getNumFancyYarn();
				int numFilamentYarn = matdata.getNumFilamentYarn();
				int numPolyesterYarn = matdata.getNumPolyesterYarn();
				int numWoolYarn = matdata.getNumWoolYarn();
				int numCashmere = matdata.getNumCashmere();
				int numLinenYarn = matdata.getNumLinenYarn();
				int numArtificialCotton = matdata.getNumArtificialCotton();
				int numFullCotton = matdata.getNumFullCotton();
				int numTwistedYarn = matdata.getNumTwistedYarn();
				int numPolyCotton = matdata.getNumPolyCotton();
				int numSlubYarn = matdata.getNumSlubYarn();
				int numSilk = matdata.getNumSilk();
				int numAcrylicYarn = matdata.getNumAcrylicYarn();
				int numFireRetardent = matdata.getNumFireRetardent();
				int numFilamentPolyester = matdata.getNumFilamentPolyester();
				int numNylon = matdata.getNumNylon();
				int numPolyLinen = matdata.getNumPolyLinen();
				int numRecycledYarn = matdata.getNumRecycledYarn();
				request.setAttribute("numChenilleYarn", numChenilleYarn);
				request.setAttribute("numCottonYarn", numCottonYarn);
				request.setAttribute("numRayon", numRayon);
				request.setAttribute("numFancyYarn", numFancyYarn);
				request.setAttribute("numFilamentYarn", numFilamentYarn);
				request.setAttribute("numPolyesterYarn", numPolyesterYarn);
				request.setAttribute("numWoolYarn", numWoolYarn);
				request.setAttribute("numCashmere", numCashmere);
				request.setAttribute("numLinenYarn", numLinenYarn);
				request.setAttribute("numArtificialCotton", numArtificialCotton);
				request.setAttribute("numFullCotton", numFullCotton);
				request.setAttribute("numTwistedYarn", numTwistedYarn);
				request.setAttribute("numPolyCotton", numPolyCotton);
				request.setAttribute("numSlubYarn", numSlubYarn);
				request.setAttribute("numSilk", numSilk);
				request.setAttribute("numAcrylicYarn", numAcrylicYarn);
				request.setAttribute("numFireRetardent", numFireRetardent);
				request.setAttribute("numFilamentPolyester", numFilamentPolyester);
				request.setAttribute("numNylon", numNylon);
				request.setAttribute("numPolyLinen", numPolyLinen);
				request.setAttribute("numRecycledYarn", numRecycledYarn);

				request.getRequestDispatcher("/material.jsp").forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
}