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


<form id="interfaceList">
    <fieldset>
        <legend>候选服务区</legend>
            <table border="0" cellspacing="0" cellpadding="0" id="tb1">
            <tr>
                <th><nobr>
                    交易码
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="ecode" value="${ecode}">
                </td>

                <th><nobr>
                    交易名称
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="interfaceName" value="${interfaceName}">
                </td>
            </tr>
            <tr>
                <th><nobr>
                    可选服务代码
                </nobr>
                </th>
                <td>
                    <input name="serviceId" id="serviceId"  class="easyui-combobox" style="width:140px"
                           data-options="
                 method:'get',
                 url:'/service/getAll',
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
				    }
                 "
                        >
                </td>

                <th><nobr>
                    可选服务名称
                </nobr>
                </th>
                <td><input name="serviceName" id="serviceName"  class="easyui-combobox" style="width:140px"
                           data-options="
                 method:'get',
                 url:'/service/getAll',
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
				    }
                 "
                        >
                </td>
            </tr>
            <tr>
                <th><nobr>
                    可选服务操作
                </nobr>
                </th>
                <td><input name="operationId" id="operationId"  class="easyui-combobox" style="width:140px"
                           data-options="
                 method:'get',
                  url:'/operation/getByServiceId',
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
				    }
                 "
                        >
                </td>

                <th><nobr>
                    可选服务操作名称
                </nobr>
                </th>
                <td>
                    <input name="operationName" id="operationName"  class="easyui-combobox" style="width:140px"
                           data-options="
                 method:'get',
                 url:'/operation/getByServiceId',
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
				    }
                 "
                            >
                </td>
            </tr>
            <tr>
                <th><nobr>
                    交易消费者
                </nobr>
                </th>
                <td><input name="consumerId" id="consumerId"  class="easyui-combobox" style="width:140px"
                           data-options="
                 method:'get',
                 url:'/system/getSystemAll',
                 textField:'chineseName',
                 valueField:'chineseName',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
				    }
                 "
                        >
                </td>

                <th><nobr>
                    交易提供者
                </nobr>
                </th>
                <td><input class="easyui-textbox" type="text" id="systemId" value="${systemId}">
                </td>
            </tr>
                <tr>
                    <th><nobr>
                        服务大类
                    </nobr>
                    </th>
                    <td><input name="categoryId" id="categoryId"  class="easyui-combobox" style="width:140px"
                               data-options="
                 method:'get',
                 url:'/serviceIdentify/getCategoryAll',
                 textField:'chineseName',
                 valueField:'chineseName',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
				    }
                 "
                            >
                    </td>
                </tr>
            <tr>
                <th><nobr>
                    分析结果
                </nobr>
                </th>
                <td colspan="4">
                    <input class="easyui-textbox" type="text" id="remark" style="width:450px;height:50px" >
                </td>
            </tr>

        </table>

    </fieldset>
    <fieldset>
        <legend>最终生成服务区</legend>
        <table border="0" cellspacing="0" cellpadding="0" id="tb2">
            <tr>
                <th><nobr>
                    服务编码
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="serviceId1">
                </td>

                <th><nobr>
                    服务名称
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="serviceName1">
                </td>
            </tr>
            <tr>
                <th><nobr>
                    场景代码
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="operationId1">
                </td>

                <th><nobr>
                    场景名称
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="operationName1">
                </td>
            </tr>
            <tr>
                <th><nobr>
                    服务消费者
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="consumerId1">
                </td>

                <th><nobr>
                    服务提供者
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="providerId1" value="${systemId}">
                </td>
            </tr>
            <tr>
                <th><nobr>
                    服务大类
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="categoryId1">
                </td>
            </tr>

        </table>

    </fieldset>
    <fieldset>
        <table border="0" cellspacing="0" cellpadding="0" id="tb3">
            <tr>
                <td>
                    <a href="#" id="saveTagBtn" onclick="cloneData()" class="easyui-linkbutton" iconCls="icon-ok" style="margin-left:1em" >确定</a>
                </td>
                <td>
                    <a href="#" id="save" onclick="saveData()" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:1em" >保存</a>
                </td>
                <td>
                    <a href="#" id="clean" onclick="clearData()" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
                </td>

            </tr>
        </table>

    </fieldset>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
    function cloneData(){
        var sId = $("#serviceId").combobox("getValue");
        var sName = $("#serviceName").combobox("getText");
        var oId = $("#operationId").combobox("getValue");
        var oName = $("#operationName").combobox("getText");
        var cId = $("#consumerId").combobox("getValue");
//        var pId = $("#providerId").combobox("getValue");
        var scId = $("#categoryId").combobox("getValue");
        $("#serviceId1").textbox("setValue",sId);
        $("#serviceName1").textbox("setValue",sName);
        $("#operationId1").textbox("setValue",oId);
        $("#operationName1").textbox("setValue",oName);
        $("#consumerId1").textbox("setValue",cId);
//        $("#providerId1").textbox("setValue",pId);
        $("#categoryId1").textbox("setValue",scId);
    }
    function clearData(){
        $("#serviceId1").textbox("setValue","");
        $("#serviceName1").textbox("setValue","");
        $("#operationId1").textbox("setValue","");
        $("#operationName1").textbox("setValue","");
        $("#consumerId1").textbox("setValue","");
//        $("#providerId1").textbox("setValue","");
        $("#categoryId1").textbox("setValue","");
    }


    function saveData(){
        var consumers = encodeURI($("#consumerId1").textbox("getValue"));
        var providers = encodeURI($("#providerId1").textbox("getValue"));
        var sid=$("#serviceId1").combobox("getText");
        var sName=$("#serviceName1").combobox("getText");
        var oid=$("#operationId1").combobox("getText");
        var oName=$("#operationName1").combobox("getText");
        var numB=${iId};
        var scId=encodeURI($("#categoryId1").combobox("getText"));

        $.ajax({
            type:"get",
            url: "/serviceIdentify/save",
            dataType: "json",
            data: {"interfaceId":numB,"serviceId":sid,"serviceName":sName,"operationId":oid,"operationName":oName,"consumers":consumers,"providers":providers,"categoryName":scId},
            success: function(data){
                if(data){
                    alert("保存成功！");
                    $('#w').window('close', true);
                    var url ="/jsp/serviceIdentify/addTask.jsp?interfaceId="+numB+"&serviceId="+sid+"&operationId=1"+oid;
                    uiinit.win({
                        w: 500,
                        iconCls: 'icon-edit',
                        title: "提交任务",
                        url: url
                    });
                }
                else{
                    alert("保存失败！");
                }
            }
        });
    }

</script>
</body>
</html>


