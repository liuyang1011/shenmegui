<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
  <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">

  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/resources/js/ui.js"></script>
  <script type="text/javascript" src="/assets/task/taskManager.js"></script>
  <script type="text/javascript">
    var userID = "";
    $(function () {
      userID = $("#userId").text();
      var url = '/process/' + userID + '/areadyList';
      $("#taskTable").datagrid({
        rownumbers: true,
        border: false,
        toolbar: toolbar,
        singleSelect: false,
        url: url,
        method: 'get',
        pagination: false,
        loadFilter:function(data){
          var newDate = new Date();
          var newDate2 = new Date();
          for(var i=0;i<data.length;i++){
            newDate.setTime(data[i].activationTime);
            newDate2.setTime(data[i].createdOn);
            data[i].activationTime = newDate.toLocaleString();
            data[i].createdOn = newDate2.toLocaleString();
          }
          return {total:data.length,rows:data};
        }
      });
    });
    var taskFormatter = {
      "formatStatus": function (data, row) {
        if (data) {
          if (data == "Reserved") {
            return "待分派";
          }
          if (data == "Ready") {
            return "已分派";
          }
          if (data == "Completed") {
            return "已完成";
          }
          if (data == "Obsolete") {
            return "已删除";
          }
        }
      },
      "formatPriority": function (data, row) {
        if (data != undefined) {
          if ("0" == data) {
            return "低";
          }
          if ("1" == data) {
            return "中";
          }
          if ("2" == data) {
            return "高";
          }
        }
      },
      "formatCreateBy": function (data, row) {
        if (row) {
          if (row.createdBy)
            return row.createdBy.id;
        }
      },
      "formatActualOwner": function (data, row) {
        if (data) {
          return data.id;
        }
      }
    }
  </script>
</head>

<body>
<div id="userId" style="display: none"><shiro:principal/></div>
<div class="easyui-tabs" id="taskTabs" style="width:100%;height:auto">
  <div title="已办事项" style="padding:0px">
    <table id="taskTable" style="height:530px; width:auto;">
      <thead>
      <tr>
        <th data-options="field:'productid',checkbox:true"></th>
        <th data-options="field:'id'">任务ID</th>
        <th data-options="field:'name'">任务名称</th>
        <th data-options="field:'subject'">主题</th>
        <th data-options="field:'description'">描述</th>
        <th data-options="field:'status'" formatter="taskFormatter.formatStatus">状态</th>
        <th data-options="field:'priority'" formatter="taskFormatter.formatPriority">优先级</th>
        <th data-options="field:'createBy'" formatter="taskFormatter.formatCreateBy">创建人</th>
        <th data-options="field:'actualOwner'" formatter="taskFormatter.formatActualOwner">受理人</th>
        <th data-options="field:'createdOn'" >创建时间</th>
        <th data-options="field:'activationTime'">接收时间</th>
      </tr>
      </thead>
    </table>
  </div>
  <%--<div title="任务详细" style="padding:0px">--%>
  <%--<table id="taskInfoTable" style="height:620px; width:auto;">--%>
  <%--<thead>--%>
  <%--<tr>--%>
  <%--<th data-options="field:'productid',checkbox:true"></th>--%>
  <%--<th data-options="field:'id'">任务ID</th>--%>
  <%--<th data-options="field:'name'">任务名称</th>--%>
  <%--<th data-options="field:'subject'">主题</th>--%>
  <%--<th data-options="field:'description'">描述</th>--%>
  <%--<th data-options="field:'status'" formatter="taskFormatter.formatStatus">状态</th>--%>
  <%--<th data-options="field:'priority'" formatter="taskFormatter.formatPriority">优先级</th>--%>
  <%--<th data-options="field:'createBy'" formatter="taskFormatter.formatCreateBy">创建人</th>--%>
  <%--<th data-options="field:'actualOwner'" formatter="taskFormatter.formatActualOwner">受理人</th>--%>
  <%--</tr>--%>
  <%--</thead>--%>
  <%--</table>--%>
  <%--</div>--%>

  <script type="text/javascript">
    var Global = {};
    var toolbar = [
      {
        text:'<input class="easyui-textbox" type="text" style="width: 100%" id="search"/>',
      },
      {
        iconCls:"icon-search",
        text:'搜索',
        handler:function(){
          var condition=$("#search").val();
          if(condition==null||condition==""){
            alert("请输入搜素条件!");
            return;
          }
          $.ajax({
            type: "POST",
            url: "/process/findByCondition/"+userID,
            data: "condition="+condition,
            success: function(data){
              $('#taskTable').datagrid("loadData",data);
            }
          });
        }
      },
      {
        text: '查看详情',
        iconCls: 'icon-search',
        handler: function () {
          var checkedItems = $('#taskTable').datagrid('getChecked');
          var checkedItem;
          if (checkedItems != null && checkedItems.length > 0) {
            if (checkedItems.length > 1) {
              alert("请选择一个任务！");
              return false;
            }
            else {
              checkedItem = checkedItems[0];
              Global.actualOwner = checkedItem.actualOwner.id;
              Global.taskId = checkedItem.id;
              Global.processInstanceId = checkedItem.processInstanceId;
              Global.taskName = checkedItem.name;
              var task = {};
              task.processInstanceId = Global.processInstanceId;
              task.taskId = Global.taskId;
              task.userId = $("#userId").text();
              task.name = Global.taskName;
              task.name = task.name.replace(/(^\s*)|(\s$)/g, '');
              parent.PROCESS_INFO.processId = checkedItem.processInstanceId;
              parent.PROCESS_INFO.taskName = task.name;
              parent.PROCESS_INFO.taskId = task.taskId;
              var taskInfoTab = $('#taskTabs').tabs('getTab', "任务详细");
              var name=checkedItem.name;
              var urlData = checkedItem.processInstanceId+"&actualOwner="+checkedItem.actualOwner.id+"&taskName="+name;
              var url = '<iframe scrolling="auto" frameborder="0"  src="/jsp/myWorkbench/taskInfo.jsp?processId='+urlData+'" style="width:100%;height:600px;"></iframe>';
              if (taskInfoTab) {
                $('#taskTabs').tabs("update", {
                  tab: taskInfoTab,
                  options: {
                    content: url
                  }
                });
              }else{
                $('#taskTabs').tabs("add", {
                  title: "任务详细",
                  content: url
                });
              }
              $("#taskTabs").tabs("select", "任务详细");
            }
          }
          else {
            alert("请选中要修改的数据！");
          }

        }
      }
    ];
  </script>
</body>
</html>