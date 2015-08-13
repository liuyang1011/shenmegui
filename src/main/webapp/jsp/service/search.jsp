<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
  <link rel="stylesheet" type="text/css"
        href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
</head>

<body>
<form id="searchForm">
<fieldset>
  <legend>条件搜索</legend>
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>服务代码</th>
      <td><input name="serviceId" id="serviceId"  class="easyui-combobox" style="width:100px"
                 data-options="
                 method:'get',
                 url:'/service/getAll',
                 textField:'serviceId',
                 valueField:'serviceId',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
							$('#serviceName').combobox('setValue',newValue);
				    }
                 "
                  >
      </td>
      <th>服务名称</th>
      <td><input name="serviceName" id="serviceName"  class="easyui-combobox" style="width:100px"
                 data-options="
                 method:'get',
                 url:'/service/getAll',
                 textField:'serviceName',
                 valueField:'serviceId',
                 onChange:function(newValue, oldValue){
							$('#serviceId').combobox('setValue',newValue);
				    }
                 "
              >
      </td>
      <th>服务功能描述</th>
      <td><input class="easyui-textbox" style="width:100px"
                 type="text" name="desc" id="serviceDesc">
      </td>
      <th>
        服务状态
      </th>
      <td><input class="easyui-combobox" style="width:100px"
                 type="text" name="serviceState" id="serviceState"></td>
    </tr>
    <tr>
      <th>场景代码</th>
      <td><input name="operationId" id="operationId"  class="easyui-combobox" style="width:100px"
                 data-options="
                 method:'get',
                 url:'/operation/getServiseById/1',
                 textField:'operationId',
                 valueField:'operationId',
                 onChange:function(newValue, oldValue){
                         this.value=newValue;
							$('#operationName').combobox('setValue',newValue);
				    }
                 "
              >
      </td>
      <th>场景名称</th>
      <td>
        <input name="operationName" id="operationName"  class="easyui-combobox" style="width:100px"
               data-options="
                 method:'get',
                 url:'/operation/getServiseById/1',
                 textField:'operationName',
                 valueField:'operationId',
                 onChange:function(newValue, oldValue){
							$('#operationId').combobox('setValue',newValue);
				    }
                 "
                >
      </td>
      <th>功能描述</th>
      <td><input class="easyui-textbox" style="width:100px"
                 type="text" name="operationDesc" id="operationDesc">
      </td>
      <th>场景状态</th>
      <td><input class="easyui-combobox" style="width:100px"
                 type="text" name="operationState" id="operationState">
      </td>
      </tr>
    <tr>
      <th>系统编号</th>
      <td><input id="systemId" name="systemId" class="easyui-textbox" style="width:100px" readonly=" true"
                 type="text" >
      </td>
      <th>服务类型</th>
      <td>
        <select id="systemType" class="easyui-combobox" name="type" readonly=" true"
                data-options="width:100,valueField:'value', textField:'text',data:[
                {'value':'1','text':'消费者'},
                {'value':'0','text':'提供者'}
                ]">
        </select>
      </td>
      <th>

      </th>
      <td></td>
      <th>

      </th>
      <td></td>
      <th style="width:200px">
        <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-search" style="margin-left:1em" >清空</a>
        <a href="#" id="saveTagBtn" onclick="query()" class="easyui-linkbutton" iconCls="icon-search" style="margin-left:1em" >查询</a>
      </th>
      <td></td>

    </tr>
  </table>


</fieldset>
</form>
<div style="width:100%">
<table id="resultList" class="easyui-datagrid"
       data-options="
			rownumbers:true,
			singleSelect:false,
			fitColumns:false,
			url:'/operation/query',
			method:'get',toolbar:toolbar,
			pagination:true,
				pageSize:10"
       style="height:370px; width:100%;">
  <thead>
  <tr>
    <th data-options="field:'',checkbox:true,width:50"></th>
    <th data-options="field:'serviceId',width:80">服务代码</th>
    <th data-options="field:'serviceName',width:100">服务名称</th>
    <th data-options="field:'operationId',width:80">场景代码</th>
    <th data-options="field:'operationName',width:100">场景名称</th>
    <th data-options="field:'operationDesc',width:150">功能描述</th>
    <th data-options="field:'consumers',width:150">消费者</th>
    <th data-options="field:'providers',width:150">提供者</th>
    <th data-options="field:'version', width:80" >版本号</th>
    <th data-options="field:'optDate',width:100">更新时间</th>
    <th data-options="field:'optUser', width:50">更新用户</th>
    <th data-options="field:'optState',width:50">状态</th>
  </tr>
  </thead>
