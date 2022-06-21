package uo.mp.minesweeper.ranking;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Score implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private Date fecha;
	
	private GameLevel level;
	
	private boolean hasWon;
	
	private long time;
	
	/**
	 * Constructor con parametros para la clase Score
	 * (con fecha como parametro para el parse a fichero y para test)
	 * 
	 * @param userName
	 * @param level
	 * @param time
	 * @param hasWon
	 * @param fecha
	 */
	public Score(String userName, Date fecha, GameLevel level, long time, boolean hasWon) {
		ArgumentChecks.isTrue(userName.length() != 0, "Nombre invalido");
		ArgumentChecks.isTrue(time > 0, "tiempo invalido");
		this.fecha = fecha; 
		this.userName = userName;
		this.level = level;
		this.time = time;
		this.hasWon = hasWon;
	}
	
	/**
	 * Constructor con parametros para la clase Score
	 * 
	 * @param userName
	 * @param level
	 * @param time
	 * @param hasWon
	 */
	public Score(String userName, GameLevel level, long time, boolean hasWon) {
		ArgumentChecks.isTrue(userName.length() != 0, "Nombre invalido");
		ArgumentChecks.isTrue(time > 0, "tiempo invalido");
		this.fecha = new Date(); 
		this.userName = userName;
		this.level = level;
		this.time = time;
		this.hasWon = hasWon;
	}
	
	/**
	 * Redifinicion del toString para el Score
	 */
	public String toString() {
		String devolver = userName + "\t\t ";
		String date = new SimpleDateFormat("dd/MM/yy").format(fecha); 
		String time = new SimpleDateFormat("HH:mm:ss").format(fecha);
		devolver = devolver + date + " " + time + " ";
		switch (level) {
		case EASY:
			devolver = devolver + "EASY    ";
			break;
		case MEDIUM:
			devolver = devolver + "MEDIUM    ";
			break;
		case HIGH:	
			devolver = devolver + "HIGH    ";
			break;
		}
		if(hasWon) {
			devolver = devolver + "won  ";
		}
		else {
			devolver = devolver + "lost  ";
		}
		devolver = devolver + this.getTime();
		return devolver;
	}     
	
	/**
	 * Variacion del toString en el cual no se muestra el usuario de la
	 * score
	 * 
	 * @return toString sin usuario
	 */
	public String toStringWithOutUser() {
		String devolver = "";
		String date = new SimpleDateFormat("dd/MM/yy").format(fecha); 
		String time = new SimpleDateFormat("HH:mm:ss").format(fecha);
		devolver = devolver + date + " " + time + " ";
		switch (level) {
		case EASY:
			devolver = devolver + "EASY    ";
			break;
		case MEDIUM:
			devolver = devolver + "MEDIUM    ";
			break;
		case HIGH:	
			devolver = devolver + "HIGH    ";
		}
		if(hasWon) {
			devolver = devolver + "won  ";
		}
		else {
			devolver = devolver + "lost  ";
		}
		devolver = devolver + this.getTime();
		return devolver;
	}

	/**
	 * Metodo que devuelve el nombre de usuario
	 * 
	 * @return nombre de usuario
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Metodo que devuelve el tiempo
	 * 
	 * @return tiempo
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Metodo que devuelve la fecha
	 * 
	 * @return fecha
	 */
	public Date getDate() {
		return this.fecha;
	}
	
	/**
	 * Metodo que devuelve le nivel de dificultad del nivel
	 * 
	 * @return nivel de dificultad
	 */
	public GameLevel getLevel() {
		return this.level;
	}
	
	/**
	 * Metodo que devuelve un booleano segun haya sido el 
	 * resultado de la partida
	 * 
	 * @return true si ha ganado, false si no
	 */
	public boolean hasWon() {
		return this.hasWon;
	}

	/**
	 * Metodo que serializa una score con el fin de devolver un String con el
	 * formato que se nos solicita para guardar a fichero
	 * 
	 * @return String con score serializada
	 */
	public String serialize() {
		String cadena = "";
		cadena = cadena + this.userName + ";";
		String date = new SimpleDateFormat("dd/MM/yy").format( this.getDate()); 
		String time = new SimpleDateFormat("HH:mm:ss").format( this.getDate());
		cadena = cadena + date + ";";
		cadena = cadena + time + ";";
		switch (this.getLevel()) {
		case EASY:
			cadena = cadena + "EASY" + ";";
			break;
		case MEDIUM:
			 cadena = cadena + "MEDIUM" + ";";
			break;
		case HIGH:	
			cadena = cadena + "HIGH" + ";";
		}
		if(hasWon) {
			cadena = cadena + "won" + ";";
		}
		else {
			cadena = cadena + "lost" + ";";
		}
		cadena = cadena + this.getTime();
		return cadena;
	}
}