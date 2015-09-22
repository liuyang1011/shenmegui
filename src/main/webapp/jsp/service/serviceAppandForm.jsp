<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form id="serviceForm" class="formui">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th>服务码</th>
    <td><input class="easyui-textbox" data-options="required:true, validType:['englishB']" type="text" id="serviceId" ></td>
  </tr>
  <tr>
    <th>服务名称</th>
    <td><input class="easyui-textbox" type="text" id="serviceName" ></td>
  </tr>
  <tr>
    <th>描述</th>
    <td><input class="easyui-textbox" type="text" id="discription" ></td>
  </tr>
  <tr style="display:none">
    <th>服务分类</th>
    <td><input class="easyui-textbox" type="text" id="serviceCategory" ></td>
  </tr>
<tr style="display: none;">
    <th>version</th>
    <td><input class="easyui-textbox" type="text" id="version" ></td>
  </tr>
  <tr style="display: none;">
    <th>state</th>
    <td><input class="easyui-textbox" type="text" id="state" ></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="win-bbar">
      <a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
      <a id="serviceAddBtn" href="#" class="easyui-linkbutton"  iconCls="icon-save">保存</a>
    </td>
  </tr>
</table>
</form>
<script type="text/javascript" src="/assets/service/js/serviceAppendForm.js"></script>