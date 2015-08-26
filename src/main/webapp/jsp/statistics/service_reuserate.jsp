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
                        							$('#resultList').treegrid('select', newValue);
                        							var node = $('#resultList').treegrid('getSelected');
                        							alert('关联场景数：'+ node.append2 +', 关联服务数:'+ node.append3 +',消费者数:' + node.append4 + ', 复用率：'+node.append5);
                        						}
                        					"
                        />
                </td>

                <th>

                </th>
                <td></td>
                <th>

                    <a href="#" id="clean" onclick="exportExcel()" class="easyui-linkbutton" iconCls="icon-excel-export" style="margin-left:1em" >导出EXCEL</a>

                </th>
                <td></td>
                <td></td>

            </tr>
        </table>


    </fieldset>
</form>
<div style="width:100%">
    <table id="resultList" class="easyui-treegrid"
           data-options="
			rownumbers:true,
			url:'/statistics/serviceReuseRate',
			singleSelect:true,
			fitColumns:false,
			method:'get',
			idField: 'id',
			treeField: 'text'"
           style="height:auto; width:100%;">
        <thead>
        <tr>

            <th data-options="field:'text',width:280">服务</th>
            <th data-options="field:'id',width:280">编码</th>
            <th data-options="field:'append2',width:80">关联服务数</th>
            <th data-options="field:'append3',width:80">关联场景数</th>

            <th data-options="field:'append4',width:80">消费者数</th>
            <th data-options="field:'append5',width:100">复用率</th>

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
    function exportExcel(){
        var data = $("#resultList").treegrid("getData");
        var form = $("<form>");//定义一个form表单
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/excelExporter/exportServiceReuserate");

        //appendTreeNode(form, data[0],"");

        $("body").append(form);//将表单放置在web中
        form.submit();//表单提交
    }
    function appendTreeNode(form, node, nodename){
        var fields = ["id", "text", "append2", "append3", "append4", "append5"];
        console.log(nodename);
        for (var j = 0; j < fields.length; j++) {
            try {
                var input1 = $("<input>");
                input1.attr("type", "hidden");
                input1.attr("name", nodename + fields[j]);
                input1.attr("value", node[fields[j]]);
                form.append(input1);
            } catch (exception) {
                continue;
            }
        }
        var children = node['children'];
        if(children != null){
            for (var i = 0; i < children.length; i++) {
                var child = children[i];
                var childName;
                if(nodename == ""){
                    childName = "children[" + i + "]";
                }else{
                    child =  childName+"[children[" + i + "]]";
                }

                appendTreeNode(form, child,childName);
            }
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

