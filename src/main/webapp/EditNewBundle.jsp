<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Edit Bundle Pattern</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/addnewdev.css" />
	<link rel="stylesheet" type="text/css" href="css/editnewbundle.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<style>
		#home::before { content: url(home.png) !important; }
		.breadcrumb-item::before { content: url(discount.png) !important; padding-right: 5px !important; }
		.nav-link { color: black; }

		/* ✅ 复制新增页面的统一字体布局，彻底解决字体不一问题 */
		#wtable, #wtable th, #wtable td, #wtable input[type="text"] {
			font-size: 13px !important;
		}
		#wtable input[type="text"] {
			height: 36px !important;
			width: 100% !important;
			padding: 0 4px !important;
		}
		#wtable th, #wtable td {
			vertical-align: middle !important;
		}
	</style>
	<script defer>
		var devid = ${ empty devid ? 'null' : devid };
		var view = ${ empty view ? 'null' : view };
		var edit = ${ empty edit ? 'null' : edit };
		var create = ${ empty create ? 'null' : create };
		var user = '${user}';
		var dev = ${ empty dev ? 'null' : dev };
		var comments = ${ empty comments ? '[]' : comments };
		var logs = ${ empty logs ? '[]' : logs };
		var fullLogs = ${ empty fulllogs ? '[]' : fulllogs };
		var mtrcolors = ${ empty mtrcolors ? '[]' : mtrcolors };
		var mtrspec = ${ empty mtrspec ? '[]' : mtrspec };

		var quote = ${ empty data ? 'null' : data };
		console.log("后端回传的数据 quote = ", quote);
	</script>
</head>

<body class="bg-light">
<div id="empty" style="display: hidden"></div>
<c:set var="activePage" value="Bundle" scope="request" />
<jsp:include page="/WEB-INF/jspf/navbar.jsp" />

<nav aria-label="breadcrumb" id="breadcrumb" style="width: 100%; white-space: nowrap; padding: 0 40px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item" id="home">Home</li>
		<li class="breadcrumb-item">Bundle</li>
		<li class="breadcrumb-item">Edit Bundle Pattern</li>
	</ol>
</nav>

<div class="pb-0 rounded-0" id="displayqp">
	<div class="container-fluid text-center py-4 pb-1">
		<h1 class="display-5 fw-bold mb-2" id="customerName"><i class="fa fa-cny"></i>David Crowder</h1>
	</div>
