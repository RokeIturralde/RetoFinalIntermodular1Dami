package tartanga.dami.equipoa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Author;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.User;

public class WCarrito extends JDialog implements ActionListener{
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private IComprasController comprasInterface;
	private IGenreController genreInterface;
	private ArrayList<Compra> compras;
	private JTable tablaCarrito;
	private JScrollPane scrollCarrito;
	private User user;
	private JButton bComprar;

	public WCarrito(IBookController bookInterface, IAuthorController authorInterface, IComprasController comprasInterface, ArrayList<Compra> compras, IGenreController genreInterface, User user) {
		setBounds(100, 100, 1047, 680);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		this.bookInterface = bookInterface;
		this.authorInterface = authorInterface;
		this.genreInterface = genreInterface;
		this.compras = compras;
		this.comprasInterface = comprasInterface;
		String nombreApellidos = "";
		String genres = "";
		


			String autores="";
			String matrizTabla[][] = new String[compras.size()][5];
			for(int i=0; i<compras.size();i++) {
				Book book;
				
				try {
					book = bookInterface.buscarBook(compras.get(i).getIsbn());
				
					autores = bookInterface.listAuthorsIsbn(book.getIsbn());
					
					genres = genreInterface.listarGenresIsbn(book.getIsbn());
					
					matrizTabla[i][0] = book.getTitle();
					matrizTabla[i][1] = autores;
					matrizTabla[i][2] = genres;
					matrizTabla[i][3] = Integer.toString(compras.get(i).getCantidadLibros());
					float precioTotal = compras.get(i).getCantidadLibros()*comprasInterface.calcularPrecio(book.getIsbn());
					matrizTabla[i][4] = Float.toString(precioTotal);
					
					scrollCarrito = new JScrollPane();
					scrollCarrito.setBounds(25, 100, 420, 325);
					this.add(scrollCarrito);

					String[] columNames = { "Titulo", "Autor(es)", "Genero(s)", "Cantidad", "Precio"};

				} catch (GestorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			//Mostrar mensaje de carrito vacio
		}
		
		JButton bComprar = new JButton();
		bComprar.addActionListener(this);
		bComprar.setIcon(new ImageIcon(WMenu.class.getResource("/tartanga/dami/equipoa/resources/carritoVacioIcono.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bComprar)) {
			for(int i = 0; i<compras.size();i++) {
				try {
					comprasInterface.escribirCompra(compras.get(i), user.getUserName());
				} catch (GestorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}


}
