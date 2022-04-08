package tartanga.dami.equipoa.dataAccess;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.User;

public interface IUserController {
	
	public User userLogIn(String userName, String password);
	
	public void altaUsuario(User user) throws GestorException;
	
	public User buscarUser(String userName) throws GestorException;
	
	public int modificarUser(User user) throws GestorException;
	
	public void eliminarUser(String userName);
	
}