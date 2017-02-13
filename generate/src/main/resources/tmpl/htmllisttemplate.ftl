<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="Metro, a sleek, intuitive, and powerful framework for faster and easier web development for Windows Metro Style.">
		<meta name="keywords" content="HTML, CSS, JS, JavaScript, framework, metro, front-end, frontend, web development">
		<meta name="author" content="Sergey Pimenov and Metro UI CSS contributors">

		<link rel='shortcut icon' type='image/x-icon' href='../favicon.ico' />

		<title>南宁城建计划</title>

		<link href="../../../css/bootstrap.min.css" rel="stylesheet">
		<link href="../../../css/font-awesome.min.css" rel="stylesheet">
		<link href="../../../css/animate.css" rel="stylesheet">
		<link href="../../../css/bootstrap.min.css">
		<link href="../../../css/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="../../../css/style.css" rel="stylesheet">
		<link href="../../../css/xiaowei.css" rel="stylesheet">
		<style>
			body{
				padding:20px;
			}

			table thead{
				background-color: #EEE;
			}

			#dialog{
				padding-top:5px;
			}
			#dialog h4{
				font-weight: bold;
			}
			#dialog .select-head{
				font-weight:bold;
				padding-left:30px;
			}
			#dialog .select-body{
				padding-left:60px;
			}
			#dialog .select-body label{
				width:100px;
			}

			.condition{
				position:relative;
				top:5px;
				margin-right:50px;
			}
			.condition-span {
				border: 1px solid #1a7bb9;
				padding: 3px 0px 3px 10px;
				background-color: #1a7bb9;
				color: #fff;
				margin-right: 10px;
				cursor: pointer;
			}
		</style>
	</head>
	<body id="vm-app">
		<!-- 内容开始  -->
		<div class="wrapper wrapper-content">
			<div class="page-header clearfix">
				<h1 class="col-sm-11">预警指标</h1>
				<div class="col-sm-1">
					<form name="form_search" id="form_search" method="post" class="pull-right">
						<div class="input-group-btn">
							<a href="add.html" class="btn btn-sm btn-primary">新增</a>
						</div>
					</form>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12 sub_left_menu table-responsive">
					<table class="table table-bordered">
						<thead>
						<tr>
							<th width="50">编号</th>
						<#list propertyList as property>
							<th width="100">${property.propertyName}</th>
						</#list>
							<th width="100">操作</th>
						</tr>
						</thead>
						<tbody>
						<tr v-for="(key, val) in project">
							<td>{{key + 1}}</td>
						<#list propertyList as property>
							<td>{{val.${property.propertyName}}}</td>
						</#list>
							<td>修改|删除</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- 内容结束  -->
	</body>
	<script src="../../../js/jquery-2.1.1.js"></script>
	<script src="../../../js/bootstrap.min.js"></script>
	<script src="../../../js/vue.min.js"></script>
	<script>
		$.getJSON(
				'../../../data/projectData.json',
				null,
				function(data) {
					var vm = new Vue({
						el: '#vm-app',
						data:{
							project : data
						}
					});
					vm.filter('myfilter', function (value) {
						var number = Math.floor(Math.random()*(2-1+1)+1);
						if(number ==  1) return '缺口';
						if(number ==  2) return '超支';
					})
				}
		);
	</script>
</html>