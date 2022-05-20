package tartanga.dami.equipoa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;


import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.RowsRenderer;
import tartanga.dami.equipoa.model.User;

public class WCarrito extends JDialog implements ActionListener, MouseListener {
	/**
	 * @author Eneko
	 * Ventana que a traves de la lista compras genera una tabla y permite al usuario realizar la compra
	 */
	
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IComprasController comprasInterface;
	private IGenreController genreInterface;
	private ArrayList<Compra> compras;
	private JTable tablaCarrito;
	private JScrollPane scrollCarrito = new JScrollPane();
	private User user;
	private JButton bComprar;
	private JLabel lblVacio;
	private IUserController userInterface;
	private IConsultaController consultaInterface;
	private JButton bRegresar;
	private JLabel lblComprar;
	
	/**
	 * @param user el usuario que ha iniciado sesion
	 * @param bookInterface Interfaz de libro
	 * @param authorInterface Interfaz de autor
	 * @param genreInterface Intefaz de genero
	 * @param userInterface Interfaz de usuario
	 * @param comprasInterface Interfaz de compras
	 * @param consultaInterface interfaz de consultas 
	 * @param compras Lista de compras
	 */


	public WCarrito(IBookController bookInterface, IAuthorController authorInterface,
			IComprasController comprasInterface, ArrayList<Compra> compras, IGenreController genreInterface, User user,
		  IConsultaController consultaInterface) {
		setBounds(100, 100, 1047, 680);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);

		this.bookInterface = bookInterface;
		this.authorInterface = authorInterface;
		this.genreInterface = genreInterface;
		this.compras = compras;
		this.comprasInterface = comprasInterface;
		this.user = user;
		this.consultaInterface = consultaInterface;
		
		crearTablaCompras(bookInterface, genreInterface, compras);

		bRegresar = new JButton();
		bRegresar.setBounds(850, 30, 125, 55);
		bRegresar.setText("Regresar");
		bRegresar.addActionListener(this);
		this.add(bRegresar);
		
		lblComprar = new JLabel();
		lblComprar.setBounds(430, 450, 125, 75);
		getContentPane().add(lblComprar);
		lblComprar.setText("Comprar");
		lblComprar.setBackground(Color.white);;
		
		bComprar = new JButton();
		bComprar.setBounds(420, 500, 125, 75);
		getContentPane().add(bComprar);
		bComprar.setIcon(new ImageIcon(WMenu.class.getResource("/tartanga/dami/equipoa/resources/ejecutarCompra.png")));
		bComprar.addActionListener(this);
		if(compras.size()<=0) {
			bComprar.setEnabled(false);
		}

	}

	/**
	 * Meotodo que crea la tabla de compras, en el caso de que la lista de compras este vacio en su lugar sale un icono 
	 */
	private void crearTablaCompras(IBookController bookInterface, IGenreController genreInterface,
			ArrayList<Compra> compras) {
		if (compras.size() > 0) {
			String autores = "";
			String genres = "";
			String matrizTabla[][] = new String[compras.size()][5];
			for (int i = 0; i < compras.size(); i++) {
				Book book;
				try {
					book = bookInterface.buscarBook(compras.get(i).getIsbn());

					autores = bookInterface.listAuthorsIsbn(book.getIsbn());

					genres = genreInterface.listarGenresIsbn(book.getIsbn());

					matrizTabla[i][0] = book.getTitle();
					matrizTabla[i][1] = autores;
					matrizTabla[i][2] = genres;
					matrizTabla[i][3] = Integer.toString(compras.get(i).getCantidadLibros());
					float precioTotal = compras.get(i).getCantidadLibros()
							* comprasInterface.calcularPrecio(book.getIsbn());
					matrizTabla[i][4] = Float.toString(precioTotal);

					scrollCarrito.setBounds(50, 100, 900, 325);
					this.add(scrollCarrito);

					String[] columNames = { "Titulo", "Autor(es)", "Genero(s)", "Cantidad", "Precio" };
					tablaCarrito = new JTable(matrizTabla, columNames) {
						public boolean isCellEditable(int row, int column) {
							for (int i = 0; i < tablaCarrito.getRowCount(); i++) {
								if (row == i) {
									return false;
								}
							}
							return true;
						}
					};

					RowsRenderer rRowsRenderer = new RowsRenderer(4);
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					tablaCarrito.setDefaultRenderer(Object.class, rRowsRenderer);
					tablaCarrito.isCellEditable(compras.size(), 4);

					tablaCarrito.setSelectionBackground(new Color(0, 230, 168));
					tablaCarrito.setSelectionForeground(Color.WHITE);
					tablaCarrito.setRowMargin(0);
					tablaCarrito.setRowHeight(50);
					tablaCarrito.setShowHorizontalLines(true);
					tablaCarrito.setShowVerticalLines(true);
					scrollCarrito.setViewportView(tablaCarrito);
					tablaCarrito.addMouseListener(this);

				} catch (GestorException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					
				}

			}
		} else {
			lblVacio = new JLabel();
			lblVacio.setIcon(
					new ImageIcon(WMenu.class.getResource("/tartanga/dami/equipoa/resources/carritoVacioIcono.png")));
			lblVacio.setBounds(350, 100, 900, 325);
			this.add(lblVacio);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 *Compra todos los libros que haya en la tabla
		 */
		if (e.getSource().equals(bComprar)) {
			for (int i = 0; i < compras.size(); i++) {
				try {
					comprasInterface.escribirCompra(compras.get(i), user.getUserName());
				} catch (GestorException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			int seleccion = JOptionPane.showConfirmDialog(this, "Estas seguro de que quieres confirmar la compra?",
					"Aviso", 0);
			if (seleccion == 0) {
				compras.clear();
				crearTablaCompras(bookInterface, genreInterface, compras);
				bComprar.setEnabled(false);
				WMenu wMenu = new WMenu(userInterface, authorInterface, genreInterface, bookInterface, comprasInterface,
						user, consultaInterface);
				wMenu.setVisible(true);
				this.dispose();
			}
		}
		/**
		 * Regresa a la ventana menu inicio
		 */
		if(e.getSource().equals(bRegresar)) {
			this.dispose();
			WMenu wMenu = new WMenu(userInterface, authorInterface, genreInterface, bookInterface, comprasInterface,
					user, consultaInterface, compras);
			wMenu.setVisible(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * Elimina una compra de la lista
		 */
		if (e.getSource().equals(tablaCarrito)) {
			int seleccion = JOptionPane.showConfirmDialog(this,
					"Estas seguro que quieres eliminar el libro del carrito?", "Aviso", 0);
			if (seleccion == 0) {
				int cual = tablaCarrito.getSelectedRow();
				compras.remove(cual);
				crearTablaCompras(bookInterface, genreInterface, compras);
				if (compras.size() <= 0) {
					WMenu wMenu = new WMenu(userInterface, authorInterface, genreInterface, bookInterface,
							comprasInterface, user, consultaInterface);

					wMenu.setVisible(true);
					this.dispose();
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
