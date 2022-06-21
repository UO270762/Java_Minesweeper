package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class ConstructorTest {

	private Board tablero;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	//Caso de funcionalidad   

		/**
		 * Caso 1: Comprobar que la dimension de la matriz, la cantidad
		 * de minas se conrresponde con la especidicado por parametro
		 * 
		 * GIVEN: 
		 * WHEN: Se llama al contructor de la clase
		 * THEN: Se comprueba las condiciones dictadas anteriormente
		 */
		@Test
		public void testCheckGoodImplementation() {
			tablero = new Board(4,4,12);
			tablero.cargarTablero();
			tablero.cargarMinas();
			assertEquals(4, tablero.size());
			assertEquals(1, tablero.getFlagsLeft());
		}

}
