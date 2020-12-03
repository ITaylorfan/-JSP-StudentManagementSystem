<%@page import="com.front.Bean.MyGrade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
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

    <title>学生信息管理系统 | 我的成绩</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../assets/styles/css/control_home.css" rel="stylesheet">

</head>
<%
	/* 解决直接打开当前页面没有值的问题 */
	List<MyGrade> myGrade=(List<MyGrade>) session.getAttribute("MyGrade");
	if (myGrade==null) {
		response.sendRedirect("../myGrade");
	} 
%>
<body>
 <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="img-box">
          		<img src="../assets/images/logo.webp" alt="..." style="width: 40px; height: 40px; display:inhert">
          </div>
          <a class="navbar-brand" href="home.jsp">
          
          Impulse-学生信息管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
         	<ul class="nav navbar-nav navbar-right">
			<li><a href="https://itaylorfan.github.io/me/" target="blank"><span class="glyphicon glyphicon-question-sign"></span> 关于</a></li>
				<li>
					<!-- Single button -->
					<div class="btn-group" style="height:50px;margin-right:10px">
						<button type="button" class="btn btn-default dropdown-toggle" style="top:8px"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-cog"></span> 设置
						</button>
						<ul class="dropdown-menu">
							<li><a href="./changePassword.jsp" style="color:blue"><span class="glyphicon glyphicon-pencil"></span> 更改密码</a></li>
			
							<!-- 分割线 -->
							<li role="separator" class="divider"></li>
							<li><a href="../exitLogin" style="color:red"><span class="glyphicon glyphicon-off"></span> 退出登录</a></li>
						</ul>
					</div>
				</li>

				
			</ul>
		<!-- 	<form class="navbar-form navbar-left">
				<input type="text" class="form-control" placeholder="Search...">
			</form> -->
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
      <!-- 左边菜单 -->
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">  
            	<li><a href="../HomeServlet"> <span
							class="glyphicon glyphicon-user"></span> 个人中心
					</a></li>
					<li><a href="../MyClassServlet"><span
							class="glyphicon glyphicon-home"></span> 我的班级</a></li>
					<li><a href="../courseInfo"><span
							class="glyphicon glyphicon-blackboard"></span> 课程信息</a></li>
					<li><a href="MyCourseChart.jsp"><span
							class="glyphicon glyphicon-list-alt"></span> 班级课程表</a></li>
					<li class="active"><a href="../myGrade"><span
							class="glyphicon glyphicon-thumbs-up"></span> 我的成绩</a></li>
          </ul>
          
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">我的成绩</h1>

   				<div class="panel panel-default panel-primary"
					style="border-top: none;">
					<div class="panel-heading">
						<h3 class="panel-title">成绩信息</h3>
					</div>
					<div class=" panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>课程号</th>
										<th>课程名</th>
										<th style="color:green">成绩</th>
										<th>学分</th>
										<th>任课老师</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${MyGrade}" var="mg">
									<tr>
										<td>${mg.cno}</td>
										<td>${mg.cname}</td>
										<td style="color:green">${mg.grade}</td>
										<td>${mg.credit}</td>		
										<td>${mg.teacher}</td>					
									</tr>
								</c:forEach>
								</tbody>
							</table>
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
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
