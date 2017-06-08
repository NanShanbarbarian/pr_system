<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			padding: 30px 20px 50px 20px;
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
	<div class="main-content">
		<div class="main-content2">
		<h4 class="col-sm-offset-5">优才导师计划</h4>
		<hr>
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">组长名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="leader" id="leader" value="${gm.leader}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">组长职称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="title" id="title" value="${gm.title}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">成员姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" value="${gm.member}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">状态</label>
	    		<div class="col-sm-4">
	    			<c:if test="${gm.status == 0}">
	    			<input class = "form-control" type="text" name="member" value="发布中">
	    			</c:if>
	    			<c:if test="${gm.status == 1}">
	    			<input class = "form-control" type="text" name="member" value="已完成">
	    			</c:if>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">课业学生助教</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teachingAsistant" id="teachingAsistant" value="${gm.teachingAsistant}">
	    		</div>
	    		<c:if test="${gm.teachingAsistant > 0}">
	    			<button class="btn btn-primary btn-normal" onclick="applyStudent(1)">申请人员</button>
	    			<button class="btn btn-default btn-normal" onclick="selectedStudent(1)">已选人员</button>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">实验室助教</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="labAsistant" id="labAsistant" value="${gm.labAsistant}">
	    		</div>
	    		<c:if test="${gm.labAsistant > 0}">
	    			<button class="btn btn-primary btn-normal" onclick="applyStudent(2)">申请人员</button>
	    			<button class="btn btn-default btn-normal" onclick="selectedStudent(2)">已选人员</button>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">课外科技竞赛</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="competitionNum" id="competitionNum" value="${gm.competitionNum}">
	    		</div>
	    		<c:if test="${gm.competitionNum > 0}">
	    			<button class="btn btn-primary btn-normal" onclick="applyStudent(3)">申请人员</button>
	    			<button class="btn btn-default btn-normal" onclick="selectedStudent(3)">已选人员</button>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">科研研究助理</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="researchAsistant" id="researchAsistant" value="${gm.researchAsistant}">
	    		</div>
	    		<c:if test="${gm.researchAsistant > 0}">
	    			<button class="btn btn-primary btn-normal" onclick="applyStudent(4)">申请人员</button>
	    			<button class="btn btn-default btn-normal" onclick="selectedStudent(4)">已选人员</button>
	    		</c:if>
	    	</div>
	    	<div class="form-group" style="margin-top: 30px;">
	    		<div class="col-sm-4 col-sm-offset-5">
	    		<a class="btn btn-primary" href="content/teacher/mentor/mentor.jsp" style="font-size: 13px;">确定返回</a>
	    		<c:if test="${gm.status == 0}">
	    		<a class="btn btn-default" href="javascript:void(0);" onclick="gm_finish(${gm.id})" style="font-size: 13px;">完成活动</a>
	    		</c:if>
	    		</div>
	    	</div>
	    </div>
	    </div>
	</div>
	
	<div class="modal fade" id="confirm">
		<div class="modal-dialog modal-lg">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>查看框</h5>
		</div>
		<div class="modal-body"  style="min-height: 600px;">
			<div id="student_container"></div>
		</div>
		</div>
		</div>
	</div>
	
<script type="text/javascript">
	function applyStudent(type) {
		$.ajax({
			type: 'post',
			url: 'msr_applyStudent.action',
			data: {
				gmId: '${gm.id}',
				type: type
			},
			dataType: 'html',
			success: function(data) {
				$('#student_container').html(data);
				$('#confirm').modal();
			}
		});
	}
	
	function selectedStudent(type) {
		$.ajax({
			type: 'post',
			url: 'msr_selectedStudent.action', 
			data: {
				gmId: '${gm.id}',
				type: type
			},
			dataType: 'html',
			success: function(data) {
				$('#student_container').html(data);
				$('#confirm').modal();
			}
		});
	}
	
	function gm_finish(id) {
		$.ajax({
			type: 'post',
			url: 'gm_finish.action',
			data: {
				id: id
			},
			success: function() {
				clickThis(id);
			}
		});
	}
</script>
</body>
</html>