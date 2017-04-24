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
			 	 <select class="search-select" name="search_type" id="search_type">
			 	 	<option value="1">论文名称</option>
			 	 	<option value="2">学生学号</option>
			 	 </select>
           		 <input type="text" name="search_word" id="search_word"  class="search-text" value="" onKeyPress="enterSearch(event)"/>
           		 <input name="" type="button" value="搜索" class="btn btn-info search-btn" onclick="paper_search()"/>
           </div>
          <form id="excel" action="paper_exportExcel.action" method="post" style="display: inline-block; float: right;">
	        	<input type="button" value="Excel导入" class="btn btn-info import-btn" onclick="showModal()"/>
				<input type="button" value="Excel导出" class="btn btn-info import-btn" onclick="exportExcel()"/>
           </form>
           <br class="clear"/>
		</div>
		<div id="table_content">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th style="width: 200px;">论文名称</th>
					<th style="width: 200px;">期刊名称</th>
					<th style="width: 200px;">期刊号</th>
					<th>期刊等级</th>
					<th>第一作者</th>
					<th>发表时间</th>
				</tr>
			</thead>
			<tbody>		
				<c:if test="${list == null}">
				<tr>
	                <td colspan="10">没有期刊论文记录！</td>
	           	</tr>
				</c:if>		
				<c:if test="${list != null}">
				<%  int num = (Integer)request.getAttribute("currPage");
    			int i= (num-1)*10; %>	
				<c:forEach items="${list}" var="item"> 
				<tr onclick="clickThis(${item.id})">
					<% i++; %> 
	    			<td><%= i %></td>
	    			<td>${item.name}</td>
	    			<td>${item.periodical}</td>
	    			<td>${item.number}</td>
	    			<td>${item.level}</td>
	    			<td>${item.authorName}</td>
	    			<td>${item.time}</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
	            <tr>
	                <td colspan="10">
	                	共${totalRows}条记录,${currPage}/${totalPage}页
	    			<a href="javascript:void(0);" onclick="paper_manage(1)" class="link">首页</a>
	    			<a href="javascript:void(0);" onclick="paper_manage(${currPage-1})" class="link">上一页</a>
	    			<a href="javascript:void(0);" onclick="paper_manage(${currPage+1})" class="link">下一页</a>
	    			<a href="javascript:void(0);" onclick="paper_manage(${totalPage})" class="link">末页</a>
	    			<input type="text" id="manage_page" name="manage_page" class="jump-page"/>
	    			<button onclick="paper_mangage_jump()" class="btn btn-info jump-btn">跳转</button>
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
	function paper_mangage_jump() {
		var page = $("#manage_page").val();
		paper_manage(page);
	}
	
	function clickThis(id) {
		$.ajax({
			type : 'post',
			url : 'paper_getById.action',
			data : {
				id : id,
				frequency : 2
			},
			dataType : 'html',
			success : function(data) {
				$("#paper_content").html(data);
			}
		});
	}
	
	function showModal() {
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
	
	$('#file').fileinput({
		language: 'zh', //设置语言
		uploadUrl: 'paper_importExcel.action',
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
        	window.location.href = "content/admin/innovation/paper/paper.jsp";
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
	
	function paper_search() {
		query_manage(1);
	}
	
	function query_manage(currPage) {
		var searchType = $('#search_type').val();
		var searchWord = $('#search_word').val();
		$.ajax({
			type: 'post',
			url: 'paper_getByQuery.action',
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