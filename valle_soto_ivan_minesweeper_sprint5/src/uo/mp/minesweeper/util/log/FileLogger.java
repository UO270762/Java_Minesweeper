package uo.mp.minesweeper.util.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements SimpleLogger  {

	private static String nombreFichero;
	
	@SuppressWarnings("static-access")
	public FileLogger(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	/**
	 * Sends the string received as message to the log prefixing it with 
	 * a timestamp 
	 * @param message
	 */
	@Override
	public void log(String message) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero,true));
			try {
				fichero.write(message);
				fichero.newLine();
			} finally {
				fichero.close();
			}
		} catch (IOException ioe ) {
			throw new RuntimeException(ioe);
		}
	}
}
