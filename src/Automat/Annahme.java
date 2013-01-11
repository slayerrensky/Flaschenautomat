package Automat;

public class Annahme {

	// Variablen
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;

	private Sensor s_EingangsLichtschranke;
	private Sensor s_JustierungLichtschranke;
	private Sensor s_AusgangsLichtschranke;

	private ParallelWarteClass workerThread;

	// Konstruktor

	public Annahme() {
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());

		s_EingangsLichtschranke = new Sensor(
				Adressen.Eingangslichtschranke.ordinal());
		s_JustierungLichtschranke = new Sensor(
				Adressen.Justierlichtschranke.ordinal());
		s_AusgangsLichtschranke = new Sensor(
				Adressen.Ausgangslichtschranke.ordinal());

		workerThread = new ParallelWarteClass(10000);
	}

	// Methoden

	public boolean Flasche_auswerfen() {
		System.out.println("[Flasche_auswerfen] enter");
		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(10000);

		// warte Thread starten
		workerThread.start();

		// Laufband Rückwerts starten
		m_VorderesLaufband.rueckwerts();

		// sicherstellen dass die flasche am ausgang liegt und bei evtl.
		// reinfassen nicht gestoppt wird
		while (!s_EingangsLichtschranke.read() && workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				return false;
			}
		}

		// wenn wartThread aktive: beenden
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		}else{
			System.out.println("[Flasche_auswerfen] timeout!");
			return false;
		}

		// warte Zeit ändern in 400ms
		workerThread = new ParallelWarteClass(400);

		// warte Thread starten
		workerThread.start();
		
		System.out.println("[Flasche_auswerfen] starte nachlauf");
		// nachlauf für das Laufband, damit der Flaschenkopf ein Stück rausguckt
		while (workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				return false;
			}
		}
		
		System.out.println("[Flasche_auswerfen] band stop");
		// Band abschalten
		m_VorderesLaufband.stop();

		System.out.println("[Flasche_auswerfen] exit true");
		return true;
	}

	/**
	 * Spricht alle Sensoren Aktoren an welche zum Positionieren benötigt werden
	 * 
	 * @return true wenn alles geklappt hat, false wenn flasche zurückgegeben
	 *         wird (Fehlerfall)
	 */
	public boolean Flasche_positionieren() {
		System.out.println("[Flasche_positionieren] enter");

		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(10000);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();

		// sicherstellen dass die flasche an justierung angekommen ist
		while (!s_JustierungLichtschranke.read() && workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

		// Band abschalten
		m_VorderesLaufband.stop();

		// wenn wartThread aktive: beenden
		// else zeit abgelaufen: return false
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			System.out.println("[Flasche_positionieren] exit false");
			return false;
		}
		System.out.println("[Flasche_positionieren] exit true");
		return true;
	}

	/**
	 * Leitet die Flasche zum nächten Modul weiter
	 * 
	 * @return true wenn alles geklappt hat, false bei misserfolg
	 */
	public boolean flascheWeiterLeiten() {
		System.out.println("[flascheWeiterLeiten] enter");

		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(10000);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();
		System.out.println("[flascheWeiterLeiten] warte auf justier releaser");

		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (s_JustierungLichtschranke.read() && workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}
		System.out
				.println("[flascheWeiterLeiten] warte auf ausgangs lichschranke");

		workerThread = new ParallelWarteClass(10000);
		workerThread.start();

		while (!s_AusgangsLichtschranke.read() && workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}
		workerThread = new ParallelWarteClass(10000);
		workerThread.start();
		System.out.println("[flascheWeiterLeiten] warte ausgang release");
		while (s_AusgangsLichtschranke.read() && workerThread.isAlive()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

		// Band abschalten
		m_VorderesLaufband.stop();

		// wenn wartThread aktive: beenden
		// else zeit abgelaufen: return false
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			System.out.println("[flascheWeiterLeiten] false");
			return false;
		}
		System.out.println("[flascheWeiterLeiten] true");
		return true;
	}

	/**
	 * Dreh die Flasche immer ein stück nach rinks
	 */
	public void flascheDrehenLinks(int timeMS) {
		System.out.println("[flascheDrehenLinks] enter");
		// warte Zeit ändern in timeMS
		workerThread = new ParallelWarteClass(timeMS);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		// vorwaerts == linksherum
		// rueckwerts == rechtsherum
		m_DrehLaufband.rueckwerts();

		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (workerThread.isAlive())
			;

		// Band abschalten
		m_DrehLaufband.stop();
	}

	/**
	 * Dreh die Flasche immer ein stück nach rechts
	 */
	public void flascheDrehenRechts(int timeMS) {
		System.out.println("[flascheDrehenRechts] enter");
		
		// warte Zeit ändern in timeMS
		workerThread = new ParallelWarteClass(timeMS);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		// vorwaerts == linksherum
		// rueckwerts == rechtsherum
		m_DrehLaufband.vorwaerts();
		System.out.println("[flascheDrehenRechts] band gestartet");
		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (workerThread.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

		// Band abschalten
		m_DrehLaufband.stop();
		System.out.println("[flascheDrehenRechts] flasche gedreht");
	}

	public boolean getEingangsLichtschranke() {
		return s_EingangsLichtschranke.read();
	}
}
