<%@page import="javafx.scene.chart.PieChart.Data,java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
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
	String[] data = null;
	if (cookies != null && cookies.length > 0) {
		for (Cookie c : cookies) {
			if (c.getName().equals("remember")) {
				remember = c;
				//字符串分割
				data = URLDecoder.decode(remember.getValue(), "utf-8").split(","); /*使用utf-8读取*/
				//out.print(data[2]);
			}
		}
	}
%>
<body>
	<div class="container">
	
			<!-- 警告框 -->
		<div class="alert alert-danger alert-dismissible fade in" role="alert"
			id="warningBox" style="display:none">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4>错误！</h4>
			<p>${error}</p>
		</div>
		
			<!-- 成功框 -->
		<div class="alert alert-success alert-dismissible fade in" role="alert"
			id="successBox" style="display:none;width:300px">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4>成功！</h4>
			<p>${success}</p>
		</div>

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
					value="<%=remember == null ? "" : data[0]%>"> <label
					for="inputPassword" class="sr-only">密码</label> <input
					type="password" id="inputPassword" class="form-control"
					name="password" placeholder="密码" required=""
					value="<%=remember == null ? "" : data[1]%>">
				<label for="inputAccount" class="sr-only">验证码</label>
				<input type="text" name="checkCode"  class="form-control" required="" id="inputCheckCode" style="width:50%;display:inline" placeholder="验证码">
				<img src="./tools/checkCode.jsp" id="checkCodeImage"/>
				
				<div class="checkbox">
					<label> <input type="checkbox" name="isChecked"
						<%=remember == null ? "" : "checked"%>> 记住我
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</form>
		</div>
	</div>


	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script>
		//点击刷新验证码
		$("#checkCodeImage").click(function(){
			//console.log($(this).attr("src"));
			$(this).attr("src","./tools/checkCode.jsp?"+Math.random());
		});	
		
	</script>
		<%
			String error = (String) session.getAttribute("error");
			if (error != null) {
		%>
			<script>
				$("#warningBox").slideDown();
				setTimeout(()=>{
					$("#warningBox").slideUp();
				},3000)
			</script>
	
		<%
			}
			
			session.setAttribute("error", null);
		%>
		
			<%
			String success = (String) session.getAttribute("success");
			if (success != null) {
		%>
			<script>
				$("#successBox").slideDown();
				setTimeout(()=>{
					$("#successBox").slideUp();
				},3000)
			</script>
	
		<%
			}
			session.setAttribute("success", null);
		%>
		
</body>
</html>