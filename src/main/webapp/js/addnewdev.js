function validate() {
	if (document.getElementById("Code").value == "") {
		alert("Please enter fabric code!");
		return false;
	} else if (document.getElementById("FabricType").value == "") {
		alert("Please enter fabric type!");
		return false;
	} else if (document.getElementById("DesignType").value == "") {
		alert("Please enter design type!");
		return false;
	} else if (document.getElementById("Backing").value == "") {
		alert("Please enter finishing used!");
		return false;
	} else if (document.getElementById("Season").value == "") {
		alert("Please enter season!");
		return false;
	} else if (document.getElementById("YarnType").value == "") {
		alert("Please enter yarn type!");
		return false;
	} else if (document.getElementById("WarpType").value == "") {
		alert("Please enter warp type!");
		return false;
	} else if (document.getElementById("Content").value == "") {
		alert("Please enter content!");
		return false;
	}  else if (document.getElementById("Weight").value == "") {
		alert("Please enter Weight!");
		return false;
	} else if (document.getElementById("PPCM").value == "") {
		alert("Please enter PPCM!");
		return false;
	}
	
	if (window.getComputedStyle(document.getElementById("Leah-comment-input-block")).display == "block") {
		if (document.getElementById("LeahCommentInput").value == "") {
			alert("Please enter Leah comment!");
			return false;
		} else if (document.getElementById("LeahCommentDatestamp").value == "") {
			alert("Please enter datastamp for Leah comment!");
			return false;
		}
	} else if (window.getComputedStyle(getElementById("US-comment-input-block")).display == "block") {
		if (document.getElementById("USCommentInput").value == "") {
			alert("Please enter US comment!");
			return false;
		} else if (document.getElementById("USCommentDatestamp").value == "") {
			alert("Please enter datastamp for US comment!");
			return false;
		}
	} else if (window.getComputedStyle(document.getElementById("Mill-comment-input-block")).display == "block") {
		if (document.getElementById("MillCommentInput").value == "") {
			alert("Please enter Mill comment!");
			return false;
		} else if (document.getElementById("MillCommentDatestamp").value == "") {
			alert("Please enter datastamp for Mill comment!");
			return false;
		}
	} else if (window.getComputedStyle(document.getElementById("George-comment-input-block")).display == "block") {
		if (document.getElementById("GeorgeCommentInput").value == "") {
			alert("Please enter George comment!");
			return false;
		} else if (document.getElementById("GeorgeCommentDatestamp").value == "") {
			alert("Please enter datastamp for George comment!");
			return false;
		}
	}
	
	return true;
}

const strike = document.getElementById("StrikeProgress");
const blanket = document.getElementById("BlanketStatus");
const roll = document.getElementById("RollSampleProgress");
const test = document.getElementById("TestingProgress");

strike.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (selectedValue == "Confirmed") {
		document.getElementById("StrikeProgressing").style.display = "none";
		document.getElementById("StrikeProgressed").style.display = "block";
	} else {
		document.getElementById("StrikeProgressing").style.display = "block";
		document.getElementById("StrikeProgressed").style.display = "none";
	}
});

blanket.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (selectedValue == "Shipped" || selectedValue == "ColorSubmitted") {
		document.getElementById("BlanketNoProgress").style.display = "none";
		document.getElementById("BlanketProgressing").style.display = "none";
		document.getElementById("BlanketProgressed").style.display = "block";
	} else if (selectedValue == "DNE"){
		document.getElementById("BlanketNoProgress").style.display = "block";
		document.getElementById("BlanketProgressing").style.display = "none";
		document.getElementById("BlanketProgressed").style.display = "none";
	} else {
		document.getElementById("BlanketNoProgress").style.display = "none";
		document.getElementById("BlanketProgressing").style.display = "block";
		document.getElementById("BlanketProgressed").style.display = "none";
	}
});

