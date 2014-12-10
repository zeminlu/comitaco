package parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import parser.constructions.Construct;

public class JavaCodeParser {
	
	public static String CONDITION;
	
	private static final Set<Character> whiteCharacters = new HashSet<Character>();
	static {
		whiteCharacters.add(' ');
		whiteCharacters.add('\t');
		whiteCharacters.add('\n');
		whiteCharacters.add('\r');
	}
	
	private static final Set<Character> delimiterCharacters = new HashSet<Character>();
	static {
		delimiterCharacters.addAll(whiteCharacters);
		delimiterCharacters.add('(');
		delimiterCharacters.add(')');
		delimiterCharacters.add('{');
		delimiterCharacters.add('}');
		delimiterCharacters.add(':');
		delimiterCharacters.add(';');
		delimiterCharacters.add('/');
	}
	
	private String contents;
	private int index;
	private StringBuilder buffer;
	private int conditionsParsed;
	
	public JavaCodeParser(File javaFile, String condition) {
		try {
			byte[] charBuffer = new byte[(int)javaFile.length()];
			BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(javaFile));
			inputStream.read(charBuffer);
			inputStream.close();
			
			contents = new String(charBuffer);
			buffer = new StringBuilder();
			
			CONDITION = condition + "_";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String nextToken() {
		StringBuilder token = new StringBuilder();
		
		char currentChar;
		
		do {
			while (whiteCharacters.contains(currentChar = contents.charAt(index++))) {
				buffer.append(currentChar);
			}
		} while((currentChar == '/' && avoidComment()) || (currentChar == '@' && avoidAnnotation()));
		
		if (delimiterCharacters.contains(currentChar)) {
			token.append(currentChar);
			if (currentChar != '{')
				buffer.append(currentChar);
		} else {
			while (!delimiterCharacters.contains(currentChar)) {
				buffer.append(currentChar);
				token.append(currentChar);
				if (!delimiterCharacters.contains(currentChar))
					currentChar = contents.charAt(index++);
			}
			index--;
		}
		
		return token.toString();
	}
	
	public void continueTo(char target) {
		char currentChar = contents.charAt(index++);
		
		while (currentChar != target) {
			buffer.append(currentChar);
			switch (currentChar) {
				case '(':
					continueTo(')');
					break;
				case '{':
					continueTo('}');
					break;
				case '/':
					int slashIndex = buffer.length();
					if (avoidComment())
						buffer.delete(slashIndex, slashIndex+1);
					break;
				case '@':
					avoidAnnotation();
					break;
				case '\'':
				case '"':
					char oldChar = currentChar;
					char newChar = contents.charAt(index++);
					while (oldChar == '\\' || newChar != currentChar) {
						buffer.append(newChar);
						oldChar = newChar;
						newChar = contents.charAt(index++);
					}
					buffer.append(newChar);
					break;
			}
			currentChar = contents.charAt(index++);
		}
		buffer.append(currentChar);

	}
	
	public void goBackYourSelf(String token) {
		int deleteLength = token.length();
		int bufferLength = buffer.length();
		buffer.delete(bufferLength - deleteLength, bufferLength);
		index = index - deleteLength;
	}
	
	public String flush() {
		String result = buffer.toString();
		buffer.delete(0, buffer.length());
		return result;
	}
	
	public int getNumberOfConditions() {
		return conditionsParsed;
	}
	
	public int addCondition(Construct contruct) {
		return conditionsParsed++;
	}
	
	private boolean avoidComment() {
		boolean commentFound = true;
		char currentChar = contents.charAt(index++);
		if (currentChar == '/') {
			int dest = contents.indexOf("\n", index) + 1;
			buffer.append(contents.substring(index - 2, dest));
			index = dest;
		} else if (currentChar == '*') {
			int dest = contents.indexOf("*/", index) + 2;
			buffer.append(contents.substring(index - 2, dest));
			index = dest;
		} else {
			commentFound = false;
		}
		return commentFound;
	}
	
	private boolean avoidAnnotation() {
		String string = flush();
		String token = nextToken();
		buffer.delete(buffer.length() - token.length(), buffer.length());
		token = nextToken();
		if (token.equals("(")) {
			continueTo(')');
			flush(); // XXX: Avoiding annotations...
		} else {
			goBackYourSelf(token);
		}
		return true;
	}

}
