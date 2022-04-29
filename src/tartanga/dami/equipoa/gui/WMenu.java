package tartanga.dami.equipoa.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class WMenu extends JDialog {
	public WMenu() {
		setBounds(100, 100, 1001, 620);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 968, 565);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Inicio", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Perfil", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Consultas", null, panel_2, null);
		
	iniciarPaneles();
		
	}

	private void iniciarPaneles() {
		
		
	}
}
