const Phase = Object.freeze({
  ONE: "Stike-off",
  TWO: "Blanket",
  THREE: "RollSample",
  FOUR: "Test"
});

var currentPhase = Phase.ONE;

function validate() {
	if (document.getElementById("Title").value == "") {
		alert("Please enter title!");
		return false;
	} else if (document.getElementById("Code").value == "") {
		alert("Please enter fabric code!");
		return false;
	} else if (document.getElementById("Color").value == "") {
		alert("Please enter fabric color!");
		return false;
	} else if (document.getElementById("FabricType").value == "") {
		alert("Please enter fabric type!");
		return false;
	} else if (document.getElementById("DesignType").value == "") {
		alert("Please enter design type!");
		return false;
	} else if (document.getElementById("Colorist").value == "") {
		alert("Please enter fabric colorist!");
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
		alert("Please enter content! Example: 1% acrylic 99% polyester");
		return false;
	}  else if (document.getElementById("MOQ").value == "") {
		alert("Please enter MOQ!");
		return false;
	} else if (document.getElementById("Weight").value == "") {
		alert("Please enter Weight!");
		return false;
	} else if (document.getElementById("PPCM").value == "") {
		alert("Please enter PPCM!");
		return false;
	} else {
		if (document.getElementById("StrikeProgress").value == "") {
			alert("Please enter strike progress!");
			return false;
		}
		
		checkPhase();
		if (currentPhase == Phase.TWO || currentPhase == Phase.THREE || currentPhase == Phase.FOUR)
		{
			if (document.getElementById("BlanketStatus").value == "") {
				alert("Please enter blanket progress!");
				return false;
			}
		}
		
		if (currentPhase == Phase.THREE || currentPhase == Phase.FOUR) {
			if (document.getElementById("RollSampleProgress").value == "") {
				alert("Please enter roll sample progress!");
				return false;
			} else if (document.getElementById("RollSampleDatestamp").value == "") {
				alert("Please enter roll sample expected ready date!");
				return false;
			} else if (document.getElementById("ColorLineProgress").value == "") {
				alert("Please enter colorline progress!");
				return false;
			} else if (document.getElementById("ColorlineDatestamp").value == "") {
				alert("Please enter colorline expected ready date!");
				return false;
			}
		}
		
		if (currentPhase == Phase.FOUR) {
			if (document.getElementById("TestingProgress").value == "") {
				alert("Please enter testing progress!");
				return false;
			} else if (document.getElementById("TestingDatestamp").value == "") {
				alert("Please enter testing expected ready date!");
				return false;
			}
		}
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

function checkPhase(selectedValue) {
	if (currentPhase == Phase.ONE && (selectedValue == "Confirmed" || selectedValue == "Revision")) {
		currentPhase = Phase.TWO;
	} else if (currentPhase == Phase.TWO && selectedValue == "ColorSubmitted") {
		currentPhase = Phase.THREE;
	} else if (currentPhase == Phase.THREE && selectedValue == "Shipped") {
		currentPhase = Phase.FOUR;
	}
}

function changePhase(selectedValue) {
	checkPhase(selectedValue);
	if (currentPhase == Phase.TWO) {
		document.getElementById("StrikeProgressing").style.display = "none";
		document.getElementById("StrikeProgressed").style.display = "block";
		document.getElementById("BlanketNoProgress").style.display = "none";
		document.getElementById("BlanketProgressing").style.display = "block";
		document.getElementById("BlanketBlock").style.display = "block";
	} else if (currentPhase == Phase.THREE) {
		document.getElementById("BlanketProgressing").style.display = "none";
		document.getElementById("BlanketProgressed").style.display = "block";
		document.getElementById("RollNoProgress").style.display = "none";
		document.getElementById("RollProgressing").style.display = "block";
		document.getElementById("ColorlineBlock").style.display = "block";
		document.getElementById("RollSampleBlock").style.display = "block";
	} else if (currentPhase == Phase.FOUR) {
		document.getElementById("RollProgressing").style.display = "none";
		document.getElementById("RollProgressed").style.display = "block";
		document.getElementById("TestNoProgress").style.display = "none";
		document.getElementById("TestProgressing").style.display = "block";
		document.getElementById("TestBlock").style.display = "block";
		if (selectedValue == "Passed") {
			document.getElementById("TestProgressing").style.display = "none";
			document.getElementById("TestProgressed").style.display = "block";
		} 
	}
}

function revertPhase(phase) {
	if (phase == Phase.ONE) {
		document.getElementById("StrikeProgressing").style.display = "block";
		document.getElementById("StrikeProgressed").style.display = "none";
		document.getElementById("BlanketNoProgress").style.display = "block";
		document.getElementById("BlanketProgressing").style.display = "none";
		document.getElementById("BlanketProgressed").style.display = "none";
		document.getElementById("RollNoProgress").style.display = "block";
		document.getElementById("RollProgressing").style.display = "none";
		document.getElementById("RollProgressed").style.display = "none";
		document.getElementById("TestNoProgress").style.display = "block";
		document.getElementById("TestProgressing").style.display = "none";
		document.getElementById("TestProgressed").style.display = "none";
		document.getElementById("BlanketBlock").style.display = "none";
		document.getElementById("ColorlineBlock").style.display = "none";
		document.getElementById("RollSampleBlock").style.display = "none";
		document.getElementById("TestBlock").style.display = "none";
	} else if (phase == Phase.TWO) {
		document.getElementById("StrikeProgressing").style.display = "none";
		document.getElementById("StrikeProgressed").style.display = "block";
		document.getElementById("BlanketNoProgress").style.display = "none";
		document.getElementById("BlanketProgressing").style.display = "block";
		document.getElementById("BlanketProgressed").style.display = "none";
		document.getElementById("RollNoProgress").style.display = "block";
		document.getElementById("RollProgressing").style.display = "none";
		document.getElementById("RollProgressed").style.display = "none";
		document.getElementById("TestNoProgress").style.display = "block";
		document.getElementById("TestProgressing").style.display = "none";
		document.getElementById("TestProgressed").style.display = "none";
		document.getElementById("BlanketBlock").style.display = "block";
		document.getElementById("ColorlineBlock").style.display = "none";
		document.getElementById("RollSampleBlock").style.display = "none";
		document.getElementById("TestBlock").style.display = "none";
	} else if (phase == Phase.THREE) {
		document.getElementById("BlanketProgressing").style.display = "none";
		document.getElementById("BlanketProgressed").style.display = "block";
		document.getElementById("RollNoProgress").style.display = "none";
		document.getElementById("RollProgressing").style.display = "block";
		document.getElementById("RollProgressed").style.display = "none";
		document.getElementById("TestNoProgress").style.display = "block";
		document.getElementById("TestProgressing").style.display = "none";
		document.getElementById("TestProgressed").style.display = "none";
		document.getElementById("ColorlineBlock").style.display = "block";
		document.getElementById("RollSampleBlock").style.display = "block";
		document.getElementById("TestBlock").style.display = "none";
	} else if (phase == Phase.FOUR) {
		document.getElementById("TestProgressing").style.display = "block";
		document.getElementById("TestProgressed").style.display = "none";
	}
	currentPhase = phase;
}

const strike = document.getElementById("StrikeProgress");
const blanket = document.getElementById("BlanketStatus");
const roll = document.getElementById("RollSampleProgress");
const test = document.getElementById("TestingProgress");

strike.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (currentPhase != Phase.ONE && (selectedValue != "Confirmed" && selectedValue != "Revision")) {
		revertPhase(Phase.ONE);
	} else {
		changePhase(selectedValue);
	}
});

blanket.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (currentPhase != Phase.TWO && selectedValue != "ColorSubmitted") {
		revertPhase(Phase.TWO);
	} else {
		changePhase(selectedValue);
	}
});

roll.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (currentPhase != Phase.THREE && selectedValue != "Shipped") {
		revertPhase(Phase.THREE);
	} else {
		changePhase(selectedValue);
	}
});

test.addEventListener("change", function(event) {
	const selectedValue = event.target.value;
	if (selectedValue != "Passed") {
		revertPhase(Phase.FOUR);
	}
	changePhase(selectedValue);
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
        if (!input.closest('.carousel')) {  
            input.disabled = true;  
        }
    });
}

function populateAllInputs() {
	document.getElementById("FabricPic").src = dev.fabric_img_path;
	if (typeof dev.pid_path !== 'undefined') {
		document.getElementById("PidPic").src = dev.pid_path;
	}
	if (typeof dev.test_report_path !== 'undefined') {
		document.getElementById("TestPic").src = dev.test_report_path;
	}
}
