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
	<title>科技创新智能互助平台</title>
	
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
		<c:if test="${competition == null}">系统错误，请重试！</c:if>
		<c:if test="${competition != null}">
		<h4 class="col-sm-offset-5">竞赛信息</h4>
		<hr>
		<div class="form-horizontal" style="margin-top: 50px;">
			<div class="form-group">
	    		<label class="col-sm-4 control-label">竞赛名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="name" id="name" value="${ct.name}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">竞赛类别</label>
	    		<div class="col-sm-4">
	    			<c:if test="${ct.category == 0}">
	    				<input class = "form-control" type="text" name="category" id="category" value="专业赛事">
	    			</c:if>
	    			<c:if test="${ct.category == 1}">
	    				<input class = "form-control" type="text" name="category" id="category" value="非专业赛事">
	    			</c:if>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">作品名称</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="workName" id="workName" value="${competition.workName}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖层次</label>
	    		<div class="col-sm-4">
	    			<c:choose>
	    				<c:when test="${competition.level == 1}">
	    					<input class = "form-control" type="text" name="level" id="level" value="国家级">
	    				</c:when>
	    				<c:when test="${competition.level == 2}">
	    					<input class = "form-control" type="text" name="level" id="level" value="省级">
	    				</c:when>
	    				<c:when test="${competition.level == 3}">
	    					<input class = "form-control" type="text" name="level" id="level" value="市级">
	    				</c:when>
	    				<c:when test="${competition.level == 4}">
	    					<input class = "form-control" type="text" name="level" id="level" value="校级">
	    				</c:when>
	    				<c:when test="${competition.level == 5}">
	    					<input class = "form-control" type="text" name="level" id="level" value="院级">
	    				</c:when>
	    			</c:choose>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖等级</label>
	    		<div class="col-sm-4">
	    			<c:choose>
	    				<c:when test="${competition.rank == 0}">
	    					<input class = "form-control" type="text" name="rank" id="rank" value="特等奖">
	    				</c:when>
	    				<c:when test="${competition.rank == 1}">
	    					<input class = "form-control" type="text" name="rank" id="rank" value="一等奖">
	    				</c:when>
	    				<c:when test="${competition.rank == 2}">
	    					<input class = "form-control" type="text" name="rank" id="rank" value="二等奖">
	    				</c:when>
	    				<c:when test="${competition.rank == 3}">
	    					<input class = "form-control" type="text" name="rank" id="rank" value="三等奖">
	    				</c:when>
	    				<c:when test="${competition.rank == 4}">
	    					<input class = "form-control" type="text" name="rank" id="rank" value="成功参赛奖">
	    				</c:when>
	    			</c:choose>
	    		</div>
	    	</div>
	    	<c:if test="${competition.member1 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员1学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member1}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member1})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member2 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员2学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member2}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member2})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member3 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员3学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member3}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member3})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member4 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员4学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member4}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member4})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member5 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员5学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member5}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member5})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member6 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员6学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member6}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member6})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member7 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员7学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member7}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member7})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member8 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员8学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member8}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member8})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member9 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员9学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member9}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member9})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member10 != ''}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员10学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member10}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member10})">查看</button>
	    	</div>
	    	</c:if>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">指导老师</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="teacher" id="teacher" value="${competition.teacher}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖时间</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="winning_time" id="winning_time" value="${competition.winningTime}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖学年</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="period" id="period" value="${competition.period}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖学期</label>
	    		<div class="col-sm-4">
	    			<c:if test="${competition.term == 1}">
	    				<input class = "form-control" type="text" name="term" id="term" value="上学期">
	    			</c:if>
	    			<c:if test="${competition.term == 2}">
	    				<input class = "form-control" type="text" name="term" id="term" value="下学期">
	    			</c:if>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5">
	    			<a class="btn btn-info" href="content/innovation/competition/history.jsp" style="font-size: 13px;">确定返回</a>
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
		    			<input class = "form-control" type="text" name="number" id="number">
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
					$('#number').val(number);
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