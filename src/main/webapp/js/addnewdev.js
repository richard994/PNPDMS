const Phase = Object.freeze({
  ONE: "Stike-off",
  TWO: "Blanket",
  THREE: "RollSample",
  FOUR: "Test"
});

var currentPhase = Phase.ONE;

function validate() {
	if (document.getElementById("Title") == "") {
		alert("Please enter title!");
		return false;
	} else if (document.getElementById("Code") == "") {
		alert("Please enter fabric code!");
		return false;
	} else if (document.getElementById("Color") == "") {
		alert("Please enter fabric color!");
		return false;
	} else if (document.getElementById("FabricType") == "") {
		alert("Please enter fabric type!");
		return false;
	} else if (document.getElementById("DesignType") == "") {
		alert("Please enter design type!");
		return false;
	} else if (document.getElementById("Colorist") == "") {
		alert("Please enter fabric colorist!");
		return false;
	} else if (document.getElementById("Backing") == "") {
		alert("Please enter finishing used!");
		return false;
	} else if (document.getElementById("Season") == "") {
		alert("Please enter season!");
		return false;
	} else if (document.getElementById("YarnType") == "") {
		alert("Please enter yarn type!");
		return false;
	} else if (document.getElementById("WarpType") == "") {
		alert("Please enter warp type!");
		return false;
	} else if (document.getElementById("Content") == "") {
		alert("Please enter content! Example: 1% acrylic 99% polyester");
		return false;
	} else {
		if (document.getElementById("StrikeProgress") == "") {
			alert("Please enter strike progress!");
			return false;
		}
		
		checkPhase();
		if (currentPhase == Phase.TWO || currentPhase == Phase.THREE || currentPhase == Phase.FOUR)
		{
			if (document.getElementById("BlanketStatus") == "") {
				alert("Please enter blanket progress!");
				return false;
			}
		}
		
		if (currentPhase == Phase.THREE || currentPhase == Phase.FOUR) {
			if (document.getElementById("RollSampleProgress") == "") {
				alert("Please enter roll sample progress!");
				return false;
			} else if (document.getElementById("RollSampleDatestamp") == "") {
				alert("Please enter roll sample expected ready date!");
				return false;
			} else if (document.getElementById("ColorLineProgress") == "") {
				alert("Please enter colorline progress!");
				return false;
			} else if (document.getElementById("ColorlineDatestamp") == "") {
				alert("Please enter colorline expected ready date!");
				return false;
			}
		}
		
		if (currentPhase == Phase.FOUR) {
			if (document.getElementById("TestingProgress") == "") {
				alert("Please enter testing progress!");
				return false;
			} else if (document.getElementById("TestingDatestamp") == "") {
				alert("Please enter testing expected ready date!");
				return false;
			}
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