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
	<h4 class="col-sm-offset-5">发布记录</h4>
	<hr>
	<div class="subcontainer" id="subcontainer">
  		<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有优才导师计划发布记录！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content" onclick="clickThis(${item.id})">
					<div>
						<h5 style="overflow: hidden; width: 80%;height: 25px; line-height: 25px; float:left;">${item.leader}${item.member}</h5>	
						<h6 style="width: 20%; float: right;">${item.publishTime}</h6>						
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
	  	function clickThis(id) {
	  		$.ajax({
	  			type : 'post',
	  			url : 'gm_getById.action',
	  			data : {
	  				id: id,
	  				frequency: 1
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#manage_content').html(data);
	  			} 
	  		});
	  	}
  	</script>
</body>
</html>