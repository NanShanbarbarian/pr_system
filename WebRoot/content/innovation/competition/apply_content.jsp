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
	    		<label class="col-sm-4 control-label">竞赛类别</label>
	    		<div class="col-sm-4">
	    			<select class="form-control" name="category" id="category" onchange="onCompetitionType()">
	   					<option value="0">专业赛事</option>
	   					<option value="1">非专业赛事</option>
	   				</select>
	    		</div>
	    	</div>
			<div class="form-group">
	    		<label class="col-sm-4 control-label">竞赛名称</label>
	    		<div class="col-sm-4">
	    			<select class="form-control" name="name" id="name" >
	    			</select>
	    		</div>
	    	</div>
		    <div class="form-group">
	    		<label class="col-sm-4 control-label">作品名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="workName" id="workName">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖层次</label>
	    		<div class="col-sm-4">
	    			<select class="form-control" name="level" id="level" >
	    				<option value="">--请选择--</option>
	   					<option value="1">国家级</option>
	   					<option value="2">省级</option>
	   					<option value="3">市级</option>
	   					<option value="4">校级</option>
	   					<option value="5">院级</option>
	    			</select>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖等级</label>
	    		<div class="col-sm-4">
	    			<select class="form-control" name="rank" id="rank">
	    				<option value="">--请选择--</option>
	   					<option value="0">特等奖</option>
	   					<option value="1">一等奖</option>
	   					<option value="2">二等奖</option>
	   					<option value="3">三等奖</option>
	   					<option value="4">成功参赛奖</option>
	    			</select>
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
	    		<label class="col-sm-4 control-label">获奖时间</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="winning_time" id="winning_time">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖学年</label>
	    		<div class="col-sm-4">
	    		<div style="width: 60px; display: inline-block;">
	    			<input class = "form-control" type="text" name="begin_date" id="begin_date">
	    		</div>
	    		<label style="margin-top: 7px; margin-left: 2px; margin-right: 2px;">到</label>
	    		<div style="width: 60px; display: inline-block;">
	    			<input class = "form-control" type="text" name="end_date" id="end_date">
	    		</div>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖学期</label>
	    		<div class="col-sm-4 radio">
	    			<label><input type="radio" name="term" value="1"  checked>上学期</label>&nbsp;&nbsp;
	    			<label><input type="radio" name="term"value="2">下学期</label>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5" style="margin-top: 30px;">
	    			<button class="btn btn-info" onclick="competition_apply()">确定申请</button>
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
			if(date.getMonth() < 9) {
				$("#begin_date").val(date.getFullYear()-1);
				$("#end_date").val(date.getFullYear());
			} else {
				$("#begin_date").val(date.getFullYear());
				$("#end_date").val(date.getFullYear() + 1);
			}
			
			$("#begin_date").datetimepicker({
				language : "zh-CN",
				format : "yyyy", //日期格式
				/* todayBtn : "linked", //初始固定当天日期
				todayHighlight : "true", //当天日期高亮 */
				autoclose : "false", //选中即关闭
				startView : "decade",
				minView : "decade", //最小显示到“年”
			});
			
			$("#end_date").datetimepicker({
				language : "zh-CN",
				format : "yyyy", //日期格式
				/* todayBtn : "linked", //初始固定当天日期
				todayHighlight : "true", //当天日期高亮 */
				autoclose : "false", //选中即关闭
				startView : "decade",
				minView : "decade", //最小显示到“年”
			});
			
			$("#winning_time").val(date.getFullYear() + "-" + (date.getMonth()+1) + "-"+date.getDate());
			$("#winning_time").datetimepicker({
				language : "zh-CN",
				format : "yyyy-mm-dd", //日期格式
				todayBtn : "linked", //初始固定当天日期
				todayHighlight : "true", //当天日期高亮 
				autoclose : "false", //选中即关闭
				startView : "month",
				minView : "month", //最小显示到“月”
			});
			
			onCompetitionType();
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
		
		function competition_apply() {
			if(checkForm) {
				var period = $('#begin_date').val() + "/" + $('#end_date').val();
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
					url : 'competition_applyByForm.action',
					data : {
						ctId : $('#name').val(),
						workName : $('#workName').val(),
						level : $('#level').val(),
						rank : $('#rank').val(),
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
						winningTime : $('#winning_time').val(), 
						period : period,
						applicantId : '${sessionScope.login.id}',
						term : $("input[name='term']:checked").val()
					},
					success : function() {
						alert("申请成功！");
						window.location.href="content/innovation/competition/history.jsp";
					},
					error : function() {
						alert("申请失败，请重试！");
					}
				});
			} else {
				alert("请检查填写规范！");
			}
			
		}
		
		function onCompetitionType() {
			$('#name option').remove();
			$('#name').append("<option value=''>--请选择--</option>");
			$.ajax({
				type : 'post',
				url : 'ct_getListByCategory.action',
				data : {
					category : $('#category').val()
				},
				dataType : 'json',
				success : function(data) {
					for(var i=0; i<data.length; i++) {
						$('#name').append("<option value=" + data[i].id + ">" + data[i].name + "</option>");
					}
				}
			});
		}
		
		function checkForm() {
			if($('#end_date').val() != ($('#begin_date').val()+1)) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>