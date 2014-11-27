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
	private int readingLineCount = 0;
	public TreeMap<Integer, Pair<Integer, Integer>> parsedLinesMap;
	public TreeMap<Integer, Integer> reversedLinesMap;

	public MarkParser(String fileName) {
		this.fileName = fileName;
	}

	public TreeMap<Integer, Pair<Integer, Integer>> parse()
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				new File(fileName)));
		String currentLine = readLine(br);
		int originalJavaLine = 1;
		parsedLinesMap = new TreeMap<Integer, Pair<Integer, Integer>>();
		int javaLineStart = 0;
		int parserState = 0;
		boolean foundFirst = false;
		while (currentLine != null) {
			if (currentLine.equals("and")
					&& (parserState == 0 || parserState == 2)) {
				parserState++;
			} else if (currentLine.contains("roops_core_objects_BugLineMarker_mark_0")
					&& (parserState == 1)) {
				parserState++;
			} else {
				// es una linea posta
				parserState = 0;
			}
			if (parserState == 2) {
				// separacion de dos lineas
				if (!foundFirst) {
					foundFirst = true;
				} else {
					parsedLinesMap.put(originalJavaLine + SequencerLineMapper.lineBegginingMethod, new Pair<Integer, Integer>(
							javaLineStart, readingLineCount - 3));
					originalJavaLine++;
				}
				javaLineStart = readingLineCount + 1;
			}
			if (foundFirst) {
				parsedLinesMap.put(originalJavaLine + SequencerLineMapper.lineBegginingMethod, new Pair<Integer, Integer>(javaLineStart,
						readingLineCount));
			}
			currentLine = readLine(br);
		}
		return parsedLinesMap;
	}

	public Integer getOriginalLine(int line) {
		if (parsedLinesMap == null) {
			throw new IllegalAccessError("Should call parse first");
		}
		if (reversedLinesMap == null) buildReversedMap();
		Integer i = reversedLinesMap.get(line);
		if (i == null) return -1;
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
		readingLineCount++;
		String s = bf.readLine();
		if (s != null) {
			s = s.trim();
		}
		return s;
	}

	public static void main(String[] args) {
		MarkParser mp = new MarkParser("/Users/framundo/output.als");
		try {
			Map<Integer, Pair<Integer, Integer>> m = mp.parse();
			for (Entry<Integer, Pair<Integer, Integer>> e : m.entrySet()) {
				System.out.println(e.getKey() + ":");
				System.out.println("\t " + e.getValue().a + " - "
						+ e.getValue().b);
			}
			System.out.println(mp.getOriginalLine(3));
			System.out.println(mp.getOriginalLine(30));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
