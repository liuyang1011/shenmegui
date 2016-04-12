<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
  <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="searchForm">
  <fieldset>
    <legend>条件搜索</legend>
    <table border="0" cellspacing="0" cellpadding="0" heigth="auto">
      <tr>
        <th><nobr>元数据名称</nobr></th>
        <td><input class="easyui-textbox" type="text" style="width:100px" name="name" id="name"></td>
        <th><nobr>中文名称</nobr></th>
        <td><input class="easyui-textbox" type="text" style="width:100px" name="chineseName" id="chineseName"></td>
        <th><nobr> 创建人</nobr></th>
        <td><input class="easyui-textbox" style="width:100px" type="text" name="optUser" id="optUser"></td>
        <th></th>
        <td></td>
        <th></th>
        <td></td>
      </tr>
      <tr>
        <th><nobr> 创建起始日期</nobr></th>
        <td><input class="easyui-datebox" style="width:100px" type="text" name="startDate" id="startDate"></td>
        <th><nobr> 创建结束日期</nobr></th>
        <td><input class="easyui-datebox" style="width:100px" type="text" name="endDate" id="endDate"></td>
        <th></th>
        <td></td>
        <td align="right">
          <shiro:hasPermission name="metaType-get">
            <a href="#" id="queryOrigDataBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
          </shiro:hasPermission>
        </td>
      </tr>
    </table>
  </fieldset>
</form>
<table id="originalDataList" title="元数据管理"
       style="height:620px; width:98%;">
  <thead>
  <tr>
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'name'" width="10%">元数据名称</th>
    <th data-options="field:'chineseName'" width="10%">中文名称</th>
    <th data-options="field:'classify'" width="6%">一级分类</th>
    <th data-options="field:'type'" width="7%">类型</th>
    <th data-options="field:'enAllName'" width="13%">ESB标准英文全称</th>
    <th data-options="field:'standardChName'" width="10%">标准中文名称</th>
    <th data-options="field:'standardEnName'" width="13%">标准英文名称</th>
    <th data-options="field:'optUser'" width="6%">操作用户</th>
    <th data-options="field:'optDate'" width="13%">操作时间</th>
  </tr>
  </thead>
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<div id="codeEnumList" class="easyui-dialog"
     style="width:200px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<scirpt type="text/javascript" src="/resources/js/jquery.fileupload.js"></scirpt>
