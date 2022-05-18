package tartanga.dami.equipoa.gui;

import java.awt.Color;

import javax.swing.JPanel;

import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Genre;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Sendoa
 *
 */
public class WAdminGenre extends JPanel implements ActionListener, FocusListener{
	
	private IGenreController genreInterface;
	private JTextField textGenero;
	private JTextArea textDescripcion;
	private JButton btnCancelarGenero;
	private JButton btnRegistrarGenero;
	private JButton btnModificarGenero;
	private JButton btnEliminarGenero;
	private Genre genero;
	
	/**
	 * @param genreInterface Interfaz de genero
	 */
	public WAdminGenre(IGenreController genreInterface) {
		this.genreInterface = genreInterface;
		
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1047, 680);
		setLayout(null);
		
		textGenero = new JTextField();
		textGenero.setColumns(10);
		textGenero.setBounds(73, 141, 137, 20);
		add(textGenero);
		textGenero.addFocusListener(this);
		
		JLabel lblGenero = new JLabel("Introduce el nombre del genero:");
		lblGenero.setVerticalAlignment(SwingConstants.TOP);
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(73, 116, 205, 20);
		add(lblGenero);
		
		JLabel lblDescripcionDelGenero = new JLabel("Descripcion del genero:");
		lblDescripcionDelGenero.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcionDelGenero.setForeground(Color.WHITE);
		lblDescripcionDelGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcionDelGenero.setBounds(73, 289, 221, 20);
		add(lblDescripcionDelGenero);
		
		textDescripcion = new JTextArea();
		textDescripcion.setBounds(73, 312, 361, 177);
		add(textDescripcion);
		textDescripcion.addFocusListener(this);
		
		JLabel lblIcono = new JLabel("New label");
		lblIcono.setIcon(new ImageIcon(WAdminGenre.class.getResource("/tartanga/dami/equipoa/resources/Logo.png")));
		lblIcono.setBounds(515, 30, 484, 344);
		add(lblIcono);
		
		btnCancelarGenero = new JButton("Cancelar");
		btnCancelarGenero.setForeground(Color.WHITE);
		btnCancelarGenero.setFocusPainted(false);
		btnCancelarGenero.setBorder(null);
		btnCancelarGenero.setBackground(Color.GRAY);
		btnCancelarGenero.setBounds(687, 531, 159, 23);
		add(btnCancelarGenero);
		btnCancelarGenero.addActionListener(this);
		
		btnRegistrarGenero = new JButton("Registrar genero");
		btnRegistrarGenero.setForeground(Color.WHITE);
		btnRegistrarGenero.setFocusPainted(false);
		btnRegistrarGenero.setBorder(null);
		btnRegistrarGenero.setBackground(Color.GRAY);
		btnRegistrarGenero.setBounds(687, 497, 159, 23);
		add(btnRegistrarGenero);
		btnRegistrarGenero.addActionListener(this);
		
		btnModificarGenero = new JButton("Guardar cambios");
		btnModificarGenero.setForeground(Color.WHITE);
		btnModificarGenero.setFocusPainted(false);
		btnModificarGenero.setEnabled(false);
		btnModificarGenero.setBorder(null);
		btnModificarGenero.setBackground(Color.GRAY);
		btnModificarGenero.setBounds(687, 463, 159, 23);
		add(btnModificarGenero);
		btnModificarGenero.addActionListener(this);
		
		btnEliminarGenero = new JButton("Eliminar genero");
		btnEliminarGenero.setForeground(Color.WHITE);
		btnEliminarGenero.setFocusPainted(false);
		btnEliminarGenero.setEnabled(false);
		btnEliminarGenero.setBorder(null);
		btnEliminarGenero.setBackground(Color.GRAY);
		btnEliminarGenero.setBounds(687, 429, 159, 23);
		add(btnEliminarGenero);
		btnEliminarGenero.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnRegistrarGenero)) {
			if(textDescripcion.getText().isEmpty() || textGenero.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					genero = new Genre();
					genero.setGenreName(textGenero.getText());
					genero.setDescription(textDescripcion.getText());
					genreInterface.altaGenre(genero);
					
					JOptionPane.showMessageDialog(this, "Genero registrado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);
					
					textGenero.setText("");
					textDescripcion.setText("");
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource().equals(btnModificarGenero)) {
			if(textDescripcion.getText().isEmpty() || textGenero.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					genero = new Genre();
					genero.setGenreName(textGenero.getText());
					genero.setDescription(textDescripcion.getText());
					genreInterface.modificarGenre(genero);
					
					JOptionPane.showMessageDialog(this, "Genero modificado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);
					
					textGenero.setText("");
					textDescripcion.setText("");
					textGenero.setEnabled(true);
					btnEliminarGenero.setEnabled(false);
					btnModificarGenero.setEnabled(false);
					btnRegistrarGenero.setEnabled(true);
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource().equals(btnEliminarGenero)) {
			int seleccion = JOptionPane.showConfirmDialog(this, "Quieres eliminar el genero "+textGenero.getText()+"?", "Aviso", 0);
			if(seleccion == 0) {
				try {
					genreInterface.eliminarGenre(textGenero.getText());
					
					JOptionPane.showMessageDialog(this, "Genero eliminado con exito", "Ta bien",
							JOptionPane.INFORMATION_MESSAGE);
					
					textGenero.setText("");
					textDescripcion.setText("");
					textGenero.setEnabled(true);
					btnEliminarGenero.setEnabled(false);
					btnModificarGenero.setEnabled(false);
					btnRegistrarGenero.setEnabled(true);
				} catch (GestorException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource().equals(btnCancelarGenero)) {
			textGenero.setText("");
			textDescripcion.setText("");
			if(btnModificarGenero.isEnabled()) {
				textGenero.setEnabled(true);
				btnEliminarGenero.setEnabled(false);
				btnModificarGenero.setEnabled(false);
				btnRegistrarGenero.setEnabled(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(textGenero)) {
			if(insertString(50, textGenero.getText())) {
				textGenero.setText("");
				textGenero.grabFocus();
			}
			try {
				genero = genreInterface.buscarGenre(textGenero.getText());
				if(genero != null) {
					int seleccion = JOptionPane.showConfirmDialog(this,
							"Ese genero ya a sido introducido quieres modificarlo?", "Aviso", 0);
					if(seleccion == 0) {
						btnRegistrarGenero.setEnabled(false);
						btnEliminarGenero.setEnabled(true);
						btnModificarGenero.setEnabled(true);
						textGenero.setEnabled(false);
						
						textGenero.setText(genero.getGenreName());
						textDescripcion.setText(genero.getDescription());
					} else {
						textGenero.setText("");
						textGenero.grabFocus();
					}
				}
			} catch (GestorException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(textDescripcion)) {
			if(insertString(50, textDescripcion.getText())) {
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
			JOptionPane.showMessageDialog(this, "Excedido el limite de caracteres ("+maximo+")", "Error",
					JOptionPane.WARNING_MESSAGE);
			return true;
		}
		return false;
	}
}
