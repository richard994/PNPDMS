// 全局计数器
let counter = document.getElementById('wtablerow').childElementCount - 3;

// ============================================================
// 【新增 1】通用验证函数：判断输入的值是否存在于绑定的 datalist 中
// ============================================================
function isValidDatalistValue(inputEl, val) {
	if (!inputEl) return false;
	const listName = inputEl.getAttribute('list');
	if (!listName) return true; // 如果没有绑定 datalist，默认保留
	const list = document.getElementById(listName);
	if (!list) return true; // 如果 datalist 没找到，默认保留
	const trimmedVal = String(val).trim();
	if (trimmedVal === '') return true; // 空值始终有效（允许清空）

	const options = list.querySelectorAll('option');
	for (let opt of options) {
		if (String(opt.value).trim() === trimmedVal) {
			return true; // 在字典中找到完全匹配的值
		}
	}
	return false; // 没找到匹配项
}

// ============================================================
// 【核心联动】构建 specMap 和 colorMap
// ============================================================
const specMap = {};
if (typeof mtrspec !== 'undefined' && mtrspec && mtrspec.length > 0) {
	mtrspec.forEach(item => {
		specMap[(item.name || '').trim()] = (item.mtrid || '').trim();
	});
}
const colorMap = {};
if (typeof mtrcolors !== 'undefined' && mtrcolors && mtrcolors.length > 0) {
	mtrcolors.forEach(item => {
		const key = (item.materialtypestruc || '').trim();
		const val = (item.coloridx || '').trim();
		if (key && val) {
			if (!colorMap[key]) colorMap[key] = [];
			colorMap[key].push(val);
		}
	});
}

// ============================================================
// 【核心联动】刷新某列下拉选项
// ============================================================
function updateColumnOptions(colIndex) {
	// 1. 取当前列 Yarn Type 的值
	const yarnVal = (document.getElementById('yarn_sel' + colIndex) || { value: '' }).value.trim();
	// 2. 找到对应的 mtrid
	const mtrid = specMap[yarnVal] || '';
	// 3. 找到对应的颜色列表
	const validOptions = mtrid ? (colorMap[mtrid] || []) : [];

	// 4. 更新当前列的 datalist
	const listEl = document.getElementById('color_datalist_' + colIndex);
	if (listEl) {
		listEl.innerHTML = '';
		validOptions.forEach(colorVal => {
			const opt = document.createElement('option');
			opt.value = colorVal;
			listEl.appendChild(opt);
		});
	}

	// 5. 检查并清空该列下所有行的无效值
	const allInputs = document.querySelectorAll(`input[id^="sel${colIndex}_"]`);
	allInputs.forEach(inp => {
		const curVal = inp.value.trim();
		if (curVal !== '' && !validOptions.includes(curVal)) {
			inp.value = '';
			inp.title = '';
		}
	});
}

// ============================================================
// Toast 提示封装函数（2秒自动消失）
// ============================================================
function showToast(message) {
	var toastEl = document.getElementById('liveToast');
	if (!toastEl) return;
	var toastBody = toastEl.querySelector('.toast-body');
	toastBody.textContent = message;
	var toast = new bootstrap.Toast(toastEl, {
		autohide: true,
		delay: 2000 // 2秒后自动消失
	});
	toast.show();
}

