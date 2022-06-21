package uo.mp.minesweeper.BoardTest;
/**
 * Esta clase es solo para comprobar yo como funciona el toString
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class ToStringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Board bd = new Board();
		bd.cargarTablero();
		bd.cargarMinas();
		bd.unveil();
		String tablero = bd.toString();
		assertEquals("[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\r\n" + 
				"[ #, #, #, #, #, #, #, #]\n", tablero);
	}

}
