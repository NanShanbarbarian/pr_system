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
	<title>科技创新智能互助平台</title>
	
</head>

<body>
	<div>
	<h4 class="col-sm-offset-5">申请人员</h4>
	<hr>
	<div class="subcontainer" id="subcontainer">
  			<ul>	
  			<c:choose>
    			<c:when test="${list == null}"><li>没有申请人员！</li></c:when>
    			<c:otherwise>
    			<c:forEach items="${list}" var="item">   
				<li>
				<div class="content">
					<div class="subcontent" onclick="student_info(${item.loginId})" style="width: 60%">
						<h5>${item.name}</h5>	
						<h6>${item.className}</h6>
					</div>
					<div class="operate" style="width: 35%;">
					<a id="accept" class="btn btn-success btn-sm" href="javascript:void(0);" onclick="msr_accept(${item.loginId})" >添加</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-sm" href="javascript:void(0);" onclick="msr_refuse(${item.loginId})" >拒绝</a>
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
  	
	  	function student_info(loginId) {
	  		$.ajax({
	  			type : 'post',
	  			url : 'student_getById.action',
	  			data : {
	  				loginId : loginId,
	  				frequency : 3,
	  				gmId: '${param.gmId}',
	  				type: '${param.type}',
	  				count: 1
	  			},
	  			dataType : 'html',
	  			success : function(data) {
	  				$('#subcontainer').html(data);
	  			} 
	  		});
	  	}
  	
  		function msr_accept(stuLoginId) {
  			$.ajax({
  				type : 'post',
  				url : 'msr_accept.action',
  				data : {
  					gmId : '${param.gmId}',
  					stuLoginId : stuLoginId,
  					type: '${param.type}'
  				},
  				success : function() {
  					applyStudent('${param.id}');
  				}
  			});
  		}
  		
  		function msr_refuse(stuLoginId) {
  			$.ajax({
  				type : 'post',
  				url : 'msr_refuse.action',
  				data : {
  					gmId : '${param.gmId}',
  					stuLoginId : stuLoginId,
  					type: '${param.type}'
  				},
  				success : function() {
  					applyStudent('${param.id}');
  				}
  			});
  		}
  	</script>
</body>
</html>