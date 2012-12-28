package Automat;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedList;
import Fassade.Fassade;


/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:54
 */
public class Ablieferung extends Thread{

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
	private BigDecimal Guthaben = new BigDecimal(0.0);
	private Fassade DieFassade;
	private Scanner m_scanner;
	private ParallelWarteClass workerThread;
	
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
		m_scanner.attach(m_KundenZaehler);
		m_scanner.attach(m_TagesZaehler);
		HWSimulation HWaccess = HWSimulation.getInstance();
		HWaccess.write(Adressen.Leuchte_Frabe.ordinal(), 2);
		workerThread = new ParallelWarteClass(1000);
	}

	public void AbbruchDurchButton(){

	}
	
	public void run()
	{
		ablaufFlaschenablieferung();
	}
	
	/**
	 * Hier ist der UseCase Ablauf f�r eine Flaschenablieferung niedergeschrieben.
	 */
	public void ablaufFlaschenablieferung(){
		
		
		while (true)
		{
			if (m_Druckknopf.read())
			{
				// Guthaben errechnen, Bon Drucken, Flaschenz�hler leeren
				
				m_KundenZaehler.reset();
			}
			DisplayAktualisieren("Warten auf Flasche.");
			wartenAufFlasche();
			if (m_Annahme.Flasche_positionieren())
			{
				int flaschenanzahl = m_KundenZaehler.getGesamtAnzahlFlaschen();
				
				int versuche; versuche = 0;
				boolean scan;
				while (!(scan = m_scanner.Scan()) && versuche <= 6)
				{
					DieFassade.simKonsolenText(0, "Ablieferung: Flasche nicht erkannt.");
					workerThread.wait(1000);
					DieFassade.simKonsolenText(0, "Ablieferung: Flasche nach rechts drehen.");
					m_Annahme.flascheDrehenRechts(2000);
					versuche++;
				}
				if (scan)
				{
					DisplayAktualisieren("Flasche erkannt, bitte warten.");
					//anzahl kontrollieren
					if (flaschenanzahl == (m_KundenZaehler.getGesamtAnzahlFlaschen() - 1))
					{
						DieFassade.simKonsolenText(0, "Ablieferung: Flasche weiterleiten.");
						if (m_Annahme.flascheWeiterLeiten())
						{
							if (m_Verteilung.Flasche_weiterleiten(m_KundenZaehler.getLastFlaschenType()))
							{
								DieFassade.simKonsolenText(0, "Ablieferung: Flasche erfolgreich abgegeben.");
								Guthaben = m_KundenZaehler.getGesamtGuthaben();
								Guthaben.setScale(2,BigDecimal.ROUND_HALF_UP);
								
								// Display Aktualisieren
								DisplayAktualisieren("Flasche gutgeschrieben.");
								
							}
							else
							{
								DieFassade.simKonsolenText(0, "Ablieferung: Flasche konnte nicht an den Endbek�lter "+
																m_KundenZaehler.getLastFlaschenType().toString() +
																" weitergeleitet werden.");
								flascheZurueckGeben();
							}
						}
						else
						{
							DieFassade.simKonsolenText(0, "Ablieferung: Flasche konnte nicht an die Auswahlklappe weitergeleitet werden.");
							flascheZurueckGeben();
						}
					}
					else
					{
						DieFassade.simKonsolenText(0, "Ablieferung: Flaschencode ist nicht im System.");
						flascheZurueckGeben();
					}
				}
				else
				{
					DieFassade.simKonsolenText(0, "Ablieferung: Flasche Code ist nicht im System.");
					flascheZurueckGeben();
				}
					
			}
			else
			{
				DieFassade.simKonsolenText(0, "Ablieferung: Flasche konnte nicht positioniert werden.");
				flascheZurueckGeben();
			}
		}
		
		
	}

	/**
	 * 
	 * @param FlaschenType
	 */
	public void addGuthaben(FlaschenType FlaschenType){

	}

	public void DisplayAktualisieren(String info)
	{
		String str = "";
		str += "Guthaben:          " + NumberFormat.getCurrencyInstance().format(Guthaben) + "\n";
		str += info;
		DieFassade.displayAktualisieren(str);
	
	}
	
	public void BonDrucken(){
		
	}

	public void flascheZurueckGeben()
	{
		DisplayAktualisieren("Falsche k�nnte nicht angenommen werden.");
		DieFassade.simKonsolenText(0, "Ablieferung: Flasche wird zur�ck gegeben.");
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
		while (! m_Annahme.getEingangsLichtschranke())
		{
			workerThread.run();
			workerThread.isAlive();
		}
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

}