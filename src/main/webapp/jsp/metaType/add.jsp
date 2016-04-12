<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>My JSP 'MyJsp.jsp' starting page</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->

</head>
<body>
<form class="formui" id="originalDataForm">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>元数据名称</th>
      <td><input class="easyui-textbox" type="text" id="originalData2" data-options="required:true,validType:['uniqueValName','chineseB']"></td>
    </tr>
    <tr>
      <th>中文名称</th>
      <td><input class="easyui-textbox" type="text"  id="chineseName2" data-options="required:true,validType:['uniqueValChName','chineseB']"></td>
    </tr>
      <tr>
          <th>一级分类</th>
          <td><input class="easyui-textbox" type="text" id="classify2"></td>
      </tr>
      <tr>
          <th>标签</th>
          <td><input class="easyui-textbox" type="text"  id="label2"></td>
      </tr>
      <tr>
          <th>ESB标准英文全称</th>
          <td><input class="easyui-textbox" type="text"  id="enAllName2"></td>
      </tr>
    <tr>
      <th>标准中文名称</th>
      <td><input class="easyui-textbox" type="text"  id="standardChName2"></td>
    </tr>
      <tr>
          <th>标准英文名称</th>
          <td><input class="easyui-textbox" type="text"  id="standardEnName2"></td>
      </tr>
      <tr>
          <th>业务含义</th>
          <td><input class="easyui-textbox" type="text"  id="meaning2"></td>
      </tr>
      <tr>
          <th>数据类型</th>
          <td><input class="easyui-textbox" type="text"  id="type2"></td>
      </tr>
      <tr>
          <th>数据格式</th>
          <td><input class="easyui-textbox" type="text"  id="format2"></td>
      </tr>
      <tr>
          <th>取值范围</th>
          <td><input class="easyui-textbox" type="text"  id="valueRange2"></td>
      </tr>
      <tr>
          <th>度量单位</th>
          <td><input class="easyui-textbox" type="text"  id="measurement2"></td>
      </tr>
    <tr>
      <td colspan="2" style="text-align:center">
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="originalDataSaveBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
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
                        data: {"originalData": value},
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
    $("#originalDataSaveBtn").click(function () {
        var params = {};
        params.chineseName = $("#chineseName2").textbox("getValue");
        params.name = $("#originalData2").textbox("getValue");
        params.classify = $("#classify2").textbox("getValue");
        params.label = $("#label2").textbox("getValue");
        params.enAllName = $("#enAllName2").textbox("getValue");
        params.standardChName = $("#standardChName2").textbox("getValue");
        params.standardEnName = $("#standardEnName2").textbox("getValue");
        params.meaning = $("#meaning2").textbox("getValue");
        params.type = $("#type2").textbox("getValue");
        params.format = $("#format2").textbox("getValue");
        params.valueRange = $("#valueRange2").textbox("getValue");
        params.measurement = $("#measurement2").textbox("getValue");

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
</script>
</body>
</html>
