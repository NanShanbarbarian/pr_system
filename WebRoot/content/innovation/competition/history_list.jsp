<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			width: 70%;
			margin-left: 20px;
			margin-top: 2px;
		}
		.operate {
			float: right;
			width: 25%;
			padding-left: 10%;
			margin-top: 20px;
		}
		
		a {
			text-decoration: none;
		}
		a : hover {
			text-decoration: none;
		}
	</style>
</head>

<body>
	<div class="main-content2">
	<h4 class="col-sm-offset-5">竞赛获奖记录</h4>
	<hr>
	<div class="subcontainer" id="subcontainer">
  			<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有竞赛获奖记录！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li  onclick="competition_info(${item.id})">
				<div class="content">
					<div class="subcontent">
						<h5>${item.name}</h5>	
						<h6>
							<c:choose>
								<c:when test="${item.level == 1}">国家级</c:when>
								<c:when test="${item.level == 2}">省级</c:when>
								<c:when test="${item.level == 3}">市级</c:when>
								<c:when test="${item.level == 4}">校级</c:when>
								<c:when test="${item.level == 5}">院级</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${item.rank == 0}">特等奖</c:when>
								<c:when test="${item.rank == 1}">一等奖</c:when>
								<c:when test="${item.rank == 2}">二等奖</c:when>
								<c:when test="${item.rank == 3}">三等奖</c:when>
								<c:when test="${item.rank == 4}">成功参赛奖</c:when>
							</c:choose>
						</h6>
					</div>
					<div class="operate">
						<c:choose>
							<c:when test="${item.status == 0}">
								<button class="btn btn-success btn-sm">已验证</button>
							</c:when>
							<c:when test="${item.status == 1}">
								<button class="btn btn-info btn-sm">申请中</button>
							</c:when>
							<c:when test="${item.status == 2}">
								<button class="btn btn-danger btn-sm">已拒绝</button>
							</c:when>
						</c:choose>
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
  	
  	<script type="text/javascript">
  	
	  	function competition_info(id) {
	  		$.ajax({
	  			type : 'post',
	  			url : 'competition_getById.action',
	  			data : {
	  				id : id/* ,
	  				frequency : 2  */
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#main-content').html(data);
	  			} 
	  		});
	  	}
  	</script>
</body>
</html>