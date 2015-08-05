<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
</head>

<body>
<fieldset>
    <legend>条件搜索</legend>
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>接口ID</th>
            <td><input class="easyui-textbox" type="text" name="interfaceId" id="interfaceId"></td>
            <th>接口名称</th>
            <td><input class="easyui-textbox" type="text" name="interfaceName" id="interfaceName"></td>
            <th>服务代码</th>
            <td><input class="easyui-textbox" type="text" name="serviceId" id="serviceId"></td>
            <th>服务名称</th>
            <td><input class="easyui-textbox" type="text" name="serviceName" id="serviceName"></td>
        </tr>
        <tr>
            <%--<th>交易属性标识</th>
            <td><input class="easyui-textbox" type="text" name="attribute" id="attribute"></td>
            <th>节点状态</th>
            <td><input class="easyui-textbox" type="text" name="status" id="status"></td>
            <th>版本号</th>
            <td><input class="easyui-textbox" type="text" name="status" id=""></td>--%>
            <th></th>
            <td align="right"><a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a></td>
        </tr>
    </table>


</fieldset>
<table title="交易节点" id="invokeLinkeTable" style="height:370px; width:auto;">
    <thead>
    <tr>
        <th data-options="field:'productid',checkbox:true"></th>
        <th data-options="field:'interfaceId'">交易码</th>
        <th data-options="field:'interfaceName'">交易名称</th>
        <th data-options="field:'serviceId'">服务码</th>
        <th data-options="field:'serviceName'">服务名称</th>
        <th data-options="field:'operationId'">场景码</th>
        <th data-options="field:'operationName'">场景名称</th>
        <th data-options="field:'invokeType'">调用类型</th>
        <th data-options="field:'nodeType'">节点类型</th>
        <th data-options="field:'status'">状态</th>
    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title="" data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;">

</div>
<script type="text/javascript">
    $(function (){
        $('#invokeLinkeTable').datagrid({
            rownumbers:true,
            singleSelect:true,
            url:'/serviceLink/getServiceLink/system/<%=request.getParameter("systemId") %>',
            method:'get',
            toolbar:toolbar,
            pagination:true,
            pageSize:10
        });
    });
    $("#search").click(function(){
        var queryParams = $('#invokeLinkeTable').datagrid('options').queryParams;
        queryParams.interfaceId = $("#interfaceId").textbox("getValue");
        queryParams.interfaceName = encodeURI($("#interfaceName").textbox("getValue"));
        queryParams.serviceId = $("#serviceId").textbox("getValue");
        queryParams.serviceName = $("#serviceName").textbox("getValue");
        if (queryParams.englishWord || queryParams.chineseWord || queryParams.esglisgAb || queryParams.remark) {
            $("#invokeLinkeTable").datagrid('options').queryParams = queryParams;//传递值
            $("#invokeLinkeTable").datagrid('reload');//重新加载table
//                categoryWordManager.query(param, function (result) {
//                    $('#tt').edatagrid('loadData', result);
//                });
        } else {
            $("#invokeLinkeTable").datagrid('reload');
            //alert("请输入查询条件");
        }
    });
    function selectTab(title, content) {
        var exsit = parent.$('#subtab').tabs('getTab', title);
        if (exsit == null) {
            parent.$('#subtab').tabs('add', {
                title: title,
                content: content
            });
        } else {
            parent.$('#subtab').tabs('update', {
                tab: exsit,
                options: {
                    content: content
                }
            });
            parent.$('#subtab').tabs('select',title);
        }
    }
    var toolbar = [

        {
            text: '预览',
            iconCls: 'icon-qxfp',
            handler: function () {
                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
                var checkedItem;
                if (checkedItems != null && checkedItems.length > 0) {
                    if (checkedItems.length > 1) {
                        alert("请选择一个节点进行预览！");
                        return false;
                    }
                    else {
                        checkedItem = checkedItems[0];
                        var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/previewLink.jsp?sourceId='+checkedItem.id+'" style="width:100%;height:100%;"></iframe>';
                        selectTab('预览', content);
                        selectTab('预览', content);
                    }
                }
                else {
                    alert("请选中要预览的节点！");
                }


            }
        },
        {
            text: '血缘分析',
            iconCls: 'icon-qxfp',
            handler: function () {
                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
                var checkedItem;
                if (checkedItems != null && checkedItems.length > 0) {
                    if (checkedItems.length > 1) {
                        alert("请选择一个节点进行预览！");
                        return false;
                    }
                    else {
                        checkedItem = checkedItems[0];
                        var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/parentLink.jsp?sourceId='+checkedItem.id+'" style="width:100%;height:100%;"></iframe>';
                        selectTab('血缘分析', content);
                        selectTab('血缘分析', content);
                    }
                }
                else {
                    alert("请选中要预览的节点！");
                }
            }
        }];
</script>

</body>
</html>