package Automat;
/**
 * @author Dennis
 * @version 1.0
 * @created 12-Okt-2012 13:53:38
 */
public class Automat {

	public Auswahlklappe m_Auswahlklappe;
	public EndbehälterMehrweg m_EndbehälterMehrweg;
	public EndbehälterPET m_EndbehälterPET;
	public Näherungssensor m_Näherungssensor;
	public DB m_DB;
	public Laufband m_Laufband;
	public Display m_Display;
	public Scanner m_Scanner;
	public Leuchte m_Leuchte;
	public Tröte m_Tröte;

	public Automat(){

	}

	/**
	 * 
	 * @param Text
	 */
	public void Display_akt(String Text){

	}

	/**
	 * 
	 * @param errno
	 */
	public void FehlerMelden(int errno){

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