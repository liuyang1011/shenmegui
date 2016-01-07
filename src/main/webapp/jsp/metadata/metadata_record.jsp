<%@ page language="java" import="java.util.*, java.net.URLDecoder" pageEncoding="utf-8"%>
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
<table id="recordList" class="easyui-datagrid"
       data-options="
			rownumbers:true,
			singleSelect:false,
			<%--fitColumns:true,--%>
			url:'/metadataVersion/showRecord?id=${param.id }',
			method:'get',
          "
       style="height:370px; width:100%;">
    <thead>
    <tr>
        <th data-options="field:'',checkbox:true,width:50"></th>
        <th data-options="field:'optUser',width:100">用户账号</th>
        <th data-options="field:'chineseName',width:150">操作对象</th>
        <th data-options="field:'optType',width:80">操作类型</th>
        <th data-options="field:'params',width:250">操作内容</th>
        <th data-options="field:'optResult',width:80">结果</th>
        <th data-options="field:'optDate',width:150">操作时间</th>
    </tr>
</table>
</body>
</html>
