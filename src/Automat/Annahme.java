package Automat;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

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

		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(20000);

		// warte Thread starten
		workerThread.start();

		// Laufband Rückwerts starten
		m_VorderesLaufband.rueckwerts();

		// sicherstellen dass die flasche am ausgang liegt und bei evtl.
		// reinfassen nicht gestoppt wird
		while (!s_EingangsLichtschranke.read()
				//&& !s_JustierungLichtschranke.read()
				//&& !s_AusgangsLichtschranke.read() 
				&& workerThread.isAlive())
		{
			workerThread.wait(1000);
		}

		// wenn wartThread aktive: beenden
		if (workerThread.isAlive()) {
			workerThread.interrupt();
			return false;
		}

		// warte Zeit ändern in 400ms
		workerThread = new ParallelWarteClass(1000);

		// warte Thread starten
		workerThread.start();

		// nachlauf für das Laufband, damit der Flaschenkopf ein Stück tausguckt
		while (workerThread.isAlive())
			;

		// Band abschalten
		m_VorderesLaufband.stop();
		
		while (s_EingangsLichtschranke.read())
		{
			workerThread.wait(1000);
		}

		return true;
	}

	/**
	 * Spricht alle Sensoren Aktoren an welche zum Positionieren benötigt werden
	 * 
	 * @return true wenn alles geklappt hat, false wenn flasche zurückgegeben
	 *         wird (Fehlerfall)
	 */
	public boolean Flasche_positionieren() {

		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(10000);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();
		

		// sicherstellen dass die flasche an justierung angekommen ist
		while (!s_JustierungLichtschranke.read() && workerThread.isAlive())
		{
			workerThread.wait(1000);
		}
		
		
		// Band abschalten
		m_VorderesLaufband.stop();


		// wenn wartThread aktive: beenden
		// else zeit abgelaufen: return false
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Leitet die Flasche zum nächten Modul weiter
	 * 
	 * @return true wenn alles geklappt hat, false bei misserfolg
	 */
	public boolean flascheWeiterLeiten() {

		// warte Zeit ändern in 10 sek.
		workerThread = new ParallelWarteClass(10000);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();

		
		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (s_JustierungLichtschranke.read()
				&& workerThread.isAlive())
		{
			workerThread.wait(1000);
		}
		
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}
		
		workerThread = new ParallelWarteClass(10000);
		workerThread.start();
		
		while (!s_AusgangsLichtschranke.read() && workerThread.isAlive())
		{
			workerThread.wait(1000);
		}
		
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}
		workerThread = new ParallelWarteClass(10000);
		workerThread.start();
		
		while (s_AusgangsLichtschranke.read() && workerThread.isAlive())
		{
			workerThread.wait(1000);
		}
		
		// Band abschalten
		m_VorderesLaufband.stop();

		// wenn wartThread aktive: beenden
		// else zeit abgelaufen: return false
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Dreh die Flasche immer ein stück nach rinks
	 */
	public void flascheDrehenLinks(int timeMS) {

		// warte Zeit ändern in timeMS
		workerThread =  new ParallelWarteClass(timeMS);

		// warte Thread starten
		workerThread.start();

		// Band einschalten
		// vorwaerts == linksherum
		// rueckwerts == rechtsherum
		m_DrehLaufband.vorwaerts();

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

	public boolean getEingangsLichtschranke(){
		return s_EingangsLichtschranke.read();
	}
}
