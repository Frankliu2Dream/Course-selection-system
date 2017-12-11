package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import models.TimeSlot;

public class InstanceTimeSlotDAO {
	public TimeSlot instance(TimeSlot ts) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		
		int day=ts.getDay();
		int order=ts.getClassorder();
		sql="select id from timeslot where day="+day+" and classorder="+order+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			int id=rs.getInt(1);
			ts.setId(id);
			return ts;
		}
		else
		{
			System.out.println("InstanceTimeSlotDAO²éÑ¯Îª¿Õ");
			return null;
		}
		
	}
}
