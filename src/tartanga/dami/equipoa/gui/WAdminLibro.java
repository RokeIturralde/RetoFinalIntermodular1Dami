package tartanga.dami.equipoa.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Genre;

/**
 * @author Sendoa
 *
 */
public class WAdminLibro extends JPanel implements ActionListener, FocusListener {

	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private JTextField textIsbn;
	private JTextField textTitulo;
	private JTextField textEditorial;
	private JTextField textStock;
	private JTextField textPrecio;
	private JTextField textAutorLibro1;
	private JTextField textGeneroLibro1;
	private JButton btnModificarLibro;
	private JButton btnRegistrarLibro;
	private JComboBox<Integer> comboBoxDescuento;
	private JTextArea textDescripcion;
	private Book book;
	private JCalendar calendarioLibro;
	private JTabbedPane tabbedPane;
	private JPanel libros;;
	private JButton btnEliminarLibro;
	private JButton btnCancelarLibro;
	private ArrayList<String> authorList;
	private ArrayList<String> genreList;
	private JTextField textAutorLibro2;
	private JTextField textAutorLibro3;
	private JTextField textGeneroLibro2;
	private JTextField textGeneroLibro3;

	/**
	 * @param authorInterface Interfaz de autores
	 * @param genreInterface Interfaz de generos
	 * @param bookInterface Interfaz de libros
	 * @param tabbedPane lo que almacena todos los panes
	 */
	public WAdminLibro(IAuthorController authorInterface, IGenreController genreInterface,
			IBookController bookInterface, JTabbedPane tabbedPane) {
		this.authorInterface = authorInterface;
		this.bookInterface = bookInterface;
		this.genreInterface = genreInterface;
		this.tabbedPane = tabbedPane;

		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1047, 680);
		setLayout(null);

		JLabel lblIsbn = new JLabel("Introduce isbn:");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setForeground(Color.WHITE);
		lblIsbn.setBounds(36, 46, 101, 14);
		this.add(lblIsbn);

		textIsbn = new JTextField();
		textIsbn.setBounds(36, 71, 137, 20);
		this.add(textIsbn);
		textIsbn.setColumns(10);
		textIsbn.addFocusListener(this);

		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(36, 127, 137, 20);
		this.add(textTitulo);
		textTitulo.addFocusListener(this);

