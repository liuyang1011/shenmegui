<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sdaHisPage.jsp' starting page</title>
  </head>
  
  <body>
   <table title="服务列表" class="easyui-treegrid" id="serviceTree" style=" width:auto;"
			data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				fitColumns: true,
				url: '/service/serviceTree',
				method: 'get',
				idField: 'id',
				treeField: 'text'
				"
                >
		<thead>
			<tr>
				<th data-options="field:'id',width:180,editor:'text'">服务编号</th>
				<th data-options="field:'text',width:180,editor:'text'">服务名称</th>
				<th data-options="field:'append1',width:60,align:'right',editor:'text'">版本</th>
				<th data-options="field:'append2',width:80,editor:'text'">功能描述</th>
				<th data-options="field:'append3',width:80,editor:'text'">备注</th>
			</tr>
		</thead>
	</table>
  </body>
</html>