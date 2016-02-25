<table style="width:auto;">
    <tr>
        <td>
            <div style="margin-top:1em;margin-bottom: 1em;">
                <label style="margin-right: 1em;">选择系统:</label>
                <select class="easyui-combobox" id="systemId" name="systemId"
                        style="width:155px" panelHeight="200px"
                        data-options="editable:false">
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <select id="type" panelHeight="auto" style="width:70px" editable="false"/>
                <input class="easyui-textbox" id="searchCondition"/>
                </div>
        </td>
        <td>
            <div style="margin-top:1em;margin-bottom: 1em;">
            <a href="javascript:;" id="selectSysBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
            <a href="javascript:;" id="selectNodeBtn" class="easyui-linkbutton" iconCls="icon-save"
               style="margin-left:2em">选择节点</a>
            </div>
        </td>
    </tr>
    <tr>
             <td>
                    <table title="相邻节点" id="nodeTable" style="height:370px; width:650px; margin-left: 0px">
                        <thead>
                        <tr>
                            <th data-options="field:'productid',checkbox:true"></th>
                            <th data-options="field:'interfaceId'" width="10%">交易码</th>
                            <th data-options="field:'interfaceName'" width="12%">交易名称</th>
                            <th data-options="field:'serviceId'" width="12%">服务码</th>
                            <th data-options="field:'serviceName'" width="12%">服务名称</th>
                            <th data-options="field:'bussCategory'" width="12%">业务分类</th>
                            <th data-options="field:'location'" formatter='formatter.location' width="13%">交易属性标识</th>
                            <th data-options="field:'nodeType'" formatter='formatter.nodeType' width="12%">节点类型</th>
                    </tr>
                    </thead>
                </table>
            </td>
            <td>
                <fieldset style="height: 360px">
                    <legend>节点依赖信息</legend>
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="height: 1em;"></td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>ESB调用方式</NOBR>
                            </th>
                            <td><select name="esbAccessPattern" id="esbAccessPattern"panelHeight="auto" style="width:180px" editable="false"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>条件位</NOBR>
                            </th>
                            <td><input class="easyui-textbox" type="text" name="condition" id="condition" style="width:180px"/></td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>条件信息</NOBR>
                            </th>
                            <td><input class="easyui-textbox" type="text" name="connectionDesc" id="connectionDesc" style="width:180px"/></td>
                        </tr>
                        <tr>
                            <th>
                                <NOBR>组合位</NOBR>
                            </th>
                            <td><input class="easyui-textbox" type="text" name="linkCondition" id="linkCondition" style="width:180px"/></td>
                        </tr>
                    </table>
                </fieldset>
            </td>
    </tr>
    </table>
<script type="text/javascript">
    var formatter = {
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

    $(function () {
        var constructNodeTable = function constructNodeTable(systemId,condition,type) {
            $('#nodeTable').datagrid({
                rownumbers: true,
                singleSelect: true,
                url: "/serviceLink/getServiceLinkNode/" + systemId + "?condition=" + condition+"&type="+type,
                method: 'get',
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
                },
                onClickRow:function(rowIndex,rowData){
                    getNodeInfo(rowData.id);
                }
            });
        }

        $('#systemId').combobox({
            url: '/system/getSystemAll',
            method: 'get',
            mode: 'remote',
            valueField: 'id',
            textField: 'text'
        });
        $("#selectSysBtn").click(function () {
            constructNodeTable($("#systemId").combobox("getValue"),$("#searchCondition").textbox("getValue"),$("#type").combobox("getValue"));
        });
        $("#selectNodeBtn").click(function () {
            var connections = [];
            var checkedItems = $('#nodeTable').datagrid('getChecked');
            var checkedItemNum = checkedItems.length;
            if(checkedItemNum==0){
                alert("请选择节点！");
                return;
            }
            for (var i = 0; i < checkedItemNum; i++) {
                var connection = {};
                connection.sourceId = nodeId;
                connection.targetId = checkedItems[i].invokeId;
                connections.push(connection);
                saveNodeInfoProperties(checkedItems[i].invokeId);
            }

            serviceLinkManager.saveConnections(connections, function () {
                alert("关联成功");
                $("#systemDlg").window('close');
                $("#invokeLinkeTable").datagrid("reload");

            });
        });
        constructNodeTable();
    });
    $('#type').combobox({
        valueField: 'value',
        textField: 'label',
        data: [{
            label: '交易码',
            value: '0',
            selected:true
        },{
            label: '服务码',
            value: '1'
        }]
    });
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
    function getNodeInfo(invokeId){
        $.ajax({
            type: "GET",
            url: "/serviceLink/nodeInfoProperties/"+invokeId,
            success: function(data){
                var esbAccessPattern =data.esbAccessPattern;
                if(esbAccessPattern != ""){
                    $('#esbAccessPattern').combobox('setValue',esbAccessPattern);
                }
                $("#condition").textbox('setValue',data.condition);
                $("#connectionDesc").textbox('setValue',data.connectionDesc);
                $("#linkCondition").textbox('setValue',data.linkCondition);
            }
        });
    }
    function saveNodeInfoProperties(invokeId){
        var nodeProperties = [];
        var esbAccessPattern = {
            "invokeId": invokeId,
            "propertyName": "esbAccessPattern",
            "propertyValue": $('#esbAccessPattern').combobox('getValue')
        };
        var condition = {
            "invokeId": invokeId,
            "propertyName": "condition",
            "propertyValue": $("#condition").val()
        };
        var conditionDesc = {
            "invokeId": invokeId,
            "propertyName": "connectionDesc",
            "propertyValue": $("#connectionDesc").val()
        };
        var linkCondition = {
            "invokeId": invokeId,
            "propertyName": "linkCondition",
            "propertyValue": $("#linkCondition").val()
        };
        nodeProperties.push(esbAccessPattern);
        nodeProperties.push(condition);
        nodeProperties.push(conditionDesc);
        nodeProperties.push(linkCondition);
        serviceLinkManager.saveNodeProperties(nodeProperties, function () {});
    }
</script>

