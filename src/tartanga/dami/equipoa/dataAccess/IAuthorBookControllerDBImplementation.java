package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.AuthorBook;
import tartanga.dami.equipoa.model.ConnectionOpenClose;

public class IAuthorBookControllerDBImplementation implements IAuthorBookController {

	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();

	@Override
	public ArrayList<AuthorBook> listAuthorBook(String username) throws GestorException {
		ResultSet rs = null;
		AuthorBook authorBook;
		ArrayList<AuthorBook> listAuthorBook = new ArrayList();

		String sentencia = "select distinct b.title,b.description,a.name,a.surname,b.price from author a, book b, bookauthor ba, partnerauthor pa,bookgenre bg, partnergenre pg where (pa.username=? and pa.codauthor=ba.codauthor and ba.isbn=b.isbn and ba.codAuthor=a.codAuthor) or (pg.username=? and pg.genreName=bg.genreName and bg.isbn=b.isbn and bg.isbn=ba.isbn and ba.codAuthor=a.codAuthor)";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, username);
			stmt.setString(2, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				authorBook = new AuthorBook();
				authorBook.setTitle(rs.getString("b.title"));
				authorBook.setDescription(rs.getString("b.description"));
				authorBook.setName(rs.getString("a.name"));
				authorBook.setSurname(rs.getString("a.surname"));
				authorBook.setPrice(rs.getFloat("b.price"));
				listAuthorBook.add(authorBook);
			}
		} catch (SQLException e) {
			String error = "Error en la conexion de la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e) {
				String error = "Error al cerrar conexion con la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return listAuthorBook;
	}

}
