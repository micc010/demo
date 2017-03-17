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

		<title>查看</title>

		<link rel="stylesheet" href="../../../css/metro.min.css" />
		<link rel="stylesheet" href="../../../css/metro-icons.css" />
		<link rel="stylesheet" href="../../../css/font-awesome.min.css" />
		<link rel="stylesheet" href="../../../css/public.css" />
		<style>
			body{
				padding:20px;
			}
			
			.table.border, .table.bordered td, .table.bordered th {
			  border: 1px solid #CCC;
			}
			
			table thead{
				background-color: #EEE;
			}
			
			table tbody{
				  vertical-align: top;
				  font-size:16px;
			}
			
			table td.title{
				width:140px;
				font-weight: bold;
				background-color: rgb(245,255,254);
			}
			
			.fixed-head{
				position: fixed;
				width: 100%;
				padding: 22px 40px 0px 0px;
				background-color: #FFF;
				top: 0px;
			}
		</style>
	</head>

	<body>
		<!-- 内容开始 -->
		<div class="fixed-head">
			<h1 class="text-light">查看${entity.tableComment!''}</h1>
			<hr class="thin bg-grayLighter">
			<div style="position:relative;">
				<div style="position:absolute;top:-54px;right:0px;">
					<div class="place-right" style="position:relative;">
						<a href="./index.html">
				        	<button class="button primary">保存</button>
				        </a>
				        <a href="./index.html">
				    	    <button class="button">返回</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		
		<div style="padding:80px 0px 5px 0px;">
			<table class="table border bordered">
				<tbody>
				<#list propertyList as property>
					<#if property_index%2 == 0>
                    <tr>
					</#if>
						<td class="title">${property.columnComment}</td>
						<td>{{val.${property.propertyName}}}</td>
					<#if property_index%2 == 1>
                    </tr>
					</#if>
				</#list>
				</tbody>
			</table>
		</div>
		<!-- 内容结束  -->
	</body>

    <script type="text/javascript" src="../../../js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="../../../js/metro.min.js"></script>
    <script type="text/javascript" src="../../../js/vue.min.js" ></script>

</html>