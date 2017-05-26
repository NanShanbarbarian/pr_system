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
					<div class="main-content2 form-horizontal">
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
					    		<label class="col-xs-2 control-label">学号</label>
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
					    			<span class = "form-control">项目开发者</span>
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">经验值</label>
					    		<div class="col-xs-8">
					    			<span class = "form-control">${sessionScope.user.experience}</span>
					    		</div>
					    	</div>
					    </div>
					  </div>
					  <div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">学院</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="academy" id="academy" value="${sessionScope.user.academy}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">层次</label>
					    		<div class="col-xs-8">
					    			<c:if test="${sessionScope.user.level == '0'}">
						 			<select class = "form-control" name="level" id="level">
						 				<option value="0" selected>本科生</option>
						 				<option value="1">研究生</option>
						 			</select>
						 			</c:if>
						 			<c:if test="${sessionScope.user.level == '1'}">
						 			<select class = "form-control" name="level" id="level">
						 				<option value="0">本科生</option>
						 				<option value="1" selected>研究生</option>
						 			</select>
						 			</c:if>
					    		</div>
					    	</div>
					    </div>
					 </div>
					 <div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">性别</label>
					    		<div class="col-xs-8">
					    			<c:if test="${sessionScope.user.sex == '0'}">
					    			<label><input type="radio" name="sex" value="0" checked>男</label>&nbsp;&nbsp;
					    			<label><input type="radio" name="sex"value="1">女</label>
					    			</c:if>
					    			<c:if test="${sessionScope.user.sex == '1'}">
					    			<label><input type="radio" name="sex" value="0">男</label>&nbsp;&nbsp;
					    			<label><input type="radio" name="sex"value="1" checked>女</label>
					    			</c:if>
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">专业</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="major" id="major" value="${sessionScope.user.major}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">年级</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="grade" id="grade" value="${sessionScope.user.grade}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">班级</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="className" id="className" value="${sessionScope.user.className}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">民族</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="nation" id="nation" value="${sessionScope.user.nation}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">政治面貌</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="polity" id="polity" value="${sessionScope.user.polity}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">号码</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="telephone" id="telephone" value="${sessionScope.user.telephone}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">短号</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="cornet" id="cornet" value="${sessionScope.user.cornet}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">QQ</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="qq" id="qq" value="${sessionScope.user.qq}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">邮箱</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="email" id="email" value="${sessionScope.user.email}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">宿舍</label>
					    		<div class="col-xs-8">
					    			<input class = "form-control" type="text" name="dormitory" id="dormitory" value="${sessionScope.user.dormitory}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-2 control-label">技能标签</label>
					    		<div class="col-xs-8">
					    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
					  	<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-xs-3 control-label">简介</label>
					    		<div class="col-xs-8">
					    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;">${sessionScope.user.description}</textarea>
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
				    	<div class="form-group">
				    		<div class="col-xs-2 col-xs-offset-5">
				    		<a class="btn btn-info" href="user/student_alter.jsp" style="font-size: 13px;">点击修改</a>
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
	
	$(function(){
		$.ajax({
			type : 'post',
			url : 'str_getByStuLoginId.action',
			data : {
				loginId : '${sessionScope.login.id}'
			},
			dataType : 'json',
			success : function(data) {
				for(var i=0; i<data.length; i++) {
					addTag(data[i].tag);
				}
			}
		});
	});
	
	function addTag(tag) {
		$("#tag-list").append($("<button class='btn btn-default btn-xs' style='margin-right: 2px;'>"+ tag +"</button>"));
	}
</script>

</body>
</html>