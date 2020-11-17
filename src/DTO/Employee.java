package DTO;

public class Employee extends Member{
	private String position;
	private int type = 1;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
