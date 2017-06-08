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
	<link rel="stylesheet" href="css/table.css">

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
			<jsp:include page="/user/top.jsp"></jsp:include>
			<section>
				<ul class="breadcrumb nav-bar">
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li><a href="javascript:void(0);">账号信息</a></li>
					<li class="active">申请人员管理</li> 
				</ul>
				<div class="main-content">
					<div class="manage-content" id="manage_content">
					<div class="manage-content2">
						<select class="search-select" name="search_type" id="search_type">
					 	 	<option value="0" selected>所有申请人员</option>
					 	 	<option value="1">项目开发者</option>
					 	 	<option value="2">项目发布者</option>
					 	 </select>
			        	<input name="" type="button" value="搜索" class="btn btn-info search-btn" onclick="applyUser_manage(1)"/>
					</div>
					<div id="table_content">
					</div>
					</div>
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
		applyUser_manage(1);
	});
	
	function applyUser_manage(currPage) {
		var searchType = $('#search_type').val();
		$.ajax({
			type: 'post',
			url: 'login_getListOnApply.action',
			data: {
				currPage: currPage,
				searchType: searchType,
			},
			dataType: 'html',
			success: function(data) {
				$("#table_content").html(data);
			}
		});
	}
	
</script>

</body>
</html>