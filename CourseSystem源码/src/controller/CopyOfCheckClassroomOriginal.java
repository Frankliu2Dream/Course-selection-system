package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import models.Classroom;
import models.TimeSlot;
//check��allTimeSlotsList������ʱ����Ƿ��з���capacityҪ���classroom
//���ص��ǰ���Ŀ��allTimeSlotsList������timeslot����Ӧ��Чclassroom��HashMap
	public class CopyOfCheckClassroomOriginal {
		public HashMap<Integer, ArrayList<Classroom>>  checkClassroom(
		ArrayList<TimeSlot> allTimeSlotsList, 
		int capacity,
		HashMap<Integer, ArrayList<Classroom>> TimeSlot_ClassroomMap,
		ArrayList<Classroom> allClassroomsList) throws Exception {
	
			
			
			//����comparator�����ڶ�ɸѡ����classroom����capacity�Ӵ�С����
			Comparator<Classroom> comparator = new Comparator<Classroom>() {
				public int compare(Classroom c1, Classroom c2) {
					if (c1.getCapacity() != c2.getCapacity()) {
						return c1.getCapacity() - c2.getCapacity();
	
				} else {
					return c1.getId() - c2.getId();
				}
				}
			};
			
			//���������HashMap
			HashMap<Integer, ArrayList<Classroom>> resultMap = new HashMap<Integer, ArrayList<Classroom>>();
			
			//����ɸѡ����
			for (TimeSlot ts : allTimeSlotsList) {
				ArrayList<Classroom> temp_list = new ArrayList<Classroom>();
				for(Classroom cr:allClassroomsList){
					Classroom newClassroom=new Classroom();
					newClassroom.setBuilding(cr.getBuilding());
					newClassroom.setCapacity(cr.getCapacity());
					newClassroom.setId(cr.getId());
					temp_list.add(newClassroom);
				}
				ArrayList<Classroom> selectedClassroom = TimeSlot_ClassroomMap.get(ts.getId());
				if(selectedClassroom==null||selectedClassroom.size()==0)
				{
					
				}
				else
				{
					for(Classroom cr:selectedClassroom){
						Iterator<Classroom> itr=temp_list.iterator();
							while(itr.hasNext()){
								if(itr.next().getId()==cr.getId()){
									//temp_list.remove(itr.next());
									itr.remove();
								}
						}
					}
				}
				

				for(Classroom cr:temp_list) {
					Iterator<Classroom> itr=temp_list.iterator();
					while(itr.hasNext())
					{
						if (itr.next().getId() < capacity) {
							itr.remove();}
						
					}
				}
					Collections.sort(temp_list, comparator);
					resultMap.put((Integer)ts.getId(), temp_list);
			}
	return resultMap;
	}
}
