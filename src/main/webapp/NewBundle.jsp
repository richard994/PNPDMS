<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bundle Pattern</title>
	<script src="js/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="js/newBundle.js"></script>
	<!-- 引入 SheetJS 库，用于前端无后端依赖生成 Excel -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
	<link rel="stylesheet" href="css/newBundle.css">

	<style>
		/* 内部保留导航必要样式 */
		#headbar { font-size: calc( 12px + (20 - 16) * (100vw - 400px) / (800 - 400) ); font-weight: 300; }
		#home::before { content: url(home.png) !important; }
		.breadcrumb-item::before { content: url(discount.png) !important; padding-right: 5px !important; }
		.breadcrumb-item::after { content: ">"; padding-left: 10px; }
		.breadcrumb-item:last-child::after { content: ""; }
		.breadcrumb-item:last-child { opacity: 1.0; }
		.breadcrumb-item { opacity: 0.5; }
		.nav-link { color: black; }
		.dropdown-menu { display: none; position: absolute; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); z-index: 1; }
		.dropdown-menu li a { color: black; text-decoration: none; display: block; text-align: left; }
		.dropdown-menu li a:hover { background-color: #ddd; }
		.dropdown:hover .dropdown-menu { display: block; }
	</style>

	<script type="text/javascript">
		var quotes = ${empty quotes ? '[]' : quotes};
	</script>
</head>
<body class="bg-light" style="overflow: auto">
      <c:set var="activePage" value="Bundle" scope="request" />
	  <jsp:include page="/WEB-INF/jspf/navbar.jsp" />

<nav aria-label="breadcrumb" id="breadcrumb" style="margin-left: 165px">
	<ol class="breadcrumb">
		<li class="breadcrumb-item" id="home">Home</li>
		<li class="breadcrumb-item">Bundle Pattern</li>
	</ol>
</nav>

<!-- 全新重写的包裹层，统一样式与对齐 -->
<div class="main-wrapper">

	<!-- 查询条件区域 -->
	<div class="filter-area bg-white p-3 mb-3 rounded border shadow-sm">
		<div class="row g-3 align-items-end">
			<div class="col-md-3">
				<label class="form-label mb-1">Pattern Name</label>
				<input type="text" id="f_patternid" class="form-control form-control-sm">
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">Flowers</label>
				<input type="text" id="f_flowers" class="form-control form-control-sm">
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">Designer</label>
				<input type="text" id="f_designer" class="form-control form-control-sm">
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">Start Date</label>
				<input type="date" id="f_bdate" class="form-control form-control-sm">
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">End Date</label>
				<input type="date" id="f_edate" class="form-control form-control-sm">
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">Status</label>
				<select id="f_confirmflag" class="form-select form-select-sm">
					<option value="">All</option>
					<option value="Y">Confirmed</option>
					<option value="N">Unconfirmed</option>
				</select>
			</div>
			<div class="col-md-3">
				<label class="form-label mb-1">Quote Flag</label>
				<select id="f_quoteflag" class="form-select form-select-sm">
					<option value="">All</option>
					<option value="Y">Quoted</option>
					<option value="N">Unquoted</option>
				</select>
			</div>
			<div class="col-md-3 d-flex gap-2 align-items-end">
				<button class="btn btn-primary btn-sm flex-grow-1" onclick="filterAndRender()" id="searchqp">Search</button>
				<button class="btn btn-secondary btn-sm flex-grow-1" onclick="refresh()" id="resetqpsearch">Reset</button>
			</div>
		</div>
	</div>

	<!-- 创建与导出按钮 -->
	<div class="d-flex justify-content-between mb-3">
		<button class="btn btn-primary btn-sm" onclick="window.location.href='BundlePatternService?action=create'">
			+ Create Bundle Pattern
		</button>
	</div>

	<!-- 数据表格区域（彻底清洗） -->
	<div class="table-wrapper bg-white rounded border shadow-sm p-0">
		<table class="table table-bordered table-hover mb-0" id="quotedprices" style="width: 100%;">
			<thead>
			<tr>
				<th style="width: 40px; text-align: center;"><input type="radio" disabled></th>
				<th style="width: 50px;">#</th>
				<th>Pattern Name</th>
				<th>Flowers</th>
				<th>Designer</th>
				<th>Date</th>
				<th>Width</th>
				<th>Total Warp</th>
				<th>Total Ends</th>
				<th>Price</th>
				<th>Status</th>
				<th>Quote Flag</th>
			</tr>
			</thead>
			<tbody class="bg-white" id="qpbody"></tbody>
		</table>
	</div>

	<!-- 底部操作按钮 -->
	<div class="d-flex gap-2 mt-3">
<%--		<button class="btn btn-outline-primary btn-sm" id="btnEdit">编辑</button>--%>
<%--		<button class="btn btn-outline-danger btn-sm" id="btnDelete">删除</button>--%>
<%--		<button class="btn btn-outline-success btn-sm" id="btnConfirm">确认</button>--%>
<%--		<button class="btn btn-outline-warning btn-sm" id="btnUnConfirm">反确认</button>--%>
<%--		<button class="btn btn-outline-secondary btn-sm" id="btnExport">导出Excel</button>--%>
		<button class="btn btn-outline-primary btn-sm" id="btnEdit">Edit</button>
		<button class="btn btn-outline-danger btn-sm" id="btnDelete">Delete</button>
		<button class="btn btn-outline-success btn-sm" id="btnConfirm">Confirm</button>
		<button class="btn btn-outline-warning btn-sm" id="btnUnConfirm">Unconfirm</button>
		<button class="btn btn-outline-secondary btn-sm" id="btnExport">Export Excel</button>
	</div>

	<!-- 删除确认弹窗 -->
	<div class="modal fade" id="dlModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="dlModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Attention</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle" style="color: red">
						<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
						<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
					</svg>&nbsp;&nbsp;Please confirm deletion.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="confirmDelete" data-bs-dismiss="modal">Confirm</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="js/newBundle.js"></script>
</body>
</html>