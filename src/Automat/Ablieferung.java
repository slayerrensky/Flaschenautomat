package Automat;
import java.util.LinkedList;
import java.util.List;

import Fassade.Fassade;


/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:54
 */
public class Ablieferung {

	private boolean ButtonPressed = false;
	/**
	 * in Sekunden
	 */
	private int Timeout = 4;
	private Automat m_Automat;
	private Anzeige m_Anzeige;
	private BonDrucker m_BonDrucker;
	private Sensor m_Druckknopf;
	private LinkedList<Flasche> abgelieferteFlaschen;
	private FlaschenZaehler m_KundenZaehler;
	private FlaschenZaehler m_TagesZaehler;
	private double Guthaben;
	private Fassade DieFassade;
	private Scanner m_Scanner;
	
	
	public Ablieferung(Fassade fassade, LinkedList<Flasche> ListofBottlesTag, LinkedList<Flasche> ListofBottlesKunde){
		DieFassade = fassade;
		m_Automat = new Automat();
		m_Anzeige = new Anzeige(fassade);
		m_BonDrucker= new BonDrucker(Adressen.BonDrucker.ordinal());
		m_Druckknopf = new Sensor(Adressen.Druckknopf.ordinal());
		//m_FlaschenZaehlerSubject = new FlaschenAbrechnungSubject();
		m_Scanner = new Scanner(Adressen.Scanner.ordinal(),10000);
		m_KundenZaehler = new FlaschenZaehler(m_Scanner,ListofBottlesTag);
		m_TagesZaehler = new FlaschenZaehler(m_Scanner,ListofBottlesKunde);
		//m_FlaschenZaehlerSubject.attach(m_KundenZaehler);
		//m_FlaschenZaehlerSubject.attach(m_TagesZaehler);
		HWLayer HWaccess = HWLayer.getInstance();
		HWaccess.write(Adressen.Leuchte_Frabe.ordinal(), 2);
		
	}

	public void AbbruchDurchButton(){

	}

	public void ablaufFlaschenablieferung(){

	}

	/**
	 * 
	 * @param FlaschenType
	 */
	public void addGuthaben(FlaschenType FlaschenType){

	}

	public void BonDrucken(){

	}

	/**
	 * 
	 * @param Flaschen
	 */
	public void calc(List ListofBottles){

	}

	/**
	 * 
	 * @param Flaschen
	 */
	public void clearList(List ListofBottles){

	}

	public void timeout(){

	}
	
	public void test(){
		m_Anzeige.FehlerMelden(0);
	}

	public void wartenAufFlasche(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

}