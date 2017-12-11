<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacherRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">

  </head>
  
  <body  background:url(../ruc_bg_204b.jpg)>
	<div class="login">
   		<h1>教师注册页面</h1>
   		<form action="pages/register/doTeacherRegister.jsp" method="post" class="basic-grey" >
   			<input type="text" name="teacherId"  style="float:left" placeholder="职工号"><br>
   			<input type="text" name="teacherName"  style="float:left" placeholder="姓名（真实姓名）"><br>
   			<input type="password" name="password1" style="float:left" placeholder="密码（由且只能由大写、小写字母和数字组成）"><br>
   			<input type="password" name="password2"  style="float:left" placeholder="重新确认密码密码"><br>
   			
   			
   			<select name="teacherDepartment" >
   				<option value="">请选择您的院系</option>
   				<option value="1">环境</option>
   				<option value="2">信息</option>
   				<option value="3">艺术</option>
   			</select>
   			<br>
   			
   			
   			<select name="birthYear"   >
   				<option value="">选择出生年份</option>
   				<%
   					for(int x=1950;x<=2016;x++) 
   					{
   						out.println("<option value=\""+x+"\">"+x+"</option>");
   					}
   				
   				%>
   			 </select>
   			 <br>
   			 
   			 
   			<select name="teacherGender" s >
   				<option value="">请选择您的性别</option>
   				<option value="male">男</option>
   				<option value="female">女</option>
   			</select>
   			<br>
   			<center>
   			<input type="submit" value="提交" align="middle" ><br>
   			</center>
   		
   		
   		</form>

   
   
   </div>

  </body>
</html>
