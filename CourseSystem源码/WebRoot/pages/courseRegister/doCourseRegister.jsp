<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@page import="util.*" %>
<%@page import="models.*" %>
<%@page import="Dao.*" %>
<%@page import="objectStorage.*" %>
<%@page import="controller.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'doCourseRegister.jsp' starting page</title>
    
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
    <%request.setCharacterEncoding("utf-8"); %>
  	<% CourseRegister cr=new CourseRegister();
  		RegisterInformationCheck check=new RegisterInformationCheck();
  	%>
    <%//获取所有Parameter
    	Integer tid=null;
    	String courseName="";
    	String courseDepartment="";
    	String courseCapacity="";
    	String courseTime="";
    	String courseOrder="";
    	
    	tid=(Integer)session.getAttribute("userID");
    	courseName=request.getParameter("courseName");
    	courseTime=request.getParameter("courseTime");
    	courseOrder=request.getParameter("courseOrder");
    	courseDepartment=request.getParameter("courseDepartment");
    	courseCapacity=request.getParameter("courseCapacity");
    	
     %>
   
     <%//输入信息是否为空验证
     	if(check.isEmpty(courseName)){
     		out.println("课程名不能为空！请重新填写注册信息！");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/studentRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(courseDepartment)){
     		out.println("请选择院系!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/studentRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(courseTime)){
     		out.println("请选择上课日!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/studentRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(courseOrder)){
     		out.println("请选择上课节次");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/studentRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(courseCapacity)){
     		out.println("请选择课堂大小！");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/studentRegister.jsp");
     		return;
     		}
      %>
      
      
       
         
           <%//实例化Department
           	int dept_id=0;
    		dept_id=Integer.parseInt(courseDepartment);
           	Department course_dept=new Department();
           	course_dept.setId(dept_id);
           	Instance.Instantiation(course_dept,objectPool.om);
            %>
            
            <%//实例化timeSlot
           	int day=Integer.parseInt(courseTime);
           	int order=Integer.parseInt(courseOrder);
           	InitTimeSlot its=new InitTimeSlot();
           	TimeSlot ts=its.init(day, order);
             %>
           <%//实例化教师
           		int t_id=tid;
           		Teacher t=new Teacher();
           		t.setId(t_id);
           		Instance.Instantiation(t, objectPool.om); 
            %>
            <%//课堂容量
            	int c_capacity=Integer.parseInt(courseCapacity);
             %>
            <%//实例化课程
            	Course c=new Course();
            	c.setName(courseName);
            	c.setTeacher(t);
            	c.setDepartment(course_dept);
            	c.setTimeSlot(ts);
            	c.setCapacity(c_capacity);
             %>
            <%
 				boolean flag=cr.register(c);
 				if(!flag)
 				{
 					out.println("注册失败！");
 					response.setHeader("Refresh","5;url=http://localhost:8090/CourseSystem/pages/courseRegister/courseRegister.jsp");
 					return;
 				}       
            	else
            	{
            		request.getRequestDispatcher("courseRegisterSuccess.jsp").forward(request,response);
            		}
             %>
  </body>