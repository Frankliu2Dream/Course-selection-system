package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import models.StudentCourse;

public class UpdateStudentCourseDAO {
	public boolean update(StudentCourse sc) throws Exception{
		int s_id=sc.getStudent().getId();
		int c_id=sc.getCourse().getId();
		int grade=sc.getGrade();
		
		Connection conn=DBHelper.getConnection();

		PreparedStatement pstmt=null;
		String sql="update student_course set grade=" +grade+" where s_id= "+s_id+" and c_id= "+c_id+";";
		pstmt=conn.prepareStatement(sql);
		int flag=pstmt.executeUpdate();
		if(flag==1)
		{
			pstmt.close();
			return true;
		}
		else
		{
			pstmt.close();
			return false;
		}
	}
}
