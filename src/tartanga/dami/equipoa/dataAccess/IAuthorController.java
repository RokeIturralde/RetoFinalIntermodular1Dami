package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;

public interface IAuthorController {

	public void altaAuthor(Author author) throws GestorException;

	public Author buscarAuthor(String codAuthor) throws GestorException;

	public int modificarAuthor(Author author) throws GestorException;

	public int eliminarAuthor(String codAuthor) throws GestorException;

	public ArrayList<Author> listarAutoresPreferidos(String username) throws GestorException;

	public ArrayList<Author> listarComprasAutores(String username) throws GestorException;

}