</table>
</div>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<div id="opDialog" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
</body>
<script type="text/javascript">
  var formatter = {
    operationState: function (value, row, index) {
      if (value == 0) {
        return "<font color=''>待审核</font>";
      }
      if (value == 1) {
        return "<font color='green'>审核通过</font>";
      }
      if (value == 2) {
        return "<font color='red'>审核未通过</font>";
      }
    },
    version: function (value, row, index) {
      try {
        return row.version.code
      } catch (exception) {
      }
    }
  };
  var toolbar = [
    {
      text:'导出EXCEL',
      iconCls:'icon-excel-export',
      handler: function () {
        var checkedItems = $('#resultList').datagrid('getChecked');
        if (checkedItems != null && checkedItems.length > 0) {
          var form=$("<form>");//定义一个form表单
          form.attr("style","display:none");
          form.attr("target","");
          form.attr("method","post");
          form.attr("action","/excelExporter/exportOperation");

          for(var i=0; i < checkedItems.length; i++){
            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","pks["+i+"].serviceId");
            input1.attr("value",checkedItems[i].serviceId);
            var input2=$("<input>");
            input2.attr("type","hidden");
            input2.attr("name","pks["+i+"].operationId");
            input2.attr("value",checkedItems[i].operationId);

            form.append(input1);
            form.append(input2);
          }

          $("body").append(form);//将表单放置在web中
          form.submit();//表单提交
        }
        else{
          alert("没有选中数据！");
        }
      }
    },
    {
      text:'导出PDF',
      iconCls:'icon-excel-export',
      handler: function () {
        var checkedItems = $('#resultList').datagrid('getChecked');
        if (checkedItems != null && checkedItems.length > 0) {
          var form=$("<form>");//定义一个form表单
          form.attr("style","display:none");
          form.attr("target","");
          form.attr("method","post");
          form.attr("action","/pdfExporter/exportOperation");

          for(var i=0; i < checkedItems.length; i++){
            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","pks["+i+"].serviceId");
            input1.attr("value",checkedItems[i].serviceId);
            var input2=$("<input>");
            input2.attr("type","hidden");
            input2.attr("name","pks["+i+"].operationId");
            input2.attr("value",checkedItems[i].operationId);

            form.append(input1);
            form.append(input2);
          }

          $("body").append(form);//将表单放置在web中
          form.submit();//表单提交
        }
        else{
          alert("没有选中数据！");
        }
      }
    },
    {
      text:'详细信息',
      iconCls:'icon-excel-export',
      handler: function () {
        var checkedItems = $('#resultList').datagrid('getChecked');
        if (checkedItems != null && checkedItems.length == 1) {
          $('#opDialog').dialog({
            title: '详细信息',
            width: 700,
            closed: false,
            cache: false,
            href: '/operation/detailPage?serviceId=' +  checkedItems[0].serviceId + '&operationId=' + checkedItems[0].operationId,
            modal: true
          });
        }
        else{
          alert("请选中一行数据！");
        }
      }
    }
  ];
  function query(){
    var params = {
      "serviceId":$("#serviceId").combobox("getValue"),
     // "serviceName":$("#serviceName").textbox("getValue"),
      "serviceDesc":$("#serviceDesc").textbox("getValue"),
      "serviceState":$("#serviceState").combobox("getValue"),

      "systemId":$("#systemId").textbox("getValue"),
      "systemType":$("#systemType").textbox("getValue"),
      "serviceId":$("#serviceId").textbox("getValue"),

      "operationId":$("#operationId").textbox("getValue"),
     // "operationName":$("#operationName").textbox("getValue"),
      "operationDesc":$("#operationDesc").textbox("getValue"),
      "operationState":$("#operationState").combobox("getValue")
    }
    $("#resultList").datagrid('options').queryParams = params;
    $("#resultList").datagrid('reload');
  }

  function exportFile(url, values){
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    form.attr("action",url);
    for(var i=0; i < values.length; i++){
      var input1=$("<input>");
      input1.attr("type","hidden");
      input1.attr("name",values[i].name);
      input1.attr("value",values[i].value);
      form.append(input1);
    }

    $("body").append(form);//将表单放置在web中
    form.submit();//表单提交
  }
</script>

</body>
</html>

