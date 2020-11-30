<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="../assets/images/favicon.jpeg">
<title>页面走丢了</title>
<style>
	.wrap{
		width:100%;
		height:100%;
		position:absolute;
		left:0;
		top:0;
		overflow:hidden;
		right:0;
		bottom:0;
		margin:auto;
		background-color:#F2F2F2;
	}
	.wrap img{		
		height:100%;
	}
	.wrap .img_box{
		text-align:center;
		height:100%;
		position:relative;
		margin:auto;
	}
	.wrap .text{
		position:absolute;
		left:10px;
		top:10px;
		z-index:100;
		
	}
	.wrap .text a{
		text-decoration:none;
	}
</style>
</head>
<body>

<section class="wrap">
	<div class="text">
		<span>请点此处</span><a href="JavaScript:history.go(-1)">返回</a>
	</div>
	<div class="img_box">
	<img src="<%=request.getContextPath()%>/assets/images/404.png">
	</div>
</section>

</body>
</html>