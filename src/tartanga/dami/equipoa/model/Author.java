package tartanga.dami.equipoa.model;

import java.io.Serializable;
import java.sql.Date;

public class Author implements Serializable {
	private String codAuthor;
	private String name;
	private String surname;

	@Override
	public String toString() {
		return "Author [codAuthor=" + codAuthor + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
				+ ", deathDate=" + deathDate + "]";
	}

	private Date birthDate;
	private Date deathDate;

	public Author() {
		super();
	}

	public String getCodAuthor() {
		return codAuthor;
	}

	public void setCodAuthor(String codAuthor) {
		this.codAuthor = codAuthor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
}
