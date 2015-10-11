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
</head>
<body>
<form class="formui" id="metadataForm" action="/metadata/add" method="post">
    <table border="0" cellspacing="0" cellpadding="0">
        <input type="hidden" name="status" value="${entity.status }"/>
        <tr>
            <th>元数据名称</th>
            <td><input class="easyui-textbox" type="text" name="metadataId" value="${entity.metadataId }"
            data-options="required:true,validType:['englishB']"></td>
        </tr>
        <tr>
            <th>中文名称</th>
            <td><input class="easyui-textbox" type="text" name="chineseName" value="${entity.chineseName }"
                       data-options="required:true, validType:['chineseB']"></td>
        </tr>
        <tr style="display:none;">
            <th>英文名称</th>
            <td><input class="easyui-textbox" type="text" name="metadataName" value="${entity.metadataName }"></td>
        </tr>
        <tr>
            <th>别名</th>
            <td><input class="easyui-textbox" type="text" name="metadataAlias" value="${entity.metadataAlias}"></td>
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
                       value='${entity.categoryWordId }'
                       style="width: 100px; "/></td>
        </tr>
        <tr style="display:none;">
            <th>业务定义</th>
            <td><input class="easyui-textbox" type="text" name="bussDefine" value="${entity.bussDefine }"></td>
        </tr>
        <tr style="display:none;">
            <th>业务规则</th>
            <td><input class="easyui-textbox" type="text" name="bussRule" value="${entity.bussRule }"></td>
        </tr>
        <tr>
            <th>类型</th>

            <td><input class="easyui-textbox" type="text" name="type" value="${entity.type}"
                       data-options="validType:['english']"></td>
        </tr>
        <tr>
            <th>长度</th>
            <td><input class="easyui-textbox" type="text" name="length" value="${entity.length }"
                       data-options="validType:['intOrFloat']"></td>
        </tr>
        <tr>
            <th>精度</th>
            <td><input class="easyui-textbox" type="text" name="scale" value="${entity.scale }"
                       data-options="validType:['intOrFloat']"></td>
        </tr>
        <tr>
            <th>数据项分类</th>
            <td><input class="easyui-textbox" type="text" name="dataCategory" value="${entity.dataCategory }"></td>
        </tr>
        <tr>

            <th>数据来源</th>
            <td><input class="easyui-textbox" type="text" name="dataSource" value="${entity.dataSource }"></td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td class="win-bbar">
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
                <a href="#" onclick="modify('metadataForm',oldMetadataId)" class="easyui-linkbutton"
                   iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
    var oldMetadataId = "${entity.metadataId }";
</script>
</body>
</html>


