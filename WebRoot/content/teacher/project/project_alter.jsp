<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		.main-content2 {
			width: 90%;
			font-size: 14px;
			font-family: "微软雅黑";
			font-weight: lighter;
			margin: 10px auto;
			padding: 50px 20px 50px 20px;
			background-color: #fff;
		}
		
		.form-group label {
			font-family: '微软雅黑';
			font-weight: lighter !important; 
			font-size: 14px;
		}
	</style>

</head>
<body>
	<div class="main-content">
		<div class="main-content2">
		<div class="form-horizontal">
			<div class="form-group">
	    		<label class="col-sm-2 control-label">项目名称</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="name" id="name" value="${project.name}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">项目人数</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="num" id="num" value="${project.num}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">开发周期</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="period" id="period" value="${project.period}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">标签</label>
	    		<div class="col-sm-8">
	    			<span style="display: inline;" id="tag-list" class="tag-list"></span>
	    			<button class="btn btn-info btn-sm" onclick="showTagDiaog()">添加标签</button>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">备注关键词</label>
	    		<div class="col-sm-8">
	    			<input class = "form-control" type="text" name="keyword" id="keyword" value="${project.period}">
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">项目描述</label>
	    		<div class="col-sm-8">
	    			<textarea class = "form-control" name="description" id="description" style="width:100%; height:100px;vertical-align:top;resize:none;">${project.description}</textarea>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<label class="col-sm-2 control-label">项目要求</label>
	    		<div class="col-sm-8">
	    			<textarea class = "form-control" name="requirement" id="requirement" style="width:100%; height:100px;vertical-align:top;resize:none;">${project.requirement}</textarea>
	    		</div>
	    	</div>
	    	<div class="form-group">
	    		<div class="col-sm-2 col-sm-offset-5">
	    		<a class="btn btn-info" href="javascript:void(0);" onclick="project_update()" style="font-size: 13px;">确定修改</a>
	    		</div>
	    	</div>
	    </div>
	    </div>
	</div>

	<div class="modal fade" id="confirm">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
			  <h5>添加标签</h5>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label>添加更多标签</label>
	    		<input class = "form-control" type="text" id="search" name="search" placeholder="搜索标签" onKeyPress="enterSearchTag(event)">
	    		<div style="margin-top: 3px;" id="tag-list2" class="tag-list2"></div>
	    	</div>
		    <div class="form-group">
		    	<label>已有标签</label>
		    	<div id="tag-list3" class="tag-list3"></div>
		    </div>
			<div style="width: 100%; height: 30px; margin-top: 50px; text-align: center;" >
			<button class="btn btn-info" onclick="tag_confirm()" data-dismiss="modal">确定</button>
			<button class="btn" data-dismiss="modal" onclick="tag_cancel()">取消</button>
			</div>
			<div style="clear:both"></div>
		</div>
		</div>
		</div>
	</div>

<script src="js/tag/tag_add.js?t=20170617"  type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : 'post',
			url : 'ptr_getByProjectId.action',
			data : {
				ProjectId : '${project.id}'
			},
			dataType : 'json',
			success : function(data) {
				for(var i=0; i<data.length; i++) {
					addTag(data[i].tag, "tag-list");
				}
			}
		});
	});
	
	function project_update() {
		var tags = [];
		var taglist = document.getElementById("tag-list");
		for(var i=0; i<6; i++) {   //因为最多六个标签
			if(i < taglist.childNodes.length) {
				tags.push(taglist.childNodes[i].id);
			} else {
				tags.push("");
			}
		} 
		$.ajax({
			type : 'post',
			url : 'project_update.action',
			data : {
				id : '${project.id}',
				name : $('#name').val(),
				num : $('#num').val(),
				period : $('#period').val(),
				keyword : $('#keyword').val(),
				description : $('#description').val(),
				requirement : $('#requirement').val(),
				tag1 : tags[0],
				tag2 : tags[1],
				tag3 : tags[2],
				tag4 : tags[3],
				tag5 : tags[4],
				tag6 : tags[5]
			},
			success : function() {
				window.location.href = "content/teacher/project/project_my.jsp";
			},
			error : function() {
				alert("修改失败，请重试！");
			}
		});
	}
</script>

</body>
</html>