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
			width: 65%; 
			font-family: '微软雅黑'; 
			font-size: 14px;
			font-weight: lighter;
			margin-top: 30px;
			margin-left: 50px;
			padding: 50px 50px;
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
		<div style="margin-bottom: 3px;">
			<button class="btn btn-info" onclick="add_confirm()" style="font-size: 13px;">新增标签</button>
			<!-- <button class="btn btn-info" data-toggle="modal" data-toggle="modal" onclick="teacher_confirm()" style="font-size: 13px;">删除标签</button> -->
			<div style="width:280px; display: inline-block; float: right; ">
				<span style="width: 200px; display: inline-block; vertical-align: middle;">
					<input id="keyword" class="form-control"  type="text" placeholder="请输入关键词" onKeyPress="enterSearchTag(event)">
				</span>
				<span style="vertical-align: middle;"><button class="btn btn-info" onclick="searchTag()" style="font-size: 13px; ">查询</button></span>
			</div>
		</div>
		<div id="tag_content">
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
	                <td colspan="10">没有标签！</td>
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
	    			<a href="javascript:void(0);" onclick="tag_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="tag_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="tag_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="tag_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" style="width: 30px; vertical-align: middle;" />
	    			<button onclick="tag_mangage_jump()" class="btn btn-info" style="font-size:13px; padding: 3px; margin: 0px; vertical-align: middle;">跳转</button>
	    			</td>
	            </tr>
	        </tfoot>
		</table>
		</div>
	</div>
	
	<div class="modal fade" id="confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>确定框</h5>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="form-group">
		    		<label class="col-sm-2 control-label">标签名</label>
		    		<div class="col-sm-8">
		    			<input class = "form-control" type="text" name="name" id="name">
		    		</div>
		    	</div>
			</div>
			<div style="width: 100%; height: 30px; margin-top: 20px; text-align: center;" >
			<button class="btn btn-info" onclick="saveTag()" data-dismiss="modal">确定</button>
			<button class="btn" data-dismiss="modal">取消</button>
			</div>
			<div style="clear:both"></div>
		</div>
		</div>
		</div>
	</div>
	
<script type="text/javascript">
	function tag_mangage_jump() {
		var page = $("#manage_page").val();
		tag_manage(page);
	}
	
	function deleteTag(id) {
		$.ajax({
			type : 'post',
			url : 'tag_delete.action',
			data : {
				id : id
			},
			success : function() {
				window.location.href = "content/tag/tag.jsp";
			},
			error : function() {
				window.location.href = "content/tag/tag.jsp";
			}
		});
	}
	
	function saveTag() {
		$.ajax({
			type : 'post',
			url : 'tag_save.action',
			data : {
				name : $('#name').val()
			},
			success : function() {
				window.location.href = "content/tag/tag.jsp";
			},
			error : function() {
				window.location.href = "content/tag/tag.jsp";
			}
		});
	}
	
	function add_confirm() {
		$('#confirm').on('show.bs.modal', function (e) { 
	      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零 
	      $(this).css('display', 'block'); 
	      var modalHeight = $(window).height() / 2 - $(".modal-dialog").height() / 2; 
	      $(this).find('.modal-dialog').css({ 
	        'margin-top': modalHeight 
	      }); 
	    });
		$('#confirm').modal();
	}
	
	function searchTag() {
		tag_search(1);
	}
	
	function tag_search(currPage) {
		var search = $('#keyword').val();
		if(search != "") {
			$.ajax({
				type : 'post',
				url : 'tag_getListOnSearch.action',
				data : {
					name : search,
					currPage : currPage
				},
				dataType : 'html',
				success : function(data) {
					$('#tag_content').html(data);
					$('#keyword').val(search);
				},
				error : function() {
					alert("系统错误，查询失败！");
				}
			});
		} else {
			tag_manage(1);
		}
	}
	
	function enterSearchTag(e) {
		var keyNum;
		if(window.event) {   //IE
			keyNum = e.keyCode;
		} else if(e.which) {   //Netscape|Firefox|Opera
			keyNum = e.which;
		}
		if(keyNum == 13) {
			tag_search(1);
		}
	}
</script>
</body>
</html>