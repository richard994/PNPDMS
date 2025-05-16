<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/devsuccess.css">
	<link rel="stylesheet" href="css/createqp.css">
	<title>DevSuccess</title>
	<style>
		.breadcrumb-item::before {
			content: url(discount.png) !important;
			padding-right: 5px !important;
		}
		.nav-link {
			color: black;
		}
	</style>
	<script type="text/javascript">		
		function clickTracker() {
			document.getElementById("Tracker").click();
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
	
	<nav aria-label="breadcrumb" id="breadcrumb" style="margin-left: 165px">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item" id="home">Home</li>
	    <li class="breadcrumb-item">Tracker</li>
	    <li class="breadcrumb-item">New Product Development</li>
	    <li class="breadcrumb-item">New Development Added</li>
	  </ol>
	</nav>
	
	<div class="pb-0 rounded-0" id="displayqp">
	    <div class="container-fluid text-center py-4 pb-1">
	      <h1 class="display-5 fw-bold mb-2">Paragon Development Tracker</h1>
	      <p class="mb-0">Check Status on Tracker Page</p>
	    </div>
    </div>
   	
   	<div class="bg-white rounded-0 mt-2 overflow-auto" id="constraint">
		<div class="container-fluid text-center mx-auto mt-5 rounded-circle pt-3 border-5" style="width: 150px; height: 150px; border: 10px solid #32BF00 !important; display: block" id="checkmark">
	   		<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16" style="color: #32BF00" id="check">
			  <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
			</svg>
		</div>
		
		<div class="container-fluid text-center mx-auto mt-3 mb-3 ml-1" style="width: 250px" id="title2">
			<h4>Successfully Created</h4>
		</div>
		
		<div class="container-fluid text-center mx-auto mt-5 mb-5" style="width: 500px; display: block" id="afterbtngroup">
			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: white; color: #4D73FF; width: 180px; border: 1px solid #4D73FF !important" onclick="clickTracker()" id="backbtn">Back to tracker</button>
			<div style="display: inline-block; width: 30px"></div>
			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: #4D73FF; color: white; width: 180px" onclick="window.location.href='NewDevService?action=create'" id="morebtn">Add more</button>
		</div>
	</div>

</body>
</html>