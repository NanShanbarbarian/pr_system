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
			margin-left: 20px;
			margin-top: 2px;
		}
		.operate {
			float: right;
			width: 25%;
			padding-left: 20px;
			margin-top: 20px;
		}
	</style>
</head>

<body>
	<div class="main-content2">
	<ul class="nav nav-tabs col-sm-offset-1">
	  <li><a href="javascript:void(0);" onclick="project_view(${param.id})">项目简介</a></li>
	  <li class="active"><a href="javascript:void(0);" onclick="student_apply(${param.id})">申请人员</a></li>
	  <li><a href="javascript:void(0);" onclick="student_selected(${param.id})">已选人员</a></li>
	</ul>
	<div class="subcontainer" id="subcontainer">
  			<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有申请人员！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content">
					<div class="subcontent" onclick="student_info(${item.loginId})">
						<h5>${item.name}</h5>	
						<h6>${item.className}</h6>
					</div>
					<div class="operate">
					<a id="accept" class="btn btn-success btn-sm" href="javascript:void(0);" onclick="upr_accept(${item.loginId})" >添加</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-sm" href="javascript:void(0);" onclick="upr_refuse(${item.loginId})" >拒绝</a>
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
  	
  	<script type="text/javascript">
  	
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
  	
  		function upr_accept(stuLoginId) {
  			$.ajax({
  				type : 'post',
  				url : 'upr_accept.action',
  				data : {
  					projectId : '${param.id}',
  					stuLoginId : stuLoginId
  				},
  				success : function() {
  					student_apply('${param.id}');
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
  					status : 1
  				},
  				success : function() {
  					student_apply('${param.id}');
  				}
  			});
  		}
  	</script>
</body>
</html>