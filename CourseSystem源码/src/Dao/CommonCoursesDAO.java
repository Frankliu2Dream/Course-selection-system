package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Course;
import models.Student;
import objectStorage.objectMap;
import objectStorage.objectPool;
import util.DBHelper;

/*
 * 校选课查询DAO，查询返回结果为所有校选课信息（不区分是否已经选择其中的课课程）
 * 传入：无
 * 调用 getCourseList()方法，在数据库中查询所有院系字段为null的课程，即校选课，返回ArrayList<Course>
 * 
 * 
 */
public class CommonCoursesDAO {
	@SuppressWarnings("null")
	public ArrayList<Course> getCourseList() throws Exception{
		
		//objectMap om=new objectMap();//!!!!!
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		
		String sql=new String("select * from course where department is null;");
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		ArrayList<Course> courseList=new ArrayList<Course>();
		while(rs.next())
		{
			int courseID=rs.getInt(1);
			Course c=new Course();
			c.setId(courseID);
			c=(Course)Instance.Instantiation(c, objectPool.om);
			courseList.add(c);
		}
		rs.close();
		pstmt.close();
		return courseList;
		
		
	}
}
