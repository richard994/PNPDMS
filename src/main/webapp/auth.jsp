<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/auth.css">
	<title>AUTH</title>
</head>

<body>
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
		        <li class="nav-item mr-5"><a href="TrackerService" class="nav-link" id="Tracker">Tracker</a></li>
		        <li class="nav-item mr-5"><a href="auth.jsp" class="nav-link" id="Account" style="color: #4D73FF">Account</a></li>
		      </ul>
		    </div>
	    </div>
	</section>
	
	<section class="vh-100">
	  <div class="container-fluid h-custom">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-md-9 col-lg-6 col-xl-5">
	        <img src="lpic.png" class="img-fluid">
	      </div>
	      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
	        <form action="LoginService" method="get">
	          <div id="loginmsg" class="mb-3">
	          	Please Login
	          </div>
	        
	          <!-- Email input -->
	          <div class="form-outline mb-4">
	            <input type="email" id="email" name="email" class="form-control form-control-lg"
	              placeholder="Enter a valid email address" />
	          </div>
	
	          <!-- Password input -->
	          <div class="form-outline mb-3">
	            <input type="password" id="password" name="password" class="form-control form-control-lg"
	              placeholder="Enter password" />
	          </div>
	
	          <div class="text-center text-lg-start mt-4 pt-2">
	            <button type="submit" class="btn btn-lg"
	              style="padding-left: 2.5rem; padding-right: 2.5rem; background-color: #4D73FF; color: white">Login</button>
	          </div>
	        </form>
	      </div>
	    </div>
	  </div>
	</section>
	
	<script>
		var wronginput = "${wrongInput}";
		if (wronginput === "yes") {
			alert("Please enter correct username/password");
		}
	</script>
</body>
</html>