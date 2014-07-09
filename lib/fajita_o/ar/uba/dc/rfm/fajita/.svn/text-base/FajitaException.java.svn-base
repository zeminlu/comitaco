package ar.uba.dc.rfm.fajita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the exception thrown if Fajita encounters a problem. 
 */
@SuppressWarnings("serial")
public class FajitaException extends RuntimeException {
	
	/**
	 * Constructor for a <code>FajitaException</code>.
	 * 
	 * @param message a <code>String</code> with the description of the error.
	 * 
	 */
	public FajitaException(String message) {
		super(message);
	}
	
	
	/**
	 * Constructor for a <code>FajitaException</code>.
	 * 
	 * @param message a <code>String</code> with the description of the error.
	 * @param e an <code>Exception</code> with the source of the problem that
	 * triggered this exception.
	 * 
	 */
	public FajitaException(String message, Exception e) {
		super(message);
		List<StackTraceElement> list = new ArrayList<StackTraceElement>();
		list.addAll(Arrays.asList(e.getStackTrace()));
		list.addAll(Arrays.asList(this.getStackTrace()));
		StackTraceElement[] newStack = new StackTraceElement[list.size()];
		for (int i = 0; i < newStack.length; i++)
			newStack[i] = list.get(i);
		this.setStackTrace(newStack);
	}
	
}
