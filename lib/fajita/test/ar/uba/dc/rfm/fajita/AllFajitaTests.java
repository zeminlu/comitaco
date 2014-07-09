package ar.uba.dc.rfm.fajita;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FajitaJavaCodeDecoratorTest.class,
	FajitaOptionsParserTest.class,
})
public class AllFajitaTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ar.uba.dc.rfm.fajita");
		return suite;
	}

}
