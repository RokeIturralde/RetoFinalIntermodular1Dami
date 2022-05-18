package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;
import tartanga.dami.equipoa.model.User;

/**
 * @author Eneko
 *
 */
public interface IComprasController {

	/**
	 * Metodo para listar las compras hechas por un usuario
	 * @param userName el nombre de usuario
	 * @return un ArrayList de las compras realizadas
	 * @throws GestorException
	 */
	public ArrayList<Compra> historialCompras(String userName) throws GestorException;
	
	/**
	 * Calcula el precio con descuento de un libro
	 * @param isbn el isbn del libro
	 * @return el precio final con descuento
	 * @throws GestorException
	 */
	public float calcularPrecio(int isbn) throws GestorException;
	
	/**
	 * Metodo para añadir una compra
	 * @param compra la compra a introducir
	 * @param user el nombre de usuario que compra
	 * @throws GestorException
	 */
	public void escribirCompra(Compra compra, String user) throws GestorException;

}
