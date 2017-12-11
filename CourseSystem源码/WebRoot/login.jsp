<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程管理系统登录页面</title>
     <link rel="stylesheet" href="css/style.css">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <section class="container">
  <div class="login">
  	<h1>课程管理系统</h1>
  	<%//检查是否有Cookie,如果有的话储存在Sting 类型变量中
  		String userIDCookie="";
  		String passwordCookie="";
  		Cookie[] cookies=request.getCookies();
  		if(cookies!=null&&cookies.length>0)
  		{
  		 for(Cookie c:cookies)
  		 {
  		 	if(c.getName().equals("userID"))
  		 	{
  		 		userIDCookie=c.getValue();
  		 	}
  		 	if(c.getName().equals("password"))
  		 	{
  		 		passwordCookie=c.getValue();
  		 	}
  		 }
  		}
  	 %>
  	 
  	 <!-- 打印登录界面信息表单 -->
  	 <form action="pages/login/doLogin.jsp"  method="post">
  	 <p>
  	<label>
  		请选择您的身份<br>
  		<input type="radio" name="identity" value="student" checked="true">学生
  		<input type="radio" name="identity" value="teachingStaff">教职工<br>
  	</label>
   </p>
   
   <p>
    	账号<input type="text" name="userID" value=<%=userIDCookie%>><br>
   </p>
   <p>
    	密码<input type="password" name="password" value=<%=passwordCookie %>><br>
   </p>
    	<p class="remember_me">
    	<lable>
    	<input type="checkbox" name="isUseCookie" checked="true" value="true">十天内保存账号密码</input><br>
    	</lable>
    	</p>
    	<p class="submit">
    	<input type="submit" value="提交">
    	</p>
    </form>
    <a href="pages/register/register.jsp">账号注册</a>
   </center>
   </div>
   <section>
  </body>
</html>
