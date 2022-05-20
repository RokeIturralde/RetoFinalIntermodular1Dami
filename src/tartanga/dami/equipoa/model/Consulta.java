package tartanga.dami.equipoa.model;

import java.sql.Date;

public class Consulta {
	private Date fechaPub;
	private String titulo;
	private String description;
	private float precio;
	private String generos;
	private String autores;

	public Date getFechaNacim() {
		return fechaPub;
	}

	public void setFechaNacim(Date fechaNacim) {
		this.fechaPub = fechaNacim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getGeneros() {
		return generos;
	}

	public void setGeneros(String generos) {
		this.generos = generos;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}
}
