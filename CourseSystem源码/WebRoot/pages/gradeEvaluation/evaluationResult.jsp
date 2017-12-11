<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'evaluationResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

	<link rel="stylesheet" type="text/css" href="css/style.css">


  </head>
  
  <body>
   <section class="container">
  <div class="login">
   <center>
   	<img src="http://img4.imgtn.bdimg.com/it/u=1237192754,1444102304&fm=206&gp=0.jpg"></img><br>
   	打分成功！
   	<a href="pages/login/teacherLoginSuccess.jsp">返回登录成功界面</a>
   
   
   
   </center>
   </div>
   </section>
  </body>
</html>
