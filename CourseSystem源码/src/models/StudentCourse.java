package models;

public class StudentCourse {
	private Student student;
	private Course course;
	private int grade;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "StudentCourse [student=" + student + ", course=" + course + ", grade=" + grade + "]";
	}
	
	
}
