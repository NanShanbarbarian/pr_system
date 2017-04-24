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
			width: 60%;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 30px auto;
			padding: 80px 20px 80px 20px;
			background-color: #fff;
		}
		
		.form-group label {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
		
		.dis {
			display: none;
		}
	</style>
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
			<header class="main-head">
				<div class="dropdown user-nav">
				  <button class="btn btn-default dropdown-toggle none-border" type="button" data-toggle="dropdown">
				     ${sessionScope.user.name}
				    <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu" id="dropdown-width">
				    <li><a href="#">个人资料</a></li>
				    <li class="divider"></li>
				    <li><a href="login_out.action">注销</a></li>
				  </ul>
				</div>
			</header>
			<section>
				<ul class="breadcrumb nav-bar">
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li><a href="javascript:void(0);">用户管理</a></li>
					<li class="active">修改密码</li> 
				</ul>
				<div class="main-content">
					<div class="main-content2">
						<div class="alert alert-warning alert-dismissible dis" id="alert">
							<button type="button" class="close" aria-label="Close" onclick="$('#alert').hide();">&times;</button>
						  	<strong>Warning:</strong> 当前密码错误！！！
						</div>
						<div class="alert alert-warning alert-dismissible dis" id="alert2">
							<button type="button" class="close" aria-label="Close" onclick="$('#alert2').hide();">&times;</button>
						  	<strong>Warning:</strong> 输入不为空或两次输入的密码不一致！！！
						</div>
						<div class="alert alert-success alert-dismissible dis" id="alert3">
							<button type="button" class="close" aria-label="Close" onclick="$('#alert3').hide();">&times;</button>
						  	<strong>修改成功！</strong>
						</div>
						<div class="form-horizontal">
							<div class="form-group">
					    		<label class="col-sm-2 control-label">当前密码</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="password" name="now_password" id="now_password">
					    		</div>
					    	</div>
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">新密码</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="password" name="new_password" id="new_password" required>
					    		</div>
					    	</div>
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">再次确认</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="password" name="new_password2" id="new_password2" required>
					    		</div>
					    	</div>
					    	<div class="form-group">
				    		<div class="col-sm-2 col-sm-offset-5">
				    		<button class="btn btn-info" onclick="login_alterPassword()" style="font-size: 13px;">确定修改</button>
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
	$.sidebarMenu($('.sidebar-menu'));
	$(function() {
		
		$("#now_password").blur(function() {
			if(!checkNowPassword()) {
				$("#alert").show();
				$('#alert3').hide();
			} 
		});
		$("#new_password2").blur(function() {
			if(!checkNewPassword()) {
				$('#alert2').show();
				$('#alert3').hide();
			}
		});
		
	});
	
	function checkNowPassword() {
		var result;
		$.ajax({
			type : 'post',
			async: false,
			url : 'login_isPassword.action',
			data : {
				id : '${sessionScope.login.id}',
				password : $('#now_password').val()
			},
			success : function(data) {
				if(data == 1) {
					result = true;
				} else if(data == 0) {
					result = false;
				}
			},
			error : function(data) {
				result = false;
			}
		});
		return result;
	}
	
	function checkNewPassword() {
		if($('#new_password').val() != "" && $('#new_password2').val() != "") {
			if($('#new_password').val() == $('#new_password2').val()) {
				return true;
			}
		}
		return false;
	} 
	
	function login_alterPassword() {
		if(checkNowPassword() && checkNewPassword()) {
			$.ajax({
				type : 'post',
				url : 'login_alterPassword.action',
				data : {
					id : '${sessionScope.login.id}',
					password : $('#new_password').val()
				},
				success : function() {
					$('#alert3').show();
					$('#alert').hide();
					$('#alert').hide();
					$('#now_password').val("");
					$('#new_password').val("");
					$('#new_password2').val("");
				},
				error : function() {
					$('#alert2').show();
					$('#alert3').hide();
				}
			});
		} else {
			$('#alert2').show();
			$('#alert3').hide();
		}
	}

</script>

</body>
</html>