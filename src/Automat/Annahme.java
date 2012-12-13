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

	public void Flasche_auswerfen() {

		// warte Zeit �ndern in 10 sek.
		workerThread.setTimeout(1000);

		// warte Thread starten
		workerThread.run();

		// Laufband R�ckwerts starten
		m_VorderesLaufband.rueckwerts();

		// sicherstellen dass die flasche am ausgang liegt und bei evtl.
		// reinfassen nicht gestoppt wird
		while (s_EingangsLichtschranke.read()
				&& !s_JustierungLichtschranke.read()
				&& !s_AusgangsLichtschranke.read() && workerThread.isAlive())
			;

		// wenn wartThread aktive: beenden
		if (workerThread.isAlive()) {
			workerThread.interrupt();
		}

		// warte Zeit �ndern in 400ms
		workerThread.setTimeout(400);

		// warte Thread starten
		workerThread.run();

		// nachlauf f�r das Laufband, damit der Flaschenkopf ein St�ck tausguckt
		while (workerThread.isAlive())
			;

		// wenn wartThread aktive: beenden
		if (workerThread.isAlive()) {

			workerThread.interrupt();
		}

		// Band abschalten
		m_VorderesLaufband.stop();

		return;
	}

	/**
	 * Spricht alle Sensoren Aktoren an welche zum Positionieren ben�tigt werden
	 * 
	 * @return true wenn alles geklappt hat, false wenn flasche zur�ckgegeben
	 *         wird (Fehlerfall)
	 */
	public boolean Flasche_positionieren() {

		// warte Zeit �ndern in 10 sek.
		workerThread.setTimeout(1000);

		// warte Thread starten
		workerThread.run();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();

		// sicherstellen dass die flasche an justierung angekommen ist
		while (s_JustierungLichtschranke.read() && workerThread.isAlive())
			;

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
	 * Leitet die Flasche zum n�chten Modul weiter
	 * 
	 * @return true wenn alles geklappt hat, false bei misserfolg
	 */
	public boolean flascheWeiterLeiten() {

		// warte Zeit �ndern in 10 sek.
		workerThread.setTimeout(1000);

		// warte Thread starten
		workerThread.run();

		// Band einschalten
		m_VorderesLaufband.vorwaerts();

		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (!s_JustierungLichtschranke.read()
				&& !s_AusgangsLichtschranke.read() && workerThread.isAlive())
			;

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
	 * Dreh die Flasche immer ein st�ck nach rinks
	 */
	public void flascheDrehenLinks(int timeMS) {

		// warte Zeit �ndern in timeMS
		workerThread.setTimeout(timeMS);

		// warte Thread starten
		workerThread.run();

		// Band einschalten
		// vorwaerts == linksherum
		// rueckwerts == rechtsherum
		m_DrehLaufband.vorwaerts();

		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (workerThread.isAlive())
			;

		// Band abschalten
		m_VorderesLaufband.stop();
	}

	/**
	 * Dreh die Flasche immer ein st�ck nach rechts
	 */
	public void flascheDrehenRechts(int timeMS) {

		// warte Zeit �ndern in timeMS
		workerThread.setTimeout(timeMS);

		// warte Thread starten
		workerThread.run();

		// Band einschalten
		// vorwaerts == linksherum
		// rueckwerts == rechtsherum
		m_DrehLaufband.rueckwerts();

		// sicherstellen dass die flasche nicht an justierlichtschranke und
		// nicht an Ausgangslichtschranke mehr ist
		while (workerThread.isAlive())
			;

		// Band abschalten
		m_VorderesLaufband.stop();
	}

	public boolean getEingangsLichtschranke(){
		return s_EingangsLichtschranke.read();
	}
}
