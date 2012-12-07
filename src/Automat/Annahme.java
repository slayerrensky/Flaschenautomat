package Automat;

public class Annahme {

//Variablen
	
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	
	private Sensor s_EingangsLichtschranke;
	private Sensor s_JustierungLichtschranke;
	private Sensor s_AusgangsLichtschranke;

//Konstruktor
	
	public Annahme(){
	
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		s_EingangsLichtschranke = new Sensor(Adressen.Eingangslichtschranke.ordinal());
		s_JustierungLichtschranke = new Sensor(Adressen.Justierlichtschranke.ordinal());
		s_AusgangsLichtschranke = new Sensor(Adressen.Ausgangslichtschranke.ordinal());
		}
	
//Methoden
	
	
	public void Flasche_auswerfen(){
		
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
