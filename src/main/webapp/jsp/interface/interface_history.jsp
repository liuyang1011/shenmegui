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
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var formatter = {
            interfaceState: function (value, row, index) {
                if (value == 0) {
                    return "<font color='green'>投产</font>";
                }
                if (value == 1) {
                    return "<font color='red'>废弃</font>";
                }
            },
            versionHis:function(value, row, index){
                try {
                    return row.versionHis.code
                } catch (exception) {
                }
            }
        };
        function operation(value){
            var s = '<a iconcls="icon-save"  style="margin-top:5px;margin-bottom:5px;margin-left:5px;" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)" group="" ><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">idaHis</span><span class="l-btn-icon icon-save">&nbsp;</span></span></a>';
            return s;
        }
        function idaHis(value){
            $('#historyDlg').dialog({
                title: 'ida',
                width: 600,
                height: 400,
                closed: false,
                cache: false,
                href: '/jsp/interface/idaHis.jsp?id='+value,
                modal: true
            });
        }
    </script>
    </head>
<body>

<table id="interfaceHisList" class="easyui-datagrid" data-options="
			rownumbers:true,
			singleSelect:true,
			fitColumns:true,
			url:'/interfaceHis/history/${param.interfaceId}',
			method:'get',
			pagination:true,
				pageSize:10"
       style="height:470px; width:100%;">
    <thead>
    <tr>
        <th data-options="field:'ecode',width:'15%'">
            交易码
        </th>
        <th data-options="field:'interfaceName',width:'25%'">
            交易名称
        </th>
        <th data-options="field:'versionId',width:'15%'" formatter='formatter.versionHis'>
            版本号
        </th>
        <th data-options="field:'optDate',width:'15%',align:'center'">
            更新时间
        </th>
        <th data-options="field:'optUser',width:'10%'">
            更新用户
        </th>
        <th data-options="field:' ',width:'15%', formatter:operation">
            操作
        </th>

    </tr>
    </thead>
</table>
<div id="historyDlg" class="easyui-dialog" closed="true"
     resizable="true"></div>
</body>
</body>
</html>