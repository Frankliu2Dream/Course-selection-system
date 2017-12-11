package controller;

import java.util.ArrayList;

import models.TimeSlot;
import Dao.GetAllTimeSlotDAO;

public class GetAllTimeSlots {
	public ArrayList<TimeSlot> getAllTimeSlots() throws Exception{
		GetAllTimeSlotDAO dao=new GetAllTimeSlotDAO();
		return dao.get();
	}
}
