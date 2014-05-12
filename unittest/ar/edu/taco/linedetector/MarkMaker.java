package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkMaker {

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
		String line = bf.readLine();
		boolean insideMethod = false;
		boolean foundReturn = false;
		int curlyBraces = 0;
		while (line != null) {
			copyWriter.write(line + "\n");
			if (insideMethod) {
				if (!foundReturn) {
					writer.write("__marker__.mark();\n");
				}
				foundReturn = line.contains("return ");
				
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
				writer.write("BugLineMarker __marker__ = new BugLineMarker();\n");
				curlyBraces = 1;
			}
			// chequeo si se termino el metodo
			if (curlyBraces == 0 && insideMethod) {
				System.out.println("End of method");
				insideMethod = false;
			}
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
