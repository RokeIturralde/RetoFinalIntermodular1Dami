package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class WAdmin extends JDialog {
	
	private IBookController bookInterface;
	private IAuthorController authorController;
	private IGenreController genereController;
	private JTextField textIsbn;
	private JTextField textTitulo;
	private JTextField textEditorial;
	private JTextField textStock;
	private JTextField textPrecio;

	public WAdmin(User user, IBookController bookInterface, IAuthorController authorInterface, IGenreController genreInterface) {
		this.authorController = authorInterface;
		this.bookInterface = bookInterface;
		this.genereController = genreInterface;
		
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1047, 680);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(0, 0, 1031, 641);
		getContentPane().add(tabbedPane);
		
		JPanel libros = new JPanel();
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
		
		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(36, 127, 137, 20);
		libros.add(textTitulo);
		
		JLabel lblTitulo = new JLabel("Introduce el titulo:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(36, 102, 137, 14);
		libros.add(lblTitulo);
		
		textEditorial = new JTextField();
		textEditorial.setColumns(10);
		textEditorial.setBounds(36, 183, 137, 20);
		libros.add(textEditorial);
		
		JLabel lblEditorial = new JLabel("Introduce la editorial:");
		lblEditorial.setForeground(Color.WHITE);
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(36, 158, 137, 14);
		libros.add(lblEditorial);
		
		textStock = new JTextField();
		textStock.setColumns(10);
		textStock.setBounds(36, 239, 137, 20);
		libros.add(textStock);
		
		JLabel lblStock = new JLabel("Introduce el stock:");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(36, 214, 137, 14);
		libros.add(lblStock);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(36, 295, 137, 20);
		libros.add(textPrecio);
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(36, 351, 137, 22);
		libros.add(comboBox);
		
		JLabel lblFecha = new JLabel("Selecciona la fecha de publicacion:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(36, 384, 221, 14);
		libros.add(lblFecha);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		JPanel autores = new JPanel();
		autores.setBorder(null);
		autores.setBackground(Color.DARK_GRAY);
		autores.setForeground(Color.WHITE);
		tabbedPane.addTab("Autores", null, autores, null);
		autores.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		JPanel generos = new JPanel();
		generos.setForeground(Color.WHITE);
		generos.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Generos", null, generos, null);
		generos.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		JPanel perfil = new JPanel();
		perfil.setForeground(Color.WHITE);
		perfil.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Perfil", null, perfil, null);
		perfil.setLayout(null);
		tabbedPane.setBackgroundAt(0, Color.DARK_GRAY);


	}
}
