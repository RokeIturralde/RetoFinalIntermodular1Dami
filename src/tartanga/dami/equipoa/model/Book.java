package tartanga.dami.equipoa.model;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable{
	private int isbn;
	private String title;
	private String description;
	private String editorial;
	private int stock;
	private float price;
	private int idDiscount;
	private Date pubDate;
	private String author;
	private String genre;
	
	

	public Book() {
		super();
	}
	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", editorial=" + editorial
				+ ", stock=" + stock + ", price=" + price + ", idDiscount=" + idDiscount + ", pubDate=" + pubDate + "]";
	}

	public Book(int isbn, String title, String description, String editorial, int stock, float price, int idDiscount,
			Date pubDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.editorial = editorial;
		this.stock = stock;
		this.price = price;
		this.idDiscount = idDiscount;
		this.pubDate = pubDate;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(int idDiscount) {
		this.idDiscount = idDiscount;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
