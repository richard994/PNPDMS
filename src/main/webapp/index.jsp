<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="js/index.css">
	<title>Miju</title>
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

<body>
   	<section class="bg-white">
		<div class="container">
	   		<div class="d-flex flex-wrap justify-content-center py-3 mb-4" id="headbar">
		      <p class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
		      <img src="icon.png" class="img-fluid d-sm-block d-none" style="width: 95px; margin-left: 90px">
		      </p>
		
		      <ul class="nav nav-pills">
		        <li class="nav-item mr-5"><a href="HomeService" class="nav-link" id="Home" style="color: #4D73FF">Home</a></li>
		        <li class="nav-item mr-5"><a href="PricingService" class="nav-link" id="Pricing">Pricing</a></li>
		        <li class="nav-item mr-5"><a href="MatService" class="nav-link" id="Material">Material</a></li>
		        <li class="nav-item mr-5"><a href="FinishService" class="nav-link" id="Finishing">Finishing</a></li>
		        <li class="nav-item mr-5" id="goToLogin"><a href="auth.jsp" class="nav-link" id="Account">Account</a></li>
           		<li class="nav-item dropdown mr-5" id="AccountSetting" style="display: none">
				   <a class="nav-link  dropdown-toggle" href="#" data-bs-toggle="dropdown">Account</a>
				    <ul class="dropdown-menu">
					  <li><a class="dropdown-item" href="LogoutService">Log Out</a></li>
				    </ul>
				</li>
		      </ul>
		    </div>
	    </div>
	</section>

  <section id="about" class="text-center text-sm-start ml-3 mt-4">
       <div class="row align-items-center justify-content-start">
           <div class="col-md" id="welcomemsg" style="margin-left: 150px; margin-right: 0px; padding-right: 0px">
           		 <div class="row">
				  <div class="col">WELCOME TO</div>
				 </div>
				 <div class="row">
				  <div class="col">
 				  	<span style="color: #4D73FF">MIJU</span>
 				  	<span>HOME</span>
 				  </div>
				 </div>
				 <div class="row align-items-end">
				 	<a href="CreateService" class="btn btn-primary btn-lg rounded-0" id="startbtn" style="width: 50%; margin-top: 80px">Calculate Sale Price &#8594</a>
				 </div>
				 <script>
				 	var btn = document.getElementById("startbtn");
				 	btn.addEventListener('click', function onClick(){
				 		sessionStorage.setItem("qp", " Create Quoted Price");
				 		sessionStorage.setItem("curr", " Create Quoted Price");
				 	});
				 </script>
            </div>
            <div class="col-md">
           		<div class="row justify-content-start">
           			<img src="hpic1.png" class="img-fluid d-sm-block d-none" style="width: 250px">
               		<img src="hpic2.png" class="img-fluid d-sm-block d-none" style="width: 150px">
               		<img src="hpic3.png" class="img-fluid d-sm-block d-none" style="width: 80px">
           		</div>
           </div>
       </div>
  </section>
  
  <script>
	var loggedin = "${login}";
	if (loggedin === "yes") {
		document.getElementById("goToLogin").style.display = "none";
		document.getElementById("AccountSetting").style.display = "block";
	}
  </script>
</body>

</html>