<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表页</title>
<link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/jsp/version/version.js"></script>
</head>

<body>
<fieldset>
 <legend>版本信息</legend>
 <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th>基线版本号</th>
    <td>
      <input class="easyui-textbox" type="text" name="code" disabled="true" value="${baseLine.code}"></td>
    <th>版本描述</th>
    <td><input class="easyui-textbox" type="text" name="blDesc" disabled="true" value="${baseLine.blDesc}"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <th>发布人</th>
    <td><input class="easyui-textbox" type="text" name="optUser" disabled="true"  value="${baseLine.optUser}"/></td>
    <th>版本发布时间</th>
    <td><input class="easyui-textbox" type="text" name="optDate"disabled="true" value="${baseLine.optDate}"/></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><a href="#" onclick="showHistoryVersion()" class="easyui-linkbutton" plain="true" iconcls="icon-search">历史基线版本</a></td>
    <td><a href="#" class="easyui-linkbutton" plain="true" iconcls="icon-save">版本下载</a></td>
    </tr>
 </table>

</fieldset>
<table id="operationList" class="easyui-datagrid" title="服务规范定义列表"
       data-options="
			rownumbers:true,
			singleSelect:false,
			url:'',
			method:'get',
			pagination:false,
				pageSize:10
				" style="height:365px; width:100%;">
  <thead>
  <tr>
    <th data-options="field:'productid',checkbox:true"></th>
    <th data-options="field:'service.serviceId'" formatter='service.serviceId'>服务代码
    </th>
    <th data-options="field:'service.serviceName'"
        formatter='service.serviceName'>服务名称</th>
    <th data-options="field:'operationId' ,width:40,align:'left'">服务场景</th>
    <th data-options="field:'operationName',width:80,align:'right'">服务场景名称</th>
    <th data-options="field:'operationDesc',width:280,align:'left'">场景描述</th>
    <th data-options="field:'versionHis.optType'" formatter="versionHis.optType">修订类型</th>
    <th data-options="field:'versionHis.optUser'" formatter="versionHis.optUser">发布用户</th>
    <th data-options="field:'versionHis.optDate'" formatter="versionHis.optDate">发布时间</th>
    <th data-options="field:'versionHis.versionDesc'" formatter="versionHis.versionDesc">发布说明</th>
    <th data-options="field:'versionHis.code'" formatter="versionHis.code">版本号</th>
  </tr>
  </thead>
</table>
<table id="invokeList" class="easyui-datagrid" title="服务接口映射列表"
       data-options="
				rownumbers:true,
				singleSelect:false,
				url:'',
				method:'get',
				pagination:false,
				pageSize:10
				" style="height:365px; width:100%;">
  <thead>
  <tr>
    <th data-options="field:'invokeId',checkbox:true"> </th>

    <th data-options="field:'serviceId'">服务编号 </th>
    <th data-options="field:'operationId'">场景编号</th>
    <th data-options="field:'systemNo'">系统编号 </th>
    <th data-options="field:'systemChineseName'">系统名称 </th>
    <th data-options="field:'interfaceId'">交易码 </th>
    <th data-options="field:'interfaceName'">接口 </th>
    <th data-options="field:'isStandard'"
        formatter='invoke.isStandardText'>标准 </th>
    <th data-options="field:'type'"
        formatter='invoke.typeText'>类型 </th>
    <th data-options="field:'desc'">描述 </th>
  </tr>
  </thead>
</table>
<!--
<table class="easyui-datagrid" title="服务公共代码" 
			data-options="
			rownumbers:true,
			singleSelect:false,
			url:'',method:'get',
			pagination:false,
			pageSize:10" style="height:365px; width:100%;">
  <thead>
    <tr>
          <th data-options="field:'productid',checkbox:true"> </th>

    
      <th data-options="field:'itemid'">字段1 </th>
      <th data-options="field:'status'">字段2 </th>
      <th data-options="field:'listprice',align:'right'">字段3 </th>
      <th data-options="field:'unitcost',width:80,align:'right'">字段4 </th>
      <th data-options="field:'attr1'">消费方 </th>
      <th data-options="field:'status',width:60,align:'center'">字段5 </th>
      <th data-options="field:'attr1'"> 字段6 </th>
      <th data-options="field:'attr1'"> 字段7 </th>
      <th data-options="field:'attr1'">字段8 </th>
      <th data-options="field:'attr1'">字段9 </th>
    </tr>
  </thead>
</table>
-->
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript">
  var baseId="${baseLine.baseId}";
  $(function(){
    $.ajax({
      type : "get",
      async : false,
      url : "/baseLine/getBLOperationHiss",
      dataType : "json",
      data : {"baseId" : baseId},
      success : function(data) {
        $("#operationList").datagrid("loadData", data);
      }
    });
    $.ajax({
      type : "get",
      async : false,
      url : "/baseLine/getBLInvoke",
      dataType : "json",
      data : {"baseId" :baseId},
      success : function(data) {
        $("#invokeList").datagrid("loadData", data);
      }
    });


  })
		function showHistoryVersion(){
			
			uiinit.win({
					w:500,
                    top:1,
                    left:400,
                    h:520,
					iconCls:'icon-search',
					title:"历史基线版本",
					url : "/jsp/version/grid.jsp"
				});	
		}
	</script>

</body>
</html>