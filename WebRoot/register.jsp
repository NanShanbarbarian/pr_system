<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/register.css">
	<script src="plugins/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/check/register.js" type="text/javascript"></script>
	<title>注册界面</title>
	
	<style type="text/css">
		.form-group label {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>
</head>

<body>
	<div class="bg">
		<div class="register-top">
			<span class="register-top-title">科技创新智能互助平台</span>&nbsp;&nbsp;&nbsp;&nbsp;<label style="color: #fff; font-weight: lighter;">|</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="register-top-word">欢迎注册</span>
			<span class="register-top-word register-back" style="float: right; margin-right: 20px;"><a href="index.jsp">返回首页</a></span>
		</div>
		<div class="register-div">
			<ul class="nav nav-tabs">
			  <li class="active" id="student"><a href="javascript:void(0);" onclick="showStudent()">项目开发者</a></li>
			  <li id="teacher"><a href="javascript:void(0);" onclick="showTeacher()">项目发布者</a></li>
			</ul>
			<div id="form_content"></div>			
		</div>
	</div>
	
<script type="text/javascript">
	
	$(function() {
		showStudent();
	});
	function showStudent() {
		$('#teacher').removeClass('active');
		$('#student').addClass('active');
		$.ajax({
			type: 'post',
			url: 'register_student.jsp',
			dataType: 'html',
			success: function(data) {
				$('#form_content').html(data);
			}
		});
	}
	
	function showTeacher() {
		$('#student').removeClass('active');
		$('#teacher').addClass('active');
		$.ajax({
			type: 'post',
			url: 'register_teacher.jsp',
			dataType: 'html',
			success: function(data) {
				$('#form_content').html(data);
			}
		});
	}
	
</script>
</body>
</html>