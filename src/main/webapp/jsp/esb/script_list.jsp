<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>ESB服务器管理</title>
</head>
<body>
<div class="win-bbar" style="text-align:center"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                                                   onClick="closeDialog('#opDialog')">取消</a><a href="#" id="saveBtn"
                                                                              onclick="exportScript()"
                                                                              class="easyui-linkbutton"
                                                                              iconCls="icon-save">确定</a></div>
<fieldset style="margin-top: 20px">
    <table >
        <tr>
            <th style="width:50px"></th>
            <th style="width:300px">脚本类型</th>
            <th style="width:100px">操作</th>
        </tr>
        <tr>
            <td><input id="delScript" type="checkbox"  checked="selected" /></td>
            <td>删除</td>
            <td><a href="javascript:void(0)" onclick="showScript('del')">预览</a></td>
        </tr>
        <%--<tr>--%>
            <%--<td><input id="protocolTypeScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>协议类型</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input id="protocolScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>协议</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input id="adapterFrameScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>适配流程</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input id="serviceSystemScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>服务系统</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input id="chinalScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>渠道</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
        <tr>
            <td><input id="serviceScript" type="checkbox" checked="selected" onclick="showScript('service')"/></td>
            <td>服务</td>
            <td><a href="javascript:void(0)" onclick="showScript('service')">预览</a></td>
        </tr>
        <%--<tr>--%>
            <%--<td><input id="baseServiceScript" type="checkbox" onclick="changeDicSyncValue()"/></td>--%>
            <%--<td>基础服务</td>--%>
            <%--<td>预览</td>--%>
        <%--</tr>--%>
    </table>
</fieldset>
<div id="showDialog" class="easyui-dialog"
     style="width:700px;height:380px;padding:10px 20px" closed="true"
     resizable="true"></div>
<script type="text/javascript">
    function closeDialog(id){
        $(id).dialog("close");
    }
    function showScript(type){
        var operationIds = [];
        var checkedItems = $('#resultList').datagrid('getChecked');
        $.each(checkedItems, function(index, item){
            operationIds.push(item.serviceId + item.operationId);
        });
        $.ajax({
            type: "POST",
            async: false,
            url: "/esbScriptExport/preview",
            data: {
                "type":type,
                "operationIds":operationIds.join(",")
            },
            dataType: "json",
            success: function (result) {
                var content = result.result;
                content = content.replace(new RegExp("\n","gm"), "<br/>");
                $('#showDialog').dialog({
                    title: '预览',
                    width: 700,
                    left:50,
                    closed: false,
                    cache: false,
                    modal: true,
                    content: content
                });
            }
        });
    }
    function exportScript(){
        var operationIds = [];
        var checkedItems = $('#resultList').datagrid('getChecked');
        $.each(checkedItems, function(index, item){
            operationIds.push(item.serviceId + item.operationId);
        });
        var delScript = $("#delScript").attr("checked");
        var protocolTypeScript =  $("#protocolTypeScript").attr("checked");
        var protocolScript =  $("#protocolScript").attr("checked");
        var adapterFrameScript =  $("#adapterFrameScript").attr("checked");
        var serviceSystemScript =  $("#serviceSystemScript").attr("checked");
        var chinalScript =  $("#chinalScript").attr("checked");
        var serviceScript =  $("#serviceScript").attr("checked");
        var baseServiceScript =  $("#baseServiceScript").attr("checked");
        $.fileDownload("/esbScriptExport/checkedExport", {
            httpMethod:"POST",
            data:{
                delScript: delScript,
                protocolTypeScript: protocolTypeScript,
                protocolScript: protocolScript,
                adapterFrameScript: adapterFrameScript,
                serviceSystemScript: serviceSystemScript,
                chinalScript: chinalScript,
                serviceScript: serviceScript,
                baseServiceScript: baseServiceScript,
                operationIds:operationIds.join(",")
            },
            successCallback: function (url) {
                alert("导出成功!")
            }
        });
    }
</script>
</body>
</html>
