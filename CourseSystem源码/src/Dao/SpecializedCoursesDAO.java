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
 * רҵ�β�ѯDAO����ѯ���ؽ��Ϊѧ��������רҵ����Ϣ���������Ƿ��Ѿ�ѡ�����еĿογ̣�
 * ���룺ѧ��ѧ�ţ�int���ͣ�
 * ���� getCourseList()�������÷�����ʵ������import����ѧ�������ѧ������Ժϵ���������ݿ��в�ѯ��Ժϵ�����пγ̣�����ArrayList<Course>
 * 
 * 
 */
public class SpecializedCoursesDAO {
	@SuppressWarnings("null")
	public ArrayList<Course> getCourseList(int student_id) throws Exception{
		//objectMap om=new objectMap();//!!!!
		Connection conn=DBHelper.getConnection();
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		Student s=new Student();
		s.setId(student_id);
		s=(Student)Instance.Instantiation(s, objectPool.om);
		int dept_id=s.getDepartment().getId();
		String sql=new String("select * from course where department="+dept_id+";");
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
