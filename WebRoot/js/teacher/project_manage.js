/**
 * 
 */
function project_view(id) {   //项目简介
	$.ajax({
		type : 'post',
		url : 'content/teacher/project/project_view.jsp',
		data : {
			id : id
		},
		dataType : 'html',
		success : function(data) {
			$('#manage_content').html(data);
		}
	});
}

function student_apply(id) {   //申请人员
	$.ajax({
		type : 'post',
		url : 'student_getByProjectId.action',
		data : {
			id : id,
			frequency : 1
		},
		dataType : 'html',
		success : function(data) {
			$('#manage_content').html(data);
		}
	});
}

function student_selected(id) {   //申请人员
	$.ajax({
		type : 'post',
		url : 'student_getByProjectId.action',
		data : {
			id : id,
			frequency : 2
		},
		dataType : 'html',
		success : function(data) {
			$('#manage_content').html(data);
		}
	});
}

function student_evaluate(id) {
	$.ajax({
		type : 'post',
		url : 'student_getByProjectId.action',
		data : {
			id : id,
			frequency : 3,
		},
		dataType : 'html',
		success : function(data) {
			$('#manage_content').html(data);
		}
	});
}