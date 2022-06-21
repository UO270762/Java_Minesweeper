package uo.mp.minesweeper.session;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.EmptyFileException;
import uo.mp.minesweeper.util.log.FileLogger;

public class GameSession {

	private Game game;
	
	private GameRanking ranking;
	
	private SessionInteractor interadorSesionAsociado;   
	
	private GameInteractor interadorJuegoAsociado;
	
	private static FileLogger logger;
	
	/**
	 * Constructor de la clase GameSession
	 */
	public GameSession() {
	}
	
	/**
	 * Metodo que simula la ejecucion de una sesion de juego, tambien es la 
	 * encargada de crear el Game y el tablero donde se juega en el caso
	 * de que se elija jugar una partida
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public void run() throws FileNotFoundException, ClassNotFoundException {
		String nombre = interadorSesionAsociado.askUserLevel();
		System.out.println("Bienvenido " + nombre);
		int opcion = interadorSesionAsociado.askNextOption();
		doSesion(opcion, nombre);
		interadorSesionAsociado.showGoodBye();
	}
	
	/**
	 * Metodo que simula la sesion segun las distintas opciones eleguidas
	 * (Metodo auxiliar para el run)
	 * 
	 * @param opcion
	 * @param nombre jugador
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void doSesion(int opcion, String nombre) throws FileNotFoundException, ClassNotFoundException {
		ArgumentChecks.isTrue((opcion > 0 || opcion < 5) 
				|| nombre.length() != 0,"Inicio de sesion invalido");
		while(opcion != 0) {
			ranking.persistenceRankingLoad();
			switch (opcion) {
			case 1:
				try {
					playGame(nombre);
				} catch (RuntimeException rte) {
					handlerRTE(rte);
				} catch (Exception e) {
					handlerException(e);
				}
				break;
			case 2:
				interadorSesionAsociado.showPersonalRanking(ranking.getGeneral(),nombre);
				break;
			case 3:
				interadorSesionAsociado.showRanking(ranking.getGeneral());
				break;
			case 4:
				ranking.exportRanking(interadorSesionAsociado.askFileName());
				break;
			case 5:
				try {
					ranking.importRanking(interadorSesionAsociado.askFileName());
					ranking.persistenceRankingSave();
					ranking.persistenceRankingLoad();
				} catch (EmptyFileException e) {
					String fecha = fechaString(new Date());
					logger.log(fecha + e.getMessage());
				} catch (FileNotFoundException e) {
					String fecha = fechaString(new Date());
					interadorSesionAsociado.showFatalErrorMessage("-No existe "
							+ "ningun fichero con ese nombre-");
					logger.log(fecha + e.getMessage());
				}
				break;
			}
			opcion = interadorSesionAsociado.askNextOption();
		}
	}

	/**
	 * Metodo para opcion: jugar una partida 
	 * (Metodo auxiliar para el run)
	 * 
	 * @param nombre jugador
	 * @throws FileNotFoundException 
	 */
	private void playGame(String nombre) throws FileNotFoundException {
		ArgumentChecks.isTrue(nombre.length() != 0, "Nombre de sesion invalido");
		GameLevel nivelDific = interadorSesionAsociado.askGameLevel();
		Board tablero = null;
		switch (nivelDific) {
		case EASY:
			tablero = new Board(9, 9, 12 );
			break;
		case MEDIUM:
			tablero = new Board(16, 16 ,15);
			break;
		case HIGH:
			tablero = new Board(30, 16, 20);
			break;
		}
		establishGame(tablero);
		doSave(nombre, nivelDific);
	}
	
	/**
	 * Metodo que establece un nuevo juego
	 * 
	 * @param tablero
	 */
	private void establishGame(Board tablero) {
		ArgumentChecks.isTrue(tablero != null, "Tablero null invalido");
		game = new Game(tablero);
		game.setInteractor(interadorJuegoAsociado);
		game.play();
	}
	
