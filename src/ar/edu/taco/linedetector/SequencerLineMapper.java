package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.ImmutableMap;

import edu.mit.csail.sdg.alloy4.Pair;

public class SequencerLineMapper {
	
	private static final String LINE_NUMBER_COMMENT_SUFIX = " //lineNumber=";

	private TreeMap<Integer, Pair<Integer, Integer>> lineMap;
	public TreeMap<Integer, Integer> lineMapInverted;
	private String file;
	private String methodToCheck;
	static public int lineBegginingMethod;
	
	public SequencerLineMapper(String file, String methodToCheck) {
		this.file = file;
		this.methodToCheck = methodToCheck;
	}
	
	public void parse() {
		if (lineMap != null) return;
		lineMap = new TreeMap<Integer, Pair<Integer, Integer>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int currentLine = 1;
			int begginingLine = currentLine;
			boolean inMethod = false;
			while (line != null) {
				if (!inMethod && (line.contains(methodToCheck + "(") || line.contains(methodToCheck + " ("))) {
					inMethod = true;
					lineBegginingMethod = currentLine;
					begginingLine = currentLine;
				} 
				if (inMethod && line.contains(LINE_NUMBER_COMMENT_SUFIX)) {
					String[] splitted = line.split(LINE_NUMBER_COMMENT_SUFIX);
					int lineNumber = Integer.valueOf(splitted[splitted.length - 1]);
					lineMap.put(lineNumber-1, new Pair<Integer, Integer>(begginingLine, currentLine));
					begginingLine = currentLine + 1;
				}
				if (!line.contains("__marker__")) {
					currentLine++;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		invertedMap();
	}
	
	public void invertedMap() {
		if (lineMap == null) return;
		if (lineMapInverted != null) return;
		lineMapInverted = new TreeMap<Integer, Integer>();
		for (Entry<Integer, Pair<Integer, Integer>> entry : lineMap.entrySet()) {
			int a = entry.getValue().a;
			int b = entry.getValue().b;
			while (a <= b) {
				lineMapInverted.put(a, entry.getKey());
				a++;
			}
		}
	}
	
	public Integer getOriginalLine(int i) {
		if (i == -1) return null;
		return lineMapInverted.get(i);
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