package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import models.Course;

public class GetCoursePreferenceDAO {
	public int getPreference(Course c) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		
		int c_id=c.getId();
		sql="select preference from course_preference where c_id="+c_id+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()){
			if(rs.getInt(1)==0){
				return 0;
			}
			else if(rs.getInt(1)==1)
			{
				return 1;
			}
			else if(rs.getInt(1)==2)
			{
				return 2;
			}
			else
			{
				return 0;
			}
		
		}
		else
			return 0;
		
	}
}
