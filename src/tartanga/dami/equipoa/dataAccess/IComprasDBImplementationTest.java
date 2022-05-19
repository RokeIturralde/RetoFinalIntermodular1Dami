package tartanga.dami.equipoa.dataAccess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;

/**
 * @author 1dami
 * Clase para testear la implementacion de la interfaz de compras
 */
public class IComprasDBImplementationTest {
	
	IComprasController pruebaCompra = new IComprasDBImplementation();

	/**
	 * Metodo test que lista el historial de las compras de un usuario
	 * @throws GestorException
	 */
	@Test
	public void testHistorialCompras() throws GestorException {
		ArrayList<Compra> prueba = pruebaCompra.historialCompras("gitanito77");
		assertNotEquals(prueba, null);
	}

	/**
	 * Metodo de testeo para comprobar que el precio es correcto
	 * @throws GestorException
	 */
	@Test
	public void testCalcularPrecio() throws GestorException {
		float precio = pruebaCompra.calcularPrecio(1);
		assertTrue(precio == 18);
	}

	/**
	 * Metodo test para escribir una compra en la base de datos
	 * @throws GestorException
	 */
	@Test
	public void testEscribirCompra() throws GestorException {
		Compra compra = new Compra();
		compra.setCantidadLibros(1);
		compra.setIsbn(1);
		compra.setPrecioCompra(pruebaCompra.calcularPrecio(1));
		pruebaCompra.escribirCompra(compra, "gitanito77");
	}

}
