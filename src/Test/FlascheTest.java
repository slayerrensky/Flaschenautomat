package Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;
import Automat.Flasche;
import Automat.FlaschenType;

public class FlascheTest {
	
	Flasche f;
	
	public FlascheTest()
	{
		f = new Flasche(FlaschenType.Mehrweg, new BigDecimal(0.20), "000");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testFlasche() {
		assertEquals(FlaschenType.Mehrweg, f.getType());
		assertEquals(new BigDecimal(0.20), f.getPfandwert());
		assertEquals(new String("000"), f.getCode());
	}

	@Test
	public void testGetCode() {
		assertEquals(new String("000"), f.getCode());
	}

	@Test
	public void testAnzahlInc() {
		int anz = f.getAnzahl();
		f.AnzahlInc();
		assertEquals("Vergleich stimmt nicht:",anz+1, f.getAnzahl());
		
	}

	@Test
	public void testAnzahlDec() {
		int anz = f.getAnzahl();
		f.AnzahlDec();
		assertEquals("Vergleich stimmt nicht:",anz-1, f.getAnzahl());
	}

	@Test
	public void testGetPfandwert() {
		assertEquals(new BigDecimal(0.20), f.getPfandwert());
	}

	@Test
	public void testGetAnzahl() {
		f.AnzahlInc();
		assertEquals("Vergleich mit 1:", 1, f.getAnzahl());
	}

	@Test
	public void testReset() {
		f.AnzahlInc();
		f.reset();
		assertEquals("Vergleich stimmt nicht:",0, f.getAnzahl());
	}

	@Test
	public void testGetType() {
		assertEquals(FlaschenType.Mehrweg, f.getType());
	}


}
