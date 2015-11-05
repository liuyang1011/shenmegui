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
                    交易码
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="ecode">
                </td>

                <th><nobr>
                    交易名称
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="interfaceName">
                </td>
                <th><nobr>
                    接口功能描述
                </nobr>
                </th>
                <td>
                    <input class="easyui-textbox" type="text" id="remarkSearch">
                </td>
                <!--
                <th>
                    <nobr>
                        状态
                    </nobr>
                </th>
                <td>

                    <select id="statusSearch" class="easyui-combobox"  panelHeight="auto" style="width: 170px"  data-options="editable:false">
                        <option value="">全部</option>
                        <option value="0">投产</option>
                        <option value="1">废弃</option>
                    </select>
                </td>
                -->
                <th>
                    <nobr>
                        系统
                    </nobr>
                </th>
                <td>
                    <input name="systemIdSearch" id="systemIdSearch"  class="easyui-combobox" style="width:150px"
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
            </tr>
            <tr>
                <th><nobr>
                    接口标签
                </nobr>
                </th>
                <td>
                    <select class="easyui-textbox" id="interfaceTag" style="width: 170px">
                    </select>
                </td>
                <th><nobr>
                    报文头
                </nobr>
                </th>
                <td>
                    <select class="easyui-combobox" id="headIdSearch" style="width: 170px" panelHeight="auto" data-options="editable:false">
                    </select>
                </td>
                <th><nobr>
                    通讯协议
                </nobr>
                </th>
                <td>
                    <select class="easyui-combobox" id="protocolIdSearch" style="width: 165px" panelHeight="auto" data-options="editable:false">
                    </select>
                </td>
                <td>
                    &nbsp;
                </td>
                <td align="right">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData();">搜索</a>
                    <a href="#" id="clean" onclick="clearCondition()" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
                </td>
            </tr>
        </table>


    </fieldset>
</form>
<table id="tg" style="height: 370px; width: auto;">
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
        <th data-options="field:'optDate',width:'15%',align:'center'">
            修订时间
        </th>
        <th data-options="field:'optUser'">
            更新用户
        </th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    $(document).ready(function(){
        $('#tg').datagrid({
            title:'基本信息',
            iconCls:'icon-edit',//图标
            width: '100%',
            height: '500px',
            method:'post',
            collapsible: true,
            url:'/interface/getInterface/all' ,
            singleSelect:true,//是否单选
            pagination:true,//分页控件
            pageSize: 15,//每页显示的记录条数，默认为10
            pageList: [15,20,30],//可以设置每页记录条数的列表
            rownumbers:true,//行号
            onDblClickRow : dbClick
        });

        var p = $('#tg').treegrid('getPager');

        $(p).pagination({

            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        });

        $('#protocolIdSearch').combobox({
            url:'/system/getProtocolAll?query=y',
            method:'get',
            mode:'remote',
            valueField:'id',
            textField:'text'
        });

        $('#headIdSearch').combobox({
            url:'/interface/getHeadAll?query=y',
            method:'get',
            mode:'remote',
            valueField:'id',
            textField:'text'
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
        var systemId = $("#systemIdSearch").combobox('getValue');
        if(systemId){
            var url = "/interface/getInterface/" + systemId;
            $('#tg').datagrid({url:url})
        }
        var ecode = $("#ecode").val();
        var interfaceName = $("#interfaceName").val();
        var desc = $("#remarkSearch").val();
//        var status = $("#statusSearch").combobox('getValue');
        var interfaceTag = $("#interfaceTag").textbox('getValue');
        var queryParams = $('#tg').datagrid('options').queryParams;
        queryParams.ecode = ecode;
        queryParams.interfaceName = interfaceName;
        queryParams.desc = encodeURI(desc);
        queryParams.interfaceTag = encodeURI(interfaceTag);
//        queryParams.status = status;
        queryParams.protocolId = $("#protocolIdSearch").combobox('getValue');
        queryParams.headId = $("#headIdSearch").combobox('getValue');
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

    function clearCondition(){
        $('#tg').datagrid({url:"/interface/getInterface/all"})
        $('#searchForm').form('clear');
    }

</script>

</body>
</html>