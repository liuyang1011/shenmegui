<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
</head>

<body>
<form id="searchForm">
    <fieldset>
        <legend>条件搜索</legend>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th><nobr>服务代码</nobr></th>
                <td><input name="serviceId" id="serviceId"  class="easyui-combobox" width="25%"
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
                <th><nobr>服务名称</nobr></th>
                <td><input name="serviceName" id="serviceName"  class="easyui-combobox"  width="25%"
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
                <th><nobr>服务功能描述</nobr></th>
                <td><input class="easyui-textbox"  width="20%"
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
                <th><nobr>场景代码</nobr></th>
                <td><input name="operationId" id="operationId"  class="easyui-combobox"  width="25%"
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
                <th><nobr>场景名称</nobr></th>
                <td>
                    <input name="operationName" id="operationName"  class="easyui-combobox"  width="25%"
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
                <th><nobr>场景功能描述</nobr></th>
                <td><input class="easyui-textbox"  width="20%"
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
                <th><nobr>消费者</nobr></th>
                <td><input name="consumerId" id="consumerId"  class="easyui-combobox"  width="25%"
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
                <th><nobr>提供者</nobr></th>
                <td><input name="providerId" id="providerId"  class="easyui-combobox"  width="25%"
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
                <th></th>
                <td>
                </td>
                <th style="width:200px" >
                    <nobr>
                        <a href="#" id="saveTagBtn" onclick="query()" class="easyui-linkbutton" iconCls="icon-search" style="margin-left:1em" >查询</a>
                        <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
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
			onDblClickCell:onDblClickCell,
			pagination:true,
			pageSize: 13,
			pageList: [13,20,50]
				"
           style="height:410px; width:100%;">
        <thead>
        <tr>
            <th data-options="field:'',checkbox:true,width:50"></th>
            <th data-options="field:'serviceId',width:90">服务代码</th>
            <th data-options="field:'serviceName',width:150">服务名称</th>
            <th data-options="field:'operationId',width:70">场景代码</th>
            <th data-options="field:'operationName',width:180">场景名称</th>
            <th data-options="field:'consumer',width:80">调用方</th>
            <th data-options="field:'conIp',width:120">调用方IP</th>
            <th data-options="field:'provider', width:80" >提供方</th>
            <th data-options="field:'proIp',width:120">提供方IP</th>
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
        $("#serviceId").combobox({url:"/service/getAll"});
        $("#serviceName").combobox({url:"/service/getAll"});
        $("#operationId").combobox({url:"/operation/getByServiceId"});
        $("#operationName").combobox({url:"/operation/getByServiceId"});
        $("#providerId").combobox({url:"/system/getSystemAll"});
        $("#consumerId").combobox({url:"/system/getSystemAll"});
        $("#resultList").datagrid({url:"/ipSearch/query"});
        var configResult;
        query();
    });
    function query(){
        var params = {
            "serviceId":$("#serviceId").combobox("getValue"),
//            "serviceName":encodeURI($("#serviceName").combobox("getText")),
            "operationId":$("#operationId").textbox("getValue"),
//            "operationName":encodeURI($("#operationName").combobox("getText")),
            "providerId":$("#providerId").combobox("getValue"),
            "consumerId":$("#consumerId").combobox("getValue")
        }
        $("#resultList").datagrid('options').queryParams = params;
        changePageList()
        $("#resultList").datagrid('reload');
    }
    function changePageList(){
        var p = $("#resultList").datagrid('getPager');
        var total =  $(p).pagination("options").total;
        if(total < 100){
            total = 100;
        }
        $(p).pagination({
            pageSize: 13,
            pageList: [13,20,50,total]
        });

    }

    function onDblClickCell(rowIndex, field,value){
        var texts = '<div style="word-wrap:break-word" >'+value+'</div>';
        $.messager.show({
            title:'详细',
            msg:texts,
            showType:'show',
            height:'auto'
        });
    }
    function closeDialog(){
        $('#opDialog').dialog("close");
    }
</script>

</body>
</html>

