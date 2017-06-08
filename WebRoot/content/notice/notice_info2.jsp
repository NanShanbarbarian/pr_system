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
	<title>新增项目开发者</title>
	
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
	<div class="main-content2" style="padding-left: 15%; padding-right: 15%;">
		<p style="text-align: center; font-size: 18px;">${notice.title}</p>
		<p style="text-align: center; font-size: 14px;">时间：${notice.createtime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布人：${notice.create_user}</p>
		<div style="font-size: 16px !important; min-height: 350px;">${notice.content}</div>
    	<div class="col-sm-offset-5" style="padding-top: 15px;">
			<a class="btn btn-primary" href="user/user.jsp">确定返回</a>
		</div>
	</div>
</body>
</html>