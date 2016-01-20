<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>ESB服务器添加页</title>
</head>

<body>
<form class="formui" id="esbServerForm">
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="serverInfo">
    <tr>
        <th>服务器名称</th>
        <td><input name="serverName" class="easyui-textbox" type="text" id="serverName" data-options="required:true"/></td>
    </tr>
    <tr>
        <th>服务器IP</th>
        <td><input name="serverIp" class="easyui-textbox" type="text" id="serverIp" data-options="required:true"/></td>
    </tr>
    <tr>
        <th>服务器用户</th>
        <td><input name="userName" class="easyui-textbox" type="text" id="userName" data-options="required:true"/>
        </td>
    </tr>
    <tr>
        <th>用户密码</th>
        <td><input name="userPsw" class="easyui-textbox" type="text" id="userPsw" data-options="required:true"/>
        </td>
    </tr>
    <tr>
        <th>in端路径</th>
        <td><input name="inConfigPath" class="easyui-textbox" type="text" id="inConfigPath" data-options="required:true"/>
        </td>
    </tr>
    <tr>
        <th>out端路径</th>
        <td><input name="outConfigPath" class="easyui-textbox" type="text" id="outConfigPath" data-options="required:true"/>
        </td>
    </tr>
    <tr>
        <th>备注</th>
        <td><input name="remark" class="easyui-textbox" type="text" id="remark" data-options="required:true"/>
        </td>
    </tr>
</table>
</form>
<div style="margin-top:10px;"></div>

<div align="center"><a class="easyui-linkbutton" id="saveBtn">保存</a>&nbsp;&nbsp;<a class="easyui-linkbutton" id="close"
                                                                                   onclick="$('#w').window('close');">关闭</a>
</div>

<script type="text/javascript" src="/plugin/validate.js"></script>

<script type="text/javascript">
    $(function () {
        $('#saveBtn').click(function () {
            if (!$("#esbServerForm").form('validate')) {
                return false;
            }
            var params = $("#esbServerForm").serialize();

            esbServerManager.saveAdd(params, function (result) {
                if (result) {
                    if (result) {
                        alert("保存成功");
                        $('#tt').datagrid('reload');
                    } else {
                        alert("保存失败");
                    }
                } else {
                    alert("保存失败");
                }
            });
            $('#w').window('close');

        });
    });


</script>
</body>
</html>
