package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectStorage.objectPool;

import models.Course;
import models.Student;
import models.StudentCourse;

import util.DBHelper;

public class CourseStudentsDAO {
	public ArrayList<StudentCourse> getList(int c_id) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		
		sql="select * from student_course where c_id="+c_id+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		ArrayList<StudentCourse> scList=new ArrayList<StudentCourse>();
		while(rs.next())
		{
			StudentCourse sc=new StudentCourse();
			Student s=new Student();
			Course c=new Course();
			s.setId(rs.getInt("s_id"));
			c.setId(rs.getInt("c_id"));
			Instance.Instantiation(s, objectPool.om);
			Instance.Instantiation(c, objectPool.om);
			sc.setStudent(s);
			sc.setCourse(c);
			sc.setGrade(rs.getInt("grade"));
			scList.add(sc);
		}
		rs.close();
		pstmt.close();
		return scList;
	}
}
