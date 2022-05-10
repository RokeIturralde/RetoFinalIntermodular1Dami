package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

public class IGenreControllerDBImplementation implements IGenreController {
	private Connection con;
	private PreparedStatement stmt;
	final String insertarGenero = "INSERT INTO genre VALUES( ?,?)";

	// Abrir conexion con nuestra base de datos
	private void openConnection() {
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
	}

	@Override
	public void altaGenre(Genre genre) throws GestorException {
		this.openConnection();
		try {
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

		String busquedaGenero = "SELECT * FROM GENRE WHERE GENRENAME = ?";
		try {
			this.openConnection();
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

		int cambio;
		try {
			this.openConnection();
			String modificarGenero = "UPDATE GENRE SET DESCRIPTION=? WHERE GENRENAME=?";
			stmt = con.prepareStatement(modificarGenero);
			stmt.setString(1, genre.getDescription());
			stmt.setString(2, genre.getGenreName());
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la modificacion de un genero";
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

	@Override
	public int eliminarGenre(String genreName) throws GestorException {
		int cambio;
		try {
			this.openConnection();
			String eliminarGenero = "DELETE FROM GENRE WHERE GENRENAME=?";
			stmt = con.prepareStatement(eliminarGenero);
			stmt.setString(1, genreName);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la eliminacion de un genero";
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

	@Override
	public ArrayList<String> listarGenerosPreferidos(String username) throws GestorException {
		ArrayList<String> generos = new ArrayList<>();
		ResultSet rs = null;
		Genre genero = null;

		// Abrir conexion con BD
		openConnection();
		String listarGenerosPreferidos = "select genreName from genreauthor where username=?";
		try {
			stmt = con.prepareStatement(listarGenerosPreferidos);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				genero = new Genre();
				genero.setGenreName("genreName");
				generos.add(genero.getGenreName());
			}

			if (rs != null) {
				rs.close();
			}
			closeConnection();
		} catch (Exception e) {
			String error = "Error en el listado de generos";
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
		return generos;
	}

	@Override
	public int borrarGenerosPreferidos(String genreCode, String username) throws GestorException {
		int cambio;
		// Abrir conexion con BD
		openConnection();
		String eliminarGeneroPreferido = "DELETE FROM PARTNERGENRE WHERE GENRENAME=? AND USERNAME=?";
		try {
			stmt = con.prepareStatement(eliminarGeneroPreferido);
			stmt.setString(1, genreCode);
			stmt.setString(1, username);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la eliminacion de un genero preferido";
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

	@Override
	public ArrayList<String> listarGeneros() throws GestorException {
		ResultSet rs = null;
		ArrayList<String> generos = new ArrayList<>();

		// Abrir conexion con BD

		String listadoAutores = "SELECT GENRENAME FROM GENRE";
		try {
			this.openConnection();
			stmt = con.prepareStatement(listadoAutores);
			rs = stmt.executeQuery();
			if (rs.next()) {
				generos.add(rs.getString("GENRENAME"));
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en el listado de los Generos";
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
		return generos;
	}

	@Override
	public int insertarGeneroPreferido(String username, String genreName) throws GestorException {
		int cambio;
		// Abrir conexion con BD
		openConnection();
		String insertarAutorPreferido = "insert into partnerGenre values (?,?)";
		try {
			stmt = con.prepareStatement(insertarAutorPreferido);
			stmt.setString(1, username);
			stmt.setString(2, genreName);
			cambio = stmt.executeUpdate();
		} catch (Exception e) {
			String error = "Error en la insercion de un genero preferido";
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
