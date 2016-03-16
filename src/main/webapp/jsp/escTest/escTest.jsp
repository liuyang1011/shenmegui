<%--
  Created by IntelliJ IDEA.
  User: vincentfxz
  Date: 16/3/13
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>服务测试</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script src="/newui/plugins/jQueryUI/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/plugin/aehlke-tag-it/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <style type="text/css">
        <!--
        ul#tags li {
            font-size: 1.4em;
            font-weight: bold;
        }

        -->
    </style>
</head>
<body>
<div style="padding: 1em">
    <fieldset>
        <table>
            <tr>
                <td>
                    <label for="protocol">协议类型:</label>
                </td>
                <td>
                    <select id="protocol" class="easyui-combobox" style="width:200px;">
                        <option value="TCP">TCP</option>
                        <option value="HTTP">HTTP</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="encoding">编码类型:</label>
                </td>
                <td>
                    <select id="encoding" class="easyui-combobox" style="width:200px;">
                        <option value="GBK">GBK</option>
                        <option value="UTF-8">UTF-8</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="headLen">长度头长度:</label>
                </td>
                <td>
                    <input id="headLen" class="easyui-textbox" type="text" style="width:200px" name="headLen">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="address">访问地址:</label>
                </td>
                <td>
                    <input id="address" class="easyui-textbox" type="text" style="width:500px" name="address">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="reqMessage">请求报文:</label>
                </td>
                <td>
                    <textarea id="reqMessage" style="width:500px;height:200px;overflow:scroll;"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="rspMessage">返回报文:</label>
                </td>
                <td>
                    <textarea id="rspMessage" style="width:500px;height:200px;overflow:scroll;"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="#" id="goTestBtn" class="easyui-linkbutton" iconCls="icon-save"
                       style="margin-left:1em">提交</a>
                </td>
            </tr>
        </table>
    </fieldset>
</div>
<script>
    $(function () {
        $("#goTestBtn").click(function () {
            var protocol = $("#protocol").val();
            var encoding = $("#encoding").val();
            var address = $("#address").val();
            var reqMessage = $("#reqMessage").val();
            var headLen = $("#headLen").val();
            var data = {
                "protocol": protocol,
                "encoding": encoding,
                "address": address,
                "reqMessage": reqMessage,
                "headLen": headLen
            };
            $.ajax({
                "type": "POST",
                "contentType": "application/json;charset=utf-8",
                "url": "/esc/testEsc",
                "data": JSON.stringify(data),
                "dataType": "json",
                "success": function (result) {
                    console.log(result);
                    $("#rspMessage").val(result.result);
                }
            });

        });
    });
</script>
</body>
</html>
