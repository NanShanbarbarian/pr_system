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

<body>
	<div class="manage-content">
		<p class="manage-title">申请列表</p>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th style="width: 200px;">论文名称</th>
					<th style="width: 200px;">期刊名称</th>
					<th style="width: 200px;">期刊号</th>
					<th>期刊等级</th>
					<th>第一作者</th>
					<th>发表时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">没有期刊论文申请！</td>
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
	    			<td>${item.periodical}</td>
	    			<td>${item.number}</td>
	    			<td>${item.level}</td>
	    			<td>${item.authorName}</td>
	    			<td>${item.time}</td>
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
	    			<input type="text" id="manage_page" name="manage_page" class="jump-page"/>
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
			url : 'paper_getById.action',
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
			url : 'paper_accept.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/paper/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/paper/apply.jsp";
			}
		});
	}
	
	function refuse(id) {
		$.ajax({
			type : 'post',
			url : 'paper_refuse.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/admin/innovation/paper/apply.jsp";
			},
			error : function() {
				window.location.href = "content/admin/innovation/paper/apply.jsp";
			}
		});
	}
</script>
</body>
</html>