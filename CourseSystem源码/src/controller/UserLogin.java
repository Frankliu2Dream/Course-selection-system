package controller;

import models.Student;
import models.Teacher;
import Dao.UserDAO;

public class UserLogin {
	
	public Student StudentLogin(int id,String password) throws Exception{
		UserDAO userDao=new UserDAO();
		return userDao.StudentLogin(id, password);
	}
	
	public Teacher TeacherLogin(int id,String password) throws Exception{
		UserDAO userDao=new UserDAO();
		return userDao.TeacherLogin(id, password);
	}
}
