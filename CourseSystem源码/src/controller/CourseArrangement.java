package controller;

import java.util.ArrayList;
import java.util.HashMap;

import models.Classroom;
import models.Course;
import models.Teacher;
import models.TimeSlot;
import Dao.GetCoursePreferenceDAO;
import Dao.InstanceTimeSlotDAO;

/*
 * ��:CourseArrangement
 * ������private����:
 * 		1.private int getLastDay(ArrayList<Course> courses),courseListΪһ��teacher���������ſογ̵ļ���
 * 			��ø�courseList�����һ�ſγ̵��Ͽ��գ�day��
 * 		2.private int orderDay(int former_day)    former_day��ĳ��ʦ��һ��ע��γ̵��Ͽ�����
 * 			����1��3��5��2��4��˳���ű��ſε��Ͽ�ʱ��
 * 		3.private boolean hasSameTimeSlot(ArrayList<TimeSlot> list,TimeSlot ts)
 * 			���ts�Ƿ������list��
 * 
 * 		public������
 *      1.main������
 *      	�����㷨����
 *    	  	�����ݿ���ȡ������Ҫ�����ݣ�����arrange��������ӡ�ſν��
 *    
 * 		2.public ArrayList<Course> arrange	(ArrayList<Teacher> teacherList,ArrayList<Course> courseList,
 *			ArrayList<Classroom> classroomList,ArrayList<TimeSlot> timeslotList)
 *			����:������ע���ʦteacherList,������ע��γ�courseList��TimeSlot��ClassroomΪ�գ�,������ע�����classroomList,����
 *			������ʱ���timeSlotList
 *			���������Course����ſΣ�TimeSlot��Classroom���Ա���ֵ����ArrayList<Course>
 */
public class CourseArrangement {
	// ���teacher_courseMap��course[]�����һ�ſγ̵�����
	private int getLastDay(ArrayList<Course> courseList) throws Exception {
		int size = courseList.size();
		if(size==0){
			return 4;
		}
		else{
		Course c = courseList.get(size - 1);
		TimeSlot ts = c.getTimeSlot();
		int day = ts.getDay();
		return day;
		}
	}

	// ˳��������
	private int orderDay(int former_day) {
		if (former_day == 1 || former_day == 3 || former_day == 2) {
			return former_day += 2;
		} else if (former_day == 5) {
			return 2;
		} else if (former_day == 4) {
			return 1;
		} else {
			System.out.println("����1");
			return 0;
		}
	}
	
	//�ж�list���Ǻ���ts
	private boolean hasSameTimeSlot(ArrayList<TimeSlot> list,TimeSlot ts) throws Exception{
		if(list.size()==0)
		{
			return false;
		}
		else{
			for(TimeSlot ts_:list){
				if(ts_.getId()==ts.getId())
				{
					return true;
				}
			}
			return false;
		
		}
		
	}
	
	
	public static void main(String args[]) throws Exception{
		//�㷨���Ժ���
		GetAllObjects getter=new GetAllObjects();//GetAllObjectsΪ��ȡĳһ��model����ʵ���ķ���������DAO�����ݿ���ȡ����model����ʵ��
		
		ArrayList<Object> objList=new ArrayList<Object>();
		
		//��ȡ����Classroom
		ArrayList<Classroom> allClassrooms=new ArrayList<Classroom>();
		Classroom cr=new Classroom();
		objList=getter.get(cr);
		for(Object o:objList){
			Classroom cr1=new Classroom();
			cr1=(Classroom)o;
			allClassrooms.add(cr1);
		}
		
		//��ȡ����Teacher
		ArrayList<Teacher> allTeachers=new ArrayList<Teacher>();
		Teacher t=new Teacher();
		objList=getter.get(t);
		for(Object o:objList){
			Teacher t1=new Teacher();
			t1=(Teacher)o;
			allTeachers.add(t1);
		}
		
		//��ȡ����TimeSlot
		ArrayList<TimeSlot> allTimeSlots=new ArrayList<TimeSlot>();
		TimeSlot ts=new TimeSlot();
		objList=getter.get(ts);
		for(Object o:objList){
			TimeSlot ts1=new TimeSlot();
			ts1=(TimeSlot)o;
			allTimeSlots.add(ts1);
		}
		
		//��ȡ����Course
		ArrayList<Course> allCourses=new ArrayList<Course>();
		Course c=new Course();
		objList=getter.get(c);
		for(Object o:objList){
			Course c1=new Course();
			c1=(Course)o;
			allCourses.add(c1);
		}
		
		//�����ſβ���ӡ���
		CourseArrangement ca=new CourseArrangement();
		ArrayList<Course> resultCourses=new ArrayList<Course>();
		resultCourses=ca.arrange(allTeachers, allCourses, allClassrooms, allTimeSlots);
		for(Course course:resultCourses)
		{
			System.out.println(course.toString());
		}
	}
	
	
	
