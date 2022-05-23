package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;

/**
 * @author Roke
 * Interfaz para la gestion de autores
 */
public interface IAuthorController {

	/**
	 * Metodo para insertar un nuevo autor
	 * @param author el autor a introducir en la base de datos
	 * @throws GestorException
	 */
	public void altaAuthor(Author author) throws GestorException;

	/**
	 * Metodo para buscar un autor
	 * @param codAuthor el codigo del autor
	 * @return el autor a buscar, devuelve null si no lo encuentra
	 * @throws GestorException
	 */
	public Author buscarAuthor(String codAuthor) throws GestorException;

	/**
	 * Metodo para modificar un autor
	 * @param author el autor con los datos nuevos
	 * @return el numero de lineas modificadas
	 * @throws GestorException
	 */
	public int modificarAuthor(Author author) throws GestorException;

	/**
	 * Metodo para eliminar autor
	 * @param codAuthor codigo del autor para eliminar
	 * @return el numero de lineas eliminadas
	 * @throws GestorException
	 */
	public int eliminarAuthor(String codAuthor) throws GestorException;

	/**
	 * Metodo para listar los autores preferidos de un usuario
	 * @param username nombre de usuario al que sacar los autores
	 * @return un arrayList con los autores preferidos del usuario
	 * @throws GestorException
	 */
	public ArrayList<Author> listarAutoresPreferidos(String username) throws GestorException;

	/**
	 * Metodo para tener los autores favoritos de un usuario si ha hecho alguna compra
	 * @param username el nombre de usuario
	 * @return Un arraylist con sus autores favoritos
	 * @throws GestorException
	 */
	public ArrayList<Author> listarComprasAutores(String username) throws GestorException;

	/**
	 * Metodo para borrar los autores preferidos de un usuario
	 * @param surname el apellido del autor
	 * @param username el nombre de usuario 
	 * @param name el nombre del autor
	 * @return numero de las lineas eliminadas
	 * @throws GestorException
	 */
	public int borrarAutorPreferidos(String surname, String username, String name) throws GestorException;

	/**
	 * Metodo para insertar un autor preferido a un usuario
	 * @param username el nombre de usuario
	 * @param codAuthor el codigo del autor
	 * @return numero de las lineas introducidas
	 * @throws GestorException
	 */
	public int insertarAutorPreferido(String username, String codAuthor) throws GestorException;

	/**
	 * Metodo que lista los autores
	 * @return devuelve un arrayList con los autores
	 * @throws GestorException
	 */
	public ArrayList<Author> listarAutores() throws GestorException;

}
