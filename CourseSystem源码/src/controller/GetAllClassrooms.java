package controller;

import java.util.ArrayList;

import models.Classroom;
import Dao.getAllClassroomsDAO;

public class GetAllClassrooms {
	public ArrayList<Classroom> getAllClassrooms() throws Exception{
		getAllClassroomsDAO dao=new getAllClassroomsDAO();
		return dao.get();
	}
}
