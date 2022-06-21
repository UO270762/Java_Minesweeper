package uo.mp.minesweeper.RankingParser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.parser.RankingParser;
import uo.mp.minesweeper.util.exceptions.EmptyFileException;

public class ParserTest {

	private RankingParser rp;
	
	private List<String> listaString;
	
	private List<Score> listaResult;
	
	@Before
	public void setUp() throws Exception {
		rp = new RankingParser();
		listaString = new ArrayList<String>();
		listaResult = new ArrayList<Score>();
	}
	
	//Casos positivos
	
	/**
	 * Caso 1: Parse de lista con solo elementos validos
	 * 
	 * GIVEN: Un RankingParser
	 * WHEN: Se da a procesar con una lista de elementos validos
	 * THEN: Se comprueba que la lista resultado tiene la cantidad de elementos 
	 * 		esperada
	 */
	@Test
	public void testParseWithRightsElement() {
		String uno = "Ivan;07/05/20;17:35:00;EASY;won;25";
		String dos = "Ivan;07/05/20;17:35:12;HIGH;won;8";
		 
		listaString.add(uno);
		listaString.add(dos);
		
		try {
			listaResult = rp.parser(listaString);
			assertEquals(2, listaResult.size());
		} catch (EmptyFileException e) {
			//doNothing
		}
	}
	//Casos negativos
	
	/**
	 * Caso 1: Parse de lista con solo elementos invalidos (uno con un error de 
	 * 											cada tipo)
	 * 
	 * GIVEN: Un RankingParser
	 * WHEN: Se da a procesar con una lista de elementos invalidos
	 * THEN: Se comprueba que la lista resultado tiene 0, ya que todos son
	 * 		invalidos
	 */
	@Test
	public void testParseListWithVariusErrors() {
		String uno = "juan;05/05/20;;EASY;won;3";
		String dos = "";
		String tres = ";05/05/20;18:31:55;EASY;won;3";
		String cuatro = "ivan;05/05/20;19:40:40;ESY;won;7";
		String cinco = "ivan;;19:40:40;EASY;;7";
		String sexto = "IVAN;05/05/20;19:52:04;EASY;lost;-13";
		String septima = "IVAN;05/05/20;19:52:04;EASY;;-13";
		
		listaString.add(uno);
		listaString.add(dos);
		listaString.add(tres);
		listaString.add(cuatro);
		listaString.add(cinco);
		listaString.add(sexto);
		listaString.add(septima);
		
		try {
			listaResult = rp.parser(listaString);
			assertEquals(0, listaResult.size());
		} catch (EmptyFileException e) {
			//doNothing
		}
		
	}
	
	/**
	 * Caso 1: Parse de lista con solo elementos invalidos (uno con un error de 
	 * 											cada tipo)
	 * 
	 * GIVEN: Un RankingParser
	 * WHEN: Se da a procesar con una lista de elementos invalidos
	 * THEN: Se comprueba que la lista resultado tiene 0, ya que todos son
	 * 		invalidos
	 */
	@Test
	public void testParseWithEmptyList() {
		try {
			rp.parser(listaString);
		} catch (EmptyFileException e) {
			assertEquals("Fichero vacio", e.getMessage());
		}
	}

	
}
