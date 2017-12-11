<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="controller.*" %>
<%@ page import="models.*" %>

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
  <section class="container">
  <div class="login">
    <center>
    	<h1>选择打分课程</h1>
    	<%!
    		public static final int pageSize=3;
    		int pageCount;
    		int curPage=1;
    	
    	 %>
    	 
    	 
    	  
    	 
    	 
    	 
    	<%
    		RegisteredCourses rc=new RegisteredCourses();
    		int teacher_id=0;
    		teacher_id=(Integer)session.getAttribute("userID");
    		
    		ArrayList<Course> courseList=new ArrayList<Course>();
    		courseList=rc.getCourses( teacher_id);
    		if(courseList.size()==0)
    		{
    			out.println("已注册课程为空");
    			response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/login/teacherLoginSuccess.jsp");
    			return;
    			
    		}
    		
    	 %>
    	 
    	 
    	 <%
    		int size=courseList.size();
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
    	 <form action="pages/gradeEvaluation/gradeEvaluation.jsp" method="post">
    	 <table class="newtable">
    	 	<tr>
    			<th>选择</th>
    	 		<th>课程编号</th>
    	 		<th>课程名称</th>
    	 		<th>星期</th>
    	 		<th>节次</th>
    	 	</tr>
    	 <%
    	 	for(int x=(curPage-1)*pageSize;x<curPage*pageSize&&x<size;x++)
    	 	{
    	 		int id=courseList.get(x).getId();
    	 		String name=courseList.get(x).getName();
    	 		int day=courseList.get(x).getTimeSlot().getDay();
    	 		int order=courseList.get(x).getTimeSlot().getClassorder();
    	 		out.println("<tr>");
    	 		out.println("<td><input type=\"radio\" name=\"evaluateCourseId\" value=\""+id+"\"></td>");
    	 		out.println("<td>"+id+"</td>");
    	 		out.println("<td>"+name+"</td>");
    	 		out.println("<td>"+day+"</td>");
    	 		out.println("<td>"+order+"</td>");
    	 		out.println("</tr>");
    	 	}
    	 
    	  %>
    	
    	  </table>
    	  	
    	    <input type="submit" value="提交">
    	  </form>
    	 
    	 
 
    	    <a href = "pages/gradeEvaluation/courseSelection.jsp?curPage=1" >首页</a>  
			<a href = "pages/gradeEvaluation/courseSelection.jsp?curPage=<%=curPage-1%>" >上一页</a>  
			<a href = "pages/gradeEvaluation/courseSelection.jsp?curPage=<%=curPage+1%>" >下一页</a>  
			<a href = "pages/gradeEvaluation/courseSelection.jsp?curPage=<%=pageCount%>" >尾页</a>  
			第<%=curPage%>页 共<%=pageCount%>页 <br>
			<a href="pages/login/teacherLoginSuccess.jsp">返回登录成功界面</a>
    </center>
    </div>
    </section>
  </body>
</html>
