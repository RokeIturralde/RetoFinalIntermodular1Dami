package tartanga.dami.equipoa.gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VMenuConsultas extends JPanel {
	private JTextField txtAutor;
	private JTable tableConsultas;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnBuscar;

	public VMenuConsultas() {
		setLayout(null);

		JLabel lblInformacion = new JLabel("Busca informacion en la base de datos:");
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacion.setBounds(10, 21, 223, 52);
		add(lblInformacion);

		txtAutor = new JTextField();
		txtAutor.setBounds(125, 90, 160, 26);
		add(txtAutor);
		txtAutor.setColumns(10);

		JRadioButton rdbAutor = new JRadioButton("Por Autor");
		rdbAutor.setBounds(10, 92, 109, 23);
		add(rdbAutor);

		JRadioButton rdbTitulo = new JRadioButton("Por Titulo");
		rdbTitulo.setBounds(10, 130, 109, 23);
		add(rdbTitulo);

		JRadioButton rdbIsbn = new JRadioButton("Por Isbn");
		rdbIsbn.setBounds(10, 166, 109, 23);
		add(rdbIsbn);

		JLabel lblAgrupar = new JLabel("Filtrar");
		lblAgrupar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAgrupar.setBounds(10, 59, 103, 26);
		add(lblAgrupar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(107, 314, 109, 41);
		add(btnBuscar);

		JComboBox cbxGenero = new JComboBox();
		cbxGenero.setName("");
		cbxGenero.setBounds(107, 223, 109, 23);
		add(cbxGenero);

		tableConsultas = new JTable();
		tableConsultas.setBounds(352, 78, 503, 449);
		add(tableConsultas);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(125, 127, 160, 26);
		add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 164, 160, 26);
		add(textField_1);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {

		}

	}
}
