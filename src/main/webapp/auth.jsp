<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/shared.css">
	<link rel="stylesheet" href="css/auth.css">
	<title>AUTH</title>
</head>

<body>
	<c:set var="activePage" value="Account" scope="request" />
	<c:set var="accountVariant" value="plain" scope="request" />
	<jsp:include page="/WEB-INF/jspf/navbar.jsp" />
	
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