<script type="text/javascript" src="/resources/js/jquery.fileDownload.js"></script>
<script type="text/javascript">
  var Global = {};
  var codeData;
  $(function(){
    $("#originalDataList").datagrid({
      rownumbers:true,
      singleSelect:false,
      url:'/metaType/query',
      method:'get',
      toolbar:toolbar,
      pagination:true,
      pageSize:20,
      pageList: [20,30,50],
      fitColumns:'false',
      onDblClickCell:onDblClickCell,
      onLoadError: function (responce) {
        var resText = responce.responseText;
        if(resText.toString().indexOf("没有操作权限") > 0){
//                    alert("没有权限！");
          window.location.href = "/jsp/403.jsp";
        }
      }
    });

    $("#queryOrigDataBtn").click(function(){
      var queryParams = $('#originalDataList').datagrid('options').queryParams;
      queryParams.name = $("#name").textbox("getValue");
      queryParams.chineseName = encodeURI($("#chineseName").textbox("getValue"));
      queryParams.optUser = encodeURI($("#optUser").textbox("getValue"));
      queryParams.startDate = $("#startDate").datebox("getValue");
      queryParams.endDate = $("#endDate").datebox("getValue");
      if(queryParams.name||queryParams.chineseName||queryParams.optUser||queryParams.startDate||queryParams.endDate){
        $("#originalDataList").datagrid('options').queryParams = queryParams;
        $("#originalDataList").datagrid('reload');
      }else{
        alert("请输入查询条件！");
        $("#originalDataList").datagrid('reload');
      }
    });

  });

  var toolbar = [];
  <shiro:hasPermission name="metaType-add">
  toolbar.push({
    text: '新增',
    iconCls: 'icon-add',
    handler: function () {
      uiinit.win({
        top:"20px",
        left:"150px",
        w: 500,
        iconCls: 'icon-add',
        title: "新增元数据",
        url: "/jsp/metaType/add.jsp"
      });
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="metaType-edit">
  toolbar.push({
    text: '修改',
    iconCls: 'icon-edit',
    handler: function () {
      var checkedItems = $('#originalDataList').datagrid('getChecked');
      var checkedItem;
      if(checkedItems != null && checkedItems.length > 0) {
        if (checkedItems.length > 1) {
          alert("请选择一条数据进行修改！")
        } else{
        checkedItem = checkedItems[0];
        Global.id = checkedItem.id;
        uiinit.win({
          top: "20px",
          left: "150px",
          w: 500,
          iconCls: 'icon-edit',
          title: "修改元数据   ",
          url: "/metaType/edit?id=" + checkedItem.id
        });
      }
      }else{
        alert("没有选中项！");
      }
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="metaType-delete">
  toolbar.push({
    text: '删除',
    iconCls: 'icon-remove',
    handler: function () {
      var checkedItems = $('#originalDataList').datagrid('getChecked');
      var checkedItem;
      if(checkedItems != null && checkedItems.length > 0){
          if (confirm("确定要删除已选中的" + checkedItems.length + "项吗？一旦删除无法恢复！")) {
            var ids = [];
            $.each(checkedItems, function(index, item) {
              ids.push(item.id);
            });
            $.ajax({
              type: "post",
              async: false,
              url: "/metaType/deletes",
              dataType: "json",
              data:{"ids":ids.join(",")},
              success: function (result) {
                if (result) {
                  alert("操作成功!");
                  $('#originalDataList').datagrid('reload');
                } else {
                  alert("删除失败!");
                }
              }
            });
          }
      }else{
        alert("没有选中项！");
      }
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="metaType-get">
  toolbar.push({
    text:'查看代码型详细',
    iconCls:'icon-search',
    handler:function(){
      var checkedItems = $('#originalDataList').datagrid('getChecked');
      var checkedItem;
      if (checkedItems != null && checkedItems.length > 0) {
        if(checkedItems.length > 1){
          alert("只能同时查看一条数据！");
        }else{
          checkedItem = checkedItems[0];
          var type = checkedItem.type;
          Global.standardChName = checkedItem.standardChName;
          if(type != '代码型'){
            alert("只能查看类型是“代码型”的数据！");
          }else{
            uiinit.win({
              w: 400,
              h: 260,
              title: "查看代码型详细",
              url: "/jsp/metaType/typeList.jsp"
            });
          }
        }
      }else{
        alert("没有选中项");
      }
    }
  });
  </shiro:hasPermission>
  <shiro:hasPermission name="metaType-exportExcel">
  toolbar.push({
    text:'导出EXCEL',
    iconCls:'icon-excel-export',
    handler:function(){
      var form = $("<form>");//定义一个form表单
      form.attr("style", "display:none");
      form.attr("target", "");
      form.attr("method", "post");
      form.attr("action", "/metaType/exportExcel");
      $("body").append(form);//将表单放置在web中
      form.submit();//表单提交
    }
  });
  </shiro:hasPermission>
  //双击单元格
  function onDblClickCell(rowIndex, field,value){
    if(value=="代码型"){
//      getCodeEnum(rowIndex);
    }else{
      var texts = '<div style="word-wrap:break-word" >'+value+'</div>';
      $.messager.show({
        title:'详细',
        msg:texts,
        showType:'show',
        height:'auto'
      });
    }
  }
  function getCodeEnum(rowIndex){
    var row = $('#originalDataList').datagrid('getData').rows[rowIndex];
    var name = row.standardChName;
    $.ajax({
      type: "get",
      async: false,
      url: encodeURI("/metaType/"+encodeURI(name)+"/getCodeEnum"),
      dataType:'text',
      scriptCharset:'utf-8',
      success: function (result) {
        if(null != result){
          codeData = decodeURI(result);
//          $.messager.alert('详细信息',codeData,'info');
          $.messager.show({
            title:name+'——代码型枚举值列表',
            msg:codeData,
            showType:'slide',
          });
        }
      }
    });
  }
</script>
</body>
</html>
