<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <base href="<%=basePath%>">

    <title>My JSP 'sdaHisPage.jsp' starting page</title>
  </head>

  <body>
  	<table title="服务场景" class="easyui-treegrid" id="tg" style=" width:auto;"
    			data-options="
    				iconCls: 'icon-ok',
    				rownumbers: true,
    				fitColumns: false,
    				animate: true,
    				collapsible: true,
    				fitColumns: true,
    				url: '/serviceIdentify/getByMetadataId/${param.interfaceId}',
    				method: 'get',
    				idField: 'id',
    				treeField: 'text'
    				"
                    >
    		<thead>
    			<tr>
    				<th data-options="field:'serviceId',width:80,editor:'text'">服务代码</th>
    				<th data-options="field:'operationId',width:40,editor:'text'">场景代码</th>
                    <th data-options="field:'systemId',width:200,editor:'text'">关联系统</th>
    			</tr>
    		</thead>
    	</table>
  </body>
</html>
