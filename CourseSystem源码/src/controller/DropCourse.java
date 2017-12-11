package controller;

import Dao.DropCourseDAO;

public class DropCourse {
	public boolean dropCourse(int sid,int cid)throws Exception{
		DropCourseDAO dcd= new DropCourseDAO();
		int flag=dcd.drop(sid, cid);
		if(flag==0)return false;
		else return true;
	}
}
