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
	<link rel="stylesheet" href="css/table.css">
	<title>科技创新智能互助平台</title>
</head>

<body>
	<div class="manage-content">
		<h4 class="col-sm-offset-5">优才导师申请</h4>
		<hr>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>组长姓名</th>
					<th>组长职称</th>
					<th>成员姓名</th>
					<th>发布时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
					<tr>
	                <td colspan="10">没有发布记录！</td>
	            </tr>
				</c:if>		
				<c:if test="${list != null}">
				<c:forEach items="${list}" var="item"> 
				<%  int num = (Integer)request.getAttribute("currPage");
    			int i= (num-1)*10; %>	
				<tr onclick="clickThis(${item.id})">
					<% i++; %> 
    				<td><%= i %></td>
	    			<td>${item.leader}</td>
	    			<td>${item.title}</td>
	    			<td>${item.member}</td>
	    			<td>${item.publishTime}</td>
	    			<td>发布中</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="mentor_select(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="mentor_select(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="mentor_select(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="mentor_select(${totalPage})" class="link">末页</a>
	    			<input type="text" id="select_page" name="select_page" class="jump-page"/>
	    			<button onclick="mentor_select_jump()" class="btn btn-info jump-btn">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
	</div>
	
<script type="text/javascript">
	function mentor_select_jump() {
		var page = $('#select_page').val();
		mentor_select(page);
	}
	
	function clickThis(id) {
  		$.ajax({
  			type : 'post',
  			url : 'gm_getById.action',
  			data : {
  				id: id,
  				stuLoginId: '${sessionScope.login.id}',
  				frequency: 2
  			},
  			dataType : 'html',
  			success : function(data) {
  				$('#manage_content').html(data);
  			} 
  		});
  	}
</script>
</body>
</html>