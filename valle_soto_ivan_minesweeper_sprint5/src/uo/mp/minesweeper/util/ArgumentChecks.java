package uo.mp.minesweeper.util;

public class ArgumentChecks {


	/**
	 * Metodo auxiliar que chequea una condicion de un parametro,
	 * si es falso lanza una IllegalArgumentException con mensaje
	 * 
	 * @param condition
	 * @param msg
	 */
	public static void isTrue(boolean condition, String msg) {
		if(! condition) {
			throw new IllegalArgumentException();
		}
	}  
	
	/**
	 * Metodo auxiliar que chequea una condicion de un parametro,
	 * si es falso lanza una IllegalArgumentException
	 * 
	 * @param condition
	 */
	public static void isTrue(boolean condition) {
		if(! condition) {
			throw new IllegalArgumentException();
		}
	}  
	
}
