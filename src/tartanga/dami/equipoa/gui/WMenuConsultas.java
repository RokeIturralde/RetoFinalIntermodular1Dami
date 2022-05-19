package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.KeyEvent;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.mxrck.autocompleter.TextAutoCompleter;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Consulta;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class WMenuConsultas extends JPanel implements ActionListener {
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
	private JScrollPane scrollPane;

	public WMenuConsultas(IGenreController genreInterface, IBookController bookInterface,
			IConsultaController consultaInterface, IAuthorController authorInterface) {
		setLayout(null);
		this.genreInterface = genreInterface;
		this.bookInterface = bookInterface;
		this.consultaInterface = consultaInterface;
		this.authorInterface = authorInterface;
		JLabel lblInformacion = new JLabel("Busca informacion en la base de datos:");
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacion.setBounds(10, 21, 223, 52);
		add(lblInformacion);

		JLabel lblAgrupar = new JLabel("Filtrar Automatico");
		lblAgrupar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAgrupar.setBounds(10, 59, 183, 26);
		add(lblAgrupar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(221, 117, 109, 41);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			boolean autorEsta = false;
			ArrayList<Consulta> consultas;
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
					consultas = consultaInterface
							.tablaConsulta(txteditorComp.getText().substring(txteditorComp.getText().indexOf(" ") + 1));
				} else {
					consultas = consultaInterface.tablaConsulta(txteditorComp.getText());
				}
				// Si no escribe nada, aparecen todos los datos en la table
				if (txteditorComp.getText().equalsIgnoreCase("")) {
					consultas = consultaInterface.listarTodosLosDatos();
				}

				txteditorComp.setText("");
				txteditorComp.setEditable(true);

				if (consultas.size() > 0) {
					String matrizTabla[][] = new String[consultas.size()][6];
					for (int i = 0; i < consultas.size(); i++) {
						matrizTabla[i][0] = consultas.get(i).getTitulo();
						matrizTabla[i][1] = consultas.get(i).getAutores();
						matrizTabla[i][2] = consultas.get(i).getFechaNacim().toString();
						matrizTabla[i][3] = consultas.get(i).getDescription();
						matrizTabla[i][4] = consultas.get(i).getGeneros();
						matrizTabla[i][5] = Float.toString(consultas.get(i).getPrecio());
					}

					String titulos[] = { "Titulo", "Autor/es", "Fecha Publicacion", "Descripcion", "Genero/s",
							"Precio" };

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

					scrollPane = new JScrollPane();
					scrollPane.setBounds(396, 67, 461, 528);
					this.add(scrollPane);

					tableConsultas.setSelectionBackground(new Color(0, 230, 168));
					tableConsultas.setSelectionForeground(Color.WHITE);
					tableConsultas.setRowMargin(0);
					tableConsultas.setRowHeight(22);
					tableConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableConsultas.setFont(new Font("Tahoma", Font.PLAIN, 12));
					scrollPane.setViewportView(tableConsultas);

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
				e1.printStackTrace();
			}
		}

	}
}
