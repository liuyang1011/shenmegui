<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<html>
<head>
    <title></title>
</head>
<body>
<div id="systemBar" style="text-align:center;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="addUserSystem()">确定</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close');">取消</a>
</div>
<table id="systemList" class="easyui-datagrid"
       data-options="
			rownumbers:true,
			singleSelect:false,
			url:'/userSystemRelation/getByUserId?userId=${param.userId }',
			method:'get',
			onLoadSuccess:function(row){//当表格成功加载时执行
                var rowData = row.rows;
                $.each(rowData,function(idx,val){//遍历JSON
                      if(val.checked==1){
                        $('#systemList').datagrid('selectRow', idx);//如果数据行为已选中则选中改行
                      }
                });
           }
          "
       style="height:370px; width:98%;">
  <thead>
  <tr>
    <th data-options="field:'checked',checkbox:true"></th>
    <th data-options="field:'systemNo',width:100">系统编号</th>
    <th data-options="field:'systemChineseName',width:200">系统名称</th>
    <th data-options="field:'systemAb', width:150">系统简称</th>
  </thead>
</table>
</body>
</html>