roll.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (selectedValue == "Shipped" || selectedValue == "Finished") {
		document.getElementById("RollNoProgress").style.display = "none";
		document.getElementById("RollProgressing").style.display = "none";
		document.getElementById("RollProgressed").style.display = "block";
	} else if (selectedValue == "DNE"){
		document.getElementById("RollNoProgress").style.display = "block";
		document.getElementById("RollProgressing").style.display = "none";
		document.getElementById("RollProgressed").style.display = "none";
	} else {
		document.getElementById("RollNoProgress").style.display = "none";
		document.getElementById("RollProgressing").style.display = "block";
		document.getElementById("RollProgressed").style.display = "none";
	}
});

test.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (selectedValue == "Passed") {
		document.getElementById("TestNoProgress").style.display = "none";
		document.getElementById("TestProgressing").style.display = "none";
		document.getElementById("TestProgressed").style.display = "block";
	} else if (selectedValue == "DNE"){
		document.getElementById("TestNoProgress").style.display = "block";
		document.getElementById("TestProgressing").style.display = "none";
		document.getElementById("TestProgressed").style.display = "none";
	} else {
		document.getElementById("TestNoProgress").style.display = "none";
		document.getElementById("TestProgressing").style.display = "block";
		document.getElementById("TestProgressed").style.display = "none";
	}
});

// Function to trigger file input dialog on button click
function triggerFileInput(buttonId, fileInputId) {
    const button = document.getElementById(buttonId);
    const fileInput = document.getElementById(fileInputId);

    button.addEventListener('click', function() {
        fileInput.click();  // Trigger the file input click when the button is clicked
    });

    // Handle file selection
    fileInput.addEventListener('change', function(event) {
        const file = event.target.files[0];  // Get the selected file

        if (file) {
            const reader = new FileReader();
            
            reader.onload = function(e) {
                // Preview the selected image
                if (buttonId == "FabricPicBtn"){
					var pic = document.getElementById('FabricPic');
	                pic.src = e.target.result;
	                pic.alt = file.name;
				}
				else if (buttonId == "PidBtn") {
					var pic = document.getElementById('PidPic');
	                pic.src = e.target.result;
	                pic.alt = file.name;
				}
				else if (buttonId == "TestReportBtn") {
					var pic = document.getElementById('TestPic');
	                pic.src = e.target.result;
	                pic.alt = file.name;
				}
            }

            reader.readAsDataURL(file);  // Convert the image to a Data URL for preview
        }
    });
}

// Initialize the buttons with their corresponding file input elements
triggerFileInput("FabricPicBtn", "FabricPicInput");
triggerFileInput("PidBtn", "PidInput");
triggerFileInput("TestReportBtn", "TestReportInput");

const leahcmt = document.getElementById("LeahCommentBtn");
const uscmt = document.getElementById("USCommentBtn");
const millcmt = document.getElementById("MillCommentBtn");
const georgecmt = document.getElementById("GeorgeCommentBtn");

leahcmt.addEventListener("click", function(){
	document.getElementById("placeholder-comment-Leah").style.display = "none";
	document.getElementById("Leah-comment-input-block").style.display = "block";
});

uscmt.addEventListener("click", function(){
	document.getElementById("placeholder-comment-US").style.display = "none";
	document.getElementById("US-comment-input-block").style.display = "block";
});

millcmt.addEventListener("click", function(){
	document.getElementById("placeholder-comment-Mill").style.display = "none";
	document.getElementById("Mill-comment-input-block").style.display = "block";
});

georgecmt.addEventListener("click", function(){
	document.getElementById("placeholder-comment-George").style.display = "none";
	document.getElementById("George-comment-input-block").style.display = "block";
});

function disableAllInputs() {
    // Get all input, textarea, select elements and disable them
    var inputs = document.querySelectorAll('input, select, textarea, button'); 
    inputs.forEach(function(input) {
        if (!input.closest('.carousel, .modal-header, .modal-footer') && input.id !== "ShowFullLogHistoryLink") {  
            input.style.pointerEvents = 'none';  // Disable mouse interaction
            input.setAttribute('readonly', true);  // For inputs, textarea (optional)
            input.disabled = false;  // Don't set disabled to true to avoid style changes
        }
    });
}

