package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
/*
 * ѡ��DAO������һ������select
 * select �����������Ϊѧ��id�Ϳγ�id
 * �����ݿ��е�student_course�б��в�ѯ�Ƿ��Ѿ����ڸ���Ŀ��������ڵĻ�˵��ѧ���Ѿ�ѡ�����ſγ̣�����0
 * û��ѡ�����ſΣ�����ѡ�β�������student_course���в�����Ŀ��������ɹ�����1��������ݿ����ʧ�ܷ���0
 * 
 */
public class CourseSelectionDAO {
		public int select(int student_id,int course_id) throws Exception
		{
			Connection conn=DBHelper.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=null;
			
			
			
			//��ѯѧ�ƽ����student_course���Ƿ��Ѿ����ڸ�����¼
			sql="select * from student_course where s_id="+student_id+" and c_id="+course_id+";";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//���ݿ����Ѿ����ڸ���Ŀ
			if(rs.next()){
				System.out.println("����ѡ��ÿγ̣���������ѡ�ν����");
				rs.close();
				pstmt.close();
				return 0;
			}
			
			//���ݿ���û�и���Ŀ������ѡ�β���
			else
			{
				//ѧ��û��ѡ���ÿγ̣�����ѡ�β���
				sql="insert into student_course(s_id,c_id) values("+student_id+","+course_id+");";
				pstmt=conn.prepareStatement(sql);
				if(pstmt.executeUpdate()!=0)
				{
					System.out.println("ѡ�γɹ���");
					rs.close();
					pstmt.close();
					return 1;
				}
				else
				{
					System.out.println("���ݿ����ʧ�ܣ�");
					rs.close();
					pstmt.close();
					return 0;
				}
			}
		}
}
