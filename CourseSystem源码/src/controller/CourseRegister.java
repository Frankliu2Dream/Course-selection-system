package controller;

import models.Course;
import models.Teacher;
import Dao.CourseRegisterDAO;
import Dao.TeacherCourseRegisterDAO;

public class CourseRegister {
	public boolean register(Course c) throws Exception{
		CourseRegisterDAO crd= new CourseRegisterDAO();
		TeacherCourseRegisterDAO tcr=new TeacherCourseRegisterDAO();
		
		int c_id=crd.register(c);
		if(c_id==0)
		{
			return false;
		}
		else{
			int t_id=c.getTeacher().getId();
			int flag=tcr.register(t_id, c_id);
			if(flag==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		
	}
}
