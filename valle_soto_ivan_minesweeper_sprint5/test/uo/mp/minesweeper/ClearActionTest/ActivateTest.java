package uo.mp.minesweeper.ClearActionTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class ActivateTest {

	private Board tablero;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	/**
	 * Caso 1: Intentar desencadenar una ClearAction 
	 * 			al descubrir una casilla numerica
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con casillas numericas y se mete en el tablero
	 * THEN: Se descubre una casilla numerica y no se desencadena la BlowUpAction
	 * 		lo comprobamos al ver que todas las casillas siguen cerradas
	 */
	@Test
	public void testClearActionUponNotEmptySquare() {
		copiaMatriz = new Square[4][4];
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(1,i,j);
			}
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[0][0].getAccionAsociada().activate();
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				assertFalse(copiaMatriz[i][j].isOpen());
			}
		}
	}
	
	/**
	 * Caso 2: Desencadenar una ClearAction al abrir una casilla vacia
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con casillas vacias y se mete en el tablero
	 * THEN: Se descubre una casilla mina y se desencadena la BlowUpAction,
	 * 			lo comprobamos al ver que todas las casillas estan abiertas
	 */
	@Test
	public void testClearActionUponEmptySquare() {
		copiaMatriz = new Square[4][4];
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(0,i,j);
			}
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[0][0].getAccionAsociada().activate();
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				assertTrue(copiaMatriz[i][j].isOpen());
			}
		}
	}
	
	/**
	 * Caso 3: Desencadenar una ClearAction al abrir una casilla vacia en un
	 * 			tablero con casillas mixtas
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con casillas mixtas y se mete en el tablero
	 * THEN: Se descubre una casilla vacia y se desencadena la BlowUpAction,
	 * 			lo comprobamos al ver que se crea una isla de casillas vacias
	 * 			y las casillas limites de la misma
	 */
	@Test
	public void testClearActionUponEmptyIsland() {
		copiaMatriz = new Square[4][4];
		for(int i = 1; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(1,i,j);
			}
		}
		for(int i = 0; i < 4; i++) {
			copiaMatriz[0][i] = new Square(0,0,i);
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[0][0].getAccionAsociada().activate();
		//Se comprueba la zoña abierta
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				assertTrue(copiaMatriz[i][j].isOpen());
			}
		}
		//Se comprueba la zona cerrada
		for(int i = 2; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				assertFalse(copiaMatriz[i][j].isOpen());
			}
		}
	}

}
