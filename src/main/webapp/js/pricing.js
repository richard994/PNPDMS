function renderQuotes(list) {
  if ($.fn.DataTable.isDataTable('#quotedprices')) {
    $('#quotedprices').DataTable().destroy();
  }
	
  const tbody = document.getElementById('qpbody');
  tbody.innerHTML = '';
  list.forEach((quote, idx) => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <th scope="row">${idx+1}</th>
      <td>${quote.modelName}</td>
      <td>155</td>
      <td>${quote.picksPer}</td>
      <td>${quote.totalWarp}</td>
      <td>${quote.weight}</td>
      <td>${quote.finishModule}</td>
      <td>${quote.salePrice}</td>
      <td style="white-space: nowrap">${quote.date}</td>
      <td style="white-space: nowrap; display: flex">
        <button class="btn btn-link btn-sm" type="button"
                onclick="redirect(
                  'CreateService?action=edit&quoteId=${quote.id}'
                )">EDIT</button>
        <button class="btn btn-link btn-sm" type="button"
                onclick="redirect(
                  'CalcService?action=copy&quoteId=${quote.id}'
                )">COPY</button>
        <button class="btn btn-link btn-sm text-danger" type="button"
                onclick="showModal(${quote.id})">DELETE</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
  
    $('#quotedprices').dataTable({
    	"sDom": 'rt<"bottom-wrapper"<"info"i><"length"l><"page"p>>',
    	"language": {
    		"paginate": {
    			"first": "<<",
    			"last": ">>",
    			"next": ">",
    			"previous": "<"
    		},
    		"sLengthMenu": "Show&nbsp&nbsp_MENU_&nbsp&nbspEntries"
    	},
    	"lengthMenu": [5, 10, 25, 50],
    	columns: [
    		{ width: '50px' },
    		{ width: '120px', orderable: false },
    		{ width: '60px', orderable: false },
    		{ width: '60px' },
    		{ width: '110px' },
    		{ width: '110px' },
    		{ width: '150px', orderable: false },
    		{ width: '110px' },
    		{ width: '110px' },
    		{ width: '200px', orderable: false }
    	]
   	});	
    
    $('.bottom-wrapper').css("width", "87%");
    $('.info').css("width", "40%");
    $('.length').css("width", "20%");
    $('.page').css("width", "40%");
    $('#quotedprices_length').children().css("display", "flex");
    $('#quotedprices_length').children().css("align-items", "center");
}

function filterAndRender() {
  // grab the raw filter values
  const pmodel = document
    .getElementById("pmodel")
    .value
    .trim()
    .toLowerCase();

  // handle multiple‐select properly
  const finishingFilter = document.getElementById("finishingmodule").value;

  const dateFilter = document.getElementById("pdate").value;

  // 2) iterate over each row
  document.querySelectorAll("#qpbody tr").forEach(tr => {
    // note: zero-based children, so
    // td:nth-child(2) is children[1], etc.
    const rowpm = tr.children[1].textContent.trim().toLowerCase();
    const rowfn = tr.children[6].textContent.trim();
    const rowdt = tr.children[8].textContent.trim();

    // 3) check each filter (empty filter ⇒ pass)
    let visible = true;
    if (pmodel && !rowpm.includes(pmodel))     visible = false;
    if (dateFilter && rowdt !== dateFilter)     visible = false;
    if (finishingFilter.length && !finishingFilter.includes(rowfn)) {
      visible = false;
    }

    // 4) show or hide
    tr.style.display = visible ? "" : "none";
  });
}
  
function refresh() {
	window.location.reload();
}

function redirect(url) {
    window.location.replace(url);
}

function showModal(quoteId) {
    $('#dlModal').modal("show");
    $('#confirmDelete').on('click', function(){
        redirect('CalcService?action=delete&quoteId=' + quoteId);
    });
}

document.addEventListener('DOMContentLoaded', () => {
  // on page load, show all quotes
  renderQuotes(quotes);
});