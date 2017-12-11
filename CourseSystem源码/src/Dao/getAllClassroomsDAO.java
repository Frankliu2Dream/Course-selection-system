package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectStorage.objectPool;

import models.Classroom;
import util.DBHelper;

public class getAllClassroomsDAO {
	
	public ArrayList<Classroom> get() throws Exception{
	Connection conn=DBHelper.getConnection();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql=null;
	
	ArrayList<Classroom> classrooms=new ArrayList<Classroom>();
	sql="select * from classroom";
	pstmt=conn.prepareStatement(sql);
	rs=pstmt.executeQuery();
	while(rs.next()){
		Classroom cr=new Classroom();
		int cr_id=rs.getInt(0);
		cr.setId(cr_id);
		Instance.Instantiation(cr, objectPool.om);
		classrooms.add(cr);
	}
	rs.close();
	pstmt.close();
	return classrooms;
	}
	

}
