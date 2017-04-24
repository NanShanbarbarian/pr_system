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
			min-height: 800px;
		}
		.subcontainer {
			width:60%;
			margin:50px auto;
			overflow: hidden;
		}
		.subcontainer ul { 
			list-style:none;
			padding:0;
			margin:0px; 
		}
		.subcontainer ul li { 
			margin-top:2px; 
		}
		.content { 
			width:98%;
			border-bottom: #DCDCDC 1px solid;
			clear:both; 
		}
		.subcontent {
			float: left;
			width: 80%;
			padding-left: 20px;
			margin-top: 2px;
		}
		.operate {
			float: right;
			width: 20%;
			padding-left: 25px;
			margin-top: 20px;
		}
	</style>
</head>

<body>
	<div class="main-content2">
	<ul class="nav nav-tabs col-sm-offset-1" id="head_content2">
	  <li><a href="javascript:void(0);" onclick="project_view(${param.id})">项目简介</a></li>
	  <li><a href="javascript:void(0);" onclick="student_selected(${param.id})">开发人员</a></li>
	  <li class="active"><a href="javascript:void(0);" onclick="student_evaluate(${param.id})">评价模块</a></li>
	</ul>
	<div class="subcontainer" id="subcontainer">
  			<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有申请人员！</li></c:when>
    			<c:otherwise>
    			<input type="hidden" name="stuLoginId" id="stuLogin.Id">
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content">
					<div class="subcontent" onclick="student_info(${item.loginId})">
						<h5>${item.name}</h5>	
						<h6>${item.className}</h6>
					</div>
					<div class="operate">
						<c:if test="${item.stuRemark == null}">
							<a class="btn btn-info" href="javascript:void(0);" onclick="evaluate_confirm(${item.loginId});" >评价</a>
						</c:if>
						<c:if test="${item.stuRemark != null}">
							<a class="btn btn-info" href="javascript:void(0);" onclick="evaluate_info(${item.stuScore}, '${item.stuRemark}');" >已评价</a>
						</c:if>
					</div>	
					<div style="clear:both;"></div>		
				</div>		
				</li>
				</c:forEach>
    			</c:otherwise>
    		</c:choose>        					
		</ul>
  		</div>
  	</div>
  	
  	<div class="modal fade" id="evaluate_confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>评价框</h5>
		</div>
		<div class="modal-body"  style="padding: 30px 10px 30px 10px;">
			<input type="hidden" name="id" id="id">
			<div class="form-horizontal">
				<div class="form-group">
		    		<label class="col-sm-2 control-label">评分</label>
		    		<div class="col-sm-8">
		    			<input class = "form-control" type="text" name="score" id="score">
    				</div>
    			</div>
    			<div class="form-group">
		    		<label class="col-sm-2 control-label">评语</label>
		    		<div class="col-sm-8">
		    			<textarea class = "form-control" name="remark" id="remark" style="width:100%; height:200px;vertical-align:top;resize:none;"></textarea>
    				</div>
    			</div>
    			<div class="form-group">
		    		<div class="col-sm-4 col-sm-offset-5" id="isEvaluate">
    				</div>
    			</div>
			</div>
		</div>
		</div>
		</div>
	</div>
  	<script type="text/javascript">
  	
	  	function student_info(loginId) {
	  		$.ajax({
	  			type : 'post',
	  			url : 'student_getById.action',
	  			data : {
	  				loginId : loginId,
	  				frequency : 2 
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#subcontainer').html(data);
	  			} 
	  		});
	  	}
  	
	  	function evaluate_confirm(id) {
	  		$('#isEvaluate').append($("<button class='btn btn-info' onclick='sendRemark()'>确定评价</button>"));
	  		$('#evaluate_confirm').modal();
	  		$('#id').val(id);
	  	}
	  	
	  	function sendRemark() {
	  		$.ajax({
	  			type : 'post',
	  			url : 'upr_remarkByTeacher.action',
	  			data : {
	  				projectId : '${param.id}',
	  				stuLoginId : $('#id').val(),
	  				stuRemark : $('#remark').val(),
	  				stuScore : $('#score').val() 
	  			},
	  			success : function() {
	  				$('#evaluate_confirm').modal('hide');
	  			}
	  		});
	  	}
	  	
	  	function evaluate_info(score, remark) {
	  		$('#score').val(score);
	  		$('#remark').val(remark);
	  		$('#evaluate_confirm').modal();
	  	}
  		
  	</script>
</body>
</html>