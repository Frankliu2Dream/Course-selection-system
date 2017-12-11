<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacherLoginSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	

  </head>
  
  <body>
  <section class="container">
  <center>
  <div class="login">
  	<h1>登陆成功！</h1>
  	<p class="welcome">欢迎您！<%=session.getAttribute("userName") %>老师！</p><br>
  	<br>
  	<a href="pages/courseRegister/courseRegister.jsp" class="button">课程注册</a><br><br>
  	<a href="pages/classroomArrangementResult/classroomArrangementResult.jsp" class="button">查询排课结果</a><br><br>
  	<a href="pages/gradeEvaluation/courseSelection.jsp" class="button">课程打分</a>  <br>
  </div>
  </center>
  </section>
  </body>
</html>
