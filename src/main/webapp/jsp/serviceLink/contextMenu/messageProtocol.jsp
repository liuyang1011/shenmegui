<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<form class="formui">
    <table id="#tg" border="0" cellspacing="0" cellpadding="0">

        <tr>
            <th>
                协议名称
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="protocolNameText" value="${protocol.protocolName}" required="true">
            </td>
        </tr>
        <tr>
            <th>
                通讯协议
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="commuProtocol" value="${protocol.commuProtocol}">
            </td>
        </tr>
        <tr>
            <th>
                报文类型
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="msgTypeText" value="${protocol.msgType}">
            </td>
        </tr>
        <tr>
            <th>
                协议编码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="encodingText" value="${protocol.encoding}">
            </td>
        </tr>
        <tr>
            <th>
                超时时间
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="timeoutText" value="${protocol.timeout}">
            </td>
        </tr>
        <tr>
            <th>
                加密
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="isEncrypt" value="${protocol.isEncrypt}">
                <%--<input class="easyui-combobox" id="isEncrypt"--%>
                <%--data-options="--%>
                <%--valueField:'value',--%>
                <%--textField:'label',--%>
                <%--data:[{label:'加密', value:'1'}, {label:'不加密', value:'0'}]--%>
                <%--"--%>
                <%--value="${protocol.isEncrypt}"--%>
                <%-->--%>
            </td>
        </tr>
        <tr>
            <th>
                同步
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="isSync" value="${protocol.isSync}">
                <%--<input class="easyui-combobox" id="isSync"--%>
                <%--data-options="--%>
                <%--valueField:'value',--%>
                <%--textField:'label',--%>
                <%--data:[{label:'同步', value:'1'}, {label:'不同步', value:'0'}]--%>
                <%--"--%>
                <%--value="${protocol.isSync}"--%>
                <%-->--%>
            </td>
        </tr>
        <tr>
            <th>
                链接
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="isLongCon" value="${protocol.isLongCon}">
                <%--<input class="easyui-combobox" id="isLongCon"--%>
                <%--data-options="--%>
                <%--valueField:'value',--%>
                <%--textField:'label',--%>
                <%--data:[{label:'长链接', value:'1'}, {label:'短链接', value:'0'}]--%>
                <%--"--%>
                <%--value="${protocol.isLongCon}"--%>
                <%-->--%>
            </td>
        </tr>
        <%--
        <tr style="display: none">
            <th>
                错误代码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="errorCodeText" value="${protocol.errorCode}">
            </td>
        </tr>

        <tr style="display:none">
            <th>
                成功代码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="succCodeText" value="${protocol.succCode}">
            </td>
        </tr>
        --%>
        <tr>
            <th>
                备注
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="remarkText" value="${protocol.remark}">
            </td>
        </tr>
        <tr>
            <th>
                报文模板
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="templateContent" value="${protocol.msgTemplate.templateContent}">
            </td>
        </tr>

        <tr>
            <th>
                生成类
            </th>
            <td>
                <select class="easyui-combobox" id="generator" style="width:173px" panelHeight="auto"
                        data-options="
                    value:'${protocol.generatorId}',
						url:'/generator/getAll',
						method:'get',
						mode:'remote',
						valueField:'id',
						textField:'name'
						"
                        >
                </select>
            </td>
        </tr>
    </table>
    <input  type="hidden" id="protocolIdText" value="${protocol.protocolId}" disabled>
    <input  type="hidden" id="msgTemplateIdText" value="${protocol.msgTemplateId}" disabled>
</form>



