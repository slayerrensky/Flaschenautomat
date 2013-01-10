package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.HWSimulation;
import Automat.Sensor;
import Fassade.Fassade;

public class SensorTest {

	protected Sensor s = new Sensor(1);
	static HWSimulation HWaccess = HWSimulation.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		HWaccess.setF(new Fassade());
	}


	@Test
	public void testReadtrue() {
		
		HWaccess.write(1, true);
		assertEquals(true, s.read());
	}
	
	@Test
	public void testReadfalse() {
		HWaccess.write(1, false);
		assertEquals(false, s.read());
	}
	
}