function populateAllInputs() {
	if (typeof dev.fabric_img_path !== 'undefined') {
		document.getElementById("FabricPic").src = dev.fabric_img_path;
	}
	if (typeof dev.pid_path !== 'undefined') {
		document.getElementById("PidPic").src = dev.pid_path;
	}
	if (typeof dev.test_report_path !== 'undefined') {
		document.getElementById("TestPic").src = dev.test_report_path;
	}
	document.getElementById("Code").value = dev.code;
	document.getElementById("Color").value = dev.color;
	document.getElementById("Cost").value = dev.cost;
	document.getElementById("ParagonCleanCB").checked = dev.paragonClean;
	document.getElementById("FCLCB").checked = dev.is400hrFCL;
	document.getElementById("PDCB").checked = dev.pieceDyed;
	document.getElementById("ChenilleCB").checked = dev.chenille;
	document.getElementById("FeedbackCB").checked = dev.needFeedback;
	document.getElementById("SDYCB").checked = dev.sdy;
	document.getElementById("FabricType").value = dev.fabric_type;
	document.getElementById("DesignType").value = dev.design_type;
	document.getElementById("Colorist").value = dev.colorist;
	const selectedFinArr = dev.finishing_used.split(",");
	document.querySelector('#Backing').setValue(selectedFinArr, false);
	document.getElementById("Season").value = dev.season;
	document.getElementById("YarnType").value = dev.yarn_type;
	document.getElementById("WarpType").value = dev.warp_type;
	document.getElementById("Content").value = dev.content;
	if (dev.strike_off_status !== "DNE") {
		document.getElementById("StrikeProgress").value = dev.strike_off_status;
		document.getElementById("StrikeProgress").dispatchEvent(new Event('change'));
	}
	if (dev.blanket_status !== "DNE") {
		document.getElementById("BlanketStatus").value = dev.blanket_status;
		document.getElementById("BlanketStatus").dispatchEvent(new Event('change'));
	}
	if (dev.rollsample_status !== "DNE") {
		document.getElementById("ColorLineProgress").value = dev.colorline_status;
		document.getElementById("ColorlineDatestamp").value = dev.colorline_datestamp;
		document.getElementById("RollSampleProgress").value = dev.rollsample_status;
		document.getElementById("RollSampleDatestamp").value = dev.rollsample_datestamp;
		document.getElementById("RollSampleProgress").dispatchEvent(new Event('change'));
	}
	if (dev.test_status !== "DNE") {
		document.getElementById("TestingProgress").value = dev.test_status;
		document.getElementById("TestingDatestamp").value = dev.test_datestamp;
		document.getElementById("TestingProgress").dispatchEvent(new Event('change'));
	}
	document.getElementById("MOQ").value = dev.moq;
	document.getElementById("Weight").value = dev.weight;
	document.getElementById("NumColorLine").value = dev.numColorline;
	document.getElementById("PPCM").value = dev.ppcm;
	document.getElementById("Note").value = dev.note;
	var devDate = new Date(dev.dateCurrentPhase);
	var currentDate = new Date();
	var difference = currentDate - devDate;
	var daysPassed = difference / (1000 * 60 * 60 * 24);
	daysPassed = Math.floor(daysPassed);
	document.getElementById("CurrDaySpent").textContent = daysPassed;
	parseComments();
	parseLogs();
}

