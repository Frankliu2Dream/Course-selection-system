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
    
    <title>My JSP 'classRoomArrangement.jsp' starting page</title>
    
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
    	<h1>排课结果</h1>
    	<%!//设置Servlet类成员
    		public static final int pageSize=3;//一页放两条
    		int pageCount;//页数
    		int curPage=1;//当前页码
    	
    	 %>
    	 
    	 
    	  
    	 
    	 
    	 
    	<%//获取所有专业课列表和列表长度
    		RegisteredCourses rc=new RegisteredCourses();
    		int teacher_id=0;
    		teacher_id=(Integer)session.getAttribute("userID");
    		
    		ArrayList<Course> courseList=null;
    		courseList=rc.getCourses(teacher_id);
    		if(courseList.size()==0)
    		{
    			out.println("您已注册课程为空！");
    			out.println("5秒后返回登录成功界面");
    			response.setHeader("Refresh","5,url=http://localhost:8090/CourseSystem/pages/login/teacherLoginSuccess.jsp");
    			return;
    		}
    		
    	 %>
    	 
    	 
    	 <%//设置分页信息
    		int size=courseList.size();
    		pageCount=(size%pageSize==0)?(size/pageSize):(size/pageSize+1);//页数
    		String tmpString=request.getParameter("curPage");//判断传入参数请求跳转到第几页
    		
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
    	
    	 <!-- 打印表单 -->
    	 <table class="newtable">
    	 	<tr>
    			<th>课程id</th>
    	 		<th>课程名称</th>
    	 		<th>教学楼</th>
    	 		<th>教室</th>
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
    	 		if(courseList.get(x).getClassroom().getId()!=0)
    	 		{
    	 			int building_id=courseList.get(x).getClassroom().getBuilding().getId();
    	 			int classroom_id=courseList.get(x).getClassroom().getId();
	    	 		out.println("<tr>");
	    	 		out.println("<td>"+id+"</td>");
	    	 		out.println("<td>"+name+"</td>");
	    	 		out.println("<td>"+building_id+"</td>");
	    	 		out.println("<td>"+classroom_id+"</td>");
	    	 		out.println("<td>"+day+"</td>");
	    	 		out.println("<td>"+order+"</td>");
	    	 		out.println("</tr>");
    	 		}
    	 		else
    	 		{
    	 			out.println("<tr>");
	    	 		out.println("<td>"+id+"</td>");
	    	 		out.println("<td>"+name+"</td>");
	    	 		out.println("<td>"+"未排"+"</td>");
	    	 		out.println("<td>"+"未排"+"</td>");
	    	 		out.println("<td>"+day+"</td>");
	    	 		out.println("<td>"+order+"</td>");
	    	 		out.println("</tr>");
    	 		}
    	 	}
    	 
    	  %>
    	
    	  </table>
    	
    	  <!-- 设置跳转链接 -->
    	    <a href = "pages/classroomArrangementResult/classroomArrangementResult.jsp?curPage=1" >首页</a>  
			<a href = "pages/classroomArrangementResult/classroomArrangementResult.jsp?curPage=<%=curPage-1%>" >上一页</a>  
			<a href = "pages/classroomArrangementResult/classroomArrangementResult.jsp?curPage=<%=curPage+1%>" >下一页</a>  
			<a href = "pages/classroomArrangementResult/classroomArrangementResult.jsp?curPage=<%=pageCount%>" >尾页</a>  
			第<%=curPage%>页/共<%=pageCount%>页  <br>
			 <a href="pages/login/teacherLoginSuccess.jsp">跳转回登录成功界面</a><br>
    </center>
    </div>
    </section>
  </body>
</html>
