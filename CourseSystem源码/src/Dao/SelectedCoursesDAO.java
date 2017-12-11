package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectStorage.objectPool;

import models.Course;
import util.DBHelper;

public class SelectedCoursesDAO {
	public ArrayList<Course> getSelectedCourses(int s_id) throws Exception{
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		String sql="select * from student_course where s_id= "+s_id+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		ArrayList<Course> courseList=new ArrayList<Course>();
		while(rs.next())
		{
			Course c=new Course();
			c.setId(rs.getInt("c_id"));
			Instance.Instantiation(c, objectPool.om);
			courseList.add(c);
		}
		return courseList;
		
	}
}
