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
	
	<style type="text/css">
		.main-content2 {
			width: 90%;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 30px auto;
			padding: 100px 20px 80px 20px;
			background-color: #fff;
		}
		
		.form-group label {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>
</head>
<body>
	<section class="bg-color">
		<aside class="sidebar">
			<div  class="left-head">
				<a class="left-head-text" href="user/teacher.jsp">科技创新智能互助平台</a>
			</div>
			<div class="blank"></div>
			<jsp:include page="menu.jsp"></jsp:include>
		</aside>
		
		<section class="main-container">
			<jsp:include page="top.jsp"></jsp:include>
			<section>
				<ul class="breadcrumb nav-bar">
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li><a href="javascript:void(0);">账号信息</a></li>
					<li class="active">个人资料</li> 
				</ul>
				<div class="main-content">
					<div class="main-content2">
					<div class="form-horizontal">
						<div class="row">
							<div class="col-xs-6">
								<div class="form-group">
						    		<label class="col-xs-3 control-label">姓名</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.name}</span>
						    		</div>
						    	</div>
				    		</div>
				    		<div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-2 control-label">工号</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.login.number}</span>
						    		</div>
						    	</div>
						    </div>
						</div>
						<div class="row">
							<div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-3 control-label">角色</label>
						    		<div class="col-xs-8">
						    			<c:if test="${sessionScope.login.role == '0'}">
						    				<span class = "form-control">超级管理员</span>
						    			</c:if>
						    			<c:if test="${sessionScope.login.role == '1'}">
						    				<span class = "form-control">管理员</span>
						    			</c:if>
						    			<c:if test="${sessionScope.login.role == '2'}">
						    				<span class = "form-control">项目发布者</span>
						    			</c:if>
						    		</div>
						    	</div>
						    </div>
						    <div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-2 control-label">学院</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.academy}</span>
						    		</div>
						    	</div>
						    </div>
						</div>
						<div class="row">
							<div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-3 control-label">职称</label>
						    		<div class="col-xs-8">
						    			<c:if test="${sessionScope.user.level == '0'}">
						    				<span class = "form-control">教授</span>
						    			</c:if>
						    			<c:if test="${sessionScope.user.level == '1'}">
						    				<span class = "form-control">副教授</span>
						    			</c:if>
						    			<c:if test="${sessionScope.user.level == '2'}">
						    				<span class = "form-control">讲师</span>
						    			</c:if>
						    		</div>
						    	</div>
						    </div>
						    <div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-2 control-label">性别</label>
						    		<div class="col-xs-8">
						    			<c:if test="${sessionScope.user.sex == '0'}">
						    				<span class = "form-control">男</span>
						    			</c:if>
						    			<c:if test="${sessionScope.user.sex == '1'}">
						    				<span class = "form-control">女</span>
						    			</c:if>
						    		</div>
						    	</div>
						    </div>
						</div>
						<div class="row">
							<div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-3 control-label">号码</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.telephone}</span>
						    		</div>
						    	</div>
						    </div>
						    <div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-2 control-label">短号</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.cornet}</span>
						    		</div>
						    	</div>
						    </div>
						</div>
						<div class="row">
							<div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-3 control-label">QQ</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.qq}</span>
						    		</div>
						    	</div>
						    </div>
						    <div class="col-xs-6">
						    	<div class="form-group">
						    		<label class="col-xs-2 control-label">邮箱</label>
						    		<div class="col-xs-8">
						    			<span class = "form-control">${sessionScope.user.email}</span>
						    		</div>
						    	</div>
						    </div>
						</div>
						<div class="row">
							<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">简介</label>
					    		<div class="col-xs-8">
					    			<textarea class = "form-control" style="width:100%; height:80px;vertical-align:top;resize:none;">${sessionScope.user.description}</textarea>
					    		</div>
					    	</div>
					    	</div>
					    </div>
					    <div class="row" style="margin-top: 30px;">
					    	<div class="form-group">
					    		<div class="col-xs-2 col-xs-offset-5">
					    		<a class="btn btn-info" href="user/teacher_alter.jsp" style="font-size: 13px;">点击修改</a>
					    		</div>
					    	</div>
				    	</div>
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
	$.sidebarMenu($('.sidebar-menu'))
</script>

</body>
</html>