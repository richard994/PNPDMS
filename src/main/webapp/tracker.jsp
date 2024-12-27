<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/tracker.css" />
	<title>Tracker</title>
	
	<style>
		.breadcrumb-item::before {
			content: url(discount.png) !important;
			padding-right: 5px !important;
		}
		.nav-link {
			color: black;
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
		        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material">Material</a></li>
		        <li class="nav-item mr-5"><a href="FinishService" class="nav-link" id="Finishing">Finishing</a></li>
		        <li class="nav-item mr-5"><a href="TrackerService" class="nav-link" id="Tracker" style="color: #4D73FF">Tracker</a></li>
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
	
	<div class="container" id="breadcrumbContainer">
		<nav aria-label="breadcrumb" id="breadcrumb" style="width: 50%">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item" id="home">Home</li>
		    <li class="breadcrumb-item">Tracker</li>
		  </ol>
		</nav>
		<div class="container">
			<div style="float: right">
				<img class="language" src="img/language.svg" alt="language" />
				<select class="custom-select border border-black border-2 rounded-0 bg-white" id="Language" name="Language" size="1" style="width: 50px; height: 25px">
			    	<option value="EN" selected>EN</option>
			    	<option value="CN">CN</option>
			  	</select>
		  	</div>
	  	</div>
	</div>
	
	<form id="filterForm" onsubmit="return filter(this)" class="input-group border-bottom pb-4 mb-4" style="width: 1250px; margin-left: 165px">
		<div class="hstack gap-3">
			<div class="input-group-prepend">
				<span class="input-group-text bg-light border-light">Name</span>
			</div>
			<input type="text" id="recordname" class="form-control border rounded-0 me-auto" placeholder="Enter"/>
			
			<button class="btn border-0" type="submit" id="search" style="background-color: #4D73FF; color: white; width: 180px">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
				Search
			</button>
	    	<button class="btn border-0" type="button" id="resetsearch" onclick="refresh()" style="background-color: #E8E8E8; width: 180px">
	    		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
				  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
				</svg>
	    		Reset
	    	</button>
	    	<button class="btn border-0" type="button" id="export" onclick="export()" style="background-color: #4D73FF; color: white; width: 180px">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
				  <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
				</svg>
	    		Export
	    	</button>
		</div>
	</form>
	
	<div class="d-flex justify-content-start" style="gap: 20px; width: 98%">
		<div class="container bg-white" style="width: 350px; margin-left: 165px">
			<div class="list-group-flush mt-3 mb-3 mx-auto" style="width: 200px">
				<div class="container p-0 range-container">
					<div class="row" style="height: 100%">
						<label for="PriceRangeMin" class="col range-label">Price Range</label>
					</div>
					<div class="row" style="position: relative">
						<div class="col p-0">
							<div class="default-track"></div>
							<span class="slider-track"></span>
							<input type="range" class="min-val" min="0" max="10" step="0.1" value="0" id="PriceRangeMin" oninput="slideMin()">
							<input type="range" class="max-val" min="0" max="10" step="0.1" value="10" id="PriceRangeMax" oninput="slideMax()">
			            </div>
		            </div>
		            <div class="row" style="position: relative">
			            <div class="col price-container mt-0 p-0">
			              <div class="price-1" id="min-price" style="width: 20px">$0</div>
			              <div class="price-2" id="max-price" style="width: 20px; transform: translateX(20px)">$10</div>
			            </div>
		            </div>
	            </div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="ParagonCleanCB">
					<label class="form-check-label" for="ParagonCleanCB">
					  Paragon Clean
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="FCLCB">
					<label class="form-check-label" for="FCLCB">
					  400hr FCL
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="PDCB">
					<label class="form-check-label" for="PDCB">
					  Piece Dyed
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="FeedbackCB">
					<label class="form-check-label" for="FeedbackCB">
					  Need US Feedback?
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="StrikeCB">
					<label class="form-check-label" for="StrikeCB">
					  In Strike-off Phase
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="BlanketCB">
					<label class="form-check-label" for="BlanketCB">
					  In Blanket Phase
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="RollSampleCB">
					<label class="form-check-label" for="RollSampleCB">
					  In Roll Sample Phase
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" value="" id="TestingCB">
					<label class="form-check-label" for="TestingCB">
					  In Testing Phase
					</label>
				</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="Season" class="control-label opacity-75">Season</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Season" name="Season" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="23Fall">23Fall</option>
				    	<option value="24Spring">24Spring</option>
				    	<option value="24Fall">24Fall</option>
				    	<option value="25Spring">25Spring</option>
				    	<option value="25Fall">25Fall</option>
				    	<option value="26Spring">26Spring</option>
				    	<option value="26Fall">26Fall</option>
				    	<option value="27Spring">27Spring</option>
				    	<option value="27Fall">27Fall</option>
				  	</select>
    			</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="TimeInDev" class="control-label opacity-75">Time in Development</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="TimeInDev" name="TimeInDev" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="LTOW">Less Than One Week</option>
				    	<option value="OTTW">One to Two Weeks</option>
				    	<option value="TWTOM">Two Weeks to One Month</option>
				    	<option value="MTOM">More Than One Month</option>
				    	<option value="MTSM">More Than Six Months</option>
				  	</select>
    			</div>
 			    <div class="d-flex flex-column mt-4">
    				<label for="WarpType" class="control-label opacity-75">Warp Type</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="WarpType" name="WarpType" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="chenille">Chenille</option>
				    	<option value="fancy">Fancy Yarn</option>
				    	<option value="filament">Filament Yarn</option>
				    	<option value="cotton">Cotton</option>
				    	<option value="poly">Polyester</option>
				    	<option value="rayon">Rayon</option>
				    	<option value="slub">Slub Yarn</option>
				  	</select>
    			</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="YarnType" class="control-label opacity-75">Yarn Type</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="YarnType" name="YarnType" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="YarnDyed">Yarn Dyed</option>
				    	<option value="Piece">Piece Dyed</option>
				    	<option value="Space">Space Dyed</option>
				    	<option value="Jet">Jet Dyed</option>
				  	</select>
    			</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="Colorist" class="control-label opacity-75">Colorist</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Colorist" name="Colorist" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="house">House</option>
				    	<option value="marteen">Marteen</option>
				    	<option value="crowder">Crowder</option>
				    	<option value="derocher">Derocher</option>
				  	</select>
    			</div>
			</div>
		</div>
		
		<div class="container d-flex flex-column" style="gap: 20px">
			<div class="container" id="catalogBtnContainer">
				<button class="btn border-0" type="button" id="AddNewDevBtn" onclick="window.location.href='NewDevService?action=create'">
			  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
					  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
					</svg>
			  		Add New Product Development
			  	</button>
		  	</div>
		  	
		  	<div class="container" id="DevListingContainer">
		  		
		  		<div class="row" style="margin-bottom: 25px">
		  			<!-- Loop through developmentsList -->
		  			<c:forEach var="dev" items="${developmentsList}" varStatus="status">
		                <!-- Start a new row every 3 developments -->
		                <c:if test="${status.index % 3 == 0 && status.index != 0}">
		                    </div> <!-- Close the previous row -->
		                    <div class="row"> <!-- Start a new row -->
		                </c:if>
		  			<div class="col-4">
		  				<div class="card p-3">
		  					<div class="imgblock">
		  						<a href="NewDevService?action=view&devId=${dev.dev_id}">
		  							<img src="${dev.fabric_img_path}" alt="img/placeholder-image.jpg" onerror="this.onerror=null; this.src='img/placeholder-image.jpg';"/>
		  						</a>
		  					</div>
		  					<div class="container p-0" id="CardDescription">
		  						<div class="row mt-2">
		  							<div class="col">
		  								<span id="CardCode" style="font-size: 15px">${dev.code}</span>
		  							</div>
		  							<div class="col">
		  								<span style="float: right" id="CardNameAndColor">${dev.title}</span>
		  							</div>
		  						</div>
		  						<div class="row">
		  							<div class="col">
		  								<div class="form-check">
											<input class="form-check-input" type="checkbox" ${dev.needFeedback ? 'checked' : ''} id="CardFeedBackCB" disabled>
											<label class="form-check-label" for="CardFeedBackCB" style="white-space: nowrap">
											  Need Feedback
											</label>
										</div>
		  							</div>
		  							<div class="col">
		  								<span style="float: right" id="CardCurrStatus" class="cardcurrstatus">${dev.currentPhase}</span>
		  							</div>
		  						</div>
		  						<div class="row">
		  							<div class="col">
		  								<span id="CardPrice" class="cardprice">$${dev.cost}</span>
		  							</div>
		  							<div class="col">
		  								<div id="CardBtnGroup" style="display: flex; gap: 10px">
			  								<button class="btn border-2 cardbtn" type="button" id="DuplicateBtn">
										  		Duplicate
										  	</button>
										  	<button class="btn border-2 cardbtn" type="button" id="EditBtn" onclick="window.location.href='NewDevService?action=edit&devId=${dev.dev_id}'">
										  		Edit
										  	</button>
		  								</div>
		  							</div>
		  						</div>
		  					</div>
		  				</div>
		  			</div>
		  				<!-- Handle the last row if it has fewer than 3 items -->
		                <c:if test="${status.index == developmentsList.size() - 1}">
		                    </div> <!-- Close the last row -->
		                </c:if>
		
		            </c:forEach>
		  		
		  		</div>
		</div>
	</div>
	
	<script src="js/tracker.js"></script>
</body>
</html>