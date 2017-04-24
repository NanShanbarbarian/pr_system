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
	    	<div id="member">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">成员姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" value="${gm.member}">
	    		</div>
	    	</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">课业学生助教</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teachingAsistant" id="teachingAsistant" value="${gm.teachingAsistant}">
	    		</div>
	    		<c:if test="${gm.teachingAsistant > 0}">
	    			<c:if test="${msr != null}">
    					<c:if test="${msr.type == 1}">
    						<c:if test="${msr.isPass == 0}">
    							<button class="btn btn-default btn-normal" onclick="msr_remove()">取消申请</button>
    						</c:if>
    						<c:if test="${msr.isPass == 1}">
    							<button class="btn btn-success btn-normal">已验证</button>
    						</c:if>
    						<c:if test="${msr.isPass == 2}">
    							<button class="btn btn-danger btn-normal">已拒绝</button>
    						</c:if>
    					</c:if>
	    			</c:if>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">实验室助教</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="labAsistant" id="labAsistant" value="${gm.labAsistant}">
	    		</div>
	    		<c:if test="${gm.labAsistant > 0}">
	    			<c:if test="${msr != null}">
    					<c:if test="${msr.type == 2}">
    						<c:if test="${msr.isPass == 0}">
    							<button class="btn btn-default btn-normal" onclick="msr_remove()">取消申请</button>
    						</c:if>
    						<c:if test="${msr.isPass == 1}">
    							<button class="btn btn-success btn-normal">已验证</button>
    						</c:if>
    						<c:if test="${msr.isPass == 2}">
    							<button class="btn btn-danger btn-normal">已拒绝</button>
    						</c:if>
    					</c:if>
	    			</c:if>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">课外科技竞赛</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="competitionNum" id="competitionNum" value="${gm.competitionNum}">
	    		</div>
	    		<c:if test="${gm.competitionNum > 0}">
	    			<c:if test="${msr != null}">
    					<c:if test="${msr.type == 3}">
    						<c:if test="${msr.isPass == 0}">
    							<button class="btn btn-default btn-normal" onclick="msr_remove()">取消申请</button>
    						</c:if>
    						<c:if test="${msr.isPass == 1}">
    							<button class="btn btn-success btn-normal">已验证</button>
    						</c:if>
    						<c:if test="${msr.isPass == 2}">
    							<button class="btn btn-danger btn-normal">已拒绝</button>
    						</c:if>
    					</c:if>
	    			</c:if>
	    		</c:if>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">科研研究助理</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="researchAsistant" id="researchAsistant" value="${gm.researchAsistant}">
	    		</div>
	    		<c:if test="${gm.researchAsistant > 0}">
	    			<c:if test="${msr != null}">
    					<c:if test="${msr.type == 4}">
    						<c:if test="${msr.isPass == 0}">
    							<button class="btn btn-default btn-normal" onclick="msr_remove()">取消申请</button>
    						</c:if>
    						<c:if test="${msr.isPass == 1}">
    							<button class="btn btn-success btn-normal">已验证</button>
    						</c:if>
    						<c:if test="${msr.isPass == 2}">
    							<button class="btn btn-danger btn-normal">已拒绝</button>
    						</c:if>
    					</c:if>
	    			</c:if>
	    		</c:if>
	    	</div>
	    	<div class="form-group" style="margin-top: 30px;">
	    		<div class="col-sm-2 col-sm-offset-5">
	    		<a class="btn btn-info" href="content/student/mentor/mentor.jsp" style="font-size: 13px;">确定返回</a>
	    		</div>
	    	</div>
	    </div>
	    </div>
	</div>
	
<script type="text/javascript">
	function msr_remove() {
		$.ajax({
			type: 'post',
			url: 'msr_remove.action',
			data: {
				id: '${msr.id}'
			},
			success: function() {
				window.location.href = "content/student/mentor/mentor.jsp";
			}
		});
	}
</script>  
</body>
</html>