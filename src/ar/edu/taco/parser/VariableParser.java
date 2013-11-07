package ar.edu.taco.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableParser {

	/**
	 * @author concoMB
	 * Devuelve un multimapa en donde la clave es el numero de linea donde
	 * aparece la variable @code{var} y como valor las variables que aparecen en
	 * dicha linea
	 * 
	 */
	public static Map<Integer, List<Integer>> parse(File file, int var)
			throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("No se pudo abrir el archivo ='(");
			throw e;
		}
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		String line;
		String myVar = String.valueOf(var) + ",";
		int i = 0;
		try {
			while ((line = reader.readLine()) != null) {
				// TODO no se bien como es el formato del archivo... cada numero tiene una coma si o si?
				if (line.contains(myVar)) {
					List<Integer> list = new ArrayList<Integer>();
					String[] lineVars = line.split(", ");
					for(String v : lineVars) {
						try{
						list.add(Integer.parseInt(v));
						} catch (NumberFormatException e) {
							// Do Nothing
						}
					}
					map.put(i, list);
				}
				i++;
			}
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo ='(");
			reader.close();
			throw e;
		}
		reader.close();
		return map;
	}
}
