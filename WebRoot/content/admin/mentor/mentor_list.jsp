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
		<div class="manage-content2">
			<div>
			 	<button class="btn btn-info btn-normal" onclick="allFinish()">一键结束</button>
				<button class="btn btn-info btn-normal" onclick="allClear()">一键清除</button>
	           	<form id="excel" action="gm_exportExcel.action" method="post" style="display: inline-block;" >
					<input type="submit" value="Excel导出" class="btn btn-info btn-normal"/>
	           </form>
           </div>
			<!-- <div style="display: inline-block; float: left;">
			 	<button class="btn btn-info btn-normal" onclick="allFinish()">一键结束</button>
				<button class="btn btn-info btn-normal" onclick="allClear()">一键清除</button>
	           	<form id="excel" action="student_exportExcel.action" method="post" style="display: inline-block;" >
					<input type="button" value="Excel导出" class="btn btn-info btn-normal" onclick="exportExcel()"/>
	           </form>
           </div>
           <div style="display: inline-block; float: right;">
           		<input type="text" name="search_word" id="search_word"  class="search-text" placeholder="请输入组长姓名" onKeyPress="enterSearch(event)"/>
           		<input name="" type="button" value="搜索" class="btn btn-info search-btn" onclick="student_search()"/>
           </div> -->
         
           <br class="clear"/>
		</div>
		
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
	    			<c:if test="${item.status == 0}">
	    			<td>发布中</td>
	    			</c:if>
	    			<c:if test="${item.status == 1}">
	    			<td>已完成</td>
	    			</c:if>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="mentor_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="mentor_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="mentor_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="mentor_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" class="jump-page"/>
	    			<button onclick="mentor_manage_jump()" class="btn btn-info jump-btn">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
	</div>
	
<script type="text/javascript">
	function mentor_manage_jump() {
		var page = $('#manage_page').val();
		mentor_manage(page);
	}
	
	function clickThis(id) {
  		$.ajax({
  			type : 'post',
  			url : 'gm_getById.action',
  			data : {
  				id: id,
  				frequency: 4
  			},
  			dataType : 'html',
  			success : function(data) {
  				$('#manage_content').html(data);
  			} 
  		});
  	}
	
	function allFinish() {
		$.ajax({
			type: 'post',
			url: 'gm_allFinish.action',
			success: function() {
				window.location.href="content/admin/mentor/mentor.jsp";
			}
		});
	}
	
	function allClear() {
		$.ajax({
			type: 'post',
			url: 'gm_allClear.action',
			success: function(data) {
				alert("已删除" + data + "条记录！");
				window.location.href="content/admin/mentor/mentor.jsp";
			}
		});
	}
</script>
</body>
</html>