package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.HWSimulation;
import Automat.Aktor;
import Fassade.Fassade;

public class AktorTest {

	protected Aktor a = new Aktor(1);
	protected Aktor aktorUnderTest = new Aktor(2);
	static HWSimulation HWaccess = HWSimulation.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HWaccess.setF(new Fassade());
	}

	@Test
	public void testAktor() {
		
		aktorUnderTest.ausschalten();
		assertTrue(HWaccess.readBool(2));
	}
	
	@Test
	public void testAusschalten() {
		a.ausschalten();
		assertFalse(HWaccess.readBool(1));
	}

	@Test
	public void testEinschalten() {
		a.einschalten();
		assertTrue(HWaccess.readBool(1));
	}

}
