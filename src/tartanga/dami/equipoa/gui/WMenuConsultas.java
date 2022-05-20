package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.KeyEvent;
import com.mxrck.autocompleter.TextAutoCompleter;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.Consulta;
import tartanga.dami.equipoa.model.RowsRenderer;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class WMenuConsultas extends JPanel implements ActionListener, MouseListener {
	private JButton btnBuscar;
	private IGenreController genreInterface;
	private IBookController bookInterface;
	private IConsultaController consultaInterface;
	private IAuthorController authorInterface;
	private JComboBox cbxBusqueda;
	private JTextField txteditorComp;
	private ArrayList<String> listadoConsulta;
	private ArrayList<Author> autores;
	private JTable tableConsultas;
	private JScrollPane scrollPane = new JScrollPane();
	private ArrayList<Book> listLikedBooks;
	private ArrayList<Compra> compras;
	private ArrayList<Consulta> consultas;

	public WMenuConsultas(IGenreController genreInterface, IBookController bookInterface,
			IConsultaController consultaInterface, IAuthorController authorInterface, ArrayList<Compra> compras) {
		setLayout(null);
		this.genreInterface = genreInterface;
		this.bookInterface = bookInterface;
		this.consultaInterface = consultaInterface;
		this.authorInterface = authorInterface;
		this.listLikedBooks = listLikedBooks;
		this.compras = compras;
		
		JLabel lblInformacion = new JLabel("Busca informacion en la base de datos:");
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacion.setBounds(10, 21, 223, 52);
		add(lblInformacion);

		JLabel lblAgrupar = new JLabel("Filtrar Automatico");
		lblAgrupar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAgrupar.setBounds(10, 59, 183, 26);
		add(lblAgrupar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(260, 54, 109, 41);
		add(btnBuscar);
		btnBuscar.addActionListener(this);

		txteditorComp = new JTextField();
		txteditorComp.setBounds(10, 124, 201, 26);
		add(txteditorComp);
		txteditorComp.setColumns(10);

		TextAutoCompleter textAutoCompletado = new TextAutoCompleter(txteditorComp);
		textAutoCompletado.setMode(0);
		try {
			listadoConsulta = bookInterface.listarConsulta();
			for (int i = 0; i < listadoConsulta.size(); i++) {
				textAutoCompletado.addItem(listadoConsulta.get(i));
			}

		} catch (GestorException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			boolean autorEsta = false;
			try {
				autores = authorInterface.listarAutores();
				for (int i = 0; i < autores.size(); i++) {
					if ((autores.get(i).getName() + " " + autores.get(i).getSurname())
							.equalsIgnoreCase(txteditorComp.getText())) {
						autorEsta = true;
						i = autores.size();
					}
				}

				if (autorEsta) {
					consultas = consultaInterface.tablaConsulta(txteditorComp.getText().substring(txteditorComp.getText().indexOf(" ") + 1));
				} else {
					consultas = consultaInterface.tablaConsulta(txteditorComp.getText());
				}
				// Si no escribe nada, aparecen todos los datos en la table
				if (txteditorComp.getText().equalsIgnoreCase("")) {
					consultas = consultaInterface.listarTodosLosDatos();
				}

				txteditorComp.setText("");
				txteditorComp.setEditable(true);

				String matrizTabla[][] = null;
				if (tableConsultas != null) {
					cleanTableConsultas();
				}
				if (consultas.size() > 0) {
					matrizTabla = new String[consultas.size()][6];
					for (int i = 0; i < consultas.size(); i++) {
						matrizTabla[i][0] = consultas.get(i).getTitulo();
						matrizTabla[i][1] = consultas.get(i).getAutores();
						matrizTabla[i][2] = consultas.get(i).getFechaNacim().toString();
						matrizTabla[i][3] = consultas.get(i).getDescription();
						matrizTabla[i][4] = consultas.get(i).getGeneros();
						matrizTabla[i][5] = Float.toString(consultas.get(i).getPrecio());
					}
					String titulos[] = { "Titulo", "Autor/es", "Fecha Publicacion", "Descripcion", "Genero/s",
							"Precio/Te Interesa?" };
					tableConsultas = new JTable(matrizTabla, titulos) {
						/*
						 * 
						 */
						private static final long serialVersionUID = 1L;

						// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
						// HACER DOBLE CLICK************************************
						// Para ello sobreescribimos el metodo que ya tiene la clase
						// JTable.isCellEditable
						public boolean isCellEditable(int row, int column) {
							for (int i = 0; i < tableConsultas.getRowCount(); i++) {
								if (row == i) {
									return false;
								}
							}
							return true;
						}
					};

					
					scrollPane.setBounds(33, 188, 890, 401);
					this.add(scrollPane);

					RowsRenderer rRowsRenderConsultas = new RowsRenderer(5);
					DefaultTableCellRenderer centerRenderConsultas = new DefaultTableCellRenderer();
					tableConsultas.setDefaultRenderer(Object.class, rRowsRenderConsultas);
					tableConsultas.getColumnModel().getColumn(0).setCellRenderer(centerRenderConsultas);
					tableConsultas.isCellEditable(consultas.size(), 6);
					tableConsultas.setSelectionBackground(new Color(0, 230, 168));
					tableConsultas.setSelectionForeground(Color.WHITE);
					tableConsultas.setRowMargin(0);
					tableConsultas.setRowHeight(22);
					tableConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableConsultas.setFont(new Font("Tahoma", Font.PLAIN, 12));
					scrollPane.setViewportView(tableConsultas);
					tableConsultas.addMouseListener(this);
					centerRenderConsultas.setHorizontalAlignment(JLabel.CENTER);

					JTableHeader tableHeader = tableConsultas.getTableHeader();
					tableHeader.setBackground(new Color(0, 191, 140));
					tableHeader.setForeground(Color.WHITE);
					tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
					tableHeader.setBorder(null);
					tableHeader.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(this, "No se han introducido datos validos de busqueda");
				}
			} catch (GestorException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void cleanTableConsultas() {
		String titulos[] = { "Titulo", "Autor/es", "Fecha Publicacion", "Descripcion", "Genero/s",
				"Precio/Te Interesa?" };
		tableConsultas = new JTable(null, titulos) {
			/*
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
			// HACER DOBLE CLICK************************************
			// Para ello sobreescribimos el metodo que ya tiene la clase
			// JTable.isCellEditable
			public boolean isCellEditable(int row, int column) {
				for (int i = 0; i < tableConsultas.getRowCount(); i++) {
					if (row == i) {
						return false;
					}
				}
				return true;
			}
		};
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tableConsultas)) {
			if (tableConsultas.getSelectedColumn() == 5) {

				int cual = tableConsultas.getSelectedRow();
				String canti = null;
				int cantidad;
				boolean repetido = false;
				try {
					Book book = bookInterface.buscarBook(consultas.get(cual).getIsbn());
					canti = String.valueOf(JOptionPane.showInputDialog(null,
							"Introduce el numero de ejemplares que deseas comprar. Stock: " + book.getStock(),
							"Confirma la compra", JOptionPane.PLAIN_MESSAGE));
					cantidad = Integer.parseInt(canti);

					for (int i = 0; i < compras.size(); i++) {
						if (compras.get(i).getIsbn() == book.getIsbn()) {
							compras.get(i).setCantidadLibros(compras.get(i).getCantidadLibros() + cantidad);
							if (compras.get(i).getCantidadLibros() > book.getStock()) {
								JOptionPane.showMessageDialog(this, "No hay suficiente stock", "Error",
										JOptionPane.INFORMATION_MESSAGE);
								compras.get(i).setCantidadLibros(compras.get(i).getCantidadLibros() - cantidad);
							}
							repetido = true;
							i = compras.size();
						}
					}
					if (cantidad > 0 && cantidad <= book.getStock() && repetido == false) {
						Compra compra = new Compra();
						compra.setIsbn(book.getIsbn());
						compra.setCantidadLibros(cantidad);
						compra.setPrecioCompra(book.getPrice());
						compras.add(compra);
					}
				} catch (NumberFormatException a) {
					if (!canti.isEmpty())
						JOptionPane.showMessageDialog(this, "En este campo solo se pueden introducir numeros", "Error",
								JOptionPane.ERROR_MESSAGE);
				} catch (GestorException i) {
					JOptionPane.showMessageDialog(this, i.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

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
}
