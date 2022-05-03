package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.table.JTableHeader;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
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

public class VMenuPerfil extends JPanel implements ActionListener {
	private JTextField txtNombre;
	private JTextField txtContrasenna;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtNumCuenta;
	private JTable tablePreferenciasPersonales;
	private JTable tableHistorialCompras;
	private Partner user;
	private Author autor;
	private IUserController userInterface;
	private IAuthorController authorInterface;
	private IGenreController genreInterface;
	private IComprasController comprasInterface;
	private ArrayList<Partner> partnerList = new ArrayList<Partner>();
	private ArrayList<Author> authorList = new ArrayList<Author>();
	private ArrayList<Genre> genreList = new ArrayList<Genre>();
	private ArrayList<Book> bookList = new ArrayList<Book>();

	JButton btnGuardarCambios;
	JButton btnModificarDatos;
	private JLabel lblApellido;
	private JTextField txtApellido;

	public VMenuPerfil(IUserController userInterface, IAuthorController authorInterface, IGenreController genreInerface,
			IComprasController comprasInterface, Partner user) {
		setLayout(null);
		this.user = user;
		this.userInterface = userInterface;
		this.genreInterface = genreInerface;
		this.authorInterface = authorInterface;
		this.comprasInterface = comprasInterface;
		btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBounds(32, 25, 118, 35);
		add(btnModificarDatos);
		btnModificarDatos.addActionListener(this);

		btnGuardarCambios = new JButton("GuardarCambios");
		btnGuardarCambios.setBounds(205, 25, 118, 35);
		add(btnGuardarCambios);
		btnGuardarCambios.setEnabled(false);

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
		lblNewLabel.setBounds(136, 383, 118, 35);
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

		tablePreferenciasPersonales = new JTable();
		tablePreferenciasPersonales.setBounds(32, 417, 319, 187);
		add(tablePreferenciasPersonales);

		tableHistorialCompras = new JTable();
		tableHistorialCompras.setBounds(525, 94, 319, 382);
		add(tableHistorialCompras);

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

		// Creacion de la tabla de Autores y Generos preferidos
		try {
			ArrayList<Author> idAutores = authorInterface.listarAutoresPreferidos(user.getUserName());
			ArrayList<Genre> generos = genreInerface.listarGenerosPreferidos(user.getUserName());
			if (idAutores.size() > 0 || generos.size() > 0) {
				String matrizTabla[][] = new String[3][2];
				for (int i = 0; i < 3; i++) {
					// Carga los datos de ese autor
					autor = authorInterface.buscarAuthor(idAutores.get(i).getCodAuthor());
					if (i == 0) {
						matrizTabla[0][0] = autor.getName() + " " + autor.getSurname();
						matrizTabla[0][1] = generos.get(i).getGenreName();
					}
					if (i == 1) {
						matrizTabla[1][0] = autor.getName() + " " + autor.getSurname();
						matrizTabla[1][1] = generos.get(i).getGenreName();
					}
					if (i == 2) {
						matrizTabla[2][0] = autor.getName() + " " + autor.getSurname();
						matrizTabla[2][1] = generos.get(i).getGenreName();
					}

				}

				String titulos[] = { "Autores", "Generos" };

				tablePreferenciasPersonales = new JTable(matrizTabla, titulos);

				tablePreferenciasPersonales.setSelectionBackground(new Color(0, 230, 168));
				tablePreferenciasPersonales.setSelectionForeground(Color.WHITE);
				tablePreferenciasPersonales.setRowMargin(0);
				tablePreferenciasPersonales.setRowHeight(22);
				tablePreferenciasPersonales.setShowVerticalLines(false);
				tablePreferenciasPersonales.setFont(new Font("Tahoma", Font.PLAIN, 12));
				tablePreferenciasPersonales.add(tablePreferenciasPersonales);

				JTableHeader tableHeader = tablePreferenciasPersonales.getTableHeader();
				tableHeader.setBackground(new Color(0, 191, 140));
				tableHeader.setForeground(Color.WHITE);
				tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
				tableHeader.setBorder(null);
				tableHeader.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(this, "No hay preferencias personales");
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
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
				JOptionPane.showMessageDialog(this, "No hay preferencias personales");
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnModificarDatos)) {
			modificar();
		}
		if (e.getSource().equals(btnGuardarCambios)) {
			guardar();
		}

	}

	private void modificar() {
		btnGuardarCambios.setEnabled(true);
		txtNombre.setEditable(true);
		txtApellido.setEditable(true);
		txtContrasenna.setEditable(true);
		txtEmail.setEditable(true);
		txtDireccion.setEditable(true);
		txtTelefono.setEditable(true);
		txtNumCuenta.setEditable(true);
	}

	private void guardar() {
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
