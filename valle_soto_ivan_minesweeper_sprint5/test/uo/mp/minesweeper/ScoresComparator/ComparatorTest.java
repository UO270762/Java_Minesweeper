package uo.mp.minesweeper.ScoresComparator;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.comparator.ScoresComparator;
import uo.mp.minesweeper.session.GameLevel;

public class ComparatorTest {

	
	private ArrayList<Score> listaOrdenar;
	
	private ArrayList<Score> listaOrdenada;
	
	@Before
	public void setUp() throws Exception {
		listaOrdenar = new ArrayList<Score>();
		listaOrdenada = new ArrayList<Score>();
		
	}

	/**
	 * Caso 1 Comparar dos score con misma dificultad
	 * 
	 * GIVEN: Una lista con dos scores de igual dificultad
	 * WHEN: Se ordena esa lista
	 * THEN: Se comprueba (con otra lista ordenada "manualmente") que la lista
	 * 		se ha ordenado segun el siguiente criterio de menor tiempo
	 */
	@Test
	public void testWithSameDificulty() {
		Score sc1 = new Score("juan", GameLevel.HIGH, 7, true);
		Score sc2 = new Score("pepe", GameLevel.HIGH, 3, false);
		
		listaOrdenar.add(sc1);
		listaOrdenar.add(sc2);
		  
		listaOrdenada.add(sc2);
		listaOrdenada.add(sc1);
		
		listaOrdenar.sort(new ScoresComparator());
		
		for(int i = 0; i < listaOrdenar.size(); i++) {
			assertEquals(listaOrdenada.get(i), listaOrdenar.get(i));
		}
	}

	/**
	 * Caso 2 Comparar dos score con misma dificultad y mismo timepo
	 * 
	 * GIVEN: Una lista con dos scores de igual dificultad y mismo tiempo
	 * WHEN: Se ordena esa lista
	 * THEN: Se comprueba (con otra lista ordenada "manualmente") que la lista
	 * 		se ha ordenado segun el siguiente criterio de fecha mas antigua
	 */
	@Test
	public void testWithSameDificultyAndTime() {
		String f1 = "3/3/2010 12:23:2";
		String f2 = "3/3/2003 12:23:2";
		
		Date fecha1 = null;
		try {
			fecha1 = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(f1);
		} catch (ParseException e1) {
			//doNothing
		}
		
		Date fecha2 = null;
		try {
			fecha2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(f2);
		} catch (ParseException e) {
			//doNothing
		}
		
		Score sc1 = new Score("juan", fecha1, GameLevel.HIGH, 3, true);
		Score sc2 = new Score("pepe", fecha2, GameLevel.HIGH, 3, false);
		
		listaOrdenar.add(sc1);
		listaOrdenar.add(sc2);
		  
		listaOrdenada.add(sc2);
		listaOrdenada.add(sc1);
		
		listaOrdenar.sort(new ScoresComparator());
		
		for(int i = 0; i < listaOrdenar.size(); i++) {
			assertEquals(listaOrdenada.get(i), listaOrdenar.get(i));
		}
	}
	
	/**
	 * Caso 3 Comparar una lista con scores varios
	 * 
	 * GIVEN: Una lista con dos scores variados
	 * WHEN: Se ordena esa lista
	 * THEN: Se comprueba (con otra lista ordenada "manualmente") que la lista
	 * 		se ha ordenado correctamente
	 */
	@Test
	public void testWithVariusScores() {
		Score sc1 = new Score("sen", GameLevel.EASY, 21, true);
		Score sc2 = new Score("xkilx", GameLevel.MEDIUM, 14, false);
		Score sc3 = new Score("pepe", GameLevel.HIGH, 3, false);
		Score sc4 = new Score("juan", GameLevel.HIGH, 7, true);
		
		listaOrdenar.add(sc1);
		listaOrdenar.add(sc2);
		listaOrdenar.add(sc3);
		listaOrdenar.add(sc4);
		
		listaOrdenada.add(sc3);
		listaOrdenada.add(sc4);
		listaOrdenada.add(sc2);
		listaOrdenada.add(sc1);
		
		listaOrdenar.sort(new ScoresComparator());
		
		for(int i = 0; i < listaOrdenar.size(); i++) {
			assertEquals(listaOrdenada.get(i), listaOrdenar.get(i));
		}
	}
}
