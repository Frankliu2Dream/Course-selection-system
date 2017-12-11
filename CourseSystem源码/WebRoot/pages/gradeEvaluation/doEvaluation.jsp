<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="controller.*" %>
<%@ page import="models.*" %>
<%@ page import="Dao.*" %>
<%@ page import="objectStorage.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'doEvalutation.jsp' starting page</title>
    
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
  <%//生成StudentCourse的list
  	CourseStudentList csl=new CourseStudentList();
  	int course_id=(Integer)session.getAttribute("evaluateCourseId");
  	ArrayList<StudentCourse> list=new ArrayList<StudentCourse>();
    list=csl.getList(course_id);
	
   %>
   
   <%//判断输入分数的合法性
   		ArrayList<String> scoreList=new ArrayList<String>();
   		
   		for(int x=0;x<list.size();x++)
		{
			Integer s_id=list.get(x).getStudent().getId();
			int score=0;
			try{
				score=Integer.parseInt(request.getParameter(s_id.toString()));
				}
			catch(Exception e)
				{
				out.println("输入的分数不合法，请检查！");
				return;
				}
			finally{
				if(score>=0&&score<=100)
				{
					list.get(x).setGrade(score);
					}
				else
				{
					out.println("分数越界");
					return;
				}
				}
			}
   
    %>
    <%	
    	SetGrade sg= new SetGrade();
    	for(int x=0;x<list.size();x++)
    	{
    		if(sg.set(list.get(x))==false)
    		{
    			out.println("数据储存失败！");
    			return;
    		}
    	}
    	 %>
    <%
    	request.getRequestDispatcher("evaluationResult.jsp").forward(request,response);
    
    
    
     %>
    	 
  </body>
</html>
