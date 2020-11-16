<%@page import="com.front.Bean.MyInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../assets/images/favicon.jpeg">

<title>学生信息管理系统 | 个人中心</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../assets/styles/css/control_home.css" rel="stylesheet">

</head>
<%
	/* 解决直接打开当前页面没有值的问题 */
	MyInfo myInfo = (MyInfo) session.getAttribute("MyInfo");
	if (myInfo == null) {
		response.sendRedirect("../HomeServlet");
	}
%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="img-box">
				<img src="../assets/images/logo.webp" alt="..."
					style="width: 40px; height: 40px; display: inhert">
			</div>
			<a class="navbar-brand" href="home.jsp"> Impulse-学生信息管理系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-question-sign"></span> 关于</a></li>
				<li>
					<!-- Single button -->
					<div class="btn-group" style="height:50px;margin-right:10px">
						<button type="button" class="btn btn-default dropdown-toggle" style="top:8px"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-cog"></span> 设置
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">更改密码</a></li>
			
							<!-- 分割线 -->
							<li role="separator" class="divider"></li>
							<li><a href="#">退出登录</a></li>
						</ul>
					</div>
				</li>

				
			</ul>
			<form class="navbar-form navbar-left">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<!-- 左边菜单 -->
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#"> <span
							class="glyphicon glyphicon-user"></span> 个人中心
					</a></li>
					<li><a href="MyClass.jsp"><span
							class="glyphicon glyphicon-home"></span> 我的班级</a></li>
					<li><a href="CourseInfo.jsp"><span
							class="glyphicon glyphicon-blackboard"></span> 课程信息</a></li>
					<li><a href="MyCourseChat.jsp"><span
							class="glyphicon glyphicon-list-alt"></span> 班级课程表</a></li>
					<li><a href="MyGrade.jsp"><span
							class="glyphicon glyphicon-thumbs-up"></span> 我的成绩</a></li>
				</ul>
				<!--      <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul> -->
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">个人中心</h1>

				<div class="row placeholders">
					<!-- 头像 -->
					<div class="col-xs-12 col-sm-12 col-md-3 placeholder">
						<img src="../assets/images/avatar.jpg" width="200" height="200"
							class="" alt="Generic placeholder thumbnail">
						<h4>${MyInfo.name}</h4>
						<span class="text-muted">${MyInfo.sno}</span>
					</div>
					<!-- 个人详细信息 -->
					<div class="col-xs-12 col-sm-12  col-md-9 placeholder">
						<div class="panel panel-default panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">个人信息</h3>
							</div>
							<div class=" panel-body">
								<ul class="list-group col-xs-5 col-sm-5  col-md-4">
									<li class="list-group-item list-group-item-info">学号</li>
									<li class="list-group-item list-group-item-success">姓名</li>
									<li class="list-group-item list-group-item-info">性别</li>
									<li class="list-group-item list-group-item-success">生日</li>
									<li class="list-group-item list-group-item-info">专业</li>
									<li class="list-group-item list-group-item-success">班级</li>
									<li class="list-group-item list-group-item-info">家庭住址</li>
									<li class="list-group-item list-group-item-success">手机号</li>
									<li class="list-group-item list-group-item-info">应届往届</li>
								</ul>
								<ul class="list-group col-xs-7 col-sm-7  col-md-8">
									<li class="list-group-item list-group-item-info">${MyInfo.sno}</li>
									<li class="list-group-item list-group-item-success">${MyInfo.name}</li>
									<li class="list-group-item list-group-item-info">${MyInfo.sex}</li>
									<li class="list-group-item list-group-item-success">${MyInfo.birthday}</li>
									<li class="list-group-item list-group-item-info">${MyInfo.major}</li>
									<li class="list-group-item list-group-item-success">${MyInfo.myClass}</li>
									<li class="list-group-item list-group-item-info">${MyInfo.address}</li>
									<li class="list-group-item list-group-item-success">${MyInfo.phone}</li>
									<li class="list-group-item list-group-item-info">${MyInfo.remark}</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>