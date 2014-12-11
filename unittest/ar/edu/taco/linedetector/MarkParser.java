package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.ImmutableMap;

import edu.mit.csail.sdg.alloy4.Pair;

public class MarkParser {

	private String fileName;
	public TreeMap<Integer, Pair<Integer, Integer>> parsedLinesMap;
	public TreeMap<Integer, Integer> reversedLinesMap;

	public MarkParser(String fileName) {
		this.fileName = fileName;
	}

	public TreeMap<Integer, Pair<Integer, Integer>> parse() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				new File(fileName)));
		String currentLine = readLine(br);
		Integer originalJavaLine = null;
		parsedLinesMap = new TreeMap<Integer, Pair<Integer, Integer>>();
		int lineStart = 1, lineEnd = 1;
		while (currentLine != null) {
			if (currentLine
					.contains("roops_core_objects_BugLineMarker_mark_0[throw_")) {
				if (originalJavaLine != null) {
					parsedLinesMap.put(originalJavaLine,
							new Pair<Integer, Integer>(lineStart, lineEnd));
				}
				lineStart = lineEnd + 1;
				currentLine = readLine(br);
				lineEnd++;
				originalJavaLine = Integer.valueOf(currentLine.substring(
						currentLine.lastIndexOf('l') + 1,
						currentLine.lastIndexOf(',')));
			}
			currentLine = readLine(br);
			lineEnd++;
		}
		parsedLinesMap.put(originalJavaLine, new Pair<Integer, Integer>(
				lineStart, lineEnd));
		return parsedLinesMap;
	}

	public Integer getOriginalLine(int line) {
		if (parsedLinesMap == null) {
			throw new IllegalAccessError("Should call parse first");
		}
		if (reversedLinesMap == null)
			buildReversedMap();
		Integer i = reversedLinesMap.get(line);
		if (i == null)
			return -1;
		return i;
	}

	private void buildReversedMap() {
		reversedLinesMap = new TreeMap<Integer, Integer>();
		for (Entry<Integer, Pair<Integer, Integer>> e : parsedLinesMap
				.entrySet()) {
			for (int i = e.getValue().a; i <= e.getValue().b; i++) {
				reversedLinesMap.put(i, e.getKey());
			}
		}
	}

	private String readLine(BufferedReader bf) throws IOException {
		String s = bf.readLine();
		if (s != null) {
			s = s.trim();
		}
		return s;
	}
}
