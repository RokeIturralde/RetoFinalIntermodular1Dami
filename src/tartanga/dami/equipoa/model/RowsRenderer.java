package tartanga.dami.equipoa.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private int columna;

	public RowsRenderer(int Colpatron) {
		this.columna = Colpatron;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		setBackground(Color.white);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		if (table.getValueAt(row, columna).equals("false")) {
			this.setForeground(Color.ORANGE);
		} else if (table.getValueAt(row, columna).equals("true")) {
			this.setForeground(Color.GREEN);
		}
		return this;
	}
}