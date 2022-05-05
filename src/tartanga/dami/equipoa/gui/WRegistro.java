package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.dataAccess.IUserControllerDBImplementation;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Genre;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class WRegistro extends JDialog implements ActionListener, FocusListener {
	private JTextField textNombreUsr;
	private JTextField textEmail;
	private JPasswordField passwordField;
	private JPasswordField confPasswordField;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JLabel lblDireccion;
	private JTextField textDireccion;
	private JLabel lblNumTelef;
	private JTextField textNumtelef;
	private JLabel lblNumCuenta;
	private JTextField textCuenta;
	private JLabel lblAutores;
	private JTextField textAutor;
	private JLabel lblGeneros;
	private JTextField textGenero;
	private JButton btnGenero;
	private JButton btnAutores;
	private JLabel lblNewLabel;
	private JButton btnRegistrar;
	private User user;
	private ArrayList<Author> authorList = new ArrayList<Author>();
	private ArrayList<Genre> genreList = new ArrayList<Genre>();
	private IUserController userInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;

	public WRegistro(IUserController userInterface, IAuthorController authorInterface, IGenreController genreInterface) {
		this.userInterface = userInterface;
		this.authorInterface = authorInterface;
		this.genreInterface = genreInterface;

		this.setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(WRegistro.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 791, 613);
		getContentPane().setLayout(null);

		JLabel lblNombreUsr = new JLabel("Nombre de Usuario:");
		lblNombreUsr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreUsr.setForeground(Color.WHITE);
		lblNombreUsr.setBounds(155, 120, 134, 14);
		getContentPane().add(lblNombreUsr);

		textNombreUsr = new JTextField();
		textNombreUsr.setBounds(309, 118, 172, 20);
		getContentPane().add(textNombreUsr);
		textNombreUsr.setColumns(10);
		textNombreUsr.addFocusListener(this);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(309, 149, 172, 20);
		getContentPane().add(textEmail);
		textEmail.addFocusListener(this);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(155, 151, 134, 14);
		getContentPane().add(lblEmail);

		JLabel lblContr = new JLabel("Contrase\u00F1a:");
		lblContr.setForeground(Color.WHITE);
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContr.setBounds(155, 182, 134, 14);
		getContentPane().add(lblContr);

		passwordField = new JPasswordField();
		passwordField.setBounds(309, 180, 172, 20);
		getContentPane().add(passwordField);
		passwordField.addFocusListener(this);

		confPasswordField = new JPasswordField();
		confPasswordField.setBounds(309, 211, 172, 20);
		getContentPane().add(confPasswordField);
		confPasswordField.addFocusListener(this);

		JLabel lblConfirmar = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmar.setForeground(Color.WHITE);
		lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmar.setBounds(155, 213, 143, 14);
		getContentPane().add(lblConfirmar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(155, 244, 134, 14);
		getContentPane().add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(309, 242, 172, 20);
		getContentPane().add(textNombre);
		textNombre.addFocusListener(this);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(155, 275, 134, 14);
		getContentPane().add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(309, 273, 172, 20);
		getContentPane().add(textApellidos);
		textApellidos.addFocusListener(this);

		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(155, 306, 134, 14);
		getContentPane().add(lblDireccion);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(309, 304, 172, 20);
		getContentPane().add(textDireccion);
		textDireccion.addFocusListener(this);

		lblNumTelef = new JLabel("* Numero de telefono:");
		lblNumTelef.setForeground(Color.WHITE);
		lblNumTelef.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumTelef.setBounds(140, 337, 149, 14);
		getContentPane().add(lblNumTelef);

		textNumtelef = new JTextField();
		textNumtelef.setColumns(10);
		textNumtelef.setBounds(309, 335, 172, 20);
		getContentPane().add(textNumtelef);
		textNumtelef.addFocusListener(this);

		lblNumCuenta = new JLabel("Numero de cuenta:");
		lblNumCuenta.setForeground(Color.WHITE);
		lblNumCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumCuenta.setBounds(155, 368, 134, 14);
		getContentPane().add(lblNumCuenta);

		textCuenta = new JTextField();
		textCuenta.setColumns(10);
		textCuenta.setBounds(309, 366, 172, 20);
		getContentPane().add(textCuenta);
		textCuenta.addFocusListener(this);

		lblAutores = new JLabel("* Autores preferidos:");
		lblAutores.setForeground(Color.WHITE);
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutores.setBounds(140, 399, 149, 14);
		getContentPane().add(lblAutores);

		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(309, 397, 172, 20);
		getContentPane().add(textAutor);
		textAutor.addFocusListener(this);

		lblGeneros = new JLabel("* Generos preferidos:");
		lblGeneros.setForeground(Color.WHITE);
		lblGeneros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneros.setBounds(140, 430, 149, 14);
		getContentPane().add(lblGeneros);

		textGenero = new JTextField();
		textGenero.setColumns(10);
		textGenero.setBounds(309, 428, 172, 20);
		getContentPane().add(textGenero);
		textGenero.addFocusListener(this);

		btnGenero = new JButton("A\u00F1adir preferencia(3 max)");
		btnGenero.setBounds(491, 427, 185, 23);
		getContentPane().add(btnGenero);
		btnGenero.addActionListener(this);

		btnAutores = new JButton("A\u00F1adir preferencia(3 max)");
		btnAutores.setBounds(491, 396, 185, 23);
		getContentPane().add(btnAutores);
		btnAutores.addActionListener(this);

		lblNewLabel = new JLabel("CREA TU CUENTA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(314, 40, 159, 43);
		getContentPane().add(lblNewLabel);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setBackground(new Color(128, 128, 128));
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setBounds(314, 486, 159, 30);
		getContentPane().add(btnRegistrar);
		btnRegistrar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAutores)) {
			if (authorList.size() <= 2) {
				boolean esta = false;
				try {
					Author autor = authorInterface.buscarAuthor(textAutor.getText());
					if (autor != null) {
						for (int i = 0; i < authorList.size(); i++) {
							if (authorList.get(i).getCodAuthor().equalsIgnoreCase(autor.getCodAuthor())) {
								JOptionPane.showMessageDialog(this, "Ese autor ya ha sido introducido", "Error",
										JOptionPane.WARNING_MESSAGE);
								esta = true;
							}
						}
						if (!esta) {
							authorList.add(autor);
							textAutor.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(this, "No se ha encontrado el autor", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Solo se pueden introducir un maximo de 3 autores", "Error",
						JOptionPane.WARNING_MESSAGE);
				textAutor.setText("");
			}
		}
		if (e.getSource().equals(btnGenero)) {
			if (genreList.size() <= 2) {
				boolean esta = false;
				try {
					Genre genero = genreInterface.buscarGenre(textGenero.getText());
					if (genero != null) {
						for (int i = 0; i < genreList.size(); i++) {
							if (genreList.get(i).getGenreName().equalsIgnoreCase(genero.getGenreName())) {
								JOptionPane.showMessageDialog(this, "Ese genero ya ha sido introducido", "Error",
										JOptionPane.WARNING_MESSAGE);
								esta = true;
							}
						}
						if (!esta) {
							genreList.add(genero);
							textGenero.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(this, "No se ha encontrado el genero", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Solo se pueden introducir un maximo de 3 generos", "Error",
						JOptionPane.WARNING_MESSAGE);
				textGenero.setText("");
			}
		}
		if (e.getSource().equals(btnRegistrar)) {
			String pass = new String(passwordField.getPassword());
			if (!(textDireccion.getText().isEmpty() || textEmail.getText().isEmpty() || textNombre.getText().isEmpty()
					|| pass.isEmpty() || textApellidos.getText().isEmpty() || textNombreUsr.getText().isEmpty()
					|| textCuenta.getText().isEmpty())) {
				try {
					user = new Partner();
					user.setAddress(textDireccion.getText());
					user.setEmail(textEmail.getText());
					user.setName(textNombre.getText());
					user.setPassword(pass);
					if (textNumtelef.getText().length() != 0) {
						user.setPhone(Integer.valueOf(textNumtelef.getText()));
					}
					user.setSurname(textApellidos.getText());
					user.setTipo('P');
					user.setUserName(textNombreUsr.getText());
					((Partner) user).setNumAccount(Integer.valueOf(textCuenta.getText()));

					userInterface.altaUsuario(user);
					for (int i = 0; i < authorList.size(); i++) {
						userInterface.anadirAutor(user.getUserName(), authorList.get(i).getCodAuthor());
					}
					for (int i = 0; i < genreList.size(); i++) {
						userInterface.anadirGenero(user.getUserName(), genreList.get(i).getGenreName());
					}

					JOptionPane.showMessageDialog(this, "Usuario registrado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);
					
					this.dispose();
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(confPasswordField)) {
			String contr = new String(passwordField.getPassword());
			if (contr.isEmpty()) {
				confPasswordField.transferFocusBackward();
				JOptionPane.showMessageDialog(this, "Primero debes introducir una contraseña", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textNombreUsr)) {
			if (insertString(50, textNombreUsr.getText())) {
				textNombreUsr.setText("");
			}
			try {
				User usr = userInterface.buscarUser(textNombreUsr.getText());
				if (usr == null && !textNombreUsr.getText().isEmpty()) {
					textNombreUsr.setText("");

					JOptionPane.showMessageDialog(this, "El nombre de usuario ya esta registrado", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (GestorException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(passwordField)) {
			String contr = new String(passwordField.getPassword());
			if (insertString(50, contr)) {
				passwordField.setText("");
			}
		}
		if (e.getSource().equals(confPasswordField)) {
			String contr = new String(passwordField.getPassword());
			String conf = new String(confPasswordField.getPassword());
			if (!contr.equalsIgnoreCase(conf)) {
				JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error",
						JOptionPane.WARNING_MESSAGE);
				passwordField.setText("");
				confPasswordField.setText("");
			}
			if (insertString(50, conf)) {
				confPasswordField.setText("");
			}
		}
		if (e.getSource().equals(textApellidos)) {
			if (insertString(50, textApellidos.getText())) {
				textApellidos.setText("");
			}
		}
		if (e.getSource().equals(textAutor)) {
			if (insertString(50, textAutor.getText())) {
				textAutor.setText("");
			}
		}
		if (e.getSource().equals(textCuenta)) {
			if (insertString(50, textCuenta.getText())) {
				textCuenta.setText("");
			}
			if (textCuenta.getText().matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
				textCuenta.setText("");
			}
		}
		if (e.getSource().equals(textDireccion)) {
			if (insertString(50, textDireccion.getText())) {
				textDireccion.setText("");
			}
		}
		if (e.getSource().equals(textEmail)) {
			if (insertString(50, textEmail.getText())) {
				textEmail.setText("");
			}
		}
		if (e.getSource().equals(textGenero)) {
			if (insertString(50, textGenero.getText())) {
				textGenero.setText("");
			}
		}
		if (e.getSource().equals(textNombre)) {
			if (insertString(50, textNombre.getText())) {
				textNombre.setText("");
			}
		}
		if (e.getSource().equals(textNumtelef)) {
			if (insertString(50, textNumtelef.getText())) {
				textNumtelef.setText("");
			}
			if (textNumtelef.getText().matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
						JOptionPane.WARNING_MESSAGE);
				textNumtelef.setText("");
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
