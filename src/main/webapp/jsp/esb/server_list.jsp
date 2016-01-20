<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>ESB服务器管理</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<table id="tt" style="height:500px; width:auto;" title="所有ESB应用服务器"
       data-options="rownumbers:true,singleSelect:true,url:'/esbServer/getServerList',method:'get',toolbar:toolbar,pagination:true,
				pageSize:10">
    <thead>
    <tr>
        <th data-options="field:'serverId',checkbox:true"></th>
        <th field="serverName" width="130px" type="text" align="center">服务器名称</th>
        <th field="serverIp" width="130px" align="center">IP地址</th>
        <th field="userName" width="130px" align="center">用户</th>
        <th field="inConfigPath" width="130px" align="center">in端路径</th>
        <th field="outConfigPath" width="130px" align="center">out端路径</th>
        <th field="state" width="130px" align="center">状态</th>
        <th field="remark" width="130px" align="center">备注</th>

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
    var toolbar = [
        <shiro:hasRole name="admin">
        {
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                uiinit.win({
                    w: 400,
                    top:$(document).scrollTop() + ($(window).height()-250) * 0.5,
                    iconCls: 'icon-add',
                    title: "新增服务器",
                    url: "server_add.jsp"
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
                        w: 400,
                        iconCls: 'icon-edit',
                        title: "修改服务器",
                        url: "/esbServer/editPage?serverId=" + row.serverId
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
                    if (!confirm("确定要删除该生成类吗？")) {
                        return;
                    }
                    generatorManager.deleteById(row.id, function (result) {
                        if (result) {
                            alert("删除成功");
                            $('#tt').edatagrid('deleteRow', rowIndex);
                        } else {
                            alert("删除失败");
                        }
                    });
                } else {
                    alert("请选中要删除的数据！");
                }
            }
        },
        </shiro:hasRole>
    ];

    $(function () {
        $('#tt').edatagrid({
            autoSave: false,
            saveUrl: '/',
            updateUrl: '/',
            destroyUrl: '/'
        });
    });
    var esbServerManager = {
        "saveAdd": function (data, callBack) {
            $.ajax({
                type: "POST",
                url: "/esbServer/saveAdd",
                data: data,
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
        "saveEdit": function (data, callBack) {
            $.ajax({
                type: "POST",
                url: "/esbServer/saveEdit",
                data: data,
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
</script>

</body>
</html>
