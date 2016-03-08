<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/assets/serviceLink/serviceLinkManager.js"></script>
</head>

<body>
<fieldset>
    <legend>条件搜索</legend>
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <%--<th>接口ID</th>--%>
            <th><nobr>交易码</nobr></th>
            <td><input class="easyui-textbox" type="text" name="interfaceId" id="interfaceId"></td>
            <th><nobr>交易名称</nobr></th>
            <td><input class="easyui-textbox" type="text" name="interfaceName" id="interfaceName"></td>
            <th><nobr>服务码</nobr></th>
            <td><input class="easyui-textbox" type="text" name="serviceId" id="serviceId"></td>
            <th><nobr>服务名称</nobr></th>
            <td><input class="easyui-textbox" type="text" name="serviceName" id="serviceName"></td>
        </tr>
        <tr>
            <th><nobr>节点状态</nobr></th>
            <td><select name="status" id="status" panelHeight="auto" editable="false" style="width: 150px"/></td>
            <th><nobr>节点展示方式</nobr></th>
            <td><select name="display" id="display" panelHeight="auto" editable="false" style="width: 150px"/></td>
            <td></td>
            <td align="right"><a href="javascript:;" id="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a></td>
        </tr>
        <tr>
            <%--<th>交易属性标识</th>
            <td><input class="easyui-textbox" type="text" name="attribute" id="attribute"></td>
            <th>节点状态</th>
            <td><input class="easyui-textbox" type="text" name="status" id="status"></td>
            <th>版本号</th>
            <td><input class="easyui-textbox" type="text" name="status" id=""></td>--%>
        </tr>
    </table>


</fieldset>
<table title="交易节点" id="invokeLinkeTable" style="height:440px; width:100%;">
</table>

<div id="w" class="easyui-window" title="" data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;">
</div>
<script type="text/javascript">

