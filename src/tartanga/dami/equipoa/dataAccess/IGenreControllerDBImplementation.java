package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.ConnectionOpenClose;
import tartanga.dami.equipoa.model.Genre;

public class IGenreControllerDBImplementation implements IGenreController {
	private Connection con;
	private PreparedStatement stmt;
	final String insertarGenero = "INSERT INTO genre VALUES( ?,?)";
	private ConnectionOpenClose connection = new ConnectionOpenClose();
	
	// Abrir conexion con nuestra base de datos
	/*private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/irakurle?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("No se puede acceder a la base de Datos: " + e.getMessage());
		}
	}

	// Cerrar conexion con la base de datos
	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}*/

	@Override
	public void altaGenre(Genre genre) throws GestorException {
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(insertarGenero);
			stmt.setString(1, genre.getGenreName());
			stmt.setString(2, genre.getDescription());
			stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en el alta de Genero, asegurate de que no se haya introducido previamente";
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

	@Override
	public Genre buscarGenre(String genreName) throws GestorException {
		ResultSet rs = null;
		Genre genero = null;

		// Abrir conexion con BD

		String busquedaGenero = "SELECT * FROM GENRE WHERE GENRENAME = ?";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(busquedaGenero);
			stmt.setString(1, genreName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				genero = new Genre();
				genero.setGenreName(genreName);
				genero.setDescription(rs.getString("description"));
			} else {
				genero = null;

			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en la busqueda de un genero";
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
		return genero;
	}

	@Override
	public int modificarGenre(Genre genre) throws GestorException {

		int cambio;
		try {
			con = connection.openConnection();
			String insertarProp = "UPDATE GENRE SET DESCRIPTION=? WHERE GENRENAME=?";
			stmt = con.prepareStatement(insertarProp);
			stmt.setString(1, genre.getDescription());
			stmt.setString(2, genre.getGenreName());
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la modificacion de un genero";
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
	public int eliminarGenre(String genreName) throws GestorException {
		int cambio;
		try {
			con = connection.openConnection();
			String insertarProp = "DELETE FROM GENRE WHERE GENRENAME=?";
			stmt = con.prepareStatement(insertarProp);
			stmt.setString(1, genreName);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la eliminacion de un genero";
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

}
