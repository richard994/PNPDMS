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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.7.0.min.js"></script>
	<title>Tracker</title>
	
	<style>
		#home::before {
			content: url(home.png) !important;
		}
		.breadcrumb-item::before {
			content: url(discount.png) !important;
			padding-right: 5px !important;
		}
		.nav-link {
			color: black;
		}
	</style>
	<script defer>
		var filtered = ${filtered};
		var dev = JSON.parse('${filterdev}');
	</script>
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
	
	<form id="filterForm" name="filterForm" action="FilterService" method="get">
	<div class="input-group border-bottom pb-4 mb-4" style="width: 1250px; margin-left: 165px">
		<div class="hstack gap-3">
			<div class="input-group-prepend">
				<span class="input-group-text bg-light border-light">Title or Code</span>
			</div>
			<input type="text" id="titleCode" name="titleCode" class="form-control border rounded-0 me-auto" placeholder="Enter"/>
			
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
	    	<button class="btn border-0" type="button" id="export" onclick="showModal()" style="background-color: #4D73FF; color: white; width: 180px">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
				  <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
				</svg>
	    		Export
	    	</button>
		</div>
	</div>
	
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
							<input type="range" class="min-val" min="0" max="100" step="1" value="0" id="PriceRangeMin" name="PriceRangeMin" oninput="slideMin()">
							<input type="range" class="max-val" min="0" max="100" step="1" value="100" id="PriceRangeMax" name="PriceRangeMax" oninput="slideMax()">
			            </div>
		            </div>
		            <div class="row" style="position: relative">
			            <div class="col price-container mt-0 p-0">
			              <div class="price-1" id="min-price" style="width: 20px">¥0</div>
			              <div class="price-2" id="max-price" style="width: 20px; transform: translateX(20px)">¥100</div>
			            </div>
		            </div>
	            </div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="ParagonCleanCB" name="ParagonCleanCB">
					<label class="form-check-label" for="ParagonCleanCB">
					  Paragon Clean
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="FCLCB" name="FCLCB">
					<label class="form-check-label" for="FCLCB">
					  400hr FCL
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="PDCB" name="PDCB">
					<label class="form-check-label" for="PDCB">
					  Piece Dyed
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="ChenilleCB" name="ChenilleCB">
					<label class="form-check-label" for="ChenilleCB">
					  Chenille
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="FeedbackCB" name="FeedbackCB">
					<label class="form-check-label" for="FeedbackCB">
					  Need US Feedback?
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="SDYCB" name="SDYCB">
					<label class="form-check-label" for="SDYCB">
					  SDY
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="KnitCB" name="KnitCB">
					<label class="form-check-label" for="KnitCB">
					  Knit
					</label>
				</div>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" id="GeorgeCancelCB" name="GeorgeCancelCB">
					<label class="form-check-label" for="GeorgeCancelCB">
					  George Canceled
					</label>
				</div>
				<div class="d-flex flex-column mt-4">
    				<label for="FabricType" class="control-label opacity-75">Fabric Type</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="FabricType" name="FabricType" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="Base">Base</option>
				    	<option value="Jaquard">Jaquard</option>
				  	</select>
			  	</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="Season" class="control-label opacity-75">Season</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Season" name="Season" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="24Spring">24Spring</option>
				    	<option value="24Fall">24Fall</option>
				    	<option value="25Spring">25Spring</option>
				    	<option value="25Fall">25Fall</option>
				    	<option value="26Spring">26Spring</option>
				    	<option value="26Fall">26Fall</option>
				    	<option value="27Spring">27Spring</option>
				    	<option value="27Fall">27Fall</option>
				    	<option value="28Spring">28Spring</option>
				    	<option value="28Fall">28Fall</option>
				    	<option value="29Spring">29Spring</option>
				    	<option value="29Fall">29Fall</option>
				    	<option value="30Spring">30Spring</option>
				    	<option value="30Fall">30Fall</option>
				  	</select>
    			</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="DesignType" class="control-label opacity-75">Design Type</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="DesignType" name="DesignType" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="NewDesign">New Design</option>
				    	<option value="Reshow">Reshow</option>
				    	<option value="24SP to 24FA">24SP to 24FA</option>
				    	<option value="24FA to 25SP">24FA to 25SP</option>
				    	<option value="25SP to 25FA">25SP to 25FA</option>
				    	<option value="25FA to 26SP">25FA to 26SP</option>
				    	<option value="26SP to 26FA">26SP to 26FA</option>
				    	<option value="26FA to 27SP">26FA to 27SP</option>
				    	<option value="27SP to 27FA">27SP to 27FA</option>
				    	<option value="27FA to 28SP">27FA to 28SP</option>
				    	<option value="28SP to 28FA">28SP to 28FA</option>
				    	<option value="28FA to 29SP">28FA to 29SP</option>
				    	<option value="29SP to 29FA">29SP to 29FA</option>
				    	<option value="29FA to 30SP">29FA to 30SP</option>
				    	<option value="30SP to 30FA">30SP to 30FA</option>
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
			  	<div class="d-flex flex-column mt-4">
    				<label for="Designer" class="control-label opacity-75">Designer</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Designer" name="Designer" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="house">House</option>
				    	<option value="Outsource">Outsource</option>
				    	<option value="crowder">Crowder</option>
				    	<option value="derocher">Derocher</option>
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
    				<label for="YarnType" class="control-label opacity-75">Dyeing Type</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="YarnType" name="YarnType" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="YarnDyed">Yarn Dyed</option>
				    	<option value="Piece">Piece Dyed</option>
				    	<option value="Space">Space Dyed</option>
				    	<option value="Jet">Jet Dyed</option>
				  	</select>
    			</div>
    			<div class="d-flex flex-column mt-4">
    				<label for="Direction" class="control-label opacity-75">Direction</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Direction" name="Direction" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="UU">UU</option>
				    	<option value="UTR">UTR</option>
				  	</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
    				<label for="Content" class="control-label opacity-75">Content<span style="font-size: 12px">(%Mix)</span></label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Content" name="Content" size="1" style="width: 100%; height: 36px" placeholder="Enter">
			  			<option value="" selected>Enter</option>
				    	<option value="100% polyester">100% polyester</option>
				    	<option value="1% acrylic 99% polyester">1% acrylic 99% polyester</option>
				    	<option value="2% acrylic 98% polyester">2% acrylic 98% polyester</option>
				    	<option value="3% acrylic 97% polyester">3% acrylic 97% polyester</option>
				    	<option value="10% acrylic 90% polyester">10% acrylic 90% polyester</option>
				    	<option value="3% linen 97% polyester">3% linen 97% polyester</option>
				    	<option value="5% linen 95% polyester">5% linen 95% polyester</option>
				    	<option value="10%N 90%P">10%N 90%P</option>
				    	<option value="10%V 90%P">10%V 90%P</option>
			  		</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
    				<label for="StrikeProgress" class="control-label opacity-75">Strike-Off Progress</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="StrikeProgress" name="StrikeProgress" size="1" style="width: 100%; height: 36px">
				    	<option value="DNE" selected>Enter</option>
				    	<option value="Wait for US feedback">Wait for US feedback</option>
				    	<option value="US canceled">US canceled</option>
				    	<option value="Strike-off producing">Strike-off producing</option>
				    	<option value="Strike-off confirmed">Strike-off confirmed</option>
				  	</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
    				<label for="BlanketStatus" class="control-label opacity-75">Blanket Status</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="BlanketStatus" name="BlanketStatus" size="1" style="width: 100%; height: 36px">
				    	<option value="DNE" selected>Enter</option>
				    	<option value="Strike-off confirmed. Wait for US blanket proceeding">Strike-off confirmed. Wait for US blanket proceeding</option>
				    	<option value="Blanket under production">Blanket under production</option>
				    	<option value="Banket sent. Wait for US feedback">Blanket sent. Wait for US feedback</option>
				    	<option value="China Team are confirming the colors">China Team are confirming the colors</option>
				    	<option value="Blanket confirmed">Blanket confirmed</option>
				  	</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
		  			<label for="ColorLineProgress" class="control-label opacity-75">Colorline Status</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ColorLineProgress" name="ColorLineProgress" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="Yibei has received all colors">Yibei has received all colors</option>
				    	<option value="Colorline completed">Colorline completed</option>
				    	<option value="Colorline shipped">Colorline shipped</option>
				  	</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
			  		<label for="RollSampleProgress" class="control-label opacity-75">Roll Sample Progress</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="RollSampleProgress" name="RollSampleProgress" size="1" style="width: 100%; height: 36px">
				    	<option value="DNE" selected>Enter</option>
				    	<option value="Roll Samples Arranged">Roll Samples Arranged</option>
				    	<option value="Roll samples completed">Roll samples completed</option>
				    	<option value="Roll samples to Yibei">Roll samples to Yibei</option>
				  	</select>
			  	</div>
			  	<div class="d-flex flex-column mt-4">
			  		<label for="TestingProgress" class="control-label opacity-75">Testing Progress</label>
    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="TestingProgress" name="TestingProgress" size="1" style="width: 100%; height: 36px">
				    	<option value="DNE" selected>Enter</option>
				    	<option value="Testing In Process">Testing In Process</option>
				    	<option value="Test Passed">Passed</option>
				    	<option value="Test Failed">Failed</option>
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
		  			<c:forEach var="dev" items="${currentPageList}" varStatus="status">
		                <!-- Start a new row every 3 developments -->
		                <c:if test="${status.index % 3 == 0 && status.index != 0}">
		                    </div> <!-- Close the previous row -->
		                    <div class="row" style="margin-bottom: 25px"> <!-- Start a new row -->
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
		  								<span id="CardCode${status.index}" style="font-size: 15px">${dev.code}</span>
		  							</div>
		  							<div class="col">
		  								<span style="float: right" id="CardNameAndColor${status.index}">${dev.color}</span>
		  							</div>
		  						</div>
		  						<div class="row">
		  							<div class="col">
		  								<div class="form-check">
											<input class="form-check-input" type="checkbox" ${dev.needFeedback ? 'checked' : ''} id="CardFeedBackCB${status.index}" disabled>
											<label class="form-check-label" for="CardFeedBackCB${status.index}" style="white-space: nowrap">
											  Need Feedback
											</label>
										</div>
		  							</div>
		  							<div class="col">
		  								<span style="float: right" id="CardCurrStatus${status.index}" class="cardcurrstatus">${dev.currentPhase}</span>
		  							</div>
		  						</div>
		  						<div class="row">
		  							<div class="col">
		  								<span id="CardPrice${status.index}" class="cardprice">¥${dev.cost}</span>
		  							</div>
		  							<div class="col">
		  								<div id="CardBtnGroup" style="display: flex; gap: 10px">
			  								<button class="btn border-2 cardbtn" type="button" onclick="redirect('SaveNewDevService?action=duplicate&devId=${dev.dev_id}')" id="DuplicateBtn${status.index}">
										  		Duplicate
										  	</button>
										  	<button class="btn border-2 cardbtn" type="button" id="EditBtn${status.index}" onclick="window.location.href='NewDevService?action=edit&devId=${dev.dev_id}'">
										  		Edit
										  	</button>
		  								</div>
		  							</div>
		  						</div>
		  					</div>
		  				</div>
		  			</div>
		  				<!-- Handle the last row if it has fewer than 3 items -->
		                <c:if test="${status.index == currentPageList.size() - 1}">
		                    </div> <!-- Close the last row -->
		                </c:if>
		
		            </c:forEach>
		  		
		  		</div>
		  		
	  			<nav aria-label="Page navigation" id="unfilteredNav">
				  <ul class="pagination justify-content-center">
				    <c:if test="${currentPage > 1}">
					    <li class="page-item">
					      <a class="page-link" href="TrackerService?page=${currentPage-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:if>
				    
				    <c:forEach var="i" begin="1" end="${totalPages}">
				    	<li class="page-item"><a class="page-link" href="TrackerService?page=${i}">${i}</a></li>
				    </c:forEach>
				    
				    <c:if test="${currentPage < totalPages}">
					    <li class="page-item">
					      <a class="page-link" href="TrackerService?page=${currentPage + 1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:if>
				  </ul>
				</nav>
				
				<nav aria-label="Page navigation" id="filteredNav" style="display: none">
				  <ul class="pagination justify-content-center">
				    <c:if test="${currentPage > 1}">
					    <li class="page-item">
					      <a class="page-link" href="FilterService?page=${currentPage-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:if>
				    
				    <c:forEach var="i" begin="1" end="${totalPages}">
				    	<li class="page-item"><a class="page-link" href="FilterService?page=${i}">${i}</a></li>
				    </c:forEach>
				    
				    <c:if test="${currentPage < totalPages}">
					    <li class="page-item">
					      <a class="page-link" href="FilterService?page=${currentPage + 1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:if>
				  </ul>
				</nav>
		</div>
	</div>
	</form>
	
	<form id="expform" name="expform" action="ExportService" method="get">
		<div class="modal fade" id="expModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="expModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
				    <div class="modal-header">
				    	<h5 class="modal-title" id="staticBackdropLabel">Export to Excel</h5>
			    		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	    			</div>
				    <div class="modal-body">
				    	<div class="container">
				    		<div class="row mb-3">
				    			<span style="color: red">*Filter by selected field; if not selected, it will not be filtered.</span>
				    		</div>
				    		<div class="row mb-3">
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalFeedbackCB" name="ModalFeedbackCB">
										<label class="form-check-label" for="ModalFeedbackCB">
										  Need US Feedback
										</label>
									</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalParagonCleanCB" name="ModalParagonCleanCB">
										<label class="form-check-label" for="ModalParagonCleanCB">
										  Paragon Clean
										</label>
									</div>
				    			</div>
				    		</div>
				    		<div class="row mb-3">
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalFCLCB" name="ModalFCLCB">
										<label class="form-check-label" for="ModalFCLCB">
										  400hr FCL
										</label>
									</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalPDCB" name="ModalPDCB">
										<label class="form-check-label" for="ModalPDCB">
										  Piece Dyed
										</label>
									</div>
				    			</div>
				    		</div>
				    		<div class="row mb-3">
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalSDYCB" name="ModalSDYCB">
										<label class="form-check-label" for="ModalSDYCB">
										  SDY
										</label>
									</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalChenilleCB" name="ModalChenilleCB">
										<label class="form-check-label" for="ModalChenilleCB">
										  Chenille
										</label>
									</div>
				    			</div>
				    		</div>
				    		<div class="row mb-3">
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalKnitCB" name="ModalKnitCB">
										<label class="form-check-label" for="ModalKnitCB">
										  Knit
										</label>
									</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="form-check">
										<input class="form-check-input" type="checkbox" id="ModalGeorgeCancelCB" name="ModalGeorgeCancelCB">
										<label class="form-check-label" for="ModalGeorgeCancelCB">
										  George Canceled
										</label>
									</div>
				    			</div>
				    		</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
			    					<label for="ModalFabricType" class="control-label opacity-75">Fabric Type</label>
				    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalFabricType" name="ModalFabricType" size="1" style="width: 100%; height: 36px">
								    	<option value="" selected>Enter</option>
								    	<option value="Base">Base</option>
								    	<option value="Jaquard">Jaquard</option>
								  	</select>
			    				</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalSeason" class="control-label opacity-75">Season</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalSeason" name="ModalSeason" size="1" style="width: 100%; height: 36px">
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
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalWarpType" class="control-label opacity-75">Warp Type</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalWarpType" name="ModalWarpType" size="1" style="width: 100%; height: 36px">
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
				    			</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalYarnType" class="control-label opacity-75">Dyeing Type</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalYarnType" name="ModalYarnType" size="1" style="width: 100%; height: 36px">
									    	<option value="" selected>Enter</option>
									    	<option value="YarnDyed">Yarn Dyed</option>
									    	<option value="Piece">Piece Dyed</option>
									    	<option value="Space">Space Dyed</option>
									    	<option value="Jet">Jet Dyed</option>
									  	</select>
    								</div>
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
			    					<label for="ModalDesignType" class="control-label opacity-75">Design Type</label>
				    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalDesignType" name="ModalDesignType" size="1" style="width: 100%; height: 36px">
								    	<option value="" selected>Enter</option>
								    	<option value="NewDesign">New Design</option>
								    	<option value="Reshow">Reshow</option>
								    	<option value="24SP to 24FA">24SP to 24FA</option>
								    	<option value="24FA to 25SP">24FA to 25SP</option>
								    	<option value="25SP to 25FA">25SP to 25FA</option>
								    	<option value="25FA to 26SP">25FA to 26SP</option>
								    	<option value="26SP to 26FA">26SP to 26FA</option>
								    	<option value="26FA to 27SP">26FA to 27SP</option>
								    	<option value="27SP to 27FA">27SP to 27FA</option>
								    	<option value="27FA to 28SP">27FA to 28SP</option>
								    	<option value="28SP to 28FA">28SP to 28FA</option>
								    	<option value="28FA to 29SP">28FA to 29SP</option>
								    	<option value="29SP to 29FA">29SP to 29FA</option>
								    	<option value="29FA to 30SP">29FA to 30SP</option>
								    	<option value="30SP to 30FA">30SP to 30FA</option>
								  	</select>
			    				</div>
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalDesigner" class="control-label opacity-75">Designer</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalDesigner" name="ModalDesigner" size="1" style="width: 100%; height: 36px">
									    	<option value="" selected>Enter</option>
									    	<option value="house">House</option>
									    	<option value="Outsource">Outsource</option>
									    	<option value="crowder">Crowder</option>
									    	<option value="derocher">Derocher</option>
									  	</select>
					    			</div>
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalColorist" class="control-label opacity-75">Colorist</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalColorist" name="ModalColorist" size="1" style="width: 100%; height: 36px">
									    	<option value="" selected>Enter</option>
									    	<option value="house">House</option>
									    	<option value="marteen">Marteen</option>
									    	<option value="crowder">Crowder</option>
									    	<option value="derocher">Derocher</option>
									  	</select>
					    			</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
					    				<label for="ModalDirection" class="control-label opacity-75">Direction</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalDirection" name="ModalDirection" size="1" style="width: 100%; height: 36px">
									    	<option value="" selected>Enter</option>
									    	<option value="UU">UU</option>
									    	<option value="UTR">UTR</option>
									  	</select>
					    			</div>
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalContent" class="control-label opacity-75">Content<span style="font-size: 12px">(%Mix)</span></label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalContent" name="ModalContent" size="1" style="width: 100%; height: 36px" placeholder="Enter">
								  			<option value="" selected>Enter</option>
									    	<option value="100% polyester">100% polyester</option>
									    	<option value="1% acrylic 99% polyester">1% acrylic 99% polyester</option>
									    	<option value="2% acrylic 98% polyester">2% acrylic 98% polyester</option>
									    	<option value="3% acrylic 97% polyester">3% acrylic 97% polyester</option>
									    	<option value="10% acrylic 90% polyester">10% acrylic 90% polyester</option>
									    	<option value="3% linen 97% polyester">3% linen 97% polyester</option>
									    	<option value="5% linen 95% polyester">5% linen 95% polyester</option>
									    	<option value="10%N 90%P">10%N 90%P</option>
									    	<option value="10%V 90%P">10%V 90%P</option>
								  		</select>
					    			</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalStrikeProgress" class="control-label opacity-75">Strike-Off Progress</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalStrikeProgress" name="ModalStrikeProgress" size="1" style="width: 100%; height: 36px">
									    	<option value="DNE" selected>Enter</option>
									    	<option value="Wait for US feedback">Wait for US feedback</option>
									    	<option value="US canceled">US canceled</option>
									    	<option value="Strike-off producing">Strike-off producing</option>
									    	<option value="Strike-off confirmed">Strike-off confirmed</option>
									  	</select>
					    			</div>
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalBlanketStatus" class="control-label opacity-75">Blanket Status</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalBlanketStatus" name="ModalBlanketStatus" size="1" style="width: 100%; height: 36px">
									    	<option value="DNE" selected>Enter</option>
									    	<option value="Strike-off confirmed. Wait for US blanket proceeding">Strike-off confirmed. Wait for US blanket proceeding</option>
									    	<option value="Blanket under production">Blanket under production</option>
									    	<option value="Blanket sent. Wait for US feedback">Blanket sent. Wait for US feedback</option>
									    	<option value="China Team are confirming the colors">China Team are confirming the colors</option>
									    	<option value="Blanket confirmed">Blanket confirmed</option>
									  	</select>
					    			</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalColorLineProgress" class="control-label opacity-75">Colorline Status</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalColorLineProgress" name="ModalColorLineProgress" size="1" style="width: 100%; height: 36px">
									    	<option value="" selected>Enter</option>
									    	<option value="Yibei has received all colors">Yibei has received all colors</option>
									    	<option value="Colorline completed">Colorline completed</option>
									    	<option value="Colorline shipped">Colorline shipped</option>
									  	</select>
					    			</div>
				    			</div>
			    			</div>
			    			<div class="row mb-3">
			    				<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalRollSampleProgress" class="control-label opacity-75">Roll Sample Progress</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalRollSampleProgress" name="ModalRollSampleProgress" size="1" style="width: 100%; height: 36px">
									    	<option value="DNE" selected>Enter</option>
									    	<option value="Roll Samples Arranged">Roll Samples Arranged</option>
									    	<option value="Roll samples completed">Roll samples completed</option>
									    	<option value="Roll samples to Yibei">Roll samples to Yibei</option>
									  	</select>
					    			</div>
				    			</div>
				    			<div class="col-6">
				    				<div class="d-flex flex-column">
				    					<label for="ModalTestingProgress" class="control-label opacity-75">Testing Progress</label>
					    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ModalTestingProgress" name="ModalTestingProgress" size="1" style="width: 100%; height: 36px">
									    	<option value="DNE" selected>Enter</option>
									    	<option value="Testing In Process">Testing In Process</option>
									    	<option value="Test Passed">Passed</option>
									    	<option value="Test Failed">Failed</option>
									  	</select>
					    			</div>
				    			</div>
			    			</div>
				    	</div>
				    </div>
				    <div class="modal-footer">
				    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				    	<button type="submit" class="btn btn-primary" id="confirmExport" data-bs-dismiss="modal">Export</button>
				    </div>
			 	</div>
			</div>
		</div>
	</form>
	
	<script src="js/tracker.js"></script>
	<script type="text/javascript">
		if (filtered) {
			document.getElementById("filteredNav").style.display = "block";
			document.getElementById("unfilteredNav").style.display = "none";
			populateAllInputs();
		}
	</script>
</body>
</html>