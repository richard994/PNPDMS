package Util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/MatService")
public class MatService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
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
				PriceData priceData = new PriceData();
				ArrayList<Mats> matList = priceData.getMatList();
				
				int numChenilleYarn=0;
				int numCottonYarn=0;
				int numRayon=0;
				int numFancyYarn=0;
				int numFilamentYarn=0;
				int numPolyesterYarn=0;
				int numWoolYarn=0;
				int numCashmere=0;
				int numLinenYarn=0;
				int numArtificialCotton=0;
				int numFullCotton=0;
				int numTwistedYarn=0;
				int numPolyCotton=0;
				int numSlubYarn=0;
				int numSilk=0;
				int numAcrylicYarn=0;
				int numFireRetardent=0;
				int numFilamentPolyester=0;
				int numNylon=0;
				int numPolyLinen=0;
				int numRecycledYarn=0;
				for (Mats mat : matList) {
					if (mat.getKey().equals("1")) {
						numChenilleYarn++;
					} else if (mat.getKey().equals("2")) {
						numCottonYarn++;
					} else if (mat.getKey().equals("3")) {
						numRayon++;
					} else if (mat.getKey().equals("4")) {
						numFancyYarn++;
					} else if (mat.getKey().equals("5")) {
						numFilamentYarn++;
					} else if (mat.getKey().equals("6")) {
						numPolyesterYarn++;
					} else if (mat.getKey().equals("7")) {
						numWoolYarn++;
					} else if (mat.getKey().equals("8")) {
						numCashmere++;
					} else if (mat.getKey().equals("9")) {
						numLinenYarn++;
					} else if (mat.getKey().equals("A")) {
						numArtificialCotton++;
					} else if (mat.getKey().equals("B")) {
						numFullCotton++;
					} else if (mat.getKey().equals("C")) {
						numTwistedYarn++;
					} else if (mat.getKey().equals("D")) {
						numPolyCotton++;
					} else if (mat.getKey().equals("E")) {
						numSlubYarn++;
					} else if (mat.getKey().equals("F")) {
						numSilk++;
					} else if (mat.getKey().equals("G")) {
						numAcrylicYarn++;
					} else if (mat.getKey().equals("H")) {
						numFireRetardent++;
					} else if (mat.getKey().equals("J")) {	
						numFilamentPolyester++;
					} else if (mat.getKey().equals("K")) {
						numNylon++;
					} else if (mat.getKey().equals("L")) {
						numPolyLinen++;
					} else if (mat.getKey().equals("N")) {
						numRecycledYarn++;
					} else {} 
				}
				
				ObjectMapper objectMapper = new ObjectMapper();
				String matJson = objectMapper.writeValueAsString(matList);
				request.setAttribute("mats", matJson);
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