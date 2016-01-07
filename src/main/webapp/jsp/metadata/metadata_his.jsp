<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>
<table id="metadataVersionList" title="数据字典历史版本"
        style="height:620px; width:98%;">
    <thead>
    <tr>
        <th data-options="field:'',checkbox:true"></th>
        <th data-options="field:'versionNo'" width="15%">版本号</th>
        <th data-options="field:'versionDesc'" width="30%">说明</th>
        <th data-options="field:'optUser'" width="15%">发布人</th>
        <th data-options="field:'optDate'" width="15%">发布日期</th>
        <th data-options="field:'id', formatter:formatConsole" width="20%">操作</th>
    </tr>
    </thead>
</table>
<div id="opDialog" class="easyui-dialog"
     style="width:700px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/assets/metadata/metadataManager.js"></script>
<script type="text/javascript" src="/assets/metadata/metadata.js"></script>
<scirpt type="text/javascript" src="/resources/js/jquery.fileupload.js"></scirpt>
<script type="text/javascript" src="/resources/js/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(function(){
        $("#metadataVersionList").datagrid({
            rownumbers:true,
            singleSelect:false,
            url:'/metadataVersion/query',
            method:'get',
            pagination:true,
            pageSize:20,
            pageList: [20,30,50],
            fitColumns:'false',
            onLoadError: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
//                    alert("没有权限！");
                    window.location.href = "/jsp/403.jsp";
                }
            }
        });
    });
    //操作按钮
    function formatConsole(value, row, index){
        var s = '<a onclick="showRecord(\'' + value + '\')" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)" id="cancelbtn'+value+'">' +
                '<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">修订记录</span></span></a>&nbsp;&nbsp;'
//                '<a class="easyui-linkbutton l-btn l-btn-small"  href="/metadataVersion/exportExcel?id='+ value + '" id="comparebtn'+value+'">' +
//                '<span class="l-btn-text">导出EXCEL</span>&nbsp;</span></span></a>';
        return s;

    }
    // 展示修订记录
    function showRecord(id){
        var urlPath = "/jsp/metadata/metadata_record.jsp?id="+id;
        $('#opDialog').dialog({
            title: '修订记录',
            width: 900,
            left:150,
            closed: false,
            cache: false,
            href: urlPath,
            modal: true
        });
    }
    //下载EXCEL
    function exportExcel(id){

    }
</script>
</body>
</html>