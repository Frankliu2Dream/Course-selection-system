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
    
    <title>My JSP 'doTeacherRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	

  </head>
  
  <body>
  <center>
  <section class="container">
  <div class="login">
    <%request.setCharacterEncoding("utf-8"); %>
  	<% RegisterInformationCheck check=new RegisterInformationCheck(); %>
    <%//获取所有Parameter
    	String id=request.getParameter("teacherId");
    	String name="";
    	String birthYear="";
    	String gender="";
    	String department="";
    	String password1="";
    	String password2="";
    	id=request.getParameter("teacherId");
    	name=request.getParameter("teacherName");
    	birthYear=request.getParameter("birthYear");
    	gender=request.getParameter("teacherGender");
    	department=request.getParameter("teacherDepartment");
    	password1=request.getParameter("password1");
    	password2=request.getParameter("password2");
     %>
   
     <%//输入信息是否为空验证
     	if(check.isEmpty(id)){
     		out.println("<img src=\"http://ico.ooopic.com/iconset02/4/gif/18386.gif\" ></img>");
    		out.println("<br>");
     		out.println("职工号不能为空！请重新填写注册信息！");
     	
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(name)){
     		out.println("姓名不能为空！请重新填写注册信息！");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(birthYear)){
     		out.println("请选择出生年份!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(gender)){
     		out.println("请选择性别!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(department)){
     		out.println("请选择院系");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(password1)){
     		out.println("密码不能为空！");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
     	if(check.isEmpty(password2)){
     		out.println("验证密码不能为空！请重新填写！");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     		}
      %>
      
      <%//用户名合法性验证
      	if(!check.isChineseChar(name))
      	{
      		out.println("用户名必须为中文真实姓名,请重新填写!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     	}
       %>
       <%
       	//Id合法性验证
       	if(!check.isLegalId(id))
      	{
      		out.println("密码只能由数字组成，请重新填写!");
     		response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
     		return;
     	}
        %>
        <%//密码合法性验证
        if(!check.isLegalPassword(password1))
        {
        	out.println("跳转提示：密码中必须出现大写、小写和数字，请重新输入！");
  	  		response.setHeader("Refresh","5;url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
  	  		return;
        }
         %>
         <%//验证密码一致性检验
         if(!password1.equals(password2))
         {
          	out.println("两次输入密码不一致，请重新输入！");
  	  		response.setHeader("Refresh","5;url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
  	  		return;
         }
          %>
          <%//计算生日
          	int birthY=0;
          	if(!"".equals(birthYear))
          	{
          		birthY=Integer.parseInt(birthYear);
          	}
          	int age=2016-birthY;
           %>
           <%//实例化Department
           	int dept_id=0;
           	if(!"".equals(department))
           	{
           		dept_id=Integer.parseInt(department);
           	}
           	Department user_dept=new Department();
           	user_dept.setId(dept_id);
           	Instance.Instantiation(user_dept,objectPool.om);
            %>
            <%//处理id
            int t_id=0;
            if(!"".equals(id))
            {
            	t_id=Integer.parseInt(id);
            	}
             %>
           <%//实例化学生
           		Teacher t=new Teacher();
           		t.setId(t_id);
           		t.setName(name);
           		t.setAge(age);
           		t.setDepartment(user_dept);
           		t.setGender(gender);   
            %>
            <%
 				UserRegister ur=new UserRegister();
 				boolean flag=ur.register(t, password1);
 				if(!flag)
 				{
 					out.println("您已注册过！请重新检查注册信息！");
 					response.setHeader("Refresh","5;url=http://localhost:8090/CourseSystem/pages/register/teacherRegister.jsp");
 					return;
 				}       
            	else
            	{
            		request.getRequestDispatcher("registerSuccess.jsp").forward(request,response);
            		}
             %>
             </div>
             </section>
             </center>
  </body>
</html>