document.addEventListener('DOMContentLoaded', function() {
	// ============================================================
	// 【新增】聚焦事件：如果 Yarn Type 为空，弹出 2 秒提示并阻止操作
	// ============================================================
	$(document).on('focus', '#wtablerow input[list]', function(e) {
		var input = this;
		var id = input.id;
		// 排除 Yarn Type 行自身的聚焦
		var row = input.closest('tr');
		if (!row || row.id === 'row_yarn') return;

		// 通过 ID 提取列号，例如 sel1_1 提取为 1
		var match = id.match(/sel(\d+)_/);
		if (match && match.length > 1) {
			var colNum = parseInt(match[1]);
			var yarnInput = document.getElementById('yarn_sel' + colNum);
			if (yarnInput) {
				var yarnVal = yarnInput.value.trim();
				if (yarnVal === '') {
					// 【修改点】用 Toast 弹窗代替生硬的 alert
					showToast("Please select the Yarn Type value in the current column first.");
					input.blur(); // 强制失去焦点，阻止后续操作
					return;
				}
			}
		}
	});

	// ============================================================
	// 【新增 3】blur 事件：输入值无效时自动清空
	// ============================================================
	$(document).on('blur', '#wtablerow input[list]', function(e) {
		var input = this;
		var val = input.value;
		// 如果值无效，且不是空值，则强制清空
		if (!isValidDatalistValue(input, val) && val.trim() !== '') {
			input.value = '';
			input.title = '';
		}
	});

	// ============================================================
	// 【新增 4】联动监听：监听 Yarn Type 行变化
	// ============================================================
	$(document).on('input propertychange', '#row_yarn input[type="text"]', function(e) {
		const match = this.id.match(/\d+$/);
		if (match) {
			const colIndex = parseInt(match[0]);
			updateColumnOptions(colIndex);
		}
	});

	initStaticRows();
	initDynamicRows();

	// ============================================================
	// 【新增 5】初始化联动：初次加载时刷新所有列的 options
	// ============================================================
	setTimeout(() => {
		for (let i = 1; i <= 12; i++) {
			updateColumnOptions(i);
		}
	}, 200);
});

function initStaticRows() {
	const yarnSelects = document.querySelectorAll('#row_yarn input[type="text"]');
	yarnSelects.forEach(input => { input.setAttribute('list', 'globalSpecList'); });
}

function initDynamicRows() {
	const rows = document.querySelectorAll('#wtablerow tr[id^="row"]');
	rows.forEach(tr => {
		const id = tr.id;
		if (id.startsWith('row') && !isNaN(id.replace('row',''))) {
			const inputs = tr.querySelectorAll('input[type="text"]');
			for (let j = 2; j < inputs.length; j++) {
				if (inputs[j]) {
					// 由联动函数统一处理 list
				}
			}
		}
	});
	renumberRows();
}

function next() {
	document.getElementById("progress2").style.display = "none";
	document.getElementById("nextbtn").style.display = "none";
	document.getElementById("progressed1").style.display = "block";
	document.getElementById("wwbtncontainer").style.display = "block";
	document.getElementById("WW").style.display = "block";
	document.getElementById("savebtn").style.display = "block";
	document.getElementById("wtable").style.display = "table";
}

function validatePhaseOne() {
	if (document.getElementById("pmodel").value.trim() == "") { alert("Please enter Pattern Name!"); return; }
	if (document.getElementById("flowers").value.trim() == "") { alert("Please enter flowers!"); return; }
	if (document.getElementById("breadth").value.trim() == "") { alert("Please enter breadth!"); return; }
	if (document.getElementById("picksInch").value.trim() == "") { alert("Please enter Picks/inch!"); return; }
	if (document.getElementById("ppcm").value.trim() == "") { alert("Please enter Picks/cm!"); return; }
	next();
}

function addWW(needInitChoice, targetParent) {
	counter++;
	const wtbody = document.getElementById('wtablerow');
	const tpl = document.getElementById('ww-row-template');
	const clone = tpl.content.cloneNode(true);
	const tr = clone.querySelector('tr');
	tr.id = 'row' + counter;

	const inputs = tr.querySelectorAll('input[type="text"]');
	inputs[0].id = `index${counter}`;
	inputs[0].name = `index${counter}`;
	inputs[0].value = counter;
	inputs[1].id = `colorCode${counter}`;
	inputs[1].name = `colorCode${counter}`;
	inputs[1].value = '';

	for (let j = 0; j < 12; j++) {
		const input = inputs[j + 2];
		if (input) {
			input.id = `sel${j+1}_${counter}`;
			input.name = `sel${j+1}_${counter}`;
			input.value = '';
			// 【核心修改】新增行时，绑定列级 datalist
			input.setAttribute('list', 'color_datalist_' + (j+1));
		}
	}

	const [btnCopy, btnDel] = tr.querySelectorAll('button');
	btnCopy.id = `cpbtn${counter}`;
	btnCopy.onclick = () => copy(btnCopy.id);
	btnDel.id = `${counter}`;
	btnDel.onclick = () => { tr.remove(); renumberRows(); };

	if (!targetParent) { targetParent = wtbody; }
	targetParent.appendChild(tr);
	return tr;
}

