<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="plugins/bootstrap/css/fileinput.min.css">
	<script type="text/javascript" src="plugins/bootstrap/js/fileinput.js"></script>
	<script type="text/javascript" src="plugins/bootstrap/js/fileinput_locale_zh.js"></script>
	<title>科技创新智能互助平台</title>
	
	<style type="text/css">
		.main-content2 {
			width: 90%;
			min-height: 800px;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 30px auto;
			padding: 100px 50px 80px 20px;
			background-color: #fff;
		}
		
		.appply_container {
			width:90%;
			margin: 50px auto;
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
		<ul class="nav nav-tabs col-sm-offset-1">
		  <li><a href="javascript:void(0);" onclick="applyForm()">资料申请</a></li>
		  <li class="active"><a href="javascript:void(0);" onclick="applyExcel()">Excel申请</a></li>
		</ul>
		<input type="hidden" name="fileName" id="fileName" >
		<div class="appply_container" id="appply_container">
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<input type="file" class="file" name="file" id="file" onchange="change(event)" accept=".xls,.xlsx">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-3" style="margin-top: 50px;">
    			<p class="help-block"><a href="attachment/软件著作申请表.xlsx">附件：软件著作申请表Excel文件</a></p>
    			</div>
    		</div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		$('#file').fileinput({
				language: 'zh', //设置语言
				uploadUrl: 'sc_applyExcel.action',
				allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
				showUpload: true, //是否显示上传按钮
				showCaption: true,//是否显示标题
				browseClass: "btn btn-primary", //按钮样式	
				maxFileCount: 1, //表示允许同时上传的最大文件个数
				enctype: 'multipart/form-data',
				validateInitialCount:true,
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
				uploadExtraData: function(event, file) {
					return {
						"fileName": $('#fileName').val(),
						"applicantId": '${sessionScope.login.id}'
					};
				}
			}).on("fileuploaded", function(event, data) {
		        if(data.response == 10) {
		            alert("申请成功！");
		            window.location.href = "content/innovation/sc/history.jsp";
		        } else if(data.response == 1) {
		        	alert("申请失败，上传时文件出现问题！");
		        } else if(data.response == 2) {
		        	alert("申请失败，文件格式规范错误或只能允许申请一次！");
		        } else if(data.response == 3) {
		        	alert("申请失败，软件著作状态必须填写“授权”！");
		        } else if(data.response == 4) {
		        	alert("申请失败，授权时间填写不规范！");
		        } else if(data.response == 5) {
		        	alert("申请失败，系统出错！");
		        } 
			});
		
		function change(e) {
			 var src=e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
			 var filename=src.value;
			 var fileName = filename.substring( filename.lastIndexOf('\\')+1 );
			 
			 $('#fileName').val(fileName);
		}
		
	</script>
</body>
</html>