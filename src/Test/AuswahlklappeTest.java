package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.Adressen;
import Automat.Auswahlklappe;
import Automat.FlaschenType;
import Automat.HWSimulation;
import Automat.Sensor;
import Fassade.Fassade;

public class AuswahlklappeTest {

	static HWSimulation HWaccess = HWSimulation.getInstance();
	protected Auswahlklappe klappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//HWaccess.setF(new Fassade());
	}

	@Test
	public void testStellenMehrweg() {
		klappe.stellen(FlaschenType.Mehrweg);
		assertTrue( HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}
	
	@Test
	public void testStellenPet() {
		klappe.stellen(FlaschenType.PET);
		assertFalse( HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}
	
	@Test
	public void testStellenCodeNichtValide() {
		klappe.stellen(FlaschenType.PET);
		klappe.stellen(FlaschenType.CodeNichtValide);
		assertFalse( HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}
}
