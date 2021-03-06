package tartanga.dami.equipoa.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;

/**
 * @author Sendoa
 * Panel para la gestion administrativa de los autores, se podran a?adir modificar y/o eliminar
 */
public class WAdminAutor extends JPanel implements ActionListener, FocusListener {

	private IAuthorController authorInterface;
	private JCalendar calendarAutorNac;
	private JTextField textAutor;
	private JTextField textNombreA;
	private JLabel lblNombreA;
	private JLabel lblApellido;
	private JTextField textApellido;
	private JLabel lblFechaNacimiento;
	private JLabel lblFechaMuerte;
	private JCheckBox chckbxVivo;
	private JCalendar calendarioAutorMuer;
	private Author autor;
	private JButton btnRegistrarAutor;
	private JButton btnModificarAutor;
	private JButton btnEliminarAutor;
	private JButton btnCancelarAutor;

	/**
	 * @param authorInterface Interfaz de autor
	 */
	public WAdminAutor(IAuthorController authorInterface) {
		this.authorInterface = authorInterface;

		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1047, 680);
		setLayout(null);

		JLabel lblAutor = new JLabel("Introduce el codigo del autor:");
		lblAutor.setVerticalAlignment(SwingConstants.TOP);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutor.setBounds(56, 62, 188, 20);
		this.add(lblAutor);

		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(56, 87, 137, 20);
		this.add(textAutor);
		textAutor.addFocusListener(this);

		textNombreA = new JTextField();
		textNombreA.setColumns(10);
		textNombreA.setBounds(56, 143, 137, 20);
		this.add(textNombreA);
		textNombreA.addFocusListener(this);

		lblNombreA = new JLabel("Introduce el nombre del autor:");
		lblNombreA.setForeground(Color.WHITE);
		lblNombreA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreA.setBounds(56, 118, 188, 14);
		this.add(lblNombreA);

		lblApellido = new JLabel("Introduce el apellido del autor:");
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellido.setBounds(56, 174, 188, 14);
		this.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(56, 199, 137, 20);
		this.add(textApellido);
		textApellido.addFocusListener(this);

		lblFechaNacimiento = new JLabel("Introduce la fecha de nacimiento del autor:");
		lblFechaNacimiento.setForeground(Color.WHITE);
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaNacimiento.setBounds(56, 230, 270, 14);
		this.add(lblFechaNacimiento);

		calendarAutorNac = new JCalendar();
		calendarAutorNac.setBounds(56, 255, 184, 153);
		this.add(calendarAutorNac);

		lblFechaMuerte = new JLabel("Introduce la fecha de muerte del autor:");
		lblFechaMuerte.setForeground(Color.WHITE);
		lblFechaMuerte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaMuerte.setBounds(56, 419, 270, 14);
		this.add(lblFechaMuerte);

		calendarioAutorMuer = new JCalendar();
		calendarioAutorMuer.setBounds(56, 444, 184, 153);
		this.add(calendarioAutorMuer);

		chckbxVivo = new JCheckBox("Sigue vivo");
		chckbxVivo.setBackground(Color.GRAY);
		chckbxVivo.setBounds(267, 507, 97, 23);
		this.add(chckbxVivo);
		chckbxVivo.addActionListener(this);

		btnRegistrarAutor = new JButton("Registrar autor");
		btnRegistrarAutor.setForeground(Color.WHITE);
		btnRegistrarAutor.setFocusPainted(false);
		btnRegistrarAutor.setBorder(null);
		btnRegistrarAutor.setBackground(Color.GRAY);
		btnRegistrarAutor.setBounds(618, 473, 159, 23);
		this.add(btnRegistrarAutor);
		btnRegistrarAutor.addActionListener(this);

		btnModificarAutor = new JButton("Guardar cambios");
		btnModificarAutor.setEnabled(false);
		btnModificarAutor.setForeground(Color.WHITE);
		btnModificarAutor.setFocusPainted(false);
		btnModificarAutor.setBorder(null);
		btnModificarAutor.setBackground(Color.GRAY);
		btnModificarAutor.setBounds(618, 439, 159, 23);
		this.add(btnModificarAutor);
		btnModificarAutor.addActionListener(this);

