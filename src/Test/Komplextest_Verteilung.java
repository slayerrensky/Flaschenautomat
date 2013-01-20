package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.Adressen;
import Automat.FlaschenType;
import Automat.HWSimulation;
import Automat.ParallelWarteClass;
import Automat.Verteilung;

public class Komplextest_Verteilung {

	private static Verteilung simVerteilung;
	protected static HWSimulation HWaccess;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		simVerteilung = new Verteilung();
		HWaccess = HWSimulation.getInstance();
		// HWaccess.setF(new Fassade());
	}

	@Test
	public void test_Flasche_WeiterleitenMehrweg() throws InterruptedException {
		FlaschenType type = FlaschenType.Mehrweg;

		paraAblaufeFlascheWeiterleiten test_FlascheWeiterleitenHW = new paraAblaufeFlascheWeiterleiten(
				type);
		paraWeiterleitenUeberpruefen test_FlascheWeiter = new paraWeiterleitenUeberpruefen();

		test_FlascheWeiterleitenHW.start();
		test_FlascheWeiter.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				simVerteilung.Flasche_weiterleiten(type));

		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());

		// auswahlklappe == true bei Mehrweg
		assertTrue(HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}

	@Test
	public void test_Flasche_WeiterleitenPET() throws InterruptedException {
		FlaschenType type = FlaschenType.PET;

		paraAblaufeFlascheWeiterleiten test_FlascheWeiterleitenHW = new paraAblaufeFlascheWeiterleiten(
				type);
		paraWeiterleitenUeberpruefen test_FlascheWeiter = new paraWeiterleitenUeberpruefen();

		test_FlascheWeiterleitenHW.start();
		test_FlascheWeiter.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				simVerteilung.Flasche_weiterleiten(type));

		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());

		// auswahlklappe == true bei Mehrweg
		assertFalse(HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}

	@Test
	public void test_NEGATIV_WeiterleitenMehrwegn() throws InterruptedException {

		FlaschenType type = FlaschenType.Mehrweg;

		paraWeiterleitenUeberpruefen test_FlascheWeiter = new paraWeiterleitenUeberpruefen();

		test_FlascheWeiter.start();

		assertFalse("[FAILED] Flasche auswerfen returned false",
				simVerteilung.Flasche_weiterleiten(type));

		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());

		// auswahlklappe == true bei Mehrweg
		assertTrue(HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}

	@Test
	public void test_NEGATIV_WeiterleitenPET() throws InterruptedException {

		FlaschenType type = FlaschenType.PET;

		paraWeiterleitenUeberpruefen test_FlascheWeiter = new paraWeiterleitenUeberpruefen();

		test_FlascheWeiter.start();

		assertFalse("[FAILED] Flasche auswerfen returned false",
				simVerteilung.Flasche_weiterleiten(type));

		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());

		// auswahlklappe == true bei Mehrweg
		assertFalse(HWaccess.readBool(Adressen.Auswahlklappe.ordinal()));
	}

	// -------------------------------------------------------------------------------------->>>>>>>>
	// Weiterleiten

	// Ablauf
	// 1. AuswahlklappeEingangsLS muss belegt werden
	// 2. AuswahlklappeEingangsLS muss frei werden
	// 3. PET oder Mehrweg LS wird belegt
	// 4. PET oder Mehrweg LS wird frei
	public class paraAblaufeFlascheWeiterleiten extends Thread {

		private FlaschenType type;

		public paraAblaufeFlascheWeiterleiten(FlaschenType type) {
			this.type = type;
		}

		public void run() {

			// 1. Schritt
			HWaccess.write(
					Adressen.AuswahlklappeEingangslichtschranke.ordinal(), true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}

			// 2. Schritt
			HWaccess.write(
					Adressen.AuswahlklappeEingangslichtschranke.ordinal(),
					false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}

			// 3. u. 4.
			if (type == FlaschenType.Mehrweg) {
				// 3. Schritt
				HWaccess.write(
						Adressen.UebergabelichtschrankeMehrweg.ordinal(), true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// do nothing
				}

				// 4. Schritt
				HWaccess.write(
						Adressen.UebergabelichtschrankeMehrweg.ordinal(), false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// do nothing
				}
			}

			// 3. u. 4.
			if (type == FlaschenType.PET) {
				// 3. Schritt
				HWaccess.write(Adressen.UebergabelichtschrankePET.ordinal(),
						true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// do nothing
				}

				// 4. Schritt
				HWaccess.write(Adressen.UebergabelichtschrankePET.ordinal(),
						false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}
	}

	public class paraWeiterleitenUeberpruefen extends Thread {

		private boolean result;

		public paraWeiterleitenUeberpruefen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandAusgang.ordinal()) == 1)
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
				return;
			} else {
				// stopping paraWait
				paraWait.interrupt();
			}

			// ------------------>>>>>>>

			paraWait = new ParallelWarteClass(10000);

			paraWait.start();

			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandAusgang.ordinal()) == 0)
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
