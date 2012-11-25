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
	
	//private Scanner m_Scanner;
	private Sensor s_EingangsLichtschranke;
	private Sensor s_JstierungLichtschranke;
	private Sensor s_AusgangLichtschranke;
	private Sensor s_AuswahlklappeEingangLichtschranke;
	private Sensor s_MehrwegBeaelterLichtschranke;
	private Sensor s_PetBeaelterLichtschranke;

	public Automat(){
		m_VorderesLaufband = new Laufband(Adressen.LaufbandEingang.ordinal());
		m_HinteresLaufband = new Laufband(Adressen.LaufbandAusgang.ordinal());
		m_DrehLaufband = new Laufband(Adressen.LaufbandDrehen.ordinal());
		m_Auswahlklappe = new Auswahlklappe(Adressen.Auswahlklappe.ordinal());
				
		s_EingangsLichtschranke = new Sensor(Adressen.Eingangslichtschranke.ordinal());
		s_JstierungLichtschranke = new Sensor(Adressen.Justierlichtschranke.ordinal());
		s_AusgangLichtschranke = new Sensor(Adressen.Ausgangslichtschranke.ordinal());
		s_AuswahlklappeEingangLichtschranke = new Sensor(Adressen.AuswahlklappeEingangslichtschranke.ordinal());
		s_MehrwegBeaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankeMehrweg.ordinal());
		s_PetBeaelterLichtschranke = new Sensor(Adressen.UebergabelichtschrankePET.ordinal());
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