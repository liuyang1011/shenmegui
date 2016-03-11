<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript">

    </script>
</head>
<style>
    #notice-panel {
        height: 271px
    }

    #notice {
        height: 271px;
        padding-left: 50px;
        width: 513px;
    }

    #guide {
        height: 271px;
        width: 513px;
        margin-right: 50px;
    }

    .notice-h1 {
        margin-top: 30px;
        margin-bottom: 30px;
    }

    .notice-h2 {
        margin-top: 15px;
        margin-bottom: 20px;
    }

    #flow-panel {
        height: 271px;
        margin-left: 50px
    }

    .clear {
        clear: both;
        height: 0;
        overflow: hidden;
    }
</style>

<body>
<div id="userId" style="display: none"><shiro:principal/></div>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" id="notice-panel">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'west', border:false" id="notice">
                <div class="notice-h1">
                    <h1>通知栏</h1>
                </div>
                <div class="notice-h2">
                    <h2>共建全连接世界,拥抱新商业文明</h2>
                </div>
                <div>
                    <p style="line-height:150%">全连接的智慧世界驱动着新的商业文明,唯有洞察和拥抱新的变化，才能适应拥有无限可能的未来。</p>
                </div>
                <%--<div>--%>
                <%--<p>2015-07-25</p>--%>
                <%--</div>--%>
                <div class="notice-h2">
                    <h2>《营赢》第22期</h2>
                </div>
                <div>
                    <p style="line-height:150%">本期封面:加拿大Bell计划将传统网络转换为开放的,以API为中心的网络,Bell称之为网络3.0。</p>
                </div>
            </div>
            <div data-options="region: 'center',border:false">
                <img src="">
            </div>
            <div data-options="region: 'east',border:false" id="guide">
                <div class="notice-h1">
                    <h1>帮助说明</h1>
                    <img src="/jsp/esc/adv.png">
                </div>
            </div>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <div style="margin-left: 50px;margin-right: 50px;">
            <hr style="border:1px dashed #000; height:1px">
        </div>
        <div class="notice-h1" style="margin-left: 50px">
            <h1>管理流程图</h1>
        </div>
        <div style="background:url('/jsp/esc/flow1.png') no-repeat;width:160px;height:103px;margin-left: 50px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow4.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">02 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow5.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">03 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow6.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">04 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow7.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">05 步骤说明</font></div>
        <div class="clear"></div>
        <div class="notice-h1" style="margin-left: 50px">
            <h1>交易流程图</h1>
        </div>
        <div style="background:url('/jsp/esc/flow1.png') no-repeat;width:160px;height:103px;margin-left: 50px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow2.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow3.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow4.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>
        <div style="background:url('/jsp/esc/flow5.png') no-repeat;width:160px;height:103px;margin-left: 10px;padding: 20px; float:left;">
            <font size="3" color="#ffffff">01 步骤说明</font></div>

    </div>
</div>
</body>
</html>