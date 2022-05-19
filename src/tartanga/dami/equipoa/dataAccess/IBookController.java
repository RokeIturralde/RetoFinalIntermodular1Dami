package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;
import java.util.List;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Genre;

/**
 * @author Eneko
 * Interfaz para la gestion de libros
 */
public interface IBookController {

	/**
	 * Metodo para introducir un libro
	 * @param book recibe un libro que se introduce en la base de datos
	 * @throws GestorException
	 */
	public void altaBook(Book book) throws GestorException;
	
	/**
	 * Metodo para añadir autores a un libro
	 * @param codAuthor un ArrayList con todos los codigos de los autores que han participado en el libro
	 * @param isbn el isbn del libro
	 * @throws GestorException
	 */
	public void anadirAuthor(ArrayList<String> codAuthor, int isbn) throws GestorException;
	
	/**
	 * Metodo para añadir generos a un libro
	 * @param genre un ArrayList con todos los nombres de los generos del libro
	 * @param isbn el isbn del libro
	 * @throws GestorException
	 */
	public void anadirGenre(ArrayList<String> genre, int isbn) throws GestorException;
	
	/**
	 * Metodo para buscar un libro
	 * @param isbn el isbn del libro a buscar 
	 * @return el libro buscado, si no lo encuentra es null
	 * @throws GestorException
	 */
	public Book buscarBook(int isbn) throws GestorException;
	
	/**
	 * Metodo para listar los autores de un libro
	 * @param isbn el isbn del libro
	 * @return un ArrayList con los codigos de todos los autores que han participado en el libro
	 * @throws GestorException
	 */
	public ArrayList<String> listAuthors(int isbn) throws GestorException;
	
	/**
	 * Metodo para listar los generos de un libro
	 * @param isbn el isbn del libro 
	 * @return un ArrayList con los nombres de los generos a los que pertenece
	 * @throws GestorException
	 */
	public ArrayList<String> listGenres(int isbn) throws GestorException;
	
	/**
	 * Metodo para modificar libro
	 * @param book datos nuevos del libro a modificar
	 * @param codAuthor ArrayList con los nuevos codigos de los autores que han participado en el libro
	 * @param genrename ArrayList con los nuevos generos a los que pertenece el libro
	 * @return el numero de lineas modificadas
	 * @throws GestorException
	 */
	public int modificarBook(Book book, ArrayList<String> codAuthor, ArrayList<String> genrename) throws GestorException;
	
	/**
	 * Metodo para eliminar un libro
	 * @param isbn el isbn del libro que se quiere eliminar
	 * @throws GestorException
	 */
	public void eliminarBook(int isbn) throws GestorException;

	/**
	 * Metodo para listar los libros por genero
	 * @param genre el nombre del genero
	 * @return un arrayList con los libros que tienen ese genero
	 * @throws GestorException
	 */
	public ArrayList<Book> listaBookGenre(String genre) throws GestorException;

	/**
	 * Metodo para listar los libros por autor
	 * @param author el codigo del autor
	 * @return un arrayList de los libros que tienen ese autor
	 * @throws GestorException
	 */
	public ArrayList<Book> listaBookAuthor(String author) throws GestorException;

	/**
	 * Metodo para listar los libros por autor y genero
	 * @param author el codigo del autor
	 * @param genre el nombre del genero
	 * @return un arrayList de libros que tienen ese genero y autor
	 * @throws GestorException
	 */
	public ArrayList<Book> listBookAuthorGenre(String author, String genre) throws GestorException;

	/**
	 * Listar todos los libros
	 * @return un ArrayList con todos los libros
	 * @throws GestorException
	 */
	public ArrayList<Book> listAllBooks() throws GestorException;

	/**
	 * Listamos los libros superventas
	 * @return un arrayList con todos los isbn y cantidad de los libros superventas
	 * @throws GestorException
	 */
	public ArrayList<Integer> listTopSales() throws GestorException;

	/**
	 * Listado de todos los id de descuento
	 * @return un ArrayList con los id
	 * @throws GestorException
	 */
	public ArrayList<Integer> listDiscount() throws GestorException;
  
}