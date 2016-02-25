<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
<fieldset style="height: 170px;">
    <legend>节点信息</legend>
        <table style="width:100%">
            <tr>
                <td>
                    <table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
                        <tr>
                            <th>
                                <NOBR>节点类型</NOBR>
                            </th>
                            <td><select name="nodeType" id="nodeType" panelHeight="auto" style="width:180px" editable="false"/></td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>交易属性标识</NOBR>
                            </th>
                            <td><select name="location" id="location" panelHeight="auto" style="width:180px" editable="false"/></td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>节点业务分类</NOBR>
                            </th>
                            <td><input style="width: 180px" class="easyui-textbox" type="text" name="bussCategory" id="bussCategory" value="${node.bussCategory}"/></td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
                        <tr>
                            <td style="height: 1em;"></td>
                        </tr>
                            <tr>
                                <th>
                                    <NOBR>交易码</NOBR>
                                </th>
                                <td><input disabled="true" class="easyui-textbox" type="text" name="interfaceId"
                                           id="interfaceId" value="${node.interfaceId}"/>&nbsp;<span style="font-size: small;font-weight: bold">${node.interfaceId==""?"":node.interfaceName}</span></span></td>
                            </tr>
                            <tr>
                                <th>
                                    <NOBR>服务码</NOBR>
                                </th>
                                <td><input disabled="true" class="easyui-textbox" type="text" name="serviceId" id="serviceId"
                                           value="${node.serviceId}"/>&nbsp;<span style="font-size: small;font-weight: bold">${node.serviceId==""?"":node.serviceName}</span></td>
                            </tr>
                            <tr>
                                <th>
                                    <NOBR>场景码</NOBR>
                                </th>
                                <td><input disabled="true" class="easyui-textbox" type="text" name="operationId"
                                           id="operationId" value="${node.operationId}"/>&nbsp;<span style="font-size: small;font-weight: bold">${node.operationId==""?"":node.operationName}</span></td>
                            </tr>
                    </table>
                    <a href="#" id="saveNodeDepBtn" class="easyui-linkbutton" iconCls="icon-save"
                       style="margin-left:0em">保存</a>
                </td>
            </tr>
        </table>
</fieldset>
<table title="服务方信息" id="invokeLinkeTable" style="height:370px; width:99%;">
    <thead>
    <tr>
        <th data-options="field:'productid',checkbox:true"></th>
        <th data-options="field:'interfaceId'" width="8%">交易码</th>
        <th data-options="field:'interfaceName'" width="8%">交易名称</th>
        <th data-options="field:'serviceId'" width="8%">服务码</th>
        <th data-options="field:'serviceName'" width="8%">服务名称</th>
        <th data-options="field:'operationId'"width="8%">场景码</th>
        <th data-options="field:'operationName'" width="8%">场景名称</th>
        <th data-options="field:'invokeType'" width="6%">调用类型</th>

        <th data-options="field:'esbAccessPattern'" formatter='formatter.esbAccessPattern' width="18%">ESB调用方式</th>
        <th data-options="field:'condition'" width="10%">条件位</th>
        <th data-options="field:'connectionDesc'" width="10%">条件信息</th>
        <th data-options="field:'linkCondition'" width="10%">组合位</th>
    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title="" data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;">
</div>
<div id="systemDlg" class="easyui-dialog"
     style="width:600px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
