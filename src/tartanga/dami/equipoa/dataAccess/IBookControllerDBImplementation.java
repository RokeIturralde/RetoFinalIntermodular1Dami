package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Book;

public class IBookControllerDBImplementation implements IBookController {

	private Connection con;
	private PreparedStatement stmt;

	// Metodo para abrir la conexion con la base de datos
	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/irakurlev5?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	// Metodo para cerrar la conexion con la base de datos
	private void closeConnection() throws SQLException {
		System.out.println("Conexion cerrada");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("-----------------------");
	}

	@Override
	public void altaBook(Book book, String author, String genre) throws GestorException {
		try {
			this.openConnection();
			String insertBook = "call insertBook(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insertBook);
			stmt.setInt(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getDescription());
			stmt.setString(4, book.getEditorial());
			stmt.setInt(5, book.getStock());
			stmt.setFloat(6, book.getPrice());
			stmt.setInt(7, book.getIdDiscount());
			stmt.setDate(8, book.getPubDate());
			stmt.setString(9, author);
			stmt.setString(10, genre);
			stmt.executeUpdate();
		} catch (SQLException e1) {
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
			this.openConnection();
			stmt = con.prepareStatement(buscarBook);
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
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return book;
	}

	@Override
	public int modificarBook(Book book) throws GestorException {
		String modificarBook = "update book set title= ?, description=?, editorial=?, stock=?, price=?, idDiscount=?, pubdate=? where isbn = ?";
		int cuantos;
		try {
			this.openConnection();
			stmt = con.prepareStatement(modificarBook);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getDescription());
			stmt.setString(3, book.getEditorial());
			stmt.setInt(4, book.getStock());
			stmt.setFloat(5, book.getPrice());
			stmt.setInt(6, book.getIdDiscount());
			stmt.setDate(7, book.getPubDate());
			stmt.setInt(8, book.getIsbn());
			cuantos = stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return cuantos;
	}

	@Override
	public void eliminarBook(int isbn) throws GestorException {
		String eliminarBook = "delete from book where isbn = ?";
		try {
			this.openConnection();
			stmt = con.prepareStatement(eliminarBook);
			stmt.setInt(1, isbn);
			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error en la conexcion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
	}

	@Override
	public ArrayList<Book> listaBookGenre(String genre) throws GestorException {
		ResultSet rs = null;
		Book book = null;

		ArrayList<Book> books = new ArrayList();
		String buscarBook = "select b.* from book b, bookGenre bg where bg.genreName = ? and b.isbn=bg.isbn";
		try {
			this.openConnection();
			stmt = con.prepareStatement(buscarBook);
			stmt.setString(1, genre);
			rs = stmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setEditorial(rs.getString("editorial"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getFloat("price"));
				book.setIdDiscount(rs.getInt("discount"));
				book.setPubDate(rs.getDate("pubdate"));
				books.add(book);
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> listaBookAuthor(String author) throws GestorException {
		ResultSet rs = null;
		Book book = null;
		
		ArrayList<Book> books = new ArrayList();
		String listaBookAuthor = "select * from book b, bookauthor ba, author a where b.isbn=ba.isbn and ba.codAuthor=a.codAuthor and a.surname = ?";
		try {
			this.openConnection();
			stmt = con.prepareStatement(listaBookAuthor);
			stmt.setString(1, author);
			rs = stmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setEditorial(rs.getString("editorial"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getFloat("price"));
				book.setIdDiscount(rs.getInt("discount"));
				book.setPubDate(rs.getDate("pubdate"));
				books.add(book);
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> listBookAuthorGenre(String author, String genre) throws GestorException {
		ResultSet rs = null;
		Book book = null;
		ArrayList<Book> books = new ArrayList();
		
		String listaBookAuthorGenre = "select b.* from book b, author a, bookauthor ba, bookgenre bg where"
				+ " b.isbn=ba.isbn and ba.codAuthor = a.codAuthor and b.isbn=bg.isbn and bg.genreName = ? or a.surname= ? ";
		try {
			this.openConnection();
			stmt = con.prepareStatement(listaBookAuthorGenre);
			stmt.setString(1, author);
			stmt.setString(2, genre);
			rs = stmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setEditorial(rs.getString("editorial"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getFloat("price"));
				book.setIdDiscount(rs.getInt("discount"));
				book.setPubDate(rs.getDate("pubdate"));
				books.add(book);
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;
	}
	
	public ArrayList<Book> listAllBooks() throws GestorException {
		ArrayList<Book> books = new ArrayList();
		Book book = null;
		ResultSet rs = null;
		String listAllBooks = "SELECT * FROM BOOK";
		
		try {
			this.openConnection();
			stmt = con.prepareStatement(listAllBooks);
			rs = stmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setEditorial(rs.getString("editorial"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getFloat("price"));
				book.setIdDiscount(rs.getInt("idDiscount"));
				book.setPubDate(rs.getDate("pubDate"));
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;
	}

}
