package uo.mp.minesweeper.SquareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class FlagTest {

	private Board tablero;
	private Square[][] matrizAux;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board(); 
	}

	/**
	 * Caso 1: Flag en casilla abierta
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se marca una casilla abierta (esta accion no esta permitida)
	 * THEN: Se comprueba que esta sin bandera 
	 * 
	 */
	@Test
	public void testFlagSquareOpen() {
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
		tablero.flag(1, 1);
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].hasFlag());
	}

	/**
	 * Caso 2: Flag en casilla cerrada
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se marca una casilla cerrada
	 * THEN: Se comprueba que esta con bandera
	 * 
	 */
	@Test
	public void testFlagSquareClosed() {
		tablero.cargarTablero();
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].isOpen());
		tablero.flag(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].hasFlag());
	}
	/**
	 * Caso 1: Flag en casilla con bandera
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se marca una casilla con bandera
	 * THEN: Se comprueba que esta con bandera
	 * 
	 */
	@Test
	public void testFlagSquareFlagged() {
		tablero.cargarTablero();
		tablero.flag(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].hasFlag());
		tablero.flag(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].hasFlag());
	}

}
