<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="js/virtual-select.min.css">
	<link rel="stylesheet" type="text/css" href="css/addnewdev.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="js/virtual-select.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<title>AddNewDev</title>
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
		var devid = ${devid};
		var view = ${view};
		var edit = ${edit};
		var create = ${create};
		var user = '${user}';
		var dev = ${dev};
		var comments = ${comments};
		var logs = ${logs};
		var fullLogs = ${fulllogs};
	</script>
</head>
<body class="bg-light">
	<div id="empty" style="display: hidden"></div>
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
		<nav aria-label="breadcrumb" id="breadcrumb" style="width: 100%; white-space: nowrap">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item" id="home">Home</li>
		    <li class="breadcrumb-item">Tracker</li>
		    <li class="breadcrumb-item">New Product Development</li>
		  </ol>
		</nav>
		<!--  
		<div class="container">
			<div style="float: right">
				<img class="language" src="img/language.svg" alt="language" />
				<select class="custom-select border border-black border-2 rounded-0 bg-white" id="Language" name="Language" size="1" style="width: 50px; height: 25px">
			    	<option value="EN" selected>EN</option>
			    	<option value="CN">CN</option>
			  	</select>
		  	</div>
	  	</div>
	  	-->
	</div>
	
	<form id="NDform" name="NDform" action="SaveNewDevService" method="post" enctype="multipart/form-data" onsubmit="return validate()">
	<div class="d-flex justify-content-start" style="gap: 20px; width: 98%; margin-left: 15px">
		<div class="d-flex flex-column" style="gap: 25px">
			
			<div class="container bg-white" id="MainContentContainer">

				<div class="rounded-0 mt-2 overflow-auto" id="progress">
			    	<div class="container-fluid text-center pt-4">
			    		<div class="row d-flex align-items-center justify-content-center">
			    			<div class="col" style="margin-left: 120px" id="StrikeNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-1-circle" viewBox="0 0 16 16">
									  <path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8m15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0M9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383z"/>
									</svg>
				    				<span>&nbsp&nbspStrike-off</span>
			    				</div>
			    			</div>
			    			<div class="col" style="margin-left: 120px; display: none" id="StrikeProgressing">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-1-circle-fill" viewBox="0 0 16 16">
								  		<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z"/>
									</svg>
									<span>&nbsp&nbspStrike-off</span>
								</div>
			    			</div>
			    			<div class="col" style="margin-left: 120px; display: none" id="StrikeProgressed">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspStrike-off</span>
								</div>
			    			</div>
			    			<div class="col" id="BlanketNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle" viewBox="0 0 16 16">
								  		<path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z"/>
									</svg>
				    				<span>&nbsp&nbspBlanket</span>
			    				</div>
			    			</div>
			    			<div class="col" id="BlanketProgressing" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle-fill" viewBox="0 0 16 16">
								  		<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24c0-.691.493-1.306 1.336-1.306.756 0 1.313.492 1.313 1.236 0 .697-.469 1.23-.902 1.705l-2.971 3.293V12h5.344v-1.107H7.268v-.077l1.974-2.22.096-.107c.688-.763 1.287-1.428 1.287-2.43 0-1.266-1.031-2.215-2.613-2.215-1.758 0-2.637 1.19-2.637 2.402v.065h1.271v-.07Z"/></svg>
									</svg>
				    				<span>&nbsp&nbspBlanket</span>
			    				</div>
			    			</div>
			    			<div class="col" id="BlanketProgressed" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspBlanket</span>
								</div>
			    			</div>
			    			<div class="col" id="RollNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
									  <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
									</svg>
				    				<span>&nbsp&nbspRoll Sample</span>
			    				</div>
			    			</div>
			    			<div class="col" id="RollProgressing" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
									</svg>
				    				<span>&nbsp&nbspRoll Sample</span>
			    				</div>
			    			</div>
			    			<div class="col" id="RollProgressed" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspRoll Sample</span>
								</div>
			    			</div>
			    			<div class="col" style="margin-right: 120px" id="TestNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
									  <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
									</svg>
				    				<span>&nbsp&nbspTesting</span>
			    				</div>
			    			</div>
			    			<div class="col" style="margin-right: 120px; display: none" id="TestProgressing">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
									</svg>
				    				<span>&nbsp&nbspTesting</span>
			    				</div>
			    			</div>
			    			<div class="col" id="TestProgressed" style="margin-right: 120px; display:none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspTesting</span>
								</div>
			    			</div>
			    		</div>
			    	</div>
			    </div>
			    
			    <div class="d-flex mt-5 mb-3" id="MainContent">
		            <div style="flex: 1; padding-right: 20px" id="FabricImage">
		            	<div id="carouselImages" class="carousel carousel-dark slide">
						  <div class="carousel-indicators">
						    <button type="button" data-bs-target="#carouselImages" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
						    <button type="button" data-bs-target="#carouselImages" data-bs-slide-to="1" aria-label="Slide 2"></button>
						    <button type="button" data-bs-target="#carouselImages" data-bs-slide-to="2" aria-label="Slide 3"></button>
						  </div>
						  <div class="carousel-inner">
						    <div class="carousel-item active">
						      <img class="d-block w-100" src="img/placeholder-image.jpg" alt="Fabric Image" onerror="this.onerror=null; this.src='img/placeholder-img.jpg'" id="FabricPic" name="FabricPic">
						      <div class="carousel-caption d-none d-md-block">
							      <h5>Fabric Picture</h5>
							      <p>Note: This will show as front image</p>
						      </div>
						    </div>
						    <div class="carousel-item">
						      <img class="d-block w-100" src="img/placeholder-image.jpg" alt="PID Image" onerror="this.onerror=null; this.src='img/placeholder-img.jpg';" id="PidPic" name="PidPic">
						      <div class="carousel-caption d-none d-md-block">
						      	<h5>PID</h5>
						      </div>
						    </div>
						    <div class="carousel-item">
						      <img class="d-block w-100" src="img/placeholder-image.jpg" alt="Colorline Image" onerror="this.onerror=null; this.src='img/placeholder-img.jpg';" id="TestPic" name="TestPic">
						      <div class="carousel-caption d-none d-md-block">
						      	<h5>Colorline Picture</h5>
						      </div>
						    </div>
						  </div>
						  <button class="carousel-control-prev" type="button" data-bs-target="#carouselImages" data-bs-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						  </button>
						  <button class="carousel-control-next" type="button" data-bs-target="#carouselImages" data-bs-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						  </button>
						</div>
						<span style="font-size: 20px">Upload: </span>
						<div class="d-flex" id="imageBtnGroup" style="gap: 10px; white-space: nowrap">
							<button class="btn border-2 imgbtn" type="button" id="FabricPicBtn">
						  		Fabric Picture
						  	</button>
						  	<input type="file" name="FabricPicInput" id="FabricPicInput" accept="image/*" style="display: none"/>
						  	
						  	<button class="btn border-2 imgbtn" type="button" id="PidBtn">
						  		PID
						  	</button>
						  	<input type="file" name="PidInput" id="PidInput" accept="image/*" style="display: none"/>
						  	
						  	<button class="btn border-2 imgbtn" type="button" id="TestReportBtn">
						  		Colorline Picture
						  	</button>
						  	<input type="file" name="TestReportInput" id="TestReportInput" accept="image/*" style="display: none"/>
						</div>
		            </div>
		
		            <div style="flex: 2" id="InputFields">
		            	<div class="d-flex" style="gap: 5px; font-size: 18px">
	    					<div style="flex: 1">
	            				<label for="Code" class="control-label opacity-75">Code:</label>
		    					<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Code" name="Code" size="1" style="width: 70%; height: 36px" placeholder="Enter">
	    					</div>
	    					<div style="flex: 1">
	            				<label for="Color" class="control-label opacity-75">Pattern:</label>
		    					<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Color" name="Color" size="1" style="width: 70%; height: 36px" placeholder="Enter">
	    					</div>
		            	</div>
		            	<div class="d-flex mt-3">
		            		<div style="flex: 1" id="ParagonCost">
		            			<div style="display: flex; flex-direction: column; align-items: center; width: 115px">
			            			<span class="rounded p-2" style="background: #4D73FF; color: #FFF; width: 100%; white-space: nowrap">Paragon Cost</span>
			            			<div class="d-flex" style="width: 100%">
			            				<svg xmlns="http://www.w3.org/2000/svg" width="32" height="48" fill="currentColor" class="bi bi-currency-yen" viewBox="0 0 16 16">
										  <path d="M8.75 14v-2.629h2.446v-.967H8.75v-1.31h2.445v-.967H9.128L12.5 2h-1.699L8.047 7.327h-.086L5.207 2H3.5l3.363 6.127H4.778v.968H7.25v1.31H4.78v.966h2.47V14h1.502z"/>
										</svg>
										<input type="text" id="Cost" name="Cost" class="border-0" style="font-size: 32px; width: 100%" placeholder="0.0">
			            			</div>
		            			</div>
		            		</div>
		            		<div class="mt-5" style="flex: 2">
		            			<span class="mt-3" style="float: right; font-size: 20px">Time spent in the current phase: <span style="color: red" id="CurrDaySpent" name="CurrDaySpent">0</span> days</span>
		            		</div>
		            	</div>
		            	<div class="d-flex mt-4" id="CheckBoxes" style="white-space: nowrap; gap: 30px">
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="ParagonCleanCB" name="ParagonCleanCB">
								<label class="form-check-label" for="ParagonCleanCB">
								  Paragon Clean
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="SDYCB" name="SDYCB">
								<label class="form-check-label" for="SDYCB">
								  Normal SDY
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="FCLCB" name="FCLCB">
								<label class="form-check-label" for="FCLCB">
								  500hr FCL
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="TFCLCB" name="TFCLCB">
								<label class="form-check-label" for="TFCLCB">
								  1000hr FCL
								</label>
							</div>
		            	</div>
		            	<div class="d-flex mt-4" id="CheckBoxes" style="white-space: nowrap; gap: 30px">
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="FeedbackCB" name="FeedbackCB">
								<label class="form-check-label" for="FeedbackCB">
								  Need US Feedback?
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="ChinaFeedbackCB" name="ChinaFeedbackCB">
								<label class="form-check-label" for="ChinaFeedbackCB">
								  Need China Feedback?
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="PDCB" name="PDCB">
								<label class="form-check-label" for="PDCB">
								  Piece Dyed
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="ChenilleCB" name="ChenilleCB">
								<label class="form-check-label" for="ChenilleCB">
								  Chenille
								</label>
							</div>
		            	</div>
		            	<div class="d-flex mt-4" id="CheckBoxes" style="white-space: nowrap; gap: 30px">
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="KnitCB" name="KnitCB">
								<label class="form-check-label" for="KnitCB">
								  Knit
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="GeorgeCancelCB" name="GeorgeCancelCB">
								<label class="form-check-label" for="GeorgeCancelCB">
								  George Canceled
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="inactiveCB" name="inactiveCB">
								<label class="form-check-label" for="inactiveCB">
								  Inactive
								</label>
							</div>
							<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="priceConfirmCB" name="priceConfirmCB">
								<label class="form-check-label" for="priceConfirmCB">
								  Price Confirmed
								</label>
							</div>
		            	</div>
		                <div class="d-flex mt-4" style="gap: 15px">
		                	<div style="flex: 1">
			    				<label for="FabricType" class="control-label opacity-75">Fabric Type</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="FabricType" name="FabricType" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="Base">Base</option>
							    	<option value="Jaquard">Jaquard</option>
							  	</select>
						  	</div>
						  	<div style="flex: 1">
			    				<label for="DesignType" class="control-label opacity-75">Design Type</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="DesignType" name="DesignType" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="NewDesign">New Design</option>
							    	<option value="Reshow">Reshow</option>
							    	<option value="PCR">PCR</option>
							    	<option value="24SP to 24FA">24SP to 24FA</option>
							    	<option value="24FA to 25SP">24FA to 25SP</option>
							    	<option value="24FA to 25FA">24FA to 25FA</option>
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
						  	<div style="flex: 1">
			    				<label for="Colorist" class="control-label opacity-75">Colorist</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Colorist" name="Colorist" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="house">House</option>
							    	<option value="marteen">Marteen</option>
							    	<option value="crowder">Crowder</option>
							    	<option value="derocher">Derocher</option>
							    	<option value="leon">Leon</option>
							  	</select>
						  	</div>
						  	<div style="flex: 1">
			    				<label for="Designer" class="control-label opacity-75">Designer</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Designer" name="Designer" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="house">House</option>
							    	<option value="Outsource">Outsource</option>
							    	<option value="crowder">Crowder</option>
							    	<option value="derocher">Derocher</option>
							    	<option value="leon">Leon</option>
							  	</select>
						  	</div>
		                </div>
		                <div class="d-flex mt-2" style="gap: 15px">
		                	<div style="flex: 1">
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
						  	<div style="flex: 1">
			    				<label for="YarnType" class="control-label opacity-75">Style</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="YarnType" name="YarnType" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="Traditional">Traditional</option>
							    	<option value="Transitional">Transitional</option>
							    	<option value="Contemporary">Contemporary</option>
							    	<option value="Modern">Modern</option>
							  	</select>
						  	</div>
						  	<div style="flex: 1">
			    				<label for="WarpType" class="control-label opacity-75">Warp Type</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="WarpType" name="WarpType" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="9600 150D Solid">9600 150D Solid</option>
							    	<option value="9600 150D E&E">9600 150D E&E</option>
							    	<option value="9600 150D 6-End Tapestry">9600 150D 6-End Tapestry</option>
							    	<option value="4860 SDY Solid">4860 SDY Solid</option>
							    	<option value="4800 150D Space Dye">4800 150D Space Dye</option>
							    	<option value="4800 150D Solid">4800 150D Solid</option>
							    	<option value="4800 Double Beam">4800 Double Beam</option>
							    	<option value="4800 4-End Warps">4800 4-End Warps</option>
							    	<option value="4800 3-End Warps">4800 3-End Warps</option>
							    	<option value="4800 150D E&E">4800 150D E&E</option>
							    	<option value="Jenny Warp">Jenny Warp</option>
							  	</select>
						  	</div>
						  	<div style="flex: 1">
			    				<label for="Direction" class="control-label opacity-75">Direction</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Direction" name="Direction" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="RR">RR</option>
							    	<option value="UTR">UTR</option>
							  	</select>
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 15px">
		            		<div style="flex: 1">
			    				<label for="Content" class="control-label opacity-75">Content<span style="font-size: 12px">(%Mix)</span></label>
			    				<input class="custom-select border border-light border-2 rounded-0 bg-white" id="Content" name="Content" size="1" style="width: 100%; height: 36px" placeholder="Enter" readonly/>
						  	</div>
						  	<div style="flex: 1">
			    				<label for="Backing" class="control-label opacity-75">Finishing</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Backing" name="Backing" size="1" placeholder="Select Multiple" data-search="true" data-silent-initial-value-set="true" style="width: 100%; height: 36px" multiple>
							    	<option value="KC">KC</option>
							    	<option value="NP">NP</option>
							    	<option value="HS">HS</option>
							    	<option value="TC">TC</option>
							    	<option value="AW">AW</option>
							    	<option value="SPB">SPB</option>
							    	<option value="FB">FB</option>
							    	<option value="KB">KB</option>
							  	</select>
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 15px">
		                	<div style="flex: 2">
                				<div id="StrikeBlock">
                					<span class="control-label opacity-75">Stike-off Progress</span>
                					<div class="d-flex" style="gap: 5px">
                						<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="StrikeProgress" name="StrikeProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="Strike-off submitted">Strike-off submitted</option>
										    	<option value="Strike-off producing">Strike-off producing</option>
										    	<option value="Strike-off shipped, awaiting initial feedback from US">Strike-off shipped, awaiting initial feedback from US</option>
										    	<option value="One team confirmed; both parties in discussion, see comment">One team confirmed; both parties in discussion, see comment</option>
										    	<option value="US team is submitting a revision">US team is submitting a revision</option>
										    	<option value="Confirm to drop">Confirm to drop</option>
										    	<option value="Strike-off confirmed by both teams">Strike-off confirmed by both teams</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="StrikeBirthday" name="StrikeBirthday" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
								  </div>
						  	</div>
						  	<div style="flex: 2">
		                		<div class="d-flex" style="gap: 15px">
								  	<div style="flex: 1">
								  		<div id="BlanketBlock">
								  			<span class="control-label opacity-75">Blanket Status</span>
								  			<div class="d-flex" style="gap: 5px">
								  				<div style="flex: 2">
								  					<select class="custom-select border border-light border-2 rounded-0 bg-white" id="BlanketStatus" name="BlanketStatus" size="1" style="width: 100%; height: 36px">
												    	<option value="DNE" selected>Enter</option>
												    	<option value="Strike-off confirmed. Wait for US blanket proceeding">Strike-off confirmed. Wait for US blanket proceeding</option>
												    	<option value="US blanket submitted">US blanket submitted</option>
												    	<option value="Blanket in production">Blanket in production</option>
												    	<option value="Blanket shipped, awaiting initial feedback from US">Blanket shipped, awaiting initial feedback from US</option>
												    	<option value="Colorline proposal submitted">Colorline proposal submitted</option>
												    	<option value="US color revisions requested, see comment">US color revisions requested, see comment</option>
												    	<option value="China team revisions suggested, see comment">China team revisions suggested, see comment</option>
												    	<option value="Confirm to drop">Confirm to drop</option>
												    	<option value="Colorline confirmed">Colorline confirmed</option>
												  	</select>
								  				</div>
								  				<div style="flex: 1">
											  		<input type="date" id="BlanketDatestamp" name="BlanketDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
											  	</div>
								  			</div>
									  	</div>
								  	</div>
							  	</div>
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 15px">
		            		<div style="flex: 2">
		            			<div id="RollSampleBlock">
							  		<span class="control-label opacity-75">Roll Sample Progress and Est. Ready Date</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="RollSampleProgress" name="RollSampleProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="Roll Samples Arranged">Roll Samples Arranged</option>
										    	<option value="Roll samples completed">Roll samples completed</option>
										    	<option value="Roll samples to Yibei">Roll samples to Yibei</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="RollSampleDatestamp" name="RollSampleDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
							  	</div>
						  	</div>
		            		<div style="flex: 2">
						  		<div id="ColorlineBlock">
						  			<span class="control-label opacity-75">Color Line Progress and Est. Ready Date</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ColorLineProgress" name="ColorLineProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="" selected>Enter</option>
										    	<option value="Yibei has received all colors">Yibei has received all colors</option>
										    	<option value="Colorline completed">Colorline completed</option>
										    	<option value="Colorline shipped">Colorline shipped</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="ColorlineDatestamp" name="ColorlineDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
							  	</div>
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 15px">
		                	<div style="flex: 2">
						  		<div id="TestBlock">
						  			<span class="control-label opacity-75">Testing Progress</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="TestingProgress" name="TestingProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="Testing in progress">Testing in progress</option>
										    	<option value="Under improvement and retesting">Under improvement and retesting</option>
										    	<option value="Testing failed, awaiting US feedback, see comment">Testing failed, awaiting US feedback, see comment</option>
										    	<option value="Testing confirmed to proceed">Testing confirmed to proceed</option>
										    	<option value="Testing passed">Testing passed</option>
										    	<option value="Testing failed, product dropped">Testing failed, product dropped</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="TestingDatestamp" name="TestingDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
							  	</div>
						  	</div>
						  	<div style="flex: 2"></div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 20px">
		            		<div style="flex: 1">
			            		<label for="MOQ" class="control-label opacity-75">MOQ in meters</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="MOQ" name="MOQ" size="1" style="width: 100%; height: 36px" placeholder="Enter" readonly>
		            		</div>
		            		<div style="flex: 1">
			            		<label for="Weight" class="control-label opacity-75">Weight in grams</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Weight" name="Weight" size="1" style="width: 100%; height: 36px" placeholder="Enter" readonly>
		            		</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 20px">
		            		<div style="flex: 1">
			            		<label for="NumColorLine" class="control-label opacity-75">Requested # of Colorline</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="NumColorLine" name="NumColorLine" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="PPCM" class="control-label opacity-75">PPCM</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="PPCM" name="PPCM" size="1" style="width: 100%; height: 36px" placeholder="Enter" readonly>
		            		</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 20px">
		            		<div style="flex: 1">
			            		<label for="NumColor" class="control-label opacity-75">Number of Colors</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="NumColor" name="NumColor" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="NumTotalColor" class="control-label opacity-75">Number of Total Colors</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="NumTotalColor" name="NumTotalColor" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            	</div>
		            	<div class="mt-2">
		            		<label for="Note" class="control-label opacity-75">Note (maximum 1000 characters)</label>
		    				<textarea class="custom-select border border-light border-2 rounded-0 bg-white" id="Note" name="Note" size="1" style="width: 100%; height: 90px" placeholder="Enter"></textarea>
		            	</div>
		        	</div>
			    </div>
		   		
			</div>
			
			<div class="container bg-white mb-3 pb-3" id="CommentContainer">
				<div class="d-flex flex-column" style="gap: 15px">
				
					<div class="d-flex mt-3" id="commentBtnGroupContainer">
						<div style="flex: 2">
							<span style="font-size: 30px; color: #4D73FF">Comments</span>
						</div>
						<div style="flex: 3">
							<div class="d-flex" id="commentBtnGroup" style="gap: 10px; white-space: nowrap">
								<button class="btn border-2 commentbtn" type="button" id="LeahCommentBtn">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
									  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
									</svg>
							  		Add Leah Comment
							  	</button>
							  	<button class="btn border-2 commentbtn" type="button" id="USCommentBtn">
							  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
									  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
									</svg>
							  		Add US Comment
							  	</button>
							  	<button class="btn border-2 commentbtn" type="button" id="MillCommentBtn">
							  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
									  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
									</svg>
							  		Add Mill Comment
							  	</button>
							  	<button class="btn border-2 commentbtn" type="button" id="GeorgeCommentBtn">
							  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
									  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
									</svg>
							  		Add George Comment
							  	</button>
							</div>
						</div>
					</div>
					
					<div class="d-flex p-2 commentBlocks" style="gap: 15px">
						<div style="flex: 2">
							<div class="d-flex bg-light p-2 flex-column" id="LeahCommentBlock">
								<span style="font-size: 30px">Leah Comments</span>
								<div class="d-flex flex-column mt-2 mb-3" style="gap: 5px" id="LeahComments">
									<div class="comment p-2" id="Leah-comment-input-block" style="display: none">
										<div class="d-flex" style="gap: 5px">
											<div style="flex: 4">
												<input type="text" class="border-0 rounded-0 bg-white" id="LeahCommentInput" name="LeahCommentInput" size="1" style="width: 100%; height: 100%" placeholder="Enter">
											</div>
											<div style="flex: 1">
												<input type="date" id="LeahCommentDatestamp" name="LeahCommentDatestamp" class="border-0 rounded-0 bg-white" size="1" style="width: 100%; height: 100%">
											</div>
										</div>
									</div>
									<div class="comment p-2" id="placeholder-comment-Leah">
										<span style="opacity: 0.5">Nothing Yet</span>
									</div>
								</div>
							</div>
						</div>
						<div style="flex: 2">
							<div class="d-flex bg-light p-2 flex-column" id="USCommentBlock">
								<span style="font-size: 30px">US Comments</span>
								<div class="d-flex flex-column mt-2 mb-3" style="gap: 5px" id="USComments">
									<div class="comment p-2" id="US-comment-input-block" style="display: none">
										<div class="d-flex" style="gap: 5px">
											<div style="flex: 4">
												<input type="text" class="border-0 rounded-0 bg-white" id="USCommentInput" name="USCommentInput" size="1" style="width: 100%; height: 100%" placeholder="Enter">
											</div>
											<div style="flex: 1">
												<input type="date" id="USCommentDatestamp" name="USCommentDatestamp" class="border-0 rounded-0 bg-white" size="1" style="width: 100%; height: 100%">
											</div>
										</div>
									</div>
									<div class="comment p-2" id="placeholder-comment-US">
										<div class="commentTexts" style="flex: 3">
											<span style="opacity: 0.5">Nothing Yet</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="d-flex p-2 commentBlocks" style="gap: 15px">
						<div style="flex: 2">
							<div class="d-flex bg-light p-2 flex-column" id="MillCommentBlock">
								<span style="font-size: 30px">Mill Comments</span>
								<div class="d-flex flex-column mt-2 mb-3" style="gap: 5px" id="MillComments">
									<div class="comment p-2" id="Mill-comment-input-block" style="display: none">
										<div class="d-flex" style="gap: 5px">
											<div style="flex: 4">
												<input type="text" class="border-0 rounded-0 bg-white" id="MillCommentInput" name="MillCommentInput" size="1" style="width: 100%; height: 100%" placeholder="Enter">
											</div>
											<div style="flex: 1">
												<input type="date" id="MillCommentDatestamp" name="MillCommentDatestamp" class="border-0 rounded-0 bg-white" size="1" style="width: 100%; height: 100%">
											</div>
										</div>
									</div>
									<div class="comment p-2" id="placeholder-comment-Mill">
										<div class="commentTexts" style="flex: 3">
											<span style="opacity: 0.5">Nothing Yet</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="flex: 2">
							<div class="d-flex bg-light p-2 flex-column" id="GeorgeCommentBlock">
								<span style="font-size: 30px">George Comments</span>
								<div class="d-flex flex-column mt-2 mb-3" style="gap: 5px" id="GeorgeComments">
									<div class="comment p-2" id="George-comment-input-block" style="display: none">
										<div class="d-flex" style="gap: 5px">
											<div style="flex: 4">
												<input type="text" class="border-0 rounded-0 bg-white" id="GeorgeCommentInput" name="GeorgeCommentInput" size="1" style="width: 100%; height: 100%" placeholder="Enter">
											</div>
											<div style="flex: 1">
												<input type="date" id="GeorgeCommentDatestamp" name="GeorgeCommentDatestamp" class="border-0 rounded-0 bg-white" size="1" style="width: 100%; height: 100%">
											</div>
										</div>
									</div>
									<div class="comment p-2" id="placeholder-comment-George">
										<div class="commentTexts" style="flex: 3">
											<span style="opacity: 0.5">Nothing Yet</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid text-center mx-auto mt-2" style="width: 260px" id="savecontainer">
				    	<div class="d-flex" style="gap: 10px">
				   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="submit" id="savebtn">SAVE</button>
				   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px; display: none" type="submit" onclick="setFormAction('SaveNewDevService?action=edit&devId=${devid}')" id="editbtn">SAVE</button>
				   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="button" onclick="showModal()" id="deletebtn">DELETE</button>
			   			</div>
			   		</div>
					
				</div>
			</div>
			
		</div>
		 
		<div class="d-flex flex-column gap-3" id="logBlock" name="logBlock" style="width: 160px; display: none">
			<a id="ShowFullLogHistoryLink" href="#" name="ShowFullLogHistoryLink" style="padding-left: 10px; color: #999999; font-size: 14px; text-decoration: underline" onclick="showFullLogModal(event)">Show full log history</a>
		</div>
		
		<div class="modal fade" id="dlCmtModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="dlModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">Attention</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16" style="color: red">
				  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				  <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
				</svg>&nbsp&nbspPlease confirm deletion of comment.
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="confirmCmtDelete"  data-bs-dismiss="modal">Confirm</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="dlModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="dlModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
				    <div class="modal-header">
				    	<h5 class="modal-title" id="staticBackdropLabel">Attention</h5>
				    		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	    			</div>
				    <div class="modal-body">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16" style="color: red">
							<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
							<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
						</svg>
						&nbsp&nbspPlease confirm deletion of the development.
				    </div>
				    <div class="modal-footer">
				    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				    	<button type="button" class="btn btn-primary" id="confirmDelete" data-bs-dismiss="modal" onclick="redirect('SaveNewDevService?action=delete&devId=${devid}')">Confirm</button>
				    </div>
			 	</div>
			</div>
		</div>
		
		<div class="modal fade" id="showFullLogModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="showFullLogModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
				    <div class="modal-header">
				    	<h5 class="modal-title" id="staticBackdropLabel">Full Log History</h5>
			    		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	    			</div>
				    <div class="modal-body">
				    	<table class="table" id="logTable">
				            <thead>
				              <tr>
				                <th>Name</th>
				                <th>Date</th>
				                <th>Content</th>
				              </tr>
				            </thead>
				            <tbody>
				            </tbody>
			            </table>
				    </div>
				    <div class="modal-footer">
				    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				    </div>
			 	</div>
			</div>
		</div>
		
	</div>
	</form>
        
    <script src="js/addnewdev.js"></script>
   	<script type="text/javascript">
   		let scrollPosition = window.scrollY;
   		
		VirtualSelect.init({
			ele: '#Backing'
		});
		
		window.scrollTo(0, scrollPosition);
		
		$(document).ready(function(){
		    $('#carouselImages').carousel({
		      interval: false  // Disables auto-sliding
		    });
		  });
		
		if (view) {
			disableAllInputs(); 
			populateAllInputs();
			// Disable the Virtual Select instance
			document.querySelector('#Backing').disable();
		} else if (edit) {
			populateAllInputs();
			document.getElementById("savebtn").style.display = "none";
			document.getElementById("editbtn").style.display = "block";
		}
	</script>
</body>
</html>