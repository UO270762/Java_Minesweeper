package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.ranking.Score;

public interface SessionInteractor {

	/**
	 * Metodo que pregunta al usuario el nivel de dificultad
	 * 
	 * @return un nivel de dificultad seleccionado
	 */
	GameLevel askGameLevel();
	
	/**
	 * Solicita al usuario el nombre de usuario y devuelve el nombre
	 * escrito
	 * 
	 * @return devuelve el nombre de usuario
	 */
	String askUserLevel();
	
	/**
	 * Metodo que solicita al usuario que introduzca una opcion del menu de 
	 * de sesion. Devuelve un entero que representra la opcion escogida 
	 * (Si el valor es 0 significa salir)
	 * 
	 * @return opcion escogida
	 */
	int askNextOption();
	
	/**
	 * Al finalizar una partida, pregunta al usuario si quiere guardar su 
	 * puntacion
	 * 
	 * @return true si quiere guardala, false si no
	 */
	boolean doYouWantToRegisterYourScore();
	
	/**
	 * Metodo que muestra el ranking representado todas las puntuaciones 
	 * registradas en el sistema y las muestra todas 
	 * 
	 * @param ranking
	 */
	void showRanking(List<Score> ranking);
	
	/**
	 * Metodo que muestra todas las puntuaciones registradas en el sistema , 
	 * que recibe como parametro, omitiendo el usuario asociado a cada partida
	 * (se sobreentiende que es el usuario actual)
	 * 
	 * @param ranking
	 */
	void showPersonalRanking(List<Score> ranking, String nombreUsuario);
	
	/**
	 * Metodo que muestra un mensaje de despedida al usuario
	 */
	void showGoodBye();
	
	/**
	 * Metodo que muestra un mensaje de error al usuario, el cual se pasa como 
	 * parametro
	 * 
	 * @param message
	 */
	void showErrorMessage(String message);
	
	/**
	 * Metodo para comunicar mensajes de error graves al usuario, el cual se 
	 * pasa como parametro
	 * 
	 * @param message
	 */
	void showFatalErrorMessage(String message);

	/**
	 * Metodo que pregunta al usuario el nombre de fichero que quiere importar
	 * o exportar
	 * 
	 * @return nombre de fichero de ranking que quiere importar o exportar
	 */
	String askFileName();
	
	
}
