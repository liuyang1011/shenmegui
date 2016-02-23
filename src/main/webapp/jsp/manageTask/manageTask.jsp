<%--
  Created by IntelliJ IDEA.
  User: lmd
  Date: 2016/1/27
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>列表页</title>
  <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<fieldset>
  <legend>条件搜索</legend>
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>工作流程</th>
      <td><input type="text" name="workFlow" id="workFlow"
                 class="easyui-combobox"
                 data-options="
						url:'workFlow.json',
				 		 method:'get',
				 		 valueField: 'id',
				 		 textField: 'id',
				 		 onChange:function(newValue, oldValue){
							this.value=newValue;
						}
					"
              /></td>
      <th>任务节点</th>
      <td><input type="text" name="taskType" id="taskType"
                 class="easyui-combobox"
                 data-options="
						url:'taskType.json',
				 		 method:'get',
				 		 valueField: 'id',
				 		 textField: 'id',
				 		 onChange:function(newValue, oldValue){
							this.value=newValue;
						}
					"
              /></td>
      <th>紧急程度</th>
      <td><select class="easyui-combobox" panelHeight="auto" style="width:130px" id="urgencyDegree">
      <option value="低">低</option>
      <option value="中">中</option>
      <option value="高">高</option>
    </select></td>
    </tr>
    <tr>
      <th>计划起始日期</th>
      <td> <input class="easyui-datebox"   type="text" name="name" id="startDate"></td>
      <th>计划结束日期</th>
      <td> <input class="easyui-datebox"  type="text" name="name" id="endDate"></td>
      <th>责任人</th>
      <td> <input class="easyui-textbox" type="text" name="user" id="user" style="width:130px"></td>
      <td align="right"><a href="#" class="easyui-linkbutton"  iconCls="icon-search" id="search">搜索任务</a></td>
    </tr>
  </table>
</fieldset>
<table class="easyui-datagrid" title="任务管理" id="taskTab" style="height:363px; width:auto;">
  <thead>
  <tr>
    <th data-options="field:'task',checkbox:true"> </th>
    <th field="taskNum" width="130px"  align="center">任务序号 </th>
    <th field="workFlow" width="130px"  align="center">工作流程 </th>
    <th field="taskPoint" width="130px"  align="center">任务节点 </th>
    <th field="responsibility" width="130px"  align="center">责任人 </th>
    <th field="urgencyDegree" width="130px"  align="center" >紧急程度 </th>
    <th field="taskDescribe" width="130px"  align="center">任务描述 </th>
    <th field="status" width="130px"  align="center" > 状态 </th>
    <th field="startDate" width="130px"  align="center" > 计划起始日期 </th>
    <th field="endDate" width="130px"  align="center">计划结束日期 </th>
  </tr>
  </thead>
</table>
<div id="w" class="easyui-window" title="" data-options="modal:true,closed:true,iconCls:'icon-add'" style="width:500px;height:200px;padding:10px;">
</div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/js/task/taskManage.js"></script>
<script type="text/javascript">
  var Global = {};
  var toolbar = [{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){

      uiinit.win({
        w:500,
        iconCls:'icon-add',
        title:"新增任务",
        url : "/jsp/manageTask/taskAdd.jsp"
      });
    }
  },{
    text:'关闭',
    iconCls:'icon-remove',
    handler:function(){
      var row = $('#taskTab').edatagrid('getSelected');
      var rowIndex = $('#taskTab').edatagrid('getRowIndex', row);
      var checkedItems = $('#taskTab').edatagrid('getChecked');
      if (checkedItems != null && checkedItems.length > 0) {
        if(window.confirm('被关闭的任务无法恢复，你确定要关闭任务吗？')){
          /*在数据库中确实删除该任务*/
//          taskManage.deleteById(row.taskNum, function (result) {
//            if (result) {
//              alert("关闭成功");
//              $('#taskTab').edatagrid('deleteRow', rowIndex);
//            } else {
//              alert("关闭失败");
//            }
//          });
          /*只改变任务状态，并未在数据库中删除*/
          var data = {};
          data.taskNum = row.taskNum;
          data.workFlow = row.workFlow;
          data.taskPoint = row.taskPoint;
          data.responsibility = row.responsibility;
          data.urgencyDegree = row.urgencyDegree;
          data.taskDescribe = row.taskDescribe;
          data.startDate = row.startDate;
          data.endDate = row.endDate;
          data.status = "强制关闭"
          taskManage.createTask(data, function(result) {
            if (result) {
              alert("关闭成功");
              $('#taskTab').datagrid('reload');
            } else {
              alert("关闭失败");
            }
          });
        }
      } else {
        alert("请选中要关闭的任务！");
      }
    }
  },
    {
      text:'挂起',
      iconCls:'icon-guaqi',
      handler:function(){
        var row = $('#taskTab').edatagrid('getSelected');
        var checkedItems = $('#taskTab').edatagrid('getChecked');
        if (checkedItems != null && checkedItems.length > 0) {
          if(row.status=="已完成"){
            alert("对不起，无法挂起已完成的任务!");
          }else{
            var data = {};
            data.taskNum = row.taskNum;
            data.workFlow = row.workFlow;
            data.taskPoint = row.taskPoint;
            data.responsibility = row.responsibility;
            data.urgencyDegree = row.urgencyDegree;
            data.taskDescribe = row.taskDescribe;
            data.startDate = row.startDate;
            data.endDate = row.endDate;
            data.status = "挂起"
            taskManage.createTask(data, function(result) {
              if (result) {
                alert("挂起成功");
                $('#taskTab').datagrid('reload');
              } else {
                alert("挂起失败");
              }
            });
          }
        }else {
          alert("请选中要挂起的任务！");
        }
      }
    },
    {
      text:'重分配',
      iconCls:'icon-cfp',
      handler:function(){
        var row = $('#taskTab').edatagrid('getSelected');
        var checkedItems = $('#taskTab').edatagrid('getChecked');
        if (checkedItems != null && checkedItems.length > 0) {
          if (checkedItems.length > 1) {
            alert("请选择一个任务进行分配！");
            return false;
          }
          else {
            Global.taskNum = row.taskNum;
            Global.workFlow = row.workFlow;
            Global.taskPoint = row.taskPoint;
            Global.urgencyDegree = row.urgencyDegree;
            Global.taskDescribe = row.taskDescribe;
            Global.startDate = row.startDate;
            Global.endDate = row.endDate;
            Global.status = row.status;
            uiinit.win({
              w: 500,
              iconCls: 'icon-edit',
              title: "重分配",
              url: "/jsp/manageTask/redistribution.jsp"
            });
          }
        }
        else {
          alert("请选中要重分配的数据！");
        }
      }
    },
