package Automat;
/**
 * @author Dennis
 * @version 1.0
 * @created 12-Okt-2012 13:53:38
 */
public class Automat {

	public Auswahlklappe m_Auswahlklappe;
	public Laufband m_VorderesLaufband;
	public Laufband m_DrehLaufband;
	public Laufband m_HinteresLaufband;
	
	public Scanner m_Scanner;
	public Lichtschranke s_EingangsLichtschranke;
	public Lichtschranke s_UstierungLichtschranke;
	public Lichtschranke s_AusgangLichtschranke;
	public Lichtschranke s_AuswahlklappeEingangLichtschranke;
	public Lichtschranke s_MehrwegBeaelterLichtschranke;
	public Lichtschranke s_PetBeaelterLichtschranke;

	public Automat(){

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