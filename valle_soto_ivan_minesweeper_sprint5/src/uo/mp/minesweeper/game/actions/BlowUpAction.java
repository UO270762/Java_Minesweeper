package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.ArgumentChecks;

public class BlowUpAction implements Action{

	private Board tablero;
	
	/**
	 * Accion para la explosion del tablero
	 * 
	 * @param tablero 
	 */
	public BlowUpAction(Board tablero) {
		ArgumentChecks.isTrue(tablero != null);
		this.tablero = tablero;
	}
	
	/**
	 * Implementacion del metodo activate para BlowUpAction
	 */
	@Override
	public void activate() {
		tablero.markAsExploded();
		tablero.unveil();
	}

}
