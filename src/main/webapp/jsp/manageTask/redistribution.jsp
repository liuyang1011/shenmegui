<%@ page language="java" pageEncoding="utf-8" %>
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
      <th align="right">责任人</th>
      <td><select class="easyui-combobox" style="width:173px;" name="select" id="userAssign" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td class="win-bbar">
        <a href="#" id="assignTaskBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="/js/task/taskManage.js"></script>
<script type="text/javascript">
  var href = window.location.href;
  var params = href.split("&");
  $(function () {
    $('#userAssign').combobox({
      url:'/user/getAllUser',
      method:'get',
      mode:'remote',
      valueField:'id',
      textField:'name'
    });
    $("#assignTaskBtn").click(function () {
      /*获取*/
      var task = {};
      task.taskNum = Global.taskNum;
      task.workFlow = Global.workFlow;
      task.taskPoint = Global.taskPoint;
      task.urgencyDegree = Global.urgencyDegree;
      task.taskDescribe = Global.taskDescribe;
      task.startDate = Global.startDate;
      task.endDate = Global.endDate;
      task.status = Global.status;
      task.responsibility = $('#userAssign').combobox('getValue');
//      task.userId = $("#userId").text();
      taskManage.createTask(task, function(result) {
        if (result) {
          alert("重分配成功");
          $("#w").window("close");
          $('#taskTab').datagrid('reload');
        } else {
          alert("重分配失败");
        }
      });
    });
  });
</script>
</body>
</html>