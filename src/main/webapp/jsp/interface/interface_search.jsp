<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/js/sysadmin/interfaceManager.js"></script>
</head>

<body>
<form id="searchForm">
    <fieldset>
        <legend>条件搜索</legend>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th><nobr>
                    接口ID
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="interfaceId"  style="width:100px">
                </td>
                <th><nobr>
                    交易码
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="ecode" style="width:100px">
                </td>

                <th><nobr>
                    交易名称
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="interfaceName" style="width:100px">
                </td>
                <th>
                    <nobr>归属系统</nobr>
                </th>
                <td><input name="systemId" id="systemId" class="easyui-combobox"
                           data-options="
                 method:'get',
                 url:'/system/getSystemAll',
                 textField:'chineseName',
                 valueField:'id',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
				    }
                 "
                        >
                </td>
                <td align="right">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData();">搜索</a>
                    <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
                </td>
            </tr>
        </table>


    </fieldset>
</form>
<table id="tg" style="width: auto;">
    <thead>
    <tr>
        <th data-options="field:'',checkbox:true"></th>
        <th data-options="field:'interfaceId',width:'10%'">
            接口ID
        </th>
        <th data-options="field:'ecode',width:'10%'">
            交易码
        </th>
        <th data-options="field:'interfaceName',width:'15%'">
            交易名称
        </th>
        <th data-options="field:'systemChineseName',width:'15%'">
            归属系统
        </th>
        <th data-options="field:'desc',width:'15%'">
            接口功能描述
        </th>
        <th data-options="field:'headName',width:'15%'">
            报文头
        </th>
        <th data-options="field:'protocolName',width:'10%'">
            接口协议
        </th>
        <th data-options="field:'status',width:'9%',align:'left'" formatter='formatter.interfaceState'>
            状态
        </th>
        <th data-options="field:'versionId',width:'10%'" formatter='formatter.version'>
            版本号
        </th>
    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width: 500px; height: 200px; padding: 10px;">
</div>
<div id="releaseDlg" class="easyui-dialog" closed="true" resizable="true"></div>
<script type="text/javascript">
    var toolbar = [];
    toolbar.push({
        text: '历史版本',
        iconCls: 'icon-qxfp',
        handler: function () {
            var row = $("#tg").datagrid("getSelected");
            if(row){
                var urlPath =  '/jsp/interface/interface_history.jsp?interfaceId='+row.interfaceId;
                var hisContent = ' <iframe scrolling="auto" frameborder="0"  src="' + urlPath + '" style="width:100%;height:100%;"></iframe>'

                parent.$('#mainContentTabs').tabs('add', {
                    title: '接口发布历史',
                    content: hisContent,
                    closable: true
                });
            }else{
                alert("请先选一个接口");
            }

        }
    });

    toolbar.push({
        text: '关联的服务场景',
        iconCls: 'icon-cfp',
        handler: function () {
            var row = $("#tg").datagrid("getSelected");
            if(row){
                //判断接口是否有服务调用
                $.ajax({
                    type: "get",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    url: "/serviceLink/contionOperation",
                    dataType: "json",
                    data: {"interfaceId": row.interfaceId},
                    success: function (data) {
                        if(data){
                            var urlPath = "/jsp/sysadmin/relate_operation.jsp?interfaceId="+row.interfaceId;
                            $('#releaseDlg').dialog({
                                title: '关联的服务场景(双击查看详细)',
                                width: 500,
                                left:150,
                                top:50,
                                closed: false,
                                cache: false,
                                href: urlPath,
                                modal: true
                            });
                        }else{
                            alert("该接口没有被调用!");
                        }
                    }
                });

            }
            else{
                alert("请选中一行数据!");
            }
        }
    });


    $(document).ready(function(){
        $('#tg').datagrid({
            title:'接口列表(双击表格行查看接口内容)',
            iconCls:'icon-edit',//图标
            width: '100%',
            method:'post',
            collapsible: true,
            url:'/interface/getInterface/all',
            singleSelect:true,//是否单选
            pagination:true,//分页控件
            pageSize: 20,//每页显示的记录条数，默认为10
            pageList: [20,50,100],//可以设置每页记录条数的列表
            rownumbers:true,//行号
            toolbar: toolbar,
            onDblClickRow : dbClick
        });

        var p = $('#tg').treegrid('getPager');

        $(p).pagination({

            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        });
    })
    var url = '/jsp/interface/interface_define.jsp';
    var dbClick = function(index,field,value){
        var title = "";
        title = field.interfaceName+"("+field.interfaceId+")";
        var mainTabs = parent.$('#mainContentTabs');
        if (mainTabs.tabs('exists', title)) {
            mainTabs.tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'?interfaceId='+field.interfaceId+'" style="width:100%;height:98%;"></iframe>';
            mainTabs.tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
    }

    function searchData(){

        var interfaceId  = $("#interfaceId").val();
        var ecode = $("#ecode").val();
        var interfaceName = $("#interfaceName").val();
        var systemId = $("#systemId").combobox("getValue");
        var queryParams = $('#tg').datagrid('options').queryParams;
        queryParams.interfaceId = interfaceId;
        queryParams.ecode = ecode;
        queryParams.interfaceName = interfaceName;
        queryParams.systemId = systemId;

        $('#tg').datagrid('options').queryParams = queryParams;//传递值
        $("#tg").datagrid('reload');//重新加载table
    }

    var formatter = {
        interfaceState: function (value, row, index) {
            if (value == 0) {
                return "<font color='green'>投产</font>";
            }
            if (value == 1) {
                return "<font color='red'>废弃</font>";
            }
        },
        version: function(value, row, index){
            try {
                return row.version.code
            } catch (exception) {
            }
        }
    };
</script>

</body>
</html>