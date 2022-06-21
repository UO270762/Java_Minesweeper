package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.Action;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Square {

	
	public enum State{OPEN, CLOSED, FLAGGED};
	
	private State estado;
	
	private int valor;
	
	private Action accionAsociada;
	
	private int xPos;
	
	private int yPos;
	
	/**
	 * Constructor con parametros para la clase Casilla
	 * 
	 * @param valor
	 */
	public Square(int valor, int x, int y) {
		ArgumentChecks.isTrue(valor >= -1 && x >= 0 && y >= 0);
		this.valor = valor;
		this.estado = State.CLOSED;
		this.xPos = x;
		this.yPos = y;
		this.accionAsociada = null;
	}

	/**
	 * Metodo que devuelve la accion asociada al metodo
	 * 
	 * @return accion asociada al metodo
	 */
	public Action getAccionAsociada() {
		return accionAsociada;
	}

	/**
	 * Metodo que establece la accion para la casilla
	 * 
	 * @param accionAsociada
	 */
	protected void setAccionAsociada(Action accionAsociada) {
		ArgumentChecks.isTrue(accionAsociada != null);
		this.accionAsociada = accionAsociada;
	}
	
	/**
	 * Metodo set para el estado de la casilla
	 * 
	 * @param estado
	 */
	public void setEstado(State estado) {
		ArgumentChecks.isTrue(estado != null);
		this.estado = estado;
	}

	/**
	 * Metodo que devuelve el estado de la casilla
	 * 
	 * @return estado de la casilla
	 */
	public State getEstado() {
		return estado;
	}
	
	/**
	 * Metodo que establece el valor de la casilla
	 * 
	 * @param valor
	 */
	protected void setValue(int valor) {
		ArgumentChecks.isTrue(valor >= -1);
		this.valor = valor;
	}
	
	/**
	 * Metodo que devuelve la posicion x de la casilla
	 * 
	 * @return xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Metodo que devuelve la posicion y de la casilla
	 * 
	 * @return yPos
	 */
	public int getyPos() {
		return yPos;
	}
	
	/**
	 * Metodo que devuelve el valor de la casilla
	 * 
	 * @return valor de la casilla
	 */
	public int getValue() {
		return valor;
	}

	/**
	 * Metodo que devuelve si la casilla esta abierta
	 * 
	 * @return true si la casilla esta abierta
	 */
	public boolean isOpen() {
		return (this.getEstado().equals(State.OPEN));
	}

	/**
	 * Metodo que devuelve si la casilla tiene una bandera
	 * 
	 * @return true si la casilla esta marcada
	 */
	public boolean hasFlag() {
		return (this.getEstado().equals(State.FLAGGED));
	}

	/**
	 * Metodo que marca una casilla
	 */
	public void markFlag() {
		this.estado = State.FLAGGED;
	}
	
	/**
	 * Metodo que abre una casilla
	 */
	public void open() {
		this.estado = State.OPEN;
	}
	
	/**
	 * Metodo que desmarca una casilla (la devuelve a cerrada)
	 */
	public void unmarkflag() {
		this.estado = State.CLOSED;
	}
	
	/**
	 * Metodo que incrementa el valor numero si una casilla esta cerca de una mina
	 */
	public void incProxMina() {
		this.valor++;
	}
	
	/**
	 * Metodo que establece el valor numero para una mina
	 */
	public void putMine() {
		this.valor = -1;
	}
	
	/**
	 * Redefinicion del metodo toString para la casilla
	 */
	public String toString() {
		String devolver = "";
		if(isOpen()) {
			if(getValue() == -1) {
				devolver = "@";
			}
			else if(getValue() == 0) {
					devolver = " ";
			}
			else if(getValue() > 0) {
				devolver = String.valueOf(getValue());
			}
		}
		else if(hasFlag()) {
			devolver = "¶";
		}
		else {
			devolver = "#";	
		}
		return devolver;
	}

}
