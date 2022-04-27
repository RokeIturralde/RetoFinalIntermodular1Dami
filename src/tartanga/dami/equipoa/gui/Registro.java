package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registro extends JDialog {
	private JTextField textNombreUsr;
	private JTextField textEmail;
	private JPasswordField passwordField;
	private JPasswordField ConfPasswordField;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JLabel lblDireccion;
	private JTextField textDireccion;
	private JLabel lblNumTelef;
	private JTextField textNumtelef;
	private JLabel lblNumCuenta;
	private JTextField textCuenta;
	private JLabel lblAutores;
	private JTextField textField;
	private JLabel lblGeneros;
	private JTextField textField_1;
	private JButton btnGenero;
	private JButton btnGenero_1;
	private JLabel lblNewLabel;
	private JButton btnRegistrar;

	public Registro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 791, 613);
		getContentPane().setLayout(null);
		
		JLabel lblNombreUsr = new JLabel("Nombre de Usuario:");
		lblNombreUsr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreUsr.setForeground(Color.WHITE);
		lblNombreUsr.setBounds(235, 116, 120, 14);
		getContentPane().add(lblNombreUsr);
		
		textNombreUsr = new JTextField();
		textNombreUsr.setBounds(375, 114, 172, 20);
		getContentPane().add(textNombreUsr);
		textNombreUsr.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(375, 145, 172, 20);
		getContentPane().add(textEmail);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(235, 147, 120, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblContr = new JLabel("Contrase\u00F1a:");
		lblContr.setForeground(Color.WHITE);
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContr.setBounds(235, 178, 120, 14);
		getContentPane().add(lblContr);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(375, 176, 172, 20);
		getContentPane().add(passwordField);
		
		ConfPasswordField = new JPasswordField();
		ConfPasswordField.setBounds(375, 207, 172, 20);
		getContentPane().add(ConfPasswordField);
		
		JLabel lblConfirmar = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmar.setForeground(Color.WHITE);
		lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConfirmar.setBounds(235, 209, 129, 14);
		getContentPane().add(lblConfirmar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(235, 240, 120, 14);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(375, 238, 172, 20);
		getContentPane().add(textNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidos.setBounds(235, 271, 120, 14);
		getContentPane().add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(375, 269, 172, 20);
		getContentPane().add(textApellidos);
		
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(235, 302, 120, 14);
		getContentPane().add(lblDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(375, 300, 172, 20);
		getContentPane().add(textDireccion);
		
		lblNumTelef = new JLabel("Numero de telefono:");
		lblNumTelef.setForeground(Color.WHITE);
		lblNumTelef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumTelef.setBounds(235, 333, 120, 14);
		getContentPane().add(lblNumTelef);
		
		textNumtelef = new JTextField();
		textNumtelef.setColumns(10);
		textNumtelef.setBounds(375, 331, 172, 20);
		getContentPane().add(textNumtelef);
		
		lblNumCuenta = new JLabel("Numero de cuenta:");
		lblNumCuenta.setForeground(Color.WHITE);
		lblNumCuenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumCuenta.setBounds(235, 364, 120, 14);
		getContentPane().add(lblNumCuenta);
		
		textCuenta = new JTextField();
		textCuenta.setColumns(10);
		textCuenta.setBounds(375, 362, 172, 20);
		getContentPane().add(textCuenta);
		
		lblAutores = new JLabel("Autores preferidos:");
		lblAutores.setForeground(Color.WHITE);
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAutores.setBounds(235, 395, 120, 14);
		getContentPane().add(lblAutores);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(375, 393, 172, 20);
		getContentPane().add(textField);
		
		lblGeneros = new JLabel("Generos preferidos:");
		lblGeneros.setForeground(Color.WHITE);
		lblGeneros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeneros.setBounds(235, 426, 120, 14);
		getContentPane().add(lblGeneros);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(375, 424, 172, 20);
		getContentPane().add(textField_1);
		
		btnGenero = new JButton("A\u00F1adir preferencia(3 max)");
		btnGenero.setBounds(557, 423, 159, 23);
		getContentPane().add(btnGenero);
		
		btnGenero_1 = new JButton("A\u00F1adir preferencia(3 max)");
		btnGenero_1.setBounds(557, 392, 159, 23);
		getContentPane().add(btnGenero_1);
		
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

	}

}
