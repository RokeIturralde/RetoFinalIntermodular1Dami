package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.User;

public interface IUserController {
	
	public User userLogIn(String userName, String password) throws GestorException;
	
	public void altaUsuario(User user) throws GestorException;
	
	public User buscarUser(String userName) throws GestorException;
	
	public int modificarUser(User user) throws GestorException;
	
	public int eliminarUser(String userName) throws GestorException;
	
	public void anadirAutor(String userName, String autor) throws GestorException;
	
	public void anadirGenero(String userName, String genero) throws GestorException;
	
	public ArrayList<String> userGenero(String username) throws GestorException;
	
	public ArrayList<String> userAuthor(String username) throws GestorException;
}