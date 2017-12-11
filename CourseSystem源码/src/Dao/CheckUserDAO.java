package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import objectStorage.objectPool;

import models.Student;
import models.Teacher;
import util.DBHelper;

public class CheckUserDAO {
	public boolean checkUser(String type,int id) throws Exception
	{
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		if("student".equals(type))
		{
			String sql="select * from student where id="+id+";";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		}
		
		if("teacher".equals(type)){
			String sql="select * from teacher where id="+id+";";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		}
		
		if(rs.next()==true)
		{
			rs.close();
			pstmt.close();
			return true;
		}
		else
		{
			rs.close();
			pstmt.close();
			return false;
		}
		
	}
}
