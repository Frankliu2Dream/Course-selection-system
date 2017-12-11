package controller;

import java.util.ArrayList;

import models.Course;
import Dao.RegisteredCoursesDAO;

public class RegisteredCourses {
	public ArrayList<Course> getCourses(int t_id) throws Exception{
		RegisteredCoursesDAO dao=new RegisteredCoursesDAO();
		return dao.getRegisteredCourses(t_id);
	}
}
