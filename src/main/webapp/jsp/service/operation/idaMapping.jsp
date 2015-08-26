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
  <legend>接口信息</legend>
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>系统id</th>
      <td>
        <input class="easyui-text" readonly="true" value="${system.systemId}" style="width:140px" type="text" name="name" id="masterName"/>
      </td>
      <th>系统名称</th>
      <td>
        <input class="easyui-text" readonly="true" value="${system.systemChineseName}" style="width:140px" type="text" name="name" id="masterName"/>
      </td>
      <th>接口id</th>
      <td>
        <input class="easyui-text" readonly="true" value="${interface.interfaceId}" style="width:140px" type="text" name="name" id="masterName"/>
      </td>
      <th>接口名称</th>
      <td>
        <input class="easyui-text" readonly="true" value="${interface.interfaceName}" style="width:140px" type="text" name="name" id="slaveName"/>
      </td>
    </tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </table>
</fieldset>
<table title="ida映射" id="mappingdatagrid" style="height:530px; width:auto;">
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/js/ida/idaManager.js"></script>
<script type="text/javascript"
        src="/assets/enumManager/js/enumManager.js"></script>
<script type="text/javascript">
  var editedRows = [];
  var processId = parent.processId;
  var taskId = parent.taskId;
  $(function(){
    var url = "/ida/getIdaMapping/"+"${interface.interfaceId}";
    $('#mappingdatagrid').datagrid({
      rownumbers:true,
      singleSelect:false,
      collapsible:true,
      url:url,
      method:'get',
      toolbar:toolbar,
//      pagination:true,
      pageSize:10,
      columns:[[
        {field:'productid',checkbox:true},
        {field:'structName',title:'字段名称',width:'30%'},
        {field:'structAlias',title:'字段别名',width:'30%'},
        {field:'metadataId',title:'元数据ID',required : true,width:'30%',
          editor:{
            type:'combobox',
            options:{
              url : '/ida/getSdaMapping/'+"${service.serviceId}" + "/" +"${operation.operationId}",
              method : 'get',
              valueField : 'metadataId',
              textField : 'metadataId',
              panelHeight : '200px'
            }
          }
        }
      ]],
      onDblClickCell: function(index,field,value){
        $(this).datagrid('beginEdit', index);
        var ed = $(this).datagrid('getEditor', {index:index,field:field});
        $(ed.target).focus();
      },
      onBeginEdit : function(index,row){
        editedRows.push(index);
      },
      onLoadSuccess : function (data){

      },
      onLoadError: function (responce) {
        var resText = responce.responseText;
        if(resText.toString().charAt(0) == "<"){
//                    alert("没有权限！");
          window.location.href = "/jsp/403.jsp";
        }
      }

    });
  });
  var toolbar = [ {
    text : '删除映射关系',
    iconCls : 'icon-remove',
    handler : function() {
      var selectData = $('#mappingdatagrid').datagrid('getSelections');
      if (selectData.length == 0) {
        alert("请先选择一条记录");
        return;
      }
      if(confirm('确定删除映射关系吗 ？')){
        idaManager.deleteIdaMapping(selectData, function(result){
          if(result){
            $('#mappingdatagrid').datagrid('reload');
          }
        });
      }
    }
  },{
    text : '保存映射关系',
    iconCls : 'icon-remove',
    handler : function() {
      for ( var per in editedRows) {
        $("#mappingdatagrid").datagrid('endEdit', editedRows[per]);
      }
      var editData = $("#mappingdatagrid").datagrid('getChanges');
      console.log(editData);
      idaManager.saveIdaMapping(editData,function(result){
        if(result){
          $('#mappingdatagrid').datagrid('reload');
        }
      });
      editedRows = [];
    }
  } ];
</script>
</body>
</html>