package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public class IBookControllerDBImplementationTest {

	private Date fecha = new Date(2006 - 04 - 05);
	private Date fechaPrueba = new Date(2014 - 05 - 06);
	private Book book;
	IBookController pruebaLibros = new IBookControllerDBImplementation();
	ArrayList<String> codAuthor;
	ArrayList<String> genreName;

	/*@Test
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
	}*/

	@Test
	public void testModificarBook() throws GestorException {
		codAuthor = new ArrayList<String>();
		genreName = new ArrayList<String>();
		int isbn = 1236;
		book = new Book(isbn, "El señor de los anillos 2", "descripcion", "Elhuyar", 7, 2, 1, fecha);
		codAuthor.add("A001");
		genreName.add("ficcion");
		pruebaLibros.modificarBook(book, codAuthor, genreName);
	}

	@Test
	public void testEliminarBook() {
		
	}

	@Test
	public void testListaBookGenre() {
		
	}

	@Test
	public void testListaBookAuthor() {
		
	}

	@Test
	public void testListBookAuthorGenre() {
		
	}

	@Test
	public void testListAllBooks() {
		
	}

	@Test
	public void testListTopSales() {
		
	}

	@Test
	public void testListDiscount() {
		
	}

	@Test
	public void testAnadirAuthor() {
		
	}

	@Test
	public void testAnadirGenre() {
		
	}

	@Test
	public void testListAuthors() {
		
	}

	@Test
	public void testListGenres() {
		
	}

}
