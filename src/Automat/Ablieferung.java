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
	private Druckknopf m_Druckknopf;
	private LinkedList<Flasche> abgelieferteFlaschen;
	private double Guthaben;
	private Fassade DieFassade;
	
	
	public Ablieferung(Fassade fassade, LinkedList ListofBottls){
		DieFassade = fassade;
		this.abgelieferteFlaschen = ListofBottls;
		m_Automat = new Automat();
		m_Anzeige = new Anzeige(fassade);
		m_BonDrucker= new BonDrucker(Adressen.BonDrucker.ordinal());
		m_Druckknopf = new Druckknopf(Adressen.Druckknopf.ordinal());
			
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