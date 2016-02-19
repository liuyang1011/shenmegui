<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
	 <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
        <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/resources/js/treegrid-dnd.js"></script>

<script type="text/javascript" src="/resources/js/ui.js"></script>
</head>

<body>
<div style="width:100%">
    <table  class="easyui-treegrid" id="resultList" style=" width:100%;"
           data-options="
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: '/menu/menuTree?t='+ new Date().getTime(),
				method: 'get',
				idField: 'id',
				treeField: 'text',
				onBeforeExpand: function(node){
				    expandMenuCategory(node)
				}
               "
            >
        <thead>
        <tr>
            <th data-options="field:'id',width:'200', hidden:true"></th>
            <th data-options="field:'text',width:'200'">菜单名称</th>
            <th data-options="field:'append1',width:'200', formatter:operationCell">操作</th>

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
    //操作按钮
    var operationCell = function(value, row){
        var s = '<a onclick="add(\'' + value + '\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[新增子菜单]</span></span></a>';
        if(value == 'category'){
            s += '<a onclick="edit(\'' + value + '\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                    '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[修改]</span></span></a>';
            s += '<a onclick="detailPage(\'' + value + '\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                    '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[删除]</span></span></a>';
        }
        if(value == 'menu'){
            s = "";
            s += '<a onclick="detailPage(\'' + value + '\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                    '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[修改]</span></span></a>';
            s += '<a onclick="detailPage(\'' + value + '\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                    '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[删除]</span></span></a>';
        }
        return s;
    }
    function expandMenuCategory(node){
        if(node.children == null){
            $.ajax({
                type: "get",
                async: false,
                url: "/menu/nodeContent?id="+node.id,
                dataType: "json",
                success: function (result) {
                    $('#resultList').treegrid('append',{
                        parent: node.id,
                        data: result
                    });
                }

            });
        }
    }
    function add(){
        $('#opDialog').dialog({
            title: '新增菜单',
            width: 500,
            closed: false,
            cache: false,
            href: '/jsp/menu/menucategory_add.jsp',
            modal: true
        });
    }
</script>

</body>
</html>

