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
  <script type="text/javascript" src="/assets/task/taskManager.js"></script>
  <script type="text/javascript" src="/js/user/userManager.js"></script>
    <script src="/newui/bootstrap/js/md5.js" type="text/javascript" ></script>
    </head>
        <body>
        <input id="id" type="hidden" value="<shiro:principal/>"/>
        <fieldset>
            <legend>密码修改</legend>
          <table width="99%" border="0" cellspacing="0" cellpadding="0" id="passWord">
            <tr>
              <th>旧密码</th>
              <td><input name="oldPW" class="easyui-textbox" type="password" id="oldPW" /><font color="#FF0000">*</font></td>
            </tr>
            <tr>
              <th>新密码</th>
              <td><input name="newPW" class="easyui-textbox" type="password" id="newPW" /><font color="#FF0000">*</font></td>
            </tr>
            <tr>
              <th>确认密码</th>
              <td><input name="confirmPW" class="easyui-textbox" type="password" id="confirmPW" /><font color="#FF0000">*</font></td>
            </tr>
          </table>
        </fieldset>
        <div style="margin-top:10px;"></div>
        <div align="center"><a class="easyui-linkbutton" id="saveBtn">修改</a>
        </div>
        <script type="text/javascript">
          var pw="";
          var userI="";
          $(function() {
            userI=$("#id").val();
            $.ajax({
              type: "GET",
              url: "/user/findById/"+userI,
              async: false,
              success: function(msg){
                pw=msg.password;
              }
            });

            $('#saveBtn').click(function(){
              var str1 = $('#oldPW').val();
                str1=hex_md5(str1);
              var str2 = $('#newPW').val();
              var str3 = $('#confirmPW').val();
              var parent1=/^[u4E00-u9FA5]+$/;
              if(!parent1.test(str2))
              {
                alert("密码不能使用中文或为空");
                return false;
              }
// 			alert(str1+"旧"+str2+"新"+str3+"确"+pw+"原")
              if(str1!=pw){
                alert("输入的旧密码有误！");
                return false;
              }
              if(str2!=str3){
                alert("两次输入的新密码不一致！");
                return false;
              }
              if(str2==pw){
                alert("新密码不能与旧密码一样！");
                return false;
              }
// 			var data = {};
// 			data.id =userI;
// 			data.password=str2;
              userManager.passWord(userI,hex_md5(str2),function(result) {
                if (result) {
                    alert("修改成功,请重新登录！");
                    parent.location.href="/logout/";
                } else {
                  alert("修改失败");
                }
              });
            });
          });
        </script>
        </body>
</html>
