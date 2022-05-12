package tartanga.dami.equipoa.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.ConnectionOpenClose;
import tartanga.dami.equipoa.model.Genre;

public class IBookControllerDBImplementation implements IBookController {

	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose connection = new ConnectionOpenClose();

	@Override
	public void altaBook(Book book) throws GestorException {
		try {
			con = connection.openConnection();
			String insertBook = "insert into book values(?,?,?,?,?,?,?,?)";

			stmt = con.prepareStatement(insertBook);
			stmt.setInt(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getDescription());
			stmt.setString(4, book.getEditorial());
			stmt.setInt(5, book.getStock());
			stmt.setFloat(6, book.getPrice());
			stmt.setInt(7, book.getIdDiscount());
			stmt.setDate(8, book.getPubDate());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
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

		String buscarBook = "select b.*,a.surname as author,bg.genreName as genre from book b,bookauthor ba, bookgenre bg, author a where b.isbn = ? and  b.isbn=ba.isbn and ba.codauthor=a.codauthor and b.isbn=bg.isbn";
		String contarAutores = "count";
		try {
			con = connection.openConnection();
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
				book.setIdDiscount(rs.getInt("idDiscount"));
				book.setPubDate(rs.getDate("pubdate"));
			}

		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return book;
	}

	@Override
	public int modificarBook(Book book, ArrayList<String> codAuthor, ArrayList<String> genrename)
			throws GestorException {
		String modificarBook = "update book set title= ?, description=?, editorial=?, stock=?, price=?, idDiscount=?, pubdate=? where isbn = ?";
		String eliminarBookAuthor = "delete from bookauthor where isbn = ?";
		String anadirAutores = "insert into bookauthor values(?,?)";
		String eliminarBookGenre = "delete from bookgenre where isbn = ?";
		String anadirGeneros = "insert into bookgenre values(?,?)";
		int cuantos;
		try {
			con = connection.openConnection();
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

			stmt = con.prepareStatement(eliminarBookAuthor);
			stmt.setInt(1, book.getIsbn());
			stmt.executeUpdate();

			stmt = con.prepareStatement(anadirAutores);
			for (int i = 0; i < codAuthor.size(); i++) {
				stmt.setString(1, codAuthor.get(i));
				stmt.setInt(2, book.getIsbn());
				stmt.executeUpdate();
			}

			stmt = con.prepareStatement(eliminarBookGenre);
			stmt.setInt(1, book.getIsbn());
			stmt.executeUpdate();

			stmt = con.prepareStatement(anadirGeneros);
			for (int i = 0; i < genrename.size(); i++) {
				stmt.setString(1, genrename.get(i));
				stmt.setInt(2, book.getIsbn());
				stmt.executeUpdate();
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			e1.printStackTrace();
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
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
			con = connection.openConnection();
			stmt = con.prepareStatement(eliminarBook);
			stmt.setInt(1, isbn);
			stmt.executeUpdate();
		} catch (SQLException e1) {
			String error = "Error en la conexcion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
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

		ArrayList<Book> books = new ArrayList<>();
		String buscarBook = "select b.* from book b, bookGenre bg where bg.genreName = ? and b.isbn=bg.isbn";
		try {
			con = connection.openConnection();
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
				connection.closeConnection(stmt, con);
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
		ArrayList<Book> books = new ArrayList<Book>();
		String listaBookAuthor = "select * from book b, bookauthor ba, author a where b.isbn=ba.isbn and ba.codAuthor=a.codAuthor and a.surname = ?";
		try {
			con = connection.openConnection();
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
				connection.closeConnection(stmt, con);
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
		ArrayList<Book> books = new ArrayList<>();

		String listaBookAuthorGenre = "select b.* from book b, author a, bookauthor ba, bookgenre bg where"
				+ " b.isbn=ba.isbn and ba.codAuthor = a.codAuthor and b.isbn=bg.isbn and bg.genreName = ? or a.surname= ? ";
		try {
			con = connection.openConnection();
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
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;
	}

	public ArrayList<Book> listAllBooks() throws GestorException {
		ArrayList<Book> books = new ArrayList<>();
		Book book = null;
		ResultSet rs = null;
		String listAllBooks = "SELECT * FROM BOOK";

		try {
			con = connection.openConnection();
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
				books.add(book);
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return books;

	}

	@Override
	public ArrayList<Integer> listTopSales() throws GestorException {
		ArrayList<Integer> listTopSales = new ArrayList();
		Book book = null;
		ResultSet rs = null;
		String sentence = "SELECT isbn, sum(quantity) as quantity FROM purchase group by isbn order by sum(quantity) desc limit 5";

		try {
			con = connection.openConnection();
			stmt = con.prepareStatement(sentence);
			rs = stmt.executeQuery();
			if (rs.next()) {
				listTopSales.add(rs.getInt("isbn"));
				listTopSales.add(rs.getInt("quantity"));
			}
			System.out.println(rs.getFetchSize());
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}

		}
		return listTopSales;
	}

	@Override
	public ArrayList<Integer> listDiscount() throws GestorException {
		ArrayList<Integer> array = new ArrayList<Integer>();
		ResultSet rs = null;
		String listAllDiscounts = "SELECT idDiscount FROM discount";

		try {
			con = connection.openConnection();

			stmt = con.prepareStatement(listAllDiscounts);

			rs = stmt.executeQuery();

			while (rs.next()) {
				array.add(rs.getInt("idDiscount"));
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}
		return array;
	}

	@Override
	public void anadirAuthor(ArrayList<String> codAuthor, int isbn) throws GestorException {
		String anadirAuthor = "insert into bookauthor values(?,?)";
		for (int i = 0; i < codAuthor.size(); i++) {
			try {
				con = connection.openConnection();

				stmt = con.prepareStatement(anadirAuthor);

				stmt.setString(1, codAuthor.get(i));
				stmt.setInt(2, isbn);

				stmt.executeUpdate();
			} catch (SQLException e1) {
				String error = "Error en la conexion con la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			} finally {
				try {
					connection.closeConnection(stmt, con);
				} catch (SQLException e1) {
					String error = "Error al cerrar la base de datos";
					GestorException exception = new GestorException(error);
					throw exception;
				}
			}
		}
	}

	@Override
	public void anadirGenre(ArrayList<String> genre, int isbn) throws GestorException {
		String anadirAuthor = "insert into bookgenre values(?,?)";
		for (int i = 0; i < genre.size(); i++) {
			try {
				con = connection.openConnection();

				stmt = con.prepareStatement(anadirAuthor);

				stmt.setString(1, genre.get(i));
				stmt.setInt(2, isbn);

				stmt.executeUpdate();
			} catch (SQLException e1) {
				String error = "Error en la conexion con la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			} finally {
				try {
					connection.closeConnection(stmt, con);
				} catch (SQLException e1) {
					String error = "Error al cerrar la base de datos";
					GestorException exception = new GestorException(error);
					throw exception;
				}
			}
		}
	}

	@Override
	public ArrayList<String> listAuthors(int isbn) throws GestorException {
		ArrayList<String> array = new ArrayList<String>();
		String listAuthors = "select codauthor from bookauthor where isbn = ?";
		ResultSet rs = null;

		try {
			con = connection.openConnection();

			stmt = con.prepareStatement(listAuthors);

			stmt.setInt(1, isbn);

			rs = stmt.executeQuery();

			while (rs.next()) {
				array.add(rs.getString("codauthor"));
			}

		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}

		return array;
	}

	@Override
	public ArrayList<String> listGenres(int isbn) throws GestorException {
		ArrayList<String> array = new ArrayList<String>();
		String listGenres = "select genrename from bookgenre where isbn = ?";
		ResultSet rs = null;

		try {
			con = connection.openConnection();

			stmt = con.prepareStatement(listGenres);

			stmt.setInt(1, isbn);

			rs = stmt.executeQuery();

			while (rs.next()) {
				array.add(rs.getString("genrename"));
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}

		return array;
	}

	@Override
	public String listAuthorsIsbn(int isbn) throws GestorException {
		String autores="";
		String listGenres = "select GROUP_CONCAT(distinct a.name,\" \",a.surname) as authors from bookauthor ba, author a where ba.isbn =? and  ba.codAuthor=a.codAuthor;";
		ResultSet rs = null;

		try {
			con = connection.openConnection();

			stmt = con.prepareStatement(listGenres);

			stmt.setInt(1, isbn);

			rs = stmt.executeQuery();

			if (rs.next()) {
				autores = rs.getString("authors");
			}
		} catch (SQLException e1) {
			String error = "Error en la conexion con la base de datos";
			GestorException exception = new GestorException(error);
			throw exception;
		} finally {
			try {
				connection.closeConnection(stmt, con);
			} catch (SQLException e1) {
				String error = "Error al cerrar la base de datos";
				GestorException exception = new GestorException(error);
				throw exception;
			}
		}

		return autores;
	}
}
