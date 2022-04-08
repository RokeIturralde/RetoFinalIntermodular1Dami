package tartanga.dami.equipoa.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Genre;

public class GenreControllerDB implements IGenreController {

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
	public void altaGenre(Genre genre) throws GestorException {
		this.openConnection();

		try {
			String insertarGenero = "insert into genre values (?,?)";
			stmt = con.prepareStatement(insertarGenero);
			stmt.setString(1, genre.getGenreName());
			stmt.setString(2, genre.getDescription());
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

	@Override
	public Genre buscarGenre(String genreName) throws GestorException {
		ResultSet rs = null;
		Genre genero = null;

		// Abrir conexion con BD
		openConnection();
		String busquedaGenero = "select * from genre where genreName = ?";
		try {
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
		return genero;
	}

	@Override
	public int modificarGenre(Genre genre) throws GestorException {
		this.openConnection();
		int num;
		try {
			String insertarProp = "update genre set description=? where genreName=?";
			stmt = con.prepareStatement(insertarProp);
			stmt.setString(1, genre.getDescription());
			stmt.setString(2, genre.getGenreName());
			num = stmt.executeUpdate();
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
		return num;
	}

	@Override
	public void eliminarGenre(String genreName) throws GestorException {
		this.openConnection();

		try {
			String insertarProp = "delete * from genre where genreName=?";
			stmt = con.prepareStatement(insertarProp);
			stmt.setString(1, genreName);
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

}
