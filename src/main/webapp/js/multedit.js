function addProductBlock(dev) {
    const productCode = dev.code; 
    const blockId = `ProductBlock-${productCode}`;

    // Create the block content with the unique ID
    const productBlock = `
    <div id="${blockId}" style="margin-top: 25px" class="bg-white">
        <div id="InputFields" class="p-3">
            <div class="d-flex" style="gap: 5px; font-size: 18px">
                <div style="flex: 1">
                    <label for="Code-${productCode}" class="control-label opacity-75">Code:</label>
                    <input type="text" class="border border-light border-2 rounded-0 bg-white" id="Code-${productCode}" name="Code-${productCode}" size="1" style="width: 70%; height: 36px" placeholder="Enter" value="${productCode}" disabled>
                </div>
                <div style="flex: 1">
                    <label for="Color-${productCode}" class="control-label opacity-75">Pattern:</label>
                    <input type="text" class="border border-light border-2 rounded-0 bg-white" id="Color-${productCode}" name="Color-${productCode}" size="1" style="width: 70%; height: 36px" placeholder="Enter">
                </div>
            </div>
        </div>
        <div class="d-flex p-3" id="CheckBoxes-${productCode}" style="white-space: nowrap; gap: 30px">
            <div class="form-check" style="flex: 1">
                <input class="form-check-input" type="checkbox" id="GeorgeCancelCB-${productCode}" name="GeorgeCancelCB-${productCode}">
                <label class="form-check-label" for="GeorgeCancelCB-${productCode}">George Canceled</label>
            </div>
            <div class="form-check" style="flex: 1">
                <input class="form-check-input" type="checkbox" id="inactiveCB-${productCode}" name="inactiveCB-${productCode}">
                <label class="form-check-label" for="inactiveCB-${productCode}">inactive</label>
            </div>
            <div class="form-check" style="flex: 1">
                <input class="form-check-input" type="checkbox" id="priceConfirmCB-${productCode}" name="priceConfirmCB-${productCode}">
                <label class="form-check-label" for="priceConfirmCB-${productCode}">Price Confirmed</label>
            </div>
        </div>
        <div class="d-flex p-3" style="gap: 15px">
            <div style="flex: 1">
                <label for="DesignType-${productCode}" class="control-label opacity-75">Design Type</label>
                <select class="custom-select border border-light border-2 rounded-0 bg-white" id="DesignType-${productCode}" name="DesignType-${productCode}" size="1" style="width: 100%; height: 36px">
                    <option value="" selected>Enter</option>
                    <option value="NewDesign">New Design</option>
                    <option value="Reshow">Reshow</option>
                    <option value="PCR">PCR</option>
			    	<option value="24SP to 24FA">24SP to 24FA</option>
			    	<option value="24FA to 25SP">24FA to 25SP</option>
			    	<option value="24FA to 25FA">24FA to 25FA</option>
			    	<option value="25SP to 25FA">25SP to 25FA</option>
			    	<option value="25FA to 26SP">25FA to 26SP</option>
			    	<option value="26SP to 26FA">26SP to 26FA</option>
			    	<option value="26FA to 27SP">26FA to 27SP</option>
			    	<option value="27SP to 27FA">27SP to 27FA</option>
			    	<option value="27FA to 28SP">27FA to 28SP</option>
			    	<option value="28SP to 28FA">28SP to 28FA</option>
			    	<option value="28FA to 29SP">28FA to 29SP</option>
			    	<option value="29SP to 29FA">29SP to 29FA</option>
			    	<option value="29FA to 30SP">29FA to 30SP</option>
			    	<option value="30SP to 30FA">30SP to 30FA</option>
                </select>
            </div>
            <div style="flex: 1">
                <label for="Colorist-${productCode}" class="control-label opacity-75">Colorist</label>
                <select class="custom-select border border-light border-2 rounded-0 bg-white" id="Colorist-${productCode}" name="Colorist-${productCode}" size="1" style="width: 100%; height: 36px">
                    <option value="" selected>Enter</option>
                    <option value="house">House</option>
                    <option value="marteen">Marteen</option>
                    <option value="crowder">Crowder</option>
			    	<option value="derocher">Derocher</option>
                </select>
            </div>
            <div style="flex: 1">
                <label for="Designer-${productCode}" class="control-label opacity-75">Designer</label>
                <select class="custom-select border border-light border-2 rounded-0 bg-white" id="Designer-${productCode}" name="Designer-${productCode}" size="1" style="width: 100%; height: 36px">
                    <option value="" selected>Enter</option>
                    <option value="house">House</option>
                    <option value="Outsource">Outsource</option>
                    <option value="crowder">Crowder</option>
			    	<option value="derocher">Derocher</option>
                </select>
            </div>
            <div style="flex: 1">
   				<label for="Season-${productCode}" class="control-label opacity-75">Season</label>
   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Season-${productCode}" name="Season-${productCode}" size="1" style="width: 100%; height: 36px">
			    	<option value="" selected>Enter</option>
			    	<option value="24Spring">24Spring</option>
			    	<option value="24Fall">24Fall</option>
			    	<option value="25Spring">25Spring</option>
			    	<option value="25Fall">25Fall</option>
			    	<option value="26Spring">26Spring</option>
			    	<option value="26Fall">26Fall</option>
			    	<option value="27Spring">27Spring</option>
			    	<option value="27Fall">27Fall</option>
			    	<option value="28Spring">28Spring</option>
			    	<option value="28Fall">28Fall</option>
			    	<option value="29Spring">29Spring</option>
			    	<option value="29Fall">29Fall</option>
			    	<option value="30Spring">30Spring</option>
			    	<option value="30Fall">30Fall</option>
			  	</select>
		  	</div>
        </div>
        <div class="d-flex p-3" style="gap: 15px">
       		<div style="flex: 1">
   				<label for="YarnType-${productCode}" class="control-label opacity-75">Style</label>
   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="YarnType-${productCode}" name="YarnType-${productCode}" size="1" style="width: 100%; height: 36px">
			    	<option value="" selected>Enter</option>
			    	<option value="Traditional">Traditional</option>
			    	<option value="Transitional">Transitional</option>
			    	<option value="Contemporary">Contemporary</option>
			    	<option value="Modern">Modern</option>
			  	</select>
		  	</div>
		  	<div style="flex: 1">
   				<label for="WarpType-${productCode}" class="control-label opacity-75">Warp Type</label>
   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="WarpType-${productCode}" name="WarpType-${productCode}" size="1" style="width: 100%; height: 36px">
			    	<option value="" selected>Enter</option>
			    	<option value="9600 150D Solid">9600 150D Solid</option>
			    	<option value="9600 150D E&E">9600 150D E&E</option>
			    	<option value="9600 150D 6-End Tapestry">9600 150D 6-End Tapestry</option>
			    	<option value="4860 SDY Solid">4860 SDY Solid</option>
			    	<option value="4800 150D Space Dye">4800 150D Space Dye</option>
			    	<option value="4800 150D Solid">4800 150D Solid</option>
			    	<option value="4800 Double Beam">4800 Double Beam</option>
			    	<option value="4800 4-End Warps">4800 4-End Warps</option>
			    	<option value="4800 3-End Warps">4800 3-End Warps</option>
			    	<option value="4800 150D E&E">4800 150D E&E</option>
			    	<option value="Jenny Warp">Jenny Warp</option>
			  	</select>
		  	</div>
		  	<div style="flex: 1">
   				<label for="Direction-${productCode}" class="control-label opacity-75">Direction</label>
   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="Direction-${productCode}" name="Direction-${productCode}" size="1" style="width: 100%; height: 36px">
			    	<option value="" selected>Enter</option>
			    	<option value="RR">RR</option>
			    	<option value="UTR">UTR</option>
			  	</select>
		  	</div>
		  	<div style="flex: 1"></div>
       	</div>
       	<div class="d-flex p-3" style="gap: 15px">
       		<div id="StrikeBlock" style="flex: 2">
 					<span class="control-label opacity-75">Stike-off Progress and Stike-off Birthday</span>
				<div class="d-flex" style="gap: 5px">
					<div style="flex: 2">
		   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="StrikeProgress-${productCode}" name="StrikeProgress-${productCode}" size="1" style="width: 100%; height: 36px">
					    	<option value="DNE" selected>Enter</option>
					    	<option value="Strike-off submitted">Strike-off submitted</option>
					    	<option value="Strike-off producing">Strike-off producing</option>
					    	<option value="Strike-off shipped, awaiting initial feedback from US">Strike-off shipped, awaiting initial feedback from US</option>
					    	<option value="One team confirmed; both parties in discussion, see comment">One team confirmed; both parties in discussion, see comment</option>
					    	<option value="US team is submitting a revision">US team is submitting a revision</option>
					    	<option value="Confirm to drop">Confirm to drop</option>
					    	<option value="Strike-off confirmed by both teams">Strike-off confirmed by both teams</option>
					  	</select>
			  		</div>
				  	<div style="flex: 1">
				  		<input type="date" id="StrikeBirthday-${productCode}" name="StrikeBirthday-${productCode}" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
				  	</div>
		  		</div>
		  	</div>
		  	<div id="BlanketBlock" style="flex: 2">
   				<label for="BlanketStatus-${productCode}" class="control-label opacity-75">Blanket Status</label>
   				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="BlanketStatus-${productCode}" name="BlanketStatus-${productCode}" size="1" style="width: 100%; height: 36px">
			    	<option value="DNE" selected>Enter</option>
			    	<option value="Strike-off confirmed. Wait for US blanket proceeding">Strike-off confirmed. Wait for US blanket proceeding</option>
			    	<option value="US blanket submitted">US blanket submitted</option>
			    	<option value="Blanket in production">Blanket in production</option>
			    	<option value="Blanket shipped, awaiting initial feedback from US">Blanket shipped, awaiting initial feedback from US</option>
			    	<option value="Colorline proposal submitted">Colorline proposal submitted</option>
			    	<option value="US color revisions requested, see comment">US color revisions requested, see comment</option>
			    	<option value="China team revisions suggested, see comment">China team revisions suggested, see comment</option>
			    	<option value="Confirm to drop">Confirm to drop</option>
			    	<option value="Colorline confirmed">Colorline confirmed</option>
			  	</select>
		  	</div>
		  	<div id="TestBlock" style="flex: 2">
	  			<span class="control-label opacity-75">Testing Progress and Est. Ready Date</span>
		  		<div class="d-flex" style="gap: 5px">
		  			<div style="flex: 2">
	    				<select class="custom-select border border-light border-2 rounded-0 bg-white" id="TestingProgress-${productCode}" name="TestingProgress-${productCode}" size="1" style="width: 100%; height: 36px">
					    	<option value="DNE" selected>Enter</option>
					    	<option value="Testing in progress">Testing in progress</option>
					    	<option value="Under improvement and retesting">Under improvement and retesting</option>
					    	<option value="Testing failed, awaiting US feedback, see comment">Testing failed, awaiting US feedback, see comment</option>
					    	<option value="Testing confirmed to proceed">Testing confirmed to proceed</option>
					    	<option value="Testing passed">Testing passed</option>
					    	<option value="Testing failed, product dropped">Testing failed, product dropped</option>
					  	</select>
				  	</div>
				  	<div style="flex: 1">
				  		<input type="date" id="TestingDatestamp-${productCode}" name="TestingDatestamp-${productCode}" class="custom-select border border-light border-2 rounded-0 bg-white" size="1" style="width: 100%; height: 36px">
				  	</div>
			  	</div>
		  	</div>
       	</div>
       	<div class="container-fluid text-center mx-auto mt-2" style="width: 260px" id="rvcontainer">
            <button class="btn border-0 btn-lg rounded-0 mb-3" style="flex: 1; background-color: #4D73FF; color: white; width: 180px" type="button" onclick="removeProductBlock('${blockId}', '${productCode}')" id="rvbtn">REMOVE</button>
        </div>
    </div>`;

    // Append the block to the container
    document.getElementById("MainContent").insertAdjacentHTML('beforeend', productBlock);
    populateAllInputs(dev);
}

