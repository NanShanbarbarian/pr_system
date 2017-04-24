<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html">
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
	<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>标签名</th>
					<th>使用次数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">查询没有结果！</td>
	            </tr>
				</c:if>	
				<c:if test="${list != null}">
				<%  int num = (Integer)request.getAttribute("currPage");
    			int i= (num-1)*10; %>	
				<c:forEach items="${list}" var="item"> 
				<tr>
					<% i++; %> 
    				<td><%= i %></td>
	    			<td>${item.name}</td>
	    			<td>${item.num}</td>
	    			<td><a href="javascript:void(0);" onclick="deleteTag(${item.id})">删除</a></td>
	    		</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="tag_search(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="tag_search(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="tag_search(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="tag_search(${totalPage})" class="link">末页</a>
	    			<input type="text" id="search_page" name="search_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="tag_search_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
		
	<script type="text/javascript">
		function tag_search_jump() {
			var page = $("#search_page").val();
			tag_search(page);
		}
	</script>
</body>
</html>