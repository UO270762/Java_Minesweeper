package uo.mp.minesweeper.ranking.comparator;

import java.util.Comparator;

import uo.mp.minesweeper.ranking.Score;

public class ScoresComparator implements Comparator<Score>{

	/**
	 * Metodo que compara dos score y devolvera uno valor numerico segun el 
	 * resultado esa comparacion: Los criterios de prioridad son:
	 * MayorDificultad-Menor tiempo-Mayor antiguedad
	 */
	@Override
	public int compare(Score o1, Score o2) {
		
		int higestLevel = o1.getLevel().compareTo(o2.getLevel());
		int fechaMayor = o1.getDate().compareTo(o2.getDate());
		
		if(higestLevel == 0) {
			if(o1.getTime() == o2.getTime()) {
				if(fechaMayor == 0) {
					return 0;
				} else if(fechaMayor == -1) {
					return -1;
				} else {
					return 1;
				}
			} else if (o1.getTime() > o2.getTime()) {
				return 1;
			} else {
				return -1;
			}
		} else if (higestLevel == -1) {
			return 1;
		} else  {
			return -1;
		}
	}
}