	/**
	 * Metodo que hace la accion de guardado de partida
	 * 
	 * @param nombre jugador
	 * @param nivelDific
	 * @throws FileNotFoundException 
	 */
	private void doSave(String nombre, GameLevel nivelDific) throws FileNotFoundException {
		ArgumentChecks.isTrue(nombre.length() != 0, "Salvado de datos invalido");
		boolean guardado = 
				interadorSesionAsociado.doYouWantToRegisterYourScore();
		Date fecha = new Date();
		if(guardado) {
				Score puntuacion = 
						new Score(nombre, fecha, nivelDific,  
								game.getTime(), game.hasWon());
				ranking.append(puntuacion);
				ranking.persistenceRankingSave();
		}
	}
	
	/**
	 * Metodo para leer el caracter que se pasa por teclado
	 * 
	 * @return codificacion en ASCII del caracter leido
	 */
	public static int readCharacter() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int character = 0;
		try {
			character = br.read();
		} catch (IOException e) {
			String fecha = fechaString(new Date());
			logger.log(fecha + "Error de I/O" +e.getMessage());
			System.exit(0);
		}
		return character;
	}
	
	/**
	 * Metodo que lee un numero de teclado y devuelve su valor numerico
	 * 
	 * @return numero leido
	 */
	public static int readNumber() {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner( System.in );
		int num = reader.nextInt();
		return num;
	}
	
	/**
	 * Metodo que lee una cadena de string de consola y la devuelve
	 * 
	 * @return cadena de strings leida por consola
	 */
	@SuppressWarnings("resource")
	public static String readString() {
		String nombre;
        Scanner teclado = new Scanner(System.in);
        nombre = teclado.nextLine();
        return nombre;
	}

	/**
	 * Metodo que devuelve el iterador asociado
	 *  
	 * @return iterador asociado
	 */
	public SessionInteractor getIteradorAsociado() {
		return interadorSesionAsociado;
	}

	/**
	 * Metodo que establece un logger asociado a la sesion
	 * 
	 * @param logger
	 */
	@SuppressWarnings("static-access")
	public void setLogger(FileLogger logger) {
		ArgumentChecks.isTrue(logger != null);
		this.logger = logger;
	}
	
	/**
	 * Metodo que establece el interador de sesion asociado por aquel que se 
	 * pasa como parametro
	 * 
	 * @param interador
	 */
	public void setSessionInteractor(SessionInteractor interador) {
		ArgumentChecks.isTrue(interador != null);
		this.interadorSesionAsociado = interador;
	}
	
	/**
	 * Metodo que establece el interador de juego asociado a esta sesion por 
	 * aquel que se pasa como parametro
	 * 
	 * @param interactor
	 */
	public void setGameInteractor(GameInteractor interactor) {
		ArgumentChecks.isTrue(interadorSesionAsociado != null); 
		this.interadorJuegoAsociado = interactor;
	}
	
	/**
	 * Metodo que establece el ranking asociado a la sesion por aquel que se 
	 * pasa como parametro
	 * 
	 * @param ranking
	 */
	public void setGameRanking(GameRanking ranking) {
		ArgumentChecks.isTrue(ranking != null); 
		this.ranking = ranking;
	}
	
	/**
	 * Metodo que genera un String espedifico para el solictado en el formato
	 * de mesage de log
	 * 
	 * @param fecha
	 * @return String [dd/MM/yy-HH:mm:ss]:
	 */
	public static String fechaString(Date fecha) {
		String date = new SimpleDateFormat("dd/MM/yy").format(fecha); 
		String time = new SimpleDateFormat("HH:mm:ss").format(fecha);
		return "[" + date + "-" + time + "]:";
	}
	
	/**
	 * Handler para RuntimeException
	 * 
	 * @param npe
	 */
	private void handlerRTE(RuntimeException rte) {
		interadorSesionAsociado.showFatalErrorMessage("(!)Ha ocurrido un error "
				+ "fatal, contacte con el servicio tecnico(!)");
		String fecha = fechaString(new Date());
		logger.log(fecha + rte.getMessage());
		
	}
	
	/**
	 * Handler para exception
	 * 
	 * @param e
	 */
	private void handlerException(Exception e) {
		interadorSesionAsociado.showFatalErrorMessage("(!)Ha ocurrido un error "
				+ "fatal, contacte con el servicio tecnico(!)");
		String fecha = fechaString(new Date());
		logger.log(fecha + e.getMessage());
	}

}
