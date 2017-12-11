package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


import util.DBHelper;

public class DropCourseDAO {
	public int drop(int s_id,int c_id) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		String sql="delete from student_course where s_id="+s_id+" and c_id= "+c_id+";";
		pstmt=conn.prepareStatement(sql);
		int flag=pstmt.executeUpdate();
		pstmt.close();
		return flag	;
	}
}
