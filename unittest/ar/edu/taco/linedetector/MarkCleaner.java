package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkCleaner {

	private String fileName;
	
	public MarkCleaner(String fileName) {
		this.fileName = fileName;
	}
	
	public void clean() throws IOException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		BufferedReader bf = new BufferedReader(new FileReader(
				new File("./originalCopy.java")));
		String line = bf.readLine();
		while (line != null) {
			writer.append(line + "\n");
			line = bf.readLine();
		}
		writer.close();
		bf.close();
	}
	
}
