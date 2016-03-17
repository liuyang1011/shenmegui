<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
 	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>列表页</title>
	<link rel="stylesheet" type="text/css"
		  href="/resources/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		  href="/resources/themes/icon.css">
	<link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>

<table id="slaTemplate" style="height:100%; width:auto;">
	<thead>
	<tr>
		<th field="slaId" width="10%" editor="text" data-options="hidden:true"  >ID</th>
		<th field="slaName" width="15%" editor="{type:'validatebox',options:{required:true,validType:['chineseB']}}" align="left">SLA指标</th>
		<th field="slaValue" width="15%" align="left" editor="text">取值范围</th>
		<th field="slaDesc" width="40%" align="left" editor="text">描 述</th>
		<th field="slaRemark" width="25%" align="left" editor="text">备 注</th>
		<th field="slaTemplateId" width="10%" editor="text" data-options="hidden:true"  >模板</th>
	</tr>
	</thead>
</table>

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>
<script type="text/javascript"
		src="/assets/sla/js/slaManager.js"></script>
		<script type="text/javascript" src="/assets/slaTemplate/js/slaTemplateManager.js"></script>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
 var surl="/slaTemplate/getSLA/"+"${param.slaTemplateId}";
	$(function() {
		$('#slaTemplate').edatagrid({
			rownumbers: true,
			singleSelect: true,
			url: surl,
			method: 'get'
		});
	})
</script>
</body>
</html>
