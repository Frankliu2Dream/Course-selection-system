package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import models.Course;
import models.Department;
import models.Teacher;
import models.TimeSlot;
//·µ»Ø¿Î³Ìid
public class CourseRegisterDAO {
	public int	register(Course course) throws Exception{
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		String name=course.getName();
		int teacher_id=course.getTeacher().getId();
		int dept_id=course.getDepartment().getId();
		int time_slot=course.getTimeSlot().getId();
		int capacity=course.getCapacity();
		sql="insert into course(capacity,name,teacher,timeslot,department) values("+capacity+"," +
				"\""+name+"\""+","+teacher_id+","+time_slot+","+dept_id+");";
		
		pstmt=conn.prepareStatement(sql);
		int flag=pstmt.executeUpdate(sql);
		if(flag==0)
		{
			return 0;
		}
		
		sql="select last_insert_id();";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next();
		return rs.getInt(1);
		
	}
	
}	
