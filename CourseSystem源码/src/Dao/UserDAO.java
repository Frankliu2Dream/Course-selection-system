package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Student;
import models.Teacher;
import util.DBHelper;


/*
 * DAO成员UserLogin用来处理用户登录请求信息
 * StudentLogin方法用来处理学生登录信息
 * TeacherLogin方法用来处理教师登录信息
 * 传入参数：学生/教师账号（int 类型） 和 密码(String) 类型
 * 返回：Student 对象和 Teacher对象
 * 
 */
public class UserDAO {
	public Student StudentLogin(int id,String password)throws Exception{
		
		//获取数据库连接
		Connection conn=DBHelper.getConnection();
	  	PreparedStatement pstmt=null;
	 	ResultSet rs=null;
	 	
	 	//查询数据库该用户是否存在
	 	String sql=new String("select * from student where id="+id+" and password= "+"\'"+password+"\';");
	  	pstmt=conn.prepareStatement(sql);
	  	rs=pstmt.executeQuery();
	  	
	  	//如果用户存在，实例化该用户并返回student对象
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
