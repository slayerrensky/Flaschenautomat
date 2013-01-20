package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Automat.Adressen;
import Automat.Annahme;
import Automat.HWSimulation;
import Automat.ParallelWarteClass;
import Fassade.Fassade;

public class Complex_Annahme {

	private static Annahme model;
	protected static HWSimulation HWaccess;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new Annahme();
		HWaccess = HWSimulation.getInstance();
		// HWaccess.setF(new Fassade());
	}

	@Test
	public void test_Auswerfen() throws InterruptedException {

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

		paraAblaufeFlaschePosition test_FlaschePositionierenHW = new paraAblaufeFlaschePosition();
		paraPositionierenUeberpruefen test_FlaschePos = new paraPositionierenUeberpruefen();

		test_FlaschePositionierenHW.start();
		test_FlaschePos.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				model.Flasche_positionieren());
		test_FlaschePos.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlaschePos.getResult());
	}

	@Test
	public void test_Weiterleiten() throws InterruptedException {

		paraAblaufeFlascheWeiterleiten test_FlascheWeiterleitenHW = new paraAblaufeFlascheWeiterleiten();
		// hier wird die Class paraPositionierenUeberpruefen doppelt genutzt!
		paraPositionierenUeberpruefen test_FlascheWeiter = new paraPositionierenUeberpruefen();

		test_FlascheWeiterleitenHW.start();
		test_FlascheWeiter.start();

		assertTrue("[FAILED] Flasche auswerfen returned false",
				model.flascheWeiterLeiten());
		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());
	}

	@Test
	public void test_DrehenLinks() throws InterruptedException {

		paraDrehenLinksUeberpruefen test_FlascheDrehenLinks = new paraDrehenLinksUeberpruefen();

		test_FlascheDrehenLinks.start();
		model.flascheDrehenLinks(300);

		test_FlascheDrehenLinks.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheDrehenLinks.getResult());
	}

	@Test
	public void test_DrehenRechts() throws InterruptedException {

		paraDrehenRechtsUeberpruefen test_FlascheDrehenRechts = new paraDrehenRechtsUeberpruefen();

		test_FlascheDrehenRechts.start();
		model.flascheDrehenRechts(300);

		test_FlascheDrehenRechts.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheDrehenRechts.getResult());
	}

	@Test
	public void test_EingangsLichtschranke() throws InterruptedException {

		HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), true);
		assertTrue(model.getEingangsLichtschranke());

		HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), false);
		assertFalse(model.getEingangsLichtschranke());
	}

	@Test
	public void test_NEGATIV_Positionieren() throws InterruptedException {

		paraPositionierenUeberpruefen test_FlaschePos = new paraPositionierenUeberpruefen();

		test_FlaschePos.start();

		assertFalse("[FAILED] Flasche auswerfen returned false",
				model.Flasche_positionieren());
		test_FlaschePos.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlaschePos.getResult());
	}

	@Test
	public void test_NEGATIV_Weiterleiten() throws InterruptedException {

		// hier wird die Class paraPositionierenUeberpruefen doppelt genutzt!
		paraPositionierenUeberpruefen test_FlascheWeiter = new paraPositionierenUeberpruefen();

		test_FlascheWeiter.start();

		assertFalse("[FAILED] Flasche auswerfen returned false",
				model.flascheWeiterLeiten());
		test_FlascheWeiter.join();

		assertTrue("[FAILED] Flasche auswerfen Laufband war nicht an.",
				test_FlascheWeiter.getResult());
	}

	// -------------------------------------------------------------------------------------->>>>>>>>
	// Rueckgabe

	public class paraAblaufeFlascheRueckgabe extends Thread {

		public paraAblaufeFlascheRueckgabe() {
		}

		public void run() {

			HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Eingangslichtschranke.ordinal(), true);
			try {
				Thread.sleep(500);
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
				return;
			} else {
				// stopping paraWait
				paraWait.interrupt();
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

	// -------------------------------------------------------------------------------------->>>>>>>>
	// Positionieren

	public class paraAblaufeFlaschePosition extends Thread {

		public paraAblaufeFlaschePosition() {
		}

		public void run() {

			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}
			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), true);
			try {
				Thread.sleep(500);
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
				return;
			} else {
				// stopping paraWait
				paraWait.interrupt();
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

	// -------------------------------------------------------------------------------------->>>>>>>>
	// Weiterleiten

	// Ablauf
	// 1. JustierLS muss frei werden
	// 2. HintereLS wird belegt
	// 3. HintereLS wird wieder frei
	// a. in dieser zeit muss das vordere Laufband angegangen sein und wieder
	// aus
	public class paraAblaufeFlascheWeiterleiten extends Thread {

		public paraAblaufeFlascheWeiterleiten() {
		}

		public void run() {

			// 1. Schritt
			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}

			// 2. Schritt
			HWaccess.write(Adressen.Justierlichtschranke.ordinal(), false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}

			// 3. Schritt
			HWaccess.write(Adressen.Ausgangslichtschranke.ordinal(), true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}

			// 4. Schritt
			HWaccess.write(Adressen.Ausgangslichtschranke.ordinal(), false);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	// --------------------------------------------------------------------------------------->>>>>>>
	// Drehe Links

	public class paraDrehenLinksUeberpruefen extends Thread {

		private boolean result;

		public paraDrehenLinksUeberpruefen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandDrehen.ordinal()) == -1)
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
				// paraWait.interrupt();
			}

			// ------------------>>>>>>>

			paraWait = new ParallelWarteClass(10000);

			paraWait.start();

			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandDrehen.ordinal()) == 0)
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

	// --------------------------------------------------------------------------------------->>>>>>>
	// Drehe Links

	public class paraDrehenRechtsUeberpruefen extends Thread {

		private boolean result;

		public paraDrehenRechtsUeberpruefen() {
			result = false;
		}

		public void run() {

			ParallelWarteClass paraWait = new ParallelWarteClass(10000);

			paraWait.start();
			// warte auf laufband rueckwerts oder timeout 10 Sek.
			while (!(HWaccess.readInt(Adressen.LaufbandDrehen.ordinal()) == 1)
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
			while (!(HWaccess.readInt(Adressen.LaufbandDrehen.ordinal()) == 0)
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
