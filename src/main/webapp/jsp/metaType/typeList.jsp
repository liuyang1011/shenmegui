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
<form id="typeListForm">
  <table  id="typeList" class="easyui-datagrid">
    <thead>
    <tr>
      <th data-options="field:'codeValue'" width="50%">编号</th>
      <th data-options="field:'codeMeaning'" width="50%">代码含义</th>
    </tr>
    </thead>
  </table>
</form>
<script type="text/javascript">
  $(function (){
    var name = Global.standardChName;
    $("#typeList").datagrid({
      url:encodeURI("/metaType/"+encodeURI(name)+"/getCodeEnum"),
      method:'get',
      onLoadError: function (responce) {
      }
    });
  });
</script>
</body>
</html>
