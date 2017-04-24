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
	    			<select class="form-control" name="level" id="level">
	    			<c:choose>
	    				<c:when test="${competition.level == 1}">
		   					<option value="1" selected>国家级</option>
		   					<option value="2">省级</option>
		   					<option value="3">市级</option>
		   					<option value="4">校级</option>
		   					<option value="5">院级</option>
	    				</c:when>
	    				<c:when test="${competition.level == 2}">
	    					<option value="1">国家级</option>
		   					<option value="2" selected>省级</option>
		   					<option value="3">市级</option>
		   					<option value="4">校级</option>
		   					<option value="5">院级</option>
	    				</c:when>
	    				<c:when test="${competition.level == 3}">
	    					<option value="1">国家级</option>
		   					<option value="2">省级</option>
		   					<option value="3" selected>市级</option>
		   					<option value="4">校级</option>
		   					<option value="5">院级</option>
	    				</c:when>
	    				<c:when test="${competition.level == 4}">
	    					<option value="1">国家级</option>
		   					<option value="2">省级</option>
		   					<option value="3">市级</option>
		   					<option value="4" selected>校级</option>
		   					<option value="5">院级</option>
	    				</c:when>
	    				<c:when test="${competition.level == 5}">
	    					<option value="1">国家级</option>
		   					<option value="2">省级</option>
		   					<option value="3">市级</option>
		   					<option value="4">校级</option>
		   					<option value="5" selected>院级</option>
	    				</c:when>
	    			</c:choose>
	    			</select>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">获奖等级</label>
	    		<div class="col-sm-4">
	    			<select class="form-control" name="rank" id="rank">
	    			<c:choose>
	    				<c:when test="${competition.rank == 0}">
	    					<option value="0" selected>特等奖</option>
		   					<option value="1">一等奖</option>
		   					<option value="2">二等奖</option>
		   					<option value="3">三等奖</option>
		   					<option value="4">成功参赛奖</option>
	    				</c:when>
	    				<c:when test="${competition.rank == 1}">
	    					<option value="0">特等奖</option>
		   					<option value="1" selected>一等奖</option>
		   					<option value="2">二等奖</option>
		   					<option value="3">三等奖</option>
		   					<option value="4">成功参赛奖</option>
	    				</c:when>
	    				<c:when test="${competition.rank == 2}">
	    					<option value="0">特等奖</option>
		   					<option value="1">一等奖</option>
		   					<option value="2" selected>二等奖</option>
		   					<option value="3">三等奖</option>
		   					<option value="4">成功参赛奖</option>
	    				</c:when>
	    				<c:when test="${competition.rank == 3}">
	    					<option value="0">特等奖</option>
		   					<option value="1">一等奖</option>
		   					<option value="2">二等奖</option>
		   					<option value="3" selected>三等奖</option>
		   					<option value="4">成功参赛奖</option>
	    				</c:when>
	    				<c:when test="${competition.rank == 4}">
	    					<option value="0">特等奖</option>
		   					<option value="1">一等奖</option>
		   					<option value="2">二等奖</option>
		   					<option value="3">三等奖</option>
		   					<option value="4" selected>成功参赛奖</option>
	    				</c:when>
	    			</c:choose>
	    			</select>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员1学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member1}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member1})">查看</button>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员2学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member2}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member2})">查看</button>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员3学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member3}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member3})">查看</button>
	    	</div>
	    	<c:if test="${competition.member4 != '' && competition.member4 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员4学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member4}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member4})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member5 != '' && competition.member5 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员5学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member5}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member5})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member6 != '' && competition.member6 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员6学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member6}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member6})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member7 != '' && competition.member7 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员7学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member7}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member7})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member8 != '' && competition.member8 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员8学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member8}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member8})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member9 != '' && competition.member9 != null}">
	    	<div class="form-group">
	    		<label class="col-sm-4 control-label">队员9学号</label>
	    		<div class="col-sm-4">
	    			<input class = "form-control" type="text" name="member" id="member1" value="${competition.member9}">
	    		</div>
	    		<button class="btn btn-info btn-sm" onclick="student_info(${competition.member9})">查看</button>
	    	</div>
	    	</c:if>
	    	<c:if test="${competition.member10 != '' && competition.member10 != null}">
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
	    				<label><input type="radio" name="term" value="1"  checked>上学期</label>&nbsp;&nbsp;
	    				<label><input type="radio" name="term"value="2">下学期</label>
	    			</c:if>
	    			<c:if test="${competition.term == 2}">
	    				<label><input type="radio" name="term" value="1">上学期</label>&nbsp;&nbsp;
	    				<label><input type="radio" name="term"value="2" checked>下学期</label>
	    			</c:if>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-4 col-sm-offset-5">
	    			<c:if test="${competition.isPass == 1}">
	    				<button class="btn btn-success" onclick="accept(${competition.id})">同意</button>
	    				<button class="btn btn-danger" onclick="refuse(${competition.id})">拒绝</button>
	    			</c:if>
	    			<c:if test="${competition.isPass == 0}">
	    				<a class="btn btn-success" href="content/admin/innovation/competition/competition.jsp" style="font-size: 13px;">确定返回</a>
	    				<button class="btn btn-danger" onclick="remove(${competition.id})">删除记录</button>
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
			url : 'competition_accept.action',
			data : {
				id : id,
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
				period : $('#period').val(),
				applicantId : '${sessionScope.login.id}',
				term : $("input[name='term']:checked").val()
			},
			success : function() {
				window.location.href = "content/admin/innovation/competition/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/competition/apply.jsp";
			}
		});
	}
	
	function refuse(id) {
		$.ajax({
			type : 'post',
			url : 'competition_refuse.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/competition/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/competition/apply.jsp";
			}
		});
	}
	
	function remove(id) {
		$.ajax({
			type : 'post',
			url : 'competition_remove.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/competition/competition.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/competition/competition.jsp";
			}
		});
	}
</script>
</body>
</html>