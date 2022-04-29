package tartanga.dami.equipoa.model;

import java.io.Serializable;

public class Genre implements Serializable{
	private String genreName;
	private String description;
	
	public Genre() {
		super();
	}

	public Genre(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Genre [genreName=" + genreName + ", description=" + description + "]";
	}
	
	
}
