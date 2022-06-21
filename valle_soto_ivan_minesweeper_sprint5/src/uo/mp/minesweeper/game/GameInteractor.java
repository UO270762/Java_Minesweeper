package uo.mp.minesweeper.game;

public interface GameInteractor {

	/**
	 * Solicita al usuario un comando y unas coordenadas fila y columna
	 * @param heigh
	 * @param width
	 * @return
	 */
	GameMove askMove(int heigh, int width);
	
	/**
	 * metodo que muestra el estado el juego al usuario
	 * 
	 * @param elapsedTime
	 * @param board
	 */
	void showGame(long elapsedTime, Board board);
	
	/**
	 * Informa al usuario de que el juego ha terminado
	 */
	void showGameFinished();
	
	/**
	 * Informa al usuario de que ha ganado el juego
	 * 
	 * @param tiempoInicio
	 */
	void showCongratulations(long tiempoInicio);
	
	/**
	 * Informa al usuario de que ha levantado una casilla mina y ha perdido
	 */
	void showBooommm();
	
	/**
	 * Informa al usuario de que el juego esta apunto de comenzar
	 */
	void showReadyToStart();
}
