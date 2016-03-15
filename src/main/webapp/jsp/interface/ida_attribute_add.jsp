<%@ page language="java" import="java.util.*, java.net.URLDecoder" pageEncoding="utf-8"%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <base href="<%=basePath%>">
</head>

<body>
<fieldset>
    <table width="100%">
        <tr>
            <td width="20%">

            </td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#dlg').dialog('close');">取消</a>
                <a href="javascript:void(0)" onclick="saveAttr()" class="easyui-linkbutton"  iconCls="icon-save">保存</a>
            </td>
        </tr>
        <tr>
            <td width="20%">
                属性：
            </td>
            <td>
                <input type="text" id="attrType"
                       class="easyui-combobox"
                       data-options="
                 textField:'text',
                 valueField:'id',
                 value:'0',
                 data:[
                    {'id':'0','text':'固定值'},
                    {'id':'1','text':'表达式'}
                 ],
                 onSelect:function(record){
                           if(record.id == '1'){
                            selectExpression(record);
                       }
                 }
                 "
                 />
            </td>
        </tr>
        <tr>
            <td width="20%">
                属性：
            </td>
            <td>
                <input id="attrName"  type="text" class="easyui-textbox" required="true" style="width:200px">
            </td>
        </tr>
        <tr>
            <td width="20%">
                值：
            </td>
            <td>
                <input id="attrValue"  type="text" class="easyui-textbox" required="true" style="width:200px">
            </td>
        </tr>
        <tr>
            <td width="20%">
                说明：
            </td>
            <td>
                <input id="attrRemarks"  type="text" class="easyui-textbox" style="width:200px">
            </td>
        </tr>
    </table>
</fieldset>
<script type="text/javascript">
    function saveAttr(){
        var attrName = $("#attrName").textbox("getValue");
        var attrValue = $("#attrValue").textbox("getValue");
        var attrType = $("#attrType").combobox("getValue");
        var attrRemarks = $("#attrRemarks").textbox("getValue");
        if(attrName == null || attrName == ""){
            alert("请输入属性名称！");
            return;
        }
        if(attrValue == null || attrValue == ""){
            alert("请输入属性值！");
            return;
        }else{
            var attrType = $("#attrType").combobox("getValue");
            var path = $("#tg").treegrid('options').url;
            var idaAttribute ={};
            idaAttribute.id = new Date().getTime();
            idaAttribute.type = attrType;
            idaAttribute.name = attrName;
            idaAttribute.value = attrValue;
            idaAttribute.remarks = attrRemarks;
            idaAttribute.idaId = "${param.idaId}";
            $.ajax({
                type: "post",
                async: false,
                contentType : "application/json;charset=utf-8",
                url: "/idaAttribute/add?_t="+ new Date().getTime(),
                data: JSON.stringify(idaAttribute),
                dataType: "json",
                success: function (data) {
                    if (data) {
                        alert("属性保存成功!");
                        $("#tg").treegrid({url:path+"&_t="+ new Date().getDate});
                        $('#releaseDlg').dialog('close');
                        showAttr();
                    }
                }
            });
        }
    }
    //类型选择触发方法
    function selectExpression(record){
        var urlPath = "/jsp/service/sda/func_choose.jsp"
        $("#attrDlg").dialog({
            title: '字段处理方法选择',
            left:70,
            width: 800,
            height: 'auto',
            closed: false,
            cache: false,
            href: urlPath,
            modal: true
        });
    }
</script>
</body>
</html>
