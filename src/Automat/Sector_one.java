package Automat;

//import Automat.Adressen;
//import Automat.Laufband;
//import Automat.Lichtschranke;
//import Automat.Scanner;

public class Sector_one {

//Variablen
	
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	
	private Scanner m_Scanner;
	
	private Sensor s_EingangsLichtschranke;
	private Sensor s_JstierungLichtschranke;
	private Sensor s_AusgangLichtschranke;

//Konstruktor
	
	public Sector_one(){
	
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		s_EingangsLichtschranke = new Sensor(Adressen.Eingangslichtschranke.ordinal());
		s_JstierungLichtschranke = new Sensor(Adressen.Justierlichtschranke.ordinal());
		s_AusgangLichtschranke = new Sensor(Adressen.Ausgangslichtschranke.ordinal());
		}
	
//Methoden
	
	public void flasch_erkenneStart(){
		
	}
	
	public void flasch_erkenneStop(){
		
	}
	
	public void Flasche_auswerfen(){
		
	}
	
//	@Parameter CODE	
	public void Flasche_erkannt(int code){
		
	}
	
	public void Flasche_positionieren(){
		
	}
	
	public boolean getAusgangslichtschranke(){
	
		return false;
	}
	
	public boolean getEingangslichtschranke(){
		
		return false;
	}
	
	public boolean getJustierlichtschranke(){
		
		return false;
	}
	
}
