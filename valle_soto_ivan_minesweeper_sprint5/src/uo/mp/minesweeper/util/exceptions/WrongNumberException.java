package uo.mp.minesweeper.util.exceptions;

public class WrongNumberException extends LineFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param msg
	 */
	public WrongNumberException(String msg) {
		super(msg);
		
	}

}
