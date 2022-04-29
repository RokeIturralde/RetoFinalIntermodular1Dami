package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;

public class IAuthorControllerDBImplementation implements IAuthorController {

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

	// Insertar un nuevo Autor en la base de Datos
	@Override
	public void altaAuthor(Author author) throws GestorException {

		try {
			this.openConnection();
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
				this.closeConnection();
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

		String busquedaAutor = "SELECT * FROM AUTHOR WHERE CODAUTHOR = ?";
		try {
			this.openConnection();
			stmt = con.prepareStatement(busquedaAutor);
			stmt.setString(1, codAuthor);
			rs = stmt.executeQuery();
			if (rs.next()) {
				autor = new Author();
				autor.setCodAuthor(codAuthor);
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
				this.closeConnection();
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
			this.openConnection();
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
				this.closeConnection();
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
			this.openConnection();
			String eliminarAutor = "DELETE FROM AUTHOR WHERE CODAUTHOR=?";
			stmt = con.prepareStatement(eliminarAutor);
			stmt.setString(1, codAuthor);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
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
		return cambio;
	}
}