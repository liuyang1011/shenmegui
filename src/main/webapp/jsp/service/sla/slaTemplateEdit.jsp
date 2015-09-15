<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<table id="slaTemplateTable" style="height:auto; width:auto;" title="">
	<thead>
		<tr>
			<th data-options="field:'productid',checkbox:true"></th>
			<th field="slaTemplateId" width="100" editor="text" id="idText"
				data-options="hidden:true">ID</th>
			<th field="templateName" width="100" editor="text" align="center">SLA模板名称</th>
			<th field="desc" width="150" align="center" editor="text">描 述</th>
		</tr>
	</thead>
</table>
<div id="w" class="easyui-window" title=""
	data-options="modal:true,closed:true,iconCls:'icon-add'"
	style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/assets/slaTemplate/js/slaTemplateManager.js"></script>
<script type="text/javascript" src="/assets/sla/js/slaManager.js"></script>
<script type="text/javascript">
	var slaTemplatetoolbar = [
			{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					$('#slaTemplateTable').edatagrid('addRow');
					}
			},{
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
				
				var row = $('#slaTemplateTable').edatagrid("getSelected");
					var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/service/sla/slaTemplate.jsp?slaTemplateId='+row.slaTemplateId+'&templateName='+row.templateName+'&serviceId='+serviceId+'&operationId='+operationId+'" style="width:100%;height:100%;"></iframe>';
// 					alert(content);
					parent.parent.parent.addTab('SLA模板', content);
					}
			},
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#slaTemplateTable').edatagrid('getSelected');
					if(row==""||row==null){
					alert("请选择一条信息！");
					return false;
					}	
					var rowIndex = $('#slaTemplateTable').datagrid('getRowIndex', row);
					var deleteData = $("#slaTemplateTable").datagrid('getChanges','deleted');	
					$('#slaTemplateTable').edatagrid('deleteRow', rowIndex);
					slaTemplateManager.deleteByEntity(deleteData,function(result){
						if(result){
							$('#slaTemplateTable').datagrid('reload');
						alert("删除成功！");
						}else{alert("删除失败！");}
					});
				}
			},{
			text : ' 保存',
			iconCls : 'icon-save',
			handler : function() {
			for ( var per in editedRows) {
				$("#slaTemplateTable").datagrid('endEdit', editedRows[per]);
			}
			var editData = $("#slaTemplateTable").datagrid('getChanges');
				slaTemplateManager.add(editData,function(result){
					if(result){
						$('#slaTemplateTable').datagrid('reload');
					alert("保存成功！");
					}else{alert("保存失败！");}
				});
				editedRows = [];

			}
		}, {
				text : '选择',
				iconCls : 'icon-qxfp',
				handler : function() {
					var serviceId = "${param.serviceId}";
					var operationId = "${param.operationId}";
					var info = $('#slaTemplateTable').edatagrid('getSelected');
					if(info==""||info==null){
					alert("请选择一条信息！");
					return false;
					}
					if(info.slaTemplateId){
						slaManager.setTemplateData(serviceId,operationId,info.slaTemplateId,function(result){

						});

						/*slaManager.getByParams(info.slaTemplateId,function(result){
							$('#sla').edatagrid('loadData',result);
							slaManager.deleteByAll(serviceId,operationId, function(result1){
							if(result1){
							slaManager.addList(result,function(result2){
								if(result2){alert("导入成功");}else{alert("导入失败");}
							   });
							  }
						   });
						});*/
						$('#w').window('close');
					}
				}
			} ];
			
	var editedRows = [];
	$(function() {

		$('#slaTemplateTable').edatagrid({
			rownumbers:true,
				singleSelect:true,
				url:"/slaTemplate/getAll/",
				method:'get',
				toolbar:slaTemplatetoolbar,
				onBeginEdit : function(index,row){
					editedRows.push(index);
				}
		});
	});
</script>
