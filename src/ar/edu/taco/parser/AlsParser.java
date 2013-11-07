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

public class AlsParser {

	public static Map<String, List<Integer>> parse(File als, List<String> vars) throws IOException {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (String var : vars) {
			map.put(var, getAppereances(als, var));
		}
		return map;
	}

	private static List<Integer> getAppereances(File als, String var) throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(als));
		} catch (FileNotFoundException e) {
			System.err.println("No se pudo abrir el archivo ='(");
			throw e;
		}
		String line;
		int i = 0;
		List<Integer> appereances = new ArrayList<Integer>();
		try {
			while ((line = reader.readLine()) != null) {
				if (line.contains(var)) {
					appereances.add(i);
				}
				i++;
			}
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo ='(");
			reader.close();
			throw e;
		}
		reader.close();
		return appereances;
	}
}
