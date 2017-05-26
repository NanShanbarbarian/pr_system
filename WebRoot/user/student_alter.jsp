<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>科技创新智能互助平台</title>
	
	<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/sidebar/sidebar-menu.css">
	<link rel="stylesheet" href="css/main.css">
	
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
	<section class="bg-color">
		<aside class="sidebar">
			<div  class="left-head">
				<a class="left-head-text" href="user/teacher.jsp">科技创新智能互助平台</a>
			</div>
			<div class="blank"></div>
			<jsp:include page="menu.jsp"></jsp:include>
		</aside>
		
		<section class="main-container">
			<jsp:include page="top.jsp"></jsp:include>
			<section>
				<ul class="breadcrumb nav-bar">
					<li><a href="javascript:void(0);">科技创新智能互助平台</a></li>
					<li><a href="javascript:void(0);">账号信息</a></li>
					<li class="active">个人资料</li> 
				</ul>
				<div class="main-content">
					<div class="main-content2 form-horizontal">
					<div class="row">
						<div class="col-xs-6">
							<div class="form-group">
					    		<label class="col-sm-3 control-label">姓名</label>
					    		<div class="col-sm-8">
					    			<span class = "form-control">${sessionScope.user.name}</span>
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">学号</label>
					    		<div class="col-sm-8">
					    			<span class = "form-control">${sessionScope.login.number}</span>
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">角色</label>
					    		<div class="col-sm-8">
					    			<span class = "form-control">项目开发者</span>
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">经验值</label>
					    		<div class="col-sm-8">
					    			<span class = "form-control">${sessionScope.user.experience}</span>
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">学院</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="academy" id="academy" value="${sessionScope.user.academy}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">层次</label>
					    		<div class="col-sm-8">
					    			<c:if test="${sessionScope.user.level == '0'}">
						 			<select class = "form-control" name="level" id="level">
						 				<option value="0" selected>本科生</option>
						 				<option value="1">研究生</option>
						 			</select>
						 			</c:if>
						 			<c:if test="${sessionScope.user.level == '1'}">
						 			<select class = "form-control" name="level" id="level">
						 				<option value="0">本科生</option>
						 				<option value="1" selected>研究生</option>
						 			</select>
						 			</c:if>
					    		</div>
				    		</div>
				    	</div>
				   	</div>
				   	<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">性别</label>
					    		<div class="col-sm-8">
					    			<c:if test="${sessionScope.user.sex == '0'}">
					    			<label><input type="radio" name="sex" value="0" checked>男</label>&nbsp;&nbsp;
					    			<label><input type="radio" name="sex"value="1">女</label>
					    			</c:if>
					    			<c:if test="${sessionScope.user.sex == '1'}">
					    			<label><input type="radio" name="sex" value="0">男</label>&nbsp;&nbsp;
					    			<label><input type="radio" name="sex"value="1" checked>女</label>
					    			</c:if>
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">专业</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="major" id="major" value="${sessionScope.user.major}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">年级</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="grade" id="grade" value="${sessionScope.user.grade}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">班级</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="className" id="className" value="${sessionScope.user.className}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">民族</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="nation" id="nation" value="${sessionScope.user.nation}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">政治面貌</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="polity" id="polity" value="${sessionScope.user.polity}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">号码</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="telephone" id="telephone" value="${sessionScope.user.telephone}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">短号</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="cornet" id="cornet" value="${sessionScope.user.cornet}">
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">QQ</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="qq" id="qq" value="${sessionScope.user.qq}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">邮箱</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="email" id="email" value="${sessionScope.user.email}">
					    		</div>
					    	</div>
					    </div>
					</div>	
					<div class="row">
						<div class="col-xs-6">				    
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">宿舍</label>
					    		<div class="col-sm-8">
					    			<input class = "form-control" type="text" name="dormitory" id="dormitory" value="${sessionScope.user.dormitory}">
					    		</div>
					    	</div>
					    </div>
					    <div class="col-xs-6">
					    	<div class="form-group">
					    		<label class="col-sm-2 control-label">技能标签</label>
					    		<div class="col-sm-8">
					    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
					    			<button class="btn btn-info btn-sm" onclick="showTagDiaog()">添加标签</button>
					    		</div>
					    	</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-xs-6">	
					    	<div class="form-group">
					    		<label class="col-sm-3 control-label">简介</label>
					    		<div class="col-sm-8">
					    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:80px;vertical-align:top;resize:none;">${sessionScope.user.description}</textarea>
					    		</div>
					    	</div>
				    	</div>
				    </div>
				    <div class="row">
				    	<div class="form-group">
				    		<div class="col-sm-2 col-sm-offset-5">
				    		<a class="btn btn-info" href="javascript:void(0);" onclick="student_alter()" style="font-size: 13px;">提交修改</a>&nbsp;&nbsp;&nbsp;
				    		</div>
				    	</div>
				    </div>
			    	</div>
			    </div>
			</section>
		</section>
	</section>
	
	<div class="modal fade" id="confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>添加标签</h5>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label>添加更多标签</label>
	    		<input class = "form-control" type="text" id="search" name="search" placeholder="搜索标签" onKeyPress="enterSearchTag(event)">
	    		<div style="margin-top: 3px;" id="tag-list2" class="tag-list2"></div>
	    	</div>
		    <div class="form-group">
		    	<label>已有标签</label>
		    	<div id="tag-list3" class="tag-list3"></div>
		    </div>
			<div style="width: 100%; height: 30px; margin-top: 50px; text-align: center;" >
			<button class="btn btn-info" onclick="tag_confirm()" data-dismiss="modal">确定</button>
			<button class="btn" data-dismiss="modal" onclick="tag_cancel()">取消</button>
			</div>
			<div style="clear:both"></div>
		</div>
		</div>
		</div>
	</div>

