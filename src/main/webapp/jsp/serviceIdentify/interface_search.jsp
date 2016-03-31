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
    <script type="text/javascript" src="/js/serviceIdentify/taskManagers.js"></script>

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
                <th>
                    <nobr>
                        识别状态
                    </nobr>
                </th>
                <td>

                    <select id="statusSearch" class="easyui-combobox"  panelHeight="auto" style="width: 170px"  data-options="editable:false">
                        <option value="">全部</option>
                        <option value="5">识别中</option>
                        <option value="6">已识别</option>
                        <option value="a">未识别</option>
                    </select>
                </td>
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
        <th data-options="field:'interfaceId',width:'12%'">
            接口ID
        </th>
        <th data-options="field:'ecode',width:'12%'">
            交易码
        </th>
        <th data-options="field:'interfaceName',width:'20%'">
            交易名称
        </th>
        <th data-options="field:'desc',width:'21%'">
            接口功能描述
        </th>
        <th data-options="field:'status',width:'10%',align:'left'" formatter='formatter.interfaceState'>
            状态
        </th>
        <%--<th data-options="field:'consumerId',width:'10%'">--%>
            <%--消费方--%>
        <%--</th><th data-options="field:'providerId',width:'10%'">--%>
            <%--提供方--%>
        <%--</th>--%>
        <th data-options="field:'optDate',width:'15%',align:'center'">
            修改时间
        </th>
        <th data-options="field:'optUser',width:'10%'">
            修改用户
        </th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    var toolbar = [];
    var Global = {};
    toolbar.push({
        text:'查看详情',
        iconCls:'icon-edit',
        handler: function () {
            var checkedItems = $('#tg').datagrid('getChecked');
            var checkedItem;
            if (checkedItems != null && checkedItems.length > 0) {
                if (checkedItems.length > 1) {
                    alert("请只选中一行要查询的数据！");
                    return false;
                }
                else {
                    checkedItem = checkedItems[0];
                    var url = '/jsp/serviceIdentify/interfaceDefine.jsp';
                    var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'?interfaceId='+checkedItem.interfaceId+'" style="width:100%;height:98%;"></iframe>';
                    title = checkedItem.interfaceName+"("+checkedItem.interfaceId+")";
                    selectTab(title,content);
                }
            }
            else {
                alert("请选中要查询的数据！");
            }

        }
    });

    toolbar.push({
        text:'服务识别',
        iconCls:'icon-edit',
        handler: function () {
            var checkedItems = $('#tg').datagrid('getChecked');
            var checkedItem;
            if (checkedItems != null && checkedItems.length > 0) {
                if (checkedItems.length > 1) {
                    alert("请只选中一行要修改的数据！");
                    return false;
                }
                else {

                    checkedItem = checkedItems[0];
                    uiinit.win({
                        w: 700,
                        height:800,
                        iconCls: 'icon-edit',
                        title: "服务识别",
                        url: "/serviceIdentify/edit/" +checkedItem.ecode+"/"+checkedItem.interfaceId
                    });
                }
            }
            else {
                alert("请选中要修改的数据！");
            }
        }
    });

    toolbar.push({
        text: '查询已关联场景',
        iconCls: 'icon-cfp',
        handler: function () {
            var checkedItems = $('#tg').datagrid('getChecked');
            var checkedItem;
            if (checkedItems != null && checkedItems.length > 0) {
                checkedItem = checkedItems[0];
                var status=checkedItem.status;
                if (checkedItems.length > 1) {
                    alert("请选择一条数据进行查询！");
                    return false;
                }
                else if(status>=5){
                    $.ajax({
                        type:"get",
                        url: "/serviceIdentify/judgeByInterfaceId",
                        dataType: "json",
                        data: {"interfaceId":checkedItem.interfaceId},
                        success: function(data){
                            if(data){
                                uiinit.win({
                                    w: 600,
                                    iconCls: 'icon-cfp',
                                    title: "关联服务场景",
                                    url: "/jsp/serviceIdentify/list.jsp?interfaceId="+checkedItems[0].interfaceId
                                });
                            }
                            else{
                                alert("接口["+checkedItems[0].interfaceId+"]没有关联任何服务场景");
                            }
                        }
                    });
                }
                else{
                    alert("接口状态异常！");
                }
            }
            else {
                alert("请选中需要查询数据！");
            }
        }
    });
//    toolbar.push({
//        text: '提交',
//        iconCls: 'icon-edit',
//        handler: function () {
//            var checkedItems = $('#tg').datagrid('getChecked');
//            var checkedItem;
//            if (checkedItems != null && checkedItems.length > 0) {
//                checkedItem = checkedItems[0];
//                var status=checkedItem.status;
//                if (checkedItems.length > 1) {
//                    alert("请选择一个任务进行提交！");
//                    return false;
//                }
//                else if(status==6){
//                    uiinit.win({
//                        w: 500,
//                        iconCls: 'icon-edit',
//                        title: "提交任务",
//                        url: "/jsp/serviceIdentify/addTask.jsp"
//                    });
//                }
//                else{
//                    alert("接口状态异常！");
//                }
//            }
//            else {
//                alert("请选中需要提交数据！");
//            }
//        }
//    });
    $(document).ready(function(){
        $('#tg').datagrid({
            title:'接口信息列表',
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
    var url = '/jsp/serviceIdentify/interfaceDefine.jsp';
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
        var status = $("#statusSearch").combobox('getValue');
        var queryParams = $('#tg').datagrid('options').queryParams;
        queryParams.ecode = ecode;
        queryParams.interfaceName = interfaceName;
        queryParams.status = status;
        $('#tg').datagrid('options').queryParams = queryParams;//传递值
        $("#tg").datagrid('reload');//重新加载table
    }

    var formatter = {
        interfaceState: function (value, row, index) {
            if (value == 5) {
                return "<font color='green'>识别中</font>";
            }
            if (value == 6) {
                return "<font color='red'>已识别</font>";
            }else if (value == "a") {
                return "<font color='green'>未识别</font>";
            }
        },
        version: function(value, row, index){
            try {
                return row.version.code
            } catch (exception) {
            }
        }
    };

    function selectTab(title, content) {
        var mainTabs = parent.$('#mainContentTabs');
        var exsit = parent.$('#mainContentTabs').tabs('getTab',title);
        if(exsit==null){
            mainTabs.tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        } else {
            mainTabs.tabs('update', {
                tab: exsit,
                options: {
                    content: content
                }
            });
            mainTabs.tabs('select', title);
        }
    }
    function clearCondition(){
        $('#tg').datagrid({url:"/interface/getInterface/all"})
        $('#searchForm').form('clear');
    }

</script>

</body>
</html>