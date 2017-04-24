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
	<link rel="stylesheet" href="css/list.css">
	<link rel="stylesheet" href="css/switch/switch.css">
	<title>科技创新智能互助平台</title>
</head>

<body>
	<div class="main-content2">
	<h4 class="col-sm-offset-5">活动管理</h4>
	<hr>
	<div class="subcontainer" id="subcontainer">
  		<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有系统活动记录！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content">
					<div class="subcontent">
						<h5 style="overflow: hidden; width: 100%;height: 40px; line-height: 40px;">${item.name}</h5>	
					</div>
					<div class="operate" style="height: 40px; line-height: 40px;">
						<c:choose>
							<c:when test="${item.isOpen == 0}">
								<label>
								<input type="checkbox" class="switch ios-switch" onchange="changeStatus(${item.id})"/>
								<div><div></div></div>
								</label>
							</c:when>
							<c:when test="${item.isOpen == 1}">
								<label>
								<input type="checkbox" class="switch ios-switch" checked onchange="changeStatus(${item.id})"/>
								<div><div></div></div>
								</label>
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
  		function changeStatus(id) {
  			$.ajax({
  				type: 'post',
  				url: 'am_change.action',
  				data : {
  					id : id
  				}
  			});
  		}
  	</script>
</body>
</html>