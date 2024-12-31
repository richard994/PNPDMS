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
		var view = ${view};
		var edit = ${edit};
		var create = ${create};
		var dev = JSON.parse('${dev}');
		var comments = JSON.parse('${comments}');
		var logs = JSON.parse('${logs}');
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
		<nav aria-label="breadcrumb" id="breadcrumb" style="width: 100%; white-space: nowrap">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item" id="home">Home</li>
		    <li class="breadcrumb-item">Tracker</li>
		    <li class="breadcrumb-item">New Product Development</li>
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
	
	<form id="NDform" name="NDform" action="SaveNewDevService" method="post" enctype="multipart/form-data" onsubmit="return validate()">
	<div class="d-flex justify-content-start" style="gap: 20px; width: 98%; margin-left: 15px">
		<div class="d-flex flex-column" style="gap: 25px">
			
			<div class="container bg-white" id="MainContentContainer">

				<div class="rounded-0 mt-2 overflow-auto" id="progress">
			    	<div class="container-fluid text-center pt-4">
			    		<div class="row d-flex align-items-center justify-content-center">
			    			<div class="col" style="margin-left: 120px" id="StrikeProgressing">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-1-circle-fill" viewBox="0 0 16 16">
								  		<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z"/>
									</svg>
									<span>&nbsp&nbspStrike-off Phase</span>
								</div>
			    			</div>
			    			<div class="col" style="margin-left: 120px; display: none" id="StrikeProgressed">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspStrike-off Phase</span>
								</div>
			    			</div>
			    			<div class="col barcontainer opacity-50 bg-light" id="bar1"><div class="card" style="height:1%; width:80%"></div></div>
			    			<div class="col" id="BlanketNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle" viewBox="0 0 16 16">
								  		<path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z"/>
									</svg>
				    				<span>&nbsp&nbspBlanket Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" id="BlanketProgressing" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle-fill" viewBox="0 0 16 16">
								  		<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24c0-.691.493-1.306 1.336-1.306.756 0 1.313.492 1.313 1.236 0 .697-.469 1.23-.902 1.705l-2.971 3.293V12h5.344v-1.107H7.268v-.077l1.974-2.22.096-.107c.688-.763 1.287-1.428 1.287-2.43 0-1.266-1.031-2.215-2.613-2.215-1.758 0-2.637 1.19-2.637 2.402v.065h1.271v-.07Z"/></svg>
									</svg>
				    				<span>&nbsp&nbspBlanket Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" id="BlanketProgressed" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspBlanket Phase</span>
								</div>
			    			</div>
			    			<div class="col barcontainer opacity-50 bg-light" id="bar2"><div class="card" style="height:1%; width:80%"></div></div>
			    			<div class="col" id="RollNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
									  <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
									</svg>
				    				<span>&nbsp&nbspRoll Sample Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" id="RollProgressing" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
									</svg>
				    				<span>&nbsp&nbspRoll Sample Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" id="RollProgressed" style="display: none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspRoll Sample Phase</span>
								</div>
			    			</div>
			    			<div class="col barcontainer opacity-50 bg-light" id="bar3"><div class="card" style="height:1%; width:80%"></div></div>
			    			<div class="col" style="margin-right: 120px" id="TestNoProgress">
			    				<div class="d-flex align-items-center justify-content-center opacity-50">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
									  <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
									</svg>
				    				<span>&nbsp&nbspTesting Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" style="margin-right: 120px; display: none" id="TestProgressing">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
									  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
									</svg>
				    				<span>&nbsp&nbspTesting Phase</span>
			    				</div>
			    			</div>
			    			<div class="col" id="TestProgressed" style="margin-right: 120px; display:none">
			    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="m10.97 4.97-.02.022-3.473 4.425-2.093-2.094a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05"/>
									</svg>
									<span>&nbsp&nbspTesting Phase</span>
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
						      <img class="d-block w-100" src="img/placeholder-image.jpg" alt="Test Report Image" onerror="this.onerror=null; this.src='img/placeholder-img.jpg';" id="TestPic" name="TestPic">
						      <div class="carousel-caption d-none d-md-block">
						      	<h5>Test Report</h5>
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
						  		Test Report
						  	</button>
						  	<input type="file" name="TestReportInput" id="TestReportInput" accept="image/*" style="display: none"/>
						</div>
		            </div>
		
		            <div style="flex: 2" id="InputFields">
		            	<div class="d-flex" style="gap: 5px; font-size: 18px">
            				<div style="flex: 1">
	            				<label for="Title" class="control-label opacity-75">Title:</label>
		    					<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Title" name="Title" size="1" style="width: 70%; height: 36px" placeholder="Enter">
	    					</div>
	    					<div style="flex: 1">
	            				<label for="Code" class="control-label opacity-75">Code:</label>
		    					<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Code" name="Code" size="1" style="width: 70%; height: 36px" placeholder="Enter">
	    					</div>
	    					<div style="flex: 1">
	            				<label for="Color" class="control-label opacity-75">Color:</label>
		    					<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Color" name="Color" size="1" style="width: 70%; height: 36px" placeholder="Enter">
	    					</div>
		            	</div>
		            	<div class="d-flex mt-3">
		            		<div style="flex: 1" id="ParagonCost">
		            			<div style="display: flex; flex-direction: column; align-items: center; width: 115px">
			            			<span class="rounded p-2" style="background: #4D73FF; color: #FFF; width: 100%; white-space: nowrap">Paragon Cost</span>
			            			<div class="d-flex" style="width: 100%">
			            				<svg xmlns="http://www.w3.org/2000/svg" width="32" height="48" fill="currentColor" class="bi bi-currency-dollar" viewBox="0 0 16 16">
										  <path d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73z"/>
										</svg>
										<input type="text" id="Cost" name="Cost" class="border-0" style="font-size: 38px; width: 100%" placeholder="0.0">
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
								<input class="form-check-input" type="checkbox" id="FCLCB" name="FCLCB">
								<label class="form-check-label" for="FCLCB">
								  400hr FCL
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="PDCB" name="PDCB">
								<label class="form-check-label" for="PDCB">
								  Piece Dyed
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="FeedbackCB" name="FeedbackCB">
								<label class="form-check-label" for="FeedbackCB">
								  Need US Feedback?
								</label>
							</div>
		            		<div class="form-check" style="flex: 1">
								<input class="form-check-input" type="checkbox" id="SDYCB" name="SDYCB">
								<label class="form-check-label" for="SDYCB">
								  SDY
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
							  	</select>
						  	</div>
						  	<div style="flex: 1; transform: translateY(-1px)">
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
		                	<div style="flex: 1">
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
						  	<div style="flex: 1">
			    				<label for="YarnType" class="control-label opacity-75">Yarn Type</label>
			    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="YarnType" name="YarnType" size="1" style="width: 100%; height: 36px">
							    	<option value="" selected>Enter</option>
							    	<option value="YarnDyed">Yarn Dyed</option>
							    	<option value="Piece">Piece Dyed</option>
							    	<option value="Space">Space Dyed</option>
							    	<option value="Jet">Jet Dyed</option>
							  	</select>
						  	</div>
						  	<div style="flex: 1">
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
						  	<div style="flex: 1">
			    				<label for="Content" class="control-label opacity-75">Content<span style="font-size: 12px">(%Mix)</span></label>
			    				<input type="text" class="custom-select border border-light border-2 rounded-0 bg-white" id="Content" name="Content" size="1" style="width: 100%; height: 36px" placeholder="Enter">
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 15px">
		                	<div style="flex: 2">
		                		<div class="d-flex" style="gap: 15px">
		                			<div style="flex: 1">
		                				<div id="StrikeBlock">
						    				<label for="StrikeProgress" class="control-label opacity-75">Strike-Off Progress</label>
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="StrikeProgress" name="StrikeProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="" selected>Enter</option>
										    	<option value="Submitted">Submitted</option>
										    	<option value="Woven">Woven</option>
										    	<option value="Shipped">Shipped</option>
										    	<option value="Confirmed">Confirmed</option>
										    	<option value="Revision">Revision Submitted</option>
										  	</select>
									  	</div>
								  	</div>
								  	<div style="flex: 1">
								  		<div id="BlanketBlock" style="display: none">
						    				<label for="BlanketStatus" class="control-label opacity-75">Blanket Status</label>
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="BlanketStatus" name="BlanketStatus" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="Ready">Ready to Blanket</option>
										    	<option value="Submitted">Blanket Submitted</option>
										    	<option value="Woven">Blanket Woven</option>
										    	<option value="Shipped">Blanket Shipped</option>
										    	<option value="ColorSubmitted">Color Proposal Submitted</option>
										  	</select>
									  	</div>
								  	</div>
							  	</div>
						  	</div>
						  	<div style="flex: 2">
						  		<div id="ColorlineBlock" style="display: none">
						  			<span class="control-label opacity-75">Color Line Progress and Est. Ready Date</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="ColorLineProgress" name="ColorLineProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="" selected>Enter</option>
										    	<option value="Submitted">Proposal Submitted</option>
										    	<option value="Revision">Revision Submitted</option>
										    	<option value="Final">Final Version</option>
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
		            			<div id="RollSampleBlock" style="display: none">
							  		<span class="control-label opacity-75">Roll Sample Progress and Est. Ready Date</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="RollSampleProgress" name="RollSampleProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="GotRotation">Got Colorline Rotation</option>
										    	<option value="Arranged">Roll Sample Arranged</option>
										    	<option value="Finished">Colorline Production Finished</option>
										    	<option value="Shipped">Colorline Shipped</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="RollSampleDatestamp" name="RollSampleDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
							  	</div>
						  	</div>
						  	<div style="flex: 2">
						  		<div id="TestBlock" style="display: none">
						  			<span class="control-label opacity-75">Testing Progress and Est. Ready Date</span>
							  		<div class="d-flex" style="gap: 5px">
							  			<div style="flex: 2">
						    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="TestingProgress" name="TestingProgress" size="1" style="width: 100%; height: 36px">
										    	<option value="DNE" selected>Enter</option>
										    	<option value="Process">Testing In Process</option>
										    	<option value="Passed">Passed</option>
										    	<option value="Failed">Failed</option>
										  	</select>
									  	</div>
									  	<div style="flex: 1">
									  		<input type="date" id="TestingDatestamp" name="TestingDatestamp" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
									  	</div>
								  	</div>
							  	</div>
						  	</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 20px">
		            		<div style="flex: 1">
			            		<label for="CustomsCat" class="control-label opacity-75">Customs Category</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="CustomsCat" name="CustomsCat" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="MOQ" class="control-label opacity-75">MOQ in meters</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="MOQ" name="MOQ" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="Weight" class="control-label opacity-75">Weight in grams</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="Weight" name="Weight" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            	</div>
		            	<div class="d-flex mt-2" style="gap: 20px">
		            		<div style="flex: 1">
			            		<label for="FabricNickname" class="control-label opacity-75">Fabric Nickname</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="FabricNickname" name="FabricNickname" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="NumColorLine" class="control-label opacity-75">Requested # of Colorline</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="NumColorLine" name="NumColorLine" size="1" style="width: 100%; height: 36px" placeholder="Enter">
		            		</div>
		            		<div style="flex: 1">
			            		<label for="PPCM" class="control-label opacity-75">PPCM</label>
			    				<input type="text" class="border border-light border-2 rounded-0 bg-white" id="PPCM" name="PPCM" size="1" style="width: 100%; height: 36px" placeholder="Enter">
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
				   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px; display: none" type="submit" onclick="setFormAction('SaveNewDevService?action=edit&devId=${devid}')" id="editbtn">EDIT</button>
				   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="button" onclick="redirect('SaveNewDevService?action=delete&devId=${devid}')" id="deletebtn">DELETE</button>
			   			</div>
			   		</div>
					
				</div>
			</div>
			
		</div>
		 
		<div class="d-flex flex-column gap-3" id="logBlock" name="logBlock" style="width: 160px; display: none">
			<a id="ShowFullLogHistoryLink" name="ShowFullLogHistoryLink" href="" style="padding-left: 10px; color: #999999; font-size: 14px; text-decoration: underline">Show full log history</a>
		</div>
		
	</div>
	</form>
        
    <script src="js/addnewdev.js"></script>
   	<script type="text/javascript">
		VirtualSelect.init({
			ele: '#Backing'
		});
		
		$(document).ready(function(){
		    $('#carouselImages').carousel({
		      interval: false  // Disables auto-sliding
		    });
		  });
		
		if (view) {
			disableAllInputs(); 
			populateAllInputs();
		} else if (edit) {
			populateAllInputs();
			document.getElementById("savebtn").style.display = "none";
			document.getElementById("editbtn").style.display = "block";
		}
	</script>
</body>
</html>