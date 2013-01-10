package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.Adressen;
import Automat.Annahme;
import Automat.HWSimulation;
import Automat.ParallelWarteClass;
import Fassade.Fassade;

public class WhiteBox_Annahme {

	private static Annahme model;
	protected static HWSimulation HWaccess;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new Annahme();
		HWaccess = HWSimulation.getInstance();
		HWaccess.setF(new Fassade());
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		paraAblaufeFlascheRueckgabe test_FlascheAuswerfen = new paraAblaufeFlascheRueckgabe();
		
		test_FlascheAuswerfen.start();
		assertTrue("[FAILED] Flasche auswerfen returned false",model.Flasche_auswerfen());
		
		//assertTrue("Flasche auswerfen gibt ein true zurück und ist somit Funktionstüchtig",model.flascheWeiterLeiten() == true);
	}
	
	public class paraAblaufeFlascheRueckgabe extends Thread{
		
		public paraAblaufeFlascheRueckgabe() {}
		
		public void run() {			
			
			HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), false);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), false);			
		}

	}

}
