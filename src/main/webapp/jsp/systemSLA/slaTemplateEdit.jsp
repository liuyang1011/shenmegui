<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String systemId=request.getParameter("systemId");
%>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<table id="slaTemplateTable" style="height:auto; width:auto;" title="">
	<thead>
		<tr>
			<th data-options="field:'productid',checkbox:true"></th>
			<th field="slaTemplateId" width="100" editor="text" id="idText"
				data-options="hidden:true">ID</th>
			<th field="templateName" width="100" editor="{type:'validatebox',options:{required:true,validType:['chineseB']}}" align="left">SLA模版名称</th>
			<th field="desc" width="150" align="left" editor="text">描 述</th>
		</tr>
	</thead>
</table>
<div id="win"></div>
<div id="w" class="easyui-window" title=""
	data-options="modal:true,closed:true,iconCls:'icon-add'"
	style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/assets/slaTemplate/js/slaTemplateManager.js"></script>
<script type="text/javascript" src="/assets/sla/js/slaManager.js"></script>
<script type="text/javascript">
	var slaTemplatetoolbar = [];
	slaTemplatetoolbar.push({})

	slaTemplatetoolbar.push({
		text : '选择',
		iconCls : 'icon-qxfp',
		handler : function() {
			var info = $('#slaTemplateTable').edatagrid('getSelected');
			if(info==""||info==null){
				alert("请选择一条信息！");
				return false;
			}
				if(saveSLATemplate(info.slaTemplateId)){
					$('#w').window('close');
					$('#sla').datagrid('reload');
				}
		}
	});

	slaTemplatetoolbar.push({
		text : 'SLA指标',
		iconCls : 'icon-qxfp',
		handler : function() {
			var row = $('#slaTemplateTable').edatagrid("getSelected");
			$('#win').window({
				width:600,
				height:300,
				iconCls : 'icon-add',
				title : "SLA["+row.templateName+"]模板",
				content:'<iframe scrolling="auto" frameborder="0"  src="/jsp/systemSLA/slaTemplate.jsp?slaTemplateId='+row.slaTemplateId+'" style="width:100%;height:100%;"></iframe>',
			});
		}
	});


	var editedRows = [];
	$(function() {

		$('#slaTemplateTable').edatagrid({
			rownumbers:true,
				singleSelect:true,
				url:"/slaTemplate/getAll/",
				method:'get',
				toolbar:slaTemplatetoolbar,
				onBeginEdit : function(index,row){
					editedRows.push(index);
				}
		});
	});
	function saveSLATemplate(slaTemplateId,systemId){
		var systemId='<%=systemId%>';
		var url = "/systemSla/setTemplateData/"+ slaTemplateId+"/"+systemId;
		$.ajax({
			"type" : "GET",
			"contentType" : "application/json; charset=utf-8",
			"url" : url,
			"dataType" : "json",
			"success" : function(result) {
			}
		});

		return true;
	}
</script>
