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
		<c:if test="${setuppro == null}">系统错误，请重试！</c:if>
		<c:if test="${setuppro != null}">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">项目官方编号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="college" id="college" value="${setuppro.officalNumber}">
	    		</div>
	    	</div>
			<div class="form-group">
	    		<label class="col-sm-4 control-label">项目类别</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name" value="${setuppro.category}">
	    		</div>
	    	</div>
		    <div class="form-group">
	    		<label class="col-sm-4 control-label">项目子类别</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="type" id="type" value="${setuppro.subcategory}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">项目名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="number" id="number" value="${setuppro.name}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">项目层次</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="proLevel" id="proLevel" value="${setuppro.proLevel}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">立项状态</label>
	    		<div class="col-sm-4">
	    			<c:choose>
	    				<c:when test="${setuppro.status == 0}">
	    					<input class = "form-control" type="text" name="status" id="status" value="立项">
	    				</c:when>
	    				<c:when test="${setuppro.status == 1}">
	    					<input class = "form-control" type="text" name="status" id="status" value="结题">
	    				</c:when>
	    			</c:choose>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">学生层次</label>
	    		<div class="col-sm-4">
	    			<c:choose>
	    				<c:when test="${setuppro.stuLevel == 0}">
	    					<input class = "form-control" type="text" name="stuLevel" id="stuLevel" value="本科生">
	    				</c:when>
	    				<c:when test="${setuppro.stuLevel == 1}">
	    					<input class = "form-control" type="text" name="stuLevel" id="stuLevel" value="研究生">
	    				</c:when>
	    			</c:choose>
	    		</div>
	    	</div>
	    	
	    	<div id="member">
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">学员1学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member1}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member1})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">学员2学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member2" value="${setuppro.member2}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member2})">查看</button>
	    		</div>
	    		<div class="form-group">
		    		<label class="col-sm-4 control-label">学员3学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member3" value="${setuppro.member3}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member3})">查看</button>
	    		</div>
	    		<c:if test="${setuppro.member4 != '' && setuppro.member4 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员4学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member4}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member4})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member5 != '' && setuppro.member5 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员5学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member5}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member5})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member6 != '' && setuppro.member6 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员6学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member6}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member6})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member7 != '' && setuppro.member7 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员7学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member7}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member7})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member8 != '' && setuppro.member8 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员8学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member8}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member8})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member9 != '' && setuppro.member9 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员9学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member9}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member9})">查看</button>
		    	</div>
		    	</c:if>
		    	<c:if test="${setuppro.member10 != '' && setuppro.member10 != null}">
		    	<div class="form-group">
		    		<label class="col-sm-4 control-label">学员10学号</label>
		    		<div class="col-sm-4">
		    			<input class = "form-control" type="text" name="member" id="member1" value="${setuppro.member10}">
		    		</div>
		    		<button class="btn btn-info btn-sm" onclick="student_info(${setuppro.member10})">查看</button>
		    	</div>
		    	</c:if>
	    	</div>
	    	
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">指导老师</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teacher" id="teacher" value="${setuppro.teacher}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5" style="margin-top: 30px;">
    				<a class="btn btn-success" href="content/admin/innovation/setuppro/setuppro.jsp" style="font-size: 13px;">确定返回</a>
    				<button class="btn btn-danger" onclick="remove(${setuppro.id})">删除记录</button>
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
	
	function remove(id) {
		$.ajax({
			type : 'post',
			url : 'setuppro_remove.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/setuppro/setuppro.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/setuppro/setuppro.jsp";
			}
		});
	}
	</script>
</body>
</html>