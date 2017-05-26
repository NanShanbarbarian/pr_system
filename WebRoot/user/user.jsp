<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>科技创新智能互助平台</title>
	
	<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/sidebar/sidebar-menu.css">
	<link rel="stylesheet" href="css/main.css">
	<script src="plugins/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/sidebar//sidebar-menu.js"  type="text/javascript"></script>

</head>
<body>
	<section class="bg-color">
		<aside class="sidebar">
			<div  class="left-head">
				<a class="left-head-text" href="user/user.jsp">科技创新智能互助平台</a>
			</div>
			<div class="blank"></div>
			<jsp:include page="menu.jsp"></jsp:include>
		</aside>
		
		<section class="main-container">
			<jsp:include page="top.jsp"></jsp:include>
			<section>
				<!-- <ul class="breadcrumb nav-bar">
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li class="active">首页</li> 
				</ul>
				<div class="main-content">
				欢迎来到科技创新智能互助平台!
				</div> -->
			</section>
		</section>
	</section>

<script>
	$.sidebarMenu($('.sidebar-menu'))
</script>

</body>
</html>