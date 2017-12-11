package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
/*
 * 选课DAO，包含一个方法select
 * select 方法传入参数为学生id和课程id
 * 在数据库中的student_course列表中查询是否已经存在该条目，如果存在的或说明学生已经选过该门课程，返回0
 * 没有选过这门课，进行选课操作（向student_course表中插入条目），如果成功返回1，如果数据库操作失败返回0
 * 
 */
public class CourseSelectionDAO {
		public int select(int student_id,int course_id) throws Exception
		{
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=null;
			
			
			
			//查询学科结果表student_course中是否已经存在该条记录
			sql="select * from student_course where s_id="+student_id+" and c_id="+course_id+";";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//数据库中已经存在该条目
			if(rs.next()){
				System.out.println("您以选择该课程，请检查您的选课结果！");
				rs.close();
				pstmt.close();
				return 0;
			}
			
			//数据库中没有该条目，进行选课操作
			else
			{
				//学生没有选过该课程，进行选课操作
				sql="insert into student_course(s_id,c_id) values("+student_id+","+course_id+");";
				pstmt=conn.prepareStatement(sql);
				if(pstmt.executeUpdate()!=0)
				{
					System.out.println("选课成功！");
					rs.close();
					pstmt.close();
					return 1;
				}
				else
				{
					System.out.println("数据库操作失败！");
					rs.close();
					pstmt.close();
					return 0;
				}
			}
		}
}
