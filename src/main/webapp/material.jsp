<%@page import="Util.MatService"%>
<%@page import="java.util.List"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="js/shared.css">
	<title>Material</title>
	
	<style>
		#headbar {
			font-size: calc( 12px + (20 - 16) * (100vw - 400px) / (800 - 400) );
			font-weight: 300;
		}
		#home::before {
			content: url(home.png) !important;
		}
		.breadcrumb-item::before {
			content: url(discount.png) !important;
			padding-right: 5px !important;
		}
		
		.breadcrumb-item::after {
			content: ">";
			padding-left: 10px;
		}
		
		.breadcrumb-item:last-child::after {
			content: "";
		}
		
		.breadcrumb-item:last-child {
			opacity: 1.0;
		}
		
		.breadcrumb-item {
			opacity: 0.5;
		}
		.nav-link {
			color: black;
		}
		.dropdown-menu {
		  display: none;
		  position: absolute;
		  background-color: #f9f9f9;
		  min-width: 160px;
		  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		  z-index: 1;
		}
		
		.dropdown-menu li a {
		  float: none;
		  color: black;
		  text-decoration: none;
		  display: block;
		  text-align: left;
		}
		
		.dropdown-menu li a:hover {
		  background-color: #ddd;
		}
		
		.dropdown:hover .dropdown-menu {
		  display: block;
		}
	</style>
