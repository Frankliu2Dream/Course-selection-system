package controller;

import models.TimeSlot;
import Dao.GetTimeSlotIdDAO;

public class InitTimeSlot {
	public TimeSlot init(int day,int order) throws Exception{
		if(day>0&&day<=7&&order>0&&order<=5)
		{
			GetTimeSlotIdDAO dao=new GetTimeSlotIdDAO();
			int timeslotId=dao.getId(day, order);
			TimeSlot ts=new TimeSlot();
			ts.setClassorder(order);
			ts.setDay(day);
			ts.setId(timeslotId);
			return ts;
		}
		else
		{
			System.out.println("课程时间不合法！");
			return null;
		}
	}
}
