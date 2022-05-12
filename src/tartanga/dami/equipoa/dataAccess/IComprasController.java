package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.User;

public interface IComprasController {

	public ArrayList<Compra> historialCompras(String userName) throws GestorException;
	
	public float calcularPrecio(int isbn) throws GestorException;
	
	public void escribirCompra(Compra compra, String user) throws GestorException;

}
