package tartanga.dami.equipoa.dataAccess;

import java.util.List;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public interface IBookController {
	
	public void altaBook(Book book) throws GestorException;
	
	public Book buscarBook(int isbn) throws GestorException;
	
	public int modificarBook(Book book);
	
	public void eliminarBook(String isbn);
	
	public List<Book> listaBookGenre();
	
	public List<Book> listaBookAuthor();
	
	public List<Book> listBookAuthorGenre();
	
}
