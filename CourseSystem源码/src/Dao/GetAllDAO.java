package Dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectStorage.objectPool;

import com.sun.org.apache.xml.internal.utils.ObjectPool;

import util.DBHelper;

public class GetAllDAO {
	public ArrayList<Object> getAll(Object obj) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		ArrayList<Object> resultList=new ArrayList<Object>();
		
		Class cl=obj.getClass();
		String clName=cl.getName();
		String[] tempArray=clName.split("\\.");
		String tableName=tempArray[tempArray.length-1].toLowerCase();
		Method setId=cl.getMethod("setId", int.class);
		
		sql="select * from "+tableName+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			Object t_o=cl.newInstance();
			int id=rs.getInt("id");
			setId.invoke(t_o, id);
			Instance.Instantiation(t_o, objectPool.om);
			resultList.add(t_o);
		}
		rs.close();
		pstmt.close();
		return resultList;
		
	}
}
