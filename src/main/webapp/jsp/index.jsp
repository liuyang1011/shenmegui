<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta charset="UTF-8">
    <title>服务治理平台</title>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/resources/themes/icon.css">
    <link href="<%=basePath%>/resources/css/css.css" rel="stylesheet" type="text/css">
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" id="header">
    <div id="logo"></div>
    <ul id="nav">
        <li>
            <a href="javascript:;" mid="2">公共信息</a>
        </li>
        <li>
            <a href="javascript:;" mid="6">系统管理</a>
            <%--<ul>--%>
                <%--<li><a href="javascript:;" mid="6">接口管理</a></li>--%>
                <%--&lt;%&ndash;<li><a href="javascript:;" mid="13">接口检索</a></li>&ndash;%&gt;--%>
                <%--<li><a href="javascript:;" mid="3">报文头管理</a></li>--%>
            <%--</ul>--%>
        </li>
        <li>
            <a href="javascript:;">服务管理</a>
            <ul>
                <li><a href="javascript:;" mid="4">服务信息管理</a></li>
                <li><a href="javascript:;" mid="12">服务发布管理</a></li>
                <li><a href="javascript:;" mid="14">服务检索</a></li>
            </ul>
        </li>
        <li>
            <a href="javascript:;" mid="5">交易链路</a>
        </li>
        <li>
            <a href="javascript:;" mid="11">统计报表</a>
        </li>
        <li>
            <a href="javascript:;" mid="1">平台管理</a>
        </li>
    </ul>
    <div id="header_toolsbar">
        <a href="javascript:;" class="login_user"><shiro:principal/></a>
        <a id="taskName" href="javascript:;" style="display: none">当前任务:</a>
        <a href="/logout/">退出</a>
        <a href="javascript:;">帮助</a>
    </div>
</div>
<div data-options="region:'west',collapsible:true,title:'菜单'"
     class="west-menu" id="west-menu">

</div>
<div data-options="region:'center',collapsible:true,border:false,tabHeight:39,"
     class="easyui-tabs " id="mainContentTabs">
</div>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/app.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/ui.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/sysadmin/sysManager.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/sysadmin/interfaceManager.js"></script>
<script type="text/javascript" src="<%=basePath%>/assets/service/js/serviceManager.js"></script>
<script type="text/javascript" src="<%=basePath%>/assets/service/js/serviceUIHelper.js"></script>
<script type="text/javascript" src="<%=basePath%>/assets/service/js/serviceTree.js"></script>
<script type="text/javascript" src="<%=basePath%>/assets/mainContent/js/mainContentUIHelper.js"></script>
<script type="text/javascript" src="<%=basePath%>/assets/mainContent/js/mainContent.js"></script>

<div id="mm-mxmaintabs" class="easyui-menu" style="width: 150px;">
    <div id="maintabsCloseLeft" data-options="iconCls:'icon-clear'">关闭左侧</div>
    <div id="maintabsCloseRight" data-options="iconCls:'icon-clear'">关闭右侧</div>
    <div id="maintabsCloseOhters" data-options="iconCls:'icon-clear'">关闭其他</div>
    <div id="maintabsCloseAll" data-options="iconCls:'icon-clear'">关闭所有</div>
</div>

<div id="mm-mxsysadmintree" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.append()" data-options="iconCls:'icon-add'">
        新增报文头
    </div>
    <%--<div onclick="sysManager.edit()" data-options="iconCls:'icon-edit'">--%>
        <%--编辑报文头--%>
    <%--</div>--%>
    <%--<div onclick="sysManager.remove()" data-options="iconCls:'icon-remove'">--%>
        <%--删除报文头--%>
    <%--</div>--%>
</div>

<div id="mm-syshead" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.append()" data-options="iconCls:'icon-add'">
        新增报文头
    </div>
    <%--<div onclick="sysManager.edit()" data-options="iconCls:'icon-edit'">--%>
    <%--编辑报文头--%>
    <%--</div>--%>
    <div onclick="sysManager.remove()" data-options="iconCls:'icon-remove'">
        删除报文头
    </div>
