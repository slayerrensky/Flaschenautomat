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
		// fail("Not yet implemented");
		FlaschenAuswerfenTest();

		// assertTrue("Flasche auswerfen gibt ein true zurück und ist somit Funktionstüchtig",model.flascheWeiterLeiten()
		// == true);
	}
	
	public void FlaschenAuswerfenTest(){
		paraAblaufeFlascheRueckgabe test_FlascheAuswerfenHW = new paraAblaufeFlascheRueckgabe();
		paraEingangslaufbandUeberprüfen test_FlascheAuswerfen = new paraEingangslaufbandUeberprüfen();

		test_FlascheAuswerfenHW.start();
		test_FlascheAuswerfen.start();
		
		assertTrue("[FAILED] Flasche auswerfen returned false",
				model.Flasche_auswerfen());
		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheAuswerfen.getResult());
	}

	public class paraAblaufeFlascheRueckgabe extends Thread {

		public paraAblaufeFlascheRueckgabe() {
		}

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

	public class paraEingangslaufbandUeberprüfen extends Thread {

		private boolean result;

		public paraEingangslaufbandUeberprüfen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
			System.out.println("[test] laufband = ");
			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandEingang.ordinal()) == -1)
					&& paraWait.isAlive()) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					interrupt();
				}
			}

			// exit with result = false
			if (!paraWait.isAlive()) {
				interrupt();
			} else {
				// stopping paraWait
				paraWait.interrupt();
			}

			// ------------------>>>>>>>
			System.out.println("phase 2");

			paraWait = new ParallelWarteClass(10000);

			paraWait.start();

			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandEingang.ordinal()) == 0)
					&& paraWait.isAlive()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					this.interrupt();
				}
			}

			// exit with result = false
			if (!paraWait.isAlive()) {
				System.out.println("zeitabgelaufen");
				interrupt();
			} else {
				// stopping paraWait
				paraWait.interrupt();
				System.out.println("zeitabgelaufen");
			}
			
			result = true;
		}
		
		public boolean getResult(){
			return result;
		}

	}
}
