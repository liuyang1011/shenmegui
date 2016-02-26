<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<form class="formui">
    <table id="#tg" border="0" cellspacing="0" cellpadding="0">

        <tr>
            <th>
                系统名称
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="systemChineseName" value="${serviceInvoke.system.systemChineseName}" required="true">
            </td>
        </tr>
        <tr>
            <th>
                接口名称
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="interfaceName" value="${interfaceName}">
            </td>
        </tr>
        <tr>
            <th>
                交易码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="interfaceEcode" value="${interfaceEcode}">
            </td>
        </tr>
        <tr>
            <th>
                服务码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="serviceId" value="${serviceInvoke.serviceId}">
            </td>
        </tr>
        <tr>
            <th>
                场景码
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="operationId" value="${serviceInvoke.operationId}">
            </td>
        </tr>
        <tr>
            <th>
                节点类型
            </th>
            <td>
                <input class="easyui-textbox" type="text" id="nodeType" value="${type}">
            </td>
        </tr>

    </table>

</form>

