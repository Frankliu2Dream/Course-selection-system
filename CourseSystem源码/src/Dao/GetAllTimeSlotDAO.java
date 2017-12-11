package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectStorage.objectPool;

import models.TimeSlot;
import util.DBHelper;

public class GetAllTimeSlotDAO {
	
	public ArrayList<TimeSlot> get() throws Exception{
	Connection conn=DBHelper.getConnection();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql=null;
	
	ArrayList<TimeSlot> TimeSlots=new ArrayList<TimeSlot>();
	sql="select * from timeslot";
	pstmt=conn.prepareStatement(sql);
	rs=pstmt.executeQuery();
	while(rs.next()){
		TimeSlot ts=new TimeSlot();
		int ts_id=rs.getInt(0);
		ts.setId(ts_id);
		Instance.Instantiation(ts, objectPool.om);
		TimeSlots.add(ts);
	}
	return TimeSlots;
	}}
	