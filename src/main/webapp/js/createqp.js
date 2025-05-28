// global counter for adding yarns
let counter = document.getElementById('wtablerow').childElementCount;
// populate first row of yarns selector
populateMatTypeSelect(document.getElementById('mattype1'));
// handle delete of first row
const btnDel = document.getElementById('1');
btnDel.addEventListener('click', () => {
  	document.getElementById('row1').remove();
  	renumberRows();
  	if (validateNoAlert()) {
	      calculatePrice();
	}
});
// wire all inputs to calculate price
document.addEventListener('DOMContentLoaded', initCalculators);
// disable unavailable dye options for the yarn
document.getElementById('wtablerow')
  .addEventListener('change', e => {
    const tgt = e.target;
    if (tgt.tagName === 'SELECT' && tgt.id.startsWith('mattype')) {
      const idx = tgt.id.slice('mattype'.length);
      const colSel = document.getElementById(`colortype${idx}`);
      if (!colSel) return;
        // 1) reset to default
	    colSel.selectedIndex = 0;
	    if (validateNoAlert()) {
	      calculatePrice();
	    }
	
	    // 2) find the mat object
	    const matType = String(tgt.value);
	    const mat     = mats.find(m => m.name === matType);
	    if (!mat) return;
	
	    // 3) mapping from option text → price field name
	    const priceFieldFor = {
	      "White Yarn":     "whitePrice",
	      "Yarn Dyed":      "dyePrice",
	      "Fiber Dyed Yarn":"colorPrice",
	      "Space Dyed Yarn":"drPrice",
	      "Jet Dyed Yarn":  "prPrice"
	    };
	
	    // 4) for each <option> in the color dropdown
	    Array.from(colSel.options).forEach(opt => {
	      // leave the “Enter” placeholder enabled
	      if (opt.value === "") {
	        opt.disabled = false;
	        return;
	      }
	      const field = priceFieldFor[opt.value];
	      const price = field && mat[field];
	      // disable if price is zero or missing
	      opt.disabled = !(price > 0);
	    });
    }
  });
  
// search for every mattype select
function initMattypeChoices() {
  document.querySelectorAll('select.mattype').forEach(selectEl => {
    if (!selectEl.dataset.choicesInitialized) {
	  let preselected = selectEl.value !== '' ? selectEl.value : '';
	  const choiceOpts = {
		  searchEnabled: true,
		  shouldSort: false,
		  placeholderValue: 'Enter',
		  searchPlaceholderValue: 'Search...',
	  };
      let instance = new Choices(selectEl, choiceOpts);
      if (preselected !== '') {
	  	instance.setChoiceByValue(preselected);
	  }
      selectEl.dataset.choicesInitialized = 'true';
    };
  });
}
document.addEventListener('DOMContentLoaded', initMattypeChoices);


function next() 
{
	document.getElementById("progress2").style.display = "none";
	document.getElementById("nextbtn").style.display = "none";
	document.getElementById("progressed1").style.display = "block";
	document.getElementById("wwbtncontainer").style.display = "block";
	document.getElementById("WW").style.display = "block";
	document.getElementById("savebtn").style.display = "block";
	document.getElementById("wtable").style.display = "table";
}

function clickPricing() {
	document.getElementById("Pricing").click();
}

function validatePhaseOne() {/*
	if (document.forms["PDform"]["pmodel"].value == "") {
		alert("Please enter product model!");
		return false;
	} else if (document.forms["PDform"]["fabrictype"].value == "") {
		alert("Please select fabric type!");
		return false;
	} else if (document.forms["PDform"]["ppmodel"].value == "") {
		alert("Please select finishing module!");
		return false;
	} else if (document.forms["PDform"]["ppcm"].value == "") {
		alert("Please enter picks per centimeter!");
		return false;
	} else if (document.forms["PDform"]["totalWarp"].value == "") {
		alert("Please enter total warp!");
		return false;
	} */
	next();
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
		// sum all usePercent for this type
		var warpSum = 0;
		var weftSum = 0;
		Array.prototype.forEach.call(
		    document.querySelectorAll('input[id^="usePercent"]'),
		    function(inp) {
		      var rowIdx = inp.id.replace('usePercent','');
		      var wwt    = document.getElementById('wwtype' + rowIdx);
		      wwt = wwt ? wwt.value : '';
		      if (wwt === "Warp") {
		        warpSum += parseFloat(inp.value) || 0;
		      } else if (wwt === "Weft") {
				weftSum += parseFloat(inp.value) || 0;
			  }
		    }
		);
		
		if (warpSum != 100) {
		    alert('Total usePercent for "Warp" need to be 100 (current: '+warpSum+'%)');
		    return false;
		}
		
		if (weftSum != 100) {
		    alert('Total usePercent for "Weft" need to be 100 (current: '+warpSum+'%)');
		    return false;
		}
	}
}

