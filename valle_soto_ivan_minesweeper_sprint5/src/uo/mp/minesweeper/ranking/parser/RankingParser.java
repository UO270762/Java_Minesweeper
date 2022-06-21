package uo.mp.minesweeper.ranking.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.comparator.ScoresComparator;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.GameSession;
import uo.mp.minesweeper.util.exceptions.BlankLineException;
import uo.mp.minesweeper.util.exceptions.EmptyFileException;
import uo.mp.minesweeper.util.exceptions.LineFormatException;
import uo.mp.minesweeper.util.exceptions.WrongElementException;
import uo.mp.minesweeper.util.exceptions.WrongNumberException;
import uo.mp.minesweeper.util.exceptions.WrongNumberOfElementsException;
import uo.mp.minesweeper.util.log.FileLogger;

public class RankingParser {

	public FileLogger logger;
	/**
	 * Constructor de la clase RankingParser
	 */
	public RankingParser() {
		logger = new FileLogger("minesweeper.log");
	}
	
	/**
	 * Metodo que parsea una lista de lineas, que recibe como parametro
	 * devolviendo una lista de objetos score
	 * 
	 * @param lineas
	 * @return lista de scores
	 * @throws EmptyFileException 
	 */
	public List<Score> parser(List<String> lineas) throws EmptyFileException {
		if(lineas.size() == 0) {
			throw new EmptyFileException("Fichero vacio");
		}
		ArrayList<Score> ret = new ArrayList<Score>();
		for(int i = 0; i < lineas.size(); i++) {
			try {
				ret.add(createScore(lineas.get(i)));
			} catch (LineFormatException lfe) {
				String fecha = GameSession.fechaString(new Date());
				logger.log(fecha + "Error linea: " + i + "->" + lfe.getMessage());
			}
		}
		ret.sort(new ScoresComparator());
		return ret;
	}

	/**
	 * Metodo que recibe una linea (String) y la procesa para crear un objeto
	 * Score, puede arrogar distintas excepciones especializadas segun el error
	 * que surga
	 * 
	 * @param linea
	 * @return Objeto Score
	 * @throws BlankLineException
	 * @throws WrongElementException
	 * @throws WrongNumberException
	 * @throws WrongNumberOfElementsException
	 */
	private Score createScore(String linea) throws BlankLineException, 
												   WrongElementException,
												   WrongNumberException, 
												   WrongNumberOfElementsException {
		String[] partes = linea.split(";");
		if(partes.length == 1) {
			throw new BlankLineException("La linea esta vacia, se ignora");
		} else if (partes.length != 6) {
			throw new WrongNumberOfElementsException("La linea tiene un numero"
					+ " invalido de parametros");
		}
		
		checkName(partes[0]);
		Date date = checkDate(partes[1],partes[2]);
		GameLevel level = checkLevel(partes[3]);
		boolean res = checkResult(partes[4]);
		int time = checkTime(partes[5]);
		
		Score score = new Score(partes[0], date, level, time, res);
		return score;
	}

	/**
	 * Metodo que chequea la fecha, si es correcto la devuelve convertida en
	 * objeto fecha
	 * 
	 * @param date
	 * @param time
	 * @return objeto fecha 
	 * @throws WrongElementException
	 */
	private Date checkDate(String date, String time) throws WrongElementException {
		String str = date + " " + time; 
		Date devolver = null;
		try {
			devolver = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(str);
		} catch (ParseException dte) {
			throw new WrongElementException("Fecha invalida");
		}
		return devolver;
	}

	/**
	 * Metodo que chequea el tiempo que recibe como parametro, si
	 * es correcto a los parametros lo devuleve
	 * 
	 * @param time
	 * @return tiempo 
	 * @throws WrongNumberException
	 */
	private int checkTime(String time) throws WrongNumberException {
		int devolver = 0;
		try {
			devolver = Integer.parseInt(time);
		} catch (NumberFormatException nfe) {
			throw new WrongNumberException("Tiempo invalido (no es un numero)");
		}
		if(devolver < 0) {
			throw new WrongNumberException("Tiempo invalido");
		}
		return devolver;
	}

	/**
	 * Metodo que chequea el resultado, si es correcto de acuerdo a unos parametros
	 * y si es correcta devuelve un boolean con el resultado
	 * 
	 * @param resultado
	 * @return true si gana, false si no
	 * @throws WrongElementException
	 */
	private boolean checkResult(String resultado) throws WrongElementException {
		boolean lanzar = true;
		boolean devolver = false;
		switch (resultado) {
		case "won":
			lanzar = false;
			devolver = true;
			break;
		case "lost":
			lanzar = false;
			break;
		}
		if(lanzar) {
			throw new WrongElementException("Resultado invalido");
		}
		return devolver;
	}

	/**
	 * Metodo que chequea el nivel que recibe como parametro, si es correcto 
	 * de acuerdo a unos parametros devuelve un objeto GameLevel
	 * 
	 * @param level
	 * @return objeto GameLevel
	 * @throws WrongElementException
	 */
	private GameLevel checkLevel(String level) throws WrongElementException {
		boolean lanzar = true;
		GameLevel devolver = null;
		switch (level) {
		case "EASY":
			lanzar = false;
			devolver = GameLevel.EASY;
			break;
		case "MEDIUM":
			lanzar = false;
			devolver = GameLevel.MEDIUM;
			break;
		case "HIGH":
			lanzar = false;
			devolver = GameLevel.HIGH;
			break;
		}
		if(lanzar) {
			throw new WrongElementException("Nivel de partida invalido");
		}
		return devolver;
	}

	/**
	 * Metodo que chequea el nombre, de acuerdo a unos parametros
	 * 
	 * @param name
	 * @throws WrongElementException
	 */
	private void checkName(String name) throws WrongElementException {
		if(name.length() == 0) {
			throw new WrongElementException("Nombre invalido");
		}
	}
}
