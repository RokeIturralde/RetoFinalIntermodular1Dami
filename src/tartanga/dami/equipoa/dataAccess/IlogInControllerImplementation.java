package tartanga.dami.equipoa.dataAccess;

import tartanga.dami.equipoa.gui.WLogIn;

public class IlogInControllerImplementation implements IlogInController{

	@Override
	public void launchApp(IUserController userInterface, IAuthorController authorInterface,
			IGenreController genreInterface, IBookController bookInterface, IComprasController comprasInterface,
			IConsultaController consultaInterface) {
		
		WLogIn logIn = new WLogIn(userInterface, authorInterface, genreInterface, bookInterface,
				comprasInterface, consultaInterface);
		logIn.setVisible(true);
		
	}

}
