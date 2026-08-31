// 渲染表格
// 渲染表格
function renderQuotes(list) {
    if ($.fn.DataTable.isDataTable('#quotedprices')) {
        $('#quotedprices').DataTable().destroy();
    }

    const tbody = document.getElementById('qpbody');
    tbody.innerHTML = '';
    list.forEach((quote, idx) => {
        // 根据后端返回的 Y/N 转换为前端显示文本
        const statusText = quote.confirmflag === 'Y' ? 'Confirmed' : (quote.confirmflag === 'N' ? 'Unconfirmed' : '');
        const quoteFlagText = quote.quoteflag === 'Y' ? 'Quoted' : (quote.quoteflag === 'N' ? 'Unquoted' : '');

        const tr = document.createElement('tr');
        tr.innerHTML = `
      <td style="text-align: center; vertical-align: middle;">
        <input type="radio" name="selected_quote" value="${quote.id}">
      </td>
      <th scope="row">${idx+1}</th>
      <td>${quote.patternid || ''}</td>
      <td>${quote.flower || ''}</td>
      <td>${quote.DESIGNER || ''}</td>
      <td>${quote.DSGDATE || ''}</td>
      <td>${quote.breadth || ''}</td>
      <td>${quote.warp || ''}</td>
      <td>${quote.totalends || ''}</td>
      <td>${quote.target_price || ''}</td>
      <td>${statusText}</td>
      <td>${quoteFlagText}</td>
    `;
        tbody.appendChild(tr);
    });

    // 配置 DataTable，启用分页，样式与 pricing.jsp 保持一致
    $('#quotedprices').DataTable({
        paging: true,
        info: true,
        lengthChange: true,
        searching: false,
        pageLength: 10,
        lengthMenu: [5, 10, 25, 50],
        // 【关键修复】取消 scrollY，使用标准的 table-layout 和分页
        dom: 'rt<"bottom-wrapper"<"info"i><"length"l><"page"p>>',
        language: {
            paginate: {
                first: "<<",
                last: ">>",
                next: ">",
                previous: "<"
            },
            sLengthMenu: "Show&nbsp&nbsp_MENU_&nbsp&nbspEntries"
        },
        // 【平衡列宽】手动计算总和 100%（150px 固定宽度，其余百分比如下）
        columnDefs: [
            { targets: 0, width: '40px', orderable: false },
            { targets: 1, width: '50px', orderable: false },
            { targets: 2, width: '11%', orderable: false },   // Pattern Name
            { targets: 3, width: '10%', orderable: false },   // Flowers
            { targets: 4, width: '10%', orderable: false },   // Designer
            { targets: 5, width: '10%', orderable: false },   // Date
            { targets: 6, width: '5%',  orderable: false },   // Width
            { targets: 7, width: '25%', orderable: false },   // Total Warp (容纳长文本)
            { targets: 8, width: '8%',  orderable: false },   // Total Ends
            { targets: 9, width: '5%',  orderable: false },   // Price
            { targets: 10, width: '9%', orderable: false },   // Status
            { targets: 11, width: '7%', orderable: false }    // Quote Flag
        ],
    });

    // 调整分页区域样式，保持与 pricing 一致
    $('.bottom-wrapper').css({
        "width": "100%",
        "display": "flex",
        "background-color": "white",
        "padding": "5px 0"
    });
    $('#quotedprices_length').children().css({
        "display": "flex",
        "align-items": "center"
    });
    $('#quotedprices_length').find('select').css("width", "50px");
}

// 查询过滤
async function filterAndRender() {
    const params = new URLSearchParams();
    params.append('action', 'query');
    params.append('patternid', document.getElementById('f_patternid').value);
    params.append('flowers', document.getElementById('f_flowers').value);
    params.append('designer', document.getElementById('f_designer').value);
    params.append('bdate', document.getElementById('f_bdate').value);
    params.append('edate', document.getElementById('f_edate').value);
    params.append('confirmflag', document.getElementById('f_confirmflag').value);
    params.append('quoteflag', document.getElementById('f_quoteflag').value);

    try {
        const response = await fetch('BundlePatternService?' + params.toString());
        const data = await response.json();
        renderQuotes(data);
    } catch (error) {
        // console.error('查询失败:', error);
        console.error('Query failed:', error);
        // alert('查询失败，请检查网络及后端接口！');
        alert('Query failed, please check network and backend interface!');
    }
}

