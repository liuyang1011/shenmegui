<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <link href="/plugin/aehlke-tag-it/css/jquery.tagit.css" rel="stylesheet" type="text/css">
    <link href="/plugin/aehlke-tag-it/css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script src="/newui/plugins/jQueryUI/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/plugin/aehlke-tag-it/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>


    <script type="text/javascript" src="/assets/tag/tagManager.js"></script>
    <script type="text/javascript" src="/jsp/service/operation/operation.js"></script>
    <script type="text/javascript">
        var serviceId;
        var operationId;
        $(function(){
            /**
             *  初始化接口标签
             * @param result
             */
            var initTags = function initTags(result){
                result.forEach(function(tag){
                    $("#tags").append("<li>" + tag.tagName + "</li>");
                });
                $("#tags").tagit();

            };
            serviceId = $("#serviceId").attr("value");
            operationId = $("#operationId").textbox("getValue");
            tagManager.getTagForOperation(serviceId,operationId,initTags);

            /*$("#saveTagBtn").click(function () {
                var tagNames = $("#tags").tagit("assignedTags");
                var tags = [];
                tagNames.forEach(function (tagName){
                    var tagToAdd = {};
                    tagToAdd.tagName = tagName;
                    tags.push(tagToAdd);
                });
                tagManager.addTagForOperation(serviceId,operationId, tags, function (){
                    alert("标签保存成功");
                });
            });*/
        })
        var toolbar = [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                if (!$("#operationForm").form('validate')) {
                    alert("请先完善基础信息!");
                    return false;
                }
                $('#interfaceDlg').dialog({
                    title: '添加消费者-提供者关系',
                    width: 500,
                    height: 500,
                    closed: false,
                    cache: false,
                    href: '/jsp/service/operation/consumer_provider_add.jsp',
                    modal: true
                });
            }},{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
//                $("#resultList").datagrid('get',{total:0,rows:[]});
                var row = $("#resultList").datagrid('getSelected');
                console.log(row);
                var index =  $("#resultList").datagrid('getRowIndex',row);
                $.ajax({
                    type: "POST",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    url: "/serviceLink/deleteInvoke",
                    dataType: "json",
                    data : JSON.stringify(row),
                    success: function (data) {
                        $("#resultList").datagrid('deleteRow',index);
                        alert("删除成功");
                    }
                });
//                invokeList = new Array();
            }}
        ];
        var systemList = ${systemList};
        var consumerList = new Array();
        var providerList = new Array();
        var invokeList = ${invokeList};
        /*在接口选完服务提供者消费者关系点击确定执行*/
        function addInterfaceContent(){
            var consumerNames = "";
            var consumerNameIds = "";
            var standardConsumerNames = "";
            var standardConsumerIds = "";
            for(var i = 0; i < consumerList.length; i++){
                var csi = consumerList[i];
                if(i > 0){
                    consumerNames += ", ";
                    consumerNameIds += ", ";
                }
                consumerNames += csi.systemChineseName;
                consumerNameIds += csi.systemId;
                if(csi.interfaceId == ""){
                    if(i > 0){
                        standardConsumerNames += ",";
                        standardConsumerIds += ",";
                    }
                    standardConsumerNames += csi.systemChineseName;
                    standardConsumerIds += csi.systemId;
                }
                invokeList.push(csi);
            }
            var standardProviderNames = "";
            var standardProviderIds = "";
            for(var i = 0; i < providerList.length; i++){
                var psi = providerList[i];
                if(psi.interfaceId == ""){
                    if(i > 0){
                        standardProviderNames += ", ";
                        standardProviderIds += ", ";
                    }
                    standardProviderNames += psi.systemChineseName;
                    standardProviderIds += psi.systemId;
                }
                invokeList.push(psi);
            }
            for(var i = 0; i < providerList.length; i++){
                var psi = providerList[i];
                if(psi.interfaceId != ""){//如果提供者是非标准接口
                    //添加一条记录
                    var row =  genderRow(consumerNames, consumerNameIds, psi.systemChineseName, psi.systemId, psi.interfaceName, psi.interfaceId);
                    $("#resultList").datagrid("appendRow", row);
                }else{//如果是标准接口
                    for(var j = 0; j < consumerList.length; j++){
                        var csi = consumerList[j];
                        if(csi.interfaceId != ""){ //如果消费者是非标接口
                            var row = genderRow(csi.systemChineseName, csi.systemId, standardProviderNames, standardProviderIds, csi.interfaceName, csi.interfaceId);
                            $("#resultList").datagrid("appendRow", row);
                        }
                    }
                }
            }
            if(standardConsumerIds !="" && standardProviderIds !=""){
                var row = genderRow(standardConsumerNames, standardConsumerIds, standardProviderNames, standardProviderIds, "", "");
                $("#resultList").datagrid("appendRow", row);
            }
            consumerList = new Array();
            providerList = new Array();
            $("#interfaceDlg").dialog("close");
        }
        function genderRow(consumerNames, consumerIds, providerNames, providerIds, interfaceName, interfaceId){
            var row = {};
            row.consumers= consumerNames;
            row.consumerIds= consumerIds;
            row.providers= providerNames;
            row.providerIds= providerIds;
            row.interfaceName= interfaceName;
            row.interfaceId= interfaceId;
            return row;
        }
    </script>

