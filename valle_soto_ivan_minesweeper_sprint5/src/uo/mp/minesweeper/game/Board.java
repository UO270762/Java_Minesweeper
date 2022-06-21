package uo.mp.minesweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Board {

	private Square[][] board;
	private int flags;
	private int percentage;
	private boolean explotado;
	
	/**
	 * Constructor con parametros del tablero
	 * @param x
	 * @param y
	 * @param percBombs
	 */
	public Board(int x, int y, int percBombs) {
		ArgumentChecks.isTrue(x >= 0 && y >= 0 && percBombs >= 0);
		board = new Square[x][y];
		this.percentage = percBombs;
		cargarTablero();
		cargarMinas();
		asociateAccion();
	}
	
	/**
	 * Contructor sin parametros del tablero
	 */
	public Board() {
		board = new Square[9][9];
		percentage = 12;
		flags = 0;
		explotado = false;
		cargarTablero();
		cargarMinas();
		asociateAccion();
	}
	
	/**
	 * Contructor con parametros del tablero (USO PARA TEST)
	 * @param mines
	 * @param square
	 */
	public Board(int mines, Square[][] square) {
		ArgumentChecks.isTrue(mines > 0, "Numero de minas invalido");
		ArgumentChecks.isTrue(square.length != 0, "Matriz invalida");
		board = square;
	}	

	/**
	 * Metodo que devuelve le tamaño (para poder usarlo desde otras clases)
	 * 
	 * @return tamaño tablero
	 */
	public int size() {
		return board.length;
	}
	
	/**
	 * Metodo que devuelve si el tablero esta explotado
	 * 
	 * @return true si esta explotado false si no
	 */
	public boolean isExplotado() {
		return explotado;
	}
	
	/**
	 * Metodo que al empezar el juego ,en tablero se descubre una isla de casillas vacias
	 */
	public void uncoverWelcomeArea() {
		Random rnd = new Random();
		int x = rnd.nextInt(board.length);
		int y = rnd.nextInt(board[x].length);
		while(board[x][y].getValue() != 0) {
			x = rnd.nextInt(board.length);
			y = rnd.nextInt(board[x].length);
		}
		this.stepOn(x, y);
	}
	/**
	 * Metodo que establece el tablero
	 * 
	 * @param board
	 */
	public void setBoard(Square[][] board) {
		ArgumentChecks.isTrue(board != null);
		this.board = board;
	}

	/**
	 * Metodo que una vez cargado el tablero y colocadas las minas establece la 
	 * accion para cada una de ellas
	 * 
	 */
	public void asociateAccion() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].getValue() == 0) {
					board[i][j].setAccionAsociada(
							new ClearAction(getNeighbouringSquares(i, j)));
				}
				else if(board[i][j].getValue() == -1) {
					board[i][j].setAccionAsociada(new BlowUpAction(this));
				}
				else {
					board[i][j].setAccionAsociada(new NullAction());
				}
			}
		}
	}
	
	/**
	 * Metodo que devuelve las casillas vecinas (a 1 de distancia de una casilla dada)
	 * 
	 * @param x
	 * @param y
	 * @return lista con las casillas vecinas de una
	 */
	public List<Square> getNeighbouringSquares(int x, int y) {
		List<Square> listaDevolver = new ArrayList<Square>();
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1;j++ ) {
				if(i >= 0 && j >= 0 && i < board.length && j < board[i].length) {
					listaDevolver.add(board[i][j]);
				}
			}
		}
		return listaDevolver;
	}
	
	/**
	 * Metodo que devuelve le tablero
	 * 
	 * @return tablero
	 */
	public Square[][] getBoard() {
		return board;
	}
	
	/**
	 * Metodo que devuelve el % de bombas en el tablero
	 * 
	 * @return % de bombas por casilla
	 */
	public int getPercentage() {
		return percentage;
	}
	
	/**
	 * Metodo que devuelve las banderas puestas
	 * 
	 * @return numero de banderas
	 */
	public int getFlags() {
		return flags;
	}
	
	/**
	 * Metodo que incrementa el numero de banderas en el tablero
	 */
	public void incFlags() {
		this.flags++;
	}
	
	/**
	 * Metodo que carga el tablero con casillas vacias
	 */
	public void cargarTablero() {
		 for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board[i].length; j++) {
				 Square plantModelo = new Square(0, i, j);
				 board[i][j] = plantModelo;
			 }
		 }
	}
	
	/**
	 * Metodo que carga el tablero con las minas correspondientes
	 */
	public void cargarMinas() {
		Random rnd = new Random();
		for(int i = 0; i < (board.length * board[0].length) * getPercentage() / 100; i++) {
			int x = rnd.nextInt(board.length);
			int y = rnd.nextInt(board[x].length);
			Square mina = new Square(-1, x, y);
			while(board[x][y].getValue() == -1) {
				x = rnd.nextInt(board.length);
				y = rnd.nextInt(board[x].length);
				mina = new Square(-1, x, y);
			}
			meterMina(x,y,mina);
		}
	}
	
	/**
	 * Metodo que mete una mina en el tablero y incrementa la poximidad a mina
	 * de las 8 casillas que la rodean
	 * 
	 * @param x
	 * @param y
	 * @param mina
	 */
	public void meterMina(int x, int y, Square mina) {
		board[x][y] = mina;
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1;j++ ) {
				if(i >= 0 && j >= 0 && i < board.length && j < board[i].length
						&& board[i][j].getValue() != -1) {
					board[i][j].incProxMina();
				}
			}
		}
	}

	/**
	 * Metodo que devuelve si el tablero a explotado
	 * 
	 * @return true si ha explotado alguna mina en el tablero
	 */
	public boolean isExplode() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].getValue() == -1 &&
						board[i][j].isOpen()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Metodo que comprueba si se se ha ganado, esto se da si las casillas con minas estan 
	 * marcadas con bandera y el resto destapadas
	 * 
	 * @return true si se ha ganado
	 */
	public boolean win() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].getValue() != -1 && !board[i][j].isOpen()) {
					return false;
				}
				else if(board[i][j].getValue() == -1 && !board[i][j].hasFlag()) {
					return false;
				}
				if(this.isExplode()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Metodo que destapa una casilla y desencadena la accion correspondiente,
	 * 		si esta marcada con una bandera no la abre
	 * 
	 * @param x
	 * @param y
	 */
	public void stepOn(int x, int y) {
		if(board[x][y].hasFlag()) {
			//No se puede abrir una casilla que tenga bandera
		}
		else if (!board[x][y].isOpen() && board[x][y].getValue() > 0) {
			board[x][y].open();
		}
		else if(!board[x][y].isOpen() && board[x][y].getValue() == 0) {
   			((ClearAction)board[x][y].getAccionAsociada()).activate();
		}
		else if(!board[x][y].isOpen() && board[x][y].getValue() == -1) {
			board[x][y].open();
			((BlowUpAction)board[x][y].getAccionAsociada()).activate();
		}
		
	}
	
	/**
	 * Metodo que marca una casilla
	 * 
	 * @param x
	 * @param y
	 */
	public void flag(int x, int y) {
		if (!board[x][y].isOpen() && !board[x][y].hasFlag()) {
			board[x][y].markFlag();
		}
	}
	
	/**
	 * Metodo que desmarca una casilla
	 * 
	 * @param x
	 * @param y
	 */
	public void unFlag(int x, int y) {
		if (!board[x][y].isOpen() && board[x][y].hasFlag()) {
			board[x][y].unmarkflag();
		}
	}
	
	/**
	 * Metodo que desvela todo el tablero
	 */
	public void unveil() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j].open();
			}
		}
	}
	
	/**
	 * Metodo que calcula y devuelve el numero de banderas que faltan por colocar
	 * 
	 * @return numero de banderas que faltan por colocar
	 */
	public int getFlagsLeft() {
		int bandePuestas = getBanderasPuestas();
		return  (((int)(board.length * board.length) * getPercentage())/100 - bandePuestas);
	}

	/**
	 * Metodo que devuelve el numero de minas que faltan por marcar y lo de vuelve
	 * 
	 * @return numero de minas marcadas
	 */
	public int getMinesLeft() {
		int minasMarcadas = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].hasFlag() && board[i][j].getValue() == -1) {
					minasMarcadas++;
				}
			}
		}
		return minasMarcadas;
	}
	
	/**
	 * Metodo que devuelve el numero de banderas puestas en el tablero
	 * 
	 * @return numero de casillas con bandera en el tablero
	 */
	public int getBanderasPuestas() {
		int banderasPuestas = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].hasFlag()) {
					banderasPuestas++;
				}
			}
		}
		return banderasPuestas;
	}
	
	/**
	 * Metodo que marca el tablero como explotado
	 */
	public void markAsExploded() {
		if(this.isExplode()) {
			this.explotado = true;
		}
	}
	
	/**
	 * Metodo que devuelve una matriz con el estado del las casillas del tablero
	 * 
	 * @return matriz de char con el estado del tablero
	 */
	 public char[][] getStatus() {
		 char[][] devolver = new char[board.length][board.length];
		 for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if(board[i][j].hasFlag()) {
						devolver[i][j] = '¶';
					}
					else if(!board[i][j].isOpen()) {
						devolver[i][j] = '#';
					}
					else if(board[i][j].getValue() == -1) {
						devolver[i][j] = '@';
					}
					else if(board[i][j].getValue() == 0) {
						devolver[i][j] = ' ';
					}
					else if (board[i][j].getValue() > 0) {
						devolver[i][j] = (char) (board[i][j].getValue() + 48);
					}
				}
		 }
		 return devolver;
	 }
	 
	 /**
	  * Metodo que devuelve el tablero (PARA TEST)
	  * 
	  * @return tablero de juego
	  */
	 public Square[][] getSquaresForTest() {
		 return board;
	 }
	 
	 /**
	  * Metodo que devuelve el numero de filas del tablero
	  * 
	  * @return numero de filas
	  */
	 public int rows() {
		 return board.length;
	 }
	 
	 /**
	  * Metodo que devuelve el numero de columnas del tablero
	  * 
	  * @return numero de columnas
	  */
	 public int cols() {
		 return board[0].length;
	 }
	 
	/**
	 * Metodo toString pero hecho segun la matriz de chars
	 * 
	 * @return String con el estado de la matriz
	 */
	 public String toStringMatrix() {
		char[][] charMatrix = this.getStatus();
		String devolver = "";
		for(int i = 0; i < charMatrix.length; i++) {
			for(int j = 0; j < charMatrix[i].length; i++) {
				if(j == 0) {
					devolver = devolver + "[ " + charMatrix[i][j] + ",";
				}
				else if(j == charMatrix.length - 1) {
					devolver = devolver + charMatrix[i][j] + "]\n";
				}
				else {
					devolver = devolver + charMatrix[i][j] + ", ";
				}
			}
		}
		return devolver;
	}
	 
	 /**
	  * Redefinicion del metodo toString en tablero de cara a usarlo
	  * para mostrar por pantalla
	  */
	 public String toString() {
		String devolver = "";
		String separador = "";
		for(int column = 0; column < board[0].length; column ++) {
			if(column == 0 ) {
				devolver = devolver +"    0" + column + "  ";
			}
			else if(column == board[0].length - 1) {
				if(column <= 9) {
					devolver = devolver +"0"+ column + "\n";
				}
				else {
					devolver = devolver + column + "\n";
				}
			}
			else if(column >= 1 && column <= 9) {
				devolver = devolver + "0"+ column + "  ";
			}
			else  {
				devolver = devolver + column + "  ";
			}
		}
		for(int i = 0; i < board[0].length; i++) {
			if(i == 0) {
				separador = separador + "   +---";
			}
			else if( i == board[0].length - 1) {
				separador = separador + "+---+\n";
			}
			else {
				separador = separador + "+---";
			}
		}
		for(int row = 0; row < board.length; row++) {
			if(row == board.length - 1) {
				devolver = devolver + separador
						+ toStringFila(row)
						+ separador;
			}
			else {
				devolver = devolver + separador    
						+ toStringFila(row);
			}
		}
		return devolver;
	}
	 
	/**
	 * Metodo que hace el toString de una fila pasada como parametro
	 * a fin de usarse de metodo auxiliar para el nuevo toString del tablero
	 * 
	 * @param filaAHacerse
	 * @return toString fila
	 */
	public String toStringFila(int filaAHacerse) {
		 String devolver = "";
		 for(int j = 0; j < board[filaAHacerse].length; j++) {
				if(j == 0) {
					if(filaAHacerse >= 0 && filaAHacerse <= 9) {
						devolver = devolver + "0" + filaAHacerse + " | ";
					} else {
						devolver = devolver + filaAHacerse + " | ";
					}
					if(board[filaAHacerse][j].isOpen()) {
						if(board[filaAHacerse][j].getValue() == -1) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() +" | ";
						}
						else if(board[filaAHacerse][j].getValue() == 0) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() + " | ";
						}
						else if(board[filaAHacerse][j].getValue() > 0) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() + " | ";
						}
					}
					else if(board[filaAHacerse][j].hasFlag()) {
						devolver = devolver + board[filaAHacerse][j].toString() + " | ";
					}
					else {
						devolver = devolver + board[filaAHacerse][j].toString() + " | ";
					}
				}
				else if(j == board[filaAHacerse].length - 1) {
					if(board[filaAHacerse][j].isOpen()) {
						if(board[filaAHacerse][j].getValue() == -1) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() + " |\n";
						}	
						else if(board[filaAHacerse][j].getValue() == 0) {
								devolver = devolver + board[filaAHacerse][j].toString() 
										+ " |\n";
						}
						else if(board[filaAHacerse][j].getValue() > 0) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() + " |\n";
						}
					}
					else if(board[filaAHacerse][j].hasFlag()) {
						devolver = devolver 
								+ board[filaAHacerse][j].toString() + " |\n";
					}
					else {
						devolver = devolver 
								+ board[filaAHacerse][j].toString() + " |\n";
					}
				}
				else if(board[filaAHacerse][j].isOpen()) {
					if(board[filaAHacerse][j].getValue() == -1) {
						devolver = devolver 
								+ board[filaAHacerse][j].toString() + " | ";
					}
					else if(board[filaAHacerse][j].getValue() == 0) {
							devolver = devolver + 
									board[filaAHacerse][j].toString() + " | ";
					}
					else if(board[filaAHacerse][j].getValue() > 0) {
						devolver = devolver + board[filaAHacerse][j].toString() + " | ";
					}
				}
				else if(board[filaAHacerse][j].hasFlag()) {
					devolver = devolver 
							+ board[filaAHacerse][j].toString() + " | ";
				}
				else {
					devolver = devolver 
							+ board[filaAHacerse][j].toString() + " | ";
				}
			}
		 return devolver;
	 }

}
