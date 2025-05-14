<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/index.css">
	<title>Miju</title>
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
		        <li class="nav-item mr-5"><a href="TrackerService" class="nav-link" id="Tracker">Tracker</a></li>
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
				 	<a href="CreateService" class="btn btn-primary btn-lg rounded-0 mt-5" id="startbtn" style="width: 50%; margin-top: 80px">Calculate Sale Price &#8594</a>
				 </div>
				 <div class="row align-items-end">
				 	<a href="NewDevService?action=create" class="btn btn-primary btn-lg rounded-0 mt-3" id="devbtn" style="width: 50%; margin-top: 80px; background-color: #4D73FF">Add New Development &#8594</a>
				 </div>
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
  <script src="js/index.js"></script>
</body>

</html>