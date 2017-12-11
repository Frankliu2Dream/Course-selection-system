package controller;

import models.StudentCourse;
import Dao.UpdateStudentCourseDAO;

public class SetGrade {
	public boolean set(StudentCourse sc) throws Exception{
		UpdateStudentCourseDAO dao=new UpdateStudentCourseDAO();
		return dao.update(sc);
	}
}
