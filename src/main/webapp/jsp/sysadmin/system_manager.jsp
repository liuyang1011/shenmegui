<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>系统管理</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/js/sysadmin/sysManager.js"></script>
</head>
<body>
<form id="searchForm">
<fieldset>
    <legend>
        条件搜索
    </legend>
    <table border="0" cellspacing="0" cellpadding="0" style="width:100%">
        <tr>
            <th width="100">
                系统编号
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="systemNo" width="100"/>
            </td>
            <th width="100">
                系统英文简称
            </th>
            <td width="100">
                <input class="easyui-textbox" type="text" id="systemAb" width="100"/>
            </td>
            <th width="100">
                系统中文名称
            </th>
            <td width="100">
                <input class="easyui-textbox" type="text" id="systemChineseName" width="100"/>
            </td>
            <th width="100">
                系统分类
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="systemClassify" width="100"/>
            </td>
        </tr>

        <tr>
            <th>
                系统负责人
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="systemPrincipal"/>
            </td>
            <td>
                &nbsp;
            </td>
            <td>
                <shiro:hasPermission name="system-get">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData();">搜索</a>
                    <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
                </shiro:hasPermission>
            </td>

            <td>
                &nbsp;
            </td>
            <td align="right">
                &nbsp;
            </td>
        </tr>
    </table>

</fieldset>
</form>
<table id="tg" style="width:100%">
    <thead>
    <tr>
        <th data-options="field:'systemNo',width:'10%'">
            系统编号
        </th>
        <th data-options="field:'systemAb',width:'12%'">
            系统英文简称
        </th>
        <th data-options="field:'systemChineseName',width:'15%'">
            系统中文名称
        </th>
        <th data-options="field:'systemClassify',width:'10%',align:'center'">
            系统分类
        </th>
        <th data-options="field:'systemPrincipal',width:'10%'">
            系统负责人
        </th>
        <th data-options="field:'principalTel',align:'left',width:'10%'">
            负责人联系方式
        </th>
        <th data-options="field:'createUser',width:'10%',align:'left'">
            创建用户
        </th>
        <th data-options="field:'updateDate',width:'15%',align:'left'">
            更新时间
        </th>
        <th data-options="field:'updateUser',width:'10%',align:'left'">
            更新用户
        </th>
    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width: 500px; height: 200px; padding: 10px;">

</div>
<script type="text/javascript">
    var toolbar = [];
    <shiro:hasPermission name="system-add">
    toolbar.push({
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            sysManager.addSystemPage();
        }
    });
    </shiro:hasPermission>
    <shiro:hasPermission name="system-update">
    toolbar.push({
        text: '修改',
        iconCls: 'icon-edit',
        handler: function () {
            var node = $('#tg').datagrid("getSelected");
            if (node) {
                uiinit.win({
                    w: 500,
                    iconCls: 'icon-add',
                    title: "修改系统",
                    url: "/system/edit/" + node.systemId
                });
            } else {
                alert("请选择要修改的行");
            }
        }
    });
    </shiro:hasPermission>
    <shiro:hasPermission name="system-delete">
    toolbar.push({
        text: '删除',
        iconCls: 'icon-remove',
        handler: function () {
            var node = $('#tg').datagrid("getSelected");
            if (node) {
                if (!confirm("确定要删除选中的记录吗？")) {
                    return;
                }
                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    //如果systemId包含#等特殊符号就会有问题
//                            url: "/system/delete/" + node.systemId,
                    url: "/system/delete2",
                    dataType: "json",
                    data: JSON.stringify(node.systemId),
                    success: function (result) {
                        if(result){
                            $('#tg').datagrid("reload");
                            parent.parent.$('.msinterfacetree').tree('reload');
                        }else{
                            alert("系统有下挂接口，无法删除");
                        }
                    },
                    complete: function (responce) {
                        var resText = responce.responseText;
                        if(resText.toString().indexOf("没有操作权限") > 0){
                            alert("没有权限！");
                            //window.location.href = "/jsp/403.jsp";
                        }
                    }
                });
            } else {
                alert("请选择要删除的行");
            }
        }
    });
    </shiro:hasPermission>
    toolbar.push({
        text: 'SLA协议',
        iconCls: 'icon-qxfp',
        handler: function () {
            var node = $('#tg').datagrid("getSelected");
            if(node==null){
                alert("请选择系统!");
                return;
            }
                var content = '<iframe scrolling="auto" frameborder="0"  src="jsp/systemSLA/slaPage.jsp?systemNo='+node.systemNo+'&systemId='+node.systemId+'" style="width:100%;height:100%;"></iframe>';
                parent.$('#sysContentTabs').tabs('add', {
                    title: "SLA协议",
                    content: content,
                    closable: true
                });
        }
    });

    $(document).ready(function () {
        $('#tg').datagrid({
            title: '系统基本信息列表',
            iconCls: 'icon-edit',//图标
            width: '100%',
            height: '500px',
            collapsible: true,
            method: 'post',
            url: '/system/getAll',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            pageSize: 15,//每页显示的记录条数，默认为10
            pageList: [15, 20, 30],//可以设置每页记录条数的列表
            rownumbers: true,//行号
            toolbar: toolbar,
            onLoadError: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
//                    alert("没有权限！");
                    window.location.href = "/jsp/403.jsp";
                }
            }
        });

        var p = $('#tg').datagrid('getPager');

        $(p).pagination({

            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        });

    })

    function searchData() {
        var systemNo = $("#systemNo").val();
        var systemAb = $("#systemAb").val();
        var systemChineseName = $("#systemChineseName").val();
        var systemPrincipal = $("#systemPrincipal").val();
        var systemClassify = $("#systemClassify").val();
        var queryParams = $('#tg').datagrid('options').queryParams;
        queryParams.systemNo = systemNo;
        queryParams.systemChineseName = encodeURI(systemChineseName);
        queryParams.systemAb = systemAb;
        queryParams.systemPrincipal = systemPrincipal;
        queryParams.systemClassify = systemClassify;
        $('#tg').datagrid('options').queryParams = queryParams;//传递值
        $("#tg").datagrid('reload');//重新加载table
    }
</script>

</body>
</html>