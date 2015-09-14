<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表页</title>
<link rel="stylesheet" type="text/css"
	href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
</head>

<body>
	<fieldset>
		<legend>条件搜索</legend>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>代码名称</th>
				<td><input class="easyui-textbox" type="text" id="name">
				</td>
				<th>是否标准代码</th>
				<td><select id="isStandard" editable="false" 
					panelHeight="auto" style="width:140px">
				</select>
				</td>
				<th>主代码数据来源</th>
				<td><input class="easyui-textbox" type="text" id="dataSource">
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>代码状态</th>
				<td><select id="status" editable="false" panelHeight="auto" style="width:140px">
				</select>
				</td>
			</tr>
			<!-- <tr>
				<th>备注</th>
				<td><input class="easyui-textbox" type="text" id="remark">
				</td>
				<th>修订人</th>
				<td><input class="easyui-textbox" type="text" id="optUser">
				 </td>
				<th>修订时间</th>
				<td><input class="easyui-textbox" type="text" id="optDate">
				</td> 
			</tr> -->
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="right"><a href="#" class="easyui-linkbutton" id="searchBtn"
					iconCls="icon-search">搜索</a></td>
			</tr>
		</table>


	</fieldset>
	<table title="公共代码列表" id="dg"
		style="height:420px;width:auto">
		<thead>
			<tr>
				<th data-options="field:'productid',checkbox:true"></th>
				<th data-options="field:'name',sortable:true">代码名称</th>
				<th data-options="field:'remark',width:60">中文名称</th>
				<th data-options="field:'isStandard'" formatter='formatter.isStandard'>是否标准代码</th>
				<th data-options="field:'status'" formatter='formatter.status'>代码状态</th>
				<th data-options="field:'dataSource'">主代码数据来源</th>
				<!-- <th data-options="field:'isMaster'">是否主代码</th>
				<th data-options="field:'version'">代码版本</th> -->
				<th data-options="field:'optUser',width:60">修订人</th>
				<th data-options="field:'optDate',width:100">修订时间</th>
			</tr>
		</thead>
	</table>
	<div id="w" class="easyui-window" title=""
		data-options="modal:true,closed:true,iconCls:'icon-add'"
		style="width:500px;height:200px;padding:10px;"></div>

	<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/resources/js/ui.js"></script>
	<script type="text/javascript"
		src="/assets/enumManager/js/enumManager.js"></script>
	<script type="text/javascript">
		var processId = parent.processId;
		var taskId = parent.taskId;

		$(function(){
			$('#dg').datagrid({
				rownumbers:true,
				singleSelect:true,
				collapsible:true,
				url:"/enum/getAll",
				method:'POST',
				toolbar:toolbar,
				pagination:true,
				striped: true,
				pageSize:'10',
				onLoadError: function (responce) {
					var resText = responce.responseText;
					if(resText.toString().charAt(0) == "<"){
//                    alert("没有权限！");
						window.location.href = "/jsp/403.jsp";
					}
				}
			});
			$('#isStandard').combobox({
				valueField: 'value',
				textField: 'label',
				data: [{
					label: '是',
					value: '1',
					selected:true
				},{
					label: '否',
					value: '0'
				}]
			});
			$('#status').combobox({
				valueField: 'value',
				textField: 'label',
				data: [{
					label: '使用',
					value: '1',
					selected:true
				},{
					label: '退役',
					value: '0'
				}]
			});
			$('#searchBtn').click(function(){
				var queryParams = $('#dg').datagrid('options').queryParams;  
				queryParams.name = $('#name').val();
				queryParams.isStandard = $('#isStandard').combobox('getValue');
				queryParams.dataSource = $('#dataSource').val();
				queryParams.status = $('#status').combobox('getValue');
				$("#dg").datagrid('options').queryParams = queryParams;//传递值  
				$("#dg").datagrid('reload');//重新加载table  
			});
		});
		var formatter = {
			isStandard: function (value, row, index) {
				if (value == 0) {
					return "<font>否</font>";
				}
				if (value == 1) {
					return "<font>是</font>";
				}
			},
			status: function (value, row, index) {
				if (value == 0) {
					return "<font>退役</font>";
				}
				if (value == 1) {
					return "<font>使用</font>";
				}
			}
		};
		var toolbar = [
				{
					text : '新增代码',
					iconCls : 'icon-add',
					handler : function() {
						uiinit.win({
							w : 500,
							iconCls : 'icon-add',
							title : "新增代码",
							url : "/pages/SGEnum/form/enumAppandForm.jsp"
						});
					}
				},
				{
					text : '维护',
					iconCls : 'icon-edit',
					handler : function() {
						var selectData = $('#dg').datagrid('getSelected');
						if (selectData == null) {
							alert("请先选择一条记录");
							return;
						}
						//主代码
						var content = '<iframe scrolling="auto" frameborder="0"  src="/enum/getByEnumId/'
								+ selectData.id+ '/'+selectData.isMaster+'/1" style="width:100%;height:100%;"></iframe>';
						var title = "公共代码维护";
						if (parent.$('#subtab').tabs('exists', title)) {
							parent.$('#subtab').tabs('close', title);
							parent.$('#subtab').tabs('add', {
								"title" : title,
								"content" : content,
								"closable" : true
							});
						} else {
							parent.$('#subtab').tabs('add', {
								"title" : title,
								"content" : content,
								"closable" : true
							});
						}
					}
				}, {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var selectData = $('#dg').datagrid('getSelected');
						if (selectData == null) {
							alert("请先选择一条记录");
							return;
						}
						if(confirm('确定删除吗 ？')){
							enumManager.deleteEnum(selectData.id);
						}
					}
				}, {
					text: '提交任务',
					iconCls: 'icon-qxfp',
					handler: function () {
						uiinit.win({
							w: 500,
							iconCls: 'icon-cfp',
							title: "完成任务",
							url: "/jsp/task/completeTask.jsp"
						});
					}
				} ];
	</script>
</body>
</html>