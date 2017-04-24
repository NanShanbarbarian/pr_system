/**
 * 
 */
function showTagDiaog() {   //显示标签框
	initTagDialog();
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

function initTagDialog() {   //初始化标签框
	$(".tag-list3 button").remove();
	$(".tag-list2 button").remove();
	$('#search').val("");
	var taglist = document.getElementById("tag-list");
	for(var i=0; i<taglist.childNodes.length; i++) {
		addTag(taglist.childNodes[i].id, "tag-list3");	
	}
}

function enterSearchTag(e) {	//判断回车
	var keyNum;
	if(window.event) {   //IE
		keyNum = e.keyCode;
	} else if(e.which) {   //Netscape|Firefox|Opera
		keyNum = e.which;
	}
	if(keyNum == 13) {
		tag_search();
	}
}

function tag_search() {
	var search = $('#search').val();
	if(search != "") {
		$.ajax({
			type : 'post',
			url : 'tag_search.action',
			data : {
				name : $('#search').val()
			},
			dataType : 'json',
			success : function(data) {
				if(data == 0) {
					document.getElementById('tag-list2').innerHTML = '没有搜索的标签';
				} else {
					var taglist3 = document.getElementById("tag-list3");
	        	    if(taglist3.childNodes.length > 0) {
	        		   for(var i=0; i<data.length; i++) {  
	        			   for(var j=0; j<taglist3.childNodes.length; j++) {
	        				   if(data[i].name == taglist3.childNodes[j].id) {
	        					   data.splice(i,1);  //删除数组中的某个元素
	        				   }
	        			   }       			   
	        		   }       		       
	        	    }  
	        	    for(var k=0; k<data.length; k++) {
	        	    	addTag(data[k].name,"tag-list2"); 
	        	    }
				}
			}
		});
	} else {
		alert("请输入标签关键词！");
	}
}

function addTag(tag, taglist) {   //添加标签按钮
	
	if(taglist == "tag-list") {
		$("#"+taglist+"").append($("<button class='btn btn-default btn-xs' style='margin-right: 2px;' id='"+tag+"' onclick=tag_do3('"+ tag +"')>"+ tag +"</button>"));
	} else if(taglist == "tag-list2") {
		$("#"+taglist+"").append($("<button class='btn btn-default btn-xs' style='margin-right: 2px;' id='"+tag+"' onclick=tag_do1('"+ tag +"')>"+ tag +"</button>"));
	} else if(taglist == "tag-list3") {
		$("#"+taglist+"").append($("<button class='btn btn-default btn-xs' style='margin-right: 2px;' id='"+tag+"' onclick=tag_do2('"+ tag +"')>"+ tag +"</button>"));
	}
}

function tag_do1(tag) {   //tag-list3中添加标签按钮
	if($('#tag-list3 button').length < 6) {   //最多只能添加6个标签
		var button = document.getElementById(tag);
		document.getElementById("tag-list2").removeChild(button);
		addTag(tag, "tag-list3");
		$(".tag-list3 label").remove();
	} else {
		$("#tag-list3").append($("<label>最多只能选择六个标签！</label>"));
	}
}

function tag_do2(tag) {   //tag-list2中添加标签按钮，tag-list3中移除标签按钮
	var button = document.getElementById(tag);	
	document.getElementById("tag-list3").removeChild(button);
	addTag(tag, "tag-list2");
}

function tag_do3(tag) {   //tag-list中移除标签按钮
	var button = document.getElementById(tag);
	document.getElementById("tag-list").removeChild(button);
}

function tag_confirm() {   //确定操作
	$('.tag-list button').remove();
	var taglist3 = document.getElementById("tag-list3");
	for(var i=0; i<$('#tag-list3 button').length; i++) {
		addTag(taglist3.childNodes[i].id, "tag-list");
	}
}

function tag_cancel() {   //取消操作
	$(".tag-list3 button").remove();
}