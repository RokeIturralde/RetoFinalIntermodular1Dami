package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.dataAccess.IUserControllerDBImplementation;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registro extends JDialog implements ActionListener, FocusListener {
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
	private IUserController userInterface = new IUserControllerDBImplementation();

	public Registro() {
		this.setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Registro.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
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

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(155, 275, 134, 14);
		getContentPane().add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(309, 273, 172, 20);
		getContentPane().add(textApellidos);

		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(155, 306, 134, 14);
		getContentPane().add(lblDireccion);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(309, 304, 172, 20);
		getContentPane().add(textDireccion);

		lblNumTelef = new JLabel("* Numero de telefono:");
		lblNumTelef.setForeground(Color.WHITE);
		lblNumTelef.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumTelef.setBounds(140, 337, 149, 14);
		getContentPane().add(lblNumTelef);

		textNumtelef = new JTextField();
		textNumtelef.setColumns(10);
		textNumtelef.setBounds(309, 335, 172, 20);
		getContentPane().add(textNumtelef);

		lblNumCuenta = new JLabel("Numero de cuenta:");
		lblNumCuenta.setForeground(Color.WHITE);
		lblNumCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumCuenta.setBounds(155, 368, 134, 14);
		getContentPane().add(lblNumCuenta);

		textCuenta = new JTextField();
		textCuenta.setColumns(10);
		textCuenta.setBounds(309, 366, 172, 20);
		getContentPane().add(textCuenta);

		lblAutores = new JLabel("* Autores preferidos:");
		lblAutores.setForeground(Color.WHITE);
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutores.setBounds(140, 399, 149, 14);
		getContentPane().add(lblAutores);

		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(309, 397, 172, 20);
		getContentPane().add(textAutor);

		lblGeneros = new JLabel("* Generos preferidos:");
		lblGeneros.setForeground(Color.WHITE);
		lblGeneros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneros.setBounds(140, 430, 149, 14);
		getContentPane().add(lblGeneros);

		textGenero = new JTextField();
		textGenero.setColumns(10);
		textGenero.setBounds(309, 428, 172, 20);
		getContentPane().add(textGenero);

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
			// Se necesita metodo de busqueda
		}
		if (e.getSource().equals(btnGenero)) {
			// Se necesita metodo de busqueda
		}
		if (e.getSource().equals(btnRegistrar)) {
			String pass = new String(passwordField.getPassword());
			if (!(textDireccion.getText().isEmpty() || textEmail.getText().isEmpty() || textNombre.getText().isEmpty()
					|| pass.isEmpty() || textApellidos.getText().isEmpty()
					|| textNombreUsr.getText().isEmpty() || textCuenta.getText().isEmpty())) {
				try {
					user = new Partner();
					user.setAddress(textDireccion.getText());
					user.setEmail(textEmail.getText());
					user.setName(textNombre.getText());
					user.setPassword(pass);
					if(textNumtelef.getText().length() != 0) {
						user.setPhone(Integer.valueOf(textNumtelef.getText()));
					}
					user.setSurname(textApellidos.getText());
					user.setTipo('P');
					user.setUserName(textNombreUsr.getText());
					((Partner)user).setNumAccount(Integer.valueOf(textCuenta.getText()));

					userInterface.altaUsuario(user);

					JOptionPane.showMessageDialog(this, "Usuario registrado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.INFORMATION_MESSAGE);
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
			try {
				User usr = userInterface.buscarUser(textNombreUsr.getText());
				if (usr != null) {
					textApellidos.setText("");
					textAutor.setText("");
					textCuenta.setText("");
					textDireccion.setText("");
					textEmail.setText("");
					textGenero.setText("");
					textNombre.setText("");
					textNombreUsr.setText("");
					textNumtelef.setText("");

					JOptionPane.showMessageDialog(this, "El nombre de usuario ya esta registrado", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (GestorException e1) {
				e1.printStackTrace();
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
		}
	}

}
