<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registerSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css">


  </head>
  
  <body>
  <body>
  <center>
  <section class="container">
  <div class="login">
    <center>
    	<img src="http://img4.imgtn.bdimg.com/it/u=1237192754,1444102304&fm=206&gp=0.jpg"></img>
    	<br>
    	注册成功！请返回登录页面进行登录！
    	<a href="login.jsp">登录页面</a>
    
    
    </center>
    </div>
    </section>
  </body>
</html>
