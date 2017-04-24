<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
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
		<c:if test="${patent == null}">系统错误，请重试！</c:if>
		<c:if test="${patent != null}">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">学院</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="college" id="college" value="${patent.college}">
	    		</div>
	    	</div>
			<div class="form-group">
	    		<label class="col-sm-4 control-label">专利名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name" value="${patent.name}">
	    		</div>
	    	</div>
		    <div class="form-group">
	    		<label class="col-sm-4 control-label">专利类型</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="type" id="type" value="${patent.type}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">专利号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="number" id="number" value="${patent.number}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">专利状态</label>
	    		<div class="col-sm-4">
	    			<select class = "form-control" name="status" id="status">
	    				<option value="0">授权</option>
	    			</select>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">授权时间</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="time" id="time" value="${patent.time}">
	    		</div>
	    	</div>
	    	
	    	<div id="member">
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员1学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member1}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member1})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员2学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member2" value="${patent.member2}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member2})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员3学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member3" value="${patent.member3}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member3})">查看</button>
	    		</div>
	    		<c:if test="${patent.member4 != '' && patent.member4 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员4学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member4}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member4})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member5 != '' && patent.member5 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员5学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member5}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member5})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member6 != '' && patent.member6 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员6学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member6}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member6})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member7 != '' && patent.member7 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员7学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member7}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member7})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member8 != '' && patent.member8 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员8学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member8}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member8})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member9 != '' && patent.member9 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员9学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member9}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member9})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${patent.member10 != '' && patent.member10 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员10学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${patent.member10}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${patent.member10})">查看</button>
		    	</div>
		    	</c:if>
	    	</div>
	    	
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">指导老师</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teacher" id="teacher" value="${patent.teacher}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_name" id="author_name" value="${patent.authorName}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_number" id="author_number" value="${patent.authorNumber}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者电话</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_telephone" id="author_telephone" value="${patent.authorTelephone}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5" style="margin-top: 30px;">
	    			<c:if test="${patent.isPass == 1}">
	    				<button class="btn btn-success" onclick="accept(${patent.id})">同意</button>
	    				<button class="btn btn-danger" onclick="refuse(${patent.id})">拒绝</button>
	    			</c:if>
	    			<c:if test="${patent.isPass == 0}">
	    				<a class="btn btn-success" href="content/admin/innovation/patent/patent.jsp" style="font-size: 13px;">确定返回</a>
	    				<button class="btn btn-danger" onclick="remove(${patent.id})">删除记录</button>
	    			</c:if>
	    		</div>
	    	</div>
		</div>
		</c:if>	
	</div>
	
	<div class="modal fade" id="confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>学生信息</h5>
		</div>
		<div class="modal-body"  style="padding: 30px 10px 30px 10px;" id="info_content">
			<div class="form-horizontal">
				<div class="form-group">
		    		<label class="col-sm-4 control-label">姓名</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="peopeleName" id="peopeleName">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-4 control-label">学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="peopleNumber" id="peopleNumber">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-4 control-label">学院</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="academy" id="academy">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-4 control-label">专业</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="major" id="major">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-4 control-label">班级</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="className" id="className">
    				</div>
    			</div>
			</div>
		</div>
		</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	function student_info(number) {
		$.ajax({
			type : 'post',
			url : 'student_getByNumber.action',
			data : {
				number : number
			},
			dataType : 'json',
			success : function(data) {
				
				if(data == 0) {
					alert("没有该学生用户信息！")
				} else {
					$('#peopeleName').val(data.name);
					$('#peopleNumber').val(number);
					$('#academy').val(data.academy);
					$('#major').val(data.major);
					$('#className').val(data.className);
		
					$('#confirm').modal();
				}
			} 
		});
	}
	
	function accept(id) {
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
			url : 'patent_accept.action',
			data : {
				id : id,
				college : $('#college').val(),
				name : $('#name').val(),
				type : $('#type').val(),
				level : $('#level').val(),
				number : $('#number').val(),
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
				time : $('#time').val(),
				authorName : $('#author_name').val(),
				authorNumber : $('#author_number').val(),
				authorTelephone : $('#author_telephone').val()
			},
			success : function() {
				window.location.href = "content/admin/innovation/patent/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/patent/apply.jsp";
			}
		});
	}
	
	function refuse(id) {
		$.ajax({
			type : 'post',
			url : 'patent_refuse.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/patent/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/patent/apply.jsp";
			}
		});
	}
	
	function remove(id) {
		$.ajax({
			type : 'post',
			url : 'patent_remove.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/patent/patent.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/patent/patent.jsp";
			}
		});
	}
	</script>
</body>
</html>