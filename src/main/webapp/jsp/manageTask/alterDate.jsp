<%--
  Created by IntelliJ IDEA.
  User: lmd
  Date: 2016/2/2
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <title></title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
</head>
<body>
<form class="formui">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>计划起始日期</th>
      <td> <input class="easyui-datebox"   type="text" id="startDate2"></td>
    </tr>
    <tr>
      <th>计划结束日期</th>
      <td> <input class="easyui-datebox"   type="text" id="endDate2"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td class="win-bbar">
        <a href="#" id="alterDateBtn" class="easyui-linkbutton"  iconCls="icon-save">确定</a>
        <a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="/js/task/taskManage.js"></script>
<script type="text/javascript">
  $("#alterDateBtn").click(function () {
    var data={};
    var date={};
    data.taskNum = Global.taskNum;
    data.workFlow = Global.workFlow;
    data.taskPoint =  Global.taskPoint;
    data.responsibility = Global.responsibility;
    data.taskDescribe = Global.taskDescribe;
    data.urgencyDegree = Global.urgencyDegree;
    data.status = Global.status;
    /*日期的处理*/
    date.startDate = $('#startDate2').datebox('getValue');
    date.endDate = $('#endDate2').datebox('getValue');
    if(date.startDate != ""){
      var arr = (date.startDate).split("/");
      if(arr[0].substring(0,1)==0){
        arr[0]=arr[0].substring(1,2)
      }
      if(arr[1].substring(0,1)==0){
        arr[1]=arr[1].substring(1,2)
      }
      data.startDate = arr[2]+"/"+arr[0]+"/"+arr[1];
    }else{
      data.startDate = Global.startDate;
    }

    if(date.endDate != ""){
      var arr = (date.endDate).split("/");
      if(arr[0].substring(0,1)==0){
        arr[0]=arr[0].substring(1,2)
      }
      if(arr[1].substring(0,1)==0){
        arr[1]=arr[1].substring(1,2)
      }
      data.endDate = arr[2]+"/"+arr[0]+"/"+arr[1];
    }else{
      data.endDate = Global.endDate;
    }
    taskManage.createTask(data,function(){
      alert("保存成功！");
      $("#w").window("close");
      $('#taskTab').datagrid('reload');
    });
  });
</script>
</body>
</html>