		JLabel lblTitulo = new JLabel("Introduce el titulo:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(36, 102, 137, 14);
		this.add(lblTitulo);

		textEditorial = new JTextField();
		textEditorial.setColumns(10);
		textEditorial.setBounds(36, 183, 137, 20);
		this.add(textEditorial);
		textEditorial.addFocusListener(this);

		JLabel lblEditorial = new JLabel("Introduce la editorial:");
		lblEditorial.setForeground(Color.WHITE);
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(36, 158, 137, 14);
		this.add(lblEditorial);

		textStock = new JTextField();
		textStock.setColumns(10);
		textStock.setBounds(36, 239, 137, 20);
		this.add(textStock);
		textStock.addFocusListener(this);

		JLabel lblStock = new JLabel("Introduce el stock:");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(36, 214, 137, 14);
		this.add(lblStock);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(36, 295, 137, 20);
		this.add(textPrecio);
		textPrecio.addFocusListener(this);

		JLabel lblPrecio = new JLabel("Introduce el precio:");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(36, 270, 137, 14);
		this.add(lblPrecio);

		JLabel lblDescuento = new JLabel("Selecciona el id de descuento:");
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescuento.setBounds(36, 326, 197, 14);
		this.add(lblDescuento);

		comboBoxDescuento = new JComboBox<Integer>();
		comboBoxDescuento.setBounds(36, 351, 137, 22);
		this.add(comboBoxDescuento);
		// Esto es para añadir todos los id de descuentos al combo box
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
		this.add(lblFecha);

		calendarioLibro = new JCalendar();
		calendarioLibro.setBounds(36, 409, 197, 177);
		this.add(calendarioLibro);

		JLabel lblDescripcion = new JLabel("Descripcion del libro:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(293, 386, 221, 14);
		this.add(lblDescripcion);

		textDescripcion = new JTextArea();
		textDescripcion.setBounds(293, 409, 361, 177);
		this.add(textDescripcion);
		textDescripcion.addFocusListener(this);

		JLabel lblAutorLibro = new JLabel("Introduce el codigo de los autores:");
		lblAutorLibro.setVerticalAlignment(SwingConstants.TOP);
		lblAutorLibro.setForeground(Color.WHITE);
		lblAutorLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutorLibro.setBounds(293, 48, 225, 20);
		this.add(lblAutorLibro);

		textAutorLibro1 = new JTextField();
		textAutorLibro1.setColumns(10);
		textAutorLibro1.setBounds(293, 71, 137, 20);
		this.add(textAutorLibro1);
		textAutorLibro1.addFocusListener(this);

		textAutorLibro2 = new JTextField();
		textAutorLibro2.setColumns(10);
		textAutorLibro2.setBounds(293, 101, 137, 20);
		add(textAutorLibro2);
		textAutorLibro2.addFocusListener(this);

		textAutorLibro3 = new JTextField();
		textAutorLibro3.setColumns(10);
		textAutorLibro3.setBounds(293, 132, 137, 20);
		add(textAutorLibro3);
		textAutorLibro3.addFocusListener(this);

		JLabel lblGeneroLibro = new JLabel("Introduce el nombre de los generos:");
		lblGeneroLibro.setVerticalAlignment(SwingConstants.TOP);
		lblGeneroLibro.setForeground(Color.WHITE);
		lblGeneroLibro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneroLibro.setBounds(293, 214, 235, 22);
		this.add(lblGeneroLibro);

		textGeneroLibro1 = new JTextField();
		textGeneroLibro1.setColumns(10);
		textGeneroLibro1.setBounds(293, 239, 137, 20);
		this.add(textGeneroLibro1);
		textGeneroLibro1.addFocusListener(this);

		textGeneroLibro2 = new JTextField();
		textGeneroLibro2.setColumns(10);
		textGeneroLibro2.setBounds(293, 269, 137, 20);
		add(textGeneroLibro2);
		textGeneroLibro2.addFocusListener(this);

		textGeneroLibro3 = new JTextField();
		textGeneroLibro3.setColumns(10);
		textGeneroLibro3.setBounds(293, 300, 137, 20);
		add(textGeneroLibro3);
		textGeneroLibro3.addFocusListener(this);

		btnModificarLibro = new JButton("Guardar cambios");
		btnModificarLibro.setEnabled(false);
		btnModificarLibro.setForeground(Color.WHITE);
		btnModificarLibro.setFocusPainted(false);
		btnModificarLibro.setBorder(null);
		btnModificarLibro.setBackground(Color.GRAY);
		btnModificarLibro.setBounds(774, 429, 159, 23);
		this.add(btnModificarLibro);
		btnModificarLibro.addActionListener(this);

		btnRegistrarLibro = new JButton("Registrar libro");
		btnRegistrarLibro.setForeground(Color.WHITE);
		btnRegistrarLibro.setFocusPainted(false);
		btnRegistrarLibro.setBorder(null);
		btnRegistrarLibro.setBackground(Color.GRAY);
		btnRegistrarLibro.setBounds(774, 497, 159, 23);
		this.add(btnRegistrarLibro);
		btnRegistrarLibro.addActionListener(this);

		btnEliminarLibro = new JButton("Eliminar libro");
		btnEliminarLibro.setForeground(Color.WHITE);
		btnEliminarLibro.setFocusPainted(false);
		btnEliminarLibro.setEnabled(false);
		btnEliminarLibro.setBorder(null);
		btnEliminarLibro.setBackground(Color.GRAY);
		btnEliminarLibro.setBounds(774, 463, 159, 23);
		this.add(btnEliminarLibro);
		btnEliminarLibro.addActionListener(this);

		btnCancelarLibro = new JButton("Cancelar");
		btnCancelarLibro.setForeground(Color.WHITE);
		btnCancelarLibro.setFocusPainted(false);
		btnCancelarLibro.setBorder(null);
		btnCancelarLibro.setBackground(Color.GRAY);
		btnCancelarLibro.setBounds(774, 531, 159, 23);
		this.add(btnCancelarLibro);

		JLabel lblIconoLibro = new JLabel("New label");
		lblIconoLibro.setIcon(new ImageIcon(WAdmin.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		lblIconoLibro.setBounds(528, 11, 465, 294);
		this.add(lblIconoLibro);
		btnCancelarLibro.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnRegistrarLibro)) {
			if (textEditorial.getText().isEmpty() || textIsbn.getText().isEmpty() || textTitulo.getText().isEmpty()
					|| textStock.getText().isEmpty() || textPrecio.getText().isEmpty()
					|| (textAutorLibro1.getText().isEmpty() && textAutorLibro2.getText().isEmpty()
							&& textAutorLibro3.getText().isEmpty())
					|| (textGeneroLibro1.getText().isEmpty() && textGeneroLibro2.getText().isEmpty()
							&& textGeneroLibro3.getText().isEmpty())
					|| comboBoxDescuento.getSelectedIndex() == -1 || textDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (calendarioLibro.getCalendar().after(Calendar.getInstance())) {
					JOptionPane.showMessageDialog(this, "La fecha no puede ser superior a la actual", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						authorList = new ArrayList<String>();
						genreList = new ArrayList<String>();
						book = new Book();
						book.setIsbn(Integer.valueOf(textIsbn.getText()));
						book.setTitle(textTitulo.getText());
						book.setDescription(textDescripcion.getText());
						book.setEditorial(textEditorial.getText());
						book.setStock(Integer.valueOf(textStock.getText()));
						book.setPrice(Float.valueOf(textPrecio.getText()));
						book.setIdDiscount(Integer.valueOf(comboBoxDescuento.getSelectedItem().toString()));
						Date fecha = new Date(calendarioLibro.getDate().getTime());
						book.setPubDate(fecha);
						bookInterface.altaBook(book);
						if (!textAutorLibro1.getText().isEmpty())
							authorList.add(textAutorLibro1.getText());
						if (!textAutorLibro2.getText().isEmpty())
							authorList.add(textAutorLibro2.getText());
						if (!textAutorLibro3.getText().isEmpty())
							authorList.add(textAutorLibro3.getText());
						bookInterface.anadirAuthor(authorList, book.getIsbn());
						if (!textGeneroLibro1.getText().isEmpty())
							genreList.add(textGeneroLibro1.getText());
						if (!textGeneroLibro2.getText().isEmpty())
							genreList.add(textGeneroLibro2.getText());
						if (!textGeneroLibro3.getText().isEmpty())
							genreList.add(textGeneroLibro3.getText());
						bookInterface.anadirGenre(genreList, book.getIsbn());

						JOptionPane.showMessageDialog(this, "Libro registrado con exito", "Ta bien",
								JOptionPane.INFORMATION_MESSAGE);

						textIsbn.setText("");
						textTitulo.setText("");
						textDescripcion.setText("");
						textEditorial.setText("");
						textStock.setText("");
						textPrecio.setText("");
						comboBoxDescuento.setSelectedIndex(-1);
						calendarioLibro.setCalendar(Calendar.getInstance());
						textAutorLibro1.setText("");
						textAutorLibro2.setText("");
						textAutorLibro3.setText("");
						textGeneroLibro1.setText("");
						textGeneroLibro2.setText("");
						textGeneroLibro3.setText("");
					} catch (GestorException e1) {
						JOptionPane.showMessageDialog(this, "Error al registrar el libro", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		}
		if (e.getSource().equals(btnModificarLibro)) {
			if (textEditorial.getText().isEmpty() || textIsbn.getText().isEmpty() || textTitulo.getText().isEmpty()
					|| textStock.getText().isEmpty() || textPrecio.getText().isEmpty()
					|| (textAutorLibro1.getText().isEmpty() && textAutorLibro2.getText().isEmpty()
							&& textAutorLibro3.getText().isEmpty())
					|| (textGeneroLibro1.getText().isEmpty() && textGeneroLibro2.getText().isEmpty()
							&& textGeneroLibro3.getText().isEmpty())
					|| comboBoxDescuento.getSelectedIndex() == -1 || textDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (calendarioLibro.getCalendar().after(Calendar.getInstance())) {
					JOptionPane.showMessageDialog(this, "La fecha no puede ser superior a la actual", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int seleccion = JOptionPane.showConfirmDialog(libros, "Quieres modificar este libro?", "Aviso", 0);
					if (seleccion == 0) {
						try {
							authorList = new ArrayList<String>();
							genreList = new ArrayList<String>();
							book = new Book();
							book.setIsbn(Integer.valueOf(textIsbn.getText()));
							book.setTitle(textTitulo.getText());
							book.setDescription(textDescripcion.getText());
							book.setEditorial(textEditorial.getText());
							book.setStock(Integer.valueOf(textStock.getText()));
							book.setPrice(Float.valueOf(textPrecio.getText()));
							book.setIdDiscount(Integer.valueOf(comboBoxDescuento.getSelectedItem().toString()));
							if (!textAutorLibro1.getText().isEmpty())
								authorList.add(textAutorLibro1.getText());
							if (!textAutorLibro2.getText().isEmpty())
								authorList.add(textAutorLibro2.getText());
							if (!textAutorLibro3.getText().isEmpty())
								authorList.add(textAutorLibro3.getText());
							if (!textGeneroLibro1.getText().isEmpty())
								genreList.add(textGeneroLibro1.getText());
							if (!textGeneroLibro2.getText().isEmpty())
								genreList.add(textGeneroLibro2.getText());
							if (!textGeneroLibro3.getText().isEmpty())
								genreList.add(textGeneroLibro3.getText());
							Date fecha = new Date(calendarioLibro.getDate().getTime());
							book.setPubDate(fecha);
							bookInterface.modificarBook(book, authorList, genreList);

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
							calendarioLibro.setCalendar(Calendar.getInstance());
							textAutorLibro1.setText("");
							textAutorLibro2.setText("");
							textAutorLibro3.setText("");
							textGeneroLibro1.setText("");
							textGeneroLibro2.setText("");
							textGeneroLibro3.setText("");
							btnModificarLibro.setEnabled(false);
							btnEliminarLibro.setEnabled(false);
							btnRegistrarLibro.setEnabled(true);
						} catch (GestorException e1) {
							JOptionPane.showMessageDialog(this, "Error al modificar el libro", "Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
				}
			}
		}
		if (e.getSource().equals(btnEliminarLibro)) {
			int seleccion = JOptionPane.showConfirmDialog(libros, "Quieres eliminar este libro con el isbn: "+textIsbn.getText()+" ?", "Aviso", 0);
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
					calendarioLibro.setCalendar(Calendar.getInstance());
					textAutorLibro1.setText("");
					textAutorLibro2.setText("");
					textAutorLibro3.setText("");
					textGeneroLibro1.setText("");
					textGeneroLibro2.setText("");
					textGeneroLibro3.setText("");
					btnModificarLibro.setEnabled(false);
					btnEliminarLibro.setEnabled(false);
					btnRegistrarLibro.setEnabled(true);
				} catch (NumberFormatException | GestorException e1) {
					JOptionPane.showMessageDialog(this, "Error al eliminar el libro", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(btnCancelarLibro)) {
			// Cancela el modo modificacion y borra todos los textos
			textIsbn.setText("");
			textTitulo.setText("");
			textDescripcion.setText("");
			textEditorial.setText("");
			textStock.setText("");
			textPrecio.setText("");
			comboBoxDescuento.setSelectedIndex(-1);
			calendarioLibro.setCalendar(Calendar.getInstance());
			textAutorLibro1.setText("");
			textAutorLibro2.setText("");
			textAutorLibro3.setText("");
			textGeneroLibro1.setText("");
			textGeneroLibro2.setText("");
			textGeneroLibro3.setText("");
			if (btnModificarLibro.isEnabled()) {
				btnModificarLibro.setEnabled(false);
				btnEliminarLibro.setEnabled(false);
				btnRegistrarLibro.setEnabled(true);
				textIsbn.setEnabled(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textIsbn)) {
			boolean b = false;
			if (insertString(50, textIsbn.getText())) {
				textIsbn.setText("");
				textIsbn.grabFocus();
				b = true;
			}
			try {
				if (!textIsbn.getText().isEmpty()) {
					if (Integer.valueOf(textIsbn.getText()) < 0) {
						JOptionPane.showMessageDialog(this, "No se puede introducir un numero negativo", "Error",
								JOptionPane.WARNING_MESSAGE);
						textIsbn.setText("");
						textIsbn.grabFocus();
					}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
				textIsbn.setText("");
				textIsbn.grabFocus();
			}
			if (!b && textIsbn.getText().length() > 0) {
				try {
					book = bookInterface.buscarBook(Integer.valueOf(textIsbn.getText()));
					if (book != null) {
						int seleccion = JOptionPane.showConfirmDialog(this,
								"Ese isbn ya a sido introducido quieres modificarlo?", "Aviso", 0);
						if (seleccion == 0) {
							authorList = bookInterface.listAuthors(book.getIsbn());
							genreList = bookInterface.listGenres(book.getIsbn());
							btnModificarLibro.setEnabled(true);
							btnEliminarLibro.setEnabled(true);
							btnRegistrarLibro.setEnabled(false);

							textIsbn.setText(String.valueOf(book.getIsbn()));
							textIsbn.setEnabled(false);
							textDescripcion.setText(book.getDescription());
							textEditorial.setText(book.getEditorial());
							textPrecio.setText(String.valueOf(book.getPrice()));
							textStock.setText(String.valueOf(book.getStock()));
							textTitulo.setText(book.getTitle());
							comboBoxDescuento.setSelectedItem(book.getIdDiscount());
							calendarioLibro.setDate(book.getPubDate());
							if (authorList.size() == 1) {
								textAutorLibro1.setText(authorList.get(0));
							} else if (authorList.size() == 2) {
								textAutorLibro1.setText(authorList.get(0));
								textAutorLibro2.setText(authorList.get(1));
							} else {
								textAutorLibro1.setText(authorList.get(0));
								textAutorLibro2.setText(authorList.get(1));
								textAutorLibro3.setText(authorList.get(2));
							}
							if (genreList.size() == 1) {
								textGeneroLibro1.setText(genreList.get(0));
							} else if (genreList.size() == 2) {
								textGeneroLibro1.setText(genreList.get(0));
								textGeneroLibro2.setText(genreList.get(1));
							} else {
								textGeneroLibro1.setText(genreList.get(0));
								textGeneroLibro2.setText(genreList.get(1));
								textGeneroLibro3.setText(genreList.get(2));
							}
						} else {
							textIsbn.setText("");
							textIsbn.grabFocus();
						}
					}
				} catch (NumberFormatException | GestorException e1) {
					JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
							JOptionPane.WARNING_MESSAGE);
					textIsbn.setText("");
					textIsbn.grabFocus();
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(textStock)) {
			try {
				if (!textStock.getText().isEmpty()) {
					if (Integer.valueOf(textStock.getText()) < 0) {
						JOptionPane.showMessageDialog(this, "No se puede introducir un numero negativo", "Error",
								JOptionPane.WARNING_MESSAGE);
						textStock.setText("");
						textStock.grabFocus();
					}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros enteros", "Error",
						JOptionPane.WARNING_MESSAGE);
				textStock.setText("");
				textStock.grabFocus();
			}
			if (insertString(50, textIsbn.getText())) {
				textIsbn.setText("");
				textStock.grabFocus();
			}
		}
		if (e.getSource().equals(textPrecio)) {
			try {
				if (!textPrecio.getText().isEmpty()) {
					if (Float.valueOf(textPrecio.getText()) < 0) {
						JOptionPane.showMessageDialog(this, "No se puede introducir un numero negativo", "Error",
								JOptionPane.WARNING_MESSAGE);
						textPrecio.setText("");
						textPrecio.grabFocus();
					}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
				textPrecio.setText("");
				textPrecio.grabFocus();
			}
			if (insertString(50, textPrecio.getText())) {
				textPrecio.setText("");
				textPrecio.grabFocus();
			}
		}
		if (e.getSource().equals(textAutorLibro1)) {
			if (insertString(50, textAutorLibro1.getText())) {
				textAutorLibro1.setText("");
				textAutorLibro1.grabFocus();
			}
			textAutorLibro1.setText(encontrarAutor(textAutorLibro1.getText()));
		}
		if (e.getSource().equals(textAutorLibro2)) {
			if (insertString(50, textAutorLibro2.getText())) {
				textAutorLibro2.setText("");
				textAutorLibro2.grabFocus();
			}
			textAutorLibro2.setText(encontrarAutor(textAutorLibro2.getText()));
		}
		if (e.getSource().equals(textAutorLibro3)) {
			if (insertString(50, textAutorLibro3.getText())) {
				textAutorLibro3.setText("");
				textAutorLibro3.grabFocus();
			}
			textAutorLibro3.setText(encontrarAutor(textAutorLibro3.getText()));
		}
		if (e.getSource().equals(textGeneroLibro1)) {
			if (insertString(50, textGeneroLibro1.getText())) {
				textGeneroLibro1.setText("");
				textGeneroLibro1.grabFocus();
			}
			textGeneroLibro1.setText(encontrarGenero(textGeneroLibro1.getText()));
		}
		if (e.getSource().equals(textGeneroLibro2)) {
			if (insertString(50, textGeneroLibro2.getText())) {
				textGeneroLibro2.setText("");
				textGeneroLibro2.grabFocus();
			}
			textGeneroLibro2.setText(encontrarGenero(textGeneroLibro2.getText()));
		}
		if (e.getSource().equals(textGeneroLibro3)) {
			if (insertString(50, textGeneroLibro3.getText())) {
				textGeneroLibro3.setText("");
				textGeneroLibro3.grabFocus();
			}
			textGeneroLibro3.setText(encontrarGenero(textGeneroLibro3.getText()));
		}
		if (e.getSource().equals(textTitulo)) {
			if (insertString(50, textTitulo.getText())) {
				textTitulo.setText("");
				textTitulo.grabFocus();
			}
		}
		if (e.getSource().equals(textEditorial)) {
			if (insertString(50, textEditorial.getText())) {
				textEditorial.setText("");
				textEditorial.grabFocus();
			}
		}
		if (e.getSource().equals(textDescripcion)) {
			if (insertString(1000, textDescripcion.getText())) {
				textDescripcion.setText("");
				textDescripcion.grabFocus();
			}
		}

	}

	
	/**
	 * Metodo para comprobar que el texto introducido no es mayor de lo permitido
	 * @param maximo el numero maximo de caracteres permitido
	 * @param texto el texto que quieres comprobar
	 * @return un boolean, en caso de que tenga mas caracteres de lo permitido sera <b>true</b>, en caso contrario <b>false</b>
	 */
	public boolean insertString(int maximo, String texto) {
		if (texto.length() > maximo) {
			JOptionPane.showMessageDialog(this, "Excedido el limite de caracteres (" + maximo + ")", "Error",
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}

	/**
	 * Metodo para cuando no existe un autor introducido se cambie de panel para introducirlo
	 * @param codAutor el codigo del autor
	 * @return un texto vacio si el autor no existe o el codigo del autor si existe
	 */
	public String encontrarAutor(String codAutor) {
		try {
			Author author = authorInterface.buscarAuthor(codAutor);
			if (author == null && !codAutor.isEmpty()) {
				int seleccion = JOptionPane.showConfirmDialog(libros, "Ese autor no existe quieres añadirlo?", "Aviso", 0);
				if (seleccion == 0) {
					tabbedPane.setSelectedIndex(1);
				}
				codAutor = "";
			}
		} catch (GestorException e1) {
			e1.printStackTrace();
		}
		return codAutor;
	}

	/**
	 * Metodo para cuando no existe un genero introducido se cambie de panel para introducirlo
	 * @param genero el nombre del genero
	 * @return un texto vacio si el genero no existe o el nombre si existe
	 */
	public String encontrarGenero(String genero) {
		try {
			Genre genre = genreInterface.buscarGenre(genero);
			if (genre == null && !genero.isEmpty()) {
				int seleccion = JOptionPane.showConfirmDialog(libros, "Ese genero no existe quieres añadirlo?", "Aviso", 0);
				if (seleccion == 0) {
					tabbedPane.setSelectedIndex(1);
				}
				genero = "";
			}
		} catch (GestorException e1) {
			e1.printStackTrace();
		}
		return genero;
	}

}
