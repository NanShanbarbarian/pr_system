<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
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
	<style type="text/css">
		.main-content2 {
			width: 90%;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 30px auto;
			padding: 100px 20px 80px 20px;
			background-color: #fff;
			min-height: 800px;
		}
		.subcontainer {
			width:60%;
			margin:50px auto;
			overflow: hidden;
		}
		.subcontainer ul { 
			list-style:none;
			padding:0;
			margin:0px; 
		}
		.subcontainer ul li { 
			margin-top:2px; 
		}
		.content { 
			width:98%;
			border-bottom: #DCDCDC 1px solid;
			clear:both; 
		}
		.subcontent {
			float: left;
			width: 70%;
			padding-left: 20px;
			padding-top: 2px;
		}
		.operate {
			float: right;
			width: 25%;
			padding-left: 20px;
			margin-top: 20px;
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
	<ul class="nav nav-tabs col-sm-offset-1" id="head_content1">
		<li id="nav1"><a href="javascript:void(0);" onclick="project_view(${param.id})">项目简介</a></li>
		<li class="active" id="nav2"><a href="javascript:void(0);" onclick="student_selected(${param.id})">开发人员</a></li>
		<li id="nav3"><a href="javascript:void(0);" onclick="student_apply(${param.id})">申请人员</a></li>
		<li class="active" id="nav4"><a href="javascript:void(0);" onclick="student_selected(${param.id})">已选人员</a></li>
		<li id="nav5"><a href="javascript:void(0);" onclick="student_evaluate(${param.id})">评价模块</a></li>
	</ul>
	<div class="subcontainer" id="subcontainer">
  			<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有已选人员！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content">
					<div class="subcontent" onclick="student_info(${item.loginId})">
						<h5>${item.name}</h5>	
						<h6>${item.className}</h6>
					</div>
					<div class="operate">
					<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="message_confirm(${item.loginId})" >消息</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-sm" href="javascript:void(0);" onclick="upr_refuse(${item.loginId})" >删除</a>
					</div>	
					<div style="clear:both;"></div>		
				</div>		
				</li>
				</c:forEach>
    			</c:otherwise>
    		</c:choose>        					
		</ul>
  		</div>
  	</div>
  	
  	<div class="modal fade" id="confirm">
		<div class="modal-dialog modal-lg">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>消息框</h5>
		</div>
		<div class="modal-body"  style="height: 600px;">
			<div>
				<input type="hidden" id="id" name="id">
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
  	<script type="text/javascript">
	  	$(function(){
	  		$('#nav1').hide();
	    	$('#nav2').hide();
	    	$('#nav3').hide();
	    	$('#nav4').hide();
	    	$('#nav5').hide();
			
			$.ajax({
				type : 'post',
				url : 'project_getByProjectId.action',
				data : {
					id : '${param.id}',
				},
				dataType : 'json',
				success : function(data) {
					if(data.status == 0) {   //发布中
						$('#nav1').show();
						$('#nav3').show();
						$('#nav4').show();
					} else if(data.status == 1) {  //进行中
						$('#nav1').show();
						$('#nav2').show();
					} else if(data.status == 2) {   //已完成
						$('#nav1').show();
						$('#nav2').show();
						$('#nav5').show();
					}
				},
				error : function() {
					window.location.href = "content/student/project/project_recommend.jsp"; 
				}
			});
		});
	  	
	  	function student_info(loginId) {
	  		$.ajax({
	  			type : 'post',
	  			url : 'student_getById.action',
	  			data : {
	  				loginId : loginId,
	  				frequency : 2 
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#subcontainer').html(data);
	  			} 
	  		});
	  	}
  	
	  	function upr_refuse(stuLoginId) {
  			$.ajax({
  				type : 'post',
  				url : 'upr_refuse.action',
  				data : {
  					projectId : '${param.id}',
  					stuLoginId : stuLoginId,
  					status : 0
  				},
  				success : function() {
  					student_apply('${param.id}');
  				}
  			});
  		}
	  	
	  	function message_confirm(id) {
	  		$('#confirm').modal();
	  		$('#id').val(id);
	  		sendMessage();
	  	}
	  	
	  	function sendMessage() {
	  		$('#first').addClass("active");
	  		$('#second').removeClass("active");
	  		$('#third').removeClass("active");
	  		$.ajax({
	  			type : 'post',
	  			url : 'content/teacher/project/message_send.jsp',
	  			data : {
	  				projectId : '${param.id}',
	  				receiverId : $('#id').val(),
	  				senderId : '${sessionScope.login.id}'
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
	  			url : 'message_getListOnTeacher.action',
	  			data : {
	  				projectId : '${param.id}',
	  				receiverId : '${sessionScope.login.id}',
	  				senderId : $('#id').val(),
	  				frequency : 0
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
	  			url : 'message_getListOnTeacher.action',
	  			data : {
	  				projectId : '${param.id}',
	  				receiverId : $('#id').val(),
	  				senderId : '${sessionScope.login.id}',
	  				frequency : 1
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#message_container').html(data);
	  			}
	  		});
	  	}
  	</script>
</body>
</html>