package uo.mp.minesweeper.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import uo.mp.minesweeper.util.ArgumentChecks;

public class Game {

	private static Board tablero;
	
	private GameInteractor interactorAsociado;
	
	private long tiempoPartida;
	
	/**
	 * Constructor de la clase Game recibe unicamente un tablero 
	 * como parametro
	 * 
	 * @param tablero
	 */
	@SuppressWarnings("static-access")
	public Game(Board tablero) {
		ArgumentChecks.isTrue(tablero != null);
		this.tablero = tablero;
	}
	
	/**
	 * Devuelve el tablero de juego
	 * 
	 * @return tablero 
	 */
	public Board getTablero() {
		return tablero;
	}
	
	/**
	 * Metodo que simula el juego del buscaminas
	 */
	public void play() {
		interactorAsociado.showReadyToStart();
		boolean finDeJuego = tablero.isExplode() || tablero.win();
		long tiempoInicio = System.currentTimeMillis();
		tablero.uncoverWelcomeArea();
		long tiempoReferencia =
				(System.currentTimeMillis() - tiempoInicio)/1000;
		interactorAsociado.showGame(tiempoReferencia, tablero);
		while (!finDeJuego) {
			GameMove gameMove = null;
			while(gameMove == null) {
				try {
					gameMove = interactorAsociado.askMove(tablero.rows(),
					tablero.cols());
				} catch (InputMismatchException e) {
					System.out.println("Porfavor introduzca una cifra");
				}
			}
			switch (gameMove.getOperation()) {
			case 's':
				tablero.stepOn(gameMove.getRow(), gameMove.getColumn());
				break;
			case 'f':
				tablero.flag(gameMove.getRow(), gameMove.getColumn());
				break;
			case 'u':
				tablero.unFlag(gameMove.getRow(), gameMove.getColumn());
				break;
			default:
				break;
			}
			tiempoReferencia = (System.currentTimeMillis() - tiempoInicio)/1000;
			interactorAsociado.showGame(tiempoReferencia, tablero);
			finDeJuego = tablero.isExplotado() || tablero.win();
		}
		if(tablero.win()) {
			interactorAsociado.showGameFinished();
			tiempoReferencia = (System.currentTimeMillis() - tiempoInicio)/1000;
			this.tiempoPartida = tiempoReferencia;
			interactorAsociado.showCongratulations(tiempoReferencia);
		}
		else if(tablero.isExplode()) {
			tiempoReferencia = (System.currentTimeMillis() - tiempoInicio)/1000;
			this.tiempoPartida = tiempoReferencia;
			interactorAsociado.showGameFinished();
			interactorAsociado.showBooommm();
		}
	}

	/**
	 * Comprueba que la casilla introducida sea valida
	 * en posicion y el movimiento
	 * 
	 * @param x
	 * @param y
	 * @return true si lo es, false si no
	 */
	public static boolean seleccionValida(int x, int y) {
		if ((x >= 0 &&  y >= 0 &&  x < tablero.size() 
				&&  y < tablero.size())) {
			return true;
		}
		System.out.println("Casilla no valida, Introduzca de nuevo");
		return false;
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
			System.out.println("¡Error de lectura en la entrada de datos!");
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
	 * Metodo que devuelve un booleano segun se haya ganado o perdido la partida
	 * 
	 * @return true si se ha ganado, false si se ha perdido
	 */
	public boolean hasWon() {
		if(tablero.win()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que devuelve el tiempo que se ha empleado en una partida
	 * 
	 * @return tiempo empleado en jugar una partida
	 */
	public long getTime() {
		return tiempoPartida;
	}
	
	/**
	 * Metodo que devuelve el objeto iterador asociado al juego
	 * 
	 * @return iteradorAsociado
	 */
	public GameInteractor getIterador() {
		return interactorAsociado;
	}

	/**
	 * Metodo que asocia un iterador al juego
	 * 
	 * @param iteradorAsociado
	 */
	public void setInteractor(GameInteractor iteradorAsociado) {
		ArgumentChecks.isTrue(iteradorAsociado != null);
		this.interactorAsociado = iteradorAsociado;
	}
}
