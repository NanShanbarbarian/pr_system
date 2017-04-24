<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<th>项目官方编号</th>
			<th>项目类别</th>
			<th>项目子类别</th>
			<th>项目名称</th>
			<th>项目层次</th>
			<th>项目状态</th>
			<th>学生层次</th>
			<th>指导老师</th>
		</tr>
	</thead>
	<tbody>		
		<c:if test="${list == null}">
		<tr>
               <td colspan="10">没有查询记录！</td>
          	</tr>
		</c:if>		
		<c:if test="${list != null}">
		<%  int num = (Integer)request.getAttribute("currPage");
  			int i= (num-1)*10; %>	
		<c:forEach items="${list}" var="item"> 
		<tr onclick="clickThis(${item.id})">
			<% i++; %> 
   			<td><%= i %></td>
   			<td>${item.officalNumber}</td>
   			<td>${item.category}</td>
   			<td>${item.subcategory}</td>
   			<td>${item.name}</td>
   			<td>${item.proLevel}</td>
   			<td>
   				<c:choose>
   					<c:when test="${item.status == 0}">立项</c:when>
   					<c:when test="${item.status == 1}">结题</c:when>
   				</c:choose>
   			</td>
   			<td>
   				<c:choose>
   					<c:when test="${item.stuLevel == 0}">本科生</c:when>
   					<c:when test="${item.stuLevel == 1}">研究生</c:when>
   				</c:choose>
   			</td>
   			<td>${item.teacher}</td>
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