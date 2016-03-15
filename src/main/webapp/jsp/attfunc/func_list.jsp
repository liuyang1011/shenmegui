<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>字段处理管理方法</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="searchForm">
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
<table id="tt" style="height:500px; width:auto;" title="字段处理方法"
       data-options="rownumbers:true,singleSelect:true,url:'/attFunction/query',method:'get',toolbar:toolbar,pagination:true,fitColumns:'false',
				pageSize:10">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <%--<th field="type" width="130px" type="text" align="center"  formatter='formatter.type'>类型</th>--%>
        <th field="name" width="130px" type="text" align="center">中文名称</th>
        <th field="funcName" width="130px" align="center">名称</th>
        <th field="paramNames" width="130px" align="center">参数</th>
        <th field="des" width="130px" align="center">说明</th>
        <th field=" " width="230px" align="center"  formatter="operationCell">操作</th>
    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>

<script type="text/javascript">
    var operationCell = function(value, row){
        var s = '<a onclick="addParam(\'' + row.id +'\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[添加参数]</span></span></a>' +
                '<a onclick="params(\'' + row.id +'\')" class="easyui-linkbutton" href="javascript:void(0)" >' +
                '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">[修改参数]</span></span></a>'
        return s;
    }
    var toolbar = [
        <shiro:hasRole name="admin">
        {
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                uiinit.win({
                    w: 400,
                    iconCls: 'icon-add',
                    title: "新增字段处理",
                    url: "func_add.jsp"
                })
            }
        },
        {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var row = $('#tt').edatagrid('getSelected');
                var checkedItems = $('#tt').edatagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    uiinit.win({
                        w: 600,
                        iconCls: 'icon-edit',
                        title: "修改字段处理方法",
                        url: "/attFunction/editPage?id=" + row.id
                    })
                } else {
                    alert("请选中要修改的数据！");
                }
            }
        },
        {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var row = $('#tt').edatagrid('getSelected');
                var rowIndex = $('#tt').edatagrid('getRowIndex', row);
                var checkedItems = $('#tt').edatagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    $.messager.confirm('确认', '确定删除吗？', function (r) {
                                if (r) {
                                    $.ajax({
                                        type: "post",
                                        async: false,
                                        contentType: "application/json; charset=utf-8",
                                        url: "/attFunction/deletes",
                                        dataType: "json",
                                        data: JSON.stringify(checkedItems),
                                        success: function (data) {
                                            if (data) {
                                                alert("操作成功");
                                                $("#tt").datagrid("reload");
                                            } else {
                                                alert("操作失败！");
                                            }
                                        }
                                    });
                                }
                            }
                    );
                } else {
                    alert("请选中要删除的数据！");
                }
            }
        }
        </shiro:hasRole>
    ];

    function query(){
        var params = {
            "name" : encodeURI($("#name").textbox("getValue")),
            "funcName" : $("#funcName").textbox("getValue"),
            "des" : $("#des").textbox("getValue")
        };
        $("#tt").datagrid('options').queryParams = params;
        $("#tt").datagrid('reload');
    }

    $(function () {
        $('#tt').edatagrid({
            autoSave: false,
            saveUrl: '/',
            updateUrl: '/',
            destroyUrl: '/'
        });
    });

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

    var manager = {
        "add": function (data, callBack) {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/attFunction/save",
                data: JSON.stringify(data),
                dataType: "json",
                success: function (result) {
                    callBack(result);
                },
                complete: function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
                        alert("没有权限！");
                        //window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        },
        "getAll": function (callBack) {
            $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: "/user/getAll",
                data: JSON.stringify(),
                dataType: "json",
                success: function (result) {
                    callBack(result);
                }
            });
        },
        "deleteById": function (id, callBack) {
            $.ajax({
                "type": "DELETE",
                "contentType": "application/json; charset=utf-8",
                "url": "/generator/delete/" + id,
                "dataType": "json",
                "success": function (result) {
                    callBack(result);
                },
                "complete": function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
                        alert("没有权限！");
                        //window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        },
        "checkExit": function (implementsClazz, callBack) {
            $.ajax({
                type: "post",
//        contentType: "application/json; charset=utf-8",
                url: "/generator/checkClassExit",
                dataType: "json",
                data: {"implementsClazz": implementsClazz},
                success: function (result) {
                    callBack(result);
                }
            });
        },
        "modify": function (data, callBack) {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/generator/modify",
                data: JSON.stringify(data),
                dataType: "json",
                success: function (result) {
                    callBack(result);
                },
                complete: function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
                        alert("没有权限！");
                        //window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        }
    };
    function addParam(id){
        uiinit.win({
            w: 500,
            iconCls: 'icon-save',
            title: "添加参数",
            url: "/jsp/attfunc/param_add.jsp?funcId="+id
        })
    }
    function params(id){
        uiinit.win({
            w: 600,
            iconCls: 'icon-save',
            title: "参数列表",
            url: "/jsp/attfunc/func_param_list.jsp?funcId="+id
        })
    }
</script>

</body>
</html>
