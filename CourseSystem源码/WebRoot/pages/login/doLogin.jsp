<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="util.*" %>
<%@ page import="util.*" %>
<%@ page import="models.Student" %>
<%@ page import="models.Teacher" %>
<%@ page import="controller.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="objectStorage.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录信息中转页面</title>
    
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
  	<%//设置编码格式为UTF-8
  		request.setCharacterEncoding("UTF-8"); 
  	%>
  
  	<%//判断账号密码是否为空，如果为空，打印“账号或密码不能为空”并重定向至登录页面
  		String userID="";
  		String password="";
  		userID=request.getParameter("userID");
  		password=request.getParameter("password");
  		if("".equals(userID)||"".equals(password))
  		{
  			out.println("跳转提示：账户或密码不能为空");
  			response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  		}
  	 %>
  	 
  	 <%//判断账号是否全部由数字组成，非法账号重定向至登录界面
  	 	char[] userIDNumbers=userID.toCharArray();
  	 	for(int i=0;i<userID.length();i++){
  	 		if(userIDNumbers[i]>'9'||userIDNumbers[i]<'0')
  	 		{
  	 			out.println("跳转提示：账户只能由数字组成");
  	 			response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  	 		}
  	 		}
  	  %>
  	  
  	  <%//判断密码是否符合要求,必须且只能出现大写字母，小写字母和数字，不能出现其他特殊字符
  	  	char[] userPasswordLetters=password.toCharArray();
  	  	boolean hasNumbers=false;
  	  	boolean hasLowercase=false;
  	  	boolean hasUppercase=false;
  	  	boolean hasIllegalLetter=false;
  	  	for(char c:userPasswordLetters){
  	  		if(c>='a'&&c<='z'){
  	  		hasLowercase=true;
  	  		}
  	  		else if(c>='A'&&c<='Z'){
  	  		hasUppercase=true;
  	  		}
  	  		else if(c>='0'&&c<='9'){
  	  		hasNumbers=true;
  	  		}
  	  		else{
  	  		hasIllegalLetter=true;
  	  		}
  	  		
  	  		}
  	  	
  	  	if(hasIllegalLetter)
  	  	{
  	  		out.println("跳转提示：密码出现非法字符，请重新输入！");
  	  		response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  	  	}
  	  	if (!(hasLowercase&&hasUppercase&&hasNumbers)&&userPasswordLetters.length>0){
  	  		out.println("跳转提示：密码中必须出现大写、小写和数字，请重新输入！");
  	  		response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  	  	}

  	   %>
  	   <%
  	   		//设置保存Cookie
  	   		String isUseCookie=request.getParameter("isUseCookie");
  	   		if(isUseCookie!=null)
  	   		{
  	   			String userID_=request.getParameter("userID");
  	   			String password_=request.getParameter("password");
  	   			Cookie userIDCookie=new Cookie("userID",userID);
  	   			Cookie passwordCookie=new Cookie("password",password);
  	   			userIDCookie.setMaxAge(86400);//设置Cookie保存时间为10天
  	   			passwordCookie.setMaxAge(86400);
  	   			response.addCookie(userIDCookie);
  	   			response.addCookie(passwordCookie);
  	   		}
  	   		
  	   		else
  	   		{
  	   			//把Cookie 设置为失效
  	   			Cookie[] cookies=request.getCookies();
  	   			if(cookies!=null&&cookies.length>0)
  	   			{
  	   				for(Cookie c:cookies)
  	   				{
  	   					if(c.getName().equals("userID")||c.getName().equals("password"))
  	   					{
  	   						c.setMaxAge(0);
  	   						response.addCookie(c);
  	   					}
  	   				}
  	   			}
  	   		}
  	    %>
  
  
  <%
  	//判断身份
  	String identity=request.getParameter("identity");
  	boolean isStudent="student".equals(identity);
  %>
  <%//获得账号和密码
  	int _userID=0;
  	if(!"".equals(userID)){
  	_userID=Integer.parseInt(userID);
  	}
  	
  	String _password=request.getParameter("password");
   %>
  <% //调用数据库验证身份
  	UserLogin ul=new UserLogin();
  	if(isStudent)
  	{
  		if(ul.StudentLogin(_userID, _password)!=null)
  		{
  			Student user=new Student();
  			user=ul.StudentLogin(_userID, _password);
  			session.setAttribute("userName",user.getName());
  			session.setAttribute("userID",user.getId());
  			session.setAttribute("userIdentity","student");
  			request.getRequestDispatcher("studentLoginSuccess.jsp").forward(request,response);
  		}
  		else
  		{
  			out.println("登录失败！请验证登录信息！");
  			response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  		}
  	}
  	else
  	{
  		if(ul.TeacherLogin(_userID, _password)!=null)
  		{
  			Teacher user=null;
			user=ul.TeacherLogin(_userID, _password);
  			session.setAttribute("userName",user.getName());
  			session.setAttribute("userID",user.getId());
  			session.setAttribute("userIdentity","teacher");
  			request.getRequestDispatcher("teacherLoginSuccess.jsp").forward(request,response);
  		}
  		else
  		{
  			out.println("登录失败！请检查登录设备！");
  			response.setHeader("Refresh","2;url=http://localhost:8090/CourseSystem/login.jsp");
  		}
  	}
  		
   %>
  
  	</body>
</html>
