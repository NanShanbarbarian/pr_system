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
	<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" />
	<link rel="stylesheet" href="plugins/bootstrap/css/fileinput.min.css">
	<script type="text/javascript" src="plugins/bootstrap/js/fileinput.js"></script>
	<script type="text/javascript" src="plugins/bootstrap/js/fileinput_locale_zh.js"></script>
	<title>科技创新智能互助平台</title>
	
</head>

<body>
	<div class="manage-content">
		<div class="manage-content2">
			<div style="display: inline-block; float: left;">
			 	<button class="btn btn-info btn-normal" onclick="student_save()">新增用户</button>
				<button class="btn btn-info btn-normal" data-toggle="modal" data-toggle="modal" onclick="student_confirm()">批量删除</button>
	           	<form id="excel" action="student_exportExcel.action" method="post" style="display: inline-block;" >
		        	<input type="button" value="Excel导入" class="btn btn-info btn-normal" onclick="showModal()"/>
					<input type="button" value="Excel导出" class="btn btn-info btn-normal" onclick="exportExcel()"/>
	           </form>
           </div>
           <div style="display: inline-block; float: right;">
           		<select class="search-select" name="search_type" id="search_type">
			 	 	<option value="1">学生姓名</option>
			 	 	<option value="2">学生学号</option>
			 	 </select>
           		<input type="text" name="search_word" id="search_word"  class="search-text" value="" onKeyPress="enterSearch(event)"/>
           		<input name="" type="button" value="搜索" class="btn btn-info search-btn" onclick="student_search()"/>
           </div>
         
           <br class="clear"/>
		</div>
		<div id="table_content">
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
	    			<a href="javascript:void(0);" onclick="student_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="student_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="student_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="student_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" class="jump-page"/>
	    			<button onclick="teacher_mangage_jump()" class="btn btn-info jump-btn">跳转</button>
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
			<button class="btn btn-info" onclick="student_deleteMore()" data-dismiss="modal">确定</button>
			<button class="btn" data-dismiss="modal">取消</button>
			</div>
			<div style="clear:both"></div>
		</div>
		</div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmExcel">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>Excel导入框</h5>
		</div>
		<div class="modal-body">
			<input type="hidden" name="fileName" id="fileName" >
			<div class="form-group" style="min-height: 400px; height: auto;">
	    		<div class="col-sm-offset-3 col-sm-6">
				<input type="file" class="file" name="file" id="file" onchange="change(event)" accept=".xls,.xlsx">
				</div>
	    	</div>
		</div>
		</div>
		</div>
	</div>
	
<script type="text/javascript">
	function student_mangage_jump() {
		var page = $("#manage_page").val();
		teacher_manage(page);
	}
	
	function clickThis(loginId) {
		$.ajax({
			type : 'post',
			url : 'student_getById.action',
			data : {
				loginId : loginId,
			},
			dataType : 'html',
			success : function(data) {
				$("#manage_content").html(data);
			}
		});
	}
	
	function student_save() {
		$.ajax({
			type : 'post',
			url : 'content/student/student_save.jsp',
			dataType : 'html',
			success : function(data) {
				$('#manage_content').html(data);
			},
			error : function(data) {
				window.location.href = "content/student/student.jsp";
			}
		});
	}
	
	function student_confirm() {
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
	
	function student_deleteMore() {
		var ids = document.getElementsByName("id");
		var str = new Array();
		for(var i=0; i<ids.length; i++) {
			if(ids[i].checked) {
				str.push(ids[i].value);
			}
		}
	
		$.ajax({
			type: 'post',
			url: 'student_deleteMore.action',
			data: {
				str: str.toString()
			},
			success: function(data) {
				window.location.href = "content/student/student.jsp";
			}
		});
	}
	
	function showModal() {
		$('#confirmExcel').on('show.bs.modal', function (e) { 
	      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零 
	      $(this).css('display', 'block'); 
	      var modalHeight = $(window).height() / 2 - $(".modal-dialog").height() / 2; 
	      $(this).find('.modal-dialog').css({ 
	        'margin-top': modalHeight 
	      }); 
	    });
		
		$('#confirmExcel').modal();
	}
	
	$('#file').fileinput({
		language: 'zh', //设置语言
		uploadUrl: 'student_importExcel.action',
		allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
		showUpload: true, //是否显示上传按钮
		showCaption: true,//是否显示标题
		browseClass: "btn btn-primary", //按钮样式	
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		enctype: 'multipart/form-data',
		validateInitialCount:true,
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		uploadExtraData: function(event, file) {
			return {
				"fileName": $('#fileName').val()
			};
		}
	}).on("fileuploaded", function(event, data) {
        if(data.response > 0) {
        	alert("成功导入"+ data.response +"条记录！");
        	window.location.href = "content/student/student.jsp";
        } else {
        	alert("导入失败！");
        }
	}); 

	function change(e) {
		 var src=e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
		 var filename=src.value;
		 var fileName = filename.substring( filename.lastIndexOf('\\')+1 );
		 
		 $('#fileName').val(fileName);
	}
	
	function student_search() {
		query_manage(1);
	}
	
	function query_manage(currPage) {
		var searchType = $('#search_type').val();
		var searchWord = $('#search_word').val();
		$.ajax({
			type: 'post',
			url: 'student_getByQuery.action',
			data : {
				searchType: searchType,
				searchWord: searchWord,
				currPage: currPage
			},
			dataType: 'html',
			success: function(data) {
				$('#table_content').html(data);
			}
		});
	}
	
	function enterSearch(e) {
		var keyNum;
		if(window.event) {   //ie
			keyNum = e.keyCode;
		} else if(e.which) {   //netscape,firefox,opera
			keyNum = e.which;
		}
		
		if(keyNum == 13) {
			query_manage(1);
		}
	}
	
	function exportExcel() {
		document.getElementById("excel").submit(); 
	}
</script>
</body>
</html>