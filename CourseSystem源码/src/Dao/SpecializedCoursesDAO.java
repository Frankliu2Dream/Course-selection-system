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
 * 专业课查询DAO，查询返回结果为学生的所有专业课信息（不区分是否已经选择其中的课课程）
 * 传入：学生学号（int类型）
 * 调用 getCourseList()方法，该方法先实例化（import）该学生，获得学生所在院系，再在数据库中查询该院系的所有课程，返回ArrayList<Course>
 * 
 * 
 */
public class SpecializedCoursesDAO {
	@SuppressWarnings("null")
	public ArrayList<Course> getCourseList(int student_id) throws Exception{
		//objectMap om=new objectMap();//!!!!
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		Student s=new Student();
		s.setId(student_id);
		s=(Student)Instance.Instantiation(s, objectPool.om);
		int dept_id=s.getDepartment().getId();
		String sql=new String("select * from course where department="+dept_id+";");
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
