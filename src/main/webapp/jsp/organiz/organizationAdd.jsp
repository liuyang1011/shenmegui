<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
</head>

<body>
<fieldset>
  <table width="99%" border="0" cellspacing="0" cellpadding="0" id="orgTable">
    <tr>
      <th>机构代码</th>
      <td><input name="orgId" class="easyui-validatebox" type="text" id="orgId2" data-options="required:true,validType:'englishB'" /><font color="#FF0000">*</font></td>
    </tr>
    <tr>
      <th>机构名称</th>
      <td><input name="orgName" class="easyui-textbox" type="text" id="orgName2" data-options="required:true,validType:'chineseB'"/><font color="#FF0000">*</font>
      </td>
    </tr>
    <tr>
      <th>机构分类上级</th>
      <td><input name="orgAB" class="easyui-textbox" type="text" id="orgAB2" data-options="validType:'chineseB'"/></td>
    </tr>
    <tr>
      <th>机构状态</th>
      <td><input name="orgStatus" class="easyui-textbox" type="text" id="orgStatus2" data-options="validType:'chineseB'"/></td>
    </tr>
    <tr>
      <th>机构责任人</th>
      <td><input name="orgResponsibility2" class="easyui-textbox" type="text" id="orgResponsibility2" data-options="validType:'chineseB'"/></td>
    </tr>
    <tr>
      <th>机构备注</th>
      <td><input name="orgRemark2" class="easyui-textbox" type="text" id="orgRemark2" data-options="validType:'chineseB'"/></td>
    </tr>
  </table>
</fieldset>
<div style="margin-top:10px;"></div>

<div align="right">
  <a class="easyui-linkbutton" id="saveBtn">保存</a>
  &nbsp;&nbsp;
  <a class="easyui-linkbutton" id="close" onclick="$('#w').window('close');">关闭</a>
</div>
<script type="text/javascript" src="/js/org/organizationManager.js"></script>
<script type="text/javascript" src="/plugin/validate.js"></script>
<script type="text/javascript">
  var orgFormatter = {
    "creatDate":function(){
      var nowDate = new Date();
      var stringDate = nowDate.getFullYear()+"/"+(nowDate.getMonth()+1)+"/"+(nowDate.getDate());
      return stringDate.toString();
    }
  };
  $('#saveBtn').click(function () {
    var data = {};
    var orgData;
    var hasOrgId;
    var hasOrgName;
    $.ajax({
      type:"GET",
      url:"/org/getAllOrg",
      async:false,
      success:function(msg){
        orgData = msg;
      }
    });
    data.orgId = $('#orgId2').val();
    for(var i = 0,len = orgData.length; i < len; i++){
      if(orgData[i].orgId==data.orgId){
        hasOrgId = true;
        break;
      }else{
        hasOrgId = false;
      }
    }
    data.orgName = $('#orgName2').val();
    for(var i = 0,len = orgData.length; i < len; i++){
      if(orgData[i].orgName==data.orgName){
        hasOrgName = true;
        break;
      }else{
        hasOrgName = false;
      }
    }
    data.orgAB = $('#orgAB2').val();
    data.orgStatus = $('#orgStatus2').val();
    data.orgRemark = $('#orgRemark2').val();
    data.orgResponsibility = $('#orgResponsibility2').val();
    data.orgUpDate = orgFormatter.creatDate();
    if(hasOrgId){
      alert("该机构号已存在");
    }else{
      if(hasOrgName){
        alert("该机构名称已存在");
      }else{
        organizationManager.add(data, function (result) {
          if (result) {
            alert("保存成功");
            $('#w').window('close');
            $('#orgTab').datagrid('reload');
          } else {
            alert("保存失败");
          }
        });
      }
    }
  });
</script>
</body>
</html>