</body>
<script type="text/javascript">
    var nodeId = "${nodeId}";
    var formatter = {
        esbAccessPattern: function (value, row, index) {
            if (value == 0) {
                return "0-穿透模式";
            }
            if (value == 1) {
                return "1-代理模式";
            }
            if (value == 2) {
                return "2-寻址模式";
            }
            if (value == 4) {
                return "4-组合模式";
            }
        },
        location: function (value, row, index) {
            if (value == 1) {
                return "本系统发起";
            }
            if (value == 2) {
                return "上游系统调用";
            }
        },
        nodeType: function (value, row, index) {
            if (value == 0) {
                return "消费方节点";
            }
            if (value == 1) {
                return "提供方节点";
            }
            if (value == 2) {
                return "复合型节点";
            }
        }
    };

    /**
     * 交易链路相邻节点table的ToolBar
     * @type {{text: string, iconCls: string, handler: Function}[]}
     */
    var toolbar = [
        {
            text: "添加",
            iconCls: 'icon-add',
            handler: function () {
                var nodeType=$("#nodeType").combobox("getValue");
                if(nodeType=="1"){
                    alert("此节点不能添加服务方信息！");
                    return;
                }
                $("#systemDlg").dialog({
                    title: '服务方信息',
                    width: 990,
                    height: 520,
                    closed: false,
                    cache: false,
                    left:10,
                    top:15,
                    href: "/jsp/serviceLink/systemSelectDlg.jsp",
                    modal: true
                });
            }
        },{
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                var connections = [];
                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
                var checkedItemNum = checkedItems.length;
                if(checkedItemNum==0){
                    alert("请选择一个节点删除");
                    return;
                }
                for (var i = 0; i < checkedItemNum; i++) {
                    var connection = {};
                    connection.sourceId = nodeId;
                    connection.targetId = checkedItems[i].invokeId;
                    connections.push(connection);
                }
                var flag=confirm("确定删除吗？");
                if(flag) {
                    serviceLinkManager.delConnections(connections, function () {
                        $("#systemDlg").window('close');
                        $("#invokeLinkeTable").datagrid("reload");

                    });
                }
            }
        }, {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
                var checkedItem;
                if (checkedItems != null && checkedItems.length > 0) {
                    if (checkedItems.length > 1) {
                        alert("请选择一个节点进行修改！");
                        return false;
                    }
                    else {
                        checkedItem = checkedItems[0];
                        $("#systemDlg").dialog({
                            title: '节点信息修改',
                            width: 500,
                            height: 280,
                            closed: false,
                            cache: false,
                            href: "/jsp/serviceLink/modifyProperties.jsp?nodeId=" + checkedItem.id,
                            modal: true
                        });
                    }
                }
            }
        }
    ];
    $(function () {
        /**
         *初始化节点相邻节点的table
         */

        $('#invokeLinkeTable').datagrid({
            rownumbers: true,
            singleSelect: true,
            url: '/serviceLink/getTargetNode/sourceId/${nodeId}',
            method: 'get',
            toolbar: toolbar,
            pagination: true,
            pageSize: 10,
            onLoadSuccess: function (data) {
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
                    window.location.href = "/jsp/403.jsp";
                }
            }
        });

        $('#nodeType').combobox({
            valueField: 'value',
            textField: 'label',
            data: [{
                label: '消费方节点',
                value: '0'
            },{
                label: '提供方节点',
                value: '1'
            },{
                label: '复合型节点',
                value: '2'
            }]
        });
        var nodeType = "${node.nodeType}"
        if(nodeType != ""){
            $('#nodeType').combobox('setValue',nodeType);
        }

        $('#location').combobox({
            valueField: 'value',
            textField: 'label',
            data: [{
                label: '本系统发起',
                value: '1'
            },{
                label: '上游系统调用',
                value: '2'
            }]
        });
        var location = "${node.location}"
        if(location != ""){
            $('#location').combobox('setValue',location);
        }

        $('#esbAccessPattern').combobox({
            valueField: 'value',
            textField: 'label',
            data: [{
                label: '0-穿透模式',
                value: '0'
            },{
                label: '1-代理模式',
                value: '1'
            },{
                label: '2-寻址模式',
                value: '2'
            },{
                label: '4-组合模式',
                value: '4'
            }]
        });
        var esbAccessPattern = "${node.esbAccessPattern}"
        if(esbAccessPattern != ""){
            $('#esbAccessPattern').combobox('setValue',esbAccessPattern);
        }
        $("#saveNodeDepBtn").click(function () {
            var nodeProperties = [];
            var nodeType = {
                "invokeId": nodeId,
                "propertyName": "nodeType",
                "propertyValue": $('#nodeType').combobox('getValue')
            };
            var location = {
                "invokeId": nodeId,
                "propertyName": "location",
                "propertyValue": $('#location').combobox('getValue')
            };
            var bussCategory = {
                "invokeId": nodeId,
                "propertyName": "bussCategory",
                "propertyValue": $("#bussCategory").val()
            };
            nodeProperties.push(nodeType);
            nodeProperties.push(location);
            nodeProperties.push(bussCategory);
            serviceLinkManager.saveNodeProperties(nodeProperties, function () {
                alert("保存成功！");
            });
        });
    });


</script>

</body>
</html>