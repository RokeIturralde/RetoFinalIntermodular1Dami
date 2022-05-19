package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.Genre;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.Rectangle;

public class WMenuPerfil extends JPanel implements ActionListener, MouseListener {
	private JTextField txtNombre;
	private JTextField txtContrasenna;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtNumCuenta;
	private IUserController userInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IBookController bookInterface;
	private IComprasController comprasInterface;

	private JButton btnGuardarCambios;
	private JButton btnModificarDatos;
	private JButton btnAnnadirPreferencia;
	private JButton btnBorrarPreferencias;
	private JTextField txtApellido;
	private JScrollPane scrollPane;
	private JTable tableHistorialCompras;
	private JLabel lblAutor, lblGenero;
	private JComboBox cbxGeneros, cbxAutores;
	private JList listGeneros, listAutores;
	// Listas para el JList
	private ArrayList<Author> autores;
	private ArrayList<String> generos;
	private ArrayList<String> generosComboBox;
	private WMenuInicio menuInicio;

	private User user;
	private DefaultListModel modelo2, modelo;

	public WMenuPerfil(IUserController userInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IComprasController comprasInterface, User user, WMenuInicio panelInicio) {

		setLayout(null);
		this.userInterface = userInterface;
		this.authorInterface = authorInterface;
		this.user = user;
		this.genreInterface = genreInterface;
		this.comprasInterface = comprasInterface;
		this.menuInicio = panelInicio;

		setBounds(100, 300, 520, 12);

		btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBounds(32, 25, 118, 35);
		add(btnModificarDatos);
		btnModificarDatos.addActionListener(this);

		btnGuardarCambios = new JButton("GuardarCambios");
		btnGuardarCambios.setBounds(205, 25, 118, 35);
		add(btnGuardarCambios);
		btnGuardarCambios.setEnabled(false);
		btnGuardarCambios.addActionListener(this);

		btnAnnadirPreferencia = new JButton("A\u00F1adir (Max 3)");
		btnAnnadirPreferencia.setBounds(526, 531, 118, 23);
		add(btnAnnadirPreferencia);
		btnAnnadirPreferencia.setEnabled(false);
		btnAnnadirPreferencia.addActionListener(this);

		btnBorrarPreferencias = new JButton("Borrar");
		btnBorrarPreferencias.setBounds(107, 575, 118, 23);
		add(btnBorrarPreferencias);
		btnBorrarPreferencias.setEnabled(false);
		btnBorrarPreferencias.addActionListener(this);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(32, 94, 84, 29);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido/s");
		lblApellido.setBounds(32, 134, 84, 29);
		add(lblApellido);

		JLabel lblContrasenna = new JLabel("Contrase\u00F1a");
		lblContrasenna.setBounds(32, 172, 84, 29);
		add(lblContrasenna);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(32, 212, 84, 29);
		add(lblEmail);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(32, 248, 84, 29);
		add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(32, 284, 84, 29);
		add(lblTelefono);

		JLabel lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setBounds(10, 320, 128, 29);
		add(lblNumCuenta);

		JLabel lblNewLabel = new JLabel("Preferencias Personales");
		lblNewLabel.setBounds(90, 360, 158, 35);
		add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setBounds(126, 98, 194, 25);
		add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setText((String) null);
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(126, 138, 194, 25);
		add(txtApellido);

		txtContrasenna = new JTextField();
		txtContrasenna.setColumns(10);
		txtContrasenna.setBounds(126, 174, 194, 25);
		add(txtContrasenna);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(126, 214, 194, 25);
		add(txtEmail);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(126, 250, 194, 25);
		add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(126, 286, 194, 25);
		add(txtTelefono);

		txtNumCuenta = new JTextField();
		txtNumCuenta.setColumns(10);
		txtNumCuenta.setBounds(126, 322, 194, 25);
		add(txtNumCuenta);

		JLabel lblHistorialCompra = new JLabel("Historial de Compra");
		lblHistorialCompra.setBounds(636, 50, 118, 29);
		add(lblHistorialCompra);

		JPanel panelHistorialCompra = new JPanel();
		panelHistorialCompra.setBounds(454, 76, 490, 319);
		add(panelHistorialCompra);

		// Cargar los datos del Usuario
		txtNombre.setText(user.getName());
		txtNombre.setEditable(false);
		txtApellido.setText(user.getSurname());
		txtApellido.setEditable(false);
		txtContrasenna.setText(user.getPassword());
		txtContrasenna.setEditable(false);
		txtEmail.setText(user.getEmail());
		txtEmail.setEditable(false);
		txtDireccion.setText(user.getAddress());
		txtDireccion.setEditable(false);
		txtTelefono.setText(Integer.toString(user.getPhone()));
		txtTelefono.setEditable(false);
		txtNumCuenta.setText(Integer.toString(((Partner) user).getNumAccount()));
		txtNumCuenta.setEditable(false);

		// Listas de Autores Preferidos
		JLabel lblAutores = new JLabel("Autores");
		lblAutores.setBounds(67, 384, 106, 29);
		add(lblAutores);

		JLabel lblGeneros = new JLabel("Generos");
		lblGeneros.setBounds(217, 381, 106, 35);
		add(lblGeneros);

		try {
			autores = authorInterface.listarAutoresPreferidos(user.getUserName());
			generos = genreInterface.listarGenerosPreferidos(user.getUserName());

			listAutores = new JList();
			modelo = new DefaultListModel();
			if (autores.size() > 0) {
				for (int i = 0; i < autores.size(); i++) {
					modelo.addElement(autores.get(i).getName() + " " + autores.get(i).getSurname());
				}
			}
			listAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAutores.setBounds(32, 406, 118, 148);
			listAutores.setModel(modelo);
			add(listAutores);

			// Inicializamos la Lista de Generos Preferidos
			listGeneros = new JList();
			modelo2 = new DefaultListModel();
			if (generos.size() > 0) {
				for (int i = 0; i < generos.size(); i++) {
					modelo2.addElement(generos.get(i));
				}
			}
			listGeneros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listGeneros.setBounds(183, 406, 118, 148);
			listGeneros.setModel(modelo2);
			add(listGeneros);

			lblAutor = new JLabel("A\u00F1adir Autor");
			lblAutor.setBounds(389, 444, 106, 23);
			add(lblAutor);

			cbxGeneros = new JComboBox();
			cbxGeneros.setBounds(652, 476, 158, 29);
			cargarGeneros();
			cbxGeneros.setEnabled(false);
			add(cbxGeneros);

			lblGenero = new JLabel("A\u00F1adir Genero");
			lblGenero.setBounds(691, 447, 96, 17);
			add(lblGenero);

			cbxAutores = new JComboBox();
			cbxAutores.setBounds(351, 476, 158, 29);
			cargarAutores();
			cbxAutores.setEnabled(false);
			add(cbxAutores);
		} catch (GestorException e1) {
			e1.printStackTrace();
		}

		// Creacion tabla del historial de compra
		try {
			ArrayList<Compra> compras = comprasInterface.historialCompras(user.getUserName());
			if (compras.size() > 0) {
				String matrizTabla[][] = new String[compras.size()][5];
				for (int i = 0; i < compras.size(); i++) {
					matrizTabla[i][0] = compras.get(i).getFechaCompra().toString();
					matrizTabla[i][1] = compras.get(i).getAuthors();
					matrizTabla[i][2] = Integer.toString(compras.get(i).getIsbn());
					matrizTabla[i][3] = Integer.toString(compras.get(i).getCantidadLibros());
					matrizTabla[i][4] = Float.toString(compras.get(i).getPrecioCompra());
				}

				scrollPane = new JScrollPane();
				scrollPane.setBounds(25, 209, 583, 125);
				panelHistorialCompra.add(scrollPane);

				String titulos[] = { "Fecha", "Autor", "Isbn", "Cantidad", "Precio" };

				tableHistorialCompras = new JTable(matrizTabla, titulos) {
					/*
					 * 
					 */
					private static final long serialVersionUID = 1L;

					// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
					// HACER DOBLE CLICK************************************
					// Para ello sobreescribimos el metodo que ya tiene la clase
					// JTable.isCellEditable
					public boolean isCellEditable(int row, int column) {
						for (int i = 0; i < tableHistorialCompras.getRowCount(); i++) {
							if (row == i) {
								return false;
							}
						}
						return true;
					}
				};

				tableHistorialCompras.setSelectionBackground(new Color(0, 230, 168));
				tableHistorialCompras.setSelectionForeground(Color.WHITE);
				tableHistorialCompras.setRowMargin(0);
				tableHistorialCompras.setRowHeight(22);
				tableHistorialCompras.setShowVerticalLines(false);
				scrollPane.setViewportView(tableHistorialCompras);
				tableHistorialCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));

				JTableHeader tableHeader = tableHistorialCompras.getTableHeader();
				tableHeader.setBackground(new Color(0, 191, 140));
				tableHeader.setForeground(Color.WHITE);
				tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
				tableHeader.setBorder(null);
				tableHeader.setEnabled(false);
			} else {
				JLabel lblNoHayCompras = new JLabel("");

				lblNoHayCompras.setIcon(new ImageIcon(
						WMenuPerfil.class.getResource("/tartanga/dami/equipoa/resources/imgNoHayCompras.png")));
				lblNoHayCompras.setBounds(25, 209, 583, 125);
				this.add(lblNoHayCompras);
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnModificarDatos)) {
			modificarDatos();
		}
		if (e.getSource().equals(btnGuardarCambios)) {
			guardarCambios(user);
		}
		if (e.getSource().equals(btnAnnadirPreferencia)) {
			annadirPreferencia();
		}
		if (e.getSource().equals(btnBorrarPreferencias)) {
			borrarPreferencia(autores, user, generos);
		}

	}

	// Refrescar los datos modificados
	public void refrescarPreferencias() {
		try {
			modelo.clear();
			modelo2.clear();
			ArrayList<String> generosPreferidos = genreInterface.listarGenerosPreferidos(user.getUserName());
			ArrayList<Author> autoresPreferidos = authorInterface.listarAutoresPreferidos(user.getUserName());
			for (int i = 0; i < autoresPreferidos.size(); i++) {
				modelo.addElement(autoresPreferidos.get(i).getName() + " " + autoresPreferidos.get(i).getSurname());
			}
			for (int i = 0; i < generosPreferidos.size(); i++) {
				modelo2.addElement(generosPreferidos.get(i));
			}
			cbxAutores.removeAllItems();
			cbxGeneros.removeAllItems();
			cargarAutores();
			cargarGeneros();
		} catch (GestorException e) {
			e.printStackTrace();
		}
		menuInicio.crearTablaFavoritos(user);

	}

	// Eliminar preferencias personales de la base de datos
	private void borrarPreferencia(ArrayList<Author> autores, User user, ArrayList<String> generos) {
		int cambio, confirmacion, posicion, posicion2;

		confirmacion = JOptionPane.showConfirmDialog(this, "Quieres eliminar la preferencia seleccionada?", "Aviso", 0);
		posicion = listAutores.getSelectedIndex();
		posicion2 = listGeneros.getSelectedIndex();
		if (confirmacion == 0 && posicion != -1 || posicion2 != -1) {
			try {
				// Si solo se ha seleccionado la lista de los Autores
				if (posicion != -1 && posicion2 == -1) {
					cambio = authorInterface.borrarAutorPreferidos(autores.get(posicion).getSurname(),
							user.getUserName(), autores.get(posicion).getName());
					if (cambio == 1) {
						JOptionPane.showMessageDialog(this, "Se han modificado los cambios de Autores preferidos");
						refrescarPreferencias();
					}
					// Si solo se ha seleccionado la lista de los Generos
				} else if (posicion == -1) {
					cambio = genreInterface.borrarGenerosPreferidos(generos.get(posicion2), user.getUserName());
					if (cambio == 1) {
						JOptionPane.showMessageDialog(this, "Se han modificado los cambios de Generos preferidos");
						refrescarPreferencias();
					}
					// Si las dos listas han sido seleccionadas
				} else {
					int cambioAutor = authorInterface.borrarAutorPreferidos(autores.get(posicion).getSurname(),
							user.getUserName(), autores.get(posicion).getName());
					int cambioGenero = genreInterface.borrarGenerosPreferidos(generos.get(posicion2),
							user.getUserName());

					// Los cambios se efectuan correctamente
					if (cambioAutor == 1 && cambioGenero == 1) {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Autores y de Generos preferidos");
						refrescarPreferencias();
						// Los cambios solo se hacen en Autor
					} else if (cambioAutor == 1 && cambioGenero == 0) {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Autores preferidos, pero no de Generos");
						refrescarPreferencias();
						// Los cambios solo se hacen en Genero
					} else {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Generos preferidos, pero no de Autor");
						refrescarPreferencias();
					}
				}

			} catch (GestorException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarAutores() {
		ArrayList<Author> autores;
		boolean esta;
		try {
			autores = authorInterface.listarAutores();
			ArrayList<Author> autoresPref = authorInterface.listarAutoresPreferidos(user.getUserName());
			for (int i = 0; i < autores.size(); i++) {
				esta = false;
				for (int j = 0; j < autoresPref.size(); j++) {
					if (autoresPref.get(j).getCodAuthor().equals(autores.get(i).getCodAuthor())) {
						esta = true;
						j = autores.size();
					}

				}
				if (!esta) {
					cbxAutores.addItem(autores.get(i).getName() + " " + autores.get(i).getSurname());
				}
			}
			cbxAutores.setSelectedIndex(-1);

		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	private void cargarGeneros() {
		boolean esta;
		try {
			generosComboBox = genreInterface.listarGeneros();
			ArrayList<String> generosPref = genreInterface.listarGenerosPreferidos(user.getUserName());
			for (int i = 0; i < generosComboBox.size(); i++) {
				esta = false;
				for (int j = 0; j < generosPref.size(); j++) {
					if (generosPref.get(j).equals(generosComboBox.get(i))) {
						esta = true;
						j = generosPref.size();
					}
				}
				if (!esta) {
					cbxGeneros.addItem(generosComboBox.get(i));
				}

			}
			cbxGeneros.setSelectedIndex(-1);

		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	private void annadirPreferencia() {
		int cantidadAutores, cantidadGeneros, longitudCombobox;
		ArrayList<Author> autoresCombo;
		ArrayList<String> generosCombo;
		try {
			ArrayList<Author> autores = authorInterface.listarAutoresPreferidos(user.getUserName());
			ArrayList<String> generos = genreInterface.listarGenerosPreferidos(user.getUserName());
			cantidadAutores = autores.size();
			cantidadGeneros = generos.size();
			// Tiene el maximo de preferencias (3 autores y 3 generos)
			if (cantidadAutores == 3 && cantidadGeneros == 3) {
				JOptionPane.showMessageDialog(this,
						"No se pueden a�adir generos o autores, elimine preferencias actuales para poder a�adir nuevas");
				// Tiene el maximo de preferencias en generos, pero no en autores
			} else if (cantidadAutores < 3 && cantidadGeneros == 3) {
				JOptionPane.showMessageDialog(this,
						"Solo se han a�adido preferencias de autor, ya tienes 3 generos preferidos, no puedes a�adir mas, elimine preferencias actuales para poder a�adir nuevas");
				refrescarPreferencias();
				// Tiene el maximo de preferencias en autores, pero no en generos
			} else if (cantidadAutores == 3 && cantidadGeneros < 3) {
				JOptionPane.showMessageDialog(this,
						"Solo se han a�adido preferencias de genero, ya tienes 3 autores preferidos, no puedes a�adir mas, elimine preferencias actuales para poder a�adir nuevas");
				refrescarPreferencias();
			} else {
				autoresCombo = authorInterface.listarAutores();
				generosCombo = genreInterface.listarGeneros();
				if (cbxAutores.getSelectedIndex() == -1 && cbxGeneros.getSelectedIndex() != -1) {
					longitudCombobox = cantidadComboBoxGeneros();
					if (cbxGeneros.getSelectedIndex() == 0 && longitudCombobox > 1) {
						genreInterface.insertarGeneroPreferido(user.getUserName(),
								generosCombo.get(cbxGeneros.getSelectedIndex() + 1));
					} else {
						genreInterface.insertarGeneroPreferido(user.getUserName(),
								generosCombo.get(cbxGeneros.getSelectedIndex()));
					}

				} else if (cbxGeneros.getSelectedIndex() == -1 && cbxAutores.getSelectedIndex() != -1) {
					Author a = authorInterface.buscarAuthor(cbxAutores.getSelectedItem().toString()
							.substring(cbxAutores.getSelectedItem().toString().indexOf(" ") + 1));
					authorInterface.insertarAutorPreferido(user.getUserName(), a.getCodAuthor());
				} else {
					genreInterface.insertarGeneroPreferido(user.getUserName(), cbxGeneros.getSelectedItem().toString());
					authorInterface.insertarAutorPreferido(user.getUserName(),
							autoresCombo.get(cbxAutores.getSelectedIndex()).getCodAuthor());
				}
				refrescarPreferencias();
			}
		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	private void modificarDatos() {
		btnGuardarCambios.setEnabled(true);
		btnAnnadirPreferencia.setEnabled(true);
		btnBorrarPreferencias.setEnabled(true);
		txtNombre.setEditable(true);
		txtApellido.setEditable(true);
		txtContrasenna.setEditable(true);
		txtEmail.setEditable(true);
		txtDireccion.setEditable(true);
		txtTelefono.setEditable(true);
		txtNumCuenta.setEditable(true);
		cbxGeneros.setEnabled(true);
		cbxAutores.setEnabled(true);
	}

	private void guardarCambios(User user) {
		User userModificado;
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtContrasenna.setEditable(false);
		txtEmail.setEditable(false);
		txtDireccion.setEditable(false);
		txtTelefono.setEditable(false);
		txtNumCuenta.setEditable(false);
		btnGuardarCambios.setEnabled(false);
		btnAnnadirPreferencia.setEnabled(false);
		btnBorrarPreferencias.setEnabled(false);
		cbxAutores.setEnabled(false);
		cbxGeneros.setEnabled(false);

		userModificado = new Partner();
		userModificado.setUserName(user.getUserName());
		userModificado.setName(txtNombre.getText());
		userModificado.setSurname(txtApellido.getText());
		userModificado.setPassword(txtContrasenna.getText());
		userModificado.setEmail(txtEmail.getText());
		userModificado.setAddress(txtDireccion.getText());
		userModificado.setPhone(Integer.parseInt(txtTelefono.getText()));
		((Partner) userModificado).setNumAccount(Integer.parseInt(txtNumCuenta.getText()));

		try {
			int modificacion = userInterface.modificarUser(userModificado);

			if (modificacion == 1) {
				JOptionPane.showMessageDialog(this, "La modificacion se ha hecho satisfactoriamente");

			} else {
				JOptionPane.showMessageDialog(this, "Modificacion no realizada");
			}
		} catch (GestorException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<Compra> compras;
		try {
			compras = comprasInterface.historialCompras(user.getUserName());
			// Mostrar descripcion del libro
			if (e.getSource().equals(tableHistorialCompras)) {
				if (e.getClickCount() == 2) {
					if (tableHistorialCompras.getSelectedColumn() == 2) {
						JOptionPane.showMessageDialog(this,
								compras.get(tableHistorialCompras.getSelectedRow()).getAuthors(), "Autor/es de la obra",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} catch (GestorException e1) {
			e1.printStackTrace();
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

	public int cantidadComboBoxGeneros() {
		boolean esta;
		int cuantos = 0;
		try {
			generosComboBox = genreInterface.listarGeneros();
			ArrayList<String> generosPref = genreInterface.listarGenerosPreferidos(user.getUserName());
			for (int i = 0; i < generosComboBox.size(); i++) {
				esta = false;
				for (int j = 0; j < generosPref.size(); j++) {
					if (generosPref.get(j).equals(generosComboBox.get(i))) {
						esta = true;
						j = generosPref.size();
					}
				}
				if (!esta) {
					cuantos++;
				}

			}
		} catch (GestorException e) {
			e.printStackTrace();
		}
		return cuantos;
	}
}
