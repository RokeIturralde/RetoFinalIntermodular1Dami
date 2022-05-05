package tartanga.dami.equipoa;


import tartanga.dami.equipoa.dataAccess.IAuthorBookController;
import tartanga.dami.equipoa.dataAccess.IAuthorBookControllerDBImplementation;


import tartanga.dami.equipoa.dataAccess.IAuthorController;
import tartanga.dami.equipoa.dataAccess.IAuthorControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IBookController;
import tartanga.dami.equipoa.dataAccess.IBookControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IGenreController;
import tartanga.dami.equipoa.dataAccess.IGenreControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IUserController;
import tartanga.dami.equipoa.dataAccess.IUserControllerDBImplementation;
import tartanga.dami.equipoa.dataAccess.IUserControllerDBImplementationTest;
import tartanga.dami.equipoa.gui.WLogIn;


public class Application {

	
	public static void main(String[] args) {

		IUserController userInterface = new IUserControllerDBImplementation();
		IAuthorController authorInterface = new IAuthorControllerDBImplementation();
		IGenreController genreInterface = new IGenreControllerDBImplementation();
		IBookController bookInterface = new IBookControllerDBImplementation();

		IAuthorBookController authorBook = new IAuthorBookControllerDBImplementation();
		
		WLogIn logIn = new WLogIn(userInterface, authorInterface, genreInterface, bookInterface);
		logIn.setVisible(true);
	}

}
