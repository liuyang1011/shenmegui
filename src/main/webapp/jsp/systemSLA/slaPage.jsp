<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String systemNo=request.getParameter("systemNo");
	String systemId=request.getParameter("systemId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>
<table id="sla" style="height:470px; width:auto;" title="系统[<%=systemNo%>]SLA管理">
    <thead>
    <tr>
        <th field="slaId" width="100" editor="text"
            data-options="hidden:true">ID
        </th>
        <th field="slaName" width="150" editor="{type:'validatebox',options:{required:true,validType:['unique','chineseB']}}" align="left">SLA指标</th>
        <th field="slaValue" width="150" align="left" editor="{type:'validatebox'}">取值范围</th>
        <th field="slaDesc" width="400" align="left" editor="{type:'validatebox'}">描
            述
        </th>
        <th field="slaRemark" width="150" align="left" editor="text">备
            注
        </th>
    </tr>
    </thead>
</table>

<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>
<script type="text/javascript" src="/assets/sla/js/slaManager.js"></script>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
	var systemId='<%=systemId%>';
	$(function () {
		$.extend($.fn.validatebox.defaults.rules, {
			unique: {
				validator: function (value, param) {
					var result;
					var node=$('#sla').edatagrid("getSelected");
					var nodeName=node.slaName;
					if(nodeName==value){
						return true;
					}
					$.ajax({
						type: "post",
						async: false,
						url: "/systemSla/uniqueValid",
						data:"slaName="+value+"&systemId="+systemId,
						success: function (data) {
							result = data;
						}
					});
					return result;
				},
				message: '已存在相同SLA指标'
			}
		});
	});
	var slaUrl = "/systemSla/getAll/" + systemId;
	var toolbar = [];
	<shiro:hasPermission name="sla-add">
	toolbar.push({
		text : '新增',
		iconCls : 'icon-add',
		handler : function() {
			$('#sla').edatagrid('addRow');
		}
	})
	</shiro:hasPermission>
	<shiro:hasPermission name="sla-delete">
	toolbar.push({
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var deleteData = $('#sla').edatagrid('getSelected');
			if(deleteData==""||deleteData==null){
				alert("请选择一条信息！");
				return false;
			}
			if(!confirm("确定删除吗？")){
				return;
			}
			deleteByEntity(deleteData,function(result){
				if(result){
					$('#sla').datagrid('reload');
					alert("删除成功！");
				}else{
					$('#sla').datagrid('reload');
					alert("删除失败！");
				}
			});
		}
	});
	</shiro:hasPermission>
	<shiro:hasPermission name="sla-update">
	toolbar.push({
		text : ' 保存',
		iconCls : 'icon-save',
		handler : function() {
			for ( var per in editedRows) {
				$("#sla").datagrid('endEdit', editedRows[per]);
				if(!$("#sla").datagrid('validateRow',editedRows[per])){
					alert("请以正确格式输入必输项");
					return false;
				}
			}
			var editData = $("#sla").datagrid('getChanges');
			addSystemSLA(editData,systemId,function(result){
				if(result){
					$('#sla').datagrid('reload');
					alert("保存成功！");
				}else{
					$('#sla').datagrid('reload');
					alert("保存失败！");
				}
			});

			editedRows = [];

		}
	});
	</shiro:hasPermission>
	<shiro:hasPermission name="slaTemp-get">
	toolbar.push({
		text : 'SLA模版',
		iconCls : 'icon-qxfp',
		handler : function() {
			uiinit.win({
				w : 600,
				iconCls : 'icon-add',
				title : "SLA模版",
				url : "/jsp/systemSLA/slaTemplateEdit.jsp?systemId=<%=systemId%>"
			})
		}
	});
	</shiro:hasPermission>

		var editedRows = [];
		$(function() {
			$('#sla').edatagrid({
				rownumbers:true,
				singleSelect:true,
				url:slaUrl,
				method:'get',
				toolbar:toolbar,
				pagination: true,
				pageSize: 15,
				pageList: [15,30,50],
				onBeginEdit : function(index,row){
					editedRows.push(index);
				}
			});

		});
	function addSystemSLA(data,systemId, callBack) {
		var url="/systemSla/add/"+systemId;
		$.ajax({
			"type" : "POST",
			"contentType" : "application/json; charset=utf-8",
			"url" : url,
			"data" : JSON.stringify(data),
			"dataType" : "json",
			"success" : function(result) {
				callBack(result);
			}
		});
	}

	function deleteByEntity(data, callBack) {
		$.ajax({
			"type" : "DELETE",
			"contentType" : "application/json; charset=utf-8",
			"url" : "/systemSla/delete",
			"dataType" : "json",
			"data": JSON.stringify(data),
			"success" : function(result) {
				callBack(result);
			}
		});
	}
	</script>
</body>
</html>