function copy(btnId) {
	const srcIdx = parseInt(btnId.replace('cpbtn',''), 10);
	const srcRow = document.getElementById('row' + srcIdx);
	if (!srcRow) return;

	const clone = srcRow.cloneNode(true);
	counter += 1;
	const newIdx = counter;
	clone.id = 'row' + newIdx;

	const srcInputs = srcRow.querySelectorAll('input[type="text"]');
	const newInputs = clone.querySelectorAll('input[type="text"]');

	for (let i = 0; i < srcInputs.length; i++) {
		const val = srcInputs[i].value;
		if (i === 0) {
			newInputs[i].id = `index${newIdx}`;
			newInputs[i].name = `index${newIdx}`;
			newInputs[i].value = newIdx;
		} else if (i === 1) {
			newInputs[i].id = `colorCode${newIdx}`;
			newInputs[i].name = `colorCode${newIdx}`;
			newInputs[i].value = val;
		} else {
			newInputs[i].id = srcInputs[i].id.replace(/\d+$/, newIdx);
			newInputs[i].name = srcInputs[i].name.replace(/\d+$/, newIdx);
			newInputs[i].value = val;
			// 【核心修改】复制行时，提取原列号，绑定列级 datalist
			const colMatch = srcInputs[i].id.match(/sel(\d+)_/);
			if (colMatch) {
				const colNum = colMatch[1];
				newInputs[i].setAttribute('list', 'color_datalist_' + colNum);
			}
		}
	}

	const [btnCopy, btnDel] = clone.querySelectorAll('button');
	btnCopy.id = `cpbtn${newIdx}`;
	btnCopy.onclick = () => copy(btnCopy.id);
	btnDel.id = `${newIdx}`;
	btnDel.onclick = () => { clone.remove(); renumberRows(); };
	document.getElementById('wtablerow').appendChild(clone);
}

function renumberRows() {
	const tbody = document.getElementById('wtablerow');
	const rows = Array.from(tbody.querySelectorAll('tr'));
	const dynamicRows = rows.filter(tr => tr.id.startsWith('row') && !isNaN(tr.id.replace('row','')));
	dynamicRows.forEach((tr, idx) => {
		const newIdx = idx + 1;
		tr.id = 'row' + newIdx;
		const inputs = tr.querySelectorAll('input[type="text"]');
		if (!inputs || inputs.length < 2) return;

		inputs[0].id = `index${newIdx}`;
		inputs[0].name = `index${newIdx}`;
		inputs[0].value = newIdx;
		inputs[1].id = `colorCode${newIdx}`;
		inputs[1].name = `colorCode${newIdx}`;

		for (let col = 1; col <= 12; col++) {
			const input = inputs[col + 1];
			if (input) {
				input.id = `sel${col}_${newIdx}`;
				input.name = `sel${col}_${newIdx}`;
			}
		}

		const btnCopy = tr.querySelector(`button[id^="cpbtn"]`);
		if (btnCopy) {
			btnCopy.id = `cpbtn${newIdx}`;
			btnCopy.onclick = () => copy(btnCopy.id);
		}
		const btnDel = tr.querySelector(`button.text-danger`);
		if (btnDel) {
			btnDel.id = `${newIdx}`;
			btnDel.onclick = (function(currentTr) {
				return function() { currentTr.remove(); renumberRows(); };
			})(tr);
		}
	});
	counter = dynamicRows.length;
}

