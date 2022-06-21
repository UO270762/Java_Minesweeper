package uo.mp.minesweeper.BlowUpActionTest;

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
	 * Caso 1: Desencadenar una BlowUpAction al descubrir una mina
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con minas y se mete en el tablero
	 * THEN: Se descubre una casilla mina y se desencadena la BlowUpAction
	 */
	@Test
	public void testBlowUpActionUponMine() {
		copiaMatriz = new Square[4][4];
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(-1,i,j);
			}
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		tablero.stepOn(0, 0);
		assertTrue(tablero.isExplode());
	}
	
	/**
	 * Caso 2: Intentar desencadenar una BlowUpAction al descubrir una
	 * 			casilla vacia
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con casillas vacias y se mete en el tablero
	 * THEN: Se descubre una casilla vacia y no se desencadena la BlowUpAction
	 */
	@Test
	public void testBlowUpActionUponEmptySquare() {
		copiaMatriz = new Square[4][4];
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(0,i,j);
			}
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		tablero.stepOn(0, 0);
		assertFalse(tablero.isExplode());
		
	}
	
	/**
	 * Caso 3: Intentar desencadenar una 
	 * 			BlowUpAction al descubrir una casilla numerica
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga una matriz con casillas numericas y se mete en el tablero
	 * THEN: Se descubre una casilla numerica y no se desencadena la BlowUpAction
	 */
	@Test
	public void testBlowUpActionUponNotEmptySquare() {
		copiaMatriz = new Square[4][4];
		for(int i = 0; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz.length; j++) {
				copiaMatriz[i][j] = new Square(1,i,j);
			}
		}
		tablero.setBoard(copiaMatriz);
		tablero.asociateAccion();
		tablero.stepOn(0, 0);
		assertFalse(tablero.isExplode());
		
	}


}
