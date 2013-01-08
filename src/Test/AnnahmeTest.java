package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Automat.Annahme;

public class AnnahmeTest {

	Annahme a = new Annahme();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFlasche_auswerfen() {
		assertTrue("Flasche auswerfen gibt TRUE zurück", a.Flasche_auswerfen());
	}

	@Test
	public void testFlasche_positionieren() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlascheWeiterLeiten() {
		fail("Not yet implemented");
	}

}
