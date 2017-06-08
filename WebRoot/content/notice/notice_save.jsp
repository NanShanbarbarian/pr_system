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
	<div class="main-content2">
		<div class="form-horizontal">
			<div class="row">
			<div class="form-group">
	    		<label class="col-sm-2 control-label">标题：</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="title" id="title">
	    		</div>
	    	</div>
	    	</div>
	    	
	    	<div class="row">
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">内容：</label>
		    	<div class="col-sm-8">
		    		<div style="height: 400px;" id="content"></div>
		    	</div>
	    	</div>
	    	</div>
	    	
	    	<div class="row">
	    	<div class="col-sm-offset-5" style="padding-top: 15px;">
				<button class="btn btn-primary" onclick="notice_publish()">确定发布</button>
			</div>
			</div>
	    </div>
	</div>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript">
	$(function() {
		var ue = UE.getEditor('content',{
				autoHeightEnabled: true,
				autoFloatEnabled: true,
			});
	});
	
	function notice_publish() {
		$.ajax({
			type : 'post',
			url : 'notice_publish.action',
			data : {
				title : $('#title').val(),
				content : UE.getEditor('content').getContent()
			},
			dataType : 'html',
			success : function(data) {
				window.location.href = "content/notice/notice.jsp";
			}, 
			error : function(data) {
				window.location.href = "content/notice/notice.jsp";
			}
		});
	}
</script>
</body>
</html>