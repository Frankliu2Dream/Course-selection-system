package models;

public class Classroom {
	private int id;
	private Building building;
	private int Capacity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building abuilding) {
		building = abuilding;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	@Override
	public String toString() {
		return "Classroom [id=" + id + ", building=" + building + ", Capacity=" + Capacity + "]";
	}
	
}
