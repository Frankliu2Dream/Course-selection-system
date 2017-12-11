package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Course;
import models.Student;
import objectStorage.objectMap;
import objectStorage.objectPool;
import util.DBHelper;

/*
 * Уѡ�β�ѯDAO����ѯ���ؽ��Ϊ����Уѡ����Ϣ���������Ƿ��Ѿ�ѡ�����еĿογ̣�
 * ���룺��
 * ���� getCourseList()�����������ݿ��в�ѯ����Ժϵ�ֶ�Ϊnull�Ŀγ̣���Уѡ�Σ�����ArrayList<Course>
 * 
 * 
 */
public class CommonCoursesDAO {
	@SuppressWarnings("null")
	public ArrayList<Course> getCourseList() throws Exception{
		
		//objectMap om=new objectMap();//!!!!!
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		
		String sql=new String("select * from course where department is null;");
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		ArrayList<Course> courseList=new ArrayList<Course>();
		while(rs.next())
		{
			int courseID=rs.getInt(1);
			Course c=new Course();
			c.setId(courseID);
			c=(Course)Instance.Instantiation(c, objectPool.om);
			courseList.add(c);
		}
		rs.close();
		pstmt.close();
		return courseList;
		
		
	}
}