function revertPriceAndWeight() {
	document.getElementById('weight').textContent = 0.0;
	document.getElementById('weightInput').value = 0.0;
	document.getElementById('calculatedPrice').innerHTML = `<i class="fa fa-cny"></i> 0.0`;
	document.getElementById('calculatedPriceInput').value = 0.0;
}

function validateNoAlert() {
	if (document.forms["PDform"]["fabrictype"].value == "") {
		return false;
	} else if (document.forms["PDform"]["ppmodel"].value == "") {
		return false;
	} else if (document.forms["PDform"]["ppcm"].value == "") {
		return false;
	} else if (document.forms["PDform"]["totalWarp"].value == "") {
		return false;
	} else {}
	return true;
}

function addWW(needInitChoice) {
  counter++;
  const wtbody = document.getElementById('wtablerow');
  const tpl    = document.getElementById('ww-row-template');
  const clone  = tpl.content.cloneNode(true);
  const tr     = clone.querySelector('tr');

  tr.id = 'row' + counter;
  tr.querySelector('th').textContent = counter;
  
  const selects = tr.querySelectorAll('select');
  const ww = selects[0];
  const matSelect = selects[1];
  const col = selects[2];
  ww.id    = `wwtype${counter}`;
  ww.name  = `wwtype${counter}`;
  ww.innerHTML = `
    <option value="" selected>Enter</option>
    <option value="Warp">Warp</option>
    <option value="Weft">Weft</option>
  `;

  // Populate the mattype <select>:
  matSelect.id     = `mattype${counter}`;
  matSelect.name   = `mattype${counter}`;
  populateMatTypeSelect(matSelect);
     
  col.id    = `colortype${counter}`;
  col.name  = `colortype${counter}`;
  col.innerHTML = `
    <option value="" selected>Enter</option>
    <option value="White Yarn">White Yarn</option>
    <option value="Yarn Dyed">Yarn Dyed</option>
    <option value="Fiber Dyed Yarn">Piece Dyed Yarn</option>
    <option value="Space Dyed Yarn">Space Dyed Yarn</option>
    <option value="Jet Dyed Yarn">Jet Dyed Yarn</option>
  `;
  
  const use = tr.querySelectorAll('input.form-control')[0];
  use.id    = `usePercent${counter}`;
  use.name  = `usePercent${counter}`;
  
  const memo = tr.querySelectorAll('input.form-control')[1];
  memo.id    = `matMemo${counter}`;
  memo.name  = `matMemo${counter}`;

  const [btnCopy, btnDel] = tr.querySelectorAll('button');
  btnCopy.id       = `cpbtn${counter}`;
  btnCopy.onclick  = () => copy(btnCopy.id);
  btnDel.id        = `${counter}`;
  btnDel.onclick   = () => {
    document.getElementById('row' + counter).remove();
    renumberRows();
    if (validateNoAlert()) {
	      calculatePrice();
	}
  };

  wtbody.appendChild(clone);
  if (needInitChoice) {
	initMattypeChoices();
  }
}

