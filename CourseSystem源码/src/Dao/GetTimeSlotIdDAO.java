package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;

public class GetTimeSlotIdDAO {
	public int getId(int day,int order) throws Exception{
		Connection conn=DBHelper.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		sql="select id from timeslot where day="+day+" and classorder= "+order+";";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			int id= rs.getInt(1);
			pstmt.close();
			rs.close();
			return id;
		}
		else
		{
			pstmt.close();
			rs.close();
			return 0;
		}
	}
}
