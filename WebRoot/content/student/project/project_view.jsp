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
		.message_container {
			border: #EEE8CD 1px solid; 
			height: 90%; 
			width: 100%; 
			margin-top: 10px;
		}
	</style>

</head>
<body>
	<div class="main-content2">
	<div class="form-horizontal" style="margin-top: 10px;">
		<input type="hidden" id="teaLoginId" name="teaLoginId">
		<div class="form-group">
    		<label class="col-sm-3 control-label">项目名称</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="name" id="name">
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-3 control-label">项目发布者</label>
    		<div class="col-sm-8">
    			<input class = "form-control" type="text" name="teaName" id="teaName">
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
	
	<div class="modal fade" id="message_confirm">
		<div class="modal-dialog modal-lg">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>消息框</h5>
		</div>
		<div class="modal-body"  style="height: 600px;">
			<div>
				<ul class="nav nav-pills">
				  <li class="active" id="first"><a href="javascript:void(0);" onclick="sendMessage()">发送消息</a></li>
				  <li id="second"><a href="javascript:void(0);" onclick="receiveMessage()">收件箱</a></li>
				   <li id="third"><a href="javascript:void(0);" onclick="sentMessage()">已发送</a></li>
				</ul>
			</div>
			<div  class="message_container" id="message_container"></div>
		</div>
		</div>
		</div>
	</div>
	
	<div class="modal fade" id="evaluate_confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>评价框</h5>
		</div>
		<div class="modal-body"  style="padding: 30px 10px 30px 10px;">
			<div class="form-horizontal">
				<div class="form-group">
		    		<label class="col-sm-2 control-label">评分</label>
		    		<div class="col-sm-8">
		    			<input class = "form-control" type="text" name="score" id="score">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-2 control-label">评语</label>
		    		<div class="col-sm-8">
		    			<textarea class = "form-control" name="remark" id="remark" style="width:100%; height:200px;vertical-align:top;resize:none;"></textarea>
    				</div>
    			</div>
    			<div class="form-group">
		    		<div class="col-sm-4 col-sm-offset-5" id="isEvaluate">
    				</div>
    			</div>
			</div>
		</div>
		</div>
		</div>
	</div>
	<script type="text/javascript" src="js/teacher/project_manage.js"></script>
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
					$('#teaLoginId').val(data.teaLoginId);
					$('#teaName').val(data.teaName);
					$('#num').val(data.num);
					$('#period').val(data.period);
					$('#keyword').val(data.keyword);
					$('#description').val(data.description);
					$('#requirement').val(data.requirement);
					$('#selectCount').val(data.selectCount);
					if(data.status == 0) {   //发布中
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='发布中'>"));
						$('#foot_content').append($("<div class='col-sm-4 col-sm-offset-6'><a class='btn btn-info' href='javascript:void(0);'onclick='message_confirm()' style='font-size: 13px;'>消息</a></div>"));
					} else if(data.status == 1) {  //进行中
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='进行中'>"));
						$('#foot_content').append($("<div class='col-sm-4 col-sm-offset-6' ><a class='btn btn-info' href='javascript:void(0);' onclick='message_confirm()' style='font-size: 13px;'>消息</a></div>"));
					} else if(data.status == 2) {   //已完成
						$('#status_content').append($("<input class = 'form-control' type='text' name='status' id='status' value='已完成'>"));
						$('#foot_content').append($("<div class='col-sm-2 col-sm-offset-6' ><a class='btn btn-info' href='javascript:void(0);' onclick='message_confirm()' style='font-size: 13px;'>消息</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-info' href='javascript:void(0);' onclick='evaluate_confirm()' style='font-size: 13px;'>评价</a></div>"));
					}
				},
				error : function() {
					window.location.href = "content/student/project/project_my.jsp"; 
				}
			});
		}
		
		function message_confirm() {
	  		$('#message_confirm').modal();
	  		sendMessage();
	  	}
	  	
	  	function sendMessage() {
	  		$('#first').addClass("active");
	  		$('#second').removeClass("active");
	  		$('#third').removeClass("active");
	  		$.ajax({
	  			type : 'post',
	  			url : 'content/student/project/message_send.jsp',
	  			data : {
	  				projectId : '${param.id}',
	  				teaLoginId : $('#teaLoginId').val()
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#message_container').html(data);
	  			}
	  		});
	  	}
	  	
	  	function receiveMessage() {
	  		$('#first').removeClass("active");
	  		$('#second').addClass("active");
	  		$('#third').removeClass("active");
	  		$.ajax({
	  			type : 'post',
	  			url : 'message_getListOnStudent.action',
	  			data : {
	  				projectId : '${param.id}',
	  				receiverId : '${sessionScope.login.id}'
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#message_container').html(data);
	  			}
	  		});
	  	}
	  	
	  	function sentMessage() {
	  		$('#first').removeClass("active");
	  		$('#second').removeClass("active");
	  		$('#third').addClass("active");
	  		$.ajax({
	  			type : 'post',
	  			url : 'message_getListOnStudent.action',
	  			data : {
	  				projectId : '${param.id}',
	  				senderId : '${sessionScope.login.id}'
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#message_container').html(data);
	  			}
	  		});
	  	}
	  	
	  	function evaluate_confirm() {
	  		
	  		$.ajax({
	  			type : 'post',
	  			url : 'upr_get.action',
	  			data : {
	  				projectId : '${param.id}',
	  				stuLoginId : '${sessionScope.login.id}'
	  			},
	  			dataType : 'json',
	  			success : function(data) {
					if(data.isEvaluate ==  1) {
						$('#score').val(data.teaScore);
						$('#remark').val(data.teaRemark);
						$('#isEvaluate').append($("<button class='btn btn-info' onclick='modelHide()'>已评价</button>"));
					} else {
						$('#isEvaluate').append($("<button class='btn btn-info' onclick='sendRemark()'>确定评价</button>"));
					}  				
	  			}
	  		});
	  		$('#evaluate_confirm').modal();
	  		
	  	}
	  	
	  	function sendRemark() {
	  		$.ajax({
	  			type : 'post',
	  			url : 'upr_remarkByStudent.action',
	  			data : {
	  				projectId : '${param.id}',
	  				stuLoginId : '${sessionScope.login.id}',
	  				teaRemark : $('#remark').val(),
	  				teaScore : $('#score').val() 
	  			},
	  			success : function() {
	  				$('#evaluate_confirm').modal('hide');
	  			}
	  		});
	  	}
	  	
	  	function modelHide() {
	  		$('#evaluate_confirm').modal('hide');
	  	}
	</script>	  
</body>
</html>