function copy(btnId) {
  // 1) figure out source index & row
  const srcIdx = parseInt(btnId.replace('cpbtn',''), 10);
  const srcRow = document.getElementById('row' + srcIdx);
  if (!srcRow) return;

  // 2) clone it & assign new index
  const clone  = srcRow.cloneNode(true);
  counter += 1;
  const newIdx = counter;
  clone.id = 'row' + newIdx;

  // 3) update the row-number cell
  clone.querySelector('th[scope="row"]').textContent =
    document.getElementById('wtablerow').childElementCount + 1;
  
  // rewire mattype select
  const mattypeCellSrc = srcRow.querySelector(`td:nth-child(3)`); 
  const mattypeCellNew = clone.querySelector(`td:nth-child(3)`);

  // Clear out the cloned cell’s contents
  mattypeCellNew.innerHTML = '';

  // Create a new <select>
  const freshSelect = document.createElement('select');
  freshSelect.className = mattypeCellSrc.querySelector('select.mattype').className; 
  freshSelect.style.cssText = mattypeCellSrc.querySelector('select.mattype').style.cssText;
  freshSelect.size    = mattypeCellSrc.querySelector('select.mattype').size;
  freshSelect.id      = `mattype${newIdx}`;
  freshSelect.name    = `mattype${newIdx}`;

  populateMatTypeSelect(freshSelect);
  const currentValue = mattypeCellSrc.querySelector('div.choices__item--selectable').textContent;
  freshSelect.value = currentValue;
  mattypeCellNew.appendChild(freshSelect);


  // 4) fields to re-map
  const fields = ['wwtype','mattype','colortype','usePercent','matMemo'];

  fields.forEach(field => {
    // locate source & new element
    const srcEl = srcRow.querySelector(`#${field}${srcIdx}`);
    let newEl = clone.querySelector(`#${field}${srcIdx}`);
    if (!srcEl || !newEl) return;
    
    // grab the actual value from the source
    const val = srcEl.value;

    // re-id / re-name on the new element
    newEl.id    = field + newIdx;
    newEl.name  = field + newIdx;

    // **copy** the value back onto the new element
    newEl.value = val;
  });

  // 5) re-wire COPY button
  const [ btnCopy, btnDel ] = clone.querySelectorAll('button');
  btnCopy.id      = 'cpbtn' + newIdx;
  btnCopy.onclick = () => copy(btnCopy.id);

  // 6) re-wire DELETE button
  btnDel.id       = '' + newIdx;
  btnDel.onclick  = () => {
    clone.remove();
    renumberRows();
    if (validateNoAlert()) {
	      calculatePrice();
	}
  };

  // 7) append it
  document.getElementById('wtablerow').appendChild(clone);
  
  initMattypeChoices();
  
  const newUse = document.getElementById(`usePercent${newIdx}`);
  if (newUse && !checkUsePercentLimit(newUse)) {
    // clears it if the sum would exceed 100
    newUse.value = '';
  }
  
  if (validateNoAlert()) {
      calculatePrice();
  }
}

function populateMatTypeSelect(selectEl) {
  // Clear any existing options
  selectEl.innerHTML = '';
  
  const defaultOpt = document.createElement('option');
  defaultOpt.value    = '';
  defaultOpt.text     = 'Enter';     
  defaultOpt.disabled = true;        
  defaultOpt.hidden   = true;         
  defaultOpt.selected = true; 
  selectEl.appendChild(defaultOpt);

  // Loop through your mats JSON and append one <option> per item
  mats.forEach(m => {
    const opt = document.createElement('option');
    opt.value = m.name;        // or m.mat_name, m.color_type, whichever you need
    opt.textContent = m.name;  // visible text
    selectEl.appendChild(opt);
  });
}

function renumberRows() {
  const tbody  = document.getElementById('wtablerow');
  const rows   = Array.from(tbody.querySelectorAll('tr'));
  const fields = ['wwtype','mattype','colortype','usePercent','matMemo'];

  rows.forEach((tr, idx) => {
    const newIdx = idx + 1;

    // 1) TR id and the row-number cell
    tr.id = 'row' + newIdx;
    tr.querySelector('th[scope="row"]').textContent = newIdx;

    // 2) re-index each select/input
    fields.forEach(field => {
      const el = tr.querySelector(`[id^="${field}"]`);
      if (!el) return;
      const val = el.value;
      el.id   = field + newIdx;
      el.name = field + newIdx;
      el.value = val;
    });

    // 3) re-wire COPY button
    const btnCopy = tr.querySelector(`button[id^="cpbtn"]`);
    if (btnCopy) {
      btnCopy.id      = 'cpbtn' + newIdx;
      btnCopy.onclick = () => copy(btnCopy.id);
    }

    // 4) re-wire DELETE button
    const btnDel = tr.querySelector(`button.text-danger`);
    if (btnDel) {
      btnDel.id      = '' + newIdx;
      btnDel.onclick = () => {
        tr.remove();
        renumberRows();
        if (validateNoAlert()) {
	      calculatePrice();
	    }
      };
    }
  });

  // 5) keep your counter in sync
  counter = rows.length;
}

function initCalculators() {
  const tableBody = document.getElementById('wtablerow');

  // When any of the global fields change…
  ['ppmodel','fabrictype','ppcm','totalWarp'].forEach(id => {
    const el = document.getElementById(id);
    if (!el) return;
    if (el) {
	  el.addEventListener('change', e => {
	    if (validateNoAlert()) {
	      calculatePrice();
	    }
	  });
	 }
  });

  // When any per-row input or select changes…
  tableBody.addEventListener('change', e => {
	if (e.target.matches('input[id^="usePercent"], select[id^="wwtype"]')) {
      // 1) validate per‐type sums
      if (!checkUsePercentLimit(e.target)) {
        // reset this one back to default (you choose: "" or "0")
        e.target.value = '';
        if (validateNoAlert()) {
	      calculatePrice();
	    }
        return;
      }
    }
	
    if (
      e.target.matches('select[id^="wwtype"], select[id^="mattype"], select[id^="colortype"], input[id^="usePercent"]')
    ) {
      if (validateNoAlert()) {
	      calculatePrice();
	  }
    }
  });
}

