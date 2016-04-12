<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.fileDownload.js"></script>
</head>

<body>
<form id="searchForm">
    <fieldset>
        <legend>条件搜索</legend>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>
                    <nobr>服务代码</nobr>
                </th>
                <td><input name="serviceId" id="serviceId" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'serviceId',
                 valueField:'serviceId',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
							var values = $('#serviceName').combobox('getData');
							 $.each(values, function (index, item) {
							   if($.trim(item.serviceId) == $.trim(newValue)){
							        $('#serviceName').combobox('setValue',newValue);
							        }
							 });
							 var urlpath = '/operation/getByServiceId?serviceId='+newValue;
							 $('#operationId').combobox({url : urlpath});
							 $('#operationName').combobox({url : urlpath});
							 query();
				    }
                 "
                        >
                </td>
                <th>
                    <nobr>服务名称</nobr>
                </th>
                <td><input name="serviceName" id="serviceName" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'serviceName',
                 valueField:'serviceId',
                  onChange:function(newValue, oldValue){
							this.value=newValue;
							var values = $('#serviceId').combobox('getData');
							 $.each(values, function (index, item) {
							   if($.trim(item.serviceId) == $.trim(newValue) || $.trim(item.serviceName) == $.trim(newValue)){
							        $('#serviceId').combobox('setValue',item.serviceId);
							        }
							 });
							 query();
				    }
                 "
                        >
                </td>
                <th>
                    <nobr>服务功能描述</nobr>
                </th>
                <td><input class="easyui-textbox" width="20%"
                           type="text" name="desc" id="serviceDesc"
                           data-options="
                 onChange:function(newValue, oldValue){
                    query();
                 }
                 "
                        >
                </td>
                <th>

                </th>
            </tr>
            <tr>
                <th>
                    <nobr>场景代码</nobr>
                </th>
                <td><input name="operationId" id="operationId" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'operationId',
                 valueField:'operationId',
                 onChange:function(newValue, oldValue){
                    this.value=newValue;
							var values = $('#operationName').combobox('getData');
							 $.each(values, function (index, item) {
							   if($.trim(item.operationId) == $.trim(newValue)){
							        $('#operationName').combobox('setValue',newValue);
							        }
							 });
							 query();
				    }
                 "
                        >
                </td>
                <th>
                    <nobr>场景名称</nobr>
                </th>
                <td>
                    <input name="operationName" id="operationName" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'operationName',
                 valueField:'operationId',
                 onChange:function(newValue, oldValue){
                    this.value=newValue;
                         var values = $('#operationId').combobox('getData');
							 $.each(values, function (index, item) {
							   if($.trim(item.operationId) == $.trim(newValue)){
							        $('#operationId').combobox('setValue',newValue);
							        }
							 });
							 query();
				    }
                 "
                            >
                </td>
                <th>
                    <nobr>场景功能描述</nobr>
                </th>
                <td><input class="easyui-textbox" width="20%"
                           type="text" name="operationDesc" id="operationDesc"
                           data-options="
                 onChange:function(newValue, oldValue){
                    query();
                 }
                 "
                        >
                </td>
                <th>

                </th>
            </tr>
            <tr>
                <th>
                    <nobr>消费者</nobr>
                </th>
                <td><input name="consumerId" id="consumerId" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'chineseName',
                 valueField:'id',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
							query();
				    }
                 "
                        >
                </td>
                <th>
                    <nobr>提供者</nobr>
                </th>
                <td><input name="providerId" id="providerId" class="easyui-combobox" width="25%"
                           data-options="
                 method:'get',
                 textField:'chineseName',
                 valueField:'id',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
							query();
				    }
                 "
                        >
                </td>
                <th>
                    <nobr>场景状态</nobr>
                </th>
                <td><input class="easyui-combobox" width="20%"
                           type="text" name="operationState" id="operationState"
                           data-options="
                 textField:'text',
                 valueField:'id',
                 data:[
                    {'id':'0','text':'服务定义'},
                    {'id':'6','text':'待审核'},
                    {'id':'1','text':'审核通过'},
                    {'id':'2','text':'审核不通过'},
                    {'id':'3','text':'已发布'},
                    {'id':'4','text':'已上线'},
                    {'id':'7','text':'修订'},
                    {'id':'8','text':'已下线'},
                    {'id':'9','text':'已废弃'}

                 ],
                 onChange:function(newValue, oldValue){
                    query();
                 }
                 "
                        >
                </td>
                <th style="width:200px">
                    <nobr>
                        <a href="#" id="saveTagBtn" onclick="query()" class="easyui-linkbutton" iconCls="icon-search"
                           style="margin-left:1em">查询</a>
                        <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton"
                           iconCls="icon-clear" style="margin-left:1em">清空</a>
                    </nobr>
                </th>
            </tr>
        </table>


    </fieldset>
