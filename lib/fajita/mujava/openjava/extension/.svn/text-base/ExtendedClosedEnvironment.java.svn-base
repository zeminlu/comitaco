package mujava.openjava.extension;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import openjava.mop.ClosedEnvironment;
import openjava.mop.Environment;

public class ExtendedClosedEnvironment extends ClosedEnvironment {

	public ExtendedClosedEnvironment(Environment env) {
		super(env);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAccessibleVariables() {
		Enumeration<String> e = (Enumeration<String>) symbol_table.keys();
		List<String> v = new LinkedList<String>();
		while (e.hasMoreElements()) {
			v.add(e.nextElement());
		}
		return v;
	}
	
}
