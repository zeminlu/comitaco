package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkMaker {

	private static final String MARKER_CLASS = "public class BugLineMarker {public BugLineMarker() {}	public void mark() {} }";
	private static final String NEW_MARKER = "BugLineMarker __marker__ = new BugLineMarker();\n";
	private static final String MARK = "__marker__.mark();\n";
	
	private String fileName;
	private String methodName;

	public MarkMaker(String fileName, String methodName) {
		this.fileName = fileName;
		this.methodName = methodName;
	}

	public void mark() throws IOException {
		PrintWriter writer = new PrintWriter("./tempFile.java", "UTF-8");
		PrintWriter copyWriter = new PrintWriter("./originalCopy.java", "UTF-8");
		BufferedReader bf = new BufferedReader(new FileReader(
				new File(fileName)));
		String prevLine = null;
		String line = bf.readLine();
		boolean insideMethod = false;
		boolean foundReturn = false;
		int curlyBraces = 0;
		while (line != null) {
			copyWriter.write(line + "\n");
			if (insideMethod) {
				if (!foundReturn && lineIsEnded(prevLine) && !lineIsComment(prevLine)) {
					writer.write(MARK);
				}
				foundReturn = lineIsReturn(line);

				int curly = occurrencesOfCurlyBraces(line);
				if (curly != 0) {
					foundReturn = false;
				}
				curlyBraces += curly;
			}
			writer.write(line + "\n");
			// chequeo si estoy entrando al metodo
			if (!insideMethod
					&& (line.contains(methodName) && line.contains("{"))) {
				System.out.println("Found method");
				insideMethod = true;
				writer.write(NEW_MARKER);
//				writer.write(MARK);
				curlyBraces = 1;
			}
			// chequeo si se termino el metodo
			if (curlyBraces == 0 && insideMethod) {
				System.out.println("End of method");
				insideMethod = false;
				System.out.println("Escribo la clase inner");
//				writer.write(MARKER_CLASS);
			}
			prevLine = line;
			line = bf.readLine();
		}
		bf.close();
		writer.close();
		copyWriter.close();

		// transpaso lo que escribi en el temp al orginal
		System.out.println("Traspassing");

		bf = new BufferedReader(new FileReader(new File("./tempFile.java")));
		writer = new PrintWriter(fileName, "UTF-8");
		line = bf.readLine();
		while (line != null) {
			writer.write(line + "\n");
			line = bf.readLine();
		}
		bf.close();
		writer.close();
		File f = new File("./tempFile.java");
		f.delete();
	}

	private int occurrencesOfCurlyBraces(String line) {
		char[] l = line.toCharArray();
		int curly = 0;
		for (char c : l) {
			if (c == '{') {
				curly++;
			} else if (c == '}') {
				curly--;
			}
		}
		return curly;
	}
	
	private boolean lineIsEnded(String line) {
		if (line == null) return false;
		return line.contains(";") || line.contains("}") || line.contains("{");
	}
	
	private boolean lineIsComment(String line) {
		if (line == null) return false;
		String l = line.trim();
		return l.startsWith("//");
	}
	
	private boolean lineIsReturn(String line) {
		if (line == null) return false;
		return line.contains("return ") || line.contains("return;");
	}

	public static void main(String[] args) {
		MarkMaker mm = new MarkMaker(
				"/Users/concoMB/pf/comitaco/tests/roops/core/objects/SinglyLinkedList.java",
				"contains");
		try {
			mm.mark();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
