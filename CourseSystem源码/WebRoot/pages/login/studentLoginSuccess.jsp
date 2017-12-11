<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'studentLoginSuccess.jsp' starting page</title>
    <link rel="stylesheet"  href="css/style.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	

  </head>
  
  <body>
  <section class="container">
  <center>
  	<div class="login">
  	<h1>登陆成功！</h1>
  	<p class="welcome">欢迎您！<%=session.getAttribute("userName") %>同学！</p>
  	<br>
  	<a href="pages/courseSelect/courseSelection.jsp" class="button">进入选课界面</a>  <br><br>
  	<a href="pages/courseDrop/studentDropCourse.jsp" class="button">进入退课界面</a><br><br>
  	<a href="pages/gradeQuery/gradeQuery.jsp" class="button">成绩查询</a><br>
  	</div>
  </center>
  </section>
  </body>
</html>
