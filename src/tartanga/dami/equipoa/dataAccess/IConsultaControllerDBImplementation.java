package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.ConnectionOpenClose;
import tartanga.dami.equipoa.model.Consulta;

public class IConsultaControllerDBImplementation implements IConsultaController {

	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();

	public ArrayList<Consulta> tablaConsulta(String dato) throws GestorException {
		ResultSet rs = null;
		Consulta consulta = null;
		ArrayList<Consulta> listaConsulta = new ArrayList<>();
		String consultaQuery = "select distinct b.isbn,b.pubDate,b.title,b.description,b.price,concat(a.name,\" \",a.surname) as authors,group_concat(g.genrename) as generos from author a,book b,bookauthor ba,bookgenre bg,genre g where a.codAuthor=ba.codAuthor and g.genrename=bg.genrename and bg.isbn=b.isbn  and ba.isbn=bg.isbn and (g.genreName=? or b.title=? or a.name=? or a.surname=?) group by b.title";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(consultaQuery);
			stmt.setString(1, dato);
			stmt.setString(2, dato);
			stmt.setString(3, dato);
			stmt.setString(4, dato);
			rs = stmt.executeQuery();
			while (rs.next()) {
				consulta = new Consulta();
				consulta.setFechaNacim(rs.getDate("b.pubDate"));
				consulta.setTitulo(rs.getString("b.title"));
				consulta.setDescription(rs.getString("b.description"));
				consulta.setPrecio(rs.getFloat("b.price"));
				consulta.setGeneros(rs.getString("generos"));
				consulta.setAutores(rs.getString("authors"));
				consulta.setIsbn(rs.getInt("b.isbn"));
				listaConsulta.add(consulta);
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en el listado de la tabla consultas";
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
		return listaConsulta;
	}

	public ArrayList<Consulta> listarTodosLosDatos() throws GestorException {
		ResultSet rs = null;
		Consulta consulta = null;
		ArrayList<Consulta> listaConsulta = new ArrayList<>();
		String consultaQuery = "select distinct b.isbn,a.birthDate,b.title,b.description,b.price,concat(a.name,\" \",a.surname) as authors,group_concat(g.genrename) as generos from author a,book b,bookauthor ba,bookgenre bg,genre g where a.codAuthor=ba.codAuthor and g.genrename=bg.genrename and bg.isbn=b.isbn  and ba.isbn=bg.isbn group by b.title";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(consultaQuery);
			rs = stmt.executeQuery();
			while (rs.next()) {
				consulta = new Consulta();
				consulta.setFechaNacim(rs.getDate("a.birthDate"));
				consulta.setTitulo(rs.getString("b.title"));
				consulta.setDescription(rs.getString("b.description"));
				consulta.setPrecio(rs.getFloat("b.price"));
				consulta.setGeneros(rs.getString("generos"));
				consulta.setAutores(rs.getString("authors"));
				consulta.setIsbn(rs.getInt("b.isbn"));
				listaConsulta.add(consulta);
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en el listado de la tabla consultas";
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
		return listaConsulta;
	}
}
