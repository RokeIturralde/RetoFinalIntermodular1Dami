package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import tartanga.dami.equipoa.dataAccess.IAuthorBookController;
import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Genre;
import tartanga.dami.equipoa.model.User;
import tartanga.dami.equipoa.gui.WLogIn;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 * @author Sendoa
 * Ventana en la que se almacenaran a modo de pesta�as todos los paneles para la gestion administrativa de la libreria
 */
public class WAdmin extends JDialog implements MouseListener {

	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IUserController userInterface;
	private IComprasController comprasInterface;
	private IAuthorBookController authorBookInterface;
	private IConsultaController consultaInterface;
	private JTabbedPane tabbedPane;
	private JPanel libros;
	private JPanel autores;
	private JPanel generos;
	private JLabel lblNombreAdmin;
	private JLabel lblCerrarSesion;
	private JLabel lblIconoCerrar;

	/**
	 * @param user el usuario que ha iniciado sesion
	 * @param bookInterface Interfaz de libro
	 * @param authorInterface Interfaz de autor
	 * @param genreInterface Intefaz de genero
	 * @param userInterface Interfaz de usuario
	 * @param authorBookInterface Interfaz auxiliar
	 * @param comprasInterface Interfaz de compras
	 */
	public WAdmin(User user, IBookController bookInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IUserController userInterface, IAuthorBookController authorBookInterface,
			IComprasController comprasInterface, IConsultaController consultaInterface) {
		this.authorInterface = authorInterface;
		this.bookInterface = bookInterface;
		this.genreInterface = genreInterface;
		this.userInterface = userInterface;
		this.comprasInterface = comprasInterface;
		this.authorBookInterface = authorBookInterface;
		this.consultaInterface = consultaInterface;
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

		lblNombreAdmin = new JLabel("Admin: " + user.getUserName());
		lblNombreAdmin.setForeground(Color.WHITE);
		lblNombreAdmin.setBounds(650, 0, 99, 23);
		getContentPane().add(lblNombreAdmin);

		lblCerrarSesion = new JLabel("Cerrar sesion");
		lblCerrarSesion.setForeground(Color.WHITE);
		lblCerrarSesion.setBounds(850, 0, 99, 23);
		getContentPane().add(lblCerrarSesion);
		lblCerrarSesion.addMouseListener(this);

		lblIconoCerrar = new JLabel();
		lblIconoCerrar.setIcon(
				new ImageIcon(WAdmin.class.getResource("/tartanga/dami/equipoa/resources/iconoSalir30x19.png")));
		lblIconoCerrar.setBounds(930, 0, 99, 23);
		this.add(lblIconoCerrar);
		lblIconoCerrar.addMouseListener(this);

		iniciarComponentes(userInterface, authorInterface, genreInterface, bookInterface, tabbedPane);

	}

	/**
	 * Metodo para iniciar los componentes de la ventana
	 * @param userInterface Interfaz de usuario
	 * @param authorInterface Interfaz de autor
	 * @param genreInterface Interfaz de genero
	 * @param bookInterface Interfaz de libro
	 * @param tabbedPane donde se guardan los paneles
	 */
	private void iniciarComponentes(IUserController userInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IBookController bookInterface, JTabbedPane tabbedPane) {
		libros = new WAdminLibro(authorInterface, genreInterface, bookInterface, tabbedPane);
		autores = new WAdminAutor(authorInterface);
		generos = new WAdminGenre(genreInterface);

		tabbedPane.add("Libros", libros);
		tabbedPane.add("Autores", autores);
		tabbedPane.add("Generos", generos);

		getContentPane().add(tabbedPane);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(lblCerrarSesion)) {
			cerrarSesion();
		}
		if (e.getSource().equals(lblIconoCerrar)) {
			cerrarSesion();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * Metodo para cerrar sesion en el programa
	 */
	public void cerrarSesion() {
		int seleccion = JOptionPane.showConfirmDialog(libros, "Estas seguro que quieres cerrar sesion?", "Aviso", 0);
		if (seleccion == 0) {
			this.dispose();
			WLogIn login = new WLogIn(userInterface, authorInterface, genreInterface, bookInterface,
					authorBookInterface, comprasInterface, consultaInterface);
			login.setVisible(true);
		}
	}
}
