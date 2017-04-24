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
	<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-datetimepicker.min.css">
	<title>科技创新智能互助平台</title>
	
	<style type="text/css">
		.main-content2 {
			width: 90%;
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
		.form-group label,option {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>
</head>

<body>
	<div class="main-content2">
		<ul class="nav nav-tabs col-sm-offset-1">
		  <li class="active"><a href="javascript:void(0);" onclick="applyForm()">资料申请</a></li>
		  <li><a href="javascript:void(0);" onclick="applyExcel()">Excel申请</a></li>
		</ul>
		<div class="appply_container" id="appply_container">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">学院</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="college" id="college">
	    		</div>
	    	</div>
			<div class="form-group">
	    		<label class="col-sm-4 control-label">专利名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name">
	    		</div>
	    	</div>
		    <div class="form-group">
	    		<label class="col-sm-4 control-label">专利类型</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="type" id="type">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">专利号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="number" id="number">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">专利状态</label>
	    		<div class="col-sm-4">
	    			<select class = "form-control" name="status" id="status">
	    				<option value="0">授权</option>
	    			</select>
	    		</div>
	    		<div style="margin-top: 7px;">注：必须为授权</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">授权时间</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="time" id="time">
	    		</div>
	    	</div>
	    	
	    	<div id="member">
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员1学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1">
		    		</div>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员2学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member2">
		    		</div>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员3学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member3">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="addMember()">添加</button>
	    		</div>
	    	</div>
	    	
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">指导老师</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teacher" id="teacher">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_name" id="author_name">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_number" id="author_number">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者电话</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_telephone" id="author_telephone">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5" style="margin-top: 30px;">
	    			<button class="btn btn-info" onclick="patent_apply()">确定申请</button>
	    		</div>
	    	</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="plugins/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script type="text/javascript">
	
		$(function() {
	
			var date = new Date();
			$("#time").val(date.getFullYear() + "-" + (date.getMonth()+1) + "-"+date.getDate());
			$("#time").datetimepicker({
				language : "zh-CN",
				format : "yyyy-mm-dd", //日期格式
				todayBtn : "linked", //初始固定当天日期
				todayHighlight : "true", //当天日期高亮 
				autoclose : "false", //选中即关闭
				startView : "month",
				minView : "month", //最小显示到“月”
			});
			
		});
		
		function addMember() {
			var member = $('#member');
			var num = member.children('div').length;
			
			if(num > 9) {
				alert("最多只能10个成员！");
			} else {
				num = num + 1;
				$('.btn-sm').remove();
				var str = "<div class='form-group'>" + "<label class='col-sm-4 control-label'>队员"+ num +"学号</label>" + "<div class='col-sm-4'><input class='form-control' type='text' name='member'></div>" + 
						"<button class='btn btn-info btn-sm' onClick='addMember()'>添加</button></div>";
				member.append(str);
			}
			
		}
		
		function patent_apply() {
			var members = [];
			var member = $("input[name='member']");
			for(var i=0; i<10; i++) {
				if(i < member.length) {
					members.push(member[i].value);
				} else {
					members.push("");
				}
			}
			$.ajax({
				type : 'post',
				url : 'patent_applyForm.action',
				data : {
					college : $('#college').val(),
					name : $('#name').val(),
					type : $('#type').val(),
					number : $('#number').val(),
					level : $('#level').val(),
					time : $('#time').val(),
					member1 : members[0],
					member2 : members[1],
					member3 : members[2],
					member4 : members[3],
					member5 : members[4],
					member6 : members[5],
					member7 : members[6],
					member8 : members[7],
					member9 : members[8],
					member10 : members[9],
					teacher : $('#teacher').val(),
					authorName: $('#author_name').val(),
					authorNumber: $('#author_number').val(),
					authorTelephone: $('#author_telephone').val()
				},
				success : function() {
					alert("申请成功！");
					window.location.href="content/innovation/patent/history.jsp";
				},
				error : function() {
					alert("申请失败，请重试！");
				}
			});
		}
		
	</script>
</body>
</html>