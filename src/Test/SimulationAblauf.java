package Test;

import Automat.FlaschenType;
import Automat.HWSimulation;
import Automat.Adressen;
import Fassade.Fassade;

public class SimulationAblauf extends Thread {
	
	private Fassade Fassade;
	private HWSimulation HW;
	
	private FlaschenType FType;
	
	public SimulationAblauf(Fassade fassade,FlaschenType FTypeIn) {
		Fassade = fassade;
		HW = HWSimulation.getInstance();
		FType = FTypeIn;
	}
	
	
	
	public void run() {

		int tmp=0;
		
		
		Fassade.simKonsolenText(0, "Simulation einer Flasche des Typs: " + FType.toString());
		
		/* 
		 * Flasche wird eingelegt und aktiviert die vordere LS für eine Sekunde
		 */
		HW.write(Adressen.Eingangslichtschranke.ordinal(), true);
		Fassade.simKonsolenText(0, "Sim: Eingangslichschranke aktiviert durch Flasche einlegen.");
		try { Thread.sleep(1000); }
		catch(Exception e){ System.out.println(e); }
		HW.write(Adressen.Eingangslichtschranke.ordinal(), false);
		
		/*
		 * Das System sollte nun das vordere Laufband aktivieren
		 * Die Justierungslichtschranke wird aktiviert sobald
		 * die Flasche an der richtigen Position ist
		 */
		
		// hier warten auf aktivierung des Laufbandes einfügen
		
		tmp = HW.readInt(Adressen.Scanner.ordinal()).intValue();
		if(tmp > 0){ 
			
			HW.write(Adressen.Justierlichtschranke.ordinal(), true);
			Fassade.simKonsolenText(0, "Sim: Justierlichtschranke aktiviert weil vorderes Laufband. "
										+ "die Flasche richtig positioniert hat");
			HW.write(Adressen.Justierlichtschranke.ordinal(), false);
		}
		else if(tmp == 0){ Fassade.simKonsolenText(0, "Fehler: vorderes Laufband" +
				" aktiviert nicht beim Flasche einziehen."); }

		
		switch(FType){
			case Mehrweg : 
			break;
			case PET :
			break;
			case CodeNichtValide :
			break;
			case CodeUnlesbar :
				
				/*
				 *  Testen ob das Laufband dreht
				 */
				tmp=0;
				tmp=HW.readInt(Adressen.LaufbandDrehen.ordinal()).intValue();
				if( tmp != 0){
					Fassade.simKonsolenText(0, "Laufband dreht Flasche.");
				}
				else { Fassade.simKonsolenText(0, "Fehler: Dreh-Laufband aktiviert nicht"); }
				/*
				 * testen ob der scanner funktioniert
				 */
				tmp=0;
				tmp=HW.readInt(Adressen.Scanner.ordinal());
				if( tmp == -1){
					Fassade.simKonsolenText(0, "Scanner hat die flasche korrekt nicht erkannt.");
				}
				else { Fassade.simKonsolenText(0, "Fehler: Scanner hat nicht das" +
												" korrekte Ergebnis geliefert"); }
				
				/*
				 * da die Flasche nicht erkannt wurde muss das System sie wieder auswerfen.
				 */
				tmp=0;
				tmp=HW.readInt(Adressen.LaufbandEingang.ordinal()).intValue();
				if( tmp < 0){
					Fassade.simKonsolenText(0, "Laufband wirft Flasche wieder aus.");
				}
				else if(tmp == 0){ Fassade.simKonsolenText(0, "Fehler: vorderes Laufband" +
						" aktiviert nicht beim Flasche Auswerfen."); }
				
				
				
			break;
			default :
				Fassade.simKonsolenText(0, "Systemfehler: Flaschentyp nicht erkannt.");
			break;
		}
		
		
	}
	
}