</div>
<!--服务管理页面，服务树的右键菜单-->
<div id="mm-mxservicetree" class="easyui-menu" style="width: 150px;">
    <div id="serviceTreeAddBtn" data-options="iconCls:'icon-add'">新增</div>
    <div id="serviceTreeEditBtn" data-options="iconCls:'icon-edit'">编辑</div>
    <div id="serviceTreeDeleteBtn" data-options="iconCls:'icon-remove'">删除</div>
    <div id="servicePdf" data-options="iconCls:'icon-excel-export'">导出白皮书PDF</div>
    <div id="serviceExcel" data-options="iconCls:'icon-excel-export'">导出字段映射Excel</div>
    <div id="serviceView" data-options="iconCls:'icon-excel-export'">导出服务视图Excel</div>
</div>

<div id="mm-mslinktree" class="easyui-menu" style="width: 120px;">
    <div onclick="appendSysapi()" data-options="iconCls:'icon-add'">
        新增
    </div>
    <div onclick="editSysapi()" data-options="iconCls:'icon-edit'">
        编辑
    </div>
    <div onclick="removeSysapi()" data-options="iconCls:'icon-remove'">
        删除
    </div>

</div>

<div id="mm-mxsystemtree1" class="easyui-menu" style="width: 120px;">
    <div onclick="interfaceManager.append()" data-options="iconCls:'icon-add'">
        新增接口
    </div>
    <div onclick="sysManager.editSystemPage()" data-options="iconCls:'icon-edit'">
        编辑系统
    </div>
    <div onclick="sysManager.deleteSystem()" data-options="iconCls:'icon-remove'">
        删除系统
    </div>
</div>

<div id="mm-mxsystemtree" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.addSystemPage()" data-options="iconCls:'icon-add'">
        新增系统
    </div>
    <div onclick="sysManager.querySystemPage()" data-options="iconCls:'icon-add'">
        查看系统
    </div>
</div>

<>

<div id="mm-mxinterfacetree1" class="easyui-menu" style="width: 120px;">
    <div onclick="interfaceManager.append()" data-options="iconCls:'icon-add'">
        新增接口
    </div>
    <div onclick="interfaceManager.edit()" data-options="iconCls:'icon-edit'">
        编辑接口
    </div>
    <div onclick="interfaceManager.remove()" data-options="iconCls:'icon-remove'">
        删除接口
    </div>
</div>

<div id="mm-mxprotocols" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.addProtocolPage()" data-options="iconCls:'icon-add'">
        新增协议
    </div>
    <%--<div onclick="interfaceManager.edit()" data-options="iconCls:'icon-edit'">--%>
        <%--编辑协议--%>
    <%--</div>--%>
    <%--<div onclick="interfaceManager.remove()" data-options="iconCls:'icon-remove'">--%>
        <%--删除协议--%>
    <%--</div>--%>
</div>

<div id="mm-mxprotocol" class="easyui-menu" style="width: 120px;">
    <%--<div onclick="sysManager.addProtocolPage()" data-options="iconCls:'icon-add'">--%>
        <%--新增协议--%>
    <%--</div>--%>
    <%--<div onclick="interfaceManager.edit()" data-options="iconCls:'icon-edit'">--%>
    <%--编辑协议--%>
    <%--</div>--%>
    <div onclick="sysManager.deleteProtocol()" data-options="iconCls:'icon-remove'">
    删除协议
    </div>
</div>
<div id="mm-mxfiles" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.refreshFile()" data-options="iconCls:'icon-add'">
        刷新
    </div>
</div>
<div id="mm-mxfile" class="easyui-menu" style="width: 120px;">
    <div onclick="sysManager.deleteFile()" data-options="iconCls:'icon-add'">
        删除文件
    </div>
</div>
</body>
</html>