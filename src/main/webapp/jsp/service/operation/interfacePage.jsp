<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/jsp/service/operation/operation.js"></script>
    <script type="text/javascript">
        var serviceId;
        $(function () {
            var url = '/serviceLink/getInterfaceByOSS?operationId=${operation.operationId }&serviceId=${service.serviceId }';
            $("#invokeList").datagrid({
                rownumbers: true,
                singleSelect: true,
                fixed: false,
                url: url,
                method: 'get',
                pagination: true,
                toolbar: '#tb',
                striped: true,
                pageSize: 10,
                onClickRow: function () {
                    relateInterface();
                }
            });
        });
        var item;
        var toolbar = [
            <shiro:hasPermission name="ida-get">
            {
                text: '关联SDA',
                iconCls: 'icon-qxfp',
                handler: function () {
                    //标准的没有映射功能
                    if (item.isStandard == 0) {
                        alert("标准接口没有映射");
                        return false;
                    }
                    /*var selectData = $('#ida').datagrid('getSelected');
                     if (selectData == null) {
                     alert("请先选择一条记录");
                     return;
                     }*/
                    serviceId = $("#1").textbox('getValue');
                    var operationId = $("#3").textbox('getValue');
                    var url = '/ida/idaMapping/' + serviceId + '/' + operationId + '/' + item.interfaceId + '/' + item.systemId ;
                    var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '"  style="width:100%;height:100%;"></iframe>';
                    var title = "ida映射元数据";

                    if (parent.$('#subtab').tabs('exists', title)) {
                        parent.$('#subtab').tabs('close', title);
                        parent.$('#subtab').tabs('update', {
                            "title": title,
                            "content": content,
                            "closable": true
                        });
                    } else {
                        parent.$('#subtab').tabs('add', {
                            "title": title,
                            "content": content,
                            "closable": true
                        });
                    }
                }
            }
            </shiro:hasPermission>
        ];
        //根据已选中的接口关系在“接口映射”区域更新接口信息
        function relateInterface() {
            item = $('#invokeList').treegrid('getSelected');
            if (item != null) {
                if (item.interfaceId == null || item.interfaceId == '') {
                    alert("该系统没有关联接口！");
                    return false;
                }
                else {
                    $('#ida').treegrid({
                        url: '/ida/getInterfaces/' + item.interfaceId + "?time=" + (new Date()).valueOf()
                    });
                }
            }
        }
        //根据已选中的sda的元数据替换“接口映射”区域中对应选中接口的元数据。
        function replaceMetadataId() {
            var sda = $("#sda").treegrid("getSelected");
            if (sda != null) {
                if (sda.text == "root" || sda.text == "request" || sda.text == "response") {
                    alert("请选择其他节点!");
                    return false
                }
                var ida = $("#ida").treegrid("getSelected");
                if (ida != null) {
                    if (ida.structName == "root" || ida.structName == "request" || ida.structName == "response") {
                        alert("请选择其他节点!");
                        return false
                    }

                    if (sda.append4 != null) {
                        $.ajax({
                            type: "post",
                            async: false,
                            url: "/ida/updateMetadataId",
                            dataType: "json",
                            data: {"metadataId": sda.append4, "id": ida.id},
                            success: function (data) {
                                if (data) {
                                    $('#ida').treegrid("reload");
                                }
                            }
                        });
                    }
                }
            }
        }

        function standardStyle(value, row, index) {
            if(row.isStandard == '99'){
                return 'background-color:#d9d2e9;color:white';
            }

        }
    </script>

</head>

