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
                                                   onClick="tranConfigClose()">取消</a><a href="#" id="saveBtn"
                                                                              onclick="tranConfigSave()"
                                                                              class="easyui-linkbutton"
                                                                              iconCls="icon-save">确定</a></div>
<%--<fieldset>--%>
    <%--<input id="dicUpdate" type="checkbox" onclick="changeDicSyncValue()"/>更新数据字典--%>
<%--</fieldset>--%>
<table id="esbServerList" style="height:500px; width:auto;" title="ESB应用服务器"
       class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:false,url:'/esbServer/getServerList',method:'get',pagination:true,
				pageSize:10,
				onLoadSuccess:function(row){//当表格成功加载时执行
                var rowData = row.rows;
                $.each(rowData,function(idx,val){//遍历JSON
                       $('#esbServerList').datagrid('selectRow', idx);//如果数据行为已选中则选中改行
                });
           }
				">
    <thead>
    <tr>
        <th data-options="field:'serverId',checkbox:true"></th>
        <th field="serverName" width="130px" type="text" align="center">服务器名称</th>
        <th field="serverIp" width="130px" align="center">IP地址</th>
        <th field="state" width="50px" align="center">状态</th>
        <th field="remark" width="130px" align="center">备注</th>
    </tr>
    </thead>
</table>
</body>
</html>
