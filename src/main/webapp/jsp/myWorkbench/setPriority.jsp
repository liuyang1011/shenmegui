<%--
  Created by IntelliJ IDEA.
  User: lmd
  Date: 2016/2/19
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <base href="<%=basePath%>">
  <title></title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
</head>

<body>

<form class="formui" id="metadataForm" action="/metadata/add" method="post">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>优先级</th>
      <td><select class="easyui-combobox" style="width:173px;" name="user"  id="priority">
        <option value="0">低</option>
        <option value="1">中</option>
        <option value="2">高</option>
      </select>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td class="win-bbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="setPriorityBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="/assets/task/taskManager.js"></script>
<script type="text/javascript">
  $(function () {
    $("#setPriorityBtn").click(function () {
      var task = {};
      task.priority = $("#priority").combobox('getValue');
      task.taskId = parent.PROCESS_INFO.taskId;
      taskManager.setPriority(task,function(){
        alert("优先级已修改！");
        $("#w").window("close");
        $('#taskTable').datagrid('reload');
      });
    });
  })
</script>
</body>
</html>
