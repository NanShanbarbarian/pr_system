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
				<th></th>
				<th>姓名</th>
				<th>工号</th>
				<th>性别</th>
				<th>学院</th>
				<th>层次</th>
				<th>专业</th>
				<th>班级</th>
				<th>号码</th>
				<th>邮箱</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${list == null}">
				<tr>
                <td colspan="10">没有项目开发者！</td>
            </tr>
			</c:if>		
			<c:if test="${list != null}">
			<c:forEach items="${list}" var="item"> 
			<tr>
    			<td><input type="checkbox" name="id" value="${item.loginId}"/></td>
    			<td onclick="clickThis(${item.loginId})">${item.name}</td>
    			<td onclick="clickThis(${item.loginId})">${item.number}</td>
    			<c:if test="${item.sex == '0'}">
    				<td onclick="clickThis(${item.loginId})">男</td>
    			</c:if>
    			<c:if test="${item.sex == '1'}">
    				<td onclick="clickThis(${item.loginId})">女</td>
    			</c:if>
    			<td onclick="clickThis(${item.loginId})">${item.academy}</td>
    			<c:if test="${item.level == '0'}">
    				<td onclick="clickThis(${item.loginId})">本科生</td>
    			</c:if>
    			<c:if test="${item.level == '1'}">
    				<td onclick="clickThis(${item.loginId})">研究生</td>
    			</c:if>
    			<td onclick="clickThis(${item.loginId})">${item.major}</td>
    			<td onclick="clickThis(${item.loginId})">${item.className}</td>
    			<td onclick="clickThis(${item.loginId})">${item.telephone}</td>
    			<td onclick="clickThis(${item.loginId})">${item.email}</td>
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
    			<input type="text" id="query_page" name="query_page" class="jump-page"/>
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