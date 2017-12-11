package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;

public class TeacherCourseRegisterDAO {
	public int register(int t_id,int c_id) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		String sql=null;
		sql="insert into teacher_course(t_id,c_id) values"+"("+t_id+","+c_id+");";
		pstmt=conn.prepareStatement(sql);
		int flag=pstmt.executeUpdate();
		pstmt.close();
		return flag;
	}
}