<body>
<fieldset>
    <div>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>
                    <nobr>服务代码</nobr>
                </th>
                <td><input class="easyui-textbox" type="text" name="1" id="1"
                           value="${service.serviceId }" disabled="disabled">
                </td>
                <th>
                    <nobr>服务名称</nobr>
                </th>
                <td><input class="easyui-textbox" type="text" name="2" id="2"
                           value="${service.serviceName }" disabled="disabled">
                </td>
                <th>
                    <nobr>场景号</nobr>
                </th>
                <td><input class="easyui-textbox" type="text" name="3" id="3"
                           value="${operation.operationId }" disabled="disabled">
                </td>
                <th>
                    <nobr>场景名称</nobr>
                </th>
                <td><input class="easyui-textbox" type="text" name="4" id="4"
                           value="${operation.operationName }" disabled="disabled">
                </td>
            </tr>
            <tr>
                <th>
                    <nobr>映射关系列表</nobr>
                </th>
                <td></td>
            </tr>
        </table>
    </div>
    <div>
        <table id="invokeList" style="height:200px; width:100%;">
            <thead>
            <tr>
                <%--<th data-options="field:'invokeId',checkbox:true"></th>--%>
                <th data-options="field:'systemId', width:'10%'">系统id</th>
                <th data-options="field:'systemChineseName', width:'15%'">系统名称</th>
                <th data-options="field:'isStandard', width:'10%', styler:standardStyle"
                    formatter='ff.isStandardText'>标准
                </th>
                <th data-options="field:'interfaceId', width:'15%', styler:standardStyle">接口id</th>
                <th data-options="field:'interfaceName', width:'15%', styler:standardStyle">接口名称</th>
                <th data-options="field:'type', width:'10%'"
                    formatter='ff.typeText'>类型
                </th>
                <th data-options="field:'desc', width:'10%'">描述</th>
                <th data-options="field:'remark', width:'10%'">备注</th>
            </tr>
            </thead>
        </table>

    </div>
    <%--<div id="tb" style="padding:5px;height:auto">
            <a href="javascript:void(0);" onclick="relateInterface()" class="easyui-linkbutton" iconCls="icon-save" plain="true">关联映射结果</a>
    </div>--%>
</fieldset>
<fieldset>
    <div>
        <table width="100%">
            <tr>
                <td width="50%" style="display: none">
                    接口需求
                </td>
                <td width="50%">
                    映射结果
                </td>
            </tr>
            <tr>
                <td colspan=2 width="100%">
                    <div style="width:40%;padding:1px; margin-top:0; float:left;display: none">
                        <table id="sda" title="sda" class="easyui-treegrid" id="tg" style=" width:auto;"
                               data-options="
                                iconCls: 'icon-ok',
                                rownumbers: true,
                                animate: true,
                                fitColumns: false,
                                url: '/sda/sdaTree?serviceId=${service.serviceId }&operationId='+encodeURI(encodeURI('${operation.operationId }')),
                                method: 'get',
                                idField: 'id',
                                treeField: 'text'
                                "
                                >
                            <thead>
                            <tr>
                                <th data-options="field:'id',checkbox:true"></th>
                                <th data-options="field:'text',width:180,editor:'text'">字段名</th>
                                <th data-options="field:'append1',width:130,align:'right',editor:'text'">字段别名</th>
                                <th data-options="field:'append2',width:80,editor:'text'">类型</th>
                                <%--<th data-options="field:'append3',width:80,editor:'text'">长度</th>
                                <th data-options="field:'append4',width:80,editor:'text'">元数据</th>--%>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div style="width:15px; float:left;text-align:center; display: none">
                        <table style="margin:auto; margin-top:20px">
                            <tr>
                                <td><a href="#" title="元数据关联" class="easyui-linkbutton" iconCls="icon-select-add"
                                       onClick="replaceMetadataId()"></a></td>
                            </tr>
                        </table>
                    </div>
                    <div style="width:100%;padding:1px; margin-top:0; float:left">
                        <table title="接口定义信息" id="ida"
                               class="easyui-treegrid"
                               data-options="
                                                                iconCls:'icon-edit',
                                                                rownumbers: false,
                                                                animate: true,
                                                                fitColumns: false,
                                                                method: 'get',
                                                                idField: 'id',
                                                                treeField: 'structName',
                                                                singleSelect:true,
                                                                collapsible:true,
                                                                toolbar:toolbar
                                                            ">
                            <thead>
                            <tr>
                                <%--<th data-options="field:'id',checkbox:true"></th>--%>
                                <th
                                        data-options="field:'structName',width:'20%',align:'left',editor:'text'">
                                    字段名称
                                </th>
                                <th
                                        data-options="field:'structAlias',width:'16%',align:'left',editor:'text'">
                                    字段别名
                                </th>
                                <th data-options="field:'type',width:'6%',editor:'text'">
                                    类型
                                </th>
                                <th data-options="field:'length',width:'6%',editor:'text'">
                                    长度
                                </th>
                                <th data-options="field:'metadataId',width:'20%',editor:'text'">
                                    元数据ID
                                </th>
                                <th data-options="field:'remark',width:'16%',editor:'text'">
                                    备注
                                </th>
                                <th data-options="field:'required',width:'10%',editor:'text'">
                                    是否必须
                                </th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
</fieldset>

</body>
</html>