</form>
<div style="width:100%">
    <table id="resultList" class="easyui-datagrid"
           data-options="
			rownumbers:true,
			singleSelect:false,
			fitColumns:false,
			method:'get',
			idField:'autoId',
			toolbar:toolbar,
			onDblClickCell:onDblClickCell,
			pagination:true,
			pageSize: 20,
			pageList: [20,100,150]
				"
           style="width:100%;">
        <thead>
        <tr>
            <th data-options="field:'autoId',checkbox:true"></th>
            <th data-options="field:'serviceId',width:90">服务代码</th>
            <th data-options="field:'serviceName',width:150">服务名称</th>
            <%--<th data-options="field:'serviceDesc',width:100">服务功能描述</th>--%>
            <th data-options="field:'operationId',width:70">场景代码</th>
            <th data-options="field:'operationName',width:180">场景名称</th>
            <%--<th data-options="field:'operationDesc',width:150">场景功能描述</th>--%>
            <th data-options="field:'consumers',width:130">调用方</th>
            <th data-options="field:'providers',width:130">提供方</th>
            <th data-options="field:'version', width:80">版本号</th>
            <th data-options="field:'optDate',width:120">更新时间</th>
            <th data-options="field:'optUser', width:80">更新用户</th>
            <th data-options="field:'optState',width:60" formatter='formatter.operationState'>状态</th>
        </tr>
        </thead>
    </table>
