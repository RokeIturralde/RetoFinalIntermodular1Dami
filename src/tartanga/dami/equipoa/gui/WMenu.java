package tartanga.dami.equipoa.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;

import tartanga.dami.equipoa.dataAccess.IAuthorBookController;
import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.AuthorBook;
import tartanga.dami.equipoa.model.Book;
import tartanga.dami.equipoa.model.User;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WMenu extends JDialog {
	private ArrayList<AuthorBook> listLikedBooks;
	private JTable tableFav;
	private JScrollPane scrollFav;
	private IUserController userInterface;
	private IBookController bookInterface;
	private IAuthorController authorInterface;
	private User user;
	private JTable tableSales;
	private JScrollPane scrollSales;
	
	
	public WMenu(IAuthorController authorInterface, IBookController bookInterface, IUserController userInterface, User user, IAuthorBookController authorBookInterface) {
		setBounds(100, 100, 1001, 620);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 968, 565);
		getContentPane().add(tabbedPane);
		
		JPanel inicio = new JPanel();
		tabbedPane.addTab("Inicio", null, inicio, null);
		inicio.setLayout(null);
		
		//Panel perfil
		JPanel perfil = new JPanel();
		tabbedPane.addTab("Perfil", null, perfil, null);
				
		//Panel consultas
		JPanel consultas = new JPanel();
		tabbedPane.addTab("Consultas", null, consultas, null);
		
		
		
	}
}
