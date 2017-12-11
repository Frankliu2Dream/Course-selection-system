package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Student;
import models.Teacher;
import util.DBHelper;


/*
 * DAO��ԱUserLogin���������û���¼������Ϣ
 * StudentLogin������������ѧ����¼��Ϣ
 * TeacherLogin�������������ʦ��¼��Ϣ
 * ���������ѧ��/��ʦ�˺ţ�int ���ͣ� �� ����(String) ����
 * ���أ�Student ����� Teacher����
 * 
 */
public class UserDAO {
	public Student StudentLogin(int id,String password)throws Exception{
		
		//��ȡ���ݿ�����
		Connection conn=DBHelper.getConnection();
	  	PreparedStatement pstmt=null;
	 	ResultSet rs=null;
	 	
	 	//��ѯ���ݿ���û��Ƿ����
	 	String sql=new String("select * from student where id="+id+" and password= "+"\'"+password+"\';");
	  	pstmt=conn.prepareStatement(sql);
	  	rs=pstmt.executeQuery();
	  	
	  	//����û����ڣ�ʵ�������û�������student����
	  	Student studentUser=new Student();
	  	if(rs.next())
	  	{
	  		studentUser.setId(rs.getInt(1));
	  		studentUser.setName(rs.getString(2));
	  		rs.close();
			pstmt.close();
	  		return studentUser;
	  	}
	  	else{
	  		rs.close();
			pstmt.close();
	  		return null;
	  	}
	  	
	}
	
	public Teacher TeacherLogin(int id,String password)throws Exception{
		
		
		Connection conn=DBHelper.getConnection();
	  	PreparedStatement pstmt=null;
	 	ResultSet rs=null;
	
	 	String sql=new String("select * from student where id="+id+" and password= "+"\'"+password+"\';");
	  	pstmt=conn.prepareStatement(sql);
	  	rs=pstmt.executeQuery();
	  	
	  	
	  	Teacher teacherUser=new Teacher();
	  	if(rs.next())
	  	{
	  		teacherUser.setId(rs.getInt(1));
	  		teacherUser.setName(rs.getString(2));
	  		rs.close();
			pstmt.close();
	  		return teacherUser;
	  		
	  	}
	  	else
	  	{
	  		rs.close();
			pstmt.close();
	  		return null;
	  	}
	  	
	}
	
}
