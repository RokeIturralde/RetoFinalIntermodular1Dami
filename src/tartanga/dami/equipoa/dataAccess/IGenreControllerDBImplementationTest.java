package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

/**
 * @author Sendoa
 *
 */
public class IGenreControllerDBImplementationTest {
	private IGenreController pruebaGenero = new IGenreControllerDBImplementation();
	private Genre genero;
	
	/**
	 * Metodo para testear la insercion de un genero
	 * @throws GestorException
	 */
	@Test
	public void testAltaGenre() throws GestorException {
		genero = new Genre();
		genero.setGenreName("Thriller");
		genero.setDescription("Hola Caracola");

		System.out.println(genero.toString());
		pruebaGenero.altaGenre(genero);
		Genre pruebaBusqueda = pruebaGenero.buscarGenre(genero.getGenreName());
		assertNotEquals(null, pruebaBusqueda);
	}

	/**
	 * Metodo test para buscar un genero
	 * @throws GestorException
	 */
	@Test
	public void testBuscarGenre() throws GestorException {
		genero = new Genre();
		genero.setGenreName("Ficcion");
		genero.setDescription("Heyyyyy");
		System.out.println(genero.toString());
		Genre pruebaBusqueda = pruebaGenero.buscarGenre(genero.getGenreName());
		assertEquals(genero, pruebaBusqueda);
	}

	/**
	 * Metodo de testeo para modificar un genero
	 * @throws GestorException
	 */
	@Test
	public void testModificarGenre() throws GestorException {
		genero = new Genre();
		genero.setGenreName("Ficcion");
		genero.setDescription("Heyyyyy");
		int prueba = pruebaGenero.modificarGenre(genero);
		assertEquals(prueba, 1);
	}

	/**
	 * Metodo test para eliminar un genero
	 * @throws GestorException
	 */
	@Test
	public void testEliminarGenre() throws GestorException {
		int prueba = pruebaGenero.eliminarGenre("Thriller");
		assertEquals(prueba, 1);
	}
	
	/**
	 * Metodo test para listar los generos preferidos de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testListarGenerosPreferidos() throws GestorException {
		ArrayList<String> prueba = pruebaGenero.listarGenerosPreferidos("gitanito77");
		assertNotEquals(prueba, null);
	}
	
	/**
	 * Metodo test para borrar los generos preferidos de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testBorrarGenerosPreferidos() throws GestorException {
		int prueba = pruebaGenero.borrarGenerosPreferidos("fantasia","da");
		assertTrue(prueba>0);
	}
	
	/**
	 * Metodo test para listar todos los generos
	 * @throws GestorException
	 */
	@Test
	public void testListarGeneros() throws GestorException {
		ArrayList<String> prueba = pruebaGenero.listarGeneros();
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo de testeo para la insercion de un genero preferido
	 * @throws GestorException
	 */
	@Test
	public void testInsertarGeneroPreferido() throws GestorException {
		int prueba = pruebaGenero.insertarGeneroPreferido("dingle", "prueba");
		assertEquals(prueba, 1);
	}
}
