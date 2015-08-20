<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <base href="<%=basePath%>">
    
    <title>sda信息</title>
    
	<link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
<link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
	 <script type="text/javascript" src="/resources/js/jquery.min.js"></script> 
        <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/resources/js/treegrid-dnd.js"></script>

<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript">
var editingId;
var newIds = [];
var delIds = [];
		function onContextMenu(e,row){
			e.preventDefault();
			$(this).treegrid('select', row.id);
			$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
		}
		function removeIt(){
			var node = $('#tg').treegrid('getSelected');
			if (node){
				delIds.push(node.id);
				$('#tg').treegrid('remove', node.id);
			}
		}
		function editIt(){
				var row = $('#tg').treegrid('getSelected');
				
				if (row){
					editingId = row.id
					newIds.push(editingId);
					$('#tg').treegrid('beginEdit', editingId);
					
					$("#cancelbtn"+editingId).show();
					$("#okbtn"+editingId).show();
				}
		}
		
		function append(){
			var uuid = new Date().getTime();
			var node = $('#tg').treegrid('getSelected');
			if(node.text == "root"){
				alert("请选择其他节点！");
				return false;
			}
			$('#tg').treegrid('append',{
				parent: node.id,
				data: [{
					id: uuid,
					parentId:node.id
				}]
			});
			editingId = uuid;
			newIds.push(uuid);
			$('#tg').treegrid('reloadFooter');
			$('#tg').treegrid('beginEdit', uuid);
		}
		function saveSDA(){
			if (!$("#sdaForm").form('validate')) {
                return false;
            }
			var t = $('#tg');
			if (editingId != undefined){
				var editNodes = [];
				for(var i=0; i<newIds.length; i++){
					var editNode = t.treegrid('find', newIds[i]);
					t.treegrid('endEdit', editNode.id);
					var node = {};
					node.sdaId = editNode.id;
					node.structName = editNode.text;
					node.parentId = editNode.parentId;
					
					node.serviceId = "${service.serviceId }";
					node.operationId = "${operation.operationId }";
					
					node.structAlias = editNode.append1;
					node.type = editNode.append2;
					node.length = editNode.append3;
					node.metadataId = editNode.append4;
					node.required = editNode.append5;
					node.remark = editNode.append6;
					node.seq = editNode.attributes;

					editNodes.push(node);
				}
				
				editingId = undefined;
				
				$.ajax({
			         type: "post",
			         async: false,
			         contentType:"application/json; charset=utf-8",
			         url: "/sda/saveSDA",
			         dataType: "json",
			         data: JSON.stringify(editNodes),
			         success: function(data){
			        	 if(data){
			        	 	newIds = [];
			        	 	alert("保存成功");
							 t.treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});
			        	 	//t.treegrid('reload');
			        	 }
			            }
				 });
			}
			if(delIds.length > 0){
				$.ajax({
			         type: "post",
			         async: false,
			         contentType:"application/json; charset=utf-8",
			         url: "/sda/deleteSDA",
			         dataType: "json",
			         data: JSON.stringify(delIds),
			         success: function(data){
			        	 if(data){
			        	 	delIds = [];
			        	 	alert("保存成功");
			        	 	t.treegrid('reload');
			        	 }
			            }
				 });
			}
		}
		function cancel(){
			if (editingId != undefined){
				$('#tg').treegrid('cancelEdit', editingId);
				editingId = undefined;
			}
		}
		function formatConsole(value){
	    	
		    	
				var s = '<a iconcls="icon-close" onclick="cancel()" style="display:none;margin-top:5px;margin-bottom:5px;margin-left:5px;" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)" group="" id="cancelbtn'+value+'"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">取消</span><span class="l-btn-icon icon-cancel">&nbsp;</span></span></a>';
				 s += '<a iconcls="icon-ok" onclick="saveSDA()" style="display:none;margin-top:5px;margin-bottom:5px;margin-left:5px;" class="easyui-linkbutton l-btn l-btn-small" href="javascript:void(0)" group="" id="okbtn'+value+'"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">保存</span><span class="l-btn-icon icon-ok">&nbsp;</span></span></a>';
		    	return s;
	    	
		}
		//节点上移
		function moveUp(){
			var node = $('#tg').treegrid('getSelected');

			if(node != null){
				//判断是否是第一个节点
				var parentNode = $('#tg').treegrid('getParent', node.id);
				var brothers =  $('#tg').treegrid('getChildren', parentNode.id);
				if(node.id == brothers[0].id){
					alert("已经在顶部！");
					return false;
				}
				$.ajax({
					type:"get",
					url: "/sda/moveUp?_t=" + new Date().getTime(),
			        dataType: "json",
			        data: {"sdaId": node.id},
			        success: function(data){
			        	 if(data){
							 $('#tg').treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});
						 }
			            }
				});
			}
			
		}
		
		function moveDown(){
			var node = $('#tg').treegrid('getSelected');
			if(node != null){
				//判断是否是第一个节点
				var parentNode = $('#tg').treegrid('getParent', node.id);
				var brothers =  $('#tg').treegrid('getChildren', parentNode.id);
				if(node.id == brothers[brothers.length -1 ].id){
					alert("已经在底部！");
					return false;
				}
				$.ajax({
					type:"get",
					url: "/sda/moveDown?_t="+new Date().getTime(),
			        dataType: "json",
			        data: {"sdaId": node.id},
			        success: function(data){
			        	 if(data){
							 $('#tg').treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});

						 }
			            }
				});
			}
			
		}
		$.extend($.fn.validatebox.defaults.rules, {
                        unique: {
                            validator: function (value, param) {
                               if(value != 'root' && value != 'request' && value != 'response'){
                               		return true;
                               }
                                return false;
                            },
                            message: '新建节点名称不能为“root、request、response”'
                        }
                    });
