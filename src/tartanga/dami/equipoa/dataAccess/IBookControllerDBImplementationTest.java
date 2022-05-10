package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public class IBookControllerDBImplementationTest {

	private Date fecha = new Date(2006 - 04 - 05);
	private Date fechaPrueba = new Date(2014 - 05 - 06);
	private Book book;
	IBookController pruebaLibros = new IBookControllerDBImplementation();

	@Test
	public void testAltaBook() {
		try {
			book = new Book(1232, "El señor de los anillos", "descripcion", "Elhuyar", 7, 2, 1, fecha);
			System.out.println(book.toString());
			// pruebaLibros.altaBook(book, "A001", "Fantasia");
			Book libroPrueba = pruebaLibros.buscarBook(book.getIsbn());
			assertNotEquals(null, libroPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuscarBook() {
		try {
			int isbnBuscar = 2;
			Book libroBuscado = pruebaLibros.buscarBook(isbnBuscar);
			Book bookComprobar = new Book(2, "La luz que no puedes ver", "Una novela imaginativa e intrincada",
					"Charles Scribners sons", 16, 28, 1, fechaPrueba);
			assertEquals(bookComprobar, libroBuscado);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModificarBook() {
		try {
			book = new Book(1232, "ROKE EL ROKERO", "descripcion", "El barco de papel", 7, 3, 1, fecha);
			int cuantos = pruebaLibros.modificarBook(book);
			System.out.println(cuantos);
			assertEquals(1, cuantos);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEliminarBook() {

		try {
			int isbnPrueba = 1232;
			pruebaLibros.eliminarBook(isbnPrueba);
			Book libroPrueba = pruebaLibros.buscarBook(isbnPrueba);
			assertEquals(null, libroPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testListaBookGenre() {
		try {
			ArrayList<Book> listaPrueba = new ArrayList();
			listaPrueba = pruebaLibros.listaBookGenre("Fantasia");
			assertNotEquals(null, listaPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListaBookAuthor() {
		try {
			ArrayList<Book> listaPrueba = new ArrayList();
			listaPrueba = pruebaLibros.listaBookAuthor("Tolkien");
			assertNotEquals(null, listaPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}
		;
	}

	@Test
	public void testListBookAuthorGenre() {
		try {
			ArrayList<Book> listaPrueba = new ArrayList();
			listaPrueba = pruebaLibros.listBookAuthorGenre("Tolkien", "Fantasia");
			assertNotEquals(null, listaPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListAllBooks() {
		try {
			ArrayList<Book> listaPrueba = new ArrayList();
			listaPrueba = pruebaLibros.listAllBooks();
			assertNotEquals(null, listaPrueba);
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}

}
