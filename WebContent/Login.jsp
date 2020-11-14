<%@page import="javafx.scene.chart.PieChart.Data"%>
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

    <%
        Cookie[] cookies = request.getCookies();
        Cookie remember = null;
        String[] data=null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("remember")) {
                    remember = c;
                    //字符串分割
                    data=remember.getValue().split(",");
                   	//out.print(data[1]);
                }
            }
        }
    %>
<body>
	<div class="container">
		<%
			String error=(String)session.getAttribute("error");
			if(error!=null&&error.equals("账号或密码错误！")){
			%>

		<!-- 警告框 -->
				<div class="alert alert-danger alert-dismissible fade in" role="alert" id="warningBox">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4>错误！</h4>
					<p>账号或密码错误</p>
				</div>
				<%
			}
		%>
		
		
		<div class="form-box">
			<div class="img-box"></div>
			<div class="title-box">
				<span>学生信息管理系统登录</span>
			</div>

			<form class="form-signin" action="loginCheck" method="post">
				<!--  <h3 class="form-signin-heading">登陆</h2> -->
				<label for="inputAccount" class="sr-only">用户名</label> <input
					type="text" id="inputAccount" class="form-control" name="account"
					placeholder="用户名" required="" autofocus=""
					value="<%=remember==null?"":data[0] %>"> <label
					for="inputPassword" class="sr-only">密码</label> <input
					type="password" id="inputPassword" class="form-control"
					name="password" placeholder="密码" required=""
					value="<%=remember==null?"":data[1] %>">
				<div class="checkbox">
					<label> <input type="checkbox" name="isChecked"
						<%=remember==null?"":"checked" %>> 记住我
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</form>
		</div>
	</div>


	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>