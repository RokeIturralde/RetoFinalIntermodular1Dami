package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.User;
import tartanga.dami.equipoa.model.Administrator;
import tartanga.dami.equipoa.model.ConnectionOpenClose;
import tartanga.dami.equipoa.model.Partner;
/**
 * 
 * @author Sendoa
 *
 */
public class IUserControllerDBImplementation implements IUserController {


	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();


	@Override
	public User userLogIn(String userName, String password) throws GestorException {
		ResultSet rs;
		User user = null;
		String userLogIn = "CALL logIn(?,?)";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(userLogIn);

			stmt.setString(1, userName);
			stmt.setString(2, password);

			rs = stmt.executeQuery();

			if (rs.next()) {
				if(rs.getString("tipo").equalsIgnoreCase("A")) {
					user = new Administrator();
				} else {
					user = new Partner();
				}
				user.setUserName(userName);
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("passwd"));
				user.setSurname(rs.getString("surname"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getInt("phone"));
				user.setTipo(rs.getString("tipo").charAt(0));
				if(user instanceof Partner) {
					((Partner)user).setNumAccount(rs.getInt("numaccount"));
				}
			} 
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
		return user;
	}


	@Override
	public void altaUsuario(User user) throws GestorException {

		try {
			con = connection.openConnection();
			String insertUser = "call altaPartner(?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(insertUser);

			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getSurname());
			stmt.setString(5, user.getName());
			stmt.setString(6, user.getAddress());
			stmt.setInt(7, user.getPhone());
			stmt.setString(8, "P");
			stmt.setInt(9, ((Partner) user).getNumAccount());

			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
	public User buscarUser(String userName) throws GestorException {
		ResultSet rs = null;
		User user = null;
		String searchUser = "select u.*,p.numaccount from user u,partner p where u.username=p.username and u.username = ?";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(searchUser);

			stmt.setString(1, userName);

			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new Partner();
				user.setUserName(userName);
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("passwd"));
				user.setSurname(rs.getString("surname"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getInt("phone"));
				user.setTipo(rs.getString("tipo").charAt(0));
				((Partner)user).setNumAccount(rs.getInt("numaccount"));
			} 
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
		return user;
	}


	@Override
	public int modificarUser(User user) throws GestorException {
		int cambios = 0;
		String updateUser = "CALL modificarPartner(?,?,?,?,?,?,?,?)";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(updateUser);
			
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getSurname());
			stmt.setString(5, user.getName());
			stmt.setString(6, user.getAddress());
			stmt.setInt(7, user.getPhone());
			stmt.setInt(8, ((Partner) user).getNumAccount());

			cambios = stmt.executeUpdate();
			
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
		return cambios;
	}


	@Override
	public int eliminarUser(String userName) throws GestorException {
		int cambios;
		String deleteUser = "delete from user where username = ? and tipo = ?";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(deleteUser);
			
			stmt.setString(1, userName);
			stmt.setString(2, "P");
			
			cambios = stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
		return cambios;
	}

	@Override
	public void anadirAutor(String userName, String autor) throws GestorException {
		String anadirAutor = "insert into partnerauthor values(?,?)";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(anadirAutor);
			
			stmt.setString(1, userName);
			stmt.setString(2, autor);
			
			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
	public void anadirGenero(String userName, String genero) throws GestorException {
		String anadirGenero = "insert into partnergenre values(?,?)";
		
		try {
			con = connection.openConnection();
			
			stmt = con.prepareStatement(anadirGenero);
			
			stmt.setString(1, userName);
			stmt.setString(2, genero);
			
			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
	public ArrayList<String> userGenero(String username) throws GestorException {
		ArrayList<String> userGenero = new ArrayList<String>();
		String buscarGenero = "select genreName from partnerGenre where username = ?";
		ResultSet rs = null;
		
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(buscarGenero);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				String genre;
				genre = rs.getString("genreName");
				userGenero.add(genre);
			}
		}  catch (SQLException e1) {
			String error = "Error con la conexion con la base de datos";
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
		return userGenero;
	}

}