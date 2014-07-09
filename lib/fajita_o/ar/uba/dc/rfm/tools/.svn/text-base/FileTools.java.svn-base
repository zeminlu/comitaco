package ar.uba.dc.rfm.tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * This class contains functions for accessing files.
 */
public class FileTools {

	
	/**
	 * This function returns the contents of the file as a <code>String</code>.
	 * 
	 * @param filename a <code>String</code> with the path to the file to read.
	 * 
	 * @return a <code>String</code> with the contents of the file.
	 * 
	 * @throws IOException if the file does not exists, the current user has no
	 *	read permission over it or any other I/O error.
	 *
	 */
	public static String readFile(String filename) throws IOException {
		StringBuilder builder = new StringBuilder();
		RandomAccessFile reader = new RandomAccessFile(filename, "r");
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append('\n');
		}
		reader.close();
		return builder.toString();
	}
	
	
	/**
	 * This function writes the content of a <code>String</code> to a file.
	 * 
	 * @param filename a <code>String</code> with the path to the file being
	 *	written. If it does't exists it will be created.
	 *
	 * @param contents a <code>String</code> with the text to write.
	 * 
	 * @throws IOException if an I/O error occurred.
	 * 
	 */
	public static void writeFile(String filename, String contents) throws IOException {
		File file = new File(filename);
		if (file.exists())
			file.delete();
		file.getParentFile().mkdirs();
		file.createNewFile();
		RandomAccessFile writer = new RandomAccessFile(file, "rw");
		writer.writeBytes(contents);
		writer.close();
	}
	
}
