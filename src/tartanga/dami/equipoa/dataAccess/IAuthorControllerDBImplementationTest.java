package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Genre;

public class IAuthorControllerDBImplementationTest {
	private IAuthorController pruebaAutor = new IAuthorControllerDBImplementation();
	private Author autor;
	private Date fecha = new Date(1998 - 02 - 02);
	private Date fecha2 = new Date(2002 - 11 - 24);

	/*
	 * @Test public void testAltaAuthor() { try { autor = new Author();
	 * autor.setCodAuthor("A003"); autor.setName("Koke"); autor.setSurname("Ruiz");
	 * autor.setBirthDate(fecha);
	 * 
	 * System.out.println(autor.toString()); pruebaAutor.altaAuthor(autor); Author
	 * pruebaBusqueda = pruebaAutor.buscarAuthor(autor.getCodAuthor());
	 * assertNotEquals(null, pruebaBusqueda); } catch (Exception e) { // TODO:
	 * handle exception } }
	 */

	@Test
	public void testBuscarAuthor() {
		try {
			autor = new Author();
			autor.setCodAuthor("A001");
			Author pruebaBusqueda = pruebaAutor.buscarAuthor(autor.getCodAuthor());
			assertEquals(autor.getCodAuthor(), pruebaBusqueda.getCodAuthor());
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	@Test
	public void testModificarAuthor() {
		try {
			autor = new Author();
			autor.setCodAuthor("A003");
			autor.setName("Sendoa");
			autor.setSurname("awa awa");
			autor.setBirthDate(fecha);
			autor.setDeathDate(fecha2);
			System.out.println(autor.toString());
			int prueba = pruebaAutor.modificarAuthor(autor);
			assertEquals(prueba, 1);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@Test
	public void testEliminarAuthor() {
		try {
			int prueba = pruebaAutor.eliminarAuthor("A003");
			assertEquals(prueba, 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
