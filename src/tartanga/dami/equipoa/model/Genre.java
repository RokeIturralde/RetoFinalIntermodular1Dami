package tartanga.dami.equipoa.model;

import java.io.Serializable;

public class Genre implements Serializable{
	private String genreName;
	private String description;
	
	public Genre() {
		super();
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
	
	
}
