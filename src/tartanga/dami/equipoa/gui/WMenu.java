package tartanga.dami.equipoa.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.AuthorBook;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * 
 * @author Eneko
 * Ventana en la que se inician los paneles Inicio, consultas y perfil. Gestiona tambien la lista 
 * de compras y el boton de carrito
 * 
 *
 */

public class WMenu extends JDialog implements MouseListener {
	private ArrayList<AuthorBook> listLikedBooks;
	private IUserController userInterface;
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IConsultaController consultaInterface;
	private IComprasController comprasInterface;
	private User user;
	private WMenuInicio panelInicio;
	private WMenuPerfil panelPerfil;
	private WMenuConsultas panelConsultas;
	private JTabbedPane tabbedPane;
	private JLabel lblCarrito;
	private JLabel lblCerrar;
	private ArrayList<Compra> compras;
	/**
	 * @param user el usuario que ha iniciado sesion
	 * @param bookInterface Interfaz de libro
	 * @param authorInterface Interfaz de autor
	 * @param genreInterface Intefaz de genero
	 * @param userInterface Interfaz de usuario
	 * @param comprasInterface Interfaz de compras
	 * @param compras 
	 * @param compras lista que almacena todas las compras pendientes que tiene un usuario
	 */

	public WMenu(IUserController userInterface, IAuthorController authorInterface, IGenreController genreInterface,
			IBookController bookInterface, IComprasController comprasInterface, User user,
			IConsultaController consultaInterface, ArrayList<Compra> compras) {
		setBounds(100, 100, 1050, 720);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);

		this.userInterface = userInterface;
		this.bookInterface = bookInterface;
		this.authorInterface = authorInterface;
		this.genreInterface = genreInterface;
		this.comprasInterface = comprasInterface;
		this.consultaInterface = consultaInterface;
		this.compras=compras;
		// userInterface, authorInterface, genreInterface, bookInterface,
		// authorBookInterface, comprasInterface
		this.user = user;
		// userInterface, authorInterface, genreInterface, bookInterface,
		// authorBookInterface, comprasInterface
		compras = new ArrayList<Compra>();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 27, 1005, 683);
		getContentPane().add(tabbedPane);

		
		
		JLabel usuario = new JLabel("");
		usuario.setVerticalAlignment(SwingConstants.TOP);
		usuario.setForeground(new Color(255, 255, 255));
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usuario.setBounds(630, 20, 230, 23);
		getContentPane().add(usuario);
		usuario.setText("Usuario: " + user.getUserName());

		lblCarrito = new JLabel("");
		lblCarrito.setIcon(new ImageIcon(WMenu.class.getResource("/tartanga/dami/equipoa/resources/carritoIcono.png")));
		lblCarrito.setBounds(890, 10, 50, 30);
		lblCarrito.addMouseListener(this);
		getContentPane().add(lblCarrito);

		lblCerrar = new JLabel("");
		lblCerrar.setIcon(new ImageIcon(WMenu.class.getResource("/tartanga/dami/equipoa/resources/iconoSalir.png")));
		lblCerrar.setBounds(948, 10, 53, 33);
		getContentPane().add(lblCerrar);
		lblCerrar.addMouseListener(this);

		iniciarComponentes(userInterface, authorInterface, bookInterface, user, comprasInterface,
				genreInterface);

	}

	/**
	 * Inicia los componentes de la ventana
	 */
	private void iniciarComponentes(IUserController userInterface, IAuthorController authorInterface,
			IBookController bookInterface, User user, IComprasController comprasInterface, IGenreController genreInterface) {

		panelInicio = new WMenuInicio(userInterface, bookInterface, authorInterface, user,
				compras);
		panelPerfil = new WMenuPerfil(userInterface, authorInterface, bookInterface, genreInterface, comprasInterface,
				user, panelInicio);
		panelConsultas = new WMenuConsultas(genreInterface, bookInterface, consultaInterface, authorInterface, compras);

		tabbedPane.add("Inicio", panelInicio);
		tabbedPane.add("Perfil", panelPerfil);
		tabbedPane.add("Consulta", panelConsultas);

		getContentPane().add(tabbedPane);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * Cierra la ventana y regresa al login
		 */
		if (e.getSource().equals(lblCerrar)) {
			WLogIn login = new WLogIn(userInterface, authorInterface, genreInterface, bookInterface,
				 comprasInterface, consultaInterface);
			login.setVisible(true);
			this.dispose();
		}
		/**
		 * Abre la ventana carrito
		 */
		if (e.getSource().equals(lblCarrito)) {
			WCarrito carrito = new WCarrito(bookInterface, authorInterface, comprasInterface, compras, genreInterface,
					user, consultaInterface, userInterface);
			carrito.setVisible(true);
			this.dispose();
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
}
