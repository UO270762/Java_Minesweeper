package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UncoverWelcomeAreaTest {

	private Board tablero;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board(); 
	}

	//Casos de funcionalidad
	
	
	/**
	 * Caso 1: Comprobar se ha abierto todo el tablero con uncoverWelcomeArea
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llama a uncoverWelcomeArea con ese tablero lleno de casillas 
	 * 		vacias por lo tanto se descubre todo el tablero
	 * THEN: Se comprueba que se ha abierto todo el tablero
	 */
	@Test
	public void testUncoverFullBoardEmptySquares() {
		tablero.cargarTablero();
		tablero.asociateAccion();
		tablero.uncoverWelcomeArea();
		copiaMatriz = tablero.getBoard();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0;j < tablero.size(); j++ ){
				assertTrue(copiaMatriz[i][j].isOpen());
			}
		}
	}
	
	/**
	 * Caso 2: Comprobar se ha abierto una isla de casillas vacias
	 * 		al llamar a uncoverWelcomeArea
	 * 
	 * GIVEN: Un tablero vacio.
	 * WHEN: se llena la penultima fila de minas para generar
	 * 		una isla de casillas vacias y otra que debido a la "barrera" de minas
	 * 		queda cerrada.
	 * THEN: Se comprueba que se ha abierto una isla de casillas vacias
	 */
	@Test
	public void testUncoverStandarBoard() {
		tablero.cargarTablero();
		for(int i = 0; i < tablero.size(); i++) {
			tablero.meterMina(7, i, new Square(-1,7,i));
		}	
		tablero.asociateAccion();
		tablero.uncoverWelcomeArea();
		copiaMatriz = tablero.getBoard();
		//Se comprueba la zoña abierta
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < copiaMatriz[i].length; j++) {
				assertTrue(copiaMatriz[i][j].isOpen());
			}
		}
		//Se comprueba la zona cerrada
		for(int i = 7; i < copiaMatriz.length; i++) {
			for(int j = 0; j < copiaMatriz[i].length; j++) {
				assertFalse(copiaMatriz[i][j].isOpen());
			}
		}
	}
}
