package uo.mp.minesweeper.BoardTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class StepOnTest {

	private Board tablero;
	private Square casillaMultUsos;
	private Square[][] copiaMatriz;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	//Casos de funcionalidad
	
	/**
	 * Caso 1: Comprobar se ha descubierto una casilla ya descubierta
	 * 
	 * GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	 * WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	 * 		matriz auxiliar para introducirle una casilla ya abierta
	 * 		y se reintroduce
	 * THEN: Se abre esa casilla en concreto
	 */
	@Test
	public void testOpenSquare() {
		tablero.cargarTablero();
		casillaMultUsos = new Square(0,1,1);
		casillaMultUsos.open();
		copiaMatriz = tablero.getBoard();
		copiaMatriz[1][1] = casillaMultUsos;
		tablero.setBoard(copiaMatriz);
		tablero.stepOn(1, 1);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[1][1].isOpen());
	}
		
	/**
	* Caso 2: Comprobar se ha descubierto una casilla numerica
	* 
	* GIVEN: Un tablero vacio, se llena de casillas vacias cerradas
	* WHEN: Se llena el tablero de casillas, se hace una copia de este a una 
	* 		matriz auxiliar para introducirle una casilla con mina
	* 		y se reintroduce
	* THEN: Se abre esa casilla en concreto
	*/
	@Test
	public void testNumericSquare() {
		tablero.cargarTablero();
		casillaMultUsos = new Square(-1,1,1);
		tablero.meterMina(1, 1, casillaMultUsos);
		tablero.stepOn(0, 0);
		copiaMatriz = tablero.getBoard();
		assertTrue(copiaMatriz[0][0].isOpen() && 
				copiaMatriz[0][0].getValue() == 1);
	}
}
