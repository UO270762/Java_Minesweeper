package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class UnveilTest {

	private Board tablero;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	//Casos de funcionalidad
	
	/**
	 * Caso 1: Comprobar que todas las casillas estan abiertas con un tablero 
	 * completamento cubierto
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se llena el tablero de casillas 
	 * THEN: Se llama a unveil y se comprueba que se han abierto todas las casillas
	 */
	@Test
	public void testWithFullBoard() {
		tablero.cargarTablero();
		tablero.unveil();
		copiaMatriz = tablero.getBoard();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0; j < tablero.size(); j++) {
				assertEquals(State.OPEN, copiaMatriz[i][j].getEstado());
			}
		}
	}
	/**
	 * Caso 1: Comprobar que todas las casillas estan abiertas con un tablero 
	 * completamento cubierto y con algunas banderas puestas
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga el tablero, se extrae para poner algunas banderas y 
	 * 		se pasa como nuevo tablero
	 * THEN: Se llama a unveil y se comprueba que se han abierto todas las casillas 
	 * 		(hasta las banderas)
	 */
	@Test
	public void testWithSomeFlags() {
		tablero.cargarTablero();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[0][0].markFlag();
		copiaMatriz[1][1].markFlag();
		copiaMatriz[4][7].markFlag();
		tablero.setBoard(copiaMatriz);
		tablero.unveil();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0; j < tablero.size(); j++) {
				assertEquals(State.OPEN, copiaMatriz[i][j].getEstado());
			}
		}
	}
	/**
	 * Caso 1: Comprobar que todas las casillas estan abiertas con un tablero 
	 * completamento cubierto y con algunas casillas ya abiertas
	 * 
	 * GIVEN: Un tablero vacio
	 * WHEN: Se carga el tablero, se extrae para poner algunas casillas abiertas y 
	 * 		se pasa como nuevo tablero 
	 * THEN: Se llama a unveil y se comprueba que se han abierto todas las casillas
	 * 		(hasta las que estaban abiertas)
	 */
	@Test
	public void testWithSomeOpenSquares() {
		tablero.cargarTablero();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[0][0].open();
		copiaMatriz[1][1].open();
		copiaMatriz[4][7].open();
		tablero.setBoard(copiaMatriz);
		tablero.unveil();
		for(int i = 0; i < tablero.size(); i++) {
			for(int j = 0; j < tablero.size(); j++) {
				assertEquals(State.OPEN, copiaMatriz[i][j].getEstado());
			}
		}
	}
}