</div>
<div class="bg-white rounded-0 mt-2 overflow-auto" id="constraint">
	<div class="container-fluid text-center pt-4">
		<div class="row d-flex align-items-center justify-content-center">
			<div class="col" style="margin-left: 20px">
				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-1-circle-fill" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z"/>
					</svg>
					<span>&nbsp&nbspProduct Design</span>
				</div>
			</div>
			<div class="col opacity-50" id="progress1"><div class="card bg-light" style="height:1%; width:80%"></div></div>
			<div class="col" id="progress2">
				<div class="d-flex align-items-center justify-content-center opacity-50">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle" viewBox="0 0 16 16">
						<path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z"/>
					</svg>
					<span>&nbsp&nbspYarns</span>
				</div>
			</div>
			<div class="col" id="progressed1" style="display: none">
				<div class="d-flex align-items-center justify-content-center" style="color: #4D73FF">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle-fill" viewBox="0 0 16 16">
						<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24c0-.691.493-1.306 1.336-1.306.756 0 1.313.492 1.313 1.236 0 .697-.469 1.23-.902 1.705l-2.971 3.293V12h5.344v-1.107H7.268v-.077l1.974-2.22.096-.107c.688-.763 1.287-1.428 1.287-2.43 0-1.266-1.031-2.215-2.613-2.215-1.758 0-2.637 1.19-2.637 2.402v.065h1.271v-.07Z"/></svg>
					</svg>
					<span>&nbsp&nbspYarns</span>
				</div>
			</div>
			<div class="col opacity-50" id="progress3"><div class="card bg-light" style="height:1%; width:80%"></div></div>
			<div class="col" style="margin-right: 20px" id="progress4">
				<div class="d-flex align-items-center justify-content-center opacity-50">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
						<path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
						<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
					</svg>
					<span>&nbsp&nbspSave Pattern</span>
				</div>
			</div>
		</div>
	</div>

	<div class="container m-4 h5" id="title1"><span>Edit Product Design</span></div>

	<div class="row g-4 border-bottom border-2" style="margin-left: 0; margin-right:0; padding: 0 10px;">
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="pmodel" class="control-label opacity-75">Pattern Name: <span class="text-danger align-middle">*</span></label>
				<input type="text" name="pmodel" id="pmodel" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="warpNo" class="control-label opacity-75">Warp #:</label>
				<input type="text" name="warpNo" id="warpNo" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="flowers" class="control-label opacity-75">flowers: <span class="text-danger align-middle">*</span></label>
				<input type="text" name="flowers" id="flowers" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="breadth" class="control-label opacity-75">breadth: <span class="text-danger align-middle">*</span></label>
				<input type="text" name="breadth" id="breadth" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="designer" class="control-label opacity-75">Designer:</label>
				<input type="text" name="designer" id="designer" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="yarnType" class="control-label opacity-75">Yarn Type:</label>
				<input type="text" name="yarnType" id="yarnType" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="date" class="control-label opacity-75">Date:</label>
				<input type="date" id="date" name="date" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="totalEnds" class="control-label opacity-75">Total Ends:</label>
				<input type="text" name="totalEnds" id="totalEnds" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="discNo" class="control-label opacity-75">Disc #:</label>
				<input type="text" name="discNo" id="discNo" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label class="control-label opacity-75">Target Price:</label>
				<input type="text" id="targetPrice" name="targetPrice" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="construction" class="control-label opacity-75">CONSTRUCTION</label>
				<input type="text" name="construction" id="construction" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="picksInch" class="control-label opacity-75">Picks/inch: <span class="text-danger align-middle">*</span></label>
				<input type="text" name="picksInch" id="picksInch" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="ppcm" class="control-label opacity-75">Picks/cm: <span class="text-danger align-middle">*</span></label>
				<input type="text" name="ppcm" id="ppcm" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="shipTo" class="control-label opacity-75">Ship to:</label>
				<input type="text" name="shipTo" id="shipTo" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>
		<div class="col-md-4">
			<div class="d-flex flex-column">
				<label for="attention" class="control-label opacity-75">Attention:</label>
				<input type="text" name="attention" id="attention" class="form-control border-0 rounded-0 bg-light"/>
			</div>
		</div>

		<div class="container-fluid text-center mt-4">
			<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px" type="button" onclick="validatePhaseOne()" id="nextbtn">NEXT</button>
		</div>
	</div>

	<!-- 第二个表格 -->
	<div class="container m-4 h5" style="display: none" id="WW"><span>Edit Yarns</span></div>
	<div class="container m-4 h5" id="wwbtncontainer" style="display: none">
		<button class="btn border-0 btn-lg rounded-0 mb-3" type="button" style="background-color: #4D73FF; color: white; width: 180px" onclick="addWW(true)" id="WWbtn">+ Create</button>
	</div>

	<!-- ✅ 核心修改：删除 select，改为原生的 input + datalist，和新增页面完全一致 -->
	<table class="table table-lg table-bordered" style="margin: 0 auto; width: 100%; display: none; table-layout: fixed;" id="wtable">
		<colgroup>
			<col style="width: 60px;">
			<col style="width: 100px;">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: calc((100% - 60px - 100px - 120px) / 12);">
			<col style="width: 120px;">
		</colgroup>
		<thead class="table-light">
		<tr>
			<th colspan="2">Selectors</th>
			<th>sel 1</th>
			<th>sel 2</th>
			<th>sel 3</th>
			<th>sel 4</th>
			<th>sel 5</th>
			<th>sel 6</th>
			<th>sel 7</th>
			<th>sel 8</th>
			<th>sel 9</th>
			<th>sel 10</th>
			<th>sel 11</th>
			<th>sel 12</th>
			<th>Option</th>
		</tr>
		</thead>
		<tbody id="wtablerow">
		<!-- 静态行 1：Yarn Type -->
		<tr id="row_yarn">
			<td colspan="2">Yarn Type</td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel1" name="yarn_sel1" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel2" name="yarn_sel2" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel3" name="yarn_sel3" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel4" name="yarn_sel4" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel5" name="yarn_sel5" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel6" name="yarn_sel6" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel7" name="yarn_sel7" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel8" name="yarn_sel8" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel9" name="yarn_sel9" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel10" name="yarn_sel10" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel11" name="yarn_sel11" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" id="yarn_sel12" name="yarn_sel12" list="globalSpecList" placeholder="Enter" title=""/></td>
			<td></td>
		</tr>
		<!-- 静态行 2：Shots -->
		<tr id="row_shots">
			<td colspan="2">Shots</td>
			<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
			<td></td>
		</tr>
		<!-- 静态行 3：# / color code -->
		<tr id="row_header">
			<td>#</td>
			<td>color code</td>
			<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
			<td></td>
		</tr>
		<!-- 动态数据行 1 -->
		<tr id="row1">
			<td><input type="text" id="index1" name="index1" class="form-control border-0 rounded-0" value="1" /></td>
			<td><input type="text" id="colorCode1" name="colorCode1" class="form-control border-0 rounded-0" value="" placeholder="Enter" /></td>
			<td><input type="text" id="sel1_1" name="sel1_1" class="form-control border-0 rounded-0" list="color_datalist_1" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel2_1" name="sel2_1" class="form-control border-0 rounded-0" list="color_datalist_2" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel3_1" name="sel3_1" class="form-control border-0 rounded-0" list="color_datalist_3" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel4_1" name="sel4_1" class="form-control border-0 rounded-0" list="color_datalist_4" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel5_1" name="sel5_1" class="form-control border-0 rounded-0" list="color_datalist_5" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel6_1" name="sel6_1" class="form-control border-0 rounded-0" list="color_datalist_6" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel7_1" name="sel7_1" class="form-control border-0 rounded-0" list="color_datalist_7" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel8_1" name="sel8_1" class="form-control border-0 rounded-0" list="color_datalist_8" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel9_1" name="sel9_1" class="form-control border-0 rounded-0" list="color_datalist_9" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel10_1" name="sel10_1" class="form-control border-0 rounded-0" list="color_datalist_10" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel11_1" name="sel11_1" class="form-control border-0 rounded-0" list="color_datalist_11" placeholder="Enter" title=""/></td>
			<td><input type="text" id="sel12_1" name="sel12_1" class="form-control border-0 rounded-0" list="color_datalist_12" placeholder="Enter" title=""/></td>
			<td style="white-space: nowrap">
				<button class="btn btn-link btn-sm text-decoration-none" type="button" id="cpbtn1" onclick="copy(this.id)">COPY</button>
				<button class="btn btn-link btn-sm text-decoration-none text-danger" type="button" id="1">DELETE</button>
			</td>
		</tr>
		</tbody>
	</table>

	<!-- 动态行模板 -->
	<template id="ww-row-template">
		<tr style="width:100%">
			<td><input type="text" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
			<td><input type="text" class="form-control border-0 rounded-0" placeholder="Enter"/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td><input type="text" class="form-control border-0 rounded-0" list="globalColorList" placeholder="Enter" title=""/></td>
			<td style="white-space:nowrap;width:10%">
				<button type="button" class="btn btn-link btn-sm text-decoration-none">COPY</button>
				<button type="button" class="btn btn-link btn-sm text-decoration-none text-danger">DELETE</button>
			</td>
		</tr>
	</template>

	<div class="container-fluid text-center mx-auto" style="width: 180px" id="saveblk">
		<button class="btn border-0 btn-lg rounded-0 mb-3" style="background-color: #4D73FF; color: white; width: 180px; display: none" type="submit" id="savebtn">SAVE</button>
	</div>
	<!-- Toast 提示组件（2秒后自动消失） -->
	<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 9999;">
		<div id="liveToast" class="toast align-items-center text-white border-0" role="alert" aria-live="assertive" aria-atomic="true" style="background-color: #4D73FF !important;">
			<div class="d-flex">
				<div class="toast-body" id="toastMessage">
					<!-- 提示信息填充处 -->
				</div>
				<button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="js/editnewbundle.js"></script>

