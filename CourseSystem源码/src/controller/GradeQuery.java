package controller;

import java.util.ArrayList;

import models.StudentCourse;
import Dao.GradeQueryDAO;

public class GradeQuery {
	public ArrayList<StudentCourse> getGrades(int sid) throws Exception{
		GradeQueryDAO gqd=new GradeQueryDAO();
		return gqd.getGrades(sid);
	}
}
