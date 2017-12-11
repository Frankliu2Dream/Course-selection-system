package controller;

import java.util.ArrayList;

import models.Course;
import Dao.SelectedCoursesDAO;

public class SelectedCourses {
	public ArrayList<Course> getCourses(int s_id) throws Exception{
		SelectedCoursesDAO scd=new SelectedCoursesDAO();
		return scd.getSelectedCourses(s_id);
	}
}
