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
			padding: 50px;
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
		
		.form-group label {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>
</head>

<body>
	<div class="manage_content">
		<p class="manage_title">我的项目</p>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>项目名</th>
					<th>项目人数</th>
					<th>开发周期</th>
					<th>创建时间</th>
					<th>项目状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">没有项目发布！</td>
	            </tr>
				</c:if>	
				<c:if test="${list != null}">
				<%  int num = (Integer)request.getAttribute("currPage");
    			int i= (num-1)*10; %>	
				<c:forEach items="${list}" var="item"> 
				<tr>
					<% i++; %> 
    				<td onclick="clickThis(${item.id})"><%= i %></td>
	    			<td onclick="clickThis(${item.id})">${item.name}</td>
	    			<td onclick="clickThis(${item.id})">${item.num}</td>
	    			<td onclick="clickThis(${item.id})">${item.period}</td>
	    			<td onclick="clickThis(${item.id})">${item.createtime}</td>
	    			<c:if test="${item.status == '0'}">
	    				<td onclick="clickThis(${item.id})">发布中</td>
	    			</c:if>
	    			<c:if test="${item.status == '1'}">
	    				<td onclick="clickThis(${item.id})">进行中</td>
	    			</c:if>
	    			<c:if test="${item.status == '2'}">
	    				<td onclick="clickThis(${item.id})">已完成</td>
	    			</c:if>
		    		<td>
						<button class="btn btn-info btn-xs" onclick="project_alter(${item.id})">修改</button>
						<button class="btn btn-danger btn-xs" onclick="project_remove(${item.id})">删除</button>
					</td>
	    		</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="project_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="project_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="project_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="project_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="project_mangage_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
	</div>
	
<script type="text/javascript">
	function project_mangage_jump() {
		var page = $("#manage_page").val();
		project_manage(page);
	}
	
	function clickThis(id) {
		$.ajax({
			type : 'post',
			url : 'content/teacher/project/project_view.jsp',
			data : {
				id : id
			},
			dataType : 'html',
			success : function(data) {
				$('#manage_content').html(data);
			},
			error : function() {
				alert("查看项目失败！");
			}
		});
	}
	
	function project_remove(id) {
		$.ajax({
			type : 'post',
			url : 'project_remove.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/teacher/project/project_my.jsp";
			},
			error : function() {
				window.location.href = "content/teacher/project/project_my.jsp";
			}
		});
	}
	
	function project_alter(id) {
		$.ajax({
			type : 'post',
			url : 'project_getById.action?id=' + id,
			success : function(data) {
				$('#manage_content').html(data);
			},
			error : function() {
				window.location.href = "content/teacher/project/project_my.jsp";
			}
		});
	}
</script>
</body>
</html>