<%@page import="com.administrator.bean.GradeInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.administrator.bean.AllStudentInfo"%>
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

<title>学生信息管理系统 | 成绩信息</title>

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
	List<GradeInfo> list = (List<GradeInfo>) session.getAttribute("GradeInfo");
	if (list == null) {
		response.sendRedirect("../administratorGradeInfoGet");
	}
%>
<body>
	<!-- 模态框 -->
	<div class="modal" tabindex="-1" role="dialog" id="myModal"
		style="display: none">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-question-sign"></span> 提示
					</h4>
				</div>
				<div class="modal-body">
					<p>
						确定要删除&nbsp;<span style="color: red" id="myModalTip"></span>&nbsp;的课程吗？
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="cancelDelete()">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="comfirmDelete()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- 模态框2 -->
	<div class="modal" tabindex="-1" role="dialog" id="myModal2"
		style="display: none">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-pencil"></span> 编辑
					</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" id="editForm"
						action="../administratorGradeInfoEdit" method="post">
						<div class="form-group">
							<label for="selectSno" class="col-sm-2 control-label">学号</label>
							<div class="col-sm-10">
								<!-- <input type="text" class="form-control" id="inputPlanId"
									placeholder="请输入学号" name="plan_id" required="">  -->
								<select class="form-control" id="selectSnoEdit" name="sno"
									disabled>
									<!-- <option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option> -->
								</select>
							</div>
						</div>


						<div class="form-group">
							<label for="selectCno" class="col-sm-2 control-label">课程号</label>
							<!-- 	<div class="col-sm-10">
								<input type="text" class="form-control" id="inputCno"
									placeholder="请输入课程号" name="cno" required="" >
							</div> -->
							<div class=" col-sm-10">
								<select class="form-control" id="selectCnoEdit" name="cno">
									<!-- <option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option> -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputGrade" class="col-sm-2 control-label">成绩</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputGrade"
									placeholder="请输入成绩" name="grade">
							</div>

							<!-- <div class=" col-sm-10">
								<select class="form-control" id="selectClassId" name="class_id">
									<option>5183002141</option>
									<option>5183002142</option>
								</select>
							</div> -->
						</div>

						<div class="form-group">
							<!-- <label for="OldSno" class="col-sm-2 control-label">更改前的课程号</label> -->
							<div class="col-sm-10">
								<input type="hidden" class="form-control" id="OldCno"
									placeholder="请输入学号" name="oldCno" required="">
							</div>
						</div>
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="cancelEdit()">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="comfirmEdit()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- 模态框3 添加信息用 -->
	<div class="modal" tabindex="-1" role="dialog" id="myModal3"
		style="display: none">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-pencil"></span> 添加信息
					</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" id="editForm2"
						action="../administratorGradeInfoAdd" method="post">
						<div class="form-group">
							<label for="selectSno" class="col-sm-2 control-label">学号</label>
							<div class="col-sm-10">
								<!-- <input type="text" class="form-control" id="inputPlanId"
									placeholder="请输入学号" name="plan_id" required="">  -->
								<select class="form-control" id="selectSno" name="sno">
									<!-- <option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option> -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="selectCno" class="col-sm-2 control-label">课程号</label>
							<!-- 	<div class="col-sm-10">
								<input type="text" class="form-control" id="inputCno"
									placeholder="请输入课程号" name="cno" required="" >
							</div> -->
							<div class=" col-sm-10">
								<select class="form-control" id="selectCno" name="cno">
									<!-- <option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option> -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputGrade" class="col-sm-2 control-label">成绩</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputGrade"
									placeholder="请输入成绩" name="grade">
							</div>

							<!-- <div class=" col-sm-10">
								<select class="form-control" id="selectClassId" name="class_id">
									<option>5183002141</option>
									<option>5183002142</option>
								</select>
							</div> -->
						</div>
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="cancelEdit()">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="confirmAdd()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<!-- 警告框 -->
	<div class="alert alert-danger alert-dismissible fade in" role="alert"
		id="warningBox"
		style="display: none; width: 300px; height: 70px; z-index: 1000; position: absolute; left: 0; right: 0; top: 0; bottom: 0; margin: 60px auto">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">×</span>
		</button>
		<h4>错误！</h4>
		<p>${error}</p>
	</div>

	<!-- 成功框 -->
	<div class="alert alert-success alert-dismissible fade in" role="alert"
		id="successBox"
		style="display: none; width: 300px; height: 70px; z-index: 1000; position: absolute; left: 0; right: 0; top: 0; bottom: 0; margin: 60px auto">
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
			<a class="navbar-brand" href="Home.jsp"> Impulse-学生信息管理系统</a>
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
				<input type="text" class="form-control" placeholder="请输入内容..."
					id="searchInput" value="${searchContent}">
				<button type="button" class="btn btn-default" style="top: 8px"
					id="search">
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
					<li><a href="Home.jsp"> <span
							class="glyphicon glyphicon-home"></span> 用户管理
					</a></li>
					<li><a href="StudentInfo.jsp"><span
							class="glyphicon glyphicon-user"></span> 学生信息管理</a></li>
					<li><a href="ClassInfo.jsp"><span
							class="glyphicon glyphicon-blackboard"></span> 班级信息管理</a></li>
					<li><a href="CourseInfo.jsp"><span
							class="glyphicon glyphicon-list-alt"></span> 课程信息管理</a></li>
					<li><a href="CourseChartInfo.jsp"><span
							class="glyphicon glyphicon glyphicon-th"></span> 课程表信息管理</a></li>
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-thumbs-up"></span> 成绩信息管理</a></li>
					<li><a href="UserCenter.jsp"><span
							class="glyphicon glyphicon-asterisk"></span> 个人中心</a></li>
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">成绩信息管理</h1>

				<!-- <h4 class="sub-header">班级信息</h4> -->

				<div class="panel panel-default panel-primary"
					style="border-top: none; text-align: center">
					<div class="panel-heading">
						<h3 class="panel-title">成绩信息</h3>
					</div>
					<div class=" panel-body">
						<div class="table-responsive">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>学号</th>
										<th>姓名</th>
										<th>班名</th>
										<th>课程号</th>
										<th>课程名</th>
										<th>成绩</th>

										<th><button type="button" class="btn btn-info btn-sm"
												style="" id="refresh">刷新</button>
											<button type="button" class="btn btn-warning btn-sm" style=""
												id="add">添加</button></th>
									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${GradeInfo}" var="ci">
										<tr>
											<td>${ci.sno}</td>
											<td>${ci.name}</td>
											<td>${ci.className}</td>
											<td>${ci.cno}</td>
											<td>${ci.cname}</td>
											<td>${ci.grade}</td>


											<td style="width: 20%">
												<button type="button" class="btn btn-success btn-sm"
													style="outline: none" onclick="editDialog()">编辑</button>
												<button type="button" class="btn btn-danger btn-sm"
													style="outline: none" onclick="deleteDialog()">删除</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<!-- 分页 -->
							<nav aria-label="Page navigation">
							<ul class="pagination">
								<li><a href="../administratorGradeInfoPaging?page=prev"
									aria-label="Previous"> <span aria-hidden="true">«</span>
								</a></li>
								<!-- <li class="active"><span>1 <span class="sr-only">(current)</span></span>
								</li> -->


								<c:forEach items="${GradeInfoPageNum}" var="ci">
									<li class="pageNum"><a
										href="../administratorGradeInfoPaging?page=${ci-1}">${ci}</a></li>
								</c:forEach>


								<li><a href="../administratorGradeInfoPaging?page=next"
									aria-label="Next"> <span aria-hidden="true">»</span>
								</a></li>
							</ul>

							</nav>
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
	//点击后设置当前被选中
	<%
	int current_page=0;
	if(session.getAttribute("GradeInfoCurrentPage")!=null){
		 current_page = (Integer) session.getAttribute("GradeInfoCurrentPage");
	}
	%>
	var currentPage=<%=current_page%>
	console.log(currentPage);
	$(".pageNum").eq(currentPage).addClass("active");
	//console.log($(".pageNum")[1].classList.add("active"))
	
	</script>	
	
	<script>
		/* 确认添加信息 */
		function confirmAdd(){
			const Form=$("#editForm2 input");
			//console.log(Form);
			//获取学号数据
			const tbody=$("#tbody>tr");
			
			let index=0;
		    for(let i=0;i<Form.length;i++){
				if(Form.eq(i).val().replace(/\s+/g,"")===""){
					alert("请填写完整！");
					return
				}else{
					index++;
				}
			}
		    //console.log($("#selectCno").val());
		    
		    for(let j=0;j<tbody.length;j++){
		    	//一个学号中课程号唯一
		    	if(tbody.eq(j).find("td:first")[0].innerHTML==$("#selectSno").val()&&tbody.eq(j).find("td").eq(3)[0].innerHTML==$("#selectCno").val()){
		    		alert("该学生已有该课程成绩！");
					return
		    	}
				//console.log(tbody.eq(j).find("td:first")[0].innerHTML);
			}
		    
		    if(Form.eq(0).val().indexOf(".")!=-1){
	    		alert("成绩为整数！");
	    		return
	    	}
		    
			if(index===Form.length){
				console.log("可以提交！");
				$("#editForm2").submit();
			} 
					
		}
		/* 添加信息 */
		$("#add").click(function(){
			//console.log("aaa");
			$('#myModal3').modal("show");
			//getClassId();
			getData();  //获取课程号
			getSnoData();  //获取学号
		})
		
		
		/*搜索*/
		$("#search").click(function(){
			let text=$("#searchInput").val();
			if(text!==""){
				//console.log(text);
				window.location = "../administratorGradeInfoSearch?query="+text;
			}
			
		})
	
		/* 	弹出编辑框 */
		function editDialog(e){
			const event=e||window.event;
			const Form=$("#editForm input");
			
		
			//console.log(Form);
			
			let data=0;
			//获取当前tr的下标
			data=event.target.parentNode.parentNode.rowIndex;
			//console.log(data);
			
			//遍历一行的每个数据
	/* 		let j=0;
			for(let i=0;i<event.target.parentNode.parentNode.children.length-1;i++){
			 	if(i==0){
					
			 		$("#selectCnoEdit").append($("<option>").val(event.target.parentNode.parentNode.children[i].innerHTML)
							.text(event.target.parentNode.parentNode.children[i].innerHTML+" "+event.target.parentNode.parentNode.children[3].innerHTML));
				}else if(i==2){
					$("#selectClassIdEdit").append($("<option>").val(event.target.parentNode.parentNode.children[i].innerHTML).text(event.target.parentNode.parentNode.children[i].innerHTML));
					//console.log($("#selectClassIdEdit"));
				}else if(i!==3){
					Form.eq(j).val(event.target.parentNode.parentNode.children[i].innerHTML);
					if(j<Form.length){
						j++;
					}
				}		
							
			} */
			
			
			//获取成绩到编辑框
			Form.eq(0).val(event.target.parentNode.parentNode.children[5].innerHTML);
			Form.eq(1).val(event.target.parentNode.parentNode.children[3].innerHTML);
			//获取学号到编辑框
			$("#selectSnoEdit").append($("<option>").val(event.target.parentNode.parentNode.children[0].innerHTML).text(event.target.parentNode.parentNode.children[0].innerHTML+" "+event.target.parentNode.parentNode.children[1].innerHTML));
			//console.log($("#selectClassIdEdit"));
			//获取课程号到编辑框
	 		$("#selectCnoEdit").append($("<option>").val(event.target.parentNode.parentNode.children[3].innerHTML)
					.text(event.target.parentNode.parentNode.children[3].innerHTML+" "+event.target.parentNode.parentNode.children[4].innerHTML));
			
			
			//显示模态框
			$('#myModal2').modal("show");
			//暂存数据
			$('#myModal2').attr("data",data);
			
			getData();    //获取课程号
			getSnoData(); //获取学号
		}
		/* 确认编辑 */
		function comfirmEdit(){
			const Form=$("#editForm input"); //只能获取input
			//const SelectSex=$("#inputSex");
			//console.log(Form.eq(0).val());
			//console.log(SelectSex.val());
			const tbody=$("#tbody>tr");
			
			//获取下标
			let rowIndex=$('#myModal2').attr("data");
			let index=0;
			//console.log(rowIndex-1);
		    for(let i=0;i<Form.length;i++){
				if(Form.eq(i).val().replace(/\s+/g,"")===""){
					alert("请填写完整！");
					return
				}else{
					index++;
				}
			}
		    
		    for(let j=0;j<tbody.length;j++){
		    	//一个学号中课程号唯一
		    	
		    	if(j!==rowIndex-1){
		    		if(tbody.eq(j).find("td:first")[0].innerHTML==$("#selectSnoEdit").val()&&tbody.eq(j).find("td").eq(3)[0].innerHTML==$("#selectCnoEdit").val()){
			    		alert("该学生已存在该课程成绩！");
			    	/* 	console.log(tbody.eq(j).find("td:first")[0].innerHTML);
			    		console.log(tbody.eq(j).find("td").eq(3)[0].innerHTML);
			    		console.log($("#selectSnoEdit").val())
			    		console.log($("#selectCnoEdit").val()) */		    		
						return
			    	}
		    	}	    
				//console.log(tbody.eq(j).find("td:first")[0].innerHTML);
			}
		    
		    if(Form.eq(0).val().indexOf(".")!=-1){
	    		alert("成绩为整数！");
	    		return
	    	}
		    
		
		    
			if(index===Form.length){
				console.log("可以提交！");
				//取消下拉框的禁用 以传值给servlet
				$("#selectSnoEdit")[0].disabled=false;
				$("#editForm").submit();
			} 
			//$("#editForm").submit();
			
		}
		
		/* 取消编辑 */
		function cancelEdit(){
			//删除节点中的数据
			$('#myModal2').removeAttr("data");
			$("#selectSnoEdit>option").remove();
			$("#selectCnoEdit>option").remove();
			
			$("#selectSno>option").remove();
			$("#selectCno>option").remove();
		}
		
		/*取消删除*/
	    function cancelDelete(){
	    	$('#myModal').removeAttr("data");
	    }
		
		/*确定删除*/
	    function comfirmDelete(){
			let sno,cno;
			sno=$('#myModal').attr("data").split(",")[0];
			cno=$('#myModal').attr("data").split(",")[1];
	    	//console.log(sno);
	    	window.location = "../administratorGradeInfoDelete?sno="+sno+"&cno="+cno;
	    }
		
	    /* 触发删除模态框 */
		function deleteDialog(e){
			const event=e||window.event;
			let sno=event.target.parentNode.parentNode.children[0].innerHTML;
			let cno=event.target.parentNode.parentNode.children[3].innerHTML
			//显示模态框
			$('#myModal').modal("show");
			//暂存数据
			let data=sno+","+cno;
			$('#myModal').attr("data",data);
			$("#myModalTip").text(sno+"课程号为："+cno);
			//window.location = "../administratorHomeDelete";
		}
		/* 刷新按钮 */
		$("#refresh").click(function(){
			window.location = "../administratorGradeInfoGet"
		})
		
		//获取已有学号
		function getSnoData(){
			//获取数据
			$.get("../getAllSnoJson",function(data,status){
	    	//console.log("数据: " + JSON.stringify(data)+ "\n状态: " + status);
	    	let DATA=JSON.stringify(data);
	    	//console.log(JSON.parse(DATA))
	   	 	DATA=JSON.parse(DATA)
	     	//console.log(DATA);
	    	//console.log($("#selectCno"));
	    	for(let i=0;i<DATA.length;i++){
	    		$("#selectSno").append("<option value='"+DATA[i].sno+"'>"+DATA[i].sno+" "+DATA[i].name+"</option>");
	    		$("#selectSnoEdit").append("<option value='"+DATA[i].sno+"'>"+DATA[i].sno+" "+DATA[i].name+"</option>");
	    	}
	    	
	  		});
		}
		
		
		//获取课程表的课程ID
		function getData(){
			//获取数据
			$.get("../administratorCourseChartInfoGetCno?search=1",function(data,status){
	    	//console.log("数据: " + JSON.stringify(data)+ "\n状态: " + status);
	    	let DATA=JSON.stringify(data);
	    	//console.log(JSON.parse(DATA))
	   	 	DATA=JSON.parse(DATA)
	     	//console.log(DATA);
	    	//console.log($("#selectCno"));
	    	for(let i=0;i<DATA.length;i++){
	    		$("#selectCno").append("<option value='"+DATA[i].cno+"'>"+DATA[i].cno+" "+DATA[i].cname+"</option>");
	    		$("#selectCnoEdit").append("<option value='"+DATA[i].cno+"'>"+DATA[i].cno+" "+DATA[i].cname+"</option>");
	    	}
	    	
	  		});
		}
		
		//获取班级ID
		function getClassId(){
			//获取数据
			$.get("../administratorCourseChartInfoGetClassId",function(data,status){
	    	//console.log("数据: " + JSON.stringify(data)+ "\n状态: " + status);
	    	let DATA=JSON.stringify(data);
	    	//console.log(JSON.parse(DATA))
	   	 	DATA=JSON.parse(DATA)
	     	//console.log(DATA);
	    	//console.log($("#selectClassId"));
	    	for(let i=0;i<DATA.length;i++){
	    		$("#selectClassId").append("<option value='"+DATA[i].class_id+"'>"+DATA[i].class_id+" "+DATA[i].class_name+"</option>");
	    		$("#selectClassIdEdit").append("<option value='"+DATA[i].class_id+"'>"+DATA[i].class_id+" "+DATA[i].class_name+"</option>");
	    	}
	    	
	  		});
		}
		/* getClassId();
		getData(); */
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