</script>
<script type="text/javascript">
    $(function () {
        $('#invokeLinkeTable').datagrid({
            rownumbers:true,
            singleSelect:true,
            url:'/serviceLink/getServiceLinkNode/system/<%=request.getParameter("systemId") %>',
            method:'get',
            toolbar:toolbar,
            pagination:true,
            pageSize:13,
            pageList: [13,30,50],
            columns:[[
                {field:'productid',title:'',width:10,checkbox:'true'},
                {field:'interfaceId',title:'交易码',width:80,align:'right'},
                {field:'interfaceName',title:'交易名称',width:80,align:'right'},
                {field:'serviceId',title:'服务码',width:80,align:'right'},
                {field:'serviceName',title:'服务名称',width:80,align:'right'},
                {field:'operationId',title:'场景码',width:80,align:'right'},
                {field:'operationName',title:'场景名称',width:80,align:'right'},
                {field:'invokeType',title:'调用类型',width:60,align:'right'},
                {field:'bussCategory',title:'业务分类',width:100,align:'right'},
                {field:'location',title:'交易属性标识',width:100,align:'right',formatter:function (value, row, index) {if (value == 1) {return "本系统发起";}if (value == 2) {return "上游系统调用";}}},
                {field:'status',title:'节点状态',width:80,align:'right',formatter:function (value, row, index) {if (value == 0) {return "草稿";}if (value == 1) {return "生效";}if (value == 2) {return "废弃";}}},
                {field:'nodeType',title:'节点类型',width:80,align:'right',formatter:function (value, row, index) {if (value == 0) {return "消费方节点";}if (value == 1) {return "提供方节点";}if (value == 2) {return "复合型节点";}}},
                {field:'esbAccessPattern',title:'ESB调用方式',width:150,align:'right',formatter:function (value, row, index) {if (value == 0) {return "0-穿透模式";}if (value == 1) {return "1-代理模式";}if (value == 2) {return "2-寻址模式";}if (value == 4) {return "4-组合模式";}}},
                {field:'condition',title:'条件位',width:80,align:'right'},
                {field:'connectionDesc',title:'条件信息',width:80,align:'right'},
                {field:'linkCondition',title:'组合位',width:80,align:'right'},
            ]],
            onLoadSuccess:function(data){
                $.each(data.rows, function (index, item) {
                    var invokeType = item.invokeType;
                    if (invokeType == '0') {
                        item.invokeType = '提供者';
                    } else if (invokeType == '1') {
                        item.invokeType = '消费者';
                    }
                });
            },
            onLoadError: function (responce) {
                var resText = responce.responseText;
                if (resText.toString().indexOf("没有操作权限") > 0) {
//                    alert("没有权限！");
                    window.location.href = "/jsp/403.jsp";
                }
            }
        });
    });
    $("#search").click(function () {
        var queryParams = $('#invokeLinkeTable').datagrid('options').queryParams;
        queryParams.interfaceId = $("#interfaceId").textbox("getValue");
        queryParams.interfaceName = encodeURI($("#interfaceName").textbox("getValue"));
        queryParams.serviceId = $("#serviceId").textbox("getValue");
        queryParams.serviceName = encodeURI($("#serviceName").textbox("getValue"));
        queryParams.status=$('#status').combobox('getValue');
        queryParams.display=$('#display').combobox('getValue');
        if($('#display').combobox('getValue')=="0"){
            $('#invokeLinkeTable').datagrid({
                rownumbers:true,
                singleSelect:true,
                url:'/serviceLink/getServiceLinkNode/system/<%=request.getParameter("systemId") %>',
                method:'get',
                toolbar:toolbar,
                pagination:true,
                pageSize:13,
                pageList: [13,30,50],
                columns:[[
                    {field:'productid',title:'',width:10,checkbox:'true'},
                    {field:'interfaceId',title:'交易码',width:100,align:'right'},
                    {field:'interfaceName',title:'交易名称',width:100,align:'right'},
                    {field:'bussCategory',title:'业务分类',width:100,align:'right'},
                    {field:'invokeType',title:'调用类型',width:100,align:'right'},
                    {field:'location',title:'交易属性标识',width:100,align:'right',formatter:function (value, row, index) {if (value == 1) {return "本系统发起";}if (value == 2) {return "上游系统调用";}}},
                    {field:'status',title:'节点状态',width:100,align:'right',formatter:function (value, row, index) {if (value == 0) {return "草稿";}if (value == 1) {return "生效";}if (value == 2) {return "废弃";}}},
                    {field:'nodeType',title:'节点类型',width:100,align:'right',formatter:function (value, row, index) {if (value == 0) {return "消费方节点";}if (value == 1) {return "提供方节点";}if (value == 2) {return "复合型节点";}}}
                ]],
                onLoadSuccess:function(data){
                    $.each(data.rows, function (index, item) {
                        var invokeType = item.invokeType;
                        if (invokeType == '0') {
                            item.invokeType = '提供者';
                        } else if (invokeType == '1') {
                            item.invokeType = '消费者';
                        }
                    });
                },
                onLoadError: function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
//                    alert("没有权限！");
                        window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        }else if($('#display').combobox('getValue')=="1"){
            $('#invokeLinkeTable').datagrid({
                rownumbers:true,
                singleSelect:true,
                url:'/serviceLink/getServiceLinkNode/system/<%=request.getParameter("systemId") %>',
                method:'get',
                toolbar:toolbar,
                pagination:true,
                pageSize:13,
                pageList: [13,30,50],
                columns:[[
                    {field:'productid',title:'',width:10,checkbox:'true'},
                    {field:'serviceId',title:'服务码',width:100,align:'right'},
                    {field:'serviceName',title:'服务名称',width:100,align:'right'},
                    {field:'operationId',title:'场景ID',width:100,align:'right'},
                    {field:'operationName',title:'场景名称',width:100,align:'right'},
                    {field:'invokeType',title:'调用类型',width:100,align:'right'},
                    {field:'nodeType',title:'节点类型',width:100,align:'right',formatter:function (value, row, index) {if (value == 0) {return "消费方节点";}if (value == 1) {return "提供方节点";}if (value == 2) {return "复合型节点";}}},
                    {field:'bussCategory',title:'节点业务分类',width:100,align:'right'},
                    {field:'status',title:'节点状态',width:100,align:'right',formatter:function (value, row, index) {if (value == 0) {return "草稿";}if (value == 1) {return "生效";}if (value == 2) {return "废弃";}}},
                ]],
                onLoadSuccess:function(data){
                    $.each(data.rows, function (index, item) {
                        var invokeType = item.invokeType;
                        if (invokeType == '0') {
                            item.invokeType = '提供者';
                        } else if (invokeType == '1') {
                            item.invokeType = '消费者';
                        }
                    });
                },
                onLoadError: function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
//                    alert("没有权限！");
                        window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        }else{
            $('#invokeLinkeTable').datagrid({
                rownumbers:true,
                singleSelect:true,
                url:'/serviceLink/getServiceLinkNode/system/<%=request.getParameter("systemId") %>',
                method:'get',
                toolbar:toolbar,
                pagination:true,
                pageSize:13,
                pageList: [13,30,50],
                columns:[[
                    {field:'productid',title:'',width:10,checkbox:'true'},
                    {field:'interfaceId',title:'交易码',width:80,align:'right'},
                    {field:'interfaceName',title:'交易名称',width:80,align:'right'},
                    {field:'serviceId',title:'服务码',width:80,align:'right'},
                    {field:'serviceName',title:'服务名称',width:80,align:'right'},
                    {field:'operationId',title:'场景码',width:80,align:'right'},
                    {field:'operationName',title:'场景名称',width:80,align:'right'},
                    {field:'invokeType',title:'调用类型',width:60,align:'right'},
                    {field:'bussCategory',title:'业务分类',width:100,align:'right'},
                    {field:'location',title:'交易属性标识',width:100,align:'right',formatter:function (value, row, index) {if (value == 1) {return "本系统发起";}if (value == 2) {return "上游系统调用";}}},
                    {field:'status',title:'节点状态',width:80,align:'right',formatter:function (value, row, index) {if (value == 0) {return "草稿";}if (value == 1) {return "生效";}if (value == 2) {return "废弃";}}},
                    {field:'nodeType',title:'节点类型',width:80,align:'right',formatter:function (value, row, index) {if (value == 0) {return "消费方节点";}if (value == 1) {return "提供方节点";}if (value == 2) {return "复合型节点";}}},
                    {field:'esbAccessPattern',title:'ESB调用方式',width:150,align:'right',formatter:function (value, row, index) {if (value == 0) {return "0-穿透模式";}if (value == 1) {return "1-代理模式";}if (value == 2) {return "2-寻址模式";}if (value == 4) {return "4-组合模式";}}},
                    {field:'condition',title:'条件位',width:80,align:'right'},
                    {field:'connectionDesc',title:'条件信息',width:80,align:'right'},
                    {field:'linkCondition',title:'组合位',width:80,align:'right'},
                ]],
                onLoadSuccess:function(data){
                    $.each(data.rows, function (index, item) {
                        var invokeType = item.invokeType;
                        if (invokeType == '0') {
                            item.invokeType = '提供者';
                        } else if (invokeType == '1') {
                            item.invokeType = '消费者';
                        }
                    });
                },
                onLoadError: function (responce) {
                    var resText = responce.responseText;
                    if (resText.toString().indexOf("没有操作权限") > 0) {
//                    alert("没有权限！");
                        window.location.href = "/jsp/403.jsp";
                    }
                }
            });
        }
        if (queryParams.englishWord || queryParams.chineseWord || queryParams.esglisgAb || queryParams.remark) {
            $("#invokeLinkeTable").datagrid('options').queryParams = queryParams;//传递值
            $("#invokeLinkeTable").datagrid('reload');//重新加载table
        } else {
            $("#invokeLinkeTable").datagrid('reload');
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
            parent.$('#subtab').tabs('select', title);
        }
    }

    var toolbar = [];
    <shiro:hasPermission name="link-update">
    toolbar.push(

            {
                text: '添加节点',
                iconCls: 'icon-add',
                handler: function () {
                    var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/addNode.jsp?systemId=<%=request.getParameter("systemId") %>' + '" style="width:100%;height:100%;"></iframe>';
                    selectTab('节点添加', content);
                    selectTab('节点添加', content);
                }
            }
            ,{
                text: '节点维护',
                iconCls: 'icon-edit',
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
                            var content = '<iframe scrolling="auto" frameborder="0"  src="/serviceLink/nodeInfo/' + checkedItem.id + '" style="width:100%;height:100%;"></iframe>';
                            selectTab('节点维护', content);
                            selectTab('节点维护', content);
                        }
                    }
                    else {
                        alert("请选中要预览的节点！");
                    }
                }
            }
    );
    </shiro:hasPermission>
    <shiro:hasPermission name="link-get">
    toolbar.push({
                text: '预览1',
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
                            var oldIE;
                            var ieVersion = "undefined" != typeof navigator && /MSIE\s([\d.]+)/.test(navigator.userAgent) ? new Number(RegExp.$1) : -1, oldIE = ieVersion > -1 && 9 > ieVersion;
                            var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink2.0/serviceLink.jsp?sourceId=' + checkedItem.id + '" style="width:100%;height:100%;"></iframe>';
                            if(oldIE){
                                content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/previewLink.jsp?sourceId='+checkedItem.id+'" style="width:100%;height:100%;"></iframe>';
                            }
//                        //测试
//                        content= '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/previewLink.jsp?sourceId='+checkedItem.id+'" style="width:100%;height:100%;"></iframe>';
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
                            var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/serviceLink/parentLink.jsp?sourceId=' + checkedItem.id + '" style="width:100%;height:100%;"></iframe>';
                            selectTab('血缘分析', content);
                            selectTab('血缘分析', content);
                        }
                    }
                    else {
                        alert("请选中要预览的节点！");
                    }
                }
            });
    </shiro:hasPermission>


    //        ,{
    //            text: '编辑节点',
    //            iconCls: 'icon-qxfp',
    //            handler: function () {
    //                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
    //                var checkedItem;
    //                if (checkedItems != null && checkedItems.length > 0) {
    //                    if (checkedItems.length > 1) {
    //                        alert("请选择一个节点进行预览！");
    //                        return false;
    //                    }
    //                    else {
    //                        checkedItem = checkedItems[0];
    //                        invokeId = checkedItem.id;
    //                        uiinit.win({
    //                            w:500,
    //                            iconCls:'icon-add',
    //                            title:"编辑",
    //                            url : "/jsp/serviceLink/serviceLinkModifyDialog.jsp"
    //                        });
    //
    //                    }
    //                }
    //                else {
    //                    alert("请选中要预览的节点！");
    //                }
    //            }
    //        }
    $('#status').combobox({
        valueField: 'value',
        textField: 'label',
        data: [{
            label: '生效',
            value: '1'
        },{
            label: '废弃',
            value: '2'
        },{
            label: '全部',
            value: ''
        }]
    });
    var status = "${node.status}"
    if(status != ""){
        $('#status').combobox('setValue',status);
    }
    $('#display').combobox({
        valueField: 'value',
        textField: 'label',
        data: [
            {
                label: '全部',
                value: '2'
            } ,{
                label: '交易码',
                value: '0'
            },{
                label: '服务码',
                value: '1'
            }]
    });
    var display = "2"
    if(display != ""){
        $('#display').combobox('setValue',display);
    }
</script>

</body>
</html>