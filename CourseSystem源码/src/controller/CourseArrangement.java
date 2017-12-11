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
 * 类:CourseArrangement
 * 方法：private方法:
 * 		1.private int getLastDay(ArrayList<Course> courses),courseList为一个teacher的所有已排课课程的集合
 * 			获得该courseList的最后一门课程的上课日（day）
 * 		2.private int orderDay(int former_day)    former_day是某教师上一门注册课程的上课日子
 * 			按照1，3，5，2，4的顺序安排本门课的上课时间
 * 		3.private boolean hasSameTimeSlot(ArrayList<TimeSlot> list,TimeSlot ts)
 * 			检查ts是否存在与list中
 * 
 * 		public方法：
 *      1.main方法：
 *      	进行算法测试
 *    	  	从数据库中取出所需要的数据，调用arrange函数，打印排课结果
 *    
 * 		2.public ArrayList<Course> arrange	(ArrayList<Teacher> teacherList,ArrayList<Course> courseList,
 *			ArrayList<Classroom> classroomList,ArrayList<TimeSlot> timeslotList)
 *			输入:所有以注册教师teacherList,所有已注册课程courseList（TimeSlot和Classroom为空）,所有已注册教室classroomList,所有
 *			已生成时间段timeSlotList
 *			输出：所有Course完成排课（TimeSlot和Classroom属性被赋值）的ArrayList<Course>
 */
public class CourseArrangement {
	// 获得teacher_courseMap的course[]中最后一门课程的日子
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

	// 顺序排日期
	private int orderDay(int former_day) {
		if (former_day == 1 || former_day == 3 || former_day == 2) {
			return former_day += 2;
		} else if (former_day == 5) {
			return 2;
		} else if (former_day == 4) {
			return 1;
		} else {
			System.out.println("错误1");
			return 0;
		}
	}
	
	//判断list中是含有ts
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
		//算法测试函数
		GetAllObjects getter=new GetAllObjects();//GetAllObjects为获取某一类model所有实例的方法，调用DAO从数据库中取出该model所有实例
		
		ArrayList<Object> objList=new ArrayList<Object>();
		
		//获取所有Classroom
		ArrayList<Classroom> allClassrooms=new ArrayList<Classroom>();
		Classroom cr=new Classroom();
		objList=getter.get(cr);
		for(Object o:objList){
			Classroom cr1=new Classroom();
			cr1=(Classroom)o;
			allClassrooms.add(cr1);
		}
		
		//获取所有Teacher
		ArrayList<Teacher> allTeachers=new ArrayList<Teacher>();
		Teacher t=new Teacher();
		objList=getter.get(t);
		for(Object o:objList){
			Teacher t1=new Teacher();
			t1=(Teacher)o;
			allTeachers.add(t1);
		}
		
		//获取所有TimeSlot
		ArrayList<TimeSlot> allTimeSlots=new ArrayList<TimeSlot>();
		TimeSlot ts=new TimeSlot();
		objList=getter.get(ts);
		for(Object o:objList){
			TimeSlot ts1=new TimeSlot();
			ts1=(TimeSlot)o;
			allTimeSlots.add(ts1);
		}
		
		//获取所有Course
		ArrayList<Course> allCourses=new ArrayList<Course>();
		Course c=new Course();
		objList=getter.get(c);
		for(Object o:objList){
			Course c1=new Course();
			c1=(Course)o;
			allCourses.add(c1);
		}
		
