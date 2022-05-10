package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.Partner;
import tartanga.dami.equipoa.model.User;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class VMenuPerfil extends JPanel implements ActionListener {
	private JTextField txtNombre;
	private JTextField txtContrasenna;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtNumCuenta;
	private IUserController userInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;

	private JButton btnGuardarCambios;
	private JButton btnModificarDatos;
	private JButton btnAnnadirPreferencia;
	private JButton btnBorrarPreferencias;
	private JTextField txtApellido;
	private JScrollPane scrollPane;
	private JTable tableHistorialCompras;
	private JLabel lblAutor;
	private JLabel lblGenero;
	private JComboBox cbxGeneros;
	private JComboBox cbxAutores;
	private JList listGeneros;
	private JList listAutores;
	// Listas para el JList
	private ArrayList<Author> autores;
	private ArrayList<String> generos;
	private Partner user;

	public VMenuPerfil(IUserController userInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IComprasController comprasInterface, Partner user) {
		setLayout(null);
		this.userInterface = userInterface;
		this.authorInterface = authorInterface;
		this.user = user;
		this.genreInterface = genreInterface;

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
		lblNumCuenta.setBounds(32, 320, 106, 29);
		add(lblNumCuenta);

		JLabel lblNewLabel = new JLabel("Preferencias Personales");
		lblNewLabel.setBounds(107, 360, 118, 35);
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
		panelHistorialCompra.setBounds(506, 76, 370, 319);
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
		txtNumCuenta.setText(Integer.toString(user.getNumAccount()));
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
			DefaultListModel modelo = new DefaultListModel();
			for (int i = 0; i < autores.size(); i++) {
				modelo.addElement(autores.get(i).getName() + " " + autores.get(i).getSurname());
			}

			listAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAutores.setBounds(32, 406, 118, 148);
			listAutores.setModel(modelo);
			add(listAutores);

			// Inicializamos la Lista de Generos Preferidos
			listGeneros = new JList();
			DefaultListModel modelo2 = new DefaultListModel();
			for (int i = 0; i < generos.size(); i++) {
				modelo2.addElement(generos.get(i));
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
			add(cbxGeneros);

			lblGenero = new JLabel("A\u00F1adir Genero");
			lblGenero.setBounds(691, 447, 96, 17);
			add(lblGenero);

			cbxAutores = new JComboBox();
			cbxAutores.setBounds(351, 476, 158, 29);
			cargarAutores();
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
					matrizTabla[i][1] = compras.get(i).getNombreAutor() + " " + compras.get(i).getApellidoAutor();
					matrizTabla[i][2] = Integer.toString(compras.get(i).getIsbn());
					matrizTabla[i][3] = Integer.toString(compras.get(i).getCantidadLibros());
					matrizTabla[i][1] = Float.toString(compras.get(i).getPrecioCompra());
				}

				scrollPane = new JScrollPane();
				scrollPane.setBounds(506, 76, 370, 389);
				panelHistorialCompra.add(scrollPane);

				String titulos[] = { "Fecha", "Autor", "Isbn", "Cantidad", "Precio" };

				tableHistorialCompras = new JTable(matrizTabla, titulos);

				tableHistorialCompras.setSelectionBackground(new Color(0, 230, 168));
				tableHistorialCompras.setSelectionForeground(Color.WHITE);
				tableHistorialCompras.setRowMargin(0);
				tableHistorialCompras.setRowHeight(22);
				tableHistorialCompras.setShowVerticalLines(false);
				tableHistorialCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
				tableHistorialCompras.add(tableHistorialCompras);

				JTableHeader tableHeader = tableHistorialCompras.getTableHeader();
				tableHeader.setBackground(new Color(0, 191, 140));
				tableHeader.setForeground(Color.WHITE);
				tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
				tableHeader.setBorder(null);
				tableHeader.setEnabled(false);
			} else {
				// JOptionPane.showMessageDialog(this, "No hay preferencias personales");
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
			guardarCambios();
		}
		if (e.getSource().equals(btnAnnadirPreferencia)) {
			annadirPreferencia();
		}
		if (e.getSource().equals(btnBorrarPreferencias)) {
			borrarPreferencia(autores, user, generos);
		}

	}

	// Eliminar preferencias personales de la base de datos (JList se verá igual)
	private void borrarPreferencia(ArrayList<Author> autores, Partner user, ArrayList<String> generos) {
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
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Autores preferidos, en el siguiente inicio de sesion los cambios estaran actualizados");

					}
					// Si solo se ha seleccionado la lista de los Generos
				} else if (posicion == -1) {
					cambio = genreInterface.borrarGenerosPreferidos(generos.get(posicion2), user.getUserName());
					if (cambio == 1) {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Generos preferidos, en el siguiente inicio de sesion los cambios estaran actualizados");
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
								"Se han modificado los cambios de Autores y de Generos preferidos, en el siguiente inicio de sesion los cambios estaran actualizados");
						// Los cambios solo se hacen en Autor
					} else if (cambioAutor == 1 && cambioGenero == 0) {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Autores preferidos, pero no de Generos, en el siguiente inicio de sesion los cambios estaran actualizados");
						// Los cambios solo se hacen en Genero
					} else {
						JOptionPane.showMessageDialog(this,
								"Se han modificado los cambios de Generos preferidos, pero no de Autor, en el siguiente inicio de sesion los cambios estaran actualizados");
					}
				}

			} catch (GestorException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarAutores() {
		ArrayList<Author> autores;
		try {
			autores = authorInterface.listarAutores();
			for (int i = 0; i < autores.size(); i++) {
				cbxAutores.addItem(autores.get(i).getSurname());
			}
			cbxAutores.setSelectedIndex(-1);

		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	private void cargarGeneros() {
		ArrayList<String> generos;
		try {
			generos = genreInterface.listarGeneros();
			for (int i = 0; i < generos.size(); i++) {
				cbxGeneros.addItem(generos.get(i));
			}
			cbxAutores.setSelectedIndex(-1);

		} catch (GestorException e) {
			e.printStackTrace();
		}

	}

	private void annadirPreferencia() {
		int cantidadAutores, cantidadGeneros, cambioAutor, cambioGenero;
		ArrayList<Author> autoresCombo;
		try {
			ArrayList<Author> autores = authorInterface.listarAutoresPreferidos(user.getUserName());
			ArrayList<String> generos = genreInterface.listarGenerosPreferidos(user.getUserName());
			cantidadAutores = autores.size();
			cantidadGeneros = generos.size();
			// Tiene el maximo de preferencias (3 autores y 3 generos)
			if (cantidadAutores == 3 && cantidadGeneros == 3) {
				JOptionPane.showMessageDialog(this,
						"No se pueden añadir generos o autores, elimine preferencias actuales para poder añadir nuevas");
				// Tiene el maximo de preferencias en generos, pero no en autores
			} else if (cantidadAutores < 3 && cantidadGeneros == 3) {
				JOptionPane.showMessageDialog(this,
						"Solo se han añadido preferencias de autor, ya tienes 3 generos preferidos, no puedes añadir mas, elimine preferencias actuales para poder añadir nuevas");
				// Tiene el maximo de preferencias en autores, pero no en generos
			} else if (cantidadAutores == 3 && cantidadGeneros < 3) {
				JOptionPane.showMessageDialog(this,
						"Solo se han añadido preferencias de genero, ya tienes 3 autores preferidos, no puedes añadir mas, elimine preferencias actuales para poder añadir nuevas");
			} else {
				autoresCombo = authorInterface.listarAutores();
				cambioAutor = authorInterface.insertarAutorPreferido(user.getUserName(),
						autoresCombo.get(cbxAutores.getSelectedIndex()).getCodAuthor());
				cambioGenero = genreInterface.insertarGeneroPreferido(user.getUserName(),
						generos.get(cbxGeneros.getSelectedIndex()));
				if (cambioAutor == 1 && cambioGenero == 1) {
					JOptionPane.showMessageDialog(this,
							"Se han añadido tanto un autor como un genero favorito, en el siguiente inicio de sesion se actualizaran los datos");
				} else {
					JOptionPane.showMessageDialog(this, "No puedes introducir una preferencia que ya este introducida");
				}
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
	}

	private void guardarCambios() {
		User user;
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtContrasenna.setEditable(false);
		txtEmail.setEditable(false);
		txtDireccion.setEditable(false);
		txtTelefono.setEditable(false);
		txtNumCuenta.setEditable(false);

		user = new Partner();
		user.setName(txtNombre.getText());
		user.setSurname(txtApellido.getText());
		user.setPassword(txtContrasenna.getText());
		user.setEmail(txtEmail.getText());
		user.setAddress(txtDireccion.getText());
		user.setPhone(Integer.parseInt(txtTelefono.getText()));
		((Partner) user).setNumAccount(Integer.parseInt(txtNumCuenta.getText()));

		try {
			int modificacion = userInterface.modificarUser(user);

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
}
