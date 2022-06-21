package uo.mp.minesweeper.util.exceptions;

public class BlankLineException extends LineFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param msg
	 */
	public BlankLineException(String msg) {
		super(msg);
	}

}
