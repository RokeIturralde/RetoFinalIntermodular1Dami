package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import tartanga.dami.equipoa.model.Genre;

public class IGenreControllerDBImplementationTest {
	private IGenreController pruebaGenero = new IGenreControllerDBImplementation();
	private Genre genero;

	@Test
	public void testAltaGenre() {
		try {
			genero = new Genre();
			genero.setGenreName("Thriller");
			genero.setDescription("Hola Caracola");

			System.out.println(genero.toString());
			pruebaGenero.altaGenre(genero);
			Genre pruebaBusqueda = pruebaGenero.buscarGenre(genero.getGenreName());
			assertNotEquals(null, pruebaBusqueda);
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}

	@Test
	public void testBuscarGenre() {
		try {
			genero = new Genre();
			genero.setGenreName("Ficcion");
			genero.setDescription("Heyyyyy");
			System.out.println(genero.toString());
			Genre pruebaBusqueda = pruebaGenero.buscarGenre(genero.getGenreName());
			// assertEquals(genero, pruebaBusqueda);
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}

	@Test
	public void testModificarGenre() {
		try {
			genero = new Genre();
			genero.setGenreName("Ficcion");
			genero.setDescription("Heyyyyy");
			int prueba = pruebaGenero.modificarGenre(genero);
			assertEquals(prueba, 1);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	@Test
	public void testEliminarGenre() {
		try {
			int prueba = pruebaGenero.eliminarGenre("Thriller");
			assertEquals(prueba, 1);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