//    {
//      text:'任务操作权限',
//      iconCls:'icon-qxfp',
//      handler:function(){
//        var row = $('#taskTab').edatagrid('getSelected');
//        var checkedItems = $('#taskTab').edatagrid('getChecked');
//        if (checkedItems != null && checkedItems.length > 0) {
//          if (checkedItems.length > 1) {
//            alert("请选择一个任务！");
//            return false;
//          }
//          else {
//            Global.taskNum = row.taskNum;
//            Global.workFlow = row.workFlow;
//            Global.taskPoint = row.taskPoint;
//            Global.responsibility = row.responsibility;
//            Global.urgencyDegree = row.urgencyDegree;
//            Global.taskDescribe = row.taskDescribe;
//            Global.startDate = row.startDate;
//            Global.endDate = row.endDate;
//            Global.status = row.status;
//            uiinit.win({
//              w:500,
//              iconCls:'icon-qxfp',
//              title:"任务操作权限",
//              url : "/jsp/manageTask/limitsOfRole.jsp"
//            });
//          }
//        }
//        else {
//          alert("请选中要操作权限的任务！");
//        }
//      }
//    },
    {
      text : '修改日期',
      iconCls : 'icon-edit',
      handler : function() {
        var row = $('#taskTab').edatagrid('getSelected');
        var checkedItems = $('#taskTab').edatagrid('getChecked');
        if (checkedItems != null && checkedItems.length > 0) {
          Global.taskNum = row.taskNum;
          Global.workFlow = row.workFlow;
          Global.taskPoint = row.taskPoint;
          Global.responsibility = row.responsibility;
          Global.urgencyDegree = row.urgencyDegree;
          Global.taskDescribe = row.taskDescribe;
          Global.startDate = row.startDate;
          Global.endDate = row.endDate;
          Global.status = row.status;
          uiinit.win({
            w: 370,
            iconCls: 'icon-edit',
            title: "修改日期",
            url: "alterDate.jsp"
          })
        }else {
          alert("请选中要修改日期的任务！");
        }
      }
    }
     ];
  $(function () {
    /*从数据库中拿出所有数据*/
    $('#taskTab').datagrid({
      rownumbers: true,
      singleSelect: true,
      url: '/task/getAll',
      method: 'get',
      toolbar: toolbar,
      pagination: true,
      pageSize: 10,
      onLoadError: function (responce) {
        var resText = responce.responseText;
        if (resText.toString().indexOf("没有操作权限") > 0) {
          window.location.href = "/jsp/403.jsp";
        }
      }
    });
    /*获取所有责任人*/
    $('#user').combobox({
      url: '/user/getAllUser',
      method: 'get',
      mode: 'remote',
      valueField: 'id',
      textField: 'id'
    });
    /*查询功能*/
    $('#search').click(function () {
      var workFlow=$('#workFlow').val();
      var taskPoint= $('#taskType').combobox("getValue");
      var urgencyDegree= $('#urgencyDegree').combobox("getValue");
      var startDate= $('#startDate').datebox("getValue");
      var endDate= $('#endDate').datebox("getValue");
      var responsibility= $('#user').combobox("getValue");
      if(workFlow=="" && taskPoint=="" && urgencyDegree=="" && responsibility==""
          && startDate=="" && endDate=="")
      {window.location.reload();}
      else{
        var param = {};
        param.workFlow = workFlow;
        param.taskPoint = taskPoint;
        param.urgencyDegree = urgencyDegree;
        /*对日期的处理*/
        if(startDate != ""){
          var arr = startDate.split("/");
          if(arr[0].substring(0,1)==0){
            arr[0]=arr[0].substring(1,2)
          }
          if(arr[1].substring(0,1)==0){
            arr[1]=arr[1].substring(1,2)
          }
          param.startDate = arr[2]+"/"+arr[0]+"/"+arr[1];
        }
        if(endDate != ""){
          var arr = endDate.split("/");
          if(arr[0].substring(0,1)==0){
            arr[0]=arr[0].substring(1,2)
          }
          if(arr[1].substring(0,1)==0){
            arr[1]=arr[1].substring(1,2)
          }
          param.endDate = arr[2]+"/"+arr[0]+"/"+arr[1];
        }
        param.responsibility = responsibility;
        taskManage.query(param, function(result){
          $('#taskTab').edatagrid('loadData', result);
        });
      }
    });

  });
</script>
</body>
</html>