</div>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<div id="opDialog" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#serviceId").combobox({url: "/service/getAll"});
        $("#serviceName").combobox({url: "/service/getAll"});
        $("#operationId").combobox({url: "/operation/getByServiceId"});
        $("#operationName").combobox({url: "/operation/getByServiceId"});
        $("#providerId").combobox({url: "/system/getSystemAll"});
        $("#consumerId").combobox({url: "/system/getSystemAll"});
        $("#resultList").datagrid({url: "/operation/query"});
        var configResult;
        query();
    });
    var formatter = {
        operationState: function (value, row, index) {
            if (value == 0) {
                return "<font color='green'>服务定义</font>";
            }
            if (value == 1) {
                return "<font color='green'>审核通过</font>";
            }
            if (value == 2) {
                return "<font color='red'>审核未通过</font>";
            }
            if (value == 3) {
                return "<font color='green'>已发布</font>";
            }
            if (value == 4) {
                return "<font color='green'>已上线</font>";
            }
            if (value == 5) {
                return "<font color='red'>已下线</font>";
            }
            if (value == 6) {
                return "<font color='red'>待审核</font>";
            }
            if (value == 7) {
                return "<font color='red'>修订</font>";
            }
            if (value == 8) {
                return "<font color='red'>已下线</font>";
            }
        },
        version: function (value, row, index) {
            try {
                return row.version.code
            } catch (exception) {
            }
        }
    };
    var toolbar = [
        {
            text: '导出EXCEL&nbsp;&nbsp;&nbsp;',
    iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "");
                    form.attr("method", "post");
                    form.attr("action", "/excelExporter/exportOperation");
                    for (var i = 0; i < checkedItems.length; i++) {
                        var input1 = $("<input>");
                        input1.attr("type", "hidden");
                        input1.attr("name", "pks[" + i + "].serviceId");
                        input1.attr("value", checkedItems[i].serviceId);
                        var input2 = $("<input>");
                        input2.attr("type", "hidden");
                        input2.attr("name", "pks[" + i + "].operationId");
                        input2.attr("value", checkedItems[i].operationId);

                        form.append(input1);
                        form.append(input2);
                    }

                    $("body").append(form);//将表单放置在web中
                    form.submit();//表单提交
                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        {
            text: '导出PDF&nbsp;&nbsp;&nbsp;',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "");
                    form.attr("method", "post");
                    form.attr("action", "/pdfExporter/exportOperation");
                    for (var i = 0; i < checkedItems.length; i++) {
                        var input1 = $("<input>");
                        input1.attr("type", "hidden");
                        input1.attr("name", "pks[" + i + "].serviceId");
                        input1.attr("value", checkedItems[i].serviceId);
                        var input2 = $("<input>");
                        input2.attr("type", "hidden");
                        input2.attr("name", "pks[" + i + "].operationId");
                        input2.attr("value", checkedItems[i].operationId);

                        form.append(input1);
                        form.append(input2);
                    }

                    $("body").append(form);//将表单放置在web中
                    form.submit();//表单提交
                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        <shiro:hasPermission name="exportConfig-get">
        {
            text: '导出配置&nbsp;&nbsp;&nbsp;',
            iconCls: 'icon-excel-export',
            handler: function () {
                var consumer = $("#consumerId").combobox("getValue");
                if(null == consumer || "" == consumer){
                    consumer = "null";
                }
                var checkedItems = $('#resultList').datagrid('getSelections');
                if (checkedItems != null && checkedItems.length > 0) {
                    $.ajax({
                        "type": "POST",
                        "async": false,
                        "contentType": "application/json; charset=utf-8",
                        "url": "/export/getConfigVo/"+consumer,
                        "data": JSON.stringify(checkedItems),
                        "dataType": "json",
                        "success": function (result) {
                            if (result && result.length > 0) {
                                configResult = result;
                                $('#opDialog').dialog({
                                    title: '导出配置',
                                    width: 1000,
                                    left: 50,
                                    closed: false,
                                    cache: false,
                                    href: "/jsp/service/export_config_list.jsp",
                                    modal: true,
                                    onLoad: function () {
                                        $("#choosedList").datagrid("loadData", configResult);
                                    }
                                });
                            } else {
                                alert("没有可导出的配置！");
                            }
                        }
                    });

                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        </shiro:hasPermission>
        <shiro:hasPermission name="scriptExport-get">
        {
            text: '导出脚本&nbsp;&nbsp;&nbsp;',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getSelections');
                if (checkedItems != null && checkedItems.length > 0) {
                    $('#opDialog').dialog({
                        title: '导出脚本',
                        width: 700,
                        left: 100,
                        height: 500,
                        top: $(document).scrollTop() + ($(window).height() - 250) * 0.3,
                        closed: false,
                        cache: false,
                        href: "/jsp/esb/script_list.jsp",
                        modal: true
                    });
                }
                else {
                    alert("没有选中数据！");
                    // if(confirm("确定导出全部数据的脚本吗？")){
                    // var form=$("<form>");//定义一个form表单
                    // form.attr("style","display:none");
                    // form.attr("target","");
                    // form.attr("method","post");
                    // form.attr("action","/esbScriptExport/downloadAll");
                    // $("body").append(form);//将表单放置在web中
                    // form.submit();//表单提交
                    // }


                }
            }
        },
        </shiro:hasPermission>
        {
            text: '导出MapFile&nbsp;&nbsp;&nbsp',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "");
                    form.attr("method", "post");
                    form.attr("action", "/mapFile/exportOperation");

                    for (var i = 0; i < checkedItems.length; i++) {
                        var input1 = $("<input>");
                        input1.attr("type", "hidden");
                        input1.attr("name", "pks[" + i + "].serviceId");
                        input1.attr("value", checkedItems[i].serviceId);
                        var input2 = $("<input>");
                        input2.attr("type", "hidden");
                        input2.attr("name", "pks[" + i + "].operationId");
                        input2.attr("value", checkedItems[i].operationId);

                        form.append(input1);
                        form.append(input2);
                    }

                    $("body").append(form);//将表单放置在web中
                    form.submit();//表单提交
                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        {
            text: '导出WSDL&nbsp;&nbsp;&nbsp',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                if (checkedItems != null && checkedItems.length > 0) {
                    var form = $("<form>");//定义一个form表单
                    form.attr("style", "display:none");
                    form.attr("target", "");
                    form.attr("method", "post");
                    form.attr("action", "/wsdlExport/exportOperation");

                    for (var i = 0; i < checkedItems.length; i++) {
                        var input1 = $("<input>");
                        input1.attr("type", "hidden");
                        input1.attr("name", "pks[" + i + "].serviceId");
                        input1.attr("value", checkedItems[i].serviceId);
                        var input2 = $("<input>");
                        input2.attr("type", "hidden");
                        input2.attr("name", "pks[" + i + "].operationId");
                        input2.attr("value", checkedItems[i].operationId);

                        form.append(input1);
                        form.append(input2);
                    }

                    $("body").append(form);//将表单放置在web中
                    form.submit();//表单提交
                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        {
            text: '详细信息&nbsp;&nbsp;&nbsp',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                if (checkedItems != null) {
                    if(checkedItems.length == 0){
                        alert("没有选中数据！");
                    }
                    else if(checkedItems.length >1){
                        alert("已选中多行数据，请只选中要展示的数据!")
                    }else{
                        $('#opDialog').dialog({
                            title: '详细信息',
                            width: 800,
                            left: 200,
                            top: 100,
                            closed: false,
                            cache: false,
                            href: '/operation/detailPage?serviceId=' + checkedItems[0].serviceId + '&operationId=' + checkedItems[0].operationId,
                            modal: true
                        });
                    }
                }
                else {
                    alert("没有选中数据！");
                }
            }
        },
        {
            text: '关联服务场景',
            iconCls: 'icon-excel-export',
            handler: function () {
                var checkedItems = $('#resultList').datagrid('getChecked');
                console.log(checkedItems);
                if (checkedItems != null) {
                    if(checkedItems.length == 0){
                        alert("没有选中数据！");
                    }
                    else if(checkedItems.length >1){
                        alert("已选中多行数据，请只选中要展示的数据!")
                    }else{
                        var urlPath = '/jsp/service/servicePage2.jsp?serviceId=' + checkedItems[0].serviceId + '&operationId=' + checkedItems[0].operationId + "&_t=" + new Date().getTime();
                        var content = ' <iframe scrolling="auto" frameborder="0" src="' + urlPath + '" style="width:100%;height:100%;"></iframe>'

                        parent.$('#mainContentTabs').tabs('add', {
                            title: '服务（' + checkedItems[0].serviceId + ":" + checkedItems[0].operationId + ")",
                            content: content,
                            closable: true
                        });
                    }
                }
                else {
                    alert("没有选中数据！");
                }
            }
        }
    ];
    function query() {
        var params = {
            "serviceId": $("#serviceId").combobox("getValue"),
            "serviceName": encodeURI($("#serviceName").combobox("getText")),
            "serviceDesc": encodeURI($("#serviceDesc").textbox("getValue")),
            // "serviceState":$("#serviceState").combobox("getValue"),

            "operationId": $("#operationId").textbox("getValue"),
            "operationName": encodeURI($("#operationName").combobox("getText")),
            "operationDesc": encodeURI($("#operationDesc").textbox("getValue")),
            "operationState": $("#operationState").combobox("getValue"),
            "providerId": $("#providerId").combobox("getValue"),
            "consumerId": $("#consumerId").combobox("getValue")
        }
        $("#resultList").datagrid('options').queryParams = params;
        changePageList()
        $("#resultList").datagrid('reload');
        //取消选中
        $("#resultList").datagrid('unselectAll');
    }
    function changePageList() {
        var p = $("#resultList").datagrid('getPager');
        var total = $(p).pagination("options").total;
        if (total < 600) {
            total = 600;
        }
        $(p).pagination({
            pageSize: 20,
            pageList: [20, 100, 150, total]
        });

    }
    function exportFile(url, values) {
        var form = $("<form>");//定义一个form表单
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", url);
        for (var i = 0; i < values.length; i++) {
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", values[i].name);
            input1.attr("value", values[i].value);
            form.append(input1);
        }

        $("body").append(form);//将表单放置在web中
        form.submit();//表单提交
    }

    function onDblClickCell(rowIndex, field, value) {
        var texts = '<div style="word-wrap:break-word" >' + value + '</div>';
        $.messager.show({
            title: '详细',
            msg: texts,
            showType: 'show',
            height: 'auto'
        });
    }
    function closeDialog() {
        $('#opDialog').dialog("close");
    }
</script>

</body>
</html>

