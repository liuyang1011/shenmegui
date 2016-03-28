

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
  String systemNo=request.getParameter("systemNo");
  String systemId=request.getParameter("systemId");

%>

<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
  <link rel="stylesheet" type="text/css"href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript"src="/resources/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/resources/js/ui.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
  <script type="text/javascript" src="/plugin/json/json2.js"></script>
  <script type="text/javascript" src="/assets/sla/js/slaManager.js"></script>
  <script type="text/javascript" src="/plugin/validate.js"></script>
</head>

<table id="slaTemplateTable" title="SLA模板" style="height:620px; width:100%;">
  <thead>
  <tr>
    <th data-options="field:'productid',checkbox:true"></th>
    <th field="slaTemplateId" width="100" editor="text" id="idText"
        data-options="hidden:true" >ID</th>
    <th field="templateNo" width="100" editor="{type:'validatebox',options:{required:true,validType:['unique']}}" align="left">模版编号</th>
    <th field="templateName" width="100" editor="{type:'validatebox',options:{required:true,validType:['uniquea']}}" align="left">模版名称</th>
    <th field="desc" width="150" align="left" editor="text">模板描述</th>
    <th field="updateTime" width="150" align="left" editor="text">更新时间</th>
    <th field="updateUser" width="150" align="left" editor="text">更新用户</th>
    <th field="creatUser" width="150" align="left" editor="text">创建用户</th>


  </tr>
  </thead>
</table>
<div id="win"></div>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/assets/slaTemplate/js/slaTemplateManager.js"></script>
<script type="text/javascript" src="/assets/sla/js/slaManager.js"></script>
<script type="text/javascript">




  var slaTemplatetoolbar = [];
  <shiro:hasPermission name="slaTemp-add">
  slaTemplatetoolbar.push({
    text : '新增',
    iconCls : 'icon-add',
    handler : function() {
      $('#slaTemplateTable').edatagrid('addRow');
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="slaTemp-update">
  slaTemplatetoolbar.push({
    text : '编辑',
    iconCls : 'icon-edit',
    handler : function() {
      var row = $('#slaTemplateTable').edatagrid('getSelected');
      if(row==""||row==null){
        alert("请选择一个模板！");
        return false;
      }
      var row = $('#slaTemplateTable').edatagrid("getSelected");
      var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/service/sla/slaZhiBiao.jsp?slaTemplateId='+row.slaTemplateId+'&templateName='+row.templateName+'&serviceId='+row.serviceId+'&operationId='+row.operationId+'" style="width:100%;height:100%;"></iframe>';
      var title = "["+row.templateName+"]指标维护";
      parent.addTab(title, content);
    }
  });
  </shiro:hasPermission>


  <shiro:hasPermission name="slaTemp-delete">
  slaTemplatetoolbar.push({
    text : '删除',
    iconCls : 'icon-remove',
    handler : function() {
      var row = $('#slaTemplateTable').edatagrid('getSelected');
      if(row==""||row==null){
        alert("请选择一个模板！");
        return false;
      }
      if(confirm("确定要删除吗？一旦删除无法恢复！")){
        var rowIndex = $('#slaTemplateTable').datagrid('getRowIndex', row);
        var deleteData = $("#slaTemplateTable").datagrid('getChanges','deleted');
        $('#slaTemplateTable').edatagrid('deleteRow', rowIndex);
        slaTemplateManager.deleteByEntity(deleteData,function(result){
          if(result){
            $('#slaTemplateTable').datagrid('reload');
            alert("删除成功！");
          }else{alert("删除失败！");}
        });
      }
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="slaTemp-update">
  slaTemplatetoolbar.push({
    text : ' 保存',
    iconCls : 'icon-save',
    handler : function() {
      for ( var per in editedRows) {
        $("#slaTemplateTable").datagrid('endEdit', editedRows[per]);
        if(!$("#slaTemplateTable").datagrid('validateRow',editedRows[per])){
          alert("请以正确格式输入必输项");
          return false;
        }
      }
      var editData = $("#slaTemplateTable").datagrid('getChanges');
      for(var i=0;i<editData.length;i++){
        if(editData[i].creatUser=="") {
          editData[i].creatUser = "<shiro:principal/>";
          editData[i].updateUser = "<shiro:principal/>";
        }else{
          editData[i].updateUser = "<shiro:principal/>";
        }
      }
      slaTemplateManager.add(editData,function(result){
        if(result){
          $('#slaTemplateTable').datagrid('reload');
          alert("保存成功！");
        }else{alert("保存失败！");}
      });
      editedRows = [];
    }
  });



  slaTemplatetoolbar.push({
    text : '撤销',
    iconCls : 'icon-undo',
    handler : function() {
      $('#slaTemplateTable').datagrid('reload');
    }
  });


  </shiro:hasPermission>
  slaTemplatetoolbar.push({
    text : 'SLA指标',
    iconCls : 'icon-qxfp',
    handler : function() {
      var row = $('#slaTemplateTable').edatagrid("getSelected");
      if(row==""||row==null){
        alert("请选择一个模板！");
        return false;
      }
      $('#win').window({
        width:600,
        height:300,
        iconCls : 'icon-add',
        title : "["+row.templateName+"]SLA指标",
        content:'<iframe scrolling="auto" frameborder="0"  src="/jsp/systemSLA/slaTemplate.jsp?slaTemplateId='+row.slaTemplateId+'" style="width:100%;height:100%;"></iframe>',
      });
    }
  });


  var editedRows = [];
  $(function() {

    $('#slaTemplateTable').edatagrid({
      rownumbers:true,
      singleSelect:true,
      url:"/slaTemplate/getAll/",
      method:'get',
      toolbar:slaTemplatetoolbar,
      pagination: true,
      pageSize: 20,
      pageList: [20,30,50],
      onBeginEdit : function(index,row){
        editedRows.push(index);
      }
    });



  });
  function saveSLATemplate(slaTemplateId,systemId){
    var systemId='1';
    var url = "/systemSla/setTemplateData/"+ slaTemplateId+"/"+systemId;
    $.ajax({
      "type" : "GET",
      "contentType" : "application/json; charset=utf-8",
      "url" : url,
      "dataType" : "json",
      "success" : function(result) {
      }
    });

    return true;
  }

  $(function () {
    $.extend($.fn.validatebox.defaults.rules, {
      unique: {
        validator: function (value, param) {
          var result;
          var node=$('#slaTemplateTable').edatagrid("getSelected");
          var nodeName=node.templateNo;
          if(nodeName==value){
            return true;
          }
          $.ajax({
            type: "get",
            async: false,
            url: "/slaTemplate/uniqueValid1",
            dataType: "json",
            data: {"slaTemplate": value},
            success: function (data) {
              result = data;
            }
          });
          return result;
        },
        message: '已存在相同模板编号'
      }
    });
  });
  $(function () {
    $.extend($.fn.validatebox.defaults.rules, {
      uniquea: {
        validator: function (value, param) {
          var result;
          var node=$('#slaTemplateTable').edatagrid("getSelected");
          var nodeName=node.templateName;
          if(nodeName==value){
            return true;
          }
          $.ajax({
            type: "get",
            async: false,
            url: "/slaTemplate/uniqueValid3",
            dataType: "json",
            data: {"slaTemplate": encodeURI(encodeURI(value))},
            success: function (data) {
              result = data;
            }
          });
          return result;
        },
        message: '已存在相同模板名称'
      }
    });
  });






</script>


</body>
</html>

