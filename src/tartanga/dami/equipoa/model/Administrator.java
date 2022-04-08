package tartanga.dami.equipoa.model;

public class Administrator extends User{
	private int salary;
	private String education;
	
	public Administrator() {
		super();
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
}