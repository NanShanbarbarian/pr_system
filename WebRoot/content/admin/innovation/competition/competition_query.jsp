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
</head>

<body>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>序号</th>
			<th>竞赛名称</th>
			<th>竞赛类别</th>
			<th>作品名称</th>
			<th>获奖层次</th>
			<th>获奖等级</th>
			<th>指导老师</th>
			<th>获奖时间</th>
		</tr>
	</thead>
	<tbody>		
		<c:if test="${list == null}">
		<tr>
               <td colspan="10">没有竞赛获奖申请！</td>
          	</tr>
		</c:if>		
		<c:if test="${list != null}">
		<%  int num = (Integer)request.getAttribute("currPage");
  			int i= (num-1)*10; %>	
		<c:forEach items="${list}" var="item"> 
		<tr onclick="clickThis(${item.id})">
			<% i++; %> 
   			<td><%= i %></td>
   			<td>${item.name}</td>
   			<td>
   				<c:choose>
   					<c:when test="${item.category == 0}">专业赛事</c:when>
   					<c:when test="${item.category == 1}">非专业赛事</c:when>
   				</c:choose>
   			</td>
   			<td>${item.workName}</td>
   			<td>
   				<c:choose>
   					<c:when test="${item.level == 1}">国家级</c:when>
   					<c:when test="${item.level == 2}">省级</c:when>
   					<c:when test="${item.level == 3}">市级</c:when>
   					<c:when test="${item.level == 4}">校级</c:when>
   					<c:when test="${item.level == 5}">院级</c:when>
   				</c:choose>
   			</td>
   			<td>
   				<c:choose>
   					<c:when test="${item.rank == 0}">特等奖</c:when>
   					<c:when test="${item.rank == 1}">一等奖</c:when>
   					<c:when test="${item.rank == 2}">二等奖</c:when>
   					<c:when test="${item.rank == 3}">三等奖</c:when>
   					<c:when test="${item.rank == 4}">成功参赛奖</c:when>
   				</c:choose>
   			</td>
   			<td>${item.teacher}</td>
			<td>${item.winningTime}</td>
		</tr>
		</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
           <tr>
               <td colspan="10">
               	共${totalRows}条记录,${currPage}/${totalPage}页
   			<a href="javascript:void(0);" onclick="query_manage(1)" class="link">首页</a>
   			<a href="javascript:void(0);" onclick="query_manage(${currPage-1})" class="link">上一页</a>
   			<a href="javascript:void(0);" onclick="query_manage(${currPage+1})" class="link">下一页</a>
   			<a href="javascript:void(0);" onclick="query_manage(${totalPage})" class="link">末页</a>
   			<input type="text" id="query_page" name="query_page" class="jump-page" />
   			<button onclick="query_mangage_jump()" class="btn btn-info jump-btn">跳转</button>
   			</td>
           </tr>
       </tfoot>
</table>

<script type="text/javascript">
	function query_manage_jump() {
		var page = $('#query_page').val();
		query_manage(page);
	}
</script>
</body>
</html>