		btnEliminarAutor = new JButton("Eliminar autor");
		btnEliminarAutor.setEnabled(false);
		btnEliminarAutor.setForeground(Color.WHITE);
		btnEliminarAutor.setFocusPainted(false);
		btnEliminarAutor.setBorder(null);
		btnEliminarAutor.setBackground(Color.GRAY);
		btnEliminarAutor.setBounds(618, 405, 159, 23);
		this.add(btnEliminarAutor);
		btnEliminarAutor.addActionListener(this);

		btnCancelarAutor = new JButton("Cancelar");
		btnCancelarAutor.setForeground(Color.WHITE);
		btnCancelarAutor.setFocusPainted(false);
		btnCancelarAutor.setBorder(null);
		btnCancelarAutor.setBackground(Color.GRAY);
		btnCancelarAutor.setBounds(618, 507, 159, 23);
		this.add(btnCancelarAutor);
		btnCancelarAutor.addActionListener(this);

		JLabel lblIconoAutor = new JLabel("New label");
		lblIconoAutor.setIcon(new ImageIcon(WAdmin.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		lblIconoAutor.setBounds(448, 11, 484, 344);
		this.add(lblIconoAutor);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(chckbxVivo)) {
			// Dependiendo de si esta vivo o no se activara el calendario
			if (chckbxVivo.isSelected()) {
				calendarioAutorMuer.setEnabled(false);
			} else {
				calendarioAutorMuer.setEnabled(true);
			}
		}
		if (e.getSource().equals(btnRegistrarAutor)) {
			if (textAutor.getText().isEmpty() || textApellido.getText().isEmpty() || textNombreA.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (calendarAutorNac.getCalendar().after(Calendar.getInstance())
						|| calendarioAutorMuer.getCalendar().after(Calendar.getInstance())) {
					JOptionPane.showMessageDialog(this, "La fecha no puede ser superior a la actual", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {

					try {
						autor = new Author();
						autor.setCodAuthor(textAutor.getText());
						autor.setName(textNombreA.getText());
						autor.setSurname(textApellido.getText());
						Date fechaNac = new Date(calendarAutorNac.getDate().getTime());
						autor.setBirthDate(fechaNac);
						if (chckbxVivo.isSelected()) {
							autor.setDeathDate(null);
						} else {
							Date fechaMuert = new Date(calendarioAutorMuer.getDate().getTime());
							autor.setDeathDate(fechaMuert);
						}
						authorInterface.altaAuthor(autor);

						JOptionPane.showMessageDialog(this, "Autor registrado con exito", "Ta bien",
								JOptionPane.INFORMATION_MESSAGE);

						textAutor.setText("");
						textNombreA.setText("");
						textApellido.setText("");
						calendarAutorNac.setCalendar(Calendar.getInstance());
						calendarioAutorMuer.setCalendar(Calendar.getInstance());
					} catch (GestorException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		}
		if (e.getSource().equals(btnModificarAutor)) {
			if (textAutor.getText().isEmpty() || textApellido.getText().isEmpty() || textNombreA.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (calendarAutorNac.getCalendar().after(Calendar.getInstance())
						|| calendarioAutorMuer.getCalendar().after(Calendar.getInstance())) {
					JOptionPane.showMessageDialog(this, "La fecha no puede ser superior a la actual", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {

					try {
						autor = new Author();
						autor.setCodAuthor(textAutor.getText());
						autor.setName(textNombreA.getText());
						autor.setSurname(textApellido.getText());
						Date fechaNac = new Date(calendarAutorNac.getDate().getTime());
						autor.setBirthDate(fechaNac);
						if (chckbxVivo.isSelected()) {
							autor.setDeathDate(null);
						} else {
							Date fechaMuert = new Date(calendarioAutorMuer.getDate().getTime());
							autor.setDeathDate(fechaMuert);
						}
						authorInterface.modificarAuthor(autor);

						JOptionPane.showMessageDialog(this, "Autor modificado con exito", "Ta bien",
								JOptionPane.INFORMATION_MESSAGE);

						textAutor.setText("");
						textNombreA.setText("");
						textApellido.setText("");
						calendarAutorNac.setCalendar(Calendar.getInstance());
						calendarioAutorMuer.setCalendar(Calendar.getInstance());
						calendarioAutorMuer.setEnabled(true);
						chckbxVivo.setSelected(false);
						btnEliminarAutor.setEnabled(false);
						btnModificarAutor.setEnabled(false);
						btnRegistrarAutor.setEnabled(true);
						textAutor.setEnabled(true);
					} catch (GestorException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		}
		if (e.getSource().equals(btnEliminarAutor)) {
			int seleccion = JOptionPane.showConfirmDialog(this, "Quieres eliminar este autor con el codigo: "+textAutor.getText()+"?", "Aviso", 0);
			if (seleccion == 0) {
				try {
					authorInterface.eliminarAuthor(textAutor.getText());

					JOptionPane.showMessageDialog(this, "Autor eliminado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);

					textAutor.setText("");
					textNombreA.setText("");
					textApellido.setText("");
					calendarAutorNac.setCalendar(Calendar.getInstance());
					calendarioAutorMuer.setCalendar(Calendar.getInstance());
					calendarioAutorMuer.setEnabled(true);
					chckbxVivo.setSelected(false);
					btnEliminarAutor.setEnabled(false);
					btnModificarAutor.setEnabled(false);
					btnRegistrarAutor.setEnabled(true);
					textAutor.setEnabled(true);
				} catch (GestorException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (e.getSource().equals(btnCancelarAutor)) {
			textAutor.setText("");
			textNombreA.setText("");
			textApellido.setText("");
			calendarAutorNac.setCalendar(Calendar.getInstance());
			calendarioAutorMuer.setCalendar(Calendar.getInstance());
			if (btnModificarAutor.isEnabled()) {
				btnEliminarAutor.setEnabled(false);
				btnModificarAutor.setEnabled(false);
				btnRegistrarAutor.setEnabled(true);
				textAutor.setEnabled(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textAutor)) {
			if (insertString(50, textAutor.getText())) {
				textAutor.setText("");
				textAutor.grabFocus();
			}
			try {
				autor = authorInterface.buscarAuthor(textAutor.getText());
				if (autor != null) {
					int seleccion = JOptionPane.showConfirmDialog(this,
							"Ese autor ya a sido introducido quieres modificarlo?", "Aviso", 0);
					if (seleccion == 0) {
						btnRegistrarAutor.setEnabled(false);
						btnEliminarAutor.setEnabled(true);
						btnModificarAutor.setEnabled(true);
						textAutor.setEnabled(false);
						calendarioAutorMuer.setEnabled(true);
						chckbxVivo.setSelected(false);

						textApellido.setText(autor.getSurname());
						textNombreA.setText(autor.getName());
						calendarAutorNac.setDate(autor.getBirthDate());
						// Si la fecha que le llega es null quiere decir que esta vivo
						try {
							calendarioAutorMuer.setDate(autor.getDeathDate());
						} catch (NullPointerException e1) {
							calendarioAutorMuer.setCalendar(Calendar.getInstance());
							chckbxVivo.setSelected(true);
							calendarioAutorMuer.setEnabled(false);
						}
					} else {
						textAutor.setText("");
						textAutor.grabFocus();
					}
				}
			} catch (GestorException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(textApellido)) {
			if (insertString(50, textApellido.getText())) {
				textApellido.setText("");
				textApellido.grabFocus();
			}
		}
		if (e.getSource().equals(textNombreA)) {
			if (insertString(50, textNombreA.getText())) {
				textNombreA.setText("");
				textApellido.grabFocus();
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
			JOptionPane.showMessageDialog(this, "Excedido el limite de caracteres ("+maximo+")", "Error",
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
}
