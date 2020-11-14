<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="./assets/images/favicon.jpeg">
    <!-- Bootstrap core CSS -->
    <link href="./assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- My CSS -->
    <link href="./assets/styles/css/login.css" rel="stylesheet">
<title>登录</title>
</head>
<body>
	<div class="container">
	
	<div class="form-box">
      <form class="form-signin" action="Register" method="post">
        <h2 class="form-signin-heading">注册</h2>
        <label for="inputAccount" class="sr-only">学号</label>
        <input type="text" id="inputAccount" class="form-control" name="account" placeholder="学号" required="" autofocus="">
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" name="password" placeholder="密码" required="">
     <!--    <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div> -->
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>
	</div>
    </div>
    
    
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>