package tartanga.dami.equipoa.model;

import java.io.Serializable;

public class User implements Serializable {

	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + ", surname=" + surname
				+ ", name=" + name + ", address=" + address + ", phone=" + phone + ", tipo=" + tipo + "]";
	}

	private String userName;
	private String email;
	private String password;
	private String surname;
	private String name;
	private String address;
	private int phone;
	private char tipo;

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

}
