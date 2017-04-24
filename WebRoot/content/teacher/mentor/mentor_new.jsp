<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
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
			padding: 30px 20px 80px 20px;
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
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li><a href="javascript:void(0);">系统活动</a></li>
					<li class="active">优才导师</li> 
				</ul>
				<div class="main-content">
					<div class="main-content2">
					<h4 class="col-sm-offset-5">优才导师计划发布</h4>
					<hr>
					<div class="form-horizontal">
						<div class="form-group">
				    		<label class="col-sm-4 control-label">组长名称</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="leader" id="leader">
				    		</div>
				    	</div>
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">组长职称</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="title" id="title">
				    		</div>
				    	</div>
				    	<div id="member">
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">成员姓名</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="member">
				    		</div>
				    		<button class="btn btn-info btn-sm" onclick="addMember()">添加</button>
				    	</div>
				    	</div>
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">课业学生助教</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="teachingAsistant" id="teachingAsistant">
				    		</div>
				    	</div>
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">实验室助教</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="labAsistant" id="labAsistant">
				    		</div>
				    	</div>
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">课外科技竞赛</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="competitionNum" id="competitionNum">
				    		</div>
				    	</div>
				    	<div class="form-group">
				    		<label class="col-sm-4 control-label">科研研究助理</label>
				    		<div class="col-sm-4">
				    			<input class = "form-control" type="text" name="researchAsistant" id="researchAsistant">
				    		</div>
				    	</div>
				    	<div class="form-group" style="margin-top: 30px;">
				    		<div class="col-sm-2 col-sm-offset-5">
				    		<a class="btn btn-info" href="javascript:void(0);" onclick="mentor_save()" style="font-size: 13px;">确定发布</a>
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
<script type="text/javascript">
	$.sidebarMenu($('.sidebar-menu'));
	
	function mentor_save() {
		var members = [];
		var member = $("input[name='member']");
		for(var i=0; i<member.length; i++) {
			members.push(member[i].value);
		}
		var memberlist = members.join("、");
		$.ajax({
			type : 'post',
			url : 'gm_save.action',
			data : {
				leader: $('#leader').val(),
				title: $('#title').val(),
				member: memberlist,
				teachingAsistant: $('#teachingAsistant').val(),
				labAsistant: $('#labAsistant').val(),
				competitionNum: $('#competitionNum').val(),
				researchAsistant: $('#researchAsistant').val(),
				publisherId: '${sessionScope.login.id}',
			},
			success : function() {
				alert("发布成功！");
				window.location.href="content/teacher/mentor/mentor.jsp";
			},
			error : function() {
				alert("发布失败，请重试！");
			}
		});
	}
	
	function addMember() {
		var member = $('#member');
		var num = member.children('div').length;
		
		if(num > 9) {
			alert("最多只能10个成员！");
		} else {
			num = num + 1;
			$('.btn-sm').remove();
			var str = "<div class='form-group'>" + "<label class='col-sm-4 control-label'>成员姓名</label>" + "<div class='col-sm-4'><input class='form-control' type='text' name='member'></div>" + 
					"<button class='btn btn-info btn-sm' onClick='addMember()'>添加</button></div>";
			member.append(str);
		}
		
	}
</script>

</body>
</html>