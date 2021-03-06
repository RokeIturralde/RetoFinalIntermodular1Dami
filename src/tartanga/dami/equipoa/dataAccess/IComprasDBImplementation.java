package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.ConnectionOpenClose;

/**
 * @author Eneko
 *
 */
public class IComprasDBImplementation implements IComprasController {
	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();

	// Sacar informacion de la compra, menos los autores
	@Override
	public ArrayList<Compra> historialCompras(String username) throws GestorException {
		ResultSet rs;
		Compra compra = null;
		ArrayList<Compra> compras = new ArrayList();
		String listadoCompras = "select distinct p.purchaseDate,p.isbn,p.quantity,CONCAT(a.name,\" \",a.surname) as authors,(p.quantity*b.price)-((p.quantity*b.price)*d.discount)/100 from author a, book b, purchase p, discount d,bookAuthor ba where p.username=? and p.isbn=ba.isbn and ba.codAuthor=a.codAuthor and ba.isbn=b.isbn and b.isbn=p.isbn and d.idDiscount=b.idDiscount";
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(listadoCompras);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				compra = new Compra();
				compra.setFechaCompra(rs.getDate("p.purchaseDate"));
				compra.setIsbn(rs.getInt("p.isbn"));
				compra.setCantidadLibros(rs.getInt("p.quantity"));
				compra.setAuthors(rs.getString("authors"));
				compra.setPrecioCompra(rs.getFloat("(p.quantity*b.price)-((p.quantity*b.price)*d.discount)/100"));
				compras.add(compra);
			}
		} catch (SQLException e) {
			String error = "Error en la agregacion de datos al Array de Compras";
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
		return compras;
	}

	@Override
	public float calcularPrecio(int isbn) throws GestorException {
		String sentencia = "select b.price-(b.price*d.discount)/100 as price from book b, discount d where b.isbn = ? and b.idDiscount = d.idDiscount";
		ResultSet rs;
		float precio = 0;
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(sentencia);
			stmt.setInt(1, isbn);
			rs = stmt.executeQuery();
			if (rs.next()) {
				precio = rs.getInt("price");
			}
		} catch (SQLException e) {
			String error = "Error en la agregacion de datos al Array";
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
		return precio;
	}

	@Override
	public int escribirCompra(Compra compra, String user) throws GestorException {
		String sentencia = "insert into purchase values (?, ?, ?, ?)";
		String sentencia2 = "update book set stock = stock - ? where isbn = ?";
		int cuantos;
		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, user);
			stmt.setInt(2, compra.getIsbn());
			stmt.setInt(3, compra.getCantidadLibros());
			Timestamp tsNow = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(4, tsNow);
			stmt.executeUpdate();

			stmt = con.prepareStatement(sentencia2);
			stmt.setInt(1, compra.getCantidadLibros());
			stmt.setInt(2, compra.getIsbn());
			cuantos = stmt.executeUpdate();
		} catch (SQLException e) {
			String error = "Error en el registro de compra";
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
		return cuantos;
	}
}
