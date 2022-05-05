package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
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
import tartanga.dami.equipoa.model.ModeloTabla;
import tartanga.dami.equipoa.model.User;

import javax.swing.JTable;
import javax.swing.JButton;

public class WMenuInicio extends JPanel {
	private JTable soloParaTi;
	private IUserController userInterface;
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private User user;
	private JTable tablaFav;
	private ArrayList<AuthorBook> listLikedBooks;
	private JTable tableFav;
	private JScrollPane scrollFav;
	
	public WMenuInicio(IUserController userInterface, IBookController bookInterface, IAuthorController authorInterface, User user, IAuthorBookController authorBookInterface) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Solo para ti");
		lblNewLabel.setBounds(129, 67, 54, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Best sellers");
		lblNewLabel_1.setBounds(658, 67, 54, 14);
		this.add(lblNewLabel_1);
		
		JTextArea txtrPromociones = new JTextArea();
		txtrPromociones.setEditable(false);
		txtrPromociones.setLineWrap(true);
		txtrPromociones.setRows(2);
		txtrPromociones.setText("Comprar libros en Irakurle tiene numerosas ventajas como m\u00EDnimo env\u00EDo gratis en un d\u00EDa desde 19\u20AC en nuestros libros, numerosas formas de env\u00EDo como env\u00EDo en 24 horas, posibilidad de recogerlo en tienda en alguna de nuestras m\u00E1s de 46 librer\u00EDas y numerosos ofertas , descuentos y promociones.");
		txtrPromociones.setBounds(200, 442, 457, 117);
		this.add(txtrPromociones);
		
		//Tabla de preferencias personales
		
				try {
					listLikedBooks = authorBookInterface.listAuthorBook(user.getUserName());
				} catch (GestorException e) {
					e.printStackTrace();
				}
					
				if(listLikedBooks.size()>0) {
					String matrizTabla[][] = new String[listLikedBooks.size()][4];
					for(int i=0; i<listLikedBooks.size();i++) {
						matrizTabla[i][0]=listLikedBooks.get(i).getTitle();
						matrizTabla[i][1]= listLikedBooks.get(i).getName()+listLikedBooks.get(i).getSurname();
						matrizTabla[i][2]= listLikedBooks.get(i).getDescription();
						matrizTabla[i][3]=Float.toString(listLikedBooks.get(i).getPrice());
					}
					
					scrollFav = new JScrollPane();
					scrollFav.setBounds(25, 209, 583, 125);
					this.add(scrollFav);
					
					String[] columNames = {
							"Titulo",
							"Autor",
							"Descripcion",
							"Precio"};
					
					//Estilo de la tabla
					tableFav = new JTable(matrizTabla, columNames);
					tableFav.setSelectionBackground(new Color(0, 230, 168));
					tableFav.setSelectionForeground(Color.WHITE);
					tableFav.setRowMargin(0);
					tableFav.setRowHeight(20);
					tableFav.setShowVerticalLines(false);
					scrollFav.setViewportView(tableFav);
					
					//Estilo del header
					JTableHeader tableHeader = tableFav.getTableHeader();
					tableHeader.setBackground(new Color(0, 191, 140));
					tableHeader.setForeground(Color.WHITE);
					tableHeader.setBorder(null);
					tableHeader.setEnabled(false);
					
					}
				else {
					JOptionPane.showMessageDialog(this, "Usuario sin libros");
				}
				
				//Tabla de Best Sellers
				
				Book book = null;
				try {
					ArrayList<Integer> bookSales= bookInterface.listTopSales();
					ArrayList<Book> libros = new ArrayList();
					ArrayList<Integer> ventas = new ArrayList();
					for(int i=0; i<10; i++) {
						if(i/2==0) {
							libros.add(bookInterface.buscarBook(bookSales.get(i)));
						}
						else {
							ventas.add(bookSales.get(bookSales.get(i)));
						}
					}
					
					String matrizTablaSales[][] = new String[5][5]; 
				for(int i=0; i<5;i++) {
					matrizTablaSales[i][0]= Integer.toString(i+1)+" "+bookSales.get(i);
					matrizTablaSales[i][1]= libros.get(i).getTitle();
					matrizTablaSales[i][2]= libros.get(i).getAuthor();
					matrizTablaSales[i][3]= libros.get(i).getDescription();
					matrizTablaSales[i][4]= "comprar";
				}	
				} catch (GestorException e) {
					JOptionPane.showMessageDialog(this, "Error al cargar la tabla", "Aviso", 0);
					e.printStackTrace();
				} 
	}
}
