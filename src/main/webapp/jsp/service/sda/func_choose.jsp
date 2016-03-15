<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>字段处理管理</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="searchForm">
    <div class="win-bbar" style="text-align:center"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
                                                       onClick="javascript:$('#attrDlg').dialog('close');">取消</a><a href="javascript:void(0)" id="saveBtn"
                                                                                                                onclick="chooseFunc()"
                                                                                                                class="easyui-linkbutton"
                                                                                                                iconCls="icon-save">确定</a></div>
    <fieldset>
        <legend>条件搜索</legend>
        <table border="0" cellspacing="0" cellpadding="0" heigth="auto">
            <tr>
                <th><nobr> 中文名称</nobr></th>
                <td><input class="easyui-textbox" style="width:100px" type="text" name="name" id="name"></td>
                <th><nobr> 名称</nobr></th>
                <td><input class="easyui-textbox" style="width:100px" type="text" name="funcName" id="funcName"></td>
                <th><nobr> 说明</nobr></th>
                <td><input class="easyui-textbox" style="width:100px" type="text" name="des" id="des"></td>
                <td align="right">
                    <shiro:hasPermission name="metadata-get">
                        <a href="#" onclick="query()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
                        <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<table id="funcList" style="width:auto;" title="所有字段处理" class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:true,url:'/attFunction/query',method:'get',pagination:true,fitColumns:'false',
				pageSize:10">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <%--<th field="type" width="130px" type="text" align="center"  formatter='formatter.type'>类型</th>--%>
        <th field="name" width="130px" type="text" align="center">中文名称</th>
        <th field="funcName" width="130px" align="center">名称</th>
        <th field="paramNames" width="130px" align="center">参数</th>
        <th field="des" width="130px" align="center">说明</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    function query(){
        var params = {
            "name" : encodeURI($("#name").textbox("getValue")),
            "funcName" : $("#funcName").textbox("getValue"),
            "des" : $("#des").textbox("getValue")
        };
        $("#funcList").datagrid('options').queryParams = params;
        $("#funcList").datagrid('reload');
    }
    var formatter = {
        type: function (value, row, index) {
            if (value == 0) {
                return "<font color='green'>固定值</font>";
            }
            if (value == 1) {
                return "<font color='green'>表达式</font>";
            }
        }
    };
    function chooseFunc(){
        var row = $("#funcList").datagrid("getSelected");
        if(row){
            $("#attrName").textbox("setValue", "expression");
            //拼接表达式值
            $("#attrValue").textbox("setValue", row.params);
            $("#attrDlg").dialog("close");
        }else{
            alert("没有选中数据!");
        }
    }
</script>

</body>
</html>
