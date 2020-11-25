<%@page import="com.administrator.bean.RegisterStudentInfo"%>
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

<title>学生信息管理系统 | 用户管理</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../assets/styles/css/Administrator/home.css"
	rel="stylesheet">

</head>
<%
	/* 解决直接打开当前页面没有值的问题 */
 	Object obj =session.getAttribute("RegisterStudentInfo");
	if (obj == null) {
		response.sendRedirect("../administratorHome");
	} 
%>
<body>
<!-- 模态框 -->
		<div class="modal" tabindex="-1" role="dialog"  id="myModal" style="display:none">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <h4 class="modal-title"><span class="glyphicon glyphicon-question-sign"></span> 提示</h4>
          </div>
          <div class="modal-body">
            <p>确定要删除吗？</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancelDelete()">取消</button>
            <button type="button" class="btn btn-primary" onclick="comfirmDelete()">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div>
    
    <!-- 模态框2 -->
		<div class="modal" tabindex="-1" role="dialog"  id="myModal2" style="display:none">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <h4 class="modal-title"><span class="glyphicon glyphicon-question-sign"></span> 提示</h4>
          </div>
          <div class="modal-body">
            <p>确定要重置吗？</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancelDelete()">取消</button>
            <button type="button" class="btn btn-primary" onclick="comfirmReset()">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div>
    

		<!-- 警告框 -->
		<div class="alert alert-danger alert-dismissible fade in" role="alert"
			id="warningBox" style="display:none;width:300px;height:70px;z-index:1000;position:absolute;left:0;right:0;top:0;bottom:0;margin:60px auto">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4>错误！</h4>
			<p>${error}</p>
		</div>
		
			<!-- 成功框 -->
		<div class="alert alert-success alert-dismissible fade in" role="alert"
			id="successBox" style="display:none;width:300px;height:70px;z-index:1000;position:absolute;left:0;right:0;top:0;bottom:0;margin:60px auto">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4>成功！</h4>
			<p>${success}</p>
		</div>
		
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
				<li><a href="https://itaylorfan.github.io/me/" target="blank"><span
						class="glyphicon glyphicon-question-sign"></span> 关于</a></li>
				<li>
					<!-- Single button -->
					<div class="btn-group" style="height: 50px; margin-right: 10px">
						<button type="button" class="btn btn-default dropdown-toggle"
							style="top: 8px" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							<span class="glyphicon glyphicon-cog"></span> 设置
						</button>
						<ul class="dropdown-menu">
							<li><a href="./ChangePassword.jsp" style="color: blue"><span
									class="glyphicon glyphicon-pencil"></span> 更改密码</a></li>

							<!-- 分割线 -->
							<li role="separator" class="divider"></li>
							<li><a href="../administratorExitLogin" style="color: red"><span
									class="glyphicon glyphicon-off"></span> 退出登录</a></li>
						</ul>
					</div>
				</li>


			</ul>
			<form class="navbar-form navbar-left">
				<input type="text" class="form-control" placeholder="请输入内容..." id="searchInput">
				<button type="button" class="btn btn-default"style="top: 8px" id="search">
							<span class="glyphicon glyphicon-search"></span> 搜索
				</button>
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<!-- 左边菜单 -->
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li ><a href="Home.jsp"> <span
							class="glyphicon glyphicon-home"></span> 用户管理
					</a></li>
					<li><a href="StudentInfo.jsp"><span class="glyphicon glyphicon-user"></span>
							学生信息管理</a></li>
					<li><a href="CourseInfo.jsp"><span
							class="glyphicon glyphicon-blackboard"></span> 班级信息管理</a></li>
					<li><a href="MyCourseChart.jsp"><span
							class="glyphicon glyphicon-list-alt"></span> 课程信息管理</a></li>
					<li><a href="MyGrade.jsp"><span
							class="glyphicon glyphicon glyphicon-th"></span> 课程表信息管理</a></li>
						<li><a href="MyGrade.jsp"><span
							class="glyphicon glyphicon-thumbs-up"></span> 成绩信息管理</a></li>
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-asterisk"></span> 个人中心</a></li>		
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">用户管理</h1>

				<!-- <h4 class="sub-header">班级信息</h4> -->

				<div class="panel panel-default panel-primary"
					style="border-top: none; text-align: center">
					<div class="panel-heading">
						<h3 class="panel-title">已注册学生用户信息</h3>
					</div>
					<div class=" panel-body" >
					<div class="table-responsive">
						<table class="table table-condensed" >
						<thead>
							<tr>
								<th>用户名</th>							
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th><button type="button" class="btn btn-info btn-xs" style="" id="refresh">刷新</button></th>
							</tr>
						</thead>
							<tbody>
							<c:forEach items="${RegisterStudentInfo}" var="ci">
							<tr>
								<td>${ci.username}</td>
								<td>${ci.sno}</td>
								<td>${ci.name}</td>
								<td>${ci.sex}</td>
								<td style="width:28%">
								<button type="button" class="btn btn-success btn-sm" style="outline:none" onclick="resetDialog()">重置密码</button>
								<button type="button" class="btn btn-danger btn-sm" style="outline:none" onclick="deleteDialog()">删除</button>
								</td>
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
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		
	<script>
		/*搜索*/
		$("#search").click(function(){
			let text=$("#searchInput").val();
			if(text!==""){
				//console.log(text);
				window.location = "../administratorHomeSearch?query="+text;
			}
			
		})
	
		/* 	弹出确认重置框 */
		function resetDialog(e){
			const event=e||window.event;
			let sno=event.target.parentNode.parentNode.children[1].innerHTML;
			//显示模态框
			$('#myModal2').modal("show");
			//暂存数据
			$('#myModal2').attr("data",sno);
		}
		/* 确认重置 */
		function comfirmReset(){
			let sno;
	    	sno=$('#myModal2').attr("data");
	    	//console.log(sno);
	    	window.location = "../administratorHomeReset?sno="+sno;
		}
		
		/* 取消删除 */
		function cancelDelete(){
			//删除节点中的数据
			$('#myModal').removeAttr("data");
			
		}
		/*确定删除*/
	    function comfirmDelete(){
			let sno;
	    	sno=$('#myModal').attr("data");
	    	console.log(sno);
	    	window.location = "../administratorHomeDelete?sno="+sno;
	    }
	    /* 触发删除模态框 */
		function deleteDialog(e){
			const event=e||window.event;
			let sno=event.target.parentNode.parentNode.children[1].innerHTML;
			//显示模态框
			$('#myModal').modal("show");
			//暂存数据
			$('#myModal').attr("data",sno);
			//$(".modal-body>p").text("确定要删除吗？");
			//window.location = "../administratorHomeDelete";
		}
		/* 刷新按钮 */
		$("#refresh").click(function(){
			window.location = "../administratorHome"
		})
		
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