// ============================================================
// 保存功能（保持不变）
// ============================================================
function bindSaveEvent() {
	const saveBtn = document.getElementById("savebtn");
	if (!saveBtn) { setTimeout(bindSaveEvent, 200); return; }

	const newSaveBtn = saveBtn.cloneNode(true);
	saveBtn.parentNode.replaceChild(newSaveBtn, saveBtn);

	newSaveBtn.addEventListener('click', function() {
		let isContinuous = true;
		for (let j = 1; j <= 12; j++) {
			const inputEl = document.getElementById(`yarn_sel${j}`);
			if (inputEl) {
				const val = inputEl.value;
				if (isContinuous) {
					if (val === '' || val === null) isContinuous = false;
				} else {
					if (val !== '' && val !== null) {
						alert(`In Yarn Type row, sel ${j} is filled, so columns before sel ${j} must also be filled.`);
						return;
					}
				}
			}
		}
		// ============================================================
		// 【新增】此处替换为纵向校验代码
		// ============================================================
		for (let col = 1; col <= 12; col++) {
			const yarnInput = document.getElementById('yarn_sel' + col);
			if (!yarnInput) continue;
			const yarnVal = yarnInput.value.trim();
			if (yarnVal !== '') {
				const colInputs = document.querySelectorAll(`#wtablerow tr[id^="row"] input[id^="sel${col}_"]`);
				for (let i = 0; i < colInputs.length; i++) {
					const colInput = colInputs[i];
					if (colInput.value.trim() === '') {
						const rowIdx = colInput.id.replace(`sel${col}_`, '');
						alert(`In column sel ${col}, all rows must have values because Yarn Type is selected. Please check row ${rowIdx}.`);
						return;
					}
				}
			}
		}
		const head = {
			patternid: document.getElementById('pmodel').value,
			flower: document.getElementById('flowers').value,
			designer: document.getElementById('designer').value,
			dsgdate: document.getElementById('date').value,
			disc: document.getElementById('discNo').value,
			const: document.getElementById('construction').value,
			picks_inch: document.getElementById('picksInch').value || '0',
			picks_cm: document.getElementById('ppcm').value || '0',
			warp: document.getElementById('warpNo').value,
			breadth: document.getElementById('breadth').value,
			yarn: document.getElementById('yarnType').value,
			totalends: document.getElementById('totalEnds').value || '0',
			target_price: document.getElementById('targetPrice').value || '0',
			shipto: document.getElementById('shipTo').value,
			yarn_config: JSON.stringify({
				sel1: document.getElementById('yarn_sel1')?.value || '',
				sel2: document.getElementById('yarn_sel2')?.value || '',
				sel3: document.getElementById('yarn_sel3')?.value || '',
				sel4: document.getElementById('yarn_sel4')?.value || '',
				sel5: document.getElementById('yarn_sel5')?.value || '',
				sel6: document.getElementById('yarn_sel6')?.value || '',
				sel7: document.getElementById('yarn_sel7')?.value || '',
				sel8: document.getElementById('yarn_sel8')?.value || '',
				sel9: document.getElementById('yarn_sel9')?.value || '',
				sel10: document.getElementById('yarn_sel10')?.value || '',
				sel11: document.getElementById('yarn_sel11')?.value || '',
				sel12: document.getElementById('yarn_sel12')?.value || ''
			})
		};

		const detail = [];
		const rows = document.querySelectorAll('#wtablerow tr[id^="row"]');
		rows.forEach(tr => {
			const id = tr.id;
			if (id.startsWith('row') && !isNaN(id.replace('row',''))) {
				const idxVal = tr.querySelector('input[id^="index"]')?.value || '';
				const colorcodeVal = tr.querySelector('input[id^="colorCode"]')?.value || '';
				for (let j = 1; j <= 12; j++) {
					const inputEl = tr.querySelector(`input[id^="sel${j}_"]`);
					if (inputEl) {
						const colornameVal = inputEl.value;
						if (colornameVal && colornameVal !== '') {
							detail.push({
								selectors: String(j),
								idx: idxVal,
								yarntype: document.getElementById('yarn_sel' + j)?.value || '',
								colorcode: colorcodeVal,
								shorts: "",
								colorname: colornameVal,
								color: ""
							});
						}
					}
				}
			}
		});

		const params = new URLSearchParams();
		params.append('head', JSON.stringify(head));
		params.append('detail', JSON.stringify(detail));

		fetch('BundlePatternService?action=savenew', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: params
		})
			.then(response => response.json())
			.then(data => {
				if (data.success === 'true') { alert('Save successful!'); window.location.href = 'BundlePatternService?action=view'; }
				else { alert('Save failed: ' + (data.msg || 'unknown error')); }
			})
			.catch(err => { console.error('保存错误:', err); alert('Save request failed, please check network or console error info'); });
	});
}

function populateAllInputs() {
	bindSaveEvent();
}