<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="utf-8" %>
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
    <input type="hidden" id="categoryId" value="${param.categoryId}">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>菜单名称</th>
            <td><input class="easyui-textbox" type="text" id="menuName" style="width:200px"
                       data-options="required:true"></td>
        </tr>
        <tr>
            <th>上级菜单</th>
            <td>
                <%=URLDecoder.decode(request.getParameter("categoryName"), "utf-8")%>
            </td>
        </tr>
        <tr>
            <th>类型</th>
            <td><input class="easyui-combobox" style="width:200px" id="menuType"
                       type="text" id="type"
                       value="category"
                       data-options="
                       required:true,
                     textField:'text',
                     valueField:'id',
                     data:[
                        {'id':'category','text':'导航菜单'},
                        {'id':'menu','text':'功能按钮'},
                     ],
                     onChange:function(newValue, oldValue){
							this.value=newValue;
							if(newValue == '1'){
							}
							else{
							}
				        }
                     "
                    ></td>
        </tr>
        <tr>
            <th>权限</th>
            <td><input id="permission" class='easyui-textbox' type='text' id='permission' style='width:200px'>&nbsp;例：user-get</td>
        </tr>
        <tr>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#opDialog').dialog('close')">取消</a>
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
    var categoryId = $("#categoryId").val();
    var menuName = $("#menuName").val();
    var menuType = $("#menuType").combobox("getValue");
    var permission = $("#permission").val();
    if(undefined != permission && ""!= permission){
        if(permission.indexOf("-") < 0){
            alert("权限格式不正确！");
            return false;
        }
    }
    $.ajax({
        type: "post",
        async: false,
        url: "/menu/saveAdd",
        dataType: "json",
        data:{
            "categoryId":categoryId,
            "menuName":menuName,
            "menuType":menuType,
            "permission":permission
        },
        success: function (result) {
            alert("操作成功!");
            $('#opDialog').dialog('close')
            var treeObj = $('#resultList');
            var selectNode = treeObj.treegrid("getSelected");
            treeObj.treegrid("reload");
//            treeObj.treegrid('append', {
//                parent: (selectNode?selectNode.target:null),
//                data: [{
//                    text: 'new item1'
//                }]
//            });
//            var urlPath = treeObj.treegrid('options').url;
//            treeObj.treegrid('options').url = "'/menu/menuTree?t='" + new Date().getTime();
//            treeObj.treegrid("reload", selectNode.id);
//            treeObj.treegrid('options').url = urlPath;
        }

    });
}
</script>

</body>
</html>



