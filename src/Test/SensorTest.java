package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Automat.HWSimulation;
import Automat.Sensor;
import Fassade.Fassade;

public class SensorTest {

	protected Sensor s = new Sensor(1);
	protected HWSimulation HWaccess = HWSimulation.getInstance();
	
	public SensorTest()
	{
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
