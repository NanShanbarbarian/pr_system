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
	<title>科技创新智能互助平台</title>
	
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
	<div class="main-content2 form-horizontal">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
		    		<label class="col-xs-3 control-label">姓名</label>
		    		<div class="col-xs-8">
		    			<span class = "form-control">${user.name}</span>
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">学号</label>
		    		<div class="col-xs-8">
		    			<span class = "form-control">${login.number}</span>
		    		</div>
		    	</div>
		    </div>
		</div>
	    <div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">经验值</label>
		    		<div class="col-xs-8">
		    			<span class = "form-control">${user.experience}</span>
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">学院</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="academy" id="academy" value="${user.academy}">
		    		</div>
		    	</div>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">层次</label>
		    		<div class="col-xs-8">
		    			<c:if test="${user.level == '0'}">
			 			<select class = "form-control" name="level" id="level">
			 				<option value="0" selected>本科生</option>
			 				<option value="1">研究生</option>
			 			</select>
			 			</c:if>
			 			<c:if test="${user.level == '1'}">
			 			<select class = "form-control" name="level" id="level">
			 				<option value="0">本科生</option>
			 				<option value="1" selected>研究生</option>
			 			</select>
			 			</c:if>
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">性别</label>
		    		<div class="col-xs-8">
		    			<c:if test="${user.sex == '0'}">
		    			<label><input type="radio" name="sex" value="0" checked>男</label>&nbsp;&nbsp;
		    			<label><input type="radio" name="sex"value="1">女</label>
		    			</c:if>
		    			<c:if test="${user.sex == '1'}">
		    			<label><input type="radio" name="sex" value="0">男</label>&nbsp;&nbsp;
		    			<label><input type="radio" name="sex"value="1" checked>女</label>
		    			</c:if>
		    		</div>
		    	</div>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">专业</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="major" id="major" value="${user.major}">
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">年级</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="grade" id="grade" value="${user.grade}">
		    		</div>
		    	</div>
		    </div>
		 </div>
		 <div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">班级</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="className" id="className" value="${user.className}">
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">民族</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="nation" id="nation" value="${user.nation}">
		    		</div>
		    	</div>
		    </div>
		 </div>
		 <div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">政治面貌</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="polity" id="polity" value="${user.polity}">
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">号码</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="telephone" id="telephone" value="${user.telephone}">
		    		</div>
		    	</div>
		    </div>
		 </div>
		 <div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">短号</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="cornet" id="cornet" value="${user.cornet}">
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">QQ</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="qq" id="qq" value="${user.qq}">
		    		</div>
		    	</div>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">邮箱</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="email" id="email" value="${user.email}">
		    		</div>
		    	</div>
		    </div>
		    <div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-2 control-label">宿舍</label>
		    		<div class="col-xs-8">
		    			<input class = "form-control" type="text" name="dormitory" id="dormitory" value="${user.dormitory}">
		    		</div>
		    	</div>
		    </div>
		</div>
		<div class="row">
			<div class="col-xs-6">
		    	<div class="form-group">
		    		<label class="col-xs-3 control-label">简介</label>
		    		<div class="col-xs-8">
		    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;">${user.description}</textarea>
		    		</div>
		    	</div>
		    </div>
		</div>
		<div class="row" style="margin-top: 30px;">
	    	<div class="form-group">
	    		<div class="col-xs-4 col-xs-offset-5">
	    		<a class="btn btn-info" href="javascript:void(0);" onclick="student_update(${user.id})" style="font-size: 13px;">提交修改</a>&nbsp;&nbsp;&nbsp;
	    		<a class="btn btn-info" href="javascript:void(0);" onclick="student_delete(${login.id})" style="font-size: 13px;">删除用户</a>
	    		</div>
	    	</div>
	    </div>
	</div>

<script type="text/javascript">
	function student_update(userId) {
		$.ajax({
			type : 'post',
			url : 'student_update.action',
			data : {
				id : userId,
				academy: $('#academy').val(),
				level: $('#level').val(),
				sex: $("input[name='sex']:checked").val(),
				major: $('#major').val(),
				grade: $('#grade').val(),
				className: $('#className').val(),
				nation: $('#nation').val(),
				polity: $('#polity').val(),
				telephone: $('#telephone').val(),
				cornet: $('#cornet').val(),
				qq: $('#qq').val(),
				email: $('#email').val(),
				dormitory: $('#dormitory').val(),
				description: $('#description').val()
			},
			success : function(data) {
				window.location.href = "content/student/student.jsp";
			}, 
			error : function(data) {
				window.location.href = "content/student/student.jsp";
			}
		});
	}
	
	function student_delete(loginId) {
		$.ajax({
			type : 'post',
			url : 'student_delete.action',
			data : {
				loginId : loginId,
			},
			success : function(data) {
				window.location.href = "content/student/student.jsp";
			}, 
			error : function(data) {
				window.location.href = "content/student/student.jsp";
			}
		});
	}
</script>
</body>
</html>