		//进行排课并打印结果
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
	{//算法开始
		
		//载入所有需要的DAO
			//该DAO用于给出day和classorder，实例化timeslot
			InstanceTimeSlotDAO ITSdao = new InstanceTimeSlotDAO();
			//该DAO输入一个Course，返回该Course的Preference
			GetCoursePreferenceDAO GCPdao = new GetCoursePreferenceDAO();
			
			
		//创建输出的Course的ArrayList
		ArrayList<Course> resultCourses = new ArrayList<Course>();
		
		
		//创建所要用到的HashMap
			//该Map用于存储一个Teacher所有已经排好的的课程,给每一个Teacher创建一个空的ArrayList<Course>
			HashMap<Integer, ArrayList<Course>> TeacherCourseMap = new HashMap<Integer, ArrayList<Course>>();
			for (Teacher t : teacherList) {
				ArrayList<Course> courselist = new ArrayList<Course>();
				TeacherCourseMap.put((Integer)t.getId(),courselist);
			}
			
			//该Map用于储存每一个timeslot已经占用的Classroom列表，起初该ArrayList<Classroom>为空
			HashMap<Integer, ArrayList<Classroom>> TimeslotClassroomMap = new HashMap<Integer, ArrayList<Classroom>>();
			for (TimeSlot ts : timeslotList) {
				ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
				TimeslotClassroomMap.put((Integer)ts.getId(), classrooms);
			}

		//开始进行排课操作
		outer:
		while (courseList.size() > 0) {
			//从courseList中抽取出第一门课程，设置为current_Course
			Course current_Course = courseList.get(0);
			Teacher current_Teacher = current_Course.getTeacher();
			//System.out.println(TeacherCourseMap.get(current_Course.getTeacher()));
			//获得该课程的preference
			int preference = GCPdao.getPreference(current_Course);
			
			//初始化day节点
			int day1 = getLastDay(TeacherCourseMap.get((Integer)current_Teacher.getId()));//day1是该Teacher上一门课程的day
			int day2 = orderDay(day1);//day2是第一次循环时的时间
			int day3 = day2;//day3是当前进行处理的事件
			
			//创建preferredTimeSlot列表，放置优先选择的TimeSlot
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
				System.out.println("错误2");
			}
			
			
			//开始循环
			inner: 
			for (; true;) 
			{
				//创建tsList存放day3的所有timeslot
				ArrayList<TimeSlot> tsList = new ArrayList<TimeSlot>();
				for (int x = 1; x <= 5; x++) {

					TimeSlot ts = new TimeSlot();
					ts.setDay(day3);
					ts.setClassorder(x);
					ITSdao.instance(ts);
					tsList.add(ts);
				}
				
				
				//check出day3所有符合capacity的空教室，储存在day3_valid_classroom的HashMap中
				FilterClassroom check=new FilterClassroom();
 				HashMap<Integer, ArrayList<Classroom>> day3_valid_classroom =check.filter(
						tsList, current_Course.getCapacity(),
						TimeslotClassroomMap, classroomList);

				
				//将day3_valid_classroom中所有不为空的时间段放入名为validTime的timeslot的List中
				//即所有validTime列表中的timeslot都有教室可以选择
				ArrayList<TimeSlot> validTime = new ArrayList<TimeSlot>();
				for (TimeSlot ts : tsList) {
					if (day3_valid_classroom.get(ts.getId()).size() > 0) {
						validTime.add(ts);
					}
				}

				//当validTime不为空，即day3至少含有一个时间段有valid的空教室，进行排课
				if (validTime.size() > 0) 
				{
									
					//isFinished变量用来查看是否已经进行排课操作，用于优先考虑preferred时间
					boolean isFinished = false;
					
					//先在preferred的Timeslot内进行循环查看
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
					//preferred的timeslot不存在validTime中，对剩余的timeslot进行循环
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
				
				
				//day3不存在valid空教室
				//先检查是否有人少的课程占大教室的情况,相关操作
				//没有的话，day3向后延期
				else 
				{
					boolean isFinished=false;
					//ts_list用来储存day3的所有timeslot
					ArrayList<TimeSlot> ts_list = new ArrayList<TimeSlot>();
					for (int x = 1; x <= 5; x++) {

						TimeSlot ts = new TimeSlot();
						ts.setDay(day3);
						ts.setClassorder(x);
						ITSdao.instance(ts);
						ts_list.add(ts);
						isFinished = false;
					
					}
					//isFinished如果为true，表示已经进行了排课，不需要延后时间
					
					
					for (Course c : resultCourses) {
						if (	ts_list.contains(c.getTimeSlot())//条件1
								&& c.getClassroom().getCapacity() >= current_Course.getCapacity()//条件2
								&& c.getCapacity() < current_Course.getCapacity()//条件3
							) 
						{
							Classroom classroom = c.getClassroom();
							TimeSlot timeslot = c.getTimeSlot();
							Teacher teacher = c.getTeacher();
							
							//退回该课程排课的所有记录
							TeacherCourseMap.get(teacher.getId()).remove(c);
							TimeslotClassroomMap.get(timeslot.getId()).remove(classroom);
							resultCourses.remove(c);
							courseList.add(c);
							c.setClassroom(null);
							c.setTimeSlot(null);
							
							//对current_Course进行排课
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
							System.out.println("错误3");
							break outer;
							}
					
						else 
						{
							continue inner;
						}

					}
				}
								

			}
		}//结束while循环
			

		return resultCourses;
	}


}
