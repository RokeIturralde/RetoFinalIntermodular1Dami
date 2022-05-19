package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;

/**
 * @author Sendoa
 * Test de la implementacion de la interfaz de autores
 */
public class IAuthorControllerDBImplementationTest {
	private IAuthorController pruebaAutor = new IAuthorControllerDBImplementation();
	private Author autor;
	private Date fecha = new Date(1998 - 02 - 02);
	private Date fecha2 = new Date(2002 - 11 - 24);

	/**
	 * Metodo que testea la creacion y busqueda de un autor
	 * @throws GestorException
	 */
	@Test
	public void testAltaAuthor() throws GestorException {
		autor = new Author();
		autor.setCodAuthor("A003");
		autor.setName("Koke");
		autor.setSurname("Ruiz");
		autor.setBirthDate(fecha);

		System.out.println(autor.toString());
		pruebaAutor.altaAuthor(autor);
		Author pruebaBusqueda = pruebaAutor.buscarAuthor(autor.getCodAuthor());
		assertNotEquals(null, pruebaBusqueda);
	}

	/**
	 * Metodo para testear unicamente la busqueda de un autor
	 * @throws GestorException
	 */
	@Test
	public void testBuscarAuthor() throws GestorException {
		autor = new Author();
		autor.setCodAuthor("A001");
		Author pruebaBusqueda = pruebaAutor.buscarAuthor(autor.getCodAuthor());
		assertEquals(autor.getCodAuthor(), pruebaBusqueda.getCodAuthor());
	}

	/**
	 * Metodo test de la modificacion de un autor
	 * @throws GestorException
	 */
	@Test
	public void testModificarAuthor() throws GestorException {
		autor = new Author();
		autor.setCodAuthor("A003");
		autor.setName("Sendoa");
		autor.setSurname("awa awa");
		autor.setBirthDate(fecha);
		autor.setDeathDate(fecha2);
		System.out.println(autor.toString());
		int prueba = pruebaAutor.modificarAuthor(autor);
		assertEquals(prueba, 1);
	}

	/**
	 * Metodo test para la eliminacion de un autor
	 * @throws GestorException
	 */
	@Test
	public void testEliminarAuthor() throws GestorException {
		int prueba = pruebaAutor.eliminarAuthor("A003");
		assertEquals(prueba, 1);
	}
	
	/**
	 * Metodo test para listar todos los autores favoritos de un usuario y guardarlos en un ArrayList
	 * @throws GestorException
	 */
	@Test
	public void testListarAutoresPreferidos() throws GestorException {
		ArrayList<Author> prueba = pruebaAutor.listarAutoresPreferidos("gitanito77");
		assertNotEquals(prueba, null);
	}
	
	/**
	 * Metodo test para listar los autores favoritos de un usuario que ha hecho alguna compra
	 * @throws GestorException
	 */
	@Test
	public void testComprasAutores() throws GestorException {
		ArrayList<Author> prueba = pruebaAutor.listarComprasAutores("gitanito77");
		assertNotEquals(prueba, null);
	}
	
	/**
	 * Metodo para testear que se borran los autores preferidos a un usuario
	 * @throws GestorException
	 */
	@Test
	public void testBorrarAutorPreferidos() throws GestorException {
		int i = pruebaAutor.borrarAutorPreferidos("Tolkien", "da", "John");
		assertEquals(i, 1);
	}
	
	/**
	 * Metodo para testear la insercion de un autor preferido a un usuario
	 * @throws GestorException
	 */
	@Test
	public void testInsertarAutorPreferido() throws GestorException {
		int i = pruebaAutor.insertarAutorPreferido("gitanito77", "A001");
		assertEquals(i, 1);
	}

	/**
	 * Metodo test que lista todos los autores y los guarda en un ArrayList
	 * @throws GestorException
	 */
	@Test
	public void testListarAutores() throws GestorException {
		ArrayList<Author> prueba = pruebaAutor.listarAutores();
		assertNotNull(prueba);
	}
}
