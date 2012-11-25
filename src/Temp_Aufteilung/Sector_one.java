package Temp_Aufteilung;

//import Automat.Adressen;
//import Automat.Laufband;
//import Automat.Lichtschranke;
//import Automat.Scanner;

public class Sector_one {

//Variablen
	
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	
	private Scanner m_Scanner;
	
	private Lichtschranke s_EingangsLichtschranke;
	private Lichtschranke s_JstierungLichtschranke;
	private Lichtschranke s_AusgangLichtschranke;

//Konstruktor
	
	public Sector_one(){
	
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		m_Scanner = new Scanner(Adressen.Scanner.ordinal());
		s_EingangsLichtschranke = new Lichtschranke(Adressen.Eingangslichtschranke.ordinal());
		s_JstierungLichtschranke = new Lichtschranke(Adressen.Justierlichtschranke.ordinal());
		s_AusgangLichtschranke = new Lichtschranke(Adressen.Ausgangslichtschranke.ordinal());
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
