package tartanga.dami.equipoa.model;

import java.sql.Date;
import java.util.ArrayList;

public class Compra {
	private Date fechaCompra;
	private ArrayList<Author> authors;
	private int isbn;
	private int cantidadLibros;
	private float precioCompra;

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getCantidadLibros() {
		return cantidadLibros;
	}

	public void setCantidadLibros(int cantidadLibros) {
		this.cantidadLibros = cantidadLibros;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	

}
