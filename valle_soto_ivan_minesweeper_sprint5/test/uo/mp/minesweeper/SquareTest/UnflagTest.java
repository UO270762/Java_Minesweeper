package uo.mp.minesweeper.SquareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UnflagTest {

	private Board tablero;
	private Square[][] matrizAux;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	/**
	 * Caso 1: unFlag en casilla abierta
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se desmarca una casilla abierta
	 * THEN: Se comprueba que esta sin bandera
	 * 
	 */
	@Test
	public void testUnFlagSquareOpen() {
		matrizAux = new Square[4][4];
		for(int i = 0; i < matrizAux.length; i++) {
			for(int j = 0; j < matrizAux.length; j++) {
				if(i== 1 && j == 1) {
					matrizAux[i][j] = new Square(1,1,1);
					matrizAux[i][j].open();
				}
			}
		}
		assertTrue(matrizAux[1][1].isOpen());
		tablero.setBoard(matrizAux);
		tablero.unFlag(1, 1);
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].hasFlag());
	}

	/**
	 * Caso 2: unFlag en casilla cerada
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se desmarca una casilla cerrada
	 * THEN: Se comprueba que esta sin bandera
	 * 
	 */
	@Test
	public void testUnFlagSquareClosed() {
		tablero.cargarTablero();
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].isOpen());
		tablero.unFlag(1, 1);
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].hasFlag());
	}
	/**
	 * Caso 3: unFlag en casilla con bandera
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se desmarca una casilla con bandera
	 * THEN: Se comprueba que esta sin bandera
	 * 
	 */
	@Test
	public void testUnFlagSquareFlagged() {
		tablero.cargarTablero();
		tablero.flag(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].hasFlag());
		tablero.unFlag(1, 1);
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].hasFlag());
	}

}
