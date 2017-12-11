<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="controller.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>selectResult</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"  href="css/style.css">

  </head>
  
  <body>
  <section class="container">
  <div class="login">
  <center>
      <% //选课结果展示
    	String s1=(String)request.getParameter("selectedCourseId");
    	int courseId=Integer.parseInt(s1);//获得课程id
    	
    	int studentId=(Integer)session.getAttribute("userID");
    	CourseSelection cs=new CourseSelection();
    	
    	boolean isSucceed=cs.select(studentId, courseId);//进行选课操作,选课成功返回1,选课失败返回0
    	
    	if(isSucceed)
    	{
    		out.println("<img src=\"http://img4.imgtn.bdimg.com/it/u=1237192754,1444102304&fm=206&gp=0.jpg\"></img>");
    		out.println("<br>");
    		out.println("选课成功！");
    	}
    	else
    	{
    		out.println("<img src=\"http://ico.ooopic.com/iconset02/4/gif/18386.gif\" ></img>");
    		out.println("<br>");
    		out.println("选课失败！您已选择该课程，请检查选课结果！");
    	}
     %>
     <br>
     <a href="pages/courseSelect/courseSelection.jsp"> 返回选课界面</a>
   </center>
   </div>
   </section>
  </body>
</html>
