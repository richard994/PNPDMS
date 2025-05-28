function renderMaterials(list) {
	const container = document.getElementById('mattable');
	container.innerHTML = '';
	for (let i = 0; i < list.length; i += 3) {
	    const rowDiv = document.createElement('div');
	    rowDiv.className = 'row';

	    for (let j = 0; j < 3; j++) {
	      const mat = list[i + j];
	      const colDiv = document.createElement('div');
	      colDiv.className = 'col-sm';
	
	      colDiv.innerHTML = `
	        <table class="table table-bordered mt-3">
	          <thead class="table-light">
	            <tr>
	              <th scope="col" style="width:40px">#</th>
	              <th scope="col" style="font-weight:normal">Name</th>
	            </tr>
	          </thead>
	          <tbody class="border-top-0">
	            <tr style="height:65px">
	              <th scope="row" style="width:40px">${i + j + 1}</th>
	              <td>${ mat ? mat.name : '' }</td>
	            </tr>
	          </tbody>
	        </table>
	      `;
	      rowDiv.appendChild(colDiv);
	    }
	    container.appendChild(rowDiv);
    }
}

function catFilter(key) {
	const filtered = key === '' ? mats : mats.filter(m => m.key === key);
    renderMaterials(filtered);
}

function search() {
	const matname = document.getElementById('matname').value;
	const filtered = matname === '' ? mats : mats.filter(m => m.name.includes(matname));
	renderMaterials(filtered);
}

document.addEventListener('DOMContentLoaded', () => {
  const badge = document.getElementById('numMats');
  badge.textContent = mats.length;
  renderMaterials(mats);
});