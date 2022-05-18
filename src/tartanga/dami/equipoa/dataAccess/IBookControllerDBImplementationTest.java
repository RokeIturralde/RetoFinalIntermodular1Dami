package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public class IBookControllerDBImplementationTest {

	private Date fecha = new Date(2006 - 04 - 05);
	private Book book;
	IBookController pruebaLibros = new IBookControllerDBImplementation();
	ArrayList<String> codAuthor;
	ArrayList<String> genreName;

	
	@Test
	public void testAltaBookYBusqueda() throws GestorException {
		codAuthor = new ArrayList<String>();
		genreName = new ArrayList<String>();
		book = new Book(1236, "El señor de los anillos", "descripcion", "Elhuyar", 7, 2, 1, fecha);
		pruebaLibros.altaBook(book);
		codAuthor.add("A001");
		genreName.add("ficcion");
		pruebaLibros.anadirAuthor(codAuthor, book.getIsbn());
		pruebaLibros.anadirGenre(genreName, book.getIsbn());
		Book libroPrueba = pruebaLibros.buscarBook(book.getIsbn());
		System.out.println(libroPrueba.toString());
		assertEquals(libroPrueba.getIsbn(), book.getIsbn());
	}

	@Test
	public void testModificarBook() throws GestorException {
		codAuthor = new ArrayList<String>();
		genreName = new ArrayList<String>();
		int isbn = 1236;
		book = new Book(isbn, "El señor de los anillos 3", "descripcion", "Elhuyar", 7, 2, 1, fecha);
		codAuthor.add("A001");
		genreName.add("ficcion");
		int prueba = pruebaLibros.modificarBook(book, codAuthor, genreName);
		assertSame(prueba, 1);
	}

	@Test
	public void testEliminarBookYBusqueda() throws GestorException {
		pruebaLibros.eliminarBook(1235);
		Book libroPrueba = pruebaLibros.buscarBook(1235);
		assertEquals(libroPrueba, null);
	}
	

	@Test
	public void testListaBookGenre() throws GestorException {
		ArrayList<Book> prueba = pruebaLibros.listaBookGenre("fantasia");
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListaBookAuthor() throws GestorException {
		ArrayList<Book> prueba = pruebaLibros.listaBookAuthor("Tolkien");
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListBookAuthorGenre() throws GestorException {
		ArrayList<Book> prueba = pruebaLibros.listBookAuthorGenre("Tolkien", "fantasia");
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListAllBooks() throws GestorException {
		ArrayList<Book> prueba = pruebaLibros.listAllBooks();
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListTopSales() throws GestorException {
		ArrayList<Integer> prueba = pruebaLibros.listTopSales();
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListDiscount() throws GestorException {
		ArrayList<Integer> prueba = pruebaLibros.listDiscount();
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListAuthors() throws GestorException {
		ArrayList<String> prueba = pruebaLibros.listAuthors(1);
		assertNotEquals(prueba, null);
	}

	@Test
	public void testListGenres() throws GestorException {
		ArrayList<String> prueba = pruebaLibros.listGenres(1);
		assertNotEquals(prueba, null);
	}

}
