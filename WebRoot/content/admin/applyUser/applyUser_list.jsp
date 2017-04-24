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
	
</head>

<body>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>学号(工号)</th>
				<th>性别</th>
				<th>学院</th>
				<th>层次</th>
				<th>号码</th>
				<th>短号</th>
				<th>QQ</th>
				<th>邮箱</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${list == null}">
			<tr>
                <td colspan="11">没有申请记录！</td>
           	</tr>
			</c:if>		
			<c:if test="${list != null}">
			<%  int num = (Integer)request.getAttribute("currPage");
   			int i= (num-1)*10; %>	
			<c:forEach items="${list}" var="item"> 
			<tr>
				<% i++; %> 
   				<td onclick="clickThis(${item.loginId})"><%= i %></td>
    			<td onclick="clickThis(${item.loginId})">${item.name}</td>
    			<td onclick="clickThis(${item.loginId})">${item.number}</td>
    			<c:if test="${item.sex == '0'}">
    				<td onclick="clickThis(${item.loginId})">男</td>
    			</c:if>
    			<c:if test="${item.sex == '1'}">
    				<td onclick="clickThis(${item.loginId})">女</td>
    			</c:if>
    			<td onclick="clickThis(${item.loginId})">${item.academy}</td>
    			<!-- 项目发布者 -->
    			<c:if test="${item.role == 2}">
	    			<c:if test="${item.level == '0'}">
	    				<td onclick="clickThis(${item.loginId})">教授</td>
	    			</c:if>
	    			<c:if test="${item.level == '1'}">
	    				<td onclick="clickThis(${item.loginId})">副教授</td>
	    			</c:if>
	    			<c:if test="${item.level == '2'}">
	    				<td onclick="clickThis(${item.loginId})">讲师</td>
	    			</c:if>
    			</c:if>
    			<!-- 项目开发者 -->
    			<c:if test="${item.role == 3}">
	    			<c:if test="${item.level == '0'}">
	    				<td onclick="clickThis(${item.loginId})">本科生</td>
	    			</c:if>
	    			<c:if test="${item.level == '1'}">
	    				<td onclick="clickThis(${item.loginId})">研究生</td>
	    			</c:if>
    			</c:if>
    			<td onclick="clickThis(${item.loginId})">${item.telephone}</td>
    			<td onclick="clickThis(${item.loginId})">${item.cornet}</td>
    			<td onclick="clickThis(${item.loginId})">${item.Qq}</td>
    			<td onclick="clickThis(${item.loginId})">${item.email}</td>
    			<td>
					<button class="btn btn-success btn-xs" onclick="accept(${item.loginId})">同意</button>
					<button class="btn btn-danger btn-xs" onclick="refuse(${item.loginId})">拒绝</button>
				</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
            <tr>
                <td colspan="11">
                	共${totalRows}条记录,${currPage}/${totalPage}页
    			<a href="javascript:void(0);" onclick="applyUser_manage(1)" class="link">首页</a>
    			<a href="javascript:void(0);" onclick="applyUser_manage(${currPage-1})" class="link">上一页</a>
    			<a href="javascript:void(0);" onclick="applyUser_manage(${currPage+1})" class="link">下一页</a>
    			<a href="javascript:void(0);" onclick="applyUser_manage(${totalPage})" class="link">末页</a>
    			<input type="text" id="manage_page" name="manage_page" class="jump-page"/>
    			<button onclick="applyUser_mangage_jump()" class="btn btn-info jump-btn">跳转</button>
    			</td>
            </tr>
        </tfoot>
	</table>
	
<script type="text/javascript">
	function applyUser_mangage_jump() {
		var page = $("#manage_page").val();
		admin_manage(page);
	}
	
	function clickThis(loginId) {
		$.ajax({
			type : 'post',
			url : 'login_getById.action',
			data : {
				id : loginId,
			},
			dataType : 'html',
			success : function(data) {
				$("#manage_content").html(data);
			}
		});
	}
	
	function accept(loginId) {
		$.ajax({
			type : 'post',
			url : 'login_accept.action',
			data : {
				id : loginId,
			},
			dataType : 'html',
			success : function(data) {
				alert("验证申请成功！");
				window.location.href = "content/admin/applyUser/applyUser.jsp";
			}
		});
	}
	
	function refuse(loginId) {
		$.ajax({
			type : 'post',
			url : 'login_remove.action',
			data : {
				id : loginId,
			},
			dataType : 'html',
			success : function(data) {
				alert("删除申请成功！");
				window.location.href = "content/admin/applyUser/applyUser.jsp";
			}
		});
	}
</script>
</body>
</html>