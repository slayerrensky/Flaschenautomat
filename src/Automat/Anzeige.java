package Automat;

/**
 * @author Rensky
 * @version 1.0
 * @created 18-Nov-2012 16:41:59
 */
public class Anzeige {

	private Aktor_Meldung m_Troete; 
	private Aktor_Meldung m_Leuchte;
	private Display m_Display;
	
	public Anzeige(){
		m_Troete = new Aktor_Meldung(Adressen.Troete.ordinal());
		m_Leuchte = new Aktor_Meldung(Adressen.Leuchte.ordinal());
	}

	public void finalize() throws Throwable {

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

}