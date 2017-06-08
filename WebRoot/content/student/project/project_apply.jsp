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
	<div class="main-content2">
	<div class="form-horizontal">
		<div class="form-group">
    		<label class="col-sm-2 control-label">项目名称</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="name" id="name">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">项目状态</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="teaName" id="teaName">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">项目状态</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="status" id="status" value="发布中">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">项目人数</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="num" id="num">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">已选人数</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="selectCount" id="selectCount">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">开发周期</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="period" id="period">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">标签</label>
    		<div class="col-sm-8">
    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">备注关键词</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="keyword" id="keyword">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">项目描述</label>
    		<div class="col-sm-8">
    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:100px;vertical-align:top;resize:none;"></textarea>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">项目要求</label>
    		<div class="col-sm-8">
    			<textarea class = "form-control" name="requirement" id="requirement" style="width:100%; height:100px;vertical-align:top;resize:none;"></textarea>
    		</div>
    	</div>
    	<div class="form-group">
    		<div class="col-sm-2 col-sm-offset-5">
    			<c:if test="${param.isApply == 0}">
    			<a class="btn btn-info" href="javascript:void(0);" onclick="upr_apply()" style="font-size: 13px;">申请项目</a>
    			</c:if>
    			<c:if test="${param.isApply == 1}">
    			<a class="btn btn-info" href="content/student/project/project_recommend.jsp" style="font-size: 13px;">已申请</a>
    			</c:if>
    			<c:if test="${param.isApply == 2}">
    			<a class="btn btn-info" href="content/student/project/project_recommend.jsp" style="font-size: 13px;">申请成功</a>
    			</c:if>
    		</div>
    	</div>
    </div>
	</div>	
	
	<script type="text/javascript">
	
		$(function(){
			$.ajax({
				type : 'post',
				url : 'ptr_getByProjectId.action',
				data : {
					ProjectId : '${param.id}'
				},
				dataType : 'json',
				success : function(data) {
					for(var i=0; i<data.length; i++) {
						addTag(data[i].tag);
					}
				}
			});
			
			project_getByProjectId();
			
		});
		
		function addTag(tag) {
			$("#tag-list").append($("<button class='btn btn-default btn-xs' style='margin-right: 2px;'>"+ tag +"</button>"));
		}
		
		function project_getByProjectId() {
			$.ajax({
				type : 'post',
				url : 'project_getByProjectId.action',
				data : {
					id : '${param.id}',
				},
				dataType : 'json',
				success : function(data) {
					$('#name').val(data.name);
					$('#teaName').val(data.teaName);
					$('#num').val(data.num);
					$('#period').val(data.period);
					$('#keyword').val(data.keyword);
					$('#description').val(data.description);
					$('#requirement').val(data.requirement);
					$('#selectCount').val(data.selectCount);
				},
				error : function() {
					window.location.href = "content/student/project/project_recommend.jsp"; 
				}
			});
		}
		
		function upr_apply() {
			$.ajax({
				type : 'post',
				url : 'upr_save.action',
				data : {
					projectId : '${param.id}',
					stuLoginId : '${sessionScope.login.id}'
				},
				success : function() {
					window.location.href = "content/student/project/project_recommend.jsp";
				},
				error : function() {
					window.location.href = "content/student/project/project_recommend.jsp";
				}
			});
		}
	</script>	  
</body>
</html>