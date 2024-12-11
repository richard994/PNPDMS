<%@page import="Util.CalcService"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/createqp.css">

	<title>Success</title>
	
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
	
	<script type="text/javascript">		
		function clickPricing() {
			document.getElementById("Pricing").click();
		}
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
		        <li class="nav-item mr-5"><a href="PricingService" class="nav-link" id="Pricing" style="color: #4D73FF">Pricing</a></li>
		        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material">Material</a></li>
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
	    <li class="breadcrumb-item">Pricing</li>
	    <li class="breadcrumb-item">Create Quoted Price</li>
	    <li class="breadcrumb-item">Price Offer</li>
	  </ol>
	</nav>
	
    <div class="pb-0 rounded-0" id="displayqp">
	    <div class="container-fluid text-center py-4 pb-1">
	      <h1 class="display-5 fw-bold mb-2" id="calculatedPrice"><i class="fa fa-cny"></i>${salePrice}</h1>
	      <p class="mb-0">MIJU HOME OFFER</p>
	    </div>
    </div>
    
    <div class="bg-white rounded-0 mt-2 overflow-auto" id="constraint">
    	<div class="container-fluid text-center pt-4">
    		<div class="row d-flex align-items-center justify-content-center">
    			<div class="col" style="margin-left: 120px">
    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
	    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-1-circle-fill" viewBox="0 0 16 16">
					  		<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z"/>
						</svg>
						<span>&nbsp&nbspProduct Design</span>
					</div>
    			</div>
    			<div class="col opacity-50" id="progress1"><div class="card bg-light" style="height:1%; width:80%"></div></div>
    			<div class="col" id="progressed1" style="display: block">
    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
	    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle-fill" viewBox="0 0 16 16">
					  		<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24c0-.691.493-1.306 1.336-1.306.756 0 1.313.492 1.313 1.236 0 .697-.469 1.23-.902 1.705l-2.971 3.293V12h5.344v-1.107H7.268v-.077l1.974-2.22.096-.107c.688-.763 1.287-1.428 1.287-2.43 0-1.266-1.031-2.215-2.613-2.215-1.758 0-2.637 1.19-2.637 2.402v.065h1.271v-.07Z"/></svg>
						</svg>
	    				<span>&nbsp&nbspWarp and Weft</span>
    				</div>
    			</div>
    			<div class="col opacity-50" id="progress3"><div class="card bg-light" style="height:1%; width:80%"></div></div>
    			<div class="col" style="margin-right: 120px; display: block">
    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
						  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
						</svg>
	    				<span>&nbsp&nbspSave Price Offer</span>
    				</div>
    			</div>
    		</div>
    	</div>
   		
   		<div class="container-fluid text-center mx-auto mt-5 rounded-circle pt-3 border-5" style="width: 150px; height: 150px; border: 10px solid #32BF00 !important; display: block" id="checkmark">
	   		<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16" style="color: #32BF00" id="check">
			  <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
			</svg>
		</div>
		
		<div class="container-fluid text-center mx-auto mt-3 mb-3 ml-1" style="width: 250px" id="title2">
			<h4>Successfully Created</h4>
		</div>
		
		<div class="container-fluid text-center mx-auto mt-5" style="width: 500px; display: block" id="afterbtngroup">
			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: white; color: #4D73FF; width: 180px; border: 1px solid #4D73FF !important" onclick="clickPricing()" id="backbtn">Back to price list</button>
			<div style="display: inline-block; width: 30px"></div>
			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: #4D73FF; color: white; width: 180px" onclick="window.location.href='CreateService'" id="morebtn">Add more</button>
		</div>
    </div>

</body>
</html>