function calculatePrice() {
	let sumMtrCost = 0;
	let weftCost = 0;
	let warpCost = 0;
	let coefficient = 0;
	const profitMargin = 0.1;
	
	const finishing = String(document.getElementById('ppmodel').value);
	let finPrice = 0;
	let pdr = 0;
	let pdrate = 0;
	
	const finishes = finishing
	  .split(',')
	  .map(s => s.trim())
	  .filter(s => s !== '');
	  
	finishes.forEach(f => {
	    if (f === "KC") {
			finPrice += 1.75;
			pdr = 0.05;
		} else if (f === "NP") {
			finPrice += 2.2;
			pdr = 0.09;
		} else if (f === "FB") {
			finPrice += 2.2;
			pdr = 0.08;
		} else if (f === "TC") {
			finPrice += 3;
			pdr = 0.09;
		} else if (f === "SPB") {
			finPrice += 0.4;
			pdr = 0.12;
		}
		if (pdr > pdrate) {
			pdrate = pdr;
		}
	});
	  
	const ppcm = parseFloat(document.getElementById('ppcm').value) || 0;
	const totalWarp = parseFloat(document.getElementById('totalWarp').value) || 0;
	
	if (ppcm > 10) {
		coefficient = 0.2;
	} else {
		coefficient = 0.25;
	}
	
	let sumWeight = 0;
	let weftWeight = 0;
	let warpWeight = 0;
	const rows = document.querySelectorAll('#wtablerow tr');
	rows.forEach((tr, idx) => {
	    const rowIdx = idx + 1;
	    const wwTypeEl  = document.getElementById(`wwtype${rowIdx}`);
    	const matTypeEl = document.getElementById(`mattype${rowIdx}`);
    	const colTypeEl = document.getElementById(`colortype${rowIdx}`);
    	const useEl     = document.getElementById(`usePercent${rowIdx}`);
    	if (
	      !wwTypeEl   || !wwTypeEl.value   ||
	      !matTypeEl  || !matTypeEl.value  ||
	      !colTypeEl  || !colTypeEl.value  ||
	      !useEl      || !useEl.value
	    ) {
	      return;  // skip to next row
	    }
	    
	    
	    const wwtype = String(wwTypeEl.value);
	    const mattype = String(matTypeEl.value);
	    const m = mats.find(m => m.name === mattype);
	    if (!m) {
		    alert(`No mat found for type "${mattype}"`);
		    return;
	    }
	    const colortype = String(colTypeEl.value);
	    const usePct = parseFloat(useEl.value) || 0;
	    if (isNaN(usePct)) return;
	    const weight = calculateWeight(m.mtrtype, wwtype, totalWarp, m.countgu, m.countzhi, usePct, ppcm, m.wloss, 155);
	    const matPrice = checkPrice(colortype, m.prPrice, m.colorPrice, m.drPrice, m.whitePrice, m.dyePrice);
	    if (wwtype === "Warp") {
			warpWeight += weight;
			warpCost += weight * matPrice * 10 / 1000 * 1.1 * 1.1;
		} else if (wwtype === "Weft") {
			weftWeight += weight;
			weftCost += weight * matPrice * 10 / 1000 * 1.1;
		}
	 });
	 
  	sumWeight = weftWeight + warpWeight;
  	let sumWeightPerMeterInGram = sumWeight * 10;
  	document.getElementById('weight').textContent = sumWeightPerMeterInGram.toFixed(2);
  	document.getElementById('weightInput').value = sumWeightPerMeterInGram.toFixed(2);
  	
  	sumMtrCost = weftCost + warpCost;
  	if (sumMtrCost == 0) {
		document.getElementById('calculatedPrice').innerHTML = `<i class="fa fa-cny"></i> 0.0`;
		return;
	}
  	const processCost = ppcm * coefficient;
  	const finCost = (sumMtrCost + processCost) * pdrate + finPrice;
  	const costPrice = processCost + sumMtrCost + finCost + 1.4;
  	const managementCost = costPrice/(0.87/1.1)*0.14;
  	const totalProductCost = costPrice + managementCost;
  	const finalSalePrice = totalProductCost/(1-profitMargin)*profitMargin + totalProductCost;
  	document.getElementById('calculatedPrice').innerHTML = `<i class="fa fa-cny"></i> ${finalSalePrice.toFixed(2)}`;
  	document.getElementById('calculatedPriceInput').value = finalSalePrice.toFixed(2);
}

