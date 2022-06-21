package uo.mp.minesweeper.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import uo.mp.minesweeper.util.ArgumentChecks;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil  {

	/**
	 * Metodo que lee un fichero y devuelve su contenido
	 * 
	 * @param inFileName
	 * @return lista de String con las lineas del fichero
	 * @throws FileNotFoundException
	 */
	public List<String> readLines(String inFileName) throws  FileNotFoundException  {
		ArgumentChecks.isTrue(inFileName.length() != 0);
		List<String> res = new LinkedList<String>();
		BufferedReader fichero = new BufferedReader(new FileReader(inFileName));
		try {
			try {
				String linea = "";
				while ( (linea = fichero.readLine()) != null) {
					res.add(linea);
				}
			} finally {
				fichero.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Metodo que guarda en un fichero (cuyo nombre recibe como parametro) una 
	 * serie de lineas String 
	 * 
	 * @param outFileName
	 * @param lines
	 * @param append
	 * @throws FileNotFoundException
	 */
	public void saveToFile(String outFileName, List<String> lines, boolean append) throws FileNotFoundException {
		ArgumentChecks.isTrue(outFileName.length() != 0);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outFileName,append));
			try {
				for(String line: lines) {
					out.write(line);
					out.newLine();
				}
			} finally {
				out.close();
			}
		} catch (IOException ioe ) {
			throw new RuntimeException(ioe);
		}
	}

}
