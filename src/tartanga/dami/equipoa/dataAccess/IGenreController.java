package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

/**
 * @author Roke
 * Interfaz para la gestion de generos
 */
public interface IGenreController {

	/**
	 * Añadir nuevo genero
	 * @param genre genero que va a añadir
	 * @throws GestorException
	 */
	public void altaGenre(Genre genre) throws GestorException;

	/**
	 * Buscar un genero
	 * @param genreName nombre del genero
	 * @return el genero buscado, null si no lo encuentra
	 * @throws GestorException
	 */
	public Genre buscarGenre(String genreName) throws GestorException;

	/**
	 * Modificar un genero
	 * @param genre genero con los datos nuevos
	 * @return numero con las lineas modificadas
	 * @throws GestorException
	 */
	public int modificarGenre(Genre genre) throws GestorException;

	/**
	 * Eliminar un genero
	 * @param genreName nombre del genero
	 * @return numero de las lineas eliminadas
	 * @throws GestorException
	 */
	public int eliminarGenre(String genreName) throws GestorException;

	/**
	 * Listado de generos preferidos
	 * @param username nombre de usuario
	 * @return lista con los nombres de los generos
	 * @throws GestorException
	 */
	public ArrayList<String> listarGenerosPreferidos(String username) throws GestorException;

	/**
	 * Borrado de generos preferidos
	 * @param genreCode nombre del genero
	 * @param username nombre de usuario
	 * @return numero de lineas borradas
	 * @throws GestorException
	 */
	public int borrarGenerosPreferidos(String genreCode, String username) throws GestorException;

	/**
	 * listado de todos los generos
	 * @return un arraylist con los nombres de los generos 
	 * @throws GestorException
	 */
	public ArrayList<String> listarGeneros() throws GestorException;

	/**
	 * Metodo para insertar un genero preferido
	 * @param username el nombre de usuario
	 * @param genreName el nombre del genero
	 * @return el numero de lineas insertadas
	 * @throws GestorException
	 */
	public int insertarGeneroPreferido(String username, String genreName) throws GestorException;

}
