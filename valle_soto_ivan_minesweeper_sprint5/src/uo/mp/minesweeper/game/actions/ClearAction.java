package uo.mp.minesweeper.game.actions;

import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.game.Square;

public class ClearAction implements Action {

	private List<Square> neighbouringSquares;
	
	/**
	 * Metodo que abre la lista de casillas vecinas que recibe como parametro
	 * 
	 * @param neighbouringSquares
	 */
	public ClearAction(List<Square> neighbouringSquares) {
		this.neighbouringSquares = neighbouringSquares;
	}
	
	/**
	 * Metodo que si se da el caso abre una isla de casillas vacias, su funcionamiento
	 * es el siguiente, itera sobre la lista de casillas vecias de la casilla 
	 * seleccionada para abrir, si se encuentra una casilla vecina vacia, 
	 * llama a la accion de esa casilla y añade las casillas vecinas de la misma
	 * a la lista de casillas a abrir (menos las casillas que ya se ecnuentren 
	 * en ella para evitar un StackOverflow), una vez que tiene calculada la 
	 * posible isla de casillas vacias vecinas y sus limites las va abriendo
	 *  
	 */
	@Override
	public void activate() {
		List<Square> listaIte = new ArrayList<Square>();
		for(int i = 0; i < neighbouringSquares.size(); i++) {
			if(neighbouringSquares.get(i).getValue() == 0) {
				ClearAction act = (ClearAction)neighbouringSquares.get(i)
						.getAccionAsociada();
				listaIte = act.getNeighbouringSquares();
				for(Square sq : listaIte) {
					if(!neighbouringSquares.contains(sq)) {
						neighbouringSquares.add(sq);
					}
				}
			}
			neighbouringSquares.get(i).open();
		}
	}
	
	private List<Square> getNeighbouringSquares() {
		return neighbouringSquares;
	}
}
