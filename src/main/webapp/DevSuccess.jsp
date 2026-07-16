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
	<link rel="stylesheet" href="css/devsuccess.css">
	<link rel="stylesheet" href="css/createqp.css">
	<title>DevSuccess</title>
	<script type="text/javascript">
		function clickTracker() {
			document.getElementById("Tracker").click();
		}
	</script>
</head>
<body class="bg-light">
	<c:set var="activePage" value="Tracker" scope="request" />
	<jsp:include page="/WEB-INF/jspf/navbar.jsp" />
	
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