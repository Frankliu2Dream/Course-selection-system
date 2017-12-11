package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;

public class FieldInsertDAO {
	public int insertStringField(String tableName,String fieldName,String value,int id)throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		String sql="update "+tableName+" set  "+fieldName+" = \""+value+"\" where id="+id+";";
		pstmt=conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
