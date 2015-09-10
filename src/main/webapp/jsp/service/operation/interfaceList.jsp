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
  </head>
  
  <body>
   <table id="intefaceList" class="easyui-datagrid"
		style="height:370px; width:auto;">
		<thead>
			<tr>
				<th data-options="field:'invokeId',checkbox:true"></th>
				<th data-options="field:'systemId', width:80">系统id</th>
				<th data-options="field:'systemChineseName', width:100">系统名称</th>
				<th data-options="field:'interfaceId', width:150">接口id</th>
				<th data-options="field:'interfaceName', width:150">接口名称</th>
				<th data-options="field:'remark', width:100">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><a href="javascript:void(0)" onclick="$('#opDlg').dialog('close');" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">取消</a>&nbsp;&nbsp;
	    <a href="javascript:void(0)" onclick="selectInterface('${param.newListId}', '${param.type }');" class="easyui-linkbutton" iconCls="icon-ok" plain="true">确定</a>
    </td>
	  <td>
		  </td>
	  <td>
	  </td>
	  <td>
		  <input id="interfaceText" class="easyui-textbox" /> <a href="javascript:void(0)" onclick="queryInterfaceList()" class="easyui-linkbutton"  iconCls="icon-search">查询</a>

	  </td>
    </tr>
    </table>
    </div>
   <script type="text/javascript">
	   $(function(){
		   $("#intefaceList").datagrid({
			   rownumbers:true,
			   singleSelect:true,
			   url:'/serviceLink/getInterface?systemId=${param.systemId}&type=${param.type}',
			   method:'post',
			   toolbar:'#tb',
			   pagination:true,
			   pageSize:10
		   });
	   });
	   function queryInterfaceList(){
		   var params = {
			   "text" : $("#interfaceText").textbox("getValue")
		   };
		   $("#intefaceList").datagrid('options').queryParams = params;
		   $("#intefaceList").datagrid('reload');
	   }
   </script>
  </body>
</html>
