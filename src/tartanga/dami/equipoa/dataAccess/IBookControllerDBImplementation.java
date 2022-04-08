package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public class IBookControllerDBImplementation implements IBookController{

	private Connection con;
	private PreparedStatement stmt;
	
	//Metodo para abrir la conexion con la base de datos
		private void openConnection(){
			 try {
			  String url ="jdbc:mysql://localhost:3306/irakurle?serverTimezone=Europe/Madrid&useSSL=false";
			  con =  DriverManager.getConnection(url,"root" ,"abcd*1234");
			} catch (SQLException e) {
				System.out.println("Error al intentar abrir la BD");
			 }	
		}
		
	//Metodo para cerrar la conexion con la base de datos
		private void closeConnection() throws SQLException {
			System.out.println("Conexion cerrada");
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
			System.out.println("-----------------------");
		}
	
	@Override
	public void altaBook(Book book) throws GestorException {
		try {
			String insertBook = "Insert into book values (?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(insertBook);
			stmt.setInt(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getDescription());
			stmt.setString(4, book.getEditorial());
			stmt.setInt(5, book.getStock());
			stmt.setFloat(6, book.getPrice());
			stmt.setInt(7, book.getIdDiscount());
			stmt.setDate(8, book.getPubDate());
		}
		catch (SQLException e1){
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
	}

	@Override
	public Book buscarBook(int isbn) throws GestorException {
		ResultSet rs = null;
		Book book = null;
		
		String buscarBook = "select * from book where isbn = ?";
		try {
			stmt=con.prepareStatement(buscarBook);
			stmt.setInt(1, isbn);
			rs = stmt.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setIsbn(isbn);
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setEditorial(rs.getString("editorial"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getFloat("price"));
				book.setIdDiscount(rs.getInt("discount"));
				book.setPubDate(rs.getDate("pubdate"));
			}
		}
		catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		}
		finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return null;
	}

	@Override
	public int modificarBook(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void eliminarBook(String isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> listaBookGenre() {
		
		return null;
	}

	@Override
	public List<Book> listaBookAuthor() {
		// select:  select * from book b, partnerauthor pa, partner u, author a, bookauthor ba where u.username=pa.username and pa.codAuthor=ba.codAuthor and ba.isbn=b.isbn and u.username="gitanito77";
		return null;
	}

	@Override
	public List<Book> listBookAuthorGenre() {
		// TODO Auto-generated method stub
		return null;
	}

}
