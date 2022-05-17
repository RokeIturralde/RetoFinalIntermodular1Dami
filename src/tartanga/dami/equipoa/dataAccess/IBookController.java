package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;
import java.util.List;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Genre;

public interface IBookController {

	public void altaBook(Book book) throws GestorException;

	public void anadirAuthor(ArrayList<String> codAuthor, int isbn) throws GestorException;

	public void anadirGenre(ArrayList<String> genre, int isbn) throws GestorException;

	public Book buscarBook(int isbn) throws GestorException;

	public ArrayList<String> listAuthors(int isbn) throws GestorException;

	public ArrayList<String> listGenres(int isbn) throws GestorException;

	public int modificarBook(Book book, ArrayList<String> codAuthor, ArrayList<String> genrename)
			throws GestorException;

	public void eliminarBook(int isbn) throws GestorException;

	public ArrayList<Book> listaBookGenre(String genre) throws GestorException;

	public ArrayList<Book> listaBookAuthor(String author) throws GestorException;

	public ArrayList<Book> listBookAuthorGenre(String author, String genre) throws GestorException;

	public ArrayList<Book> listAllBooks() throws GestorException;

	public ArrayList<Integer> listTopSales() throws GestorException;

	public ArrayList<Integer> listDiscount() throws GestorException;

	public String listAuthorsIsbn(int isbn) throws GestorException;

	public ArrayList<String> listarConsulta() throws GestorException;

}