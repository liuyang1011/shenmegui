<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<form class="formui">
	<table border="0" cellspacing="0" cellpadding="0">

		<tr>
			<th>
				系统ID
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="systemIdText" value="${system.systemId}" readOnly>
			</td>
		</tr>
		<tr>
			<th>
				系统简称
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="systemAbText" value="${system.systemAb}">
			</td>
		</tr>
		<tr>
			<th>
				系统名称
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="systemChineseNameText" value="${system.systemChineseName}">
			</td>
		</tr>
		<!--<tr>
			<th>
				系统协议
			</th>
			<td>
				<select class="easyui-combobox" id="protocolIdText" style="width:155px" panelHeight="auto" data-options="editable:false">
                </select>
			</td>
		</tr>
		-->
		<tr>
			<th>
				联系人一
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="principal1Text" value="${system.principal1}">
			</td>
		</tr>
		<tr>
			<th>
				联系人二
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="principal2Text" value="${system.principal2}">
			</td>
		</tr>

        <tr>
			<th>
				工作范围
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="workRangeText" value="${system.workRange}">
			</td>
		</tr>
		<tr>
			<th>
				系统描述
			</th>
			<td>
				<input class="easyui-textbox" type="text" id="featureDescText" value="${system.featureDesc}">
			</td>
		</tr>


		<tr>
			<td>
				&nbsp;
			</td>
			<td class="win-bbar">
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onClick="$('#w').window('close')">取消</a><a href="#"
					class="easyui-linkbutton" onclick="save()" iconCls="icon-save">保存</a>
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	$(document).ready(function (){
		$('#protocolId').combobox({
			url:'/system/getProtocolAll',
			method:'get',
			mode:'remote',
			valueField:'id',
			textField:'text',
			onLoadSuccess:function(){

			}
		});
    });

	function save(){

            var systemId = $("#systemIdText").val();
    		var systemAb = $("#systemAbText").val();
    		if(systemAb==null || systemAb == ''){
				alert("请填写系统简称");
				return;
			}
			var flag = true;
			$.ajax({
				 type: "GET",
				 async:false,
				 url: "/system/systemAbcheck/"+systemAb+"/"+systemId,
				 dataType: "json",
				 success: function(data){
					if(data){
						alert("系统简称已存在");
						flag = false;;
					}
				 }
			});

			if(!flag){
				return;
			}
    		var systemChineseName = $("#systemChineseNameText").val();
    		//var protocolId = $("#protocolIdText").combobox('getValue');
    		var principal1 = $("#principal1Text").val();
    		var principal2 = $("#principal2Text").val();
    		var workRange = $("#workRangeText").val();
    		var featureDesc = $("#featureDescText").val();
    		var data = {};

    		data.systemId = systemId;
    		data.systemAb = systemAb;
    		data.systemChineseName = systemChineseName;
    		data.principal1 = principal1;
    		data.principal2 = principal2;
    		data.workRange = workRange;
    		data.featureDesc = featureDesc;

    		//var protocolData = {};

    		//protocolData.protocolId = protocolId;
    		//protocolData.systemId = systemId;
    		//data.systemProtocol = protocolData;

    		sysManager.addSystem(data,function(result){
    			if(result){
    				$('#w').window('close');
    				//alert(parent.parent.$('.msinterfacetree'));
    				//alert($('.msinterfacetree'));
    				parent.parent.$('.msinterfacetree').tree("reload");
					$('#tg').datagrid("reload");
    			}else{
    				alert("保存失败");
    			}
    		});


    	}

</script>


