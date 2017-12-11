package controller;

import java.util.ArrayList;

import models.Building;
import models.Classroom;
import Dao.GetAllDAO;

public class GetAllObjects {
	public ArrayList<Object> get(Object obj) throws Exception{
		GetAllDAO dao=new GetAllDAO();
		return dao.getAll(obj);
	}
}
