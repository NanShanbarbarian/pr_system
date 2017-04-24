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
	<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" />
	<title>科技创新智能互助平台</title>
	
</head>

<body>
	<div class="manage-content">
		<p class="manage-title">申请列表</p>
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
					<th>操作</th>
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
					<td>
						<button class="btn btn-success btn-xs" onclick="accept(${item.id})">同意</button>
						<button class="btn btn-danger btn-xs" onclick="refuse(${item.id})">拒绝</button>
					</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="apply_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="apply_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="apply_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="apply_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" class="jump-page" />
	    			<button onclick="apply_mangage_jump()" class="btn btn-info jump-btn">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
		</div>
	
<script type="text/javascript">
	function apply_mangage_jump() {
		var page = $("#manage_page").val();
		apply_manage(page);
	}
	
	function clickThis(id) {
		$.ajax({
			type : 'post',
			url : 'competition_getById.action',
			data : {
				id : id,
				frequency : 2
			},
			dataType : 'html',
			success : function(data) {
				$("#apply_content").html(data);
			}
		});
	}
	
	function accept(id) {
		$.ajax({
			type : 'post',
			url : 'competition_accept.action',
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
</script>
</body>
</html>