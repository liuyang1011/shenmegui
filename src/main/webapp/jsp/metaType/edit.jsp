<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>My JSP 'MyJsp.jsp' starting page</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->

</head>
<body>
<form class="formui" id="originalDataEditForm">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>元数据名称</th>
      <td><input class="easyui-textbox" type="text" id="nameE" value="${metaType.name}" data-options="required:true,validType:['uniqueValName','chineseB']"></td>
    </tr>
    <tr>
      <th>中文名称</th>
      <td><input class="easyui-textbox" type="text"  id="chineseNameE" value="${metaType.chineseName}" data-options="required:true,validType:['uniqueValChName','chineseB']"></td>
    </tr>
    <th>一级分类</th>
    <td><input class="easyui-textbox" type="text" id="classifyE" value="${metaType.classify}"></td>
    </tr>
    <tr>
      <th>标签</th>
      <td><input class="easyui-textbox" type="text"  id="labelE" value="${metaType.label}"></td>
    </tr>
    <tr>
      <th>ESB标准英文全称</th>
      <td><input class="easyui-textbox" type="text"  id="enAllNameE" value="${metaType.enAllName}"></td>
    </tr>
    <tr>
      <th>标准中文名称</th>
      <td><input class="easyui-textbox" type="text"  id="standardChNameE" value="${metaType.standardChName}"></td>
    </tr>
    <tr>
      <th>标准英文名称</th>
      <td><input class="easyui-textbox" type="text"  id="standardEnNameE" value="${metaType.standardEnName}"></td>
    </tr>
    <tr>
      <th>业务含义</th>
      <td><input class="easyui-textbox" type="text"  id="meaningE" value="${metaType.meaning}"></td>
    </tr>
    <tr>
      <th>数据类型</th>
      <td><input class="easyui-textbox" type="text"  id="typeE" value="${metaType.type}"></td>
    </tr>
    <tr>
      <th>数据格式</th>
      <td><input class="easyui-textbox" type="text"  id="formatE" value="${metaType.format}"></td>
    </tr>
    <tr>
      <th>取值范围</th>
      <td><input class="easyui-textbox" type="text"  id="valueRangeE" value="${metaType.valueRange}"></td>
    </tr>
    <tr>
      <th>度量单位</th>
      <td><input class="easyui-textbox" type="text"  id="measurementE" value="${metaType.measurement}"></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align:center">
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="originalDataEditSaveBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
  $(function () {
    $.extend($.fn.validatebox.defaults.rules, {
      uniqueValName: {
        validator: function (value, param) {
          var result;
          $.ajax({
            type: "get",
            async: false,
            url: "/metaType/uniqueValName",
            dataType: "json",
            data: {"metaType": value},
            success: function (data) {
              result = data;
            }
          });
          return result;
        },
        message: '已存在相同元数据英文'
      },
      uniqueValChName: {
        validator: function (value, param) {
          var result;
          $.ajax({
            type: "get",
            async: false,
            url: "/metaType/uniqueValChName",
            dataType: "json",
            data: {"chineseName": encodeURI(value)},
            success: function (data) {
              result = data;
            }
          });
          return result;
        },
        message: '已存在相同元数据中文'
      }
    });
  });
</script>
<script type="text/javascript">
  $(function () {
    $("#originalDataEditSaveBtn").click(function () {
      var params ={};
      params.id = Global.id;
      params.chineseName = $("#chineseNameE").textbox("getValue");
      params.name = $("#nameE").textbox("getValue");
      params.classify = $("#classifyE").textbox("getValue");
      params.label = $("#labelE").textbox("getValue");
      params.enAllName = $("#enAllNameE").textbox("getValue");
      params.standardChName = $("#standardChNameE").textbox("getValue");
      params.standardEnName = $("#standardEnNameE").textbox("getValue");
      params.meaning = $("#meaningE").textbox("getValue");
      params.type = $("#typeE").textbox("getValue");
      params.format = $("#formatE").textbox("getValue");
      params.valueRange = $("#valueRangeE").textbox("getValue");
      params.measurement = $("#measurementE").textbox("getValue");
      $.ajax({
        "type": "POST",
        "contentType": "application/json;charset=utf-8",
        "url": "/metaType/add",
        "data": JSON.stringify(params),
        "dataType": "json",
        "success": function (result) {
          if(result){
            $("#w").window("close");
            $('#originalDataList').datagrid('reload');
          }
        },
        "complete": function (responce) {
          var resText = responce.responseText;
          if(resText.toString().indexOf("没有操作权限") > 0){
            alert("没有权限！");
            //window.location.href = "/jsp/403.jsp";
          }
        }
      });
    });
  });
</script>
</body>
</html>