</script>
</head>
<body >
<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
		<div onclick="editIt()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>

	</div>
<fieldset>
 <legend>条件搜索</legend>
<table border="0" cellspacing="0" cellpadding="0">

  <tr>
     <th>服务代码</th>
    <td><input class="easyui-textbox" disabled type="text" name="serviceId" value="${service.serviceId }" ></td>
    <th>服务名称</th>
    <td><input class="easyui-textbox" disabled type="text" name="serviceName" value="${service.serviceName }" ></td>
     <th>场景号</th>
    <td> <input class="easyui-textbox"disabled  type="text" name="operationId" value="${operation.operationId }" ></td>
 	 <th>场景名称</th>
        <td><input class="easyui-textbox" disabled type="text" name="operationName" value="${operation.operationName }" ></td>
  </tr>

</table>


</fieldset>
<form id="sdaForm">
	<table title="sda" class="easyui-treegrid" id="tg" style=" width:auto;"
			data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: '/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime(),
				method: 'get',
				idField: 'id',
				treeField: 'text',
                toolbar:'#tb',
                onContextMenu:onContextMenu"
                >
		<thead>
			<tr>
				<th data-options="field:'text',width:180, editor:'text'" editor="{ type : 'validatebox', options : { required : true, validType:'unique'} } ">字段名</th>
				<th data-options="field:'append1',width:60,align:'right',editor:'text'">字段别名</th>
				<th data-options="field:'append2',width:60,editor:'text'">类型/长度</th>
				<%--<th data-options="field:'append3',width:60,editor:'text'">长度</th>
				<th field="append4" width="80" editor="{type:'combobox', options:{method:'get', url:'/metadata/getAll', valueField:'metadataId',textField:'metadataName'}}">元数据</th>--%>
                <th data-options="field:'append5',width:60,editor:'text'">是否必输</th>
                <!--
               	<th data-options="field:'append6',width:80,formatter:formatConsole">备注</th>
               	-->
               	<th data-options="field:'append6',width:80,editor:'text'">备注</th>
			</tr>
		</thead>
	</table>
    <div id="tb" style="padding:5px;height:auto">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><a href="javascript:void(0)" onclick="moveUp()" class="easyui-linkbutton" iconCls="icon-up" plain="true">上移</a>&nbsp;&nbsp;
    	<a href="javascript:void(0)" onclick="moveDown()" class="easyui-linkbutton" iconCls="icon-down" plain="true">下移</a>&nbsp;&nbsp;
	    <!--
	    <a href="javascript:void(0)" onclick="addNode()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>&nbsp;&nbsp;
	    -->
	    <a href="javascript:void(0)" onclick="saveSDA()" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
    </td>
    <td align="right"></td>
  </tr>
</table>
</div>
</form>

  
  </body>
</html>
