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
            <th>词汇英文名称</th>
            <td><input class="easyui-textbox" type="text" id="englishWord1" data-options="required:true, validType:['uniqueAb','englishB']"></td>
        </tr>
        <tr>
            <th>词汇英文缩写</th>
            <td><input class="easyui-textbox" type="text" id="wordAb_" data-options="required:true, validType:['englishB']"></td>
        </tr>
        <tr>
            <th>词汇中文名称</th>
            <td><input class="easyui-textbox" type="text" id="chineseWord1" data-options="validType:['chineseB']"  ></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td class="win-bbar">
                <a id="addBtn" href="#" class="easyui-linkbutton"  iconCls="icon-save">保存</a>
                <a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
    $(function () {
        $.extend($.fn.validatebox.defaults.rules, {
            uniqueAb: {
                validator: function (value, param) {
                    var result;
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "/englishWord/uniqueValid",
                        dataType: "json",
                        data: {"englishWord": value},
                        success: function (data) {
                            result = data;
                        }
                    });
                    return result;
                },
                message: '已存在相同词汇'
            }
        });
    });
    $("#addBtn").click(function(){
        saveEnglishWord();
    });

    var saveEnglishWord = function saveEnglishWord(){
        if (!$("#englishWordForm").form('validate')) {
            return false;
        }
        var englishWords = {};
        englishWords.englishWord = $('#englishWord1').textbox("getValue");
        englishWords.wordAb = $('#wordAb_').textbox("getValue");
        englishWords.chineseWord = $('#chineseWord1').textbox("getValue");
        $.ajax({
            "type" : "POST",
            "contentType" : "application/json;charset=utf-8",
            "url": "/englishWord/add",
            "data": JSON.stringify(englishWords),
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
                }
            }

        });
    };

</script>