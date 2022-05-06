package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Genre;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class WAdmin extends JDialog implements ActionListener, FocusListener {

	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IUserController userInterface;
	private JTextField textIsbn;
	private JTextField textTitulo;
	private JTextField textEditorial;
	private JTextField textStock;
	private JTextField textPrecio;
	private JTextField textAutor;
	private JTextField textGenero;
	private JButton btnModificar;
	private JButton btnRegistrar;
	private JComboBox<Integer> comboBoxDescuento;
	private JTextArea textDescripcion;
	private Book book;
	private JCalendar calendario;
	private JTabbedPane tabbedPane;
	private JPanel libros;
	private JPanel autores;
	private JPanel generos;
	private JPanel perfil;
	private JButton btnEliminar;
	private JButton btnCancelar;

	public WAdmin(User user, IBookController bookInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IUserController userInterface) {
		this.authorInterface = authorInterface;
		this.bookInterface = bookInterface;
		this.genreInterface = genreInterface;
		this.userInterface = userInterface;

		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1047, 680);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(0, 0, 1031, 641);
		getContentPane().add(tabbedPane);

		libros = new JPanel();
		libros.setBorder(null);
		libros.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		libros.setForeground(Color.WHITE);
		libros.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Libros", null, libros, null);
		libros.setLayout(null);

		JLabel lblIsbn = new JLabel("Introduce isbn:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setForeground(Color.WHITE);
		lblIsbn.setBounds(36, 46, 101, 14);
		libros.add(lblIsbn);

		textIsbn = new JTextField();
		textIsbn.setBounds(36, 71, 137, 20);
		libros.add(textIsbn);
		textIsbn.setColumns(10);
		textIsbn.addFocusListener(this);

		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(36, 127, 137, 20);
		libros.add(textTitulo);
		textTitulo.addFocusListener(this);

		JLabel lblTitulo = new JLabel("Introduce el titulo:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(36, 102, 137, 14);
		libros.add(lblTitulo);

		textEditorial = new JTextField();
		textEditorial.setColumns(10);
		textEditorial.setBounds(36, 183, 137, 20);
		libros.add(textEditorial);
		textEditorial.addFocusListener(this);

		JLabel lblEditorial = new JLabel("Introduce la editorial:");
		lblEditorial.setForeground(Color.WHITE);
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(36, 158, 137, 14);
		libros.add(lblEditorial);

		textStock = new JTextField();
		textStock.setColumns(10);
		textStock.setBounds(36, 239, 137, 20);
		libros.add(textStock);
		textStock.addFocusListener(this);

		JLabel lblStock = new JLabel("Introduce el stock:");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(36, 214, 137, 14);
		libros.add(lblStock);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(36, 295, 137, 20);
		libros.add(textPrecio);
		textPrecio.addFocusListener(this);

		JLabel lblPrecio = new JLabel("Introduce el precio:");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(36, 270, 137, 14);
		libros.add(lblPrecio);

		JLabel lblDescuento = new JLabel("Selecciona el id de descuento:");
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescuento.setBounds(36, 326, 197, 14);
		libros.add(lblDescuento);

		comboBoxDescuento = new JComboBox<Integer>();
		comboBoxDescuento.setBounds(36, 351, 137, 22);
		libros.add(comboBoxDescuento);
		try {
			for (Integer i : bookInterface.listDiscount()) {
				comboBoxDescuento.addItem(i);
			}
		} catch (GestorException e) {
			JOptionPane.showMessageDialog(this, "Error en la lectura de descuentos", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		JLabel lblFecha = new JLabel("Selecciona la fecha de publicacion:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(36, 384, 221, 14);
		libros.add(lblFecha);

		calendario = new JCalendar();
		calendario.setBounds(36, 409, 197, 177);
		libros.add(calendario);

		JLabel lblDescripcion = new JLabel("Descripcion del libro:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(293, 386, 221, 14);
		libros.add(lblDescripcion);

		textDescripcion = new JTextArea();
		textDescripcion.setBounds(293, 409, 361, 177);
		libros.add(textDescripcion);
		textDescripcion.addFocusListener(this);

		JLabel lblAutor = new JLabel("Introduce el codigo del autor:");
		lblAutor.setVerticalAlignment(SwingConstants.TOP);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutor.setBounds(293, 48, 197, 20);
		libros.add(lblAutor);

		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(293, 71, 137, 20);
		libros.add(textAutor);
		textAutor.addFocusListener(this);

		JLabel lblGenero = new JLabel("Introduce el nombre del genero:");
		lblGenero.setVerticalAlignment(SwingConstants.TOP);
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(293, 214, 213, 22);
		libros.add(lblGenero);

		textGenero = new JTextField();
		textGenero.setColumns(10);
		textGenero.setBounds(293, 239, 137, 20);
		libros.add(textGenero);
		textGenero.addFocusListener(this);

		btnModificar = new JButton("Guardar cambios");
		btnModificar.setEnabled(false);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFocusPainted(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.GRAY);
		btnModificar.setBounds(774, 429, 159, 23);
		libros.add(btnModificar);
		btnModificar.addActionListener(this);

		btnRegistrar = new JButton("Registrar libro");
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBackground(Color.GRAY);
		btnRegistrar.setBounds(774, 497, 159, 23);
		libros.add(btnRegistrar);
		btnRegistrar.addActionListener(this);

		btnEliminar = new JButton("Eliminar libro");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setEnabled(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.GRAY);
		btnEliminar.setBounds(774, 463, 159, 23);
		libros.add(btnEliminar);
		btnEliminar.addActionListener(this);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(Color.GRAY);
		btnCancelar.setBounds(774, 531, 159, 23);
		libros.add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBounds(616, 102, 227, 101);
		libros.add(panel);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		btnCancelar.addActionListener(this);

		autores = new JPanel();
		autores.setBorder(null);
		autores.setBackground(Color.DARK_GRAY);
		autores.setForeground(Color.WHITE);
		tabbedPane.addTab("Autores", null, autores, null);
		autores.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.GRAY);

		generos = new JPanel();
		generos.setForeground(Color.WHITE);
		generos.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Generos", null, generos, null);
		generos.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.GRAY);

		perfil = new JPanel();
		perfil.setForeground(Color.WHITE);
		perfil.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Perfil", null, perfil, null);
		perfil.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.DARK_GRAY);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnRegistrar)) {
			if (textEditorial.getText().isEmpty() || textIsbn.getText().isEmpty() || textTitulo.getText().isEmpty()
					|| textStock.getText().isEmpty() || textPrecio.getText().isEmpty() || textAutor.getText().isEmpty()
					|| textGenero.getText().isEmpty() || comboBoxDescuento.getSelectedIndex() == -1
					|| textDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					book = new Book();
					book.setIsbn(Integer.valueOf(textIsbn.getText()));
					book.setTitle(textTitulo.getText());
					book.setDescription(textDescripcion.getText());
					book.setEditorial(textEditorial.getText());
					book.setStock(Integer.valueOf(textStock.getText()));
					book.setPrice(Float.valueOf(textPrecio.getText()));
					book.setIdDiscount(Integer.valueOf(comboBoxDescuento.getSelectedItem().toString()));
					book.setAuthor(textAutor.getText());
					book.setGenre(textGenero.getText());
					Date fecha = new Date(calendario.getDate().getTime());
					book.setPubDate(fecha);
					bookInterface.altaBook(book);

					JOptionPane.showMessageDialog(this, "Libro registrado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);

					textIsbn.setText("");
					textTitulo.setText("");
					textDescripcion.setText("");
					textEditorial.setText("");
					textStock.setText("");
					textPrecio.setText("");
					comboBoxDescuento.setSelectedIndex(-1);
					calendario.setCalendar(Calendar.getInstance());
					textAutor.setText("");
					textGenero.setText("");
				} catch (GestorException e1) {
					JOptionPane.showMessageDialog(this, "Error al registrar el libro", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(btnModificar)) {
			if (textEditorial.getText().isEmpty() || textIsbn.getText().isEmpty() || textTitulo.getText().isEmpty()
					|| textStock.getText().isEmpty() || textPrecio.getText().isEmpty() || textAutor.getText().isEmpty()
					|| textGenero.getText().isEmpty() || comboBoxDescuento.getSelectedIndex() == -1
					|| textDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int seleccion = JOptionPane.showConfirmDialog(libros, "Quieres modificar este libro?", "Aviso", 0);
				if (seleccion == 0) {
					try {
						book = new Book();
						book.setIsbn(Integer.valueOf(textIsbn.getText()));
						book.setTitle(textTitulo.getText());
						book.setDescription(textDescripcion.getText());
						book.setEditorial(textEditorial.getText());
						book.setStock(Integer.valueOf(textStock.getText()));
						book.setPrice(Float.valueOf(textPrecio.getText()));
						book.setIdDiscount(Integer.valueOf(comboBoxDescuento.getSelectedItem().toString()));
						book.setAuthor(textAutor.getText());
						book.setGenre(textGenero.getText());
						Date fecha = new Date(calendario.getDate().getTime());
						book.setPubDate(fecha);
						bookInterface.modificarBook(book);

						JOptionPane.showMessageDialog(this, "Libro modificado con exito", "Ta bien",
								JOptionPane.INFORMATION_MESSAGE);

						textIsbn.setText("");
						textIsbn.setEnabled(true);
						textTitulo.setText("");
						textDescripcion.setText("");
						textEditorial.setText("");
						textStock.setText("");
						textPrecio.setText("");
						comboBoxDescuento.setSelectedIndex(-1);
						calendario.setCalendar(Calendar.getInstance());
						textAutor.setText("");
						textGenero.setText("");
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnRegistrar.setEnabled(true);
					} catch (GestorException e1) {
						JOptionPane.showMessageDialog(this, "Error al modificar el libro", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		}
		if (e.getSource().equals(btnEliminar)) {
			int seleccion = JOptionPane.showConfirmDialog(libros, "Quieres eliminar este libro?", "Aviso", 0);
			if (seleccion == 0) {
				try {
					bookInterface.eliminarBook(Integer.valueOf(textIsbn.getText()));

					JOptionPane.showMessageDialog(this, "Libro eliminado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);

					textIsbn.setText("");
					textIsbn.setEnabled(true);
					textTitulo.setText("");
					textDescripcion.setText("");
					textEditorial.setText("");
					textStock.setText("");
					textPrecio.setText("");
					comboBoxDescuento.setSelectedIndex(-1);
					calendario.setCalendar(Calendar.getInstance());
					textAutor.setText("");
					textGenero.setText("");
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnRegistrar.setEnabled(true);
				} catch (NumberFormatException | GestorException e1) {
					JOptionPane.showMessageDialog(this, "Error al eliminar el libro", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(btnCancelar)) {
			textIsbn.setText("");
			textTitulo.setText("");
			textDescripcion.setText("");
			textEditorial.setText("");
			textStock.setText("");
			textPrecio.setText("");
			comboBoxDescuento.setSelectedIndex(-1);
			calendario.setCalendar(Calendar.getInstance());
			textAutor.setText("");
			textGenero.setText("");
			if (btnModificar.isEnabled()) {
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnRegistrar.setEnabled(true);
				textIsbn.setEnabled(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textIsbn)) {
			boolean b = false;
			if (insertString(50, textIsbn.getText())) {
				textIsbn.setText("");
				b = true;
			}
			if (textIsbn.getText().matches("[a-zA-Z]+")) {
				textIsbn.setText("");
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
				b = true;
			}
			if (!b && textIsbn.getText().length() > 0) {
				try {
					book = bookInterface.buscarBook(Integer.valueOf(textIsbn.getText()));
					if (book != null) {
						int seleccion = JOptionPane.showConfirmDialog(libros,
								"Ese isbn ya a sido introducido quieres modificarlo?", "Aviso", 0);
						if (seleccion == 0) {
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							btnRegistrar.setEnabled(false);

							textIsbn.setText(String.valueOf(book.getIsbn()));
							textIsbn.setEnabled(false);
							textAutor.setText(book.getAuthor());
							textGenero.setText(book.getGenre());
							textDescripcion.setText(book.getDescription());
							textEditorial.setText(book.getEditorial());
							textPrecio.setText(String.valueOf(book.getPrice()));
							textStock.setText(String.valueOf(book.getStock()));
							textTitulo.setText(book.getTitle());
							comboBoxDescuento.setSelectedItem(book.getIdDiscount());
							calendario.setDate(book.getPubDate());
						} else {
							textIsbn.setText("");
						}
					}
				} catch (NumberFormatException | GestorException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(textStock)) {
			if (textStock.getText().matches("[a-zA-Z]+")) {
				textStock.setText("");
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			if (insertString(50, textIsbn.getText())) {
				textIsbn.setText("");
			}
		}
		if (e.getSource().equals(textPrecio)) {
			if (textPrecio.getText().matches("[a-zA-Z]+")) {
				textPrecio.setText("");
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			if (insertString(50, textPrecio.getText())) {
				textPrecio.setText("");
			}
		}
		if (e.getSource().equals(textAutor)) {
			if (insertString(50, textAutor.getText())) {
				textAutor.setText("");
			}
			try {
				Author author = authorInterface.buscarAuthor(textAutor.getText());
				if (author == null && !textAutor.getText().isEmpty()) {
					int seleccion = JOptionPane.showConfirmDialog(libros, "Ese autor no existe quieres añadirlo?", "Aviso", 0);
					if (seleccion == 0) {
						tabbedPane.setSelectedIndex(1);
					} else {
						textAutor.setText("");
					}
				}
			} catch (GestorException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(textGenero)) {
			if (insertString(50, textGenero.getText())) {
				textGenero.setText("");
			}
			try {
				Genre genre = genreInterface.buscarGenre(textGenero.getText());
				if (genre == null && !textGenero.getText().isEmpty()) {
					int seleccion = JOptionPane.showConfirmDialog(libros, "Ese genero no existe quieres añadirlo?", "Aviso", 0);
					if (seleccion == 0) {
						tabbedPane.setSelectedIndex(2);
					} else {
						textGenero.setText("");
					}
				}
			} catch (GestorException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(textTitulo)) {
			if (insertString(50, textTitulo.getText())) {
				textTitulo.setText("");
			}
		}
		if (e.getSource().equals(textEditorial)) {
			if (insertString(50, textEditorial.getText())) {
				textEditorial.setText("");
			}
		}
		if (e.getSource().equals(textDescripcion)) {
			if (insertString(1000, textDescripcion.getText())) {
				textDescripcion.setText("");
			}
		}
	}

	public boolean insertString(int maximo, String texto) {
		if (texto.length() > maximo) {
			JOptionPane.showMessageDialog(this, "Excedido el limite de caracteres (50)", "Error",
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
}
