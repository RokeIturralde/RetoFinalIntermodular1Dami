package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;
import java.util.List;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public interface IBookController {
	
	public void altaBook(Book book, String author, String genre) throws GestorException;
	
	public Book buscarBook(int isbn) throws GestorException;
	
	public int modificarBook(Book book) throws GestorException;
	
	public void eliminarBook(int isbn) throws GestorException;
	
	public ArrayList<Book> listaBookGenre(String genre) throws GestorException;
	
	public ArrayList<Book> listaBookAuthor(String author) throws GestorException;
	
	public ArrayList<Book> listBookAuthorGenre(String author, String genre) throws GestorException;
	
	public ArrayList<Book> listAllBooks() throws GestorException;
}
