<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'courseRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<link rel="stylesheet" type="text/css" href="css/style1.css">
  </head>
  
  <body>
	<section class="container">
  <div class="login">
   		<h1>课程注册页面</h1>
   		<form action="pages/courseRegister/doCourseRegister.jsp" method="post" class="basic-grey">
   			<input type="text" name="courseName"  style="float:left" placeholder="课程名称"><br>
   			
   			
   			<select name="courseDepartment" >
   				<option value="">院系</option>
   				<option value="1">校选课</option>
   				<option value="2">环境</option>
   				<option value="3">信息</option>
   				<option value="4">艺术</option>
   			</select>
   			<br>
   			
   			<select name="courseCapacity"   >
   				<option value="">容纳学生数量</option>
   				<option value="30">30</option>
   				<option value="100">100</option>
   			 </select>
   			 <br>
   			 
   			 <select name="courseTime"   >
   				<option value="">选择星期</option>
   				<option value="1">周一</option>
   				<option value="2">周二</option>
   				<option value="3">周三</option>
   				<option value="4">周四</option>
   				<option value="5">周五</option>
   				<option value="6">周六</option>
   				<option value="7">周日</option>
   			 </select>
   			 <br>
   			 
   			  
   			 <select name="courseOrder"   >
   				<option value="">选择上课节次</option>
   				<option value="1">第一节</option>
   				<option value="2">第二节</option>
   				<option value="3">第三节</option>
   				<option value="4">第四节</option>
   				<option value="5">第五节</option>
   			 </select>
   			 <br>
   			<center>
   			<input type="submit" value="提交"  ><br>
   			</center>
   		
   		</form>

   <a href="pages/login/teacherLoginSuccess.jsp">返回登录成功界面</a>
   
   
</div>
</section>
  </body>
</html>
