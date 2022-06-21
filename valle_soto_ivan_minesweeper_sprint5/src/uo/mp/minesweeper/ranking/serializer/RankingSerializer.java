package uo.mp.minesweeper.ranking.serializer;

import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.comparator.ScoresComparator;

public class RankingSerializer {
	
	/**
	 * Constructor de la clase ranking serializer
	 */
	public RankingSerializer() {
		
	}
	
	/**
	 * Metodo que serializa una lista de puntuaciones que recibe como parametro
	 * 
	 * @param scores
	 * @return lista con los String de las Score serializadas
	 * 
	 */
	public static ArrayList<String> serializer (List<Score> scores) {
		scores.sort(new ScoresComparator());
		ArrayList<String> lista = new ArrayList<String>();
		for(Score score: scores) {
			lista.add(score.serialize());
		}
		return lista;
	}
}
