package controller;

import models.Student;
import models.Teacher;
import Dao.CheckUserDAO;
import Dao.FieldInsertDAO;
import Dao.Storage;

public class UserRegister {
	public boolean register(Object user,String password) throws Exception{
		CheckUserDAO userCheck=new CheckUserDAO();
		FieldInsertDAO insert=new FieldInsertDAO();
		
		boolean flag=false;
		String userIden=user.getClass().getName();
		int id;
		if(userIden.contains("Student"))
		{
			userIden="student";
			Student s=(Student)user;
			id=s.getId();
			flag=userCheck.checkUser("student", s.getId());
		}
		else if(userIden.contains("Teacher"))
		{
			userIden="teacher";
			Teacher t=(Teacher)user;
			id=t.getId();
			flag=userCheck.checkUser("teacher", t.getId());
		}
		else
		{
			System.out.println("传入对象类型错误");
			return false;
		}
		
		
		if(flag==true)
		{
			System.out.println("用户已经存在");
			return false;
		}
		else
		{
			int i=Storage.ItemStorage(user);
			int j=insert.insertStringField(userIden, "password", password, id);
			if(i==1&&j==1){
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
		
	}
}
