<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form id="englishWordForm" class="formui">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>英文单词</th>
            <td><input class="easyui-textbox" data-options="required:true, validType:['unique']" type="text" id="englishWord" ></td>
        </tr>
        <tr>
            <th>单词缩写</th>
            <td><input class="easyui-textbox" type="text" data-options="required:true, validType:['uniqueAb']"  id="wordAb"></td>
        </tr>
        <tr>
            <th>单词中文</th>
            <td><input class="easyui-textbox" type="text" id="chineseWord" data-options="required:true, validType:['uniqueChinese']"></td>
        </tr>
        <tr>
            <th>单词备注</th>
            <td><input class="easyui-textbox" type="text" id="remark"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td class="win-bbar">
                <a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a id="addBtn" href="#" class="easyui-linkbutton"  iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
    $(function () {
        $.extend($.fn.validatebox.defaults.rules, {
            unique: {
                validator: function (value, param) {
                    var result;
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/englishWord/uniqueValid",
                        dataType: "json",
                        data: {"propertyName": "englishWord",
                            "propertyValue": value
                        },
                        success: function (data) {
                            result = data;
                        }
                    });
                    return result;
                },
                message: '已存在相同单词名称'
            },
            uniqueAb: {
                validator: function (value, param) {
                    var result;
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/englishWord/uniqueValid",
                        dataType: "json",
                        data: {"propertyName": "wordAb",
                            "propertyValue": value
                        },
                        success: function (data) {
                            result = data;
                        }
                    });
                    return result;
                },
                message: '已存在相同单词缩写'
            },
            uniqueChinese: {
                validator: function (value, param) {
                    var result;
                    $.ajax({
                        type: "post",
                        async: false,
                        url: "/englishWord/uniqueValid",
                        dataType: "json",
                        data: {"propertyName": "chineseWord",
                            "propertyValue": value
                        },
                        success: function (data) {
                            result = data;
                        }
                    });
                    return result;
                },
                message: '已存在相同单词中文'
            }
        });
    });
    $("#addBtn").click(function(){
        saveEnglishword();
    });

    var saveEnglishword = function saveEnglishword(){
        if (!$("#englishWordForm").form('validate')) {
            return false;
        }
        var word = {};
        word.englishWord = $('#englishWord').textbox("getValue");
        word.wordAb = $('#wordAb').textbox("getValue");
        word.chineseWord = $('#chineseWord').textbox("getValue");
        word.remark = $('#remark').textbox("getValue");
        $.ajax({
            "type" : "POST",
            "contentType" : "application/json;charset=utf-8",
            "url": "/englishWord/add",
            "data": JSON.stringify(word),
            "dataType": "json",
            "success": function(result) {
                if(result){
                    $("#w").window("close");
                    $('#tt').datagrid('reload');
                }
            },
            "complete":function(responce){
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }

        });
    };

</script>