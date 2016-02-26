<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <title></title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
</head>
<body>
<form class="formui" id="metadataForm" action="/metadata/add" method="post">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th>工作流程</th>
      <td><input type="text" name="workFlow2" id="workFlow2"
                 class="easyui-combobox"
                 data-options="
                     required:true,
						url:'workFlow.json',
				 		 method:'get',
				 		 valueField: 'id',
				 		 textField: 'name',
				 		 onChange:function(newValue, oldValue){
							this.value=newValue;
						}
					"
              /></td>
    </tr>

    <tr>
      <th>任务类型</th>
      <td><input type="text" name="taskType2" id="taskType2"
                 class="easyui-combobox"
                 data-options="
                     required:true,
						url:'taskType.json',
				 		 method:'get',
				 		 valueField: 'id',
				 		 textField: 'name',
				 		 onChange:function(newValue, oldValue){
							this.value=newValue;
						}
					"
              /></td>
    </tr>
    <tr>
      <th>责任人</th>
      <td><input class="easyui-textbox" name="responsibility" id="responsibility" ></td>
    </tr>
    <tr>
      <th>任务描述</th>
      <td><input class="easyui-textbox"  type="text" name="taskDescribe2" id="taskDescribe2"></td>
    </tr>
    <%--<tr>--%>
      <%--<th>计划起始日期</th>--%>
      <%--<td><input class="easyui-datebox" type="text" name="name" ></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<th>计划结束日期</th>--%>
      <%--<td><input class="easyui-datebox" type="text" name="name" ></td>--%>
    <%--</tr>--%>
    <tr>
      <td>&nbsp;</td>
      <td class="win-bbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="createTaskBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
      </td>
    </tr>
  </table>
</form>
    <script type="text/javascript" src="/js/task/taskManage.js"></script>
    <script type="text/javascript" src="/js/task/taskAdd.js"></script>
</body>
</html>