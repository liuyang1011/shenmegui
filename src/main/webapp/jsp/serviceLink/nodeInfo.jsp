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
<table style="width:100%">
    <tr>
        <td>
            <fieldset style="height: 230px;">
                <legend>交易属性</legend>
                <table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
                    <tr>
                        <%--<th>接口ID</th>--%>
                        <th>
                            <NOBR>交易码</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="interfaceId"
                                   id="interfaceId" value="${node.interfaceId}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>交易名称</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="interfaceName"
                                   id="interfaceName" value="${node.interfaceName}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>服务码</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="serviceId" id="serviceId"
                                   value="${node.serviceId}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>服务名称</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="serviceName"
                                   id="serviceName" value="${node.serviceName}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>场景码</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="operationId"
                                   id="operationId" value="${node.operationId}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>场景名称</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="operationName"
                                   id="operationName" value="${node.operationName}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>调用类型</NOBR>
                        </th>
                        <td><input disabled="true" class="easyui-textbox" type="text" name="invokeType" id="invokeType"
                                   value="${node.invokeType}"/></td>
                    </tr>
                </table>


            </fieldset>
        </td>
        <td>
            <fieldset style="height: 230px">
                <legend>节点属性</legend>
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th>
                            <NOBR>节点类型</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="nodeType" id="nodeType"
                                   value="${node.nodeType}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>交易属性标识</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="location" id="location"
                                   value="${node.location}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>节点业务分类</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="bussCategory" id="bussCategory"
                                   value="${node.bussCategory}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>节点状态</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="status" id="status" value="${node.status}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="height: 1em;"></td>
                    </tr>
                    <tr>
                        <td style="height: 1em;"></td>
                    </tr>
                    <tr>
                        <th>
                            <a href="#" id="saveNodeInfoBtn" class="easyui-linkbutton" iconCls="icon-save"
                               style="margin-left:1em">保存</a>
                        </th>
                        <td>
                            <a href="#" id="refreshNodeInfoBtn" class="easyui-linkbutton" iconCls="icon-save"
                               style="margin-left:1em">清空</a>
                        </td>
                    </tr>
                </table>


            </fieldset>
        </td>
        <td>
            <fieldset style="height: 230px">
                <legend>节点依赖信息</legend>
                <table>
                    <tr>
                        <th>
                            <NOBR>ESB标识</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="esbAccessPattern" id="esbAccessPattern"
                                   value="${node.esbAccessPattern}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>条件位</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="condition" id="condition"
                                   value="${node.condition}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <NOBR>条件信息</NOBR>
                        </th>
                        <td><input class="easyui-textbox" type="text" name="connectionDesc" id="connectionDesc"
                                   value="${node.connectionDesc}"/></td>
                    </tr>
                    <tr>
                        <td style="height: 1em;"></td>
                    </tr>
                    <tr>
                        <td style="height: 1em;"></td>
                    </tr>
                    <tr>
                        <td style="height: 1em;"></td>
                    </tr>
                    <tr>
                        <th>
                            <a href="#" id="saveNodeDepBtn" class="easyui-linkbutton" iconCls="icon-save"
                               style="margin-left:1em">保存</a>
                        </th>
                        <td>
                            <a href="#" id="refreshNodeDepBtn" class="easyui-linkbutton" iconCls="icon-save"
                               style="margin-left:1em">清空</a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </td>
    </tr>
</table>

<table title="相邻节点" id="invokeLinkeTable" style="height:370px; width:auto;">
    <thead>
    <tr>
        <th data-options="field:'productid',checkbox:true"></th>
        <th colspan="7">交易属性</th>
        <th colspan="4">节点属性</th>
        <th colspan="3">节点依赖信息</th>

    </tr>
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
        <th data-options="field:'location'">交易属性标识</th>
        <th data-options="field:'bussCategory'">节点业务分类</th>
        <th data-options="field:'status'">节点状态</th>

        <th data-options="field:'esbAccessPattern'">ESB调用方式</th>
        <th data-options="field:'condition'">条件位</th>
        <th data-options="field:'conditionDesc'">条件信息</th>
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

    /**
     * 交易链路相邻节点table的ToolBar
     * @type {{text: string, iconCls: string, handler: Function}[]}
     */
    var toolbar = [
        {
            text: "添加",
            iconCls: 'icon-qxfp',
            handler: function () {
                $("#systemDlg").dialog({
                    title: '选择接口',
                    width: 700,
                    height: 500,
                    closed: false,
                    cache: false,
                    href: "/jsp/serviceLink/systemSelectDlg.jsp",
                    modal: true
                });
            }
        }, {
            text: "删除",
            iconCls: 'icon-qxfp',
            handler: function () {
                var connections = [];
                var checkedItems = $('#invokeLinkeTable').datagrid('getChecked');
                var checkedItemNum = checkedItems.length;
                for (var i = 0; i < checkedItemNum; i++) {
                    var connection = {};
                    connection.sourceId = nodeId;
                    connection.targetId = checkedItems[i].invokeId;
                    connections.push(connection);
                }

                serviceLinkManager.delConnections(connections, function () {
                    alert("删除成功");
                    $("#systemDlg").window('close');
                    $("#invokeLinkeTable").datagrid("reload");

                });
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


        $("#saveNodeInfoBtn").click(function () {
            var nodeProperties = [];
            var nodeType = {
                "invokeId": nodeId,
                "propertyName": "nodeType",
                "propertyValue": $("#nodeType").val()
            };
            var location = {
                "invokeId": nodeId,
                "propertyName": "location",
                "propertyValue": $("#location").val()
            };
            var bussCategory = {
                "invokeId": nodeId,
                "propertyName": "bussCategory",
                "propertyValue": $("#bussCategory").val()
            };
            var status = {
                "invokeId": nodeId,
                "propertyName": "status",
                "propertyValue": $("#status").val()
            };
            nodeProperties.push(nodeType);
            nodeProperties.push(location);
            nodeProperties.push(bussCategory);
            nodeProperties.push(status);
            serviceLinkManager.saveNodeProperties(nodeProperties, function () {
                alert("保存成功！");
            });

        });

        $("#saveNodeDepBtn").click(function () {
            var nodeProperties = [];
            var esbAccessPattern = {
                "invokeId": nodeId,
                "propertyName": "esbAccessPattern",
                "propertyValue": $("#esbAccessPattern").val()
            };
            var condition = {
                "invokeId": nodeId,
                "propertyName": "condition",
                "propertyValue": $("#condition").val()
            };
            var conditionDesc = {
                "invokeId": nodeId,
                "propertyName": "connectionDesc",
                "propertyValue": $("#connectionDesc").val()
            };

            nodeProperties.push(esbAccessPattern);
            nodeProperties.push(condition);
            nodeProperties.push(conditionDesc);
            serviceLinkManager.saveNodeProperties(nodeProperties, function () {
                alert("保存成功！");
            });
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
    });


</script>

</body>
</html>