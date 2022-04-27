package tartanga.dami.equipoa.dataAccess;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

public interface IGenreController {

	public void altaGenre(Genre genre) throws GestorException;

	public Genre buscarGenre(String genreName) throws GestorException;

	public int modificarGenre(Genre genre) throws GestorException;

	public int eliminarGenre(String genreName) throws GestorException;

}
