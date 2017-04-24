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
	<link rel="stylesheet" href="css/table.css">
	<title>科技创新智能互助平台</title>
	
</head>

<body>
	<div class="manage-content">
		<p class="manage_title">推荐项目</p>
		<div style="margin-bottom: 3px;">
			<div style="width:280px; display: inline-block; float: right; ">
				<span style="width: 200px; display: inline-block; vertical-align: middle;">
					<input id="keyword" class="form-control"  type="text" placeholder="请输入项目名" />
				</span>
				<span style="vertical-align: middle;"><button class="btn btn-info" onclick="getDeviceByNum();" style="font-size: 13px; ">查询</button></span>
			</div>
		</div>
		<div id="project_select">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>项目名</th>
					<th>项目发布者</th>
					<th>项目人数</th>
					<th>开发周期</th>
					<th>创建时间</th>
					<th>项目状态</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
					<tr>
	                <td colspan="10">没有推荐项目！</td>
	            </tr>
				</c:if>		
				<c:if test="${list != null}">
				<c:forEach items="${list}" var="item"> 
				<%  int num = (Integer)request.getAttribute("currPage");
    			int i= (num-1)*10; %>	
				<tr>
					<% i++; %> 
    				<td onclick="clickThis(${item.id}, ${item.isApply})"><%= i %></td>
	    			<td onclick="clickThis(${item.id}, ${item.isApply})">${item.name}</td>
	    			<td onclick="clickThis(${item.id}, ${item.isApply})">${item.teaName}</td>
	    			<td onclick="clickThis(${item.id}, ${item.isApply})">${item.num}</td>
	    			<td onclick="clickThis(${item.id}, ${item.isApply})">${item.period}</td>
	    			<td onclick="clickThis(${item.id}, ${item.isApply})">${item.createtime}</td>
	    			<c:if test="${item.isApply == 0}">
	    				<td onclick="clickThis(${item.id}, ${item.isApply})">发布中</td>
	    			</c:if>
	    			<c:if test="${item.isApply == 1}">
	    				<td onclick="clickThis(${item.id}, ${item.isApply})">申请中</td>
	    			</c:if>
	    			<c:if test="${item.isApply == 2}">
	    				<td onclick="clickThis(${item.id}, ${item.isApply})">申请成功</td>
	    			</c:if>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="recommend_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="recommend_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="recommend_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="recommend_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="recommend_page" name="recommend_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="project_recommend_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
		</div>
	</div>
	
<script type="text/javascript">
	function project_recommend_jump() {
		var page = $("#recommend_page").val();
		recommend_manage(page);
	}
	
	function clickThis(projectId, isApply) {
		$.ajax({
			type : 'post',
			url : 'content/student/project/project_apply.jsp',
			data : {
				id : projectId,
				isApply : isApply
			},
			dataType : 'html',
			success : function(data) {
				$("#manage_content").html(data);
			}
		});
	}
	
</script>
</body>
</html>