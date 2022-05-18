package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

public class IGenreControllerDBImplementationTest {
	private IGenreController pruebaGenero = new IGenreControllerDBImplementation();
	private Genre genero;
	
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

	@Test
	public void testBuscarGenre() throws GestorException {
		genero = new Genre();
		genero.setGenreName("Ficcion");
		genero.setDescription("Heyyyyy");
		System.out.println(genero.toString());
		Genre pruebaBusqueda = pruebaGenero.buscarGenre(genero.getGenreName());
		assertEquals(genero, pruebaBusqueda);
	}

	@Test
	public void testModificarGenre() throws GestorException {
		genero = new Genre();
		genero.setGenreName("Ficcion");
		genero.setDescription("Heyyyyy");
		int prueba = pruebaGenero.modificarGenre(genero);
		assertEquals(prueba, 1);
	}

	@Test
	public void testEliminarGenre() throws GestorException {
		int prueba = pruebaGenero.eliminarGenre("Thriller");
		assertEquals(prueba, 1);
	}
	
	@Test
	public void testListarGenerosPreferidos() throws GestorException {
		ArrayList<String> prueba = pruebaGenero.listarGenerosPreferidos("gitanito77");
		assertNotEquals(prueba, null);
	}
	
	@Test
	public void testBorrarGenerosPreferidos() throws GestorException {
		int prueba = pruebaGenero.borrarGenerosPreferidos("fantasia","da");
		assertTrue(prueba>0);
	}
	
	@Test
	public void testListarGeneros() throws GestorException {
		ArrayList<String> prueba = pruebaGenero.listarGeneros();
		assertNotEquals(prueba, null);
	}

	@Test
	public void testInsertarGeneroPreferido() throws GestorException {
		int prueba = pruebaGenero.insertarGeneroPreferido("dingle", "ficcion");
		assertEquals(prueba, 1);
	}
}
