package Automat;
import java.math.BigDecimal;
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
	private Annahme m_Annahme;
	private Verteilung m_Verteilung;
	private Anzeige m_Anzeige;
	private BonDrucker m_BonDrucker;
	private Sensor m_Druckknopf;
	private LinkedList<Flasche> abgelieferteFlaschen;
	private FlaschenZaehler m_KundenZaehler;
	private FlaschenZaehler m_TagesZaehler;
	private double Guthaben;
	private Fassade DieFassade;
	private Scanner m_scanner;
	
	
	public Ablieferung(Fassade fassade, LinkedList<Flasche> ListofBottlesTag, LinkedList<Flasche> ListofBottlesKunde){
		DieFassade = fassade;
		m_Annahme = new Annahme();
		m_Verteilung = new Verteilung();
		m_Anzeige = new Anzeige(fassade);
		m_BonDrucker= new BonDrucker(Adressen.BonDrucker.ordinal());
		m_Druckknopf = new Sensor(Adressen.Druckknopf.ordinal());
		//m_FlaschenZaehlerSubject = new FlaschenAbrechnungSubject();
		m_scanner = new Scanner(Adressen.Scanner.ordinal(),10000);
		m_KundenZaehler = new FlaschenZaehler(m_scanner,ListofBottlesTag);
		m_TagesZaehler = new FlaschenZaehler(m_scanner,ListofBottlesKunde);
		//m_FlaschenZaehlerSubject.attach(m_KundenZaehler);
		//m_FlaschenZaehlerSubject.attach(m_TagesZaehler);
		HWSimulation HWaccess = HWSimulation.getInstance();
		HWaccess.write(Adressen.Leuchte_Frabe.ordinal(), 2);
		
	}

	public void AbbruchDurchButton(){

	}
	
	/**
	 * Hier ist der UseCase Ablauf für eine Flaschenablieferung niedergeschrieben.
	 */
	public void ablaufFlaschenablieferung(){

		wartenAufFlasche();
		if (m_Annahme.Flasche_positionieren())
		{
			int flaschenanzahl = m_KundenZaehler.getFlaschenListe().size();
			if (m_scanner.Scan())
			{
				//anzahl kontrollieren
				if (flaschenanzahl == (m_KundenZaehler.getFlaschenListe().size() - 1))
				{
					
				}
				else
				{
				flascheZurueckGeben();
				}
			}
			else
			{
				flascheZurueckGeben();
			}
				
		}
		else
		{
			flascheZurueckGeben();
		}
		
		
		
	}

	/**
	 * 
	 * @param FlaschenType
	 */
	public void addGuthaben(FlaschenType FlaschenType){

	}

	public void BonDrucken(){
		
	}

	public void flascheZurueckGeben()
	{
		DieFassade.simKonsolenText(0, "Flasche wird zurück gegeben.");
		m_Annahme.Flasche_auswerfen();
	}

	/**
	 * 
	 * @param Flaschen
	 */
	public void clearList(LinkedList<Flasche> ListofBottles){
		ListofBottles.clear();
	}

	public void timeout(){

	}
	
	public void test(){
		m_Anzeige.FehlerMelden(0);
	}

	public void wartenAufFlasche(){
		while (! m_Annahme.getAusgangsLichtschranke());
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

}