<script src="plugins/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/sidebar//sidebar-menu.js"  type="text/javascript"></script>
<script src="js/tag/tag_add.js"  type="text/javascript"></script>
<script>
	$.sidebarMenu($('.sidebar-menu'));
	
	$(function(){
		$.ajax({
			type : 'post',
			url : 'str_getByStuLoginId.action',
			data : {
				loginId : '${sessionScope.login.id}'
			},
			dataType : 'json',
			success : function(data) {
				for(var i=0; i<data.length; i++) {
					addTag(data[i].tag, "tag-list");
				}
			}
		});
	});
	
	function student_alter() {
		var tags = [];
		var taglist = document.getElementById("tag-list");
		for(var i=0; i<6; i++) {   //因为最多六个标签
			if(i < taglist.childNodes.length) {
				tags.push(taglist.childNodes[i].id);
			} else {
				tags.push("");
			}
		} 
		$.ajax({
			type: 'post',
			url: 'student_alter.action',
			data: {
				id : '${sessionScope.user.id}',
				loginId : '${sessionScope.login.id}',
				academy : $('#academy').val(),
				level : $('#level').val(),
				sex : $("input[name='sex']:checked").val(),
				major : $('#major').val(),
				grade : $('#grade').val(),
				className : $('#className').val(),
				nation : $('#nation').val(),
				polity : $('#polity').val(),
				telephone : $('#telephone').val(),
				cornet : $('#cornet').val(),
				qq : $('#qq').val(),
				email : $('#email').val(),
				dormitory : $('#dormitory').val(),
				description : $('#description').val(),
				tag1 : tags[0],
				tag2 : tags[1],
				tag3 : tags[2],
				tag4 : tags[3],
				tag5 : tags[4],
				tag6 : tags[5]
			},
			success: function() {
				window.location.href="user/student_info.jsp";
			},
			error: function() {
				window.location.href="user/student_info.jsp";
			}
		});
	}
</script>

</body>
</html>