package controller;

import java.util.ArrayList;

import models.Course;
import Dao.CommonCoursesDAO;
import Dao.SpecializedCoursesDAO;

public class CourseList {
	public ArrayList<Course> getCourseList(String courseType,int s_id) throws Exception{
		if("commonCourse".equals(courseType))
		{
			CommonCoursesDAO ccd=new CommonCoursesDAO();
			return ccd.getCourseList();
		}
		else
		{
			SpecializedCoursesDAO scd=new SpecializedCoursesDAO();
			return scd.getCourseList(s_id);
		}
	}
}
