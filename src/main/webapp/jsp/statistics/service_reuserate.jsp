<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
</head>

<body>
<form id="searchForm">
    <fieldset>
        <legend>条件搜索</legend>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>服务分类</th>
                <td><input type="text" name="categoryId" id="categoryId"
                           class="easyui-combotree"
                           style="width:350px"
                           data-options="
                                           panelHeight:'auto',
                        						url:'/service/getTree',
                        				 		 method:'get',
                        				 		 valueField: 'categoryId',
                        				 		 textField: 'categoryName',
                        				 		 onChange:function(newValue, oldValue){
                        							this.value=newValue;
                        							var t = $('#categoryId').combotree('tree');	// get the tree object
                                              var node = t.tree('getSelected');
                                              $('#resultList').datagrid({ url:'/statistics/serviceReuseRate?id=' + newValue +'&type='+node.type});
                        						}
                        					"
                        />
                </td>

                <th>

                </th>
                <td></td>
                <th>
                    <!--
                    <a href="#" id="clean" onclick="exportExcel()" class="easyui-linkbutton" iconCls="icon-excel-export" style="margin-left:1em" >导出EXCEL</a>
                    -->
                </th>
                <td></td>
                <td></td>

            </tr>
        </table>


    </fieldset>
</form>
<div style="width:100%">
    <table id="resultList" class="easyui-datagrid"
           data-options="
			rownumbers:true,
			url:'/statistics/serviceReuseRate?id=root&type=serviceCategory',
			singleSelect:false,
			fitColumns:false,
			method:'get',
			pagination:true,
				pageSize:50"
           style="height:auto; width:100%;">
        <thead>
        <tr>

            <th data-options="field:'serviceNum',width:80">关联服务数</th>
            <th data-options="field:'operationNum',width:80">关联场景数</th>

            <th data-options="field:'operationInvokeNum',width:80">消费者数</th>
            <th data-options="field:'reuseRate',width:100">复用率</th>

        </tr>
        </thead>
    </table>
</div>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<div id="opDialog" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
</body>
<script type="text/javascript">
    var formatter = {
        typeText: function (value, row, index) {
            if ("1" == value) {
                return "消费者";
            }
            if ("0" == value) {
                return "提供者";
            }
        }
    };
    function exportExcel(){
        var checkedItems = $('#resultList').datagrid('getChecked');

        if (checkedItems != null && checkedItems.length > 0) {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/excelExporter/exportServiceReuserate");
            var fields = ["serviceNum", "operationNum", , "operationInvokeNum", "reuseRate"];
            for (var i = 0; i < checkedItems.length; i++) {
                for (var j = 0; j < fields.length; j++) {
                    var input1 = $("<input>");
                    input1.attr("type", "hidden");
                    input1.attr("name", "list[" + i + "]." + fields[j]);
                    input1.attr("value", checkedItems[i][fields[j]]);
                    form.append(input1);
                }
            }

            $("body").append(form);//将表单放置在web中
            form.submit();//表单提交
        }
        else {
            alert("没有选中数据！");
        }
    }

    function query() {
        var params = {
            "categoryId": $("#categoryId").textbox("getValue")
        }
        $("#resultList").datagrid('options').queryParams = params;
        var p = $("#resultList").datagrid('getPager');
        var total = $(p).pagination("options").total;
        if (total < 100) {
            total = 100;
        }
        $(p).pagination({
            pageList: [10, 20, 50, total]
        });
        $("#resultList").datagrid('reload');
    }
</script>

</body>
</html>

