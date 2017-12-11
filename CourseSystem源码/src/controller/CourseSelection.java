package controller;

import Dao.CourseSelectionDAO;

public class CourseSelection {
	public boolean select(int s_id,int c_id) throws Exception
	{
		CourseSelectionDAO csd=new CourseSelectionDAO();
		int flag=csd.select(s_id, c_id);
		if(flag==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
