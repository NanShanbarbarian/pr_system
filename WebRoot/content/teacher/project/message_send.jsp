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
	<title>科技创新智能互助平台</title>
	
</head>

<body>
	<div style="height: 66%;;" id="send_content"></div>
	<div class="col-sm-offset-5" style="padding-top: 15px;">
		<button class="btn btn-primary" onclick="message_new()">确定发送</button>
	</div>
	<script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="plugins/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript">
		$(function() {
			var ue = UE.getEditor('send_content',{
	  			autoHeightEnabled: true,
	  			autoFloatEnabled: true,
	  		});
		});
		
		function message_new() {
			$.ajax({
				type : 'post',
				url : 'message_new.action',
				data : {
					projectId : '${param.projectId}',
					senderId : '${param.senderId}',
					receiverId : '${param.receiverId}',
					content : UE.getEditor('send_content').getContent()
				},
				success : function() {
					$('#confirm').modal('hide');
				}
			});
		}
	</script>
</body>
</html>