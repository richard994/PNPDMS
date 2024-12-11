<%@page import="Util.CreateService"%>
<%@page import="Util.CalcService"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/virtual-select.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.bootstrap3.min.css" integrity="sha256-ze/OEYGcFbPRmvCnrSeKbRTtjG4vGLHXgOqsyLFTRjg=" crossorigin="anonymous" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="js/virtual-select.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.min.js" integrity="sha256-+C0A5Ilqmu4QcSPxrlGpaZxJ04VjsRjKu+G82kl5UJk=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="js/createqp.css">

	<title>Createqp</title>
	
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
		var counter = 1;
		<%List<String> finishings = (List<String>)request.getAttribute("finishingsList");
          List<String> materials = (List<String>)request.getAttribute("matList");
          int numFinish = Integer.parseInt(request.getAttribute("numFinish").toString());
          String matString = request.getAttribute("matStr").toString();
          String matColorPriceString = request.getAttribute("matColorPriceStr").toString();
          String matDrPriceString = request.getAttribute("matDrPriceStr").toString();
          String matPrPriceString = request.getAttribute("matPrPriceStr").toString();
          String matWhitePriceString = request.getAttribute("matWhitePriceStr").toString();
          String matDyePriceString = request.getAttribute("matDyePriceStr").toString();
          String matMtrtypeString = request.getAttribute("matMtrtypeStr").toString();
          String matCgString = request.getAttribute("matCgStr").toString();
          String matCzString = request.getAttribute("matCzStr").toString();
          String matLossString = request.getAttribute("matLossStr").toString();%>
        var matstr = "<%= matString%>";
	
		function next() {
			document.getElementById("progress2").style.display = "none";
			document.getElementById("nextbtn").style.display = "none";
			document.getElementById("progressed1").style.display = "block";
			document.getElementById("wwbtncontainer").style.display = "block";
			document.getElementById("WW").style.display = "block";
			document.getElementById("savebtn").style.display = "block";
			document.getElementById("wtable").style.display = "table";
		}
		
		function addWW() {
			counter += 1;
			var wtable = document.getElementById("wtablerow");
			var numChilds = wtable.childElementCount + 1;
			var p =    				
				"	<tr>\n                         " +
				"      <th scope=\"row\" style=\"width: 2%\">" + numChilds + "</th>\n  " +
				"      <td style=\"width: 7%\">" + 
				"      	<select class=\"custom-select border-0 rounded-0\" id=\"wwtype" + counter + "\" name=\"wwtype" + counter + "\" size=\"1\" style=\"width: 100%; height: 36px\">\n" +
				"	    	<option value=\"\" selected> Enter</option>\n" +
				"           <option value=\"Warp\">Warp</option>" +
				"           <option value=\"Weft\">Weft</option>" +
				"  		</select>\n" + 
				"	   </td>\n" +
				"      <td style=\"width: 32%\">" + 
				"      	<select class=\"mattype custom-select border-0 rounded-0\" id=\"mattype" + counter + "\" name=\"mattype" + counter + "\" size=\"1\" style=\"width: 100%; height: 36px\">\n" +
				"	    	<option value=\"\" selected> Enter</option>\n" +
				"  		</select>\n" + 
				"	   </td>\n" +
				"      <td style=\"width: 7%\"> " +   				
				"      	<select class=\"custom-select border-0 rounded-0\" id=\"colortype" + counter + "\" name=\"colortype" + counter + "\" size=\"1\"> " +
				"	    	<option value=\"\" selected>Enter</option> " +
				"	    	<option value=\"White Yarn\">White Yarn</option> " +
				"	    	<option value=\"Yarn Dyed\">Yarn Dyed</option> " +
				"	    	<option value=\"Fiber Dyed Yarn\">Fiber Dyed Yarn</option> " +
				"	    	<option value=\"Space Dyed Yarn\">Space Dyed Yarn</option> " +
				"	  	</select> " +
				"	   </td> " +
				"      <td style=\"width: 8%\"><input type=\"text\" id=\"usePercent" + counter + "\" name=\"usePercent" + counter + "\" class=\"form-control border-0 rounded-0\" placeholder=\"Enter\"/></td>\n" +
				"      <td style=\"width: 5%\">%</td>\n                " +
				"      <td style=\"width: 24%\"><input type=\"text\" id=\"matMemo" + counter + "\" name=\"matMemo" + counter + "\" class=\"form-control border-0 rounded-0\" placeholder=\"Enter\"/></td>\n" +
				"      <td style=\"white-space: nowrap; width: 15%\">" + 
				"	   	<button type=\"button\" class=\"btn btn-link btn-sm text-decoration-none\" id=\"cpbtn" + counter + "\" onclick=\"copy(this.id)\">COPY</button>\n    " +
		      	"		<button type=\"button\" class=\"btn btn-link btn-sm text-decoration-none text-danger\" id=\"" + counter + "\">DELETE</button>\n" +
				"	   </td>\n              " +
				"   </tr>\n                       ";
			var o = document.createElement("tr");
			o.innerHTML = p;
			o.setAttribute("id", "row" + counter);
			o.setAttribute("style", "width: 100%");
			wtable.appendChild(o);
			
			var m = 
			   	"    <div class=\"modal fade\" id=\"dlModal" + counter + "\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"dlModalLabel\" aria-hidden=\"true\">" +
			    "      <div class=\"modal-dialog\"> " + 
			    "        <div class=\"modal-content\"> " +
			    "          <div class=\"modal-header\">  " +
			    "            <h5 class=\"modal-title\" id=\"staticBackdropLabel\">Attention</h5> " +
			    "            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button> " +
			    "          </div> " +
			    "          <div class=\"modal-body\"> " +
			    "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-exclamation-circle\" viewBox=\"0 0 16 16\" style=\"color: red\"> " +
				"	           <path d=\"M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z\"/> " +
				"	           <path d=\"M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z\"/> " +
				"	         </svg>&nbsp&nbspPlease confirm deletion of the material. " +
			    "          </div> " +
			    "          <div class=\"modal-footer\"> " +
			    "            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button> " +
			    "            <button type=\"button\" class=\"btn btn-primary\" id=\"confirmDelete" + counter + "\"  data-bs-dismiss=\"modal\">Confirm</button> " +
			    "          </div> " +
			    "        </div> " +
			    "      </div> " +
			    "    </div> ";
			var d = document.createElement("div");
			d.innerHTML = m;
			d.setAttribute("id", "dlModalBlock" + counter);
			document.body.appendChild(d);
			
			var id = "mattype" + counter;
			var select = document.getElementById(id);
			var option = document.createElement("option");
			const matarr = matstr.split(",");
			for (var i=0; i<matarr.length; i++) {
				option.value = matarr[i];
				option.text = matarr[i];
				select.appendChild(option.cloneNode(true));
			}
		}
		
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
	    <li class="breadcrumb-item">Calculate Sale Price</li>
	  </ol>
	</nav>
    
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
    			<div class="col" id="progress2">
    				<div class="d-flex align-items-center justify-content-center opacity-50">
	    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle" viewBox="0 0 16 16">
					  		<path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z"/>
						</svg>
	    				<span>&nbsp&nbspWarp and Weft</span>
    				</div>
    			</div>
    			<div class="col" id="progressed1" style="display: none">
    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
	    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle-fill" viewBox="0 0 16 16">
					  		<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24c0-.691.493-1.306 1.336-1.306.756 0 1.313.492 1.313 1.236 0 .697-.469 1.23-.902 1.705l-2.971 3.293V12h5.344v-1.107H7.268v-.077l1.974-2.22.096-.107c.688-.763 1.287-1.428 1.287-2.43 0-1.266-1.031-2.215-2.613-2.215-1.758 0-2.637 1.19-2.637 2.402v.065h1.271v-.07Z"/></svg>
						</svg>
	    				<span>&nbsp&nbspWarp and Weft</span>
    				</div>
    			</div>
    			<div class="col opacity-50" id="progress3"><div class="card bg-light" style="height:1%; width:80%"></div></div>
    			<div class="col" style="margin-right: 120px" id="progress4">
    				<div class="d-flex align-items-center justify-content-center opacity-50">
	    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
						  <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
						  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
						</svg>
	    				<span>&nbsp&nbspSave Price Offer</span>
    				</div>
    			</div>
    			<div class="col" style="margin-right: 120px; display: none" id="progressed2">
    				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle-fill" viewBox="0 0 16 16">
						  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0Zm-8.082.414c.92 0 1.535.54 1.541 1.318.012.791-.615 1.36-1.588 1.354-.861-.006-1.482-.469-1.54-1.066H5.104c.047 1.177 1.05 2.144 2.754 2.144 1.653 0 2.954-.937 2.93-2.396-.023-1.278-1.031-1.846-1.734-1.916v-.07c.597-.1 1.505-.739 1.482-1.876-.03-1.177-1.043-2.074-2.637-2.062-1.675.006-2.59.984-2.625 2.12h1.248c.036-.556.557-1.054 1.348-1.054.785 0 1.348.486 1.348 1.195.006.715-.563 1.237-1.342 1.237h-.838v1.072h.879Z"/>
						</svg>
	    				<span>&nbsp&nbspSave Price Offer</span>
    				</div>
    			</div>
    			
    		</div>
    	</div>
    	
    	<div class="container m-4 h5" id="title1"><span>Add Product Design</span></div>
    	
    	<form id="PDform" action="CalcService" method="get" onsubmit="return validate()">
    	<div class="row g-4 border-bottom border-2" style="margin-left: 25px; margin-right:5px">
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="pmodel" class="control-label opacity-75">Product Model</label>
    				<input type="text" name="pmodel" id="pmodel" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    			</div>
    		</div>
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="fabrictype" class="control-label opacity-75">Fabric Type</label>
    				<select class="custom-select border-0 rounded-0 bg-light" id="fabrictype" name="fabrictype" size="1" style="width: 100%; height: 36px">
				    	<option value="" selected>Enter</option>
				    	<option value="plain">plain</option>
				    	<option value="jacquard">jacquard</option>
				  	</select>
    			</div>
    		</div>
    		<div class="col-md-6">
    			<div class="d-flex flex-column">
    				<label for="ppmodel" class="control-label opacity-75">Finishing Module</label>
    				<select id="ppmodel" name="ppmodel" placeholder="Select Desired Finishings" data-search="true" data-silent-initial-value-set="true" style="width: 100%; height: 36px" multiple>
				    	<%for(String f:finishings){%>
				    	<option value="<%=f%>"><%=f%></option>
				    	<%}%>
				  	</select>
    			</div>
    		</div>		
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="ppcm" class="control-label opacity-75">Picks Per Inches/Centimeters</label>
    				<div class="row">
    					<div class="col-4">
    						<select class="custom-select border-0 rounded-0 bg-light" id="ppunit" name="ppunit" size="1" style="height: 100%; width: 90px">
						    	<option value="" selected>Enter</option>
						    	<option value="inches">inches</option>
						    	<option value="centimeters">cm</option>
						  	</select>
    					</div>
    					<div class="col-8">
    						<input type="text" name="ppcm" id="ppcm" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    					</div>
    				</div>
    			</div>
    		</div>
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="totalWarp" class="control-label opacity-75">Total Warp</label>
    				<input type="text" id="totalWarp" name="totalWarp" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    			</div>
    		</div>
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<div class="control-label opacity-75">Weight(g)</div>
    				<div id="weight" class="form-control border-0 rounded-0 bg-light">0.00</div>
    			</div>
    		</div>
    		<div class="col-md-3">
    			<div class="d-flex flex-column" style="margin-right: 5	px">
    				<label for="width" class="control-label opacity-75">Width</label>
				    <select class="custom-select border-0 rounded-0 bg-light" id="width" name="width" size="1" style="width: 100%; height: 36px" disabled>
				    	<option selected>155</option>
				  	</select>
    			</div>
    		</div>
    		
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="date" class="control-label opacity-75">Date</label>
    				<input type="date" id="date" name="date" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    			</div>
    		</div>
    		<div class="col-md-9">
    			<div class="d-flex flex-column">
    				<label for="memo" class="control-label opacity-75">Memo</label>
    				<input type="text" id="memo" name="memo" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    			</div>
    		</div>
    		
    		<div class="container-fluid text-center">
    			<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px" type="button" onclick="next()" id="nextbtn">NEXT</button>
    		</div>
    	</div>
    	
    	<div class="container m-4 h5" style="display: none" id="WW"><span>Add Yarns</span></div>
    	
	    <div class="container m-4 h5" id="wwbtncontainer" style="display: none">
   			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: #4D73FF; color: white; width: 180px" onclick="addWW()" id="WWbtn">+ Create</button>
   		</div>
   		
   		<table class="table table-lg table-bordered" style="margin-left: 40px; width: 95%; display: none" id="wtable">
   			<thead class="table-light">
   				<tr>
   					<th scope="col">#</th>
   					<th scope="col">Type</th>
   					<th scope="col">Material</th>
   					<th scope="col">Color</th>
   					<th scope="col">Usage</th>
   					<th scope="col">Unit</th>
   					<th scope="col">Memo</th>
   					<th scope="col">Option</th>
   				</tr>
   			</thead>
   			<tbody id="wtablerow">
   				<tr id="row1" style="width: 100%">
			      <th scope="row" style="width: 2%">1</th>
			      <td style="width: 7%">    				
			      	<select class="custom-select border-0 rounded-0" id="wwtype1" name="wwtype1" size="1">
				    	<option value="" selected>Enter</option>
				    	<option value="Warp">Warp</option>
				    	<option value="Weft">Weft</option>
				  	</select>
				  </td>
			      <td style="width: 32%">
			      	<select class="mattype custom-select border-0 rounded-0" id="mattype1" name="mattype1" size="1">
				    	<option value="" selected> Enter</option>
			    		<%for(String m:materials){%>
				    	<option value="<%=m%>"><%=m%></option>
				    	<%}%>
				  	</select>
			      </td>
   			      <td style="width: 7%">    				
			      	<select class="custom-select border-0 rounded-0" id="colortype1" name="colortype1" size="1">
				    	<option value="" selected>Enter</option>
				    	<option value="White Yarn">White Yarn</option>
				    	<option value="Yarn Dyed">Yarn Dyed</option>
				    	<option value="Fiber Dyed Yarn">Fiber Dyed Yarn</option>
				    	<option value="Space Dyed Yarn">Space Dyed Yarn</option>
				    	<option value="Jet Dyed Yarn">Jet Dyed Yarn</option>
				  	</select>
				  </td>
			      <td style="width: 8%"><input type="text" id="usePercent1" name="usePercent1" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
			      <td style="width: 5%">%</td>
			      <td style="width: 24%"><input type="text" id="matMemo1" name="matMemo1" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
			      <td style="white-space: nowrap; width: 15%">
			      	<button class="btn btn-link btn-sm text-decoration-none" type="button" id="cpbtn1" onclick="copy(this.id)">COPY</button>
			      	<button class="btn btn-link btn-sm text-decoration-none text-danger" type="button" id="1">DELETE</button>
			      </td>
			    </tr>
   			</tbody>
   		</table>
   		
		<div class="container-fluid text-center mx-auto" style="width: 180px" id="saveblk">
   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px; display: none" type="submit" id="savebtn">SAVE</button>
   		</div>
   		
   		</form>
    </div>
    
   	<div class="modal fade" id="dlModal1" data-bs-keyboard="false" tabindex="-1" aria-labelledby="dlModalLabel" aria-hidden="true">
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
			</svg>&nbsp&nbspPlease confirm deletion of the material.
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="confirmDelete1"  data-bs-dismiss="modal">Confirm</button>
	      </div>
	    </div>
	  </div>
	</div>
		
    <script src="js/bootstrap.bundle.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		VirtualSelect.init({
			ele: '#ppmodel'
		});
		
	    $( '#1' ).on('click', function() {
	    	$("#dlModal1").modal('show');
		    $("#confirmDelete1").on('click', function() {
		    	warr[0] = 0;
		    	addUpWeights();
			  	$( "#weight" ).text( weight );
		    	$("#row1").remove();
		    	$("#dlModal1").remove();
		    	var rows = document.getElementById("wtablerow");
		    	var numChilds = rows.childElementCount;
		    	for (var i=0; i<numChilds; i++) {
		    		rows.children[i].children[0].innerHTML = i+1;
		    	}
		    });
	    });
	    
		$(document).ready(function () {
		    $('#mattype1').selectize({
		        sortField: 'text'
		    });
		});
		
		const warr = [];
		const pcarr = [];
		const pwarr = [];
		const pmarr = [];
		const mtparr = [];
		const cgarr = [];
		const czarr = [];
		const wlarr = [];
		const uparr = [];
		const memarr = [];
		
		pwarr[0] = "";
		pcarr[0] = "";
		pmarr[0] = "";
		uparr[0] = "";
		memarr[0] = "";
		
		var wwtype = "";
		var colortype = "";
		var str = "";
		var usePercent = "";
		var weight = "";
		var ppunitInput = "";
		var ppcmInput = "";
		var totalWarpInput = "";
		var r = /\d+/;
		let width = 155;
		
		$( "#ppunit" )
		  .on( "change", function() {
			  ppunitInput = this.value;
			  for (var i=0; i<warr.length; i++) {
				  if (!Number.isNaN(warr[i])) {
					  warr[i] = calculateWeight(mtparr[i], pwarr[i], totalWarpInput, cgarr[i], czarr[i], uparr[i], ppcmInput, ppunitInput, wlarr[i], width);
				  }
			  }
			  addUpWeights();
			  $( "#weight" ).text( weight );
		  } );
		
		$( "#ppcm" )
		  .on( "input", function() {
			  ppcmInput = this.value;
			  for (var i=0; i<warr.length; i++) {
				  if (!Number.isNaN(warr[i])) {
					  warr[i] = calculateWeight(mtparr[i], pwarr[i], totalWarpInput, cgarr[i], czarr[i], uparr[i], ppcmInput, ppunitInput, wlarr[i], width);
				  }
			  }
			  addUpWeights();
			  $( "#weight" ).text( weight );
		  } );
		
		$( "#totalWarp" )
		  .on( "input", function() {
			  totalWarpInput = this.value;
			  for (var i=0; i<warr.length; i++) {
				  if (!Number.isNaN(warr[i])) {
					  warr[i] = calculateWeight(mtparr[i], pwarr[i], totalWarpInput, cgarr[i], czarr[i], uparr[i], ppcmInput, ppunitInput, wlarr[i], width);
				  }
			  }
			  addUpWeights();
			  $( "#weight" ).text( weight );
		  } );
		
		var matColorPriceStr = "<%= matColorPriceString%>";
		var matDrPriceStr = "<%= matDrPriceString%>";
		var matPrPriceStr = "<%= matPrPriceString%>";
		var matWhitePriceStr = "<%= matWhitePriceString%>";
		var matDyePriceStr = "<%= matDyePriceString%>";
		var matMtrtypeStr = "<%= matMtrtypeString%>";
		var matCgStr = "<%= matCgString%>";
		var matCzStr = "<%= matCzString%>";
		var matLossStr = "<%= matLossString%>";
		const matColorPriceMap = new Map();
		const matDrPriceMap = new Map();
		const matPrPriceMap = new Map();
		const matWhitePriceMap = new Map();
		const matDyePriceMap = new Map();
		const matMtrtypeMap = new Map();
		const matCgMap = new Map();
		const matCzMap = new Map();
		const matLossMap = new Map();
		const matColorPriceArr = matColorPriceStr.split(",");
		const matDrPriceArr = matDrPriceStr.split(",");
		const matPrPriceArr = matPrPriceStr.split(",");
		const matWhitePriceArr = matWhitePriceStr.split(",");
		const matDyePriceArr = matDyePriceStr.split(",");
		const matMtrtypeArr = matMtrtypeStr.split(",");
		const matCgArr = matCgStr.split(",");
		const matCzArr = matCzStr.split(",");
		const matLossArr = matLossStr.split(",");
		for (var i=0; i<matColorPriceArr.length; i++) {
			const mcarray = matColorPriceArr[i].split(":");
			const mdrarray = matDrPriceArr[i].split(":");
			const mprarray = matPrPriceArr[i].split(":");
			const mwarray = matWhitePriceArr[i].split(":");
			const mdarray = matDyePriceArr[i].split(":");
			const mtrtypearray = matMtrtypeArr[i].split(":");
			const cgarray = matCgArr[i].split(":");
			const czarray = matCzArr[i].split(":");
			const lossarray = matLossArr[i].split(":");
			matColorPriceMap.set(mcarray[0], mcarray[1]);
			matDrPriceMap.set(mdrarray[0], mdrarray[1]);
			matPrPriceMap.set(mprarray[0], mprarray[1]);
			matWhitePriceMap.set(mwarray[0], mwarray[1]);
			matDyePriceMap.set(mdarray[0], mdarray[1]);
			matMtrtypeMap.set(mtrtypearray[0], mtrtypearray[1]);
			matCgMap.set(cgarray[0], cgarray[1]);
			matCzMap.set(czarray[0], czarray[1]);
			matLossMap.set(lossarray[0], lossarray[1]);
		}
		
		$( "#wwtype1" )
		  .on( "change", function() {
		    $( "#wwtype1 option:selected" ).each(function() {
		    	wwtype = $( this ).text();
		    	pwarr[0] = wwtype;
		    	warr[0] = calculateWeight(mtparr[0], pwarr[0], totalWarpInput, cgarr[0], czarr[0], uparr[0], ppcmInput, ppunitInput, wlarr[0], width);
		    } );
		    addUpWeights();
		    $( "#weight" ).text( weight );
		  } );
		
		$( "#colortype1" )
		  .on( "change", function() {
		    $( "#colortype1 option:selected" ).each(function() {
		    	colortype = $( this ).text();
		    	pcarr[0] = colortype;
		    } );
		  } );
		
		$( "#mattype1" )
		  .on( "change", function() {
			$( "#colortype1" ).prop('selectedIndex', 0);
		    str = "";
		    $( "#mattype1 option:selected" ).each(function() {
		    	str += $( this ).text();
		    	pmarr[0] = str;
		    	var ctid = "#colortype1 option";
		    	checkMatPrice(ctid, parseFloat(matPrPriceMap.get(pmarr[0])), parseFloat(matColorPriceMap.get(pmarr[0])), parseFloat(matDrPriceMap.get(pmarr[0])), parseFloat(matWhitePriceMap.get(pmarr[0])), parseFloat(matDyePriceMap.get(pmarr[0])));
				mtparr[0] = matMtrtypeMap.get(pmarr[0]);
				cgarr[0] = matCgMap.get(pmarr[0]);
				czarr[0] = matCzMap.get(pmarr[0]);
				wlarr[0] = matLossMap.get(pmarr[0]);
				warr[0] = calculateWeight(mtparr[0], pwarr[0], totalWarpInput, cgarr[0], czarr[0], uparr[0], ppcmInput, ppunitInput, wlarr[0], width);
		    } );
		    addUpWeights();
		    $( "#weight" ).text( weight );
		  } );
		
		$( "#usePercent1" )
		  .on( "input", function() {
			  usePercent = this.value;
			  uparr[0] = usePercent;
			  warr[0] = calculateWeight(mtparr[0], pwarr[0], totalWarpInput, cgarr[0], czarr[0], uparr[0], ppcmInput, ppunitInput, wlarr[0], width);
			  addUpWeights();
			  $( "#weight" ).text( weight );
		  } );
		
		$( "#matMemo1" )
		  .on( "input", function() {
			  memarr[0] = this.value;
		  } );
		
		$( "#WWbtn" )
		  .on( "click", function() {
		  	wwtype = "";
			mtparr[counter-1] = "";
			cgarr[counter-1] = "";
			czarr[counter-1] = "";
			wlarr[counter-1] = "";
			uparr[counter-1] = "";
			memarr[counter-1] = "";
			warr[counter-1] = 0;
			usePercent = "";
			var typeselector = "#mattype" + counter;
			var wwselector = "#wwtype" + counter;
			var ctselector = "#colortype" + counter;
			var upInput = "#usePercent" + counter;
			var rowselector = "#" + counter;
			var memselector = "#matMemo" + counter;
		    $( typeselector ).selectize({
		        sortField: 'text'
		    });
			$( typeselector )
			  .on( "change", function() {
				var idxstr = typeselector.match(r);
				var idx = parseInt(idxstr);
				$( "#colortype" + idx ).prop('selectedIndex', 0);
				idx -= 1;
			    str = "";
			    $( typeselector + " option:selected" ).each(function() {
			    	str += $( this ).text();
			    	pmarr[idx] = str;
			    	var ctidx = idx+1;
			    	var ctid = "#colortype" + ctidx + " option";
			    	checkMatPrice(ctid, parseFloat(matPrPriceMap.get(pmarr[0])), parseFloat(matColorPriceMap.get(pmarr[idx])), parseFloat(matDrPriceMap.get(pmarr[idx])), parseFloat(matWhitePriceMap.get(pmarr[idx])), parseFloat(matDyePriceMap.get(pmarr[idx])));
			    	mtparr[idx] = matMtrtypeMap.get(pmarr[idx]);
					cgarr[idx] = matCgMap.get(pmarr[idx]);
					czarr[idx] = matCzMap.get(pmarr[idx]);
					wlarr[idx] = matLossMap.get(pmarr[idx]);
					warr[idx] = calculateWeight(mtparr[idx], pwarr[idx], totalWarpInput, cgarr[idx], czarr[idx], uparr[idx], ppcmInput, ppunitInput, wlarr[idx], width);
			    } );
			    addUpWeights();
			    $( "#weight" ).text( weight );
			  } );
			
			$( wwselector )
			  .on( "change", function() {
				var idxstr = wwselector.match(r);
				var idx = parseInt(idxstr);
				idx -= 1;
			    $( wwselector + " option:selected" ).each(function() {
			    	wwtype = $( this ).text();
			    	pwarr[idx] = wwtype;
			    	warr[idx] = calculateWeight(mtparr[idx], pwarr[idx], totalWarpInput, cgarr[idx], czarr[idx], uparr[idx], ppcmInput, ppunitInput, wlarr[idx], width);
			    } );
			    addUpWeights();
			    $( "#weight" ).text( weight );
			  } );
			
			$( ctselector )
			  .on( "change", function() {
			    $( ctselector + " option:selected" ).each(function() {
					var idxstr = ctselector.match(r);
					var idx = parseInt(idxstr);
					idx -= 1;
			    	colortype = $( this ).text();
			    	pcarr[idx] = colortype;
			    } );
			  } );
			
			$( upInput )
			  .on( "input", function() {
				  var idxstr = upInput.match(r);
				  var idx = parseInt(idxstr);
				  idx -= 1;
				  usePercent = this.value;
				  uparr[idx] = usePercent;
				  warr[idx] = calculateWeight(mtparr[idx], pwarr[idx], totalWarpInput, cgarr[idx], czarr[idx], uparr[idx], ppcmInput, ppunitInput, wlarr[idx], width);
				  addUpWeights();
				  $( "#weight" ).text( weight );
			  } );
			
		    $( rowselector ).on('click', function() {
				var idxstr = rowselector.match(r);
				var idx = parseInt(idxstr);
		    	$("#dlModal" + idx).modal('show');
			    $("#confirmDelete" + idx).on('click', function() {
			    	$("#row" + idx).remove();
			    	$("#dlModalBlock" + idx).remove();
			    	var rows = document.getElementById("wtablerow");
			    	var numChilds = rows.childElementCount;
			    	for (var i=0; i<numChilds; i++) {
			    		rows.children[i].children[0].innerHTML = i+1;
			    	}
			    	warr[idx-1] = 0;
			    	addUpWeights();
				  	$( "#weight" ).text( weight );
			    });
		    });
		    
		    $( memselector ).on('input', function() {
				var idxstr = memselector.match(r);
				var idx = parseInt(idxstr);
				idx -= 1;
				memarr[idx] = this.value;
		    });
		  } );
		
		function calculateWeight(mt, wt, tw, cg, cz, up, ppcm, ppunit, wl, w) {
			let temp = 0.0;
			
			if (!mt || !wt || !tw || !cg || !cz || !up || !ppcm || !wl) {
				return temp;
			} else {
				tw = parseFloat(tw);
				cg = parseFloat(cg);
				cz = parseFloat(cz);
				up = parseFloat(up);
				ppcm = parseFloat(ppcm);
				wl = parseFloat(wl);
				
				if (Number.isNaN(tw) || Number.isNaN(cg) || Number.isNaN(cz) || Number.isNaN(up) || Number.isNaN(ppcm) || Number.isNaN(wl)) {
					return temp;
				}
			}
			
			if (ppunit === "") {
				return temp;
			} else if (ppunit === "inches") {
				ppcm = ppcm * 2.54;
			}
			
			if (wt === "Weft") {
				if (mt === "D") {
					temp = w * ((cz/cg)*((ppcm*up)/1000)/9000) * wl;
				} else if (mt === "N") {
					temp = w * (9000/(cz/cg)*(ppcm*up)/1000)/9000 * wl;
				} else if (mt === "S") {
					temp = w * (5315/(cz/cg)) * ((ppcm*up)/1000)/9000 * wl;
				} else {}
			} else if (wt === "Warp") {
				if (mt === "D") {
					temp = cz/cg * tw * up/1000/9000 * wl;
				} else if (mt === "N") {
					temp = (9000/(cz/cg)) * tw * up/1000/9000 * wl;
				} else if (mt === "S") {
					temp = (5315/(cz/cg)) * tw * up/1000/9000 * wl;
				} else {}
			} else {}
			
			return temp;
		}
		
		function addUpWeights() {
			temp = 0;
			for (var i=0; i<warr.length; i++) {
				if (!Number.isNaN(warr[i])) {
					temp += warr[i];
				}
			}
			temp *= 8.8;
			temp = temp.toFixed(2);
			if (!Number.isNaN(temp)) {
				weight = temp.toString();
			}
		}
		
		function checkMatPrice(ctid, pp, cp, drp, wp, dp) {
			document.querySelectorAll(ctid).forEach(opt => {
				opt.disabled = false;
			});
			if (cp == 0) {
				document.querySelectorAll(ctid).forEach(opt => {
				    if (opt.value == "Fiber Dyed Yarn") {
				        opt.disabled = true;
				    }
				});
			} 
			
			if (wp == 0) {
				document.querySelectorAll(ctid).forEach(opt => {
				    if (opt.value == "White Yarn") {
				        opt.disabled = true;
				    }
				});
			}
			
			if (dp == 0) {
				document.querySelectorAll(ctid).forEach(opt => {
				    if (opt.value == "Yarn Dyed") {
				        opt.disabled = true;
				    }
				});
			}
			
			if (drp == 0) {
				document.querySelectorAll(ctid).forEach(opt => {
				    if (opt.value == "Space Dyed Yarn") {
				        opt.disabled = true;
				    }
				});
			}
			
			if (pp == 0) {
				document.querySelectorAll(ctid).forEach(opt => {
				    if (opt.value == "Jet Dyed Yarn") {
				        opt.disabled = true;
				    }
				});
			}
		}
		
		function copy(btnid) {
			document.getElementById("WWbtn").click();
			
			var idxstr = btnid.match(r);
			var idx = parseInt(idxstr);
			idx -= 1;
			var sw = pwarr[idx];
			var ct = pcarr[idx];
			var sm = pmarr[idx];
			var su = uparr[idx];
			var so = memarr[idx];
			
			document.getElementById("wwtype" + counter).value = sw;
			document.getElementById("wwtype" + counter).dispatchEvent(new Event('change'));
			document.getElementById("usePercent" + counter).value = su;
			document.getElementById("usePercent" + counter).dispatchEvent(new Event('input'));
			document.getElementById("matMemo" + counter).value = so;
			document.getElementById("matMemo" + counter).dispatchEvent(new Event('input'));
			
			var $select = $('#mattype' + counter).selectize();
			var selectize = $select[0].selectize;
			selectize.setValue(sm, false);
			
			document.getElementById("colortype" + counter).value = ct;
			document.getElementById("colortype" + counter).dispatchEvent(new Event('change'));
		}
		
		function validate() {
			if (document.forms["PDform"]["pmodel"].value == "") {
				alert("Please enter product model!");
				return false;
			} else if (document.forms["PDform"]["fabrictype"].value == "") {
				alert("Please select fabric type!");
				return false;
			} else if (document.forms["PDform"]["ppmodel"].value == "") {
				alert("Please select finishing module!");
				return false;
			} else if (document.forms["PDform"]["ppunit"].value == "") {
				alert("Please enter unit for picks!");
				return false;
			} else if (document.forms["PDform"]["ppcm"].value == "") {
				alert("Please enter picks per centimeter!");
				return false;
			} else if (document.forms["PDform"]["totalWarp"].value == "") {
				alert("Please enter total warp!");
				return false;
			} else if (document.forms["PDform"]["date"].value == "") {
				alert("Please select a date!");
				return false;
			} else {
				for (var i=1; i<=counter; i++) {
					try {
						if (document.getElementById("wwtype" + i).value == "") {
							alert("Please select type for every yarn added!");
							return false;
						} else if (document.getElementById("mattype" + i).value == "") {
							alert("Please select material for every yarn added!");
							return false;
						} else if (document.getElementById("colortype" + i).value == "") {
							alert("Please select color for every yarn added!");
							return false;
						} else if (document.getElementById("usePercent" + i).value == "") {
							alert("Please enter use percentage for every yarn added!");
							return false;
						} else {}
					} catch (error) {}
				}
			}
		}
	</script>

</body>
</html>