package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import Automat.Annahme;

public class WhiteBox_Annahme {

	private static Annahme model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new Annahme();
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		model.flascheWeiterLeiten();
		assertTrue("Flasche auswerfen gibt ein true zurück und ist somit Funktionstüchtig",model.flascheWeiterLeiten() == true);
	}

}
