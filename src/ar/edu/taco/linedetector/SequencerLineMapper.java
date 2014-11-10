package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import kodkod.engine.bool.Int;

import org.antlr.grammar.v3.ANTLRParser.finallyClause_return;

import edu.mit.csail.sdg.alloy4.Pair;

import com.google.common.collect.ImmutableMap;

public class SequencerLineMapper {
	
	private static final String LINE_NUMBER_COMMENT_SUFIX = " //lineNumber=";

	private ImmutableMap<Integer, Pair<Integer, Integer>> lineMap;
	private ImmutableMap<Integer, Integer> lineMapInverted;
	private String file;
	private String methodToCheck;
	private int lineBegginingMethod;
	
	public SequencerLineMapper(String file, String methodToCheck) {
		this.file = file;
		this.methodToCheck = methodToCheck;
	}
	
	public void parse() {
		if (lineMap != null) return;
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
					lineBegginingMethod = currentLine;
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
		invertedMap();
	}
	
	public void invertedMap() {
		if (lineMap == null) return;
		if (lineMapInverted != null) return;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Entry<Integer, Pair<Integer, Integer>> entry : lineMap.entrySet()) {
			int a = entry.getValue().a;
			int b = entry.getValue().b;
			while (a <= b) {
				map.put(a, entry.getKey());
				a++;
			}
		}
		lineMapInverted = new ImmutableMap.Builder<Integer, Integer>()
				.putAll(map).build();
	}
	
	public Integer getOriginalLine(int i) {
		if (i == -1) return null;
		return lineMapInverted.get(i + lineBegginingMethod - 1);
	}
	
	public static void main(String[] args) {
		SequencerLineMapper mapper = new SequencerLineMapper("/Users/concoMB/holis.java", "contains");
		for (Entry<Integer, Pair<Integer, Integer>> e : mapper.lineMap.entrySet()) {
			System.out.println(e.getKey() + " -> (" + e.getValue().a + ", " + e.getValue().b +")");
		}
		for (Entry<Integer, Integer> e : mapper.lineMapInverted.entrySet()) {
			System.out.println(e.getKey() + " ->" + e.getValue());
		}
	}
}
