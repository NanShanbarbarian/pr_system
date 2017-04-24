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

</head>
<body>
	<section class="bg-color">
		<aside class="sidebar">
			<div  class="left-head">
				<a class="left-head-text" href="user/user.jsp">科技创新智能互助平台</a>
			</div>
			<div class="blank"></div>
			<jsp:include page="/user/menu.jsp"></jsp:include>
		</aside>
		
		<section class="main-container">
			<header class="main-head">
				<div class="dropdown user-nav">
				  <button class="btn btn-default dropdown-toggle none-border" type="button" data-toggle="dropdown">
				     ${sessionScope.user.name}
				    <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu" id="dropdown-width">
				    <li><a href="user/profile/super_admin.jsp">个人资料</a></li>
				    <li class="divider"></li>
				    <li><a href="login_out.action">注销</a></li>
				  </ul>
				</div>
			</header>
			<section>
				<ul class="breadcrumb nav-bar">
					<li>科技创新智能互助平台</li>
					<li>科技创新</li>
					<li class="active">期刊论文</li> 
				</ul>
				<div class="main-content">
					<div id="paper_content"></div>
				</div>
			</section>
		</section>
	</section>

<script src="plugins/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/sidebar//sidebar-menu.js"  type="text/javascript"></script>
<script>
	$.sidebarMenu($('.sidebar-menu'));
	
	$(function() {
		paper_manage(1);
	});
	
	function paper_manage(currPage) {
		$.ajax({
			type : 'post',
			url : 'paper_getPassedList.action',
			dataType : 'html',
			data : {
				currPage : currPage
			},
			success : function(data) {
				$('#paper_content').html(data);
			}
		});
	}
	
</script>

</body>
</html>