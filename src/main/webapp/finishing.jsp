<%@page import="Util.FinishService"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="index.css">
	<title>Finishing</title>
	
	<style>
		body {
			font-family: 'Inter', sans-serif !important;
		}
		[data-title]:hover:after {
		    opacity: 1;
		    transition: all 0.1s ease 0.5s;
		    visibility: visible;
		}
		[data-title]:after {
		    content: attr(data-title);
    		background-color: #696969;
    		color: white;
    		font-size: 14px;
    		visibility: hidden;
    		position: absolute;
    		white-space: nowrap;
    		padding: 1px 5px 2px 5px;
		}
		[data-title] {
			position: relative;
		}
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
		        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material">Material</a></li>
		        <li class="nav-item mr-5"><a href="FinishService" class="nav-link" id="Finishing" style="color: #4D73FF">Finishing</a></li>
		        <li class="nav-item mr-5"><a href="TrackerService" class="nav-link" id="Tracker">Tracker</a></li>
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
	    <li class="breadcrumb-item">Finishing</li>
	  </ol>
	</nav>
	
	<div class="container bg-white mt-5" style="margin-left: 165px; width: 85%">
		<h3 class="p-3">Finishing Module(${numFinish})</h3>
		
		<div class="container">
		<%List<String> finishings = 
            (List<String>)request.getAttribute("finishingsList");
		  int numFinish = Integer.parseInt(request.getAttribute("numFinish").toString());
          for(int i=0; i<numFinish;){%>
			<div class="row justify-content-between" style="width: 100%">
			    <div class="col-sm">
			      <table class="table table-bordered mt-3">
			      	<thead class="table-light">
			      	  <tr>
			      		<th scope="col" style="width: 40px">#</th>
		     			<th scope="col" style="font-weight: normal">Module Name</th>
			      	  </tr>
			      	</thead>
			      	<tbody class="border-top-0">
					    <tr>
					      <th scope="row" style="width: 40px"><%=i+1%></th>
					      <td style="height: 65px; white-space: nowrap; position: relative">
					      	<%=finishings.get(i++)%>
					      	<button type="button" class="btn btn-secondary border-0 bg-white text-black shadow-none" data-bs-toggle="tooltip" data-bs-placement="bottom" data-title="Explanations" style="position: absolute; right: 0; top: 0">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
								  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
								  <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
								</svg>
							</button>
					      </td>
					    </tr>
					 </tbody>
			      </table>
			 	 </div>
			 	 <div class="col-sm">
			      <table class="table table-bordered mt-3">
			      	<thead class="table-light">
			      	  <tr>
			      		<th scope="col" style="width: 40px">#</th>
		     			<th scope="col" style="font-weight: normal">Module Name</th>
			      	  </tr>
			      	</thead>
			      	<tbody class="border-top-0">
					    <tr>
					      <th scope="row" style="width: 40px"><%=i+1%></th>
					      <td style="height: 65px; white-space: nowrap; position: relative">
					      	<%=finishings.get(i++)%>
			      			<button type="button" class="btn btn-secondary border-0 bg-white text-black shadow-none" data-bs-toggle="tooltip" data-bs-placement="bottom" data-title="Explanations" style="position: absolute; right: 0; top: 0">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
								  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
								  <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
								</svg>
							</button>
					      </td>
					    </tr>
					 </tbody>
			      </table>
			 	 </div>
			 	 <div class="col-sm">
			      <table class="table table-bordered mt-3">
			      	<thead class="table-light">
			      	  <tr>
			      		<th scope="col" style="width: 40px">#</th>
		     			<th scope="col" style="font-weight: normal">Module Name</th>
			      	  </tr>
			      	</thead>
			      	<tbody class="border-top-0">
					    <tr>
					      <th scope="row" style="width: 40px"><%=i+1%></th>
					      <td style="height: 65px; white-space: nowrap; position: relative">
					      	<%=finishings.get(i++)%>
					      	<button type="button" class="btn btn-secondary border-0 bg-white text-black shadow-none" data-bs-toggle="tooltip" data-bs-placement="bottom" data-title="Explanations"  style="position: absolute; right: 0; top: 0">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
								  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
								  <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
								</svg>
							</button>
					      </td>
					    </tr>
					 </tbody>
			      </table>
			 	 </div>
			 </div>
			 
		<%}%>
		</div>
		 
	</div>
</body>
</html>