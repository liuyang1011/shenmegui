<%--
  Created by IntelliJ IDEA.
  User: vincentfxz
  Date: 15/7/2
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta charset="UTF-8">
  <title>服务治理平台 | 登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <!-- Bootstrap 3.3.4 -->
  <link href="../../newui/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <!-- Font Awesome Icons -->
  <!-- Theme style -->
  <link href="../../newui/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
  <!-- iCheck -->
  <link href="../../newui/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <![endif]-->
  <script type="text/javascript">
            var errMsg = '${errMsg}';
            if(errMsg != null && errMsg!= ""){
              alert(errMsg);
            }
            var topFlag = '${topFlag}';
            if(topFlag){
                top.location.href="/login/?topFlag=true" ;
            }


  </script>
</head>
<body class="login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../../newui/index2.html"><b>服务治理平台</b>SG</a>
  </div><!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">登入</p>
      <div class="form-group has-feedback">
        <input name="username" type="user" class="form-control" placeholder="用户名" id="username"/>
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input name="password" type="password" class="form-control" placeholder="密码" id="password"/>
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-4" style="width:100%">
          <button class="btn btn-primary btn-block btn-flat" onclick="login()">登 录</button>
        </div><!-- /.col -->
      </div>

    <div class="social-auth-links text-center">
      <p>- OR -</p>
      <!--
      <a href="#" class="btn btn-block btn-social  btn-flat"><i class="fa"></i> 游客登录 </a>
      -->
    </div><!-- /.social-auth-links -->

  </div><!-- /.login-box-body -->
</div><!-- /.login-box -->

<!-- jQuery 2.1.4 -->
<script src="../../newui/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="../../newui/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- iCheck -->
<script src="../../newui/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
<!--md5加密-->
<script src="../../newui/bootstrap/js/md5.js" type="text/javascript" ></script>

<script type="text/javascript">
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });

</script>
<script type="text/javascript">
  function login(){
    var username=$("#username").val();
    var password=$("#password").val();
    password=hex_md5(password);
    var temp=document.createElement("form");
    temp.action="/login/";
    temp.method="post";
    temp.style.display="none";
    var opt=document.createElement("input");
    opt.name="username";
    opt.value=username;
    var opt1=document.createElement("input");
    opt1.name="password";
    opt1.value=password;
    temp.appendChild(opt);
    temp.appendChild(opt1);
    temp.submit();
  }
</script>
</body>
</html>
