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
    
    <title>My JSP 'gradeEvaluation.jsp' starting page</title>
    
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
    	<h1>选择打分课程</h1>
    	<%!
    		public static final int pageSize=3;
    		int pageCount;
    		int curPage=1;
    	
    	 %>
    	 
    	 
    	  
    	 
    	 
    	 
    	<%
    		CourseStudentList scl= new CourseStudentList();
    		int course_id=0;
    		course_id=Integer.parseInt(request.getParameter("evaluateCourseId"));
    		session.setAttribute("evaluateCourseId", course_id);
    		Course course=new Course();
    		course.setId(course_id);
    		Instance.Instantiation(course, objectPool.om);
    		ArrayList<StudentCourse> list=new ArrayList<StudentCourse>();
    		list=scl.getList(course_id);
    		if(list.size()==0)
    		{
    			out.println("该门课程暂时没有学生选择！");
    			return;
    		}
    		
    	 %>
    	 
    	 
    	 <%
    		int size=list.size();
    		pageCount=(size%pageSize==0)?(size/pageSize):(size/pageSize+1);
    		String tmpString=request.getParameter("curPage");
    		
    		if(tmpString==null)
    		{
    			tmpString="1";
    		}
    		if(tmpString.equals("0"))
    		{
    			tmpString="1";
    		}
    		
    		curPage=Integer.parseInt(tmpString);
    		if(curPage>=pageCount) curPage= pageCount;
    		
    	 %>
    	
    	 <!-- 鎵撳嵃琛ㄥ崟 -->
    	 <form action="pages/gradeEvaluation/doEvaluation.jsp" method="post">
    	 <table class="newtable">
    	 	<tr>
    	 		<th colspan="3" sytle="color:black">课程名：<%=course.getName() %></th>
    	 	</tr>
    	 	<tr>
    			<th style="color:black">当前分数</th>
    	 		<th style="color:black">学生姓名</th>
    	 		<th style="color:black">学生id</th>
    	 	</tr>
    	 <%
    	 	for(int x=(curPage-1)*pageSize;x<curPage*pageSize&&x<size;x++)
    	 	{
    	 		int grade=list.get(x).getGrade();
    	 		String s_name=list.get(x).getStudent().getName();
    	 		int s_id=list.get(x).getStudent().getId();
    	 		out.println("<tr>");
    	 		out.println("<td><input type=\"text\" name=\""+s_id+"\" value=\""+grade+"\"></td>");
    	 		out.println("<td>"+s_name+"</td>");
    	 		out.println("<td>"+s_id+"</td>");
    	 		out.println("</tr>");
    	 	}
    	 
    	  %>
    	
    	  </table>
    	    <input type="submit" value="提交">
    	  </form>
    	 
    	 
 
    	    <a href = "pages/gradeEvaluation/gradeEvaluation.jsp?curPage=1" >首页</a>  
			<a href = "pages/gradeEvaluation/gradeEvaluation.jsp?curPage=<%=curPage-1%>" >上一页</a>  
			<a href = "pages/gradeEvaluation/gradeEvaluation.jsp?curPage=<%=curPage+1%>" >下一页</a>  
			<a href = "pages/gradeEvaluation/gradeEvaluation.jsp?curPage=<%=pageCount%>" >尾页</a>  
			第<%=curPage%>页 共<%=pageCount%>页<br> 
			<a href="pages/login/teacherLoginSuccess.jsp">返回登录成功界面</a>
    </div>
    </section>
    </center>
  </body>
</html>
