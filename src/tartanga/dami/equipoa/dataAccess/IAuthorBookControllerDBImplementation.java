package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.AuthorBook;

public class IAuthorBookControllerDBImplementation implements IAuthorBookController{

	private Connection con;
	private PreparedStatement stmt;

	// Abrir conexion con nuestra base de datos
	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/irakurle?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("No se puede acceder a la base de Datos");
		}
	}

	// Cerrar conexion con la base de datos
	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}
	@Override
	public ArrayList<AuthorBook> listAuthorBook(String username) throws GestorException {
		ResultSet rs = null;
		AuthorBook authorBook = null;
		ArrayList<AuthorBook> listAuthorBook = new ArrayList();
		
		String sentencia = "select distinct b.title,b.description,a.name,a.surname,b.price from author a, book b, bookauthor ba, partnerauthor pa,bookgenre bg, partnergenre pg where (pa.username=? and pa.codauthor=ba.codauthor and ba.isbn=b.isbn and ba.codAuthor=a.codAuthor) or (pg.username=? and pg.genreName=bg.genreName and bg.isbn=b.isbn and bg.isbn=ba.isbn and ba.codAuthor=a.codAuthor)";
		try {
			this.openConnection();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, username);
			stmt.setString(2, username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				authorBook.setTitle(rs.getString("title"));
				authorBook.setDescription(rs.getString("description"));
				authorBook.setSurname(rs.getString("surname"));
				authorBook.setPrice(rs.getFloat("price"));
				listAuthorBook.add(authorBook);
			}
		}  catch (SQLException e) {
			String error = "Error en la conexion de la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al cerrar conexion con la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return listAuthorBook;
	}

	
	
}
