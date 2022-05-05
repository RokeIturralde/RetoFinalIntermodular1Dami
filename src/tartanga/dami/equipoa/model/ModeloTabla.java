package tartanga.dami.equipoa.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTabla extends AbstractTableModel{

	private String[] columNames = {
			"Titulo",
			"Autor",
			"Descripcion",
			"Precio"};
	
	private Object[][] data;
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public ModeloTabla(List<AuthorBook> likedBooks){
		for(int i=0; i<likedBooks.size();i++) {
			data[i][0] = likedBooks.get(i).getTitle();
			data[i][1] = likedBooks.get(i).getName()+likedBooks.get(i).getSurname();
			data[i][2] = likedBooks.get(i).getDescription();
			data[i][3] = likedBooks.get(i).getPrice();
		}
	}
	
	
}
