<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/js/serviceIdentify/taskManagers.js"></script>
</head>

<body>
<form class="formui" id="metadataForm" action="/metadata/add" method="post">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>处理人</th>
            <td><input style="width:173px;" name="user" id="user" ></td>
        </tr>
        <tr>
            <th>任务描述</th>
            <td><input class="easyui-textbox" type="text" style="width: 100%" id="description"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td class="win-bbar">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a href="#" id="saveTask" onclick="saveTask()"  class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
    <script type="text/javascript" src="/js/serviceIdentify/addTask.js"></script>
    <script type="text/javascript">
        function saveTask(){
            alert("sssss");
            alert("sssss");
            var taskType = "inter";
            var user =$('#user').combobox('getValue');
            var description = $("#description").val();
            var task = {};
            task.userId = user;
            task.taskType = taskType;
            task.interfaceId=<%=request.getParameter("interfaceId")%>;
            task.serviceId=<%=request.getParameter("serviceId")%>;
            task.operationId=<%=request.getParameter("operationId")%>;
            var params = {};
            params.commentInput = description;
            params.userId = user;
            params.approved = false;
            taskManagers.createTask(task,params,function(){
                alert("任务已经提交！");
                $("#w").window("close");
                $('#taskTable').datagrid('reload');
            });
        }
    </script>
</body>
</html>