// 重置功能
function refresh() {
    document.getElementById('f_patternid').value = '';
    document.getElementById('f_flowers').value = '';
    document.getElementById('f_designer').value = '';
    document.getElementById('f_bdate').value = '';
    document.getElementById('f_edate').value = '';
    document.getElementById('f_confirmflag').value = '';
    document.getElementById('f_quoteflag').value = '';
    filterAndRender();
}

function redirect(url) {
    window.location.replace(url);
}

// 获取选中的行ID和对应的状态文本
function getSelectedRowStatus() {
    const selected = document.querySelector('input[name="selected_quote"]:checked');
    if (!selected) return null;
    const tr = selected.closest('tr');
    // 【核心修复】直接从 <tr> 标签上读取 data-status，绝不会错位！
    const statusValue = tr.dataset.status || '';
    return { id: selected.value, status: statusValue };
}

// ----------------------------------------------------------------
// 【核心修复】防止初始化事件被重复绑定
// ----------------------------------------------------------------
if (typeof window._bundleEventsInitialized === 'undefined') {
    window._bundleEventsInitialized = true;

    document.addEventListener('DOMContentLoaded', () => {
        filterAndRender();

        document.getElementById('btnEdit').addEventListener('click', function() {
            const selected = getSelectedRowStatus();
            // if (!selected) { alert("请先勾选表格左侧的单选按钮！"); return; }
            if (!selected) { alert("Please select the radio button on the left of the table first!"); return; }
            redirect('BundlePatternService?action=edit&packageid=' + selected.id);
        });

        document.getElementById('btnDelete').addEventListener('click', function() {
            const selected = getSelectedRowStatus();
            // if (!selected) { alert("请先勾选表格左侧的单选按钮！"); return; }
            if (!selected) { alert("Please select the radio button on the left of the table first!"); return; }
            // if (!confirm("确定要删除该记录吗？")) return;
            if (!confirm("Are you sure to delete this record?")) return;

            this.disabled = true;
            fetch('BundlePatternService?action=delete&packageid=' + selected.id)
                .then(response => response.text())
                .then(text => {
                    alert(text && text.includes("成功") ? text : "删除失败: " + text);
                    if(text && text.includes("成功")) filterAndRender();
                })
                .catch(err => {
                    console.error(err);
                    // alert('删除请求发送失败！');
                    alert('Delete request failed!');
                })
                .finally(() => {
                    setTimeout(() => { this.disabled = false; }, 1000);
                });
        });

        document.getElementById('btnConfirm').addEventListener('click', function() {
            const selected = getSelectedRowStatus();
            // if (!selected) { alert("请先勾选表格左侧的单选按钮！"); return; }
            if (!selected) { alert("Please select the radio button on the left of the table first!"); return; }

            // 【核心修复】直接判断获取到的 data-status 是否为 Y
            // if (selected.status === 'Y') { alert('该记录已确认，不能再次进行确认！'); return; }
            if (selected.status === 'Y') { alert('This record is already confirmed, cannot confirm again!'); return; }
            // if (!confirm("确定要确认该记录吗？")) return;
            if (!confirm("Are you sure to confirm this record?")) return;

            this.disabled = true;
            fetch('BundlePatternService?action=finish&packageid=' + selected.id + '&field=confirmflag&upvalue=Y')
                .then(response => response.json())
                .then(data => {
                    // alert(data.success === true ? "确认成功！" : "确认失败: " + (data.msg || '未知错误'));
                    alert(data.success === true ? "Confirmation successful!" : "Confirmation failed: " + (data.msg || 'unknown error'));
                    if(data.success === true) filterAndRender();
                })
                .catch(err => {
                    console.error(err);
                    // alert('确认请求发送失败！');
                    alert('Confirmation request failed!');
                })
                .finally(() => {
                    setTimeout(() => { this.disabled = false; }, 1000);
                });
        });

        document.getElementById('btnUnConfirm').addEventListener('click', function() {
            const selected = getSelectedRowStatus();
            // if (!selected) { alert("请先勾选表格左侧的单选按钮！"); return; }
            if (!selected) { alert("Please select the radio button on the left of the table first!"); return; }

            // 【核心修复】直接判断获取到的 data-status 是否为 N
            // if (selected.status === 'N') { alert('该记录未确认，不能进行反确认！'); return; }
            if (selected.status === 'N') { alert('This record is not confirmed, cannot unconfirm!'); return; }
            // if (!confirm("确定要反确认该记录吗？")) return;
            if (!confirm("Are you sure to unconfirm this record?")) return;

            this.disabled = true;
            fetch('BundlePatternService?action=finish&packageid=' + selected.id + '&field=confirmflag&upvalue=N')
                .then(response => response.json())
                .then(data => {
                    // alert(data.success === true ? "反确认成功！" : "反确认失败: " + (data.msg || '未知错误'));
                    alert(data.success === true ? "Unconfirmation successful!" : "Unconfirmation failed: " + (data.msg || 'unknown error'));
                    if(data.success === true) filterAndRender();
                })
                .catch(err => {
                    console.error(err);
                    // alert('反确认请求发送失败！');
                    alert('Unconfirmation request failed!');
                })
                .finally(() => {
                    setTimeout(() => { this.disabled = false; }, 1000);
                });
        });

        // 导出Excel部分保持不变...
        document.getElementById('btnExport').addEventListener('click', function() {
            const selected = getSelectedRowStatus();
            // if (!selected) { alert("请先勾选表格左侧的单选按钮以选择一条记录！"); return; }
            if (!selected) { alert("Please select a record by checking the radio button on the left of the table!"); return; }
            const id = selected.id;
            fetch('BundlePatternService?action=queryitem&remote=remote&packageid=' + id)
                .then(response => response.json())
                .then(data => {
                    const head = data.head;
                    const items = data.items;
                    const rowsMap = {};
                    items.forEach(item => {
                        const idx = item.idx;
                        if (!rowsMap[idx]) {
                            rowsMap[idx] = { colorcode: item.colorcode || "", cols: {} };
                        }
                        rowsMap[idx].cols[item.selectors] = item.colorname || "";
                    });

                    const excelData = [
                        ["David Crowder", "", "", "", "", "", "", "", "", "", "", "", "", ""],
                        ["Pattern Name:", "", head.patternid || "", "", "", "", "Warp #:", head.warp || "", "", "", "", "", ""],
                        ["flowers", "", head.flower || "", "", "", "", "breadth", head.breadth || "", "", "", "", "", ""],
                        ["Designer:", "", head.DESIGNER || "", "", "", "", "Yarn Type:", head.yarn || "", "Total Ends:", head.totalends || "", "", ""],
                        ["Date:", "", head.DSGDATE || "", "", "", "", "Target Price:", head.target_price || "", "", "", "", "", ""],
                        ["Disc #:", "", head.DISC || "", "", "", "", "Ship to:", head.shipto || "", "", "", "", "", ""],
                        ["CONSTRUCTION", "", "", "", "", "", "", "Attention: " + (head.DESIGNER || ""), "", "", "", "", "", ""],
                        ["Picks/inch:", "", head.picks_inch || "", "Picks/cm:", head.picks_cm || "", "", "", "", "", "", "", "", ""],
                        ["", "", "", "", "", "", "", "", "", "", "", "", "", ""],
                        ["Selectors", "", "sel 1", "sel 2", "sel 3", "sel 4", "sel 5", "sel 6", "sel 7", "sel 8", "sel 9", "sel 10", "sel 11", "sel 12"],
                        ["Yarn Type", "", "", "", "", "", "", "", "", "", "", "", "", ""],
                        ["Shots", "", "", "", "", "", "", "", "", "", "", "", "", ""],
                        ["#", "color code", "", "", "", "", "", "", "", "", "", "", "", ""],
                        ["", "", "", "", "", "", "", "", "", "", "", "", "", ""]
                    ];

                    const sortedIdx = Object.keys(rowsMap).sort((a, b) => parseInt(a) - parseInt(b));
                    sortedIdx.forEach(idx => {
                        const rowObj = rowsMap[idx];
                        const row = [idx, rowObj.colorcode];
                        for (let j = 1; j <= 12; j++) {
                            row.push(rowObj.cols[String(j)] || "");
                        }
                        excelData.push(row);
                    });

                    const wb = XLSX.utils.book_new();
                    const ws = XLSX.utils.aoa_to_sheet(excelData);
                    XLSX.utils.book_append_sheet(wb, ws, "BundlePattern");
                    XLSX.writeFile(wb, 'BundlePattern_' + id + '.xlsx');
                })
                .catch(err => {
                    // console.error('导出Excel数据拉取失败:', err);
                    console.error('Export Excel data fetch failed:', err);
                    // alert('导出失败，无法获取该记录的数据，请检查控制台错误！');
                    alert('Export failed, unable to get data for this record, please check console errors!');
                });
        });
    });
}