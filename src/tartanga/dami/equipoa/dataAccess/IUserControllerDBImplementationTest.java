package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

/**
 * @author Sendoa
 * 
 */
public class IUserControllerDBImplementationTest {

	private User user;
	private IUserController pruebaInterfaz = new IUserControllerDBImplementation();

	/**
	 * Metodo de testeo para el logIn de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testUserLogIn() throws GestorException {
		User prueba = pruebaInterfaz.userLogIn("gitanito77", "abcd*1234");
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo test para el alta de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testAltaUsuario() throws GestorException {
		user = new Partner();
		user.setAddress("erandio");
		user.setEmail("erandio@gmail.com");
		user.setName("erandio");
		user.setPassword("erandio");
		user.setPhone(48950);
		user.setSurname("eran");
		user.setTipo('P');
		user.setUserName("erandio10");

		pruebaInterfaz.altaUsuario(user);
		pruebaInterfaz.anadirAutor("erandio", "A001");
		pruebaInterfaz.anadirGenero("erandio", "fantasia");
		User prueba = pruebaInterfaz.buscarUser(user.getUserName());
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo test para buscar un usuario
	 * @throws GestorException
	 */
	@Test
	public void testBuscarUser() throws GestorException {
		User prueba = pruebaInterfaz.buscarUser("gitanito77");
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo test para modificar un usuario
	 * @throws GestorException
	 */
	@Test
	public void testModificarUser() throws GestorException {
		user = new Partner();
		user.setAddress("tartanga");
		user.setEmail("erandio@gmail.com");
		user.setName("erandio");
		user.setPassword("erandio");
		user.setPhone(48950);
		user.setSurname("erandio");
		user.setTipo('P');
		user.setUserName("erandio2");

		int prueba = pruebaInterfaz.modificarUser(user);
		assertEquals(prueba, 1);
	}

	/**
	 * Metodo test para la eliminacion de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testEliminarUser() throws GestorException {
		int prueba = pruebaInterfaz.eliminarUser("erandio7");
		assertEquals(prueba, 1);
	}

	/**
	 * Metodo test que lista los generos favoritos de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testUserGenero() throws GestorException {
		ArrayList<String> prueba = pruebaInterfaz.userGenero("gitanito77");
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo test que lista los autores favoritos de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testUserAuthor() throws GestorException {
		ArrayList<String> prueba = pruebaInterfaz.userAuthor("gitanito77");
		assertNotEquals(prueba, null);
	}

}
