<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表页</title>
<link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/css.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/resources/js/jquery.min.js"></script> 
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/jsp/version/version.js"></script>
	<script type="text/javascript" src="/js/version/versionManager.js"></script>
<script type="text/javascript">
	$(function (){
		$("#operationList").datagrid({
			rownumbers:true,
			singleSelect:true,
			url:'/versionHis/hisVersionList',
			method:'get',
			<%--toolbar:toolbar,--%>
			pagination:true,
			pageSize:15,
			pageList: [15,30,50]
		});
	});
		var toolbar = [
		{
			text:'撤销',
			iconCls:'icon-cancel',
			handler:function(){alert('移出')}
		}];
		
		//按照关键字查询版本历史
		function verionHisSearch(){
			var queryParams = $('#operationList').datagrid('options').queryParams;
			queryParams.keyValue = encodeURI($("#keyValue").textbox("getValue"));
			if(queryParams.keyValue){
				$("#operationList").datagrid('options').queryParams = queryParams;//传递值
				$("#operationList").datagrid('reload');
			}else{
				$("#operationList").datagrid('reload');
			}
			/*var data = $("#keyValue").textbox("getValue");
			$.ajax({
				type : "get",
				async : false,
				url : "/versionHis/hisVersionList",
				dataType : "json",
				data : {"keyValue" : data},
				success : function(data) {
					$("#operationList").datagrid("loadData", data.rows);
				}
			});*/
		}
		
		//操作按钮
		function formatConsole(value, row, index){
				var s = '<a onclick="detailPage(\'' + value + '\')" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)" id="cancelbtn'+value+'">' +
						'<span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">数据详情</span><span class="l-btn-icon icon-search">&nbsp;</span></span></a>&nbsp;&nbsp;'+
						'<a onclick="comparePage(\'' + row.autoId + '\')" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)"  id="comparebtn'+value+'">' +
						'<span class="l-btn-text">版本对比</span>&nbsp;</span></span></a>';
		    	return s;
	    	
		}
	function detailPage(autoId) {
		var urlPath = "/versionHis/hisDetailPage?autoId="+autoId;
		$('#versionDlg').dialog({
			title : 'His',
			top:'100px',
			left:'150px',
			width : 700,
			closed : false,
			cache : false,
			href : urlPath,
			modal : true
		});
	}
	//弹出对比页面
	function comparePage(autoId){
		$.ajax({
			type: "get",
			async: false,
			url: "/versionHis/judgeVersionPre?autoId="+autoId,
			dataType: "json",
			success: function (data) {
				if(data.autoId != null){
					var urlPath = "/jsp/version/sdaComparePage.jsp?autoId1="+autoId+"&type=1&autoId2="+data.autoId+"&versionId="+ data.id;
					$("#versionDlg").dialog({
						title: '版本对比',
						left:'50px',
						width: 1000,
						height:'auto',
						closed: false,
						cache: false,
						href: urlPath,
						modal: true
					});
				}else{
					alert("该版本为初始版本!");
				}
			}
		});

	}
		
	</script> 
</head>

<body>
<fieldset>
 <legend>条件过滤</legend>
 <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th>关键字</th>
    <td>
      <input id="keyValue" class="easyui-textbox" type="text" name="name" ></td>
   
    <td><a href="#" onclick="verionHisSearch()" class="easyui-linkbutton" iconcls="icon-search">过滤</a></td>

  </tr>
</table>

</fieldset>
<table id="operationList" title="版本历史" style="height:525px; width:auto;">
  <thead>
    <tr>
        <th data-options="field:'autoId',checkbox:true"> </th>
		<th data-options="field:'serviceId'">服务代码 </th>
	    <th data-options="field:'serviceName'">服务名称 </th>
		<th data-options="field:'operationId'">服务场景 </th>
		<th data-options="field:'operationName'">场景名称 </th>
		<%--<th data-options="field:'operationDesc'">场景描述 </th>--%>
      <th data-options="field:'code'">版本号 </th>
      <%--<th data-options="field:'type'" formatter="versionHis.type">是否基线版本 </th>--%>
      <th data-options="field:'baseLineNum'">基线版本号 </th>
      <th data-options="field:'optType'" formatter="versionHis.optType0">修订类型 </th>
      <th data-options="field:'versionDesc'">发布说明 </th>
      <th data-options="field:'optDate'">发布时间 </th>
      <th data-options="field:'optUser'">发布人 </th>
      <th data-options="field:'targetId',width:180,formatter:formatConsole">操作</th>
    </tr>
  </thead>
</table>
 <div id="versionDlg" class="easyui-dialog"
		style="width:400px;height:auto;padding:10px 20px" closed="true"
		resizable="true"></div>
</body>
</html>