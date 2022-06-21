package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class FlagTest {

	private Board tablero;
	private Square casillaMultUsos;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board(); 
	}

	//Casos de funcionalidad
	
	/**
	 * Caso 1: Comprobar se ha desmarcado una casilla mina desmarcada
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	 * 		matriz auxiliar para introducirle una casilla con una mina con bandera
	 * 		y se reintroduce
	 * THEN: Se marca esa casilla en concreto
	 */
	@Test
	public void testMinedNotFlaged() {
		tablero.cargarTablero();
		casillaMultUsos = new Square(-1,1,1);
		tablero.meterMina(1, 1, casillaMultUsos);
		tablero.flag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[1][1].hasFlag());
	}

	/**
	 * Caso 2: Comprobar se ha marcado una casilla sin mina desmarcada 
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas 
	 * WHEN: Se llena el tablero
	 * THEN: Se marca una casilla sin mina
	 */
	@Test
	public void testNotMinedNotFlaged() {
		tablero.cargarTablero();
		tablero.flag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[1][1].hasFlag());
	}
	
	/**
	 * Caso 3: Comprobar se ha marcado una casilla marcada
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	 * 		matriz auxiliar para introducirle una casilla con bandera
	 * 		y se reintroduce
	 * THEN: Se marca esa casilla 
	 */
	@Test
	public void testFlaged() {
		tablero.cargarTablero();
		tablero.cargarTablero();
		casillaMultUsos = new Square(0,1,1);
		casillaMultUsos.markFlag();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[1][1] = casillaMultUsos;
		tablero.setBoard(copiaMatriz);
		tablero.flag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[1][1].hasFlag());
	}
	
	/**
	 * Caso 4: Comprobar se ha desmarcado una casilla dos veces 
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas
	 * THEN: Se marca una casilla dos veces
	 */
	@Test
	public void testTwiceUnFlaged() {
		tablero.cargarTablero();
		tablero.flag(1, 1);
		tablero.flag(1, 1);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[1][1].hasFlag());
	}

}
