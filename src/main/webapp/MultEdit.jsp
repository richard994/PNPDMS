<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="js/virtual-select.min.css">
	<link rel="stylesheet" type="text/css" href="css/MultEdit.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="js/virtual-select.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<title>MultEdit</title>
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
		var dev = ${dev};
		let devids = [];
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
	
	<div class="container" id="breadcrumbContainer">
		<nav aria-label="breadcrumb" id="breadcrumb" style="width: 100%; white-space: nowrap">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item" id="home">Home</li>
		    <li class="breadcrumb-item">Tracker</li>
		    <li class="breadcrumb-item">Edit Multiple Product Developments</li>
		  </ol>
		</nav>
	</div>
	
	<div class="input-group border-bottom pb-4 mb-4" style="width: 1250px; margin-left: 165px">
		<div class="hstack gap-3">
			<div class="input-group-prepend">
				<span class="input-group-text bg-light border-light">Product Code</span>
			</div>
			<input type="text" id="titleCode" name="titleCode" class="form-control border rounded-0 me-auto" placeholder="Enter"/>
			
			<button class="btn border-0" type="button" id="add" style="background-color: #4D73FF; color: white; width: 180px" onclick="search()">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
				  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
				</svg>
				Add
			</button>
		</div>
	</div>
	<form id="MEform" name="MEform" action="SaveNewDevService" method="post">
	<div id="MainContent" style="width: 1250px; margin-left: 165px"></div>
	
	<div class="container-fluid text-center mx-auto mt-4" style="width: 300px" id="savecontainer">
    	<div class="d-flex" style="gap: 10px">
   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="button" onclick="saveall()" id="editbtn">SAVE</button>
   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="button" onclick="showBackModal()" id="backbtn">Back</button>
		</div>
	</div>
	</form>
	
	<div class="modal fade" id="bkModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="bkModalLabel" aria-hidden="true">
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
					&nbsp&nbspGoing back to Tracker, changes are not saved.
			    </div>
			    <div class="modal-footer">
			    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			    	<button type="button" class="btn btn-primary" id="confirmDelete" data-bs-dismiss="modal" onclick="refresh()">Confirm</button>
			    </div>
		 	</div>
		</div>
	</div>
	
	<script src="js/multedit.js"></script>
</body>
</html>