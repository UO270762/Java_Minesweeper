package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UnflagTest {

	private Board tablero;
	private Square casillaMultUsos;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board(); 
	}

	//Casos de funcionalidad
	
	/**
	 * Caso 1: Comprobar se ha desmarcado una casilla mina y con bandera
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	 * 		matriz auxiliar para introducirle una casilla con una mina con bandera
	 * 		y se reintroduce
	 * THEN: Se desmarca esa casilla en concreto
	 */
	@Test
	public void testMinedFlaged() {
		tablero.cargarTablero();
		casillaMultUsos = new Square(-1,1,1);
		casillaMultUsos.markFlag();
		tablero.meterMina(1, 1, casillaMultUsos);
		tablero.unFlag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertFalse(copiaMatriz[1][1].hasFlag());
	}

	/**
	 * Caso 2: Comprobar se ha desmarcado una casilla mina sin bandera
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	 * 		matriz auxiliar para introducirle una casilla con una mina sin bandera
	 * 		y se reintroduce
	 * THEN: Se desmarca esa casilla en concreto
	 */
	@Test
	public void testMinedNotFlaged() {
		tablero.cargarTablero();
		casillaMultUsos = new Square(-1,1,1);
		tablero.meterMina(1, 1, casillaMultUsos);
		tablero.unFlag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertFalse(copiaMatriz[1][1].hasFlag());
	}
		
	/**
	 * Caso 3: Comprobar se ha desmarcado una casilla sin bandera 
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas
	 * THEN: Se desmarca una casilla 
	 */
	@Test
	public void testUnFlaged() {
		tablero.cargarTablero();
		tablero.unFlag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertFalse(copiaMatriz[1][1].hasFlag());
	}
		
	/**
	 * Caso 4: Comprobar se ha desmarcado una casilla dos veces 
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas
	 * THEN: Se desmarca una casilla dos veces
	 */
	@Test
	public void testTwiceUnFlaged() {
		tablero.cargarTablero();
		tablero.unFlag(1, 1);
		tablero.unFlag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertFalse(copiaMatriz[1][1].hasFlag());
	}
}