<!-- ✅ 注入全局面板列表：只保留 globalSpecList，移除 globalColorList -->
<datalist id="globalSpecList"></datalist>

<!-- ✅ 注入列级 datalist（替代原有的 globalColorList） -->
<datalist id="color_datalist_1"></datalist>
<datalist id="color_datalist_2"></datalist>
<datalist id="color_datalist_3"></datalist>
<datalist id="color_datalist_4"></datalist>
<datalist id="color_datalist_5"></datalist>
<datalist id="color_datalist_6"></datalist>
<datalist id="color_datalist_7"></datalist>
<datalist id="color_datalist_8"></datalist>
<datalist id="color_datalist_9"></datalist>
<datalist id="color_datalist_10"></datalist>
<datalist id="color_datalist_11"></datalist>
<datalist id="color_datalist_12"></datalist>

<script>
	// 【核心修复】页面加载完成后，触发数据回显和展开进度条
	if (edit) {
		populateAllInputs();
	}
	document.addEventListener('DOMContentLoaded', function() {
		const specDatalist = document.getElementById('globalSpecList');
		if (mtrspec && mtrspec.length > 0) {
			mtrspec.forEach(item => {
				const opt = document.createElement('option');
				opt.value = item.name;
				specDatalist.appendChild(opt);
			});
		}
		// 注意：联动 data-list 的填充交给了 JS 中的 updateColumnOptions
	});
</script>
</body>
</html>