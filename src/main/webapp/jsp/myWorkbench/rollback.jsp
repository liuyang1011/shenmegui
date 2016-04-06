<%--
  Created by IntelliJ IDEA.
  User: lmd
  Date: 2016/2/19
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <title></title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/resources/js/ui.js"></script>
</head>
<body>
<div id="userId" style="display: none"><shiro:principal/></div>
  <form class="formui" id="rollbackForm">
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th>回退意见</th>
        <td><textarea name="message" style="height:60px;" id="rollbackMessage"></textarea></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td class="win-bbar">
          <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
          <a href="#" id="rollbackTaskBtn" class="easyui-linkbutton" iconCls="icon-save">回退</a>
        </td>
      </tr>
    </table>
  </form>
<script type="text/javascript" src="/assets/task/taskManager.js"></script>
<script type="text/javascript">
  $(function () {
    $("#rollbackTaskBtn").click(function () {
      var task = {};
//      task.userId = $("#userId").text();
      task.taskId = parent.PROCESS_INFO.taskId;
      task.processInstanceId = parent.PROCESS_INFO.processInstanceId;
      task.name = parent.PROCESS_INFO.name;
      task.rollbackOpinion = $("#rollbackMessage").val();
      taskManager.rollBackTask(task,function(result){
        if(result){
          alert("回退成功！");
          $("#w").window("close");
          $('#taskTable').datagrid('reload');
        }else{
          alert("回退失败，已回退到最初节点！");
        }
      });
    });
  })
</script>
</body>
</html>