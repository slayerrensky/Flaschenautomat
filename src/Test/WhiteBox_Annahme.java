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
	public void testAuswerfen() throws InterruptedException {
		
		paraAblaufeFlascheRueckgabe test_FlascheAuswerfenHW = new paraAblaufeFlascheRueckgabe();
		paraEingangslaufbandUeberpruefen test_FlascheAuswerfen = new paraEingangslaufbandUeberpruefen();

		test_FlascheAuswerfenHW.start();
		test_FlascheAuswerfen.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				model.Flasche_auswerfen());
		test_FlascheAuswerfen.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheAuswerfen.getResult());
	}
	
	@Test
	public void test_Positionieren() throws InterruptedException {
		
		paraAblaufeFlaschePosition test_FlascheAuswerfenHW = new paraAblaufeFlaschePosition();
		paraPositionierenUeberpruefen test_FlaschePos = new paraPositionierenUeberpruefen();

		test_FlascheAuswerfenHW.start();
		test_FlaschePos.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				model.Flasche_positionieren());
		test_FlaschePos.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlaschePos.getResult());
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

	public class paraEingangslaufbandUeberpruefen extends Thread {

		private boolean result;

		public paraEingangslaufbandUeberpruefen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
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
				// paraWait.interrupt();
			}

			// ------------------>>>>>>>

			paraWait = new ParallelWarteClass(10000);

			paraWait.start();

			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandEingang.ordinal()) == 0)
					&& paraWait.isAlive()) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					this.interrupt();
				}
			}

			// exit with result = false
			if (!paraWait.isAlive()) {
				interrupt();
			} else {
				// stopping paraWait
				paraWait.interrupt();
			}

			result = true;
		}

		public boolean getResult() {
			return result;
		}

	}
	
	//--------------------->>>>>>>> Positionieren

	public class paraAblaufeFlaschePosition extends Thread {

		public paraAblaufeFlaschePosition() {
		}

		public void run() {

			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), false);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), false);
		}

	}

	public class paraPositionierenUeberpruefen extends Thread {

		private boolean result;

		public paraPositionierenUeberpruefen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandEingang.ordinal()) == 1)
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
				// paraWait.interrupt();
			}

			// ------------------>>>>>>>

			paraWait = new ParallelWarteClass(10000);

			paraWait.start();

			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandEingang.ordinal()) == 0)
					&& paraWait.isAlive()) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					this.interrupt();
				}
			}

			// exit with result = false
			if (!paraWait.isAlive()) {
				interrupt();
			} else {
				// stopping paraWait
				paraWait.interrupt();
			}

			result = true;
		}

		public boolean getResult() {
			return result;
		}

	}
}
