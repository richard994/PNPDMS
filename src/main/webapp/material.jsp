<%@page import="Util.MatService"%>
<%@page import="java.util.List"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/shared.css">
	<title>Material</title>

	<style>
		body {
			font-family: 'Inter', sans-serif !important;
		}
	</style>
	<script defer>
		var mats = ${mats};
	</script>
</head>
<body class="bg-light">
	<c:set var="activePage" value="Material" scope="request" />
	<jsp:include page="/WEB-INF/jspf/navbar.jsp" />
	
	<nav aria-label="breadcrumb" id="breadcrumb" style="margin-left: 165px">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item" id="home">Home</li>
	    <li class="breadcrumb-item">Material</li>
	  </ol>
	</nav>
	
	<form id="filterForm" onsubmit="return filter(this)" class="input-group border-bottom pb-4 mb-4" style="width: 1250px; margin-left: 165px">
		<div class="hstack gap-3">
			<div class="input-group-prepend">
				<span class="input-group-text bg-light border-light">Name</span>
			</div>
			<input type="text" id="matname" class="form-control border rounded-0 me-auto" placeholder="Enter"/>
			
			<button class="btn border-0" type="button" id="searchmat" onclick="search()" style="background-color: #4D73FF; color: white; width: 180px">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
				Search
			</button>
	    	<button class="btn border-0" type="button" id="resetmatsearch" onclick="catFilter('')" style="background-color: #E8E8E8; width: 180px">
	    		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
				  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
				</svg>
	    		Reset
	    	</button>
		</div>
	</form>
	
	<div class="d-flex justify-content-start" style="gap: 20px; width: 98%">
		<div class="container bg-white" style="width: 350px; margin-left: 165px">
			<div class="list-group-flush mx-auto mt-3 mb-3" style="width: 200px">
			  <button class="list-group-item list-group-item-action active d-flex justify-content-between align-items-center border-bottom-0 mb-2" style="background-color: #4D73FF" onclick="catFilter('')">
			  	ALL
			  	<span id="numMats" name="numMats" class="badge badge-pill" style="background-color: none; font-size: 15px"></span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Chenille Yarn" onclick="catFilter('1')">
			  	Chenille Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numChenilleYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Cotton Yarn" onclick="catFilter('2')">
			  	Cotton Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numCottonYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Rayon" onclick="catFilter('3')">
			  	Rayon
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numRayon}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Fancy Yarn" onclick="catFilter('4')">
			  	Fancy Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFancyYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Filament Yarn" onclick="catFilter('5')">
			  	Filament Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFilamentYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Polyester Yarn" onclick="catFilter('6')">
			  	Polyester Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyesterYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Wool Yarn" onclick="catFilter('7')">
			  	Wool Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numWoolYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Cashmere" onclick="catFilter('8')">
			  	Cashmere
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numCashmere}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Linen Yarn" onclick="catFilter('9')">
			  	Linen Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numLinenYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Artificial Yarn" onclick="catFilter('A')">
			  	Artificial Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numArtificialCotton}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="100% Cotton" onclick="catFilter('B')">
			  	100% Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFullCotton}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Twisted Yarn" onclick="catFilter('C')">
			  	Twisted Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numTwistedYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Poly Cotton" onclick="catFilter('D')">
			  	Poly Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyCotton}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Slub Yarn" onclick="catFilter('E')">
			  	Slub Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numSlubYarn}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Silk" onclick="catFilter('F')">
			  	Silk
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numSilk}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Acrylic Yarn" onclick="catFilter('G')">
			  	Acrylic Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numAcrylicYarn}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Fire Retardent" onclick="catFilter('H')">
			  	Fire Retardent
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFireRetardent}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Filament Polyester" onclick="catFilter('J')">
			  	Filament Polyester
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFilamentPolyester}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Nylon" onclick="catFilter('K')">
			  	Nylon
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numNylon}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Poly Linen" onclick="catFilter('L')">
			  	Poly Linen
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyLinen}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Recycled Yarn" onclick="catFilter('N')">
			  	Recycled Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numRecycledYarn}</span>
			  </button>
			</div>
		</div>
		
		<div class="container bg-white" id="mattable" style="gap: 20px"></div>
	</div>
		
		<script src="js/material.js"></script>
</body>
</html>