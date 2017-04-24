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
	<ul class="nav nav-tabs col-sm-offset-1" id="head_content1">
	  <li class="active" id="nav1"><a href="javascript:void(0);" onclick="project_view(${param.id})">项目简介</a></li>
	  <li id="nav2"><a href="javascript:void(0);" onclick="student_selected(${param.id})">开发人员</a></li>
	  <li id="nav3"><a href="javascript:void(0);" onclick="student_apply(${param.id})">申请人员</a></li>
	  <li id="nav4"><a href="javascript:void(0);" onclick="student_selected(${param.id})">已选人员</a></li>
	  <li id="nav5"><a href="javascript:void(0);" onclick="student_evaluate(${param.id})">评价模块</a></li>
	</ul>
	<div class="form-horizontal" style="margin-top: 50px;">
		<div class="form-group">
    		<label class="col-sm-3 control-label">项目名称</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="name" id="name">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">项目状态</label>
    		<div class="col-sm-8" id="status_content"></div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">项目人数</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="num" id="num">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">已选人数</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="selectCount" id="selectCount">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">开发周期</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="period" id="period">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">标签</label>
    		<div class="col-sm-8">
    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">备注关键词</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="keyword" id="keyword">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">项目描述</label>
    		<div class="col-sm-8">
    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:100px;vertical-align:top;resize:none;"></textarea>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">项目要求</label>
    		<div class="col-sm-8">
    			<textarea class = "form-control" name="requirement" id="requirement" style="width:100%; height:100px;vertical-align:top;resize:none;"></textarea>
    		</div>
    	</div>
    	<div class="form-group" id="foot_content"></div>
	</div>
	</div>
	
	<script type="text/javascript" src="js/teacher/project_manage.js"></script>
	<script type="text/javascript">
		$(function(){
	    	$('#nav1').hide();
	    	$('#nav2').hide();
	    	$('#nav3').hide();
	    	$('#nav4').hide();
	    	$('#nav5').hide();
	    	
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
					id : '${param.id}'
				},
				dataType : 'json',
				success : function(data) {
					$('#name').val(data.name);
					$('#num').val(data.num);
					$('#period').val(data.period);
					$('#keyword').val(data.keyword);
					$('#description').val(data.description);
					$('#requirement').val(data.requirement);
					$('#selectCount').val(data.selectCount);
					if(data.status == 0) {   //发布中
						$('#nav1').show();
						$('#nav3').show();
						$('#nav4').show();
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='发布中'>"));
						$('#foot_content').append($("<div class='col-sm-4 col-sm-offset-6'><a class='btn btn-info' href='javascript:void(0);'onclick='project_start()' style='font-size: 13px;'>开始项目</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-info' href='javascript:void(0);' onclick='project_remove()' style='font-size: 13px;'>删除项目</a></div>"));
					} else if(data.status == 1) {  //进行中
						$('#nav1').show();
						$('#nav2').show();
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='进行中'>"));
						$('#foot_content').append($("<div class='col-sm-4 col-sm-offset-6' ><a class='btn btn-info' href='javascript:void(0);' onclick='project_finish()' style='font-size: 13px;'>完成项目</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-info' href='javascript:void(0);' onclick='project_remove()' style='font-size: 13px;'>删除项目</a></div>"));
					} else if(data.status == 2) {   //已完成
						$('#nav1').show();
						$('#nav2').show();
						$('#nav5').show();
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='已完成'>"));
						$('#foot_content').append($("<div class='col-sm-2 col-sm-offset-6' ><a class='btn btn-info' href='javascript:void(0);' onclick='project_end()' style='font-size: 13px;'>已完成</a></div>"));
					}
				},
				error : function() {
					window.location.href = "content/teacher/project/project_my.jsp"; 
				}
			});
		}
		
		function project_start() {
			$.ajax({
				type : 'post',
				url : 'project_start.action',
				data : {
					id : '${param.id}'
				},
				success : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				},
				error : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				}
			});
		}
		
		function project_finish() {
			$.ajax({
				type : 'post',
				url : 'project_finish.action',
				data : {
					id : '${param.id}'
				},
				success : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				},
				error : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				}
			});
		}
		
		function project_remove() {
			$.ajax({
				type : 'post',
				url : 'project_remove.action',
				data : {
					id : '${param.id}'
				},
				success : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				},
				error : function() {
					window.location.href = "content/teacher/project/project_my.jsp";
				}
			});
		}
		
		function project_end() {
			window.location.href = "content/teacher/project/project_my.jsp";
		}
		
		
	</script>	  
</body>
</html>