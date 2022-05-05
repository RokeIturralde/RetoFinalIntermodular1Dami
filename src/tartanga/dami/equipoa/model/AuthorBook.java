package tartanga.dami.equipoa.model;

public class AuthorBook {

	String title;
	String name;
	String surname;
	String description;
	float price;
	
	public AuthorBook(String title, String surname, String description, float price, String name) {
		super();
		this.title = title;
		this.surname = surname;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
