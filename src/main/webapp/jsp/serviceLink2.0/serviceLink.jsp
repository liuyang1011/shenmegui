<!DOCTYPE>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String sourceId = request.getParameter("sourceId");
%>
<script type="text/javascript">
    var sourceId = "<%=sourceId%>";
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width">
    <%--<link rel="shortcut icon" href="/img/favicon.ico">--%>
    <script src="/resources/js/jquery.min.js"></script>
    <title>交易链路</title>
    <%--<link href="//fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">--%>
    <link href="/plugin/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/plugin/font-awesome.css" rel="stylesheet">
    <link href="/assets/serviceLink2.0/main.css" rel="stylesheet">
    <link href="/assets/serviceLink2.0/gollum-template.css" rel="stylesheet">


    <link rel="stylesheet" href="/assets/serviceLink2.0/jsPlumbToolkit-defaults.css">
    <link rel="stylesheet" href="/assets/serviceLink2.0/jsPlumbToolkit-demo.css">
<script type="text/javascript" >
    var selectedNode;
</script>

</head>

<body>
<script type="text/x-jtk-templates" src="/assets/serviceLink2.0/template.html"></script>

<div class="full-width-container">
    <div class="container">
        <div class="row">
            <div class="divider-30"></div>
            <div class="clearfix"></div>

            <div id="jtk-demo-flowchart" class="jtk-demo-main" style="width:100%;margin-top:-4px;">
                <!-- this is the main drawing area -->
                <div class="jtk-demo-canvas" id="jtk-demo-canvas">
                    <!-- controls -->
                    <div class="controls">
                        <i class="fa fa-arrows selected-mode" mode="pan" title="Pan Mode"></i>
                        <i class="fa fa-pencil" mode="select" title="Select Mode"></i>
                        <i class="fa fa-home" reset title="Zoom To Fit"></i>
                    </div>
                    <!-- miniview -->
                    <div class="miniview"></div>
                </div>
                <!-- the current dataset -->
                <div class="jtk-demo-dataset" id="jtk-demo-dataset"></div>
            </div>
        </div>
    </div>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div id="baseInfo" onclick="contextMenuManager.baseInfo()" data-options="iconCls:'icon-detail'">基本信息</div>
    <div id="messageProtocol" onclick="contextMenuManager.messageProtocol()" data-options="iconCls:'icon-detail'">报文协议</div>
    <div id="serviceInfo" onclick="contextMenuManager.serviceInfo()" data-options="iconCls:'icon-detail'">服务信息</div>
    <div id="interfaceInfo" onclick="contextMenuManager.interfaceInfo()" data-options="iconCls:'icon-detail'">接口信息</div>
    <div id="openNode" onclick="contextMenuManager.openNode();" data-options="iconCls:'icon-detail'">打开节点</div>
    <div id="closeNode" onclick="contextMenuManager.closeNode()" data-options="iconCls:'icon-detail'">关闭节点</div>
</div>
<div id="opDialog" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>
<script type="text/javascript" src="/assets/serviceLink2.0/contextMenu.js"></script>
<script src="/assets/serviceLink2.0/jsPlumb-1.7.10.js"></script>
<script src="/assets/serviceLink2.0/jsPlumbToolkit-1.0.5.js"></script>
<script src="/assets/serviceLink2.0/app.js"></script>
<link rel="stylesheet" href="/assets/serviceLink2.0/app.css">
<script src="/resources/js/jquery.validate.js"></script>
<script src="/resources/js/additional-methods.js"></script>
<script src="/plugin/braintree.js"></script>
<link rel="stylesheet" type="text/css"
      href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
</body>
</html>