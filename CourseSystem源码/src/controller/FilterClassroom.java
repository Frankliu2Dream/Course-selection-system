package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import models.Classroom;
import models.TimeSlot;
/*
 * 类名：FilterClassroom
 * 方法:filter
 * 功能目标：为一个Classroom选择所有满足教室容量（Capacity）的空教室
 * 输入：
 * 		1.数据库中所有的时间段TimeSlot生成的集合allTimeSlotList
 * 		2.目标教室的容量capacity
 * 		3.所有时间段的id和该时间段所有已经占用教室形成的HashMap，即TimeSlotID_ClassroomMap
 * 		4.数据库中所有教室列表allClassroomList
 * 输出：resultMap,该map的key是所有TimeSlot的id，value是该TimeSlot所有筛选出的空教室
 */
//check在allTimeSlotsList中所有时间段是否有符合capacity要求的classroom
//返回的是包含目标allTimeSlotsList中所有timeslot所对应有效classroom的HashMap
	public class FilterClassroom {
		
		public HashMap<Integer, ArrayList<Classroom>>  filter(
		ArrayList<TimeSlot> allTimeSlotsList, 
		int capacity,
		HashMap<Integer, ArrayList<Classroom>> TimeSlotID_ClassroomMap,
		ArrayList<Classroom> allClassroomsList
		) throws Exception {
			
			//初始化返回的resultMap
			HashMap<Integer, ArrayList<Classroom>> resultMap=new HashMap<Integer, ArrayList<Classroom>>();
			
			//对allTimeSlotsList进行循环，每次取出一个TimeSlot,筛选出该时间段的可用空教室，存入HashMap
			for(TimeSlot ts:allTimeSlotsList)
			{
				//先向HashMap中存入该timeSlot的id和一个空的Classroom列表
				resultMap.put(ts.getId(),new ArrayList<Classroom>() );
				
				//从已经完成排课的map中取出占用的教室列表selectedClassroomList
				ArrayList<Classroom> selectedClassroomList=TimeSlotID_ClassroomMap.get(ts.getId());
				
				//从全部教室中去掉已经占用的教室
				if(selectedClassroomList.size()>0){
				allClassroomsList.removeAll(selectedClassroomList);
				}
				
				//在未占用教室中选择capacity不小于当前目标教室的，存入HashMap的value列表中
				for(Classroom classroom:allClassroomsList)
				{
					if(classroom.getCapacity()>=capacity){
						resultMap.get(ts.getId()).add(classroom);
					}
				}
				
				//前两步中将seletedClassroomList从allClassroomList中去除，这里再进行allClassroomList还原以进行下一个循环
				allClassroomsList.addAll(selectedClassroomList);
			}
	return resultMap;
	}
		
		
}