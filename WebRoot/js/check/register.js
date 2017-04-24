/**
 * 
 */
function checkName() {
	var name = document.getElementById("name").value;
	if(name==null || name=="") {
		document.getElementById("name_div").innerHTML = "姓名不能为空！";
		return false;
	} else if(name.length>30) {
		document.getElementById("name_div").innerHTML = "长度不能超过30！";
		return false;
	}
	document.getElementById("name_div").innerHTML = "必填";
	return true;
}

function checkNumber() {
	var number = document.getElementById("number").value;
	var reg = /[0-9]{1,12}/;
	if(number==null || number=="") {
		document.getElementById("number_div").innerHTML = "学号或工号不能为空！";
		return false;
	} else if(!number.match(reg)) {
		document.getElementById("number_div").innerHTML = "学号或工号必须为数字且长度不能超过12！";
		return false;
	}
	document.getElementById("number_div").innerHTML = "必填";
	return true;
}

function checkAcademy() {
	var academy = document.getElementById("academy").value;
	if(academy == "") {
		document.getElementById("academy_div").innerHTML = "学院不能为空！";
		return false;
	} else if(academy.length>20) {
		document.getElementById("academy_div").innerHTML = "长度不能超过20！";
		return false;
	}
	document.getElementById("academy_div").innerHTML = "必填";
	return true;
}

function checkLevel() {
	var level = document.getElementById("level").value;
	if(level==null || level=="") {
		document.getElementById("level_div").innerHTML = "层次不能为空！";
		return false;
	}
	document.getElementById("level_div").innerHTML = "必选";
	return true;
}

function checkMajor() {
	var major = document.getElementById("major").value;
	if(major==null || major=="") {
		document.getElementById("major_div").innerHTML = "专业不能为空！";
		return false;
	} else if(major.length>20) {
		document.getElementById("major_div").innerHTML = "长度不能超过20！";
		return false;
	}
	document.getElementById("major_div").innerHTML = "必填";
	return true;
}

function checkGrade() {
	var grade = document.getElementById("grade").value;
	if(grade==null || grade=="") {
		document.getElementById("grade_div").innerHTML = "年级不能为空！";
		return false;
	} else if(grade.length>10) {
		document.getElementById("grade_div").innerHTML = "长度不能超过10！";
		return false;
	}
	document.getElementById("grade_div").innerHTML = "必填";
	return true;
}

function checkClassName() {
	var className = document.getElementById("className").value;
	if(className==null || className== "") {
		document.getElementById("className_div").innerHTML = "班级不能为空！";
		return false;
	} else if(className.length>10) {
		document.getElementById("className_div").innerHTML = "长度不能超过10！";
		return false;
	}
	document.getElementById("className_div").innerHTML = "必填";
	return true;
}

function checkNation() {
	var nation = document.getElementById("nation").value;
	if(nation==null || nation=="") {
		document.getElementById("nation_div").innerHTML = "民族不能为空！";
		return false;
	} else if(nation.length>20) {
		document.getElementById("nation_div").innerHTML = "长度不能超过20！";
		return false;
	}
	document.getElementById("nation_div").innerHTML = "必填";
	return true;
}

function checkPolity() {
	var polity = document.getElementById("polity").value;
	if(polity!=null && polity.length>10) {
		document.getElementById("polity_div").innerHTML = "长度不能超过10！";
		return false;
	}
	document.getElementById("polity_div").innerHTML = "";
	return true;
}

function checkTelephone() {
	var telephone = document.getElementById("telephone").value;
	var reg = /^1[0-9]{10}/
	if(telephone == "") {
		document.getElementById("telephone_div").innerHTML = "手机号码不能为空！";
		return false;
	} else if(!telephone.match(reg)) {
		document.getElementById("telephone_div").innerHTML = "请填写中国大陆手机号码！";
		return false;
	}
	document.getElementById("telephone_div").innerHTML = "必填";
	return true;
}

function checkCornet() {
	var cornet = document.getElementById("cornet").value;
	if(cornet!=null && cornet.length>10) {
		document.getElementById("cornet_div").innerHTML = "长度不能超过10！";
		return false;
	}
	document.getElementById("cornet_div").innerHTML = "";
	return true;
}

function checkQQ() {
	var qq = document.getElementById("qq").value;
	if(qq!=null && qq!="") {
		var reg = /^[1-9][0-9]{0,19}/;
		if(qq.length>20) {
			document.getElementById("qq_div").innerHTML = "长度不能超过20！";
			return false;
		} else if(!qq.match(reg)) {
			document.getElementById("qq_div").innerHTML = "qq填写不规范！";
			return false;
		}
	}
	document.getElementById("qq_div").innerHTML = "";
	return true;
}

function checkEmail() {
	var email = document.getElementById("email").value;
	if(email!=null && email!="") {
		var reg=/^[a-zA-Z0-9_-]+@(126.com|163.com|qq.com|sohu.com|sina.com|hotmail.com|gmail.com|yahoo.cn)$/;  
		if(email.length>50) {
			document.getElementById("email_div").innerHTML = "长度不能超过20！";
			return false;
		} else if(!email.match(reg)) {
			document.getElementById("email_div").innerHTML = "请填写常用邮箱！";
			return false;
		}
	}
	document.getElementById("email_div").innerHTML = "";
	return true;
}

function checkDormitory() {
	var dormitory = document.getElementById("dormitory").value;
	if(dormitory==null || dormitory=="") {
		document.getElementById("dormitory_div").innerHTML = "宿舍不能为空！";
		return false;
	} else if(dormitory.length>20) {
		document.getElementById("dormitory_div").innerHTML = "长度不能超过20！";
		return false;
	}
	document.getElementById("dormitory_div").innerHTML = "必填";
	return true;
}



function checkDescription() {
	var description = document.getElementById("description").value;
	if(description!=null && description.length>200) {
		document.getElementById("description_div").innerHTML = "填写内容不能超过200！";
		return false;
	}
	document.getElementById("description_div").innerHTML = "";
	return true;
}

function checkStudentForm() {
	var flag = checkName()&&checkNumber()&&checkAcademy()&&checkLevel()&&checkMajor()&&checkGrade()&&checkClassName()&&checkNation()&&checkPolity()&&checkTelephone()&&checkCornet()&&checkQQ()&&checkEmail()&&checkDormitory()&&checkDescription();
	if(flag) {
		return true;
	}
	return false;
}

function checkTeacherForm() {
	var flag = checkName()&&checkNumber()&&checkAcademy()&&checkLevel()&&checkTelephone()&&checkCornet()&&checkQQ()&&checkEmail()&&checkDescription();
	if(flag) {
		return true;
	}
	return false;
}