package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.User;

/**
 * @author Sendoa
 *
 */
public interface IUserController {
	
	/**
	 * Este metodo se usa para validar el nombre de usuario y contraseña introducido por el usuario para acceder a la aplicaccion
	 * @param userName es el nombre de usuario del usuario
	 * @param password es la contraseña del usuario
	 * @return devuelve un usuario que sera o un admin o un usario normal, dependiendo de lo que sea se abrira una ventana u otra
	 * @throws GestorException
	 */
	public User userLogIn(String userName, String password) throws GestorException;
	
	/**
	 * Este metodo se usa para registrar un usuario nuevo, no se pueden registrar nuevos administradores, estos se registran desde la base de datos directamente
	 * @param user recibe un objeto usuario para introducirlo en la base de datos
	 * @throws GestorException
	 */
	public void altaUsuario(User user) throws GestorException;
	
	/**
	 * Un metodo para buscar un usuario
	 * @param userName es el nombre de usuario que queremos buscar
	 * @return devuelve un usuario si el nombre de usuario introducido existe, de no ser asi devuelve un <b>null</b>
	 * @throws GestorException
	 */
	public User buscarUser(String userName) throws GestorException;
	
	/**
	 * Metodo para modificar la informacion de un usuario
	 * @param user recibe un usuario que se utiliza para introducir los datos actualizados
	 * @return devuelve el numero de lineas modificadas
	 * @throws GestorException
	 */
	public int modificarUser(User user) throws GestorException;
	
	/**
	 * Metodo para eliminar un usuario 
	 * @param userName el nombre de usuario del usuario a eliminar
	 * @return el numero de lineas a las que a afectado
	 * @throws GestorException
	 */
	public int eliminarUser(String userName) throws GestorException;
	
	/**
	 * Metodo para añadirle un autor favorito a un usuario a la hora de registrarse
	 * @param userName nombre de usuario del usuario al que le queremos añadir un autor favorito
	 * @param autor el codigo del autor
	 * @throws GestorException
	 */
	public void anadirAutor(String userName, String autor) throws GestorException;
	
	/**
	 * Metodo para añadirle un genero favorito a un usuario a la hora de registrarse
	 * @param userName nombre de usuario del usuario al que le queremos añadir un genero favorito
	 * @param genero el nombre del genero
	 * @throws GestorException 
	 */
	public void anadirGenero(String userName, String genero) throws GestorException;
	
	/**
	 * Metodo para seleccionar los generos favoritos de un usuario
	 * @param username el nombre de usuario del usuario del que queremos saber sus generos favoritos
	 * @author Eneko
	 * @return devuelve un ArrayList con todos los nombres de los generos
	 * @throws GestorException
	 */
	public ArrayList<String> userGenero(String username) throws GestorException;
	
	/**
	 * Metodo para seleccionar los autores favoritos de un usuario
	 * @param username el nombre de usuario del usuario del que queremos saber sus autores favoritos
	 * @author Eneko
	 * @return devuelve un ArrayList con todos los codigos de los autores
	 * @throws GestorException
	 */
	public ArrayList<String> userAuthor(String username) throws GestorException;
}