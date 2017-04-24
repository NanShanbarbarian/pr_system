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
		<p class="manage_title">项目开发者管理</p>
		<div style="margin-bottom: 3px;">
			<button class="btn btn-info" onclick="teacher_saveAdmin()" style="font-size: 13px;">新增用户</button>
			<button class="btn btn-info" data-toggle="modal" data-toggle="modal" onclick="admin_confirm()" style="font-size: 13px;">批量删除</button>
			<div style="width:280px; display: inline-block; float: right; ">
				<span style="width: 200px; display: inline-block; vertical-align: middle;">
					<input id="keyword" class="form-control"  type="text" placeholder="请输入工号" />
				</span>
				<span style="vertical-align: middle;"><button class="btn btn-info" onclick="getDeviceByNum();" style="font-size: 13px; ">查询</button></span>
			</div>
		</div>
		<div id="device_select">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th>姓名</th>
					<th>工号</th>
					<th>性别</th>
					<th>学院</th>
					<th>层次</th>
					<th>号码</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">没有普通管理员！</td>
	           	</tr>
				</c:if>		
				<c:if test="${list != null}">
				<c:forEach items="${list}" var="item"> 
				<tr>
	    			<td><input type="checkbox" name="id" value="${item.loginId}"/></td>
	    			<td onclick="clickThis(${item.loginId},${item.id})">${item.name}</td>
	    			<td onclick="clickThis(${item.loginId},${item.id})">${item.number}</td>
	    			<c:if test="${item.sex == '0'}">
	    				<td onclick="clickThis(${item.loginId},${item.id})">男</td>
	    			</c:if>
	    			<c:if test="${item.sex == '1'}">
	    				<td onclick="clickThis(${item.loginId},${item.id})">女</td>
	    			</c:if>
	    			<td onclick="clickThis(${item.loginId},${item.id})">${item.academy}</td>
	    			<c:if test="${item.level == '0'}">
	    				<td onclick="clickThis(${item.loginId},${item.id})">教授</td>
	    			</c:if>
	    			<c:if test="${item.level == '1'}">
	    				<td onclick="clickThis(${item.loginId},${item.id})">副教授</td>
	    			</c:if>
	    			<c:if test="${item.level == '2'}">
	    				<td onclick="clickThis(${item.loginId},${item.id})">讲师</td>
	    			</c:if>
	    			<td onclick="clickThis(${item.loginId},${item.id})">${item.telephone}</td>
	    			<td onclick="clickThis(${item.loginId},${item.id})">${item.email}</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="admin_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="admin_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="admin_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="admin_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="teacher_mangage_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
		</div>
	</div>
	
	<div class="modal fade" id="confirm">
		<div class="modal-dialog modal-sm">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>确定框</h5>
		</div>
		<div class="modal-body">
			<div id="content"></div>
			<div style="width: 100%; height: 30px; margin-top: 20px; text-align: center;" >
			<button class="btn btn-info" onclick="admin_deleteMore()" data-dismiss="modal">确定</button>
			<button class="btn" data-dismiss="modal">取消</button>
			</div>
			<div style="clear:both"></div>
		</div>
		</div>
		</div>
	</div>
	
<script type="text/javascript">
	function teacher_mangage_jump() {
		var page = $("#manage_page").val();
		admin_manage(page);
	}
	
	function clickThis(loginId, id) {
		$.ajax({
			type : 'post',
			url : 'teacher_getAdminById.action',
			data : {
				loginId : loginId,
				id : id
			},
			dataType : 'html',
			success : function(data) {
				$("#manage_content").html(data);
			}
		});
	}
	
	function teacher_saveAdmin() {
		$.ajax({
			type : 'post',
			url : 'content/admin/admin_save.jsp',
			dataType : 'html',
			success : function(data) {
				$('#manage_content').html(data);
			},
			error : function(data) {
				window.location.href = "content/admin/admin.jsp";
			}
		});
	}
	
	function admin_confirm() {
		var ids = document.getElementsByName("id");
		var str = new Array();
		for(var i=0; i<ids.length; i++) {
			if(ids[i].checked) {
				str.push(ids[i].value);
			}
		}
		if(str.length > 0) {
			$('#confirm').modal();
			$('#content').children('p').remove();
			$('#content').append("<p>你确定要删除" + str.length +"个用户嘛？</p>");
		} else if(str.length == 0){
			alert("请选中至少一个用户!!!");
		}
	}
	
	function admin_deleteMore() {
		var ids = document.getElementsByName("id");
		var str = new Array();
		for(var i=0; i<ids.length; i++) {
			if(ids[i].checked) {
				str.push(ids[i].value);
			}
		}
	
		$.ajax({
			type: 'post',
			url: 'teacher_deleteMore.action',
			data: {
				str: str.toString()
			},
			success: function(data) {
				window.location.href = "content/admin/admin.jsp";
			}
		});
		
		
	}
</script>
</body>
</html>