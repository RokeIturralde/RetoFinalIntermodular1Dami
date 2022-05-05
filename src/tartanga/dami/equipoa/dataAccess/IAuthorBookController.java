package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.AuthorBook;

public interface IAuthorBookController {

	public ArrayList<AuthorBook> listAuthorBook(String username) throws GestorException;
	
}
