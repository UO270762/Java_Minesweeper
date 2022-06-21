package uo.mp.minesweeper.util.exceptions;

public class EmptyFileException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param msg
	 */
	public EmptyFileException(String msg) {
		super(msg);
	}
}
