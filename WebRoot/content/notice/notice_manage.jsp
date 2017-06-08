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
		.table thead tr th{ 
			text-align: center;
			background-color:#F0F0F0;
			font-family: '微软雅黑'; 
			font-weight: lighter; 
			font-size: 14px;
			color: #444444;
		}
		.table tbody tr td{ 
			text-align: center;
			font-family: '微软雅黑'; 
			font-weight: lighter; 
			font-size: 12px;
			color: #666666;
		}
		
		.manage_content {
			width: 90%; 
			font-family: '微软雅黑'; 
			font-size: 14px;
			font-weight: lighter;
			margin: 30px auto;
			padding: 60px 20px 80px 20px;
			background-color: #fff;
		}
		
		.manage_title {
			font-size: 16px;
			font-weight: normal;
			color: #444444;
		}
		
		.link {
			border-bottom-color: #CFCFCF;
			font-family: '微软雅黑';
			font-weight: lighter;
			font-size: 14px;
			color: #444444 !important;
		}
		
		.link:hover {
			text-decoration: underline;
			font-family: '微软雅黑';
			font-weight: lighter;
			font-size: 14px;
			color: #444444 !important;
		}
	</style>
</head>

<body>
	<div class="manage_content">
		<c:if test="${sessionScope.login.role == '0' || sessionScope.login.role =='1'}">
		<div style="margin-bottom: 3px;">
			<button class="btn btn-info" onclick="notice_publish()" style="font-size: 13px;">发布公告</button>
		</div>
		</c:if>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>发布人</th>
					<th>标题</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">没有系统公告！</td>
	           	</tr>
				</c:if>		
				<c:if test="${list != null}">
				<c:forEach items="${list}" var="item"> 
				<tr>
	    			<td>${item.create_user}</td>
	    			<td>${item.title}</td>
	    			<td>${item.createtime}</td>
	    			<td>
						<button class="btn btn-info btn-xs" onclick="notice_getById(${item.id})">查看</button>
						<c:if test="${sessionScope.login.role == '0' || sessionScope.login.role =='1'}">
						<button class="btn btn-danger btn-xs" onclick="notice_delete(${item.id})">删除</button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="notice_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="notice_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="notice_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="notice_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="notice_mangage_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
	</div>
	
<script type="text/javascript">
	function notice_mangage_jump() {
		var page = $("#manage_page").val();
		notice_manage(page);
	}

	function notice_publish() {
		$.ajax({
			type : 'post',
			url : 'content/notice/notice_save.jsp',
			dataType : 'html',
			success : function(data) {
				$('#manage_content').html(data);
			},
			error : function(data) {
				window.location.href = "content/notice/notice.jsp";
			}
		});
	}
	
	function notice_getById(id) {
		$.ajax({
			type : 'post',
			url : 'notice_getById.action?id=' + id,
			dataType : 'html',
			success : function(data) {
				$('#manage_content').html(data); 
			},
			error : function(data) {
				window.location.href = "content/notice/notice_info.jsp";
			} 
		});
	}
	
	function notice_delete(id) {
		$.ajax({
			type : 'post',
			url : 'notice_delete.action?id=' + id,
			dataType : 'html',
			success : function(data) {
				window.location.href = "content/notice/notice.jsp";
			},
			error : function(data) {
				window.location.href = "content/notice/notice.jsp";
			}
		});
	}
	
</script>
</body>
</html>