function parseComments() {
	// Initialize an object to store comments categorized by name
	var categorizedComments = {
	    Leah: [],
	    US: [],
	    Mill: [],
	    George: []
	};
	
	
	// Loop through each comment in the `comments` array
	comments.forEach(function(comment) {
	    // Check if the content is not an empty string or null
	    var content = comment.content.trim();  // Trim to remove spaces
	    if (content !== "") {
	        // Add the formatted comment to the appropriate list based on name
	        if (categorizedComments[comment.name]) {
	            categorizedComments[comment.name].push([content, comment.datestamp, comment.commentid]);
	        }
	    }
	});
	
	for (var name in categorizedComments) {
	    if (categorizedComments.hasOwnProperty(name)) {
	        var commentsList = categorizedComments[name]; // Array of comments for this name
	
	        // Loop through the comments array for each name
	        if (commentsList.length > 0) {
				document.getElementById("placeholder-comment-" + name).style.display = "none";
				var index = 0;
	            commentsList.forEach(function(comment) {
					var row = document.createElement('div');
	                row.classList.add('comment', 'p-0', 'd-flex');
	                row.style.whiteSpace = "nowrap";
	                
	                // Create the comment div element
				    var commentDiv = document.createElement('div');
				    commentDiv.classList.add('comment', 'p-2', 'd-flex');
				    commentDiv.style.flex = "7";
				
				    // Create the comment texts div
				    var commentTextsDiv = document.createElement('div');
				    commentTextsDiv.classList.add('commentTexts');
				    commentTextsDiv.style.flex = "3";
				    commentTextsDiv.style.whiteSpace = "normal";
				    commentTextsDiv.style.wordBreak = "break-all";
				    var commentText = document.createElement('span');
				    commentText.textContent = comment[0]; 
				    commentTextsDiv.appendChild(commentText);
				
				    // Create the comment date div
				    var commentDateDiv = document.createElement('div');
				    commentDateDiv.classList.add('commentDateStamp');
				    commentDateDiv.style.flex = "2";
				    var commentDateSpanHdrDiv = document.createElement('div');
				    commentDateSpanHdrDiv.style.float = "right";
				    var commentDate = document.createElement('span');
				    commentDate.textContent = comment[1];
				    commentDateSpanHdrDiv.appendChild(commentDate);
				    commentDateDiv.appendChild(commentDateSpanHdrDiv);
				    
				    // Create the delete button
				    var deleteDiv = document.createElement('div');
				    deleteDiv.classList.add(name + 'commentDeleteBtn', 'm-auto');
				    deleteDiv.style.flex = "1";
				    var deleteBtn = document.createElement('button');
				    deleteBtn.name = 'cmtDeleteBtn' + index;
				    deleteBtn.id = 'cmtDeleteBtn' + index;
				    deleteBtn.textContent = 'DELETE';
				    deleteBtn.type = 'button';
				    deleteBtn.style.border = "0";
				    deleteBtn.style.background = "none";
				    deleteBtn.style.color = "#DC3545";
				    deleteBtn.style.fontSize = "14px";
				    deleteBtn.style.padding = "4px 8px";
				    deleteBtn.onclick = function() {
					    showCmtModal(devid, comment[2]);
					};
					deleteBtn.disabled = view;
				    deleteDiv.appendChild(deleteBtn);
				
				    commentDiv.appendChild(commentTextsDiv);
				    commentDiv.appendChild(commentDateDiv);
				    
				    row.appendChild(commentDiv);
				    row.appendChild(deleteDiv);
				
				    // Append the comment div to LeahComments
				    document.getElementById(name + "Comments").appendChild(row);
				
				    // Apply special styles to the first comment (first child)
				    if (index === 0) {
				        commentDiv.style.background = '#4D73FF';
				        commentDiv.style.color = 'white';
				    }
	                index += 1;
	            });
	        } 
	    }
	}
}

