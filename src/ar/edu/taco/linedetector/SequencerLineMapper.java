package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.grammar.v3.ANTLRParser.finallyClause_return;

import edu.mit.csail.sdg.alloy4.Pair;

import com.google.common.collect.ImmutableMap;

public class SequencerLineMapper {
	
	private static final String LINE_NUMBER_COMMENT_SUFIX = " //lineNumber=";

	private static ImmutableMap<Integer, Pair<Integer, Integer>> lineMap;

	public static ImmutableMap<Integer, Pair<Integer, Integer>> parse(String file, String methodToCheck) {
		if (lineMap != null) return lineMap;
		Map<Integer, Pair<Integer, Integer>> map = new HashMap<Integer, Pair<Integer, Integer>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int currentLine = 1;
			int begginingLine = currentLine;
			boolean inMethod = false;
			while (line != null) {
				if (!inMethod && line.contains(methodToCheck)) {
					inMethod = true;
					begginingLine = currentLine;
				} 
				if (inMethod && line.contains(LINE_NUMBER_COMMENT_SUFIX)) {
					String[] splitted = line.split(LINE_NUMBER_COMMENT_SUFIX);
					int lineNumber = Integer.valueOf(splitted[splitted.length - 1]);
					map.put(lineNumber, new Pair<Integer, Integer>(begginingLine, currentLine));
					begginingLine = currentLine + 1;
				}
				line = reader.readLine();
				currentLine++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		lineMap = new ImmutableMap.Builder<Integer, Pair<Integer, Integer>>()
				.putAll(map).build();
		return lineMap;
	}

	
	public static void main(String[] args) {
		ImmutableMap<Integer, Pair<Integer, Integer>> m = SequencerLineMapper.parse("/Users/concoMB/holis.java", "contains");
		for (Entry<Integer, Pair<Integer, Integer>> e : m.entrySet()) {
			System.out.println(e.getKey() + " -> (" + e.getValue().a + ", " + e.getValue().b +")");
		}
	}
}
