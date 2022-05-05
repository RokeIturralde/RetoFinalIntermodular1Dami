package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Compra;

public interface IComprasController {

	public ArrayList<Compra> historialCompras(String genreName) throws GestorException;

}
