package Automat;

public class Sector_one {

//Variablen
	
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	
	private Sensor s_EingangsLichtschranke;
	private Sensor s_JustierungLichtschranke;
	private Sensor s_AusgangsLichtschranke;

//Konstruktor
	
	public Sector_one(){
	
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		s_EingangsLichtschranke = new Sensor(Adressen.Eingangslichtschranke.ordinal());
		s_JustierungLichtschranke = new Sensor(Adressen.Justierlichtschranke.ordinal());
		s_AusgangsLichtschranke = new Sensor(Adressen.Ausgangslichtschranke.ordinal());
		}
	
//Methoden
	
	public void flasch_erkenneStart(){
		
	}
	
	public void flasch_erkenneStop(){
		
	}
	
	public void Flasche_auswerfen(){
		
	}
	
	public void Flasche_positionieren(){
		
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
