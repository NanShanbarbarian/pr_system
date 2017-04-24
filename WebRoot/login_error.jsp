<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="refresh" content="3; url=index.jsp">
	<title>登录失败</title>
	<style type="text/css">
		.location {
			margin: 30px auto auto 30px;
		}
	</style>
</head>

<body>
	<div class="location">
	<p >&nbsp;&nbsp;登录失败，账号或密码不正确！</p>
	<a href="index.jsp">三秒后跳转到首页。。。</a>
	</div>
</body>
</html>