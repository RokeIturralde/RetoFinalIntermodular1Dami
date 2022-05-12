package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import tartanga.dami.equipoa.dataAccess.IAuthorBookController;
import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.AuthorBook;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.ModeloTabla;
import tartanga.dami.equipoa.model.RowsRenderer;
import tartanga.dami.equipoa.model.User;

import javax.swing.JTable;
import javax.swing.JButton;

public class WMenuInicio extends JPanel implements MouseListener {
	private JTable soloParaTi;
	private IUserController userInterface;
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private User user;
	private JTable tableFav;
	private ArrayList<AuthorBook> listLikedBooks;
	private JTable tableSales;
	private JScrollPane scrollFav;
	private JScrollPane scrollSellers;
	private ArrayList<Integer> bookSales;
	private ArrayList<Book> libros;
	private ArrayList<Compra> compras;

	public WMenuInicio(IUserController userInterface, IBookController bookInterface, IAuthorController authorInterface,
			User user, IAuthorBookController authorBookInterface, ArrayList<Compra> compras) {
		setLayout(null);

		setBounds(100, 300, 520, 12);

		JLabel lblNewLabel = new JLabel("Solo para ti");
		lblNewLabel.setBounds(190, 67, 135, 14);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Best sellers");
		lblNewLabel_1.setBounds(580, 67, 181, 14);
		this.add(lblNewLabel_1);

		JTextArea txtrPromociones = new JTextArea();
		txtrPromociones.setWrapStyleWord(true);
		txtrPromociones.setEditable(false);
		txtrPromociones.setLineWrap(true);
		txtrPromociones.setRows(2);
		txtrPromociones.setText(
				"Comprar libros en Irakurle tiene numerosas ventajas como m\u00EDnimo env\u00EDo gratis en un d\u00EDa desde 19\u20AC en nuestros libros, numerosas formas de env\u00EDo como env\u00EDo en 24 horas, posibilidad de recogerlo en tienda en alguna de nuestras m\u00E1s de 46 librer\u00EDas y numerosos ofertas , descuentos y promociones.");
		txtrPromociones.setBounds(200, 442, 457, 117);
		this.add(txtrPromociones);

		// Tabla de preferencias personales
		try {
			listLikedBooks = authorBookInterface.listAuthorBook(user.getUserName());
		} catch (GestorException e) {
			e.printStackTrace();
		}

		if (listLikedBooks.size() > 0) {
			String matrizTabla[][] = new String[listLikedBooks.size()][4];
			for (int i = 0; i < listLikedBooks.size(); i++) {
				matrizTabla[i][0] = listLikedBooks.get(i).getTitle();
				matrizTabla[i][1] = listLikedBooks.get(i).getName() + listLikedBooks.get(i).getSurname();
				matrizTabla[i][2] = listLikedBooks.get(i).getDescription();
				matrizTabla[i][3] = Float.toString(listLikedBooks.get(i).getPrice());
			}

			scrollFav = new JScrollPane();
			scrollFav.setBounds(25, 100, 420, 325);
			
			this.add(scrollFav);

			String[] columNames = { "Titulo", "Autor", "Descripcion", "Precio" };

			// Estilo de la tabla
			tableFav = new JTable(matrizTabla, columNames) {
				/*
				 * 
				 */
				private static final long serialVersionUID = 1L;

				// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
				// HACER DOBLE CLICK************************************
				// Para ello sobreescribimos el metodo que ya tiene la clase
				// JTable.isCellEditable
				public boolean isCellEditable(int row, int column) {
					for (int i = 0; i < tableFav.getRowCount(); i++) {
						if (row == i) {
							return false;
						}
					}
					return true;
				}
			};

			RowsRenderer rRowsRenderer = new RowsRenderer(3);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			tableFav.setDefaultRenderer(Object.class, rRowsRenderer);
			tableFav.isCellEditable(listLikedBooks.size(), 4);

			tableFav.setSelectionBackground(new Color(0, 230, 168));
			tableFav.setSelectionForeground(Color.WHITE);
			tableFav.setRowMargin(0);
			tableFav.setRowHeight(70);
			tableFav.setShowHorizontalLines(true);
			tableFav.setShowVerticalLines(true);
			scrollFav.setViewportView(tableFav);

			tableFav.addMouseListener(this);

			// Estilo del header
			JTableHeader tableHeader = tableFav.getTableHeader();
			tableHeader.setBackground(new Color(0, 191, 140));
			tableHeader.setForeground(Color.WHITE);
			tableHeader.setBorder(null);
			tableHeader.setEnabled(false);

		} else {
			JOptionPane.showMessageDialog(this, "Usuario sin libros");
		}

		// Tabla de Best Sellers

		Book book;
		try {
			bookSales = bookInterface.listTopSales();
		}catch (GestorException e) {
				JOptionPane.showMessageDialog(this, "Error al cargar la tabla", "Aviso", 0);
				e.printStackTrace();
			}
		
		libros = new ArrayList<>();
		ArrayList<Integer> ventas = new ArrayList<>();
		System.out.println(bookSales.size());
		for (int i = 0; i < bookSales.size(); i++) {
			if (i%2 == 0) {
				try {
					libros.add(bookInterface.buscarBook(bookSales.get(i)));
				} catch (GestorException e) {
				e.printStackTrace();
				}
			} else {
				ventas.add(bookSales.get(i));
			}
		}

			String matrizTablaSales[][] = new String[ventas.size()][5];
			for (int i = 0; i < libros.size(); i++) {
				matrizTablaSales[i][0] = Integer.toString(i + 1) + " " + ventas.get(i);
				matrizTablaSales[i][1] = libros.get(i).getTitle();
				matrizTablaSales[i][2] = libros.get(i).getAuthors().toString();
				matrizTablaSales[i][3] = libros.get(i).getDescription();
				matrizTablaSales[i][4] = "comprar";
			}
			
			String[] columNames = { "Posicion", "Titulo", "Autor", "Descripcion", "¿Te interesa?" };
			
			tableSales = new JTable(matrizTablaSales, columNames) {
				
					/*
					 * 
					 */
					private static final long serialVersionUID = 1L;

					// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
					// HACER DOBLE CLICK************************************
					// Para ello sobreescribimos el metodo que ya tiene la clase
					// JTable.isCellEditable
					public boolean isCellEditable(int row, int column) {
						for (int i = 0; i < tableFav.getRowCount(); i++) {
							if (row == i) {
								return false;
							}
						}
						return true;
					}
				};
	
	scrollSellers=new JScrollPane();
	scrollSellers.setBounds(470,100,520,325);
	
	this.add(scrollSellers);
	

	RowsRenderer rRowsRenderer = new RowsRenderer(3);
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	tableSales.setDefaultRenderer(Object.class,rRowsRenderer);
	tableSales.isCellEditable(listLikedBooks.size(),4);

	tableSales.setSelectionBackground(new Color(0,230,168));
	tableSales.setSelectionForeground(Color.WHITE);
	tableSales.setRowMargin(0);
	tableSales.setRowHeight(20);
	tableSales.setShowHorizontalLines(true);
	tableSales.setShowVerticalLines(true);
	scrollSellers.setViewportView(tableSales);

	tableSales.addMouseListener(this);

	// Estilo del header
	JTableHeader tableHeader = tableSales
			.getTableHeader();tableHeader.setBackground(new Color(0,191,140));tableHeader.setForeground(Color.WHITE);tableHeader.setBorder(null);tableHeader.setEnabled(false);

	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		//Mostrar descripcion del libro
		if (e.getSource().equals(tableSales)) {
			if (e.getClickCount() == 2) {
				if (tableSales.getSelectedColumn() == 3) {
					int cual = tableSales.getSelectedRow();
					JOptionPane.showMessageDialog(this, listLikedBooks.get(cual).getDescription(),
							"Descripcion de la obra", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		if (e.getSource().equals(tableFav)) {
			if(e.getClickCount()==2) {
				if(tableFav.getSelectedColumn()==2) {
					int cual = tableFav.getSelectedRow();
					JOptionPane.showMessageDialog(this, libros.get(cual).getDescription(), 
					"Descripcion de la obra", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//Anadir libro al carrito
		if(e.getSource().equals(tableSales)){
			if(tableSales.getSelectedColumn()==4) {
				int cual = tableFav.getSelectedRow();
				int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el numero de ejemplares que deseas comprar", "Confirma la compra", JOptionPane.PLAIN_MESSAGE));
				Book book = libros.get(cual);
				Compra compra = new Compra();
				compra.setIsbn(book.getIsbn());
				compra.setCantidadLibros(1);
				compra.setPrecioCompra(book.getPrice());
				compra.setCantidadLibros(cantidad);
				compras.add(compra);
			}
		}
		
		

	}
	
	public ArrayList<Compra> enviarCompras(){
			return compras; 
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
