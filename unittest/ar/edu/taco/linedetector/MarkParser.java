package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

import edu.mit.csail.sdg.alloy4.Pair;

public class MarkParser {

	private String fileName;
	private int readingLineCount = 0;
	private ImmutableMap<Integer, Pair<Integer, Integer>> parsedLinesMap;
	private ImmutableMap<Integer, Integer> reversedLinesMap;

	public MarkParser(String fileName) {
		this.fileName = fileName;
	}

	public ImmutableMap<Integer, Pair<Integer, Integer>> parse()
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				new File(fileName)));
		String currentLine = readLine(br);
		int originalJavaLine = 1;
		Map<Integer, Pair<Integer, Integer>> map = new HashMap<Integer, Pair<Integer, Integer>>();
		int javaLineStart = 0;
		int parserState = 0;
		while (currentLine != null) {
			if (currentLine.equals("and")
					&& (parserState == 0 || parserState == 4
							|| parserState == 7 || parserState == 17)) {
				parserState++;
			} else if (currentLine.equals("(")
					&& (parserState == 1 || parserState == 2
							|| parserState == 5 || parserState == 8
							|| parserState == 12 || parserState == 13)) {
				parserState++;
			} else if (currentLine.contains("___marker___1")
					&& (parserState == 3 || parserState == 15)) {
				parserState++;
			} else if (currentLine.contains("throw")
					&& (parserState == 6 || parserState == 18)) {
				parserState++;
			} else if (currentLine.contains("exit_stmt")
					&& (parserState == 9 || parserState == 19)) {
				parserState++;
			} else if (currentLine.equals(")")
					&& (parserState == 10 || parserState == 16
							|| parserState == 20 || parserState == 21)) {
				parserState++;
			} else if (currentLine.equals("or") && (parserState == 11)) {
				parserState++;
			} else if (currentLine.equals("not (") && parserState == 14) {
				parserState++;
			} else {
				// es una linea posta
				parserState = 0;
			}
			if (parserState == 21) {
				// separacion de dos lineas
				map.put(originalJavaLine, new Pair<Integer, Integer>(
						javaLineStart, readingLineCount - 21));
				javaLineStart = readingLineCount + 1;
				originalJavaLine++;
			}
			map.put(originalJavaLine, new Pair<Integer, Integer>(javaLineStart,
					readingLineCount));
			currentLine = readLine(br);
		}
		parsedLinesMap = new ImmutableMap.Builder<Integer, Pair<Integer, Integer>>()
				.putAll(map).build();
		return parsedLinesMap;
	}

	public Integer getOriginalLine(int line) {
		if (parsedLinesMap == null) {
			throw new IllegalAccessError("Should call parse first");
		}
		if (reversedLinesMap == null) {
			buildReversedMap();
		}
		return reversedLinesMap.get(line);
	}

	private void buildReversedMap() {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (Entry<Integer, Pair<Integer, Integer>> e : parsedLinesMap
				.entrySet()) {
			for (int i = e.getValue().a; i <= e.getValue().b; i++) {
				m.put(i, e.getKey());
			}
		}
		reversedLinesMap = new ImmutableMap.Builder<Integer, Integer>().putAll(
				m).build();
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
		MarkParser mp = new MarkParser("/Users/concoMB/pf/comitaco/output/output.als");
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
