<%--
  Created by IntelliJ IDEA.
  User: lmd
  Date: 2016/2/17
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <base href="<%=basePath%>">
  <title>My JSP 'MyJsp.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">

</head>

<body>
<div id="userId" style="display: none"><shiro:principal/></div>
<script type="text/javascript" src="/assets/task/taskManager.js"></script>

<form class="formui" id="metadataForm" action="/metadata/add" method="post">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>流程1</th>
      <td><input class="easyui-textbox" style="width:150px;" name="flow1" id="flow1" disabled='disabled'></td>
      <th>下节点处理人</th>
      <td><select class="easyui-combobox" style="width:130px;" name="nextUser1" id="nextUser1" ></td>
    </tr>
    <tr>
      <th>流程2</th>
      <td><input class="easyui-textbox" style="width:150px;" name="flow2" id="flow2" disabled='disabled'></td>
      <th>下节点处理人</th>
      <td><select class="easyui-combobox" style="width:130px;" name="nextUser1" id="nextUser2" ></td>
    </tr>
    <tr>
      <th>流程3</th>
      <td><input class="easyui-textbox" style="width:150px;" name="flow3" id="flow3" disabled='disabled'></td>
      <th>下节点处理人</th>
      <td><select class="easyui-combobox" style="width:130px;" name="nextUser3" id="nextUser3" ></td>
    </tr>
    <tr>
      <th>流程4</th>
      <td><input class="easyui-textbox" style="width:150px;" name="flow4" id="flow4" disabled='disabled'></td>
      <th>下节点处理人</th>
      <td><select class="easyui-combobox" style="width:130px;" name="nextUser4" id="nextUser4" ></td>
    </tr>
    <tr>
      <th>流程5</th>
      <td><input class="easyui-textbox" style="width:150px;" name="flow5" id="flow5" disabled='disabled'></td>
      <th>下节点处理人</th>
      <td><select class="easyui-combobox" style="width:130px;" name="nextUser5" id="nextUser5" ></td>
    </tr>
    <tr>
      <th>意见</th>
      <td><textarea name="message" style="height:60px;" id="message"></textarea></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td class="win-bbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="completeTaskBtn" class="easyui-linkbutton" iconCls="icon-save">处理</a>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="/assets/task/completeAllTask.js"></script>
<script type="text/javascript">
  if(parent.PROCESS_INFO.name1 != undefined){
    document.getElementById("flow1").value = parent.PROCESS_INFO.name1;
  }
  if(parent.PROCESS_INFO.name2 != undefined){
    document.getElementById("flow2").value = parent.PROCESS_INFO.name2;
  }
  if(parent.PROCESS_INFO.name3 != undefined){
    document.getElementById("flow3").value = parent.PROCESS_INFO.name3;
  }
  if(parent.PROCESS_INFO.name4 != undefined){
    document.getElementById("flow4").value = parent.PROCESS_INFO.name4;
  }
  if(parent.PROCESS_INFO.name5 != undefined){
    document.getElementById("flow5").value = parent.PROCESS_INFO.name5;
  }
</script>
</body>
</html>
