package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class LogIn extends JFrame {
	private JTextField textUsuario;
	private JPasswordField passwordField;

	public LogIn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		getContentPane().setForeground(UIManager.getColor("textInactiveText"));
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 602, 465);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(173, 164, 239, 29);
		getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblNombreEmail = new JLabel("Nombre o email de la cuenta:");
		lblNombreEmail.setForeground(Color.WHITE);
		lblNombreEmail.setBackground(Color.WHITE);
		lblNombreEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreEmail.setBounds(173, 139, 256, 14);
		getContentPane().add(lblNombreEmail);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesi\u00F3n");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setBounds(210, 66, 159, 36);
		getContentPane().add(lblIniciarSesion);
		
		JLabel lblContr = new JLabel("Contrase\u00F1a:");
		lblContr.setForeground(Color.WHITE);
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContr.setBackground(Color.WHITE);
		lblContr.setBounds(173, 214, 256, 14);
		getContentPane().add(lblContr);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setBorder(null);
		btnIniciarSesion.setBackground(new Color(128, 128, 128));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBounds(210, 314, 159, 23);
		getContentPane().add(btnIniciarSesion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 239, 239, 29);
		getContentPane().add(passwordField);
		
		JLabel lblNoCuenta = new JLabel("No tienes cuenta?");
		lblNoCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoCuenta.setForeground(Color.WHITE);
		lblNoCuenta.setBounds(366, 389, 100, 14);
		getContentPane().add(lblNoCuenta);
		
		JButton btnRegistrar = new JButton("Registrate");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegistrar.setForeground(Color.CYAN);
		btnRegistrar.setBackground(Color.DARK_GRAY);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBounds(467, 385, 60, 23);
		getContentPane().add(btnRegistrar);

	}
}
