package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

public class IUserControllerDBImplementationTest {

	private User user;
	private IUserController pruebaInterfaz = new IUserControllerDBImplementation();

	@Test
	public void testUserLogIn() throws GestorException {
		User prueba = pruebaInterfaz.userLogIn("gitanito77", "abcd*1234");
		assertNotEquals(prueba, null);
	}

	@Test
	public void testAltaUsuario() {
		try {
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
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuscarUser() throws GestorException {
		User prueba = pruebaInterfaz.buscarUser("gitanito77");
		assertNotEquals(prueba, null);
	}

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

	@Test
	public void testEliminarUser() throws GestorException {
		int prueba = pruebaInterfaz.eliminarUser("erandio7");
		assertEquals(prueba, 1);
	}

	@Test
	public void testUserGenero() throws GestorException {
		ArrayList<String> prueba = pruebaInterfaz.userGenero("gitanito77");
		assertNotEquals(prueba, null);
	}
	
	@Test
	public void testUserAuthor() throws GestorException {
		ArrayList<String> prueba = pruebaInterfaz.userAuthor("gitanito77");
		assertNotEquals(prueba, null);
	}
	
}