function calculateWeight(mt, wt, tw, cg, cz, up, ppcm, wl, w) {
	var temp = 0;
	
	if (wt === "Weft") {
		if (mt === "D") {
			temp = w * ((cz/cg)*((ppcm*up)/1000)/9000) * wl;
		} else if (mt === "N") {
			temp = w * (9000/(cz/cg)*(ppcm*up)/1000)/9000 * wl;
		} else if (mt === "S") {
			temp = w * (5315/(cz/cg)) * ((ppcm*up)/1000)/9000 * wl;
		} else {
			alert("Selected yarn lacks D/N/S label!");
		}
	} else if (wt === "Warp") {
		if (mt === "D") {
			temp = cz/cg * tw * up/1000/9000 * wl;
		} else if (mt === "N") {
			temp = (9000/(cz/cg)) * tw * up/1000/9000 * wl;
		} else if (mt === "S") {
			temp = (5315/(cz/cg)) * tw * up/1000/9000 * wl;
		} else {
			alert("Selected yarn lacks D/N/S label!");
		}
	} else {
		alert("Warp/weft not selected, cannot calculate weight!");
	}
	
	return temp;
}
	
function checkPrice(ct, prp, cp, drp, wp, dp) {
	if (ct === "White Yarn") {
		return wp;
	} else if (ct === "Yarn Dyed") {
		return dp;
	} else if (ct === "Fiber Dyed Yarn") {
		return cp;
	} else if (ct === "Space Dyed Yarn") {
		return drp;
	} else if (ct === "Jet Dyed Yarn") {
		return prp;
	} else {
		alert("Dye method not selected, yarn cost cannot be calculated!")
		return 0.0;
	}
};

function checkUsePercentLimit(changedInput) {
  // find its row index
  var idx    = changedInput.id.replace(/\D/g, '');
  var typeEl = document.getElementById('wwtype' + idx);
  if (!typeEl) return true;

  var type   = typeEl.value;   // "Warp" or "Weft" or ""
  if (type !== 'Warp' && type !== 'Weft') return true;

  // sum all usePercent for this type
  var sum = 0;
  Array.prototype.forEach.call(
    document.querySelectorAll('input[id^="usePercent"]'),
    function(inp) {
      var rowIdx = inp.id.replace('usePercent','');
      var wwt    = document.getElementById('wwtype' + rowIdx);
      wwt = wwt ? wwt.value : '';
      if (wwt === type) {
        sum += parseFloat(inp.value) || 0;
      }
    }
  );

  if (sum > 100) {
    alert('Total usePercent for "'+type+'" cannot exceed 100 (current: '+sum+'%)');
    return false;
  }
  return true;
}

function populateAllInputs() {
	document.getElementById("calculatedPrice").innerHTML = `<i class="fa fa-cny"></i> ${quote.floatSalePrice}`;
	document.getElementById('calculatedPriceInput').value = quote.floatSalePrice;
	document.getElementById("pmodel").value = quote.modelName;
	document.getElementById("fabrictype").value = quote.fabricType;
	var finStr = quote.finishModule.split(",");
	document.querySelector('#ppmodel').setValue(finStr, false);
	document.getElementById("ppcm").value = quote.floatPicksPer;
	document.getElementById("totalWarp").value = quote.floatTotalWarp;
	document.getElementById("date").value = quote.date;
	document.getElementById("memo").value = quote.memo;
	next();
	document.getElementById("savebtn").addEventListener('click', () => {
      setFormAction(
        'CalcService?action=edit&quoteId=' + quote.id
      );
    });
	let i = 1;
	quoteMats.forEach((mat => {
		if (i != 1) {
			addWW(false);
		}
		document.getElementById("wwtype" + i).value = mat.matType;
		document.getElementById("mattype" + i).value = mat.matName;
		initMattypeChoices();
		document.getElementById("colortype" + i).value = mat.matColor;
		document.getElementById("usePercent" + i).value = mat.usePercent;
		document.getElementById("matMemo" + i).value = mat.matMemo;
		i += 1;
	}));
	document.getElementById("weight").innerHTML = quote.floatWeight;
	document.getElementById('weightInput').value = quote.floatWeight;
}

// Function to change the form action based on the clicked button
function setFormAction(action) {
    var form = document.getElementById('PDform');  // Get the form element
    form.action = action;  // Set the action to the specified URL
}
  