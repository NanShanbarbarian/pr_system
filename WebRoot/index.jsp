<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title>科技创新智能互助平台</title>
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css" >   
  	<link rel="stylesheet" href="css/index.css">
  	
</head>

<body>
	<%
		//从请求对象中取出所有的cookie
		 Cookie[] items=request.getCookies();
		 String lastName=""; //上一次登录人	
		 String lastPassword="";
		 String check="";
		//遍历
		if(items!=null && items.length>0){
			for(Cookie c : items){
				if(c.getName().equals("number"))
					lastName = c.getValue();
				else if(c.getName().equals("password"))
					lastPassword = c.getValue();
				else if(c.getName().equals("isRemeber"))
					check = c.getValue();
			}
		}
	%>
	<div class="login_info">
		<div>
			<div class="login_title">科技创新智能互助平台</div>
		</div>
		<div class="login_div">
			<form name="login" action="login.action" method="post" role="form">
			  <div class="word1"><h2>用户登录</h2></div>
			  <br>
			  <div class="form-group">
			  	<input type="text" class="form-control" id="number" name="number" placeholder="用户名" required value="<%=lastName %>">
			  </div>
			  <div class="form-group">
			  	<input type="password" class="form-control" id="password" name="password" placeholder="密码" required value="<%=lastPassword %>">
			  </div>
			  <div class="checkbox">
			    <label>
			      <% if(check.equals("checked")) { %>
			      	<input id="isRemeber" name="isRemeber" type="checkbox" checked="checked"><span class="word2">记住我</span>
			      <% } else {%>
			      	<input id="isRemeber" name="isRemeber" type="checkbox" ><span class="word2">记住我</span>
			      <% } %>
			    </label>
			  </div>
			  <button class="btn btn-lg btn-primary btn-block" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
			  <div class="login_foot word1"><a href="register.jsp">注册账号</a>&nbsp;|&nbsp;<a href="javascript:void(0);">意见反馈</a></div>
			  <div style="clear: both;"></div>
			</form> 
		</div>
    </div>
</body>
</html>