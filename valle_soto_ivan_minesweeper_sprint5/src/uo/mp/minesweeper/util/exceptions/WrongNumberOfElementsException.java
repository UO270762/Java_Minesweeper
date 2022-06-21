package uo.mp.minesweeper.util.exceptions;

public class WrongNumberOfElementsException extends LineFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param msg
	 */
	public WrongNumberOfElementsException(String msg) {
		super(msg);
		
	}

	
}
