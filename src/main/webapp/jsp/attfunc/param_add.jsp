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

<form class="formui" id="addForm" action="/attFunction/save" method="post">
    <input type="hidden" name="funcId" value="${param.funcId}" />
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>参数名称</th>
            <td><input name="name" class="easyui-textbox" type="text" id="name" required="true"/>
            </td>
        </tr>
        <tr>
            <th>默认值</th>
            <td><input name="defaultValue" class="easyui-textbox" type="text" id="funcName" required="true"/>
            </td>
        </tr>
        <tr>
            <th>序号</th>
            <td><input name="seq" class="easyui-textbox" type="text" id="des" required="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a href="#" onclick="saveParam()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
    function saveParam(){
        if(!$("#addForm").form('validate')){
            return false;
        }
        var params = $("#addForm").serialize();
        params = decodeURIComponent(params, true);
        $.ajax({
            type: "post",
            async: false,
            url: "/attFuncParam/save",
            dataType: "json",
            data: params,
            success: function(data){
                //关闭窗口
                $("#w").window("close");
                $('#tt').datagrid('reload');
                //刷新查询列表
            },
            complete:function(responce){
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    }
</script>

</body>
</html>
