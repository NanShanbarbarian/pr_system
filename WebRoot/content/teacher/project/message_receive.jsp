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
		.list-content {
			overflow: hidden;
			height: 40px; 
			line-height: 40px; 
			padding-top: 3px;
		}
		
		.left_content {
			float: left; 
			width: 25%; 
			height: 100%; 
			overflow: auto; 
			padding: 5px 2px 5px 2px; 
			border: #DCDCDC 1px solid;
		}
		
		.receive_content {
			float: right; 
			width: 75%; 
			height: 70%; 
			padding: 10px;
		}
	</style>
</head>

<body>
	<div class="left_content">
		<div class="list-group">
		<c:choose>
 			<c:when test="${list == null}"><p style="text-align: center;">没有收到消息！</p></c:when>
 			<c:otherwise>
 			<input type="hidden" name="receiveId" id="receiveId" value="${list[0].id}">
 			<c:forEach items="${list}" var="item"> 
 				<button type="button" class="list-group-item list-content" onclick="message_read(${item.id})">${item.createtime}</button>
 			</c:forEach>
 			</c:otherwise>
 		</c:choose>
		</div>
	</div>
	<div class="receive_content" id="receive_content">
	</div>
	
	<script type="text/javascript">
		$(function() {
			if($('#receiveId').val() != null ) {
				message_read($('#receiveId').val());
			}
		});
		
		function message_read(id) {
			
			$.ajax({
				type : 'post',
				url : 'message_getById.action',
				data : {
					id : id
				},
				success : function(data) {
					$("#receive_content").html(data);
				}
			});
			
		}
	</script>
</body>
</html>