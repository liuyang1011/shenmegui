<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form id="categoryWordForm" class="formui">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>词汇首写字母</th>
            <td>
                <input name="firstWord" id="firstWord"  class="easyui-combobox" style="width:140px"
                       data-options="
                 method:'get',
                 url:'/englishWord/getAllFirstWord',
                 textField:'firstWord',
                 valueField:'firstWord',
                 onChange:function(newValue, oldValue){
							this.value=newValue;
							 var urlpath = '/englishWord/getByFirstWord?firstWord='+newValue;
							 $('#wordAb').combobox({url : urlpath});
				    }
                 "
                        >
            </td>
        </tr>
        <tr>
            <th>类别词英文缩写</th>
            <td><input name="wordAb" id="wordAb"  class="easyui-combobox" style="width:140px"
                       data-options="required:true,validType:['uniqueAb'],
                 method:'get',
                 url:'/englishWord/getByFirstWord',
                 textField:'wordAb',
                 valueField:'wordAb',
                 onChange:function(newValue, oldValue){
                    this.value=newValue;
							var values = $('#wordAb').combobox('getData');
							 $.each(values, function (index, item) {
							   if($.trim(item.wordAb) == $.trim(newValue)){
							        $('#englishWord_').textbox('setValue',item.englishWord);
							        $('#chineseWord_').textbox('setValue',item.chineseWord);
							        }
							 });
				    }
                 "
                    >
            </td>
        </tr>
        <tr>
            <th>类别词英文名称</th>
            <td><input class="easyui-textbox" type="text" id="englishWord_" data-options="required:true" readonly="readonly"></td>
        </tr>
        <tr>
            <th>类别词中文名称</th>
            <td><input class="easyui-textbox" data-options="required:true" type="text" id="chineseWord_" readonly="readonly" ></td>
        </tr>
        <tr>
            <th>备注</th>
            <td><input class="easyui-textbox" type="text" id="remark_" data-options="validType:['chineseB']"></td>
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
                        url: "/categoryWord/uniqueValid",
                        dataType: "json",
                        data: {"esglisgAb": value},
                        success: function (data) {
                            result = data;
                        }
                    });
                    return result;
                },
                message: '已存在相同类别词英文缩写'
            }
        });
    });
    $("#addBtn").click(function(){
        saveCategoryWord();
    });

    var saveCategoryWord = function saveCategoryWord(){
        if (!$("#categoryWordForm").form('validate')) {
            return false;
        }
        var categoryWord = {};
        categoryWord.chineseWord = $('#chineseWord_').textbox("getValue");
        categoryWord.englishWord = $('#englishWord_').textbox("getValue");
        categoryWord.esglisgAb = $('#wordAb').combobox('getValue');
        categoryWord.remark = $('#remark_').textbox("getValue");
        $.ajax({
            "type" : "POST",
            "contentType" : "application/json;charset=utf-8",
            "url": "/categoryWord/add",
            "data": JSON.stringify(categoryWord),
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
