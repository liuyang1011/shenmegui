<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.sql.*" errorPage=""%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>列表页</title>
  <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/resources/js/ui.js"></script>
</head>
<body>
<fieldset>
  <legend>个人信息修改</legend>
            <table width="500" height="210" border="0" cellspacing="0" align="center">
                <tr>
                  <th width="78"><nobr>用户代码：</nobr></th>
                  <td width="168" height="41" scope="col"><input name="text" type="text" id="id" readonly="true" value="<shiro:principal/>" class="easyui-textbox"/></td>
                  <th width="78"><span style="color: red;">*</span><nobr>用户名称：</nobr></th>
                  <td width="168" scope="col"><input name="text2" type="text" id="name" class="easyui-textbox"  data-options="required:true"/></td>
                </tr>
                <tr>
                  <th><span style="color: red;">*</span><nobr>邮箱：</nobr></th>
                  <td height="41"><input name="text2" type="text" id="email" class="easyui-textbox"  data-options="required:true"/></td>
                  <th><span style="color: red;">*</span><nobr>所属机构：</nobr></th>
                  <td><input name="text2" type="text" id="orgEdit" class="easyui-textbox" data-options="required:true"/></td>
                </tr>
                <tr>
                  <th><span style="color: red;">*</span><nobr>手机号码：</nobr></th>
                  <td height="47"><input name="text2" type="text" id="userMobile" class="easyui-textbox" data-options="required:true,validType:'mobile'"/></td>
                  <th><nobr>电话号码：</nobr></th>
                  <td><input name="text2" type="text" id="userTel" class="easyui-textbox"/></td>
                </tr>
                <tr>
                  <th><nobr>备注：</nobr></th>
                  <td height="55%" colspan="3"><textarea id="remark" rows="5" cols="56"></textarea></td>
                </tr>
            </table>
  </fieldset>
<div align="center"><a class="easyui-linkbutton" id="saveBtn">修改</a></div>
</body>
<script type="text/javascript">
  var userInfo="";
  var orgI="";
  $(function($) {
    var id=$("#id").val();
    $.ajax({
      type: "POST",
      url: "/user/findById",
      data:"id="+id,
      async: false,
      success: function(msg){
        userInfo=msg;
        $("#id").textbox("setValue",msg.id);
        $("#name").textbox("setValue",msg.name);
        $("#userTel").textbox("setValue",msg.userTel);
        $("#remark").val(msg.remark);
        $("#userMobile").textbox("setValue",msg.userMobile);
        $("#email").textbox("setValue",msg.email);
      }
    });
    $('#orgEdit').combobox({
      url: '/org/getAll',
      method: 'get',
      mode: 'remote',
      editable:false,
      valueField: 'orgId',
      textField: 'orgName',
      onLoadSuccess: function () {
        orgI=userInfo.orgId;
        if (orgI != '') {
          $('#orgEdit').combobox('select',orgI);
        }
      }
    });
  });
</script>
  <script type="text/javascript">
    $('#saveBtn').click(function () {
      var orgEdit=$('#orgEdit').combobox('getValue');
      var id=$("#id").val();
      var name=$("#name").val();
      var userTel=$("#userTel").val();
      var remark=$("#remark").val();
      var userMobile=$("#userMobile").val();
      var email=$("#email").val();
      var data = {};
      data.id = id;
      data.name = name;
      data.userMobile = userMobile;
      data.userTel = userTel
      data.orgId = orgEdit;
      data.remark = remark;
      data.password=userInfo.password;
      data.lastdate=userInfo.lastdate;
      data.startdate=userInfo.startdate;
      data.email=email;
      if(name==""||orgEdit==""||userMobile==""||email==""){
        return;
      }
      $.ajax({
        type: "POST",
        url: "/user/save/"+id,
        data: data,
        success: function(msg){
          if(msg){
            alert("设置成功！");
            $('#w').window('close');
              }else{
            alert("设置失败！");
          }
        }
      });
    });

  </script>
</html>