	public ArrayList<Course> arrange	
		(		ArrayList<Teacher> teacherList,
				ArrayList<Course> courseList,
				ArrayList<Classroom> classroomList,
				ArrayList<TimeSlot> timeslotList
		)	throws Exception 
	{//�㷨��ʼ
		
		//����������Ҫ��DAO
			//��DAO���ڸ���day��classorder��ʵ����timeslot
			InstanceTimeSlotDAO ITSdao = new InstanceTimeSlotDAO();
			//��DAO����һ��Course�����ظ�Course��Preference
			GetCoursePreferenceDAO GCPdao = new GetCoursePreferenceDAO();
			
			
		//���������Course��ArrayList
		ArrayList<Course> resultCourses = new ArrayList<Course>();
		
		
		//������Ҫ�õ���HashMap
			//��Map���ڴ洢һ��Teacher�����Ѿ��źõĵĿγ�,��ÿһ��Teacher����һ���յ�ArrayList<Course>
			HashMap<Integer, ArrayList<Course>> TeacherCourseMap = new HashMap<Integer, ArrayList<Course>>();
			for (Teacher t : teacherList) {
				ArrayList<Course> courselist = new ArrayList<Course>();
				TeacherCourseMap.put((Integer)t.getId(),courselist);
			}
			
			//��Map���ڴ���ÿһ��timeslot�Ѿ�ռ�õ�Classroom�б������ArrayList<Classroom>Ϊ��
			HashMap<Integer, ArrayList<Classroom>> TimeslotClassroomMap = new HashMap<Integer, ArrayList<Classroom>>();
			for (TimeSlot ts : timeslotList) {
				ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
				TimeslotClassroomMap.put((Integer)ts.getId(), classrooms);
			}

		//��ʼ�����ſβ���
		outer:
		while (courseList.size() > 0) {
			//��courseList�г�ȡ����һ�ſγ̣�����Ϊcurrent_Course
			Course current_Course = courseList.get(0);
			Teacher current_Teacher = current_Course.getTeacher();
			//System.out.println(TeacherCourseMap.get(current_Course.getTeacher()));
			//��øÿγ̵�preference
			int preference = GCPdao.getPreference(current_Course);
			
			//��ʼ��day�ڵ�
			int day1 = getLastDay(TeacherCourseMap.get((Integer)current_Teacher.getId()));//day1�Ǹ�Teacher��һ�ſγ̵�day
			int day2 = orderDay(day1);//day2�ǵ�һ��ѭ��ʱ��ʱ��
			int day3 = day2;//day3�ǵ�ǰ���д�����¼�
			
			//����preferredTimeSlot�б���������ѡ���TimeSlot
			ArrayList<TimeSlot> preferredTimeSlots = new ArrayList<TimeSlot>();
			if (preference == 0) {
				for (int x = 1; x <= 5; x++) {
					TimeSlot ts = new TimeSlot();
					ts.setDay(day3);
					ts.setClassorder(x);
					ITSdao.instance(ts);
					preferredTimeSlots.add(ts);
				}
			} else if (preference == 1) {
				for (int x = 1; x <= 2; x++) {
					TimeSlot ts = new TimeSlot();
					ts.setDay(day3);
					ts.setClassorder(x);
					ITSdao.instance(ts);
					preferredTimeSlots.add(ts);
				}
			} else if (preference == 2) {
				for (int x = 4; x <= 5; x++) {
					TimeSlot ts = new TimeSlot();
					ts.setDay(day3);
					ts.setClassorder(x);
					ITSdao.instance(ts);
					preferredTimeSlots.add(ts);
				}
			} else {
				System.out.println("����2");
			}
			
			
			//��ʼѭ��
			inner: 
			for (; true;) 
			{
				//����tsList���day3������timeslot
				ArrayList<TimeSlot> tsList = new ArrayList<TimeSlot>();
				for (int x = 1; x <= 5; x++) {

					TimeSlot ts = new TimeSlot();
					ts.setDay(day3);
					ts.setClassorder(x);
					ITSdao.instance(ts);
					tsList.add(ts);
				}
				
				
				//check��day3���з���capacity�Ŀս��ң�������day3_valid_classroom��HashMap��
				FilterClassroom check=new FilterClassroom();
 				HashMap<Integer, ArrayList<Classroom>> day3_valid_classroom =check.filter(
						tsList, current_Course.getCapacity(),
						TimeslotClassroomMap, classroomList);

				
				//��day3_valid_classroom�����в�Ϊ�յ�ʱ��η�����ΪvalidTime��timeslot��List��
				//������validTime�б��е�timeslot���н��ҿ���ѡ��
				ArrayList<TimeSlot> validTime = new ArrayList<TimeSlot>();
				for (TimeSlot ts : tsList) {
					if (day3_valid_classroom.get(ts.getId()).size() > 0) {
						validTime.add(ts);
					}
				}

				//��validTime��Ϊ�գ���day3���ٺ���һ��ʱ�����valid�Ŀս��ң������ſ�
				if (validTime.size() > 0) 
				{
									
					//isFinished���������鿴�Ƿ��Ѿ������ſβ������������ȿ���preferredʱ��
					boolean isFinished = false;
					
					//����preferred��Timeslot�ڽ���ѭ���鿴
					for (TimeSlot ts : preferredTimeSlots) 
					{
						if (hasSameTimeSlot(preferredTimeSlots,ts)) 
						{
							{
								Classroom selectedClassroom = day3_valid_classroom.get(ts.getId()).get(0);
								current_Course.setClassroom(selectedClassroom);
								current_Course.setTimeSlot(ts);
								resultCourses.add(current_Course);
								courseList.remove(current_Course);
								TeacherCourseMap.get(current_Teacher.getId()).add(current_Course);
								TimeslotClassroomMap.get(ts.getId()).add(selectedClassroom);
								isFinished = true;
								continue outer;
							}
						}
					}
					//preferred��timeslot������validTime�У���ʣ���timeslot����ѭ��
					if (isFinished == false) 
					{
						for (TimeSlot ts : validTime) 
						{
							Classroom selectedClassroom = day3_valid_classroom
									.get(ts.getId()).get(0);
							current_Course.setClassroom(selectedClassroom);
							current_Course.setTimeSlot(ts);
							resultCourses.add(current_Course);
							courseList.remove(current_Course);
							TeacherCourseMap.get(current_Teacher.getId()).add(
									current_Course);
							TimeslotClassroomMap.get(ts.getId())
									.add(selectedClassroom);
							isFinished = true;
							continue outer;
						}
					}
				}
				
				
				//day3������valid�ս���
				//�ȼ���Ƿ������ٵĿγ�ռ����ҵ����,��ز���
				//û�еĻ���day3�������
				else 
				{
					boolean isFinished=false;
					//ts_list��������day3������timeslot
					ArrayList<TimeSlot> ts_list = new ArrayList<TimeSlot>();
					for (int x = 1; x <= 5; x++) {

						TimeSlot ts = new TimeSlot();
						ts.setDay(day3);
						ts.setClassorder(x);
						ITSdao.instance(ts);
						ts_list.add(ts);
						isFinished = false;
					
					}
					//isFinished���Ϊtrue����ʾ�Ѿ��������ſΣ�����Ҫ�Ӻ�ʱ��
					
					
					for (Course c : resultCourses) {
						if (	ts_list.contains(c.getTimeSlot())//����1
								&& c.getClassroom().getCapacity() >= current_Course.getCapacity()//����2
								&& c.getCapacity() < current_Course.getCapacity()//����3
							) 
						{
							Classroom classroom = c.getClassroom();
							TimeSlot timeslot = c.getTimeSlot();
							Teacher teacher = c.getTeacher();
							
							//�˻ظÿγ��ſε����м�¼
							TeacherCourseMap.get(teacher.getId()).remove(c);
							TimeslotClassroomMap.get(timeslot.getId()).remove(classroom);
							resultCourses.remove(c);
							courseList.add(c);
							c.setClassroom(null);
							c.setTimeSlot(null);
							
							//��current_Course�����ſ�
							current_Course.setClassroom(classroom);
							current_Course.setTimeSlot(timeslot);
							TeacherCourseMap.get(current_Teacher.getId()).add(current_Course);
							TimeslotClassroomMap.get(timeslot.getId()).add(classroom);
							isFinished = true;
							continue outer;
						}
					}
					
					if(isFinished==false)
					{
						day3 = orderDay(day3);
						if (day3 == day2) {
							System.out.println("����3");
							break outer;
							}
					
						else 
						{
							continue inner;
						}

					}
				}
								

			}
		}//����whileѭ��
			

		return resultCourses;
	}


}
