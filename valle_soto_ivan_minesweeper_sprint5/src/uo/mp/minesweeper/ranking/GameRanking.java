package uo.mp.minesweeper.ranking;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.ranking.comparator.ScoresComparator;
import uo.mp.minesweeper.ranking.parser.RankingParser;
import uo.mp.minesweeper.ranking.serializer.RankingSerializer;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.EmptyFileException;
import uo.mp.minesweeper.util.file.FileUtil;

public class GameRanking {

	private List<Score> puntuaciones;
	
	private String nombreFicheroRanking;
	
	/**
	 * Constructor de la clase GameRanking
	 */
	public GameRanking(String nombreFichero) {
		puntuaciones = new ArrayList<Score>();
		this.nombreFicheroRanking = nombreFichero;
	}
	
	/**
	 * Metodo que añade una puntuacion al final de la lista
	 * 
	 * @param score
	 */
	public void append(Score score) {
		ArgumentChecks.isTrue(score != null);
		puntuaciones.add(score);
		puntuaciones.sort(new ScoresComparator());
	}
	
	/**
	 * Metodo que devuelve la lista con las puntuaciones
	 * 
	 * @return puntuaciones
	 */
	public List<Score> getGeneral() {
		return puntuaciones;
	}
	
	/**
	 * Metodo que devuelve una lista con las puntuaciones del usuario con nombre
	 * el que se pasa como parametro
	 * 
	 * @param userName
	 * @return lista con las puntuaciones del usuario pasado como parametro
	 */
	public List<Score> getScoresFor(String userName) {
		ArgumentChecks.isTrue(userName.length() != 0);
		ArrayList<Score> listaDevolver = new ArrayList<Score>();
		for(Score score: puntuaciones) {
			if(score.getUserName().equals(userName)) {
				listaDevolver.add(score);
			}
		}
		return listaDevolver;
	}
	
	/**
	 * Metodo que exporta el ranking en memoria a un fichero, cuyo nombre
	 * recibe como parametro
	 * 
	 * @param nombreFichero
	 * @throws FileNotFoundException
	 */
	public void exportRanking(String nombreFichero) throws FileNotFoundException {
		ArrayList<String> lista = RankingSerializer.serializer(puntuaciones);
		new FileUtil().saveToFile(nombreFichero, lista, true);
	}
	
	/**
	 * metodo que importa un ranking desde un fichero, cuyo nombre recibe como 
	 * parametro
	 * 
	 * @param nombreFichero
	 * @throws FileNotFoundException
	 * @throws EmptyFileException
	 */
	public void importRanking(String nombreFichero) throws FileNotFoundException, EmptyFileException {
		List<String> lineas = new FileUtil().readLines(nombreFichero);
		this.puntuaciones = new RankingParser().parser(lineas);
	}
	
	/**
	 * Metodo que carga la copia persistente del ranking a memoria
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public void persistenceRankingLoad() throws FileNotFoundException, ClassNotFoundException {
		this.puntuaciones = new PersistenceRanking().loadFile(this.nombreFicheroRanking);
	}
	
	/**
	 * Metodo que guarda la copia persistente de un ranking a memoria
	 * 
	 * @throws FileNotFoundException
	 */
	public void persistenceRankingSave() throws FileNotFoundException {
		new PersistenceRanking().saveToFile(this.puntuaciones, this.nombreFicheroRanking);
	}
}
