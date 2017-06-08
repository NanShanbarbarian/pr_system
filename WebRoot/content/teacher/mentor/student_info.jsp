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
	<link rel="stylesheet" href="css/main.css">
	
	<style type="text/css">
		.main-content2 {
			width: 90%;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 30px auto;
			padding: 50px 20px 50px 20px;
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
	<c:if test="${user == null}">
		<div style="margin: 50 auto;">该用户不存在！</div>
	</c:if>
	<c:if test="${user != null}">
	<div class="form-horizontal">
		<div class="form-group">
    		<label class="col-sm-2 control-label">姓名</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="name" id="name" value="${user.name}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">学号</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="number" id="number" value="${login.number}">
    		</div>
    	</div>
    	<!-- <div class="form-group">
    		<label class="col-sm-2 control-label">角色</label>
    		<div class="col-sm-8">
    			<span class = "form-control">超级管理员</span>
    		</div>
    	</div> -->
    	<div class="form-group">
    		<label class="col-sm-2 control-label">经验值</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="experience" id="experience" value="${user.experience}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">学院</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="academy" id="academy" value="${user.academy}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">层次</label>
    		<div class="col-sm-8">
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
    	<div class="form-group">
    		<label class="col-sm-2 control-label">性别</label>
    		<div class="col-sm-8">
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
    	<div class="form-group">
    		<label class="col-sm-2 control-label">专业</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="major" id="major" value="${user.major}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">年级</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="grade" id="grade" value="${user.grade}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">班级</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="className" id="className" value="${user.className}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">民族</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="nation" id="nation" value="${user.nation}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">政治面貌</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="polity" id="polity" value="${user.polity}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">号码</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="telephone" id="telephone" value="${user.telephone}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">短号</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="cornet" id="cornet" value="${user.cornet}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">QQ</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="qq" id="qq" value="${user.qq}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">邮箱</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="email" id="email" value="${user.email}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">宿舍</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="dormitory" id="dormitory" value="${user.dormitory}">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">标签</label>
    		<div class="col-sm-8">
    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">简介</label>
    		<div class="col-sm-8">
    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;">${user.description}</textarea>
    		</div>
    	</div>
    	<div class="form-group" style="margin-top: 30px;">
    		<div class="col-sm-2 col-sm-offset-5">
    		<a class="btn btn-primary" href="javascript:void(0);" onclick="back(${param.count})" style="font-size: 13px;">确定返回</a>
    		</div>
    	</div>
    </div>
	</c:if>
<script>
	$(function(){
		$.ajax({
			type : 'post',
			url : 'str_getByStuLoginId.action',
			data : {
				loginId : '${login.id}',
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
	
	function back(count) {
		if(count == 1) {
			$.ajax({
				type: 'post',
				url: 'msr_applyStudent.action',
				data: {
					gmId: '${param.gmId}',
					type: '${param.type}'
				},
				dataType: 'html',
				success: function(data) {
					$('#student_container').html(data);
				}
			});
		} else if(count == 2) {
			$.ajax({
				type: 'post',
				url: 'msr_selectedStudent.action', 
				data: {
					gmId: '${param.gmId}',
					type: '${param.type}'
				},
				dataType: 'html',
				success: function(data) {
					$('#student_container').html(data);
					$('#confirm').modal();
				}
			});
		}
	}
</script>

</body>
</html>