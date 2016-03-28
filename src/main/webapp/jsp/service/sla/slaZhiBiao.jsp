
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>列表页</title>
  <link rel="stylesheet" type="text/css"
        href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css"
        href="/resources/themes/icon.css">
  <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>

<table id="slaTemplate" style="height:370px; width:auto;" title="sla指标">
  <thead>
  <tr>
    <th field="slaId" width="100" editor="text" data-options="hidden:true"  >ID</th>
    <th field="slaName" width="150" editor="{type:'validatebox',options:{required:true,validType:['unique']}}" align="left">SLA指标</th>
    <th field="slaValue" width="150" align="left" editor="text">取值范围</th>
    <th field="slaDesc" width="400" align="left" editor="text">描 述</th>
    <th field="slaRemark" width="250" align="left" editor="text">备 注</th>
    <th field="slaTemplateId" width="100" editor="text" data-options="hidden:true"  >模板</th>
  </tr>
  </thead>
</table>

<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>
<script type="text/javascript"
        src="/assets/sla/js/slaManager.js"></script>
<script type="text/javascript" src="/assets/slaTemplate/js/slaTemplateManager.js"></script>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">



  var serviceId = "${param.serviceId}";
  var operationId = "${param.operationId}";
  var slatoolbar = [ {
    text : '新增',
    iconCls : 'icon-add',
    handler : function() {
      $('#slaTemplate').edatagrid('addRow');
    }
  }, {
    text : '删除',
    iconCls : 'icon-remove',
    handler : function() {
      var row = $('#slaTemplate').edatagrid('getSelected');
      if(row==""||row==null){
        alert("请选择一个指标！");
        return false;
      }
      if(confirm("确定要删除吗？一旦删除无法恢复！")){
        var rowIndex = $('#slaTemplate').edatagrid('getRowIndex', row);
        var deleteData = $("#slaTemplate").datagrid('getChanges','deleted');
        $('#slaTemplate').edatagrid('deleteRow', rowIndex);
        slaManager.deleteByEntity(deleteData,function(result){
          if(result){
            $('#slaTemplate').datagrid('reload');
            alert("删除成功！");
          }else{alert("删除失败！");}
        });
      }
    }
  }, {
    text : ' 保存',
    iconCls : 'icon-save',
    handler : function() {
      for ( var per in editedRows) {
        $("#slaTemplate").datagrid('endEdit', editedRows[per]);
        if(!$("#slaTemplate").datagrid('validateRow',editedRows[per])){
          alert("请以正确格式输入必输项");
          return false;
        }
      }
      var editData = $("#slaTemplate").datagrid('getChanges');
      var	slaTid="${param.slaTemplateId}"
      slaTemplateManager.addTemplate(editData,serviceId,operationId,slaTid,function(result){
        if(result){
          $('#slaTemplate').datagrid('reload');
          alert("保存成功！");
        }else{alert("保存失败！");}
      });
      editedRows = [];

    }
  },
    {
      text : '撤销',
      iconCls : 'icon-undo',
      handler : function() {
        $('#slaTemplate').datagrid('reload');
      }
    }
  ];

  var editedRows = [];
  var surl="/slaTemplate/getSLA/"+"${param.slaTemplateId}";
  $(function() {
    $('#slaTemplate').edatagrid({
      rownumbers:true,
      singleSelect:true,
      url:surl,
      method:'get',
      toolbar:slatoolbar,
      onBeginEdit : function(index,row){
        editedRows.push(index);
      }
    });
  });
  var slaTemplateId = "${param.slaTemplateId}";
  $(function () {
    $.extend($.fn.validatebox.defaults.rules, {
      unique: {
        validator: function (value, param) {
          var result;
          var node=$('#slaTemplate').edatagrid("getSelected");
          var nodeName=node.slaName;
          if(nodeName==value){
            return true;
          }
          $.ajax({
            type: "get",
            async: false,
            url: "/slaTemplate/uniqueValid2",
            dataType: "json",
            data: {"slaTemplate2": encodeURI(encodeURI(value)),"slaTemplateId":slaTemplateId},
            success: function (data) {
              result = data;
            }
          });
          return result;
        },
        message: '已存在sla指标'
      }
    });
  });


</script>
</body>
</html>
