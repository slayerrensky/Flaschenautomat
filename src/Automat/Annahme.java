package Automat;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Annahme {

//Variablen
	private HWSimulation HWaccess;
	
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	
	private Sensor s_EingangsLichtschranke;
	private Sensor s_JustierungLichtschranke;
	private Sensor s_AusgangsLichtschranke;
	
	private ParallelWarteClass workerThread;

//Konstruktor
	
	public Annahme(){
		HWaccess = HWSimulation.getInstance();
	
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		
		s_EingangsLichtschranke = new Sensor(Adressen.Eingangslichtschranke.ordinal());
		s_JustierungLichtschranke = new Sensor(Adressen.Justierlichtschranke.ordinal());
		s_AusgangsLichtschranke = new Sensor(Adressen.Ausgangslichtschranke.ordinal());
		
		workerThread = new ParallelWarteClass(10000);
		}
	
//Methoden
	
	
	public void Flasche_auswerfen(){
		
		// warte Thread starten
		workerThread.run();
		
		//Laufband Rückwerts starten
		HWaccess.write(Adressen.LaufbandEingang.ordinal(), -1);
		
		// sicherstellen dass die flasche am ausgang liegt und bei evtl. reinfassen nicht gestoppt wird
		while(s_EingangsLichtschranke.read() && !s_JustierungLichtschranke.read() && !s_AusgangsLichtschranke.read() && workerThread.isAlive());
				
		// wenn wartThread aktive: beenden
		if(workerThread.isAlive()){
			workerThread.interrupt();			
		}
		
		// warte Zeit ändern
		workerThread.setTimeout(400);
		
		// warte Thread starten
		workerThread.run();
		
		// nachlauf für das Laufband, damit der Flaschenkopf ein Stück tausguckt
		while(workerThread.isAlive());
		
		// wenn wartThread aktive: beenden
		if (workerThread.isAlive()){
			
			workerThread.interrupt();
		}
		
		// Band abschalten
		HWaccess.write(Adressen.LaufbandEingang.ordinal(), 0);
		
		return;
	}
	
	/**
	 * Spricht alle Sensoren Aktoren an welche zum Positionieren benötigt werden
	 * @return true wenn alles geklappt hat, false wenn flasche zurückgegeben wird (Fehlerfall)
	 */
	public boolean Flasche_positionieren(){
		
		return false;
	}
	
	public boolean flascheWeiterLeiten(){
		
		m_VorderesLaufband.einschalten();
		// gegebenfalls einbauen das lichtschranke abgeprüft wird.
		return true;
		
	}
	
	public boolean getAusgangsLichtschranke(){
	
		return false;
	}
	
	public boolean getEingangsLichtschranke(){
		
		return false;
	}
	
	public boolean getJustierLichtschranke(){
		
		return false;
	}
	
}
