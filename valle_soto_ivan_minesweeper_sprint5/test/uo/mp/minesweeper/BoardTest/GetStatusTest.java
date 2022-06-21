package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class GetStatusTest {

	private Board tablero;
	private Square casillaMultUsos;
	private char[][] estadoMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	/**
	 * Caso 1: Tablero completamente cubierto
	 * 
	 * GIVEN: Un tablero vacio 
	 * WHEN: Se llena de casillas
	 * THEN: Se comprueba el estado
	 */
	@Test
	public void testCoverBoard() {
		tablero.cargarTablero();
		estadoMatriz = tablero.getStatus();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0; j < tablero.size(); j++) {
				assertEquals('#', estadoMatriz [i][j]);
			}
		}
	}
	
	/**
	 * Caso 2: Tablero completamente descubierto
	 * 
	 * GIVEN: Un tablero vacio 
	 * WHEN: Se llena de casillas
	 * THEN: Se comprueba el estado
	 */
	@Test
	public void testUncoverBoard() {
		tablero.cargarTablero();
		tablero.unveil();
		estadoMatriz = tablero.getStatus();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0; j < tablero.size(); j++) {
				assertEquals(' ', estadoMatriz [i][j]);
			}
		}
	}
	
	/**
	 * Caso 3: Tablero en estado intermedio de juego
	 * 
	 * GIVEN: Un tablero en estado intermedio de juego 
	 * WHEN:  
	 * THEN: Se comprueba el estado
	 */
	@Test
	public void testCoverBoardGame() {
		tablero = new Board(4,4,12);
		tablero.cargarTablero();
		casillaMultUsos = new Square(-1, 1, 1);
		tablero.meterMina(1, 1, casillaMultUsos);
		tablero.flag(1, 3);
		tablero.flag(2, 2);
		tablero.stepOn(0, 0);
		estadoMatriz = tablero.getStatus();
		assertEquals('¶', estadoMatriz[1][3]);
		assertEquals('¶', estadoMatriz[2][2]);
		assertEquals(1, estadoMatriz[0][0] - 48); //Es caracter numerico
	}
}
