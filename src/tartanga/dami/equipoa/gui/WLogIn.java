package tartanga.dami.equipoa.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;



import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Administrator;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.User;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

/**
 * @author Sendoa
 *
 */
public class WLogIn extends JFrame implements ActionListener, KeyListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnRegistrar;
	private JButton btnIniciarSesion;
	private IUserController userInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IBookController bookInterface;
	private IComprasController comprasInterface;
	private IConsultaController consultaInterface;
	private ArrayList<Compra> compras;
	private JLabel lblIrakurle;

	/**
	 * @param userInterface       interfaz de usuarios
	 * @param authorInterface     interfaz de autores
	 * @param genreInterface      interfaz de generos
	 * @param bookInterface       interfaz de libros
	 * @param comprasInterface    interfaz de compras
	 * @param consultaInterface   interfaz de consultas
	 */
	public WLogIn(IUserController userInterface, IAuthorController authorInterface, IGenreController genreInterface,
			IBookController bookInterface, IComprasController comprasInterface, IConsultaController consultaInterface) {
		this.userInterface = userInterface;
		this.authorInterface = authorInterface;
		this.genreInterface = genreInterface;
		this.bookInterface = bookInterface;
		this.comprasInterface = comprasInterface;
		this.consultaInterface = consultaInterface;
		this.compras =compras;
		this.lblIrakurle = lblIrakurle;
		compras = new ArrayList();

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(WLogIn.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		getContentPane().setForeground(UIManager.getColor("textInactiveText"));
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 602, 465);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblIrakurle = new JLabel();
		lblIrakurle.setText("Irakurle");
		lblIrakurle.setBounds(215,0,200,100);
		lblIrakurle.setFont(new Font("Osaka", Font.PLAIN, 45));
		lblIrakurle.setForeground(Color.WHITE);
		this.add(lblIrakurle);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(173, 164, 239, 29);
		getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		textUsuario.addKeyListener(this);
		textUsuario.addFocusListener(this);

		JLabel lblNombreEmail = new JLabel("Nombre de usuario o email de la cuenta:");
		lblNombreEmail.setForeground(Color.WHITE);
		lblNombreEmail.setBackground(Color.WHITE);
		lblNombreEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreEmail.setBounds(173, 139, 256, 14);
		getContentPane().add(lblNombreEmail);

		JLabel lblIniciarSesion = new JLabel("Iniciar Sesi\u00F3n");
		lblIniciarSesion.setBounds(210, 90, 159, 36);
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIniciarSesion.setForeground(Color.WHITE);
		
		getContentPane().add(lblIniciarSesion);

		JLabel lblContr = new JLabel("Contrase\u00F1a:");
		lblContr.setForeground(Color.WHITE);
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContr.setBackground(Color.WHITE);
		lblContr.setBounds(173, 214, 256, 14);
		getContentPane().add(lblContr);

		btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setBorder(null);
		btnIniciarSesion.setBackground(new Color(128, 128, 128));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBounds(210, 314, 159, 23);
		getContentPane().add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.addKeyListener(this);

		passwordField = new JPasswordField();
		passwordField.setBounds(173, 239, 239, 29);
		getContentPane().add(passwordField);
		passwordField.addKeyListener(this);
		passwordField.addFocusListener(this);

		JLabel lblNoCuenta = new JLabel("No tienes cuenta?");
		lblNoCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoCuenta.setForeground(Color.WHITE);
		lblNoCuenta.setBounds(366, 389, 100, 14);
		getContentPane().add(lblNoCuenta);

		btnRegistrar = new JButton("Registrate");
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegistrar.setForeground(Color.CYAN);
		btnRegistrar.setBackground(Color.DARK_GRAY);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBounds(467, 385, 60, 23);
		getContentPane().add(btnRegistrar);
		btnRegistrar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Abrimos la ventana registro
		if (e.getSource().equals(btnRegistrar)) {
			WRegistro registro = new WRegistro(userInterface, authorInterface, genreInterface);
			registro.setVisible(true);
		}
		if (e.getSource().equals(btnIniciarSesion)) {
			iniciarSesion();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			iniciarSesion();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo para inicar sesion en el programa
	 */
	public void iniciarSesion() {
		// Pasamos la contrase??a a string y comprobamos que el username y la password
		// no
		// esten vacios
		String pass = new String(passwordField.getPassword());
		if (!(textUsuario.getText().isEmpty() || pass.isEmpty())) {
			try {
				// Comprobamos que las credenciales estan correctas
				User user = userInterface.userLogIn(textUsuario.getText(), pass);
				if (user != null) {
					this.dispose();
					if (user instanceof Administrator) {
						WAdmin admin = new WAdmin(user, bookInterface, authorInterface, genreInterface, userInterface,
							 comprasInterface, consultaInterface);
						admin.setVisible(true);
					} else {
						WMenu menu = new WMenu(userInterface, authorInterface, genreInterface, bookInterface,
								comprasInterface, user, consultaInterface, compras);
						menu.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(this, "El nombre de la cuenta y/o la contrase??a son incorrectos",
							"Error", JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
					passwordField.grabFocus();
				}
			} catch (GestorException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textUsuario)) {
			if (insertString(50, textUsuario.getText())) {
				textUsuario.setText("");
			}
		}
		if (e.getSource().equals(passwordField)) {
			String pass = new String(passwordField.getPassword());
			if (insertString(50, pass)) {
				passwordField.setText("");
			}
		}
	}

	/**
	 * Metodo para comprobar que el texto introducido no es mayor de lo permitido
	 * 
	 * @param maximo el numero maximo de caracteres permitido
	 * @param texto  el texto que quieres comprobar
	 * @return un boolean, en caso de que tenga mas caracteres de lo permitido sera
	 *         <b>true</b>, en caso contrario <b>false</b>
	 */
	public boolean insertString(int maximo, String texto) {
		if (texto.length() > maximo) {
			JOptionPane.showMessageDialog(this, "Excedido el limite de caracteres (" + maximo + ")", "Error",
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
}
