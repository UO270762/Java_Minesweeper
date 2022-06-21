package uo.mp.minesweeper.SerializerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.serializer.RankingSerializer;
import uo.mp.minesweeper.session.GameLevel;

public class SerializerTest {

	private RankingSerializer rs;
	
	private ArrayList <String> listaResult;
	
	private ArrayList<Score> listaProcesar;
	
	@Before
	public void setUp() throws Exception {
		rs = new RankingSerializer();
		listaResult = new ArrayList<String>();
		listaProcesar = new ArrayList<Score>();
	}

	/**
	 * Caso 1: Se intenta seralizar una lista vacia
	 * 
	 * GIVEN: Un rankingSerializer
	 * WHEN: Se intenta serializar una lista vacia
	 * THEN: Se comprueba que no se ha podido serializar nada
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testWithEmptyList() {
		listaResult = rs.serializer(listaProcesar);
		assertEquals(0, listaResult.size());
	}
	
	/**
	 * Caso2: Se intenta serializar una lista de scores con distintos valores
	 * 
	 * GIVEN: Un rankingSerializer
	 * WHEN: Se intenta serializar una lista con scores
	 * THEN: Se comprueba que se han serializado todos, ademas tambien comprobamos
	 * 		 que aquellos atributos que no son String se han serializado dando el 
	 * 		 String esperado (analogamente se puede comprabar que la lista se ha 
	 * 		 ordenado)
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testWithVariusValors() {
		listaProcesar.add(new Score("pollo", GameLevel.EASY, 14, true));
		listaProcesar.add(new Score("pepe", GameLevel.MEDIUM, 4, false));
		listaProcesar.add(new Score("paul", GameLevel.HIGH, 194, true));
		
		listaResult = rs.serializer(listaProcesar);
		
		//Cambia el orden a devolver una lista serializada y ordenada
		
		String[] primerScore = listaResult.get(0).split(";");
		String high = primerScore[3];
		String win = primerScore[4];
		assertEquals("HIGH", high);
		assertEquals("won", win);
		
		String[] segundoScore = listaResult.get(1).split(";");
		String medium = segundoScore[3];
		String lost = segundoScore[4];
		assertEquals("MEDIUM", medium);
		assertEquals("lost", lost);
		
		String[] terceroScore = listaResult.get(2).split(";");
		String easy = terceroScore[3];
		assertEquals("EASY", easy);
		
		assertEquals(3,listaResult.size());
	}
}
