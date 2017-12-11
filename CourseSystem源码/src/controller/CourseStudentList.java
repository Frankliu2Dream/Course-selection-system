package controller;

import java.util.ArrayList;

import models.StudentCourse;
import Dao.CourseStudentsDAO;

public class CourseStudentList {
	public ArrayList<StudentCourse> getList(int c_id) throws Exception{
		CourseStudentsDAO dao=new CourseStudentsDAO();
		return dao.getList(c_id);
	}
}
