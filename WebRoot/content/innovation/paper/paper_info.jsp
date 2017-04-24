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
		
		.form-group label,option {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>
</head>

<body>
	<div class="main-content2">
		<c:if test="${paper == null}">系统错误，请重试！</c:if>
		<c:if test="${paper != null}">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">学院</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="college" id="college" value="${paper.college}">
	    		</div>
	    	</div>
			<div class="form-group">
	    		<label class="col-sm-4 control-label">论文名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name" value="${paper.name}">
	    		</div>
	    	</div>
		    <div class="form-group">
	    		<label class="col-sm-4 control-label">期刊名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="periodical" id="periodical" value="${paper.periodical}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">期刊号(ISSN)</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="number" id="number" value="${paper.number}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">期刊等级</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="level" id="level" value="${paper.level}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">发表时间</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="time" id="time" value="${paper.time}">
	    		</div>
	    	</div>
	    	
	    	<div id="member">
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员1学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member1}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member1})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员2学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member2" value="${paper.member2}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member2})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">队员3学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member3" value="${paper.member3}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member3})">查看</button>
	    		</div>
	    		<c:if test="${paper.member4 != '' && paper.member4 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员4学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member4}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member4})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member5 != '' && paper.member5 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员5学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member5}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member5})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member6 != '' && paper.member6 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员6学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member6}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member6})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member7 != '' && paper.member7 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员7学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member7}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member7})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member8 != '' && paper.member8 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员8学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member8}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member8})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member9 != '' && paper.member9 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员9学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member9}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member9})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${paper.member10 != '' && paper.member10 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">队员10学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${paper.member10}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${paper.member10})">查看</button>
		    	</div>
		    	</c:if>
	    	</div>
	    	
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">指导老师</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teacher" id="teacher" value="${paper.teacher}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者姓名</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_name" id="author_name" value="${paper.authorName}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_number" id="author_number" value="${paper.authorNumber}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">第一作者电话</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="author_telephone" id="author_telephone" value="${paper.authorTelephone}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5" style="margin-top: 30px;">
	    			<button class="btn btn-info" onclick="paper_history()">确定返回</button>
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
	<script type="text/javascript" src="plugins/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script type="text/javascript">
		function paper_history() {
			window.location.href = "content/innovation/paper/history.jsp";
		}
		
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
	</script>
</body>
</html>