function populateAllInputs(dev) {
	const productCode = dev.code;
	document.getElementById(`Code-${productCode}`).value = dev.code;
	document.getElementById(`Color-${productCode}`).value = dev.color;
	document.getElementById(`GeorgeCancelCB-${productCode}`).checked = dev.georgeCanceled;
	document.getElementById(`inactiveCB-${productCode}`).checked = dev.inactive;
	document.getElementById(`priceConfirmCB-${productCode}`).checked = dev.priceConfirmed;
	document.getElementById(`DesignType-${productCode}`).value = dev.design_type;
	document.getElementById(`Colorist-${productCode}`).value = dev.colorist;
	document.getElementById(`Designer-${productCode}`).value = dev.designer;
	document.getElementById(`Season-${productCode}`).value = dev.season;
	document.getElementById(`YarnType-${productCode}`).value = dev.yarn_type;
	document.getElementById(`WarpType-${productCode}`).value = dev.warp_type;
	document.getElementById(`Direction-${productCode}`).value = dev.direction;
	if (dev.strike_off_status !== "DNE") {
		document.getElementById(`StrikeProgress-${productCode}`).value = dev.strike_off_status;
		document.getElementById(`StrikeBirthday-${productCode}`).value = dev.strike_off_birthday;
	}
	if (dev.blanket_status !== "DNE") {
		document.getElementById(`BlanketStatus-${productCode}`).value = dev.blanket_status;
	}
	if (dev.test_status !== "DNE") {
		document.getElementById(`TestingProgress-${productCode}`).value = dev.test_status;
		document.getElementById(`TestingDatestamp-${productCode}`).value = dev.test_datestamp;
	}
}

function search() {
	var userInput = document.getElementById("titleCode").value;
        
    var matchedDevelopment = dev.find(function(item) {
        return item.code === userInput;  
    });

    if (matchedDevelopment) {
        addProductBlock(matchedDevelopment);
        devids.push(matchedDevelopment.code);
    } else {
        alert("Development not found.");
    }
}

function saveall() {
	let devidsString = devids.join(";");
	const form = document.getElementById("MEform");
    form.action = `SaveNewDevService?action=multedit&devIds=${devidsString}`;
    form.submit();
}

function showBackModal() {
	$('#bkModal').modal("show");
}

function refresh() {
	// Get the element with the id "Tracker"
	var trackerLink = document.getElementById("Tracker");
	
	// Dispatch a click event if the element is found
	if (trackerLink) {
	    trackerLink.click();
	}
}

function removeProductBlock(blockId, productCode) {
    const block = document.getElementById(blockId);
    if (block) {
        block.remove();
    }

    const index = devids.indexOf(productCode);
    if (index > -1) {
        devids.splice(index, 1);
    }
}