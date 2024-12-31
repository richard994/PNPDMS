window.onload = function () {
  minVal.value = 0;
  maxVal.value = 10;
  slideMin();
  slideMax();
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
  minPrice.innerHTML = "$" + minVal.value;
  setArea();
}

function slideMax() {
  let gap = parseInt(maxVal.value) - parseInt(minVal.value);
  if (gap <= 0){
	maxVal.value = parseInt(minVal.value) + minGap;
  }
  maxPrice.innerHTML = "$" + maxVal.value;
  setArea();
}

function setArea() {
  range.style.left = `${
    ((minVal.value - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * 100
  }%`;

  range.style.left = (minVal.value / sliderMaxValue) * 100 + "%";
  range.style.right = `${
    100 -
    ((maxVal.value - sliderMinValue) / (sliderMaxValue - sliderMinValue)) * 100
  }%`;
}

function refresh() {
  window.location.reload();
}

function redirect(url) {
    window.location.replace(url);
}