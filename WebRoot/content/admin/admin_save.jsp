<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新增项目开发者</title>
	
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
	<div class="main-content2">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-2 control-label">姓名</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="name" id="name">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">工号</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="number" id="number">
	    		</div>
	    	</div>
	    	<!-- <div class="form-group">
	    		<label class="col-sm-2 control-label">角色</label>
	    		<div class="col-sm-8">
	    			<span class = "form-control">超级管理员</span>
	    		</div>
	    	</div> -->
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">学院</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="academy" id="academy">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">职称</label>
	    		<div class="col-sm-8">
		 			<select class = "form-control" name="level" id="level">
		 				<option value="">--请选择--</option>
		 				<option value="0">教授</option>
		 				<option value="1">副教授</option>
		 				<option value="2">讲师</option>
		 			</select>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">性别</label>
	    		<div class="col-sm-8">
	    			<label><input type="radio" name="sex" value="0" checked>男</label>&nbsp;&nbsp;
	    			<label><input type="radio" name="sex"value="1">女</label>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">号码</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="telephone" id="telephone">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">短号</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="cornet" id="cornet">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">QQ</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="qq" id="qq">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">邮箱</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="email" id="email">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">简述</label>
	    		<div class="col-sm-8">
	    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;"></textarea>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-2 col-sm-offset-5">
	    		<a class="btn btn-info" href="javascript:void(0);" onclick="teacher_addAdmin()" style="font-size: 13px;">新增管理员</a>
	    		</div>
	    	</div>
	    </div>
	</div>

<script type="text/javascript">
	function teacher_addAdmin() {
		$.ajax({
			type : 'post',
			url : 'teacher_saveAdmin.action',
			data : {
				name : $('#name').val(),
				number : $('#number').val(),
				academy: $('#academy').val(),
				level: $('#level').val(),
				sex: $("input[name='sex']:checked").val(),
				telephone: $('#telephone').val(),
				cornet: $('#cornet').val(),
				qq: $('#qq').val(),
				email: $('#email').val(),
				description: $('#description').val()
			},
			dataType : 'html',
			success : function(data) {
				window.location.href = "content/admin/admin.jsp";
			}, 
			error : function(data) {
				window.location.href = "content/admin/admin.jsp";
			}
		});
	}
</script>
</body>
</html>