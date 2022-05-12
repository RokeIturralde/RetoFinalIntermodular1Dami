package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.ConnectionOpenClose;

public class IAuthorControllerDBImplementation implements IAuthorController {

	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();

	// Abrir conexion con nuestra base de datos
	/*private void openConnection() {
		try {
			con = connection.openConnection();
		} catch (SQLException e) {
			System.out.println("No se puede acceder a la base de Datos");
		} catch (GestorException e) {
			e.printStackTrace();
		}
	}
	*/

	// Cerrar conexion con la base de datos
	/*private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}
	*/

	// Insertar un nuevo Autor en la base de Datos
	@Override
	public void altaAuthor(Author author) throws GestorException {

		try {
			con = connection.openConnection();
			String insertarAutor = "INSERT INTO AUTHOR VALUES (?,?,?,?,?)";
			stmt = con.prepareStatement(insertarAutor);
			stmt.setString(1, author.getCodAuthor());
			stmt.setString(2, author.getName());
			stmt.setString(3, author.getSurname());
			stmt.setDate(4, author.getBirthDate());
			stmt.setDate(5, author.getDeathDate());
			stmt.executeUpdate();
		} catch (Exception e) {
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

	}

	// Busqueda de un Autor, te devulve sus datos si lo encuentra, de lo contrario
	// te devuelve un null
	@Override
	public Author buscarAuthor(String codAuthor) throws GestorException {
		ResultSet rs = null;
		Author autor = null;

		// Abrir conexion con BD

		String busquedaAutor = "SELECT * FROM AUTHOR WHERE CODAUTHOR = ? OR NAME = ? OR SURNAME = ?";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(busquedaAutor);
			stmt.setString(1, codAuthor);
			stmt.setString(2, codAuthor);
			stmt.setString(3, codAuthor);
			rs = stmt.executeQuery();
			if (rs.next()) {
				autor = new Author();
				autor.setCodAuthor(rs.getString("codAuthor"));
				autor.setName(rs.getString("name"));
				autor.setSurname(rs.getString("surname"));
				autor.setBirthDate(rs.getDate("birthDate"));
				autor.setDeathDate(rs.getDate("deathDate"));
			} else {
				autor = null;

			}

			if (rs != null) {
				rs.close();
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
		return autor;
	}

	// Modifica un autor mediante su clave primaria, te devuelve 1 si se ha
	// modificado correctamente
	@Override
	public int modificarAuthor(Author author) throws GestorException {

		int num;
		try {
			con = connection.openConnection();
			String modificarAutor = "UPDATE AUTHOR SET NAME=?, SURNAME=?, BIRTHDATE=?, DEATHDATE=? WHERE CODAUTHOR=?";
			stmt = con.prepareStatement(modificarAutor);
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getSurname());
			stmt.setDate(3, author.getBirthDate());
			stmt.setDate(4, author.getDeathDate());
			stmt.setString(5, author.getCodAuthor());
			num = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la conexion de la base de datos, algo no va bien con la modificacion";
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
		return num;
	}

	// Eliminas un autor mediante su clave primaria
	@Override
	public int eliminarAuthor(String codAuthor) throws GestorException {
		int cambio;
		try {
			con = connection.openConnection();
			String eliminarAutor = "DELETE FROM AUTHOR WHERE CODAUTHOR=?";
			stmt = con.prepareStatement(eliminarAutor);
			stmt.setString(1, codAuthor);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la eliminacion del autor";
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
		return cambio;
	}

	@Override
	public ArrayList<Author> listarAutoresPreferidos(String username) throws GestorException {
		ArrayList<Author> autores = new ArrayList<>();
		ResultSet rs = null;
		Author autor = null;
		
		String busquedaProp = "select a.name,a.surname,a.codAuthor from author a, partnerauthor pa where pa.username=? and pa.codAuthor=a.codAuthor";
		try {
			// Abrir conexion con BD
			con = connection.openConnection();
			stmt = con.prepareStatement(busquedaProp);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				autor = new Author();
				autor.setName(rs.getString("a.name"));
				autor.setSurname(rs.getString("a.surname"));
				autor.setCodAuthor(rs.getString("a.codAuthor"));
				autores.add(autor);
			}

			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			String error = "Error en el listado de autores preferidos";
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
		return autores;
	}

	@Override
	public ArrayList<Author> listarComprasAutores(String username) throws GestorException {
		ArrayList<Author> autores = new ArrayList<>();
		ResultSet rs = null;
		Author autor = null;

		String busquedaProp = "select pa.codAuthor from partnerAuthor pa, purchase p where p.username=? and p.username=pa.username";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(busquedaProp);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				autor = new Author();
				autor.setCodAuthor(rs.getString("pa.codAuthor"));
				autores.add(autor);
			}

			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			String error = "Error en el guardado de autores";
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
		return autores;
	}

	@Override
	public int borrarAutorPreferidos(String surname, String username, String name) throws GestorException {
		int cambio;
		String eliminarAutorPreferido = "delete pa from partnerauthor pa,author a where pa.username=? and pa.codAuthor=a.codAuthor and a.surname=? and a.name=?";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(eliminarAutorPreferido);
			stmt.setString(1, username);
			stmt.setString(2, surname);
			stmt.setString(3, name);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la eliminacion de un autor preferido";
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
		return cambio;
	}

	@Override
	public int insertarAutorPreferido(String username, String codAuthor) throws GestorException {
		int cambio;
		String insertarAutorPreferido = "insert into partnerAuthor values (?,?)";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(insertarAutorPreferido);
			stmt.setString(1, username);
			stmt.setString(2, codAuthor);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la insercion de un autor preferido";
			GestorException exception = new GestorException(error);
			e.printStackTrace();
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
		return cambio;
	}

	@Override
	public ArrayList<Author> listarAutores() throws GestorException {
		ResultSet rs = null;
		ArrayList<Author> autores = new ArrayList<>();
		Author autor = null;

		// Abrir conexion con BD

		String listadoAutores = "SELECT CODAUTHOR,SURNAME,NAME FROM AUTHOR";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(listadoAutores);
			rs = stmt.executeQuery();
			while (rs.next()) {
				autor = new Author();
				autor.setCodAuthor(rs.getString("CODAUTHOR"));
				autor.setSurname(rs.getString("SURNAME"));
				autor.setName(rs.getString("NAME"));
				autores.add(autor);
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en el listado de los Autores";
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
		return autores;
	}

}
