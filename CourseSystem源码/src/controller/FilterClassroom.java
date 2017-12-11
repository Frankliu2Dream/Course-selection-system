package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import models.Classroom;
import models.TimeSlot;
/*
 * ������FilterClassroom
 * ����:filter
 * ����Ŀ�꣺Ϊһ��Classroomѡ�������������������Capacity���Ŀս���
 * ���룺
 * 		1.���ݿ������е�ʱ���TimeSlot���ɵļ���allTimeSlotList
 * 		2.Ŀ����ҵ�����capacity
 * 		3.����ʱ��ε�id�͸�ʱ��������Ѿ�ռ�ý����γɵ�HashMap����TimeSlotID_ClassroomMap
 * 		4.���ݿ������н����б�allClassroomList
 * �����resultMap,��map��key������TimeSlot��id��value�Ǹ�TimeSlot����ɸѡ���Ŀս���
 */
//check��allTimeSlotsList������ʱ����Ƿ��з���capacityҪ���classroom
//���ص��ǰ���Ŀ��allTimeSlotsList������timeslot����Ӧ��Чclassroom��HashMap
	public class FilterClassroom {
		
		public HashMap<Integer, ArrayList<Classroom>>  filter(
		ArrayList<TimeSlot> allTimeSlotsList, 
		int capacity,
		HashMap<Integer, ArrayList<Classroom>> TimeSlotID_ClassroomMap,
		ArrayList<Classroom> allClassroomsList
		) throws Exception {
			
			//��ʼ�����ص�resultMap
			HashMap<Integer, ArrayList<Classroom>> resultMap=new HashMap<Integer, ArrayList<Classroom>>();
			
			//��allTimeSlotsList����ѭ����ÿ��ȡ��һ��TimeSlot,ɸѡ����ʱ��εĿ��ÿս��ң�����HashMap
			for(TimeSlot ts:allTimeSlotsList)
			{
				//����HashMap�д����timeSlot��id��һ���յ�Classroom�б�
				resultMap.put(ts.getId(),new ArrayList<Classroom>() );
				
				//���Ѿ�����ſε�map��ȡ��ռ�õĽ����б�selectedClassroomList
				ArrayList<Classroom> selectedClassroomList=TimeSlotID_ClassroomMap.get(ts.getId());
				
				//��ȫ��������ȥ���Ѿ�ռ�õĽ���
				if(selectedClassroomList.size()>0){
				allClassroomsList.removeAll(selectedClassroomList);
				}
				
				//��δռ�ý�����ѡ��capacity��С�ڵ�ǰĿ����ҵģ�����HashMap��value�б���
				for(Classroom classroom:allClassroomsList)
				{
					if(classroom.getCapacity()>=capacity){
						resultMap.get(ts.getId()).add(classroom);
					}
				}
				
				//ǰ�����н�seletedClassroomList��allClassroomList��ȥ���������ٽ���allClassroomList��ԭ�Խ�����һ��ѭ��
				allClassroomsList.addAll(selectedClassroomList);
			}
	return resultMap;
	}
		
		
}