package uo.mp.minesweeper.SquareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class StepOnTest {

	private Board tablero;
	private Square[][] matrizAux;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Board();
	}

	/**
	 * Caso 1: stepOn en casilla ya descubierta
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se abre una casilla en concreto se abre, y se vuelve a abrir
	 * THEN: Se comprueba que sigue abierta
	 * 
	 */
	@Test
	public void testStepOnSquareOpen() {
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
		tablero.stepOn(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].isOpen());
	}

	/**
	 * Caso 2: stepOn en casilla cerrada
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se abre una casilla que estaba cerrada
	 * THEN: Se comprueba que sigue abierta
	 * 
	 */
	@Test
	public void testStepOnSquareClose() {
		matrizAux = new Square[4][4];
		for(int i = 0; i < matrizAux.length; i++) {
			for(int j = 0; j < matrizAux.length; j++) {
				if(i== 1 && j == 1) {
					matrizAux[i][j] = new Square(1,1,1);
				}
			}
		}
		assertFalse(matrizAux[1][1].isOpen());
		tablero.setBoard(matrizAux);
		tablero.stepOn(1, 1);
		matrizAux = tablero.getBoard();
		assertTrue(matrizAux[1][1].isOpen());
	}
	
	/**
	 * Caso 2: stepOn en casilla con bandera
	 * 
	 * GIVEN: Tablero con casillas
	 * WHEN: Se intenta abrir una casilla que tiene bandera, no se puede
	 * 		ya que primero tendria que desmarcarse
	 * THEN: Se comprueba que sigue abierta
	 * 
	 */
	@Test
	public void testStepOnSquareFlagged() {
		matrizAux = new Square[4][4];
		for(int i = 0; i < matrizAux.length; i++) {
			for(int j = 0; j < matrizAux.length; j++) {
				if(i== 1 && j == 1) {
					matrizAux[i][j] = new Square(1,1,1);
					matrizAux[i][j].markFlag();
				}
			}
		}
		assertFalse(matrizAux[1][1].isOpen());
		tablero.setBoard(matrizAux);
		tablero.stepOn(1, 1);
		matrizAux = tablero.getBoard();
		assertFalse(matrizAux[1][1].isOpen());
	}

}
