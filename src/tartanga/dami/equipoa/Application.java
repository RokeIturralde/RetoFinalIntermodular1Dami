package tartanga.dami.equipoa;

import tartanga.dami.equipoa.dataAccess.IAuthorBookController;
import tartanga.dami.equipoa.dataAccess.IAuthorBookControllerDBImplementation;

import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IAuthorControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IComprasController;
import tartanga.dami.equipoa.dataAccess.IComprasDBImplementation;
import tartanga.dami.equipoa.dataAccess.IConsultaController;
import tartanga.dami.equipoa.dataAccess.IConsultaControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IBookControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IGenreControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.dataAccess.IUserControllerDBImplementation;
import tartanga.dami.equipoa.gui.WLogIn;
/**
 * @author 1dami
 * Clase para iniciar el programa
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IUserController userInterface = new IUserControllerDBImplementation();
		IAuthorController authorInterface = new IAuthorControllerDBImplementation();
		IGenreController genreInterface = new IGenreControllerDBImplementation();
		IBookController bookInterface = new IBookControllerDBImplementation();
		IAuthorBookController authorBookInterface = new IAuthorBookControllerDBImplementation();
		IComprasController comprasInterface = new IComprasDBImplementation();
		IConsultaController consultaInterface = new IConsultaControllerDBImplementation();

		WLogIn logIn = new WLogIn(userInterface, authorInterface, genreInterface, bookInterface, authorBookInterface,
				comprasInterface, consultaInterface);
		logIn.setVisible(true);
	}

}
