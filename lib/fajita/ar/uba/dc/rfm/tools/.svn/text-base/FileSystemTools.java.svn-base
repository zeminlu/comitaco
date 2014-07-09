package ar.uba.dc.rfm.tools;

import java.io.File;

import ar.uba.dc.rfm.fajita.FajitaException;


/**
 * This class contains functions to modify the file system.
 */
public class FileSystemTools {
	
	/** Path separator symbol. */
	public static final String separator = "/";

	/**
	 * Deletes a directory, including all its files and sub-folders which names
	 * do not match a given regex. 
	 * 
	 * @param dirPath the path to the directory from where to erase.
	 * 
	 * @param excludeRegex the patter of the files and directories that won't
	 *	be deleted.
	 * 
	 */
	public static void deleteNotMatching(String dirPath, String excludeRegex) {
		File dir = new File(dirPath);
		if (dir != null && dir.exists())
			deleteDir(dir, excludeRegex);
	}
	
	
	/**
	 * This function recursively delete files and sub-directories which
	 * names do not match a given regex. 
	 * 
	 * @param dir the path to the directory from where to start to delete.
	 * 
	 * @param excludeRegex the patter of the files and directories that won't
	 *	be deleted.
	 *
	 */
	public static void deleteDir(File dir, String excludeRegex) {
		if (!dir.getName().matches(excludeRegex)) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++)
					deleteDir(new File(dir, children[i]), excludeRegex);
			}
			dir.delete();
		}
	}
	
	
	/**
	 * This function deletes all files and empty folders which name matches
	 * a given regex.
	 * 
	 * @param dirPath the path to the directory from where to erase.
	 * 
	 * @param patternRegex the pattern of the file names that will be deleted.
	 * 
	 */
	public static void deleteMatching(String dirPath, String patternRegex) {
		File dir = new File(dirPath);
		if (dir != null && dir.exists())
			deleteFiles(dir, patternRegex);
	}
	
	
	/**
	 * This function deletes all files and empty folders which name matches
	 * a given regex.
	 * 
	 * @param dir the path to the directory from where to start to delete.
	 * 
	 * @param patternRegex he pattern of the file names that will be deleted.
	 * 
	 */
	public static void deleteFiles(File dir, String patternRegex) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
				deleteFiles(new File(dir, children[i]), patternRegex);
		}
		if (dir.getName().matches(patternRegex)) 
			dir.delete();
	}
	
	
	/**
	 * Creates a directory.
	 * 
	 * @param dirPath the path to the directory to be created.
	 * 
	 */
	public static void createDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir == null || (!dir.exists() && !dir.mkdirs()))
			throw new FajitaException("Unable to create directory " + dirPath);
	}
	
}
