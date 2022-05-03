package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.Genre;

public class IComprasDBImplementation implements IComprasController {
	private Connection con;
	private PreparedStatement stmt;

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
	public ArrayList<Compra> historialCompras(String username) throws GestorException {
		ResultSet rs = null;
		Compra compra = null;
		ArrayList<Compra> compras = new ArrayList<>();

		// Abrir conexion con BD

		String listadoCompras = "select p.purchaseDate,a.name,a.surname,p.isbn,p.quantity,(p.quantity*b.price)-d.discount/100 from author a, book b, purchase p, discount d,partnerAuthor pa where p.username=? and pa.username=p.username and p.isbn=b.isbn and pa.codAuthor=a.codAuthor and b.idDiscount=d.idDiscount";
		try {
			this.openConnection();
			stmt = con.prepareStatement(listadoCompras);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				compra = new Compra();
				compra.setFechaCompra(rs.getDate("p.purchaseDate"));
				compra.setNombreAutor(rs.getString("a.name"));
				compra.setApellidoAutor(rs.getString("a.surname"));
				compra.setIsbn(rs.getInt("p.isbn"));
				compra.setCantidadLibros(rs.getInt("p.quantity"));
				compra.setPrecioCompra(rs.getFloat("(p.quantity*b.price)-d.discount/100"));
				compras.add(compra);
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			String error = "Error en la agregacion de datos al Array";
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
		return compras;
	}
}
