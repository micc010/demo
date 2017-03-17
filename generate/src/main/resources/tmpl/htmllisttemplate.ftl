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
		<title>列表</title>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
		<script src="${"$"}{springMacroRequestContext.contextPath}/js/html5shiv.js"></script>
        <![endif]-->
        <link href="${"$"}{springMacroRequestContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body id="vm-app">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li>
                            <a href="#">首页</a> <span class="divider">/</span>
                        </li>
                        <li>
                            <a href="#">Library</a> <span class="divider">/</span>
                        </li>
                        <li class="active">
                            Data
                        </li>
                    </ul>
                    <div class="page-header">
                        <h1>
                        ${entity.tableComment!''}<small></small>
                        </h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group form-inline">
                        <input class="form-control" id="username" type="text" />
                        <button type="submit" class="btn btn-default" id="search">
                            搜索
                        </button>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-condensed table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>
                                序号
                            </th>
                        <#list propertyList as property>
                            <th>${property.columnComment}</th>
                        </#list>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody id="dataContain">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="pageContain">
                </div>
            </div>
        </div>
        <script id="tpl" type="text/html">
            {{each data as val i}}
            <tr>
                <td>
                    {{i + 1}}
                </td>
            <#list propertyList as property>
                <td>{{val.${property.propertyName}}}</td>
            </#list>
                <td>
                    修改|删除
                </td>
            </tr>
            {{/each}}
        </script>
        <script id="pageTpl">
            <ul class="pagination pagination-sm">
                    <li>
                    <a href="javascript:;">当前第{{pageNum}}页</a>
            </li>
                    <li>
                    <a href="javascript:;" onclick="javascript:page(1);">首页</a>
                    </li>
                    {{if hasPreviousPage}}
            <li>
            <a href="javascript:;" onclick="javascript:page({{pageNum - 1}});">前一页</a>
                    </li>
                    {{/if}}
            {{if hasNextPage}}
            <li>
            <a href="javascript:;" onclick="javascript:page({{pageNum + 1}});">后一页</a>
                    </li>
                    {{/if}}
            <li>
            <a href="javascript:;" onclick="javascript:page({{pages}});">末页</a>
                    </li>
            </ul>
        </script>
        <script src="${"$"}{springMacroRequestContext.contextPath}/js/jquery.min.js"></script>
        <!--[if lt IE 9]>
        <script src="${"$"}{springMacroRequestContext.contextPath}/js/jquery-1.9.1.min.js"></script>
        <![endif]-->
        <script src="${"$"}{springMacroRequestContext.contextPath}/js/bootstrap.min.js"></script>
        <script src="${"$"}{springMacroRequestContext.contextPath}/js/template.js"></script>
        <script src="${"$"}{springMacroRequestContext.contextPath}/js/layer/layer.js"></script>
        <script>
            var load = function (query){
                var index = layer.load(1, { shade: [0.3, '#000'] });
                $.ajax({
                    url: '${"$"}{springMacroRequestContext.contextPath}${"$"}{path}/login-list',
                    data: query,
                    type: 'GET',
                    success: function(data) {
                        $("#dataContain").html(template('dataTpl', data));
                        $("#pageContain").html(template('pageTpl', data));
                        layer.close(index);
                    },
                    error: function() {
                        layer.close(index);
                        layer.msg("数据加载错误！", { icon: 2 })
                    }
                });
            };

            var page = function(pageNum) {
                var condition = $.trim(${"$"}("#condition").val()) || undefined,
                        query = {pageNum: pageNum, pageSize: 10};

                load(query);
            }

            $(function () {
                $("#search").on('click', function () {
                    page(1);
                })

                load({pageNum: 1, pageSize: 10});
            });
        </script>
    </body>
</html>