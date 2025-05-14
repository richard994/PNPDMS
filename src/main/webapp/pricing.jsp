<%@page import="Util.PricingService"%>
<%@page import="Util.DeleteService"%>
<%@page import="Util.Quotes"%>
<%@page import="Util.PriceParser"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script 
		type="text/javascript" 
		charset="utf8" 
		src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js">
	</script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
	<script src = "https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="js/virtual-select.min.js"></script>
	<link rel="stylesheet" href="js/virtual-select.min.css">
	<link rel="stylesheet" href="css/pricing.css">
	<title>Pricing</title>
	<style>
		.bottom-wrapper {
			margin-left: 165px;
			height: 50px;
			width: 1250px;
			display: flex;
			background-color: white;
		}
		.info {
			width: 50%;
			margin: auto;
		}
		.length {
			width: 5%;
			margin: auto;
			white-space: nowrap;
		}
		.page {
			width: 45%;
			margin: auto;
			margin-top: 5px !important;
		}
		div.dataTables_length select {
			width: 50px !important;
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
<body class="bg-light" style="overflow: auto">
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
	    <li class="breadcrumb-item">Pricing</li>
	  </ol>
	</nav>
	
	<% 
		PriceParser pp = new PriceParser();
		ArrayList<Quotes> quotes = pp.getQuotes();
		int numQuotes = quotes.size() + 1;
	%>
	
	<form id="filterForm" onsubmit="return filter(this)" class="input-group border-bottom pb-4 mb-4" style="width: 1250px">
		<div class="hstack gap-3" style="width: 100%">
			<div class="input-group-prepend" style="width: 5%">
				<span class="input-group-text bg-light border-light">Product</span>
			</div>
			<input type="text" id="pmodel" name="pmodel" class="form-control border rounded-0 me-auto" style="width: 20%" placeholder="Enter Product Model"/>
			
			<div class="input-group-prepend" style="width: 6%">
				<span class="input-group-text bg-light border-light">Finishing</span>
			</div>
			<select id="finishingmodule" name="finishingmodule" placeholder="Select Finishings" data-search="true" data-silent-initial-value-set="true" style="width: 20%; height: 36px" multiple>
		    	<%List<String> finishings = 
		            (List<String>)request.getAttribute("finishingsList");
		          for(String f:finishings){%>
		    	<option value="<%=f%>"><%=f%></option>
				<%}%>
		  	</select>
		  	
			<div class="input-group-prepend" style="width: 3%">
				<span class="input-group-text bg-light border-light">Date</span>
			</div>
			<input type="date" id="pdate" name="pdate" class="form-control border rounded-0" style="width: 20%"/>
			
			<button class="btn border-0" type="submit" id="searchqp" style="width: 12%">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
				Search
			</button>
	    	<button class="btn border-0" type="button" id="resetqpsearch" onclick="refresh()" style="width: 12%">
	    		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
				  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
				</svg>
	    		Reset
	    	</button>
		</div>
	</form>
	
	<button class="btn border-0" type="button" id="createqp" onclick="window.location.href='CreateService'">
  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
		  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
		</svg>
  		Calculate Sale Price
  	</button>
  	
  	<table class="mt-4 table table-borderless" style="margin-left: 165px; width: 1250px; border-spacing: 0px 15px; border-collapse: separate" id="quotedprices">
  		<thead>
  			<tr>
  				<th scope="col" style="width: 5px">#</th>
				<th scope="col">Model #</th>
				<th scope="col">Width</th>
				<th scope="col">PPCM</th>
				<th scope="col" style="white-space: nowrap">Total Warp</th>
				<th scope="col">Weight(g)</th>
				<th scope="col">Finishing Module</th>
				<th scope="col">Price(RMB)</th>
				<th scope="col">Date</th>
				<th scope="col">Memo</th>
				<th scope="col">Option</th>
  			</tr>
  		</thead>
  		<tbody class="bg-white" id="qpbody">
  			<c:forEach items="<%= quotes %>" var="quote" varStatus="loop">
  			    <tr>
					<th scope="row">${loop.count}</th>
					<td>${quote.modelName}</td>
					<td>155</td>
					<td>${quote.picksPer}</td>
					<td>${quote.totalWarp}</td>
					<td>${quote.weight}</td>
					<td>${quote.finishModule}</td>
					<td>${quote.salePrice}</td>
					<td style="white-space: nowrap">${quote.date}</td>
					<td>
						<button class="btn btn-link btn-sm text-decoration-none" onclick="check()" data-title="${quote.memo}" id="ckbtn">CHECK</button>
					</td>
					<td style="white-space: nowrap; display: flex">
						<form id="editQuote${loop.count}" action="EditService" method="get">
							<button class="btn btn-link btn-sm text-decoration-none" id="edbtn${loop.count}">EDIT</button>
							<input type="hidden" name="quoteToEdit" value="${quote.id}"/>
						</form>
						<form id="copyQuote${loop.count}" action="CopyService" method="get">	
							<button class="btn btn-link btn-sm text-decoration-none" onclick="copy(this.id)" id="cpbtn${loop.count}">COPY</button>
							<input type="hidden" name="quoteToCopy" value="${quote.id}"/>
						</form>
						<form id="deleteRow${loop.count}" action="DeleteService" method="get">
			      			<button class="btn btn-link btn-sm text-decoration-none text-danger" type="button" id="deletebtn${loop.count}">DELETE</button>
		      			  	<input type="hidden" name="rowToDelete" value="${quote.id}"/>
			      		</form>
					</td>
			    </tr>
			</c:forEach>
  		</tbody>
  	</table>
  	
  	<script>
		VirtualSelect.init({
			ele: '#finishingmodule'
		});
  		
		var num = "<%= numQuotes %>";
  		var r = /\d+/;
		var btn = document.getElementById("createqp");
		btn.addEventListener('click', function onClick(){
			sessionStorage.setItem("qp", " Create Quoted Price");
			sessionStorage.setItem("curr", " Create Quoted Price");
		});
		
		$(document).ready(function(){
		    $('#quotedprices').dataTable({
		    	"sDom": 'rt<"bottom-wrapper"<"info"i><"length"l><"page"p>>',
		    	"language": {
		    		"paginate": {
		    			"first": "<<",
		    			"last": ">>",
		    			"next": ">",
		    			"previous": "<"
		    		},
		    		"sLengthMenu": "Show&nbsp&nbsp_MENU_&nbsp&nbspEntries"
		    	},
		    	"lengthMenu": [5, 10, 25, 50],
		    	columns: [
		    		null,
		    		{ orderable: false },
		    		{ orderable: false },
		    		null,
		    		null,
		    		null,
		    		{ orderable: false },
		    		null,
		    		null,
		    		{ orderable: false },
		    		{ orderable: false }
		    	]
		   	});	
		    
		    $('.bottom-wrapper').css("width", "87%");
		    $('.info').css("width", "40%");
		    $('.length').css("width", "20%");
		    $('.page').css("width", "40%");
		    $('#quotedprices_length').children().css("display", "flex");
		    $('#quotedprices_length').children().css("align-items", "center");
		    
		    for (var i=1; i<num; i++) {
				var m = 
				   	"    <div class=\"modal fade\" id=\"dlModal" + i + "\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"dlModalLabel\" aria-hidden=\"true\">" +
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
				    "            <button type=\"button\" class=\"btn btn-primary\" id=\"confirmDelete" + i + "\"  data-bs-dismiss=\"modal\">Confirm</button> " +
				    "          </div> " +
				    "        </div> " +
				    "      </div> " +
				    "    </div> ";
				var d = document.createElement("div");
				d.innerHTML = m;
				d.setAttribute("id", "dlModalBlock" + i);
				document.body.appendChild(d);
				
			    $('#quotedprices tbody').on('click', '#deletebtn' + i, function() {
			    	var confirmed = false;
			    	var id = $(this).attr('id');
					var idxstr = id.match(r);
					var idx = parseInt(idxstr);
			    	$('#dlModal' + idx).modal("show");
			    	$('#dlModal' + idx).on('click', '#confirmDelete' + idx, function() {
			    		confirmed = true;
			    	});
			    	$('#dlModal' + idx).on('hidden.bs.modal', function() {
				    	if (confirmed) {
				    		document.getElementById("deleteRow" + idx).submit();
				    	}
			    	});
			   	});
		    }
		});
		
		function filter(that) {
			$('#quotedprices').DataTable().page.len(num).draw();
			var pmodel = that.pmodel.value;
			var finishing = that.finishingmodule.value;
			const finArr = finishing.split(',');
			var date = that.pdate.value;
			if (!pmodel && !finishing && !date) {
				alert("Please enter at least one constraint.");
			} else {
				var rowpm;
				var rowfn;
				var rowdt;
				for (var i=1; i<num; i++) {
					var finFlag = false;
					var idx = i-1;
					
					rowfn = document.querySelector("#qpbody tr:nth-child(" + i + ") td:nth-child(7)").textContent;
					for (var j=0; j<finArr.length; j++) {
						if (rowfn.includes(finArr[j])) {
							finFlag = true;
						} else {
							finFlag = false;
						}
					}
					
					rowpm = document.querySelector("#qpbody tr:nth-child(" + i + ") td:nth-child(2)").textContent;
					rowdt = document.querySelector("#qpbody tr:nth-child(" + i + ") td:nth-child(9)").textContent;
					if ( (pmodel && (pmodel !== rowpm)) || (date && (date !== rowdt)) || (finishing && !finFlag) ) {
						$("#qpbody").children().eq(idx).css("display", "none");
					}
				}
			}
			return false;
		}
		
		function refresh() {
			window.location.reload();
		}
	</script>
	
    <script src="js/bootstrap.bundle.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
</body>
</html>