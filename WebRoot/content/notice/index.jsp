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
	<title>系统公告</title>
</head>

<body>
	<div style="margin: 0 auto; width: 60%; min-height: 600px; background-color: #fff;">
		<div class="row news">
			<c:if test="${list == null}">
			<div>
			<h4>没有系统公告!</h4>
			</div>
			</c:if>
			<c:if test="${list != null}">
			<div>
			<h4 style="color: #87CEFA;">系统公告</h4>
			<div class="more" ><a href="content/notice/notice.jsp">更多>></a></div>
			</div>
			<div class="fix"></div>
			<ul>
				<c:forEach items="${list}" var="item"> 
				<li><a onclick="notice_info(${item.id})">${item.title}</a></li>
				</c:forEach>
			</ul>
			</c:if>
		</div>
	</div>
</body>
<script>
	function notice_info(id) {
		$.ajax({
			type: 'post',
			url: 'notice_getById2.action?id=' + id,
			dataType: 'html',
			success: function(data) {
				$("#main-content").html(data);
			}
		});
	}
</script>
</html>