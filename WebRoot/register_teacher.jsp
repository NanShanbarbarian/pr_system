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
	<title>注册界面</title>
</head>

<body>
	<form id="teacher_form" class="form-body">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="name_div">必填</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">工号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="number" id="number">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="number_div">必填</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">学院</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="academy" id="academy">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="academy_div">必填</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">职称</label>
	    		<div class="col-sm-4">
		 			<select class = "form-control" name="level" id="level">
		 				<option value="">--请选择--</option>
		 				<option value="0">教授</option>
		 				<option value="1">副教授</option>
		 				<option value="2">讲师</option>
		 			</select>
	    		</div>
	    		<div class="col-sm-4 check-middle" id="level_div">必选</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">性别</label>
	    		<div class="col-sm-4 radio">
	    			<label><input type="radio" name="sex" value="0" checked>男</label>&nbsp;&nbsp;
	    			<label><input type="radio" name="sex"value="1">女</label>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">号码</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="telephone" id="telephone">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="telephone_div">必填</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">短号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="cornet" id="cornet">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="cornet_div"></div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">QQ</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="qq" id="qq">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="qq_div"></div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">邮箱</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="email" id="email">
	    		</div>
	    		<div class="col-sm-4 check-middle" id="email_div"></div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">简述</label>
	    		<div class="col-sm-4">
	    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;"></textarea>
	    		</div>
	    		<div class="col-sm-4 check-middle" id="description_div"></div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-2 col-sm-offset-5">
	    			<input type="button" class="btn btn-info" onclick="teacher_register()" value="提交注册">
	    		</div>
	    	</div>
	    </div>
	</form>
	
<script type="text/javascript">
	function teacher_register() {
		if(checkTeacherForm()) {
			$.ajax({
				type : 'post',
				url : 'teacher_register.action',
				data : {
					name : $('#name').val(),
					number : $('#number').val(),
					academy: $('#academy').val(),
					level: $('#level').val(),
					sex: $("input[name='sex']:checked").val(),
					telephone: $('#telephone').val(),
					cornet: $('#cornet').val(),
					qq: $('#qq').val(),
					email: $('#email').val(),
					description: $('#description').val()
				},
				success : function(data) {
					if(data == 1) {
						alert("注册成功，待审核通过后登陆！");
						window.location.href = "index.jsp";
					} else if(data == 0) {
						alert("注册失败，请仔细核查信息！");
					}
				}, 
				error : function(data) {
					alert("注册失败，请仔细核查信息！");
				}
			});
		}
	}
</script>
</body>
</html>