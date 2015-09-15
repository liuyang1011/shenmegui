<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'MyJsp.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>


<body>

<form class="formui" id="metadataForm" action="/metadata/add" method="post">
    <table border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="status" value="待审核"/>
        <tr>
            <td colspan="2" style="text-align:center">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a href="#" onclick="save('metadataForm')" class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
        <tr>
            <th>元数据名称</th>
            <td><input class="easyui-textbox" type="text" name="metadataId"
                       data-options="required:true, validType:['unique','english']"></td>
        </tr>
        <tr>
            <th>中文名称</th>
            <td><input class="easyui-textbox" type="text" name="chineseName"></td>
        </tr>
        <tr>
            <th>英文名称</th>
            <td><input class="easyui-textbox" type="text" name="metadataName"></td>
        </tr>
        <tr>
            <th>别名</th>
            <td><input class="easyui-textbox" type="text" name="metadataAlias"></td>
        </tr>
        <tr>
            <th>类别词</th>
            <td><input type="text" name="categoryWordId" id="categoryWordId"
                       class="easyui-combobox"
                       data-options="
                       panelHeight:'300px',
						url:'/metadata/categoryWord',
				 		 method:'get',
				 		 valueField: 'englishWord',
				 		 textField: 'chineseWord',
				 		 onChange:function(newValue, oldValue){
							this.value=newValue;
						}
					"
                    /></td>
        </tr>
        <tr>
            <th>业务定义</th>
            <td><input class="easyui-textbox" type="text" name="bussDefine"></td>
        </tr>
        <tr>
            <th>业务规则</th>
            <td><input class="easyui-textbox" type="text" name="bussRule"></td>
        </tr>
	       <tr>
            				<th>类型</th>
            				<td><input class="easyui-textbox" type="text" name="type"></td>
            </tr>

        <tr>
            <th>长度</th>
            <td><input class="easyui-textbox" type="text" name="length"></td>
        </tr>
        <tr>
            <th>精度</th>
            <td><input class="easyui-textbox" type="text" name="scale"></td>
        </tr>
        <tr>
                    <th>数据项分类</th>
                    <td><input class="easyui-textbox" type="text" name="dataCategory"></td>
                </tr>
        <tr>
            <th>数据来源</th>
            <td><input class="easyui-textbox" type="text" name="dataSource"></td>
        </tr>
        <tr>
            <th>任务id</th>
            <td><input class="easyui-textbox" disabled="disabled" type="text" name="processId" id="taskIdInput"></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    $.extend($.fn.validatebox.defaults.rules, {
        unique: {
            validator: function (value, param) {
                var result;
                $.ajax({
                    type: "get",
                    async: false,
                    url: "/metadata/uniqueValid",
                    dataType: "json",
                    data: {"metadataId": value},
                    success: function (data) {
                        result = data;
                    }
                });
                return result;
            },
            message: '元数据名称已存在'
        },
                english : {// 验证英语
                        validator : function(value) {
                           // return (/^[A-Za-z]+$/i.test(value)||/^\d+(\.\d+)?$/i.test(value));
                           return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
                        },
                        message : '请输入英文字母、下划线或数字'
                    }
    });
    if(typeof(processId) != 'undefined'){
        $('#taskIdInput').val(processId);
    }
</script>

</body>
</html>



