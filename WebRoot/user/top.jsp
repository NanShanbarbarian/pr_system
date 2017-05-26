<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-head">
	<div class="dropdown user-nav">
	  <button class="btn btn-default dropdown-toggle none-border" type="button" data-toggle="dropdown">
	     ${sessionScope.user.name}
	    <span class="caret"></span>
	  </button>
	  <c:if test="${sessionScope.login.role != '3'}">
		  <ul class="dropdown-menu" id="dropdown-width">
		    <li><a href="user/teacher_info.jsp">个人资料</a></li>
		    <li class="divider"></li>
		    <li><a href="login_out.action">注销</a></li>
		  </ul>
	  </c:if>
	  <c:if test="${sessionScope.login.role == '3'}">
	  	<ul class="dropdown-menu" id="dropdown-width">
		    <li><a href="user/student_info.jsp">个人资料</a></li>
		    <li class="divider"></li>
		    <li><a href="login_out.action">注销</a></li>
		  </ul>
	  </c:if>
	</div> 
</header>