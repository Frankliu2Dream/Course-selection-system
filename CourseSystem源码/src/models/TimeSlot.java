package models;

public class TimeSlot {
	private int id;
	private int day;
	private int Classorder;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getClassorder() {
		return Classorder;
	}
	public void setClassorder(int classorder) {
		this.Classorder = classorder;
	}
	@Override
	public String toString() {
		return "Time_slot [id=" + id + ", day=" + day + ", ClassOrder=" + Classorder + "]";
	}
	
}