</head>
<body class="bg-light">
	<section class="bg-white">
		<div class="container">
	   		<div class="d-flex flex-wrap justify-content-center py-3 mb-4" id="headbar">
		      <p class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
		      <img src="icon.png" class="img-fluid d-sm-block d-none" style="width: 95px; margin-left: 90px">
		      </p>
		
		      <ul class="nav nav-pills">
		        <li class="nav-item mr-5"><a href="HomeService" class="nav-link" id="Home">Home</a></li>
		        <li class="nav-item mr-5"><a href="PricingService" class="nav-link" id="Pricing">Pricing</a></li>
		        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material" style="color: #4D73FF">Material</a></li>
		        <li class="nav-item mr-5"><a href="FinishService" class="nav-link" id="Finishing">Finishing</a></li>
           		<li class="nav-item dropdown mr-5">
				   <a class="nav-link  dropdown-toggle" href="#" data-bs-toggle="dropdown">Account</a>
				    <ul class="dropdown-menu">
					  <li><a class="dropdown-item" href="LogoutService">Log Out</a></li>
				    </ul>
				</li>
		      </ul>
		    </div>
	    </div>
	</section>
	
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
			
			<button class="btn border-0" type="submit" id="searchmat" style="background-color: #4D73FF; color: white; width: 180px">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
				Search
			</button>
	    	<button class="btn border-0" type="button" id="resetmatsearch" onclick="refresh()" style="background-color: #E8E8E8; width: 180px">
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
			  <button class="list-group-item list-group-item-action active d-flex justify-content-between align-items-center border-bottom-0 mb-2" style="background-color: #4D73FF" onclick="refresh()">
			  	ALL
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px">${numMats}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Chenille Yarn" onclick="catFilter(this.id)">
			  	Chenille Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numChenilleYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Cotton Yarn" onclick="catFilter(this.id)">
			  	Cotton Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numCottonYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Rayon" onclick="catFilter(this.id)">
			  	Rayon
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numRayon}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Fancy Yarn" onclick="catFilter(this.id)">
			  	Fancy Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFancyYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Filament Yarn" onclick="catFilter(this.id)">
			  	Filament Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFilamentYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Polyester Yarn" onclick="catFilter(this.id)">
			  	Polyester Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyesterYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Wool Yarn" onclick="catFilter(this.id)">
			  	Wool Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numWoolYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Cashmere" onclick="catFilter(this.id)">
			  	Cashmere
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numCashmere}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Linen Yarn" onclick="catFilter(this.id)">
			  	Linen Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numLinenYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Artificial Yarn" onclick="catFilter(this.id)">
			  	Artificial Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numArtificialCotton}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="100% Cotton" onclick="catFilter(this.id)">
			  	100% Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFullCotton}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Twisted Yarn" onclick="catFilter(this.id)">
			  	Twisted Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numTwistedYarn}</span>
			  </button>
			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Poly Cotton" onclick="catFilter(this.id)">
			  	Poly Cotton
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyCotton}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Slub Yarn" onclick="catFilter(this.id)">
			  	Slub Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numSlubYarn}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Silk" onclick="catFilter(this.id)">
			  	Silk
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numSilk}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Acrylic Yarn" onclick="catFilter(this.id)">
			  	Acrylic Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numAcrylicYarn}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Fire Retardent" onclick="catFilter(this.id)">
			  	Fire Retardent
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFireRetardent}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Filament Polyester" onclick="catFilter(this.id)">
			  	Filament Polyester
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numFilamentPolyester}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Nylon" onclick="catFilter(this.id)">
			  	Nylon
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numNylon}</span>
			  </button>
   			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Poly Linen" onclick="catFilter(this.id)">
			  	Poly Linen
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numPolyLinen}</span>
			  </button>
  			  <button class="list-group-item list-group-item-action d-flex justify-content-between align-items-center border-bottom-0 mb-2" id="Recycled Yarn" onclick="catFilter(this.id)">
			  	Recycled Yarn
			  	<span class="badge badge-pill" style="background-color: none; font-size: 15px; color: black">${numRecycledYarn}</span>
			  </button>
			</div>
		</div>
		
		<div class="container bg-white" id="mattable" style="gap: 20px">
		<%List<String> materials = (List<String>) request.getAttribute("matList");
		  int loopcnt = 0;
		  int numMats = materials.size();%>
		<c:forEach items="<%= materials %>" var="mat" step="3" varStatus="loop">
		  <div class="row">
		    <div class="col-sm" id="box${loop.count*3}">
		      <table class="table table-bordered mt-3">
		      	<thead class="table-light">
		      	  <tr>
		      		<th scope="col" style="width: 40px">#</th>
      				<th scope="col" style="font-weight: normal">Name</th>
		      	  </tr>
		      	</thead>
		      	<tbody class="border-top-0">
				    <tr style="height: 65px">
				      <th scope="row" style="width: 40px"><%= loopcnt+1 %></th>
				      <td id="${loop.count*3}"><%= materials.get(loopcnt++) %></td>
				    </tr>
				 </tbody>
		      </table>
		    </div>
		    <div class="col-sm" id="box${loop.count*3+1}">
   		      <table class="table table-bordered mt-3">
		      	<thead class="table-light">
		      	  <tr>
		      		<th scope="col" style="width: 40px">#</th>
      				<th scope="col" style="font-weight: normal">Name</th>
		      	  </tr>
		      	</thead>
		      	<tbody class="border-top-0">
				    <tr style="height: 65px">
				      <th scope="row" style="width: 40px"><%= loopcnt+1 %></th>
				      <td id="${loop.count*3+1}"><%= materials.get(loopcnt++) %></td>
				    </tr>
				 </tbody>
		      </table>
		    </div>
		    <div class="col-sm" id="box${loop.count*3+2}">
  		      <table class="table table-bordered mt-3">
		      	<thead class="table-light">
		      	  <tr>
		      		<th scope="col" style="width: 40px">#</th>
      				<th scope="col" style="font-weight: normal">Name</th>
		      	  </tr>
		      	</thead>
		      	<tbody class="border-top-0">
				    <tr style="height: 65px">
				      <th scope="row" style="width: 40px"><%= loopcnt+1 %></th>
				      <td id="${loop.count*3+2}"><%= materials.get(loopcnt++) %></td>
				    </tr>
				 </tbody>
		      </table>
		    </div>
		  </div>
		 </c:forEach>
		 </div>
		</div>
		
		<script>
		<%String matCatString = request.getAttribute("matCatStr").toString();%>
		var matCatStr = "<%= matCatString%>";
		const matCatMap = new Map();
		const matCatArr = matCatStr.split(",");
		for (var i=0; i<matCatArr.length; i++) {
			const mca = matCatArr[i].split(":");
			matCatMap.set(mca[0], mca[1]);
		}
		
		function filter(that) {
			var matname = that.matname.value;
			var num = "<%= numMats%>";
			num = parseInt(num);
			if (!matname) {
				alert("Please enter matname.");
			} else {
				for (var i=0; i<num; i++) {
					var idx = i+3;
					var item = document.getElementById(idx).textContent;
					if (!item.includes(matname)) {
						document.getElementById("box" + idx).style.display = "none";
					} else {
						document.getElementById("box" + idx).style.display = "block";
					}
				}
			}
			return false;
		}
		
		function catFilter(id) {
			var num = "<%= numMats%>";
			num = parseInt(num);
			for (var i=0; i<num; i++) {
				var idx = i+3;
				var mat = document.getElementById(idx).innerText;
				if (id === matCatMap.get(mat)) {
					document.getElementById("box" + idx).style.display = "block";
				} else {
					document.getElementById("box" + idx).style.display = "none";
				}
			}
		}
		
		function refresh() {
			window.location.reload();
		}
		</script>
</body>
</html>