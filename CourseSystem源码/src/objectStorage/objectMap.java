package objectStorage;
import java.util.HashMap;

import models.Building;
import models.Classroom;
import models.Course;
import models.Department;
import models.Student;
import models.Teacher;
import models.TimeSlot;

public class objectMap {
	private HashMap<Integer,Department> deptMap;
	private HashMap<Integer,Building> buildingMap;
	private HashMap<Integer,Student> studentMap;
	private HashMap<Integer,Teacher> teacherMap;
	private HashMap<Integer,TimeSlot> timeslotMap;
	private HashMap<Integer,Classroom> classroomMap;
	private HashMap<Integer,Course> courseMap;
	public Department findDepartment(int id){
		return this.deptMap.get(id);
	}
	public Building findBuilding(int id){
		return this.buildingMap.get(id);
	}
	public boolean addDepartment(Department dept){
		if(this.findDepartment(dept.getId())==null){
			deptMap.put(dept.getId(), dept);
			return true;
		}
		return false;
		
	}
	public boolean addBuilding(Building buil){
		if(this.findBuilding(buil.getId())==null){
			buildingMap.put(buil.getId(), buil);
			return true;
		}
		return false;	
	}
	public Student findStudent(int id){
		return this.studentMap.get(id);
	}
	public Teacher findTeacher(int id){
		return this.teacherMap.get(id);
	}
	public TimeSlot findTimeSlot(int id){
		return this.timeslotMap.get(id);
	}
	public Classroom findClassroom(int id){
		return this.classroomMap.get(id);
	}
	public Course findCourse(int id){
		return this.courseMap.get(id);
	}
	public boolean addStudent(Student s){
		if(this.findStudent(s.getId())==null){
			studentMap.put(s.getId(),s);
			return true;
		}
		return false;
		
	}
	public boolean addTeaher(Teacher t){
		if(this.findTeacher(t.getId())==null){
			teacherMap.put(t.getId(), t);
			return true;
		}
		return false;
		
	}
	public boolean addTimeSlot(TimeSlot ts){
		if(this.findTimeSlot(ts.getId())==null){
			timeslotMap.put(ts.getId(), ts);
			return true;
		}
		return false;
		
	}
	public boolean addClassroom(Classroom c){
		if(this.findClassroom(c.getId())==null){
			classroomMap.put(c.getId(), c);
			return true;
		}
		return false;
		
	}
	public boolean addCourse(Course c){
		if(this.findCourse(c.getId())==null){
			courseMap.put(c.getId(),c);
			return true;
		}
		return false;
		
	}
}
