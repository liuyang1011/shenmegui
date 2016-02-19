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

<form class="formui" id="form" action="/metadata/add" method="post">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>菜单名称</th>
            <td><input class="easyui-textbox" type="text" id="name" style="width:200px"
                       data-options="required:true"></td>
        </tr>
        <tr>
            <th>类型</th>
            <td><input class="easyui-combobox" style="width:200px"
                       type="text" id="type"
                       data-options="
                       required:true,
                     textField:'text',
                     valueField:'id',
                     data:[
                        {'id':'0','text':'导航菜单'},
                        {'id':'1','text':'功能按钮'},
                     ],
                     onChange:function(newValue, oldValue){
							this.value=newValue;
							if(newValue == '1'){
							    $('#permissionTr').css('display','block');
							}
							else{
							$('#permissionTr').css('display','none');
							}
				        }
                     "
                    ></td>
        </tr>
        <tr id="permissionTr" style="display: none">
            <th>权限</th>
            <td><input class="easyui-textbox" type="text" id="permission" style="width:200px"></td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a href="#" onclick="saveAdd()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
function saveAdd(){
    if (!$("#form").form('validate')) {
        return false;
    }
    $.ajax({
        type: "post",
        async: false,
        url: "/menu/saveAdd",
        dataType: "json",
        success: function (result) {
            $('#resultList').treegrid('append',{
                parent: node.id,
                data: result
            });
        }

    });
}
</script>

</body>
</html>



