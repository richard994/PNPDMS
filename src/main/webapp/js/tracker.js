window.onload = function () {
  if (!filtered) {
	  minVal.value = 0;
	  maxVal.value = 100;
	  slideMin();
	  slideMax();
  }
};

const minVal = document.querySelector(".min-val");
const maxVal = document.querySelector(".max-val");
const minGap = 1;
const minPrice = document.getElementById("min-price");
const maxPrice = document.getElementById("max-price");
const range = document.querySelector(".slider-track");
const sliderMinValue = parseInt(minVal.min);
const sliderMaxValue = parseInt(maxVal.max);

function slideMin() {
  let gap = parseInt(maxVal.value) - parseInt(minVal.value);
  if (gap <= 0){
	minVal.value = parseInt(maxVal.value) - minGap;
  }
  minPrice.innerHTML = "¥" + minVal.value;
  setArea();
}

function slideMax() {
  let gap = parseInt(maxVal.value) - parseInt(minVal.value);
  if (gap <= 0){
	maxVal.value = parseInt(minVal.value) + minGap;
  }
  maxPrice.innerHTML = "¥" + maxVal.value;
  setArea();
}

function setArea() {
  const minPercentage = (parseInt(minVal.value) - sliderMinValue) / (sliderMaxValue - sliderMinValue) * 100;
  const maxPercentage = (parseInt(maxVal.value) - sliderMinValue) / (sliderMaxValue - sliderMinValue) * 100;

  range.style.left = `${minPercentage}%`;
  range.style.width = `${maxPercentage - minPercentage}%`;
}

function refresh() {
	// Get the element with the id "Tracker"
	var trackerLink = document.getElementById("Tracker");
	
	// Dispatch a click event if the element is found
	if (trackerLink) {
	    trackerLink.click();
	}
}

function redirect(url) {
    window.location.replace(url);
}

function showModal() {
	$('#expModal').modal("show");
}

function showSortModal() {
	$('#sortModal').modal("show");
}

function populateAllInputs() {
	document.getElementById("titleCode").value = dev.code;
	document.getElementById("min-price").textContent = dev.priceMin;
	document.getElementById("PriceRangeMin").value = dev.priceMin;
	slideMin();
	document.getElementById("max-price").textContent = dev.priceMax;
	document.getElementById("PriceRangeMax").value = dev.priceMax;
	slideMax();
	document.getElementById("ParagonCleanCB").checked = dev.paragonClean;
	document.getElementById("FCLCB").checked = dev.is400hrFCL;
	document.getElementById("TFCLCB").checked = dev.is1000hrFCL;
	document.getElementById("PDCB").checked = dev.pieceDyed;
	document.getElementById("ChenilleCB").checked = dev.chenille;
	document.getElementById("FeedbackCB").checked = dev.needFeedback;
	document.getElementById("ChinaFeedbackCB").checked = dev.needChinaFeedback;
	document.getElementById("SDYCB").checked = dev.sdy;
	document.getElementById("KnitCB").checked = dev.knit;
	document.getElementById("GeorgeCancelCB").checked = dev.georgeCanceled;
	document.getElementById("ActiveCB").checked = (dev.inactive === false);
	document.getElementById("FabricType").value = dev.fabric_type;
	document.getElementById("DesignType").value = dev.design_type;
	document.getElementById("Colorist").value = dev.colorist;
	document.getElementById("Designer").value = dev.designer;
	document.getElementById("Season").value = dev.season;
	document.getElementById("YarnType").value = dev.yarn_type;
	document.getElementById("WarpType").value = dev.warp_type;
	document.getElementById("Direction").value = dev.direction;
	const selectedStrikeArr = dev.strike_off_status.split("!");
	const modifiedStrikeArr = selectedStrikeArr.map(item => item + "!");
	document.querySelector('#StrikeProgress').setValue(modifiedStrikeArr, false);
	const selectedBlanketArr = dev.blanket_status.split("!");
	const modifiedBlanketArr = selectedBlanketArr.map(item => item + "!");
	document.querySelector('#BlanketStatus').setValue(modifiedBlanketArr, false);
	document.getElementById("ColorLineProgress").value = dev.colorline_status;
	document.getElementById("RollSampleProgress").value = dev.rollsample_status;
	const selectedTestArr = dev.test_status.split("!");
	const modifiedTestArr = selectedTestArr.map(item => item + "!");
	document.querySelector('#TestingProgress').setValue(modifiedTestArr, false);
	window.scrollTo(0, scrollPosition);
}