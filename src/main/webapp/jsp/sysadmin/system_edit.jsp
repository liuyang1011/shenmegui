<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	<!--
	#table tr th{
		text-align:right
	}
	#table tr td{
		padding-left:15px;
		text-align:left
	}
	-->
</style>
<form class="formui">
		<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:center"  id="table">
			<tr>
				<th>
					系统编号
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="systemNoText" data-options="required:true, validType:['intOrFloat']" value="${system.systemNo}">
				</td>
			</tr>
			<tr>
				<th>
					系统英文简称
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="systemAbText" data-options="required:true, validType:['englishB']" value="${system.systemAb}">
				</td>
			</tr>
			<tr>
				<th>
					系统中文名称
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="systemChineseNameText" data-options="required:true, validType:['chineseB']" value="${system.systemChineseName}">
				</td>
			</tr>
			<tr>
				<th>
					系统分类
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="systemClassify1" value="${system.systemClassify}">
				</td>
			</tr>
			<tr>
				<th>
					系统负责人
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="systemPrincipal1" value="${system.systemPrincipal}">
				</td>
			</tr>
			<tr>
				<th>
					负责人联系电话
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="principalTel" value="${system.principalTel}" data-options="validType:['mobile']">
				</td>
			</tr>
			<tr>
				<th>
					负责人邮箱
				</th>
				<td>
					<input class="easyui-textbox" type="text" id="principalEmail" value="${system.principalEmail}" data-options="validType:['email']">
				</td>
			</tr>
			<tr>
				<th>
					系统描述
				</th>
				<td>
					<textarea  type="text" id="systemDesc"style="height: 100px;">${system.systemDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align:center" >
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					   onClick="$('#w').window('close')">取消</a>&nbsp;&nbsp;<a href="#"
																			  class="easyui-linkbutton" onclick="save()" iconCls="icon-save">保存</a>
				</td>
			</tr>
	</table>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
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

		if(!$("#form1").form('validate')){
			return false;
		}
		var systemNo = $("#systemNoText").val();
		if(systemNo==null || systemNo == ''){
			alert("请填写系统编号");
			return;
		}
		var systemAb = $("#systemAbText").val();
		if(systemAb==null || systemAb == ''){
			alert("请填写系统简称");
			return;
		}
		var systemChineseName = $("#systemChineseNameText").val();
		var flag = true;
		if(systemNo!='${system.systemNo}'){
			$.ajax({
				type: "GET",
				async:false,
				url: "/system/systemNoCheck/"+systemNo+"?_t=" + new Date().getTime(),
				dataType: "json",
				success: function(data){
					if(data){
						alert("系统编号已存在");
						flag = false;;
					}
				}
			});
		}

		if(!flag){
			return;
		}
		if(systemAb!='${system.systemAb}') {
			$.ajax({
				type: "GET",
				async: false,
				url: "/system/systemAbcheck/" + systemAb + "/" + systemNo,
				dataType: "json",
				success: function (data) {
					if (data) {
						alert("系统英文简称已存在");
						flag = false;
						;
					}
				}
			});
		}

		if(!flag){
			return;
		}

		var systemClassify = $("#systemClassify1").val();
		var systemPrincipal = $("#systemPrincipal1").val();
		var principalTel = $("#principalTel").val();
		var principalEmail = $("#principalEmail").val();
		var systemDesc = $("#systemDesc").val();
		var updateUser='<shiro:principal/>';
		var data = {};
		data.systemId='${system.systemId}';
		data.systemNo=systemNo;
		data.systemAb = systemAb;
		data.systemChineseName = systemChineseName;
		data.systemClassify = systemClassify;
		data.systemPrincipal = systemPrincipal;
		data.principalTel = principalTel;
		data.principalEmail = principalEmail;
		data.systemDesc = systemDesc;
		data.updateUser = updateUser;
		//var protocolData = {};

		//protocolData.protocolId = protocolId;
		//protocolData.systemId = systemId;
		//data.systemProtocol = protocolData;

		sysManager.updateSystem(data,function(result){
			if(result){
				$('#w').window('close');
				parent.parent.$('.msinterfacetree').tree("reload");
				$('#tg').datagrid("reload");
			}else{
				alert("修改失败");
			}
		});



	}

</script>


