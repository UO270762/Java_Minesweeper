package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.ArgumentChecks;

public class GameMove {

	char operation;
	int row;
	int column;
	
	/**
	 * Constructor de la clase GameMove que recibe un char de la operacion
	 * la columna y la fila 
	 * 
	 * @param operation
	 * @param row
	 * @param columns
	 */
	public GameMove(char operation, int row, int columns) {
		ArgumentChecks.isTrue(row >= 0 && columns >= 0);
		this.operation = operation;
		this.row = row;
		this.column = columns;
	}

	/**
	 * Metodo que devuelve el char operacion
	 * 
	 * @return operacion
	 */
	public char getOperation() {
		return operation;
	}

	/**
	 * Metodo que devuelve la fila
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Metodo que devuelve la columna
	 * 
	 * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Metodo que devuelve un toString de la operacion a realizar
	 */
	public String toString() {
		String devolver = "GameMove [operation="+ getOperation()
				+", row="+ getRow()+ ", column=" + getColumn()+ " ]";
		return devolver;
	}
}
