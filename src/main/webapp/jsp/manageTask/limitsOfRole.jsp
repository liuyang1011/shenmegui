<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form class="formui">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th align="center">可选择角色</th>
      <td width="50" align="center">&nbsp;</td>
      <th align="center">已选择角色</th>
    </tr>

    <tr>
      <th align="center"><select name="select2" id="olddataui" size="10" multiple  style="width:155px;height:160px" panelHeight="auto">
      </select></th>
      <td align="center" valign="middle"><a href="#" class="easyui-linkbutton"  iconCls="icon-select-add" onClick="uiinit.selectex('olddataui','newdataui')"></a><br>
        <br>
        <br>
        <a href="#" class="easyui-linkbutton"  iconCls="icon-select-remove" onClick="uiinit.selectex('newdataui','olddataui')"></a></td>
      <td align="center"><select name="select" id="newdataui" size="10" multiple  style="width:155px;height:160px" panelHeight="auto">

      </select></td>
    </tr>
    <tr>
      <th align="center">&nbsp;</th>
      <td align="center" valign="middle">&nbsp;</td>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <th colspan="3" class="win-bbar" align="center"><a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a>
        <a href="#" id="limitsOfRoleBtn" class="easyui-linkbutton"  iconCls="icon-save">授权</a></th>
    </tr>
  </table>
</form>
<script type="text/javascript">
var roleData;
var userData;
$(function (){
          /*获得返回的所有角色*/
  $.ajax({
    type:"GET",
    url:"/role/getAllRole",
    async:false,
    success:function(msg){
      roleData = msg;
    }
  });
          /*生成option,并将获得数据插入*/
  for(var i = 0,len = roleData.length; i<len; i++){
      $('#olddataui').append($("<option value='"+roleData[i].id+"'>"+roleData[i].name+"</option>"));
    }
  }
);
$(function (){
  $("#limitsOfRoleBtn").click(function () {
    var arr = [];
    /*从右边的选择框中获取从左边选择框选择的数据*/
    var options = $('#newdataui option');
    /*将所有的角色放入数组中*/
    for (var i = 0; i < options.length; i++) {
      arr.push(options.eq(i).html());
    }
    /*拿到所有的角色之后，将所有角色放入数据库中*/
    if(arr[0] != undefined){
      var data = {};
      data.taskNum = Global.taskNum;
      data.workFlow = Global.workFlow;
      data.taskPoint = Global.taskPoint;
      data.responsibility = Global.responsibility;
      data.urgencyDegree = Global.urgencyDegree;
      data.taskDescribe = Global.taskDescribe;
      data.startDate = Global.startDate;
      data.endDate = Global.endDate;
      data.status = Global.status;
      data.authority = arr.toString();
      taskManage.createTask(data, function(result) {
        if (result) {
          alert("授权成功");
          $('#taskTab').datagrid('reload');
        } else {
          alert("授权失败");
        }
      });
      alert(arr.toString());
    }else{
      alert("请您选择一些数据！");
    }
  })
});
</script>

