<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<form class="formui">
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				系統Id
			</th>
			<td>
				<input class="easyui-textbox" type="text" value="${param.systemId}" readOnly>
			</td>
		</tr>
		<tr  style="height:50px">
			<th>
				系統协议
			</th>
			<td>
				<select class="easyui-combobox" id="protocolIdRelate" style="width:155px" panelHeight="auto" data-options="editable:false, multiple:true">
				</select>
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

		    $('#protocolIdRelate').combobox({
                url:'/system/getProtocolAll'+'?time=' + (new Date()).valueOf(),
                method:'get',
                mode:'remote',
                valueField:'id',
                textField:'text',
                onLoadSuccess:function(){
                	 $.ajax({
							type: "GET",
							contentType: "application/json; charset=utf-8",
							url: "/system/getChecked/${param.systemId}",
							dataType: "json",
							success: function(result) {
								 //alert(result);
								 $('#protocolIdRelate').combobox("setValues",result);
							}
						});

				}
            });
	});


	function save(){

        var systemId = "${param.systemId}";
        var protocolIds = $("#protocolIdRelate").combobox('getValues');
		if(protocolIds == ""){
			alert("不关联无需保存");
			return;
		}
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: "/system/protocolRelate/${param.systemId}/"+protocolIds,
			dataType: "json",
			success: function(result) {
				 //alert(result);
				 if(true){
				 	alert("关联成功");
				 	$('#w').window('close');
				 }else{

				 }
				 //$('#protocolIdRelate').combobox("setValues",result);
			},
			complete:function(responce){
				var resText = responce.responseText;
				if(resText.toString().charAt(0) == "<"){
					alert("没有权限！");
//                              window.location.href = "/jsp/403.jsp";
				}
			}
		});


	}

</script>


