package tartanga.dami.equipoa.dataAccess;

import java.util.ArrayList;

import tartanga.dami.equipoa.gestorException.GestorException;
import tartanga.dami.equipoa.model.Consulta;

public interface IConsultaController {

	public ArrayList<Consulta> tablaConsulta(String dato) throws GestorException;

	public ArrayList<Consulta> listarTodosLosDatos() throws GestorException;
}