function parseLogs() {
	if (Array.isArray(logs) && logs.length !== 0) {
		document.getElementById("logBlock").style.display = "block";
	}
	logs.forEach(function(log) {
		var name = log.name;
		var date = formatDate(log.datestamp);
		var content = log.content;
		var logDiv = document.createElement('div');
		logDiv.classList.add('logcontainer','d-flex','flex-column','bg-white','rounded-3');
		logDiv.style.padding = "9px";
		// Create the outer <span> element
		var outerSpan = document.createElement('span');
		
		// Create the inner <span> for the name
		var nameSpan = document.createElement('span');
		nameSpan.style.color = '#4D73FF';  // Set color to #4D73FF
		nameSpan.textContent = name;  // Set the text content
		
		// Create the inner <span> for the timestamp
		var datestampSpan = document.createElement('span');
		datestampSpan.style.fontSize = '11px';  // Set font size
		datestampSpan.textContent = ' ' + date + ':';  // Set the text content
		
		// Append the name and datestamp spans to the outer span
		outerSpan.appendChild(nameSpan);
		outerSpan.appendChild(datestampSpan);
		logDiv.appendChild(outerSpan);
		
		var contentSpan = document.createElement('span');
		contentSpan.style.fontSize = "14px";
		contentSpan.style.whiteSpace = "nowrap";
		contentSpan.style.overflow = "hidden";
		contentSpan.style.textOverflow = "ellipsis";
		contentSpan.textContent = content;
		contentSpan.setAttribute("title", content);
		// Create the show more button
	    var showMoreBtn = document.createElement('span');
	    showMoreBtn.textContent = "Show more";
	    showMoreBtn.style.fontSize = "14px";
	    showMoreBtn.style.opacity = "0.5";
	    showMoreBtn.style.cursor = "pointer";
	    showMoreBtn.classList.add("show-more-btn");
	
	    // Add event listener to toggle the content display
	    showMoreBtn.addEventListener("click", function() {
	        logDiv.classList.toggle("expanded");
	        if (logDiv.classList.contains("expanded")) {
	            showMoreBtn.textContent = "Show less";
	            contentSpan.style.whiteSpace = "normal";
	        } else {
	            showMoreBtn.textContent = "Show more";
	            contentSpan.style.whiteSpace = "nowrap";
	        }
	    });
		logDiv.appendChild(contentSpan);
		logDiv.appendChild(showMoreBtn);
		document.getElementById("logBlock").insertBefore(logDiv, document.getElementById("ShowFullLogHistoryLink"));
	});
}

function formatDate(datestamp) {
    // Create a Date object from the given ISO 8601 string
    var date = new Date(datestamp);

    // Extract the parts of the date (month, day, hours, minutes)
    var month = date.getMonth() + 1;  // getMonth() returns 0-11, so we add 1
    var day = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();

    // Format minutes to always show two digits (e.g., 7 -> 07)
    minutes = minutes < 10 ? '0' + minutes : minutes;

    // Determine if it's AM or PM
    var ampm = hours >= 12 ? 'pm' : 'am';

    // Convert hours to 12-hour format
    hours = hours % 12;
    hours = hours ? hours : 12; // 12-hour format fix (12 should be displayed as 12, not 0)

    // Return the formatted string as "MM/DD, hh:mm am/pm"
    return month + '/' + day + ', ' + hours + ':' + minutes + ampm;
}

// Function to change the form action based on the clicked button
function setFormAction(action) {
    var form = document.getElementById('NDform');  // Get the form element
    form.action = action;  // Set the action to the specified URL
}

function redirect(url) {
    window.location.replace(url);
}

function showModal() {
	$('#dlModal').modal("show");
}

function showCmtModal(devid, commentId) {
    $('#dlCmtModal').modal("show");
    $('#confirmCmtDelete').on('click', function(){
        redirect('NewDevService?action=deleteCmt&devId=' + devid + '&cmtId=' + commentId);
    });
}

function showFullLogModal(event) {
	event.preventDefault();
	// Get the table body where the rows will be added
    var tableBody = document.getElementById('logTable').getElementsByTagName('tbody')[0];
	// Clear any existing rows in the table
  	tableBody.innerHTML = '';
  	// Loop through the logs and add a row for each log
	fullLogs.forEach(function(log) {
	    var row = tableBody.insertRow(); // Create a new row
	    var cell1 = row.insertCell(0);   // Create first cell (name)
	    var cell2 = row.insertCell(1);   // Create second cell (date)
	    var cell3 = row.insertCell(2);   // Create third cell (content)
	
	    // Set the content of each cell
	    cell1.textContent = log.name;
	    cell2.textContent = formatDate(log.datestamp);
	    cell3.textContent = log.content;
	});
	
	// Show the modal
	$('#showFullLogModal').modal("show");
}
