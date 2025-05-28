<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/virtual-select.min.css">
	<script src="js/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="js/virtual-select.min.js"></script>
	<link rel="stylesheet" href="css/createqp.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/choices.js/public/assets/styles/choices.min.css"/>
	<script src="https://cdn.jsdelivr.net/npm/choices.js/public/assets/scripts/choices.min.js"></script>
	

	<title>Createqp</title>
	<style>
		body {
			font-family: 'Inter', sans-serif !important;
		}
		.choices__inner {
		  background-color: transparent !important; 
		  border: none             !important; 
		  box-shadow: none         !important;    
		  width: 100%              !important;   
		  height: 100%             !important;  
		  min-height: 0            !important;      
		  padding: 0               !important;      
		}
		
		.choices {
		  width: 100% !important;
		  height: 100% !important;
		}
	</style>
	
	<script type="text/javascript">
		var mats = ${matList};
		var edit = ${edit};
		var quote = ${quoteJson};
		var quoteMats = ${qmatJson};
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
	    <li class="breadcrumb-item">Calculate Sale Price</li>
	  </ol>
	</nav>
	
	<div class="pb-0 rounded-0" id="displayqp">
	    <div class="container-fluid text-center py-4 pb-1">
	      <h1 class="display-5 fw-bold mb-2" id="calculatedPrice"><i class="fa fa-cny"></i> 0.0</h1>
	      <p class="mb-0">MIJU HOME OFFER</p>
	    </div>
    </div>
    
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
    	
    	<form id="PDform" action="CalcService" method="post" onsubmit="return validate()">
    	<input type="hidden" id="calculatedPriceInput" name="calculatedPriceInput" value="0.0">
    	<div class="row g-4 border-bottom border-2" style="margin-left: 25px; margin-right:5px">
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="pmodel" class="control-label opacity-75">Product Code</label>
    				<input type="text" name="pmodel" id="pmodel" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
    			</div>
    		</div>
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="fabrictype" class="control-label opacity-75">Fabric Type</label>
    				<select class="border border-2 border-light" id="fabrictype" name="fabrictype" size="1" style="width: 100%; height: 36px">
				    	<option value="" Selected>Enter</option>
				    	<option value="plain">plain</option>
				    	<option value="jacquard">jacquard</option>
				  	</select>
    			</div>
    		</div>
    		<div class="col-md-6">
    			<div class="d-flex flex-column" style="margin-top: -1px;">
    				<label for="ppmodel" class="control-label opacity-75">Finishing</label>
    				<select id="ppmodel" class="custom-select border border-light border-2 rounded-0 bg-white" name="ppmodel" size="1" placeholder="Select Desired Finishings" data-silent-initial-value-set="true" style="width: 100%; height: 36px" multiple>
				    	<option value="KC">Kiss Coat</option>
				    	<option value="NP">Needle Punch</option>
				    	<option value="FB">Fleece Backing</option>
				    	<option value="TC">TC Cloth Backing</option>
				    	<option value="SPB">Single Padded Backing</option>
				  	</select>
    			</div>
    		</div>		
    		<div class="col-md-3">
    			<div class="d-flex flex-column">
    				<label for="ppcm" class="control-label opacity-75">Picks Per Centimeter</label>
    				<input type="text" name="ppcm" id="ppcm" class="form-control border-0 rounded-0 bg-light" placeholder="Enter"/>
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
    				<div class="control-label opacity-75">Weight(gram per meter)</div>
    				<div id="weight" class="form-control border-0 rounded-0 bg-light">0.00</div>
    				<input type="hidden" id="weightInput" name="weightInput" value="0.0">
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
    			<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px" type="button" onclick="validatePhaseOne()" id="nextbtn">NEXT</button>
    		</div>
    	</div>
    	
    	<div class="container m-4 h5" style="display: none" id="WW"><span>Add Yarns</span></div>
    	
	    <div class="container m-4 h5" id="wwbtncontainer" style="display: none">
   			<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: #4D73FF; color: white; width: 180px" onclick="addWW(true)" id="WWbtn">+ Create</button>
   		</div>
   		
   		<table class="table table-lg table-bordered" style="margin-left: 40px; width: 95%; display: none" id="wtable">
   			<thead class="table-light">
   				<tr>
   					<th scope="col">#</th>
   					<th scope="col">Type</th>
   					<th scope="col">Material</th>
   					<th scope="col">Dye method</th>
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
			      	<select class="wwtype custom-select border-0 rounded-0" id="wwtype1" name="wwtype1" size="1" placeholder="Enter" data-silent-initial-value-set="true">
				    	<option value="" Selected>Enter</option>
				    	<option value="Warp">Warp</option>
				    	<option value="Weft">Weft</option>
				  	</select>
				  </td>
			      <td style="width: 32%">
			      	<select class="mattype custom-select border-0 rounded-0" id="mattype1" name="mattype1" size="1" placeholder="Enter" data-silent-initial-value-set="true">
				  	</select>
			      </td>
   			      <td style="width: 7%">    				
			      	<select class="colortype custom-select border-0 rounded-0" id="colortype1" name="colortype1" size="1" placeholder="Enter" data-silent-initial-value-set="true">
				    	<option value="" Selected>Enter</option>
				    	<option value="White Yarn">White Yarn</option>
				    	<option value="Yarn Dyed">Yarn Dyed</option>
				    	<option value="Fiber Dyed Yarn">Piece Dyed Yarn</option>
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
   		
   		<template id="ww-row-template">
		  <tr style="width:100%">
		    <th scope="row" style="width:2%"></th>
		    <td style="width:7%">
		      <select class="wwtype custom-select border-0 rounded-0" size="1" style="width:100%;height:36px"></select>
		    </td>
		    <td style="width:32%">
		      <select class="mattype custom-select border-0 rounded-0" size="1" style="width:100%;height:36px"></select>
		    </td>
		    <td style="width:7%">
		      <select class="colortype custom-select border-0 rounded-0" size="1"></select>
		    </td>
		    <td style="width:8%"><input type="text" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
		    <td style="width:5%">%</td>
		    <td style="width:24%"><input type="text" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
		    <td style="white-space:nowrap;width:15%">
		      <button type="button" class="btn btn-link btn-sm text-decoration-none">COPY</button>
		      <button type="button" class="btn btn-link btn-sm text-decoration-none text-danger">DELETE</button>
		    </td>
		  </tr>
		</template>
   		
		<div class="container-fluid text-center mx-auto" style="width: 180px" id="saveblk">
   			<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px; display: none" type="submit" id="savebtn">SAVE</button>
   		</div>
   		
   		</form>
    </div>
		
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
	<script src="js/createqp.js"></script>
	<script type="text/javascript">
		VirtualSelect.init({
			ele: '#ppmodel'
		});
		
		if (edit) {
			populateAllInputs();
		}
	</script>

</body>
</html>