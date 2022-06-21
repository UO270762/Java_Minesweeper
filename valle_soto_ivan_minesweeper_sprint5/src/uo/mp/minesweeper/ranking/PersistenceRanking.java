package uo.mp.minesweeper.ranking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.mp.minesweeper.ranking.comparator.ScoresComparator;
import uo.mp.minesweeper.session.GameSession;
import uo.mp.minesweeper.util.log.FileLogger;

public class PersistenceRanking {

	public FileLogger logger;
	
	/**
	 * Constructor de la clase
	 */
	public PersistenceRanking() {
		
	}
	/**
	 * Metodo que carga en un fichero (pasado el nombre como parametro) de una
	 * copia persistente del ranking 
	 * 
	 * @param nomFichero
	 * @return lista con los scores que se encuentren en el ranking
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	protected List<Score> loadFile(String nomFichero) throws FileNotFoundException, ClassNotFoundException {
		ArrayList<Score> lista = new ArrayList<Score>();
		try {
		ObjectInputStream in = new ObjectInputStream(
									new BufferedInputStream(
											new FileInputStream(nomFichero)));
			try {
				lista = (ArrayList<Score>) in.readObject();
			} finally {
				in.close();
			}
		} catch (IOException ioe) {
			String fecha = GameSession.fechaString(new Date());
			logger.log(fecha + "Error en carga de fichero -> " + nomFichero);
		}
		lista.sort(new ScoresComparator());
		return lista;
	}
	
	/**
	 * Metodo que guarda una lista de scores a un fichero a fin de hacerla
	 * persistente 
	 * 
	 * @param lista
	 * @param nomFichero
	 * @throws FileNotFoundException
	 */
	protected void saveToFile(List<Score> lista,String nomFichero) throws FileNotFoundException {
		lista.sort(new ScoresComparator());
		try {
		ObjectOutputStream out = new ObjectOutputStream(
										new BufferedOutputStream(
														new FileOutputStream(nomFichero)));
			try {
				out.writeObject(lista);
			} finally {
				 out.close();
			}
		} catch (IOException ioe) {
			String fecha = GameSession.fechaString(new Date());
			logger.log(fecha + "Error en guardado de fichero -> " + nomFichero);
		}
	}
}
