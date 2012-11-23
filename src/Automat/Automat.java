package Automat;
/**
 * @author Dennis
 * @version 1.0
 * @created 12-Okt-2012 13:53:38
 */
public class Automat {

	private Auswahlklappe m_Auswahlklappe;
	private Laufband m_VorderesLaufband;
	private Laufband m_DrehLaufband;
	private Laufband m_HinteresLaufband;
	
	private Scanner m_Scanner;
	private Lichtschranke s_EingangsLichtschranke;
	private Lichtschranke s_JstierungLichtschranke;
	private Lichtschranke s_AusgangLichtschranke;
	private Lichtschranke s_AuswahlklappeEingangLichtschranke;
	private Lichtschranke s_MehrwegBeaelterLichtschranke;
	private Lichtschranke s_PetBeaelterLichtschranke;

	public Automat(){
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
		m_Scanner = new Scanner(Adressen.Scanner.ordinal());
		s_EingangsLichtschranke = new Lichtschranke(Adressen.Eingangslichtschranke.ordinal());
		s_JstierungLichtschranke = new Lichtschranke(Adressen.Justierlichtschranke.ordinal());
		s_AusgangLichtschranke = new Lichtschranke(Adressen.Ausgangslichtschranke.ordinal());
		s_AuswahlklappeEingangLichtschranke = new Lichtschranke(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
		s_MehrwegBeaelterLichtschranke = new Lichtschranke(Adressen.UebergabelichtschrankeMehrweg.ordinal());
		s_PetBeaelterLichtschranke = new Lichtschranke(Adressen.UebergabelichtschrankePET.ordinal());
	}


	public void flasch_erkenneStart(){

	}

	public void flasch_erkenneStop(){

	}

	public void Flasche_auswerfen(){

	}

	/**
	 * 
	 * @param code
	 */
	public void Flasche_erkannt(int code){

	}

	public void Flasche_positionieren(){

	}

	public void Flasche_weiterleiten(){

	}

	/**
	 * 
	 * @param ID
	 */
	public void getSensorStatus(int ID){

	}

	public boolean getEingangslichtschranke(){
		return false;
	}

	public boolean getAusgangslichtschranke(){
		return false;
	}

	public boolean getJustierlichtschranke(){
		return false;
	}

	public boolean getUebergabelichtschrankeMehrweg(){
		return false;
	}

	public boolean getUebergabelichtschrankePET(){
		return false;
	}

	public boolean getUebergabeichtschrankeMehrweg(){
		return false;
	}

	public boolean getUebergabeichtschrankePET(){
		return false;
	}

}