</head>

<body>
<form class="formui" id="operationForm">
    <div class="win-bbar" style="text-align:center"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                                                       onClick="clean()">取消</a><a href="#"
                                                                                  onclick="save('operationForm',1)"
                                                                                  class="easyui-linkbutton"
                                                                                  iconCls="icon-save">保存</a></div>
    <div class="easyui-panel" title="基本信息" style="width:100%;height:auto;padding:10px;">
        <input type="hidden" name="versionId" value="${operation.versionId }" />
        <input type="hidden" name="deleted" value="${operation.deleted }" />
        <input type="hidden" name="state" value="0" />
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>服务代码</th>
                <td><input id="serviceId" name="serviceId" class="easyui-textbox" readonly="true" type="text" value="${service.serviceId }"/></td>

                <th>服务名称</th>
                <td><input class="easyui-textbox" readonly="true" type="text" name="serviceName"
                           value="${service.serviceName }"/>
                </td>
            </tr>
            <tr>
                <th>场景号</th>
                <td><input id="operationId" name="operationId" class="easyui-textbox" type="text" readonly="true" value="${operation.operationId}"
                           data-options="required:true, validType:['unique','english']"/></td>
                <th>场景名称</th>
                <td><input id="operationName" name="operationName" value="${operation.operationName}" class="easyui-textbox" type="text"/></td>
            </tr>
            <tr>
                <th>功能描述</th>
                <td colspan="3"><input id="operationDesc" name="operationDesc" value="${operation.operationDesc}" class="easyui-textbox" style="width:100%"
                                       type="text"/></td>
            </tr>
            <tr>
                <%--<th>场景关键词</th>
                <td><input class="easyui-textbox" disabled="disabled" type="text" name=""/></td>--%>

                <th>状态</th>
                <td>
                    <input
                              class="easyui-combobox"
                              value="${operation.state }"
                              data-options="readonly:true, valueField: 'value',textField: 'label',
						data: [{label: '待审核',value: '0'},
						{label: '审核通过',value: '1'},
						{label: '审核未通过',value: '2'},
						{label: '已发布',value: '3'},
						{label: '已上线',value: '4'},
						{label: '已下线',value: '5'}
							]"
                            />
                </td>
            </tr>
            <tr>
                <th>使用范围</th>
                <td><input class="easyui-textbox" value="${operation.range}" type="text" name="range"/></td>

                <th>备注</th>
                <td><input id="operationRemark" name="operationRemark"  value="${operation.operationRemark}" class="easyui-textbox" type="text"/>
                </td>
            </tr>
            <tr>
                <th>场景关键字:</th>
                <td >
                    <ul id="tags"></ul>
                </td>
                <%--<th>
                    <a href="#" id="saveTagBtn" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:1em">保存</a>
                </th>--%>
            </tr>
        </table>


    </div>


</form>
<div  class="easyui-panel" title="消费者-提供者列表" style="width:100%;height:auto;padding:10px;">
    <table id="resultList" class="easyui-datagrid"
           data-options="
			rownumbers:true,
			singleSelect:true,
			fitColumns:false,
			url:'/operation/getInvokeMapping?serviceId=${operation.serviceId}&operationId=${operation.operationId}',
			method:'get',toolbar:toolbar,
			pagination:true,
				"
           style="height:370px; width:100%;">
        <thead>
        <tr>
            <th data-options="field:'',checkbox:true,width:50"></th>
            <th data-options="field:'consumers',width:250">消费者名称</th>
            <th data-options="field:'consumerIds',width:150">消费者编码</th>
            <th data-options="field:'interfaceName',width:100">接口名称</th>
            <th data-options="field:'interfaceId',width:100">接口代码</th>
            <th data-options="field:'providers',width:250">提供者名称</th>
            <th data-options="field:'providerIds',width:150">提供者编码</th>

        </tr>
        </thead>
    </table>
</div>
<div style="margin-top:10px;"></div>
<div id="interfaceDlg" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
</body>
</html>
